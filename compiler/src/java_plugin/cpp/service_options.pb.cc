// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service_options.proto

#include "service_options.pb.h"

#include <algorithm>

#include <google/protobuf/stubs/common.h>
#include <google/protobuf/stubs/port.h>
#include <google/protobuf/stubs/once.h>
#include <google/protobuf/io/coded_stream.h>
#include <google/protobuf/wire_format_lite_inl.h>
#include <google/protobuf/descriptor.h>
#include <google/protobuf/generated_message_reflection.h>
#include <google/protobuf/reflection_ops.h>
#include <google/protobuf/wire_format.h>
// This is a temporary google only hack
#ifdef GOOGLE_PROTOBUF_ENFORCE_UNIQUENESS
#include "third_party/protobuf/version.h"
#endif
// @@protoc_insertion_point(includes)
namespace bgs {
namespace protocol {
class BGSServiceOptionsDefaultTypeInternal {
 public:
  ::google::protobuf::internal::ExplicitlyConstructed<BGSServiceOptions>
      _instance;
} _BGSServiceOptions_default_instance_;
class SDKServiceOptionsDefaultTypeInternal {
 public:
  ::google::protobuf::internal::ExplicitlyConstructed<SDKServiceOptions>
      _instance;
} _SDKServiceOptions_default_instance_;
}  // namespace protocol
}  // namespace bgs
namespace protobuf_service_5foptions_2eproto {
void InitDefaultsBGSServiceOptionsImpl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

#ifdef GOOGLE_PROTOBUF_ENFORCE_UNIQUENESS
  ::google::protobuf::internal::InitProtobufDefaultsForceUnique();
#else
  ::google::protobuf::internal::InitProtobufDefaults();
#endif  // GOOGLE_PROTOBUF_ENFORCE_UNIQUENESS
  {
    void* ptr = &::bgs::protocol::_BGSServiceOptions_default_instance_;
    new (ptr) ::bgs::protocol::BGSServiceOptions();
    ::google::protobuf::internal::OnShutdownDestroyMessage(ptr);
  }
  ::bgs::protocol::BGSServiceOptions::InitAsDefaultInstance();
}

void InitDefaultsBGSServiceOptions() {
  static GOOGLE_PROTOBUF_DECLARE_ONCE(once);
  ::google::protobuf::GoogleOnceInit(&once, &InitDefaultsBGSServiceOptionsImpl);
}

void InitDefaultsSDKServiceOptionsImpl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

#ifdef GOOGLE_PROTOBUF_ENFORCE_UNIQUENESS
  ::google::protobuf::internal::InitProtobufDefaultsForceUnique();
#else
  ::google::protobuf::internal::InitProtobufDefaults();
#endif  // GOOGLE_PROTOBUF_ENFORCE_UNIQUENESS
  {
    void* ptr = &::bgs::protocol::_SDKServiceOptions_default_instance_;
    new (ptr) ::bgs::protocol::SDKServiceOptions();
    ::google::protobuf::internal::OnShutdownDestroyMessage(ptr);
  }
  ::bgs::protocol::SDKServiceOptions::InitAsDefaultInstance();
}

void InitDefaultsSDKServiceOptions() {
  static GOOGLE_PROTOBUF_DECLARE_ONCE(once);
  ::google::protobuf::GoogleOnceInit(&once, &InitDefaultsSDKServiceOptionsImpl);
}

::google::protobuf::Metadata file_level_metadata[2];

const ::google::protobuf::uint32 TableStruct::offsets[] GOOGLE_PROTOBUF_ATTRIBUTE_SECTION_VARIABLE(protodesc_cold) = {
  GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(::bgs::protocol::BGSServiceOptions, _has_bits_),
  GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(::bgs::protocol::BGSServiceOptions, _internal_metadata_),
  ~0u,  // no _extensions_
  ~0u,  // no _oneof_case_
  ~0u,  // no _weak_field_map_
  GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(::bgs::protocol::BGSServiceOptions, descriptor_name_),
  0,
  GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(::bgs::protocol::SDKServiceOptions, _has_bits_),
  GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(::bgs::protocol::SDKServiceOptions, _internal_metadata_),
  ~0u,  // no _extensions_
  ~0u,  // no _oneof_case_
  ~0u,  // no _weak_field_map_
  GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(::bgs::protocol::SDKServiceOptions, inbound_),
  GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(::bgs::protocol::SDKServiceOptions, outbound_),
  0,
  1,
};
static const ::google::protobuf::internal::MigrationSchema schemas[] GOOGLE_PROTOBUF_ATTRIBUTE_SECTION_VARIABLE(protodesc_cold) = {
  { 0, 6, sizeof(::bgs::protocol::BGSServiceOptions)},
  { 7, 14, sizeof(::bgs::protocol::SDKServiceOptions)},
};

