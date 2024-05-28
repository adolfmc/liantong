package p482w;

import com.unicom.pay.C10546a;
import com.unicom.pay.common.bean.EnvConfig;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: w.f */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class C14262f {

    /* renamed from: A */
    public static final String f27788A;

    /* renamed from: B */
    public static final String f27789B;

    /* renamed from: C */
    public static final String f27790C;

    /* renamed from: D */
    public static final String f27791D;

    /* renamed from: E */
    public static final String f27792E;

    /* renamed from: F */
    public static final String f27793F;

    /* renamed from: G */
    public static final String f27794G;

    /* renamed from: H */
    public static final String f27795H;

    /* renamed from: I */
    public static final String f27796I;

    /* renamed from: J */
    public static final String f27797J;

    /* renamed from: K */
    public static final String f27798K;

    /* renamed from: L */
    public static final String f27799L;

    /* renamed from: M */
    public static final String f27800M;

    /* renamed from: N */
    public static final String f27801N;

    /* renamed from: O */
    public static final String f27802O;

    /* renamed from: P */
    public static final String f27803P;

    /* renamed from: Q */
    public static final String f27804Q;

    /* renamed from: R */
    public static final String f27805R;

    /* renamed from: S */
    public static final String f27806S;

    /* renamed from: T */
    public static final String f27807T;

    /* renamed from: U */
    public static final String f27808U;

    /* renamed from: V */
    public static final String f27809V;

    /* renamed from: W */
    public static final String f27810W;

    /* renamed from: a */
    public static final String f27811a;

    /* renamed from: b */
    public static final String f27812b;

    /* renamed from: c */
    public static final String f27813c;

    /* renamed from: d */
    public static final String f27814d;

    /* renamed from: e */
    public static final String f27815e;

    /* renamed from: f */
    public static final String f27816f;

    /* renamed from: g */
    public static final String f27817g;

    /* renamed from: h */
    public static final String f27818h;

    /* renamed from: i */
    public static final String f27819i;

    /* renamed from: j */
    public static final String f27820j;

    /* renamed from: k */
    public static final String f27821k;

    /* renamed from: l */
    public static final String f27822l;

    /* renamed from: m */
    public static final String f27823m;

    /* renamed from: n */
    public static final String f27824n;

    /* renamed from: o */
    public static final String f27825o;

    /* renamed from: p */
    public static final String f27826p;

    /* renamed from: q */
    public static final String f27827q;

    /* renamed from: r */
    public static final String f27828r;

    /* renamed from: s */
    public static final String f27829s;

    /* renamed from: t */
    public static final String f27830t;

    /* renamed from: u */
    public static final String f27831u;

    /* renamed from: v */
    public static final String f27832v;

    /* renamed from: w */
    public static final String f27833w;

    /* renamed from: x */
    public static final String f27834x;

    /* renamed from: y */
    public static final String f27835y;

    /* renamed from: z */
    public static final String f27836z;

    static {
        m21a();
        String str = m21a() + "/ci-mcss-gateway-front";
        f27811a = C14261e.m22a(str, "/v2/quickPay/querySettings");
        f27812b = C14261e.m22a(str, "/v2/quickPay/closeQpay");
        f27813c = C14261e.m22a(str, "/v2/quickPay/setPaymentLimit");
        f27814d = C14261e.m22a(str, "/v2/quickPay/setPaymentOrderNum");
        f27815e = C14261e.m22a(str, "/v2/quickPay/queryAgreement");
        f27816f = C14261e.m22a(str, "/v2/quickPay/signQPay");
        f27817g = C14261e.m22a(str, "/v2/quickPay/applyVerCode");
        f27818h = C14261e.m22a(str, "/v2/quickPay/getKey");
        f27819i = C14261e.m22a(str, "/v2/quickPay/findState");
        f27820j = C14261e.m22a(str, "/v2/quickPay/findStateByScene");
        f27821k = C14261e.m22a(str, "/v2/quickPay/qpay");
        f27822l = C14261e.m22a(str, "/v2/quickPay/closeQPayRecommend");
        f27823m = C14261e.m22a(str, "/v2/cashierpay/queryPwdUrl");
        f27824n = C14261e.m22a(str, "/v2/quickPay/qpayBefore");
        f27825o = C14261e.m22a(str, "/v2/quickPay/findUserState");
        String str2 = m21a() + "/ci-mcss-gateway-front";
        f27826p = C14261e.m22a(str2, "/v2/cashierpay/queryOrderDetail");
        f27827q = C14261e.m22a(str2, "/v2/cashierpay/queryMethodList");
        f27828r = C14261e.m22a(str2, "/v2/cashierpay/queryDiscountList");
        f27829s = C14261e.m22a(str2, "/v2/cashierpay/updateMethod");
        f27830t = C14261e.m22a(str2, "/v2/cashierpay/updateDiscountList");
        f27831u = C14261e.m22a(str2, "/v2/cashierpay/payBefore");
        f27832v = C14261e.m22a(str2, "/v2/cashierpay/pay");
        f27833w = C14261e.m22a(str2, "/v2/cashierpay/sendRiskMsg");
        f27834x = C14261e.m22a(str2, "/v2/cashierpay/riskMsgCheck");
        f27835y = C14261e.m22a(str2, "/v2/cashierpay/faceCheck");
        f27836z = C14261e.m22a(str2, "/v2/cashierpay/dzlSendSignMsg");
        f27788A = C14261e.m22a(str2, "/v2/cashierpay/dzlSign");
        f27789B = C14261e.m22a(str2, "/v2/cashierpay/getPublicKey");
        f27790C = C14261e.m22a(str2, "/v2/cashierpay/queryPayResult");
        f27791D = C14261e.m22a(str2, "/v2/cashierpay/otherPay");
        f27792E = C14261e.m22a(str2, "/v1/fq/queryFqPageInfo");
        f27793F = C14261e.m22a(str2, "/v1/fq/queryFQDiscountList");
        f27794G = C14261e.m22a(str2, "/v1/fq/queryFQBankList");
        f27795H = C14261e.m22a(str2, "/v1/fq/changePayType");
        f27796I = C14261e.m22a(str2, "/v2/cashierpay/fqSendSignMsg");
        f27797J = C14261e.m22a(str2, "/v1/payfront/fido/querySupport");
        f27798K = C14261e.m22a(str2, "/v1/payfront/fido/queryFingerStatus");
        f27799L = C14261e.m22a(str2, "/v1/payfront/fido/openFidoGM");
        f27800M = C14261e.m22a(str2, "v1/payfront/guomi/getPublicKey");
        f27801N = C14261e.m22a(str2, "/v1/payfront/fido/openFidoNotice");
        f27802O = C14261e.m22a(str2, "/v1/payfront/fido/closeFido");
        f27803P = C14261e.m22a(str2, "/v1/payfront/fido/authFido");
        f27804Q = C14261e.m22a(str2, "/v2/cashierpay/phoneMsgCheck");
        f27805R = C14261e.m22a(str2, "/v2/cashierpay/sendPhoneMsg");
        f27806S = C14261e.m22a(str2, "/v2/cashierpay/tgtToRptId");
        f27807T = C14261e.m22a(str2, "/v2/cashierpay/queryNoticeList");
        f27808U = C14261e.m22a(str, "/v2/cashierpay/qPay");
        f27809V = C14261e.m22a(str, "/v2/cashierpay/fqSign");
        f27810W = C14261e.m22a(str, "/v2/cashierpay/sendMarket");
    }

    /* renamed from: a */
    public static String m21a() {
        EnvConfig envConfig = C10546a.C10576i.f20125a.f20052b;
        return envConfig == EnvConfig.SIT ? "https://test1.unicompayment.com" : envConfig == EnvConfig.UAT ? "https://www.unicompayment.com" : "https://mobile.unicompayment.com";
    }
}
