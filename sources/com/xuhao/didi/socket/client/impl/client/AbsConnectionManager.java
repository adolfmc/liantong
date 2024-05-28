package com.xuhao.didi.socket.client.impl.client;

import com.xuhao.didi.socket.client.impl.client.abilities.IConnectionSwitchListener;
import com.xuhao.didi.socket.client.impl.client.action.ActionDispatcher;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;
import java.io.Serializable;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class AbsConnectionManager implements IConnectionManager {
    protected ActionDispatcher mActionDispatcher;
    private IConnectionSwitchListener mConnectionSwitchListener;
    protected ConnectionInfo mLocalConnectionInfo;
    protected ConnectionInfo mRemoteConnectionInfo;

    public AbsConnectionManager(ConnectionInfo connectionInfo) {
        this(connectionInfo, null);
    }

    public AbsConnectionManager(ConnectionInfo connectionInfo, ConnectionInfo connectionInfo2) {
        this.mRemoteConnectionInfo = connectionInfo;
        this.mLocalConnectionInfo = connectionInfo2;
        this.mActionDispatcher = new ActionDispatcher(connectionInfo, this);
    }

    @Override // com.xuhao.didi.socket.common.interfaces.common_interfacies.dispatcher.IRegister
    public IConnectionManager registerReceiver(ISocketActionListener iSocketActionListener) {
        this.mActionDispatcher.registerReceiver(iSocketActionListener);
        return this;
    }

    @Override // com.xuhao.didi.socket.common.interfaces.common_interfacies.dispatcher.IRegister
    public IConnectionManager unRegisterReceiver(ISocketActionListener iSocketActionListener) {
        this.mActionDispatcher.unRegisterReceiver(iSocketActionListener);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendBroadcast(String str, Serializable serializable) {
        this.mActionDispatcher.sendBroadcast(str, serializable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendBroadcast(String str) {
        this.mActionDispatcher.sendBroadcast(str);
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager
    public ConnectionInfo getRemoteConnectionInfo() {
        ConnectionInfo connectionInfo = this.mRemoteConnectionInfo;
        if (connectionInfo != null) {
            return connectionInfo.m24485clone();
        }
        return null;
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager
    public ConnectionInfo getLocalConnectionInfo() {
        ConnectionInfo connectionInfo = this.mLocalConnectionInfo;
        if (connectionInfo != null) {
            return connectionInfo;
        }
        return null;
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager
    public synchronized void switchConnectionInfo(ConnectionInfo connectionInfo) {
        if (connectionInfo != null) {
            ConnectionInfo connectionInfo2 = this.mRemoteConnectionInfo;
            this.mRemoteConnectionInfo = connectionInfo.m24485clone();
            if (this.mActionDispatcher != null) {
                this.mActionDispatcher.setConnectionInfo(this.mRemoteConnectionInfo);
            }
            if (this.mConnectionSwitchListener != null) {
                this.mConnectionSwitchListener.onSwitchConnectionInfo(this, connectionInfo2, this.mRemoteConnectionInfo);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setOnConnectionSwitchListener(IConnectionSwitchListener iConnectionSwitchListener) {
        this.mConnectionSwitchListener = iConnectionSwitchListener;
    }
}
