package p387e0;

import android.view.View;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.normal.order.bean.WPFqInfoBean;
import com.unicom.pay.normal.order.p359ui.WPInstalmentActivity;
import p385d0.C11817e;
import p387e0.C11866j;

@NBSInstrumented
/* renamed from: e0.h */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class View$OnClickListenerC11864h implements View.OnClickListener {

    /* renamed from: a */
    public final /* synthetic */ WPFqInfoBean f24149a;

    /* renamed from: b */
    public final /* synthetic */ C11866j.C11867a f24150b;

    /* renamed from: c */
    public final /* synthetic */ C11866j f24151c;

    public View$OnClickListenerC11864h(C11866j c11866j, WPFqInfoBean wPFqInfoBean, C11866j.C11867a c11867a) {
        this.f24151c = c11866j;
        this.f24149a = wPFqInfoBean;
        this.f24150b = c11867a;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (!this.f24149a.isShowNext() && this.f24150b.f24166f.isSelected()) {
            NBSActionInstrumentation.onClickEventExit();
            return;
        }
        C11866j.InterfaceC11868b interfaceC11868b = this.f24151c.f24157c;
        if (interfaceC11868b != null) {
            ((WPInstalmentActivity) interfaceC11868b).m6143a(this.f24149a, this.f24150b);
        }
        C11866j.InterfaceC11869c interfaceC11869c = this.f24151c.f24158d;
        if (interfaceC11869c != null) {
            ((C11817e) interfaceC11869c).m2080a(this.f24149a);
        }
        NBSActionInstrumentation.onClickEventExit();
    }
}
