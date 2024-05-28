package com.unicom.pay.modules.result.p356ui;

import android.content.ComponentCallbacks;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.Html;
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
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.common.callback.DataCallback;
import com.unicom.pay.common.callback.NativeFunctionCallBack;
import com.unicom.pay.modules.result.bean.WPBannerDiscountBean;
import com.unicom.pay.modules.result.bean.WPBannerInfoBean;
import com.unicom.pay.modules.result.bean.WPUserStatusBean;
import com.unicom.pay.modules.verify.bean.WPGmKeyBean;
import com.unicom.pay.modules.verify.bean.WPQOpenResultBean;
import com.unicom.pay.modules.verify.bean.WPUnionPayResultBean;
import com.unicom.pay.qpay.open.bean.WPQPayUserInfoBean;
import com.unicom.pay.qpay.setting.p361ui.WPQPaySettingActivity;
import com.unicom.pay.utils.buried.WPBusinessInfoBean;
import com.unicom.pay.utils.buried.WPTrendsEventsUtils;
import java.util.ArrayList;
import java.util.HashMap;
import p393h.AbstractDialogInterface$OnCancelListenerC12000b;
import p394h0.View$OnClickListenerC12020c;
import p395i.C12048b;
import p469p.C13621a;
import p469p.C13624c;
import p470p0.C13636a;
import p470p0.C13646i;
import p470p0.C13654q;
import p470p0.C13663u;
import p471q.InterfaceC13674b;
import p473r.C13700a;
import p473r.C13712e;
import p476s.C14113a;
import p476s.C14114b;
import p481v.C14232a;
import p481v.C14239e;
import p482w.C14255c;
import p482w.C14262f;

