package com.xuhao.didi.socket.client.impl.client;

import com.xuhao.didi.core.utils.SLog;
import com.xuhao.didi.socket.client.impl.client.abilities.IConnectionSwitchListener;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.server.IServerManager;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.server.IServerManagerPrivate;
import com.xuhao.didi.socket.common.interfaces.utils.SPIUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class ManagerHolder {
    private volatile Map<ConnectionInfo, IConnectionManager> mConnectionManagerMap;
    private volatile Map<Integer, IServerManagerPrivate> mServerManagerMap;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class InstanceHolder {
        private static final ManagerHolder INSTANCE = new ManagerHolder();

        private InstanceHolder() {
        }
    }

    public static ManagerHolder getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private ManagerHolder() {
        this.mConnectionManagerMap = new HashMap();
        this.mServerManagerMap = new HashMap();
        this.mConnectionManagerMap.clear();
    }

    public IServerManager getServer(int i) {
        IServerManagerPrivate iServerManagerPrivate = this.mServerManagerMap.get(Integer.valueOf(i));
        if (iServerManagerPrivate == null) {
            IServerManagerPrivate iServerManagerPrivate2 = (IServerManagerPrivate) SPIUtils.load(IServerManager.class);
            if (iServerManagerPrivate2 == null) {
                SLog.m2258e("Oksocket.Server() load error. Server plug-in are required! For details link to https://github.com/xuuhaoo/OkSocket");
                throw new IllegalStateException("Oksocket.Server() load error. Server plug-in are required! For details link to https://github.com/xuuhaoo/OkSocket");
            }
            synchronized (this.mServerManagerMap) {
                this.mServerManagerMap.put(Integer.valueOf(i), iServerManagerPrivate2);
            }
            iServerManagerPrivate2.initServerPrivate(i);
            return iServerManagerPrivate2;
        }
        return iServerManagerPrivate;
    }

    public IConnectionManager getConnection(ConnectionInfo connectionInfo) {
        IConnectionManager iConnectionManager = this.mConnectionManagerMap.get(connectionInfo);
        if (iConnectionManager == null) {
            return getConnection(connectionInfo, OkSocketOptions.getDefault());
        }
        return getConnection(connectionInfo, iConnectionManager.getOption());
    }

    public IConnectionManager getConnection(ConnectionInfo connectionInfo, OkSocketOptions okSocketOptions) {
        IConnectionManager iConnectionManager = this.mConnectionManagerMap.get(connectionInfo);
        if (iConnectionManager != null) {
            if (!okSocketOptions.isConnectionHolden()) {
                synchronized (this.mConnectionManagerMap) {
                    this.mConnectionManagerMap.remove(connectionInfo);
                }
                return createNewManagerAndCache(connectionInfo, okSocketOptions);
            }
            iConnectionManager.option(okSocketOptions);
            return iConnectionManager;
        }
        return createNewManagerAndCache(connectionInfo, okSocketOptions);
    }

    private IConnectionManager createNewManagerAndCache(ConnectionInfo connectionInfo, OkSocketOptions okSocketOptions) {
        ConnectionManagerImpl connectionManagerImpl = new ConnectionManagerImpl(connectionInfo);
        connectionManagerImpl.option(okSocketOptions);
        connectionManagerImpl.setOnConnectionSwitchListener(new IConnectionSwitchListener() { // from class: com.xuhao.didi.socket.client.impl.client.ManagerHolder.1
            @Override // com.xuhao.didi.socket.client.impl.client.abilities.IConnectionSwitchListener
            public void onSwitchConnectionInfo(IConnectionManager iConnectionManager, ConnectionInfo connectionInfo2, ConnectionInfo connectionInfo3) {
                synchronized (ManagerHolder.this.mConnectionManagerMap) {
                    ManagerHolder.this.mConnectionManagerMap.remove(connectionInfo2);
                    ManagerHolder.this.mConnectionManagerMap.put(connectionInfo3, iConnectionManager);
                }
            }
        });
        synchronized (this.mConnectionManagerMap) {
            this.mConnectionManagerMap.put(connectionInfo, connectionManagerImpl);
        }
        return connectionManagerImpl;
    }

    protected List<IConnectionManager> getList() {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap(this.mConnectionManagerMap);
        Iterator it = hashMap.keySet().iterator();
        while (it.hasNext()) {
            IConnectionManager iConnectionManager = (IConnectionManager) hashMap.get((ConnectionInfo) it.next());
            if (!iConnectionManager.getOption().isConnectionHolden()) {
                it.remove();
            } else {
                arrayList.add(iConnectionManager);
            }
        }
        return arrayList;
    }
}
