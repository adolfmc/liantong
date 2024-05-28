package p408n;

import android.text.TextUtils;
import android.view.View;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import p395i.C12048b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: n.q */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class View$OnClickListenerC12356q implements View.OnClickListener {

    /* renamed from: a */
    public final /* synthetic */ C12335b f25017a;

    /* renamed from: b */
    public final /* synthetic */ C12048b.InterfaceC12051c f25018b;

    /* renamed from: c */
    public final /* synthetic */ C12353p f25019c;

    public View$OnClickListenerC12356q(C12353p c12353p, C12335b c12335b, C12048b.InterfaceC12051c interfaceC12051c) {
        this.f25019c = c12353p;
        this.f25017a = c12335b;
        this.f25018b = interfaceC12051c;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (!TextUtils.isEmpty(this.f25017a.f24940a.getText().toString())) {
            this.f25019c.f25009a.mo1814b();
        }
        C12048b.InterfaceC12051c interfaceC12051c = this.f25018b;
        if (interfaceC12051c != null) {
            interfaceC12051c.m1968a();
        }
        NBSActionInstrumentation.onClickEventExit();
    }
}
