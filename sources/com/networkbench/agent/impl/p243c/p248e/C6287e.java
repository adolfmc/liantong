package com.networkbench.agent.impl.p243c.p248e;

import android.content.Context;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.ConfigurationName;
import com.networkbench.agent.impl.harvest.HarvestConfiguration;
import com.networkbench.agent.impl.harvest.HarvestData;
import com.networkbench.agent.impl.harvest.p261b.C6459b;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.networkbench.agent.impl.p243c.p244a.C6255f;
import com.networkbench.agent.impl.p243c.p247d.C6269a;
import com.networkbench.agent.impl.p243c.p248e.C6295m;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6646o;
import com.networkbench.agent.impl.util.C6653u;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.e.e */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6287e {

    /* renamed from: a */
    public static boolean f15692a = true;

    /* renamed from: b */
    public static boolean f15693b = false;

    /* renamed from: c */
    public static String f15694c = null;

    /* renamed from: d */
    public static int f15695d = 1;

    /* renamed from: w */
    private static C6287e f15698w;

    /* renamed from: e */
    public long f15699e;

    /* renamed from: f */
    public long f15700f;

    /* renamed from: g */
    public long f15701g;

    /* renamed from: h */
    public long f15702h;

    /* renamed from: i */
    public long f15703i;

    /* renamed from: j */
    public long f15704j;

    /* renamed from: k */
    public long f15705k;

    /* renamed from: l */
    public long f15706l;

    /* renamed from: m */
    public long f15707m;

    /* renamed from: n */
    public long f15708n;

    /* renamed from: o */
    public long f15709o;

    /* renamed from: p */
    public long f15710p;

    /* renamed from: q */
    public long f15711q;

    /* renamed from: r */
    public long f15712r;

    /* renamed from: y */
    private String f15716y;

    /* renamed from: v */
    private static final InterfaceC6393e f15697v = C6394f.m10150a();

    /* renamed from: u */
    public static volatile long f15696u = 0;

    /* renamed from: s */
    public boolean f15713s = false;

    /* renamed from: t */
    public boolean f15714t = false;

    /* renamed from: z */
    private boolean f15717z = false;

    /* renamed from: x */
    private C6291i f15715x = new C6291i();

    /* renamed from: a */
    private boolean m10629a(int i) {
        return i == -1;
    }

    /* renamed from: a */
    private boolean m10628a(int i, int i2) {
        return i != i2;
    }

    /* renamed from: a */
    public static C6287e m10630a() {
        if (f15698w == null) {
            synchronized (C6287e.class) {
                if (f15698w == null) {
                    f15698w = new C6287e();
                }
            }
        }
        return f15698w;
    }

    /* renamed from: b */
    public C6291i m10621b() {
        return this.f15715x;
    }

    private C6287e() {
    }

    /* renamed from: c */
    public void m10618c() {
        this.f15700f = 0L;
        this.f15701g = 0L;
        this.f15703i = 0L;
        this.f15701g = 0L;
        this.f15705k = 0L;
        this.f15704j = 0L;
        this.f15706l = 0L;
        this.f15707m = 0L;
        this.f15710p = 0L;
        this.f15708n = 0L;
        this.f15709o = 0L;
        this.f15711q = 0L;
        this.f15712r = 0L;
    }

    /* renamed from: m */
    private boolean m10605m() {
        if (this.f15700f != 0 || f15696u == 0) {
            return false;
        }
        long j = HarvestConfiguration.HOT_START_THRESHOLD;
        if (NBSAgent.getImpl() != null && NBSAgent.getImpl().m9147p() != null) {
            j = NBSAgent.getImpl().m9147p().m8834f().getHotStartThreshold();
        }
        boolean z = System.currentTimeMillis() - f15696u >= j * 1000;
        this.f15717z = z;
        return z;
    }

    /* renamed from: a */
    private void m10625a(C6295m.EnumC6297b enumC6297b) {
        m10604n();
        this.f15715x.m10589a(C6295m.EnumC6301f.appstart);
    }

    /* renamed from: n */
    private void m10604n() {
        C6638h.f17113m.set(EnumC6288f.HOT_RUN.m10597a());
        this.f15699e = System.currentTimeMillis();
    }

    /* renamed from: o */
    private void m10603o() {
        try {
            Context m9076K = C6638h.m8963w().m9076K();
            int i = m9076K.getPackageManager().getPackageInfo(m9076K.getPackageName(), 0).versionCode;
            C6638h.m8963w().m9008f(i);
            C6646o c6646o = new C6646o(m9076K);
            m10627a(c6646o.m8850b(), i, c6646o, m9076K);
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = f15697v;
            interfaceC6393e.mo10116d("initInAttachBaseContextEnv:" + e.getMessage());
        }
    }

    @RequiresApi(api = 4)
    /* renamed from: a */
    public void m10627a(int i, int i2, C6646o c6646o, Context context) {
        if (m10628a(i, i2)) {
            C6459b.m9938b(2);
        }
        if (m10629a(i)) {
            f15692a = true;
            f15693b = true;
            f15695d = 1;
            if (m10626a(context) && !c6646o.m8863a()) {
                C6638h.m8963w().m9004g(511);
                C6396h.m10125q("首次启动, debug模式,设置opt为: 511 !");
            }
            C6459b.m9938b(2);
            C6459b.m9939b();
            if (C6653u.m8729b(context)) {
                m10624a(c6646o);
            }
        } else {
            if (C6653u.m8729b(context)) {
                m10623a(c6646o, m10628a(i, i2));
                m10624a(c6646o);
            } else {
                if (!TextUtils.isEmpty(ConfigurationName.processName)) {
                    m10623a(c6646o, m10628a(i, i2));
                }
                f15694c = c6646o.m8822j();
            }
            f15692a = C6638h.m8963w().m9038ah();
            C6459b.m9941a();
        }
        C6638h.m8963w().m8978n(c6646o.m8822j());
        if (m10629a(i) || m10628a(i, i2)) {
            C6638h.f17113m.set(EnumC6288f.FIRST_RUN.m10597a());
        } else {
            C6638h.f17113m.set(EnumC6288f.COLD_RUN.m10597a());
        }
        c6646o.m8849b(i2);
    }

    @RequiresApi(api = 4)
    /* renamed from: a */
    public boolean m10626a(Context context) {
        return (context.getApplicationInfo() == null || (context.getApplicationInfo().flags & 2) == 0) ? false : true;
    }

    /* renamed from: a */
    private void m10624a(C6646o c6646o) {
        if (c6646o.m8863a()) {
            int m8845c = c6646o.m8845c();
            C6396h.m10125q("saveSdkEnabled   enabledInt : " + m8845c);
            if (m8845c != 0) {
                c6646o.m8844c(2);
                return;
            }
            return;
        }
        c6646o.m8844c(2);
    }

    /* renamed from: a */
    private void m10623a(C6646o c6646o, boolean z) {
        int m8845c = c6646o.m8845c();
        if (m8845c == 1) {
            C6638h.m8963w().m9006f(true);
            C6638h.m8963w().m9004g(c6646o.m8866H());
            C6396h.m10125q("冷启动 ,enabledInt = 1  , setModuleSwitch :  " + c6646o.m8866H());
            c6646o.m8861a(0);
            C6638h.m8963w().m9000h(c6646o.m8841d());
            return;
        }
        C6638h.m8963w().m9004g(0);
        C6638h.m8963w().m9000h(0);
        C6396h.m10125q("冷启动 ,enabledInt : " + m8845c + ", setModuleSwitch :  ");
        C6638h.m8963w().m9023b(false);
        if (m8845c == 2) {
            if (z) {
                C6638h.m8963w().m9023b(false);
            } else {
                C6638h.m8963w().m9023b(true);
            }
        }
    }

    /* renamed from: d */
    public void m10616d() {
        if (C6638h.f17113m.get() == EnumC6288f.BACKGROUND_SWITCH.m10597a() || C6638h.f17113m.get() == EnumC6288f.RUNNING.m10597a()) {
            return;
        }
        C6284c c6284c = new C6284c(C6638h.f17113m.get(), m10601q(), m10602p(), m10599s(), m10600r());
        C6293k m10590a = this.f15715x.m10590a();
        if (m10590a != null) {
            c6284c.m10633a(m10590a);
            if (C6638h.m8963w().m9037ai()) {
                if (this.f15717z) {
                    C6255f.m10807a("ApplicationInForeground", "0", "", -1);
                } else {
                    C6255f.f15554c = new C6269a();
                }
            }
            if (m10590a.m10543p() >= 20000) {
                return;
            }
            HarvestData.getAppStartDatas().mo10631a((HarvestableArray) c6284c);
        }
    }

    /* renamed from: p */
    private long m10602p() {
        if (C6638h.f17113m.get() == EnumC6288f.HOT_RUN.m10597a()) {
            return -1L;
        }
        return this.f15703i - this.f15701g;
    }

    /* renamed from: q */
    private long m10601q() {
        if (C6638h.f17113m.get() == EnumC6288f.HOT_RUN.m10597a()) {
            return -1L;
        }
        return this.f15701g - this.f15700f;
    }

    /* renamed from: r */
    private long m10600r() {
        if (this.f15704j == 0) {
            return this.f15711q - this.f15707m;
        }
        long j = this.f15712r;
        if (j <= 0) {
            return this.f15711q - this.f15705k;
        }
        return j - this.f15705k;
    }

    /* renamed from: s */
    private long m10599s() {
        long j = this.f15704j;
        if (j != 0) {
            long j2 = this.f15705k;
            if (j2 != 0) {
                return (this.f15703i != 0 || j == 0) ? this.f15705k - this.f15703i : j2 - j;
            }
        }
        return this.f15707m - this.f15706l;
    }

    /* renamed from: b */
    public void m10620b(Context context) {
        this.f15713s = false;
        C6638h.m8963w().m9056a(context);
        m10603o();
        if (f15692a) {
            this.f15699e = System.currentTimeMillis();
            this.f15700f = this.f15699e;
            this.f15715x.m10589a(C6295m.EnumC6301f.appstart);
            C6291i c6291i = this.f15715x;
            c6291i.enterMethod(new NBSTraceUnit(context.getApplicationInfo().className + "#attachBaseContext", C6295m.EnumC6300e.OTHER.m10531a()));
        }
    }

    /* renamed from: t */
    private Context m10598t() {
        return C6638h.m8963w().m9076K();
    }

    /* renamed from: e */
    public void m10614e() {
        if (f15692a) {
            this.f15701g = System.currentTimeMillis();
            this.f15715x.exitMethod();
        }
    }

    /* renamed from: f */
    public void m10612f() {
        if (f15692a) {
            this.f15702h = System.currentTimeMillis();
            C6291i c6291i = this.f15715x;
            c6291i.enterMethod(new NBSTraceUnit(m10598t().getApplicationInfo().className + "#onCreate", C6295m.EnumC6300e.OTHER.m10531a()));
        }
    }

    /* renamed from: g */
    public void m10611g() {
        if (f15692a) {
            this.f15703i = System.currentTimeMillis();
            this.f15715x.exitMethod();
        }
    }

    /* renamed from: a */
    public void m10622a(String str) {
        if (!this.f15717z && m10605m() && C6638h.m8963w().m8976o()) {
            C6396h.m10133i("isHotStart yes  driveCount  !");
            HarvestData.getAppHotStartData().m10635c();
            f15695d = 0;
        }
        if ((!this.f15713s || this.f15717z) && f15692a) {
            this.f15716y = str;
            if (C6638h.f17113m.get() == EnumC6288f.BACKGROUND.m10597a()) {
                if (this.f15717z) {
                    m10625a(C6295m.EnumC6297b.ActivityCreate);
                } else {
                    C6638h.f17113m.set(EnumC6288f.BACKGROUND_SWITCH.m10597a());
                }
            }
            this.f15704j = System.currentTimeMillis();
            C6291i c6291i = this.f15715x;
            c6291i.enterMethod(new NBSTraceUnit(str + "#onCreate", C6295m.EnumC6300e.OTHER.m10531a()));
        }
    }

    /* renamed from: h */
    public void m10610h() {
        if ((!this.f15713s || this.f15717z) && f15692a) {
            this.f15705k = System.currentTimeMillis();
            this.f15715x.exitMethod();
        }
    }

    /* renamed from: b */
    public void m10619b(String str) {
        if ((!this.f15713s || this.f15717z) && f15692a) {
            C6291i c6291i = this.f15715x;
            c6291i.enterMethod(new NBSTraceUnit(str + "#onStart", C6295m.EnumC6300e.OTHER.m10531a()));
            this.f15708n = System.currentTimeMillis();
        }
    }

    /* renamed from: i */
    public void m10609i() {
        if ((!this.f15713s || this.f15717z) && f15692a) {
            if (this.f15709o == 0) {
                this.f15715x.exitMethod();
                this.f15709o = System.currentTimeMillis();
            }
            this.f15717z = false;
        }
    }

    /* renamed from: c */
    public void m10617c(String str) {
        if (!this.f15717z && m10605m() && C6638h.m8963w().m8976o()) {
            C6396h.m10133i("isHotStart yes  driveCount  !");
            HarvestData.getAppHotStartData().m10635c();
            f15695d = 0;
        }
        this.f15713s = false;
        this.f15716y = str;
        f15692a = C6638h.m8963w().m9038ah();
        if (f15692a && this.f15706l == 0) {
            if (this.f15717z) {
                m10625a(C6295m.EnumC6297b.ActivityRestart);
            } else {
                C6638h.f17113m.set(EnumC6288f.BACKGROUND_SWITCH.m10597a());
            }
            this.f15706l = System.currentTimeMillis();
            C6291i c6291i = this.f15715x;
            c6291i.enterMethod(new NBSTraceUnit(str + "#onRestart", C6295m.EnumC6300e.OTHER.m10531a()));
        }
    }

    /* renamed from: j */
    public void m10608j() {
        if (f15692a && this.f15707m == 0) {
            this.f15707m = System.currentTimeMillis();
            this.f15715x.exitMethod();
        }
    }

    /* renamed from: d */
    public void m10615d(String str) {
        if ((!this.f15713s || this.f15717z) && f15692a) {
            this.f15716y = str;
            this.f15710p = System.currentTimeMillis();
            C6291i c6291i = this.f15715x;
            c6291i.enterMethod(new NBSTraceUnit(str + "#onResume", C6295m.EnumC6300e.OTHER.m10531a()));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0071, code lost:
        if (r5.f15717z == false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x008f, code lost:
        if (r5.f15717z == false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0091, code lost:
        m10606l();
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0094, code lost:
        r5.f15717z = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0096, code lost:
        return;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006c  */
    /* renamed from: k */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m10607k() {
        /*
            r5 = this;
            boolean r0 = com.networkbench.agent.impl.p243c.p248e.C6287e.f15692a
            if (r0 != 0) goto L5
            return
        L5:
            boolean r0 = r5.f15713s
            if (r0 == 0) goto Le
            boolean r0 = r5.f15717z
            if (r0 != 0) goto Le
            return
        Le:
            com.networkbench.agent.impl.util.h r0 = com.networkbench.agent.impl.util.C6638h.m8963w()
            boolean r0 = r0.m9038ah()
            if (r0 != 0) goto L19
            return
        L19:
            r0 = 0
            com.networkbench.agent.impl.c.e.i r1 = r5.f15715x     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            r1.exitMethod()     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            long r1 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            r5.f15711q = r1     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            com.networkbench.agent.impl.util.h r1 = com.networkbench.agent.impl.util.C6638h.m8963w()     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            com.networkbench.agent.impl.util.h$a r1 = r1.m9029aq()     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            com.networkbench.agent.impl.util.h$a r2 = com.networkbench.agent.impl.util.C6638h.EnumC6639a.Native     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            if (r1 == r2) goto L5d
            boolean r1 = r5.f15714t     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            if (r1 == 0) goto L36
            goto L5d
        L36:
            com.networkbench.agent.impl.c.e.i r1 = r5.f15715x     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            com.networkbench.agent.impl.instrumentation.NBSTraceUnit r2 = new com.networkbench.agent.impl.instrumentation.NBSTraceUnit     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            r3.<init>()     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            java.lang.String r4 = r5.f15716y     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            r3.append(r4)     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            java.lang.String r4 = "#hybirdOnResume"
            r3.append(r4)     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            com.networkbench.agent.impl.c.e.m$e r4 = com.networkbench.agent.impl.p243c.p248e.C6295m.EnumC6300e.OTHER     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            int r4 = r4.m10531a()     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            r2.<init>(r3, r4)     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            r1.enterMethod(r2)     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            r1 = 1
            r5.f15713s = r1     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            goto L60
        L5d:
            r5.m10616d()     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
        L60:
            com.networkbench.agent.impl.util.h r1 = com.networkbench.agent.impl.util.C6638h.m8963w()
            com.networkbench.agent.impl.util.h$a r1 = r1.m9029aq()
            com.networkbench.agent.impl.util.h$a r2 = com.networkbench.agent.impl.util.C6638h.EnumC6639a.Native
            if (r1 != r2) goto L6f
            r5.m10606l()
        L6f:
            boolean r1 = r5.f15717z
            if (r1 == 0) goto L94
            goto L91
        L74:
            r1 = move-exception
            goto L97
        L76:
            r1 = move-exception
            com.networkbench.agent.impl.f.e r2 = com.networkbench.agent.impl.p243c.p248e.C6287e.f15697v     // Catch: java.lang.Throwable -> L74
            java.lang.String r3 = "error:"
            r2.mo10121a(r3, r1)     // Catch: java.lang.Throwable -> L74
            com.networkbench.agent.impl.util.h r1 = com.networkbench.agent.impl.util.C6638h.m8963w()
            com.networkbench.agent.impl.util.h$a r1 = r1.m9029aq()
            com.networkbench.agent.impl.util.h$a r2 = com.networkbench.agent.impl.util.C6638h.EnumC6639a.Native
            if (r1 != r2) goto L8d
            r5.m10606l()
        L8d:
            boolean r1 = r5.f15717z
            if (r1 == 0) goto L94
        L91:
            r5.m10606l()
        L94:
            r5.f15717z = r0
            return
        L97:
            com.networkbench.agent.impl.util.h r2 = com.networkbench.agent.impl.util.C6638h.m8963w()
            com.networkbench.agent.impl.util.h$a r2 = r2.m9029aq()
            com.networkbench.agent.impl.util.h$a r3 = com.networkbench.agent.impl.util.C6638h.EnumC6639a.Native
            if (r2 != r3) goto La6
            r5.m10606l()
        La6:
            boolean r2 = r5.f15717z
            if (r2 == 0) goto Lad
            r5.m10606l()
        Lad:
            r5.f15717z = r0
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.networkbench.agent.impl.p243c.p248e.C6287e.m10607k():void");
    }

    /* renamed from: e */
    public void m10613e(String str) {
        if (this.f15711q <= 0) {
            C6638h.m8963w().m8971p(false);
            return;
        }
        this.f15715x.exitMethod();
        this.f15712r = System.currentTimeMillis();
        m10616d();
        m10606l();
        this.f15713s = false;
        this.f15717z = false;
    }

    /* renamed from: l */
    public void m10606l() {
        NBSAppInstrumentation.isAppInBackground = false;
        C6638h.f17113m.set(EnumC6288f.RUNNING.m10597a());
        f15698w.m10618c();
    }
}
