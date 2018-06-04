#ifndef JAVA_GENERATOR_H_
#define JAVA_GENERATOR_H_

#include <string>

#include <google/protobuf/compiler/code_generator.h>
#include <google/protobuf/descriptor.h>

using namespace std;

using namespace google::protobuf;
using namespace google::protobuf::compiler;

class JavaBnetGenerator : public CodeGenerator
{
public:
    JavaBnetGenerator() {}
    virtual ~JavaBnetGenerator() {}

    virtual bool Generate(const FileDescriptor* file,
                          const string& parameter,
                          GeneratorContext* context,
                          string* error) const;

    void GenerateService(const ServiceDescriptor* service,
                         io::ZeroCopyOutputStream* out) const;
};

#endif  /* JAVA_GENERATOR_H_ */