@NBSInstrumented
/* renamed from: com.unicom.pay.modules.result.ui.WPPayResultActivity */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPPayResultActivity extends AbstractDialogInterface$OnCancelListenerC12000b<C13700a> implements View$OnClickListenerC12020c.InterfaceC12022b, C13624c.InterfaceC13633i, InterfaceC13674b {

    /* renamed from: F */
    public static final /* synthetic */ int f20142F = 0;

    /* renamed from: A */
    public C14232a f20143A;

    /* renamed from: B */
    public C14239e f20144B;

    /* renamed from: D */
    public boolean f20146D;

    /* renamed from: E */
    public ComponentCallbacksC10596c f20147E;
    public NBSTraceUnit _nbs_trace;

    /* renamed from: l */
    public ImageView f20148l;

    /* renamed from: m */
    public TextView f20149m;

    /* renamed from: n */
    public LinearLayout f20150n;

    /* renamed from: o */
    public TextView f20151o;

    /* renamed from: p */
    public TextView f20152p;

    /* renamed from: q */
    public TextView f20153q;

    /* renamed from: r */
    public LinearLayout f20154r;

    /* renamed from: s */
    public TextView f20155s;

    /* renamed from: t */
    public TextView f20156t;

    /* renamed from: u */
    public RecyclerView f20157u;

    /* renamed from: v */
    public RecyclerView f20158v;

    /* renamed from: w */
    public C13624c f20159w;

    /* renamed from: y */
    public WPResult<WPUnionPayResultBean> f20161y;

    /* renamed from: z */
    public View$OnClickListenerC12020c f20162z;

    /* renamed from: x */
    public String f20160x = "";

    /* renamed from: C */
    public String f20145C = "PAY_RESULT";

    /* renamed from: com.unicom.pay.modules.result.ui.WPPayResultActivity$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10593a implements C12048b.InterfaceC12050b {
        public C10593a() {
        }

        @Override // p395i.C12048b.InterfaceC12050b
        /* renamed from: a */
        public final void mo1801a() {
            WPPayResultActivity wPPayResultActivity = WPPayResultActivity.this;
            int i = WPPayResultActivity.f20142F;
            wPPayResultActivity.m6160m0();
            if (TextUtils.isEmpty(WPPayResultActivity.this.f20160x)) {
                return;
            }
            WPPayResultActivity wPPayResultActivity2 = WPPayResultActivity.this;
            ((C13700a) wPPayResultActivity2.f24311a).m129a(wPPayResultActivity2.f20160x);
        }
    }

    @NBSInstrumented
    /* renamed from: com.unicom.pay.modules.result.ui.WPPayResultActivity$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10594b implements C12048b.InterfaceC12052d {

        /* renamed from: a */
        public final /* synthetic */ WPUserStatusBean f20164a;

        /* renamed from: com.unicom.pay.modules.result.ui.WPPayResultActivity$b$a */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class C10595a implements DataCallback {
            public C10595a() {
            }

            @Override // com.unicom.pay.common.callback.DataCallback
            public final void onResult(String str) {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                UnicomPaySDK.getInstance().getNativeFunctionCallback().openWebview(WPPayResultActivity.this, str);
            }
        }

        public C10594b(WPUserStatusBean wPUserStatusBean) {
            this.f20164a = wPUserStatusBean;
        }

        @Override // p395i.C12048b.InterfaceC12052d
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            if (!TextUtils.isEmpty(this.f20164a.getNextStepUrl())) {
                C13654q.m171a(WPPayResultActivity.this.f24311a, this.f20164a.getNextStepUrl(), WPPayResultActivity.this.f20160x, new C10595a());
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.modules.result.ui.WPPayResultActivity$c */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class ComponentCallbacksC10596c implements ComponentCallbacks {
        public ComponentCallbacksC10596c() {
        }

        @Override // android.content.ComponentCallbacks
        public final void onConfigurationChanged(@NonNull Configuration configuration) {
            C13624c c13624c = WPPayResultActivity.this.f20159w;
            if (c13624c != null) {
                c13624c.notifyDataSetChanged();
            }
        }

        @Override // android.content.ComponentCallbacks
        public final void onLowMemory() {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.modules.result.ui.WPPayResultActivity$d */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10597d implements C12048b.InterfaceC12050b {
        @Override // p395i.C12048b.InterfaceC12050b
        /* renamed from: a */
        public final void mo1801a() {
        }
    }

    @NBSInstrumented
    /* renamed from: com.unicom.pay.modules.result.ui.WPPayResultActivity$e */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10598e implements C12048b.InterfaceC12052d {
        public C10598e() {
        }

        @Override // p395i.C12048b.InterfaceC12052d
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            WPPayResultActivity wPPayResultActivity = WPPayResultActivity.this;
            int i = WPPayResultActivity.f20142F;
            P p = wPPayResultActivity.f24311a;
            if (p != 0) {
                C13654q.m172a(p, wPPayResultActivity.f20160x, new C14114b(wPPayResultActivity));
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.modules.result.ui.WPPayResultActivity$f */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class RunnableC10599f implements Runnable {
        public RunnableC10599f() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            WPPayResultActivity wPPayResultActivity = WPPayResultActivity.this;
            int i = WPPayResultActivity.f20142F;
            wPPayResultActivity.m6157p0();
        }
    }

    /* renamed from: com.unicom.pay.modules.result.ui.WPPayResultActivity$g */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10600g implements C12048b.InterfaceC12050b {
        @Override // p395i.C12048b.InterfaceC12050b
        /* renamed from: a */
        public final void mo1801a() {
            WPTrendsEventsUtils.trendsPageButtonData("开通成功弹窗", "98U01170qp005", "qp005", "点击-开通成功弹窗-稍后再去");
        }
    }

    @NBSInstrumented
    /* renamed from: com.unicom.pay.modules.result.ui.WPPayResultActivity$h */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10601h implements C12048b.InterfaceC12052d {
        public C10601h() {
        }

        @Override // p395i.C12048b.InterfaceC12052d
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            WPPayResultActivity wPPayResultActivity = WPPayResultActivity.this;
            int i = WPQPaySettingActivity.f20416C;
            Bundle bundle = new Bundle();
            bundle.putBoolean("isFromPayResult", true);
            wPPayResultActivity.m2008a(bundle, WPQPaySettingActivity.class);
            WPTrendsEventsUtils.trendsPageButtonData("开通成功弹窗", "98U01170qp005", "qp005", "点击-开通成功弹窗-去看看");
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* renamed from: com.unicom.pay.modules.result.ui.WPPayResultActivity$i */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class RunnableC10602i implements Runnable {

        /* renamed from: com.unicom.pay.modules.result.ui.WPPayResultActivity$i$a */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class C10603a implements C14239e.InterfaceC14242c {
            public C10603a() {
            }

            @Override // p481v.C14239e.InterfaceC14242c
            /* renamed from: a */
            public final void mo33a(WPQOpenResultBean wPQOpenResultBean) {
                WPPayResultActivity.this.mo149a(wPQOpenResultBean, false);
            }
        }

        public RunnableC10602i() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            WPPayResultActivity wPPayResultActivity = WPPayResultActivity.this;
            wPPayResultActivity.f20144B.show(wPPayResultActivity.getSupportFragmentManager(), "PayResultVerifyCodeDialog");
            WPPayResultActivity.this.f20144B.f27737A = new C10603a();
        }
    }

    /* renamed from: a */
    public static /* synthetic */ void m6164a(View view) {
    }

    /* renamed from: l */
    public static Bundle m6162l(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("unionOrderId", str);
        return bundle;
    }

    @Override // p394h0.View$OnClickListenerC12020c.InterfaceC12022b
    /* renamed from: A */
    public final void mo1978A() {
        C13700a c13700a = (C13700a) this.f24311a;
        String str = this.f20145C;
        c13700a.getClass();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("recommendedScene", str);
        c13700a.m1797a(C14255c.C14256a.f27777a.m30a(C14262f.f27825o, hashMap, new C13712e(c13700a, c13700a)));
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, p393h.InterfaceC12016h
    /* renamed from: D */
    public final String mo56D() {
        WPResult<WPUnionPayResultBean> wPResult = this.f20161y;
        return wPResult == null ? "-" : "0000".equals(wPResult.getCode()) ? "支付结果页-成功" : "406114034".equals(this.f20161y.getCode()) ? "支付结果页-处理中" : "支付结果页-失败";
    }

    @Override // p471q.InterfaceC13674b
    /* renamed from: H */
    public final void mo156H() {
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, p393h.InterfaceC12016h
    /* renamed from: O */
    public final String mo53O() {
        WPResult<WPUnionPayResultBean> wPResult = this.f20161y;
        return wPResult == null ? "-" : "0000".equals(wPResult.getCode()) ? "wp700" : "406114034".equals(this.f20161y.getCode()) ? "wp710" : "wp720";
    }

    @Override // p471q.InterfaceC13674b
    /* renamed from: a */
    public final String mo151a(WPGmKeyBean wPGmKeyBean) {
        C14232a c14232a = this.f20143A;
        return c14232a == null ? "" : c14232a.m68c(wPGmKeyBean);
    }

    @Override // p471q.InterfaceC13674b
    /* renamed from: a */
    public final void mo155a() {
        C14232a c14232a = this.f20143A;
        if (c14232a != null) {
            c14232a.m64h0();
            this.f20143A.m66g0();
            this.f20143A.dismissAllowingStateLoss();
        }
    }

    @Override // p471q.InterfaceC13674b
    /* renamed from: a */
    public final void mo154a(WPResult<WPUnionPayResultBean> wPResult) {
        this.f20161y = wPResult;
        WPTrendsEventsUtils.addWindow(mo53O());
        m6159n0();
    }

    @Override // p471q.InterfaceC13674b
    /* renamed from: a */
    public final void mo152a(WPUserStatusBean wPUserStatusBean) {
        if (wPUserStatusBean == null) {
            return;
        }
        if ("0".equals(wPUserStatusBean.getUserState())) {
            View$OnClickListenerC12020c view$OnClickListenerC12020c = this.f20162z;
            if (view$OnClickListenerC12020c != null) {
                view$OnClickListenerC12020c.dismissAllowingStateLoss();
            }
            try {
                if (this.f20143A == null) {
                    this.f20143A = C14232a.m65h("QPAY_RESULT_SIGN");
                }
                if (!this.f20143A.isAdded() && !this.f20143A.isVisible()) {
                    this.f20143A.show(getSupportFragmentManager(), "ResultOpenQPayPayPassDialog");
                    this.f20143A.f27728n = new C14113a(this);
                    return;
                }
                this.f20143A.dismissAllowingStateLoss();
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        m2003a(wPUserStatusBean.getPromptTitle(), wPUserStatusBean.getPromptMsg(), wPUserStatusBean.getLeftNextStepDesc(), new C10593a(), wPUserStatusBean.getRightNextStepDesc(), true, new C10594b(wPUserStatusBean), false, false);
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: a0 */
    public final C13700a mo2002a0() {
        return new C13700a();
    }

    @Override // p471q.InterfaceC13674b
    /* renamed from: b */
    public final void mo147b() {
        C14232a c14232a = this.f20143A;
        if (c14232a == null || !c14232a.isAdded()) {
            return;
        }
        this.f20143A.dismissAllowingStateLoss();
    }

    @Override // p471q.InterfaceC13674b
    /* renamed from: b */
    public final void mo146b(String str) {
        C14232a c14232a = this.f20143A;
        if (c14232a != null) {
            c14232a.m64h0();
            this.f20143A.m66g0();
            this.f20143A.dismissAllowingStateLoss();
        }
        m2006a(str, getResources().getString(C10531R.string.wp_comm_cancel), new C10597d(), getResources().getString(C10531R.string.wp_union_pay_find_pwd), new C10598e());
    }

    @Override // p471q.InterfaceC13674b
    /* renamed from: c */
    public final void mo145c() {
        C14232a c14232a = this.f20143A;
        if (c14232a != null) {
            c14232a.m69c();
        }
    }

    @Override // p471q.InterfaceC13674b
    /* renamed from: c */
    public final void mo144c(String str) {
        C14232a c14232a = this.f20143A;
        if (c14232a != null) {
            c14232a.m64h0();
            this.f20143A.m66g0();
            this.f20143A.m60k(str);
            this.f20143A.m63i0();
        }
    }

    @Override // p471q.InterfaceC13674b
    /* renamed from: d */
    public final void mo143d() {
        C14232a c14232a = this.f20143A;
        if (c14232a != null) {
            c14232a.m67d();
        }
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: e0 */
    public final int mo1998e0() {
        return C10531R.C10535layout.wp_pay_result;
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: f0 */
    public final void mo1997f0() {
        Intent intent = getIntent();
        if (intent != null) {
            this.f20160x = intent.getStringExtra("unionOrderId");
            this.f20161y = (WPResult) intent.getParcelableExtra("unionPayResult");
            this.f20146D = intent.getBooleanExtra("signQPay", false);
        }
        if (this.f20161y != null) {
            m6159n0();
        } else if (!TextUtils.isEmpty(this.f20160x)) {
            ((C13700a) this.f24311a).m127b(this.f20160x);
        }
        if (this.f20146D) {
            m6157p0();
        }
    }

    @Override // android.app.Activity
    public final void finish() {
        m6160m0();
        super.finish();
    }

    @Override // p471q.InterfaceC13674b
    /* renamed from: g */
    public final String mo142g() {
        return this.f20145C;
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: g0 */
    public final void mo1996g0() {
        m1993j(getResources().getString(C10531R.string.wp_up_pay_title));
        this.f24312b.setVisibility(8);
        this.f20148l = (ImageView) findViewById(C10531R.C10534id.up_instalment_bank_status_iv);
        this.f20149m = (TextView) findViewById(C10531R.C10534id.wopay_pay_result_status_tv);
        this.f20150n = (LinearLayout) findViewById(C10531R.C10534id.wopay_pay_result_amount_ll);
        this.f20151o = (TextView) findViewById(C10531R.C10534id.wopay_pay_result_amount_tv);
        this.f20152p = (TextView) findViewById(C10531R.C10534id.wopay_pay_result_discount_tv);
        this.f20153q = (TextView) findViewById(C10531R.C10534id.wopay_pay_result_error_tv);
        this.f20154r = (LinearLayout) findViewById(C10531R.C10534id.wopay_pay_result_btn_ll);
        TextView textView = (TextView) findViewById(C10531R.C10534id.wopay_pay_result_other_method_tv);
        this.f20155s = textView;
        textView.setOnClickListener(this);
        TextView textView2 = (TextView) findViewById(C10531R.C10534id.wopay_pay_result_confirm_btn);
        this.f20156t = textView2;
        textView2.setOnClickListener(this);
        this.f20157u = (RecyclerView) findViewById(C10531R.C10534id.up_pay_result_success_rv);
        this.f20158v = (RecyclerView) findViewById(C10531R.C10534id.wopay_result_banner_rv);
        ComponentCallbacksC10596c componentCallbacksC10596c = new ComponentCallbacksC10596c();
        this.f20147E = componentCallbacksC10596c;
        registerComponentCallbacks(componentCallbacksC10596c);
    }

    /* renamed from: l0 */
    public final void m6161l0() {
        C13624c c13624c = this.f20159w;
        if (c13624c != null) {
            c13624c.getClass();
            try {
                ArrayList<WPBannerInfoBean> arrayList = c13624c.f27454b;
                if (arrayList != null && !arrayList.isEmpty()) {
                    ArrayList arrayList2 = new ArrayList();
                    for (int i = 0; i < c13624c.f27454b.size(); i++) {
                        if (!"speedPayOpen".equals(c13624c.f27454b.get(i).getBannerType())) {
                            arrayList2.add(c13624c.f27454b.get(i));
                        }
                    }
                    c13624c.f27454b.clear();
                    c13624c.f27454b.addAll(arrayList2);
                    c13624c.notifyDataSetChanged();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.f20156t.setVisibility(0);
    }

    /* renamed from: m0 */
    public final void m6160m0() {
        View$OnClickListenerC12020c view$OnClickListenerC12020c = this.f20162z;
        if (view$OnClickListenerC12020c != null && view$OnClickListenerC12020c.isVisible()) {
            this.f20162z.dismissAllowingStateLoss();
        }
        C14232a c14232a = this.f20143A;
        if (c14232a != null && c14232a.isVisible()) {
            this.f20143A.dismissAllowingStateLoss();
        }
        C14239e c14239e = this.f20144B;
        if (c14239e == null || !c14239e.isVisible()) {
            return;
        }
        this.f20144B.dismissAllowingStateLoss();
    }

    /* renamed from: n0 */
    public final void m6159n0() {
        TextView textView;
        int color;
        WPBusinessInfoBean wPBusinessInfoBean;
        String str;
        try {
            WPResult<WPUnionPayResultBean> wPResult = this.f20161y;
            if (wPResult == null) {
                return;
            }
            if (wPResult.getData() != null) {
                WPTrendsEventsUtils.setFqNum(this.f20161y.getData().getFqNum());
                WPTrendsEventsUtils.setProjectType(this.f20161y.getData().getProjectType());
            }
            this.f20148l.setVisibility(0);
            this.f20154r.setVisibility(0);
            if ("0000".equals(this.f20161y.getCode())) {
                this.f20155s.setVisibility(8);
                this.f20153q.setVisibility(8);
                this.f20156t.setBackgroundResource(C10531R.C10533drawable.wp_confirm_pure_bg);
                this.f20156t.setTextColor(getResources().getColor(C10531R.C10532color.wp_white));
                this.f20148l.setImageResource(C10531R.C10533drawable.up_pay_result_success);
                this.f20149m.setText(getResources().getString(C10531R.string.wp_pay_result_success));
                WPUnionPayResultBean data = this.f20161y.getData();
                if (data != null) {
                    WPTrendsEventsUtils.setRealPayAmount(data.getAmount());
                    WPTrendsEventsUtils.setYhAmount(data.getPayToolYxAmount());
                    WPTrendsEventsUtils.setHbAmount(data.getHbAmount());
                    this.f20150n.setVisibility(0);
                    this.f20151o.setText(C13646i.m181a(data.getAmount()));
                    this.f20157u.setVisibility(0);
                    this.f20157u.setLayoutManager(new LinearLayoutManager(this));
                    this.f20157u.setAdapter(new C13621a(this, data.getPayEndInfos()));
                    this.f20157u.setNestedScrollingEnabled(false);
                    this.f20157u.setHasFixedSize(true);
                    if (TextUtils.isEmpty(data.getDiscountMsg())) {
                        this.f20152p.setVisibility(8);
                    } else {
                        this.f20152p.setText(Html.fromHtml(data.getDiscountMsg()));
                        this.f20152p.setVisibility(0);
                    }
                    if ("1".equals(data.getShowQpayRecommend())) {
                        if (this.f20162z == null) {
                            View$OnClickListenerC12020c m1979a = View$OnClickListenerC12020c.m1979a(data.getQpqyDiscountMsg(), "QPAY_RESULT_SIGN", data.getAgreement());
                            this.f20162z = m1979a;
                            m1979a.f24377x = this;
                        }
                        this.f20162z.show(getSupportFragmentManager(), "openQPayDialog");
                    }
                }
            } else {
                this.f20150n.setVisibility(8);
                this.f20152p.setVisibility(8);
                if ("406114034".equals(this.f20161y.getCode())) {
                    this.f20148l.setImageResource(C10531R.C10533drawable.up_pay_result_dealing);
                    this.f20149m.setText(getResources().getString(C10531R.string.wp_pay_result_dealing));
                    this.f20155s.setText(getResources().getString(C10531R.string.wp_union_other_method2));
                    this.f20155s.setVisibility(0);
                    this.f20156t.setBackgroundResource(C10531R.C10533drawable.wp_confirm_red_line_bg);
                    textView = this.f20156t;
                    color = getResources().getColor(C10531R.C10532color.wp_red_color);
                } else {
                    this.f20148l.setImageResource(C10531R.C10533drawable.up_pay_result_error);
                    this.f20149m.setText(getResources().getString(C10531R.string.wp_pay_result_fail));
                    this.f20155s.setVisibility(8);
                    this.f20156t.setBackgroundResource(C10531R.C10533drawable.wp_confirm_pure_bg);
                    textView = this.f20156t;
                    color = getResources().getColor(C10531R.C10532color.wp_white);
                }
                textView.setTextColor(color);
                this.f20153q.setVisibility(0);
                this.f20153q.setText(this.f20161y.getMsg());
            }
            if (this.f24319i == null) {
                this.f24319i = WPBusinessInfoBean.generatePageEntity();
            }
            if (this.f24320j == null) {
                this.f24320j = WPBusinessInfoBean.generateButtonEntity();
            }
            if ("0000".equals(this.f20161y.getCode())) {
                this.f24319i.setPayment_results("success");
                wPBusinessInfoBean = this.f24320j;
                str = "success";
            } else if ("406114034".equals(this.f20161y.getCode())) {
                this.f24319i.setPayment_results("process");
                wPBusinessInfoBean = this.f24320j;
                str = "process";
            } else {
                this.f24319i.setPayment_results("fail");
                wPBusinessInfoBean = this.f24320j;
                str = "fail";
            }
            wPBusinessInfoBean.setPayment_results(str);
            m6158o0();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: o0 */
    public final void m6158o0() {
        try {
            WPResult<WPUnionPayResultBean> wPResult = this.f20161y;
            if (wPResult != null && wPResult.getData() != null) {
                C13624c c13624c = this.f20159w;
                if (c13624c == null) {
                    this.f20159w = new C13624c(this, this.f20161y.getData().getBannerList());
                } else {
                    ArrayList<WPBannerInfoBean> bannerList = this.f20161y.getData().getBannerList();
                    ArrayList<WPBannerInfoBean> arrayList = c13624c.f27454b;
                    if (arrayList == null) {
                        c13624c.f27454b = new ArrayList<>();
                    } else {
                        arrayList.clear();
                    }
                    c13624c.f27454b.addAll(bannerList);
                    c13624c.notifyDataSetChanged();
                }
                C13624c c13624c2 = this.f20159w;
                c13624c2.f27455c = this;
                this.f20158v.setAdapter(c13624c2);
                this.f20158v.setLayoutManager(new LinearLayoutManager(this));
                this.f20158v.setAdapter(this.f20159w);
                this.f20158v.setNestedScrollingEnabled(false);
                this.f20158v.setHasFixedSize(true);
                this.f20158v.setVisibility(0);
                if (this.f20161y.getData().getAgreement() == null) {
                    this.f20161y.getData().setAgreement(this.f20161y.getData().getAgreement());
                    this.f20161y.getData().setMerNo(this.f20161y.getData().getMerNo());
                }
                this.f20156t.setVisibility(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public final void onBackPressed() {
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.view.View.OnClickListener
    public void onClick(View view) {
        WPResult<WPUnionPayResultBean> wPResult;
        NativeFunctionCallBack nativeFunctionCallback;
        String storeIndex;
        WPResult<WPUnionPayResultBean> wPResult2;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        if (m2001b0()) {
            NBSActionInstrumentation.onClickEventExit();
        } else if (view.getId() == C10531R.C10534id.wapay_header_arrow) {
            finish();
            NBSActionInstrumentation.onClickEventExit();
        } else {
            if (view.getId() == C10531R.C10534id.wopay_pay_result_confirm_btn) {
                try {
                    wPResult = this.f20161y;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (wPResult != null) {
                    m1991k("0000".equals(wPResult.getCode()) ? "点击-支付成功-完成" : "406114034".equals(this.f20161y.getCode()) ? "点击-支付处理中-完成" : "点击-支付失败-完成");
                    WPUnionPayResultBean data = this.f20161y.getData();
                    if (data != null) {
                        if ("0000".equals(this.f20161y.getCode())) {
                            if (!TextUtils.isEmpty(data.getCallBackUrl())) {
                                nativeFunctionCallback = UnicomPaySDK.getInstance().getNativeFunctionCallback();
                                storeIndex = data.getCallBackUrl();
                                nativeFunctionCallback.loadUrl(storeIndex, false);
                            }
                        } else if (!TextUtils.isEmpty(data.getStoreIndex())) {
                            nativeFunctionCallback = UnicomPaySDK.getInstance().getNativeFunctionCallback();
                            storeIndex = data.getStoreIndex();
                            nativeFunctionCallback.loadUrl(storeIndex, false);
                        }
                        e.printStackTrace();
                    }
                }
                C13636a.m190a();
            } else if (view.getId() == C10531R.C10534id.wopay_pay_result_other_method_tv && (wPResult2 = this.f20161y) != null && "406114034".equals(wPResult2.getCode())) {
                m1991k("点击-支付处理中-查询交易结果");
                ((C13700a) this.f24311a).m127b(this.f20160x);
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 399);
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        ComponentCallbacksC10596c componentCallbacksC10596c = this.f20147E;
        if (componentCallbacksC10596c != null) {
            unregisterComponentCallbacks(componentCallbacksC10596c);
        }
        m6160m0();
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
        WPResult<WPUnionPayResultBean> wPResult = this.f20161y;
        return wPResult == null ? "-" : "0000".equals(wPResult.getCode()) ? "98U01170wp700" : "406114034".equals(this.f20161y.getCode()) ? "98U01170wp710" : "98U01170wp720";
    }

    /* renamed from: p0 */
    public final void m6157p0() {
        WPTrendsEventsUtils.trendsPageData("开通成功弹窗", "98U01170qp005", "qp005");
        m2004a(getResources().getString(C10531R.string.wp_open_result_success1), getResources().getString(C10531R.string.wp_open_result_success_msg), getResources().getString(C10531R.string.wp_open_result_cancel), new C10600g(), getResources().getString(C10531R.string.wp_open_result_confirm), new C10601h());
    }

    @Override // p394h0.View$OnClickListenerC12020c.InterfaceC12022b
    /* renamed from: q */
    public final void mo1977q() {
        ((C13700a) this.f24311a).m129a(this.f20160x);
    }

    @Override // p471q.InterfaceC13674b
    /* renamed from: a */
    public final void mo153a(WPBannerDiscountBean wPBannerDiscountBean, int i) {
        ArrayList<WPBannerInfoBean> arrayList;
        mo1790i(wPBannerDiscountBean.getBannerDiscountMsg());
        C13624c c13624c = this.f20159w;
        if (c13624c == null || (arrayList = c13624c.f27454b) == null) {
            return;
        }
        arrayList.get(i).setChange(true);
        c13624c.notifyItemChanged(i);
    }

    /* renamed from: a */
    public final void m6163a(WPBannerInfoBean wPBannerInfoBean, int i) {
        C13700a c13700a;
        String str;
        UnicomPaySDK unicomPaySDK;
        View$OnClickListenerC12020c view$OnClickListenerC12020c;
        try {
            if ("speedPayOpen".equals(wPBannerInfoBean.getBannerType())) {
                WPResult<WPUnionPayResultBean> wPResult = this.f20161y;
                if (wPResult != null && wPResult.getData() != null) {
                    this.f20145C = "PAY_BANNER";
                    View$OnClickListenerC12020c view$OnClickListenerC12020c2 = this.f20162z;
                    if (view$OnClickListenerC12020c2 != null) {
                        if (!view$OnClickListenerC12020c2.isVisible()) {
                            view$OnClickListenerC12020c = this.f20162z;
                        }
                        m1991k("点击-支付成功-开启极速支付");
                        return;
                    }
                    view$OnClickListenerC12020c = View$OnClickListenerC12020c.m1979a(wPBannerInfoBean.getDiscountMsg(), "QPAY_RESULT_SIGN", this.f20161y.getData().getAgreement());
                    this.f20162z = view$OnClickListenerC12020c;
                    view$OnClickListenerC12020c.f24377x = this;
                    view$OnClickListenerC12020c.show(getSupportFragmentManager(), "openQPayDialog");
                    m1991k("点击-支付成功-开启极速支付");
                    return;
                }
                return;
            }
            if ("h5Jump".equals(wPBannerInfoBean.getBannerType())) {
                if (TextUtils.isEmpty(wPBannerInfoBean.getUrl())) {
                    return;
                }
                m1991k("点击-支付成功-广告" + i + "-跳转");
                unicomPaySDK = UnicomPaySDK.getInstance();
            } else if ("changeText".equals(wPBannerInfoBean.getBannerType())) {
                if (!wPBannerInfoBean.isChange()) {
                    m1991k("点击-支付成功-广告" + i + "-领取");
                    c13700a = (C13700a) this.f24311a;
                    str = this.f20160x;
                    c13700a.m128a(str, wPBannerInfoBean.getRuleId(), wPBannerInfoBean.getBannerDiscountMsg(), i);
                    return;
                } else if (TextUtils.isEmpty(wPBannerInfoBean.getUrl())) {
                    return;
                } else {
                    m1991k("点击-支付成功-广告" + i + "-跳转");
                    unicomPaySDK = UnicomPaySDK.getInstance();
                }
            } else if (!"changeBackGround".equals(wPBannerInfoBean.getBannerType())) {
                return;
            } else {
                if (!wPBannerInfoBean.isChange()) {
                    m1991k("点击-支付成功-广告" + i + "-领取");
                    c13700a = (C13700a) this.f24311a;
                    str = this.f20160x;
                    c13700a.m128a(str, wPBannerInfoBean.getRuleId(), wPBannerInfoBean.getBannerDiscountMsg(), i);
                    return;
                } else if (TextUtils.isEmpty(wPBannerInfoBean.getUrl())) {
                    return;
                } else {
                    m1991k("点击-支付成功-广告" + i + "-跳转");
                    unicomPaySDK = UnicomPaySDK.getInstance();
                }
            }
            unicomPaySDK.getNativeFunctionCallback().openWebview(this, wPBannerInfoBean.getUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p471q.InterfaceC13674b
    /* renamed from: a */
    public final void mo150a(WPQOpenResultBean wPQOpenResultBean) {
        if (wPQOpenResultBean == null) {
            return;
        }
        try {
            WPResult<WPUnionPayResultBean> wPResult = this.f20161y;
            if (wPResult != null && wPResult.getData() != null) {
                C14239e m51a = C14239e.m51a(C14239e.m47a(wPQOpenResultBean.getPhoneNo(), "QPAY_RESULT_SIGN", wPQOpenResultBean.getSignTokenId(), this.f20161y.getData().getMerNo(), this.f20145C));
                this.f20144B = m51a;
                if (!m51a.isAdded() && !this.f20144B.isVisible()) {
                    ImageView imageView = this.f20148l;
                    if (imageView != null) {
                        imageView.postDelayed(new RunnableC10602i(), 1500L);
                        return;
                    }
                    return;
                }
                this.f20144B.dismissAllowingStateLoss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p471q.InterfaceC13674b
    /* renamed from: a */
    public final void mo149a(WPQOpenResultBean wPQOpenResultBean, boolean z) {
        if (wPQOpenResultBean != null) {
            try {
                if (wPQOpenResultBean.isSignSuccess()) {
                    m6161l0();
                    if (z) {
                        ImageView imageView = this.f20148l;
                        if (imageView != null) {
                            imageView.postDelayed(new RunnableC10599f(), 1500L);
                        }
                    } else {
                        m6157p0();
                    }
                    C13663u c13663u = C10546a.C10576i.f20125a.f20053c;
                    if (c13663u != null) {
                        WPQPayUserInfoBean m164a = c13663u.m164a(wPQOpenResultBean.getUserNo());
                        if (m164a == null) {
                            m164a = new WPQPayUserInfoBean();
                            m164a.setUserNo(wPQOpenResultBean.getUserNo());
                        }
                        m164a.setPayToken(wPQOpenResultBean.getPayToken());
                        c13663u.m165a(m164a);
                        return;
                    }
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        mo1790i(getResources().getString(C10531R.string.wp_open_result_fail_in_pay_result));
    }

    @Override // p471q.InterfaceC13674b
    /* renamed from: a */
    public final void mo148a(String str) {
        m2005a(str, getResources().getString(C10531R.string.wp_comm_confirm), new C12048b.InterfaceC12052d() { // from class: com.unicom.pay.modules.result.ui.-$$Lambda$aXFp3TvRD02YnW3uoQfnp_7T6jY
            @Override // p395i.C12048b.InterfaceC12052d
            public final void onClick(View view) {
                WPPayResultActivity.m6164a(view);
            }
        });
    }
}
