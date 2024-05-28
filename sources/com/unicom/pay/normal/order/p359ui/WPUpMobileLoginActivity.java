package com.unicom.pay.normal.order.p359ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.unicom.pay.C10531R;
import com.unicom.pay.normal.order.bean.WPAgreementMsg;
import com.unicom.pay.widget.WPMyEditText;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import p089b0.InterfaceC1474h;
import p091c0.C1563p;
import p385d0.C11827m;
import p385d0.CountDownTimerC11826l;
import p393h.AbstractDialogInterface$OnCancelListenerC12000b;
import p470p0.C13659r;
import p470p0.C13662t;
import p477s0.InterfaceC14117b;

@NBSInstrumented
/* renamed from: com.unicom.pay.normal.order.ui.WPUpMobileLoginActivity */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPUpMobileLoginActivity extends AbstractDialogInterface$OnCancelListenerC12000b<C1563p> implements InterfaceC1474h, InterfaceC14117b {

    /* renamed from: A */
    public boolean f20350A;
    public NBSTraceUnit _nbs_trace;

    /* renamed from: l */
    public TextView f20351l;

    /* renamed from: m */
    public TextView f20352m;

    /* renamed from: n */
    public WPMyEditText f20353n;

    /* renamed from: o */
    public View f20354o;

    /* renamed from: p */
    public CheckBox f20355p;

    /* renamed from: q */
    public TextView f20356q;

    /* renamed from: r */
    public TextView f20357r;

    /* renamed from: s */
    public Button f20358s;

    /* renamed from: t */
    public WPMyEditText f20359t;

    /* renamed from: u */
    public LinearLayout f20360u;

    /* renamed from: v */
    public CountDownTimerC11826l f20361v;

    /* renamed from: w */
    public String f20362w;

    /* renamed from: x */
    public String f20363x;

    /* renamed from: y */
    public String f20364y;

    /* renamed from: z */
    public List<WPAgreementMsg> f20365z;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.normal.order.ui.WPUpMobileLoginActivity$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10676a implements TextWatcher {
        public C10676a() {
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            WPUpMobileLoginActivity wPUpMobileLoginActivity;
            String str;
            if (charSequence == null || charSequence.length() < 13) {
                return;
            }
            if ("GATEWAY9001".equals(WPUpMobileLoginActivity.this.f20362w)) {
                wPUpMobileLoginActivity = WPUpMobileLoginActivity.this;
                str = "输入完11位手机号";
            } else {
                wPUpMobileLoginActivity = WPUpMobileLoginActivity.this;
                str = "登录页-输入完11位手机号";
            }
            wPUpMobileLoginActivity.m1991k(str);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.normal.order.ui.WPUpMobileLoginActivity$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10677b implements TextWatcher {
        public C10677b() {
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            WPUpMobileLoginActivity wPUpMobileLoginActivity;
            String str;
            if (charSequence == null || charSequence.length() < 6) {
                return;
            }
            if ("GATEWAY9001".equals(WPUpMobileLoginActivity.this.f20362w)) {
                wPUpMobileLoginActivity = WPUpMobileLoginActivity.this;
                str = "风控页-输入完6位短信";
            } else {
                wPUpMobileLoginActivity = WPUpMobileLoginActivity.this;
                str = "登录页-输入完6位短信";
            }
            wPUpMobileLoginActivity.m1991k(str);
        }
    }

    /* renamed from: com.unicom.pay.normal.order.ui.WPUpMobileLoginActivity$c */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10678c implements CompoundButton.OnCheckedChangeListener {
        public C10678c() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            Button button;
            int i;
            WPUpMobileLoginActivity wPUpMobileLoginActivity;
            String str;
            WPUpMobileLoginActivity wPUpMobileLoginActivity2;
            String str2;
            Tracker.onCheckedChanged(compoundButton, z);
            WPUpMobileLoginActivity wPUpMobileLoginActivity3 = WPUpMobileLoginActivity.this;
            boolean z2 = wPUpMobileLoginActivity3.f20350A && TextUtils.isEmpty(wPUpMobileLoginActivity3.f20353n.getText().toString());
            boolean isEmpty = TextUtils.isEmpty(WPUpMobileLoginActivity.this.f20359t.getText().toString());
            if (z2 || isEmpty || !z) {
                button = WPUpMobileLoginActivity.this.f20358s;
                i = C10531R.C10533drawable.wp_confirm_uneable_bg;
            } else {
                button = WPUpMobileLoginActivity.this.f20358s;
                i = C10531R.C10533drawable.wp_confirm_pure_bg;
            }
            button.setBackgroundResource(i);
            if (z) {
                if ("GATEWAY9001".equals(WPUpMobileLoginActivity.this.f20362w)) {
                    wPUpMobileLoginActivity2 = WPUpMobileLoginActivity.this;
                    str2 = "勾选-风控页-协议";
                } else {
                    wPUpMobileLoginActivity2 = WPUpMobileLoginActivity.this;
                    str2 = "勾选-登录页-协议";
                }
                wPUpMobileLoginActivity2.m1991k(str2);
                return;
            }
            if ("GATEWAY9001".equals(WPUpMobileLoginActivity.this.f20362w)) {
                wPUpMobileLoginActivity = WPUpMobileLoginActivity.this;
                str = "取消勾选-风控页-协议";
            } else {
                wPUpMobileLoginActivity = WPUpMobileLoginActivity.this;
                str = "取消勾选-登录页-协议";
            }
            wPUpMobileLoginActivity.m1991k(str);
        }
    }

    /* renamed from: a */
    public static Bundle m6073a(String str, String str2, String str3, ArrayList<WPAgreementMsg> arrayList) {
        Bundle bundle = new Bundle();
        bundle.putString("orderNo", str);
        bundle.putString("authId", str2);
        bundle.putString("resultCode", str3);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        bundle.putParcelableArrayList("resultBody", arrayList);
        return bundle;
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, p393h.InterfaceC12016h
    /* renamed from: D */
    public final String mo56D() {
        return "GATEWAY9001".equals(this.f20362w) ? "风控页" : "短信验证页";
    }

    @Override // p089b0.InterfaceC1474h
    /* renamed from: M */
    public final void mo6075M() {
        CountDownTimerC11826l countDownTimerC11826l = this.f20361v;
        if (countDownTimerC11826l != null) {
            countDownTimerC11826l.cancel();
            this.f20361v = null;
        }
        CountDownTimerC11826l countDownTimerC11826l2 = new CountDownTimerC11826l(this);
        this.f20361v = countDownTimerC11826l2;
        countDownTimerC11826l2.start();
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, p393h.InterfaceC12016h
    /* renamed from: O */
    public final String mo53O() {
        return "GATEWAY9001".equals(this.f20362w) ? "wp400" : "wp500";
    }

    @Override // p089b0.InterfaceC1474h
    /* renamed from: X */
    public final void mo6074X() {
        setResult(-1);
        finish();
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: a0 */
    public final C1563p mo2002a0() {
        return new C1563p();
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: e0 */
    public final int mo1998e0() {
        return C10531R.C10535layout.wp_up_login_mobile_sms;
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: f0 */
    public final void mo1997f0() {
        this.f20362w = getIntent().getStringExtra("resultCode");
        this.f20365z = getIntent().getParcelableArrayListExtra("resultBody");
        this.f20364y = getIntent().getStringExtra("orderNo");
        this.f20363x = getIntent().getStringExtra("authId");
        if ("GATEWAY9001".equals(this.f20362w)) {
            this.f20350A = false;
            this.f20353n.setVisibility(8);
            this.f20354o.setVisibility(8);
            this.f20351l.setText(getString(C10531R.string.up_login_desc_code1));
            this.f20352m.setText(getString(C10531R.string.up_login_desc_code2));
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f20360u.getLayoutParams();
            layoutParams.topMargin = C13659r.m170a(50.0f);
            this.f20360u.setLayoutParams(layoutParams);
        } else {
            this.f20350A = true;
            this.f20353n.setVisibility(0);
            this.f20353n.setmTextChangedListener(this);
            this.f20353n.addTextChangedListener(new C10676a());
            this.f20353n.setRule(1);
            this.f20353n.setClearDrawableVisible(false);
        }
        List<WPAgreementMsg> list = this.f20365z;
        if (list != null) {
            TextView textView = this.f20356q;
            C13662t c13662t = new C13662t(getString(C10531R.string.up_login_agree_protocol));
            for (WPAgreementMsg wPAgreementMsg : list) {
                String name = wPAgreementMsg.getName();
                c13662t.m166a();
                c13662t.f27499a = name;
                c13662t.f27501c = getResources().getColor(C10531R.C10532color.up_hyperlink_or_protocol_text_color);
                c13662t.f27506h = new C11827m(this, wPAgreementMsg);
            }
            c13662t.m166a();
            textView.setText(c13662t.f27507i);
        }
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: g0 */
    public final void mo1996g0() {
        m1993j(getResources().getString(C10531R.string.up_wopay_login_title));
        this.f20351l = (TextView) findViewById(C10531R.C10534id.wopay_up_login_title_tv);
        this.f20352m = (TextView) findViewById(C10531R.C10534id.wopay_up_login_desc_tv);
        this.f20353n = (WPMyEditText) findViewById(C10531R.C10534id.wopay_up_login_mobile_edt);
        this.f20354o = findViewById(C10531R.C10534id.line1);
        this.f20360u = (LinearLayout) findViewById(C10531R.C10534id.wopay_up_login_input_code_ll);
        WPMyEditText wPMyEditText = (WPMyEditText) findViewById(C10531R.C10534id.wopay_up_login_input_code_edt);
        this.f20359t = wPMyEditText;
        wPMyEditText.setmTextChangedListener(this);
        this.f20359t.addTextChangedListener(new C10677b());
        TextView textView = (TextView) findViewById(C10531R.C10534id.wopay_up_login_get_code_btn);
        this.f20357r = textView;
        textView.setOnClickListener(this);
        LinearLayout linearLayout = (LinearLayout) findViewById(C10531R.C10534id.wopay_up_login_protocol_ll);
        CheckBox checkBox = (CheckBox) findViewById(C10531R.C10534id.wopay_up_login_protocol_cb);
        this.f20355p = checkBox;
        checkBox.setOnCheckedChangeListener(new C10678c());
        TextView textView2 = (TextView) findViewById(C10531R.C10534id.wopay_up_login_protocol_tv);
        this.f20356q = textView2;
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        Button button = (Button) findViewById(C10531R.C10534id.wopay_up_login_next_btn);
        this.f20358s = button;
        button.setOnClickListener(this);
        this.f20358s.setBackgroundResource(C10531R.C10533drawable.wp_confirm_uneable_bg);
    }

    /* renamed from: l0 */
    public final boolean m6072l0() {
        boolean isEmpty = TextUtils.isEmpty(this.f20359t.getText().toString());
        if (isEmpty) {
            mo1790i("请输入验证码");
        }
        return !isEmpty;
    }

    /* renamed from: m0 */
    public final boolean m6071m0() {
        String replace = this.f20353n.getText().toString().replace(" ", "");
        if (TextUtils.isEmpty(replace)) {
            mo1790i("请输入手机号");
            return false;
        }
        if (TextUtils.isEmpty(replace) ? false : Pattern.compile("^[1]\\d{10}$").matcher(replace).matches()) {
            return true;
        }
        mo1790i("请输入正确手机号");
        return false;
    }

    /* renamed from: n0 */
    public final boolean m6070n0() {
        boolean isChecked = this.f20355p.isChecked();
        if (!isChecked) {
            mo1790i("请先勾选协议");
        }
        return isChecked;
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public final void onBackPressed() {
        super.onBackPressed();
        m1991k("登录页-返回");
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        if (m2001b0()) {
            NBSActionInstrumentation.onClickEventExit();
        } else if (view.getId() == C10531R.C10534id.wapay_header_arrow) {
            finish();
            m1991k("登录页-返回");
            NBSActionInstrumentation.onClickEventExit();
        } else {
            if (view.getId() == C10531R.C10534id.wopay_up_login_get_code_btn) {
                m1991k("GATEWAY9001".equals(this.f20362w) ? "点击-风控页-获取验证码" : "登录页-获取验证码");
                if ("GATEWAY9001".equals(this.f20362w)) {
                    ((C1563p) this.f24311a).m22165a(this.f20363x, "", this.f20364y);
                } else if (m6071m0()) {
                    ((C1563p) this.f24311a).m22165a("", this.f20353n.getText().toString().replaceAll(" ", ""), this.f20364y);
                }
            } else if (view.getId() == C10531R.C10534id.wopay_up_login_next_btn) {
                m1991k("GATEWAY9001".equals(this.f20362w) ? "点击-风控页-登录" : "点击登录页-登录");
                if (m6072l0() && m6070n0()) {
                    String obj = this.f20359t.getText().toString();
                    if ("GATEWAY9001".equals(this.f20362w)) {
                        ((C1563p) this.f24311a).m22164a(this.f20363x, "", obj, this.f20364y);
                    } else if (m6071m0()) {
                        ((C1563p) this.f24311a).m22164a("", this.f20353n.getText().toString().replaceAll(" ", ""), obj, this.f20364y);
                    }
                }
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 405);
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
        return "GATEWAY9001".equals(this.f20362w) ? "98U01170wp400" : "98U01170wp500";
    }
}
