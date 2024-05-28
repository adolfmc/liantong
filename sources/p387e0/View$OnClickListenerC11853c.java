package p387e0;

import android.view.View;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.normal.discount.bean.WPDiscountDetailBean;
import p387e0.C11854d;
import p470p0.C13647j;

@NBSInstrumented
/* renamed from: e0.c */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class View$OnClickListenerC11853c implements View.OnClickListener {

    /* renamed from: a */
    public final /* synthetic */ C11854d.C11855a f24113a;

    /* renamed from: b */
    public final /* synthetic */ WPDiscountDetailBean f24114b;

    /* renamed from: c */
    public final /* synthetic */ C11854d f24115c;

    public View$OnClickListenerC11853c(C11854d c11854d, C11854d.C11855a c11855a, WPDiscountDetailBean wPDiscountDetailBean) {
        this.f24115c = c11854d;
        this.f24113a = c11855a;
        this.f24114b = wPDiscountDetailBean;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        C11854d c11854d = this.f24115c;
        if (!c11854d.f24121f) {
            NBSActionInstrumentation.onClickEventExit();
            return;
        }
        C13647j.m180a(c11854d.f24120e, C10531R.C10533drawable.up_item_loading, this.f24113a.f24129h);
        C11854d.InterfaceC11856b interfaceC11856b = this.f24115c.f24118c;
        if (interfaceC11856b != null) {
            interfaceC11856b.mo2065a(this.f24114b, !this.f24113a.f24129h.isSelected());
        }
        C11854d.InterfaceC11857c interfaceC11857c = this.f24115c.f24119d;
        if (interfaceC11857c != null) {
            this.f24113a.f24129h.isSelected();
            interfaceC11857c.mo2064a();
        }
        NBSActionInstrumentation.onClickEventExit();
    }
}
