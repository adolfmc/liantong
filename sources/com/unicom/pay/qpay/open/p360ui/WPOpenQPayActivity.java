package com.unicom.pay.qpay.open.p360ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
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
import com.unicom.pay.UnicomPaySDK;
import com.unicom.pay.modules.verify.bean.WPGmKeyBean;
import com.unicom.pay.modules.verify.bean.WPQOpenResultBean;
import com.unicom.pay.qpay.open.bean.WPAgreementBean;
import com.unicom.pay.qpay.open.bean.WPQPayUserInfoBean;
import com.unicom.pay.qpay.setting.p361ui.WPQPaySettingActivity;
import com.unicom.pay.utils.buried.WPTrendsEventsUtils;
import java.util.HashMap;
import p389f0.InterfaceC11927b;
import p391g0.C11948a;
import p391g0.C11954d;
import p393h.AbstractDialogInterface$OnCancelListenerC12000b;
import p394h0.C12018a;
import p394h0.C12019b;
import p395i.C12048b;
import p470p0.C13647j;
import p470p0.C13654q;
import p470p0.C13659r;
import p470p0.C13663u;
import p470p0.C13665v;
import p481v.C14232a;
import p481v.C14239e;
import p482w.C14255c;
import p482w.C14262f;

@NBSInstrumented
/* renamed from: com.unicom.pay.qpay.open.ui.WPOpenQPayActivity */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPOpenQPayActivity extends AbstractDialogInterface$OnCancelListenerC12000b<C11954d> implements InterfaceC11927b {

    /* renamed from: I */
    public static final /* synthetic */ int f20386I = 0;

    /* renamed from: A */
    public ImageView f20387A;

    /* renamed from: B */
    public TextView f20388B;

    /* renamed from: C */
    public TextView f20389C;

    /* renamed from: D */
    public TextView f20390D;

    /* renamed from: E */
    public C14232a f20391E;

    /* renamed from: F */
    public C14239e f20392F;

    /* renamed from: G */
    public String f20393G;

    /* renamed from: H */
    public WPAgreementBean f20394H;
    public NBSTraceUnit _nbs_trace;

    /* renamed from: l */
    public LinearLayout f20395l;

    /* renamed from: m */
    public ImageView f20396m;

    /* renamed from: n */
    public TextView f20397n;

    /* renamed from: o */
    public ImageView f20398o;

    /* renamed from: p */
    public ImageView f20399p;

    /* renamed from: q */
    public TextView f20400q;

    /* renamed from: r */
    public TextView f20401r;

    /* renamed from: s */
    public TextView f20402s;

    /* renamed from: t */
    public LinearLayout f20403t;

    /* renamed from: u */
    public ImageView f20404u;

    /* renamed from: v */
    public TextView f20405v;

    /* renamed from: w */
    public TextView f20406w;

    /* renamed from: x */
    public ImageView f20407x;

    /* renamed from: y */
    public TextView f20408y;

    /* renamed from: z */
    public TextView f20409z;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.qpay.open.ui.WPOpenQPayActivity$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10686a implements C12048b.InterfaceC12050b {
        @Override // p395i.C12048b.InterfaceC12050b
        /* renamed from: a */
        public final void mo1801a() {
        }
    }

    @NBSInstrumented
    /* renamed from: com.unicom.pay.qpay.open.ui.WPOpenQPayActivity$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10687b implements C12048b.InterfaceC12052d {
        public C10687b() {
        }

        @Override // p395i.C12048b.InterfaceC12052d
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            WPOpenQPayActivity wPOpenQPayActivity = WPOpenQPayActivity.this;
            int i = WPOpenQPayActivity.f20386I;
            P p = wPOpenQPayActivity.f24311a;
            if (p != 0) {
                C13654q.m172a(p, "", new C12019b(wPOpenQPayActivity));
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* renamed from: com.unicom.pay.qpay.open.ui.WPOpenQPayActivity$c */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class RunnableC10688c implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ WPQOpenResultBean f20411a;

        public RunnableC10688c(WPQOpenResultBean wPQOpenResultBean) {
            this.f20411a = wPQOpenResultBean;
        }

        @Override // java.lang.Runnable
        public final void run() {
            WPOpenQPayActivity.this.mo1790i(this.f20411a.getSignResultMsg());
            WPOpenQPayActivity.this.m2008a((Bundle) null, WPQPaySettingActivity.class);
            WPOpenQPayActivity.this.finish();
        }
    }

    /* renamed from: com.unicom.pay.qpay.open.ui.WPOpenQPayActivity$d */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class RunnableC10689d implements Runnable {

        /* renamed from: com.unicom.pay.qpay.open.ui.WPOpenQPayActivity$d$a */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class C10690a implements C14239e.InterfaceC14242c {
            public C10690a() {
            }

            @Override // p481v.C14239e.InterfaceC14242c
            /* renamed from: a */
            public final void mo33a(WPQOpenResultBean wPQOpenResultBean) {
                WPOpenQPayActivity.this.mo2036a(wPQOpenResultBean, false);
            }
        }

        public RunnableC10689d() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            WPOpenQPayActivity wPOpenQPayActivity = WPOpenQPayActivity.this;
            wPOpenQPayActivity.f20392F.show(wPOpenQPayActivity.getSupportFragmentManager(), "OpenQPayVerifyCodeDialog");
            WPOpenQPayActivity.this.f20392F.f27737A = new C10690a();
        }
    }

    /* renamed from: a */
    public static /* synthetic */ void m6066a(View view) {
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, p393h.InterfaceC12016h
    /* renamed from: D */
    public final String mo56D() {
        return "极速支付开通页";
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, p393h.InterfaceC12016h
    /* renamed from: O */
    public final String mo53O() {
        return "qp012";
    }

    @Override // p389f0.InterfaceC11927b
    /* renamed from: a */
    public final String mo2038a(WPGmKeyBean wPGmKeyBean) {
        C14232a c14232a = this.f20391E;
        return c14232a == null ? "" : c14232a.m68c(wPGmKeyBean);
    }

    @Override // p389f0.InterfaceC11927b
    /* renamed from: a */
    public final void mo2039a() {
        C14232a c14232a = this.f20391E;
        if (c14232a != null) {
            c14232a.m64h0();
            this.f20391E.m66g0();
            this.f20391E.dismissAllowingStateLoss();
        }
    }

    @Override // p389f0.InterfaceC11927b
    /* renamed from: a */
    public final void mo2037a(WPQOpenResultBean wPQOpenResultBean) {
        if (wPQOpenResultBean == null) {
            return;
        }
        try {
            C14239e m51a = C14239e.m51a(C14239e.m47a(wPQOpenResultBean.getPhoneNo(), "QPAY_SET_SIGN", wPQOpenResultBean.getSignTokenId(), "", "PAY_SET"));
            this.f20392F = m51a;
            if (!m51a.isAdded() && !this.f20392F.isVisible()) {
                LinearLayout linearLayout = this.f20395l;
                if (linearLayout != null) {
                    linearLayout.postDelayed(new RunnableC10689d(), 1500L);
                    return;
                }
                return;
            }
            this.f20392F.dismissAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p389f0.InterfaceC11927b
    /* renamed from: a */
    public final void mo2035a(WPAgreementBean wPAgreementBean) {
        try {
            this.f20399p.setVisibility(0);
            this.f20397n.setVisibility(0);
            this.f20394H = wPAgreementBean;
            if (!TextUtils.isEmpty(wPAgreementBean.getAgreementName())) {
                this.f20400q.setText(this.f20394H.getAgreementName());
            }
            if (this.f20394H.getQpayTitle() != null) {
                this.f20397n.setText(this.f20394H.getQpayTitle().getTitle());
                C13647j.m179a(this, this.f20394H.getQpayTitle().getFirstTitleIcon(), this.f20396m);
                C13647j.m179a(this, this.f20394H.getQpayTitle().getSecondTitleIcon(), this.f20398o);
            }
            if (this.f20394H.getSetTitle1() != null) {
                C13647j.m179a(this, this.f20394H.getSetTitle1().getIconUrl(), this.f20404u);
                this.f20405v.setText(this.f20394H.getSetTitle1().getTitle());
                this.f20405v.getPaint().setFakeBoldText(true);
                this.f20406w.setText(this.f20394H.getSetTitle1().getTitleDesc());
                this.f20403t.setVisibility(0);
            }
            if (this.f20394H.getSetTitle2() != null) {
                C13647j.m179a(this, this.f20394H.getSetTitle2().getIconUrl(), this.f20407x);
                this.f20408y.setText(this.f20394H.getSetTitle2().getTitle());
                this.f20408y.getPaint().setFakeBoldText(true);
                this.f20409z.setText(this.f20394H.getSetTitle2().getTitleDesc());
                this.f20403t.setVisibility(0);
            }
            if (this.f20394H.getSetTitle3() != null) {
                C13647j.m179a(this, this.f20394H.getSetTitle3().getIconUrl(), this.f20387A);
                this.f20388B.setText(this.f20394H.getSetTitle3().getTitle());
                this.f20388B.getPaint().setFakeBoldText(true);
                this.f20389C.setText(this.f20394H.getSetTitle3().getTitleDesc());
                this.f20403t.setVisibility(0);
            }
            if (TextUtils.isEmpty(this.f20394H.getSetPrompt())) {
                this.f20402s.setVisibility(8);
            } else {
                this.f20402s.setText(this.f20394H.getSetPrompt());
                this.f20402s.setVisibility(0);
            }
            if (TextUtils.isEmpty(this.f20394H.getMarketText())) {
                this.f20390D.setVisibility(8);
            } else {
                this.f20390D.setText(this.f20394H.getMarketText());
                this.f20390D.setVisibility(0);
            }
            C10546a c10546a = C10546a.C10576i.f20125a;
            if (c10546a != null) {
                c10546a.f20056f = wPAgreementBean.getPhoneNo();
                m1992j0();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: a0 */
    public final C11954d mo2002a0() {
        return new C11954d();
    }

    @Override // p389f0.InterfaceC11927b
    /* renamed from: b */
    public final void mo2033b() {
        C14232a c14232a = this.f20391E;
        if (c14232a == null || !c14232a.isAdded()) {
            return;
        }
        this.f20391E.dismissAllowingStateLoss();
    }

    @Override // p389f0.InterfaceC11927b
    /* renamed from: b */
    public final void mo2032b(String str) {
        C14232a c14232a = this.f20391E;
        if (c14232a != null) {
            c14232a.m64h0();
            this.f20391E.m66g0();
            this.f20391E.dismissAllowingStateLoss();
        }
        m2006a(str, getResources().getString(C10531R.string.wp_comm_cancel), new C10686a(), getResources().getString(C10531R.string.wp_union_pay_find_pwd), new C10687b());
    }

    @Override // p389f0.InterfaceC11927b
    /* renamed from: c */
    public final void mo2031c() {
        C14232a c14232a = this.f20391E;
        if (c14232a != null) {
            c14232a.m69c();
        }
    }

    @Override // p389f0.InterfaceC11927b
    /* renamed from: c */
    public final void mo2030c(String str) {
        C14232a c14232a = this.f20391E;
        if (c14232a != null) {
            c14232a.m64h0();
            this.f20391E.m66g0();
            this.f20391E.m60k(str);
            this.f20391E.m63i0();
        }
    }

    @Override // p389f0.InterfaceC11927b
    /* renamed from: d */
    public final void mo2029d() {
        C14232a c14232a = this.f20391E;
        if (c14232a != null) {
            c14232a.m67d();
        }
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: e0 */
    public final int mo1998e0() {
        return C10531R.C10535layout.wp_speed_pay_open;
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: f0 */
    public final void mo1997f0() {
        WPTrendsEventsUtils.initData();
        this.f20393G = getIntent().getStringExtra("bizCode");
        C11954d c11954d = (C11954d) this.f24311a;
        c11954d.getClass();
        c11954d.m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27815e, new HashMap(), false, new C11948a(c11954d, c11954d)));
    }

    @Override // android.app.Activity
    public final void finish() {
        m6065l0();
        super.finish();
    }

    @Override // p389f0.InterfaceC11927b
    /* renamed from: g */
    public final String mo2028g() {
        return "PAY_SET";
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: g0 */
    public final void mo1996g0() {
        m1993j(getResources().getString(C10531R.string.wp_up_pay_title));
        LinearLayout linearLayout = (LinearLayout) findViewById(C10531R.C10534id.wopay_qpay_open_root_cl);
        this.f20395l = linearLayout;
        int m168b = C13659r.m168b(this);
        int m169a = C13659r.m169a(this);
        Bitmap createBitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), C10531R.C10533drawable.wp_open_qpay_header_bg));
        Bitmap createBitmap2 = Bitmap.createBitmap(m168b, m169a, Bitmap.Config.ARGB_8888);
        createBitmap2.eraseColor(getResources().getColor(C10531R.C10532color.wp_bgcolor));
        Canvas canvas = new Canvas(createBitmap2);
        Matrix matrix = new Matrix();
        if (m168b < createBitmap.getWidth()) {
            matrix.postScale((createBitmap.getWidth() / m168b) * 5.0f, 1.0f);
        } else {
            matrix.postScale((m168b / createBitmap.getWidth()) * 5.0f, 1.0f);
        }
        canvas.drawBitmap(Bitmap.createBitmap(createBitmap, 0, 0, createBitmap.getWidth(), createBitmap.getHeight(), matrix, false), 0.0f, 0.0f, (Paint) null);
        linearLayout.setBackground(new BitmapDrawable(getResources(), createBitmap2));
        this.f20396m = (ImageView) findViewById(C10531R.C10534id.wopay_speed_open_pay_tip_iv);
        this.f20398o = (ImageView) findViewById(C10531R.C10534id.wopay_qpay_sign_logo_iv);
        this.f20397n = (TextView) findViewById(C10531R.C10534id.wopay_speed_open_pay_tip_tv);
        ImageView imageView = (ImageView) findViewById(C10531R.C10534id.wopay_qpay_qa_iv);
        this.f20399p = imageView;
        imageView.setOnClickListener(this);
        TextView textView = (TextView) findViewById(C10531R.C10534id.wopay_speed_open_pay_protocol2_tv);
        this.f20400q = textView;
        textView.setOnClickListener(this);
        TextView textView2 = (TextView) findViewById(C10531R.C10534id.wopay_speed_open_pay_btn);
        this.f20401r = textView2;
        textView2.getPaint().setFakeBoldText(true);
        this.f20401r.setOnClickListener(this);
        this.f20402s = (TextView) findViewById(C10531R.C10534id.wopay_qpay_setting_path_tv);
        this.f20403t = (LinearLayout) findViewById(C10531R.C10534id.wopay_qpay_setting_tips_ll);
        this.f20390D = (TextView) findViewById(C10531R.C10534id.wopay_qpay_open_prompt_tv);
        this.f20404u = (ImageView) findViewById(C10531R.C10534id.wopay_qpay_open_tips_avatar_iv1);
        this.f20405v = (TextView) findViewById(C10531R.C10534id.wopay_qpay_open_tips_title_tv1);
        this.f20406w = (TextView) findViewById(C10531R.C10534id.wopay_qpay_open_tips_desc_tv1);
        this.f20407x = (ImageView) findViewById(C10531R.C10534id.wopay_qpay_open_tips_avatar_iv2);
        this.f20408y = (TextView) findViewById(C10531R.C10534id.wopay_qpay_open_tips_title_tv2);
        this.f20409z = (TextView) findViewById(C10531R.C10534id.wopay_qpay_open_tips_desc_tv2);
        this.f20387A = (ImageView) findViewById(C10531R.C10534id.wopay_qpay_open_tips_avatar_iv3);
        this.f20388B = (TextView) findViewById(C10531R.C10534id.wopay_qpay_open_tips_title_tv3);
        this.f20389C = (TextView) findViewById(C10531R.C10534id.wopay_qpay_open_tips_desc_tv3);
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: i0 */
    public final void mo1994i0() {
        C13665v.m163a(this);
        C13665v.m160c(this);
    }

    /* renamed from: l0 */
    public final void m6065l0() {
        C14232a c14232a = this.f20391E;
        if (c14232a != null && c14232a.isVisible()) {
            this.f20391E.dismissAllowingStateLoss();
        }
        C14239e c14239e = this.f20392F;
        if (c14239e == null || !c14239e.isVisible()) {
            return;
        }
        this.f20392F.dismissAllowingStateLoss();
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.view.View.OnClickListener
    public void onClick(View view) {
        String str;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        if (m2001b0()) {
            NBSActionInstrumentation.onClickEventExit();
        } else if (view.getId() == C10531R.C10534id.wapay_header_arrow) {
            finish();
            m1991k("点击-开通页-返回箭头");
            NBSActionInstrumentation.onClickEventExit();
        } else {
            if (view.getId() != C10531R.C10534id.wopay_speed_open_pay_btn) {
                if (view.getId() == C10531R.C10534id.wopay_speed_open_pay_protocol2_tv) {
                    WPAgreementBean wPAgreementBean = this.f20394H;
                    if (wPAgreementBean != null && !TextUtils.isEmpty(wPAgreementBean.getAgreementUrl())) {
                        UnicomPaySDK.getInstance().getNativeFunctionCallback().openWebview(this, this.f20394H.getAgreementUrl());
                    }
                    m1991k("点击-开通页-查看用户协议");
                    WPTrendsEventsUtils.trendsPageData("极速支付协议页面", "98U01170qp001", "qp001");
                } else if (view.getId() == C10531R.C10534id.wopay_qpay_qa_iv) {
                    WPAgreementBean wPAgreementBean2 = this.f20394H;
                    if (wPAgreementBean2 != null && !TextUtils.isEmpty(wPAgreementBean2.getQpayHelpUrl())) {
                        UnicomPaySDK.getInstance().getNativeFunctionCallback().openWebview(this, this.f20394H.getQpayHelpUrl());
                    }
                    str = "点击-开通页-小问号";
                }
                NBSActionInstrumentation.onClickEventExit();
            }
            if ("QPAY_SET_SIGN".equals(this.f20393G)) {
                if (this.f20391E == null) {
                    this.f20391E = C14232a.m65h("QPAY_SET_SIGN");
                }
                if (!this.f20391E.isAdded() && !this.f20391E.isVisible()) {
                    this.f20391E.show(getSupportFragmentManager(), "OpenQPayPayPassDialog");
                    this.f20391E.f27728n = new C12018a(this);
                } else {
                    this.f20391E.dismissAllowingStateLoss();
                }
            }
            str = "点击-开通页-同意开启按钮";
            m1991k(str);
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 406);
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        m6065l0();
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
        return "98U01170qp012";
    }

    @Override // p389f0.InterfaceC11927b
    /* renamed from: a */
    public final void mo2036a(WPQOpenResultBean wPQOpenResultBean, boolean z) {
        try {
            if (wPQOpenResultBean == null) {
                mo1790i(getResources().getString(C10531R.string.wp_open_result_fail));
                return;
            }
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
                if (z) {
                    LinearLayout linearLayout = this.f20395l;
                    if (linearLayout != null) {
                        linearLayout.postDelayed(new RunnableC10688c(wPQOpenResultBean), 1500L);
                        return;
                    }
                    return;
                }
                mo1790i(wPQOpenResultBean.getSignResultMsg());
                m2008a((Bundle) null, WPQPaySettingActivity.class);
            } else {
                mo1790i(wPQOpenResultBean.getSignResultMsg());
            }
            finish();
        } catch (Exception e) {
            e.printStackTrace();
            mo1790i(getResources().getString(C10531R.string.wp_open_result_fail));
            finish();
        }
    }

    @Override // p389f0.InterfaceC11927b
    /* renamed from: a */
    public final void mo2034a(String str) {
        m2005a(str, getResources().getString(C10531R.string.wp_comm_confirm), new C12048b.InterfaceC12052d() { // from class: com.unicom.pay.qpay.open.ui.-$$Lambda$nQ2IDqaFWkcoSpgZajLAZs4CaEU
            @Override // p395i.C12048b.InterfaceC12052d
            public final void onClick(View view) {
                WPOpenQPayActivity.m6066a(view);
            }
        });
    }
}
