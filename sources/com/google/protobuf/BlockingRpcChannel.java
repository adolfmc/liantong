package com.google.protobuf;

import com.google.protobuf.Descriptors;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface BlockingRpcChannel {
    Message callBlockingMethod(Descriptors.MethodDescriptor methodDescriptor, RpcController rpcController, Message message, Message message2) throws ServiceException;
}
