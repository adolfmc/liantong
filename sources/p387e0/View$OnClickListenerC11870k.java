package p387e0;

import android.view.View;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.normal.order.bean.WPFqInfoBean;
import com.unicom.pay.normal.order.p359ui.WPInstalmentActivity;
import p091c0.C1506b;
import p387e0.C11873n;
import p470p0.C13647j;

@NBSInstrumented
/* renamed from: e0.k */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class View$OnClickListenerC11870k implements View.OnClickListener {

    /* renamed from: a */
    public final /* synthetic */ WPFqInfoBean f24169a;

    /* renamed from: b */
    public final /* synthetic */ C11873n.C11874a f24170b;

    /* renamed from: c */
    public final /* synthetic */ C11873n f24171c;

    public View$OnClickListenerC11870k(C11873n c11873n, WPFqInfoBean wPFqInfoBean, C11873n.C11874a c11874a) {
        this.f24171c = c11873n;
        this.f24169a = wPFqInfoBean;
        this.f24170b = c11874a;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (!this.f24171c.f24182e) {
            NBSActionInstrumentation.onClickEventExit();
        } else if (!this.f24169a.isShowNext() && this.f24170b.f24189g.isSelected()) {
            NBSActionInstrumentation.onClickEventExit();
        } else {
            C11873n c11873n = this.f24171c;
            if (c11873n.f24180c != null) {
                C13647j.m180a(c11873n.f24178a, C10531R.C10533drawable.up_item_loading, this.f24170b.f24189g);
                C11873n.InterfaceC11875b interfaceC11875b = this.f24171c.f24180c;
                WPFqInfoBean wPFqInfoBean = this.f24169a;
                C11873n.C11874a c11874a = this.f24170b;
                WPInstalmentActivity wPInstalmentActivity = (WPInstalmentActivity) interfaceC11875b;
                if (wPInstalmentActivity.f20243N != null) {
                    ((C1506b) wPInstalmentActivity.f24311a).m22182a(wPFqInfoBean.getExpand(), wPInstalmentActivity.f20243N.getData().getTradeOrderNo(), c11874a.getAdapterPosition(), 0);
                }
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }
}
