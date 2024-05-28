package com.unicom.pay.modules.verify.p357ui;

import android.content.Intent;
import android.os.Bundle;
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
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.common.callback.DataCallback;
import com.unicom.pay.modules.verify.bean.WPGmKeyBean;
import com.unicom.pay.modules.verify.bean.WPQOpenResultBean;
import com.unicom.pay.qpay.open.bean.WPQPayUserInfoBean;
import com.unicom.pay.widget.WPPassEditText;
import com.unicom.pay.widget.WPPayPwdResultView;
import java.util.HashMap;
import p393h.AbstractDialogInterface$OnCancelListenerC12000b;
import p395i.C12048b;
import p400k0.C12270a;
import p470p0.C13654q;
import p470p0.C13663u;
import p473r.C13707b;
import p478t.InterfaceC14157b;
import p480u.C14190b;
import p480u.C14193d;
import p480u.C14200g;
import p481v.C14236b;
import p481v.C14237c;
import p482w.C14255c;
import p482w.C14262f;

@NBSInstrumented
/* renamed from: com.unicom.pay.modules.verify.ui.WPValidatePayPassActivity */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPValidatePayPassActivity extends AbstractDialogInterface$OnCancelListenerC12000b<C14200g> implements InterfaceC14157b {

    /* renamed from: u */
    public static int f20173u;
    public NBSTraceUnit _nbs_trace;

    /* renamed from: l */
    public WPPassEditText f20174l;

    /* renamed from: m */
    public TextView f20175m;

    /* renamed from: n */
    public TextView f20176n;

    /* renamed from: o */
    public ImageView f20177o;

    /* renamed from: p */
    public LinearLayout f20178p;

    /* renamed from: q */
    public String f20179q;

    /* renamed from: r */
    public String f20180r = "";

    /* renamed from: s */
    public String f20181s;

    /* renamed from: t */
    public String f20182t;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.modules.verify.ui.WPValidatePayPassActivity$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10611a implements C12048b.InterfaceC12050b {
        @Override // p395i.C12048b.InterfaceC12050b
        /* renamed from: a */
        public final void mo1801a() {
        }
    }

    @NBSInstrumented
    /* renamed from: com.unicom.pay.modules.verify.ui.WPValidatePayPassActivity$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10612b implements C12048b.InterfaceC12052d {
        public C10612b() {
        }

        @Override // p395i.C12048b.InterfaceC12052d
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            WPValidatePayPassActivity wPValidatePayPassActivity = WPValidatePayPassActivity.this;
            int i = WPValidatePayPassActivity.f20173u;
            P p = wPValidatePayPassActivity.f24311a;
            if (p != 0) {
                C13654q.m172a(p, "", new C14237c(wPValidatePayPassActivity));
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, p393h.InterfaceC12016h
    /* renamed from: D */
    public final String mo56D() {
        return "FIDO_OPEN".equals(this.f20179q) ? "身份验证-支付密码全页面" : "-";
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, p393h.InterfaceC12016h
    /* renamed from: O */
    public final String mo53O() {
        return "FIDO_OPEN".equals(this.f20179q) ? "qp020" : "-";
    }

    @Override // p478t.InterfaceC14157b
    /* renamed from: P */
    public final void mo110P() {
        finish();
    }

    @Override // p478t.InterfaceC14157b
    /* renamed from: a */
    public final void mo109a(WPQOpenResultBean wPQOpenResultBean) {
        if (wPQOpenResultBean == null) {
            return;
        }
        String phoneNo = wPQOpenResultBean.getPhoneNo();
        String str = this.f20179q;
        String signTokenId = wPQOpenResultBean.getSignTokenId();
        String str2 = this.f20181s;
        String str3 = this.f20182t;
        Bundle bundle = new Bundle();
        bundle.putString("mobileNo", phoneNo);
        bundle.putString("bizCode", str);
        bundle.putString(WPQPayUserInfoBean.QPAY_COLUMN_KEY, signTokenId);
        bundle.putString("refer", str2);
        bundle.putString("json", str3);
        m2008a(bundle, WPVerifyCodeActivity.class);
        finish();
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: a0 */
    public final C14200g mo2002a0() {
        return new C14200g();
    }

    @Override // p478t.InterfaceC14157b
    /* renamed from: b */
    public final void mo105b(String str) {
        WPPassEditText wPPassEditText = this.f20174l;
        if (wPPassEditText != null) {
            wPPassEditText.clear();
        }
        m2006a(str, getResources().getString(C10531R.string.wp_comm_cancel), new C10611a(), getResources().getString(C10531R.string.wp_union_pay_find_pwd), new C10612b());
    }

    @Override // p478t.InterfaceC14157b
    /* renamed from: c */
    public final void mo104c(String str) {
        WPPassEditText wPPassEditText = this.f20174l;
        if (wPPassEditText != null) {
            if (wPPassEditText != null) {
                wPPassEditText.clear();
            }
            TextView textView = this.f20176n;
            if (textView != null) {
                textView.setText(str);
            }
            WPPassEditText wPPassEditText2 = this.f20174l;
            if (wPPassEditText2 != null) {
                wPPassEditText2.StartPassGuardKeyBoard();
            }
        }
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: e0 */
    public final int mo1998e0() {
        return C10531R.C10535layout.wp_up_validate_pay_pass;
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: f0 */
    public final void mo1997f0() {
        Intent intent = getIntent();
        if (intent != null) {
            this.f20179q = intent.getStringExtra("bizCode");
            this.f20180r = intent.hasExtra("authType") ? intent.getStringExtra("authType") : "";
            this.f20181s = intent.hasExtra("url") ? intent.getStringExtra("url") : "";
            this.f20182t = intent.hasExtra("json") ? intent.getStringExtra("json") : "";
        }
        this.f20174l.setVisibility(0);
        this.f20174l.initPassGuardKeyBoard();
        this.f20174l.setOnTextChangedListener(new C14236b(this));
    }

    @Override // android.app.Activity
    public final void finish() {
        this.f20174l.StopPassGuardKeyBoard();
        super.finish();
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: g0 */
    public final void mo1996g0() {
        m1993j(getResources().getString("FIDO_OPEN".equals(this.f20179q) ? C10531R.string.up_fido_pwd_title : C10531R.string.wp_up_pay_title));
        ImageView imageView = (ImageView) findViewById(C10531R.C10534id.wapay_header_arrow);
        this.f20174l = (WPPassEditText) findViewById(C10531R.C10534id.wopay_account_validate_pay_pass_pw);
        TextView textView = (TextView) findViewById(C10531R.C10534id.wopay_account_validate_pay_pass_other_tv);
        this.f20175m = textView;
        textView.setOnClickListener(this);
        this.f20176n = (TextView) findViewById(C10531R.C10534id.wopay_account_validate_pay_pass_auxiliary_tip_tv);
        TextView textView2 = (TextView) findViewById(C10531R.C10534id.wopay_account_validate_pay_pass_title);
        TextView textView3 = (TextView) findViewById(C10531R.C10534id.wopay_account_validate_pay_pass_tip_tv);
        WPPayPwdResultView wPPayPwdResultView = (WPPayPwdResultView) findViewById(C10531R.C10534id.wopay_pop_pay_result);
        this.f20177o = (ImageView) findViewById(C10531R.C10534id.wopay_qpay_loading_circle_iv);
        this.f20178p = (LinearLayout) findViewById(C10531R.C10534id.wopay_qpay_load_ll);
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        if (m2001b0()) {
            NBSActionInstrumentation.onClickEventExit();
        } else if (view.getId() == C10531R.C10534id.wapay_header_arrow) {
            m1991k("点击-密码页-返回箭头");
            finish();
            NBSActionInstrumentation.onClickEventExit();
        } else {
            if (view.getId() == C10531R.C10534id.wopay_account_validate_pay_pass_other_tv) {
                WPPassEditText wPPassEditText = this.f20174l;
                if (wPPassEditText != null) {
                    wPPassEditText.clear();
                }
                WPPassEditText wPPassEditText2 = this.f20174l;
                if (wPPassEditText2 != null) {
                    wPPassEditText2.StopPassGuardKeyBoard();
                }
                P p = this.f24311a;
                if (p == 0) {
                    NBSActionInstrumentation.onClickEventExit();
                    return;
                }
                if (p != 0) {
                    C13654q.m172a(p, "", new C14237c(this));
                }
                m1991k("点击-密码页-忘记密码");
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 400);
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        ImageView imageView = this.f20177o;
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
        return "FIDO_OPEN".equals(this.f20179q) ? "98U01170qp020" : "-";
    }

    @Override // p478t.InterfaceC14157b
    /* renamed from: t */
    public final void mo103t() {
        finish();
    }

    @Override // p478t.InterfaceC14157b
    /* renamed from: a */
    public final void mo108a(String str) {
        C12270a.C12272b.f24894a.m1906A();
        mo1790i(str);
        finish();
    }

    @Override // p478t.InterfaceC14157b
    /* renamed from: b */
    public final void mo107b(WPGmKeyBean wPGmKeyBean) {
        if (wPGmKeyBean == null) {
            return;
        }
        this.f20174l.setEccKey(wPGmKeyBean.getSm2RealKey());
        this.f20174l.setCipherKey(wPGmKeyBean.getCipherKey());
        String sM2SM4CipherText = this.f20174l.getSM2SM4CipherText();
        this.f20174l.StopPassGuardKeyBoard();
        if ("QPAY_PAY".equals(this.f20179q)) {
            C14200g c14200g = (C14200g) this.f24311a;
            String keyboardToken = wPGmKeyBean.getKeyboardToken();
            c14200g.getClass();
            HashMap hashMap = new HashMap();
            hashMap.put("cdkey", "");
            hashMap.put("signScene", "QPAY_TOKEN");
            hashMap.put("payPwd", sM2SM4CipherText);
            hashMap.put("signTokenId", "");
            hashMap.put("verifyCode", "");
            hashMap.put("keyboardToken", keyboardToken);
            hashMap.put("userClientUid", C10546a.C10576i.f20125a.f20059i);
            c14200g.m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27816f, hashMap, false, new C14190b(c14200g, c14200g, sM2SM4CipherText)));
        } else if ("FIDO_OPEN".equals(this.f20179q)) {
            C14200g c14200g2 = (C14200g) this.f24311a;
            String str = this.f20180r;
            String keyboardToken2 = wPGmKeyBean.getKeyboardToken();
            c14200g2.getClass();
            WPResult wPResult = new WPResult();
            DataCallback dataCallback = C10546a.C10576i.f20125a.f20064n;
            if (dataCallback == null) {
                return;
            }
            HashMap m126a = C13707b.m126a("authorType", str, "keyboardToken", keyboardToken2);
            m126a.put("deviceId", C10546a.C10576i.f20125a.f20061k);
            m126a.put("payPwd", sM2SM4CipherText);
            C14255c.C14256a.f27777a.m29a(C14262f.f27799L, m126a, false, new C14193d(c14200g2, c14200g2, this, str, dataCallback, wPResult));
        }
    }

    @Override // p478t.InterfaceC14157b
    /* renamed from: b */
    public final void mo106b(WPQOpenResultBean wPQOpenResultBean) {
        LinearLayout linearLayout;
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
            if (this.f20177o != null && (linearLayout = this.f20178p) != null) {
                linearLayout.setVisibility(0);
                Animation loadAnimation = AnimationUtils.loadAnimation(this, C10531R.anim.wp_loading_rotation);
                loadAnimation.setInterpolator(new LinearInterpolator());
                this.f20177o.setAnimation(loadAnimation);
                this.f20177o.startAnimation(loadAnimation);
                TextView textView = this.f20175m;
                if (textView != null) {
                    textView.setEnabled(false);
                }
                WPPassEditText wPPassEditText = this.f20174l;
                if (wPPassEditText != null) {
                    wPPassEditText.setEnabled(false);
                }
            }
            C12270a.C12272b.f24894a.m1904a(this, this.f20182t, this.f20181s);
            return;
        }
        C12270a.C12272b.f24894a.m1906A();
        mo1790i(wPQOpenResultBean.getSignResultMsg());
        finish();
    }
}
