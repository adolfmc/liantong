package com.unicom.pay.qpay.setting.p361ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.support.p086v7.widget.helper.ItemTouchHelper;
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
import com.unicom.pay.qpay.open.bean.WPQPayUserInfoBean;
import com.unicom.pay.qpay.setting.bean.WPBankBean;
import com.unicom.pay.qpay.setting.bean.WPLimitBean;
import com.unicom.pay.qpay.setting.bean.WPSettingInfoBean;
import com.unicom.pay.utils.buried.WPTrendsEventsUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import p393h.AbstractDialogInterface$OnCancelListenerC12000b;
import p393h.EnumC12015g;
import p395i.C12048b;
import p405l0.InterfaceC12296b;
import p407m0.C12306a;
import p407m0.C12308b;
import p407m0.C12311c;
import p409n0.C12361a;
import p409n0.C12362b;
import p409n0.C12363c;
import p412o0.C12378a;
import p412o0.C12380b;
import p470p0.C13654q;
import p470p0.C13663u;
import p481v.C14232a;
import p482w.C14255c;
import p482w.C14262f;

@NBSInstrumented
/* renamed from: com.unicom.pay.qpay.setting.ui.WPQPaySettingActivity */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPQPaySettingActivity extends AbstractDialogInterface$OnCancelListenerC12000b<C12308b> implements InterfaceC12296b, C12378a.InterfaceC12379a, C12380b.InterfaceC12383c {

    /* renamed from: C */
    public static final /* synthetic */ int f20416C = 0;

    /* renamed from: A */
    public C14232a f20417A;

    /* renamed from: B */
    public boolean f20418B;
    public NBSTraceUnit _nbs_trace;

    /* renamed from: l */
    public ConstraintLayout f20419l;

    /* renamed from: m */
    public ImageView f20420m;

    /* renamed from: n */
    public ConstraintLayout f20421n;

    /* renamed from: o */
    public ImageView f20422o;

    /* renamed from: p */
    public RecyclerView f20423p;

    /* renamed from: q */
    public LinearLayout f20424q;

    /* renamed from: r */
    public TextView f20425r;

    /* renamed from: s */
    public TextView f20426s;

    /* renamed from: t */
    public TextView f20427t;

    /* renamed from: u */
    public LinearLayout f20428u;

    /* renamed from: v */
    public C12380b f20429v;

    /* renamed from: w */
    public ItemTouchHelper f20430w;

    /* renamed from: x */
    public C12378a f20431x;

    /* renamed from: y */
    public List<WPBankBean> f20432y;

    /* renamed from: z */
    public WPSettingInfoBean f20433z;

    /* renamed from: com.unicom.pay.qpay.setting.ui.WPQPaySettingActivity$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10695a implements C14232a.InterfaceC14235c {
        public C10695a() {
        }

        @Override // p481v.C14232a.InterfaceC14235c
        /* renamed from: a */
        public final void mo59a() {
            C12308b c12308b = (C12308b) WPQPaySettingActivity.this.f24311a;
            c12308b.m1798a().mo1852c();
            c12308b.m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27818h, new HashMap(), false, new C12311c(c12308b, c12308b)));
        }

        @Override // p481v.C14232a.InterfaceC14235c
        /* renamed from: b */
        public final void mo58b() {
            WPQPaySettingActivity wPQPaySettingActivity = WPQPaySettingActivity.this;
            int i = WPQPaySettingActivity.f20416C;
            P p = wPQPaySettingActivity.f24311a;
            if (p == 0) {
                return;
            }
            C13654q.m172a(p, "", new C12363c(wPQPaySettingActivity));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.qpay.setting.ui.WPQPaySettingActivity$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10696b implements C12048b.InterfaceC12050b {
        @Override // p395i.C12048b.InterfaceC12050b
        /* renamed from: a */
        public final void mo1801a() {
        }
    }

    @NBSInstrumented
    /* renamed from: com.unicom.pay.qpay.setting.ui.WPQPaySettingActivity$c */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10697c implements C12048b.InterfaceC12052d {
        public C10697c() {
        }

        @Override // p395i.C12048b.InterfaceC12052d
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            WPQPaySettingActivity wPQPaySettingActivity = WPQPaySettingActivity.this;
            int i = WPQPaySettingActivity.f20416C;
            P p = wPQPaySettingActivity.f24311a;
            if (p != 0) {
                C13654q.m172a(p, "", new C12363c(wPQPaySettingActivity));
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* renamed from: com.unicom.pay.qpay.setting.ui.WPQPaySettingActivity$d */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class RunnableC10698d implements Runnable {
        public RunnableC10698d() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            WPQPaySettingActivity wPQPaySettingActivity = WPQPaySettingActivity.this;
            wPQPaySettingActivity.mo1790i(wPQPaySettingActivity.getResources().getString(C10531R.string.wp_setting_close_success));
            WPQPaySettingActivity.this.finish();
        }
    }

    /* renamed from: a */
    public static /* synthetic */ void m6064a(View view) {
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, p393h.InterfaceC12016h
    /* renamed from: D */
    public final String mo56D() {
        return "极速支付设置页";
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, p393h.InterfaceC12016h
    /* renamed from: O */
    public final String mo53O() {
        return "qp011";
    }

    @Override // p405l0.InterfaceC12296b
    /* renamed from: R */
    public final void mo1860R() {
        try {
            if (this.f20429v == null) {
                return;
            }
            if (this.f20432y == null) {
                this.f20432y = new ArrayList();
            }
            this.f20429v.m1788a(this.f20432y);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p405l0.InterfaceC12296b
    /* renamed from: a */
    public final String mo1858a(WPGmKeyBean wPGmKeyBean) {
        C14232a c14232a = this.f20417A;
        return c14232a == null ? "" : c14232a.m68c(wPGmKeyBean);
    }

    @Override // p405l0.InterfaceC12296b
    /* renamed from: a */
    public final void mo1859a() {
        try {
            C14232a c14232a = this.f20417A;
            if (c14232a != null) {
                c14232a.m64h0();
                this.f20417A.m66g0();
                this.f20417A.dismissAllowingStateLoss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00ca A[Catch: Exception -> 0x010a, TryCatch #0 {Exception -> 0x010a, blocks: (B:4:0x0003, B:7:0x0008, B:9:0x0012, B:10:0x001d, B:12:0x0070, B:14:0x008b, B:16:0x00a8, B:19:0x00b3, B:21:0x00c0, B:23:0x00ca, B:24:0x00d1, B:26:0x00f4, B:27:0x00ff, B:20:0x00ba, B:13:0x007e), top: B:32:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00f4 A[Catch: Exception -> 0x010a, TryCatch #0 {Exception -> 0x010a, blocks: (B:4:0x0003, B:7:0x0008, B:9:0x0012, B:10:0x001d, B:12:0x0070, B:14:0x008b, B:16:0x00a8, B:19:0x00b3, B:21:0x00c0, B:23:0x00ca, B:24:0x00d1, B:26:0x00f4, B:27:0x00ff, B:20:0x00ba, B:13:0x007e), top: B:32:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00ff A[Catch: Exception -> 0x010a, TRY_LEAVE, TryCatch #0 {Exception -> 0x010a, blocks: (B:4:0x0003, B:7:0x0008, B:9:0x0012, B:10:0x001d, B:12:0x0070, B:14:0x008b, B:16:0x00a8, B:19:0x00b3, B:21:0x00c0, B:23:0x00ca, B:24:0x00d1, B:26:0x00f4, B:27:0x00ff, B:20:0x00ba, B:13:0x007e), top: B:32:0x0003 }] */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.util.List<com.unicom.pay.qpay.setting.bean.WPBankBean>, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r6v6, types: [java.util.List<com.unicom.pay.qpay.setting.bean.WPBankBean>, java.util.ArrayList] */
    @Override // p405l0.InterfaceC12296b
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void mo1857a(com.unicom.pay.qpay.setting.bean.WPSettingInfoBean r6) {
        /*
            Method dump skipped, instructions count: 271
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unicom.pay.qpay.setting.p361ui.WPQPaySettingActivity.mo1857a(com.unicom.pay.qpay.setting.bean.WPSettingInfoBean):void");
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: a0 */
    public final C12308b mo2002a0() {
        return new C12308b();
    }

    @Override // p405l0.InterfaceC12296b
    /* renamed from: b */
    public final void mo1854b() {
        try {
            C14232a c14232a = this.f20417A;
            if (c14232a == null || !c14232a.isAdded()) {
                return;
            }
            this.f20417A.dismissAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p405l0.InterfaceC12296b
    /* renamed from: b */
    public final void mo1853b(String str) {
        try {
            C14232a c14232a = this.f20417A;
            if (c14232a != null) {
                c14232a.m64h0();
                this.f20417A.m66g0();
                this.f20417A.dismissAllowingStateLoss();
            }
            m2006a(str, getResources().getString(C10531R.string.wp_comm_cancel), new C10696b(), getResources().getString(C10531R.string.wp_union_pay_find_pwd), new C10697c());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p405l0.InterfaceC12296b
    /* renamed from: c */
    public final void mo1852c() {
        try {
            C14232a c14232a = this.f20417A;
            if (c14232a != null) {
                c14232a.m69c();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p405l0.InterfaceC12296b
    /* renamed from: c */
    public final void mo1851c(String str) {
        try {
            C14232a c14232a = this.f20417A;
            if (c14232a != null) {
                c14232a.m64h0();
                this.f20417A.m66g0();
                this.f20417A.m60k(str);
                this.f20417A.m63i0();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: c */
    public final void m6063c(boolean z) {
        if (z) {
            ((C12308b) this.f24311a).m1834a(z, this.f20429v.f25040b);
            m1991k("勾选-设置页-优惠优先方式扣款");
            return;
        }
        ((C12308b) this.f24311a).m1834a(z, this.f20429v.f25040b);
        m1991k("勾选-设置页-自定义扣款方式");
    }

    @Override // p405l0.InterfaceC12296b
    /* renamed from: d */
    public final void mo1850d() {
        try {
            C14232a c14232a = this.f20417A;
            if (c14232a != null) {
                c14232a.m67d();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dealNotifyAction(EnumC12015g enumC12015g) {
        if (enumC12015g == EnumC12015g.H5_CLOSE_QPAY) {
            m6061m0();
        }
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: e0 */
    public final int mo1998e0() {
        return C10531R.C10535layout.wp_qpay_setting;
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: f0 */
    public final void mo1997f0() {
        Intent intent = getIntent();
        if (intent != null) {
            boolean booleanExtra = intent.getBooleanExtra("isFromWebSetting", false);
            this.f20418B = intent.getBooleanExtra("isFromPayResult", false);
            if (booleanExtra) {
                WPTrendsEventsUtils.initData();
            }
        }
        C12308b c12308b = (C12308b) this.f24311a;
        c12308b.getClass();
        C14255c.C14256a.f27777a.m30a(C14262f.f27811a, new HashMap<>(), new C12306a(c12308b, c12308b));
    }

    @Override // android.app.Activity
    public final void finish() {
        C14232a c14232a = this.f20417A;
        if (c14232a != null && c14232a.isVisible()) {
            this.f20417A.dismissAllowingStateLoss();
        }
        super.finish();
    }

    @Override // p405l0.InterfaceC12296b
    /* renamed from: g */
    public final void mo1849g(String str) {
        try {
            C13663u c13663u = C10546a.C10576i.f20125a.f20053c;
            WPQPayUserInfoBean m164a = c13663u.m164a(str);
            if (m164a != null) {
                m164a.setPayToken("");
                c13663u.m165a(m164a);
            }
            ConstraintLayout constraintLayout = this.f20419l;
            if (constraintLayout != null) {
                constraintLayout.postDelayed(new RunnableC10698d(), 1500L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: g0 */
    public final void mo1996g0() {
        m1993j(getResources().getString(C10531R.string.wp_speed_pay_setting));
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(C10531R.C10534id.wopay_default_cl);
        this.f20419l = constraintLayout;
        constraintLayout.setOnClickListener(this);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) findViewById(C10531R.C10534id.wopay_custom_cl);
        this.f20421n = constraintLayout2;
        constraintLayout2.setOnClickListener(this);
        ImageView imageView = (ImageView) findViewById(C10531R.C10534id.wopay_method_custom_cb);
        this.f20422o = imageView;
        imageView.setOnClickListener(this);
        ImageView imageView2 = (ImageView) findViewById(C10531R.C10534id.wopay_method_default_cb);
        this.f20420m = imageView2;
        imageView2.setOnClickListener(this);
        LinearLayout linearLayout = (LinearLayout) findViewById(C10531R.C10534id.wopay_limit_ll);
        this.f20424q = linearLayout;
        linearLayout.setOnClickListener(this);
        this.f20425r = (TextView) findViewById(C10531R.C10534id.wopay_method_default_amount_tv);
        TextView textView = (TextView) findViewById(C10531R.C10534id.wopay_limit_protocol_tv);
        this.f20426s = textView;
        textView.setOnClickListener(this);
        TextView textView2 = (TextView) findViewById(C10531R.C10534id.wopay_setting_close_tv);
        this.f20427t = textView2;
        textView2.setOnClickListener(this);
        this.f20428u = (LinearLayout) findViewById(C10531R.C10534id.wopay_qpay_close_ll);
        ((TextView) findViewById(C10531R.C10534id.wopay_qpay_close_tv)).setOnClickListener(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(C10531R.C10534id.wopay_method_custom_bank_list_rv);
        this.f20423p = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this, 1, false));
        C12380b c12380b = new C12380b(this);
        this.f20429v = c12380b;
        c12380b.f25043e = this;
        this.f20423p.setAdapter(c12380b);
        C12378a c12378a = new C12378a(this.f20429v);
        this.f20431x = c12378a;
        c12378a.f25037c = this;
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(this.f20431x);
        this.f20430w = itemTouchHelper;
        itemTouchHelper.attachToRecyclerView(this.f20423p);
        C12380b c12380b2 = this.f20429v;
        c12380b2.f25041c = this.f20430w;
        c12380b2.f25042d = this.f20431x;
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

    /* renamed from: l0 */
    public final void m6062l0() {
        C12380b c12380b = this.f20429v;
        if (c12380b == null || this.f20423p == null) {
            return;
        }
        int itemCount = c12380b.getItemCount();
        int i = 0;
        if (itemCount > 0) {
            C12380b c12380b2 = this.f20429v;
            C12380b.C12382b createViewHolder = c12380b2.createViewHolder(this.f20423p, c12380b2.getItemViewType(0));
            this.f20429v.onBindViewHolder(createViewHolder, 0);
            createViewHolder.itemView.measure(View.MeasureSpec.makeMeasureSpec(this.f20423p.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
            View view = createViewHolder.itemView;
            view.layout(0, 0, view.getMeasuredWidth(), createViewHolder.itemView.getMeasuredHeight());
            createViewHolder.itemView.setDrawingCacheEnabled(true);
            createViewHolder.itemView.buildDrawingCache();
            i = createViewHolder.itemView.getMeasuredHeight();
        }
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.f20423p.getLayoutParams();
        layoutParams.height = itemCount < 4 ? i * itemCount : i * 4;
        this.f20423p.setLayoutParams(layoutParams);
    }

    /* renamed from: m0 */
    public final void m6061m0() {
        try {
            if (this.f20417A == null) {
                this.f20417A = C14232a.m65h("QPAY_SET_CLOSE");
            }
            if (!this.f20417A.isAdded() && !this.f20417A.isVisible()) {
                this.f20417A.show(getSupportFragmentManager(), "CloseQPayPayPassDialog");
                this.f20417A.f27728n = new C10695a();
                return;
            }
            this.f20417A.dismissAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public final void onActivityResult(int i, int i2, @Nullable Intent intent) {
        WPLimitBean wPLimitBean;
        String str;
        super.onActivityResult(i, i2, intent);
        if (i != 120 || i2 != -1 || intent == null || this.f20433z == null || this.f20425r == null) {
            return;
        }
        String stringExtra = intent.getStringExtra("limitAmount");
        this.f20425r.setText(getString(C10531R.string.wp_limit_amount, stringExtra));
        List<WPLimitBean> payLimitList = this.f20433z.getPayLimitList();
        int size = payLimitList == null ? 0 : this.f20433z.getPayLimitList().size();
        for (int i3 = 0; i3 < size; i3++) {
            if (stringExtra.equals(payLimitList.get(i3).getPayLimit())) {
                wPLimitBean = payLimitList.get(i3);
                str = "1";
            } else {
                wPLimitBean = payLimitList.get(i3);
                str = "0";
            }
            wPLimitBean.setSettingFlag(str);
        }
        this.f20433z.setPayLimitList(payLimitList);
        this.f20433z.setPayLimit(stringExtra);
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.view.View.OnClickListener
    public void onClick(View view) {
        boolean z;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        if (m2001b0()) {
            NBSActionInstrumentation.onClickEventExit();
        } else if (view.getId() == C10531R.C10534id.wapay_header_arrow) {
            m1991k("点击-设置页-返回箭头");
            finish();
            NBSActionInstrumentation.onClickEventExit();
        } else {
            if (view.getId() == C10531R.C10534id.wopay_limit_ll) {
                m1991k("点击-设置页-扣款限额");
                WPSettingInfoBean wPSettingInfoBean = this.f20433z;
                if (wPSettingInfoBean != null) {
                    ArrayList<? extends Parcelable> arrayList = new ArrayList<>(wPSettingInfoBean.getPayLimitList());
                    String payLimit = this.f20433z.getPayLimit();
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("dataList", arrayList);
                    bundle.putString("payLimit", payLimit);
                    m2007a(WPSettingLimitActivity.class, 120, bundle);
                }
            } else if (view.getId() == C10531R.C10534id.wopay_setting_close_tv) {
                m1991k("点击-设置页-返回箭头");
                finish();
            } else {
                if (view.getId() == C10531R.C10534id.wopay_default_cl || view.getId() == C10531R.C10534id.wopay_method_default_cb) {
                    z = true;
                } else if (view.getId() == C10531R.C10534id.wopay_custom_cl || view.getId() == C10531R.C10534id.wopay_method_custom_cb) {
                    z = false;
                } else if (view.getId() == C10531R.C10534id.wopay_limit_protocol_tv) {
                    if (this.f20433z != null) {
                        UnicomPaySDK.getInstance().getNativeFunctionCallback().openWebview(this, this.f20433z.getAgreementUrl());
                    }
                    m1991k("点击-设置页-《极速支付服务用户协议》");
                    WPTrendsEventsUtils.trendsPageData("极速支付协议页面", "98U01170qp001", "qp001");
                } else if (view.getId() == C10531R.C10534id.wopay_qpay_close_tv) {
                    m1991k("点击-设置页-关闭按钮");
                    WPTrendsEventsUtils.trendsPageData("关闭确认弹窗", "98U01170qp016", "qp016");
                    m2004a(getResources().getString(C10531R.string.wp_setting_close_qpay_desc1), getResources().getString(C10531R.string.wp_setting_close_qpay_desc2), getResources().getString(C10531R.string.wp_setting_close_qpay_cancel), new C12361a(this), getResources().getString(C10531R.string.wp_setting_close_qpay_confirm), new C12362b());
                }
                m6063c(z);
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 407);
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        C14232a c14232a = this.f20417A;
        if (c14232a != null && c14232a.isVisible()) {
            this.f20417A.dismissAllowingStateLoss();
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
        return "98U01170qp011";
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [java.util.List<com.unicom.pay.qpay.setting.bean.WPBankBean>, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.util.List<com.unicom.pay.qpay.setting.bean.WPBankBean>, java.util.ArrayList] */
    @Override // p405l0.InterfaceC12296b
    /* renamed from: a */
    public final void mo1855a(boolean z) {
        C12378a c12378a;
        try {
            mo1790i(getResources().getString(C10531R.string.wp_setting_update_success));
            if (this.f20420m != null && this.f20422o != null) {
                if (this.f20432y == null) {
                    this.f20432y = new ArrayList();
                }
                this.f20432y.clear();
                this.f20432y.addAll(this.f20429v.f25040b);
                this.f20433z.setUserPaymentList(this.f20432y);
                boolean z2 = true;
                if (z) {
                    if (this.f20420m.isSelected()) {
                        return;
                    }
                    this.f20420m.setSelected(true);
                    this.f20422o.setSelected(false);
                    c12378a = this.f20431x;
                    z2 = false;
                } else if (this.f20422o.isSelected()) {
                    return;
                } else {
                    this.f20420m.setSelected(false);
                    this.f20422o.setSelected(true);
                    c12378a = this.f20431x;
                }
                c12378a.f25035a = z2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p405l0.InterfaceC12296b
    /* renamed from: a */
    public final void mo1856a(String str) {
        m2005a(str, getResources().getString(C10531R.string.wp_comm_confirm), new C12048b.InterfaceC12052d() { // from class: com.unicom.pay.qpay.setting.ui.-$$Lambda$nG93_YLq4KxxR6EEW_6W7_wO_04
            @Override // p395i.C12048b.InterfaceC12052d
            public final void onClick(View view) {
                WPQPaySettingActivity.m6064a(view);
            }
        });
    }
}
