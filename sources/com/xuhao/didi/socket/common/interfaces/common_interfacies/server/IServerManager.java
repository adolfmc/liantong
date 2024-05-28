package com.xuhao.didi.socket.common.interfaces.common_interfacies.server;

import com.xuhao.didi.core.iocore.interfaces.IIOCoreOptions;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IServerManager<E extends IIOCoreOptions> extends IServerShutdown {
    IClientPool<String, IClient> getClientPool();

    boolean isLive();

    void listen();

    void listen(E e);
}
