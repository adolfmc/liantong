package p387e0;

import android.text.TextUtils;
import android.view.View;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.normal.discount.bean.WPDiscountInfoBean;
import p082a0.C0788c;
import p387e0.C11849b;
import p470p0.C13647j;

@NBSInstrumented
/* renamed from: e0.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class View$OnClickListenerC11848a implements View.OnClickListener {

    /* renamed from: a */
    public final /* synthetic */ WPDiscountInfoBean f24101a;

    /* renamed from: b */
    public final /* synthetic */ C11849b.C11850a f24102b;

    /* renamed from: c */
    public final /* synthetic */ C11849b f24103c;

    public View$OnClickListenerC11848a(C11849b c11849b, WPDiscountInfoBean wPDiscountInfoBean, C11849b.C11850a c11850a) {
        this.f24103c = c11849b;
        this.f24101a = wPDiscountInfoBean;
        this.f24102b = c11850a;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        String str;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if ("1".equals(this.f24101a.getEventIconType())) {
            C13647j.m180a(this.f24103c.f24104a, C10531R.C10533drawable.up_item_loading, this.f24102b.f24112e);
        }
        C11849b.InterfaceC11851b interfaceC11851b = this.f24103c.f24106c;
        if (interfaceC11851b != null) {
            interfaceC11851b.mo2067a(this.f24101a, !this.f24102b.f24112e.isSelected());
        }
        C11849b.InterfaceC11852c interfaceC11852c = this.f24103c.f24107d;
        if (interfaceC11852c != null) {
            WPDiscountInfoBean wPDiscountInfoBean = this.f24101a;
            this.f24102b.f24112e.isSelected();
            C0788c c0788c = (C0788c) interfaceC11852c;
            if ("1".equals(wPDiscountInfoBean.getEventIconType())) {
                "DZQ".equals(wPDiscountInfoBean.getDiscountType());
            } else {
                if (WPDiscountInfoBean.TYYX.equals(wPDiscountInfoBean.getDiscountType())) {
                    str = (TextUtils.isEmpty(wPDiscountInfoBean.getDiscountDesc()) || !wPDiscountInfoBean.getDiscountDesc().contains(WPDiscountInfoBean.TRENDS_EVENT_CHOOSE)) ? "通用优惠-已选择" : "通用优惠-去选择";
                } else if (WPDiscountInfoBean.PAYTOOLSYX.equals(wPDiscountInfoBean.getDiscountType())) {
                    str = (TextUtils.isEmpty(wPDiscountInfoBean.getDiscountDesc()) || !wPDiscountInfoBean.getDiscountDesc().contains(WPDiscountInfoBean.TRENDS_EVENT_CHOOSE)) ? "支付工具优惠-已选择" : "支付工具优惠-未选择";
                }
                c0788c.m1987a(str);
            }
        }
        NBSActionInstrumentation.onClickEventExit();
    }
}
