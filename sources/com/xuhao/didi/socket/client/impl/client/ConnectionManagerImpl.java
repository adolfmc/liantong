package com.xuhao.didi.socket.client.impl.client;

import com.xuhao.didi.core.iocore.interfaces.ISendable;
import com.xuhao.didi.core.utils.SLog;
import com.xuhao.didi.socket.client.impl.client.action.ActionHandler;
import com.xuhao.didi.socket.client.impl.client.iothreads.IOThreadManager;
import com.xuhao.didi.socket.client.impl.exceptions.ManuallyDisconnectException;
import com.xuhao.didi.socket.client.impl.exceptions.UnConnectException;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.client.sdk.client.OkSocketSSLConfig;
import com.xuhao.didi.socket.client.sdk.client.connection.AbsReconnectionManager;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.IIOManager;
import com.xuhao.didi.socket.common.interfaces.default_protocol.DefaultX509ProtocolTrustManager;
import com.xuhao.didi.socket.common.interfaces.utils.TextUtils;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.SecureRandom;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class ConnectionManagerImpl extends AbsConnectionManager {
    private volatile boolean isConnectionPermitted;
    private volatile boolean isDisconnecting;
    private ActionHandler mActionHandler;
    private Thread mConnectThread;
    private IIOManager mManager;
    private volatile OkSocketOptions mOptions;
    private volatile PulseManager mPulseManager;
    private volatile AbsReconnectionManager mReconnectionManager;
    private volatile Socket mSocket;

    /* JADX INFO: Access modifiers changed from: protected */
    public ConnectionManagerImpl(ConnectionInfo connectionInfo) {
        this(connectionInfo, null);
    }

    public ConnectionManagerImpl(ConnectionInfo connectionInfo, ConnectionInfo connectionInfo2) {
        super(connectionInfo, connectionInfo2);
        this.isConnectionPermitted = true;
        this.isDisconnecting = false;
        String str = "";
        String str2 = "";
        if (connectionInfo != null) {
            str = connectionInfo.getIp();
            str2 = connectionInfo.getPort() + "";
        }
        SLog.m2257i("block connection init with:" + str + ":" + str2);
        if (connectionInfo2 != null) {
            SLog.m2257i("binding local addr:" + connectionInfo2.getIp() + " port:" + connectionInfo2.getPort());
        }
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.abilities.IConnectable
    public synchronized void connect() {
        SLog.m2257i("Thread name:" + Thread.currentThread().getName() + " id:" + Thread.currentThread().getId());
        if (this.isConnectionPermitted) {
            this.isConnectionPermitted = false;
            if (isConnect()) {
                return;
            }
            this.isDisconnecting = false;
            if (this.mRemoteConnectionInfo == null) {
                this.isConnectionPermitted = true;
                throw new UnConnectException("连接参数为空,检查连接参数");
            }
            if (this.mActionHandler != null) {
                this.mActionHandler.detach(this);
                SLog.m2257i("mActionHandler is detached.");
            }
            this.mActionHandler = new ActionHandler();
            this.mActionHandler.attach(this, this);
            SLog.m2257i("mActionHandler is attached.");
            if (this.mReconnectionManager != null) {
                this.mReconnectionManager.detach();
                SLog.m2257i("ReconnectionManager is detached.");
            }
            this.mReconnectionManager = this.mOptions.getReconnectionManager();
            if (this.mReconnectionManager != null) {
                this.mReconnectionManager.attach(this);
                SLog.m2257i("ReconnectionManager is attached.");
            }
            this.mConnectThread = new ConnectionThread(" Connect thread for " + (this.mRemoteConnectionInfo.getIp() + ":" + this.mRemoteConnectionInfo.getPort()));
            this.mConnectThread.setDaemon(true);
            this.mConnectThread.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized Socket getSocketByConfig() throws Exception {
        if (this.mOptions.getOkSocketFactory() != null) {
            return this.mOptions.getOkSocketFactory().createSocket(this.mRemoteConnectionInfo, this.mOptions);
        }
        OkSocketSSLConfig sSLConfig = this.mOptions.getSSLConfig();
        if (sSLConfig == null) {
            return new Socket();
        }
        SSLSocketFactory customSSLFactory = sSLConfig.getCustomSSLFactory();
        if (customSSLFactory == null) {
            String protocol = TextUtils.isEmpty(sSLConfig.getProtocol()) ? "SSL" : sSLConfig.getProtocol();
            TrustManager[] trustManagers = sSLConfig.getTrustManagers();
            if (trustManagers == null || trustManagers.length == 0) {
                trustManagers = new TrustManager[]{new DefaultX509ProtocolTrustManager()};
            }
            try {
                SSLContext sSLContext = SSLContext.getInstance(protocol);
                sSLContext.init(sSLConfig.getKeyManagers(), trustManagers, new SecureRandom());
                return sSLContext.getSocketFactory().createSocket();
            } catch (Exception e) {
                if (this.mOptions.isDebug()) {
                    e.printStackTrace();
                }
                SLog.m2258e(e.getMessage());
                return new Socket();
            }
        }
        try {
            return customSSLFactory.createSocket();
        } catch (IOException e2) {
            if (this.mOptions.isDebug()) {
                e2.printStackTrace();
            }
            SLog.m2258e(e2.getMessage());
            return new Socket();
        }
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    class ConnectionThread extends Thread {
        public ConnectionThread(String str) {
            super(str);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                try {
                    try {
                        ConnectionManagerImpl.this.mSocket = ConnectionManagerImpl.this.getSocketByConfig();
                        if (ConnectionManagerImpl.this.mLocalConnectionInfo != null) {
                            SLog.m2257i("try bind: " + ConnectionManagerImpl.this.mLocalConnectionInfo.getIp() + " port:" + ConnectionManagerImpl.this.mLocalConnectionInfo.getPort());
                            ConnectionManagerImpl.this.mSocket.bind(new InetSocketAddress(ConnectionManagerImpl.this.mLocalConnectionInfo.getIp(), ConnectionManagerImpl.this.mLocalConnectionInfo.getPort()));
                        }
                        SLog.m2257i("Start connect: " + ConnectionManagerImpl.this.mRemoteConnectionInfo.getIp() + ":" + ConnectionManagerImpl.this.mRemoteConnectionInfo.getPort() + " socket server...");
                        ConnectionManagerImpl.this.mSocket.connect(new InetSocketAddress(ConnectionManagerImpl.this.mRemoteConnectionInfo.getIp(), ConnectionManagerImpl.this.mRemoteConnectionInfo.getPort()), ConnectionManagerImpl.this.mOptions.getConnectTimeoutSecond() * 1000);
                        ConnectionManagerImpl.this.mSocket.setTcpNoDelay(true);
                        ConnectionManagerImpl.this.resolveManager();
                        ConnectionManagerImpl.this.sendBroadcast("action_connection_success");
                        SLog.m2257i("Socket server: " + ConnectionManagerImpl.this.mRemoteConnectionInfo.getIp() + ":" + ConnectionManagerImpl.this.mRemoteConnectionInfo.getPort() + " connect successful!");
                    } catch (Throwable th) {
                        ConnectionManagerImpl.this.isConnectionPermitted = true;
                        throw th;
                    }
                } catch (Exception e) {
                    if (ConnectionManagerImpl.this.mOptions.isDebug()) {
                        e.printStackTrace();
                    }
                    throw new UnConnectException("Create socket failed.", e);
                }
            } catch (Exception e2) {
                if (ConnectionManagerImpl.this.mOptions.isDebug()) {
                    e2.printStackTrace();
                }
                UnConnectException unConnectException = new UnConnectException(e2);
                SLog.m2258e("Socket server " + ConnectionManagerImpl.this.mRemoteConnectionInfo.getIp() + ":" + ConnectionManagerImpl.this.mRemoteConnectionInfo.getPort() + " connect failed! error msg:" + e2.getMessage());
                ConnectionManagerImpl.this.sendBroadcast("action_connection_failed", unConnectException);
            }
            ConnectionManagerImpl.this.isConnectionPermitted = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resolveManager() throws IOException {
        this.mPulseManager = new PulseManager(this, this.mOptions);
        this.mManager = new IOThreadManager(this.mSocket.getInputStream(), this.mSocket.getOutputStream(), this.mOptions, this.mActionDispatcher);
        this.mManager.startEngine();
    }

    @Override // com.xuhao.didi.socket.common.interfaces.common_interfacies.client.IDisConnectable
    public void disconnect(Exception exc) {
        synchronized (this) {
            if (this.isDisconnecting) {
                return;
            }
            this.isDisconnecting = true;
            if (this.mPulseManager != null) {
                this.mPulseManager.dead();
                this.mPulseManager = null;
            }
            if ((exc instanceof ManuallyDisconnectException) && this.mReconnectionManager != null) {
                this.mReconnectionManager.detach();
                SLog.m2257i("ReconnectionManager is detached.");
            }
            DisconnectThread disconnectThread = new DisconnectThread(exc, "Disconnect Thread for " + (this.mRemoteConnectionInfo.getIp() + ":" + this.mRemoteConnectionInfo.getPort()));
            disconnectThread.setDaemon(true);
            disconnectThread.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class DisconnectThread extends Thread {
        private Exception mException;

        public DisconnectThread(Exception exc, String str) {
            super(str);
            this.mException = exc;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v30, types: [java.lang.Exception] */
        /* JADX WARN: Type inference failed for: r0v32 */
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                if (ConnectionManagerImpl.this.mManager != null) {
                    ConnectionManagerImpl.this.mManager.close(this.mException);
                }
                if (ConnectionManagerImpl.this.mConnectThread != null && ConnectionManagerImpl.this.mConnectThread.isAlive()) {
                    ConnectionManagerImpl.this.mConnectThread.interrupt();
                    try {
                        SLog.m2257i("disconnect thread need waiting for connection thread done.");
                        ConnectionManagerImpl.this.mConnectThread.join();
                    } catch (InterruptedException unused) {
                    }
                    SLog.m2257i("connection thread is done. disconnection thread going on");
                    ConnectionManagerImpl.this.mConnectThread = null;
                }
                if (ConnectionManagerImpl.this.mSocket != null) {
                    try {
                        ConnectionManagerImpl.this.mSocket.close();
                    } catch (IOException unused2) {
                    }
                }
                if (ConnectionManagerImpl.this.mActionHandler != null) {
                    ConnectionManagerImpl.this.mActionHandler.detach(ConnectionManagerImpl.this);
                    SLog.m2257i("mActionHandler is detached.");
                    ConnectionManagerImpl.this.mActionHandler = null;
                }
            } finally {
                ConnectionManagerImpl.this.isDisconnecting = false;
                ConnectionManagerImpl.this.isConnectionPermitted = true;
                if (!(this.mException instanceof UnConnectException) && ConnectionManagerImpl.this.mSocket != null) {
                    Exception exc = this.mException;
                    if (exc instanceof ManuallyDisconnectException) {
                        exc = null;
                    }
                    this.mException = exc;
                    ConnectionManagerImpl.this.sendBroadcast("action_disconnection", this.mException);
                }
                ConnectionManagerImpl.this.mSocket = null;
                if (this.mException != null) {
                    SLog.m2258e("socket is disconnecting because: " + this.mException.getMessage());
                    if (ConnectionManagerImpl.this.mOptions.isDebug()) {
                        this.mException.printStackTrace();
                    }
                }
            }
        }
    }

    @Override // com.xuhao.didi.socket.common.interfaces.common_interfacies.client.IDisConnectable
    public void disconnect() {
        disconnect(new ManuallyDisconnectException());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.xuhao.didi.socket.common.interfaces.common_interfacies.client.ISender
    public IConnectionManager send(ISendable iSendable) {
        if (this.mManager != null && iSendable != null && isConnect()) {
            this.mManager.send(iSendable);
        }
        return this;
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.abilities.IConfiguration
    public IConnectionManager option(OkSocketOptions okSocketOptions) {
        if (okSocketOptions == null) {
            return this;
        }
        this.mOptions = okSocketOptions;
        IIOManager iIOManager = this.mManager;
        if (iIOManager != null) {
            iIOManager.setOkOptions(this.mOptions);
        }
        if (this.mPulseManager != null) {
            this.mPulseManager.setOkOptions(this.mOptions);
        }
        if (this.mReconnectionManager != null && !this.mReconnectionManager.equals(this.mOptions.getReconnectionManager())) {
            if (this.mReconnectionManager != null) {
                this.mReconnectionManager.detach();
            }
            SLog.m2257i("reconnection manager is replaced");
            this.mReconnectionManager = this.mOptions.getReconnectionManager();
            this.mReconnectionManager.attach(this);
        }
        return this;
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.abilities.IConfiguration
    public OkSocketOptions getOption() {
        return this.mOptions;
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager
    public boolean isConnect() {
        return (this.mSocket == null || !this.mSocket.isConnected() || this.mSocket.isClosed()) ? false : true;
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager
    public boolean isDisconnecting() {
        return this.isDisconnecting;
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager
    public PulseManager getPulseManager() {
        return this.mPulseManager;
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager
    public void setIsConnectionHolder(boolean z) {
        this.mOptions = new OkSocketOptions.Builder(this.mOptions).setConnectionHolden(z).build();
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager
    public AbsReconnectionManager getReconnectionManager() {
        return this.mOptions.getReconnectionManager();
    }

    @Override // com.xuhao.didi.socket.client.impl.client.AbsConnectionManager, com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager
    public ConnectionInfo getLocalConnectionInfo() {
        InetSocketAddress inetSocketAddress;
        ConnectionInfo localConnectionInfo = super.getLocalConnectionInfo();
        return (localConnectionInfo == null && isConnect() && (inetSocketAddress = (InetSocketAddress) this.mSocket.getLocalSocketAddress()) != null) ? new ConnectionInfo(inetSocketAddress.getHostName(), inetSocketAddress.getPort()) : localConnectionInfo;
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager
    public void setLocalConnectionInfo(ConnectionInfo connectionInfo) {
        if (isConnect()) {
            throw new IllegalStateException("Socket is connected, can't set local info after connect.");
        }
        this.mLocalConnectionInfo = connectionInfo;
    }
}
