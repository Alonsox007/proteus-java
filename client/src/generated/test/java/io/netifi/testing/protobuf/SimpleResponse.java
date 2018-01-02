// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: io.netifi.sdk.proteus/simpleservice.proto

package io.netifi.testing.protobuf;

/**
 * <pre>
 * A simple response message type for test.
 * </pre>
 *
 * Protobuf type {@code io.netifi.testing.SimpleResponse}
 */
public  final class SimpleResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:io.netifi.testing.SimpleResponse)
    SimpleResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use SimpleResponse.newBuilder() to construct.
  private SimpleResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SimpleResponse() {
    responseMessage_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SimpleResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            responseMessage_ = s;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return io.netifi.testing.protobuf.SimpleServiceProto.internal_static_io_netifi_testing_SimpleResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.netifi.testing.protobuf.SimpleServiceProto.internal_static_io_netifi_testing_SimpleResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.netifi.testing.protobuf.SimpleResponse.class, io.netifi.testing.protobuf.SimpleResponse.Builder.class);
  }

  public static final int RESPONSEMESSAGE_FIELD_NUMBER = 1;
  private volatile java.lang.Object responseMessage_;
  /**
   * <pre>
   * An optional string message for test.
   * </pre>
   *
   * <code>string responseMessage = 1;</code>
   */
  public java.lang.String getResponseMessage() {
    java.lang.Object ref = responseMessage_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      responseMessage_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * An optional string message for test.
   * </pre>
   *
   * <code>string responseMessage = 1;</code>
   */
  public com.google.protobuf.ByteString
      getResponseMessageBytes() {
    java.lang.Object ref = responseMessage_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      responseMessage_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getResponseMessageBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, responseMessage_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getResponseMessageBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, responseMessage_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof io.netifi.testing.protobuf.SimpleResponse)) {
      return super.equals(obj);
    }
    io.netifi.testing.protobuf.SimpleResponse other = (io.netifi.testing.protobuf.SimpleResponse) obj;

    boolean result = true;
    result = result && getResponseMessage()
        .equals(other.getResponseMessage());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + RESPONSEMESSAGE_FIELD_NUMBER;
    hash = (53 * hash) + getResponseMessage().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.netifi.testing.protobuf.SimpleResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.netifi.testing.protobuf.SimpleResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.netifi.testing.protobuf.SimpleResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.netifi.testing.protobuf.SimpleResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.netifi.testing.protobuf.SimpleResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.netifi.testing.protobuf.SimpleResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.netifi.testing.protobuf.SimpleResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.netifi.testing.protobuf.SimpleResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.netifi.testing.protobuf.SimpleResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.netifi.testing.protobuf.SimpleResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.netifi.testing.protobuf.SimpleResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.netifi.testing.protobuf.SimpleResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(io.netifi.testing.protobuf.SimpleResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * A simple response message type for test.
   * </pre>
   *
   * Protobuf type {@code io.netifi.testing.SimpleResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:io.netifi.testing.SimpleResponse)
      io.netifi.testing.protobuf.SimpleResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.netifi.testing.protobuf.SimpleServiceProto.internal_static_io_netifi_testing_SimpleResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.netifi.testing.protobuf.SimpleServiceProto.internal_static_io_netifi_testing_SimpleResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.netifi.testing.protobuf.SimpleResponse.class, io.netifi.testing.protobuf.SimpleResponse.Builder.class);
    }

    // Construct using io.netifi.testing.protobuf.SimpleResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      responseMessage_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.netifi.testing.protobuf.SimpleServiceProto.internal_static_io_netifi_testing_SimpleResponse_descriptor;
    }

    public io.netifi.testing.protobuf.SimpleResponse getDefaultInstanceForType() {
      return io.netifi.testing.protobuf.SimpleResponse.getDefaultInstance();
    }

    public io.netifi.testing.protobuf.SimpleResponse build() {
      io.netifi.testing.protobuf.SimpleResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public io.netifi.testing.protobuf.SimpleResponse buildPartial() {
      io.netifi.testing.protobuf.SimpleResponse result = new io.netifi.testing.protobuf.SimpleResponse(this);
      result.responseMessage_ = responseMessage_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof io.netifi.testing.protobuf.SimpleResponse) {
        return mergeFrom((io.netifi.testing.protobuf.SimpleResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.netifi.testing.protobuf.SimpleResponse other) {
      if (other == io.netifi.testing.protobuf.SimpleResponse.getDefaultInstance()) return this;
      if (!other.getResponseMessage().isEmpty()) {
        responseMessage_ = other.responseMessage_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      io.netifi.testing.protobuf.SimpleResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.netifi.testing.protobuf.SimpleResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object responseMessage_ = "";
    /**
     * <pre>
     * An optional string message for test.
     * </pre>
     *
     * <code>string responseMessage = 1;</code>
     */
    public java.lang.String getResponseMessage() {
      java.lang.Object ref = responseMessage_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        responseMessage_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * An optional string message for test.
     * </pre>
     *
     * <code>string responseMessage = 1;</code>
     */
    public com.google.protobuf.ByteString
        getResponseMessageBytes() {
      java.lang.Object ref = responseMessage_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        responseMessage_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * An optional string message for test.
     * </pre>
     *
     * <code>string responseMessage = 1;</code>
     */
    public Builder setResponseMessage(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      responseMessage_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * An optional string message for test.
     * </pre>
     *
     * <code>string responseMessage = 1;</code>
     */
    public Builder clearResponseMessage() {
      
      responseMessage_ = getDefaultInstance().getResponseMessage();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * An optional string message for test.
     * </pre>
     *
     * <code>string responseMessage = 1;</code>
     */
    public Builder setResponseMessageBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      responseMessage_ = value;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:io.netifi.testing.SimpleResponse)
  }

  // @@protoc_insertion_point(class_scope:io.netifi.testing.SimpleResponse)
  private static final io.netifi.testing.protobuf.SimpleResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.netifi.testing.protobuf.SimpleResponse();
  }

  public static io.netifi.testing.protobuf.SimpleResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SimpleResponse>
      PARSER = new com.google.protobuf.AbstractParser<SimpleResponse>() {
    public SimpleResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new SimpleResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SimpleResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SimpleResponse> getParserForType() {
    return PARSER;
  }

  public io.netifi.testing.protobuf.SimpleResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

