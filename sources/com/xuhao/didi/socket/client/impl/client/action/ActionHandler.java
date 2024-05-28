package com.xuhao.didi.socket.client.impl.client.action;

import com.xuhao.didi.socket.client.impl.exceptions.ManuallyDisconnectException;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener;
import com.xuhao.didi.socket.client.sdk.client.action.SocketActionAdapter;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.dispatcher.IRegister;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class ActionHandler extends SocketActionAdapter {
    private boolean iOThreadIsCalledDisconnect = false;
    private OkSocketOptions.IOThreadMode mCurrentThreadMode;
    private IConnectionManager mManager;

    public void attach(IConnectionManager iConnectionManager, IRegister<ISocketActionListener, IConnectionManager> iRegister) {
        this.mManager = iConnectionManager;
        iRegister.registerReceiver(this);
    }

    public void detach(IRegister iRegister) {
        iRegister.unRegisterReceiver(this);
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.action.SocketActionAdapter, com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener
    public void onSocketIOThreadStart(String str) {
        if (this.mManager.getOption().getIOThreadMode() != this.mCurrentThreadMode) {
            this.mCurrentThreadMode = this.mManager.getOption().getIOThreadMode();
        }
        this.iOThreadIsCalledDisconnect = false;
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.action.SocketActionAdapter, com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener
    public void onSocketIOThreadShutdown(String str, Exception exc) {
        if (this.mCurrentThreadMode == this.mManager.getOption().getIOThreadMode() && !this.iOThreadIsCalledDisconnect) {
            this.iOThreadIsCalledDisconnect = true;
            if (exc instanceof ManuallyDisconnectException) {
                return;
            }
            this.mManager.disconnect(exc);
        }
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.action.SocketActionAdapter, com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener
    public void onSocketConnectionFailed(ConnectionInfo connectionInfo, String str, Exception exc) {
        this.mManager.disconnect(exc);
    }
}
