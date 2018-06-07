#include "java_generator.h"

#include "service_options.pb.h"
#include "method_options.pb.h"

#include <google/protobuf/compiler/java/java_names.h>
#include <google/protobuf/io/printer.h>
#include <google/protobuf/io/zero_copy_stream.h>

enum RequestOrResponse { REQUEST, RESPONSE };

static string EscapeJavadoc(const string& input)
{
    string result;
    result.reserve(input.size() * 2);
    
    char prev = '*';
    
    for (string::size_type i = 0; i < input.size(); i++) {
        char c = input[i];
        switch (c) {
            case '*':
                // Avoid "/*".
                if (prev == '/') {
                    result.append("&#42;");
                } else {
                    result.push_back(c);
                }
                break;
            case '/':
                // Avoid "*/".
                if (prev == '*') {
                    result.append("&#47;");
                } else {
                    result.push_back(c);
                }
                break;
            case '@':
                // '@' starts javadoc tags including the @deprecated tag, which will
                // cause a compile-time error if inserted before a declaration that
                // does not have a corresponding @Deprecated annotation.
                result.append("&#64;");
                break;
            case '<':
                // Avoid interpretation as HTML.
                result.append("&lt;");
                break;
            case '>':
                // Avoid interpretation as HTML.
                result.append("&gt;");
                break;
            case '&':
                // Avoid interpretation as HTML.
                result.append("&amp;");
                break;
            case '\\':
                // Java interprets Unicode escape sequences anywhere!
                result.append("&#92;");
                break;
            default:
                result.push_back(c);
                break;
        }
        
        prev = c;
    }
    
    return result;
}

static string FirstLineOf(const string& value)
{
  string result = value;

  string::size_type pos = result.find_first_of('\n');
  if (pos != string::npos) {
    result.erase(pos);
  }

  // If line ends in an opening brace, make it "{ ... }" so it looks nice.
  if (!result.empty() && result[result.size() - 1] == '{') {
    result.append(" ... }");
  }

  return result;
}

static string UnderscoresToCamelCase(const string& input, bool cap_next_letter)
{
    string result;
    // Note:  I distrust ctype.h due to locales.
    for (int i = 0; i < input.size(); i++) {
        if ('a' <= input[i] && input[i] <= 'z') {
            if (cap_next_letter) {
                result += input[i] + ('A' - 'a');
            } else {
                result += input[i];
            }
            cap_next_letter = false;
        } else if ('A' <= input[i] && input[i] <= 'Z') {
            if (i == 0 && !cap_next_letter) {
                // Force first letter to lower-case unless explicitly told to
                // capitalize it.
                result += input[i] + ('a' - 'A');
            } else {
                // Capital letters after the first are left as-is.
                result += input[i];
            }
            cap_next_letter = false;
        } else if ('0' <= input[i] && input[i] <= '9') {
            result += input[i];
            cap_next_letter = true;
        } else {
            cap_next_letter = true;
        }
    }
    // Add a trailing "_" if the name should be altered.
    if (input[input.size() - 1] == '#') {
        result += '_';
    }
    return result;
}

static string UnderscoresToCamelCase(const MethodDescriptor* method) {
  return UnderscoresToCamelCase(method->name(), false);
}

static string ServiceJavaPackage(const FileDescriptor* file)
{
    string result = java::ClassName(file);
    size_t last_dot_pos = result.find_last_of('.');
    if (last_dot_pos != string::npos)
    {
        result.resize(last_dot_pos);
    }
    else
    {
        result = "";
    }

    return result;
}

static string JavaPackageToDir(const string& package_name)
{
    string package_dir = package_name;
    for (size_t i = 0; i < package_dir.size(); ++i)
    {
        if (package_dir[i] == '.')
        {
            package_dir[i] = '/';
        }
    }
    if (!package_dir.empty()) package_dir += "/";

    return package_dir;
}

static void WriteServiceDocComment(const ServiceDescriptor* service, io::Printer* p)
{
    p->Print("/**\n");
    p->Print(
        " * Protobuf service {@code $fullname$}\n"
        " */\n",
        "fullname", EscapeJavadoc(service->full_name())
    );
}

