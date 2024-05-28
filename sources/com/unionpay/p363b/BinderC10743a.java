package com.unionpay.p363b;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.unionpay.tsmservice.ITsmCallback;
import com.unionpay.tsmservice.result.VendorPayStatusResult;
import com.unionpay.utils.C10923j;

/* renamed from: com.unionpay.b.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class BinderC10743a extends ITsmCallback.Stub {

    /* renamed from: a */
    private int f20649a = 4000;

    /* renamed from: b */
    private Handler f20650b;

    public BinderC10743a(Handler handler) {
        this.f20650b = handler;
    }

    @Override // com.unionpay.tsmservice.ITsmCallback
    public final void onError(String str, String str2) {
        C10923j.m5828c("uppay", "errorCode:" + str + ", errorDesc:" + str2);
        Handler handler = this.f20650b;
        int i = this.f20649a;
        handler.sendMessage(Message.obtain(handler, 1, i, 0, str + str2));
    }

    @Override // com.unionpay.tsmservice.ITsmCallback
    public final void onResult(Bundle bundle) {
        if (this.f20649a != 4000) {
            return;
        }
        C10923j.m5828c("uppay-spay", "query vendor pay status callback");
        bundle.setClassLoader(VendorPayStatusResult.class.getClassLoader());
        Bundle vendorPayStatusResult = ((VendorPayStatusResult) bundle.get("result")).getVendorPayStatusResult();
        Handler handler = this.f20650b;
        handler.sendMessage(Message.obtain(handler, 4000, vendorPayStatusResult));
    }
}
