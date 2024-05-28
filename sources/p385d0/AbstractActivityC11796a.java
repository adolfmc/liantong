package p385d0;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.gmrz.appsdk.FidoAppSDK;
import com.gmrz.appsdk.FidoIn;
import com.gmrz.appsdk.FidoReInfo;
import com.gmrz.appsdk.commlib.api.FidoCallback;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.C10546a;
import com.unicom.pay.UnicomPaySDK;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.common.callback.DataCallback;
import com.unicom.pay.modules.result.p356ui.WPPayResultActivity;
import com.unicom.pay.modules.verify.bean.WPGmKeyBean;
import com.unicom.pay.modules.verify.bean.WPUnionPayResultBean;
import com.unicom.pay.normal.order.bean.WPPayBeforeBean;
import com.unicom.pay.qpay.open.bean.WPQPayUserInfoBean;
import com.unicom.pay.utils.buried.WPTrendsEventsUtils;
import java.util.HashMap;
import p089b0.InterfaceC1472f;
import p091c0.AbstractC1556o;
import p091c0.C1554n;
import p393h.AbstractDialogInterface$OnCancelListenerC12000b;
import p395i.C12048b;
import p470p0.C13636a;
import p470p0.C13654q;
import p481v.C14232a;
import p481v.C14239e;
import p482w.C14255c;
import p482w.C14262f;

