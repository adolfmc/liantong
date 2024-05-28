package com.xuhao.didi.socket.client.sdk.client.connection;

import com.xuhao.didi.core.iocore.interfaces.IPulseSendable;
import com.xuhao.didi.core.iocore.interfaces.ISendable;
import com.xuhao.didi.core.pojo.OriginalData;
import com.xuhao.didi.socket.client.impl.client.PulseManager;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener;
import java.util.LinkedHashSet;
import java.util.Set;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class AbsReconnectionManager implements ISocketActionListener {
    protected volatile IConnectionManager mConnectionManager;
    protected volatile boolean mDetach;
    protected volatile Set<Class<? extends Exception>> mIgnoreDisconnectExceptionList = new LinkedHashSet();
    protected PulseManager mPulseManager;

    @Override // com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener
    public void onPulseSend(ConnectionInfo connectionInfo, IPulseSendable iPulseSendable) {
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

    public synchronized void attach(IConnectionManager iConnectionManager) {
        if (this.mDetach) {
            detach();
        }
        this.mDetach = false;
        this.mConnectionManager = iConnectionManager;
        this.mPulseManager = iConnectionManager.getPulseManager();
        this.mConnectionManager.registerReceiver(this);
    }

    public synchronized void detach() {
        this.mDetach = true;
        if (this.mConnectionManager != null) {
            this.mConnectionManager.unRegisterReceiver(this);
        }
    }

    public final void addIgnoreException(Class<? extends Exception> cls) {
        synchronized (this.mIgnoreDisconnectExceptionList) {
            this.mIgnoreDisconnectExceptionList.add(cls);
        }
    }

    public final void removeIgnoreException(Exception exc) {
        synchronized (this.mIgnoreDisconnectExceptionList) {
            this.mIgnoreDisconnectExceptionList.remove(exc.getClass());
        }
    }

    public final void removeIgnoreException(Class<? extends Exception> cls) {
        synchronized (this.mIgnoreDisconnectExceptionList) {
            this.mIgnoreDisconnectExceptionList.remove(cls);
        }
    }

    public final void removeAll() {
        synchronized (this.mIgnoreDisconnectExceptionList) {
            this.mIgnoreDisconnectExceptionList.clear();
        }
    }
}
