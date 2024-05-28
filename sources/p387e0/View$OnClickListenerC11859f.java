package p387e0;

import android.view.View;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.normal.discount.bean.WPDiscountDetailBean;
import com.unicom.pay.normal.discount.bean.WPDiscountInfoBean;
import p082a0.C0784b;
import p387e0.C11860g;
import p470p0.C13647j;

@NBSInstrumented
/* renamed from: e0.f */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class View$OnClickListenerC11859f implements View.OnClickListener {

    /* renamed from: a */
    public final /* synthetic */ C11860g.View$OnClickListenerC11861a f24132a;

    /* renamed from: b */
    public final /* synthetic */ WPDiscountDetailBean f24133b;

    /* renamed from: c */
    public final /* synthetic */ C11860g f24134c;

    public View$OnClickListenerC11859f(C11860g c11860g, C11860g.View$OnClickListenerC11861a view$OnClickListenerC11861a, WPDiscountDetailBean wPDiscountDetailBean) {
        this.f24134c = c11860g;
        this.f24132a = view$OnClickListenerC11861a;
        this.f24133b = wPDiscountDetailBean;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        String str;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        C11860g c11860g = this.f24134c;
        if (!c11860g.f24139e) {
            NBSActionInstrumentation.onClickEventExit();
            return;
        }
        C13647j.m180a(c11860g.f24138d, C10531R.C10533drawable.up_item_loading, this.f24132a.f24142b);
        C11860g.InterfaceC11862b interfaceC11862b = this.f24134c.f24136b;
        if (interfaceC11862b != null) {
            interfaceC11862b.mo2063a(this.f24133b, !this.f24132a.f24142b.isSelected(), this.f24134c.f24140f);
        }
        C11860g.InterfaceC11863c interfaceC11863c = this.f24134c.f24137c;
        if (interfaceC11863c != null) {
            boolean z = !this.f24132a.f24142b.isSelected();
            C0784b c0784b = (C0784b) interfaceC11863c;
            if (WPDiscountInfoBean.TYYX.equals(c0784b.f2435k)) {
                str = z ? "勾选-二级页-沃支付优惠" : "取消勾选-二级页-沃支付优惠";
            } else if (WPDiscountInfoBean.PAYTOOLSYX.equals(c0784b.f2435k)) {
                str = z ? "勾选-二级页-支付方式优惠" : "取消勾选-二级页-支付方式优惠";
            }
            c0784b.m1987a(str);
        }
        NBSActionInstrumentation.onClickEventExit();
    }
}