/* renamed from: d0.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class AbstractActivityC11796a<P extends AbstractC1556o> extends AbstractDialogInterface$OnCancelListenerC12000b<P> implements InterfaceC1472f {

    /* renamed from: l */
    public int f24025l = 0;

    /* renamed from: m */
    public WPQPayUserInfoBean f24026m;

    /* renamed from: n */
    public C14232a f24027n;

    /* renamed from: o */
    public C14239e f24028o;

    /* renamed from: p */
    public boolean f24029p;

    /* renamed from: q */
    public String f24030q;

    /* renamed from: r */
    public boolean f24031r;

    @NBSInstrumented
    /* renamed from: d0.a$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11797a implements C12048b.InterfaceC12052d {

        /* renamed from: a */
        public final /* synthetic */ WPPayBeforeBean f24032a;

        public C11797a(WPPayBeforeBean wPPayBeforeBean) {
            this.f24032a = wPPayBeforeBean;
        }

        @Override // p395i.C12048b.InterfaceC12052d
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            AbstractActivityC11796a abstractActivityC11796a = AbstractActivityC11796a.this;
            ((AbstractC1556o) abstractActivityC11796a.f24311a).m22167a("00", abstractActivityC11796a.f24026m, this.f24032a, true);
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: d0.a$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11798b implements C12048b.InterfaceC12052d {

        /* renamed from: a */
        public final /* synthetic */ WPPayBeforeBean f24034a;

        public C11798b(WPPayBeforeBean wPPayBeforeBean) {
            this.f24034a = wPPayBeforeBean;
        }

        @Override // p395i.C12048b.InterfaceC12052d
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            AbstractActivityC11796a abstractActivityC11796a = AbstractActivityC11796a.this;
            ((AbstractC1556o) abstractActivityC11796a.f24311a).m22167a("02", abstractActivityC11796a.f24026m, this.f24034a, true);
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: d0.a$c */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C11799c implements C12048b.InterfaceC12050b {

        /* renamed from: a */
        public final /* synthetic */ WPPayBeforeBean f24036a;

        public C11799c(WPPayBeforeBean wPPayBeforeBean) {
            this.f24036a = wPPayBeforeBean;
        }

        @Override // p395i.C12048b.InterfaceC12050b
        /* renamed from: a */
        public final void mo1801a() {
            AbstractActivityC11796a.this.mo2091c(this.f24036a);
        }
    }

    /* renamed from: d0.a$d */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11800d implements DataCallback {
        public C11800d() {
        }

        @Override // com.unicom.pay.common.callback.DataCallback
        public final void onResult(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            UnicomPaySDK.getInstance().getNativeFunctionCallback().openWebview(AbstractActivityC11796a.this, str);
        }
    }

    /* renamed from: d0.a$e */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11801e implements DataCallback {
        public C11801e() {
        }

        @Override // com.unicom.pay.common.callback.DataCallback
        public final void onResult(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            UnicomPaySDK.getInstance().getNativeFunctionCallback().openWebview(AbstractActivityC11796a.this, str);
        }
    }

    /* renamed from: d0.a$f */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11802f implements C12048b.InterfaceC12050b {
        @Override // p395i.C12048b.InterfaceC12050b
        /* renamed from: a */
        public final void mo1801a() {
            WPTrendsEventsUtils.trendsPageButtonData("密码锁定弹窗", "98U01170wp160", "wp160", "点击-密码锁定弹窗-取消找回密码");
        }
    }

    @NBSInstrumented
    /* renamed from: d0.a$g */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11803g implements C12048b.InterfaceC12052d {
        public C11803g() {
        }

        @Override // p395i.C12048b.InterfaceC12052d
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            AbstractActivityC11796a.this.m2083m0();
            WPTrendsEventsUtils.trendsPageButtonData("密码锁定弹窗", "98U01170wp160", "wp160", "点击-密码锁定弹窗-找回密码");
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* renamed from: d0.a$h */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11804h implements C14232a.InterfaceC14235c {

        /* renamed from: a */
        public final /* synthetic */ String f24041a;

        /* renamed from: b */
        public final /* synthetic */ WPPayBeforeBean f24042b;

        public C11804h(String str, WPPayBeforeBean wPPayBeforeBean) {
            this.f24041a = str;
            this.f24042b = wPPayBeforeBean;
        }

        @Override // p481v.C14232a.InterfaceC14235c
        /* renamed from: a */
        public final void mo59a() {
            AbstractActivityC11796a abstractActivityC11796a = AbstractActivityC11796a.this;
            AbstractC1556o abstractC1556o = (AbstractC1556o) abstractActivityC11796a.f24311a;
            String str = this.f24041a;
            String mo6127V = abstractActivityC11796a.mo6127V();
            WPPayBeforeBean wPPayBeforeBean = this.f24042b;
            String mo6077x = AbstractActivityC11796a.this.mo6077x();
            ((InterfaceC1472f) abstractC1556o.m1798a()).mo2092c();
            HashMap hashMap = new HashMap();
            hashMap.put("expand", str);
            hashMap.put("tradeOrderNo", mo6127V);
            abstractC1556o.m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27789B, hashMap, false, new C1554n(abstractC1556o, abstractC1556o, str, wPPayBeforeBean, mo6127V, mo6077x)));
        }

        @Override // p481v.C14232a.InterfaceC14235c
        /* renamed from: b */
        public final void mo58b() {
            AbstractActivityC11796a.this.m2083m0();
        }
    }

    /* renamed from: d0.a$i */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class RunnableC11805i implements Runnable {

        /* renamed from: d0.a$i$a */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class C11806a implements C14239e.InterfaceC14241b {
            public C11806a() {
            }

            /* renamed from: a */
            public final void m2081a(String str, String str2, String str3, WPResult<WPUnionPayResultBean> wPResult) {
                if ("1".equals(str)) {
                    AbstractActivityC11796a.this.mo2089c(str2, str3);
                } else if ("2".equals(str)) {
                    AbstractActivityC11796a.this.mo2087e(wPResult);
                }
            }
        }

        public RunnableC11805i() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            AbstractActivityC11796a abstractActivityC11796a = AbstractActivityC11796a.this;
            abstractActivityC11796a.f24028o.show(abstractActivityC11796a.getSupportFragmentManager(), "VerifyCodeDialog");
            AbstractActivityC11796a.this.f24028o.f27758z = new C11806a();
        }
    }

    /* renamed from: d0.a$j */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11807j implements FidoCallback<FidoReInfo> {

        /* renamed from: a */
        public final /* synthetic */ WPPayBeforeBean f24046a;

        /* renamed from: b */
        public final /* synthetic */ WPQPayUserInfoBean f24047b;

        /* renamed from: c */
        public final /* synthetic */ String f24048c;

        public C11807j(WPPayBeforeBean wPPayBeforeBean, WPQPayUserInfoBean wPQPayUserInfoBean, String str) {
            this.f24046a = wPPayBeforeBean;
            this.f24047b = wPQPayUserInfoBean;
            this.f24048c = str;
        }

        @Override // com.gmrz.appsdk.commlib.api.FidoCallback
        public final void onFidoProcess(FidoReInfo fidoReInfo) {
            AbstractActivityC11796a.this.runOnUiThread(new RunnableC11815c(this, fidoReInfo));
        }
    }

    @NBSInstrumented
    /* renamed from: d0.a$k */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11808k implements C12048b.InterfaceC12052d {

        /* renamed from: a */
        public final /* synthetic */ WPQPayUserInfoBean f24050a;

        /* renamed from: b */
        public final /* synthetic */ WPPayBeforeBean f24051b;

        public C11808k(WPQPayUserInfoBean wPQPayUserInfoBean, WPPayBeforeBean wPPayBeforeBean) {
            this.f24050a = wPQPayUserInfoBean;
            this.f24051b = wPPayBeforeBean;
        }

        @Override // p395i.C12048b.InterfaceC12052d
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            ((AbstractC1556o) AbstractActivityC11796a.this.f24311a).m22167a("02", this.f24050a, this.f24051b, true);
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: d0.a$l */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11809l implements C12048b.InterfaceC12052d {

        /* renamed from: a */
        public final /* synthetic */ WPQPayUserInfoBean f24053a;

        /* renamed from: b */
        public final /* synthetic */ WPPayBeforeBean f24054b;

        public C11809l(WPQPayUserInfoBean wPQPayUserInfoBean, WPPayBeforeBean wPPayBeforeBean) {
            this.f24053a = wPQPayUserInfoBean;
            this.f24054b = wPPayBeforeBean;
        }

        @Override // p395i.C12048b.InterfaceC12052d
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            ((AbstractC1556o) AbstractActivityC11796a.this.f24311a).m22167a("00", this.f24053a, this.f24054b, true);
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: d0.a$m */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11810m implements C12048b.InterfaceC12052d {

        /* renamed from: a */
        public final /* synthetic */ WPPayBeforeBean f24056a;

        public C11810m(WPPayBeforeBean wPPayBeforeBean) {
            this.f24056a = wPPayBeforeBean;
        }

        @Override // p395i.C12048b.InterfaceC12052d
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            AbstractActivityC11796a abstractActivityC11796a = AbstractActivityC11796a.this;
            ((AbstractC1556o) abstractActivityC11796a.f24311a).m22167a("00", abstractActivityC11796a.f24026m, this.f24056a, true);
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: d0.a$n */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11811n implements C12048b.InterfaceC12052d {

        /* renamed from: a */
        public final /* synthetic */ WPPayBeforeBean f24058a;

        public C11811n(WPPayBeforeBean wPPayBeforeBean) {
            this.f24058a = wPPayBeforeBean;
        }

        @Override // p395i.C12048b.InterfaceC12052d
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            AbstractActivityC11796a abstractActivityC11796a = AbstractActivityC11796a.this;
            ((AbstractC1556o) abstractActivityC11796a.f24311a).m22167a("02", abstractActivityC11796a.f24026m, this.f24058a, true);
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: d0.a$o */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11812o implements C12048b.InterfaceC12052d {

        /* renamed from: a */
        public final /* synthetic */ WPPayBeforeBean f24060a;

        public C11812o(WPPayBeforeBean wPPayBeforeBean) {
            this.f24060a = wPPayBeforeBean;
        }

        @Override // p395i.C12048b.InterfaceC12052d
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            AbstractActivityC11796a abstractActivityC11796a = AbstractActivityC11796a.this;
            ((AbstractC1556o) abstractActivityC11796a.f24311a).m22167a("02", abstractActivityC11796a.f24026m, this.f24060a, true);
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: d0.a$p */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11813p implements C12048b.InterfaceC12052d {

        /* renamed from: a */
        public final /* synthetic */ WPPayBeforeBean f24062a;

        public C11813p(WPPayBeforeBean wPPayBeforeBean) {
            this.f24062a = wPPayBeforeBean;
        }

        @Override // p395i.C12048b.InterfaceC12052d
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            AbstractActivityC11796a abstractActivityC11796a = AbstractActivityC11796a.this;
            ((AbstractC1556o) abstractActivityC11796a.f24311a).m22167a("00", abstractActivityC11796a.f24026m, this.f24062a, true);
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* renamed from: a */
    public static /* synthetic */ void m2104a(View view) {
    }

    @Override // p089b0.InterfaceC1472f
    /* renamed from: E */
    public final void mo2108E() {
    }

    @Override // p089b0.InterfaceC1472f
    /* renamed from: T */
    public final void mo2107T() {
        m2082n0();
    }

    @Override // p089b0.InterfaceC1472f
    /* renamed from: a */
    public final String mo2103a(WPGmKeyBean wPGmKeyBean) {
        C14232a c14232a = this.f24027n;
        return (c14232a == null || wPGmKeyBean == null) ? "" : c14232a.m68c(wPGmKeyBean);
    }

    @Override // p089b0.InterfaceC1472f
    /* renamed from: a */
    public final void mo2106a() {
        try {
            C14232a c14232a = this.f24027n;
            if (c14232a != null) {
                c14232a.m64h0();
                this.f24027n.m66g0();
                this.f24027n.dismissAllowingStateLoss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p089b0.InterfaceC1472f
    /* renamed from: a */
    public final void mo2102a(WPUnionPayResultBean wPUnionPayResultBean, String str, WPPayBeforeBean wPPayBeforeBean) {
        m2096a("PAY_PAY", mo6127V(), str, "", wPUnionPayResultBean, wPPayBeforeBean);
    }

    @Override // p089b0.InterfaceC1472f
    /* renamed from: a */
    public final void mo2100a(WPQPayUserInfoBean wPQPayUserInfoBean, WPPayBeforeBean wPPayBeforeBean, String str) {
        AbstractC1556o abstractC1556o;
        String str2;
        try {
            if ("1".equals(wPQPayUserInfoBean.getIsSupportTwo())) {
                if ("00".equals(wPQPayUserInfoBean.getCurrentFido())) {
                    if ("00".equals(str)) {
                        abstractC1556o = (AbstractC1556o) this.f24311a;
                        str2 = "02";
                        abstractC1556o.m22167a(str2, wPQPayUserInfoBean, wPPayBeforeBean, true);
                        return;
                    }
                } else if ("02".equals(str)) {
                    abstractC1556o = (AbstractC1556o) this.f24311a;
                    str2 = "00";
                    abstractC1556o.m22167a(str2, wPQPayUserInfoBean, wPPayBeforeBean, true);
                    return;
                }
            }
            mo2091c(wPPayBeforeBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public final void m2099a(WPQPayUserInfoBean wPQPayUserInfoBean, WPPayBeforeBean wPPayBeforeBean, String str, boolean z) {
        int i;
        Object[] objArr;
        String string;
        int i2;
        Object[] objArr2;
        int i3;
        C12048b.InterfaceC12052d c11809l;
        try {
            if (!"1".equals(wPQPayUserInfoBean.getIsSupportTwo())) {
                if (z) {
                    if ("00".equals(str)) {
                        i = C10531R.string.up_fido_default_error;
                        objArr = new Object[]{"指纹"};
                    } else {
                        i = C10531R.string.up_fido_default_error;
                        objArr = new Object[]{"面容"};
                    }
                    string = getString(i, objArr);
                    mo1790i(string);
                }
                mo2091c(wPPayBeforeBean);
            }
            if ("00".equals(wPQPayUserInfoBean.getCurrentFido())) {
                if ("00".equals(str)) {
                    i3 = C10531R.string.up_fido_fail;
                    c11809l = new C11808k(wPQPayUserInfoBean, wPPayBeforeBean);
                    m2105a(i3, wPPayBeforeBean, c11809l);
                    return;
                }
                if (z) {
                    i2 = C10531R.string.up_fido_default_error;
                    objArr2 = new Object[]{"面容"};
                    string = getString(i2, objArr2);
                }
                mo2091c(wPPayBeforeBean);
            } else if ("02".equals(str)) {
                i3 = C10531R.string.up_face_fail;
                c11809l = new C11809l(wPQPayUserInfoBean, wPPayBeforeBean);
                m2105a(i3, wPPayBeforeBean, c11809l);
                return;
            } else {
                if (z) {
                    i2 = C10531R.string.up_fido_default_error;
                    objArr2 = new Object[]{"指纹"};
                    string = getString(i2, objArr2);
                }
                mo2091c(wPPayBeforeBean);
            }
            mo1790i(string);
            mo2091c(wPPayBeforeBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p089b0.InterfaceC1472f
    /* renamed from: a */
    public final void mo2097a(String str, String str2, WPQPayUserInfoBean wPQPayUserInfoBean, WPPayBeforeBean wPPayBeforeBean) {
        C11807j c11807j = new C11807j(wPPayBeforeBean, wPQPayUserInfoBean, str2);
        FidoAppSDK.getInstance().processAsync(this, FidoIn.Builder().setFidoIn(str), c11807j);
    }

    /* renamed from: a */
    public final void m2096a(String str, String str2, String str3, String str4, WPUnionPayResultBean wPUnionPayResultBean, WPPayBeforeBean wPPayBeforeBean) {
        try {
            C14239e m51a = C14239e.m51a(C14239e.m48a(str, str2, str3, str4, wPUnionPayResultBean, mo6077x(), wPPayBeforeBean));
            this.f24028o = m51a;
            if (!m51a.isAdded() && !this.f24028o.isVisible()) {
                new Handler().postDelayed(new RunnableC11805i(), 1500L);
                return;
            }
            this.f24028o.dismissAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p089b0.InterfaceC1472f
    /* renamed from: b */
    public final void mo2095b() {
        try {
            C14232a c14232a = this.f24027n;
            if (c14232a == null || !c14232a.isAdded()) {
                return;
            }
            this.f24027n.dismissAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p089b0.InterfaceC1472f
    /* renamed from: b */
    public final void mo2094b(String str) {
        C14232a c14232a = this.f24027n;
        if (c14232a != null) {
            c14232a.m64h0();
            this.f24027n.m66g0();
            this.f24027n.dismissAllowingStateLoss();
        }
        WPTrendsEventsUtils.trendsPageData("密码锁定弹窗", "98U01170wp160", "wp160");
        m2006a(str, getResources().getString(C10531R.string.wp_comm_cancel), new C11802f(), getResources().getString(C10531R.string.wp_union_pay_find_pwd), new C11803g());
    }

    /* renamed from: b */
    public void mo2093b(boolean z) {
        TextView textView = this.f24313c;
        if (textView == null || z) {
            m2082n0();
        } else {
            textView.postDelayed(new RunnableC11814b(this), 1300L);
        }
    }

    @Override // p089b0.InterfaceC1472f
    /* renamed from: c */
    public final void mo2092c() {
        try {
            C14232a c14232a = this.f24027n;
            if (c14232a != null) {
                c14232a.m69c();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p089b0.InterfaceC1472f
    /* renamed from: c */
    public final void mo2091c(WPPayBeforeBean wPPayBeforeBean) {
        try {
            if (this.f24027n == null) {
                mo2084l0();
                this.f24027n = C14232a.m65h("PAY_PAY");
            }
            if (!this.f24027n.isAdded() && !this.f24027n.isVisible()) {
                this.f24027n.show(getSupportFragmentManager(), "PayPassDialog");
                String mo6076y = mo6076y();
                this.f24027n.f27728n = new C11804h(mo6076y, wPPayBeforeBean);
                return;
            }
            this.f24027n.dismissAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p089b0.InterfaceC1472f
    /* renamed from: c */
    public final void mo2090c(String str) {
        try {
            C14232a c14232a = this.f24027n;
            if (c14232a != null) {
                c14232a.m64h0();
                this.f24027n.m66g0();
                this.f24027n.m60k(str);
                this.f24027n.m63i0();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: c */
    public void mo2089c(String str, String str2) {
    }

    @Override // p089b0.InterfaceC1472f
    /* renamed from: d */
    public final void mo2088d() {
        try {
            C14232a c14232a = this.f24027n;
            if (c14232a != null) {
                c14232a.m67d();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: e */
    public void mo2087e(WPResult<WPUnionPayResultBean> wPResult) {
    }

    /* renamed from: f */
    public final void m2086f(WPResult<WPUnionPayResultBean> wPResult) {
        if (wPResult == null || TextUtils.isEmpty(mo6127V())) {
            return;
        }
        String mo6127V = mo6127V();
        boolean z = this.f24031r;
        Bundle bundle = new Bundle();
        bundle.putString("unionOrderId", mo6127V);
        bundle.putParcelable("unionPayResult", wPResult);
        bundle.putBoolean("signQPay", z);
        m2008a(bundle, WPPayResultActivity.class);
        C13636a.m190a();
    }

    @Override // p393h.AbstractDialogInterface$OnCancelListenerC12000b
    /* renamed from: f0 */
    public void mo1997f0() {
        this.f24026m = C10546a.C10576i.f20125a.f20053c.m164a(this.f24030q);
    }

    /* renamed from: l */
    public final void m2085l(String str) {
        P p = this.f24311a;
        if (p == 0) {
            return;
        }
        C13654q.m171a(p, str, mo6127V(), new C11801e());
        WPTrendsEventsUtils.addWindow(this.f24029p ? "pwdwb" : "bccwb");
    }

    /* renamed from: l0 */
    public abstract void mo2084l0();

    /* renamed from: m0 */
    public final void m2083m0() {
        this.f24029p = true;
        P p = this.f24311a;
        if (p == 0) {
            return;
        }
        C13654q.m172a(p, mo6127V(), new C11800d());
        WPTrendsEventsUtils.addWindow("pwdwb");
    }

    /* renamed from: n0 */
    public final void m2082n0() {
        String mo6127V = mo6127V();
        if (TextUtils.isEmpty(mo6127V)) {
            return;
        }
        boolean z = this.f24031r;
        Bundle bundle = new Bundle();
        bundle.putString("unionOrderId", mo6127V);
        bundle.putBoolean("signQPay", z);
        m2008a(bundle, WPPayResultActivity.class);
        C13636a.m190a();
    }

    /* renamed from: a */
    public final void m2105a(int i, WPPayBeforeBean wPPayBeforeBean, C12048b.InterfaceC12052d interfaceC12052d) {
        m2006a(getResources().getString(i), getResources().getString(C10531R.string.up_pwd_pay), new C11799c(wPPayBeforeBean), getResources().getString(C10531R.string.up_fail_confirm), interfaceC12052d);
    }

    @Override // p089b0.InterfaceC1472f
    /* renamed from: a */
    public final void mo2101a(WPPayBeforeBean wPPayBeforeBean, String str) {
        int i;
        C12048b.InterfaceC12052d c11813p;
        try {
            WPQPayUserInfoBean wPQPayUserInfoBean = this.f24026m;
            if (wPQPayUserInfoBean == null) {
                return;
            }
            this.f24025l++;
            if ("1".equals(wPQPayUserInfoBean.getIsSupportTwo())) {
                if ("00".equals(this.f24026m.getCurrentFido())) {
                    int i2 = this.f24025l;
                    if (i2 < 2) {
                        m2005a(getResources().getString(C10531R.string.up_fido_fp_fail), getResources().getString(C10531R.string.up_fido_restart), new C11810m(wPPayBeforeBean));
                        return;
                    } else if (i2 == 2) {
                        i = C10531R.string.up_fido_fail;
                        c11813p = new C11811n(wPPayBeforeBean);
                        m2105a(i, wPPayBeforeBean, c11813p);
                        return;
                    } else if (i2 != 3) {
                        return;
                    }
                } else {
                    int i3 = this.f24025l;
                    if (i3 < 2) {
                        m2005a(getResources().getString(C10531R.string.up_fido_face_fail), getResources().getString(C10531R.string.up_fido_restart), new C11812o(wPPayBeforeBean));
                        return;
                    } else if (i3 == 2) {
                        i = C10531R.string.up_face_fail;
                        c11813p = new C11813p(wPPayBeforeBean);
                        m2105a(i, wPPayBeforeBean, c11813p);
                        return;
                    } else if (i3 != 3) {
                        return;
                    }
                }
            } else if ("00".equals(this.f24026m.getCurrentFido())) {
                int i4 = this.f24025l;
                if (i4 < 3) {
                    m2005a(getResources().getString(C10531R.string.up_fido_fp_fail), getResources().getString(C10531R.string.up_fido_restart), new C11797a(wPPayBeforeBean));
                    return;
                } else if (i4 != 3) {
                    return;
                }
            } else {
                int i5 = this.f24025l;
                if (i5 < 3) {
                    m2005a(getResources().getString(C10531R.string.up_fido_face_fail), getResources().getString(C10531R.string.up_fido_restart), new C11798b(wPPayBeforeBean));
                    return;
                } else if (i5 != 3) {
                    return;
                }
            }
            mo1790i(str);
            mo2091c(wPPayBeforeBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p089b0.InterfaceC1472f
    /* renamed from: a */
    public final void mo2098a(String str) {
        m2005a(str, getResources().getString(C10531R.string.wp_comm_confirm), new C12048b.InterfaceC12052d() { // from class: d0.-$$Lambda$QqobZUj_hih6nUnza9PF4Xv7FcA
            @Override // p395i.C12048b.InterfaceC12052d
            public final void onClick(View view) {
                AbstractActivityC11796a.m2104a(view);
            }
        });
    }
}
