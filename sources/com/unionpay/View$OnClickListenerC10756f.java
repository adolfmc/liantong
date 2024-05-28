package com.unionpay;

import android.view.View;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.unionpay.f */
/* loaded from: E:\11617560_dexfile_execute.dex */
final class View$OnClickListenerC10756f implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ UPPayWapActivity f20685a;

    View$OnClickListenerC10756f(UPPayWapActivity uPPayWapActivity) {
        this.f20685a = uPPayWapActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        this.f20685a.finish();
        NBSActionInstrumentation.onClickEventExit();
    }
}
