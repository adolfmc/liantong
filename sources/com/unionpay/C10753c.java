package com.unionpay;

import android.os.Bundle;
import android.os.Handler;
import com.huawei.nfc.sdk.service.HwOpenPayTask;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.unionpay.c */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C10753c implements HwOpenPayTask.IHwResultCallBack {
    @Override // com.huawei.nfc.sdk.service.HwOpenPayTask.IHwResultCallBack
    public final void onResult(int i, Bundle bundle) {
        Handler handler;
        Handler handler2;
        Handler handler3;
        Handler.Callback callback;
        handler = UPPayAssistEx.f20593W;
        if (handler == null) {
            callback = UPPayAssistEx.f20600ac;
            Handler unused = UPPayAssistEx.f20593W = new Handler(callback);
        }
        handler2 = UPPayAssistEx.f20593W;
        handler3 = UPPayAssistEx.f20593W;
        handler2.sendMessage(handler3.obtainMessage(1003, Integer.valueOf(i)));
    }
}
