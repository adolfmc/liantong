package com.google.protobuf;

import com.google.protobuf.Descriptors;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface MessageOrBuilder extends MessageLiteOrBuilder {
    List<String> findInitializationErrors();

    Map<Descriptors.FieldDescriptor, Object> getAllFields();

    @Override // 
    Message getDefaultInstanceForType();

    Descriptors.Descriptor getDescriptorForType();

    Object getField(Descriptors.FieldDescriptor fieldDescriptor);

    String getInitializationErrorString();

    Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneofDescriptor);

    Object getRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i);

    int getRepeatedFieldCount(Descriptors.FieldDescriptor fieldDescriptor);

    UnknownFieldSet getUnknownFields();

    boolean hasField(Descriptors.FieldDescriptor fieldDescriptor);

    boolean hasOneof(Descriptors.OneofDescriptor oneofDescriptor);
}