static ::google::protobuf::Message const * const file_default_instances[] = {
  reinterpret_cast<const ::google::protobuf::Message*>(&::bgs::protocol::_BGSServiceOptions_default_instance_),
  reinterpret_cast<const ::google::protobuf::Message*>(&::bgs::protocol::_SDKServiceOptions_default_instance_),
};

void protobuf_AssignDescriptors() {
  AddDescriptors();
  ::google::protobuf::MessageFactory* factory = NULL;
  AssignDescriptors(
      "service_options.proto", schemas, file_default_instances, TableStruct::offsets, factory,
      file_level_metadata, NULL, NULL);
}

void protobuf_AssignDescriptorsOnce() {
  static GOOGLE_PROTOBUF_DECLARE_ONCE(once);
  ::google::protobuf::GoogleOnceInit(&once, &protobuf_AssignDescriptors);
}

void protobuf_RegisterTypes(const ::std::string&) GOOGLE_PROTOBUF_ATTRIBUTE_COLD;
void protobuf_RegisterTypes(const ::std::string&) {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::internal::RegisterAllTypes(file_level_metadata, 2);
}

void AddDescriptorsImpl() {
  InitDefaults();
  static const char descriptor[] GOOGLE_PROTOBUF_ATTRIBUTE_SECTION_VARIABLE(protodesc_cold) = {
      "\n\025service_options.proto\022\014bgs.protocol\032 g"
      "oogle/protobuf/descriptor.proto\",\n\021BGSSe"
      "rviceOptions\022\027\n\017descriptor_name\030\001 \001(\t\"6\n"
      "\021SDKServiceOptions\022\017\n\007inbound\030\001 \001(\010\022\020\n\010o"
      "utbound\030\002 \001(\010:[\n\017service_options\022\037.googl"
      "e.protobuf.ServiceOptions\030\220\277\005 \001(\0132\037.bgs."
      "protocol.BGSServiceOptions:_\n\023sdk_servic"
      "e_options\022\037.google.protobuf.ServiceOptio"
      "ns\030\221\277\005 \001(\0132\037.bgs.protocol.SDKServiceOpti"
      "onsB&\n\rbnet.protocolB\023ServiceOptionsProt"
      "oH\002"
  };
  ::google::protobuf::DescriptorPool::InternalAddGeneratedFile(
      descriptor, 403);
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedFile(
    "service_options.proto", &protobuf_RegisterTypes);
  ::protobuf_google_2fprotobuf_2fdescriptor_2eproto::AddDescriptors();
}

void AddDescriptors() {
  static GOOGLE_PROTOBUF_DECLARE_ONCE(once);
  ::google::protobuf::GoogleOnceInit(&once, &AddDescriptorsImpl);
}
// Force AddDescriptors() to be called at dynamic initialization time.
struct StaticDescriptorInitializer {
  StaticDescriptorInitializer() {
    AddDescriptors();
  }
} static_descriptor_initializer;
}  // namespace protobuf_service_5foptions_2eproto
namespace bgs {
namespace protocol {

// ===================================================================

void BGSServiceOptions::InitAsDefaultInstance() {
}
#if !defined(_MSC_VER) || _MSC_VER >= 1900
const int BGSServiceOptions::kDescriptorNameFieldNumber;
#endif  // !defined(_MSC_VER) || _MSC_VER >= 1900

BGSServiceOptions::BGSServiceOptions()
  : ::google::protobuf::Message(), _internal_metadata_(NULL) {
  if (GOOGLE_PREDICT_TRUE(this != internal_default_instance())) {
    ::protobuf_service_5foptions_2eproto::InitDefaultsBGSServiceOptions();
  }
  SharedCtor();
  // @@protoc_insertion_point(constructor:bgs.protocol.BGSServiceOptions)
}
BGSServiceOptions::BGSServiceOptions(const BGSServiceOptions& from)
  : ::google::protobuf::Message(),
      _internal_metadata_(NULL),
      _has_bits_(from._has_bits_),
      _cached_size_(0) {
  _internal_metadata_.MergeFrom(from._internal_metadata_);
  descriptor_name_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  if (from.has_descriptor_name()) {
    descriptor_name_.AssignWithDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), from.descriptor_name_);
  }
  // @@protoc_insertion_point(copy_constructor:bgs.protocol.BGSServiceOptions)
}

void BGSServiceOptions::SharedCtor() {
  _cached_size_ = 0;
  descriptor_name_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}

BGSServiceOptions::~BGSServiceOptions() {
  // @@protoc_insertion_point(destructor:bgs.protocol.BGSServiceOptions)
  SharedDtor();
}

void BGSServiceOptions::SharedDtor() {
  descriptor_name_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}

void BGSServiceOptions::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* BGSServiceOptions::descriptor() {
  ::protobuf_service_5foptions_2eproto::protobuf_AssignDescriptorsOnce();
  return ::protobuf_service_5foptions_2eproto::file_level_metadata[kIndexInFileMessages].descriptor;
}

const BGSServiceOptions& BGSServiceOptions::default_instance() {
  ::protobuf_service_5foptions_2eproto::InitDefaultsBGSServiceOptions();
  return *internal_default_instance();
}

