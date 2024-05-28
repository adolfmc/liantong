package p387e0;

import android.view.View;
import cn.ltzf.passguard.C1730a;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.normal.order.bean.WPPayInfoBean;
import com.unicom.pay.normal.order.p359ui.WPOrderActivity;
import p091c0.C1529j;
import p387e0.C11886t;
import p470p0.C13647j;

@NBSInstrumented
/* renamed from: e0.s */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class View$OnClickListenerC11885s implements View.OnClickListener {

    /* renamed from: a */
    public final /* synthetic */ C11886t.C11888b f24214a;

    /* renamed from: b */
    public final /* synthetic */ WPPayInfoBean f24215b;

    /* renamed from: c */
    public final /* synthetic */ C11886t f24216c;

    public View$OnClickListenerC11885s(C11886t c11886t, C11886t.C11888b c11888b, WPPayInfoBean wPPayInfoBean) {
        this.f24216c = c11886t;
        this.f24214a = c11888b;
        this.f24215b = wPPayInfoBean;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (!this.f24216c.f24221e) {
            NBSActionInstrumentation.onClickEventExit();
        } else if (this.f24214a.f24227e.isSelected()) {
            NBSActionInstrumentation.onClickEventExit();
        } else if (this.f24214a.getAdapterPosition() == -1) {
            NBSActionInstrumentation.onClickEventExit();
        } else {
            C11886t c11886t = this.f24216c;
            if (c11886t.f24220d != null) {
                C13647j.m180a(c11886t.f24218b, C10531R.C10533drawable.up_item_loading, this.f24214a.f24227e);
                C11886t.InterfaceC11887a interfaceC11887a = this.f24216c.f24220d;
                WPPayInfoBean wPPayInfoBean = this.f24215b;
                C11886t.C11888b c11888b = this.f24214a;
                WPOrderActivity wPOrderActivity = (WPOrderActivity) interfaceC11887a;
                wPOrderActivity.getClass();
                if (WPPayInfoBean.WAPPAY.equals(wPPayInfoBean.getToolCode())) {
                    C11886t c11886t2 = wPOrderActivity.f20300e0;
                    c11886t2.f24219c = c11888b.getAdapterPosition();
                    c11886t2.notifyDataSetChanged();
                } else if (wPOrderActivity.f20315s0 != null) {
                    wPOrderActivity.m6096c(false);
                    ((C1529j) wPOrderActivity.f24311a).m22176a(wPOrderActivity.mo6077x(), wPOrderActivity.mo6076y(), wPPayInfoBean.getToolExpand(), wPOrderActivity.f20315s0.getData().getTradeOrderNo(), 1, c11888b.getAdapterPosition());
                }
                StringBuilder m22016a = C1730a.m22016a("勾选-");
                m22016a.append(wPPayInfoBean.getToolName());
                wPOrderActivity.m1991k(m22016a.toString());
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }
}
