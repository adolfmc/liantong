package com.xuhao.didi.socket.common.interfaces.common_interfacies.server;

import com.xuhao.didi.core.iocore.interfaces.ISendable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IClientPool<T, K> {
    void cache(T t);

    T findByUniqueTag(K k);

    void sendToAll(ISendable iSendable);

    int size();
}
