package p387e0;

import android.view.View;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.normal.order.bean.WPFqInfoBean;
import com.unicom.pay.normal.order.p359ui.WPInstalmentActivity;
import p387e0.C11873n;
import p470p0.C13647j;

@NBSInstrumented
/* renamed from: e0.m */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class View$OnClickListenerC11872m implements View.OnClickListener {

    /* renamed from: a */
    public final /* synthetic */ C11873n.C11874a f24175a;

    /* renamed from: b */
    public final /* synthetic */ WPFqInfoBean f24176b;

    /* renamed from: c */
    public final /* synthetic */ C11873n f24177c;

    public View$OnClickListenerC11872m(C11873n c11873n, C11873n.C11874a c11874a, WPFqInfoBean wPFqInfoBean) {
        this.f24177c = c11873n;
        this.f24175a = c11874a;
        this.f24176b = wPFqInfoBean;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        C11873n c11873n = this.f24177c;
        if (!c11873n.f24182e) {
            NBSActionInstrumentation.onClickEventExit();
            return;
        }
        if (c11873n.f24180c != null) {
            C13647j.m180a(c11873n.f24178a, C10531R.C10533drawable.up_item_loading, this.f24175a.f24195m);
            ((WPInstalmentActivity) this.f24177c.f24180c).m6144a(this.f24176b);
        }
        NBSActionInstrumentation.onClickEventExit();
    }
}
