package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.ListValue;
import com.google.protobuf.Struct;
import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class Value extends GeneratedMessageV3 implements ValueOrBuilder {
    public static final int BOOL_VALUE_FIELD_NUMBER = 4;
    public static final int LIST_VALUE_FIELD_NUMBER = 6;
    public static final int NULL_VALUE_FIELD_NUMBER = 1;
    public static final int NUMBER_VALUE_FIELD_NUMBER = 2;
    public static final int STRING_VALUE_FIELD_NUMBER = 3;
    public static final int STRUCT_VALUE_FIELD_NUMBER = 5;
    private static final long serialVersionUID = 0;
    private int kindCase_;
    private Object kind_;
    private byte memoizedIsInitialized;
    private static final Value DEFAULT_INSTANCE = new Value();
    private static final Parser<Value> PARSER = new AbstractParser<Value>() { // from class: com.google.protobuf.Value.1
        @Override // com.google.protobuf.Parser
        public Value parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Value(codedInputStream, extensionRegistryLite);
        }
    };

    private Value(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.kindCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private Value() {
        this.kindCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return UnknownFieldSet.getDefaultInstance();
    }

    private Value(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag == 0) {
                        z = true;
                    } else if (readTag == 8) {
                        int readEnum = codedInputStream.readEnum();
                        this.kindCase_ = 1;
                        this.kind_ = Integer.valueOf(readEnum);
                    } else if (readTag == 17) {
                        this.kindCase_ = 2;
                        this.kind_ = Double.valueOf(codedInputStream.readDouble());
                    } else if (readTag == 26) {
                        String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                        this.kindCase_ = 3;
                        this.kind_ = readStringRequireUtf8;
                    } else if (readTag != 32) {
                        if (readTag == 42) {
                            Struct.Builder builder = this.kindCase_ == 5 ? ((Struct) this.kind_).toBuilder() : null;
                            this.kind_ = codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom((Struct) this.kind_);
                                this.kind_ = builder.buildPartial();
                            }
                            this.kindCase_ = 5;
                        } else if (readTag != 50) {
                            if (!codedInputStream.skipField(readTag)) {
                                z = true;
                            }
                        } else {
                            ListValue.Builder builder2 = this.kindCase_ == 6 ? ((ListValue) this.kind_).toBuilder() : null;
                            this.kind_ = codedInputStream.readMessage(ListValue.parser(), extensionRegistryLite);
                            if (builder2 != null) {
                                builder2.mergeFrom((ListValue) this.kind_);
                                this.kind_ = builder2.buildPartial();
                            }
                            this.kindCase_ = 6;
                        }
                    } else {
                        this.kindCase_ = 4;
                        this.kind_ = Boolean.valueOf(codedInputStream.readBool());
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return StructProto.internal_static_google_protobuf_Value_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return StructProto.internal_static_google_protobuf_Value_fieldAccessorTable.ensureFieldAccessorsInitialized(Value.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum KindCase implements Internal.EnumLite {
        NULL_VALUE(1),
        NUMBER_VALUE(2),
        STRING_VALUE(3),
        BOOL_VALUE(4),
        STRUCT_VALUE(5),
        LIST_VALUE(6),
        KIND_NOT_SET(0);
        
        private final int value;

        KindCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static KindCase valueOf(int i) {
            return forNumber(i);
        }

        public static KindCase forNumber(int i) {
            switch (i) {
                case 0:
                    return KIND_NOT_SET;
                case 1:
                    return NULL_VALUE;
                case 2:
                    return NUMBER_VALUE;
                case 3:
                    return STRING_VALUE;
                case 4:
                    return BOOL_VALUE;
                case 5:
                    return STRUCT_VALUE;
                case 6:
                    return LIST_VALUE;
                default:
                    return null;
            }
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public KindCase getKindCase() {
        return KindCase.forNumber(this.kindCase_);
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public int getNullValueValue() {
        if (this.kindCase_ == 1) {
            return ((Integer) this.kind_).intValue();
        }
        return 0;
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public NullValue getNullValue() {
        if (this.kindCase_ == 1) {
            NullValue valueOf = NullValue.valueOf(((Integer) this.kind_).intValue());
            return valueOf == null ? NullValue.UNRECOGNIZED : valueOf;
        }
        return NullValue.NULL_VALUE;
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public double getNumberValue() {
        if (this.kindCase_ == 2) {
            return ((Double) this.kind_).doubleValue();
        }
        return 0.0d;
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public String getStringValue() {
        String str = this.kindCase_ == 3 ? this.kind_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.kindCase_ == 3) {
            this.kind_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public ByteString getStringValueBytes() {
        String str = this.kindCase_ == 3 ? this.kind_ : "";
        if (str instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.kindCase_ == 3) {
                this.kind_ = copyFromUtf8;
            }
            return copyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public boolean getBoolValue() {
        if (this.kindCase_ == 4) {
            return ((Boolean) this.kind_).booleanValue();
        }
        return false;
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public Struct getStructValue() {
        if (this.kindCase_ == 5) {
            return (Struct) this.kind_;
        }
        return Struct.getDefaultInstance();
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public StructOrBuilder getStructValueOrBuilder() {
        if (this.kindCase_ == 5) {
            return (Struct) this.kind_;
        }
        return Struct.getDefaultInstance();
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public ListValue getListValue() {
        if (this.kindCase_ == 6) {
            return (ListValue) this.kind_;
        }
        return ListValue.getDefaultInstance();
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public ListValueOrBuilder getListValueOrBuilder() {
        if (this.kindCase_ == 6) {
            return (ListValue) this.kind_;
        }
        return ListValue.getDefaultInstance();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.kindCase_ == 1) {
            codedOutputStream.writeEnum(1, ((Integer) this.kind_).intValue());
        }
        if (this.kindCase_ == 2) {
            codedOutputStream.writeDouble(2, ((Double) this.kind_).doubleValue());
        }
        if (this.kindCase_ == 3) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.kind_);
        }
        if (this.kindCase_ == 4) {
            codedOutputStream.writeBool(4, ((Boolean) this.kind_).booleanValue());
        }
        if (this.kindCase_ == 5) {
            codedOutputStream.writeMessage(5, (Struct) this.kind_);
        }
        if (this.kindCase_ == 6) {
            codedOutputStream.writeMessage(6, (ListValue) this.kind_);
        }
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeEnumSize = this.kindCase_ == 1 ? 0 + CodedOutputStream.computeEnumSize(1, ((Integer) this.kind_).intValue()) : 0;
        if (this.kindCase_ == 2) {
            computeEnumSize += CodedOutputStream.computeDoubleSize(2, ((Double) this.kind_).doubleValue());
        }
        if (this.kindCase_ == 3) {
            computeEnumSize += GeneratedMessageV3.computeStringSize(3, this.kind_);
        }
        if (this.kindCase_ == 4) {
            computeEnumSize += CodedOutputStream.computeBoolSize(4, ((Boolean) this.kind_).booleanValue());
        }
        if (this.kindCase_ == 5) {
            computeEnumSize += CodedOutputStream.computeMessageSize(5, (Struct) this.kind_);
        }
        if (this.kindCase_ == 6) {
            computeEnumSize += CodedOutputStream.computeMessageSize(6, (ListValue) this.kind_);
        }
        this.memoizedSize = computeEnumSize;
        return computeEnumSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Value)) {
            return super.equals(obj);
        }
        Value value = (Value) obj;
        boolean z = getKindCase().equals(value.getKindCase());
        if (z) {
            switch (this.kindCase_) {
                case 1:
                    return z && getNullValueValue() == value.getNullValueValue();
                case 2:
                    return z && Double.doubleToLongBits(getNumberValue()) == Double.doubleToLongBits(value.getNumberValue());
                case 3:
                    return z && getStringValue().equals(value.getStringValue());
                case 4:
                    return z && getBoolValue() == value.getBoolValue();
                case 5:
                    return z && getStructValue().equals(value.getStructValue());
                case 6:
                    return z && getListValue().equals(value.getListValue());
                default:
                    return z;
            }
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        switch (this.kindCase_) {
            case 1:
                hashCode = (((hashCode * 37) + 1) * 53) + getNullValueValue();
                break;
            case 2:
                hashCode = (((hashCode * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getNumberValue()));
                break;
            case 3:
                hashCode = (((hashCode * 37) + 3) * 53) + getStringValue().hashCode();
                break;
            case 4:
                hashCode = (((hashCode * 37) + 4) * 53) + Internal.hashBoolean(getBoolValue());
                break;
            case 5:
                hashCode = (((hashCode * 37) + 5) * 53) + getStructValue().hashCode();
                break;
            case 6:
                hashCode = (((hashCode * 37) + 6) * 53) + getListValue().hashCode();
                break;
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Value parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Value parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Value parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Value parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Value parseFrom(InputStream inputStream) throws IOException {
        return (Value) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Value parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Value) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Value parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Value) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Value parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Value) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Value parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Value) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Value parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Value) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Value value) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(value);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ValueOrBuilder {
        private int kindCase_;
        private Object kind_;
        private SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> listValueBuilder_;
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> structValueBuilder_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return this;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return StructProto.internal_static_google_protobuf_Value_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return StructProto.internal_static_google_protobuf_Value_fieldAccessorTable.ensureFieldAccessorsInitialized(Value.class, Builder.class);
        }

        private Builder() {
            this.kindCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.kindCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean z = GeneratedMessageV3.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.kindCase_ = 0;
            this.kind_ = null;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return StructProto.internal_static_google_protobuf_Value_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Value getDefaultInstanceForType() {
            return Value.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Value build() {
            Value buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Value buildPartial() {
            Value value = new Value(this);
            if (this.kindCase_ == 1) {
                value.kind_ = this.kind_;
            }
            if (this.kindCase_ == 2) {
                value.kind_ = this.kind_;
            }
            if (this.kindCase_ == 3) {
                value.kind_ = this.kind_;
            }
            if (this.kindCase_ == 4) {
                value.kind_ = this.kind_;
            }
            if (this.kindCase_ == 5) {
                SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.structValueBuilder_;
                if (singleFieldBuilderV3 == null) {
                    value.kind_ = this.kind_;
                } else {
                    value.kind_ = singleFieldBuilderV3.build();
                }
            }
            if (this.kindCase_ == 6) {
                SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> singleFieldBuilderV32 = this.listValueBuilder_;
                if (singleFieldBuilderV32 == null) {
                    value.kind_ = this.kind_;
                } else {
                    value.kind_ = singleFieldBuilderV32.build();
                }
            }
            value.kindCase_ = this.kindCase_;
            onBuilt();
            return value;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
        /* renamed from: clone */
        public Builder mo24470clone() {
            return (Builder) super.mo24470clone();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Builder mergeFrom(Message message) {
            if (message instanceof Value) {
                return mergeFrom((Value) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Value value) {
            if (value == Value.getDefaultInstance()) {
                return this;
            }
            switch (value.getKindCase()) {
                case NULL_VALUE:
                    setNullValueValue(value.getNullValueValue());
                    break;
                case NUMBER_VALUE:
                    setNumberValue(value.getNumberValue());
                    break;
                case STRING_VALUE:
                    this.kindCase_ = 3;
                    this.kind_ = value.kind_;
                    onChanged();
                    break;
                case BOOL_VALUE:
                    setBoolValue(value.getBoolValue());
                    break;
                case STRUCT_VALUE:
                    mergeStructValue(value.getStructValue());
                    break;
                case LIST_VALUE:
                    mergeListValue(value.getListValue());
                    break;
            }
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.google.protobuf.Value.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.protobuf.Value.access$500()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.protobuf.Value r3 = (com.google.protobuf.Value) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                if (r3 == 0) goto L10
                r2.mergeFrom(r3)
            L10:
                return r2
            L11:
                r3 = move-exception
                goto L21
            L13:
                r3 = move-exception
                com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                com.google.protobuf.Value r4 = (com.google.protobuf.Value) r4     // Catch: java.lang.Throwable -> L11
                java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                throw r3     // Catch: java.lang.Throwable -> L1f
            L1f:
                r3 = move-exception
                r0 = r4
            L21:
                if (r0 == 0) goto L26
                r2.mergeFrom(r0)
            L26:
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Value.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.Value$Builder");
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public KindCase getKindCase() {
            return KindCase.forNumber(this.kindCase_);
        }

        public Builder clearKind() {
            this.kindCase_ = 0;
            this.kind_ = null;
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public int getNullValueValue() {
            if (this.kindCase_ == 1) {
                return ((Integer) this.kind_).intValue();
            }
            return 0;
        }

        public Builder setNullValueValue(int i) {
            this.kindCase_ = 1;
            this.kind_ = Integer.valueOf(i);
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public NullValue getNullValue() {
            if (this.kindCase_ == 1) {
                NullValue valueOf = NullValue.valueOf(((Integer) this.kind_).intValue());
                return valueOf == null ? NullValue.UNRECOGNIZED : valueOf;
            }
            return NullValue.NULL_VALUE;
        }

        public Builder setNullValue(NullValue nullValue) {
            if (nullValue == null) {
                throw new NullPointerException();
            }
            this.kindCase_ = 1;
            this.kind_ = Integer.valueOf(nullValue.getNumber());
            onChanged();
            return this;
        }

        public Builder clearNullValue() {
            if (this.kindCase_ == 1) {
                this.kindCase_ = 0;
                this.kind_ = null;
                onChanged();
            }
            return this;
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public double getNumberValue() {
            if (this.kindCase_ == 2) {
                return ((Double) this.kind_).doubleValue();
            }
            return 0.0d;
        }

        public Builder setNumberValue(double d) {
            this.kindCase_ = 2;
            this.kind_ = Double.valueOf(d);
            onChanged();
            return this;
        }

        public Builder clearNumberValue() {
            if (this.kindCase_ == 2) {
                this.kindCase_ = 0;
                this.kind_ = null;
                onChanged();
            }
            return this;
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public String getStringValue() {
            String str = this.kindCase_ == 3 ? this.kind_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.kindCase_ == 3) {
                    this.kind_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public ByteString getStringValueBytes() {
            String str = this.kindCase_ == 3 ? this.kind_ : "";
            if (str instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.kindCase_ == 3) {
                    this.kind_ = copyFromUtf8;
                }
                return copyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setStringValue(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.kindCase_ = 3;
            this.kind_ = str;
            onChanged();
            return this;
        }

        public Builder clearStringValue() {
            if (this.kindCase_ == 3) {
                this.kindCase_ = 0;
                this.kind_ = null;
                onChanged();
            }
            return this;
        }

        public Builder setStringValueBytes(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.kindCase_ = 3;
            this.kind_ = byteString;
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public boolean getBoolValue() {
            if (this.kindCase_ == 4) {
                return ((Boolean) this.kind_).booleanValue();
            }
            return false;
        }

        public Builder setBoolValue(boolean z) {
            this.kindCase_ = 4;
            this.kind_ = Boolean.valueOf(z);
            onChanged();
            return this;
        }

        public Builder clearBoolValue() {
            if (this.kindCase_ == 4) {
                this.kindCase_ = 0;
                this.kind_ = null;
                onChanged();
            }
            return this;
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public Struct getStructValue() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.structValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.kindCase_ == 5) {
                    return (Struct) this.kind_;
                }
                return Struct.getDefaultInstance();
            } else if (this.kindCase_ == 5) {
                return singleFieldBuilderV3.getMessage();
            } else {
                return Struct.getDefaultInstance();
            }
        }

        public Builder setStructValue(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.structValueBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(struct);
            } else if (struct == null) {
                throw new NullPointerException();
            } else {
                this.kind_ = struct;
                onChanged();
            }
            this.kindCase_ = 5;
            return this;
        }

        public Builder setStructValue(Struct.Builder builder) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.structValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.kind_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.kindCase_ = 5;
            return this;
        }

        public Builder mergeStructValue(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.structValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.kindCase_ == 5 && this.kind_ != Struct.getDefaultInstance()) {
                    this.kind_ = Struct.newBuilder((Struct) this.kind_).mergeFrom(struct).buildPartial();
                } else {
                    this.kind_ = struct;
                }
                onChanged();
            } else {
                if (this.kindCase_ == 5) {
                    singleFieldBuilderV3.mergeFrom(struct);
                }
                this.structValueBuilder_.setMessage(struct);
            }
            this.kindCase_ = 5;
            return this;
        }

        public Builder clearStructValue() {
            if (this.structValueBuilder_ == null) {
                if (this.kindCase_ == 5) {
                    this.kindCase_ = 0;
                    this.kind_ = null;
                    onChanged();
                }
            } else {
                if (this.kindCase_ == 5) {
                    this.kindCase_ = 0;
                    this.kind_ = null;
                }
                this.structValueBuilder_.clear();
            }
            return this;
        }

        public Struct.Builder getStructValueBuilder() {
            return getStructValueFieldBuilder().getBuilder();
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public StructOrBuilder getStructValueOrBuilder() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3;
            if (this.kindCase_ == 5 && (singleFieldBuilderV3 = this.structValueBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.kindCase_ == 5) {
                return (Struct) this.kind_;
            }
            return Struct.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getStructValueFieldBuilder() {
            if (this.structValueBuilder_ == null) {
                if (this.kindCase_ != 5) {
                    this.kind_ = Struct.getDefaultInstance();
                }
                this.structValueBuilder_ = new SingleFieldBuilderV3<>((Struct) this.kind_, getParentForChildren(), isClean());
                this.kind_ = null;
            }
            this.kindCase_ = 5;
            onChanged();
            return this.structValueBuilder_;
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public ListValue getListValue() {
            SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> singleFieldBuilderV3 = this.listValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.kindCase_ == 6) {
                    return (ListValue) this.kind_;
                }
                return ListValue.getDefaultInstance();
            } else if (this.kindCase_ == 6) {
                return singleFieldBuilderV3.getMessage();
            } else {
                return ListValue.getDefaultInstance();
            }
        }

        public Builder setListValue(ListValue listValue) {
            SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> singleFieldBuilderV3 = this.listValueBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(listValue);
            } else if (listValue == null) {
                throw new NullPointerException();
            } else {
                this.kind_ = listValue;
                onChanged();
            }
            this.kindCase_ = 6;
            return this;
        }

        public Builder setListValue(ListValue.Builder builder) {
            SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> singleFieldBuilderV3 = this.listValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.kind_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.kindCase_ = 6;
            return this;
        }

        public Builder mergeListValue(ListValue listValue) {
            SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> singleFieldBuilderV3 = this.listValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.kindCase_ == 6 && this.kind_ != ListValue.getDefaultInstance()) {
                    this.kind_ = ListValue.newBuilder((ListValue) this.kind_).mergeFrom(listValue).buildPartial();
                } else {
                    this.kind_ = listValue;
                }
                onChanged();
            } else {
                if (this.kindCase_ == 6) {
                    singleFieldBuilderV3.mergeFrom(listValue);
                }
                this.listValueBuilder_.setMessage(listValue);
            }
            this.kindCase_ = 6;
            return this;
        }

        public Builder clearListValue() {
            if (this.listValueBuilder_ == null) {
                if (this.kindCase_ == 6) {
                    this.kindCase_ = 0;
                    this.kind_ = null;
                    onChanged();
                }
            } else {
                if (this.kindCase_ == 6) {
                    this.kindCase_ = 0;
                    this.kind_ = null;
                }
                this.listValueBuilder_.clear();
            }
            return this;
        }

        public ListValue.Builder getListValueBuilder() {
            return getListValueFieldBuilder().getBuilder();
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public ListValueOrBuilder getListValueOrBuilder() {
            SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> singleFieldBuilderV3;
            if (this.kindCase_ == 6 && (singleFieldBuilderV3 = this.listValueBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.kindCase_ == 6) {
                return (ListValue) this.kind_;
            }
            return ListValue.getDefaultInstance();
        }

        private SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> getListValueFieldBuilder() {
            if (this.listValueBuilder_ == null) {
                if (this.kindCase_ != 6) {
                    this.kind_ = ListValue.getDefaultInstance();
                }
                this.listValueBuilder_ = new SingleFieldBuilderV3<>((ListValue) this.kind_, getParentForChildren(), isClean());
                this.kind_ = null;
            }
            this.kindCase_ = 6;
            onChanged();
            return this.listValueBuilder_;
        }
    }

    public static Value getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Value> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Value> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Value getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
