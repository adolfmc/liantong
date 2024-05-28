package com.unicom.pay.normal.order.p359ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.ltzf.passguard.C1730a;
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
import com.unicom.pay.modules.result.p356ui.WPPayResultActivity;
import com.unicom.pay.modules.verify.bean.WPUnionPayResultBean;
import com.unicom.pay.normal.discount.bean.WPDiscountDetailBean;
import com.unicom.pay.normal.discount.bean.WPDiscountInfoBean;
import com.unicom.pay.normal.order.bean.WPDefaultOrderInfoBean;
import com.unicom.pay.normal.order.bean.WPDiscountQueryBean;
import com.unicom.pay.normal.order.bean.WPFqInfoBean;
import com.unicom.pay.normal.order.bean.WPFqNumInfoBean;
import com.unicom.pay.normal.order.bean.WPInstalmentBankListBean;
import com.unicom.pay.normal.order.bean.WPPayBeforeBean;
import com.unicom.pay.normal.order.bean.WPPayInfoBean;
import com.unicom.pay.normal.order.bean.WPUnionFqInfoBean;
import com.unicom.pay.normal.order.p359ui.C10681b;
import com.unicom.pay.qpay.open.bean.WPQPayUserInfoBean;
import com.unicom.pay.utils.buried.WPTrendsEventsUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import p082a0.C0788c;
import p089b0.InterfaceC1468b;
import p091c0.C1506b;
import p091c0.C1515c;
import p091c0.C1517d;
import p385d0.AbstractActivityC11796a;
import p385d0.C11817e;
import p385d0.RunnableC11816d;
import p387e0.C11849b;
import p387e0.C11860g;
import p387e0.C11866j;
import p387e0.C11873n;
import p393h.EnumC12015g;
import p411o.AbstractC12375a;
import p470p0.C13636a;
import p470p0.C13646i;
import p470p0.C13659r;
import p470p0.C13662t;
import p473r.C13707b;
import p481v.C14232a;
import p481v.C14239e;
import p482w.C14255c;
import p482w.C14262f;

