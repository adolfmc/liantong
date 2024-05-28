package com.baidu.mapapi.utils;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.baidu.mapframework.open.aidl.IComOpenClient;
import com.baidu.mapframework.open.aidl.IOpenClientCallback;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: MapOpenUtil.java */
/* renamed from: com.baidu.mapapi.utils.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class BinderC2855c extends IOpenClientCallback.AbstractBinderC2865a {

    /* renamed from: a */
    final /* synthetic */ int f7021a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BinderC2855c(int i) {
        this.f7021a = i;
    }

    @Override // com.baidu.mapframework.open.aidl.IOpenClientCallback
    /* renamed from: a */
    public void mo18559a(IBinder iBinder) throws RemoteException {
        String str;
        IComOpenClient iComOpenClient;
        str = MapOpenUtil.f6997c;
        Log.d(str, "onClientReady");
        iComOpenClient = MapOpenUtil.f6999e;
        if (iComOpenClient != null) {
            IComOpenClient unused = MapOpenUtil.f6999e = null;
        }
        IComOpenClient unused2 = MapOpenUtil.f6999e = IComOpenClient.AbstractBinderC2861a.m18567a(iBinder);
        MapOpenUtil.m18614a(this.f7021a);
        boolean unused3 = MapOpenUtil.f7016v = true;
    }
}
