package com.alipay.sdk.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import com.alipay.android.app.IRemoteServiceCallback;
import com.alipay.sdk.util.C2042e;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.util.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BinderC2046h extends IRemoteServiceCallback.Stub {

    /* renamed from: a */
    final /* synthetic */ C2042e f3886a;

    @Override // com.alipay.android.app.IRemoteServiceCallback
    public boolean isHideLoadingScreen() throws RemoteException {
        return false;
    }

    @Override // com.alipay.android.app.IRemoteServiceCallback
    public void payEnd(boolean z, String str) throws RemoteException {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BinderC2046h(C2042e c2042e) {
        this.f3886a = c2042e;
    }

    @Override // com.alipay.android.app.IRemoteServiceCallback
    public void startActivity(String str, String str2, int i, Bundle bundle) throws RemoteException {
        Activity activity;
        C2042e.InterfaceC2043a interfaceC2043a;
        Activity activity2;
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        if (bundle == null) {
            bundle = new Bundle();
        }
        try {
            bundle.putInt("CallingPid", i);
            intent.putExtras(bundle);
        } catch (Exception unused) {
        }
        intent.setClassName(str, str2);
        activity = this.f3886a.f3875c;
        if (activity != null) {
            activity2 = this.f3886a.f3875c;
            activity2.startActivity(intent);
        }
        interfaceC2043a = this.f3886a.f3879g;
        interfaceC2043a.mo20693b();
    }
}
