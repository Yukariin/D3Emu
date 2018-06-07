// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: method_options.proto

#ifndef PROTOBUF_method_5foptions_2eproto__INCLUDED
#define PROTOBUF_method_5foptions_2eproto__INCLUDED

#include <string>

#include <google/protobuf/stubs/common.h>

#if GOOGLE_PROTOBUF_VERSION < 3005000
#error This file was generated by a newer version of protoc which is
#error incompatible with your Protocol Buffer headers.  Please update
#error your headers.
#endif
#if 3005001 < GOOGLE_PROTOBUF_MIN_PROTOC_VERSION
#error This file was generated by an older version of protoc which is
#error incompatible with your Protocol Buffer headers.  Please
#error regenerate this file with a newer version of protoc.
#endif

#include <google/protobuf/io/coded_stream.h>
#include <google/protobuf/arena.h>
#include <google/protobuf/arenastring.h>
#include <google/protobuf/generated_message_table_driven.h>
#include <google/protobuf/generated_message_util.h>
#include <google/protobuf/metadata.h>
#include <google/protobuf/message.h>
#include <google/protobuf/repeated_field.h>  // IWYU pragma: export
#include <google/protobuf/extension_set.h>  // IWYU pragma: export
#include <google/protobuf/unknown_field_set.h>
#include <google/protobuf/descriptor.pb.h>
// @@protoc_insertion_point(includes)

namespace protobuf_method_5foptions_2eproto {
// Internal implementation detail -- do not use these members.
struct TableStruct {
  static const ::google::protobuf::internal::ParseTableField entries[];
  static const ::google::protobuf::internal::AuxillaryParseTableField aux[];
  static const ::google::protobuf::internal::ParseTable schema[1];
  static const ::google::protobuf::internal::FieldMetadata field_metadata[];
  static const ::google::protobuf::internal::SerializationTable serialization_table[];
  static const ::google::protobuf::uint32 offsets[];
};
void AddDescriptors();
void InitDefaultsBGSMethodOptionsImpl();
void InitDefaultsBGSMethodOptions();
inline void InitDefaults() {
  InitDefaultsBGSMethodOptions();
}
}  // namespace protobuf_method_5foptions_2eproto
namespace bgs {
namespace protocol {
class BGSMethodOptions;
class BGSMethodOptionsDefaultTypeInternal;
extern BGSMethodOptionsDefaultTypeInternal _BGSMethodOptions_default_instance_;
}  // namespace protocol
}  // namespace bgs
namespace bgs {
namespace protocol {

// ===================================================================

class BGSMethodOptions : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:bgs.protocol.BGSMethodOptions) */ {
 public:
  BGSMethodOptions();
  virtual ~BGSMethodOptions();

  BGSMethodOptions(const BGSMethodOptions& from);

  inline BGSMethodOptions& operator=(const BGSMethodOptions& from) {
    CopyFrom(from);
    return *this;
  }
  #if LANG_CXX11
  BGSMethodOptions(BGSMethodOptions&& from) noexcept
    : BGSMethodOptions() {
    *this = ::std::move(from);
  }

  inline BGSMethodOptions& operator=(BGSMethodOptions&& from) noexcept {
    if (GetArenaNoVirtual() == from.GetArenaNoVirtual()) {
      if (this != &from) InternalSwap(&from);
    } else {
      CopyFrom(from);
    }
    return *this;
  }
  #endif
  inline const ::google::protobuf::UnknownFieldSet& unknown_fields() const {
    return _internal_metadata_.unknown_fields();
  }
  inline ::google::protobuf::UnknownFieldSet* mutable_unknown_fields() {
    return _internal_metadata_.mutable_unknown_fields();
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const BGSMethodOptions& default_instance();

  static void InitAsDefaultInstance();  // FOR INTERNAL USE ONLY
  static inline const BGSMethodOptions* internal_default_instance() {
    return reinterpret_cast<const BGSMethodOptions*>(
               &_BGSMethodOptions_default_instance_);
  }
  static PROTOBUF_CONSTEXPR int const kIndexInFileMessages =
    0;

  void Swap(BGSMethodOptions* other);
  friend void swap(BGSMethodOptions& a, BGSMethodOptions& b) {
    a.Swap(&b);
  }

  // implements Message ----------------------------------------------

  inline BGSMethodOptions* New() const PROTOBUF_FINAL { return New(NULL); }

  BGSMethodOptions* New(::google::protobuf::Arena* arena) const PROTOBUF_FINAL;
  int GetCachedSize() const PROTOBUF_FINAL { return _cached_size_; }
  private:
  void SharedCtor();
  void SharedDtor();
  void SetCachedSize(int size) const PROTOBUF_FINAL;
  void InternalSwap(BGSMethodOptions* other);
  private:
  inline ::google::protobuf::Arena* GetArenaNoVirtual() const {
    return NULL;
  }
  inline void* MaybeArenaPtr() const {
    return NULL;
  }
  public:

  ::google::protobuf::Metadata GetMetadata() const PROTOBUF_FINAL;

  // nested types ----------------------------------------------------

  // accessors -------------------------------------------------------

  // optional uint32 id = 1;
  bool has_id() const;
  void clear_id();
  static const int kIdFieldNumber = 1;
  ::google::protobuf::uint32 id() const;
  void set_id(::google::protobuf::uint32 value);

  // @@protoc_insertion_point(class_scope:bgs.protocol.BGSMethodOptions)
 private:
  void set_has_id();
  void clear_has_id();

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::internal::HasBits<1> _has_bits_;
  mutable int _cached_size_;
  ::google::protobuf::uint32 id_;
  friend struct ::protobuf_method_5foptions_2eproto::TableStruct;
  friend void ::protobuf_method_5foptions_2eproto::InitDefaultsBGSMethodOptionsImpl();
};
// ===================================================================

static const int kMethodOptionsFieldNumber = 90000;
extern ::google::protobuf::internal::ExtensionIdentifier< ::google::protobuf::MethodOptions,
    ::google::protobuf::internal::MessageTypeTraits< ::bgs::protocol::BGSMethodOptions >, 11, false >
  method_options;

// ===================================================================

#ifdef __GNUC__
  #pragma GCC diagnostic push
  #pragma GCC diagnostic ignored "-Wstrict-aliasing"
#endif  // __GNUC__
// BGSMethodOptions

// optional uint32 id = 1;
inline bool BGSMethodOptions::has_id() const {
  return (_has_bits_[0] & 0x00000001u) != 0;
}
inline void BGSMethodOptions::set_has_id() {
  _has_bits_[0] |= 0x00000001u;
}
inline void BGSMethodOptions::clear_has_id() {
  _has_bits_[0] &= ~0x00000001u;
}
inline void BGSMethodOptions::clear_id() {
  id_ = 0u;
  clear_has_id();
}
inline ::google::protobuf::uint32 BGSMethodOptions::id() const {
  // @@protoc_insertion_point(field_get:bgs.protocol.BGSMethodOptions.id)
  return id_;
}
inline void BGSMethodOptions::set_id(::google::protobuf::uint32 value) {
  set_has_id();
  id_ = value;
  // @@protoc_insertion_point(field_set:bgs.protocol.BGSMethodOptions.id)
}

#ifdef __GNUC__
  #pragma GCC diagnostic pop
#endif  // __GNUC__

// @@protoc_insertion_point(namespace_scope)

}  // namespace protocol
}  // namespace bgs

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_method_5foptions_2eproto__INCLUDED