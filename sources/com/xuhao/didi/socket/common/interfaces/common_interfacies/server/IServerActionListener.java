package com.xuhao.didi.socket.common.interfaces.common_interfacies.server;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IServerActionListener {
    void onClientConnected(IClient iClient, int i, IClientPool iClientPool);

    void onClientDisconnected(IClient iClient, int i, IClientPool iClientPool);

    void onServerAlreadyShutdown(int i);

    void onServerListening(int i);

    void onServerWillBeShutdown(int i, IServerShutdown iServerShutdown, IClientPool iClientPool, Throwable th);
}
