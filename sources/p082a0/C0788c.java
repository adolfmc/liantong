package p082a0;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.normal.discount.bean.WPDiscountInfoBean;
import com.unicom.pay.normal.order.bean.WPDiscountQueryBean;
import java.util.ArrayList;
import p387e0.C11849b;
import p393h.AbstractC12007d;
import p393h.AbstractDialogInterface$OnCancelListenerC12000b;
import p470p0.C13659r;

@NBSInstrumented
/* renamed from: a0.c */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class C0788c extends AbstractC12007d implements C11849b.InterfaceC11852c {

    /* renamed from: m */
    public static final /* synthetic */ int f2440m = 0;

    /* renamed from: h */
    public TextView f2441h;

    /* renamed from: i */
    public RecyclerView f2442i;

    /* renamed from: j */
    public C11849b f2443j;

    /* renamed from: k */
    public ArrayList<WPDiscountInfoBean> f2444k = new ArrayList<>();

    /* renamed from: l */
    public WPDiscountQueryBean f2445l;

    @Override // p393h.InterfaceC12016h
    /* renamed from: D */
    public final String mo56D() {
        return "沃支付优惠弹窗";
    }

    @Override // p393h.InterfaceC12016h
    /* renamed from: O */
    public final String mo53O() {
        return "wp101";
    }

    @Override // p393h.AbstractC12007d
    /* renamed from: a */
    public final void mo1989a() {
        dismiss();
    }

    /* renamed from: a */
    public final void m22221a(WPDiscountQueryBean wPDiscountQueryBean) {
        this.f2445l = wPDiscountQueryBean;
        this.f2444k.clear();
        if (wPDiscountQueryBean != null && wPDiscountQueryBean.getDiscountInfos() != null) {
            this.f2444k.addAll(wPDiscountQueryBean.getDiscountInfos());
        }
        WPDiscountQueryBean wPDiscountQueryBean2 = this.f2445l;
        if (wPDiscountQueryBean2 == null || this.f2441h == null) {
            return;
        }
        this.f2441h.setText(wPDiscountQueryBean2.getDiscountMsg());
    }

    @Override // android.support.p083v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(C10531R.C10535layout.wp_discount_list_dialog, viewGroup, false);
        inflate.setLayoutParams(new ViewGroup.LayoutParams(C13659r.m168b(getContext()), C13659r.m169a(getContext()) / 2));
        m1988a(inflate, getString(C10531R.string.wp_select_discount_title));
        this.f2441h = (TextView) inflate.findViewById(C10531R.C10534id.wopay_discount_amount_tv);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(C10531R.C10534id.wopay_discount_rv);
        this.f2442i = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        C11849b c11849b = new C11849b(getActivity(), this.f2444k);
        this.f2443j = c11849b;
        c11849b.f24107d = this;
        this.f2442i.setAdapter(c11849b);
        AbstractDialogInterface$OnCancelListenerC12000b abstractDialogInterface$OnCancelListenerC12000b = (AbstractDialogInterface$OnCancelListenerC12000b) getActivity();
        if (abstractDialogInterface$OnCancelListenerC12000b instanceof C11849b.InterfaceC11851b) {
            this.f2443j.f24106c = (C11849b.InterfaceC11851b) abstractDialogInterface$OnCancelListenerC12000b;
        }
        WPDiscountQueryBean wPDiscountQueryBean = this.f2445l;
        if (wPDiscountQueryBean != null) {
            this.f2441h.setText(wPDiscountQueryBean.getDiscountMsg());
        }
        return inflate;
    }

    @Override // p393h.InterfaceC12016h
    /* renamed from: p */
    public final String mo36p() {
        return "98U01170wp101";
    }
}
