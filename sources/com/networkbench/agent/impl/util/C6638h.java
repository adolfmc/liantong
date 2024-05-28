package com.networkbench.agent.impl.util;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.text.TextUtils;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.p261b.C6459b;
import com.networkbench.agent.impl.p243c.p246c.C6264a;
import com.networkbench.agent.impl.p243c.p248e.C6287e;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.C6398j;
import com.networkbench.agent.impl.p254f.C6400l;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p268n.C6516c;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.util.h */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6638h {

    /* renamed from: C */
    public static final int f17100C = 2000;

    /* renamed from: H */
    public static final int f17101H = 2;

    /* renamed from: h */
    public static long f17108h = 0;

    /* renamed from: i */
    public static long f17109i = 0;

    /* renamed from: l */
    public static boolean f17112l = false;

    /* renamed from: r */
    public static final String f17118r = "X-Tingyun-Tx-Data";

    /* renamed from: s */
    public static final String f17119s = "X-Tingyun-Id";

    /* renamed from: t */
    public static final String f17120t = "X-Tingyun";

    /* renamed from: u */
    public static final String f17121u = "X-Tingyun-Data";

    /* renamed from: v */
    public static final long f17122v = 20000;

    /* renamed from: w */
    public static final String f17123w = "<_TY_C_API>";

    /* renamed from: A */
    public String f17125A;

    /* renamed from: F */
    public String f17129F;

    /* renamed from: K */
    private JSONArray f17132K;

    /* renamed from: S */
    private String f17140S;

    /* renamed from: Z */
    private int f17147Z;

    /* renamed from: aB */
    private String f17149aB;

    /* renamed from: aD */
    private boolean f17151aD;

    /* renamed from: aa */
    private C6264a f17153aa;

    /* renamed from: ab */
    private C6634d f17154ab;

    /* renamed from: ad */
    private Context f17156ad;

    /* renamed from: ae */
    private String f17157ae;

    /* renamed from: af */
    private String f17158af;

    /* renamed from: ag */
    private String f17159ag;

    /* renamed from: as */
    private String f17170as;

    /* renamed from: at */
    private String f17171at;

    /* renamed from: av */
    private String f17173av;

    /* renamed from: aw */
    private boolean f17174aw;

    /* renamed from: ax */
    private long f17175ax;

    /* renamed from: e */
    public int f17179e;

    /* renamed from: f */
    public String f17180f;

    /* renamed from: I */
    private static final C6638h f17102I = new C6638h();

    /* renamed from: a */
    public static int f17103a = 600000;

    /* renamed from: b */
    public static int f17105b = 300000;

    /* renamed from: c */
    public static int f17106c = 10;

    /* renamed from: g */
    public static int f17107g = 0;

    /* renamed from: k */
    public static String f17111k = "";

    /* renamed from: j */
    public static String f17110j = MessageFormat.format("NBS Newlens Agent/{0} ({1} {2})", NBSAgent.getVersion(), "Android", Build.VERSION.RELEASE);

    /* renamed from: m */
    public static AtomicInteger f17113m = new AtomicInteger(0);

    /* renamed from: n */
    public static int f17114n = 0;

    /* renamed from: o */
    public static boolean f17115o = false;

    /* renamed from: p */
    public static String f17116p = "";

    /* renamed from: q */
    public static int f17117q = 50;

    /* renamed from: y */
    public static final InterfaceC6393e f17124y = C6394f.m10150a();

    /* renamed from: ak */
    private static int f17104ak = 3;

    /* renamed from: d */
    public String f17178d = "";

    /* renamed from: J */
    private boolean f17131J = false;

    /* renamed from: L */
    private boolean f17133L = false;

    /* renamed from: M */
    private boolean f17134M = true;

    /* renamed from: N */
    private boolean f17135N = true;

    /* renamed from: O */
    private boolean f17136O = true;

    /* renamed from: P */
    private boolean f17137P = true;

    /* renamed from: Q */
    private int f17138Q = 1;

    /* renamed from: R */
    private Map<String, String> f17139R = new ConcurrentHashMap();

    /* renamed from: T */
    private boolean f17141T = false;

    /* renamed from: U */
    private long f17142U = 0;

    /* renamed from: V */
    private boolean f17143V = true;

    /* renamed from: W */
    private C6641j f17144W = new C6641j();

    /* renamed from: X */
    private boolean f17145X = false;

    /* renamed from: Y */
    private EnumC6639a f17146Y = EnumC6639a.Native;

    /* renamed from: x */
    public boolean f17181x = true;

    /* renamed from: z */
    public boolean f17182z = false;

    /* renamed from: B */
    public boolean f17126B = false;

    /* renamed from: ac */
    private boolean f17155ac = false;

    /* renamed from: ah */
    private boolean f17160ah = false;

    /* renamed from: ai */
    private long f17161ai = -1;

    /* renamed from: aj */
    private AtomicInteger f17162aj = new AtomicInteger(0);

    /* renamed from: al */
    private String f17163al = "";

    /* renamed from: am */
    private String f17164am = "";

    /* renamed from: an */
    private String f17165an = "";

    /* renamed from: ao */
    private AtomicBoolean f17166ao = new AtomicBoolean(true);

    /* renamed from: ap */
    private AtomicInteger f17167ap = new AtomicInteger(1);

    /* renamed from: aq */
    private Float f17168aq = Float.valueOf(1.0f);

    /* renamed from: ar */
    private Map<String, String> f17169ar = new HashMap();

    /* renamed from: au */
    private Location f17172au = null;

    /* renamed from: ay */
    private int f17176ay = 4000;

    /* renamed from: az */
    private boolean f17177az = false;

    /* renamed from: aA */
    private int f17148aA = -2;

    /* renamed from: D */
    public int f17127D = 16;

    /* renamed from: aC */
    private boolean f17150aC = false;

    /* renamed from: E */
    public HashMap<String, String[]> f17128E = new HashMap<>();

    /* renamed from: aE */
    private String f17152aE = "";

    /* renamed from: G */
    public int f17130G = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.util.h$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum EnumC6639a {
        Native,
        Hybrid
    }

    /* renamed from: a */
    public void m9047a(Date date, boolean z) {
    }

    /* renamed from: a */
    public void m9051a(String str) {
        if (this.f17151aD) {
            C6516c.m9566a(str);
        }
    }

    /* renamed from: a */
    public void m9046a(boolean z) {
        this.f17144W.m8926m(z);
    }

    /* renamed from: a */
    public JSONArray m9060a() {
        return this.f17132K;
    }

    /* renamed from: b */
    public void m9024b(String str) {
        try {
            this.f17132K = new JSONArray(str);
            if (this.f17132K.length() > 0) {
                this.f17133L = true;
            }
            C6396h.m10126p("setApmsIssue : " + this.f17132K.toString());
        } catch (Throwable unused) {
            C6396h.m10126p("setApmsIssue  has an error!");
        }
    }

    /* renamed from: b */
    public boolean m9027b() {
        return this.f17133L;
    }

    /* renamed from: c */
    public boolean m9022c() {
        return this.f17134M && this.f17137P;
    }

    /* renamed from: d */
    public boolean m9017d() {
        C6396h.m10125q("customAction : " + this.f17135N);
        C6396h.m10125q("sdkInitEnabled : " + this.f17137P);
        return this.f17135N && this.f17137P;
    }

    /* renamed from: e */
    public boolean m9013e() {
        return this.f17136O && this.f17137P;
    }

    /* renamed from: b */
    public void m9023b(boolean z) {
        this.f17137P = z;
    }

    /* renamed from: a */
    public void m9059a(int i) {
        if (i == 1) {
            this.f17136O = true;
        } else {
            this.f17136O = false;
        }
    }

    /* renamed from: b */
    public void m9026b(int i) {
        if (i == 1) {
            this.f17135N = true;
        } else {
            this.f17135N = false;
        }
    }

    /* renamed from: c */
    public void m9021c(int i) {
        if (i == 1) {
            this.f17134M = true;
        } else {
            this.f17134M = false;
        }
    }

    /* renamed from: d */
    public void m9016d(int i) {
        this.f17138Q = i;
    }

    /* renamed from: f */
    public int m9009f() {
        return this.f17138Q;
    }

    /* renamed from: g */
    public int m9005g() {
        return this.f17179e;
    }

    /* renamed from: e */
    public void m9012e(int i) {
        C6396h.m10125q("setOldFeature :  设置一下历史保存的feature值 : " + i);
        this.f17179e = i;
    }

    /* renamed from: a */
    public void m9049a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        this.f17139R.put(str, str2);
    }

    /* renamed from: h */
    public Map m9001h() {
        return this.f17139R;
    }

    /* renamed from: i */
    public String m8997i() {
        return this.f17140S;
    }

    /* renamed from: c */
    public void m9019c(String str) {
        this.f17140S = str;
    }

    /* renamed from: j */
    public boolean m8993j() {
        return this.f17141T;
    }

    /* renamed from: c */
    public void m9018c(boolean z) {
        this.f17141T = z;
    }

    /* renamed from: d */
    public void m9014d(boolean z) {
        this.f17145X = z;
    }

    /* renamed from: k */
    public boolean m8989k() {
        return this.f17145X;
    }

    /* renamed from: l */
    public C6264a m8985l() {
        return this.f17153aa;
    }

    /* renamed from: a */
    public void m9054a(C6264a c6264a) {
        this.f17153aa = c6264a;
    }

    /* renamed from: d */
    public void m9015d(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("null")) {
        }
    }

    /* renamed from: e */
    public void m9011e(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("null")) {
            this.f17182z = false;
        }
    }

    /* renamed from: m */
    public C6634d m8982m() {
        if (this.f17154ab == null && !TextUtils.isEmpty(this.f17125A)) {
            try {
                return new C6634d(this.f17125A);
            } catch (Exception e) {
                f17124y.mo10121a("getEncryptContent has an error :   ", e);
            }
        }
        return this.f17154ab;
    }

    /* renamed from: a */
    public void m9053a(C6634d c6634d) {
        this.f17154ab = c6634d;
    }

    /* renamed from: e */
    public void m9010e(boolean z) {
        this.f17131J = z;
        m8969q(z);
    }

    /* renamed from: q */
    private void m8969q(boolean z) {
        if (z) {
            C6398j.m10112a(C6400l.m10090a().m10075a(true).m10078a(2).m10074b(1).m10076a("NBSAgent").m10079a());
        }
    }

    /* renamed from: n */
    public boolean m8979n() {
        return this.f17131J;
    }

    /* renamed from: f */
    public void m9006f(boolean z) {
        this.f17144W.m8950a(z);
    }

    /* renamed from: o */
    public boolean m8976o() {
        return this.f17144W.m8949b();
    }

    /* renamed from: p */
    public boolean m8973p() {
        return this.f17155ac;
    }

    /* renamed from: g */
    public void m9002g(boolean z) {
        this.f17155ac = z;
    }

    /* renamed from: q */
    public boolean m8970q() {
        return this.f17177az;
    }

    /* renamed from: h */
    public void m8998h(boolean z) {
        this.f17177az = z;
    }

    /* renamed from: r */
    public String m8968r() {
        return this.f17173av;
    }

    /* renamed from: f */
    public void m9007f(String str) {
        this.f17173av = str;
    }

    /* renamed from: s */
    public Location m8967s() {
        return this.f17172au;
    }

    /* renamed from: a */
    public void m9055a(Location location) {
        this.f17172au = location;
    }

    /* renamed from: t */
    public long m8966t() {
        return this.f17142U;
    }

    /* renamed from: a */
    public void m9057a(long j) {
        if (this.f17142U > 0) {
            return;
        }
        this.f17142U = j;
    }

    /* renamed from: u */
    public boolean m8965u() {
        return this.f17143V;
    }

    /* renamed from: i */
    public void m8994i(boolean z) {
        this.f17143V = z;
    }

    /* renamed from: v */
    public String m8964v() {
        if (TextUtils.isEmpty(this.f17159ag)) {
            Context context = this.f17156ad;
            if (context == null) {
                return "";
            }
            return C6636f.m9092c(this.f17156ad.getSharedPreferences(m8981m(context.getPackageName()), 0).getString(C6636f.m9093b("userId"), ""));
        }
        return this.f17159ag;
    }

    /* renamed from: g */
    public void m9003g(String str) {
        this.f17159ag = str;
        Context context = this.f17156ad;
        if (context == null) {
            return;
        }
        this.f17156ad.getSharedPreferences(m8981m(context.getPackageName()), 0).edit().putString(C6636f.m9093b("userId"), C6636f.m9093b(str)).commit();
    }

    /* renamed from: w */
    public static C6638h m8963w() {
        return f17102I;
    }

    /* renamed from: x */
    protected void m8962x() {
        this.f17162aj.set(2);
        Map<String, String> map = this.f17169ar;
        map.put("status", "{o:c|b:" + this.f17165an + "}");
        this.f17170as = "";
    }

    /* renamed from: y */
    protected boolean m8961y() {
        return this.f17162aj.get() == 1;
    }

    /* renamed from: z */
    protected boolean m8960z() {
        return this.f17162aj.get() == 0;
    }

    /* renamed from: A */
    public String m9086A() {
        return this.f17158af;
    }

    /* renamed from: h */
    public void m8999h(String str) {
        this.f17158af = str;
    }

    /* renamed from: B */
    public boolean m9085B() {
        return this.f17144W.m8949b() && this.f17160ah;
    }

    /* renamed from: j */
    public void m8990j(boolean z) {
        this.f17160ah = z;
    }

    /* renamed from: C */
    public int m9084C() {
        return this.f17147Z;
    }

    /* renamed from: f */
    public void m9008f(int i) {
        this.f17147Z = i;
    }

    /* renamed from: D */
    protected boolean m9083D() {
        return this.f17162aj.get() == 2;
    }

    /* renamed from: E */
    protected String m9082E() {
        return this.f17163al;
    }

    /* renamed from: i */
    protected void m8995i(String str) {
        this.f17163al = str;
    }

    /* renamed from: F */
    protected boolean m9081F() {
        return this.f17166ao.get();
    }

    /* renamed from: k */
    protected void m8986k(boolean z) {
        this.f17166ao.set(z);
    }

    /* renamed from: G */
    protected int m9080G() {
        return this.f17167ap.get();
    }

    /* renamed from: H */
    protected int m9079H() {
        return this.f17167ap.getAndDecrement();
    }

    /* renamed from: I */
    protected Map<String, String> m9078I() {
        return this.f17169ar;
    }

    /* renamed from: J */
    protected String m9077J() {
        return this.f17170as;
    }

    /* renamed from: K */
    public Context m9076K() {
        return this.f17156ad;
    }

    /* renamed from: a */
    public void m9056a(Context context) {
        this.f17156ad = context;
    }

    /* renamed from: j */
    public void m8991j(String str) {
        this.f17157ae = str;
    }

    /* renamed from: L */
    public String m9075L() {
        return this.f17157ae;
    }

    /* renamed from: M */
    protected String m9074M() {
        return this.f17171at;
    }

    /* renamed from: k */
    protected void m8987k(String str) {
        this.f17171at = str;
    }

    /* renamed from: N */
    protected int m9073N() {
        return f17104ak;
    }

    /* renamed from: O */
    protected int m9072O() {
        return this.f17162aj.get();
    }

    /* renamed from: P */
    protected String m9071P() {
        return this.f17164am;
    }

    /* renamed from: l */
    protected void m8984l(String str) {
        this.f17165an = str;
    }

    /* renamed from: Q */
    public Float m9070Q() {
        return this.f17168aq;
    }

    /* renamed from: a */
    public void m9052a(Float f) {
        this.f17168aq = f;
    }

    /* renamed from: m */
    public static String m8981m(String str) {
        return "nbsagent_preference_" + str;
    }

    /* renamed from: R */
    public boolean m9069R() {
        return this.f17174aw;
    }

    /* renamed from: l */
    public void m8983l(boolean z) {
        this.f17174aw = z;
    }

    /* renamed from: S */
    public long m9068S() {
        return this.f17161ai;
    }

    /* renamed from: b */
    public void m9025b(long j) {
        this.f17161ai = j;
    }

    /* renamed from: T */
    public long m9067T() {
        return this.f17175ax;
    }

    /* renamed from: c */
    public void m9020c(long j) {
        this.f17175ax = j;
    }

    /* renamed from: U */
    public String m9066U() {
        return this.f17149aB;
    }

    /* renamed from: n */
    public void m8978n(String str) {
        this.f17149aB = str;
    }

    /* renamed from: V */
    public boolean m9065V() {
        return this.f17151aD;
    }

    /* renamed from: m */
    public void m8980m(boolean z) {
        this.f17151aD = z;
    }

    /* renamed from: g */
    public void m9004g(int i) {
        this.f17144W.m8952a(i);
    }

    /* renamed from: W */
    public void m9064W() {
        this.f17144W.m8953a();
    }

    /* renamed from: n */
    public void m8977n(boolean z) {
        this.f17144W.m8928l(z);
    }

    /* renamed from: a */
    public void m9058a(int i, boolean z) {
        if (C6287e.f15693b) {
            if (!this.f17150aC) {
                this.f17144W.m8951a(i, true);
                if (!z) {
                    C6459b.m9940a(i);
                }
            }
            this.f17150aC = true;
        }
    }

    /* renamed from: h */
    public void m9000h(int i) {
        this.f17144W.m8930k(i == 1);
    }

    /* renamed from: X */
    public boolean m9063X() {
        return this.f17144W.m8947c();
    }

    /* renamed from: Y */
    public boolean m9062Y() {
        return this.f17144W.m8945d() && this.f17144W.m8947c();
    }

    /* renamed from: Z */
    public boolean m9061Z() {
        return this.f17144W.m8943e();
    }

    /* renamed from: aa */
    public boolean m9045aa() {
        return this.f17144W.m8941f();
    }

    /* renamed from: ab */
    public boolean m9044ab() {
        return this.f17144W.m8939g();
    }

    /* renamed from: i */
    public void m8996i(int i) {
        this.f17148aA = i;
    }

    /* renamed from: ac */
    public int m9043ac() {
        return this.f17148aA;
    }

    /* renamed from: ad */
    public boolean m9042ad() {
        return this.f17144W.m8937h();
    }

    /* renamed from: ae */
    public boolean m9041ae() {
        return this.f17144W.m8935i();
    }

    /* renamed from: af */
    public boolean m9040af() {
        return this.f17144W.m8931k();
    }

    /* renamed from: ag */
    public boolean m9039ag() {
        return this.f17144W.m8929l();
    }

    /* renamed from: ah */
    public boolean m9038ah() {
        return this.f17144W.m8927m();
    }

    /* renamed from: ai */
    public boolean m9037ai() {
        return this.f17144W.m8933j();
    }

    /* renamed from: o */
    public void m8974o(boolean z) {
        this.f17144W.m8934i(z);
    }

    /* renamed from: aj */
    public boolean m9036aj() {
        return this.f17144W.m8925n();
    }

    /* renamed from: a */
    public void m9048a(String str, String[] strArr) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f17128E.put(str, strArr);
    }

    /* renamed from: ak */
    public boolean m9035ak() {
        return this.f17144W.m8924o();
    }

    /* renamed from: al */
    public String m9034al() {
        return this.f17129F;
    }

    /* renamed from: o */
    public void m8975o(String str) {
        this.f17129F = str;
    }

    /* renamed from: p */
    public void m8972p(String str) {
        this.f17152aE = str;
    }

    /* renamed from: am */
    public String m9033am() {
        return "c=A|" + this.f17152aE + ";";
    }

    /* renamed from: j */
    public void m8992j(int i) {
        this.f17130G = i;
    }

    /* renamed from: ar */
    private boolean m9028ar() {
        return this.f17130G == 1;
    }

    /* renamed from: an */
    public boolean m9032an() {
        return !TextUtils.isEmpty(this.f17152aE) && m9028ar() && m9036aj();
    }

    /* renamed from: ao */
    public static int m9031ao() {
        int nextInt = new Random().nextInt();
        return nextInt < 0 ? -nextInt : nextInt < 100 ? nextInt + 100 : nextInt;
    }

    /* renamed from: a */
    public static String m9050a(String str, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(";");
        sb.append("c=2");
        sb.append(";");
        sb.append("r=");
        sb.append(i);
        sb.append(";");
        String m8964v = m8963w().m8964v();
        if (!TextUtils.isEmpty(m8964v)) {
            try {
                String m8723b = C6653u.m8723b(C6637g.m9089a(m8964v.getBytes("UTF-8")));
                sb.append("u=");
                sb.append(m8723b);
                sb.append("::");
                sb.append(C6637g.m9091a());
            } catch (Exception unused) {
            }
        }
        return sb.toString();
    }

    /* renamed from: k */
    public void m8988k(int i) {
        this.f17176ay = i;
    }

    /* renamed from: ap */
    public int m9030ap() {
        return this.f17176ay;
    }

    /* renamed from: p */
    public void m8971p(boolean z) {
        if (z) {
            this.f17146Y = EnumC6639a.Hybrid;
        } else {
            this.f17146Y = EnumC6639a.Native;
        }
    }

    /* renamed from: aq */
    public EnumC6639a m9029aq() {
        return this.f17146Y;
    }
}
