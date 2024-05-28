package com.google.protobuf;

import com.google.protobuf.Descriptors;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface Service {
    void callMethod(Descriptors.MethodDescriptor methodDescriptor, RpcController rpcController, Message message, RpcCallback<Message> rpcCallback);

    Descriptors.ServiceDescriptor getDescriptorForType();

    Message getRequestPrototype(Descriptors.MethodDescriptor methodDescriptor);

    Message getResponsePrototype(Descriptors.MethodDescriptor methodDescriptor);
}
