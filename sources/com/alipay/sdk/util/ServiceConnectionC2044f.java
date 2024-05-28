package com.alipay.sdk.util;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.alipay.android.app.IAlixPay;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.util.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ServiceConnectionC2044f implements ServiceConnection {

    /* renamed from: a */
    final /* synthetic */ C2042e f3883a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServiceConnectionC2044f(C2042e c2042e) {
        this.f3883a = c2042e;
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.f3883a.f3876d = null;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Object obj;
        Object obj2;
        obj = this.f3883a.f3877e;
        synchronized (obj) {
            this.f3883a.f3876d = IAlixPay.Stub.asInterface(iBinder);
            obj2 = this.f3883a.f3877e;
            obj2.notify();
        }
    }
}