@NBSInstrumented
/* renamed from: com.unicom.pay.normal.order.ui.WPInstalmentActivity */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPInstalmentActivity extends AbstractActivityC11796a<C1506b> implements InterfaceC1468b, DataCallback, C10681b.InterfaceC10682a, C11849b.InterfaceC11851b, C11860g.InterfaceC11862b, C11866j.InterfaceC11868b, C11873n.InterfaceC11875b {

    /* renamed from: A */
    public Button f20230A;

    /* renamed from: B */
    public View f20231B;

    /* renamed from: C */
    public RelativeLayout f20232C;

    /* renamed from: D */
    public TextView f20233D;

    /* renamed from: E */
    public TextView f20234E;

    /* renamed from: F */
    public C11873n f20235F;

    /* renamed from: G */
    public C11817e f20236G;

    /* renamed from: H */
    public C0788c f20237H;

    /* renamed from: I */
    public C10681b f20238I;

    /* renamed from: J */
    public CountDownTimerC10654a f20239J;

    /* renamed from: K */
    public long f20240K;

    /* renamed from: L */
    public WPUnionFqInfoBean f20241L;

    /* renamed from: M */
    public String f20242M;

    /* renamed from: N */
    public WPDefaultOrderInfoBean f20243N;

    /* renamed from: O */
    public String f20244O = "";

    /* renamed from: P */
    public boolean f20245P = true;

    /* renamed from: Q */
    public boolean f20246Q = true;
    public NBSTraceUnit _nbs_trace;

    /* renamed from: s */
    public LinearLayout f20247s;

    /* renamed from: t */
    public TextView f20248t;

    /* renamed from: u */
    public TextView f20249u;

    /* renamed from: v */
    public TextView f20250v;

    /* renamed from: w */
    public TextView f20251w;

    /* renamed from: x */
    public TextView f20252x;

    /* renamed from: y */
    public TextView f20253y;

    /* renamed from: z */
    public RecyclerView f20254z;

    /* renamed from: com.unicom.pay.normal.order.ui.WPInstalmentActivity$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class CountDownTimerC10654a extends CountDownTimer {
        public CountDownTimerC10654a(long j) {
            super(j, 1000L);
        }

        @Override // android.os.CountDownTimer
        public final void onFinish() {
            WPInstalmentActivity wPInstalmentActivity = WPInstalmentActivity.this;
            wPInstalmentActivity.f20240K = 0L;
            wPInstalmentActivity.f20248t.setText("00");
            WPInstalmentActivity.this.f20249u.setText("00");
            WPInstalmentActivity.this.f20250v.setText("00");
            WPInstalmentActivity wPInstalmentActivity2 = WPInstalmentActivity.this;
            WPDefaultOrderInfoBean wPDefaultOrderInfoBean = wPInstalmentActivity2.f20243N;
            if (wPDefaultOrderInfoBean == null || C13636a.f27478b <= 0) {
                return;
            }
            ((C1506b) wPInstalmentActivity2.f24311a).m22184a(wPDefaultOrderInfoBean.getData().getTradeOrderNo());
        }

        @Override // android.os.CountDownTimer
        public final void onTick(long j) {
            String valueOf;
            String valueOf2;
            String valueOf3;
            WPInstalmentActivity wPInstalmentActivity = WPInstalmentActivity.this;
            wPInstalmentActivity.f20240K = j;
            int i = (int) ((j / 1000) % 60);
            int i2 = (int) ((j / 60000) % 60);
            int i3 = (int) (j / 3600000);
            TextView textView = wPInstalmentActivity.f20248t;
            if (i3 / 10 == 0) {
                valueOf = "0" + i3;
            } else {
                valueOf = String.valueOf(i3);
            }
            textView.setText(valueOf);
            TextView textView2 = WPInstalmentActivity.this.f20249u;
            if (i2 / 10 == 0) {
                valueOf2 = "0" + i2;
            } else {
                valueOf2 = String.valueOf(i2);
            }
            textView2.setText(valueOf2);
            TextView textView3 = WPInstalmentActivity.this.f20250v;
            if (i / 10 == 0) {
                valueOf3 = "0" + i;
            } else {
                valueOf3 = String.valueOf(i);
            }
            textView3.setText(valueOf3);
        }
    }

    @Override // p089b0.InterfaceC1468b
    /* renamed from: B */
    public final void mo6151B() {
    }

    @Override // p089b0.InterfaceC1468b
    /* renamed from: C */
    public final void mo6150C() {
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, p393h.InterfaceC12016h
    /* renamed from: D */
    public final String mo56D() {
        return this.f20246Q ? "分期页-未绑卡" : "分期页-已绑卡";
    }

    @Override // p089b0.InterfaceC1468b
    /* renamed from: L */
    public final void mo6149L() {
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, p393h.InterfaceC12016h
    /* renamed from: O */
    public final String mo53O() {
        return this.f20246Q ? "wp200" : "wp300";
    }

    @Override // p089b0.InterfaceC1468b
    /* renamed from: Q */
    public final void mo6148Q() {
        if (this.f20235F != null) {
            m6136c(true);
            C11873n c11873n = this.f20235F;
            c11873n.notifyItemChanged(c11873n.f24181d);
        }
    }

    @Override // p089b0.InterfaceC1472f
    /* renamed from: V */
    public final String mo6127V() {
        return this.f20243N.getData().getTradeOrderNo();
    }

    /* renamed from: a */
    public final void m6147a(long j) {
        CountDownTimerC10654a countDownTimerC10654a = this.f20239J;
        if (countDownTimerC10654a != null) {
            countDownTimerC10654a.cancel();
            this.f20239J = null;
        }
        this.f20248t.setText(String.valueOf((int) (j / 3600000)));
        this.f20249u.setText(String.valueOf((int) ((j / 60000) % 60)));
        this.f20250v.setText(String.valueOf((int) ((j / 1000) % 60)));
        CountDownTimerC10654a countDownTimerC10654a2 = new CountDownTimerC10654a(j);
        this.f20239J = countDownTimerC10654a2;
        countDownTimerC10654a2.start();
    }

    @Override // p089b0.InterfaceC1468b
    /* renamed from: a */
    public final void mo6146a(WPResult<WPUnionPayResultBean> wPResult) {
        m2086f(wPResult);
    }

    @Override // p387e0.C11860g.InterfaceC11862b
    /* renamed from: a */
    public final void mo2063a(WPDiscountDetailBean wPDiscountDetailBean, boolean z, String str) {
    }

    @Override // p387e0.C11849b.InterfaceC11851b
    /* renamed from: a */
    public final void mo2067a(WPDiscountInfoBean wPDiscountInfoBean, boolean z) {
    }

    /* renamed from: a */
    public final void m6143a(WPFqInfoBean wPFqInfoBean, C11866j.C11867a c11867a) {
        if (!wPFqInfoBean.isJumpWeb() && !WPFqInfoBean.WFQ_XYK_PWD.equals(wPFqInfoBean.getType())) {
            if (this.f20243N != null) {
                ((C1506b) this.f24311a).m22182a(wPFqInfoBean.getExpand(), this.f20243N.getData().getTradeOrderNo(), c11867a.getAdapterPosition(), 1);
                return;
            }
            return;
        }
        if (WPFqInfoBean.WFQ_XYK_PWD.equals(wPFqInfoBean.getType())) {
            m6135e(wPFqInfoBean.getJumpUrl());
        } else {
            m2085l(wPFqInfoBean.getJumpUrl());
            C11817e c11817e = this.f20236G;
            if (c11817e == null || !c11817e.isVisible()) {
                return;
            }
        }
        this.f20236G.dismissAllowingStateLoss();
    }

    @Override // p089b0.InterfaceC1468b
    /* renamed from: a */
    public final void mo6140a(WPUnionFqInfoBean wPUnionFqInfoBean) {
        this.f20241L = wPUnionFqInfoBean;
        m6131q0();
    }

    @Override // p089b0.InterfaceC1468b
    /* renamed from: a */
    public final void mo6139a(WPUnionFqInfoBean wPUnionFqInfoBean, int i, int i2) {
        C11817e c11817e;
        this.f20241L = wPUnionFqInfoBean;
        m6131q0();
        if (i2 == 0) {
            C11873n c11873n = this.f20235F;
            c11873n.f24181d = i;
            c11873n.notifyDataSetChanged();
        } else if (i2 == 1 && (c11817e = this.f20236G) != null && c11817e.isVisible()) {
            C11866j c11866j = this.f20236G.f24071i;
            c11866j.f24159e = i;
            c11866j.notifyDataSetChanged();
            this.f20236G.dismissAllowingStateLoss();
        }
    }

    @Override // p089b0.InterfaceC1468b
    /* renamed from: a */
    public final void mo6138a(String str, String str2) {
        this.f20232C.setVisibility(0);
        this.f20233D.setText(str);
        this.f20234E.setText(str2);
        this.f20230A.setVisibility(8);
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: a0 */
    public final AbstractC12375a mo2002a0() {
        return new C1506b();
    }

    @Override // p385d0.AbstractActivityC11796a, p089b0.InterfaceC1472f
    /* renamed from: b */
    public final void mo2093b(boolean z) {
        m2082n0();
    }

    @Override // p089b0.InterfaceC1468b
    /* renamed from: c */
    public final void mo6137c(WPResult<WPUnionPayResultBean> wPResult) {
        if (wPResult == null) {
            return;
        }
        if ("0000".equals(wPResult.getCode())) {
            if (this.f20230A != null) {
                mo1791a(C10531R.string.up_other_pay_success);
                this.f20230A.postDelayed(new RunnableC11816d(this, wPResult), 1000L);
                return;
            }
            return;
        }
        m2086f(wPResult);
    }

    @Override // p385d0.AbstractActivityC11796a
    /* renamed from: c */
    public final void mo2089c(String str, String str2) {
    }

    @Override // p089b0.InterfaceC1472f
    /* renamed from: d */
    public final void mo6093d(String str) {
        C1506b c1506b = (C1506b) this.f24311a;
        c1506b.getClass();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("tradeOrderNo", str);
        c1506b.m1797a(C14255c.C14256a.f27777a.m30a(C14262f.f27790C, hashMap, new C1517d(c1506b, c1506b)));
    }

    @Override // p385d0.AbstractActivityC11796a
    /* renamed from: e */
    public final void mo2087e(WPResult<WPUnionPayResultBean> wPResult) {
        if (wPResult == null) {
            mo6093d(mo6127V());
        } else {
            m2082n0();
        }
    }

    /* renamed from: e */
    public final void m6135e(String str) {
        C10681b c10681b = this.f20238I;
        c10681b.f20385n = str;
        c10681b.show(getSupportFragmentManager(), "mPwdDialog");
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: e0 */
    public final int mo1998e0() {
        return C10531R.C10535layout.wp_instalment;
    }

    @Override // com.unicom.pay.normal.order.p359ui.C10681b.InterfaceC10682a
    /* renamed from: f */
    public final void mo6069f(String str) {
        this.f24029p = true;
        m2085l(str);
    }

    @Override // p385d0.AbstractActivityC11796a, p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: f0 */
    public final void mo1997f0() {
        Intent intent = getIntent();
        if (intent != null) {
            this.f20243N = (WPDefaultOrderInfoBean) intent.getParcelableExtra("defaultOrderInfo");
            this.f20242M = intent.getStringExtra("fqExpand");
            this.f24030q = intent.getStringExtra(WPQPayUserInfoBean.QPAY_COLUMN_ID);
            WPDefaultOrderInfoBean wPDefaultOrderInfoBean = this.f20243N;
            if (wPDefaultOrderInfoBean != null) {
                ((C1506b) this.f24311a).m22181b("", wPDefaultOrderInfoBean.getData().getTradeOrderNo());
            }
        }
    }

    @Override // android.app.Activity
    public final void finish() {
        m6132p0();
        super.finish();
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: g0 */
    public final void mo1996g0() {
        m1993j(getResources().getString(C10531R.string.wp_up_pay_title));
        Typeface createFromAsset = Typeface.createFromAsset(getAssets(), "Bebas-Regular.ttf");
        this.f20247s = (LinearLayout) findViewById(C10531R.C10534id.up_instalment_time_ll);
        TextView textView = (TextView) findViewById(C10531R.C10534id.up_instalment_hour_tv);
        this.f20248t = textView;
        textView.setTypeface(createFromAsset);
        TextView textView2 = (TextView) findViewById(C10531R.C10534id.up_instalment_min_tv);
        this.f20249u = textView2;
        textView2.setTypeface(createFromAsset);
        TextView textView3 = (TextView) findViewById(C10531R.C10534id.up_instalment_sec_tv);
        this.f20250v = textView3;
        textView3.setTypeface(createFromAsset);
        LinearLayout linearLayout = (LinearLayout) findViewById(C10531R.C10534id.up_instalment_discount_amount_ll);
        TextView textView4 = (TextView) findViewById(C10531R.C10534id.up_instalment_discount_amount_tv);
        this.f20251w = textView4;
        textView4.setTypeface(createFromAsset);
        TextView textView5 = (TextView) findViewById(C10531R.C10534id.up_instalment_real_amount_tv);
        this.f20252x = textView5;
        textView5.setTypeface(createFromAsset);
        this.f20253y = (TextView) findViewById(C10531R.C10534id.up_instalment_desc_tv);
        RecyclerView recyclerView = (RecyclerView) findViewById(C10531R.C10534id.up_instalment_pay_method_list_rv);
        this.f20254z = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        C11873n c11873n = new C11873n(this);
        this.f20235F = c11873n;
        this.f20254z.setAdapter(c11873n);
        this.f20254z.setNestedScrollingEnabled(false);
        this.f20254z.setHasFixedSize(true);
        this.f20235F.f24180c = this;
        Button button = (Button) findViewById(C10531R.C10534id.up_instalment_pay_confirm_btn);
        this.f20230A = button;
        button.setOnClickListener(this);
        this.f20232C = (RelativeLayout) findViewById(C10531R.C10534id.up_error_rl);
        this.f20233D = (TextView) findViewById(C10531R.C10534id.up_error_title_tv);
        this.f20234E = (TextView) findViewById(C10531R.C10534id.up_error_desc_tv);
        View findViewById = findViewById(C10531R.C10534id.up_instalment_blank_view);
        this.f20231B = findViewById;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) findViewById.getLayoutParams();
        layoutParams.height = C13659r.m169a(this) / 3;
        this.f20231B.setLayoutParams(layoutParams);
        int i = C11817e.f24069m;
        Bundle bundle = new Bundle();
        C11817e c11817e = new C11817e();
        c11817e.setArguments(bundle);
        this.f20236G = c11817e;
        int i2 = C0788c.f2440m;
        Bundle bundle2 = new Bundle();
        C0788c c0788c = new C0788c();
        c0788c.setArguments(bundle2);
        this.f20237H = c0788c;
        int i3 = C10681b.f20377o;
        Bundle bundle3 = new Bundle();
        bundle3.putInt("type", 1);
        C10681b c10681b = new C10681b();
        c10681b.setArguments(bundle3);
        this.f20238I = c10681b;
        c10681b.f20384m = this;
    }

    @Override // com.unicom.pay.normal.order.p359ui.C10681b.InterfaceC10682a
    /* renamed from: h */
    public final void mo6068h(String str) {
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: h0 */
    public final void mo1995h0() {
        EventBus.getDefault().register(this);
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: k0 */
    public final void mo1990k0() {
        EventBus.getDefault().unregister(this);
    }

    @Override // p385d0.AbstractActivityC11796a
    /* renamed from: l0 */
    public final void mo2084l0() {
    }

    @Override // com.unicom.pay.normal.order.p359ui.C10681b.InterfaceC10682a
    /* renamed from: m */
    public final void mo6067m() {
    }

    @Override // p089b0.InterfaceC1468b
    /* renamed from: o */
    public final void mo6134o() {
        C11873n c11873n = this.f20235F;
        if (c11873n != null) {
            c11873n.notifyItemChanged(c11873n.f24181d);
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.List<com.unicom.pay.normal.order.bean.WPFqInfoBean>, java.util.ArrayList] */
    /* renamed from: o0 */
    public final WPFqInfoBean m6133o0() {
        C11873n c11873n = this.f20235F;
        if (c11873n != null) {
            int i = c11873n.f24181d;
            if (i >= 0) {
                return (WPFqInfoBean) c11873n.f24179b.get(i);
            }
            return null;
        }
        return null;
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public final void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("fqOrder", this.f20240K);
        setResult(-1, intent);
        super.onBackPressed();
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.view.View.OnClickListener
    public void onClick(View view) {
        WPFqInfoBean m6133o0;
        String str;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        if (m2001b0()) {
            NBSActionInstrumentation.onClickEventExit();
        } else if (view.getId() == C10531R.C10534id.wapay_header_arrow) {
            onBackPressed();
            NBSActionInstrumentation.onClickEventExit();
        } else if (!this.f20245P) {
            NBSActionInstrumentation.onClickEventExit();
        } else {
            if (view.getId() == C10531R.C10534id.up_instalment_pay_confirm_btn && (m6133o0 = m6133o0()) != null) {
                if (m6133o0.isJumpWeb()) {
                    m2085l(m6133o0.getJumpUrl());
                    str = "添加银行卡";
                } else if (WPFqInfoBean.WFQ_XYK_PWD.equals(m6133o0.getType())) {
                    m6135e(m6133o0.getJumpUrl());
                } else {
                    if (m6133o0.getFqNumInfos() != null && !m6133o0.getFqNumInfos().isEmpty()) {
                        Iterator<WPFqNumInfoBean> it = m6133o0.getFqNumInfos().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            WPFqNumInfoBean next = it.next();
                            if (next.isDefaultChecked() && m6133o0.getFqTitle() != null) {
                                ((C1506b) this.f24311a).m22180b(next.getExpand(), this.f20243N.getData().getTradeOrderNo(), m6133o0.getFqTitle().getMethodName(), m6133o0.getFqTitle().getIconUrl(), WPPayInfoBean.WFQ);
                                break;
                            }
                        }
                    } else {
                        ((C1506b) this.f24311a).m22180b(m6133o0.getExpand(), this.f20243N.getData().getTradeOrderNo(), m6133o0.getMethodName(), m6133o0.getIconUrl(), "");
                    }
                    str = "确认支付-分期";
                }
                m1991k(str);
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 403);
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        m6132p0();
        super.onDestroy();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public final void onNewIntent(Intent intent) {
        WPDefaultOrderInfoBean wPDefaultOrderInfoBean;
        super.onNewIntent(intent);
        if (this.f20243N != null) {
            if (!intent.hasExtra("unionPayResult")) {
                ((C1506b) this.f24311a).m22181b("", this.f20243N.getData().getTradeOrderNo());
                return;
            }
            WPResult wPResult = (WPResult) intent.getParcelableExtra("unionPayResult");
            if (wPResult == null || (wPDefaultOrderInfoBean = this.f20243N) == null) {
                return;
            }
            String tradeOrderNo = wPDefaultOrderInfoBean.getData().getTradeOrderNo();
            boolean z = this.f24031r;
            Bundle bundle = new Bundle();
            bundle.putString("unionOrderId", tradeOrderNo);
            bundle.putParcelable("unionPayResult", wPResult);
            bundle.putBoolean("signQPay", z);
            m2008a(bundle, WPPayResultActivity.class);
            C13636a.m190a();
        }
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // com.unicom.pay.common.callback.DataCallback
    public final void onResult(String str) {
        WPFqInfoBean m6133o0 = m6133o0();
        if (m6133o0 == null || this.f20243N == null) {
            return;
        }
        if (m6133o0.getFqNumInfos() == null || m6133o0.getFqNumInfos().isEmpty()) {
            ((C1506b) this.f24311a).m22166a(this.f20244O, str, m6133o0.getExpand(), this.f20243N.getData().getTradeOrderNo(), WPPayInfoBean.WFQ);
            return;
        }
        for (WPFqNumInfoBean wPFqNumInfoBean : m6133o0.getFqNumInfos()) {
            if (wPFqNumInfoBean.isDefaultChecked()) {
                ((C1506b) this.f24311a).m22166a(this.f20244O, str, m6133o0.getExpand(), this.f20243N.getData().getTradeOrderNo(), WPPayInfoBean.WFQ);
                return;
            }
        }
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        WPDefaultOrderInfoBean wPDefaultOrderInfoBean = this.f20243N;
        if (wPDefaultOrderInfoBean != null && !TextUtils.isEmpty(wPDefaultOrderInfoBean.getData().getTradeOrderNo()) && this.f24029p) {
            this.f24029p = false;
        }
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
        return this.f20246Q ? "98U01170wp200" : "98U01170wp300";
    }

    /* renamed from: p0 */
    public final void m6132p0() {
        CountDownTimerC10654a countDownTimerC10654a = this.f20239J;
        if (countDownTimerC10654a != null) {
            countDownTimerC10654a.cancel();
            this.f20239J = null;
        }
        C10681b c10681b = this.f20238I;
        if (c10681b != null && c10681b.isVisible()) {
            this.f20238I.dismissAllowingStateLoss();
        }
        C11817e c11817e = this.f20236G;
        if (c11817e != null && c11817e.isVisible()) {
            this.f20236G.dismissAllowingStateLoss();
        }
        C0788c c0788c = this.f20237H;
        if (c0788c != null && c0788c.isVisible()) {
            this.f20237H.dismissAllowingStateLoss();
        }
        C14232a c14232a = this.f24027n;
        if (c14232a != null && c14232a.isVisible()) {
            this.f24027n.dismissAllowingStateLoss();
        }
        C14239e c14239e = this.f24028o;
        if (c14239e == null || !c14239e.isVisible()) {
            return;
        }
        this.f24028o.dismissAllowingStateLoss();
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [java.util.List<com.unicom.pay.normal.order.bean.WPFqInfoBean>, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.util.List<com.unicom.pay.normal.order.bean.WPFqInfoBean>, java.util.ArrayList] */
    /* renamed from: q0 */
    public final void m6131q0() {
        String str;
        WPUnionFqInfoBean wPUnionFqInfoBean = this.f20241L;
        if (wPUnionFqInfoBean == null || wPUnionFqInfoBean.getOrderInfo() == null) {
            return;
        }
        WPTrendsEventsUtils.setRealPayAmount(this.f20241L.getOrderInfo().getRealPayAmout());
        if (!TextUtils.isEmpty(this.f20241L.getOrderInfo().getRealPayAmout())) {
            C13662t c13662t = new C13662t("¥");
            String m181a = C13646i.m181a(this.f20241L.getOrderInfo().getRealPayAmout());
            c13662t.m166a();
            c13662t.f27499a = m181a;
            c13662t.f27504f = 1.5f;
            c13662t.m166a();
            this.f20251w.setText(c13662t.f27507i);
            TextView textView = this.f20252x;
            StringBuilder m22016a = C1730a.m22016a("¥");
            m22016a.append(C13646i.m181a(this.f20241L.getOrderInfo().getOrderAmout()));
            textView.setText(m22016a.toString());
            this.f20252x.setPaintFlags(16);
            if (this.f20241L.getOrderInfo().getRealPayAmout().equals(this.f20241L.getOrderInfo().getOrderAmout())) {
                this.f20252x.setVisibility(8);
            } else {
                this.f20252x.setVisibility(0);
            }
        } else {
            C13662t c13662t2 = new C13662t("¥");
            String m181a2 = C13646i.m181a(this.f20241L.getOrderInfo().getOrderAmout());
            c13662t2.m166a();
            c13662t2.f27499a = m181a2;
            c13662t2.f27504f = 1.5f;
            c13662t2.m166a();
            this.f20251w.setText(c13662t2.f27507i);
            this.f20252x.setVisibility(8);
        }
        this.f20253y.setText(this.f20241L.getOrderInfo().getGoodName());
        try {
            if (TextUtils.isEmpty(this.f20241L.getOrderInfo().getRealPayAmout())) {
                str = "-";
            } else {
                Long valueOf = Long.valueOf(Long.parseLong(this.f20241L.getOrderInfo().getOrderAmout()));
                Long valueOf2 = Long.valueOf(Long.parseLong(this.f20241L.getOrderInfo().getRealPayAmout()));
                str = Math.abs(valueOf.longValue() - valueOf2.longValue()) + "";
            }
            WPTrendsEventsUtils.setPreferentialAmount(str);
        } catch (Exception unused) {
        }
        try {
            long parseLong = TextUtils.isEmpty(this.f20241L.getOrderInfo().getCountdownTime()) ? 0L : Long.parseLong(this.f20241L.getOrderInfo().getCountdownTime());
            if (parseLong <= 0) {
                this.f20247s.setVisibility(8);
            } else {
                m6147a(parseLong);
            }
        } catch (Exception e) {
            this.f20247s.setVisibility(8);
            e.printStackTrace();
        }
        Iterator<WPFqInfoBean> it = this.f20241L.getFqInfos().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            WPFqInfoBean next = it.next();
            if (next.isDefaultChecked()) {
                WPTrendsEventsUtils.setPayMethod(next.getMethodName());
                boolean isJumpWeb = next.isJumpWeb();
                this.f20246Q = isJumpWeb;
                C11817e c11817e = this.f20236G;
                if (c11817e != null) {
                    c11817e.f24074l = isJumpWeb;
                }
                WPTrendsEventsUtils.setPayMethod("勾选-银行卡");
            }
        }
        if (this.f20241L.getFqInfos() == null || this.f20241L.getFqInfos().isEmpty()) {
            this.f20254z.setVisibility(8);
        } else {
            C11873n c11873n = this.f20235F;
            List<WPFqInfoBean> fqInfos = this.f20241L.getFqInfos();
            c11873n.f24181d = -1;
            c11873n.f24179b.clear();
            c11873n.f24179b.addAll(fqInfos);
            c11873n.notifyDataSetChanged();
            this.f20254z.setVisibility(0);
        }
        this.f20230A.setText(this.f20241L.getButtonInfo());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void reloadRouterResource(EnumC12015g enumC12015g) {
        if (enumC12015g == EnumC12015g.BANK_CHANGE) {
            ((C1506b) this.f24311a).m22181b((String) enumC12015g.f24357a, this.f20243N.getData().getTradeOrderNo());
        } else if (enumC12015g == EnumC12015g.PWD_CHANGE) {
            ((C1506b) this.f24311a).m22181b("", this.f20243N.getData().getTradeOrderNo());
        }
    }

    @Override // p089b0.InterfaceC1472f
    /* renamed from: x */
    public final String mo6077x() {
        return null;
    }

    @Override // p089b0.InterfaceC1472f
    /* renamed from: y */
    public final String mo6076y() {
        WPFqInfoBean m6133o0 = m6133o0();
        if (m6133o0 == null) {
            return "";
        }
        if (m6133o0.getFqNumInfos() == null || m6133o0.getFqNumInfos().isEmpty()) {
            return m6133o0.getExpand();
        }
        for (WPFqNumInfoBean wPFqNumInfoBean : m6133o0.getFqNumInfos()) {
            if (wPFqNumInfoBean.isDefaultChecked()) {
                return wPFqNumInfoBean.getExpand();
            }
        }
        return "";
    }

    /* renamed from: c */
    public final void m6136c(boolean z) {
        this.f20245P = z;
        C11873n c11873n = this.f20235F;
        if (c11873n != null) {
            c11873n.f24182e = z;
        }
    }

    /* renamed from: a */
    public final void m6144a(WPFqInfoBean wPFqInfoBean) {
        if (this.f20243N != null) {
            m6136c(false);
            C1506b c1506b = (C1506b) this.f24311a;
            String expand = wPFqInfoBean.getExpand();
            String tradeOrderNo = this.f20243N.getData().getTradeOrderNo();
            c1506b.getClass();
            c1506b.m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27794G, C13707b.m126a("expand", expand, "tradeOrderNo", tradeOrderNo), false, new C1515c(c1506b, c1506b)));
        } else {
            C11873n c11873n = this.f20235F;
            if (c11873n != null) {
                c11873n.notifyItemChanged(c11873n.f24181d);
            }
        }
        m1991k(this.f20246Q ? "添加信用卡>" : "已绑信用卡>");
    }

    @Override // p089b0.InterfaceC1468b
    /* renamed from: a */
    public final void mo6141a(WPPayBeforeBean wPPayBeforeBean) {
        this.f24025l = 0;
        WPQPayUserInfoBean m164a = C10546a.C10576i.f20125a.f20053c.m164a(this.f24030q);
        this.f24026m = m164a;
        ((C1506b) this.f24311a).m22169a(m164a, wPPayBeforeBean);
    }

    @Override // p089b0.InterfaceC1468b
    /* renamed from: a */
    public final void mo6142a(WPInstalmentBankListBean wPInstalmentBankListBean) {
        if (this.f20235F != null) {
            m6136c(true);
            C11873n c11873n = this.f20235F;
            c11873n.notifyItemChanged(c11873n.f24181d);
        }
        C11817e c11817e = this.f20236G;
        c11817e.f24072j.clear();
        c11817e.f24072j.addAll(wPInstalmentBankListBean.getUserBankList());
        c11817e.f24072j.addAll(wPInstalmentBankListBean.getMerBankList());
        c11817e.f24073k = wPInstalmentBankListBean.getUserBankList().size();
        this.f20236G.show(getSupportFragmentManager(), "mInstalmentBankMethodsFragment");
    }

    @Override // p089b0.InterfaceC1468b
    /* renamed from: a */
    public final void mo6145a(WPDiscountQueryBean wPDiscountQueryBean) {
        if (wPDiscountQueryBean != null) {
            this.f20237H.m22221a(wPDiscountQueryBean);
            this.f20237H.show(getSupportFragmentManager(), "mDiscountDialogFragment");
        }
        C11873n c11873n = this.f20235F;
        if (c11873n != null) {
            c11873n.notifyItemChanged(c11873n.f24181d);
        }
    }
}
