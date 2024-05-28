package com.xuhao.didi.socket.client.sdk.client.connection;

import com.xuhao.didi.core.utils.SLog;
import com.xuhao.didi.socket.client.impl.exceptions.ManuallyDisconnectException;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.common.interfaces.basic.AbsLoopThread;
import com.xuhao.didi.socket.common.interfaces.utils.ThreadUtils;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DefaultReconnectManager extends AbsReconnectionManager {
    private static final int MAX_CONNECTION_FAILED_TIMES = 12;
    private int mConnectionFailedTimes = 0;
    private volatile ReconnectTestingThread mReconnectTestingThread = new ReconnectTestingThread();

    @Override // com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener
    public void onSocketDisconnection(ConnectionInfo connectionInfo, String str, Exception exc) {
        if (isNeedReconnect(exc)) {
            reconnectDelay();
        } else {
            resetThread();
        }
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener
    public void onSocketConnectionSuccess(ConnectionInfo connectionInfo, String str) {
        resetThread();
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener
    public void onSocketConnectionFailed(ConnectionInfo connectionInfo, String str, Exception exc) {
        if (exc != null) {
            this.mConnectionFailedTimes++;
            if (this.mConnectionFailedTimes > 12) {
                resetThread();
                ConnectionInfo remoteConnectionInfo = this.mConnectionManager.getRemoteConnectionInfo();
                ConnectionInfo backupInfo = remoteConnectionInfo.getBackupInfo();
                if (backupInfo != null) {
                    backupInfo.setBackupInfo(new ConnectionInfo(remoteConnectionInfo.getIp(), remoteConnectionInfo.getPort()));
                    if (this.mConnectionManager.isConnect()) {
                        return;
                    }
                    SLog.m2257i("Prepare switch to the backup line " + backupInfo.getIp() + ":" + backupInfo.getPort() + " ...");
                    synchronized (this.mConnectionManager) {
                        this.mConnectionManager.switchConnectionInfo(backupInfo);
                    }
                    reconnectDelay();
                    return;
                }
                reconnectDelay();
                return;
            }
            reconnectDelay();
        }
    }

    private boolean isNeedReconnect(Exception exc) {
        synchronized (this.mIgnoreDisconnectExceptionList) {
            if (exc != null) {
                if (!(exc instanceof ManuallyDisconnectException)) {
                    for (Class<? extends Exception> cls : this.mIgnoreDisconnectExceptionList) {
                        if (cls.isAssignableFrom(exc.getClass())) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            return false;
        }
    }

    private synchronized void resetThread() {
        if (this.mReconnectTestingThread != null) {
            this.mReconnectTestingThread.shutdown();
        }
    }

    private void reconnectDelay() {
        synchronized (this.mReconnectTestingThread) {
            if (this.mReconnectTestingThread.isShutdown()) {
                this.mReconnectTestingThread.start();
            }
        }
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.AbsReconnectionManager
    public void detach() {
        super.detach();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class ReconnectTestingThread extends AbsLoopThread {
        private long mReconnectTimeDelay;

        @Override // com.xuhao.didi.socket.common.interfaces.basic.AbsLoopThread
        public void loopFinish(Exception exc) {
        }

        private ReconnectTestingThread() {
            this.mReconnectTimeDelay = 10000L;
        }

        @Override // com.xuhao.didi.socket.common.interfaces.basic.AbsLoopThread
        public void beforeLoop() throws Exception {
            super.beforeLoop();
            if (this.mReconnectTimeDelay < DefaultReconnectManager.this.mConnectionManager.getOption().getConnectTimeoutSecond() * 1000) {
                this.mReconnectTimeDelay = DefaultReconnectManager.this.mConnectionManager.getOption().getConnectTimeoutSecond() * 1000;
            }
        }

        @Override // com.xuhao.didi.socket.common.interfaces.basic.AbsLoopThread
        public void runInLoopThread() throws Exception {
            if (DefaultReconnectManager.this.mDetach) {
                SLog.m2257i("ReconnectionManager already detached by framework.We decide gave up this reconnection mission!");
                shutdown();
                return;
            }
            SLog.m2257i("Reconnect after " + this.mReconnectTimeDelay + " mills ...");
            ThreadUtils.sleep(this.mReconnectTimeDelay);
            if (DefaultReconnectManager.this.mDetach) {
                SLog.m2257i("ReconnectionManager already detached by framework.We decide gave up this reconnection mission!");
                shutdown();
            } else if (DefaultReconnectManager.this.mConnectionManager.isConnect()) {
                shutdown();
            } else if (!DefaultReconnectManager.this.mConnectionManager.getOption().isConnectionHolden()) {
                DefaultReconnectManager.this.detach();
                shutdown();
            } else {
                ConnectionInfo remoteConnectionInfo = DefaultReconnectManager.this.mConnectionManager.getRemoteConnectionInfo();
                SLog.m2257i("Reconnect the server " + remoteConnectionInfo.getIp() + ":" + remoteConnectionInfo.getPort() + " ...");
                synchronized (DefaultReconnectManager.this.mConnectionManager) {
                    if (!DefaultReconnectManager.this.mConnectionManager.isConnect()) {
                        DefaultReconnectManager.this.mConnectionManager.connect();
                    } else {
                        shutdown();
                    }
                }
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass();
    }
}
