package com.baidu.mapapi.utils;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.baidu.mapframework.open.aidl.IComOpenClient;
import com.baidu.mapframework.open.aidl.IOpenClientCallback;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: MapOpenUtil.java */
/* renamed from: com.baidu.mapapi.utils.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
class BinderC2857e extends IOpenClientCallback.AbstractBinderC2865a {

    /* renamed from: a */
    final /* synthetic */ ServiceConnectionC2856d f7022a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BinderC2857e(ServiceConnectionC2856d serviceConnectionC2856d) {
        this.f7022a = serviceConnectionC2856d;
    }

    @Override // com.baidu.mapframework.open.aidl.IOpenClientCallback
    /* renamed from: a */
    public void mo18559a(IBinder iBinder) throws RemoteException {
        String str;
        IComOpenClient iComOpenClient;
        boolean z;
        str = MapOpenUtil.f6997c;
        Log.d(str, "onClientReady");
        iComOpenClient = MapOpenUtil.f6999e;
        if (iComOpenClient != null) {
            IComOpenClient unused = MapOpenUtil.f6999e = null;
        }
        IComOpenClient unused2 = MapOpenUtil.f6999e = IComOpenClient.AbstractBinderC2861a.m18567a(iBinder);
        z = MapOpenUtil.f7016v;
        if (!z) {
            MapOpenUtil.m18614a(MapOpenUtil.f6995a);
        }
        boolean unused3 = MapOpenUtil.f7016v = true;
    }
}
