package com.xuhao.didi.socket.client.sdk.client;

import java.net.Socket;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class OkSocketFactory {
    public abstract Socket createSocket(ConnectionInfo connectionInfo, OkSocketOptions okSocketOptions) throws Exception;
}
