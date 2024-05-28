package com.xuhao.didi.socket.client.sdk.client.action;

import com.xuhao.didi.core.iocore.interfaces.IPulseSendable;
import com.xuhao.didi.core.iocore.interfaces.ISendable;
import com.xuhao.didi.core.pojo.OriginalData;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class SocketActionAdapter implements ISocketActionListener {
    @Override // com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener
    public void onPulseSend(ConnectionInfo connectionInfo, IPulseSendable iPulseSendable) {
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener
    public void onSocketConnectionFailed(ConnectionInfo connectionInfo, String str, Exception exc) {
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener
    public void onSocketConnectionSuccess(ConnectionInfo connectionInfo, String str) {
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener
    public void onSocketDisconnection(ConnectionInfo connectionInfo, String str, Exception exc) {
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener
    public void onSocketIOThreadShutdown(String str, Exception exc) {
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener
    public void onSocketIOThreadStart(String str) {
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener
    public void onSocketReadResponse(ConnectionInfo connectionInfo, String str, OriginalData originalData) {
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener
    public void onSocketWriteResponse(ConnectionInfo connectionInfo, String str, ISendable iSendable) {
    }
}
