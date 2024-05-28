package com.unicom.pay.normal.order.p359ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.ltzf.passguard.C1730a;
import cn.microdone.txcrypto.C1731txcrypto;
import com.fort.andjni.JniLib;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.unicom.pay.C10531R;
import com.unicom.pay.C10546a;
import com.unicom.pay.UnicomPaySDK;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.common.callback.DataCallback;
import com.unicom.pay.common.callback.NativeFunctionCallBack;
import com.unicom.pay.modules.result.bean.WPCheckSignResult;
import com.unicom.pay.modules.result.bean.WPUserStatusBean;
import com.unicom.pay.modules.verify.bean.WPQOpenResultBean;
import com.unicom.pay.modules.verify.bean.WPQPayResultBean;
import com.unicom.pay.modules.verify.bean.WPUnionPayResultBean;
import com.unicom.pay.normal.bank.p358ui.WPAuthCreditBankActivity;
import com.unicom.pay.normal.discount.bean.WPDiscountDetailBean;
import com.unicom.pay.normal.discount.bean.WPDiscountInfoBean;
import com.unicom.pay.normal.discount.bean.WPDiscountListBean;
import com.unicom.pay.normal.order.bean.WPCompleteUserInfoNotifyBean;
import com.unicom.pay.normal.order.bean.WPDefaultOrderInfoBean;
import com.unicom.pay.normal.order.bean.WPDiscountQueryBean;
import com.unicom.pay.normal.order.bean.WPDzqInfosBean;
import com.unicom.pay.normal.order.bean.WPMobPayBean;
import com.unicom.pay.normal.order.bean.WPNoticeInfoBean;
import com.unicom.pay.normal.order.bean.WPNoticeListInfoBean;
import com.unicom.pay.normal.order.bean.WPOrderPayBeforeBean;
import com.unicom.pay.normal.order.bean.WPOtherPayResultBean;
import com.unicom.pay.normal.order.bean.WPPayBeforeBean;
import com.unicom.pay.normal.order.bean.WPPayInfoBean;
import com.unicom.pay.normal.order.bean.WPQueryPayInfoListBean;
import com.unicom.pay.normal.order.bean.WPToolFqNumInfoBean;
import com.unicom.pay.normal.order.bean.WPUnionOrderInfoBean;
import com.unicom.pay.normal.order.bean.WPUpdateMethodResultBean;
import com.unicom.pay.normal.order.bean.WPZFBResultBean;
import com.unicom.pay.normal.order.p359ui.C10679a;
import com.unicom.pay.normal.order.p359ui.C10681b;
import com.unicom.pay.qpay.open.bean.WPAgreementBean;
import com.unicom.pay.qpay.open.bean.WPQPayUserInfoBean;
import com.unicom.pay.utils.buried.WPTrendsEventsUtils;
import com.unicom.pay.widget.ticker.TickerView;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import p082a0.C0777a;
import p082a0.C0784b;
import p082a0.C0788c;
import p089b0.InterfaceC1470d;
import p091c0.C1521f;
import p091c0.C1529j;
import p091c0.C1548k;
import p091c0.C1552m;
import p385d0.AbstractActivityC11796a;
import p385d0.C11819f;
import p385d0.C11821h;
import p385d0.C11824k;
import p385d0.RunnableC11820g;
import p387e0.C11849b;
import p387e0.C11854d;
import p387e0.C11860g;
import p387e0.C11886t;
import p387e0.C11889u;
import p393h.EnumC12015g;
import p394h0.View$OnClickListenerC12020c;
import p395i.AbstractC12045a;
import p395i.C12048b;
import p408n.C12348l;
import p411o.AbstractC12375a;
import p470p0.C13636a;
import p470p0.C13646i;
import p470p0.C13648k;
import p470p0.C13652o;
import p470p0.C13653p;
import p470p0.C13654q;
import p470p0.C13659r;
import p470p0.C13662t;
import p470p0.C13663u;
import p481v.C14232a;
import p481v.C14239e;
import p482w.C14255c;
import p482w.C14262f;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
@NBSInstrumented
/* renamed from: com.unicom.pay.normal.order.ui.WPOrderActivity */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPOrderActivity extends AbstractActivityC11796a<C1529j> implements C0777a.InterfaceC0783f, C0784b.InterfaceC0787c, InterfaceC1470d, DataCallback, C10681b.InterfaceC10682a, C11849b.InterfaceC11851b, C11854d.InterfaceC11856b, C11860g.InterfaceC11862b, C11886t.InterfaceC11887a, C11889u.InterfaceC11898h, C11889u.InterfaceC11900j, View$OnClickListenerC12020c.InterfaceC12022b {

    /* renamed from: N0 */
    public static final /* synthetic */ int f20256N0 = 0;

    /* renamed from: A */
    public TextView f20257A;

    /* renamed from: A0 */
    public String f20258A0;

    /* renamed from: B */
    public ImageView f20259B;

    /* renamed from: B0 */
    public View$OnClickListenerC12020c f20260B0;

    /* renamed from: C */
    public LinearLayout f20261C;

    /* renamed from: C0 */
    public C14239e f20262C0;

    /* renamed from: D */
    public TextView f20263D;

    /* renamed from: D0 */
    public C14239e f20264D0;

    /* renamed from: E */
    public ImageView f20265E;

    /* renamed from: E0 */
    public C14239e f20266E0;

    /* renamed from: F */
    public LinearLayout f20267F;

    /* renamed from: F0 */
    public C12048b f20268F0;

    /* renamed from: G */
    public TextView f20269G;

    /* renamed from: G0 */
    public WPAgreementBean f20270G0;

    /* renamed from: H */
    public ImageView f20271H;

    /* renamed from: I */
    public LinearLayout f20273I;

    /* renamed from: J */
    public TextView f20275J;

    /* renamed from: K */
    public ImageView f20277K;

    /* renamed from: K0 */
    public WPPayBeforeBean f20278K0;

    /* renamed from: L */
    public LinearLayout f20279L;

    /* renamed from: L0 */
    public boolean f20280L0;

    /* renamed from: M */
    public RecyclerView f20281M;

    /* renamed from: M0 */
    public String f20282M0;

    /* renamed from: N */
    public RecyclerView f20283N;

    /* renamed from: O */
    public LinearLayout f20284O;

    /* renamed from: P */
    public RecyclerView f20285P;

    /* renamed from: Q */
    public LinearLayout f20286Q;

    /* renamed from: R */
    public TextView f20287R;

    /* renamed from: S */
    public ImageView f20288S;

    /* renamed from: T */
    public Button f20289T;

    /* renamed from: U */
    public View f20290U;

    /* renamed from: V */
    public RelativeLayout f20291V;

    /* renamed from: W */
    public TextView f20292W;

    /* renamed from: X */
    public TextView f20293X;

    /* renamed from: Y */
    public View f20294Y;

    /* renamed from: Z */
    public ImageView f20295Z;
    public NBSTraceUnit _nbs_trace;

    /* renamed from: a0 */
    public TextView f20296a0;

    /* renamed from: b0 */
    public ImageView f20297b0;

    /* renamed from: c0 */
    public C11889u f20298c0;

    /* renamed from: d0 */
    public C11889u f20299d0;

    /* renamed from: e0 */
    public C11886t f20300e0;

    /* renamed from: f0 */
    public C11824k f20301f0;

    /* renamed from: g0 */
    public C0788c f20302g0;

    /* renamed from: h0 */
    public C0784b f20303h0;

    /* renamed from: i0 */
    public C0777a f20304i0;

    /* renamed from: j0 */
    public C10681b f20305j0;

    /* renamed from: k0 */
    public C10679a f20306k0;

    /* renamed from: l0 */
    public C11819f f20307l0;

    /* renamed from: m0 */
    public CountDownTimerC10670n f20308m0;

    /* renamed from: n0 */
    public String f20309n0;

    /* renamed from: o0 */
    public String f20310o0;

    /* renamed from: p0 */
    public String f20311p0;

    /* renamed from: q0 */
    public String f20312q0;

    /* renamed from: r0 */
    public WPUnionOrderInfoBean f20313r0;

    /* renamed from: s */
    public LinearLayout f20314s;

    /* renamed from: s0 */
    public WPDefaultOrderInfoBean f20315s0;

    /* renamed from: t */
    public TextView f20316t;

    /* renamed from: u */
    public TextView f20318u;

    /* renamed from: u0 */
    public String f20319u0;

    /* renamed from: v */
    public TextView f20320v;

    /* renamed from: v0 */
    public boolean f20321v0;

    /* renamed from: w */
    public TickerView f20322w;

    /* renamed from: w0 */
    public boolean f20323w0;

    /* renamed from: x */
    public TextView f20324x;

    /* renamed from: y */
    public TextView f20326y;

    /* renamed from: z */
    public LinearLayout f20328z;

    /* renamed from: t0 */
    public String f20317t0 = "";

    /* renamed from: x0 */
    public boolean f20325x0 = true;

    /* renamed from: y0 */
    public boolean f20327y0 = false;

    /* renamed from: z0 */
    public int f20329z0 = 1;

    /* renamed from: H0 */
    public boolean f20272H0 = false;

    /* renamed from: I0 */
    public boolean f20274I0 = false;

    /* renamed from: J0 */
    public boolean f20276J0 = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.normal.order.ui.WPOrderActivity$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10655a extends TypeToken<JsonObject> {
    }

    /* renamed from: com.unicom.pay.normal.order.ui.WPOrderActivity$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class RunnableC10656b implements Runnable {
        public RunnableC10656b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            TextView textView;
            try {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) WPOrderActivity.this.f20257A.getLayoutParams();
                if (WPOrderActivity.this.f20257A.getLineCount() > 1) {
                    layoutParams.weight = 1.0f;
                    layoutParams.width = 0;
                    textView = WPOrderActivity.this.f20257A;
                } else {
                    layoutParams.weight = 0.0f;
                    layoutParams.width = -2;
                    textView = WPOrderActivity.this.f20257A;
                }
                textView.setLayoutParams(layoutParams);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: com.unicom.pay.normal.order.ui.WPOrderActivity$c */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10657c implements C12048b.InterfaceC12050b {
        public C10657c() {
        }

        @Override // p395i.C12048b.InterfaceC12050b
        /* renamed from: a */
        public final void mo1801a() {
            WPOrderActivity.this.m2010Z();
            WPOrderActivity wPOrderActivity = WPOrderActivity.this;
            ((C1529j) wPOrderActivity.f24311a).m22172b(wPOrderActivity.f20315s0.getData().getTradeOrderNo());
            WPTrendsEventsUtils.trendsPageButtonData("支付确认弹窗", "98U01170wp170", "wp170", "点击-支付确认弹窗-未完成支付");
        }
    }

    @NBSInstrumented
    /* renamed from: com.unicom.pay.normal.order.ui.WPOrderActivity$d */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10658d implements C12048b.InterfaceC12052d {
        public C10658d() {
        }

        @Override // p395i.C12048b.InterfaceC12052d
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            WPOrderActivity wPOrderActivity = WPOrderActivity.this;
            ((C1529j) wPOrderActivity.f24311a).m22172b(wPOrderActivity.f20315s0.getData().getTradeOrderNo());
            WPTrendsEventsUtils.trendsPageButtonData("支付确认弹窗", "98U01170wp170", "wp170", "点击-支付确认弹窗-已完成支付");
            WPOrderActivity.this.f20327y0 = true;
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.normal.order.ui.WPOrderActivity$e */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10659e implements AbstractC12045a.InterfaceC12047b {
        public C10659e() {
        }
    }

    /* renamed from: com.unicom.pay.normal.order.ui.WPOrderActivity$f */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10660f implements C12048b.InterfaceC12050b {
        public C10660f() {
        }

        @Override // p395i.C12048b.InterfaceC12050b
        /* renamed from: a */
        public final void mo1801a() {
            WPOrderActivity wPOrderActivity = WPOrderActivity.this;
            int i = WPOrderActivity.f20256N0;
            wPOrderActivity.m6082r0();
            if (TextUtils.isEmpty(WPOrderActivity.this.mo6127V())) {
                return;
            }
            WPOrderActivity wPOrderActivity2 = WPOrderActivity.this;
            ((C1529j) wPOrderActivity2.f24311a).m22179a(wPOrderActivity2.mo6127V());
        }
    }

    @NBSInstrumented
    /* renamed from: com.unicom.pay.normal.order.ui.WPOrderActivity$g */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10661g implements C12048b.InterfaceC12052d {

        /* renamed from: a */
        public final /* synthetic */ WPUserStatusBean f20335a;

        /* renamed from: com.unicom.pay.normal.order.ui.WPOrderActivity$g$a */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class C10662a implements DataCallback {
            public C10662a() {
            }

            @Override // com.unicom.pay.common.callback.DataCallback
            public final void onResult(String str) {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                UnicomPaySDK.getInstance().getNativeFunctionCallback().openWebview(WPOrderActivity.this, str);
            }
        }

        public C10661g(WPUserStatusBean wPUserStatusBean) {
            this.f20335a = wPUserStatusBean;
        }

        @Override // p395i.C12048b.InterfaceC12052d
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            if (!TextUtils.isEmpty(this.f20335a.getNextStepUrl())) {
                C13654q.m171a(WPOrderActivity.this.f24311a, this.f20335a.getNextStepUrl(), WPOrderActivity.this.mo6127V(), new C10662a());
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* renamed from: com.unicom.pay.normal.order.ui.WPOrderActivity$h */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class RunnableC10663h implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ WPQOpenResultBean f20338a;

        public RunnableC10663h(WPQOpenResultBean wPQOpenResultBean) {
            this.f20338a = wPQOpenResultBean;
        }

        @Override // java.lang.Runnable
        public final void run() {
            WPOrderActivity wPOrderActivity = WPOrderActivity.this;
            String payToken = this.f20338a.getPayToken();
            int i = WPOrderActivity.f20256N0;
            wPOrderActivity.m6087n(payToken);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.normal.order.ui.WPOrderActivity$i */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10664i implements C10679a.InterfaceC10680a {
        public C10664i() {
        }
    }

    /* renamed from: com.unicom.pay.normal.order.ui.WPOrderActivity$j */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class RunnableC10665j implements Runnable {

        /* renamed from: com.unicom.pay.normal.order.ui.WPOrderActivity$j$a */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class C10666a implements C14239e.InterfaceC14242c {
            public C10666a() {
            }

            @Override // p481v.C14239e.InterfaceC14242c
            /* renamed from: a */
            public final void mo33a(WPQOpenResultBean wPQOpenResultBean) {
                WPOrderActivity.this.mo6121a(wPQOpenResultBean, false);
            }
        }

        public RunnableC10665j() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            WPOrderActivity wPOrderActivity = WPOrderActivity.this;
            wPOrderActivity.f20262C0.show(wPOrderActivity.getSupportFragmentManager(), "DetailOpenQPayVerifyCodeDialog");
            WPOrderActivity.this.f20262C0.f27737A = new C10666a();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.normal.order.ui.WPOrderActivity$k */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10667k implements C14239e.InterfaceC14242c {
        public C10667k() {
        }

        @Override // p481v.C14239e.InterfaceC14242c
        /* renamed from: a */
        public final void mo33a(WPQOpenResultBean wPQOpenResultBean) {
            WPOrderActivity.this.m2082n0();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.normal.order.ui.WPOrderActivity$l */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class RunnableC10668l implements Runnable {
        public RunnableC10668l() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            WPOrderActivity.this.mo1792Y();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.normal.order.ui.WPOrderActivity$m */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10669m implements C14239e.InterfaceC14243d {
        public C10669m() {
        }
    }

    /* renamed from: com.unicom.pay.normal.order.ui.WPOrderActivity$n */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class CountDownTimerC10670n extends CountDownTimer {
        public CountDownTimerC10670n(long j) {
            super(j, 1000L);
        }

        @Override // android.os.CountDownTimer
        public final void onFinish() {
            WPOrderActivity wPOrderActivity = WPOrderActivity.this;
            wPOrderActivity.f20276J0 = true;
            wPOrderActivity.f20316t.setText("00");
            WPOrderActivity.this.f20318u.setText("00");
            WPOrderActivity.this.f20320v.setText("00");
            WPDefaultOrderInfoBean wPDefaultOrderInfoBean = WPOrderActivity.this.f20315s0;
            if (wPDefaultOrderInfoBean == null || wPDefaultOrderInfoBean.getData() == null || C13636a.f27478b <= 0) {
                return;
            }
            WPOrderActivity wPOrderActivity2 = WPOrderActivity.this;
            C1529j c1529j = (C1529j) wPOrderActivity2.f24311a;
            String tradeOrderNo = wPOrderActivity2.f20315s0.getData().getTradeOrderNo();
            c1529j.getClass();
            HashMap hashMap = new HashMap();
            hashMap.put("tradeOrderNo", tradeOrderNo);
            c1529j.m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27790C, hashMap, false, new C1552m(c1529j, c1529j)));
        }

        @Override // android.os.CountDownTimer
        public final void onTick(long j) {
            String valueOf;
            String valueOf2;
            String valueOf3;
            int i = (int) ((j / 1000) % 60);
            int i2 = (int) ((j / 60000) % 60);
            int i3 = (int) (j / 3600000);
            TextView textView = WPOrderActivity.this.f20316t;
            if (i3 / 10 == 0) {
                valueOf = "0" + i3;
            } else {
                valueOf = String.valueOf(i3);
            }
            textView.setText(valueOf);
            TextView textView2 = WPOrderActivity.this.f20318u;
            if (i2 / 10 == 0) {
                valueOf2 = "0" + i2;
            } else {
                valueOf2 = String.valueOf(i2);
            }
            textView2.setText(valueOf2);
            TextView textView3 = WPOrderActivity.this.f20320v;
            if (i / 10 == 0) {
                valueOf3 = "0" + i;
            } else {
                valueOf3 = String.valueOf(i);
            }
            textView3.setText(valueOf3);
        }
    }

    /* renamed from: com.unicom.pay.normal.order.ui.WPOrderActivity$o */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10671o implements C12048b.InterfaceC12050b {

        /* renamed from: a */
        public final /* synthetic */ boolean f20347a;

        public C10671o(boolean z) {
            this.f20347a = z;
        }

        @Override // p395i.C12048b.InterfaceC12050b
        /* renamed from: a */
        public final void mo1801a() {
            if (this.f20347a) {
                WPOrderActivity.super.onBackPressed();
            } else {
                WPOrderActivity.this.finish();
            }
            WPTrendsEventsUtils.trendsPageButtonData("挽留弹窗", "98U01170wp104", "wp104", "点击-挽留弹窗-确认离开");
        }
    }

    @NBSInstrumented
    /* renamed from: com.unicom.pay.normal.order.ui.WPOrderActivity$p */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10672p implements C12048b.InterfaceC12052d {
        @Override // p395i.C12048b.InterfaceC12052d
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            WPTrendsEventsUtils.trendsPageButtonData("挽留弹窗", "98U01170wp104", "wp104", "点击-挽留弹窗-继续支付");
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.normal.order.ui.WPOrderActivity$q */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    class C10673q extends TypeToken<HashMap<String, String>> {
    }

    @NBSInstrumented
    /* renamed from: com.unicom.pay.normal.order.ui.WPOrderActivity$r */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10674r implements DataCallback {
        public C10674r() {
        }

        @Override // com.unicom.pay.common.callback.DataCallback
        public final void onResult(String str) {
            C13652o.m174a("UnicomPaySDK ali", str);
            Gson gson = C13648k.f27492a;
            WPOrderActivity.this.f20323w0 = "6001".equals(((WPZFBResultBean) (!(gson instanceof Gson) ? gson.fromJson(str, (Class<Object>) WPZFBResultBean.class) : NBSGsonInstrumentation.fromJson(gson, str, (Class<Object>) WPZFBResultBean.class))).getResultStatus());
            WPOrderActivity.this.m6086o0();
            WPOrderActivity wPOrderActivity = WPOrderActivity.this;
            ((C1529j) wPOrderActivity.f24311a).m22172b(wPOrderActivity.f20315s0.getData().getTradeOrderNo());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.normal.order.ui.WPOrderActivity$s */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10675s implements DataCallback {
        @Override // com.unicom.pay.common.callback.DataCallback
        public final void onResult(String str) {
        }
    }

    /* renamed from: a */
    public static Bundle m6103a(String str, String str2, WPDefaultOrderInfoBean wPDefaultOrderInfoBean, WPResult<WPUnionOrderInfoBean> wPResult, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString("refererUrl", str);
        bundle.putString("orderJson", str2);
        bundle.putParcelable("defaultOrderInfo", wPDefaultOrderInfoBean);
        bundle.putParcelable("wpResult", wPResult);
        bundle.putBoolean("netError", z);
        return bundle;
    }

    @Override // p394h0.View$OnClickListenerC12020c.InterfaceC12022b
    /* renamed from: A */
    public final void mo1978A() {
        C1529j c1529j = (C1529j) this.f24311a;
        c1529j.getClass();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("recommendedScene", "PAY_DETAIL");
        c1529j.m1797a(C14255c.C14256a.f27777a.m30a(C14262f.f27825o, hashMap, new C1548k(c1529j, c1529j)));
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, p393h.InterfaceC12016h
    /* renamed from: D */
    public final String mo56D() {
        int i = this.f20329z0;
        return i == 1 ? "付款详情页" : i == 2 ? "降级页" : i == 3 ? "小飞机页" : "付款详情页";
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: F */
    public final void mo6130F() {
        m2082n0();
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: K */
    public final void mo6129K() {
        ImageView imageView = this.f20297b0;
        if (imageView != null) {
            imageView.setImageResource(C10531R.C10533drawable.up_notice_arrow);
        }
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, p393h.InterfaceC12016h
    /* renamed from: O */
    public final String mo53O() {
        int i = this.f20329z0;
        return (i != 1 && i == 3) ? "wp600" : "wp100";
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:13|(4:14|15|(1:17)(1:72)|18)|(6:20|(1:22)(1:70)|23|24|(1:26)(1:69)|(10:28|(2:30|31)|33|(1:35)|36|(2:38|(2:39|(2:41|(2:43|44))(1:45)))(0)|46|47|48|(5:50|51|(1:64)(3:54|(1:63)(1:59)|60)|61|62)(5:65|(0)|64|61|62)))|71|(0)|33|(0)|36|(0)(0)|46|47|48|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0145, code lost:
        if (r0 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0147, code lost:
        r4 = r0.size();
     */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0096 A[Catch: Exception -> 0x009a, TRY_LEAVE, TryCatch #0 {Exception -> 0x009a, blocks: (B:16:0x003d, B:18:0x004e, B:20:0x0059, B:22:0x0063, B:27:0x0081, B:35:0x0096, B:19:0x0053), top: B:76:0x003d }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0136 A[Catch: Exception -> 0x0145, TRY_LEAVE, TryCatch #1 {Exception -> 0x0145, blocks: (B:50:0x0125, B:53:0x0136), top: B:78:0x0125 }] */
    @Override // p089b0.InterfaceC1470d
    /* renamed from: S */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void mo6128S() {
        /*
            Method dump skipped, instructions count: 454
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unicom.pay.normal.order.p359ui.WPOrderActivity.mo6128S():void");
    }

    @Override // p089b0.InterfaceC1472f
    /* renamed from: V */
    public final String mo6127V() {
        WPDefaultOrderInfoBean wPDefaultOrderInfoBean = this.f20315s0;
        return (wPDefaultOrderInfoBean == null || wPDefaultOrderInfoBean.getData() == null) ? "" : this.f20315s0.getData().getTradeOrderNo();
    }

    /* renamed from: a */
    public final void m6125a(long j) {
        this.f20314s.setVisibility(8);
        CountDownTimerC10670n countDownTimerC10670n = this.f20308m0;
        if (countDownTimerC10670n != null) {
            countDownTimerC10670n.cancel();
            this.f20308m0 = null;
        }
        this.f20316t.setText(String.valueOf((int) (j / 3600000)));
        this.f20318u.setText(String.valueOf((int) ((j / 60000) % 60)));
        this.f20320v.setText(String.valueOf((int) ((j / 1000) % 60)));
        CountDownTimerC10670n countDownTimerC10670n2 = new CountDownTimerC10670n(j);
        this.f20308m0 = countDownTimerC10670n2;
        countDownTimerC10670n2.start();
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: a */
    public final void mo6122a(WPQOpenResultBean wPQOpenResultBean) {
        if (wPQOpenResultBean == null) {
            return;
        }
        try {
            WPUnionOrderInfoBean wPUnionOrderInfoBean = this.f20313r0;
            if (wPUnionOrderInfoBean != null && wPUnionOrderInfoBean.getOrderInfo() != null) {
                C14239e m51a = C14239e.m51a(C14239e.m47a(wPQOpenResultBean.getPhoneNo(), "QPAY_DETAIL_SIGN", wPQOpenResultBean.getSignTokenId(), this.f20313r0.getOrderInfo().getMerNo(), "PAY_DETAIL"));
                this.f20262C0 = m51a;
                if (!m51a.isAdded() && !this.f20262C0.isVisible()) {
                    ImageView imageView = this.f20288S;
                    if (imageView != null) {
                        imageView.postDelayed(new RunnableC10665j(), 1500L);
                        return;
                    }
                    return;
                }
                this.f20262C0.dismissAllowingStateLoss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p387e0.C11854d.InterfaceC11856b
    /* renamed from: a */
    public final void mo2065a(WPDiscountDetailBean wPDiscountDetailBean, boolean z) {
        C0777a c0777a;
        if (this.f20315s0 == null || (c0777a = this.f20304i0) == null) {
            return;
        }
        c0777a.m22224c(false);
        ((C1529j) this.f24311a).m22174a("4", wPDiscountDetailBean.getDiscountExpand(), mo6076y(), this.f20315s0.getData().getTradeOrderNo(), z ? "1" : "0", mo6077x(), this.f20298c0.m2059a());
    }

    @Override // p387e0.C11860g.InterfaceC11862b
    /* renamed from: a */
    public final void mo2063a(WPDiscountDetailBean wPDiscountDetailBean, boolean z, String str) {
        C0784b c0784b;
        if (this.f20315s0 == null || (c0784b = this.f20303h0) == null) {
            return;
        }
        c0784b.m22222a(false);
        ((C1529j) this.f24311a).m22174a(str, wPDiscountDetailBean.getDiscountExpand(), mo6076y(), this.f20315s0.getData().getTradeOrderNo(), z ? "1" : "0", mo6077x(), this.f20298c0.m2059a());
    }

    /* renamed from: a */
    public final void m6115a(WPPayInfoBean wPPayInfoBean) {
        String str;
        if (WPPayInfoBean.f20222KJ.equals(wPPayInfoBean.getToolCode())) {
            str = "勾选-银行卡";
        } else if (WPPayInfoBean.QPAY.equals(wPPayInfoBean.getToolCode())) {
            str = "勾选-极速支付";
        } else if (WPPayInfoBean.WFQ.equals(wPPayInfoBean.getToolCode())) {
            str = "勾选-可分期银行卡";
        } else if (WPPayInfoBean.f20221BK.equals(wPPayInfoBean.getToolCode())) {
            str = "勾选-添加银行卡";
        } else if (!WPPayInfoBean.BKYH.equals(wPPayInfoBean.getToolCode())) {
            StringBuilder m22016a = C1730a.m22016a("勾选-");
            m22016a.append(wPPayInfoBean.getToolName());
            m1991k(m22016a.toString());
            return;
        } else {
            str = "勾选-添加某银行卡享优惠";
        }
        m1991k(str);
    }

    /* renamed from: a */
    public final void m6112a(WPPayInfoBean wPPayInfoBean, C11889u.C11904n c11904n) {
        C11824k c11824k;
        WPUnionOrderInfoBean wPUnionOrderInfoBean;
        if (wPPayInfoBean.isChecked()) {
            C11824k c11824k2 = this.f20301f0;
            if (c11824k2 == null || !c11824k2.isVisible()) {
                return;
            }
            this.f20301f0.dismissAllowingStateLoss();
        } else if (WPPayInfoBean.EVENT_TYPE_CHECK.equals(wPPayInfoBean.getEventType())) {
            if (this.f20315s0 == null || (wPUnionOrderInfoBean = this.f20313r0) == null || wPUnionOrderInfoBean.getOrderInfo() == null) {
                return;
            }
            m6096c(false);
            ((C1529j) this.f24311a).m22176a(mo6077x(), mo6076y(), wPPayInfoBean.getToolExpand(), this.f20315s0.getData().getTradeOrderNo(), 2, c11904n.getAdapterPosition());
        } else {
            if ("open".equals(wPPayInfoBean.getEventType())) {
                mo6090e(wPPayInfoBean.getH5Url() + "&isSdk=1");
                c11824k = this.f20301f0;
                if (c11824k == null) {
                    return;
                }
            } else {
                m2085l(wPPayInfoBean.getH5Url());
                c11824k = this.f20301f0;
                if (c11824k == null) {
                    return;
                }
            }
            c11824k.dismissAllowingStateLoss();
        }
    }

    /* renamed from: a */
    public final void m6110a(WPUpdateMethodResultBean wPUpdateMethodResultBean) {
        if (wPUpdateMethodResultBean == null) {
            return;
        }
        if (TextUtils.isEmpty(wPUpdateMethodResultBean.getOrderDiscountMsg())) {
            this.f20273I.setVisibility(8);
            this.f20267F.setVisibility(0);
        } else {
            this.f20267F.setVisibility(8);
            this.f20273I.setOnClickListener(null);
            this.f20273I.setVisibility(0);
            this.f20275J.setText(wPUpdateMethodResultBean.getOrderDiscountMsg());
            if ("1".equals(wPUpdateMethodResultBean.getOrderDiscountIconType())) {
                this.f20273I.setOnClickListener(this);
                this.f20277K.setVisibility(0);
            } else {
                this.f20277K.setVisibility(8);
            }
        }
        WPUnionOrderInfoBean wPUnionOrderInfoBean = this.f20313r0;
        if (wPUnionOrderInfoBean != null && wPUnionOrderInfoBean.getOrderInfo() != null) {
            this.f20313r0.getOrderInfo().setOrderDiscountMsg(wPUpdateMethodResultBean.getOrderDiscountMsg());
            this.f20313r0.getOrderInfo().setOrderDiscountNext(wPUpdateMethodResultBean.getOrderDiscountNext());
            this.f20313r0.getOrderInfo().setOrderDiscountIconType(wPUpdateMethodResultBean.getOrderDiscountIconType());
            this.f20313r0.getOrderInfo().setOrderDiscountIconState(wPUpdateMethodResultBean.getOrderDiscountIconState());
        }
        if (TextUtils.isEmpty(wPUpdateMethodResultBean.getOrderAmountSubscript())) {
            this.f20326y.setVisibility(8);
            return;
        }
        this.f20326y.setText(wPUpdateMethodResultBean.getOrderAmountSubscript());
        this.f20326y.setVisibility(0);
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: a */
    public final void mo6106a(WPAgreementBean wPAgreementBean) {
        this.f20270G0 = wPAgreementBean;
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: a */
    public final void mo6104a(String str, String str2) {
        this.f20329z0 = 3;
        WPTrendsEventsUtils.addWindow(mo53O());
        this.f20291V.setVisibility(0);
        this.f20292W.setText(str);
        this.f20293X.setText(str2);
        this.f20289T.setVisibility(8);
    }

    /*  JADX ERROR: Failed to decode insn: 0x0708: UNKNOWN(0x0079), method: com.unicom.pay.normal.order.ui.WPOrderActivity.a(java.lang.String, java.lang.String, com.unicom.pay.normal.order.bean.WPUnionOrderInfoBean):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0708: UNKNOWN(0x0079)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    @Override // p089b0.InterfaceC1470d
    /* renamed from: a */
    public final void mo6102a(java.lang.String r9, java.lang.String r10, com.unicom.pay.normal.order.bean.WPUnionOrderInfoBean r11) {
        /*
            Method dump skipped, instructions count: 1835
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unicom.pay.normal.order.p359ui.WPOrderActivity.mo6102a(java.lang.String, java.lang.String, com.unicom.pay.normal.order.bean.WPUnionOrderInfoBean):void");
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: a0 */
    public final AbstractC12375a mo2002a0() {
        return new C1529j();
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: b */
    public final void mo6100b(WPResult<WPOrderPayBeforeBean> wPResult) {
        WPCheckSignResult quickPayBeforeResp;
        m1991k("点击-极速支付按钮");
        if (wPResult == null || wPResult.getData() == null || TextUtils.isEmpty(this.f24030q) || (quickPayBeforeResp = wPResult.getData().getQuickPayBeforeResp()) == null) {
            return;
        }
        if (!"0".equals(quickPayBeforeResp.getStatus())) {
            if ("1".equals(quickPayBeforeResp.getStatus())) {
                mo1790i(wPResult.getMsg());
                return;
            } else if ("2".equals(quickPayBeforeResp.getStatus()) || "3".equals(quickPayBeforeResp.getStatus())) {
                m2082n0();
                return;
            } else if (!"4".equals(quickPayBeforeResp.getStatus())) {
                if ("6".equals(quickPayBeforeResp.getStatus())) {
                    mo6090e(quickPayBeforeResp.getPwdUrl());
                    return;
                }
                return;
            } else {
                WPQPayUserInfoBean m164a = C10546a.C10576i.f20125a.f20053c.m164a(this.f24030q);
                this.f24026m = m164a;
                if (m164a != null && !TextUtils.isEmpty(m164a.getPayToken())) {
                    mo1790i(wPResult.getMsg());
                    m6087n(this.f24026m.getPayToken());
                    return;
                }
            }
        }
        m6078u0();
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: b */
    public final void mo6099b(WPPayBeforeBean wPPayBeforeBean) {
        WPDefaultOrderInfoBean wPDefaultOrderInfoBean;
        WPPayInfoBean m6084q0 = m6084q0();
        if (m6084q0 == null || (wPDefaultOrderInfoBean = this.f20315s0) == null) {
            return;
        }
        m2096a("PAY_DZL", wPDefaultOrderInfoBean.getData().getTradeOrderNo(), m6084q0.getToolExpand(), wPPayBeforeBean.getPhoneNo(), (WPUnionPayResultBean) null, wPPayBeforeBean);
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: c */
    public final void mo6097c(WPResult<WPUnionPayResultBean> wPResult) {
        if (wPResult == null) {
            return;
        }
        if ("0000".equals(wPResult.getCode())) {
            if (this.f20289T != null) {
                mo1791a(C10531R.string.up_other_pay_success);
                this.f20289T.postDelayed(new RunnableC11820g(this, wPResult), 1000L);
                return;
            }
            return;
        }
        m2010Z();
        m2086f(wPResult);
    }

    @Override // p385d0.AbstractActivityC11796a
    /* renamed from: c */
    public final void mo2089c(String str, String str2) {
        WPUnionOrderInfoBean wPUnionOrderInfoBean = this.f20313r0;
        if (wPUnionOrderInfoBean == null || wPUnionOrderInfoBean.getOrderInfo() == null) {
            return;
        }
        ((C1529j) this.f24311a).m22178a(mo6077x(), str, str2);
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: d */
    public final void mo6094d(WPPayBeforeBean wPPayBeforeBean) {
        if (this.f20315s0 == null) {
            return;
        }
        this.f20278K0 = wPPayBeforeBean;
        WPPayInfoBean m6084q0 = m6084q0();
        if (m6084q0 == null) {
            return;
        }
        String iconUrl = m6084q0.getIconUrl();
        String toolName = m6084q0.getToolName();
        String mo6076y = mo6076y();
        String tradeOrderNo = this.f20315s0.getData().getTradeOrderNo();
        Bundle bundle = new Bundle();
        bundle.putString("logoUrl", iconUrl);
        bundle.putString("bankName", toolName);
        bundle.putString("expand", mo6076y);
        bundle.putString("tradeNo", tradeOrderNo);
        bundle.putParcelable(WPQPayUserInfoBean.QPAY_COLUMN_KEY, wPPayBeforeBean);
        m2007a(WPAuthCreditBankActivity.class, 114, bundle);
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: d0 */
    public final void mo1999d0() {
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

    @Override // p089b0.InterfaceC1470d
    /* renamed from: e */
    public final void mo6091e(WPPayBeforeBean wPPayBeforeBean) {
        WPUnionOrderInfoBean wPUnionOrderInfoBean;
        WPDefaultOrderInfoBean wPDefaultOrderInfoBean = this.f20315s0;
        if (wPDefaultOrderInfoBean == null || wPDefaultOrderInfoBean.getData() == null || (wPUnionOrderInfoBean = this.f20313r0) == null || wPUnionOrderInfoBean.getOrderInfo() == null) {
            return;
        }
        this.f20278K0 = wPPayBeforeBean;
        String mo6076y = mo6076y();
        String tradeOrderNo = this.f20315s0.getData().getTradeOrderNo();
        String phoneNo = wPPayBeforeBean.getPhoneNo();
        int i = C14239e.f27736C;
        Bundle bundle = new Bundle();
        bundle.putString("bizCode", "PAY_WFQ");
        bundle.putString("expand", mo6076y);
        bundle.putString("orderNo", tradeOrderNo);
        bundle.putString("mobile", phoneNo);
        bundle.putParcelable("payBefore", wPPayBeforeBean);
        C14239e m51a = C14239e.m51a(bundle);
        this.f20266E0 = m51a;
        if (m51a.isAdded() || this.f20266E0.isVisible()) {
            this.f20266E0.dismissAllowingStateLoss();
            return;
        }
        C14239e c14239e = this.f20266E0;
        c14239e.f27738B = new C10669m();
        c14239e.show(getSupportFragmentManager(), "mWFQSmsDialog");
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: e0 */
    public final int mo1998e0() {
        return C10531R.C10535layout.wp_order;
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
        try {
            WPTrendsEventsUtils.initData();
            Intent intent = getIntent();
            if (intent != null) {
                this.f20311p0 = intent.hasExtra("refererUrl") ? intent.getStringExtra("refererUrl") : "";
                this.f20312q0 = intent.hasExtra("orderJson") ? intent.getStringExtra("orderJson") : "";
                this.f20315s0 = (WPDefaultOrderInfoBean) intent.getParcelableExtra("defaultOrderInfo");
                WPResult wPResult = intent.hasExtra("wpResult") ? (WPResult) intent.getParcelableExtra("wpResult") : null;
                boolean booleanExtra = intent.getBooleanExtra("netError", false);
                if (wPResult != null) {
                    if (booleanExtra) {
                        mo6128S();
                        return;
                    } else if ("0000".equals(wPResult.getCode())) {
                        String code = wPResult.getCode();
                        wPResult.getCommonResp().getTgtid();
                        mo6102a(code, wPResult.getCommonResp().getAuthId(), (WPUnionOrderInfoBean) wPResult.getData());
                        ((C1529j) this.f24311a).m22170d();
                        return;
                    } else {
                        if (!"GATEWAY9001".equals(wPResult.getCode()) && !"GATEWAY9002".equals(wPResult.getCode())) {
                            mo6104a(wPResult.getMsg(), wPResult.getMsgDetail());
                            return;
                        }
                        String code2 = wPResult.getCode();
                        wPResult.getCommonResp().getTgtid();
                        mo6102a(code2, wPResult.getCommonResp().getAuthId(), (WPUnionOrderInfoBean) wPResult.getData());
                        return;
                    }
                } else if (booleanExtra) {
                    mo6128S();
                    return;
                }
            }
            WPDefaultOrderInfoBean wPDefaultOrderInfoBean = this.f20315s0;
            if (wPDefaultOrderInfoBean != null && wPDefaultOrderInfoBean.getData() != null && !TextUtils.isEmpty(this.f20315s0.getData().getTradeOrderNo()) && !TextUtils.isEmpty(this.f20315s0.getData().getOrderAmount()) && !TextUtils.isEmpty(this.f20315s0.getData().getOrderSubject()) && !TextUtils.isEmpty(this.f20315s0.getSign()) && !TextUtils.isEmpty(this.f20315s0.getData().getTimeStamp())) {
                ((C1529j) this.f24311a).m22173a("default", "", this.f20315s0.getData().getTradeOrderNo(), this.f20311p0, false, "");
                return;
            }
            m6081s0();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Activity
    public final void finish() {
        CountDownTimerC10670n countDownTimerC10670n = this.f20308m0;
        if (countDownTimerC10670n != null) {
            countDownTimerC10670n.cancel();
            this.f20308m0 = null;
        }
        m6082r0();
        super.finish();
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: g0 */
    public final void mo1996g0() {
        m1993j(getResources().getString(C10531R.string.wp_up_pay_title));
        Typeface createFromAsset = Typeface.createFromAsset(getAssets(), "Bebas-Regular.ttf");
        this.f20314s = (LinearLayout) findViewById(C10531R.C10534id.wopay_order_time_ll);
        TextView textView = (TextView) findViewById(C10531R.C10534id.wopay_order_hour_tv);
        this.f20316t = textView;
        textView.setTypeface(createFromAsset);
        TextView textView2 = (TextView) findViewById(C10531R.C10534id.wopay_order_min_tv);
        this.f20318u = textView2;
        textView2.setTypeface(createFromAsset);
        TextView textView3 = (TextView) findViewById(C10531R.C10534id.wopay_order_sec_tv);
        this.f20320v = textView3;
        textView3.setTypeface(createFromAsset);
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(C10531R.C10534id.wopay_order_discount_amount_cl);
        TickerView tickerView = (TickerView) findViewById(C10531R.C10534id.wopay_order_discount_amount_tv);
        this.f20322w = tickerView;
        tickerView.setTypeface(createFromAsset);
        TextView textView4 = (TextView) findViewById(C10531R.C10534id.wopay_order_real_amount_tv);
        this.f20324x = textView4;
        textView4.setTypeface(createFromAsset);
        this.f20326y = (TextView) findViewById(C10531R.C10534id.wopay_order_discount_amount_discount_tv);
        this.f20328z = (LinearLayout) findViewById(C10531R.C10534id.wopay_order_subscribe_ll);
        this.f20257A = (TextView) findViewById(C10531R.C10534id.wopay_order_desc_tv);
        this.f20259B = (ImageView) findViewById(C10531R.C10534id.wopay_order_desc_iv);
        this.f20261C = (LinearLayout) findViewById(C10531R.C10534id.wopay_pay_method_header_ll);
        this.f20263D = (TextView) findViewById(C10531R.C10534id.wp_wap_header_first_title_tv);
        this.f20265E = (ImageView) findViewById(C10531R.C10534id.wp_wap_header_first_icon_iv);
        this.f20267F = (LinearLayout) findViewById(C10531R.C10534id.wopay_detail_discount_default_ll);
        this.f20269G = (TextView) findViewById(C10531R.C10534id.wp_wap_header_sec_title_tv);
        this.f20271H = (ImageView) findViewById(C10531R.C10534id.wp_wap_header_sec_icon_iv);
        this.f20273I = (LinearLayout) findViewById(C10531R.C10534id.wopay_detail_discount_ll);
        this.f20275J = (TextView) findViewById(C10531R.C10534id.wopay_detail_discount_tv);
        this.f20277K = (ImageView) findViewById(C10531R.C10534id.wopay_detail_discount_arrow_iv);
        this.f20279L = (LinearLayout) findViewById(C10531R.C10534id.wopay_pay_method_body_ll);
        RecyclerView recyclerView = (RecyclerView) findViewById(C10531R.C10534id.wopay_unicom_pay_discount_list_rv);
        this.f20281M = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        C11889u c11889u = new C11889u(this);
        this.f20299d0 = c11889u;
        this.f20281M.setAdapter(c11889u);
        this.f20281M.setNestedScrollingEnabled(false);
        this.f20299d0.f24231c = this;
        RecyclerView recyclerView2 = (RecyclerView) findViewById(C10531R.C10534id.wopay_unicom_pay_method_list_rv);
        this.f20283N = recyclerView2;
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        C11889u c11889u2 = new C11889u(this);
        this.f20298c0 = c11889u2;
        this.f20283N.setAdapter(c11889u2);
        this.f20283N.setNestedScrollingEnabled(false);
        this.f20298c0.f24231c = this;
        this.f20284O = (LinearLayout) findViewById(C10531R.C10534id.wopay_other_pay_method_body_ll);
        RecyclerView recyclerView3 = (RecyclerView) findViewById(C10531R.C10534id.wopay_other_pay_method_list_rv);
        this.f20285P = recyclerView3;
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));
        C11886t c11886t = new C11886t(this);
        this.f20300e0 = c11886t;
        this.f20285P.setAdapter(c11886t);
        this.f20285P.setNestedScrollingEnabled(false);
        this.f20300e0.f24220d = this;
        LinearLayout linearLayout = (LinearLayout) findViewById(C10531R.C10534id.wopay_other_pay_method_more_ll);
        this.f20286Q = linearLayout;
        linearLayout.setOnClickListener(this);
        this.f20287R = (TextView) findViewById(C10531R.C10534id.wopay_other_pay_method_more_tv);
        this.f20288S = (ImageView) findViewById(C10531R.C10534id.wopay_other_pay_method_more_iv);
        Button button = (Button) findViewById(C10531R.C10534id.wopay_pay_confirm_btn);
        this.f20289T = button;
        button.setOnClickListener(this);
        this.f20291V = (RelativeLayout) findViewById(C10531R.C10534id.up_error_rl);
        this.f20292W = (TextView) findViewById(C10531R.C10534id.up_error_title_tv);
        this.f20293X = (TextView) findViewById(C10531R.C10534id.up_error_desc_tv);
        View findViewById = findViewById(C10531R.C10534id.wopay_blank_view);
        this.f20290U = findViewById;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) findViewById.getLayoutParams();
        layoutParams.height = C13659r.m169a(this) / 3;
        this.f20290U.setLayoutParams(layoutParams);
        int i = C11824k.f24084k;
        Bundle bundle = new Bundle();
        C11824k c11824k = new C11824k();
        c11824k.setArguments(bundle);
        this.f20301f0 = c11824k;
        int i2 = C0788c.f2440m;
        Bundle bundle2 = new Bundle();
        C0788c c0788c = new C0788c();
        c0788c.setArguments(bundle2);
        this.f20302g0 = c0788c;
        int i3 = C10681b.f20377o;
        Bundle bundle3 = new Bundle();
        bundle3.putInt("type", 1);
        C10681b c10681b = new C10681b();
        c10681b.setArguments(bundle3);
        this.f20305j0 = c10681b;
        c10681b.f20384m = this;
        int i4 = C10531R.string.up_bank_title;
        int i5 = C10531R.string.up_bank_content;
        int i6 = C10531R.string.up_bank_cancel;
        int i7 = C10531R.string.up_bank_confirm;
        int i8 = C10679a.f20369n;
        Bundle bundle4 = new Bundle();
        bundle4.putInt("title", i4);
        bundle4.putInt("desc", i5);
        bundle4.putInt("cancel", i6);
        bundle4.putInt("confirm", i7);
        C10679a c10679a = new C10679a();
        c10679a.setArguments(bundle4);
        this.f20306k0 = c10679a;
        c10679a.setCancelable(false);
        this.f20306k0.f20374k = new C10664i();
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: h */
    public final void mo6089h() {
        C13663u c13663u;
        WPQPayUserInfoBean m164a;
        if (TextUtils.isEmpty(this.f24030q) || (m164a = (c13663u = C10546a.C10576i.f20125a.f20053c).m164a(this.f24030q)) == null) {
            return;
        }
        m164a.setPayToken("");
        c13663u.m165a(m164a);
    }

    @Override // com.unicom.pay.normal.order.p359ui.C10681b.InterfaceC10682a
    /* renamed from: h */
    public final void mo6068h(String str) {
        m2085l(str);
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: h0 */
    public final void mo1995h0() {
        if (EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: k0 */
    public final void mo1990k0() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override // p385d0.AbstractActivityC11796a
    /* renamed from: l0 */
    public final void mo2084l0() {
    }

    @Override // com.unicom.pay.normal.order.p359ui.C10681b.InterfaceC10682a
    /* renamed from: m */
    public final void mo6067m() {
    }

    /* renamed from: m */
    public final void m6088m(String str) {
        if (this.f20315s0 == null || this.f20298c0 == null) {
            return;
        }
        mo1792Y();
        ((C1529j) this.f24311a).m22174a("4", str, mo6076y(), this.f20315s0.getData().getTradeOrderNo(), "0", mo6077x(), this.f20298c0.m2059a());
    }

    /* renamed from: n */
    public final void m6087n(String str) {
        if (this.f20315s0 == null || TextUtils.isEmpty(str)) {
            return;
        }
        ImageView imageView = this.f20288S;
        if (imageView != null) {
            imageView.postDelayed(new RunnableC10668l(), 600L);
        }
        try {
            String str2 = this.f24030q;
            C1731txcrypto c1731txcrypto = new C1731txcrypto();
            ((C1529j) this.f24311a).m22175a(this.f20315s0.getData().getTradeOrderNo(), c1731txcrypto.microdoneSM3HMAC(this.f20315s0.getData().getTradeOrderNo() + "|" + this.f20315s0.getData().getOrderAmount() + "|" + this.f20315s0.getData().getTimeStamp().split("\\|\\|")[1], c1731txcrypto.microdoneSM4Decrypt(c1731txcrypto.microdoneSM4Encrypt("1c60f57043eec87893351db5bc953bae", "0000000000000000", str2, 0, 0, 0), "0000000000000000", str, 0, 0, 0), 0), this.f20311p0, "", "", mo6077x(), mo6076y());
        } catch (Exception unused) {
        }
    }

    /* renamed from: o0 */
    public final void m6086o0() {
        C12048b c12048b = this.f20268F0;
        if (c12048b != null) {
            c12048b.dismissAllowingStateLoss();
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public final void onActivityResult(int i, int i2, @Nullable Intent intent) {
        WPDefaultOrderInfoBean wPDefaultOrderInfoBean;
        super.onActivityResult(i, i2, intent);
        if (-1 == i2 && i == 111 && intent != null) {
            m6125a(intent.getLongExtra("fqOrder", 0L));
        } else if (i == 112 && i2 == -1) {
            this.f20287R.setTag(Boolean.FALSE);
            WPDefaultOrderInfoBean wPDefaultOrderInfoBean2 = this.f20315s0;
            if (wPDefaultOrderInfoBean2 == null || wPDefaultOrderInfoBean2.getData() == null) {
                return;
            }
            this.f20272H0 = false;
            ((C1529j) this.f24311a).m22173a("login", "", this.f20315s0.getData().getTradeOrderNo(), this.f20311p0, true, "");
        } else if (114 != i || i2 != -1) {
            if (intent == null || intent.getExtras() == null) {
                return;
            }
            String string = intent.getExtras().getString("pay_result");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            C13652o.m174a("UnicomPaySDK unionPay", string);
            this.f20323w0 = "cancel".equals(string);
            m6086o0();
            ((C1529j) this.f24311a).m22172b(this.f20315s0.getData().getTradeOrderNo());
        } else if (intent == null || intent.getExtras() == null || this.f20278K0 == null || (wPDefaultOrderInfoBean = this.f20315s0) == null || this.f20313r0 == null || wPDefaultOrderInfoBean.getData() == null || this.f20313r0.getOrderInfo() == null) {
        } else {
            String stringExtra = intent.getStringExtra("UUID");
            String stringExtra2 = intent.getStringExtra("FAP");
            String stringExtra3 = intent.getStringExtra("expand");
            this.f20278K0.setUuid(stringExtra);
            this.f20278K0.setFapAgrNo(stringExtra2);
            mo1792Y();
            ((C1529j) this.f24311a).m22168a(stringExtra3, this.f20278K0, this.f20315s0.getData().getTradeOrderNo(), "", "", mo6077x());
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public final void onBackPressed() {
        m1991k("详情页-返回");
        m6092d(true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x008e, code lost:
        if (r8.getOrderInfo() != null) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00b5, code lost:
        if (r8.getOrderInfo() != null) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00b7, code lost:
        m2007a(com.unicom.pay.normal.order.p359ui.WPUpMobileLoginActivity.class, 112, com.unicom.pay.normal.order.p359ui.WPUpMobileLoginActivity.m6073a(r7.f20315s0.getData().getTradeOrderNo(), r7.f20310o0, r7.f20309n0, (java.util.ArrayList) r7.f20313r0.getOrderInfo().getAgreementMsgs()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0121, code lost:
        if ("1".equals(r0.getButtonRequestType()) != false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01b3, code lost:
        if ("h5".equals(r0.getButtonEventType()) != false) goto L76;
     */
    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.view.View.OnClickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onClick(android.view.View r8) {
        /*
            Method dump skipped, instructions count: 821
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unicom.pay.normal.order.p359ui.WPOrderActivity.onClick(android.view.View):void");
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 404);
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        CountDownTimerC10670n countDownTimerC10670n = this.f20308m0;
        if (countDownTimerC10670n != null) {
            countDownTimerC10670n.cancel();
            this.f20308m0 = null;
        }
        m6082r0();
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

    @Override // com.unicom.pay.common.callback.DataCallback
    public final void onResult(String str) {
        WPPayInfoBean m6084q0 = m6084q0();
        if (m6084q0 == null) {
            return;
        }
        ((C1529j) this.f24311a).m22166a(this.f20317t0, str, m6084q0.getToolExpand(), this.f20315s0.getData().getTradeOrderNo(), "");
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        WPDefaultOrderInfoBean wPDefaultOrderInfoBean = this.f20315s0;
        if (wPDefaultOrderInfoBean != null && wPDefaultOrderInfoBean.getData() != null && !TextUtils.isEmpty(this.f20315s0.getData().getTradeOrderNo())) {
            if (this.f24029p) {
                this.f24029p = false;
            }
            if (!TextUtils.isEmpty(this.f20319u0) && !WPPayInfoBean.ZFB.equals(this.f20319u0) && !WPPayInfoBean.f20228YL.equals(this.f20319u0)) {
                m6086o0();
                ((C1529j) this.f24311a).m22172b(this.f20315s0.getData().getTradeOrderNo());
            }
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
        int i = this.f20329z0;
        return (i != 1 && i == 3) ? "98U01170wp600" : "98U01170wp100";
    }

    /* renamed from: p0 */
    public final void m6085p0() {
        if (this.f20272H0) {
            return;
        }
        this.f20272H0 = true;
        this.f24318h = System.currentTimeMillis();
        m2000c0();
    }

    @Override // p394h0.View$OnClickListenerC12020c.InterfaceC12022b
    /* renamed from: q */
    public final void mo1977q() {
        WPDefaultOrderInfoBean wPDefaultOrderInfoBean = this.f20315s0;
        if (wPDefaultOrderInfoBean != null) {
            ((C1529j) this.f24311a).m22179a(wPDefaultOrderInfoBean.getData().getTradeOrderNo());
        }
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.List<com.unicom.pay.normal.order.bean.WPPayInfoBean>, java.util.ArrayList] */
    /* renamed from: q0 */
    public final WPPayInfoBean m6084q0() {
        C11889u c11889u;
        C11886t c11886t = this.f20300e0;
        if (c11886t == null || (c11889u = this.f20298c0) == null) {
            return null;
        }
        int i = c11889u.f24234f;
        if (i >= 0) {
            return c11889u.f24230b.get(i);
        }
        int i2 = c11886t.f24219c;
        if (i2 >= 0) {
            return (WPPayInfoBean) c11886t.f24217a.get(i2);
        }
        return null;
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: r */
    public final void mo6083r() {
        C11849b c11849b;
        mo1793I();
        C0788c c0788c = this.f20302g0;
        if (c0788c != null && (c11849b = c0788c.f2443j) != null) {
            c11849b.notifyDataSetChanged();
        }
        C0784b c0784b = this.f20303h0;
        if (c0784b != null) {
            c0784b.m22222a(true);
            C11860g c11860g = this.f20303h0.f2433i;
            if (c11860g != null) {
                c11860g.notifyDataSetChanged();
            }
        }
        C0777a c0777a = this.f20304i0;
        if (c0777a != null) {
            c0777a.m22224c(true);
            C0777a c0777a2 = this.f20304i0;
            C11854d c11854d = c0777a2.f2412i;
            if (c11854d != null) {
                c11854d.notifyDataSetChanged();
            }
            C11854d c11854d2 = c0777a2.f2413j;
            if (c11854d2 != null) {
                c11854d2.notifyDataSetChanged();
            }
        }
    }

    /* renamed from: r0 */
    public final void m6082r0() {
        C10681b c10681b = this.f20305j0;
        if (c10681b != null && c10681b.isVisible()) {
            this.f20305j0.dismissAllowingStateLoss();
        }
        C10679a c10679a = this.f20306k0;
        if (c10679a != null && c10679a.isVisible()) {
            this.f20306k0.dismissAllowingStateLoss();
        }
        C11819f c11819f = this.f20307l0;
        if (c11819f != null && c11819f.isVisible()) {
            this.f20307l0.dismissAllowingStateLoss();
        }
        C11824k c11824k = this.f20301f0;
        if (c11824k != null && c11824k.isVisible()) {
            this.f20301f0.dismissAllowingStateLoss();
        }
        C0788c c0788c = this.f20302g0;
        if (c0788c != null && c0788c.isVisible()) {
            this.f20302g0.dismissAllowingStateLoss();
        }
        C14232a c14232a = this.f24027n;
        if (c14232a != null && c14232a.isVisible()) {
            this.f24027n.dismissAllowingStateLoss();
        }
        C14239e c14239e = this.f24028o;
        if (c14239e != null && c14239e.isVisible()) {
            this.f24028o.dismissAllowingStateLoss();
        }
        C14239e c14239e2 = this.f20262C0;
        if (c14239e2 != null && c14239e2.isVisible()) {
            this.f20262C0.dismissAllowingStateLoss();
        }
        View$OnClickListenerC12020c view$OnClickListenerC12020c = this.f20260B0;
        if (view$OnClickListenerC12020c != null && view$OnClickListenerC12020c.isVisible()) {
            this.f20260B0.dismissAllowingStateLoss();
        }
        C14239e c14239e3 = this.f20264D0;
        if (c14239e3 != null && c14239e3.isVisible()) {
            this.f20264D0.dismissAllowingStateLoss();
        }
        C14239e c14239e4 = this.f20266E0;
        if (c14239e4 != null && c14239e4.isVisible()) {
            this.f20266E0.dismissAllowingStateLoss();
        }
        C0777a c0777a = this.f20304i0;
        if (c0777a != null && c0777a.isVisible()) {
            this.f20304i0.dismissAllowingStateLoss();
        }
        C0784b c0784b = this.f20303h0;
        if (c0784b != null && c0784b.isVisible()) {
            this.f20303h0.dismissAllowingStateLoss();
        }
        C12048b c12048b = this.f20268F0;
        if (c12048b != null) {
            c12048b.dismissAllowingStateLoss();
        }
        m2010Z();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void reloadRouterResource(EnumC12015g enumC12015g) {
        WPDefaultOrderInfoBean wPDefaultOrderInfoBean;
        String scene;
        C1529j c1529j;
        String str;
        String str2;
        String str3;
        String str4;
        WPDefaultOrderInfoBean wPDefaultOrderInfoBean2;
        try {
            if (enumC12015g == EnumC12015g.BANK_CHANGE) {
                View$OnClickListenerC12020c view$OnClickListenerC12020c = this.f20260B0;
                if (view$OnClickListenerC12020c != null && view$OnClickListenerC12020c.isVisible()) {
                    this.f20260B0.dismissAllowingStateLoss();
                }
                WPCompleteUserInfoNotifyBean wPCompleteUserInfoNotifyBean = (WPCompleteUserInfoNotifyBean) enumC12015g.f24357a;
                if (wPCompleteUserInfoNotifyBean == null || (wPDefaultOrderInfoBean2 = this.f20315s0) == null || wPDefaultOrderInfoBean2.getData() == null) {
                    return;
                }
                C1529j c1529j2 = (C1529j) this.f24311a;
                String serialNo = wPCompleteUserInfoNotifyBean.getSerialNo();
                String tradeOrderNo = this.f20315s0.getData().getTradeOrderNo();
                String str5 = this.f20311p0;
                scene = wPCompleteUserInfoNotifyBean.getScene();
                c1529j = c1529j2;
                str = WPPayInfoBean.f20221BK;
                str2 = serialNo;
                str3 = tradeOrderNo;
                str4 = str5;
            } else if (enumC12015g != EnumC12015g.PWD_CHANGE) {
                if (enumC12015g != EnumC12015g.H5_PAY_RESULT) {
                    if (enumC12015g == EnumC12015g.H5_PAY_DEALING) {
                        m6086o0();
                        this.f20321v0 = true;
                        ((C1529j) this.f24311a).m22172b(this.f20315s0.getData().getTradeOrderNo());
                        return;
                    }
                    return;
                }
                this.f20319u0 = null;
                String str6 = (String) enumC12015g.f24357a;
                enumC12015g.f24357a = null;
                if (TextUtils.isEmpty(str6) || !str6.equals(mo6127V())) {
                    return;
                }
                m2082n0();
                return;
            } else {
                WPCompleteUserInfoNotifyBean wPCompleteUserInfoNotifyBean2 = (WPCompleteUserInfoNotifyBean) enumC12015g.f24357a;
                if (wPCompleteUserInfoNotifyBean2 == null || (wPDefaultOrderInfoBean = this.f20315s0) == null || wPDefaultOrderInfoBean.getData() == null) {
                    return;
                }
                String tradeOrderNo2 = this.f20315s0.getData().getTradeOrderNo();
                String str7 = this.f20311p0;
                scene = wPCompleteUserInfoNotifyBean2.getScene();
                c1529j = (C1529j) this.f24311a;
                str = "PWD";
                str2 = "";
                str3 = tradeOrderNo2;
                str4 = str7;
            }
            c1529j.m22173a(str, str2, str3, str4, false, scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: s0 */
    public final void m6081s0() {
        mo6104a(getResources().getString(C10531R.string.up_order_detail_error), "");
        m6085p0();
    }

    /* renamed from: t0 */
    public final void m6080t0() {
        if (this.f24321k == null) {
            WPTrendsEventsUtils.trendsPageData("支付确认弹窗", "98U01170wp170", "wp170");
            m2003a(getResources().getString(C10531R.string.up_other_pay_dealing_title), getResources().getString(C10531R.string.up_other_pay_dealing_msg), getResources().getString(C10531R.string.up_other_pay_dealing_cancel), new C10657c(), getResources().getString(C10531R.string.up_other_pay_dealing_confirm), false, new C10658d(), true, true);
            this.f24321k.f24380a = new C10659e();
        } else if (this.f20327y0) {
            m2010Z();
            mo1791a(C10531R.string.up_other_pay_dealing_no_pay);
        }
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: u */
    public final void mo6079u() {
        m6096c(true);
        C11889u c11889u = this.f20299d0;
        if (c11889u != null) {
            c11889u.notifyDataSetChanged();
        }
    }

    /* renamed from: u0 */
    public final void m6078u0() {
        if (this.f20270G0 == null) {
            ((C1529j) this.f24311a).m22170d();
            return;
        }
        WPPayInfoBean m6084q0 = m6084q0();
        if (m6084q0 == null) {
            return;
        }
        if (this.f20260B0 == null) {
            View$OnClickListenerC12020c m1979a = View$OnClickListenerC12020c.m1979a(m6084q0.getToolDiscountMsg(), "QPAY_DETAIL_SIGN", this.f20270G0);
            this.f20260B0 = m1979a;
            m1979a.f24377x = this;
        }
        this.f20260B0.show(getSupportFragmentManager(), "openQPayDialog");
    }

    @Override // p089b0.InterfaceC1472f
    /* renamed from: x */
    public final String mo6077x() {
        WPUnionOrderInfoBean wPUnionOrderInfoBean = this.f20313r0;
        return (wPUnionOrderInfoBean == null || wPUnionOrderInfoBean.getOrderInfo() == null) ? "" : this.f20313r0.getOrderInfo().getOrderCache();
    }

    @Override // p089b0.InterfaceC1472f
    /* renamed from: y */
    public final String mo6076y() {
        WPPayInfoBean m6084q0 = m6084q0();
        if (m6084q0 == null) {
            return "";
        }
        if (!WPPayInfoBean.WFQ.equals(m6084q0.getToolCode()) || m6084q0.getFqInfo() == null || m6084q0.getFqInfo().getFqNumInfos() == null) {
            return m6084q0.getToolExpand();
        }
        String str = "";
        Iterator<WPToolFqNumInfoBean> it = m6084q0.getFqInfo().getFqNumInfos().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            WPToolFqNumInfoBean next = it.next();
            if (next.isChecked()) {
                str = next.getFqNumExpand();
                break;
            }
        }
        return TextUtils.isEmpty(str) ? m6084q0.getToolExpand() : str;
    }

    /* renamed from: c */
    public final void m6096c(boolean z) {
        C11889u c11889u;
        this.f20325x0 = z;
        C11889u c11889u2 = this.f20298c0;
        if (c11889u2 != null) {
            c11889u2.f24236h = z;
        }
        C11889u c11889u3 = this.f20299d0;
        if (c11889u3 != null) {
            c11889u3.f24236h = z;
        }
        C11886t c11886t = this.f20300e0;
        if (c11886t != null) {
            c11886t.f24221e = z;
        }
        C11824k c11824k = this.f20301f0;
        if (c11824k == null || !c11824k.isVisible() || (c11889u = this.f20301f0.f24086i) == null) {
            return;
        }
        c11889u.f24236h = z;
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: d */
    public final void mo6095d(WPResult<WPUnionPayResultBean> wPResult) {
        m2010Z();
        m2086f(wPResult);
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: b */
    public final void mo6101b(int i) {
        m6096c(true);
        this.f20298c0.notifyItemChanged(i);
    }

    /* renamed from: d */
    public final void m6092d(boolean z) {
        WPUnionOrderInfoBean wPUnionOrderInfoBean;
        if (this.f20274I0 || this.f20329z0 == 3 || this.f20276J0 || (wPUnionOrderInfoBean = this.f20313r0) == null || wPUnionOrderInfoBean.getOrderInfo() == null || TextUtils.isEmpty(this.f20313r0.getOrderInfo().getCloseMsg())) {
            if (z) {
                super.onBackPressed();
                return;
            } else {
                finish();
                return;
            }
        }
        m6082r0();
        WPTrendsEventsUtils.trendsPageData("挽留弹窗", "98U01170wp104", "wp104");
        String string = getResources().getString(C10531R.string.up_order_leave_title);
        String closeMsg = this.f20313r0.getOrderInfo().getCloseMsg();
        String string2 = getResources().getString(C10531R.string.up_order_leave_confirm);
        C10671o c10671o = new C10671o(z);
        String string3 = getResources().getString(C10531R.string.up_order_leave_cancel);
        C10672p c10672p = new C10672p();
        C12048b.C12049a m1972a = new C12048b.C12049a(this).m1973a(string).m1972a(closeMsg, 42);
        C12348l.C12349a c12349a = m1972a.f24382a;
        c12349a.f25001j = -1;
        c12349a.f24998g = false;
        c12349a.f24999h = false;
        m1972a.f24382a.f25000i = (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics());
        this.f20268F0 = m1972a.m1970a(string2, true, (C12048b.InterfaceC12050b) c10671o).m1969a(string3, true, (C12048b.InterfaceC12052d) c10672p).m1974a();
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: b */
    public final void mo6098b(String str, String str2) {
        C10679a c10679a = this.f20306k0;
        c10679a.f20375l = str;
        c10679a.show(getSupportFragmentManager(), "mRealNameDialog");
        this.f20306k0.f20376m = str2;
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: e */
    public final void mo6090e(String str) {
        C10681b c10681b = this.f20305j0;
        c10681b.f20385n = str;
        c10681b.show(getSupportFragmentManager(), "mPwdDialog");
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: a */
    public final void mo6123a(WPUserStatusBean wPUserStatusBean) {
        if (wPUserStatusBean == null) {
            return;
        }
        if ("0".equals(wPUserStatusBean.getUserState())) {
            View$OnClickListenerC12020c view$OnClickListenerC12020c = this.f20260B0;
            if (view$OnClickListenerC12020c != null) {
                view$OnClickListenerC12020c.dismissAllowingStateLoss();
            }
            if (this.f24027n == null) {
                this.f24027n = C14232a.m65h("QPAY_SIGN_PAY");
            }
            if (!this.f24027n.isAdded() && !this.f24027n.isVisible()) {
                this.f24027n.show(getSupportFragmentManager(), "QPayPassDialog");
                mo6076y();
                this.f24027n.f27728n = new C11821h(this);
                return;
            }
            this.f24027n.dismissAllowingStateLoss();
            return;
        }
        String promptTitle = wPUserStatusBean.getPromptTitle();
        String promptMsg = wPUserStatusBean.getPromptMsg();
        String leftNextStepDesc = wPUserStatusBean.getLeftNextStepDesc();
        C10660f c10660f = new C10660f();
        String rightNextStepDesc = wPUserStatusBean.getRightNextStepDesc();
        C10661g c10661g = new C10661g(wPUserStatusBean);
        C12048b.C12049a m1972a = new C12048b.C12049a(this).m1973a(promptTitle).m1972a(promptMsg, 42);
        C12348l.C12349a c12349a = m1972a.f24382a;
        c12349a.f25001j = -1;
        c12349a.f24998g = false;
        c12349a.f24999h = false;
        m1972a.f24382a.f25000i = (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics());
        m1972a.m1970a(leftNextStepDesc, true, (C12048b.InterfaceC12050b) c10660f).m1969a(rightNextStepDesc, true, (C12048b.InterfaceC12052d) c10661g).m1974a();
    }

    @Override // p387e0.C11849b.InterfaceC11851b
    /* renamed from: a */
    public final void mo2067a(WPDiscountInfoBean wPDiscountInfoBean, boolean z) {
        if (this.f20315s0 == null) {
            return;
        }
        this.f20258A0 = wPDiscountInfoBean.getDiscountType();
        if ("1".equals(wPDiscountInfoBean.getEventIconType())) {
            ((C1529j) this.f24311a).m22174a("1", wPDiscountInfoBean.getDzqDiscountExpand(), mo6076y(), this.f20315s0.getData().getTradeOrderNo(), z ? "1" : "0", mo6077x(), this.f20298c0.m2059a());
            return;
        }
        C0784b m22223a = C0784b.m22223a(wPDiscountInfoBean.getDiscountDetails(), this.f20258A0, getString(C10531R.string.wp_select_discount_detail_title1), "1");
        this.f20303h0 = m22223a;
        m22223a.show(getSupportFragmentManager(), "detailDialogFragment");
        this.f20303h0.f2436l = this;
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:0x015c, code lost:
        if (r9.f20280L0 != false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x017c, code lost:
        if (r9.f20280L0 != false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x017e, code lost:
        r7 = 5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0180, code lost:
        r7 = 0;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m6113a(com.unicom.pay.normal.order.bean.WPPayInfoBean r10, android.support.p086v7.widget.RecyclerView.ViewHolder r11, int r12) {
        /*
            Method dump skipped, instructions count: 449
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unicom.pay.normal.order.p359ui.WPOrderActivity.m6113a(com.unicom.pay.normal.order.bean.WPPayInfoBean, android.support.v7.widget.RecyclerView$ViewHolder, int):void");
    }

    @Override // p089b0.InterfaceC1472f
    /* renamed from: d */
    public final void mo6093d(String str) {
        C1529j c1529j = (C1529j) this.f24311a;
        c1529j.getClass();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("tradeOrderNo", str);
        c1529j.m1797a(C14255c.C14256a.f27777a.m30a(C14262f.f27790C, hashMap, new C1521f(c1529j, c1529j)));
    }

    /* renamed from: a */
    public final void m6114a(WPPayInfoBean wPPayInfoBean, RecyclerView.ViewHolder viewHolder) {
        WPUnionOrderInfoBean wPUnionOrderInfoBean;
        C1529j c1529j;
        String mo6077x;
        String mo6076y;
        String toolExpand;
        if (this.f20315s0 == null || (wPUnionOrderInfoBean = this.f20313r0) == null || wPUnionOrderInfoBean.getOrderInfo() == null) {
            return;
        }
        if (this.f20298c0.f24234f == viewHolder.getAdapterPosition()) {
            ((C1529j) this.f24311a).m22171b(mo6077x(), mo6076y(), "2", this.f20315s0.getData().getTradeOrderNo(), wPPayInfoBean.getToolCode());
            return;
        }
        m6096c(false);
        if (!WPPayInfoBean.WFQ.equals(wPPayInfoBean.getToolCode()) || wPPayInfoBean.getFqInfo() == null) {
            c1529j = (C1529j) this.f24311a;
            mo6077x = mo6077x();
            mo6076y = mo6076y();
            toolExpand = wPPayInfoBean.getToolExpand();
        } else {
            String str = "";
            Iterator<WPToolFqNumInfoBean> it = wPPayInfoBean.getFqInfo().getFqNumInfos().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WPToolFqNumInfoBean next = it.next();
                if (next.isChecked()) {
                    str = next.getFqNumExpand();
                    break;
                }
            }
            if (TextUtils.isEmpty(str)) {
                str = wPPayInfoBean.getToolExpand();
            }
            toolExpand = str;
            c1529j = (C1529j) this.f24311a;
            mo6077x = mo6077x();
            mo6076y = mo6076y();
        }
        c1529j.m22176a(mo6077x, mo6076y, toolExpand, this.f20315s0.getData().getTradeOrderNo(), 3, viewHolder.getAdapterPosition());
        m6115a(wPPayInfoBean);
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: a */
    public final void mo6105a(String str, WPOtherPayResultBean wPOtherPayResultBean) {
        int i;
        if ("0".equals(wPOtherPayResultBean.getOtherPayEnv())) {
            if (!WPPayInfoBean.f20225WX.equals(str)) {
                this.f20319u0 = str;
                if (!WPPayInfoBean.f20226YB.equals(str)) {
                    WPPayInfoBean.f20229ZL.equals(str);
                }
                UnicomPaySDK.getInstance().getNativeFunctionCallback().openWebview(this, wPOtherPayResultBean.getPayUrl());
                return;
            } else if (C13653p.m173a(this, "com.tencent.mm")) {
                this.f20319u0 = str;
                UnicomPaySDK.getInstance().getNativeFunctionCallback().openWebview(this, wPOtherPayResultBean.getPayUrl(), wPOtherPayResultBean.getReferer());
                return;
            } else {
                i = C10531R.string.up_other_pay_wx_uninstall;
            }
        } else if (!"1".equals(wPOtherPayResultBean.getOtherPayEnv())) {
            return;
        } else {
            if (WPPayInfoBean.ZFB.equals(str)) {
                this.f20319u0 = str;
                String payUrl = wPOtherPayResultBean.getPayUrl();
                Type type = new C10673q().getType();
                Gson gson = C13648k.f27492a;
                Object fromJson = !(gson instanceof Gson) ? gson.fromJson(payUrl, type) : NBSGsonInstrumentation.fromJson(gson, payUrl, type);
                HashMap hashMap = new HashMap();
                hashMap.put("orderStr", (String) ((HashMap) fromJson).get("order_info"));
                Gson gson2 = C13648k.f27492a;
                C13652o.m174a("UnicomPaySDK ali", !(gson2 instanceof Gson) ? gson2.toJson(hashMap) : NBSGsonInstrumentation.toJson(gson2, hashMap));
                NativeFunctionCallBack nativeFunctionCallback = UnicomPaySDK.getInstance().getNativeFunctionCallback();
                Gson gson3 = C13648k.f27492a;
                nativeFunctionCallback.aliPay(this, !(gson3 instanceof Gson) ? gson3.toJson(hashMap) : NBSGsonInstrumentation.toJson(gson3, hashMap), new C10674r());
                return;
            } else if (WPPayInfoBean.f20228YL.equals(str)) {
                if (C13653p.m173a(this, "com.unionpay")) {
                    this.f20319u0 = str;
                    WPMobPayBean wPMobPayBean = new WPMobPayBean();
                    wPMobPayBean.setOrderTn(wPOtherPayResultBean.getPayUrl());
                    wPMobPayBean.setMode("00");
                    NativeFunctionCallBack nativeFunctionCallback2 = UnicomPaySDK.getInstance().getNativeFunctionCallback();
                    Gson gson4 = C13648k.f27492a;
                    nativeFunctionCallback2.unionPay(this, !(gson4 instanceof Gson) ? gson4.toJson(wPMobPayBean) : NBSGsonInstrumentation.toJson(gson4, wPMobPayBean), new C10675s());
                    return;
                }
                i = C10531R.string.up_other_pay_yun_uninstall;
            } else if (!WPPayInfoBean.f20223SB.equals(str)) {
                return;
            } else {
                if (C13653p.m173a(this, "cn.gov.pbc.dcep")) {
                    this.f20319u0 = str;
                    StringBuilder m22016a = C1730a.m22016a("拉起： ");
                    m22016a.append(wPOtherPayResultBean.getPayUrl());
                    C13652o.m174a("UnicomPaySDK dcep", m22016a.toString());
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(wPOtherPayResultBean.getPayUrl())));
                    return;
                }
                i = C10531R.string.up_other_pay_dcep_uninstall;
            }
        }
        mo1790i(getResources().getString(i));
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: a */
    public final void mo6116a(WPPayBeforeBean wPPayBeforeBean) {
        this.f24025l = 0;
        WPQPayUserInfoBean m164a = C10546a.C10576i.f20125a.f20053c.m164a(this.f24030q);
        this.f24026m = m164a;
        ((C1529j) this.f24311a).m22169a(m164a, wPPayBeforeBean);
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: a */
    public final void mo6118a(WPDiscountQueryBean wPDiscountQueryBean, String str, String str2) {
        m6096c(true);
        if (wPDiscountQueryBean != null) {
            if ("1".equals(str)) {
                this.f20302g0.m22221a(wPDiscountQueryBean);
                this.f20302g0.show(getSupportFragmentManager(), "mDiscountDialogFragment");
            } else if ("2".equals(str)) {
                m1991k("点击-支付方式优惠");
                if (wPDiscountQueryBean.getDiscountInfos() != null && !wPDiscountQueryBean.getDiscountInfos().isEmpty()) {
                    C0784b m22223a = C0784b.m22223a(wPDiscountQueryBean.getDiscountInfos().get(0).getDiscountDetails(), WPDiscountInfoBean.PAYTOOLSYX, getResources().getString((WPPayInfoBean.f20222KJ.equals(str2) || WPPayInfoBean.WFQ.equals(str2)) ? C10531R.string.wp_select_discount_detail_title3 : C10531R.string.wp_select_discount_detail_title2), str);
                    this.f20303h0 = m22223a;
                    m22223a.show(getSupportFragmentManager(), "detailDialogFragment");
                }
            } else if ("3".equals(str) && wPDiscountQueryBean.getDiscountInfos() != null && !wPDiscountQueryBean.getDiscountInfos().isEmpty()) {
                m1991k("点击-沃支付优惠");
                C0784b m22223a2 = C0784b.m22223a(wPDiscountQueryBean.getDiscountInfos().get(0).getDiscountDetails(), WPDiscountInfoBean.TYYX, getResources().getString(C10531R.string.wp_select_discount_detail_title1), str);
                this.f20303h0 = m22223a2;
                m22223a2.show(getSupportFragmentManager(), "tyyhDetailDialogFragment");
            } else if ("4".equals(str) && wPDiscountQueryBean.getDiscountInfos() != null && !wPDiscountQueryBean.getDiscountInfos().isEmpty()) {
                m1991k("点击-电子券");
                String string = getResources().getString(C10531R.string.wp_select_discount_detail_title4);
                int i = C0777a.f2409x;
                Bundle bundle = new Bundle();
                bundle.putParcelable("discountDetail", wPDiscountQueryBean);
                bundle.putString("title", string);
                C0777a c0777a = new C0777a();
                c0777a.setArguments(bundle);
                this.f20304i0 = c0777a;
                c0777a.f2426w = this;
                c0777a.show(getSupportFragmentManager(), "mDiscountDZQDetailDialogFragment");
            }
        }
        C11889u c11889u = this.f20299d0;
        if (c11889u != null) {
            c11889u.notifyDataSetChanged();
        }
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: a */
    public final void mo6111a(WPQueryPayInfoListBean wPQueryPayInfoListBean, int i) {
        m6096c(true);
        this.f20298c0.notifyItemChanged(i);
        if (wPQueryPayInfoListBean == null || wPQueryPayInfoListBean.getWapPayList() == null) {
            return;
        }
        C11824k c11824k = this.f20301f0;
        ArrayList<WPPayInfoBean> wapPayList = wPQueryPayInfoListBean.getWapPayList();
        c11824k.f24087j.clear();
        c11824k.f24087j.addAll(wapPayList);
        this.f20301f0.show(getSupportFragmentManager(), "mUnicomMethodsFragment");
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: a */
    public final void mo6117a(WPNoticeListInfoBean wPNoticeListInfoBean) {
        if (wPNoticeListInfoBean == null) {
            return;
        }
        try {
            ImageView imageView = this.f20297b0;
            if (imageView != null) {
                imageView.setImageResource(C10531R.C10533drawable.up_notice_arrow);
            }
            C11819f c11819f = this.f20307l0;
            if (c11819f != null) {
                c11819f.show(getSupportFragmentManager(), "mNoticeDialogFragment");
                C11819f c11819f2 = this.f20307l0;
                List<WPNoticeInfoBean> noticeContents = wPNoticeListInfoBean.getNoticeContents();
                if (noticeContents == null) {
                    c11819f2.f24078j.clear();
                    return;
                }
                c11819f2.f24078j.clear();
                c11819f2.f24078j.addAll(noticeContents);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x004b, code lost:
        if (r5.f20323w0 != false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0068, code lost:
        if (r5.f20323w0 != false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x006a, code lost:
        mo1791a(com.unicom.pay.C10531R.string.up_other_pay_dealing_no_pay);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0072, code lost:
        if (r5.f20321v0 != false) goto L20;
     */
    @Override // p089b0.InterfaceC1470d
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void mo6124a(com.unicom.pay.common.bean.WPResult<com.unicom.pay.modules.verify.bean.WPUnionPayResultBean> r6) {
        /*
            r5 = this;
            if (r6 != 0) goto L3
            return
        L3:
            java.lang.String r0 = r6.getCode()
            java.lang.String r1 = "0000"
            boolean r0 = r1.equals(r0)
            r1 = 0
            if (r0 == 0) goto L28
            android.widget.Button r0 = r5.f20289T
            if (r0 == 0) goto L25
            int r0 = com.unicom.pay.C10531R.string.up_other_pay_success
            r5.mo1791a(r0)
            android.widget.Button r0 = r5.f20289T
            d0.g r2 = new d0.g
            r2.<init>(r5, r6)
            r3 = 1000(0x3e8, double:4.94E-321)
            r0.postDelayed(r2, r3)
        L25:
            r5.f20319u0 = r1
            goto L83
        L28:
            java.lang.String r0 = r6.getCode()
            java.lang.String r2 = "406114034"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L7b
            java.lang.String r0 = r5.f20319u0
            java.lang.String r1 = "WX"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L3f
            goto L58
        L3f:
            java.lang.String r0 = r5.f20319u0
            java.lang.String r1 = "ZFB"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L4e
            boolean r0 = r5.f20323w0
            if (r0 == 0) goto L74
            goto L6a
        L4e:
            java.lang.String r0 = r5.f20319u0
            java.lang.String r1 = "SB"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L5c
        L58:
            r5.m6080t0()
            goto L83
        L5c:
            java.lang.String r0 = r5.f20319u0
            java.lang.String r1 = "YL"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L70
            boolean r0 = r5.f20323w0
            if (r0 == 0) goto L74
        L6a:
            int r6 = com.unicom.pay.C10531R.string.up_other_pay_dealing_no_pay
            r5.mo1791a(r6)
            goto L83
        L70:
            boolean r0 = r5.f20321v0
            if (r0 == 0) goto L83
        L74:
            r5.m2010Z()
            r5.m2086f(r6)
            goto L83
        L7b:
            r5.m2010Z()
            r5.m2086f(r6)
            r5.f20319u0 = r1
        L83:
            r6 = 0
            r5.f20323w0 = r6
            r5.f20321v0 = r6
            r5.f20327y0 = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unicom.pay.normal.order.p359ui.WPOrderActivity.mo6124a(com.unicom.pay.common.bean.WPResult):void");
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: a */
    public final void mo6120a(WPQPayResultBean wPQPayResultBean, String str, String str2) {
        try {
            if (this.f20313r0 != null && this.f20315s0 != null) {
                C14239e m51a = C14239e.m51a(C14239e.m46a(wPQPayResultBean.getPhoneNo(), this.f20315s0.getData().getTradeOrderNo(), str, str2, mo6077x(), mo6076y()));
                this.f20264D0 = m51a;
                if (!m51a.isAdded() && !this.f20264D0.isVisible()) {
                    this.f20264D0.show(getSupportFragmentManager(), "DetailOpenQPayVerifyCodeDialog");
                    this.f20264D0.f27737A = new C10667k();
                    return;
                }
                this.f20264D0.dismissAllowingStateLoss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: a */
    public final void mo6121a(WPQOpenResultBean wPQOpenResultBean, boolean z) {
        if (wPQOpenResultBean == null) {
            return;
        }
        if (!wPQOpenResultBean.isSignSuccess()) {
            mo1790i(wPQOpenResultBean.getSignResultMsg());
            return;
        }
        this.f24031r = true;
        if (z) {
            ImageView imageView = this.f20288S;
            if (imageView != null) {
                imageView.postDelayed(new RunnableC10663h(wPQOpenResultBean), 1500L);
            }
        } else {
            m6087n(wPQOpenResultBean.getPayToken());
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
        }
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: a */
    public final void mo6119a(WPDiscountListBean wPDiscountListBean) {
        Button button;
        double d;
        String str;
        WPUnionOrderInfoBean wPUnionOrderInfoBean;
        mo1793I();
        C0788c c0788c = this.f20302g0;
        if (c0788c != null && c0788c.isVisible()) {
            C0788c c0788c2 = this.f20302g0;
            c0788c2.getClass();
            if (wPDiscountListBean != null && wPDiscountListBean.getQueryDisCountListV2Resp() != null) {
                C11849b c11849b = c0788c2.f2443j;
                ArrayList<WPDiscountInfoBean> discountInfos = wPDiscountListBean.getQueryDisCountListV2Resp().getDiscountInfos();
                c11849b.f24105b.clear();
                if (discountInfos != null) {
                    c11849b.f24105b.addAll(discountInfos);
                }
                c11849b.notifyDataSetChanged();
            } else {
                C11849b c11849b2 = c0788c2.f2443j;
                ArrayList arrayList = new ArrayList();
                c11849b2.f24105b.clear();
                c11849b2.f24105b.addAll(arrayList);
                c11849b2.notifyDataSetChanged();
            }
            if (wPDiscountListBean != null && c0788c2.f2441h != null) {
                c0788c2.f2441h.setText(wPDiscountListBean.getQueryDisCountListV2Resp().getDiscountMsg());
            }
        }
        C0784b c0784b = this.f20303h0;
        boolean z = true;
        if (c0784b != null && c0784b.isVisible()) {
            this.f20303h0.m22222a(true);
            this.f20303h0.dismissAllowingStateLoss();
        }
        C0777a c0777a = this.f20304i0;
        if (c0777a != null) {
            c0777a.m22224c(true);
            C0777a c0777a2 = this.f20304i0;
            WPDiscountQueryBean queryDisCountListV2Resp = wPDiscountListBean.getQueryDisCountListV2Resp();
            c0777a2.f2414k = queryDisCountListV2Resp;
            if (queryDisCountListV2Resp != null && queryDisCountListV2Resp.getDiscountInfos() != null && queryDisCountListV2Resp.getDiscountInfos().get(0) != null && queryDisCountListV2Resp.getDiscountInfos().get(0).getDzqPage() != null && queryDisCountListV2Resp.getDiscountInfos().get(0).getDzqPage().getDzqInfos() != null) {
                Iterator<WPDzqInfosBean> it = queryDisCountListV2Resp.getDiscountInfos().get(0).getDzqPage().getDzqInfos().iterator();
                while (it.hasNext()) {
                    WPDzqInfosBean next = it.next();
                    if ("1".equals(next.getDzqType())) {
                        c0777a2.m22226a(next);
                    } else {
                        c0777a2.m22225b(next);
                    }
                }
                try {
                    String dzqMsg = c0777a2.f2414k.getDiscountInfos().get(0).getDzqPage().getDzqMsg();
                    String dzqAmt = c0777a2.f2414k.getDiscountInfos().get(0).getDzqPage().getDzqAmt();
                    C13662t c13662t = new C13662t(dzqMsg);
                    c13662t.m166a();
                    c13662t.f27499a = dzqAmt;
                    c13662t.f27501c = c0777a2.getResources().getColor(C10531R.C10532color.wp_red_color);
                    c13662t.m166a();
                    c0777a2.f2424u.setText(c13662t.f27507i);
                    c0777a2.f2424u.setVisibility(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (wPDiscountListBean == null) {
            return;
        }
        C11889u c11889u = this.f20298c0;
        if (c11889u != null) {
            c11889u.m2058a(wPDiscountListBean);
        }
        C11889u c11889u2 = this.f20299d0;
        if (c11889u2 != null) {
            c11889u2.m2058a(wPDiscountListBean);
        }
        if (TextUtils.isEmpty(wPDiscountListBean.getOrderDiscountMsg())) {
            this.f20273I.setVisibility(8);
            this.f20267F.setVisibility(0);
        } else {
            this.f20267F.setVisibility(8);
            this.f20273I.setOnClickListener(null);
            this.f20273I.setVisibility(0);
            this.f20275J.setText(wPDiscountListBean.getOrderDiscountMsg());
            if ("1".equals(wPDiscountListBean.getOrderDiscountIconType())) {
                this.f20273I.setOnClickListener(this);
                this.f20277K.setVisibility(0);
            } else {
                this.f20277K.setVisibility(8);
            }
        }
        WPUnionOrderInfoBean wPUnionOrderInfoBean2 = this.f20313r0;
        if (wPUnionOrderInfoBean2 != null && wPUnionOrderInfoBean2.getOrderInfo() != null) {
            this.f20313r0.getOrderInfo().setOrderDiscountMsg(wPDiscountListBean.getOrderDiscountMsg());
            this.f20313r0.getOrderInfo().setOrderDiscountNext(wPDiscountListBean.getOrderDiscountNext());
            this.f20313r0.getOrderInfo().setOrderDiscountIconType(wPDiscountListBean.getOrderDiscountIconType());
            this.f20313r0.getOrderInfo().setOrderDiscountIconState(wPDiscountListBean.getOrderDiscountIconState());
            this.f20313r0.getOrderInfo().setOrderCache(wPDiscountListBean.getOrderCache());
        }
        if (TextUtils.isEmpty(wPDiscountListBean.getOrderAmountSubscript())) {
            this.f20326y.setVisibility(8);
        } else {
            this.f20326y.setText(wPDiscountListBean.getOrderAmountSubscript());
            this.f20326y.setVisibility(0);
        }
        WPTrendsEventsUtils.setRealPayAmount(wPDiscountListBean.getRealPayAmount());
        if (!"1".equals(wPDiscountListBean.getIsShowOrderAmount()) || TextUtils.isEmpty(wPDiscountListBean.getRealPayAmount()) || (wPUnionOrderInfoBean = this.f20313r0) == null || wPUnionOrderInfoBean.getOrderInfo() == null || this.f20313r0.getOrderInfo().getOrderAmount().equals(wPDiscountListBean.getRealPayAmount())) {
            this.f20322w.setText(C13646i.m181a(wPDiscountListBean.getRealPayAmount()));
            this.f20324x.setVisibility(8);
        } else {
            this.f20322w.setText(C13646i.m181a(wPDiscountListBean.getRealPayAmount()));
            this.f20324x.setVisibility(0);
        }
        try {
            if (TextUtils.isEmpty(wPDiscountListBean.getRealPayAmount())) {
                str = "-";
            } else {
                Long valueOf = Long.valueOf(Long.parseLong(this.f20313r0.getOrderInfo().getOrderAmount()));
                Long valueOf2 = Long.valueOf(Long.parseLong(wPDiscountListBean.getRealPayAmount()));
                str = Math.abs(valueOf.longValue() - valueOf2.longValue()) + "";
            }
            WPTrendsEventsUtils.setPreferentialAmount(str);
        } catch (Exception unused) {
        }
        WPPayInfoBean m6084q0 = m6084q0();
        if (m6084q0 == null || TextUtils.isEmpty(m6084q0.getAmount()) || TextUtils.isEmpty(wPDiscountListBean.getRealPayAmount())) {
            button = this.f20289T;
        } else {
            double d2 = 0.0d;
            try {
                d = Double.parseDouble(m6084q0.getAmount());
                try {
                    d2 = Double.parseDouble(wPDiscountListBean.getRealPayAmount());
                } catch (Exception unused2) {
                }
            } catch (Exception unused3) {
                d = 0.0d;
            }
            button = this.f20289T;
            if (d < d2) {
                z = false;
            }
        }
        button.setEnabled(z);
        if (wPDiscountListBean.getOrderButton() != null) {
            this.f20313r0.setOrderButton(wPDiscountListBean.getOrderButton());
            this.f20289T.setText(wPDiscountListBean.getOrderButton().getButtonInfo());
        }
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: a */
    public final void mo6126a(int i, int i2) {
        m6096c(true);
        if (i == 0 || 4 == i || 3 == i || 5 == i || 1 == i) {
            this.f20298c0.notifyItemChanged(i2);
        } else if (2 != i) {
            return;
        } else {
            this.f20298c0.notifyItemChanged(i2);
            C11824k c11824k = this.f20301f0;
            if (c11824k != null && c11824k.isVisible()) {
                this.f20301f0.dismissAllowingStateLoss();
            }
        }
        this.f20300e0.notifyItemChanged(i2);
    }

    @Override // p089b0.InterfaceC1470d
    /* renamed from: a */
    public final void mo6109a(WPUpdateMethodResultBean wPUpdateMethodResultBean, int i, int i2) {
        String str;
        double d;
        WPUnionOrderInfoBean wPUnionOrderInfoBean;
        List<WPPayInfoBean> list;
        if (this.f20298c0 == null || this.f20300e0 == null) {
            return;
        }
        m6096c(true);
        if (i == 0 || 4 == i || 5 == i) {
            C11889u c11889u = this.f20298c0;
            c11889u.f24234f = i2;
            if (c11889u.f24238j) {
                c11889u.f24238j = false;
            }
            c11889u.notifyDataSetChanged();
            this.f20300e0.m2061a();
            if (wPUpdateMethodResultBean != null) {
                m6110a(wPUpdateMethodResultBean);
            }
            if (wPUpdateMethodResultBean != null && wPUpdateMethodResultBean.getPayToolInfo() != null) {
                this.f20298c0.m2057a(wPUpdateMethodResultBean.getPayToolInfo());
            }
            if (5 == i) {
                WPPayInfoBean m6084q0 = m6084q0();
                WPUnionOrderInfoBean wPUnionOrderInfoBean2 = this.f20313r0;
                if (wPUnionOrderInfoBean2 != null && wPUnionOrderInfoBean2.getOrderInfo() != null && m6084q0 != null) {
                    String str2 = "";
                    if (WPPayInfoBean.f20224TY.equals(this.f20282M0)) {
                        str2 = "3";
                    } else if ("DZQ".equals(this.f20282M0)) {
                        str2 = "4";
                    }
                    ((C1529j) this.f24311a).m22171b(mo6077x(), mo6076y(), str2, this.f20315s0.getData().getTradeOrderNo(), m6084q0.getToolCode());
                }
            }
        } else if (1 == i) {
            C11889u c11889u2 = this.f20298c0;
            int i3 = c11889u2.f24234f;
            c11889u2.f24234f = -2;
            if (c11889u2.f24238j) {
                c11889u2.f24238j = false;
                c11889u2.notifyDataSetChanged();
            } else {
                c11889u2.notifyItemChanged(i3);
            }
            C11886t c11886t = this.f20300e0;
            c11886t.f24219c = i2;
            c11886t.notifyDataSetChanged();
            m6110a(wPUpdateMethodResultBean);
        } else if (2 == i) {
            if (wPUpdateMethodResultBean != null) {
                m6110a(wPUpdateMethodResultBean);
                C11889u c11889u3 = this.f20298c0;
                WPPayInfoBean payToolInfo = wPUpdateMethodResultBean.getPayToolInfo();
                c11889u3.getClass();
                if (payToolInfo != null && c11889u3.f24230b != null) {
                    try {
                        if (c11889u3.f24238j) {
                            c11889u3.f24238j = false;
                        }
                        payToolInfo.setChecked("1");
                        int size = c11889u3.f24230b.size();
                        if (size > 0) {
                            int i4 = 0;
                            int i5 = -1;
                            while (true) {
                                if (i4 >= size) {
                                    int i6 = c11889u3.f24234f;
                                    if (i6 < 0 || i6 >= c11889u3.getItemCount() || !(WPPayInfoBean.f20222KJ.equals(c11889u3.f24230b.get(c11889u3.f24234f).getToolCode()) || WPPayInfoBean.f20227YE.equals(c11889u3.f24230b.get(c11889u3.f24234f).getToolCode()) || WPPayInfoBean.f20221BK.equals(c11889u3.f24230b.get(c11889u3.f24234f).getToolCode()) || WPPayInfoBean.WFQ.equals(c11889u3.f24230b.get(c11889u3.f24234f).getToolCode()))) {
                                        c11889u3.f24234f = i5;
                                        c11889u3.f24230b.remove(i5);
                                        c11889u3.notifyItemRemoved(c11889u3.f24234f);
                                        list = c11889u3.f24230b;
                                    } else {
                                        c11889u3.f24230b.remove(c11889u3.f24234f);
                                        c11889u3.notifyItemRemoved(c11889u3.f24234f);
                                        list = c11889u3.f24230b;
                                    }
                                    list.add(c11889u3.f24234f, payToolInfo);
                                } else if (payToolInfo.getToolId().equals(c11889u3.f24230b.get(i4).getToolId())) {
                                    c11889u3.f24230b.remove(i4);
                                    c11889u3.notifyItemRemoved(i4);
                                    c11889u3.f24230b.add(i4, payToolInfo);
                                    c11889u3.f24234f = i4;
                                    break;
                                } else {
                                    if (i5 == -1 && WPPayInfoBean.WAP_PAY.equals(c11889u3.f24230b.get(i4).getToolGroup())) {
                                        i5 = i4;
                                    }
                                    i4++;
                                }
                            }
                            c11889u3.notifyDataSetChanged();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            C11824k c11824k = this.f20301f0;
            if (c11824k != null && c11824k.isVisible()) {
                this.f20301f0.dismissAllowingStateLoss();
            }
            this.f20300e0.m2061a();
        } else if (3 == i) {
            C11889u c11889u4 = this.f20298c0;
            c11889u4.f24234f = i2;
            if (c11889u4.f24238j) {
                c11889u4.f24238j = false;
            }
            c11889u4.notifyDataSetChanged();
            this.f20300e0.m2061a();
            if (wPUpdateMethodResultBean != null && wPUpdateMethodResultBean.getPayToolInfo() != null) {
                this.f20298c0.m2057a(wPUpdateMethodResultBean.getPayToolInfo());
            }
            WPPayInfoBean m6084q02 = m6084q0();
            WPUnionOrderInfoBean wPUnionOrderInfoBean3 = this.f20313r0;
            if (wPUnionOrderInfoBean3 != null && wPUnionOrderInfoBean3.getOrderInfo() != null && m6084q02 != null) {
                ((C1529j) this.f24311a).m22171b(mo6077x(), mo6076y(), "2", this.f20315s0.getData().getTradeOrderNo(), m6084q02.getToolCode());
            }
        }
        if (wPUpdateMethodResultBean != null) {
            WPTrendsEventsUtils.setRealPayAmount(wPUpdateMethodResultBean.getRealPayAmount());
            if (!"1".equals(wPUpdateMethodResultBean.getIsShowOrderAmount()) || TextUtils.isEmpty(wPUpdateMethodResultBean.getRealPayAmount()) || (wPUnionOrderInfoBean = this.f20313r0) == null || wPUnionOrderInfoBean.getOrderInfo() == null || this.f20313r0.getOrderInfo().getOrderAmount().equals(wPUpdateMethodResultBean.getRealPayAmount())) {
                this.f20322w.setText(C13646i.m181a(wPUpdateMethodResultBean.getRealPayAmount()));
                this.f20324x.setVisibility(8);
            } else {
                this.f20322w.setText(C13646i.m181a(wPUpdateMethodResultBean.getRealPayAmount()));
                this.f20324x.setVisibility(0);
            }
            if (!TextUtils.isEmpty(wPUpdateMethodResultBean.getToastMsg())) {
                mo1790i(wPUpdateMethodResultBean.getToastMsg());
            }
        }
        WPPayInfoBean m6084q03 = m6084q0();
        if (m6084q03 != null) {
            WPTrendsEventsUtils.setPayMethod((WPPayInfoBean.f20222KJ.equals(m6084q03.getToolCode()) || WPPayInfoBean.WFQ.equals(m6084q03.getToolCode())) ? "银行卡" : m6084q03.getToolName());
        }
        if (m6084q03 == null || wPUpdateMethodResultBean == null) {
            return;
        }
        if (TextUtils.isEmpty(m6084q03.getAmount())) {
            this.f20289T.setEnabled(true);
        } else {
            double d2 = 0.0d;
            try {
                d = Double.parseDouble(m6084q03.getAmount());
                try {
                    d2 = Double.parseDouble(!TextUtils.isEmpty(wPUpdateMethodResultBean.getRealPayAmount()) ? wPUpdateMethodResultBean.getRealPayAmount() : !TextUtils.isEmpty(this.f20313r0.getOrderInfo().getOrderAmount()) ? this.f20313r0.getOrderInfo().getOrderAmount() : "0");
                } catch (Exception unused) {
                }
            } catch (Exception unused2) {
                d = 0.0d;
            }
            this.f20289T.setEnabled(d >= d2);
        }
        if (wPUpdateMethodResultBean.getOrderButton() != null && !TextUtils.isEmpty(wPUpdateMethodResultBean.getOrderButton().getButtonInfo())) {
            this.f20289T.setText(wPUpdateMethodResultBean.getOrderButton().getButtonInfo());
            this.f20313r0.setOrderButton(wPUpdateMethodResultBean.getOrderButton());
            this.f20289T.setVisibility(0);
        }
        try {
            if (TextUtils.isEmpty(wPUpdateMethodResultBean.getRealPayAmount())) {
                str = "-";
            } else {
                Long valueOf = Long.valueOf(Long.parseLong(this.f20313r0.getOrderInfo().getOrderAmount()));
                Long valueOf2 = Long.valueOf(Long.parseLong(wPUpdateMethodResultBean.getRealPayAmount()));
                str = Math.abs(valueOf.longValue() - valueOf2.longValue()) + "";
            }
            WPTrendsEventsUtils.setPreferentialAmount(str);
        } catch (Exception unused3) {
        }
    }
}
