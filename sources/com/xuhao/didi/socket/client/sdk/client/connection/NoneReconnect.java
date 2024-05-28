package com.xuhao.didi.socket.client.sdk.client.connection;

import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class NoneReconnect extends AbsReconnectionManager {
    @Override // com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener
    public void onSocketConnectionFailed(ConnectionInfo connectionInfo, String str, Exception exc) {
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener
    public void onSocketConnectionSuccess(ConnectionInfo connectionInfo, String str) {
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener
    public void onSocketDisconnection(ConnectionInfo connectionInfo, String str, Exception exc) {
    }
}
