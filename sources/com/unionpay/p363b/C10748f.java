package com.unionpay.p363b;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.huawei.nfc.sdk.service.HwOpenPayTask;
import com.unionpay.utils.C10923j;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.unionpay.b.f */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C10748f implements HwOpenPayTask.IHwPayResultCallBack {

    /* renamed from: a */
    final /* synthetic */ C10744b f20665a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C10748f(C10744b c10744b) {
        this.f20665a = c10744b;
    }

    @Override // com.huawei.nfc.sdk.service.HwOpenPayTask.IHwPayResultCallBack
    public final void onError(String str, String str2) {
        Handler handler;
        Handler handler2;
        Handler handler3;
        C10923j.m5828c("uppay", "queryHwPayStatus onError, errorCode:" + str + " errorMsg:" + str2);
        handler = this.f20665a.f20660j;
        if (handler != null) {
            handler2 = this.f20665a.f20660j;
            Message obtainMessage = handler2.obtainMessage(4002);
            Bundle bundle = new Bundle();
            bundle.putString("errorCode", str);
            bundle.putString("errorDesc", str2);
            obtainMessage.obj = bundle;
            handler3 = this.f20665a.f20660j;
            handler3.sendMessage(obtainMessage);
        }
    }

    @Override // com.huawei.nfc.sdk.service.HwOpenPayTask.IHwPayResultCallBack
    public final void onResult(Bundle bundle) {
        Handler handler;
        Handler handler2;
        Handler handler3;
        C10923j.m5828c("uppay", "queryHwPayStatus onResult");
        handler = this.f20665a.f20660j;
        if (handler != null) {
            handler2 = this.f20665a.f20660j;
            Message obtainMessage = handler2.obtainMessage(4001);
            obtainMessage.obj = bundle;
            handler3 = this.f20665a.f20660j;
            handler3.sendMessage(obtainMessage);
        }
    }
}
