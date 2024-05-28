package com.xuhao.didi.socket.client.sdk;

import com.xuhao.didi.socket.client.impl.client.ManagerHolder;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.dispatcher.IRegister;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.server.IServerActionListener;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.server.IServerManager;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class OkSocket {
    private static ManagerHolder holder = ManagerHolder.getInstance();

    public static IRegister<IServerActionListener, IServerManager> server(int i) {
        return (IRegister) holder.getServer(i);
    }

    public static IConnectionManager open(ConnectionInfo connectionInfo) {
        return holder.getConnection(connectionInfo);
    }

    public static IConnectionManager open(String str, int i) {
        return holder.getConnection(new ConnectionInfo(str, i));
    }

    public static IConnectionManager open(ConnectionInfo connectionInfo, OkSocketOptions okSocketOptions) {
        return holder.getConnection(connectionInfo, okSocketOptions);
    }

    public static IConnectionManager open(String str, int i, OkSocketOptions okSocketOptions) {
        return holder.getConnection(new ConnectionInfo(str, i), okSocketOptions);
    }
}
