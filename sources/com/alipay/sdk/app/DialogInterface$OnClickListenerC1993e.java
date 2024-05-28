package com.alipay.sdk.app;

import android.content.DialogInterface;
import com.alipay.sdk.app.statistic.C2000a;
import com.bytedance.applog.tracker.Tracker;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.app.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
class DialogInterface$OnClickListenerC1993e implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ RunnableC1991c f3576a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterface$OnClickListenerC1993e(RunnableC1991c runnableC1991c) {
        this.f3576a = runnableC1991c;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        Tracker.onClick(dialogInterface, i);
        this.f3576a.f3573b.cancel();
        this.f3576a.f3574c.f3567b = false;
        C2000a.m20899a("net", "SSLDenied", "1");
        C1998j.m20913a(C1998j.m20910c());
        this.f3576a.f3572a.finish();
    }
}