static void WriteMethodDocComment(const MethodDescriptor* method, io::Printer* p)
{
    p->Print("/**\n");
    p->Print(
        " * <code>$def$</code>\n"
        " */\n",
        "def", EscapeJavadoc(FirstLineOf(method->DebugString()))
    );
}

static void GenerateGetPrototype(const ServiceDescriptor* service, RequestOrResponse which, io::Printer* p)
{
    p->Print(
        "public final com.google.protobuf.Message\n"
        "    get$request_or_response$Prototype(\n"
        "    int methodId) {\n",
        "request_or_response", (which == REQUEST) ? "Request" : "Response"
    );
    p->Indent();

    p->Print("switch(methodId) {\n");
    p->Indent();

    for (int i = 0; i < service->method_count(); i++)
    {
        const MethodDescriptor* method = service->method(i);
        map<string, string> vars;
        vars["method_id"] = to_string(method->options().GetExtension(bgs::protocol::method_options).id());
        vars["type"] = java::ClassName((which == REQUEST) ? method->input_type() : method->output_type());

        p->Print(vars,
                 "case $method_id$:\n"
                 "  return $type$.getDefaultInstance();\n"
        );
    }
    p->Print("default:\n"
             "  throw new java.lang.AssertionError(\"Can't get here.\");\n");

    p->Outdent();
    p->Print("}\n");

    p->Outdent();
    p->Print("}\n\n");
}

static void GenerateMethodSignature(const MethodDescriptor* method, io::Printer* p, bool abstract)
{
    map<string, string> vars;
    vars["deprecated"] = method->options().deprecated() ? "@java.lang.Deprecated" : "";
    vars["abstract"] = abstract ? "abstract" : "";
    vars["name"] = UnderscoresToCamelCase(method);
    vars["input"] = java::ClassName(method->input_type());
    vars["output"] = java::ClassName(method->output_type());

    p->Print(vars,
    "$deprecated$ public $abstract$ void $name$(\n"
    "    io.netty.channel.ChannelHandlerContext ctx,\n"
    "    $input$ request");    
    if (method->output_type()->name() == "NO_RESPONSE")
    {
        p->Print(")");
    }
    else
    {
        p->Print(vars,
                 ",\n"
                 "    com.google.protobuf.RpcCallback<$output$> done)");
    }
}

static void GenerateAbstractMethods(const ServiceDescriptor* service, io::Printer* p)
{
    for (int i = 0; i < service->method_count(); i++)
    {
        const MethodDescriptor* method = service->method(i);
        WriteMethodDocComment(method, p);
        GenerateMethodSignature(method, p, true);
        p->Print(";\n\n");
    }
}

static void GenerateCallMethod(const ServiceDescriptor* service, io::Printer* p)
{
    p->Print("public final void callMethod(\n"
             "    int methodId,\n"
             "    io.netty.channel.ChannelHandlerContext ctx,\n"
             "    com.google.protobuf.Message request,\n"
             "    com.google.protobuf.RpcCallback<\n"
             "      com.google.protobuf.Message> done)\n"
             "{\n"
    );
    p->Indent();

    p->Print("switch(methodId) {\n");
    p->Indent();

    for (int i = 0; i < service->method_count(); i++)
    {
        const MethodDescriptor* method = service->method(i);
        map<string, string> vars;
        vars["method_id"] = to_string(method->options().GetExtension(bgs::protocol::method_options).id());
        vars["method_name"] = UnderscoresToCamelCase(method);
        vars["input"] = java::ClassName(method->input_type());
        vars["output"] = java::ClassName(method->output_type());

        p->Print(vars,
                 "case $method_id$:\n"
                 "  this.$method_name$(ctx, ($input$)request"
        );
        if (method->output_type()->name() == "NO_RESPONSE")
        {
            p->Print(");\n");
        }
        else
        {
            p->Print(vars,
                    ",\n"
                    "  com.google.protobuf.RpcUtil.<$output$>specializeCallback(done));\n");
        }
        p->Print("  return;\n");
    }
    p->Print("default:\n"
             "  throw new java.lang.AssertionError(\"Can't get here.\");\n");

    p->Outdent();
    p->Print("}\n");

    p->Outdent();
    p->Print("}\n\n");
}

