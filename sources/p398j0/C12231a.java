package p398j0;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.C10546a;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.result.bean.WPCheckSignResult;
import com.unicom.pay.modules.verify.bean.WPQPayResultBean;
import java.lang.reflect.Type;
import java.util.HashMap;
import p396i0.AbstractC12054a;
import p411o.AbstractC12375a;
import p470p0.C13648k;
import p473r.C13707b;
import p482w.AbstractC14257d;
import p482w.C14255c;
import p482w.C14262f;

/* renamed from: j0.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12231a extends AbstractC12054a {

    /* renamed from: j0.a$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C12232a extends AbstractC14257d<WPQPayResultBean> {

        /* renamed from: c */
        public final /* synthetic */ Context f24827c;

        /* renamed from: d */
        public final /* synthetic */ String f24828d;

        /* renamed from: e */
        public final /* synthetic */ String f24829e;

        /* renamed from: f */
        public final /* synthetic */ boolean f24830f;

        /* renamed from: g */
        public final /* synthetic */ String f24831g;

        /* renamed from: h */
        public final /* synthetic */ String f24832h;

        /* renamed from: i */
        public final /* synthetic */ String f24833i;

        /* renamed from: j */
        public final /* synthetic */ String f24834j;

        @NBSInstrumented
        /* renamed from: j0.a$a$a */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class RunnableC12233a implements Runnable {

            /* renamed from: a */
            public final /* synthetic */ String f24836a;

            public RunnableC12233a(String str) {
                this.f24836a = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                String str = this.f24836a;
                C12232a.this.getClass();
                Type type = new C12239f().getType();
                Gson gson = C13648k.f27492a;
                WPResult wPResult = (WPResult) (!(gson instanceof Gson) ? gson.fromJson(str, type) : NBSGsonInstrumentation.fromJson(gson, str, type));
                if ("0000".equals(wPResult.getCode())) {
                    if (wPResult.getCommonResp() != null && !TextUtils.isEmpty(wPResult.getCommonResp().getUserTokenId())) {
                        C10546a.C10576i.f20125a.m6169a(wPResult.getCommonResp().getUserTokenId());
                    }
                    C12232a c12232a = C12232a.this;
                    C12231a.m1921a(C12231a.this, c12232a.f24827c, new RunnableC12235c(wPResult));
                    return;
                }
                C12232a c12232a2 = C12232a.this;
                C12231a.m1921a(C12231a.this, c12232a2.f24827c, new RunnableC12237e(wPResult));
            }
        }

        /* renamed from: j0.a$a$b */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class RunnableC12234b implements Runnable {
            public RunnableC12234b(String str) {
            }

            @Override // java.lang.Runnable
            public final void run() {
                C12232a c12232a = C12232a.this;
                C12231a.m1921a(C12231a.this, c12232a.f24827c, new RunnableC12236d());
            }
        }

        /* renamed from: j0.a$a$c */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class RunnableC12235c implements Runnable {

            /* renamed from: a */
            public final /* synthetic */ WPResult f24839a;

            public RunnableC12235c(WPResult wPResult) {
                this.f24839a = wPResult;
            }

            @Override // java.lang.Runnable
            public final void run() {
                if (C12231a.this.m1795b()) {
                    WPQPayResultBean wPQPayResultBean = (WPQPayResultBean) this.f24839a.getData();
                    if (wPQPayResultBean == null) {
                        C12231a.this.m1798a().mo1896j();
                    } else if ("001".equals(wPQPayResultBean.getResultCode())) {
                        String phoneNo = wPQPayResultBean.getPhoneNo();
                        C12232a c12232a = C12232a.this;
                        C12231a.this.m1798a().mo1902a(phoneNo, c12232a.f24828d, c12232a.f24829e);
                    } else if ("002".equals(wPQPayResultBean.getResultCode())) {
                        C12231a.this.m1798a().mo1900e();
                    } else if ("003".equals(wPQPayResultBean.getResultCode())) {
                        C12231a.this.m1798a().mo1790i(wPQPayResultBean.getResultMsg());
                        C12231a.this.m1798a().mo1898h();
                    } else {
                        C12231a.this.m1798a().mo1790i(wPQPayResultBean.getResultMsg());
                        C12231a.this.m1798a().mo1896j();
                    }
                }
            }
        }

        /* renamed from: j0.a$a$d */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class RunnableC12236d implements Runnable {
            public RunnableC12236d() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                if (C12231a.this.m1795b()) {
                    C12231a.this.m1798a().mo1791a(C10531R.string.wp_mer_qpay_pay_fail);
                    C12231a.this.m1798a().mo1896j();
                }
            }
        }

        /* renamed from: j0.a$a$e */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class RunnableC12237e implements Runnable {

            /* renamed from: a */
            public final /* synthetic */ WPResult f24842a;

            /* renamed from: j0.a$a$e$a */
            /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
            public class C12238a implements InterfaceC12248c {
                public C12238a() {
                }

                @Override // p398j0.C12231a.InterfaceC12248c
                /* renamed from: a */
                public final void mo1919a() {
                    if (C12231a.this.m1795b()) {
                        C12231a.this.m1798a().mo1790i(RunnableC12237e.this.f24842a.getMsg());
                        if ("4066051003".equals(RunnableC12237e.this.f24842a.getCode())) {
                            C12231a.this.m1798a().mo1897i();
                        } else {
                            C12231a.this.m1798a().mo1896j();
                        }
                    }
                }

                @Override // p398j0.C12231a.InterfaceC12248c
                /* renamed from: b */
                public final void mo1918b() {
                    C12232a c12232a = C12232a.this;
                    C12231a.this.m1923a(c12232a.f24827c, c12232a.f24831g, c12232a.f24832h, c12232a.f24828d, c12232a.f24829e, c12232a.f24833i, c12232a.f24834j, true);
                }
            }

            public RunnableC12237e(WPResult wPResult) {
                this.f24842a = wPResult;
            }

            @Override // java.lang.Runnable
            public final void run() {
                C12232a c12232a = C12232a.this;
                C12231a.m1920a(C12231a.this, this.f24842a, c12232a.f24830f, new C12238a());
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: j0.a$a$f */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        class C12239f extends TypeToken<WPResult<WPQPayResultBean>> {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C12232a(AbstractC12375a abstractC12375a, Context context, String str, String str2, boolean z, String str3, String str4, String str5, String str6) {
            super(abstractC12375a, 1);
            this.f24827c = context;
            this.f24828d = str;
            this.f24829e = str2;
            this.f24830f = z;
            this.f24831g = str3;
            this.f24832h = str4;
            this.f24833i = str5;
            this.f24834j = str6;
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult<WPQPayResultBean> wPResult) {
            C12231a.m1921a(C12231a.this, this.f24827c, new RunnableC12237e(wPResult));
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPQPayResultBean> wPResult) {
            C12231a.m1921a(C12231a.this, this.f24827c, new RunnableC12235c(wPResult));
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo24b(String str) {
            C12231a.m1921a(C12231a.this, this.f24827c, new RunnableC12234b(str));
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C12239f().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final void mo23c(String str) {
            C12231a.m1921a(C12231a.this, this.f24827c, new RunnableC12233a(str));
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: d */
        public final void mo11d(String str) {
            C12231a.m1921a(C12231a.this, this.f24827c, new RunnableC12236d());
        }
    }

    /* renamed from: j0.a$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C12240b extends AbstractC14257d<WPCheckSignResult> {

        /* renamed from: c */
        public final /* synthetic */ Context f24845c;

        /* renamed from: d */
        public final /* synthetic */ boolean f24846d;

        /* renamed from: e */
        public final /* synthetic */ String f24847e;

        /* renamed from: f */
        public final /* synthetic */ String f24848f;

        @NBSInstrumented
        /* renamed from: j0.a$b$a */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class RunnableC12241a implements Runnable {

            /* renamed from: a */
            public final /* synthetic */ String f24850a;

            public RunnableC12241a(String str) {
                this.f24850a = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                String str = this.f24850a;
                C12240b.this.getClass();
                Type type = new C12247f().getType();
                Gson gson = C13648k.f27492a;
                WPResult wPResult = (WPResult) (!(gson instanceof Gson) ? gson.fromJson(str, type) : NBSGsonInstrumentation.fromJson(gson, str, type));
                if ("0000".equals(wPResult.getCode())) {
                    if (wPResult.getCommonResp() != null && !TextUtils.isEmpty(wPResult.getCommonResp().getUserTokenId())) {
                        C10546a.C10576i.f20125a.m6169a(wPResult.getCommonResp().getUserTokenId());
                    }
                    C12240b c12240b = C12240b.this;
                    C12231a.m1921a(C12231a.this, c12240b.f24845c, new RunnableC12243c(wPResult));
                    return;
                }
                C12240b c12240b2 = C12240b.this;
                C12231a.m1921a(C12231a.this, c12240b2.f24845c, new RunnableC12244d(wPResult));
            }
        }

        /* renamed from: j0.a$b$b */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class RunnableC12242b implements Runnable {
            public RunnableC12242b(String str) {
            }

            @Override // java.lang.Runnable
            public final void run() {
                C12240b c12240b = C12240b.this;
                C12231a.m1921a(C12231a.this, c12240b.f24845c, new RunnableC12246e());
            }
        }

        /* renamed from: j0.a$b$c */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class RunnableC12243c implements Runnable {

            /* renamed from: a */
            public final /* synthetic */ WPResult f24853a;

            public RunnableC12243c(WPResult wPResult) {
                this.f24853a = wPResult;
            }

            @Override // java.lang.Runnable
            public final void run() {
                if (!C12231a.this.m1795b() || this.f24853a.getData() == null) {
                    return;
                }
                C12231a.this.m1798a().mo1901b(this.f24853a);
            }
        }

        /* renamed from: j0.a$b$d */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class RunnableC12244d implements Runnable {

            /* renamed from: a */
            public final /* synthetic */ WPResult f24855a;

            /* renamed from: j0.a$b$d$a */
            /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
            public class C12245a implements InterfaceC12248c {
                public C12245a() {
                }

                @Override // p398j0.C12231a.InterfaceC12248c
                /* renamed from: a */
                public final void mo1919a() {
                    if (C12231a.this.m1795b()) {
                        C12231a.this.m1798a().mo1790i(RunnableC12244d.this.f24855a.getMsg());
                        C12231a.this.m1798a().mo1905W();
                    }
                }

                @Override // p398j0.C12231a.InterfaceC12248c
                /* renamed from: b */
                public final void mo1918b() {
                    C12240b c12240b = C12240b.this;
                    C12231a.this.m1922a(c12240b.f24845c, c12240b.f24847e, c12240b.f24848f, true);
                }
            }

            public RunnableC12244d(WPResult wPResult) {
                this.f24855a = wPResult;
            }

            @Override // java.lang.Runnable
            public final void run() {
                C12240b c12240b = C12240b.this;
                C12231a.m1920a(C12231a.this, this.f24855a, c12240b.f24846d, new C12245a());
            }
        }

        /* renamed from: j0.a$b$e */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class RunnableC12246e implements Runnable {
            public RunnableC12246e() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                if (C12231a.this.m1795b()) {
                    C12231a.this.m1798a().mo1791a(C10531R.string.wp_mer_qpay_pay_fail);
                    C12231a.this.m1798a().mo1905W();
                }
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: j0.a$b$f */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        class C12247f extends TypeToken<WPResult<WPCheckSignResult>> {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C12240b(AbstractC12375a abstractC12375a, Context context, boolean z, String str, String str2) {
            super(abstractC12375a, 1);
            this.f24845c = context;
            this.f24846d = z;
            this.f24847e = str;
            this.f24848f = str2;
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult<WPCheckSignResult> wPResult) {
            C12231a.m1921a(C12231a.this, this.f24845c, new RunnableC12244d(wPResult));
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPCheckSignResult> wPResult) {
            C12231a.m1921a(C12231a.this, this.f24845c, new RunnableC12243c(wPResult));
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo24b(String str) {
            C12231a.m1921a(C12231a.this, this.f24845c, new RunnableC12242b(str));
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C12247f().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final void mo23c(String str) {
            C12231a.m1921a(C12231a.this, this.f24845c, new RunnableC12241a(str));
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: d */
        public final void mo11d(String str) {
            C12231a.m1921a(C12231a.this, this.f24845c, new RunnableC12246e());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: j0.a$c */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC12248c {
        /* renamed from: a */
        void mo1919a();

        /* renamed from: b */
        void mo1918b();
    }

    /* renamed from: a */
    public static void m1921a(C12231a c12231a, Context context, Runnable runnable) {
        c12231a.getClass();
        if (context == null || !(context instanceof Activity)) {
            return;
        }
        ((Activity) context).runOnUiThread(runnable);
    }

    /* renamed from: a */
    public static void m1920a(C12231a c12231a, WPResult wPResult, boolean z, InterfaceC12248c interfaceC12248c) {
        c12231a.getClass();
        if ("GATEWAY9000".equals(wPResult.getCode())) {
            C10546a c10546a = C10546a.C10576i.f20125a;
            c10546a.f20057g = "";
            c10546a.m6169a("");
            if (!z) {
                C10546a.C10576i.f20125a.m6172a(new C12249b(interfaceC12248c));
                return;
            }
        }
        interfaceC12248c.mo1919a();
    }

    /* renamed from: a */
    public final void m1922a(Context context, String str, String str2, boolean z) {
        m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27824n, C13707b.m126a("payScene", str, "tradeOrderNo", str2), false, new C12240b(this, context, z, str, str2)));
    }

    /* renamed from: a */
    public final void m1923a(Context context, String str, String str2, String str3, String str4, String str5, String str6, boolean z) {
        HashMap m126a = C13707b.m126a("tradeOrderNo", str, "payScene", str2);
        m126a.put("hmac", str3);
        m126a.put("qpayUrl", str4);
        m126a.put("cdkey", str5);
        m126a.put("verifyCode", str6);
        m126a.put("userClientUid", C10546a.C10576i.f20125a.f20059i);
        C14255c.C14256a.f27777a.m29a(C14262f.f27821k, m126a, false, new C12232a(this, context, str3, str4, z, str, str2, str5, str6));
    }
}
