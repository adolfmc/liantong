package p385d0;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.normal.order.bean.WPPayInfoBean;
import com.unicom.pay.normal.order.p359ui.WPOrderActivity;
import java.util.ArrayList;
import p387e0.C11889u;
import p393h.AbstractC12007d;
import p470p0.C13659r;

@NBSInstrumented
/* renamed from: d0.k */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C11824k extends AbstractC12007d implements C11889u.InterfaceC11899i {

    /* renamed from: k */
    public static final /* synthetic */ int f24084k = 0;

    /* renamed from: h */
    public RecyclerView f24085h;

    /* renamed from: i */
    public C11889u f24086i;

    /* renamed from: j */
    public ArrayList<WPPayInfoBean> f24087j = new ArrayList<>();

    /* renamed from: d0.k$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class DialogInterface$OnShowListenerC11825a implements DialogInterface.OnShowListener {
        @Override // android.content.DialogInterface.OnShowListener
        public final void onShow(DialogInterface dialogInterface) {
            try {
                BottomSheetBehavior.from((FrameLayout) ((BottomSheetDialog) dialogInterface).findViewById(C0884R.C0887id.design_bottom_sheet)).setState(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // p393h.InterfaceC12016h
    /* renamed from: D */
    public final String mo56D() {
        return "勾选支付方式弹窗";
    }

    @Override // p393h.InterfaceC12016h
    /* renamed from: O */
    public final String mo53O() {
        return "wp140";
    }

    @Override // p393h.AbstractC12007d
    /* renamed from: a */
    public final void mo1989a() {
        dismiss();
    }

    /* renamed from: a */
    public final void m2079a(WPPayInfoBean wPPayInfoBean) {
        m1987a(WPPayInfoBean.f20222KJ.equals(wPPayInfoBean.getToolCode()) ? "点击-支付方式列表-银行卡" : WPPayInfoBean.WFQ.equals(wPPayInfoBean.getToolCode()) ? "点击-支付方式列表-可分期银行卡" : WPPayInfoBean.BKYH.equals(wPPayInfoBean.getToolCode()) ? "点击-支付方式列表-添加某银行享优惠" : WPPayInfoBean.f20227YE.equals(wPPayInfoBean.getToolCode()) ? "点击-支付方式列表-零钱" : WPPayInfoBean.f20221BK.equals(wPPayInfoBean.getToolCode()) ? "点击-支付方式列表-添加银行卡" : wPPayInfoBean.getToolName());
    }

    @Override // p393h.AbstractC12007d, android.support.design.widget.BottomSheetDialogFragment, android.support.p086v7.app.AppCompatDialogFragment, android.support.p083v4.app.DialogFragment
    @NonNull
    public final Dialog onCreateDialog(@Nullable Bundle bundle) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), C10531R.C10537style.WPBottomWhiteSheetDialog);
        bottomSheetDialog.setOnShowListener(new DialogInterface$OnShowListenerC11825a());
        return bottomSheetDialog;
    }

    @Override // android.support.p083v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(C10531R.C10535layout.wp_common_list_dialog, viewGroup, false);
        inflate.setLayoutParams(new ViewGroup.LayoutParams(C13659r.m168b(getContext()), C13659r.m169a(getContext()) / 2));
        m1988a(inflate, getString(C10531R.string.wp_select_method_title));
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(C10531R.C10534id.wopay_common_rv);
        this.f24085h = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        C11889u c11889u = new C11889u(getActivity(), this.f24087j);
        this.f24086i = c11889u;
        c11889u.f24233e = this;
        this.f24085h.setAdapter(c11889u);
        this.f24086i.f24232d = (WPOrderActivity) getActivity();
        return inflate;
    }

    @Override // p393h.InterfaceC12016h
    /* renamed from: p */
    public final String mo36p() {
        return "98U01170wp140";
    }
}
