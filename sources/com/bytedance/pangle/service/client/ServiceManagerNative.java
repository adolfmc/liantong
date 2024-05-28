package com.bytedance.pangle.service.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ServiceInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Keep;
import com.bytedance.pangle.InterfaceC3786d;
import com.bytedance.pangle.InterfaceC3821f;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.plugin.PluginManager;
import com.bytedance.pangle.servermanager.C3924b;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

@Keep
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ServiceManagerNative {
    private static volatile ServiceManagerNative sInstance;
    private final HashMap<ServiceConnection, InterfaceC3821f> serviceConn2ServiceConn = new HashMap<>();
    public HashMap<IBinder, HashMap<ServiceConnection, HashSet<ComponentName>>> process2ConnAndService = new HashMap<>();
    private HashMap<ServiceConnection, HashSet<ServiceInfo>> conn2Service = new HashMap<>();

    public static ServiceManagerNative getInstance() {
        if (sInstance == null) {
            synchronized (ServiceManagerNative.class) {
                if (sInstance == null) {
                    sInstance = new ServiceManagerNative();
                }
            }
        }
        return sInstance;
    }

    private ServiceManagerNative() {
    }

    public ServiceInfo queryServiceFromPlugin(Intent intent, String str) {
        Zeus.loadPlugin(str);
        ComponentName component = intent.getComponent();
        if (component == null) {
            return null;
        }
        return PluginManager.getInstance().getPlugin(str).pluginServices.get(component.getClassName());
    }

    public boolean bindServiceNative(Context context, Intent intent, final ServiceConnection serviceConnection, int i, String str) {
        ServiceInfo queryServiceFromPlugin = queryServiceFromPlugin(intent, str);
        if (queryServiceFromPlugin == null) {
            return context.bindService(intent, serviceConnection, i);
        }
        if (!this.serviceConn2ServiceConn.containsKey(serviceConnection)) {
            this.serviceConn2ServiceConn.put(serviceConnection, new InterfaceC3821f.AbstractBinderC3822a() { // from class: com.bytedance.pangle.service.client.ServiceManagerNative.1
                @Override // com.bytedance.pangle.InterfaceC3821f
                /* renamed from: a */
                public final void mo16661a(ComponentName componentName, IBinder iBinder) {
                    serviceConnection.onServiceConnected(componentName, iBinder);
                }

                @Override // com.bytedance.pangle.InterfaceC3821f
                /* renamed from: a */
                public final int mo16662a() {
                    return serviceConnection.hashCode();
                }
            });
        }
        if (this.conn2Service.get(serviceConnection) == null) {
            this.conn2Service.put(serviceConnection, new HashSet<>());
        }
        this.conn2Service.get(serviceConnection).add(queryServiceFromPlugin);
        InterfaceC3786d m16681a = C3924b.m16681a(queryServiceFromPlugin.processName);
        IBinder asBinder = m16681a.asBinder();
        HashMap<ServiceConnection, HashSet<ComponentName>> hashMap = this.process2ConnAndService.get(asBinder);
        if (hashMap == null) {
            hashMap = new HashMap<>();
            this.process2ConnAndService.put(asBinder, hashMap);
        }
        HashSet<ComponentName> hashSet = hashMap.get(serviceConnection);
        if (hashSet == null) {
            hashSet = new HashSet<>();
            hashMap.put(serviceConnection, hashSet);
        }
        hashSet.add(intent.getComponent());
        try {
            return m16681a.mo16677a(intent, this.serviceConn2ServiceConn.get(serviceConnection), i, str);
        } catch (RemoteException e) {
            ZeusLogger.errReport("Zeus/service_pangle", "bindService failed!", e);
            return false;
        }
    }

    public void unbindServiceNative(ServiceConnection serviceConnection) {
        HashSet<ServiceInfo> hashSet = this.conn2Service.get(serviceConnection);
        if (hashSet != null) {
            Iterator<ServiceInfo> it = hashSet.iterator();
            while (it.hasNext()) {
                try {
                    C3924b.m16681a(it.next().processName).mo16674a(this.serviceConn2ServiceConn.get(serviceConnection));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean stopServiceNative(Context context, Intent intent, String str) {
        ServiceInfo queryServiceFromPlugin = queryServiceFromPlugin(intent, str);
        if (queryServiceFromPlugin == null) {
            return context.stopService(intent);
        }
        try {
            return C3924b.m16681a(queryServiceFromPlugin.processName).mo16668b(intent, str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ComponentName startServiceNative(Context context, Intent intent, String str) {
        ServiceInfo queryServiceFromPlugin = queryServiceFromPlugin(intent, str);
        if (queryServiceFromPlugin == null) {
            return context.startService(intent);
        }
        try {
            return C3924b.m16681a(queryServiceFromPlugin.processName).mo16675a(intent, str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }
}
