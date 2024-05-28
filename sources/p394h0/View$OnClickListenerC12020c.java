package p394h0;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.C0884R;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.UnicomPaySDK;
import com.unicom.pay.qpay.open.bean.WPAgreementBean;
import com.unicom.pay.utils.buried.WPTrendsEventsUtils;
import p393h.AbstractC12007d;
import p470p0.C13647j;
import p470p0.C13659r;

@NBSInstrumented
/* renamed from: h0.c */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class View$OnClickListenerC12020c extends AbstractC12007d implements View.OnClickListener {

    /* renamed from: A */
    public WPAgreementBean f24360A;

    /* renamed from: h */
    public TextView f24361h;

    /* renamed from: i */
    public TextView f24362i;

    /* renamed from: j */
    public TextView f24363j;

    /* renamed from: k */
    public TextView f24364k;

    /* renamed from: l */
    public TextView f24365l;

    /* renamed from: m */
    public LinearLayout f24366m;

    /* renamed from: n */
    public ImageView f24367n;

    /* renamed from: o */
    public TextView f24368o;

    /* renamed from: p */
    public TextView f24369p;

    /* renamed from: q */
    public ImageView f24370q;

    /* renamed from: r */
    public TextView f24371r;

    /* renamed from: s */
    public TextView f24372s;

    /* renamed from: t */
    public ImageView f24373t;

    /* renamed from: u */
    public TextView f24374u;

    /* renamed from: v */
    public TextView f24375v;

    /* renamed from: w */
    public TextView f24376w;

    /* renamed from: x */
    public InterfaceC12022b f24377x;

    /* renamed from: y */
    public String f24378y = "";

    /* renamed from: z */
    public String f24379z;

    /* renamed from: h0.c$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class DialogInterface$OnShowListenerC12021a implements DialogInterface.OnShowListener {
        @Override // android.content.DialogInterface.OnShowListener
        public final void onShow(DialogInterface dialogInterface) {
            try {
                BottomSheetBehavior.from((FrameLayout) ((BottomSheetDialog) dialogInterface).findViewById(C0884R.C0887id.design_bottom_sheet)).setState(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: h0.c$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC12022b {
        /* renamed from: A */
        void mo1978A();

        /* renamed from: q */
        void mo1977q();
    }

    /* renamed from: a */
    public static View$OnClickListenerC12020c m1979a(String str, String str2, WPAgreementBean wPAgreementBean) {
        Bundle bundle = new Bundle();
        View$OnClickListenerC12020c view$OnClickListenerC12020c = new View$OnClickListenerC12020c();
        bundle.putString("marketTxt", str);
        bundle.putString("sceneCode", str2);
        bundle.putParcelable("agreementBean", wPAgreementBean);
        view$OnClickListenerC12020c.setArguments(bundle);
        return view$OnClickListenerC12020c;
    }

    @Override // p393h.InterfaceC12016h
    /* renamed from: D */
    public final String mo56D() {
        if ("QPAY_RESULT_SIGN".equals(this.f24379z)) {
            return "极速支付开通弹窗";
        }
        if ("QPAY_DETAIL_SIGN".equals(this.f24379z)) {
            return "极速支付开通并支付弹窗";
        }
        return null;
    }

    @Override // p393h.InterfaceC12016h
    /* renamed from: O */
    public final String mo53O() {
        if ("QPAY_RESULT_SIGN".equals(this.f24379z)) {
            return "qp006";
        }
        if ("QPAY_DETAIL_SIGN".equals(this.f24379z)) {
            return "qp000";
        }
        return null;
    }

    @Override // p393h.AbstractC12007d, android.support.p083v4.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        InterfaceC12022b interfaceC12022b = this.f24377x;
        if (interfaceC12022b != null) {
            interfaceC12022b.mo1977q();
        }
        m1987a("点击-开通弹窗-空白处");
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        String str;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        int id = view.getId();
        if (id == C10531R.C10534id.wopay_qpay_open_confirm_btn) {
            InterfaceC12022b interfaceC12022b = this.f24377x;
            if (interfaceC12022b != null) {
                interfaceC12022b.mo1978A();
            }
            if ("QPAY_RESULT_SIGN".equals(this.f24379z)) {
                str = "点击-开通弹窗-同意开启按钮";
            } else if ("QPAY_DETAIL_SIGN".equals(this.f24379z)) {
                str = "点击-开通弹窗-开启并支付按钮";
            }
            m1987a(str);
        } else {
            if (id == C10531R.C10534id.wopay_half_qpay_protocolo_close_iv) {
                InterfaceC12022b interfaceC12022b2 = this.f24377x;
                if (interfaceC12022b2 != null) {
                    interfaceC12022b2.mo1977q();
                }
                dismissAllowingStateLoss();
                str = "点击-开通弹窗-X";
            } else if (id == C10531R.C10534id.wopay_qpay_open_cancel_btn) {
                InterfaceC12022b interfaceC12022b3 = this.f24377x;
                if (interfaceC12022b3 != null) {
                    interfaceC12022b3.mo1977q();
                }
                dismissAllowingStateLoss();
                str = "点击-开通弹窗-暂不开启文案";
            } else if (id == C10531R.C10534id.wopay_qpay_open_pay_protocol2_tv) {
                if (this.f24360A != null && UnicomPaySDK.getInstance().getNativeFunctionCallback() != null) {
                    UnicomPaySDK.getInstance().getNativeFunctionCallback().openWebview(getActivity(), this.f24360A.getAgreementUrl());
                }
                WPTrendsEventsUtils.addWindow("qp006");
                m1987a("点击-查看《极速支付服务用户协议》");
                WPTrendsEventsUtils.trendsPageData("极速支付协议页面", "98U01170qp001", "qp001");
            } else if (id == C10531R.C10534id.wopay_qpay_half_protocol_title_tips_tv) {
                if (this.f24360A != null && UnicomPaySDK.getInstance().getNativeFunctionCallback() != null) {
                    UnicomPaySDK.getInstance().getNativeFunctionCallback().openWebview(getActivity(), this.f24360A.getQpayHelpUrl());
                }
                str = "点击-开通弹窗-小问号";
            }
            m1987a(str);
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    @Override // p393h.AbstractC12007d, android.support.design.widget.BottomSheetDialogFragment, android.support.p086v7.app.AppCompatDialogFragment, android.support.p083v4.app.DialogFragment
    @NonNull
    public final Dialog onCreateDialog(@Nullable Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f24378y = arguments.getString("marketTxt", "");
            this.f24379z = arguments.getString("sceneCode", "");
            this.f24360A = (WPAgreementBean) arguments.getParcelable("agreementBean");
        }
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), C10531R.C10537style.WPBottomWhiteSheetDialog);
        bottomSheetDialog.setOnShowListener(new DialogInterface$OnShowListenerC12021a());
        return bottomSheetDialog;
    }

    @Override // android.support.p083v4.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(C10531R.C10535layout.wp_qpay_protocl_dialog, viewGroup, false);
        inflate.setLayoutParams(new ViewGroup.LayoutParams(C13659r.m168b(getContext()), (C13659r.m169a(getContext()) * 2) / 3));
        this.f24361h = (TextView) inflate.findViewById(C10531R.C10534id.wopay_qpay_half_protocol_title_desc_tv);
        ((ImageView) inflate.findViewById(C10531R.C10534id.wopay_half_qpay_protocolo_close_iv)).setOnClickListener(this);
        this.f24376w = (TextView) inflate.findViewById(C10531R.C10534id.wopay_qpay_open_market_tv);
        if (TextUtils.isEmpty(this.f24378y)) {
            this.f24376w.setVisibility(8);
        } else {
            this.f24376w.setText(this.f24378y);
            this.f24376w.setVisibility(0);
        }
        ((ImageView) inflate.findViewById(C10531R.C10534id.wopay_qpay_half_protocol_title_tips_tv)).setOnClickListener(this);
        TextView textView = (TextView) inflate.findViewById(C10531R.C10534id.wopay_qpay_open_pay_protocol2_tv);
        this.f24362i = textView;
        textView.setOnClickListener(this);
        this.f24363j = (TextView) inflate.findViewById(C10531R.C10534id.wopay_qpay_half_protocol_desc_tv);
        TextView textView2 = (TextView) inflate.findViewById(C10531R.C10534id.wopay_qpay_open_confirm_btn);
        this.f24364k = textView2;
        textView2.getPaint().setFakeBoldText(true);
        this.f24364k.setOnClickListener(this);
        TextView textView3 = (TextView) inflate.findViewById(C10531R.C10534id.wopay_qpay_open_cancel_btn);
        this.f24365l = textView3;
        textView3.setOnClickListener(this);
        if ("QPAY_RESULT_SIGN".equals(this.f24379z)) {
            this.f24364k.setText(C10531R.string.wp_qpay_half_protocol_result_sign);
            this.f24365l.setVisibility(8);
        } else if ("QPAY_DETAIL_SIGN".equals(this.f24379z)) {
            this.f24364k.setText(C10531R.string.wp_qpay_half_protocol_detail_sign);
            this.f24365l.setVisibility(0);
        }
        WPAgreementBean wPAgreementBean = this.f24360A;
        if (wPAgreementBean != null) {
            if (wPAgreementBean.getQpayTitle() != null) {
                this.f24361h.setText(this.f24360A.getQpayTitle().getTitle());
            }
            this.f24363j.setText(this.f24360A.getSetPrompt());
            this.f24362i.setText(this.f24360A.getAgreementName());
        }
        this.f24366m = (LinearLayout) inflate.findViewById(C10531R.C10534id.wopay_qpay_setting_tips_ll);
        this.f24367n = (ImageView) inflate.findViewById(C10531R.C10534id.wopay_qpay_open_tips_avatar_iv1);
        this.f24368o = (TextView) inflate.findViewById(C10531R.C10534id.wopay_qpay_open_tips_title_tv1);
        this.f24369p = (TextView) inflate.findViewById(C10531R.C10534id.wopay_qpay_open_tips_desc_tv1);
        this.f24370q = (ImageView) inflate.findViewById(C10531R.C10534id.wopay_qpay_open_tips_avatar_iv2);
        this.f24371r = (TextView) inflate.findViewById(C10531R.C10534id.wopay_qpay_open_tips_title_tv2);
        this.f24372s = (TextView) inflate.findViewById(C10531R.C10534id.wopay_qpay_open_tips_desc_tv2);
        this.f24373t = (ImageView) inflate.findViewById(C10531R.C10534id.wopay_qpay_open_tips_avatar_iv3);
        this.f24374u = (TextView) inflate.findViewById(C10531R.C10534id.wopay_qpay_open_tips_title_tv3);
        this.f24375v = (TextView) inflate.findViewById(C10531R.C10534id.wopay_qpay_open_tips_desc_tv3);
        WPAgreementBean wPAgreementBean2 = this.f24360A;
        if (wPAgreementBean2 != null) {
            if (wPAgreementBean2.getSetTitle1() != null) {
                C13647j.m179a(getActivity(), this.f24360A.getSetTitle1().getIconUrl(), this.f24367n);
                this.f24368o.setText(this.f24360A.getSetTitle1().getTitle());
                this.f24368o.getPaint().setFakeBoldText(true);
                this.f24369p.setText(this.f24360A.getSetTitle1().getTitleDesc());
                this.f24366m.setVisibility(0);
            }
            if (this.f24360A.getSetTitle2() != null) {
                C13647j.m179a(getActivity(), this.f24360A.getSetTitle2().getIconUrl(), this.f24370q);
                this.f24371r.setText(this.f24360A.getSetTitle2().getTitle());
                this.f24371r.getPaint().setFakeBoldText(true);
                this.f24372s.setText(this.f24360A.getSetTitle2().getTitleDesc());
                this.f24366m.setVisibility(0);
            }
            if (this.f24360A.getSetTitle3() != null) {
                C13647j.m179a(getActivity(), this.f24360A.getSetTitle3().getIconUrl(), this.f24373t);
                this.f24374u.setText(this.f24360A.getSetTitle3().getTitle());
                this.f24374u.getPaint().setFakeBoldText(true);
                this.f24375v.setText(this.f24360A.getSetTitle3().getTitleDesc());
                this.f24366m.setVisibility(0);
            }
        }
        return inflate;
    }

    @Override // p393h.InterfaceC12016h
    /* renamed from: p */
    public final String mo36p() {
        if ("QPAY_RESULT_SIGN".equals(this.f24379z)) {
            return "98U01170qp006";
        }
        if ("QPAY_DETAIL_SIGN".equals(this.f24379z)) {
            return "98U01170qp000";
        }
        return null;
    }
}
