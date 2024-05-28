package com.unicom.pay.normal.bank.p358ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.p083v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.unicom.pay.C10531R;
import com.unicom.pay.modules.verify.bean.WPGmKeyBean;
import com.unicom.pay.normal.order.bean.WPFqSignResultBean;
import com.unicom.pay.normal.order.bean.WPPayBeforeBean;
import com.unicom.pay.qpay.open.bean.WPQPayUserInfoBean;
import com.unicom.pay.widget.WPMyEditText;
import java.util.HashMap;
import p393h.AbstractDialogInterface$OnCancelListenerC12000b;
import p395i.C12048b;
import p470p0.C13638c;
import p470p0.C13640e;
import p470p0.C13647j;
import p482w.C14255c;
import p482w.C14262f;
import p483x.InterfaceC14269b;
import p484y.C14274a;
import p484y.C14276b;
import p484y.C14278c;
import p484y.C14280d;
import p485z.C14286b;
import p485z.CountDownTimerC14285a;

@NBSInstrumented
/* renamed from: com.unicom.pay.normal.bank.ui.WPAuthCreditBankActivity */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPAuthCreditBankActivity extends AbstractDialogInterface$OnCancelListenerC12000b<C14280d> implements InterfaceC14269b {

    /* renamed from: A */
    public String f20199A;

    /* renamed from: B */
    public String f20200B;

    /* renamed from: C */
    public String f20201C;

    /* renamed from: D */
    public boolean f20202D = false;
    public NBSTraceUnit _nbs_trace;

    /* renamed from: l */
    public ImageView f20203l;

    /* renamed from: m */
    public TextView f20204m;

    /* renamed from: n */
    public WPMyEditText f20205n;

    /* renamed from: o */
    public WPMyEditText f20206o;

    /* renamed from: p */
    public WPMyEditText f20207p;

    /* renamed from: q */
    public TextView f20208q;

    /* renamed from: r */
    public TextView f20209r;

    /* renamed from: s */
    public C14286b f20210s;

    /* renamed from: t */
    public C14286b f20211t;

    /* renamed from: u */
    public String f20212u;

    /* renamed from: v */
    public String f20213v;

    /* renamed from: w */
    public String f20214w;

    /* renamed from: x */
    public String f20215x;

    /* renamed from: y */
    public WPPayBeforeBean f20216y;

    /* renamed from: z */
    public CountDownTimerC14285a f20217z;

    /* renamed from: com.unicom.pay.normal.bank.ui.WPAuthCreditBankActivity$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10613a implements TextWatcher {
        public C10613a() {
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
            String obj;
            if (WPAuthCreditBankActivity.this.f20205n.getText() != null && !TextUtils.isEmpty(WPAuthCreditBankActivity.this.f20205n.getText().toString()) && WPAuthCreditBankActivity.this.f20206o.getText() != null && !TextUtils.isEmpty(WPAuthCreditBankActivity.this.f20206o.getText().toString()) && WPAuthCreditBankActivity.this.f20207p.getText() != null && !TextUtils.isEmpty(WPAuthCreditBankActivity.this.f20207p.getText().toString())) {
                WPAuthCreditBankActivity wPAuthCreditBankActivity = WPAuthCreditBankActivity.this;
                if (wPAuthCreditBankActivity.f20202D) {
                    wPAuthCreditBankActivity.f20209r.setEnabled(true);
                    obj = WPAuthCreditBankActivity.this.f20205n.getText().toString();
                    if (TextUtils.isEmpty(obj) && obj.length() == 4) {
                        WPAuthCreditBankActivity.this.m1991k("六要素页-输入完有效期");
                        return;
                    }
                }
            }
            WPAuthCreditBankActivity.this.f20209r.setEnabled(false);
            obj = WPAuthCreditBankActivity.this.f20205n.getText().toString();
            if (TextUtils.isEmpty(obj)) {
            }
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    /* renamed from: com.unicom.pay.normal.bank.ui.WPAuthCreditBankActivity$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10614b implements TextWatcher {
        public C10614b() {
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
            String obj;
            if (WPAuthCreditBankActivity.this.f20205n.getText() != null && !TextUtils.isEmpty(WPAuthCreditBankActivity.this.f20205n.getText().toString()) && WPAuthCreditBankActivity.this.f20206o.getText() != null && !TextUtils.isEmpty(WPAuthCreditBankActivity.this.f20206o.getText().toString()) && WPAuthCreditBankActivity.this.f20207p.getText() != null && !TextUtils.isEmpty(WPAuthCreditBankActivity.this.f20207p.getText().toString())) {
                WPAuthCreditBankActivity wPAuthCreditBankActivity = WPAuthCreditBankActivity.this;
                if (wPAuthCreditBankActivity.f20202D) {
                    wPAuthCreditBankActivity.f20209r.setEnabled(true);
                    obj = WPAuthCreditBankActivity.this.f20206o.getText().toString();
                    if (TextUtils.isEmpty(obj) && obj.length() == 3) {
                        WPAuthCreditBankActivity.this.m1991k("六要素页-输入完安全码");
                        return;
                    }
                }
            }
            WPAuthCreditBankActivity.this.f20209r.setEnabled(false);
            obj = WPAuthCreditBankActivity.this.f20206o.getText().toString();
            if (TextUtils.isEmpty(obj)) {
            }
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    /* renamed from: com.unicom.pay.normal.bank.ui.WPAuthCreditBankActivity$c */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10615c implements TextWatcher {
        public C10615c() {
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
            String obj;
            if (WPAuthCreditBankActivity.this.f20205n.getText() != null && !TextUtils.isEmpty(WPAuthCreditBankActivity.this.f20205n.getText().toString()) && WPAuthCreditBankActivity.this.f20206o.getText() != null && !TextUtils.isEmpty(WPAuthCreditBankActivity.this.f20206o.getText().toString()) && WPAuthCreditBankActivity.this.f20207p.getText() != null && !TextUtils.isEmpty(WPAuthCreditBankActivity.this.f20207p.getText().toString())) {
                WPAuthCreditBankActivity wPAuthCreditBankActivity = WPAuthCreditBankActivity.this;
                if (wPAuthCreditBankActivity.f20202D) {
                    wPAuthCreditBankActivity.f20209r.setEnabled(true);
                    obj = WPAuthCreditBankActivity.this.f20207p.getText().toString();
                    if (TextUtils.isEmpty(obj) && obj.length() == 6) {
                        WPAuthCreditBankActivity.this.m1991k("六要素页-输入完6位短信");
                        return;
                    }
                }
            }
            WPAuthCreditBankActivity.this.f20209r.setEnabled(false);
            obj = WPAuthCreditBankActivity.this.f20207p.getText().toString();
            if (TextUtils.isEmpty(obj)) {
            }
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    /* renamed from: a */
    public static /* synthetic */ void m6152a(View view) {
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, p393h.InterfaceC12016h
    /* renamed from: D */
    public final String mo56D() {
        return "分期六要素签约页面";
    }

    @Override // p483x.InterfaceC14269b
    /* renamed from: N */
    public final void mo18N() {
        CountDownTimerC14285a countDownTimerC14285a = this.f20217z;
        if (countDownTimerC14285a != null) {
            countDownTimerC14285a.cancel();
            this.f20217z = null;
        }
        CountDownTimerC14285a countDownTimerC14285a2 = new CountDownTimerC14285a(this);
        this.f20217z = countDownTimerC14285a2;
        countDownTimerC14285a2.start();
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, p393h.InterfaceC12016h
    /* renamed from: O */
    public final String mo53O() {
        return "wp730";
    }

    @Override // p483x.InterfaceC14269b
    /* renamed from: a */
    public final void mo17a(WPFqSignResultBean wPFqSignResultBean) {
        if (wPFqSignResultBean == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("UUID", wPFqSignResultBean.getUuid());
        intent.putExtra("FAP", wPFqSignResultBean.getFapAgrNo());
        intent.putExtra("expand", this.f20214w);
        setResult(-1, intent);
        finish();
    }

    @Override // p483x.InterfaceC14269b
    /* renamed from: a */
    public final void mo16a(String str) {
        m2005a(str, getResources().getString(C10531R.string.wp_comm_confirm), new C12048b.InterfaceC12052d() { // from class: com.unicom.pay.normal.bank.ui.-$$Lambda$FgPAT9B_rnO_iN60m3HH-eVB38U
            @Override // p395i.C12048b.InterfaceC12052d
            public final void onClick(View view) {
                WPAuthCreditBankActivity.m6152a(view);
            }
        });
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: a0 */
    public final C14280d mo2002a0() {
        return new C14280d();
    }

    @Override // p483x.InterfaceC14269b
    /* renamed from: b */
    public final void mo15b(WPGmKeyBean wPGmKeyBean) {
        String sm2key = wPGmKeyBean.getSm2key();
        C13640e c13640e = C13640e.C13641a.f27486a;
        String m184a = c13640e.m184a(sm2key, C13638c.m187a(this.f20199A));
        String m184a2 = c13640e.m184a(sm2key, C13638c.m187a(this.f20200B));
        String m184a3 = c13640e.m184a(sm2key, C13638c.m187a(this.f20201C));
        C14280d c14280d = (C14280d) this.f24311a;
        String fapAgrNo = this.f20216y.getFapAgrNo();
        String fundChnCode = this.f20216y.getFundChnCode();
        String str = this.f20215x;
        String str2 = this.f20214w;
        c14280d.getClass();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("signType", "2");
        hashMap.put("fapAgrNo", fapAgrNo);
        hashMap.put("fundChnCode", fundChnCode);
        hashMap.put("tradeOrderNo", str);
        hashMap.put("cvv2", m184a);
        hashMap.put("expireDate", m184a2);
        hashMap.put("smsCode", m184a3);
        hashMap.put("toolExpand", str2);
        c14280d.m1797a(C14255c.C14256a.f27777a.m30a(C14262f.f27809V, hashMap, new C14276b(c14280d, c14280d)));
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: e0 */
    public final int mo1998e0() {
        return C10531R.C10535layout.up_auth_credit_activity;
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: f0 */
    public final void mo1997f0() {
        int i = C14286b.f27841k;
        Bundle bundle = new Bundle();
        C14286b c14286b = new C14286b();
        bundle.putString("type", "1");
        c14286b.setArguments(bundle);
        this.f20210s = c14286b;
        Bundle bundle2 = new Bundle();
        C14286b c14286b2 = new C14286b();
        bundle2.putString("type", "2");
        c14286b2.setArguments(bundle2);
        this.f20211t = c14286b2;
        Intent intent = getIntent();
        if (intent != null) {
            this.f20212u = intent.getStringExtra("logoUrl");
            this.f20213v = intent.getStringExtra("bankName");
            this.f20214w = intent.getStringExtra("expand");
            this.f20215x = intent.getStringExtra("tradeNo");
            this.f20216y = (WPPayBeforeBean) intent.getParcelableExtra(WPQPayUserInfoBean.QPAY_COLUMN_KEY);
        }
        C13647j.m178a(this, this.f20212u, this.f20203l, C10531R.C10533drawable.wp_bank_default);
        if (TextUtils.isEmpty(this.f20213v)) {
            return;
        }
        this.f20204m.setText(this.f20213v);
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: g0 */
    public final void mo1996g0() {
        m1993j(getResources().getString(C10531R.string.wp_up_pay_title));
        this.f20203l = (ImageView) findViewById(C10531R.C10534id.up_bank_logo_iv);
        this.f20204m = (TextView) findViewById(C10531R.C10534id.up_bank_name_tv);
        this.f20205n = (WPMyEditText) findViewById(C10531R.C10534id.wopay_up_expire_time_edt);
        ((ImageView) findViewById(C10531R.C10534id.wopay_up_expire_time_tips_iv)).setOnClickListener(this);
        this.f20206o = (WPMyEditText) findViewById(C10531R.C10534id.wopay_up_security_code_edt);
        ((ImageView) findViewById(C10531R.C10534id.wopay_up_security_code_tips_iv)).setOnClickListener(this);
        TextView textView = (TextView) findViewById(C10531R.C10534id.wopay_pay_result_confirm_btn);
        this.f20209r = textView;
        textView.setOnClickListener(this);
        this.f20207p = (WPMyEditText) findViewById(C10531R.C10534id.wopay_up_input_code_edt);
        TextView textView2 = (TextView) findViewById(C10531R.C10534id.wopay_up_get_code_btn);
        this.f20208q = textView2;
        textView2.setOnClickListener(this);
        this.f20205n.addTextChangedListener(new C10613a());
        this.f20206o.addTextChangedListener(new C10614b());
        this.f20207p.addTextChangedListener(new C10615c());
    }

    @Override // p483x.InterfaceC14269b
    /* renamed from: n */
    public final void mo14n() {
        this.f20207p.setText("");
        this.f20208q.setEnabled(true);
        this.f20208q.setTextColor(getResources().getColor(C10531R.C10532color.wp_protocol_color));
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.view.View.OnClickListener
    public void onClick(View view) {
        boolean z;
        C14286b c14286b;
        FragmentManager supportFragmentManager;
        String str;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        if (m2001b0()) {
            NBSActionInstrumentation.onClickEventExit();
        } else if (view.getId() == C10531R.C10534id.wapay_header_arrow) {
            finish();
            NBSActionInstrumentation.onClickEventExit();
        } else {
            if (view.getId() == C10531R.C10534id.wopay_up_expire_time_tips_iv) {
                c14286b = this.f20210s;
                supportFragmentManager = getSupportFragmentManager();
                str = "mTimeDialog";
            } else if (view.getId() != C10531R.C10534id.wopay_up_security_code_tips_iv) {
                boolean z2 = true;
                if (view.getId() == C10531R.C10534id.wopay_pay_result_confirm_btn) {
                    String obj = this.f20205n.getText().toString();
                    this.f20200B = obj;
                    if (TextUtils.isEmpty(obj)) {
                        mo1790i(getResources().getString(C10531R.string.up_auth_credit_time_empty_error));
                        z = false;
                    } else if (this.f20200B.length() < 4) {
                        mo1790i(getResources().getString(C10531R.string.up_auth_credit_time_length_error));
                        z = false;
                    } else {
                        z = true;
                    }
                    if (z) {
                        String obj2 = this.f20206o.getText().toString();
                        this.f20199A = obj2;
                        if (TextUtils.isEmpty(obj2)) {
                            mo1790i(getResources().getString(C10531R.string.up_auth_credit_code_empty_error));
                            z2 = false;
                        } else if (this.f20199A.length() < 3) {
                            mo1790i(getResources().getString(C10531R.string.up_auth_credit_code_length_error));
                            z2 = false;
                        }
                        if (z2) {
                            if (!this.f20202D) {
                                mo1790i("请发送验证码");
                                NBSActionInstrumentation.onClickEventExit();
                                return;
                            }
                            String obj3 = this.f20207p.getText().toString();
                            this.f20201C = obj3;
                            if (!TextUtils.isEmpty(obj3) && this.f20201C.length() >= 6) {
                                C14280d c14280d = (C14280d) this.f24311a;
                                String str2 = this.f20214w;
                                String str3 = this.f20215x;
                                c14280d.getClass();
                                HashMap hashMap = new HashMap();
                                hashMap.put("expand", str2);
                                hashMap.put("tradeOrderNo", str3);
                                c14280d.m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27789B, hashMap, false, new C14278c(c14280d, c14280d)));
                            } else {
                                mo1790i(getResources().getString(C10531R.string.up_auth_credit_sms_length_error));
                                NBSActionInstrumentation.onClickEventExit();
                                return;
                            }
                        }
                    }
                    m1991k("点击-六要素页-确认支付");
                } else if (view.getId() == C10531R.C10534id.wopay_up_get_code_btn) {
                    this.f20202D = true;
                    if (this.f20205n.getText() == null || TextUtils.isEmpty(this.f20205n.getText().toString()) || this.f20206o.getText() == null || TextUtils.isEmpty(this.f20206o.getText().toString()) || this.f20207p.getText() == null || TextUtils.isEmpty(this.f20207p.getText().toString()) || !this.f20202D) {
                        this.f20209r.setEnabled(false);
                    } else {
                        this.f20209r.setEnabled(true);
                    }
                    C14280d c14280d2 = (C14280d) this.f24311a;
                    String str4 = this.f20214w;
                    String str5 = this.f20215x;
                    String fundChnCode = this.f20216y.getFundChnCode();
                    c14280d2.getClass();
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put("signType", "2");
                    hashMap2.put("toolExpand", str4);
                    hashMap2.put("tradeOrderNo", str5);
                    hashMap2.put("fundChnCode", fundChnCode);
                    c14280d2.m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27796I, hashMap2, false, new C14274a(c14280d2, c14280d2)));
                }
                NBSActionInstrumentation.onClickEventExit();
            } else {
                c14286b = this.f20211t;
                supportFragmentManager = getSupportFragmentManager();
                str = "mCvv2Dialog";
            }
            c14286b.show(supportFragmentManager, str);
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 402);
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
        return "98U01170wp730";
    }
}
