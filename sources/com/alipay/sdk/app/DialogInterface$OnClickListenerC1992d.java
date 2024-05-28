package com.alipay.sdk.app;

import android.content.DialogInterface;
import com.alipay.sdk.app.statistic.C2000a;
import com.bytedance.applog.tracker.Tracker;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.app.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
class DialogInterface$OnClickListenerC1992d implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ RunnableC1991c f3575a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterface$OnClickListenerC1992d(RunnableC1991c runnableC1991c) {
        this.f3575a = runnableC1991c;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        Tracker.onClick(dialogInterface, i);
        this.f3575a.f3574c.f3567b = true;
        C2000a.m20899a("net", "SSLProceed", "1");
        this.f3575a.f3573b.proceed();
        dialogInterface.dismiss();
    }
}