BGSServiceOptions* BGSServiceOptions::New(::google::protobuf::Arena* arena) const {
  BGSServiceOptions* n = new BGSServiceOptions;
  if (arena != NULL) {
    arena->Own(n);
  }
  return n;
}

void BGSServiceOptions::Swap(BGSServiceOptions* other) {
  if (other == this) return;
  InternalSwap(other);
}
void BGSServiceOptions::InternalSwap(BGSServiceOptions* other) {
  using std::swap;
  GetReflection()->Swap(this, other);}

::google::protobuf::Metadata BGSServiceOptions::GetMetadata() const {
  protobuf_service_5foptions_2eproto::protobuf_AssignDescriptorsOnce();
  return ::protobuf_service_5foptions_2eproto::file_level_metadata[kIndexInFileMessages];
}


// ===================================================================

void SDKServiceOptions::InitAsDefaultInstance() {
}
#if !defined(_MSC_VER) || _MSC_VER >= 1900
const int SDKServiceOptions::kInboundFieldNumber;
const int SDKServiceOptions::kOutboundFieldNumber;
#endif  // !defined(_MSC_VER) || _MSC_VER >= 1900

SDKServiceOptions::SDKServiceOptions()
  : ::google::protobuf::Message(), _internal_metadata_(NULL) {
  if (GOOGLE_PREDICT_TRUE(this != internal_default_instance())) {
    ::protobuf_service_5foptions_2eproto::InitDefaultsSDKServiceOptions();
  }
  SharedCtor();
  // @@protoc_insertion_point(constructor:bgs.protocol.SDKServiceOptions)
}
SDKServiceOptions::SDKServiceOptions(const SDKServiceOptions& from)
  : ::google::protobuf::Message(),
      _internal_metadata_(NULL),
      _has_bits_(from._has_bits_),
      _cached_size_(0) {
  _internal_metadata_.MergeFrom(from._internal_metadata_);
  ::memcpy(&inbound_, &from.inbound_,
    static_cast<size_t>(reinterpret_cast<char*>(&outbound_) -
    reinterpret_cast<char*>(&inbound_)) + sizeof(outbound_));
  // @@protoc_insertion_point(copy_constructor:bgs.protocol.SDKServiceOptions)
}

void SDKServiceOptions::SharedCtor() {
  _cached_size_ = 0;
  ::memset(&inbound_, 0, static_cast<size_t>(
      reinterpret_cast<char*>(&outbound_) -
      reinterpret_cast<char*>(&inbound_)) + sizeof(outbound_));
}

SDKServiceOptions::~SDKServiceOptions() {
  // @@protoc_insertion_point(destructor:bgs.protocol.SDKServiceOptions)
  SharedDtor();
}

void SDKServiceOptions::SharedDtor() {
}

void SDKServiceOptions::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* SDKServiceOptions::descriptor() {
  ::protobuf_service_5foptions_2eproto::protobuf_AssignDescriptorsOnce();
  return ::protobuf_service_5foptions_2eproto::file_level_metadata[kIndexInFileMessages].descriptor;
}

const SDKServiceOptions& SDKServiceOptions::default_instance() {
  ::protobuf_service_5foptions_2eproto::InitDefaultsSDKServiceOptions();
  return *internal_default_instance();
}

SDKServiceOptions* SDKServiceOptions::New(::google::protobuf::Arena* arena) const {
  SDKServiceOptions* n = new SDKServiceOptions;
  if (arena != NULL) {
    arena->Own(n);
  }
  return n;
}

void SDKServiceOptions::Swap(SDKServiceOptions* other) {
  if (other == this) return;
  InternalSwap(other);
}
void SDKServiceOptions::InternalSwap(SDKServiceOptions* other) {
  using std::swap;
  GetReflection()->Swap(this, other);}

::google::protobuf::Metadata SDKServiceOptions::GetMetadata() const {
  protobuf_service_5foptions_2eproto::protobuf_AssignDescriptorsOnce();
  return ::protobuf_service_5foptions_2eproto::file_level_metadata[kIndexInFileMessages];
}

::google::protobuf::internal::ExtensionIdentifier< ::google::protobuf::ServiceOptions,
    ::google::protobuf::internal::MessageTypeTraits< ::bgs::protocol::BGSServiceOptions >, 11, false >
  service_options(kServiceOptionsFieldNumber, *::bgs::protocol::BGSServiceOptions::internal_default_instance());
::google::protobuf::internal::ExtensionIdentifier< ::google::protobuf::ServiceOptions,
    ::google::protobuf::internal::MessageTypeTraits< ::bgs::protocol::SDKServiceOptions >, 11, false >
  sdk_service_options(kSdkServiceOptionsFieldNumber, *::bgs::protocol::SDKServiceOptions::internal_default_instance());

// @@protoc_insertion_point(namespace_scope)
}  // namespace protocol
}  // namespace bgs

// @@protoc_insertion_point(global_scope)
