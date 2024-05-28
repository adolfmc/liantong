package com.xuhao.didi.socket.client.sdk.client.connection;

import com.xuhao.didi.socket.client.impl.client.PulseManager;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener;
import com.xuhao.didi.socket.client.sdk.client.connection.abilities.IConfiguration;
import com.xuhao.didi.socket.client.sdk.client.connection.abilities.IConnectable;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.client.IDisConnectable;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.client.ISender;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.dispatcher.IRegister;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IConnectionManager extends IConfiguration, IConnectable, IDisConnectable, ISender<IConnectionManager>, IRegister<ISocketActionListener, IConnectionManager> {
    ConnectionInfo getLocalConnectionInfo();

    PulseManager getPulseManager();

    AbsReconnectionManager getReconnectionManager();

    ConnectionInfo getRemoteConnectionInfo();

    boolean isConnect();

    boolean isDisconnecting();

    void setIsConnectionHolder(boolean z);

    void setLocalConnectionInfo(ConnectionInfo connectionInfo);

    void switchConnectionInfo(ConnectionInfo connectionInfo);
}
