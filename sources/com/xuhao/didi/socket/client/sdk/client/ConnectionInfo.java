package com.xuhao.didi.socket.client.sdk.client;

import java.io.Serializable;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class ConnectionInfo implements Serializable, Cloneable {
    private ConnectionInfo mBackupInfo;
    private String mIp;
    private int mPort;

    public ConnectionInfo(String str, int i) {
        this.mIp = str;
        this.mPort = i;
    }

    public String getIp() {
        return this.mIp;
    }

    public int getPort() {
        return this.mPort;
    }

    public ConnectionInfo getBackupInfo() {
        return this.mBackupInfo;
    }

    public void setBackupInfo(ConnectionInfo connectionInfo) {
        this.mBackupInfo = connectionInfo;
    }

    /* renamed from: clone */
    public ConnectionInfo m24485clone() {
        ConnectionInfo connectionInfo = new ConnectionInfo(this.mIp, this.mPort);
        ConnectionInfo connectionInfo2 = this.mBackupInfo;
        if (connectionInfo2 != null) {
            connectionInfo.setBackupInfo(connectionInfo2.m24485clone());
        }
        return connectionInfo;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ConnectionInfo) {
            ConnectionInfo connectionInfo = (ConnectionInfo) obj;
            if (this.mPort != connectionInfo.mPort) {
                return false;
            }
            return this.mIp.equals(connectionInfo.mIp);
        }
        return false;
    }

    public int hashCode() {
        return (this.mIp.hashCode() * 31) + this.mPort;
    }
}
