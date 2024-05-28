package com.xuhao.didi.socket.client.sdk.client.action;

import com.xuhao.didi.core.iocore.interfaces.IPulseSendable;
import com.xuhao.didi.core.iocore.interfaces.ISendable;
import com.xuhao.didi.core.pojo.OriginalData;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface ISocketActionListener {
    void onPulseSend(ConnectionInfo connectionInfo, IPulseSendable iPulseSendable);

    void onSocketConnectionFailed(ConnectionInfo connectionInfo, String str, Exception exc);

    void onSocketConnectionSuccess(ConnectionInfo connectionInfo, String str);

    void onSocketDisconnection(ConnectionInfo connectionInfo, String str, Exception exc);

    void onSocketIOThreadShutdown(String str, Exception exc);

    void onSocketIOThreadStart(String str);

    void onSocketReadResponse(ConnectionInfo connectionInfo, String str, OriginalData originalData);

    void onSocketWriteResponse(ConnectionInfo connectionInfo, String str, ISendable iSendable);
}
