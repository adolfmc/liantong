package com.xuhao.didi.socket.client.impl.client.abilities;

import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IConnectionSwitchListener {
    void onSwitchConnectionInfo(IConnectionManager iConnectionManager, ConnectionInfo connectionInfo, ConnectionInfo connectionInfo2);
}
