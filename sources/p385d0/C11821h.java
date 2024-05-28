package p385d0;

import com.unicom.pay.normal.order.bean.WPUnionOrderInfoBean;
import com.unicom.pay.normal.order.p359ui.WPOrderActivity;
import java.util.HashMap;
import p089b0.InterfaceC1470d;
import p091c0.C1525h;
import p091c0.C1529j;
import p481v.C14232a;
import p482w.C14255c;
import p482w.C14262f;

/* renamed from: d0.h */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C11821h implements C14232a.InterfaceC14235c {

    /* renamed from: a */
    public final /* synthetic */ WPOrderActivity f24081a;

    public C11821h(WPOrderActivity wPOrderActivity) {
        this.f24081a = wPOrderActivity;
    }

    @Override // p481v.C14232a.InterfaceC14235c
    /* renamed from: a */
    public final void mo59a() {
        WPUnionOrderInfoBean wPUnionOrderInfoBean = this.f24081a.f20313r0;
        if (wPUnionOrderInfoBean == null || wPUnionOrderInfoBean.getOrderInfo() == null) {
            return;
        }
        WPOrderActivity wPOrderActivity = this.f24081a;
        C1529j c1529j = (C1529j) wPOrderActivity.f24311a;
        String merNo = wPOrderActivity.f20313r0.getOrderInfo().getMerNo();
        ((InterfaceC1470d) c1529j.m1798a()).mo2092c();
        c1529j.m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27818h, new HashMap(), false, new C1525h(c1529j, c1529j, merNo)));
    }

    @Override // p481v.C14232a.InterfaceC14235c
    /* renamed from: b */
    public final void mo58b() {
        this.f24081a.m2083m0();
    }
}
