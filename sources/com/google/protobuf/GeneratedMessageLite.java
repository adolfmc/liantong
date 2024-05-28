package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.FieldSet;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLite;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class GeneratedMessageLite<MessageType extends GeneratedMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> extends AbstractMessageLite<MessageType, BuilderType> {
    protected UnknownFieldSetLite unknownFields = UnknownFieldSetLite.getDefaultInstance();
    protected int memoizedSerializedSize = -1;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface ExtendableMessageOrBuilder<MessageType extends ExtendableMessage<MessageType, BuilderType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>> extends MessageLiteOrBuilder {
        <Type> Type getExtension(ExtensionLite<MessageType, Type> extensionLite);

        <Type> Type getExtension(ExtensionLite<MessageType, List<Type>> extensionLite, int i);

        <Type> int getExtensionCount(ExtensionLite<MessageType, List<Type>> extensionLite);

        <Type> boolean hasExtension(ExtensionLite<MessageType, Type> extensionLite);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum MethodToInvoke {
        IS_INITIALIZED,
        VISIT,
        MERGE_FROM_STREAM,
        MAKE_IMMUTABLE,
        NEW_MUTABLE_INSTANCE,
        NEW_BUILDER,
        GET_DEFAULT_INSTANCE,
        GET_PARSER
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface Visitor {
        boolean visitBoolean(boolean z, boolean z2, boolean z3, boolean z4);

        Internal.BooleanList visitBooleanList(Internal.BooleanList booleanList, Internal.BooleanList booleanList2);

        ByteString visitByteString(boolean z, ByteString byteString, boolean z2, ByteString byteString2);

        double visitDouble(boolean z, double d, boolean z2, double d2);

        Internal.DoubleList visitDoubleList(Internal.DoubleList doubleList, Internal.DoubleList doubleList2);

        FieldSet<ExtensionDescriptor> visitExtensions(FieldSet<ExtensionDescriptor> fieldSet, FieldSet<ExtensionDescriptor> fieldSet2);

        float visitFloat(boolean z, float f, boolean z2, float f2);

        Internal.FloatList visitFloatList(Internal.FloatList floatList, Internal.FloatList floatList2);

        int visitInt(boolean z, int i, boolean z2, int i2);

        Internal.IntList visitIntList(Internal.IntList intList, Internal.IntList intList2);

        LazyFieldLite visitLazyMessage(LazyFieldLite lazyFieldLite, LazyFieldLite lazyFieldLite2);

        <T> Internal.ProtobufList<T> visitList(Internal.ProtobufList<T> protobufList, Internal.ProtobufList<T> protobufList2);

        long visitLong(boolean z, long j, boolean z2, long j2);

        Internal.LongList visitLongList(Internal.LongList longList, Internal.LongList longList2);

        <K, V> MapFieldLite<K, V> visitMap(MapFieldLite<K, V> mapFieldLite, MapFieldLite<K, V> mapFieldLite2);

        <T extends MessageLite> T visitMessage(T t, T t2);

        Object visitOneofBoolean(boolean z, Object obj, Object obj2);

        Object visitOneofByteString(boolean z, Object obj, Object obj2);

        Object visitOneofDouble(boolean z, Object obj, Object obj2);

        Object visitOneofFloat(boolean z, Object obj, Object obj2);

        Object visitOneofInt(boolean z, Object obj, Object obj2);

        Object visitOneofLazyMessage(boolean z, Object obj, Object obj2);

        Object visitOneofLong(boolean z, Object obj, Object obj2);

        Object visitOneofMessage(boolean z, Object obj, Object obj2);

        void visitOneofNotSet(boolean z);

        Object visitOneofString(boolean z, Object obj, Object obj2);

        String visitString(boolean z, String str, boolean z2, String str2);

        UnknownFieldSetLite visitUnknownFields(UnknownFieldSetLite unknownFieldSetLite, UnknownFieldSetLite unknownFieldSetLite2);
    }

    protected abstract Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2);

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public final Parser<MessageType> getParserForType() {
        return (Parser) dynamicMethod(MethodToInvoke.GET_PARSER);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public final MessageType getDefaultInstanceForType() {
        return (MessageType) dynamicMethod(MethodToInvoke.GET_DEFAULT_INSTANCE);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public final BuilderType newBuilderForType() {
        return (BuilderType) dynamicMethod(MethodToInvoke.NEW_BUILDER);
    }

    public String toString() {
        return MessageLiteToString.toString(this, super.toString());
    }

    public int hashCode() {
        if (this.memoizedHashCode == 0) {
            HashCodeVisitor hashCodeVisitor = new HashCodeVisitor();
            visit(hashCodeVisitor, this);
            this.memoizedHashCode = hashCodeVisitor.hashCode;
        }
        return this.memoizedHashCode;
    }

    int hashCode(HashCodeVisitor hashCodeVisitor) {
        if (this.memoizedHashCode == 0) {
            int i = hashCodeVisitor.hashCode;
            hashCodeVisitor.hashCode = 0;
            visit(hashCodeVisitor, this);
            this.memoizedHashCode = hashCodeVisitor.hashCode;
            hashCodeVisitor.hashCode = i;
        }
        return this.memoizedHashCode;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (getDefaultInstanceForType().getClass().isInstance(obj)) {
            try {
                visit(EqualsVisitor.INSTANCE, (GeneratedMessageLite) obj);
                return true;
            } catch (EqualsVisitor.NotEqualsException unused) {
                return false;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    boolean equals(EqualsVisitor equalsVisitor, MessageLite messageLite) {
        if (this == messageLite) {
            return true;
        }
        if (getDefaultInstanceForType().getClass().isInstance(messageLite)) {
            visit(equalsVisitor, (GeneratedMessageLite) messageLite);
            return true;
        }
        return false;
    }

    private final void ensureUnknownFieldsInitialized() {
        if (this.unknownFields == UnknownFieldSetLite.getDefaultInstance()) {
            this.unknownFields = UnknownFieldSetLite.newInstance();
        }
    }

    protected boolean parseUnknownField(int i, CodedInputStream codedInputStream) throws IOException {
        if (WireFormat.getTagWireType(i) == 4) {
            return false;
        }
        ensureUnknownFieldsInitialized();
        return this.unknownFields.mergeFieldFrom(i, codedInputStream);
    }

    protected void mergeVarintField(int i, int i2) {
        ensureUnknownFieldsInitialized();
        this.unknownFields.mergeVarintField(i, i2);
    }

    protected void mergeLengthDelimitedField(int i, ByteString byteString) {
        ensureUnknownFieldsInitialized();
        this.unknownFields.mergeLengthDelimitedField(i, byteString);
    }

    protected void makeImmutable() {
        dynamicMethod(MethodToInvoke.MAKE_IMMUTABLE);
        this.unknownFields.makeImmutable();
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder
    public final boolean isInitialized() {
        return dynamicMethod(MethodToInvoke.IS_INITIALIZED, Boolean.TRUE) != null;
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public final BuilderType toBuilder() {
        BuilderType buildertype = (BuilderType) dynamicMethod(MethodToInvoke.NEW_BUILDER);
        buildertype.mergeFrom(this);
        return buildertype;
    }

    protected Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj) {
        return dynamicMethod(methodToInvoke, obj, null);
    }

    protected Object dynamicMethod(MethodToInvoke methodToInvoke) {
        return dynamicMethod(methodToInvoke, null, null);
    }

    void visit(Visitor visitor, MessageType messagetype) {
        dynamicMethod(MethodToInvoke.VISIT, visitor, messagetype);
        this.unknownFields = visitor.visitUnknownFields(this.unknownFields, messagetype.unknownFields);
    }

    protected final void mergeUnknownFields(UnknownFieldSetLite unknownFieldSetLite) {
        this.unknownFields = UnknownFieldSetLite.mutableCopyOf(this.unknownFields, unknownFieldSetLite);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static abstract class Builder<MessageType extends GeneratedMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> extends AbstractMessageLite.Builder<MessageType, BuilderType> {
        private final MessageType defaultInstance;
        protected MessageType instance;
        protected boolean isBuilt = false;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.protobuf.AbstractMessageLite.Builder
        protected /* bridge */ /* synthetic */ AbstractMessageLite.Builder internalMergeFrom(AbstractMessageLite abstractMessageLite) {
            return internalMergeFrom((Builder<MessageType, BuilderType>) ((GeneratedMessageLite) abstractMessageLite));
        }

        protected Builder(MessageType messagetype) {
            this.defaultInstance = messagetype;
            this.instance = (MessageType) messagetype.dynamicMethod(MethodToInvoke.NEW_MUTABLE_INSTANCE);
        }

        protected void copyOnWrite() {
            if (this.isBuilt) {
                MessageType messagetype = (MessageType) this.instance.dynamicMethod(MethodToInvoke.NEW_MUTABLE_INSTANCE);
                messagetype.visit(MergeFromVisitor.INSTANCE, this.instance);
                this.instance = messagetype;
                this.isBuilt = false;
            }
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return GeneratedMessageLite.isInitialized(this.instance, false);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public final BuilderType clear() {
            this.instance = (MessageType) this.instance.dynamicMethod(MethodToInvoke.NEW_MUTABLE_INSTANCE);
            return this;
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder
        /* renamed from: clone */
        public BuilderType mo24470clone() {
            BuilderType buildertype = (BuilderType) getDefaultInstanceForType().newBuilderForType();
            buildertype.mergeFrom(buildPartial());
            return buildertype;
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public MessageType buildPartial() {
            if (this.isBuilt) {
                return this.instance;
            }
            this.instance.makeImmutable();
            this.isBuilt = true;
            return this.instance;
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public final MessageType build() {
            MessageType buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        protected BuilderType internalMergeFrom(MessageType messagetype) {
            return mergeFrom((Builder<MessageType, BuilderType>) messagetype);
        }

        public BuilderType mergeFrom(MessageType messagetype) {
            copyOnWrite();
            this.instance.visit(MergeFromVisitor.INSTANCE, messagetype);
            return this;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MessageType getDefaultInstanceForType() {
            return this.defaultInstance;
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            copyOnWrite();
            try {
                this.instance.dynamicMethod(MethodToInvoke.MERGE_FROM_STREAM, codedInputStream, extensionRegistryLite);
                return this;
            } catch (RuntimeException e) {
                if (e.getCause() instanceof IOException) {
                    throw ((IOException) e.getCause());
                }
                throw e;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static abstract class ExtendableMessage<MessageType extends ExtendableMessage<MessageType, BuilderType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>> extends GeneratedMessageLite<MessageType, BuilderType> implements ExtendableMessageOrBuilder<MessageType, BuilderType> {
        protected FieldSet<ExtensionDescriptor> extensions = FieldSet.newFieldSet();

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return super.newBuilderForType();
        }

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return super.toBuilder();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.protobuf.GeneratedMessageLite
        /* bridge */ /* synthetic */ void visit(Visitor visitor, GeneratedMessageLite generatedMessageLite) {
            visit(visitor, (Visitor) ((ExtendableMessage) generatedMessageLite));
        }

        protected final void mergeExtensionFields(MessageType messagetype) {
            if (this.extensions.isImmutable()) {
                this.extensions = this.extensions.m24471clone();
            }
            this.extensions.mergeFrom(messagetype.extensions);
        }

        final void visit(Visitor visitor, MessageType messagetype) {
            super.visit(visitor, (Visitor) messagetype);
            this.extensions = visitor.visitExtensions(this.extensions, messagetype.extensions);
        }

        protected <MessageType extends MessageLite> boolean parseUnknownField(MessageType messagetype, CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, int i) throws IOException {
            int tagFieldNumber = WireFormat.getTagFieldNumber(i);
            return parseExtension(codedInputStream, extensionRegistryLite, extensionRegistryLite.findLiteExtensionByNumber(messagetype, tagFieldNumber), i, tagFieldNumber);
        }

        private boolean parseExtension(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, GeneratedExtension<?, ?> generatedExtension, int i, int i2) throws IOException {
            boolean z;
            boolean z2;
            Object build;
            MessageLite messageLite;
            int tagWireType = WireFormat.getTagWireType(i);
            if (generatedExtension == null) {
                z = false;
                z2 = true;
            } else if (tagWireType == FieldSet.getWireFormatForFieldType(generatedExtension.descriptor.getLiteType(), false)) {
                z2 = false;
                z = false;
            } else if (generatedExtension.descriptor.isRepeated && generatedExtension.descriptor.type.isPackable() && tagWireType == FieldSet.getWireFormatForFieldType(generatedExtension.descriptor.getLiteType(), true)) {
                z2 = false;
                z = true;
            } else {
                z = false;
                z2 = true;
            }
            if (z2) {
                return parseUnknownField(i, codedInputStream);
            }
            if (z) {
                int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                if (generatedExtension.descriptor.getLiteType() == WireFormat.FieldType.ENUM) {
                    while (codedInputStream.getBytesUntilLimit() > 0) {
                        Object findValueByNumber = generatedExtension.descriptor.getEnumType().findValueByNumber(codedInputStream.readEnum());
                        if (findValueByNumber == null) {
                            return true;
                        }
                        this.extensions.addRepeatedField(generatedExtension.descriptor, generatedExtension.singularToFieldSetType(findValueByNumber));
                    }
                } else {
                    while (codedInputStream.getBytesUntilLimit() > 0) {
                        this.extensions.addRepeatedField(generatedExtension.descriptor, FieldSet.readPrimitiveField(codedInputStream, generatedExtension.descriptor.getLiteType(), false));
                    }
                }
                codedInputStream.popLimit(pushLimit);
            } else {
                switch (generatedExtension.descriptor.getLiteJavaType()) {
                    case MESSAGE:
                        MessageLite.Builder builder = null;
                        if (!generatedExtension.descriptor.isRepeated() && (messageLite = (MessageLite) this.extensions.getField(generatedExtension.descriptor)) != null) {
                            builder = messageLite.toBuilder();
                        }
                        if (builder == null) {
                            builder = generatedExtension.getMessageDefaultInstance().newBuilderForType();
                        }
                        if (generatedExtension.descriptor.getLiteType() == WireFormat.FieldType.GROUP) {
                            codedInputStream.readGroup(generatedExtension.getNumber(), builder, extensionRegistryLite);
                        } else {
                            codedInputStream.readMessage(builder, extensionRegistryLite);
                        }
                        build = builder.build();
                        break;
                    case ENUM:
                        int readEnum = codedInputStream.readEnum();
                        build = generatedExtension.descriptor.getEnumType().findValueByNumber(readEnum);
                        if (build == null) {
                            mergeVarintField(i2, readEnum);
                            return true;
                        }
                        break;
                    default:
                        build = FieldSet.readPrimitiveField(codedInputStream, generatedExtension.descriptor.getLiteType(), false);
                        break;
                }
                if (generatedExtension.descriptor.isRepeated()) {
                    this.extensions.addRepeatedField(generatedExtension.descriptor, generatedExtension.singularToFieldSetType(build));
                } else {
                    this.extensions.setField(generatedExtension.descriptor, generatedExtension.singularToFieldSetType(build));
                }
            }
            return true;
        }

        protected <MessageType extends MessageLite> boolean parseUnknownFieldAsMessageSet(MessageType messagetype, CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, int i) throws IOException {
            if (i == WireFormat.MESSAGE_SET_ITEM_TAG) {
                mergeMessageSetExtensionFromCodedStream(messagetype, codedInputStream, extensionRegistryLite);
                return true;
            } else if (WireFormat.getTagWireType(i) == 2) {
                return parseUnknownField(messagetype, codedInputStream, extensionRegistryLite, i);
            } else {
                return codedInputStream.skipField(i);
            }
        }

        private <MessageType extends MessageLite> void mergeMessageSetExtensionFromCodedStream(MessageType messagetype, CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int i = 0;
            ByteString byteString = null;
            GeneratedExtension<?, ?> generatedExtension = null;
            while (true) {
                int readTag = codedInputStream.readTag();
                if (readTag == 0) {
                    break;
                } else if (readTag == WireFormat.MESSAGE_SET_TYPE_ID_TAG) {
                    i = codedInputStream.readUInt32();
                    if (i != 0) {
                        generatedExtension = extensionRegistryLite.findLiteExtensionByNumber(messagetype, i);
                    }
                } else if (readTag == WireFormat.MESSAGE_SET_MESSAGE_TAG) {
                    if (i != 0 && generatedExtension != null) {
                        eagerlyMergeMessageSetExtension(codedInputStream, generatedExtension, extensionRegistryLite, i);
                        byteString = null;
                    } else {
                        byteString = codedInputStream.readBytes();
                    }
                } else if (!codedInputStream.skipField(readTag)) {
                    break;
                }
            }
            codedInputStream.checkLastTagWas(WireFormat.MESSAGE_SET_ITEM_END_TAG);
            if (byteString == null || i == 0) {
                return;
            }
            if (generatedExtension != null) {
                mergeMessageSetExtensionFromBytes(byteString, extensionRegistryLite, generatedExtension);
            } else if (byteString != null) {
                mergeLengthDelimitedField(i, byteString);
            }
        }

        private void eagerlyMergeMessageSetExtension(CodedInputStream codedInputStream, GeneratedExtension<?, ?> generatedExtension, ExtensionRegistryLite extensionRegistryLite, int i) throws IOException {
            parseExtension(codedInputStream, extensionRegistryLite, generatedExtension, WireFormat.makeTag(i, 2), i);
        }

        private void mergeMessageSetExtensionFromBytes(ByteString byteString, ExtensionRegistryLite extensionRegistryLite, GeneratedExtension<?, ?> generatedExtension) throws IOException {
            MessageLite messageLite = (MessageLite) this.extensions.getField(generatedExtension.descriptor);
            MessageLite.Builder builder = messageLite != null ? messageLite.toBuilder() : null;
            if (builder == null) {
                builder = generatedExtension.getMessageDefaultInstance().newBuilderForType();
            }
            byteString.newCodedInput().readMessage(builder, extensionRegistryLite);
            this.extensions.setField(generatedExtension.descriptor, generatedExtension.singularToFieldSetType(builder.build()));
        }

        private void verifyExtensionContainingType(GeneratedExtension<MessageType, ?> generatedExtension) {
            if (generatedExtension.getContainingTypeDefaultInstance() != getDefaultInstanceForType()) {
                throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite.ExtendableMessageOrBuilder
        public final <Type> boolean hasExtension(ExtensionLite<MessageType, Type> extensionLite) {
            GeneratedExtension<MessageType, ?> checkIsLite = GeneratedMessageLite.checkIsLite(extensionLite);
            verifyExtensionContainingType(checkIsLite);
            return this.extensions.hasField(checkIsLite.descriptor);
        }

        @Override // com.google.protobuf.GeneratedMessageLite.ExtendableMessageOrBuilder
        public final <Type> int getExtensionCount(ExtensionLite<MessageType, List<Type>> extensionLite) {
            GeneratedExtension<MessageType, ?> checkIsLite = GeneratedMessageLite.checkIsLite(extensionLite);
            verifyExtensionContainingType(checkIsLite);
            return this.extensions.getRepeatedFieldCount(checkIsLite.descriptor);
        }

        @Override // com.google.protobuf.GeneratedMessageLite.ExtendableMessageOrBuilder
        public final <Type> Type getExtension(ExtensionLite<MessageType, Type> extensionLite) {
            GeneratedExtension<MessageType, ?> checkIsLite = GeneratedMessageLite.checkIsLite(extensionLite);
            verifyExtensionContainingType(checkIsLite);
            Object field = this.extensions.getField(checkIsLite.descriptor);
            if (field == null) {
                return checkIsLite.defaultValue;
            }
            return (Type) checkIsLite.fromFieldSetType(field);
        }

        @Override // com.google.protobuf.GeneratedMessageLite.ExtendableMessageOrBuilder
        public final <Type> Type getExtension(ExtensionLite<MessageType, List<Type>> extensionLite, int i) {
            GeneratedExtension<MessageType, ?> checkIsLite = GeneratedMessageLite.checkIsLite(extensionLite);
            verifyExtensionContainingType(checkIsLite);
            return (Type) checkIsLite.singularFromFieldSetType(this.extensions.getRepeatedField(checkIsLite.descriptor, i));
        }

        protected boolean extensionsAreInitialized() {
            return this.extensions.isInitialized();
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final void makeImmutable() {
            super.makeImmutable();
            this.extensions.makeImmutable();
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\10762272_dexfile_execute.dex */
        public class ExtensionWriter {
            private final Iterator<Map.Entry<ExtensionDescriptor, Object>> iter;
            private final boolean messageSetWireFormat;
            private Map.Entry<ExtensionDescriptor, Object> next;

            private ExtensionWriter(boolean z) {
                this.iter = ExtendableMessage.this.extensions.iterator();
                if (this.iter.hasNext()) {
                    this.next = this.iter.next();
                }
                this.messageSetWireFormat = z;
            }

            public void writeUntil(int i, CodedOutputStream codedOutputStream) throws IOException {
                while (true) {
                    Map.Entry<ExtensionDescriptor, Object> entry = this.next;
                    if (entry == null || entry.getKey().getNumber() >= i) {
                        return;
                    }
                    ExtensionDescriptor key = this.next.getKey();
                    if (this.messageSetWireFormat && key.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !key.isRepeated()) {
                        codedOutputStream.writeMessageSetExtension(key.getNumber(), (MessageLite) this.next.getValue());
                    } else {
                        FieldSet.writeField(key, this.next.getValue(), codedOutputStream);
                    }
                    if (this.iter.hasNext()) {
                        this.next = this.iter.next();
                    } else {
                        this.next = null;
                    }
                }
            }
        }

        protected ExtendableMessage<MessageType, BuilderType>.ExtensionWriter newExtensionWriter() {
            return new ExtensionWriter(false);
        }

        protected ExtendableMessage<MessageType, BuilderType>.ExtensionWriter newMessageSetExtensionWriter() {
            return new ExtensionWriter(true);
        }

        protected int extensionsSerializedSize() {
            return this.extensions.getSerializedSize();
        }

        protected int extensionsSerializedSizeAsMessageSet() {
            return this.extensions.getMessageSetSerializedSize();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static abstract class ExtendableBuilder<MessageType extends ExtendableMessage<MessageType, BuilderType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>> extends Builder<MessageType, BuilderType> implements ExtendableMessageOrBuilder<MessageType, BuilderType> {
        protected ExtendableBuilder(MessageType messagetype) {
            super(messagetype);
            ((ExtendableMessage) this.instance).extensions = ((ExtendableMessage) this.instance).extensions.m24471clone();
        }

        void internalSetExtensionSet(FieldSet<ExtensionDescriptor> fieldSet) {
            copyOnWrite();
            ((ExtendableMessage) this.instance).extensions = fieldSet;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Builder
        protected void copyOnWrite() {
            if (this.isBuilt) {
                super.copyOnWrite();
                ((ExtendableMessage) this.instance).extensions = ((ExtendableMessage) this.instance).extensions.m24471clone();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public final MessageType buildPartial() {
            if (this.isBuilt) {
                return (MessageType) this.instance;
            }
            ((ExtendableMessage) this.instance).extensions.makeImmutable();
            return (MessageType) super.buildPartial();
        }

        private void verifyExtensionContainingType(GeneratedExtension<MessageType, ?> generatedExtension) {
            if (generatedExtension.getContainingTypeDefaultInstance() != getDefaultInstanceForType()) {
                throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite.ExtendableMessageOrBuilder
        public final <Type> boolean hasExtension(ExtensionLite<MessageType, Type> extensionLite) {
            return ((ExtendableMessage) this.instance).hasExtension(extensionLite);
        }

        @Override // com.google.protobuf.GeneratedMessageLite.ExtendableMessageOrBuilder
        public final <Type> int getExtensionCount(ExtensionLite<MessageType, List<Type>> extensionLite) {
            return ((ExtendableMessage) this.instance).getExtensionCount(extensionLite);
        }

        @Override // com.google.protobuf.GeneratedMessageLite.ExtendableMessageOrBuilder
        public final <Type> Type getExtension(ExtensionLite<MessageType, Type> extensionLite) {
            return (Type) ((ExtendableMessage) this.instance).getExtension(extensionLite);
        }

        @Override // com.google.protobuf.GeneratedMessageLite.ExtendableMessageOrBuilder
        public final <Type> Type getExtension(ExtensionLite<MessageType, List<Type>> extensionLite, int i) {
            return (Type) ((ExtendableMessage) this.instance).getExtension(extensionLite, i);
        }

        public final <Type> BuilderType setExtension(ExtensionLite<MessageType, Type> extensionLite, Type type) {
            GeneratedExtension<MessageType, ?> checkIsLite = GeneratedMessageLite.checkIsLite(extensionLite);
            verifyExtensionContainingType(checkIsLite);
            copyOnWrite();
            ((ExtendableMessage) this.instance).extensions.setField(checkIsLite.descriptor, checkIsLite.toFieldSetType(type));
            return this;
        }

        public final <Type> BuilderType setExtension(ExtensionLite<MessageType, List<Type>> extensionLite, int i, Type type) {
            GeneratedExtension<MessageType, ?> checkIsLite = GeneratedMessageLite.checkIsLite(extensionLite);
            verifyExtensionContainingType(checkIsLite);
            copyOnWrite();
            ((ExtendableMessage) this.instance).extensions.setRepeatedField(checkIsLite.descriptor, i, checkIsLite.singularToFieldSetType(type));
            return this;
        }

        public final <Type> BuilderType addExtension(ExtensionLite<MessageType, List<Type>> extensionLite, Type type) {
            GeneratedExtension<MessageType, ?> checkIsLite = GeneratedMessageLite.checkIsLite(extensionLite);
            verifyExtensionContainingType(checkIsLite);
            copyOnWrite();
            ((ExtendableMessage) this.instance).extensions.addRepeatedField(checkIsLite.descriptor, checkIsLite.singularToFieldSetType(type));
            return this;
        }

        public final <Type> BuilderType clearExtension(ExtensionLite<MessageType, ?> extensionLite) {
            GeneratedExtension<MessageType, ?> checkIsLite = GeneratedMessageLite.checkIsLite(extensionLite);
            verifyExtensionContainingType(checkIsLite);
            copyOnWrite();
            ((ExtendableMessage) this.instance).extensions.clearField(checkIsLite.descriptor);
            return this;
        }
    }

    public static <ContainingType extends MessageLite, Type> GeneratedExtension<ContainingType, Type> newSingularGeneratedExtension(ContainingType containingtype, Type type, MessageLite messageLite, Internal.EnumLiteMap<?> enumLiteMap, int i, WireFormat.FieldType fieldType, Class cls) {
        return new GeneratedExtension<>(containingtype, type, messageLite, new ExtensionDescriptor(enumLiteMap, i, fieldType, false, false), cls);
    }

    public static <ContainingType extends MessageLite, Type> GeneratedExtension<ContainingType, Type> newRepeatedGeneratedExtension(ContainingType containingtype, MessageLite messageLite, Internal.EnumLiteMap<?> enumLiteMap, int i, WireFormat.FieldType fieldType, boolean z, Class cls) {
        return new GeneratedExtension<>(containingtype, Collections.emptyList(), messageLite, new ExtensionDescriptor(enumLiteMap, i, fieldType, true, z), cls);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static final class ExtensionDescriptor implements FieldSet.FieldDescriptorLite<ExtensionDescriptor> {
        final Internal.EnumLiteMap<?> enumTypeMap;
        final boolean isPacked;
        final boolean isRepeated;
        final int number;
        final WireFormat.FieldType type;

        ExtensionDescriptor(Internal.EnumLiteMap<?> enumLiteMap, int i, WireFormat.FieldType fieldType, boolean z, boolean z2) {
            this.enumTypeMap = enumLiteMap;
            this.number = i;
            this.type = fieldType;
            this.isRepeated = z;
            this.isPacked = z2;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public int getNumber() {
            return this.number;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public WireFormat.FieldType getLiteType() {
            return this.type;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public WireFormat.JavaType getLiteJavaType() {
            return this.type.getJavaType();
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public boolean isRepeated() {
            return this.isRepeated;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public boolean isPacked() {
            return this.isPacked;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public Internal.EnumLiteMap<?> getEnumType() {
            return this.enumTypeMap;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public MessageLite.Builder internalMergeFrom(MessageLite.Builder builder, MessageLite messageLite) {
            return ((Builder) builder).mergeFrom((Builder) ((GeneratedMessageLite) messageLite));
        }

        @Override // java.lang.Comparable
        public int compareTo(ExtensionDescriptor extensionDescriptor) {
            return this.number - extensionDescriptor.number;
        }
    }

    static java.lang.reflect.Method getMethodOrDie(Class cls, String str, Class... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Generated message class \"" + cls.getName() + "\" missing method \"" + str + "\".", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object invokeOrDie(java.lang.reflect.Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class GeneratedExtension<ContainingType extends MessageLite, Type> extends ExtensionLite<ContainingType, Type> {
        final ContainingType containingTypeDefaultInstance;
        final Type defaultValue;
        final ExtensionDescriptor descriptor;
        final MessageLite messageDefaultInstance;

        GeneratedExtension(ContainingType containingtype, Type type, MessageLite messageLite, ExtensionDescriptor extensionDescriptor, Class cls) {
            if (containingtype == null) {
                throw new IllegalArgumentException("Null containingTypeDefaultInstance");
            }
            if (extensionDescriptor.getLiteType() == WireFormat.FieldType.MESSAGE && messageLite == null) {
                throw new IllegalArgumentException("Null messageDefaultInstance");
            }
            this.containingTypeDefaultInstance = containingtype;
            this.defaultValue = type;
            this.messageDefaultInstance = messageLite;
            this.descriptor = extensionDescriptor;
        }

        public ContainingType getContainingTypeDefaultInstance() {
            return this.containingTypeDefaultInstance;
        }

        @Override // com.google.protobuf.ExtensionLite
        public int getNumber() {
            return this.descriptor.getNumber();
        }

        @Override // com.google.protobuf.ExtensionLite
        public MessageLite getMessageDefaultInstance() {
            return this.messageDefaultInstance;
        }

        Object fromFieldSetType(Object obj) {
            if (this.descriptor.isRepeated()) {
                if (this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM) {
                    ArrayList arrayList = new ArrayList();
                    for (Object obj2 : (List) obj) {
                        arrayList.add(singularFromFieldSetType(obj2));
                    }
                    return arrayList;
                }
                return obj;
            }
            return singularFromFieldSetType(obj);
        }

        Object singularFromFieldSetType(Object obj) {
            return this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM ? this.descriptor.enumTypeMap.findValueByNumber(((Integer) obj).intValue()) : obj;
        }

        Object toFieldSetType(Object obj) {
            if (this.descriptor.isRepeated()) {
                if (this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM) {
                    ArrayList arrayList = new ArrayList();
                    for (Object obj2 : (List) obj) {
                        arrayList.add(singularToFieldSetType(obj2));
                    }
                    return arrayList;
                }
                return obj;
            }
            return singularToFieldSetType(obj);
        }

        Object singularToFieldSetType(Object obj) {
            return this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM ? Integer.valueOf(((Internal.EnumLite) obj).getNumber()) : obj;
        }

        @Override // com.google.protobuf.ExtensionLite
        public WireFormat.FieldType getLiteType() {
            return this.descriptor.getLiteType();
        }

        @Override // com.google.protobuf.ExtensionLite
        public boolean isRepeated() {
            return this.descriptor.isRepeated;
        }

        @Override // com.google.protobuf.ExtensionLite
        public Type getDefaultValue() {
            return this.defaultValue;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static final class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        private final byte[] asBytes;
        private final String messageClassName;

        /* renamed from: of */
        public static SerializedForm m15615of(MessageLite messageLite) {
            return new SerializedForm(messageLite);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public SerializedForm(MessageLite messageLite) {
            this.messageClassName = messageLite.getClass().getName();
            this.asBytes = messageLite.toByteArray();
        }

        protected Object readResolve() throws ObjectStreamException {
            try {
                java.lang.reflect.Field declaredField = Class.forName(this.messageClassName).getDeclaredField("DEFAULT_INSTANCE");
                declaredField.setAccessible(true);
                return ((MessageLite) declaredField.get(null)).newBuilderForType().mergeFrom(this.asBytes).buildPartial();
            } catch (InvalidProtocolBufferException e) {
                throw new RuntimeException("Unable to understand proto buffer", e);
            } catch (ClassNotFoundException e2) {
                throw new RuntimeException("Unable to find proto buffer class: " + this.messageClassName, e2);
            } catch (IllegalAccessException e3) {
                throw new RuntimeException("Unable to call parsePartialFrom", e3);
            } catch (NoSuchFieldException unused) {
                return readResolveFallback();
            } catch (SecurityException e4) {
                throw new RuntimeException("Unable to call DEFAULT_INSTANCE in " + this.messageClassName, e4);
            }
        }

        @Deprecated
        private Object readResolveFallback() throws ObjectStreamException {
            try {
                java.lang.reflect.Field declaredField = Class.forName(this.messageClassName).getDeclaredField("defaultInstance");
                declaredField.setAccessible(true);
                return ((MessageLite) declaredField.get(null)).newBuilderForType().mergeFrom(this.asBytes).buildPartial();
            } catch (InvalidProtocolBufferException e) {
                throw new RuntimeException("Unable to understand proto buffer", e);
            } catch (ClassNotFoundException e2) {
                throw new RuntimeException("Unable to find proto buffer class: " + this.messageClassName, e2);
            } catch (IllegalAccessException e3) {
                throw new RuntimeException("Unable to call parsePartialFrom", e3);
            } catch (NoSuchFieldException e4) {
                throw new RuntimeException("Unable to find defaultInstance in " + this.messageClassName, e4);
            } catch (SecurityException e5) {
                throw new RuntimeException("Unable to call defaultInstance in " + this.messageClassName, e5);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <MessageType extends ExtendableMessage<MessageType, BuilderType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>, T> GeneratedExtension<MessageType, T> checkIsLite(ExtensionLite<MessageType, T> extensionLite) {
        if (!extensionLite.isLite()) {
            throw new IllegalArgumentException("Expected a lite extension.");
        }
        return (GeneratedExtension) extensionLite;
    }

    protected static final <T extends GeneratedMessageLite<T, ?>> boolean isInitialized(T t, boolean z) {
        return t.dynamicMethod(MethodToInvoke.IS_INITIALIZED, Boolean.valueOf(z)) != null;
    }

    protected static final <T extends GeneratedMessageLite<T, ?>> void makeImmutable(T t) {
        t.dynamicMethod(MethodToInvoke.MAKE_IMMUTABLE);
    }

    protected static Internal.IntList emptyIntList() {
        return IntArrayList.emptyList();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.protobuf.Internal$IntList] */
    protected static Internal.IntList mutableCopy(Internal.IntList intList) {
        int size = intList.size();
        return intList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
    }

    protected static Internal.LongList emptyLongList() {
        return LongArrayList.emptyList();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.protobuf.Internal$LongList] */
    protected static Internal.LongList mutableCopy(Internal.LongList longList) {
        int size = longList.size();
        return longList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
    }

    protected static Internal.FloatList emptyFloatList() {
        return FloatArrayList.emptyList();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.protobuf.Internal$FloatList] */
    protected static Internal.FloatList mutableCopy(Internal.FloatList floatList) {
        int size = floatList.size();
        return floatList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
    }

    protected static Internal.DoubleList emptyDoubleList() {
        return DoubleArrayList.emptyList();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.protobuf.Internal$DoubleList] */
    protected static Internal.DoubleList mutableCopy(Internal.DoubleList doubleList) {
        int size = doubleList.size();
        return doubleList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
    }

    protected static Internal.BooleanList emptyBooleanList() {
        return BooleanArrayList.emptyList();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.protobuf.Internal$BooleanList] */
    protected static Internal.BooleanList mutableCopy(Internal.BooleanList booleanList) {
        int size = booleanList.size();
        return booleanList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
    }

    protected static <E> Internal.ProtobufList<E> emptyProtobufList() {
        return ProtobufArrayList.emptyList();
    }

    protected static <E> Internal.ProtobufList<E> mutableCopy(Internal.ProtobufList<E> protobufList) {
        int size = protobufList.size();
        return protobufList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class DefaultInstanceBasedParser<T extends GeneratedMessageLite<T, ?>> extends AbstractParser<T> {
        private T defaultInstance;

        public DefaultInstanceBasedParser(T t) {
            this.defaultInstance = t;
        }

        @Override // com.google.protobuf.Parser
        public T parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (T) GeneratedMessageLite.parsePartialFrom(this.defaultInstance, codedInputStream, extensionRegistryLite);
        }
    }

    static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T t, CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        T t2 = (T) t.dynamicMethod(MethodToInvoke.NEW_MUTABLE_INSTANCE);
        try {
            t2.dynamicMethod(MethodToInvoke.MERGE_FROM_STREAM, codedInputStream, extensionRegistryLite);
            t2.makeImmutable();
            return t2;
        } catch (RuntimeException e) {
            if (e.getCause() instanceof InvalidProtocolBufferException) {
                throw ((InvalidProtocolBufferException) e.getCause());
            }
            throw e;
        }
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T t, CodedInputStream codedInputStream) throws InvalidProtocolBufferException {
        return (T) parsePartialFrom(t, codedInputStream, ExtensionRegistryLite.getEmptyRegistry());
    }

    private static <T extends GeneratedMessageLite<T, ?>> T checkMessageInitialized(T t) throws InvalidProtocolBufferException {
        if (t == null || t.isInitialized()) {
            return t;
        }
        throw t.newUninitializedMessageException().asInvalidProtocolBufferException().setUnfinishedMessage(t);
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T t, ByteString byteString) throws InvalidProtocolBufferException {
        return (T) checkMessageInitialized(parseFrom(t, byteString, ExtensionRegistryLite.getEmptyRegistry()));
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T t, ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (T) checkMessageInitialized(parsePartialFrom(t, byteString, extensionRegistryLite));
    }

    private static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T t, ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        try {
            CodedInputStream newCodedInput = byteString.newCodedInput();
            T t2 = (T) parsePartialFrom(t, newCodedInput, extensionRegistryLite);
            try {
                newCodedInput.checkLastTagWas(0);
                return t2;
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(t2);
            }
        } catch (InvalidProtocolBufferException e2) {
            throw e2;
        }
    }

    private static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T t, byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        try {
            CodedInputStream newInstance = CodedInputStream.newInstance(bArr);
            T t2 = (T) parsePartialFrom(t, newInstance, extensionRegistryLite);
            try {
                newInstance.checkLastTagWas(0);
                return t2;
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(t2);
            }
        } catch (InvalidProtocolBufferException e2) {
            throw e2;
        }
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T t, byte[] bArr) throws InvalidProtocolBufferException {
        return (T) checkMessageInitialized(parsePartialFrom(t, bArr, ExtensionRegistryLite.getEmptyRegistry()));
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T t, byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (T) checkMessageInitialized(parsePartialFrom(t, bArr, extensionRegistryLite));
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T t, InputStream inputStream) throws InvalidProtocolBufferException {
        return (T) checkMessageInitialized(parsePartialFrom(t, CodedInputStream.newInstance(inputStream), ExtensionRegistryLite.getEmptyRegistry()));
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T t, InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (T) checkMessageInitialized(parsePartialFrom(t, CodedInputStream.newInstance(inputStream), extensionRegistryLite));
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T t, CodedInputStream codedInputStream) throws InvalidProtocolBufferException {
        return (T) parseFrom(t, codedInputStream, ExtensionRegistryLite.getEmptyRegistry());
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T t, CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (T) checkMessageInitialized(parsePartialFrom(t, codedInputStream, extensionRegistryLite));
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T parseDelimitedFrom(T t, InputStream inputStream) throws InvalidProtocolBufferException {
        return (T) checkMessageInitialized(parsePartialDelimitedFrom(t, inputStream, ExtensionRegistryLite.getEmptyRegistry()));
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T parseDelimitedFrom(T t, InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (T) checkMessageInitialized(parsePartialDelimitedFrom(t, inputStream, extensionRegistryLite));
    }

    private static <T extends GeneratedMessageLite<T, ?>> T parsePartialDelimitedFrom(T t, InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        try {
            int read = inputStream.read();
            if (read == -1) {
                return null;
            }
            CodedInputStream newInstance = CodedInputStream.newInstance(new AbstractMessageLite.Builder.LimitedInputStream(inputStream, CodedInputStream.readRawVarint32(read, inputStream)));
            T t2 = (T) parsePartialFrom(t, newInstance, extensionRegistryLite);
            try {
                newInstance.checkLastTagWas(0);
                return t2;
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(t2);
            }
        } catch (IOException e2) {
            throw new InvalidProtocolBufferException(e2.getMessage());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class EqualsVisitor implements Visitor {
        static final EqualsVisitor INSTANCE = new EqualsVisitor();
        static final NotEqualsException NOT_EQUALS = new NotEqualsException();

        /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\10762272_dexfile_execute.dex */
        static final class NotEqualsException extends RuntimeException {
            NotEqualsException() {
            }
        }

        private EqualsVisitor() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public boolean visitBoolean(boolean z, boolean z2, boolean z3, boolean z4) {
            if (z == z3 && z2 == z4) {
                return z2;
            }
            throw NOT_EQUALS;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public int visitInt(boolean z, int i, boolean z2, int i2) {
            if (z == z2 && i == i2) {
                return i;
            }
            throw NOT_EQUALS;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public double visitDouble(boolean z, double d, boolean z2, double d2) {
            if (z == z2 && d == d2) {
                return d;
            }
            throw NOT_EQUALS;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public float visitFloat(boolean z, float f, boolean z2, float f2) {
            if (z == z2 && f == f2) {
                return f;
            }
            throw NOT_EQUALS;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public long visitLong(boolean z, long j, boolean z2, long j2) {
            if (z == z2 && j == j2) {
                return j;
            }
            throw NOT_EQUALS;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public String visitString(boolean z, String str, boolean z2, String str2) {
            if (z == z2 && str.equals(str2)) {
                return str;
            }
            throw NOT_EQUALS;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public ByteString visitByteString(boolean z, ByteString byteString, boolean z2, ByteString byteString2) {
            if (z == z2 && byteString.equals(byteString2)) {
                return byteString;
            }
            throw NOT_EQUALS;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofBoolean(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw NOT_EQUALS;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofInt(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw NOT_EQUALS;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofDouble(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw NOT_EQUALS;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofFloat(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw NOT_EQUALS;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofLong(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw NOT_EQUALS;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofString(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw NOT_EQUALS;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofByteString(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw NOT_EQUALS;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofLazyMessage(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw NOT_EQUALS;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofMessage(boolean z, Object obj, Object obj2) {
            if (z && ((GeneratedMessageLite) obj).equals(this, (MessageLite) obj2)) {
                return obj;
            }
            throw NOT_EQUALS;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public void visitOneofNotSet(boolean z) {
            if (z) {
                throw NOT_EQUALS;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public <T extends MessageLite> T visitMessage(T t, T t2) {
            if (t == null && t2 == null) {
                return null;
            }
            if (t == null || t2 == null) {
                throw NOT_EQUALS;
            }
            ((GeneratedMessageLite) t).equals(this, t2);
            return t;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public LazyFieldLite visitLazyMessage(LazyFieldLite lazyFieldLite, LazyFieldLite lazyFieldLite2) {
            if (lazyFieldLite == null && lazyFieldLite2 == null) {
                return null;
            }
            if (lazyFieldLite == null || lazyFieldLite2 == null) {
                throw NOT_EQUALS;
            }
            if (lazyFieldLite.equals(lazyFieldLite2)) {
                return lazyFieldLite;
            }
            throw NOT_EQUALS;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public <T> Internal.ProtobufList<T> visitList(Internal.ProtobufList<T> protobufList, Internal.ProtobufList<T> protobufList2) {
            if (protobufList.equals(protobufList2)) {
                return protobufList;
            }
            throw NOT_EQUALS;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Internal.BooleanList visitBooleanList(Internal.BooleanList booleanList, Internal.BooleanList booleanList2) {
            if (booleanList.equals(booleanList2)) {
                return booleanList;
            }
            throw NOT_EQUALS;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Internal.IntList visitIntList(Internal.IntList intList, Internal.IntList intList2) {
            if (intList.equals(intList2)) {
                return intList;
            }
            throw NOT_EQUALS;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Internal.DoubleList visitDoubleList(Internal.DoubleList doubleList, Internal.DoubleList doubleList2) {
            if (doubleList.equals(doubleList2)) {
                return doubleList;
            }
            throw NOT_EQUALS;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Internal.FloatList visitFloatList(Internal.FloatList floatList, Internal.FloatList floatList2) {
            if (floatList.equals(floatList2)) {
                return floatList;
            }
            throw NOT_EQUALS;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Internal.LongList visitLongList(Internal.LongList longList, Internal.LongList longList2) {
            if (longList.equals(longList2)) {
                return longList;
            }
            throw NOT_EQUALS;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public FieldSet<ExtensionDescriptor> visitExtensions(FieldSet<ExtensionDescriptor> fieldSet, FieldSet<ExtensionDescriptor> fieldSet2) {
            if (fieldSet.equals(fieldSet2)) {
                return fieldSet;
            }
            throw NOT_EQUALS;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public UnknownFieldSetLite visitUnknownFields(UnknownFieldSetLite unknownFieldSetLite, UnknownFieldSetLite unknownFieldSetLite2) {
            if (unknownFieldSetLite.equals(unknownFieldSetLite2)) {
                return unknownFieldSetLite;
            }
            throw NOT_EQUALS;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public <K, V> MapFieldLite<K, V> visitMap(MapFieldLite<K, V> mapFieldLite, MapFieldLite<K, V> mapFieldLite2) {
            if (mapFieldLite.equals(mapFieldLite2)) {
                return mapFieldLite;
            }
            throw NOT_EQUALS;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class HashCodeVisitor implements Visitor {
        private int hashCode;

        private HashCodeVisitor() {
            this.hashCode = 0;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public boolean visitBoolean(boolean z, boolean z2, boolean z3, boolean z4) {
            this.hashCode = (this.hashCode * 53) + Internal.hashBoolean(z2);
            return z2;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public int visitInt(boolean z, int i, boolean z2, int i2) {
            this.hashCode = (this.hashCode * 53) + i;
            return i;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public double visitDouble(boolean z, double d, boolean z2, double d2) {
            this.hashCode = (this.hashCode * 53) + Internal.hashLong(Double.doubleToLongBits(d));
            return d;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public float visitFloat(boolean z, float f, boolean z2, float f2) {
            this.hashCode = (this.hashCode * 53) + Float.floatToIntBits(f);
            return f;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public long visitLong(boolean z, long j, boolean z2, long j2) {
            this.hashCode = (this.hashCode * 53) + Internal.hashLong(j);
            return j;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public String visitString(boolean z, String str, boolean z2, String str2) {
            this.hashCode = (this.hashCode * 53) + str.hashCode();
            return str;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public ByteString visitByteString(boolean z, ByteString byteString, boolean z2, ByteString byteString2) {
            this.hashCode = (this.hashCode * 53) + byteString.hashCode();
            return byteString;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofBoolean(boolean z, Object obj, Object obj2) {
            this.hashCode = (this.hashCode * 53) + Internal.hashBoolean(((Boolean) obj).booleanValue());
            return obj;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofInt(boolean z, Object obj, Object obj2) {
            this.hashCode = (this.hashCode * 53) + ((Integer) obj).intValue();
            return obj;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofDouble(boolean z, Object obj, Object obj2) {
            this.hashCode = (this.hashCode * 53) + Internal.hashLong(Double.doubleToLongBits(((Double) obj).doubleValue()));
            return obj;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofFloat(boolean z, Object obj, Object obj2) {
            this.hashCode = (this.hashCode * 53) + Float.floatToIntBits(((Float) obj).floatValue());
            return obj;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofLong(boolean z, Object obj, Object obj2) {
            this.hashCode = (this.hashCode * 53) + Internal.hashLong(((Long) obj).longValue());
            return obj;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofString(boolean z, Object obj, Object obj2) {
            this.hashCode = (this.hashCode * 53) + obj.hashCode();
            return obj;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofByteString(boolean z, Object obj, Object obj2) {
            this.hashCode = (this.hashCode * 53) + obj.hashCode();
            return obj;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofLazyMessage(boolean z, Object obj, Object obj2) {
            this.hashCode = (this.hashCode * 53) + obj.hashCode();
            return obj;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofMessage(boolean z, Object obj, Object obj2) {
            return visitMessage((MessageLite) obj, (MessageLite) obj2);
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public void visitOneofNotSet(boolean z) {
            if (z) {
                throw new IllegalStateException();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public <T extends MessageLite> T visitMessage(T t, T t2) {
            int i;
            if (t == null) {
                i = 37;
            } else if (t instanceof GeneratedMessageLite) {
                i = ((GeneratedMessageLite) t).hashCode(this);
            } else {
                i = t.hashCode();
            }
            this.hashCode = (this.hashCode * 53) + i;
            return t;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public LazyFieldLite visitLazyMessage(LazyFieldLite lazyFieldLite, LazyFieldLite lazyFieldLite2) {
            this.hashCode = (this.hashCode * 53) + (lazyFieldLite != null ? lazyFieldLite.hashCode() : 37);
            return lazyFieldLite;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public <T> Internal.ProtobufList<T> visitList(Internal.ProtobufList<T> protobufList, Internal.ProtobufList<T> protobufList2) {
            this.hashCode = (this.hashCode * 53) + protobufList.hashCode();
            return protobufList;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Internal.BooleanList visitBooleanList(Internal.BooleanList booleanList, Internal.BooleanList booleanList2) {
            this.hashCode = (this.hashCode * 53) + booleanList.hashCode();
            return booleanList;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Internal.IntList visitIntList(Internal.IntList intList, Internal.IntList intList2) {
            this.hashCode = (this.hashCode * 53) + intList.hashCode();
            return intList;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Internal.DoubleList visitDoubleList(Internal.DoubleList doubleList, Internal.DoubleList doubleList2) {
            this.hashCode = (this.hashCode * 53) + doubleList.hashCode();
            return doubleList;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Internal.FloatList visitFloatList(Internal.FloatList floatList, Internal.FloatList floatList2) {
            this.hashCode = (this.hashCode * 53) + floatList.hashCode();
            return floatList;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Internal.LongList visitLongList(Internal.LongList longList, Internal.LongList longList2) {
            this.hashCode = (this.hashCode * 53) + longList.hashCode();
            return longList;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public FieldSet<ExtensionDescriptor> visitExtensions(FieldSet<ExtensionDescriptor> fieldSet, FieldSet<ExtensionDescriptor> fieldSet2) {
            this.hashCode = (this.hashCode * 53) + fieldSet.hashCode();
            return fieldSet;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public UnknownFieldSetLite visitUnknownFields(UnknownFieldSetLite unknownFieldSetLite, UnknownFieldSetLite unknownFieldSetLite2) {
            this.hashCode = (this.hashCode * 53) + unknownFieldSetLite.hashCode();
            return unknownFieldSetLite;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public <K, V> MapFieldLite<K, V> visitMap(MapFieldLite<K, V> mapFieldLite, MapFieldLite<K, V> mapFieldLite2) {
            this.hashCode = (this.hashCode * 53) + mapFieldLite.hashCode();
            return mapFieldLite;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class MergeFromVisitor implements Visitor {
        public static final MergeFromVisitor INSTANCE = new MergeFromVisitor();

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public boolean visitBoolean(boolean z, boolean z2, boolean z3, boolean z4) {
            return z3 ? z4 : z2;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public ByteString visitByteString(boolean z, ByteString byteString, boolean z2, ByteString byteString2) {
            return z2 ? byteString2 : byteString;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public double visitDouble(boolean z, double d, boolean z2, double d2) {
            return z2 ? d2 : d;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public float visitFloat(boolean z, float f, boolean z2, float f2) {
            return z2 ? f2 : f;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public int visitInt(boolean z, int i, boolean z2, int i2) {
            return z2 ? i2 : i;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public long visitLong(boolean z, long j, boolean z2, long j2) {
            return z2 ? j2 : j;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofBoolean(boolean z, Object obj, Object obj2) {
            return obj2;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofByteString(boolean z, Object obj, Object obj2) {
            return obj2;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofDouble(boolean z, Object obj, Object obj2) {
            return obj2;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofFloat(boolean z, Object obj, Object obj2) {
            return obj2;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofInt(boolean z, Object obj, Object obj2) {
            return obj2;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofLong(boolean z, Object obj, Object obj2) {
            return obj2;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public void visitOneofNotSet(boolean z) {
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofString(boolean z, Object obj, Object obj2) {
            return obj2;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public String visitString(boolean z, String str, boolean z2, String str2) {
            return z2 ? str2 : str;
        }

        private MergeFromVisitor() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofLazyMessage(boolean z, Object obj, Object obj2) {
            LazyFieldLite lazyFieldLite = z ? (LazyFieldLite) obj : new LazyFieldLite();
            lazyFieldLite.merge((LazyFieldLite) obj2);
            return lazyFieldLite;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Object visitOneofMessage(boolean z, Object obj, Object obj2) {
            return z ? visitMessage((MessageLite) obj, (MessageLite) obj2) : obj2;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public <T extends MessageLite> T visitMessage(T t, T t2) {
            if (t == null || t2 == null) {
                return t != null ? t : t2;
            }
            return (T) t.toBuilder().mergeFrom(t2).build();
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public LazyFieldLite visitLazyMessage(LazyFieldLite lazyFieldLite, LazyFieldLite lazyFieldLite2) {
            if (lazyFieldLite2 != null) {
                if (lazyFieldLite == null) {
                    lazyFieldLite = new LazyFieldLite();
                }
                lazyFieldLite.merge(lazyFieldLite2);
            }
            return lazyFieldLite;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public <T> Internal.ProtobufList<T> visitList(Internal.ProtobufList<T> protobufList, Internal.ProtobufList<T> protobufList2) {
            int size = protobufList.size();
            int size2 = protobufList2.size();
            if (size > 0 && size2 > 0) {
                if (!protobufList.isModifiable()) {
                    protobufList = protobufList.mutableCopyWithCapacity(size2 + size);
                }
                protobufList.addAll(protobufList2);
            }
            return size > 0 ? protobufList : protobufList2;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Internal.BooleanList visitBooleanList(Internal.BooleanList booleanList, Internal.BooleanList booleanList2) {
            int size = booleanList.size();
            int size2 = booleanList2.size();
            Internal.BooleanList booleanList3 = booleanList;
            booleanList3 = booleanList;
            if (size > 0 && size2 > 0) {
                boolean isModifiable = booleanList.isModifiable();
                Internal.BooleanList booleanList4 = booleanList;
                if (!isModifiable) {
                    booleanList4 = booleanList.mutableCopyWithCapacity(size2 + size);
                }
                booleanList4.addAll(booleanList2);
                booleanList3 = booleanList4;
            }
            return size > 0 ? booleanList3 : booleanList2;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Internal.IntList visitIntList(Internal.IntList intList, Internal.IntList intList2) {
            int size = intList.size();
            int size2 = intList2.size();
            Internal.IntList intList3 = intList;
            intList3 = intList;
            if (size > 0 && size2 > 0) {
                boolean isModifiable = intList.isModifiable();
                Internal.IntList intList4 = intList;
                if (!isModifiable) {
                    intList4 = intList.mutableCopyWithCapacity(size2 + size);
                }
                intList4.addAll(intList2);
                intList3 = intList4;
            }
            return size > 0 ? intList3 : intList2;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Internal.DoubleList visitDoubleList(Internal.DoubleList doubleList, Internal.DoubleList doubleList2) {
            int size = doubleList.size();
            int size2 = doubleList2.size();
            Internal.DoubleList doubleList3 = doubleList;
            doubleList3 = doubleList;
            if (size > 0 && size2 > 0) {
                boolean isModifiable = doubleList.isModifiable();
                Internal.DoubleList doubleList4 = doubleList;
                if (!isModifiable) {
                    doubleList4 = doubleList.mutableCopyWithCapacity(size2 + size);
                }
                doubleList4.addAll(doubleList2);
                doubleList3 = doubleList4;
            }
            return size > 0 ? doubleList3 : doubleList2;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Internal.FloatList visitFloatList(Internal.FloatList floatList, Internal.FloatList floatList2) {
            int size = floatList.size();
            int size2 = floatList2.size();
            Internal.FloatList floatList3 = floatList;
            floatList3 = floatList;
            if (size > 0 && size2 > 0) {
                boolean isModifiable = floatList.isModifiable();
                Internal.FloatList floatList4 = floatList;
                if (!isModifiable) {
                    floatList4 = floatList.mutableCopyWithCapacity(size2 + size);
                }
                floatList4.addAll(floatList2);
                floatList3 = floatList4;
            }
            return size > 0 ? floatList3 : floatList2;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public Internal.LongList visitLongList(Internal.LongList longList, Internal.LongList longList2) {
            int size = longList.size();
            int size2 = longList2.size();
            Internal.LongList longList3 = longList;
            longList3 = longList;
            if (size > 0 && size2 > 0) {
                boolean isModifiable = longList.isModifiable();
                Internal.LongList longList4 = longList;
                if (!isModifiable) {
                    longList4 = longList.mutableCopyWithCapacity(size2 + size);
                }
                longList4.addAll(longList2);
                longList3 = longList4;
            }
            return size > 0 ? longList3 : longList2;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public FieldSet<ExtensionDescriptor> visitExtensions(FieldSet<ExtensionDescriptor> fieldSet, FieldSet<ExtensionDescriptor> fieldSet2) {
            if (fieldSet.isImmutable()) {
                fieldSet = fieldSet.m24471clone();
            }
            fieldSet.mergeFrom(fieldSet2);
            return fieldSet;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public UnknownFieldSetLite visitUnknownFields(UnknownFieldSetLite unknownFieldSetLite, UnknownFieldSetLite unknownFieldSetLite2) {
            return unknownFieldSetLite2 == UnknownFieldSetLite.getDefaultInstance() ? unknownFieldSetLite : UnknownFieldSetLite.mutableCopyOf(unknownFieldSetLite, unknownFieldSetLite2);
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Visitor
        public <K, V> MapFieldLite<K, V> visitMap(MapFieldLite<K, V> mapFieldLite, MapFieldLite<K, V> mapFieldLite2) {
            if (!mapFieldLite2.isEmpty()) {
                if (!mapFieldLite.isMutable()) {
                    mapFieldLite = mapFieldLite.mutableCopy();
                }
                mapFieldLite.mergeFrom(mapFieldLite2);
            }
            return mapFieldLite;
        }
    }
}
