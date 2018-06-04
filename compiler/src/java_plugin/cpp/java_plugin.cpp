#include <google/protobuf/compiler/plugin.h>

#include "java_generator.h"

int main(int argc, char* argv[])
{
    JavaBnetGenerator g;
    return PluginMain(argc, argv, &g);
}
