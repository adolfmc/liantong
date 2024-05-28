package p408n;

import android.view.View;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import p395i.C12048b;
import p399k.AbstractC12265h;

@NBSInstrumented
/* renamed from: n.m */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class View$OnClickListenerC12350m implements View.OnClickListener {

    /* renamed from: a */
    public final /* synthetic */ AbstractC12265h f25002a;

    /* renamed from: b */
    public final /* synthetic */ C12048b.InterfaceC12050b f25003b;

    /* renamed from: c */
    public final /* synthetic */ C12351n f25004c;

    public View$OnClickListenerC12350m(C12351n c12351n, AbstractC12265h abstractC12265h, C12048b.InterfaceC12050b interfaceC12050b) {
        this.f25004c = c12351n;
        this.f25002a = abstractC12265h;
        this.f25003b = interfaceC12050b;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        boolean z;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        C12351n c12351n = this.f25004c;
        c12351n.getClass();
        if (System.currentTimeMillis() - c12351n.f25008d < 800) {
            z = true;
        } else {
            c12351n.f25008d = System.currentTimeMillis();
            z = false;
        }
        if (z) {
            NBSActionInstrumentation.onClickEventExit();
            return;
        }
        this.f25002a.mo1814b();
        C12048b.InterfaceC12050b interfaceC12050b = this.f25003b;
        if (interfaceC12050b != null) {
            interfaceC12050b.mo1801a();
        }
        NBSActionInstrumentation.onClickEventExit();
    }
}
