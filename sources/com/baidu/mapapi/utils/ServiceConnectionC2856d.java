package com.baidu.mapapi.utils;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.baidu.mapframework.open.aidl.IMapOpenService;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: MapOpenUtil.java */
/* renamed from: com.baidu.mapapi.utils.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class ServiceConnectionC2856d implements ServiceConnection {
    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Thread thread;
        String str;
        String str2;
        IMapOpenService iMapOpenService;
        IMapOpenService iMapOpenService2;
        IMapOpenService iMapOpenService3;
        Thread thread2;
        thread = MapOpenUtil.f7018x;
        if (thread != null) {
            thread2 = MapOpenUtil.f7018x;
            thread2.interrupt();
        }
        str = MapOpenUtil.f6997c;
        Log.d(str, "onServiceConnected " + componentName);
        try {
            iMapOpenService2 = MapOpenUtil.f6998d;
            if (iMapOpenService2 != null) {
                IMapOpenService unused = MapOpenUtil.f6998d = null;
            }
            IMapOpenService unused2 = MapOpenUtil.f6998d = IMapOpenService.AbstractBinderC2863a.m18563a(iBinder);
            iMapOpenService3 = MapOpenUtil.f6998d;
            iMapOpenService3.mo18562a(new BinderC2857e(this));
        } catch (RemoteException e) {
            str2 = MapOpenUtil.f6997c;
            Log.d(str2, "getComOpenClient ", e);
            iMapOpenService = MapOpenUtil.f6998d;
            if (iMapOpenService != null) {
                IMapOpenService unused3 = MapOpenUtil.f6998d = null;
            }
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        String str;
        IMapOpenService iMapOpenService;
        str = MapOpenUtil.f6997c;
        Log.d(str, "onServiceDisconnected " + componentName);
        iMapOpenService = MapOpenUtil.f6998d;
        if (iMapOpenService != null) {
            IMapOpenService unused = MapOpenUtil.f6998d = null;
            boolean unused2 = MapOpenUtil.f7017w = false;
        }
    }
}
