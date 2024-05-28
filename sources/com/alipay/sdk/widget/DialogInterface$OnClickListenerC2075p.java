package com.alipay.sdk.widget;

import android.content.DialogInterface;
import com.alipay.sdk.app.C1998j;
import com.alipay.sdk.app.statistic.C2000a;
import com.bytedance.applog.tracker.Tracker;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.widget.p */
/* loaded from: E:\10201592_dexfile_execute.dex */
class DialogInterface$OnClickListenerC2075p implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ RunnableC2073n f3978a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterface$OnClickListenerC2075p(RunnableC2073n runnableC2073n) {
        this.f3978a = runnableC2073n;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        Tracker.onClick(dialogInterface, i);
        this.f3978a.f3975a.cancel();
        this.f3978a.f3976b.f3965w = false;
        C2000a.m20899a("net", "SSLDenied", "2");
        C1998j.m20913a(C1998j.m20910c());
        this.f3978a.f3976b.f3940a.finish();
    }
}
