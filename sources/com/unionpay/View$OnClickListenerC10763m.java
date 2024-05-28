package com.unionpay;

import android.view.View;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.unionpay.m */
/* loaded from: E:\11617560_dexfile_execute.dex */
final class View$OnClickListenerC10763m implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ UPPayWapActivity f20692a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC10763m(UPPayWapActivity uPPayWapActivity) {
        this.f20692a = uPPayWapActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        UPPayWapActivity.m5978a(this.f20692a);
        NBSActionInstrumentation.onClickEventExit();
    }
}
