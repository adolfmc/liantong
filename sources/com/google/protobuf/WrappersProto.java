package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class WrappersProto {
    private static Descriptors.FileDescriptor descriptor;
    static final Descriptors.Descriptor internal_static_google_protobuf_BoolValue_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_BoolValue_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_protobuf_BytesValue_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_BytesValue_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_protobuf_DoubleValue_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_DoubleValue_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_protobuf_FloatValue_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_FloatValue_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_protobuf_Int32Value_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_Int32Value_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_protobuf_Int64Value_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_Int64Value_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_protobuf_StringValue_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_StringValue_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_protobuf_UInt32Value_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_UInt32Value_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_protobuf_UInt64Value_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_UInt64Value_fieldAccessorTable;

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private WrappersProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001egoogle/protobuf/wrappers.proto\u0012\u000fgoogle.protobuf\"\u001c\n\u000bDoubleValue\u0012\r\n\u0005value\u0018\u0001 \u0001(\u0001\"\u001b\n\nFloatValue\u0012\r\n\u0005value\u0018\u0001 \u0001(\u0002\"\u001b\n\nInt64Value\u0012\r\n\u0005value\u0018\u0001 \u0001(\u0003\"\u001c\n\u000bUInt64Value\u0012\r\n\u0005value\u0018\u0001 \u0001(\u0004\"\u001b\n\nInt32Value\u0012\r\n\u0005value\u0018\u0001 \u0001(\u0005\"\u001c\n\u000bUInt32Value\u0012\r\n\u0005value\u0018\u0001 \u0001(\r\"\u001a\n\tBoolValue\u0012\r\n\u0005value\u0018\u0001 \u0001(\b\"\u001c\n\u000bStringValue\u0012\r\n\u0005value\u0018\u0001 \u0001(\t\"\u001b\n\nBytesValue\u0012\r\n\u0005value\u0018\u0001 \u0001(\fB|\n\u0013com.google.protobufB\rWrappersProtoP\u0001Z*github.com/golang/protobuf/ptypes/wrappersø\u0001\u0001", "¢\u0002\u0003GPBª\u0002\u001eGoogle.Protobuf.WellKnownTypesb\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.google.protobuf.WrappersProto.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = WrappersProto.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_google_protobuf_DoubleValue_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_google_protobuf_DoubleValue_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_protobuf_DoubleValue_descriptor, new String[]{"Value"});
        internal_static_google_protobuf_FloatValue_descriptor = getDescriptor().getMessageTypes().get(1);
        internal_static_google_protobuf_FloatValue_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_protobuf_FloatValue_descriptor, new String[]{"Value"});
        internal_static_google_protobuf_Int64Value_descriptor = getDescriptor().getMessageTypes().get(2);
        internal_static_google_protobuf_Int64Value_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_protobuf_Int64Value_descriptor, new String[]{"Value"});
        internal_static_google_protobuf_UInt64Value_descriptor = getDescriptor().getMessageTypes().get(3);
        internal_static_google_protobuf_UInt64Value_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_protobuf_UInt64Value_descriptor, new String[]{"Value"});
        internal_static_google_protobuf_Int32Value_descriptor = getDescriptor().getMessageTypes().get(4);
        internal_static_google_protobuf_Int32Value_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_protobuf_Int32Value_descriptor, new String[]{"Value"});
        internal_static_google_protobuf_UInt32Value_descriptor = getDescriptor().getMessageTypes().get(5);
        internal_static_google_protobuf_UInt32Value_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_protobuf_UInt32Value_descriptor, new String[]{"Value"});
        internal_static_google_protobuf_BoolValue_descriptor = getDescriptor().getMessageTypes().get(6);
        internal_static_google_protobuf_BoolValue_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_protobuf_BoolValue_descriptor, new String[]{"Value"});
        internal_static_google_protobuf_StringValue_descriptor = getDescriptor().getMessageTypes().get(7);
        internal_static_google_protobuf_StringValue_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_protobuf_StringValue_descriptor, new String[]{"Value"});
        internal_static_google_protobuf_BytesValue_descriptor = getDescriptor().getMessageTypes().get(8);
        internal_static_google_protobuf_BytesValue_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_protobuf_BytesValue_descriptor, new String[]{"Value"});
    }
}
