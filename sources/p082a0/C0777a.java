package p082a0;

import android.app.Dialog;
import android.os.Bundle;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.normal.discount.bean.WPDiscountDetailBean;
import com.unicom.pay.normal.order.bean.WPDiscountQueryBean;
import com.unicom.pay.normal.order.bean.WPDzqInfosBean;
import com.unicom.pay.normal.order.p359ui.WPOrderActivity;
import java.util.ArrayList;
import java.util.Iterator;
import p387e0.C11854d;
import p393h.AbstractDialogInterface$OnCancelListenerC12000b;
import p393h.AbstractView$OnClickListenerC12011e;
import p411o.AbstractC12375a;
import p470p0.C13662t;

/* renamed from: a0.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class C0777a extends AbstractView$OnClickListenerC12011e {

    /* renamed from: x */
    public static final /* synthetic */ int f2409x = 0;

    /* renamed from: g */
    public RecyclerView f2410g;

    /* renamed from: h */
    public RecyclerView f2411h;

    /* renamed from: i */
    public C11854d f2412i;

    /* renamed from: j */
    public C11854d f2413j;

    /* renamed from: k */
    public WPDiscountQueryBean f2414k;

    /* renamed from: l */
    public String f2415l;

    /* renamed from: m */
    public boolean f2416m = true;

    /* renamed from: n */
    public TextView f2417n;

    /* renamed from: o */
    public TextView f2418o;

    /* renamed from: p */
    public TextView f2419p;

    /* renamed from: q */
    public TextView f2420q;

    /* renamed from: r */
    public LinearLayout f2421r;

    /* renamed from: s */
    public RelativeLayout f2422s;

    /* renamed from: t */
    public TextView f2423t;

    /* renamed from: u */
    public TextView f2424u;

    /* renamed from: v */
    public int f2425v;

    /* renamed from: w */
    public InterfaceC0783f f2426w;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* renamed from: a0.a$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class View$OnClickListenerC0778a implements View.OnClickListener {
        public View$OnClickListenerC0778a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            C0777a.this.dismissAllowingStateLoss();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* renamed from: a0.a$b */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C0779b implements C11854d.InterfaceC11857c {
        public C0779b() {
        }

        @Override // p387e0.C11854d.InterfaceC11857c
        /* renamed from: a */
        public final void mo2064a() {
            C0777a.this.m1984f("点击-电子券弹窗-满减券");
        }
    }

    /* renamed from: a0.a$c */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class RunnableC0780c implements Runnable {
        public RunnableC0780c() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            int itemCount;
            C0777a c0777a = C0777a.this;
            c0777a.getClass();
            try {
                C11854d c11854d = c0777a.f2412i;
                if (c11854d != null && c0777a.f2410g != null && (itemCount = c11854d.getItemCount()) != c0777a.f2425v) {
                    c0777a.f2425v = itemCount;
                    int i = 0;
                    if (itemCount > 0) {
                        C11854d c11854d2 = c0777a.f2412i;
                        C11854d.C11855a createViewHolder = c11854d2.createViewHolder(c0777a.f2410g, c11854d2.f24117b);
                        c0777a.f2412i.onBindViewHolder(createViewHolder, 0);
                        createViewHolder.itemView.measure(View.MeasureSpec.makeMeasureSpec(c0777a.f2410g.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
                        View view = createViewHolder.itemView;
                        view.layout(0, 0, view.getMeasuredWidth(), createViewHolder.itemView.getMeasuredHeight());
                        createViewHolder.itemView.setDrawingCacheEnabled(true);
                        createViewHolder.itemView.buildDrawingCache();
                        try {
                            i = ((RecyclerView.LayoutParams) createViewHolder.itemView.getLayoutParams()).topMargin + createViewHolder.itemView.getMeasuredHeight();
                        } catch (Exception e) {
                            e.printStackTrace();
                            i = createViewHolder.itemView.getMeasuredHeight();
                        }
                    }
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) c0777a.f2410g.getLayoutParams();
                    layoutParams.height = itemCount < 3 ? i * itemCount : (int) (i * 2.5d);
                    c0777a.f2410g.setLayoutParams(layoutParams);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* renamed from: a0.a$d */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C0781d implements C11854d.InterfaceC11857c {
        public C0781d() {
        }

        @Override // p387e0.C11854d.InterfaceC11857c
        /* renamed from: a */
        public final void mo2064a() {
            C0777a.this.m1984f("点击-电子券弹窗-直减券");
        }
    }

    @NBSInstrumented
    /* renamed from: a0.a$e */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class View$OnClickListenerC0782e implements View.OnClickListener {
        public View$OnClickListenerC0782e() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            C0777a c0777a;
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            try {
                c0777a = C0777a.this;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!c0777a.f2416m) {
                NBSActionInstrumentation.onClickEventExit();
                return;
            }
            c0777a.m22224c(false);
            C0777a c0777a2 = C0777a.this;
            InterfaceC0783f interfaceC0783f = c0777a2.f2426w;
            if (interfaceC0783f != null) {
                ((WPOrderActivity) interfaceC0783f).m6088m(c0777a2.f2414k.getDiscountInfos().get(0).getDzqDiscountExpand());
                C0777a c0777a3 = C0777a.this;
                c0777a3.m1984f("点击-电子券弹窗-" + C0777a.this.f2414k.getDiscountInfos().get(0).getDiscountButtonInfo());
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: a0.a$f */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC0783f {
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, p393h.InterfaceC12016h
    /* renamed from: D */
    public final String mo56D() {
        return "电子券弹窗";
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, p393h.InterfaceC12016h
    /* renamed from: O */
    public final String mo53O() {
        return "wp102";
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: Z */
    public final int mo52Z() {
        return 0;
    }

    /* renamed from: a */
    public final void m22226a(WPDzqInfosBean wPDzqInfosBean) {
        try {
            if (TextUtils.isEmpty(wPDzqInfosBean.getDzqName())) {
                this.f2417n.setVisibility(8);
            } else {
                this.f2417n.setText(wPDzqInfosBean.getDzqName());
                this.f2417n.setVisibility(0);
            }
            if (TextUtils.isEmpty(wPDzqInfosBean.getDzqTail())) {
                this.f2418o.setVisibility(8);
            } else {
                this.f2418o.setText(wPDzqInfosBean.getDzqTail());
                this.f2418o.setVisibility(0);
            }
            C11854d c11854d = this.f2412i;
            if (c11854d == null) {
                C11854d c11854d2 = new C11854d(getActivity(), wPDzqInfosBean.getDzqDetails(), Integer.valueOf(wPDzqInfosBean.getDzqType()).intValue());
                this.f2412i = c11854d2;
                c11854d2.f24119d = new C0779b();
                this.f2410g.setLayoutManager(new LinearLayoutManager(getContext()));
                this.f2410g.setAdapter(this.f2412i);
                this.f2410g.setHasFixedSize(true);
                this.f2410g.setNestedScrollingEnabled(false);
                AbstractDialogInterface$OnCancelListenerC12000b abstractDialogInterface$OnCancelListenerC12000b = (AbstractDialogInterface$OnCancelListenerC12000b) getActivity();
                if (abstractDialogInterface$OnCancelListenerC12000b instanceof C11854d.InterfaceC11856b) {
                    this.f2412i.f24118c = (C11854d.InterfaceC11856b) abstractDialogInterface$OnCancelListenerC12000b;
                }
            } else {
                ArrayList<WPDiscountDetailBean> dzqDetails = wPDzqInfosBean.getDzqDetails();
                c11854d.f24116a.clear();
                c11854d.f24116a.addAll(dzqDetails);
                c11854d.notifyDataSetChanged();
            }
            this.f2421r.setVisibility(0);
            this.f2410g.setVisibility(0);
            if (this.f2414k.getDiscountInfos().get(0).getDzqPage().getDzqInfos().size() > 1) {
                this.f2410g.postDelayed(new RunnableC0780c(), 80L);
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.f2421r.setVisibility(8);
            this.f2410g.setVisibility(8);
        }
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: a0 */
    public final int mo4a0() {
        return C10531R.C10535layout.wp_common_dzq_list_dialog;
    }

    /* renamed from: b */
    public final void m22225b(WPDzqInfosBean wPDzqInfosBean) {
        try {
            if (TextUtils.isEmpty(wPDzqInfosBean.getDzqName())) {
                this.f2419p.setVisibility(8);
            } else {
                this.f2419p.setText(wPDzqInfosBean.getDzqName());
                this.f2419p.setVisibility(0);
            }
            if (TextUtils.isEmpty(wPDzqInfosBean.getDzqTail())) {
                this.f2420q.setVisibility(8);
            } else {
                this.f2420q.setText(wPDzqInfosBean.getDzqTail());
                this.f2420q.setVisibility(0);
            }
            C11854d c11854d = this.f2413j;
            if (c11854d == null) {
                C11854d c11854d2 = new C11854d(getActivity(), wPDzqInfosBean.getDzqDetails(), Integer.parseInt(wPDzqInfosBean.getDzqType()));
                this.f2413j = c11854d2;
                c11854d2.f24119d = new C0781d();
                this.f2411h.setLayoutManager(new LinearLayoutManager(getContext()));
                this.f2411h.setAdapter(this.f2413j);
                this.f2411h.setHasFixedSize(true);
                this.f2411h.setNestedScrollingEnabled(false);
                AbstractDialogInterface$OnCancelListenerC12000b abstractDialogInterface$OnCancelListenerC12000b = (AbstractDialogInterface$OnCancelListenerC12000b) getActivity();
                if (abstractDialogInterface$OnCancelListenerC12000b instanceof C11854d.InterfaceC11856b) {
                    this.f2413j.f24118c = (C11854d.InterfaceC11856b) abstractDialogInterface$OnCancelListenerC12000b;
                }
            } else {
                ArrayList<WPDiscountDetailBean> dzqDetails = wPDzqInfosBean.getDzqDetails();
                c11854d.f24116a.clear();
                c11854d.f24116a.addAll(dzqDetails);
                c11854d.notifyDataSetChanged();
            }
            this.f2423t.setText(this.f2414k.getDiscountInfos().get(0).getDiscountButtonInfo());
            this.f2423t.setVisibility(0);
            this.f2423t.setOnClickListener(new View$OnClickListenerC0782e());
            this.f2422s.setVisibility(0);
            this.f2411h.setVisibility(0);
        } catch (Exception e) {
            e.printStackTrace();
            this.f2422s.setVisibility(8);
            this.f2411h.setVisibility(8);
        }
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: b0 */
    public final int mo44b0() {
        return C10531R.C10537style.WPAdjustNothingDialog;
    }

    /* renamed from: c */
    public final void m22224c(boolean z) {
        this.f2416m = z;
        C11854d c11854d = this.f2412i;
        if (c11854d != null) {
            c11854d.f24121f = z;
        }
        C11854d c11854d2 = this.f2413j;
        if (c11854d2 != null) {
            c11854d2.f24121f = z;
        }
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: c0 */
    public final float mo3c0() {
        return 0.8f;
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: d0 */
    public final void mo2d0() {
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: m */
    public final AbstractC12375a mo0m() {
        return null;
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, p393h.InterfaceC12016h
    /* renamed from: p */
    public final String mo36p() {
        return "98U01170wp102";
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: a */
    public final void mo5a(Dialog dialog) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f2414k = (WPDiscountQueryBean) arguments.getParcelable("discountDetail");
            this.f2415l = arguments.getString("title");
        }
        ((TextView) dialog.findViewById(C10531R.C10534id.wopay_half_title_tv)).setText(TextUtils.isEmpty(this.f2415l) ? getString(C10531R.string.wp_select_discount_detail_title1) : this.f2415l);
        ((ImageView) dialog.findViewById(C10531R.C10534id.wopay_half_close_iv)).setOnClickListener(new View$OnClickListenerC0778a());
        this.f2421r = (LinearLayout) dialog.findViewById(C10531R.C10534id.wopay_dzq_title1_ll);
        this.f2422s = (RelativeLayout) dialog.findViewById(C10531R.C10534id.wopay_dzq_title2_ll);
        this.f2417n = (TextView) dialog.findViewById(C10531R.C10534id.wopay_dzq_title1_tv);
        this.f2418o = (TextView) dialog.findViewById(C10531R.C10534id.wopay_dzq_desc1_tv);
        this.f2419p = (TextView) dialog.findViewById(C10531R.C10534id.wopay_dzq_title2_tv);
        this.f2420q = (TextView) dialog.findViewById(C10531R.C10534id.wopay_dzq_desc2_tv);
        this.f2424u = (TextView) dialog.findViewById(C10531R.C10534id.wopay_dzq_amount_title_tv);
        this.f2423t = (TextView) dialog.findViewById(C10531R.C10534id.wopay_dzq_all_tv);
        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(C10531R.C10534id.wopay_discount_dzq_item_rv);
        this.f2410g = recyclerView;
        recyclerView.setOverScrollMode(2);
        RecyclerView recyclerView2 = (RecyclerView) dialog.findViewById(C10531R.C10534id.wopay_discount_dzq_detail_rv);
        this.f2411h = recyclerView2;
        recyclerView2.setOverScrollMode(2);
        WPDiscountQueryBean wPDiscountQueryBean = this.f2414k;
        if (wPDiscountQueryBean == null || wPDiscountQueryBean.getDiscountInfos() == null || this.f2414k.getDiscountInfos().get(0) == null || this.f2414k.getDiscountInfos().get(0).getDzqPage() == null) {
            return;
        }
        try {
            String dzqMsg = this.f2414k.getDiscountInfos().get(0).getDzqPage().getDzqMsg();
            String dzqAmt = this.f2414k.getDiscountInfos().get(0).getDzqPage().getDzqAmt();
            C13662t c13662t = new C13662t(dzqMsg);
            c13662t.m166a();
            c13662t.f27499a = dzqAmt;
            c13662t.f27501c = getResources().getColor(C10531R.C10532color.wp_red_color);
            c13662t.m166a();
            this.f2424u.setText(c13662t.f27507i);
            this.f2424u.setVisibility(0);
        } catch (Exception e) {
            e.printStackTrace();
            this.f2424u.setVisibility(8);
        }
        if (this.f2414k.getDiscountInfos().get(0).getDzqPage().getDzqInfos() == null) {
            return;
        }
        Iterator<WPDzqInfosBean> it = this.f2414k.getDiscountInfos().get(0).getDzqPage().getDzqInfos().iterator();
        while (it.hasNext()) {
            WPDzqInfosBean next = it.next();
            if ("1".equals(next.getDzqType())) {
                m22226a(next);
            } else {
                m22225b(next);
            }
        }
    }
}
