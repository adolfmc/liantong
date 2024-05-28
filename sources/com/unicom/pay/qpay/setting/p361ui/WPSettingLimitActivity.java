package com.unicom.pay.qpay.setting.p361ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.unicom.pay.C10531R;
import com.unicom.pay.modules.verify.bean.WPGmKeyBean;
import com.unicom.pay.qpay.setting.bean.WPLimitBean;
import java.util.ArrayList;
import java.util.List;
import p393h.AbstractDialogInterface$OnCancelListenerC12000b;
import p395i.C12048b;
import p405l0.InterfaceC12298d;
import p407m0.C12319g;
import p409n0.C12364d;
import p409n0.C12365e;
import p412o0.C12386e;
import p470p0.C13654q;
import p481v.C14232a;

@NBSInstrumented
/* renamed from: com.unicom.pay.qpay.setting.ui.WPSettingLimitActivity */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPSettingLimitActivity extends AbstractDialogInterface$OnCancelListenerC12000b<C12319g> implements InterfaceC12298d, C12386e.InterfaceC12388b {

    /* renamed from: q */
    public static final /* synthetic */ int f20437q = 0;
    public NBSTraceUnit _nbs_trace;

    /* renamed from: l */
    public RecyclerView f20438l;

    /* renamed from: m */
    public List<WPLimitBean> f20439m;

    /* renamed from: n */
    public String f20440n;

    /* renamed from: o */
    public C12386e f20441o;

    /* renamed from: p */
    public C14232a f20442p;

    /* renamed from: com.unicom.pay.qpay.setting.ui.WPSettingLimitActivity$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class RunnableC10699a implements Runnable {
        public RunnableC10699a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            WPSettingLimitActivity wPSettingLimitActivity = WPSettingLimitActivity.this;
            wPSettingLimitActivity.mo1790i(wPSettingLimitActivity.getResources().getString(C10531R.string.wp_setting_update_success));
            WPSettingLimitActivity wPSettingLimitActivity2 = WPSettingLimitActivity.this;
            wPSettingLimitActivity2.getClass();
            Intent intent = new Intent(wPSettingLimitActivity2.getBaseContext(), WPSettingLimitActivity.class);
            intent.setFlags(603979776);
            Activity parent = wPSettingLimitActivity2.getParent();
            if (parent == null) {
                wPSettingLimitActivity2.startActivity(intent);
            } else {
                parent.startActivity(intent);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.qpay.setting.ui.WPSettingLimitActivity$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10700b implements C12048b.InterfaceC12050b {
        @Override // p395i.C12048b.InterfaceC12050b
        /* renamed from: a */
        public final void mo1801a() {
        }
    }

    @NBSInstrumented
    /* renamed from: com.unicom.pay.qpay.setting.ui.WPSettingLimitActivity$c */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10701c implements C12048b.InterfaceC12052d {
        public C10701c() {
        }

        @Override // p395i.C12048b.InterfaceC12052d
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            WPSettingLimitActivity wPSettingLimitActivity = WPSettingLimitActivity.this;
            int i = WPSettingLimitActivity.f20437q;
            P p = wPSettingLimitActivity.f24311a;
            if (p != 0) {
                C13654q.m172a(p, "", new C12365e(wPSettingLimitActivity));
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* renamed from: a */
    public static /* synthetic */ void m6060a(View view) {
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, p393h.InterfaceC12016h
    /* renamed from: D */
    public final String mo56D() {
        return "限额设置页面";
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, p393h.InterfaceC12016h
    /* renamed from: O */
    public final String mo53O() {
        return "qp015";
    }

    @Override // p405l0.InterfaceC12298d
    /* renamed from: U */
    public final String mo1848U() {
        C12386e c12386e = this.f20441o;
        return (c12386e == null || c12386e.m1784a() == null || TextUtils.isEmpty(this.f20441o.m1784a().getPayLimit())) ? "" : this.f20441o.m1784a().getPayLimit();
    }

    @Override // p405l0.InterfaceC12298d
    /* renamed from: a */
    public final String mo1846a(WPGmKeyBean wPGmKeyBean) {
        C14232a c14232a = this.f20442p;
        return c14232a == null ? "" : c14232a.m68c(wPGmKeyBean);
    }

    @Override // p405l0.InterfaceC12298d
    /* renamed from: a */
    public final void mo1847a() {
        try {
            C14232a c14232a = this.f20442p;
            if (c14232a != null) {
                c14232a.m64h0();
                this.f20442p.m66g0();
                this.f20442p.dismissAllowingStateLoss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p405l0.InterfaceC12298d
    /* renamed from: a */
    public final void mo1845a(String str) {
        m2005a(str, getResources().getString(C10531R.string.wp_comm_confirm), new C12048b.InterfaceC12052d() { // from class: com.unicom.pay.qpay.setting.ui.-$$Lambda$vU-RqWat_O8o6M7GN1Kk7M-gFAw
            @Override // p395i.C12048b.InterfaceC12052d
            public final void onClick(View view) {
                WPSettingLimitActivity.m6060a(view);
            }
        });
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: a0 */
    public final C12319g mo2002a0() {
        return new C12319g();
    }

    @Override // p405l0.InterfaceC12298d
    /* renamed from: b */
    public final void mo1844b() {
        try {
            C14232a c14232a = this.f20442p;
            if (c14232a == null || !c14232a.isAdded()) {
                return;
            }
            this.f20442p.dismissAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p405l0.InterfaceC12298d
    /* renamed from: b */
    public final void mo1843b(String str) {
        try {
            C14232a c14232a = this.f20442p;
            if (c14232a != null) {
                c14232a.m64h0();
                this.f20442p.m66g0();
                this.f20442p.dismissAllowingStateLoss();
            }
            m2006a(str, getResources().getString(C10531R.string.wp_comm_cancel), new C10700b(), getResources().getString(C10531R.string.wp_union_pay_find_pwd), new C10701c());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p405l0.InterfaceC12298d
    /* renamed from: c */
    public final void mo1842c() {
        try {
            C14232a c14232a = this.f20442p;
            if (c14232a != null) {
                c14232a.m69c();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p405l0.InterfaceC12298d
    /* renamed from: c */
    public final void mo1841c(String str) {
        try {
            C14232a c14232a = this.f20442p;
            if (c14232a != null) {
                c14232a.m64h0();
                this.f20442p.m66g0();
                this.f20442p.m60k(str);
                this.f20442p.m63i0();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p405l0.InterfaceC12298d
    /* renamed from: d */
    public final void mo1840d() {
        try {
            C14232a c14232a = this.f20442p;
            if (c14232a != null) {
                c14232a.m67d();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: e0 */
    public final int mo1998e0() {
        return C10531R.C10535layout.wp_setting_limit;
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: f0 */
    public final void mo1997f0() {
        String str;
        Intent intent = getIntent();
        if (intent != null) {
            this.f20439m = intent.getParcelableArrayListExtra("dataList");
            str = intent.getStringExtra("payLimit");
        } else {
            this.f20439m = new ArrayList();
            str = "";
        }
        this.f20440n = str;
        C12386e c12386e = new C12386e(this, this.f20439m, this.f20440n);
        this.f20441o = c12386e;
        c12386e.f25057e = this;
        this.f20438l.setAdapter(c12386e);
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: g0 */
    public final void mo1996g0() {
        m1993j(getResources().getString(C10531R.string.wp_speed_pay_setting_limit));
        RecyclerView recyclerView = (RecyclerView) findViewById(C10531R.C10534id.wopay_setting_limit_rv);
        this.f20438l = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.f20438l.setNestedScrollingEnabled(false);
        ((Button) findViewById(C10531R.C10534id.wopay_setting_limit_confirm_btn)).setOnClickListener(this);
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        if (m2001b0()) {
            NBSActionInstrumentation.onClickEventExit();
        } else if (view.getId() == C10531R.C10534id.wapay_header_arrow) {
            m1991k("点击-限额设置-返回箭头");
            finish();
            NBSActionInstrumentation.onClickEventExit();
        } else {
            if (view.getId() == C10531R.C10534id.wopay_setting_limit_confirm_btn) {
                if (!TextUtils.isEmpty(this.f20440n) && !this.f20440n.equals(this.f20441o.m1784a().getPayLimit())) {
                    try {
                        if (this.f20442p == null) {
                            this.f20442p = C14232a.m65h("QPAY_LIMIT");
                        }
                        if (!this.f20442p.isAdded() && !this.f20442p.isVisible()) {
                            this.f20442p.show(getSupportFragmentManager(), "SetLimitPayPassDialog");
                            this.f20442p.f27728n = new C12364d(this);
                        }
                        this.f20442p.dismissAllowingStateLoss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    finish();
                }
                m1991k("点击-限额设置-确认更改按钮");
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 408);
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (this.f20441o == null) {
            return;
        }
        Intent intent2 = new Intent();
        String payLimit = this.f20441o.m1784a().getPayLimit();
        Bundle bundle = new Bundle();
        bundle.putString("limitAmount", payLimit);
        intent2.putExtras(bundle);
        setResult(-1, intent2);
        finish();
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
        return "98U01170qp015";
    }

    @Override // p405l0.InterfaceC12298d
    /* renamed from: w */
    public final void mo1839w() {
        try {
            RecyclerView recyclerView = this.f20438l;
            if (recyclerView != null) {
                recyclerView.postDelayed(new RunnableC10699a(), 1500L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
