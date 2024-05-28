package com.unionpay.p363b;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.huawei.nfc.sdk.service.HwOpenPayTask;
import com.unionpay.utils.C10923j;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.unionpay.b.d */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C10746d implements HwOpenPayTask.IHwResultCallBack {

    /* renamed from: a */
    final /* synthetic */ C10744b f20663a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C10746d(C10744b c10744b) {
        this.f20663a = c10744b;
    }

    @Override // com.huawei.nfc.sdk.service.HwOpenPayTask.IHwResultCallBack
    public final void onResult(int i, Bundle bundle) {
        Handler handler;
        Handler handler2;
        Handler handler3;
        C10923j.m5828c("uppay", "supportCapacity result:" + i);
        handler = this.f20663a.f20660j;
        if (handler != null) {
            handler2 = this.f20663a.f20660j;
            Message obtainMessage = handler2.obtainMessage(4004);
            obtainMessage.obj = Integer.valueOf(i);
            handler3 = this.f20663a.f20660j;
            handler3.sendMessage(obtainMessage);
        }
    }
}
