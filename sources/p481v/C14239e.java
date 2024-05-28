package p481v;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.C10546a;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPDzlResultBean;
import com.unicom.pay.modules.verify.bean.WPQOpenResultBean;
import com.unicom.pay.modules.verify.bean.WPQuickSmsBean;
import com.unicom.pay.modules.verify.bean.WPUnionPayResultBean;
import com.unicom.pay.normal.order.bean.WPDefaultOrderInfoBean;
import com.unicom.pay.normal.order.bean.WPPayBeforeBean;
import com.unicom.pay.normal.order.bean.WPUnionOrderInfoBean;
import com.unicom.pay.normal.order.p359ui.WPOrderActivity;
import com.unicom.pay.qpay.open.bean.WPQPayUserInfoBean;
import com.unicom.pay.widget.WPVerifyCodeEditText;
import java.util.HashMap;
import p091c0.C1529j;
import p385d0.AbstractActivityC11796a;
import p393h.AbstractView$OnClickListenerC12011e;
import p470p0.C13662t;
import p472q0.C13692n;
import p478t.InterfaceC14161f;
import p480u.C14201h;
import p480u.C14203i;
import p480u.C14205j;
import p480u.C14207k;
import p480u.C14209l;
import p480u.C14211m;
import p480u.C14213n;
import p480u.C14215o;
import p480u.C14217p;
import p480u.C14219q;
import p482w.C14255c;
import p482w.C14262f;

