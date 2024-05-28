package com.unicom.pay.modules.verify.p357ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.unicom.pay.C10531R;
import com.unicom.pay.C10546a;
import com.unicom.pay.modules.result.p356ui.WPPayResultActivity;
import com.unicom.pay.modules.verify.bean.WPQOpenResultBean;
import com.unicom.pay.modules.verify.bean.WPQuickSmsBean;
import com.unicom.pay.qpay.open.bean.WPQPayUserInfoBean;
import com.unicom.pay.widget.WPVerifyCodeEditText;
import java.util.HashMap;
import p393h.AbstractDialogInterface$OnCancelListenerC12000b;
import p400k0.C12270a;
import p470p0.C13636a;
import p470p0.C13662t;
import p470p0.C13663u;
import p478t.InterfaceC14159d;
import p480u.C14220r;
import p480u.C14222s;
import p480u.C14224t;
import p480u.C14226u;
import p481v.CountDownTimerC14238d;
import p482w.C14255c;
import p482w.C14262f;

@NBSInstrumented
/* renamed from: com.unicom.pay.modules.verify.ui.WPVerifyCodeActivity */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPVerifyCodeActivity extends AbstractDialogInterface$OnCancelListenerC12000b<C14226u> implements InterfaceC14159d {
    public NBSTraceUnit _nbs_trace;

    /* renamed from: l */
    public TextView f20184l;

    /* renamed from: m */
    public TextView f20185m;

    /* renamed from: n */
    public WPVerifyCodeEditText f20186n;

    /* renamed from: o */
    public ImageView f20187o;

    /* renamed from: p */
    public LinearLayout f20188p;

    /* renamed from: w */
    public String f20195w;

    /* renamed from: y */
    public String f20197y;

    /* renamed from: z */
    public CountDownTimerC14238d f20198z;

    /* renamed from: q */
    public String f20189q = "";

    /* renamed from: r */
    public String f20190r = "";

    /* renamed from: s */
    public String f20191s = "";

    /* renamed from: t */
    public String f20192t = "";

    /* renamed from: u */
    public String f20193u = "";

    /* renamed from: v */
    public String f20194v = "";

    /* renamed from: x */
    public boolean f20196x = true;

    /* renamed from: a */
    public static Bundle m6156a(String str, String str2, String str3, String str4, String str5) {
        Bundle bundle = new Bundle();
        bundle.putString("mobileNo", str);
        bundle.putString("bizCode", "QPAY_PAY_SMS");
        bundle.putString("orderId", str2);
        bundle.putString("hmac", str3);
        bundle.putString("refer", str4);
        bundle.putString("json", str5);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l */
    public void m6155l(String str) {
        if (str.length() != 6 || !this.f20196x) {
            if (str.length() < 6) {
                this.f20196x = true;
                return;
            }
            return;
        }
        this.f20196x = false;
        if (TextUtils.isEmpty(this.f20195w)) {
            mo1790i(getResources().getString(C10531R.string.wp_sms_no_cdkey));
            return;
        }
        if ("QPAY_PAY".equals(this.f20190r)) {
            C14226u c14226u = (C14226u) this.f24311a;
            String str2 = this.f20195w;
            String str3 = this.f20194v;
            c14226u.getClass();
            HashMap hashMap = new HashMap();
            hashMap.put("cdkey", str2);
            hashMap.put("signScene", "QPAY_TOKEN");
            hashMap.put("payPwd", "");
            hashMap.put("signTokenId", str3);
            hashMap.put("verifyCode", str);
            hashMap.put("userClientUid", C10546a.C10576i.f20125a.f20059i);
            c14226u.m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27816f, hashMap, false, new C14222s(c14226u, c14226u)));
        } else if ("QPAY_PAY_SMS".equals(this.f20190r)) {
            m6153m0();
            C14226u c14226u2 = (C14226u) this.f24311a;
            String str4 = this.f20191s;
            String str5 = this.f20192t;
            String str6 = this.f20193u;
            String str7 = this.f20195w;
            c14226u2.getClass();
            HashMap hashMap2 = new HashMap();
            hashMap2.put("tradeOrderNo", str4);
            hashMap2.put("payScene", "PAY_MER");
            hashMap2.put("hmac", str5);
            hashMap2.put("qpayUrl", str6);
            hashMap2.put("cdkey", str7);
            hashMap2.put("verifyCode", str);
            hashMap2.put("userClientUid", C10546a.C10576i.f20125a.f20059i);
            C14255c.C14256a.f27777a.m29a(C14262f.f27821k, hashMap2, false, new C14224t(c14226u2, c14226u2));
        }
        m1991k("风控短验页面-输入完6位短信");
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, p393h.InterfaceC12016h
    /* renamed from: D */
    public final String mo56D() {
        return "触发风控短验页面";
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, p393h.InterfaceC12016h
    /* renamed from: O */
    public final String mo53O() {
        return "qp021";
    }

    @Override // p478t.InterfaceC14159d
    /* renamed from: a */
    public final void mo102a(WPQuickSmsBean wPQuickSmsBean) {
        this.f20195w = wPQuickSmsBean.getCdKey();
        CountDownTimerC14238d countDownTimerC14238d = this.f20198z;
        if (countDownTimerC14238d != null) {
            countDownTimerC14238d.cancel();
            this.f20198z = null;
        }
        CountDownTimerC14238d countDownTimerC14238d2 = new CountDownTimerC14238d(this);
        this.f20198z = countDownTimerC14238d2;
        countDownTimerC14238d2.start();
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: a0 */
    public final C14226u mo2002a0() {
        return new C14226u();
    }

    @Override // p478t.InterfaceC14159d
    /* renamed from: b */
    public final void mo101b(WPQOpenResultBean wPQOpenResultBean) {
        if (wPQOpenResultBean.isSignSuccess()) {
            C13663u c13663u = C10546a.C10576i.f20125a.f20053c;
            if (c13663u != null) {
                WPQPayUserInfoBean m164a = c13663u.m164a(wPQOpenResultBean.getUserNo());
                if (m164a == null) {
                    m164a = new WPQPayUserInfoBean();
                    m164a.setUserNo(wPQOpenResultBean.getUserNo());
                }
                m164a.setPayToken(wPQOpenResultBean.getPayToken());
                c13663u.m165a(m164a);
            }
            m6153m0();
            C12270a.C12272b.f24894a.m1904a(this, this.f20197y, this.f20193u);
            return;
        }
        C12270a.C12272b.f24894a.m1906A();
        mo1790i(wPQOpenResultBean.getSignResultMsg());
        finish();
    }

    @Override // p478t.InterfaceC14159d
    /* renamed from: e */
    public final void mo100e() {
        m2008a(WPPayResultActivity.m6162l(this.f20191s), WPPayResultActivity.class);
        C13636a.m190a();
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: e0 */
    public final int mo1998e0() {
        return C10531R.C10535layout.wp_up_verify_code_fragment;
    }

    @Override // p478t.InterfaceC14159d
    /* renamed from: f */
    public final void mo99f() {
        WPVerifyCodeEditText wPVerifyCodeEditText = this.f20186n;
        if (wPVerifyCodeEditText != null) {
            wPVerifyCodeEditText.setText("");
        }
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: f0 */
    public final void mo1997f0() {
        Intent intent = getIntent();
        if (intent != null) {
            this.f20189q = intent.hasExtra("mobileNo") ? intent.getStringExtra("mobileNo") : "";
            this.f20190r = intent.hasExtra("bizCode") ? intent.getStringExtra("bizCode") : "";
            this.f20191s = intent.hasExtra("orderId") ? intent.getStringExtra("orderId") : "";
            this.f20194v = intent.hasExtra(WPQPayUserInfoBean.QPAY_COLUMN_KEY) ? intent.getStringExtra(WPQPayUserInfoBean.QPAY_COLUMN_KEY) : "";
            this.f20192t = intent.hasExtra("hmac") ? intent.getStringExtra("hmac") : "";
            this.f20193u = intent.hasExtra("refer") ? intent.getStringExtra("refer") : "";
            if (intent.hasExtra("refer")) {
                intent.getStringExtra("refer");
            }
            this.f20197y = intent.hasExtra("json") ? intent.getStringExtra("json") : "";
        }
        if (TextUtils.isEmpty(this.f20189q)) {
            this.f20189q = "";
        }
        C13662t c13662t = new C13662t("已向号码 ");
        String str = this.f20189q;
        c13662t.m166a();
        c13662t.f27499a = str;
        c13662t.f27501c = getResources().getColor(C10531R.C10532color.wp_primary_text_color);
        c13662t.m166a();
        c13662t.f27499a = " 发送验证码";
        c13662t.m166a();
        this.f20184l.setText(c13662t.f27507i);
        m6154l0();
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: g0 */
    public final void mo1996g0() {
        m1993j(getString(C10531R.string.wp_up_pay_title));
        this.f20184l = (TextView) findViewById(C10531R.C10534id.wopay_verify_code_number_tv);
        this.f20185m = (TextView) findViewById(C10531R.C10534id.wopay_verify_code_get_verify_tv);
        this.f20186n = (WPVerifyCodeEditText) findViewById(C10531R.C10534id.wopay_verify_code_verify_edt);
        this.f20185m.setOnClickListener(this);
        this.f20186n.setSpaceWidth(22.0f / getResources().getDisplayMetrics().density);
        this.f20186n.setOnTextChangedListener(new WPVerifyCodeEditText.InterfaceC10719b() { // from class: com.unicom.pay.modules.verify.ui.-$$Lambda$WPVerifyCodeActivity$veHgOEH5D0I55OI3zNo5mzEaOn8
            @Override // com.unicom.pay.widget.WPVerifyCodeEditText.InterfaceC10719b
            /* renamed from: a */
            public final void mo70a(String str) {
                WPVerifyCodeActivity.this.m6155l(str);
            }
        });
        this.f20187o = (ImageView) findViewById(C10531R.C10534id.wopay_qpay_loading_circle_iv);
        this.f20188p = (LinearLayout) findViewById(C10531R.C10534id.wopay_qpay_load_ll);
    }

    @Override // p478t.InterfaceC14159d
    /* renamed from: i */
    public final void mo98i() {
        finish();
    }

    @Override // p478t.InterfaceC14159d
    /* renamed from: j */
    public final void mo97j() {
        C12270a.C12272b.f24894a.m1906A();
        C13636a.m190a();
    }

    @Override // p478t.InterfaceC14159d
    /* renamed from: k */
    public final void mo96k() {
        WPVerifyCodeEditText wPVerifyCodeEditText = this.f20186n;
        if (wPVerifyCodeEditText != null) {
            wPVerifyCodeEditText.setText("");
        }
        this.f20196x = true;
        TextView textView = this.f20185m;
        if (textView != null) {
            textView.setEnabled(true);
            this.f20185m.setTextColor(getResources().getColor(C10531R.C10532color.wp_protocol_color));
            this.f20185m.setText(C10531R.string.wp_comm_verify_reset);
        }
    }

    /* renamed from: l0 */
    public final void m6154l0() {
        if ("QPAY_PAY".equals(this.f20190r) || "QPAY_PAY_SMS".equals(this.f20190r)) {
            C14226u c14226u = (C14226u) this.f24311a;
            c14226u.getClass();
            c14226u.m1797a(C14255c.C14256a.f27777a.m30a(C14262f.f27817g, new HashMap<>(), new C14220r(c14226u, c14226u)));
        }
    }

    /* renamed from: m0 */
    public final void m6153m0() {
        LinearLayout linearLayout;
        if (this.f20187o == null || (linearLayout = this.f20188p) == null) {
            return;
        }
        linearLayout.setVisibility(0);
        Animation loadAnimation = AnimationUtils.loadAnimation(this, C10531R.anim.wp_loading_rotation);
        loadAnimation.setInterpolator(new LinearInterpolator());
        this.f20187o.setAnimation(loadAnimation);
        this.f20187o.startAnimation(loadAnimation);
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        if (m2001b0()) {
            NBSActionInstrumentation.onClickEventExit();
        } else if (view.getId() == C10531R.C10534id.wapay_header_arrow) {
            m1991k("点击-风控短验页面-返回箭头");
            finish();
            NBSActionInstrumentation.onClickEventExit();
        } else {
            if (view.getId() == C10531R.C10534id.wopay_verify_code_get_verify_tv) {
                m1991k("点击-风控短验页面-重新发送");
                m6154l0();
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 401);
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        ImageView imageView = this.f20187o;
        if (imageView != null) {
            imageView.clearAnimation();
        }
        super.onDestroy();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, p393h.InterfaceC12016h
    /* renamed from: p */
    public final String mo36p() {
        return "98U01170qp021";
    }

    @Override // p478t.InterfaceC14159d
    /* renamed from: s */
    public final void mo95s() {
        LinearLayout linearLayout;
        if (this.f20187o == null || (linearLayout = this.f20188p) == null) {
            return;
        }
        linearLayout.setVisibility(8);
        this.f20187o.clearAnimation();
    }
}
