package com.alipay.sdk.widget;

import android.content.DialogInterface;
import com.alipay.sdk.app.statistic.C2000a;
import com.bytedance.applog.tracker.Tracker;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.widget.o */
/* loaded from: E:\10201592_dexfile_execute.dex */
class DialogInterface$OnClickListenerC2074o implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ RunnableC2073n f3977a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterface$OnClickListenerC2074o(RunnableC2073n runnableC2073n) {
        this.f3977a = runnableC2073n;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        Tracker.onClick(dialogInterface, i);
        this.f3977a.f3976b.f3965w = true;
        C2000a.m20899a("net", "SSLProceed", "2");
        this.f3977a.f3975a.proceed();
        dialogInterface.dismiss();
    }
}