@NBSInstrumented
/* renamed from: v.e */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14239e extends AbstractView$OnClickListenerC12011e<C14219q> implements InterfaceC14161f {

    /* renamed from: C */
    public static final /* synthetic */ int f27736C = 0;

    /* renamed from: A */
    public InterfaceC14242c f27737A;

    /* renamed from: B */
    public InterfaceC14243d f27738B;

    /* renamed from: g */
    public TextView f27739g;

    /* renamed from: h */
    public TextView f27740h;

    /* renamed from: i */
    public WPVerifyCodeEditText f27741i;

    /* renamed from: n */
    public WPUnionPayResultBean f27746n;

    /* renamed from: p */
    public WPPayBeforeBean f27748p;

    /* renamed from: r */
    public CountDownTimerC14240a f27750r;

    /* renamed from: s */
    public WPDzlResultBean f27751s;

    /* renamed from: t */
    public String f27752t;

    /* renamed from: z */
    public InterfaceC14241b f27758z;

    /* renamed from: j */
    public String f27742j = "";

    /* renamed from: k */
    public String f27743k = "";

    /* renamed from: l */
    public String f27744l = "";

    /* renamed from: m */
    public String f27745m = "";

    /* renamed from: o */
    public String f27747o = "";

    /* renamed from: q */
    public boolean f27749q = true;

    /* renamed from: u */
    public String f27753u = "";

    /* renamed from: v */
    public String f27754v = "";

    /* renamed from: w */
    public String f27755w = "";

    /* renamed from: x */
    public String f27756x = "";

    /* renamed from: y */
    public String f27757y = "";

    /* renamed from: v.e$a */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class CountDownTimerC14240a extends CountDownTimer {

        /* renamed from: a */
        public final /* synthetic */ int f27759a;

        /* renamed from: b */
        public final /* synthetic */ int f27760b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CountDownTimerC14240a(int i, int i2) {
            super(60000L, 1000L);
            this.f27759a = i;
            this.f27760b = i2;
        }

        @Override // android.os.CountDownTimer
        public final void onFinish() {
            TextView textView = C14239e.this.f27740h;
            if (textView == null) {
                return;
            }
            textView.setEnabled(true);
            C14239e.this.f27740h.setTextColor(this.f27760b);
            C14239e.this.f27740h.setText(C10531R.string.wp_comm_verify_reset);
        }

        @Override // android.os.CountDownTimer
        public final void onTick(long j) {
            TextView textView = C14239e.this.f27740h;
            if (textView == null) {
                return;
            }
            textView.setEnabled(false);
            C14239e.this.f27740h.setTextColor(this.f27759a);
            C14239e c14239e = C14239e.this;
            c14239e.f27740h.setText(c14239e.getString(C10531R.string.wp_sms_verify_time, Integer.valueOf((int) (j / 1000))));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: v.e$b */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface InterfaceC14241b {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: v.e$c */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface InterfaceC14242c {
        /* renamed from: a */
        void mo33a(WPQOpenResultBean wPQOpenResultBean);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: v.e$d */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface InterfaceC14243d {
    }

    /* renamed from: a */
    public static Bundle m48a(String str, String str2, String str3, String str4, WPUnionPayResultBean wPUnionPayResultBean, String str5, WPPayBeforeBean wPPayBeforeBean) {
        Bundle bundle = new Bundle();
        bundle.putString("bizCode", str);
        bundle.putString("expand", str3);
        bundle.putString("orderNo", str2);
        bundle.putString("mobile", str4);
        bundle.putParcelable("riskInfo", wPUnionPayResultBean);
        bundle.putString("orderCache", str5);
        bundle.putParcelable("payBefore", wPPayBeforeBean);
        return bundle;
    }

    /* renamed from: a */
    public static Bundle m47a(String str, String str2, String str3, String str4, String str5) {
        Bundle bundle = new Bundle();
        bundle.putString("mobile", str);
        bundle.putString("bizCode", str2);
        bundle.putString(WPQPayUserInfoBean.QPAY_COLUMN_KEY, str3);
        bundle.putString("merNo", str4);
        bundle.putString("sceneCode", str5);
        return bundle;
    }

    /* renamed from: a */
    public static Bundle m46a(String str, String str2, String str3, String str4, String str5, String str6) {
        Bundle bundle = new Bundle();
        bundle.putString("mobile", str);
        bundle.putString("orderNo", str2);
        bundle.putString("bizCode", "QPAY_PAY_SMS");
        bundle.putString("hmac", str3);
        bundle.putString("referUrl", str4);
        bundle.putString("sceneCode", "PAY_DETAIL");
        bundle.putString("orderCache", str5);
        bundle.putString("expand", str6);
        return bundle;
    }

    /* renamed from: a */
    public static C14239e m51a(Bundle bundle) {
        C14239e c14239e = new C14239e();
        c14239e.setArguments(bundle);
        return c14239e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public void m38h(String str) {
        if (str.length() != 6 || !this.f27749q) {
            if (str.length() < 6) {
                this.f27749q = true;
                return;
            }
            return;
        }
        this.f27749q = false;
        if ("PAY_DZL".equals(this.f27743k)) {
            m1984f("断直连弹窗-输入完6位短信");
            C14219q c14219q = (C14219q) this.f24344a;
            String str2 = this.f27745m;
            WPDzlResultBean wPDzlResultBean = this.f27751s;
            c14219q.getClass();
            if (wPDzlResultBean == null) {
                return;
            }
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("bcmcCardSn", wPDzlResultBean.getBcmcCardSn());
            hashMap.put("paymentSn", wPDzlResultBean.getPaymentSn());
            hashMap.put("cdKey", wPDzlResultBean.getCdKey());
            hashMap.put("tradeOrderNo", str2);
            hashMap.put("verifyCode", str);
            c14219q.m1797a(C14255c.C14256a.f27777a.m30a(C14262f.f27788A, hashMap, new C14203i(c14219q, c14219q)));
        } else if ("PAY_PAY".equals(this.f27743k)) {
            m1984f("风控短验弹窗-输入完6位短信");
            if (this.f27748p == null) {
                return;
            }
            C14219q c14219q2 = (C14219q) this.f24344a;
            String str3 = this.f27744l;
            String riskUuid = this.f27746n.getRiskUuid();
            String str4 = this.f27745m;
            String str5 = this.f27747o;
            String fapAgrNo = this.f27748p.getFapAgrNo();
            String fundChnCode = this.f27748p.getFundChnCode();
            c14219q2.getClass();
            HashMap<String, Object> hashMap2 = new HashMap<>();
            hashMap2.put("toolExpand", str3);
            hashMap2.put("riskUuid", riskUuid);
            hashMap2.put("tradeOrderNo", str4);
            hashMap2.put("validateCode", str);
            hashMap2.put("orderCache", str5);
            hashMap2.put("fapAgrNo", fapAgrNo);
            hashMap2.put("fundChnCode", fundChnCode);
            c14219q2.m1797a(C14255c.C14256a.f27777a.m30a(C14262f.f27834x, hashMap2, new C14207k(c14219q2, c14219q2)));
        } else if ("PAY_WFQ".equals(this.f27743k)) {
            m1984f("分期签约弹窗-输入完6位短信");
            WPPayBeforeBean wPPayBeforeBean = this.f27748p;
            if (wPPayBeforeBean == null) {
                return;
            }
            C14219q c14219q3 = (C14219q) this.f24344a;
            String fapAgrNo2 = wPPayBeforeBean.getFapAgrNo();
            String fundChnCode2 = this.f27748p.getFundChnCode();
            String str6 = this.f27745m;
            String str7 = this.f27744l;
            c14219q3.getClass();
            HashMap<String, Object> hashMap3 = new HashMap<>();
            hashMap3.put("signType", "1");
            hashMap3.put("fapAgrNo", fapAgrNo2);
            hashMap3.put("fundChnCode", fundChnCode2);
            hashMap3.put("tradeOrderNo", str6);
            hashMap3.put("cvv2", "");
            hashMap3.put("expireDate", "");
            hashMap3.put("smsCode", str);
            hashMap3.put("toolExpand", str7);
            c14219q3.m1797a(C14255c.C14256a.f27777a.m30a(C14262f.f27809V, hashMap3, new C14217p(c14219q3, c14219q3)));
        } else if ("QPAY_RESULT_SIGN".equals(this.f27743k) || "QPAY_SET_SIGN".equals(this.f27743k) || "QPAY_DETAIL_SIGN".equals(this.f27743k)) {
            m1984f("极速支付风控短验弹窗-输入完6位短信");
            if (TextUtils.isEmpty(this.f27752t)) {
                C13692n.m135a(getString(C10531R.string.wp_sms_no_cdkey));
                return;
            }
            C14219q c14219q4 = (C14219q) this.f24344a;
            String str8 = this.f27754v;
            String str9 = this.f27752t;
            String str10 = this.f27755w;
            String str11 = this.f27753u;
            c14219q4.getClass();
            HashMap<String, Object> hashMap4 = new HashMap<>();
            hashMap4.put("merNo", str8);
            hashMap4.put("cdkey", str9);
            hashMap4.put("signScene", str10);
            hashMap4.put("payPwd", "");
            hashMap4.put("signTokenId", str11);
            hashMap4.put("verifyCode", str);
            hashMap4.put("userClientUid", C10546a.C10576i.f20125a.f20059i);
            c14219q4.m1797a(C14255c.C14256a.f27777a.m30a(C14262f.f27816f, hashMap4, new C14211m(c14219q4, c14219q4)));
        } else if ("QPAY_PAY_SMS".equals(this.f27743k)) {
            m1984f("极速支付风控短验弹窗-输入完6位短信");
            if (TextUtils.isEmpty(this.f27752t)) {
                C13692n.m135a(getString(C10531R.string.wp_sms_no_cdkey));
                return;
            }
            C14219q c14219q5 = (C14219q) this.f24344a;
            String str12 = this.f27745m;
            String str13 = this.f27756x;
            String str14 = this.f27757y;
            String str15 = this.f27752t;
            String str16 = this.f27747o;
            String str17 = this.f27744l;
            c14219q5.getClass();
            HashMap<String, Object> hashMap5 = new HashMap<>();
            hashMap5.put("tradeOrderNo", str12);
            hashMap5.put("qpayUrl", str14);
            hashMap5.put("hmac", str13);
            hashMap5.put("cdkey", str15);
            hashMap5.put("verifyCode", str);
            hashMap5.put("userClientUid", C10546a.C10576i.f20125a.f20059i);
            hashMap5.put("orderCache", str16);
            hashMap5.put("toolExpand", str17);
            c14219q5.m1797a(C14255c.C14256a.f27777a.m30a(C14262f.f27808U, hashMap5, new C14213n(c14219q5, c14219q5)));
        }
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, p393h.InterfaceC12016h
    /* renamed from: D */
    public final String mo56D() {
        return "PAY_DZL".equals(this.f27743k) ? "断直连短验弹窗" : "PAY_WFQ".equals(this.f27743k) ? "分期签约弹窗" : "风控短验弹窗";
    }

    @Override // p478t.InterfaceC14161f
    /* renamed from: G */
    public final void mo55G() {
        WPDefaultOrderInfoBean wPDefaultOrderInfoBean;
        if (isAdded()) {
            dismissAllowingStateLoss();
        }
        CountDownTimerC14240a countDownTimerC14240a = this.f27750r;
        if (countDownTimerC14240a != null) {
            countDownTimerC14240a.cancel();
            this.f27750r = null;
        }
        InterfaceC14243d interfaceC14243d = this.f27738B;
        if (interfaceC14243d != null) {
            WPOrderActivity.C10669m c10669m = (WPOrderActivity.C10669m) interfaceC14243d;
            WPUnionOrderInfoBean wPUnionOrderInfoBean = WPOrderActivity.this.f20313r0;
            if (wPUnionOrderInfoBean == null || wPUnionOrderInfoBean.getOrderInfo() == null || (wPDefaultOrderInfoBean = WPOrderActivity.this.f20315s0) == null || wPDefaultOrderInfoBean.getData() == null) {
                return;
            }
            WPOrderActivity wPOrderActivity = WPOrderActivity.this;
            ((C1529j) wPOrderActivity.f24311a).m22178a(wPOrderActivity.mo6077x(), WPOrderActivity.this.mo6076y(), WPOrderActivity.this.mo6127V());
        }
    }

    @Override // p478t.InterfaceC14161f
    /* renamed from: J */
    public final void mo54J() {
        if (isAdded()) {
            dismissAllowingStateLoss();
        }
        CountDownTimerC14240a countDownTimerC14240a = this.f27750r;
        if (countDownTimerC14240a != null) {
            countDownTimerC14240a.cancel();
            this.f27750r = null;
        }
        InterfaceC14241b interfaceC14241b = this.f27758z;
        if (interfaceC14241b != null) {
            ((AbstractActivityC11796a.RunnableC11805i.C11806a) interfaceC14241b).m2081a("1", this.f27744l, this.f27745m, null);
        }
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, p393h.InterfaceC12016h
    /* renamed from: O */
    public final String mo53O() {
        return "PAY_DZL".equals(this.f27743k) ? "wp190" : "PAY_WFQ".equals(this.f27743k) ? "wp191" : "wp185";
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: Z */
    public final int mo52Z() {
        return 0;
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: a */
    public final void mo5a(Dialog dialog) {
        dialog.getWindow().setWindowAnimations(C10531R.C10537style.WPDialogAnimation);
        dialog.setCanceledOnTouchOutside(false);
        LinearLayout linearLayout = (LinearLayout) dialog.findViewById(C10531R.C10534id.up_verify_code_ll);
        ((TextView) dialog.findViewById(C10531R.C10534id.wopay_half_title_tv)).setText(getString(C10531R.string.wp_sms_verify_title));
        ((ImageView) dialog.findViewById(C10531R.C10534id.wopay_half_close_iv)).setOnClickListener(this);
        this.f27739g = (TextView) dialog.findViewById(C10531R.C10534id.wopay_verify_code_number_tv);
        this.f27740h = (TextView) dialog.findViewById(C10531R.C10534id.wopay_verify_code_get_verify_tv);
        this.f27741i = (WPVerifyCodeEditText) dialog.findViewById(C10531R.C10534id.wopay_verify_code_verify_edt);
        this.f27740h.setOnClickListener(this);
        this.f27741i.setSpaceWidth(22.0f / getActivity().getResources().getDisplayMetrics().density);
        this.f27741i.setOnTextChangedListener(new WPVerifyCodeEditText.InterfaceC10719b() { // from class: v.-$$Lambda$e$3t4c_shgJVubONQkY7h-pxpyov0
            @Override // com.unicom.pay.widget.WPVerifyCodeEditText.InterfaceC10719b
            /* renamed from: a */
            public final void mo70a(String str) {
                C14239e.this.m38h(str);
            }
        });
    }

    @Override // p478t.InterfaceC14161f
    /* renamed from: a */
    public final void mo50a(WPDzlResultBean wPDzlResultBean) {
        this.f27751s = wPDzlResultBean;
        WPVerifyCodeEditText wPVerifyCodeEditText = this.f27741i;
        if (wPVerifyCodeEditText != null) {
            wPVerifyCodeEditText.m6037b();
        }
        m39g0();
    }

    @Override // p478t.InterfaceC14161f
    /* renamed from: a */
    public final void mo49a(WPQuickSmsBean wPQuickSmsBean) {
        this.f27752t = wPQuickSmsBean.getCdKey();
        WPVerifyCodeEditText wPVerifyCodeEditText = this.f27741i;
        if (wPVerifyCodeEditText != null) {
            wPVerifyCodeEditText.m6037b();
        }
        m39g0();
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: a0 */
    public final int mo4a0() {
        return C10531R.C10535layout.up_verify_code_pop;
    }

    @Override // p478t.InterfaceC14161f
    /* renamed from: b */
    public final void mo45b(WPQOpenResultBean wPQOpenResultBean) {
        InterfaceC14242c interfaceC14242c;
        if (isAdded()) {
            dismissAllowingStateLoss();
        }
        if (("QPAY_RESULT_SIGN".equals(this.f27743k) || "QPAY_SET_SIGN".equals(this.f27743k) || "QPAY_DETAIL_SIGN".equals(this.f27743k)) && (interfaceC14242c = this.f27737A) != null) {
            interfaceC14242c.mo33a(wPQOpenResultBean);
        }
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: b0 */
    public final int mo44b0() {
        return C10531R.C10537style.WPAdjustNothingDialog;
    }

    /* renamed from: c */
    public final void m43c(boolean z) {
        if ("PAY_DZL".equals(this.f27743k)) {
            C14219q c14219q = (C14219q) this.f24344a;
            String str = this.f27744l;
            String str2 = this.f27745m;
            c14219q.getClass();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("toolExpand", str);
            hashMap.put("tradeOrderNo", str2);
            c14219q.m1797a(C14255c.C14256a.f27777a.m30a(C14262f.f27836z, hashMap, new C14201h(c14219q, c14219q, !z ? 1 : 0)));
        } else if ("PAY_PAY".equals(this.f27743k)) {
            C14219q c14219q2 = (C14219q) this.f24344a;
            String str3 = this.f27744l;
            String str4 = this.f27745m;
            c14219q2.getClass();
            HashMap<String, Object> hashMap2 = new HashMap<>();
            hashMap2.put("toolExpand", str3);
            hashMap2.put("tradeOrderNo", str4);
            c14219q2.m1797a(C14255c.C14256a.f27777a.m30a(C14262f.f27833w, hashMap2, new C14205j(c14219q2, c14219q2, !z ? 1 : 0)));
        } else if (!"PAY_WFQ".equals(this.f27743k)) {
            if ("QPAY_RESULT_SIGN".equals(this.f27743k) || "QPAY_SET_SIGN".equals(this.f27743k) || "QPAY_DETAIL_SIGN".equals(this.f27743k) || "QPAY_PAY_SMS".equals(this.f27743k)) {
                C14219q c14219q3 = (C14219q) this.f24344a;
                c14219q3.getClass();
                c14219q3.m1797a(C14255c.C14256a.f27777a.m30a(C14262f.f27817g, new HashMap<>(), new C14209l(c14219q3, c14219q3, !z ? 1 : 0)));
            }
        } else {
            WPPayBeforeBean wPPayBeforeBean = this.f27748p;
            if (wPPayBeforeBean == null) {
                return;
            }
            C14219q c14219q4 = (C14219q) this.f24344a;
            String str5 = this.f27744l;
            String str6 = this.f27745m;
            String fundChnCode = wPPayBeforeBean.getFundChnCode();
            c14219q4.getClass();
            HashMap<String, Object> hashMap3 = new HashMap<>();
            hashMap3.put("signType", "1");
            hashMap3.put("toolExpand", str5);
            hashMap3.put("tradeOrderNo", str6);
            hashMap3.put("fundChnCode", fundChnCode);
            c14219q4.m1797a(C14255c.C14256a.f27777a.m30a(C14262f.f27796I, hashMap3, new C14215o(c14219q4, c14219q4)));
        }
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: c0 */
    public final float mo3c0() {
        return 0.7f;
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: d0 */
    public final void mo2d0() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f27743k = arguments.getString("bizCode", "");
            this.f27744l = arguments.getString("expand", "");
            this.f27745m = arguments.getString("orderNo", "");
            this.f27742j = arguments.getString("mobile", "");
            this.f27746n = (WPUnionPayResultBean) arguments.getParcelable("riskInfo");
            this.f27747o = arguments.getString("orderCache", "");
            this.f27753u = arguments.getString(WPQPayUserInfoBean.QPAY_COLUMN_KEY, "");
            this.f27754v = arguments.getString("merNo", "");
            this.f27755w = arguments.getString("sceneCode", "");
            this.f27756x = arguments.getString("hmac", "");
            this.f27757y = arguments.getString("referUrl", "");
            this.f27748p = (WPPayBeforeBean) arguments.getParcelable("payBefore");
        }
        if (this.f27746n != null && TextUtils.isEmpty(this.f27742j)) {
            this.f27742j = this.f27746n.getRiskPhoneNo();
        }
        if (TextUtils.isEmpty(this.f27742j)) {
            this.f27742j = "";
        }
        C13662t c13662t = new C13662t("已向号码 ");
        String str = this.f27742j;
        c13662t.m166a();
        c13662t.f27499a = str;
        c13662t.f27501c = getResources().getColor(C10531R.C10532color.wp_primary_text_color);
        c13662t.m166a();
        c13662t.f27499a = " 发送验证码";
        c13662t.m166a();
        this.f27739g.setText(c13662t.f27507i);
        m43c(false);
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, android.support.p083v4.app.DialogFragment
    public final void dismiss() {
        CountDownTimerC14240a countDownTimerC14240a = this.f27750r;
        if (countDownTimerC14240a != null) {
            countDownTimerC14240a.cancel();
            this.f27750r = null;
        }
        super.dismiss();
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, android.support.p083v4.app.DialogFragment
    public final void dismissAllowingStateLoss() {
        CountDownTimerC14240a countDownTimerC14240a = this.f27750r;
        if (countDownTimerC14240a != null) {
            countDownTimerC14240a.cancel();
            this.f27750r = null;
        }
        super.dismissAllowingStateLoss();
    }

    @Override // p478t.InterfaceC14161f
    /* renamed from: e */
    public final void mo42e() {
        InterfaceC14242c interfaceC14242c = this.f27737A;
        if (interfaceC14242c != null) {
            interfaceC14242c.mo33a(null);
        }
    }

    @Override // p478t.InterfaceC14161f
    /* renamed from: e */
    public final void mo41e(WPResult<WPUnionPayResultBean> wPResult) {
        if (isAdded()) {
            dismissAllowingStateLoss();
        }
        CountDownTimerC14240a countDownTimerC14240a = this.f27750r;
        if (countDownTimerC14240a != null) {
            countDownTimerC14240a.cancel();
            this.f27750r = null;
        }
        InterfaceC14241b interfaceC14241b = this.f27758z;
        if (interfaceC14241b != null) {
            ((AbstractActivityC11796a.RunnableC11805i.C11806a) interfaceC14241b).m2081a("2", "", "", wPResult);
        }
    }

    @Override // p478t.InterfaceC14161f
    /* renamed from: f */
    public final void mo40f() {
        WPVerifyCodeEditText wPVerifyCodeEditText = this.f27741i;
        if (wPVerifyCodeEditText != null) {
            wPVerifyCodeEditText.setText("");
        }
    }

    /* renamed from: g0 */
    public final void m39g0() {
        CountDownTimerC14240a countDownTimerC14240a = this.f27750r;
        if (countDownTimerC14240a != null) {
            countDownTimerC14240a.cancel();
            this.f27750r = null;
        }
        CountDownTimerC14240a countDownTimerC14240a2 = new CountDownTimerC14240a(getResources().getColor(C10531R.C10532color.wp_desc_text_color), getResources().getColor(C10531R.C10532color.wp_protocol_color));
        this.f27750r = countDownTimerC14240a2;
        countDownTimerC14240a2.start();
    }

    @Override // p478t.InterfaceC14161f
    /* renamed from: k */
    public final void mo37k() {
        WPVerifyCodeEditText wPVerifyCodeEditText = this.f27741i;
        if (wPVerifyCodeEditText != null) {
            wPVerifyCodeEditText.setText("");
        }
        this.f27749q = true;
        TextView textView = this.f27740h;
        if (textView != null) {
            textView.setEnabled(true);
            this.f27740h.setTextColor(getResources().getColor(C10531R.C10532color.wp_protocol_color));
            this.f27740h.setText(C10531R.string.wp_comm_verify_reset);
        }
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: m */
    public final C14219q mo0m() {
        return new C14219q();
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, android.support.p083v4.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        CountDownTimerC14240a countDownTimerC14240a = this.f27750r;
        if (countDownTimerC14240a != null) {
            countDownTimerC14240a.cancel();
            this.f27750r = null;
        }
        super.onCancel(dialogInterface);
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, android.view.View.OnClickListener
    public final void onClick(View view) {
        String str;
        String str2;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        if (view.getId() != C10531R.C10534id.wopay_half_close_iv) {
            if (view.getId() == C10531R.C10534id.wopay_verify_code_get_verify_tv) {
                if ("PAY_DZL".equals(this.f27743k)) {
                    str = "点击-断直连弹窗-重新发送";
                } else if ("PAY_PAY".equals(this.f27743k)) {
                    str = "点击-风控短验弹窗-重新发送";
                } else if ("PAY_WFQ".equals(this.f27743k)) {
                    str = "点击-分期签约弹窗-重新发送";
                } else {
                    if ("QPAY_RESULT_SIGN".equals(this.f27743k) || "QPAY_SET_SIGN".equals(this.f27743k) || "QPAY_DETAIL_SIGN".equals(this.f27743k) || "QPAY_PAY_SMS".equals(this.f27743k)) {
                        str = "点击-极速支付风控短验弹窗-重新发送";
                    }
                    m43c(true);
                }
                m1984f(str);
                m43c(true);
            }
            NBSActionInstrumentation.onClickEventExit();
            return;
        }
        if ("PAY_DZL".equals(this.f27743k)) {
            str2 = "点击-断直连弹窗-X";
        } else if ("PAY_PAY".equals(this.f27743k)) {
            str2 = "点击-风控短验弹窗-X";
        } else if (!"PAY_WFQ".equals(this.f27743k)) {
            if ("QPAY_RESULT_SIGN".equals(this.f27743k) || "QPAY_SET_SIGN".equals(this.f27743k) || "QPAY_DETAIL_SIGN".equals(this.f27743k) || "QPAY_PAY_SMS".equals(this.f27743k)) {
                str2 = "点击-极速支付风控短验弹窗-X";
            }
            dismissAllowingStateLoss();
            NBSActionInstrumentation.onClickEventExit();
        } else {
            str2 = "点击-分期签约弹窗-X";
        }
        m1984f(str2);
        dismissAllowingStateLoss();
        NBSActionInstrumentation.onClickEventExit();
    }

    @Override // android.support.p083v4.app.Fragment
    public final void onDestroy() {
        CountDownTimerC14240a countDownTimerC14240a = this.f27750r;
        if (countDownTimerC14240a != null) {
            countDownTimerC14240a.cancel();
            this.f27750r = null;
        }
        super.onDestroy();
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, android.support.p083v4.app.DialogFragment, android.support.p083v4.app.Fragment
    public final void onDestroyView() {
        CountDownTimerC14240a countDownTimerC14240a = this.f27750r;
        if (countDownTimerC14240a != null) {
            countDownTimerC14240a.cancel();
            this.f27750r = null;
        }
        super.onDestroyView();
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, p393h.InterfaceC12016h
    /* renamed from: p */
    public final String mo36p() {
        return "PAY_DZL".equals(this.f27743k) ? "98U01170wp190" : "PAY_WFQ".equals(this.f27743k) ? "98U01170wp191" : "98U01170wp185";
    }

    @Override // p478t.InterfaceC14161f
    /* renamed from: v */
    public final void mo35v() {
        WPVerifyCodeEditText wPVerifyCodeEditText = this.f27741i;
        if (wPVerifyCodeEditText != null) {
            wPVerifyCodeEditText.m6037b();
        }
        m39g0();
    }

    @Override // p478t.InterfaceC14161f
    /* renamed from: z */
    public final void mo34z() {
        WPVerifyCodeEditText wPVerifyCodeEditText = this.f27741i;
        if (wPVerifyCodeEditText != null) {
            wPVerifyCodeEditText.m6037b();
        }
        m39g0();
    }
}
