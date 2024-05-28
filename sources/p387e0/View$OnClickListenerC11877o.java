package p387e0;

import android.text.TextUtils;
import android.view.View;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.normal.order.bean.WPPayInfoBean;
import com.unicom.pay.normal.order.bean.WPToolFqNumInfoBean;
import com.unicom.pay.normal.order.bean.WPUnionOrderInfoBean;
import com.unicom.pay.normal.order.p359ui.WPOrderActivity;
import p091c0.C1529j;
import p387e0.C11878p;
import p387e0.C11889u;

@NBSInstrumented
/* renamed from: e0.o */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class View$OnClickListenerC11877o implements View.OnClickListener {

    /* renamed from: a */
    public final /* synthetic */ WPToolFqNumInfoBean f24197a;

    /* renamed from: b */
    public final /* synthetic */ C11878p f24198b;

    public View$OnClickListenerC11877o(C11878p c11878p, WPToolFqNumInfoBean wPToolFqNumInfoBean, C11878p.C11879a c11879a) {
        this.f24198b = c11878p;
        this.f24197a = wPToolFqNumInfoBean;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        WPUnionOrderInfoBean wPUnionOrderInfoBean;
        int adapterPosition;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (this.f24197a.isChecked() || "0".equals(this.f24197a.getCanUsed())) {
            NBSActionInstrumentation.onClickEventExit();
            return;
        }
        C11878p.InterfaceC11880b interfaceC11880b = this.f24198b.f24201c;
        if (interfaceC11880b != null) {
            WPToolFqNumInfoBean wPToolFqNumInfoBean = this.f24197a;
            C11889u.C11894e c11894e = (C11889u.C11894e) interfaceC11880b;
            if (C11889u.this.f24236h) {
                if (!c11894e.f24256a.f24283j.isSelected() && WPPayInfoBean.EVENT_TYPE_CHECK.equals(c11894e.f24257b.getEventType())) {
                    C11889u.m2053a(C11889u.this, c11894e.f24256a.f24283j);
                }
                C11889u.InterfaceC11900j interfaceC11900j = C11889u.this.f24231c;
                if (interfaceC11900j != null) {
                    C11889u.C11902l c11902l = c11894e.f24256a;
                    WPOrderActivity wPOrderActivity = (WPOrderActivity) interfaceC11900j;
                    if (wPOrderActivity.f20315s0 != null && (wPUnionOrderInfoBean = wPOrderActivity.f20313r0) != null && wPUnionOrderInfoBean.getOrderInfo() != null && (adapterPosition = c11902l.getAdapterPosition()) != -1) {
                        wPOrderActivity.m6096c(false);
                        ((C1529j) wPOrderActivity.f24311a).m22176a(wPOrderActivity.mo6077x(), wPOrderActivity.mo6076y(), wPToolFqNumInfoBean.getFqNumExpand(), wPOrderActivity.f20315s0.getData().getTradeOrderNo(), 4, adapterPosition);
                        wPOrderActivity.m1991k((TextUtils.isEmpty(wPToolFqNumInfoBean.getFqNum()) || "0".equals(wPToolFqNumInfoBean.getFqNum())) ? "点击-不分期" : "点击-期数");
                    }
                }
            }
        }
        NBSActionInstrumentation.onClickEventExit();
    }
}
