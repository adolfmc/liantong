package p400k0;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import cn.microdone.txcrypto.C1731txcrypto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.C10546a;
import com.unicom.pay.UnicomPaySDK;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.common.callback.DataCallback;
import com.unicom.pay.modules.result.bean.WPCheckSignResult;
import com.unicom.pay.modules.result.p356ui.WPPayResultActivity;
import com.unicom.pay.modules.verify.p357ui.WPValidatePayPassActivity;
import com.unicom.pay.modules.verify.p357ui.WPVerifyCodeActivity;
import com.unicom.pay.normal.order.bean.WPDefaultOrderInfoBean;
import com.unicom.pay.normal.order.p359ui.WPOrderActivity;
import com.unicom.pay.qpay.open.bean.WPQPayUserInfoBean;
import com.unicom.pay.utils.buried.WPTrendsEventsUtils;
import java.lang.reflect.Type;
import p396i0.InterfaceC12055b;
import p398j0.C12231a;
import p470p0.C13636a;
import p470p0.C13648k;
import p470p0.C13663u;
import p472q0.C13692n;

@NBSInstrumented
/* renamed from: k0.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12270a implements InterfaceC12055b {

    /* renamed from: a */
    public Context f24887a;

    /* renamed from: b */
    public String f24888b;

    /* renamed from: c */
    public String f24889c;

    /* renamed from: d */
    public String f24890d;

    /* renamed from: e */
    public WPDefaultOrderInfoBean f24891e;

    /* renamed from: f */
    public C12231a f24892f;

    /* renamed from: g */
    public DataCallback f24893g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: k0.a$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C12271a extends TypeToken<WPDefaultOrderInfoBean> {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: k0.a$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C12272b {

        /* renamed from: a */
        public static C12270a f24894a = new C12270a();
    }

    public C12270a() {
        C12231a m1894p = m1894p();
        this.f24892f = m1894p;
        m1894p.m1796a((C12231a) this);
    }

    /* renamed from: A */
    public final void m1906A() {
        WPDefaultOrderInfoBean wPDefaultOrderInfoBean;
        if (TextUtils.isEmpty(this.f24890d) || TextUtils.isEmpty(this.f24889c) || (wPDefaultOrderInfoBean = this.f24891e) == null) {
            return;
        }
        String str = this.f24890d;
        String str2 = this.f24889c;
        int i = WPOrderActivity.f20256N0;
        Bundle bundle = new Bundle();
        bundle.putString("refererUrl", str);
        bundle.putString("orderJson", str2);
        bundle.putParcelable("defaultOrderInfo", wPDefaultOrderInfoBean);
        m1903a(bundle, WPOrderActivity.class);
        C13636a.m190a();
    }

    @Override // p411o.InterfaceC12376b
    /* renamed from: I */
    public final void mo1793I() {
    }

    @Override // p396i0.InterfaceC12055b
    /* renamed from: W */
    public final void mo1905W() {
        m1906A();
        m1895m();
    }

    @Override // p411o.InterfaceC12376b
    /* renamed from: Y */
    public final void mo1792Y() {
    }

    /* renamed from: a */
    public final void m1904a(Context context, String str, String str2) {
        this.f24887a = context;
        this.f24889c = str;
        this.f24890d = str2;
        try {
            Type type = new C12271a().getType();
            Gson gson = C13648k.f27492a;
            WPDefaultOrderInfoBean wPDefaultOrderInfoBean = (WPDefaultOrderInfoBean) (!(gson instanceof Gson) ? gson.fromJson(str, type) : NBSGsonInstrumentation.fromJson(gson, str, type));
            this.f24891e = wPDefaultOrderInfoBean;
            if (wPDefaultOrderInfoBean != null && wPDefaultOrderInfoBean.getData() != null && "2".equals(this.f24891e.getData().getOrderType()) && !TextUtils.isEmpty(this.f24891e.getData().getH5_url())) {
                m1895m();
                UnicomPaySDK.getInstance().getNativeFunctionCallback().loadUrl(this.f24891e.getData().getH5_url(), true);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            m1895m();
        }
        if (this.f24891e == null) {
            this.f24891e = new WPDefaultOrderInfoBean();
            m1906A();
            m1895m();
            return;
        }
        try {
            if (TextUtils.isEmpty(this.f24888b)) {
                this.f24892f.m1922a(this.f24887a, "PAY_MER", this.f24891e.getData().getTradeOrderNo(), false);
            } else {
                m1893q();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            m1895m();
        }
    }

    /* renamed from: a */
    public final void m1903a(Bundle bundle, Class<? extends Activity> cls) {
        Context context = this.f24887a;
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, cls);
        intent.putExtras(bundle);
        this.f24887a.startActivity(intent);
    }

    @Override // p396i0.InterfaceC12055b
    /* renamed from: a */
    public final void mo1902a(String str, String str2, String str3) {
        WPDefaultOrderInfoBean wPDefaultOrderInfoBean;
        try {
            wPDefaultOrderInfoBean = this.f24891e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (wPDefaultOrderInfoBean == null) {
            return;
        }
        m1903a(WPVerifyCodeActivity.m6156a(str, wPDefaultOrderInfoBean.getData().getTradeOrderNo(), str2, str3, this.f24889c), WPVerifyCodeActivity.class);
        C13636a.m190a();
        m1895m();
    }

    @Override // p396i0.InterfaceC12055b
    /* renamed from: b */
    public final void mo1901b(WPResult<WPCheckSignResult> wPResult) {
        if (wPResult != null) {
            try {
                if (wPResult.getData() != null && this.f24891e != null) {
                    C10546a.C10576i.f20125a.f20056f = wPResult.getData().getPhoneNo();
                    WPCheckSignResult data = wPResult.getData();
                    if (!"1".equals(data.getStatus()) && !"0".equals(data.getStatus())) {
                        if (!"2".equals(data.getStatus()) && !"3".equals(data.getStatus())) {
                            if ("4".equals(data.getStatus())) {
                                this.f24888b = data.getUserNo();
                                m1893q();
                            }
                        }
                        m1903a(WPPayResultActivity.m6162l(this.f24891e.getData().getTradeOrderNo()), WPPayResultActivity.class);
                        m1895m();
                        C13636a.m190a();
                    }
                    C13692n.m135a(wPResult.getMsg());
                    m1906A();
                    m1895m();
                }
            } catch (Exception e) {
                e.printStackTrace();
                m1895m();
            }
        }
    }

    @Override // p396i0.InterfaceC12055b
    /* renamed from: e */
    public final void mo1900e() {
        WPDefaultOrderInfoBean wPDefaultOrderInfoBean;
        try {
            wPDefaultOrderInfoBean = this.f24891e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (wPDefaultOrderInfoBean == null) {
            return;
        }
        m1903a(WPPayResultActivity.m6162l(wPDefaultOrderInfoBean.getData().getTradeOrderNo()), WPPayResultActivity.class);
        C13636a.m190a();
        m1895m();
    }

    /* renamed from: f */
    public final void m1899f(String str) {
        try {
            if (this.f24891e == null || TextUtils.isEmpty(str)) {
                m1895m();
                return;
            }
            try {
                String str2 = this.f24888b;
                C1731txcrypto c1731txcrypto = new C1731txcrypto();
                this.f24892f.m1923a(this.f24887a, this.f24891e.getData().getTradeOrderNo(), "PAY_MER", c1731txcrypto.microdoneSM3HMAC(this.f24891e.getData().getTradeOrderNo() + "|" + this.f24891e.getData().getOrderAmount() + "|" + this.f24891e.getData().getTimeStamp().split("\\|\\|")[1], c1731txcrypto.microdoneSM4Decrypt(c1731txcrypto.microdoneSM4Encrypt("1c60f57043eec87893351db5bc953bae", "0000000000000000", str2, 0, 0, 0), "0000000000000000", str, 0, 0, 0), 0), this.f24890d, "", "", false);
            } catch (Exception unused) {
                m1906A();
                m1895m();
            }
        } catch (Exception e) {
            e.printStackTrace();
            m1895m();
        }
    }

    @Override // p396i0.InterfaceC12055b
    /* renamed from: h */
    public final void mo1898h() {
        try {
            String str = this.f24890d;
            String str2 = this.f24889c;
            int i = WPValidatePayPassActivity.f20173u;
            Bundle bundle = new Bundle();
            bundle.putString("bizCode", "QPAY_PAY");
            bundle.putString("url", str);
            bundle.putString("json", str2);
            m1903a(bundle, WPValidatePayPassActivity.class);
            WPTrendsEventsUtils.trendsPageButtonData("极速支付loading页", "98U01170qp018", "qp018", "无paytoken提示框");
            C13636a.m190a();
        } catch (Exception e) {
            e.printStackTrace();
        }
        m1895m();
    }

    @Override // p396i0.InterfaceC12055b
    /* renamed from: i */
    public final void mo1897i() {
        m1895m();
        C13636a.m190a();
    }

    @Override // p411o.InterfaceC12376b
    /* renamed from: i */
    public final void mo1790i(String str) {
        C13692n.m135a(str);
    }

    @Override // p396i0.InterfaceC12055b
    /* renamed from: j */
    public final void mo1896j() {
        try {
            if (this.f24891e != null) {
                m1906A();
                C13636a.m190a();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        m1895m();
    }

    @Override // p411o.InterfaceC12376b
    /* renamed from: l */
    public final Activity mo1789l() {
        return null;
    }

    /* renamed from: m */
    public final void m1895m() {
        if (this.f24893g == null) {
            return;
        }
        WPResult wPResult = new WPResult();
        wPResult.setCode("0000");
        wPResult.setMsg("");
        DataCallback dataCallback = this.f24893g;
        Gson gson = C13648k.f27492a;
        dataCallback.onResult(!(gson instanceof Gson) ? gson.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson, wPResult));
        this.f24893g = null;
    }

    /* renamed from: p */
    public final C12231a m1894p() {
        return new C12231a();
    }

    /* renamed from: q */
    public final void m1893q() {
        try {
            WPQPayUserInfoBean m164a = C13663u.C13664a.f27509a.m164a(this.f24888b);
            if (m164a == null) {
                C13692n.m135a(this.f24887a.getResources().getString(C10531R.string.wp_qpay_pay_loading_get_token));
                mo1898h();
                return;
            }
            String payToken = m164a.getPayToken();
            if (!TextUtils.isEmpty(payToken)) {
                m1899f(payToken);
                return;
            }
            C13692n.m135a(this.f24887a.getResources().getString(C10531R.string.wp_qpay_pay_loading_get_token));
            mo1898h();
        } catch (Exception e) {
            e.printStackTrace();
            m1895m();
        }
    }

    @Override // p411o.InterfaceC12376b
    /* renamed from: a */
    public final void mo1791a(int i) {
        try {
            C13692n.m135a(C13692n.f27554a.getResources().getText(i));
        } catch (Resources.NotFoundException unused) {
            C13692n.m135a(String.valueOf(i));
        }
    }
}
