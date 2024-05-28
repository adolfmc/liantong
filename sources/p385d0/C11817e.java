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
import com.unicom.pay.normal.order.bean.WPFqInfoBean;
import com.unicom.pay.normal.order.p359ui.WPInstalmentActivity;
import java.util.ArrayList;
import p387e0.C11866j;
import p393h.AbstractC12007d;
import p470p0.C13659r;

@NBSInstrumented
/* renamed from: d0.e */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C11817e extends AbstractC12007d implements C11866j.InterfaceC11869c {

    /* renamed from: m */
    public static final /* synthetic */ int f24069m = 0;

    /* renamed from: h */
    public RecyclerView f24070h;

    /* renamed from: i */
    public C11866j f24071i;

    /* renamed from: j */
    public ArrayList<WPFqInfoBean> f24072j = new ArrayList<>();

    /* renamed from: k */
    public int f24073k = 0;

    /* renamed from: l */
    public boolean f24074l = true;

    /* renamed from: d0.e$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class DialogInterface$OnShowListenerC11818a implements DialogInterface.OnShowListener {
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
        boolean z = this.f24074l;
        return "银行卡列表弹窗";
    }

    @Override // p393h.InterfaceC12016h
    /* renamed from: O */
    public final String mo53O() {
        return this.f24074l ? "wp210" : "wp310";
    }

    @Override // p393h.AbstractC12007d
    /* renamed from: a */
    public final void mo1989a() {
        dismiss();
    }

    /* renamed from: a */
    public final void m2080a(WPFqInfoBean wPFqInfoBean) {
        String str;
        if (!wPFqInfoBean.isJumpWeb() && !WPFqInfoBean.WFQ_XYK_PWD.equals(wPFqInfoBean.getType())) {
            str = "已绑信用卡";
        } else if (WPFqInfoBean.WFQ_XYK_PWD.equals(wPFqInfoBean.getType())) {
            return;
        } else {
            str = "添加信用卡";
        }
        m1987a(str);
    }

    @Override // p393h.AbstractC12007d, android.support.design.widget.BottomSheetDialogFragment, android.support.p086v7.app.AppCompatDialogFragment, android.support.p083v4.app.DialogFragment
    @NonNull
    public final Dialog onCreateDialog(@Nullable Bundle bundle) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), C10531R.C10537style.WPBottomWhiteSheetDialog);
        bottomSheetDialog.setOnShowListener(new DialogInterface$OnShowListenerC11818a());
        return bottomSheetDialog;
    }

    @Override // android.support.p083v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(C10531R.C10535layout.wp_common_list_dialog, viewGroup, false);
        inflate.setLayoutParams(new ViewGroup.LayoutParams(C13659r.m168b(getContext()), C13659r.m169a(getContext()) / 2));
        m1988a(inflate, getString(C10531R.string.up_instalment_bank_list_title));
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(C10531R.C10534id.wopay_common_rv);
        this.f24070h = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        C11866j c11866j = new C11866j(getActivity(), this.f24072j);
        this.f24071i = c11866j;
        c11866j.f24158d = this;
        c11866j.f24160f = this.f24073k;
        this.f24070h.setAdapter(c11866j);
        this.f24071i.f24157c = (WPInstalmentActivity) getActivity();
        return inflate;
    }

    @Override // p393h.InterfaceC12016h
    /* renamed from: p */
    public final String mo36p() {
        return this.f24074l ? "98U01170wp210" : "98U01170wp310";
    }
}