static void GenerateStub(const ServiceDescriptor* service, io::Printer* p)
{
    p->Print("public static Stub newStub() {\n"
             "  return new Stub();\n"
             "}\n\n"
    );

    p->Print("public static final class Stub extends $service_class_name$ {\n",
             "service_class_name", service->name()
    );
    p->Indent();

    for (int i = 0; i < service->method_count(); i++)
    {
        const MethodDescriptor* method = service->method(i);
        p->Print("\n");
        GenerateMethodSignature(method, p, false);
        p->Print(" {\n");
        p->Indent();

        map<string, string> vars;
        vars["method_id"] = to_string(method->options().GetExtension(bgs::protocol::method_options).id());
        p->Print(vars,
                 "com.d3emu.bnet.rpc.ConnectionHandler handler = (com.d3emu.bnet.rpc.ConnectionHandler)ctx.handler();\n"
                 "handler.sendRequest(ctx, getHash(), $method_id$, request"
        );
        if (method->output_type()->name() == "NO_RESPONSE")
            p->Print(");\n");
        else
            p->Print(", done);\n");

        p->Outdent();
        p->Print("}\n");
    }

    p->Outdent();
    p->Print("}\n");
}

static void PrintService(const ServiceDescriptor* service,
                         map<string, string>* vars,
                         io::Printer* p)
{
    (*vars)["file_name"] = service->file()->name();
    (*vars)["service_class_name"] = service->name();
    (*vars)["descriptor_name"] = service->options().GetExtension(bgs::protocol::service_options).descriptor_name();
    (*vars)["full_name"] = service->full_name();

    WriteServiceDocComment(service, p);
    p->Print(*vars,
             "@$Generated$(\n"
             "    value = \"by BNet proto compiler\",\n"
             "    comments = \"Source: $file_name$\")\n"
             "public abstract class $service_class_name$\n"
             "    extends com.d3emu.bnet.rpc.Service {\n\n"
    );
    p->Indent();

    p->Print(*vars, "protected $service_class_name$() { super(\"$descriptor_name$\"); };\n\n");

    GenerateAbstractMethods(service, p);

    GenerateCallMethod(service, p);
    GenerateGetPrototype(service, REQUEST, p);
    GenerateGetPrototype(service, RESPONSE, p);
    GenerateStub(service, p);

    // Add an insertion point.
    p->Print(*vars,
             "\n"
             "// @@protoc_insertion_point(class_scope:$full_name$)\n");

    p->Outdent();
    p->Print("}\n");
}

void JavaBnetGenerator::GenerateService(const ServiceDescriptor* service,
                     io::ZeroCopyOutputStream* out) const
{
    map<string, string> vars;
    vars["String"] = "java.lang.String";
    vars["Deprecated"] = "java.lang.Deprecated";
    vars["Override"] = "java.lang.Override";
    vars["Iterator"] = "java.util.Iterator";
    vars["Generated"] = "javax.annotation.Generated";

    io::Printer printer(out, '$');

    string package_name = ServiceJavaPackage(service->file());
    if (!package_name.empty())
    {
        printer.Print("package $package_name$;\n\n",
                      "package_name", package_name
        );
    }

    PrintService(service, &vars, &printer);
}

bool JavaBnetGenerator::Generate(const FileDescriptor* file,
                                 const string& parameter,
                                 GeneratorContext* context,
                                 string* error) const
{
    vector<pair<string, string>> options;
    ParseGeneratorParameter(parameter, &options);

    string package_name = ServiceJavaPackage(file);
    string package_filename = JavaPackageToDir(package_name);
    for (int i = 0; i < file->service_count(); ++i)
    {
        const ServiceDescriptor* service = file->service(i);
        string filename = package_filename + service->name() + ".java";
        unique_ptr<io::ZeroCopyOutputStream> out(context->Open(filename));
        GenerateService(service, out.get());
    }

    return true;
}
