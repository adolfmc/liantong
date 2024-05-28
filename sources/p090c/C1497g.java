package p090c;

import android.app.Application;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import cn.ltzf.passguard.C1730a;
import java.lang.reflect.Field;
import p384d.C11795a;
import p388f.C11925b;
import p390g.C11944a;
import p390g.C11945b;
import p390g.C11946c;

/* renamed from: c.g */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class C1497g {

    /* renamed from: s */
    public static volatile C1497g f2511s;

    /* renamed from: a */
    public Application f2512a;

    /* renamed from: e */
    public int f2516e;

    /* renamed from: f */
    public float f2517f;

    /* renamed from: g */
    public int f2518g;

    /* renamed from: h */
    public int f2519h;

    /* renamed from: i */
    public int f2520i;

    /* renamed from: j */
    public int f2521j;

    /* renamed from: k */
    public int f2522k;

    /* renamed from: n */
    public boolean f2525n;

    /* renamed from: o */
    public boolean f2526o;

    /* renamed from: p */
    public boolean f2527p;

    /* renamed from: q */
    public Field f2528q;

    /* renamed from: r */
    public InterfaceC1503l f2529r;

    /* renamed from: b */
    public C11795a f2513b = new C11795a();

    /* renamed from: c */
    public C11925b f2514c = new C11925b();

    /* renamed from: d */
    public float f2515d = -1.0f;

    /* renamed from: l */
    public boolean f2523l = true;

    /* renamed from: m */
    public boolean f2524m = true;

    /*  JADX ERROR: NullPointerException in pass: MarkMethodsForInline
        java.lang.NullPointerException
        	at jadx.core.dex.instructions.args.RegisterArg.sameRegAndSVar(RegisterArg.java:173)
        	at jadx.core.dex.instructions.args.InsnArg.isSameVar(InsnArg.java:269)
        	at jadx.core.dex.visitors.MarkMethodsForInline.isSyntheticAccessPattern(MarkMethodsForInline.java:118)
        	at jadx.core.dex.visitors.MarkMethodsForInline.inlineMth(MarkMethodsForInline.java:86)
        	at jadx.core.dex.visitors.MarkMethodsForInline.process(MarkMethodsForInline.java:53)
        	at jadx.core.dex.visitors.MarkMethodsForInline.visit(MarkMethodsForInline.java:37)
        */
    /* renamed from: a */
    public static /* synthetic */ boolean m22189a(p090c.C1497g r0, boolean r1) {
        /*
            r0.getClass()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p090c.C1497g.m22189a(c.g, boolean):boolean");
    }

    /* renamed from: c */
    public static C1497g m22187c() {
        if (f2511s == null) {
            synchronized (C1497g.class) {
                if (f2511s == null) {
                    f2511s = new C1497g();
                }
            }
        }
        return f2511s;
    }

    /* renamed from: a */
    public final int m22191a() {
        C11945b.m2024a(this.f2519h > 0, "you must set design_height_in_dp  in your AndroidManifest file");
        return this.f2519h;
    }

    /* renamed from: a */
    public final C1497g m22190a(Application application) {
        int i = 0;
        C11945b.m2024a(this.f2515d == -1.0f, "AutoSizeConfig#init() can only be called once");
        C11945b.m2025a(application, "application == null");
        this.f2512a = application;
        this.f2523l = true;
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        new Thread(new RunnableC1495e(this, application)).start();
        int i2 = application.getResources().getConfiguration().orientation;
        int[] m2023a = C11946c.m2023a(application);
        this.f2520i = m2023a[0];
        this.f2521j = m2023a[1];
        try {
            int identifier = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android");
            if (identifier > 0) {
                i = Resources.getSystem().getDimensionPixelSize(identifier);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        this.f2522k = i;
        StringBuilder m22016a = C1730a.m22016a("designWidthInDp = ");
        m22016a.append(this.f2518g);
        m22016a.append(", designHeightInDp = ");
        m22016a.append(this.f2519h);
        m22016a.append(", screenWidth = ");
        m22016a.append(this.f2520i);
        m22016a.append(", screenHeight = ");
        m22016a.append(this.f2521j);
        C11944a.m2026a(m22016a.toString());
        this.f2515d = displayMetrics.density;
        int i3 = displayMetrics.densityDpi;
        this.f2516e = i3;
        this.f2517f = displayMetrics.scaledDensity;
        if (this.f2521j / this.f2520i >= 1.5d) {
            this.f2518g = 360;
        } else {
            this.f2518g = i3;
        }
        C1497g m22187c = m22187c();
        C1496f c1496f = new C1496f();
        m22187c.getClass();
        m22187c.f2529r = c1496f;
        application.registerComponentCallbacks(new ComponentCallbacksC1494d(this, application));
        C11944a.m2026a("initDensity = " + this.f2515d + ", initScaledDensity = " + this.f2517f);
        application.registerActivityLifecycleCallbacks(new C1491a(new C1502k(new C1498h())));
        if ("MiuiResources".equals(application.getResources().getClass().getSimpleName()) || "XResources".equals(application.getResources().getClass().getSimpleName())) {
            this.f2527p = true;
            try {
                Field declaredField = Resources.class.getDeclaredField("mTmpMetrics");
                this.f2528q = declaredField;
                declaredField.setAccessible(true);
            } catch (Exception unused) {
                this.f2528q = null;
            }
        }
        return this;
    }

    /* renamed from: b */
    public final int m22188b() {
        C11945b.m2024a(this.f2518g > 0, "you must set design_width_in_dp  in your AndroidManifest file");
        return this.f2518g;
    }

    /* renamed from: d */
    public final int m22186d() {
        return this.f2524m ? this.f2521j : this.f2521j - this.f2522k;
    }
}
