package p082a0;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.C0884R;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.normal.discount.bean.WPDiscountDetailBean;
import com.unicom.pay.normal.discount.bean.WPDiscountInfoBean;
import com.unicom.pay.normal.order.p359ui.WPOrderActivity;
import java.util.ArrayList;
import p387e0.C11860g;
import p393h.AbstractC12007d;
import p393h.AbstractDialogInterface$OnCancelListenerC12000b;
import p470p0.C13659r;

@NBSInstrumented
/* renamed from: a0.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class C0784b extends AbstractC12007d implements C11860g.InterfaceC11863c {

    /* renamed from: h */
    public RecyclerView f2432h;

    /* renamed from: i */
    public C11860g f2433i;

    /* renamed from: j */
    public ArrayList<WPDiscountDetailBean> f2434j;

    /* renamed from: k */
    public String f2435k;

    /* renamed from: l */
    public InterfaceC0787c f2436l;

    /* renamed from: m */
    public String f2437m;

    /* renamed from: n */
    public String f2438n;

    /* renamed from: a0.b$a */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class DialogInterface$OnShowListenerC0785a implements DialogInterface.OnShowListener {
        @Override // android.content.DialogInterface.OnShowListener
        public final void onShow(DialogInterface dialogInterface) {
            try {
                BottomSheetBehavior.from((FrameLayout) ((BottomSheetDialog) dialogInterface).findViewById(C0884R.C0887id.design_bottom_sheet)).setState(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @NBSInstrumented
    /* renamed from: a0.b$b */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class View$OnClickListenerC0786b implements View.OnClickListener {
        public View$OnClickListenerC0786b() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            InterfaceC0787c interfaceC0787c = C0784b.this.f2436l;
            if (interfaceC0787c != null) {
                WPOrderActivity wPOrderActivity = (WPOrderActivity) interfaceC0787c;
                C0784b c0784b = wPOrderActivity.f20303h0;
                if (c0784b != null) {
                    c0784b.dismissAllowingStateLoss();
                }
                C0788c c0788c = wPOrderActivity.f20302g0;
                if (c0788c != null) {
                    c0788c.dismissAllowingStateLoss();
                }
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: a0.b$c */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC0787c {
    }

    /* renamed from: a */
    public static C0784b m22223a(ArrayList<WPDiscountDetailBean> arrayList, String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        bundle.putParcelableArrayList("detailList", arrayList);
        bundle.putString("discountType", str);
        bundle.putString("title", str2);
        bundle.putString("discountLevel", str3);
        C0784b c0784b = new C0784b();
        c0784b.setArguments(bundle);
        return c0784b;
    }

    @Override // p393h.InterfaceC12016h
    /* renamed from: D */
    public final String mo56D() {
        return WPDiscountInfoBean.TYYX.equals(this.f2435k) ? "沃支付优惠弹窗" : WPDiscountInfoBean.PAYTOOLSYX.equals(this.f2435k) ? "支付方式优惠弹窗" : "-";
    }

    @Override // p393h.InterfaceC12016h
    /* renamed from: O */
    public final String mo53O() {
        return WPDiscountInfoBean.TYYX.equals(this.f2435k) ? "wp101" : WPDiscountInfoBean.PAYTOOLSYX.equals(this.f2435k) ? "wp103" : "-";
    }

    @Override // p393h.AbstractC12007d
    /* renamed from: a */
    public final void mo1989a() {
        dismiss();
    }

    @Override // p393h.AbstractC12007d, android.support.design.widget.BottomSheetDialogFragment, android.support.p086v7.app.AppCompatDialogFragment, android.support.p083v4.app.DialogFragment
    @NonNull
    public final Dialog onCreateDialog(@Nullable Bundle bundle) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), C10531R.C10537style.WpBottomSheetDialog);
        bottomSheetDialog.getWindow().setWindowAnimations(-1);
        bottomSheetDialog.setOnShowListener(new DialogInterface$OnShowListenerC0785a());
        return bottomSheetDialog;
    }

    @Override // android.support.p083v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f2434j = arguments.getParcelableArrayList("detailList");
            this.f2435k = arguments.getString("discountType");
            this.f2437m = arguments.getString("title");
            this.f2438n = arguments.getString("discountLevel");
        }
        View inflate = layoutInflater.inflate(C10531R.C10535layout.wp_common_list_dialog, viewGroup, false);
        inflate.setLayoutParams(new ViewGroup.LayoutParams(C13659r.m168b(getContext()), C13659r.m169a(getContext()) / 2));
        m1988a(inflate, TextUtils.isEmpty(this.f2437m) ? getString(C10531R.string.wp_select_discount_detail_title1) : this.f2437m);
        this.f2432h = (RecyclerView) inflate.findViewById(C10531R.C10534id.wopay_common_rv);
        C11860g c11860g = new C11860g(getActivity(), this.f2434j);
        this.f2433i = c11860g;
        c11860g.f24140f = this.f2438n;
        c11860g.f24137c = this;
        this.f2432h.setLayoutManager(new LinearLayoutManager(getContext()));
        this.f2432h.setAdapter(this.f2433i);
        AbstractDialogInterface$OnCancelListenerC12000b abstractDialogInterface$OnCancelListenerC12000b = (AbstractDialogInterface$OnCancelListenerC12000b) getActivity();
        if (abstractDialogInterface$OnCancelListenerC12000b instanceof C11860g.InterfaceC11862b) {
            this.f2433i.f24136b = (C11860g.InterfaceC11862b) abstractDialogInterface$OnCancelListenerC12000b;
        }
        if (this.f2436l != null) {
            this.f24335a.setVisibility(0);
            this.f24336b.setOnClickListener(new View$OnClickListenerC0786b());
        }
        return inflate;
    }

    @Override // p393h.InterfaceC12016h
    /* renamed from: p */
    public final String mo36p() {
        return WPDiscountInfoBean.TYYX.equals(this.f2435k) ? "98U01170wp101" : WPDiscountInfoBean.PAYTOOLSYX.equals(this.f2435k) ? "98U01170wp103" : "-";
    }

    /* renamed from: a */
    public final void m22222a(boolean z) {
        C11860g c11860g = this.f2433i;
        if (c11860g != null) {
            c11860g.f24139e = z;
        }
    }
}
