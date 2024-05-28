package com.xiaomi.push;

import com.xiaomi.push.service.C11541aj;
import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* renamed from: com.xiaomi.push.hf */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11431hf implements InterfaceC11442hq<C11431hf, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public int f23051a;

    /* renamed from: a */
    public long f23052a;

    /* renamed from: a */
    public EnumC11418gt f23053a;

    /* renamed from: a */
    public C11419gu f23054a;

    /* renamed from: a */
    public String f23055a;

    /* renamed from: a */
    public Map<String, String> f23057a;

    /* renamed from: b */
    public int f23059b;

    /* renamed from: b */
    public long f23060b;

    /* renamed from: b */
    public String f23061b;

    /* renamed from: c */
    public int f23063c;

    /* renamed from: c */
    public String f23064c;

    /* renamed from: d */
    public String f23066d;

    /* renamed from: e */
    public String f23067e;

    /* renamed from: f */
    public String f23068f;

    /* renamed from: g */
    public String f23069g;

    /* renamed from: h */
    public String f23070h;

    /* renamed from: i */
    public String f23071i;

    /* renamed from: j */
    public String f23072j;

    /* renamed from: k */
    public String f23073k;

    /* renamed from: l */
    public String f23074l;

    /* renamed from: m */
    public String f23075m;

    /* renamed from: n */
    public String f23076n;

    /* renamed from: o */
    public String f23077o;

    /* renamed from: p */
    public String f23078p;

    /* renamed from: q */
    public String f23079q;

    /* renamed from: r */
    public String f23080r;

    /* renamed from: a */
    private static final C11461if f23025a = new C11461if("XmPushActionRegistration");

    /* renamed from: a */
    private static final C11452hx f23024a = new C11452hx("", (byte) 11, 1);

    /* renamed from: b */
    private static final C11452hx f23026b = new C11452hx("", (byte) 12, 2);

    /* renamed from: c */
    private static final C11452hx f23027c = new C11452hx("", (byte) 11, 3);

    /* renamed from: d */
    private static final C11452hx f23028d = new C11452hx("", (byte) 11, 4);

    /* renamed from: e */
    private static final C11452hx f23029e = new C11452hx("", (byte) 11, 5);

    /* renamed from: f */
    private static final C11452hx f23030f = new C11452hx("", (byte) 11, 6);

    /* renamed from: g */
    private static final C11452hx f23031g = new C11452hx("", (byte) 11, 7);

    /* renamed from: h */
    private static final C11452hx f23032h = new C11452hx("", (byte) 11, 8);

    /* renamed from: i */
    private static final C11452hx f23033i = new C11452hx("", (byte) 11, 9);

    /* renamed from: j */
    private static final C11452hx f23034j = new C11452hx("", (byte) 11, 10);

    /* renamed from: k */
    private static final C11452hx f23035k = new C11452hx("", (byte) 11, 11);

    /* renamed from: l */
    private static final C11452hx f23036l = new C11452hx("", (byte) 11, 12);

    /* renamed from: m */
    private static final C11452hx f23037m = new C11452hx("", (byte) 8, 13);

    /* renamed from: n */
    private static final C11452hx f23038n = new C11452hx("", (byte) 8, 14);

    /* renamed from: o */
    private static final C11452hx f23039o = new C11452hx("", (byte) 11, 15);

    /* renamed from: p */
    private static final C11452hx f23040p = new C11452hx("", (byte) 11, 16);

    /* renamed from: q */
    private static final C11452hx f23041q = new C11452hx("", (byte) 11, 17);

    /* renamed from: r */
    private static final C11452hx f23042r = new C11452hx("", (byte) 11, 18);

    /* renamed from: s */
    private static final C11452hx f23043s = new C11452hx("", (byte) 8, 19);

    /* renamed from: t */
    private static final C11452hx f23044t = new C11452hx("", (byte) 8, 20);

    /* renamed from: u */
    private static final C11452hx f23045u = new C11452hx("", (byte) 2, 21);

    /* renamed from: v */
    private static final C11452hx f23046v = new C11452hx("", (byte) 10, 22);

    /* renamed from: w */
    private static final C11452hx f23047w = new C11452hx("", (byte) 10, 23);

    /* renamed from: x */
    private static final C11452hx f23048x = new C11452hx("", (byte) 11, 24);

    /* renamed from: y */
    private static final C11452hx f23049y = new C11452hx("", (byte) 11, 25);

    /* renamed from: z */
    private static final C11452hx f23050z = new C11452hx("", (byte) 2, 26);

    /* renamed from: A */
    private static final C11452hx f23021A = new C11452hx("", (byte) 13, 100);

    /* renamed from: B */
    private static final C11452hx f23022B = new C11452hx("", (byte) 2, 101);

    /* renamed from: C */
    private static final C11452hx f23023C = new C11452hx("", (byte) 11, 102);

    /* renamed from: a */
    private BitSet f23056a = new BitSet(8);

    /* renamed from: a */
    public boolean f23058a = true;

    /* renamed from: c */
    public boolean f23065c = false;

    /* renamed from: b */
    public boolean f23062b = false;

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x03fc: APUT  (r178 I:short[] A[IMMUTABLE_TYPE]), (r0 I:??[int, short, byte, char]), (r13 I:short A[IMMUTABLE_TYPE]), expected to be less than 6
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(com.xiaomi.push.C11431hf r5) {
        /*
            Method dump skipped, instructions count: 1197
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C11431hf.compareTo(com.xiaomi.push.hf):int");
    }

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public boolean m3310a() {
        return this.f23055a != null;
    }

    /* renamed from: b */
    public boolean m3302b() {
        return this.f23054a != null;
    }

    /* renamed from: a */
    public String m3312a() {
        return this.f23061b;
    }

    /* renamed from: a */
    public C11431hf m3305a(String str) {
        this.f23061b = str;
        return this;
    }

    /* renamed from: c */
    public boolean m3297c() {
        return this.f23061b != null;
    }

    /* renamed from: b */
    public String m3303b() {
        return this.f23064c;
    }

    /* renamed from: b */
    public C11431hf m3300b(String str) {
        this.f23064c = str;
        return this;
    }

    /* renamed from: d */
    public boolean m3293d() {
        return this.f23064c != null;
    }

    /* renamed from: c */
    public C11431hf m3295c(String str) {
        this.f23066d = str;
        return this;
    }

    /* renamed from: e */
    public boolean m3290e() {
        return this.f23066d != null;
    }

    /* renamed from: d */
    public C11431hf m3292d(String str) {
        this.f23067e = str;
        return this;
    }

    /* renamed from: f */
    public boolean m3287f() {
        return this.f23067e != null;
    }

    /* renamed from: c */
    public String m3298c() {
        return this.f23068f;
    }

    /* renamed from: e */
    public C11431hf m3289e(String str) {
        this.f23068f = str;
        return this;
    }

    /* renamed from: g */
    public boolean m3284g() {
        return this.f23068f != null;
    }

    /* renamed from: f */
    public C11431hf m3286f(String str) {
        this.f23069g = str;
        return this;
    }

    /* renamed from: h */
    public boolean m3281h() {
        return this.f23069g != null;
    }

    /* renamed from: g */
    public C11431hf m3283g(String str) {
        this.f23070h = str;
        return this;
    }

    /* renamed from: i */
    public boolean m3278i() {
        return this.f23070h != null;
    }

    /* renamed from: j */
    public boolean m3276j() {
        return this.f23071i != null;
    }

    /* renamed from: k */
    public boolean m3275k() {
        return this.f23072j != null;
    }

    /* renamed from: h */
    public C11431hf m3280h(String str) {
        this.f23073k = str;
        return this;
    }

    /* renamed from: l */
    public boolean m3274l() {
        return this.f23073k != null;
    }

    /* renamed from: a */
    public C11431hf m3309a(int i) {
        this.f23051a = i;
        m3304a(true);
        return this;
    }

    /* renamed from: m */
    public boolean m3273m() {
        return this.f23056a.get(0);
    }

    /* renamed from: a */
    public void m3304a(boolean z) {
        this.f23056a.set(0, z);
    }

    /* renamed from: b */
    public C11431hf m3301b(int i) {
        this.f23059b = i;
        m3299b(true);
        return this;
    }

    /* renamed from: n */
    public boolean m3272n() {
        return this.f23056a.get(1);
    }

    /* renamed from: b */
    public void m3299b(boolean z) {
        this.f23056a.set(1, z);
    }

    /* renamed from: o */
    public boolean m3271o() {
        return this.f23074l != null;
    }

    /* renamed from: p */
    public boolean m3270p() {
        return this.f23075m != null;
    }

    /* renamed from: q */
    public boolean m3269q() {
        return this.f23076n != null;
    }

    /* renamed from: i */
    public C11431hf m3277i(String str) {
        this.f23077o = str;
        return this;
    }

    /* renamed from: r */
    public boolean m3268r() {
        return this.f23077o != null;
    }

    /* renamed from: c */
    public C11431hf m3296c(int i) {
        this.f23063c = i;
        m3294c(true);
        return this;
    }

    /* renamed from: s */
    public boolean m3267s() {
        return this.f23056a.get(2);
    }

    /* renamed from: c */
    public void m3294c(boolean z) {
        this.f23056a.set(2, z);
    }

    /* renamed from: a */
    public C11431hf m3308a(EnumC11418gt enumC11418gt) {
        this.f23053a = enumC11418gt;
        return this;
    }

    /* renamed from: t */
    public boolean m3266t() {
        return this.f23053a != null;
    }

    /* renamed from: u */
    public boolean m3265u() {
        return this.f23056a.get(3);
    }

    /* renamed from: d */
    public void m3291d(boolean z) {
        this.f23056a.set(3, z);
    }

    /* renamed from: v */
    public boolean m3264v() {
        return this.f23056a.get(4);
    }

    /* renamed from: e */
    public void m3288e(boolean z) {
        this.f23056a.set(4, z);
    }

    /* renamed from: w */
    public boolean m3263w() {
        return this.f23056a.get(5);
    }

    /* renamed from: f */
    public void m3285f(boolean z) {
        this.f23056a.set(5, z);
    }

    /* renamed from: x */
    public boolean m3262x() {
        return this.f23078p != null;
    }

    /* renamed from: y */
    public boolean m3261y() {
        return this.f23079q != null;
    }

    /* renamed from: z */
    public boolean m3260z() {
        return this.f23056a.get(6);
    }

    /* renamed from: g */
    public void m3282g(boolean z) {
        this.f23056a.set(6, z);
    }

    /* renamed from: A */
    public boolean m3315A() {
        return this.f23057a != null;
    }

    /* renamed from: B */
    public boolean m3314B() {
        return this.f23056a.get(7);
    }

    /* renamed from: h */
    public void m3279h(boolean z) {
        this.f23056a.set(7, z);
    }

    /* renamed from: C */
    public boolean m3313C() {
        return this.f23080r != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11431hf)) {
            return m3306a((C11431hf) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3306a(C11431hf c11431hf) {
        if (c11431hf == null) {
            return false;
        }
        boolean m3310a = m3310a();
        boolean m3310a2 = c11431hf.m3310a();
        if ((m3310a || m3310a2) && !(m3310a && m3310a2 && this.f23055a.equals(c11431hf.f23055a))) {
            return false;
        }
        boolean m3302b = m3302b();
        boolean m3302b2 = c11431hf.m3302b();
        if ((m3302b || m3302b2) && !(m3302b && m3302b2 && this.f23054a.m3514a(c11431hf.f23054a))) {
            return false;
        }
        boolean m3297c = m3297c();
        boolean m3297c2 = c11431hf.m3297c();
        if ((m3297c || m3297c2) && !(m3297c && m3297c2 && this.f23061b.equals(c11431hf.f23061b))) {
            return false;
        }
        boolean m3293d = m3293d();
        boolean m3293d2 = c11431hf.m3293d();
        if ((m3293d || m3293d2) && !(m3293d && m3293d2 && this.f23064c.equals(c11431hf.f23064c))) {
            return false;
        }
        boolean m3290e = m3290e();
        boolean m3290e2 = c11431hf.m3290e();
        if ((m3290e || m3290e2) && !(m3290e && m3290e2 && this.f23066d.equals(c11431hf.f23066d))) {
            return false;
        }
        boolean m3287f = m3287f();
        boolean m3287f2 = c11431hf.m3287f();
        if ((m3287f || m3287f2) && !(m3287f && m3287f2 && this.f23067e.equals(c11431hf.f23067e))) {
            return false;
        }
        boolean m3284g = m3284g();
        boolean m3284g2 = c11431hf.m3284g();
        if ((m3284g || m3284g2) && !(m3284g && m3284g2 && this.f23068f.equals(c11431hf.f23068f))) {
            return false;
        }
        boolean m3281h = m3281h();
        boolean m3281h2 = c11431hf.m3281h();
        if ((m3281h || m3281h2) && !(m3281h && m3281h2 && this.f23069g.equals(c11431hf.f23069g))) {
            return false;
        }
        boolean m3278i = m3278i();
        boolean m3278i2 = c11431hf.m3278i();
        if ((m3278i || m3278i2) && !(m3278i && m3278i2 && this.f23070h.equals(c11431hf.f23070h))) {
            return false;
        }
        boolean m3276j = m3276j();
        boolean m3276j2 = c11431hf.m3276j();
        if ((m3276j || m3276j2) && !(m3276j && m3276j2 && this.f23071i.equals(c11431hf.f23071i))) {
            return false;
        }
        boolean m3275k = m3275k();
        boolean m3275k2 = c11431hf.m3275k();
        if ((m3275k || m3275k2) && !(m3275k && m3275k2 && this.f23072j.equals(c11431hf.f23072j))) {
            return false;
        }
        boolean m3274l = m3274l();
        boolean m3274l2 = c11431hf.m3274l();
        if ((m3274l || m3274l2) && !(m3274l && m3274l2 && this.f23073k.equals(c11431hf.f23073k))) {
            return false;
        }
        boolean m3273m = m3273m();
        boolean m3273m2 = c11431hf.m3273m();
        if ((m3273m || m3273m2) && !(m3273m && m3273m2 && this.f23051a == c11431hf.f23051a)) {
            return false;
        }
        boolean m3272n = m3272n();
        boolean m3272n2 = c11431hf.m3272n();
        if ((m3272n || m3272n2) && !(m3272n && m3272n2 && this.f23059b == c11431hf.f23059b)) {
            return false;
        }
        boolean m3271o = m3271o();
        boolean m3271o2 = c11431hf.m3271o();
        if ((m3271o || m3271o2) && !(m3271o && m3271o2 && this.f23074l.equals(c11431hf.f23074l))) {
            return false;
        }
        boolean m3270p = m3270p();
        boolean m3270p2 = c11431hf.m3270p();
        if ((m3270p || m3270p2) && !(m3270p && m3270p2 && this.f23075m.equals(c11431hf.f23075m))) {
            return false;
        }
        boolean m3269q = m3269q();
        boolean m3269q2 = c11431hf.m3269q();
        if ((m3269q || m3269q2) && !(m3269q && m3269q2 && this.f23076n.equals(c11431hf.f23076n))) {
            return false;
        }
        boolean m3268r = m3268r();
        boolean m3268r2 = c11431hf.m3268r();
        if ((m3268r || m3268r2) && !(m3268r && m3268r2 && this.f23077o.equals(c11431hf.f23077o))) {
            return false;
        }
        boolean m3267s = m3267s();
        boolean m3267s2 = c11431hf.m3267s();
        if ((m3267s || m3267s2) && !(m3267s && m3267s2 && this.f23063c == c11431hf.f23063c)) {
            return false;
        }
        boolean m3266t = m3266t();
        boolean m3266t2 = c11431hf.m3266t();
        if ((m3266t || m3266t2) && !(m3266t && m3266t2 && this.f23053a.equals(c11431hf.f23053a))) {
            return false;
        }
        boolean m3265u = m3265u();
        boolean m3265u2 = c11431hf.m3265u();
        if ((m3265u || m3265u2) && !(m3265u && m3265u2 && this.f23058a == c11431hf.f23058a)) {
            return false;
        }
        boolean m3264v = m3264v();
        boolean m3264v2 = c11431hf.m3264v();
        if ((m3264v || m3264v2) && !(m3264v && m3264v2 && this.f23052a == c11431hf.f23052a)) {
            return false;
        }
        boolean m3263w = m3263w();
        boolean m3263w2 = c11431hf.m3263w();
        if ((m3263w || m3263w2) && !(m3263w && m3263w2 && this.f23060b == c11431hf.f23060b)) {
            return false;
        }
        boolean m3262x = m3262x();
        boolean m3262x2 = c11431hf.m3262x();
        if ((m3262x || m3262x2) && !(m3262x && m3262x2 && this.f23078p.equals(c11431hf.f23078p))) {
            return false;
        }
        boolean m3261y = m3261y();
        boolean m3261y2 = c11431hf.m3261y();
        if ((m3261y || m3261y2) && !(m3261y && m3261y2 && this.f23079q.equals(c11431hf.f23079q))) {
            return false;
        }
        boolean m3260z = m3260z();
        boolean m3260z2 = c11431hf.m3260z();
        if ((m3260z || m3260z2) && !(m3260z && m3260z2 && this.f23062b == c11431hf.f23062b)) {
            return false;
        }
        boolean m3315A = m3315A();
        boolean m3315A2 = c11431hf.m3315A();
        if ((m3315A || m3315A2) && !(m3315A && m3315A2 && this.f23057a.equals(c11431hf.f23057a))) {
            return false;
        }
        boolean m3314B = m3314B();
        boolean m3314B2 = c11431hf.m3314B();
        if ((m3314B || m3314B2) && !(m3314B && m3314B2 && this.f23065c == c11431hf.f23065c)) {
            return false;
        }
        boolean m3313C = m3313C();
        boolean m3313C2 = c11431hf.m3313C();
        if (m3313C || m3313C2) {
            return m3313C && m3313C2 && this.f23080r.equals(c11431hf.f23080r);
        }
        return true;
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: a */
    public void mo3083a(AbstractC11456ia abstractC11456ia) {
        abstractC11456ia.mo3020a();
        while (true) {
            C11452hx mo3021a = abstractC11456ia.mo3021a();
            if (mo3021a.f23307a != 0) {
                short s = mo3021a.f23309a;
                switch (s) {
                    case 1:
                        if (mo3021a.f23307a == 11) {
                            this.f23055a = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 2:
                        if (mo3021a.f23307a == 12) {
                            this.f23054a = new C11419gu();
                            this.f23054a.mo3083a(abstractC11456ia);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 3:
                        if (mo3021a.f23307a == 11) {
                            this.f23061b = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 4:
                        if (mo3021a.f23307a == 11) {
                            this.f23064c = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 5:
                        if (mo3021a.f23307a == 11) {
                            this.f23066d = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 6:
                        if (mo3021a.f23307a == 11) {
                            this.f23067e = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 7:
                        if (mo3021a.f23307a == 11) {
                            this.f23068f = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 8:
                        if (mo3021a.f23307a == 11) {
                            this.f23069g = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 9:
                        if (mo3021a.f23307a == 11) {
                            this.f23070h = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 10:
                        if (mo3021a.f23307a == 11) {
                            this.f23071i = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 11:
                        if (mo3021a.f23307a == 11) {
                            this.f23072j = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 12:
                        if (mo3021a.f23307a == 11) {
                            this.f23073k = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 13:
                        if (mo3021a.f23307a == 8) {
                            this.f23051a = abstractC11456ia.mo3023a();
                            m3304a(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 14:
                        if (mo3021a.f23307a == 8) {
                            this.f23059b = abstractC11456ia.mo3023a();
                            m3299b(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 15:
                        if (mo3021a.f23307a == 11) {
                            this.f23074l = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 16:
                        if (mo3021a.f23307a == 11) {
                            this.f23075m = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 17:
                        if (mo3021a.f23307a == 11) {
                            this.f23076n = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 18:
                        if (mo3021a.f23307a == 11) {
                            this.f23077o = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 19:
                        if (mo3021a.f23307a == 8) {
                            this.f23063c = abstractC11456ia.mo3023a();
                            m3294c(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 20:
                        if (mo3021a.f23307a == 8) {
                            this.f23053a = EnumC11418gt.m3518a(abstractC11456ia.mo3023a());
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 21:
                        if (mo3021a.f23307a == 2) {
                            this.f23058a = abstractC11456ia.mo3017a();
                            m3291d(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 22:
                        if (mo3021a.f23307a == 10) {
                            this.f23052a = abstractC11456ia.mo3022a();
                            m3288e(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 23:
                        if (mo3021a.f23307a == 10) {
                            this.f23060b = abstractC11456ia.mo3022a();
                            m3285f(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 24:
                        if (mo3021a.f23307a == 11) {
                            this.f23078p = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 25:
                        if (mo3021a.f23307a == 11) {
                            this.f23079q = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 26:
                        if (mo3021a.f23307a == 2) {
                            this.f23062b = abstractC11456ia.mo3017a();
                            m3282g(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    default:
                        switch (s) {
                            case 100:
                                if (mo3021a.f23307a == 13) {
                                    C11454hz mo2992a = abstractC11456ia.mo2992a();
                                    this.f23057a = new HashMap(mo2992a.f23313a * 2);
                                    for (int i = 0; i < mo2992a.f23313a; i++) {
                                        this.f23057a.put(abstractC11456ia.mo2990a(), abstractC11456ia.mo2990a());
                                    }
                                    abstractC11456ia.mo2999h();
                                    continue;
                                } else {
                                    C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                                    break;
                                }
                            case 101:
                                if (mo3021a.f23307a == 2) {
                                    this.f23065c = abstractC11456ia.mo3017a();
                                    m3279h(true);
                                    continue;
                                } else {
                                    C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                                    break;
                                }
                            case 102:
                                if (mo3021a.f23307a == 11) {
                                    this.f23080r = abstractC11456ia.mo2990a();
                                    continue;
                                } else {
                                    C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                                    break;
                                }
                            default:
                                C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                                continue;
                        }
                }
                abstractC11456ia.mo3000g();
            } else {
                abstractC11456ia.mo3001f();
                m3311a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3311a();
        abstractC11456ia.mo3010a(f23025a);
        if (this.f23055a != null && m3310a()) {
            abstractC11456ia.mo3013a(f23024a);
            abstractC11456ia.mo3009a(this.f23055a);
            abstractC11456ia.mo3005b();
        }
        if (this.f23054a != null && m3302b()) {
            abstractC11456ia.mo3013a(f23026b);
            this.f23054a.mo3082b(abstractC11456ia);
            abstractC11456ia.mo3005b();
        }
        if (this.f23061b != null) {
            abstractC11456ia.mo3013a(f23027c);
            abstractC11456ia.mo3009a(this.f23061b);
            abstractC11456ia.mo3005b();
        }
        if (this.f23064c != null) {
            abstractC11456ia.mo3013a(f23028d);
            abstractC11456ia.mo3009a(this.f23064c);
            abstractC11456ia.mo3005b();
        }
        if (this.f23066d != null && m3290e()) {
            abstractC11456ia.mo3013a(f23029e);
            abstractC11456ia.mo3009a(this.f23066d);
            abstractC11456ia.mo3005b();
        }
        if (this.f23067e != null && m3287f()) {
            abstractC11456ia.mo3013a(f23030f);
            abstractC11456ia.mo3009a(this.f23067e);
            abstractC11456ia.mo3005b();
        }
        if (this.f23068f != null) {
            abstractC11456ia.mo3013a(f23031g);
            abstractC11456ia.mo3009a(this.f23068f);
            abstractC11456ia.mo3005b();
        }
        if (this.f23069g != null && m3281h()) {
            abstractC11456ia.mo3013a(f23032h);
            abstractC11456ia.mo3009a(this.f23069g);
            abstractC11456ia.mo3005b();
        }
        if (this.f23070h != null && m3278i()) {
            abstractC11456ia.mo3013a(f23033i);
            abstractC11456ia.mo3009a(this.f23070h);
            abstractC11456ia.mo3005b();
        }
        if (this.f23071i != null && m3276j()) {
            abstractC11456ia.mo3013a(f23034j);
            abstractC11456ia.mo3009a(this.f23071i);
            abstractC11456ia.mo3005b();
        }
        if (this.f23072j != null && m3275k()) {
            abstractC11456ia.mo3013a(f23035k);
            abstractC11456ia.mo3009a(this.f23072j);
            abstractC11456ia.mo3005b();
        }
        if (this.f23073k != null && m3274l()) {
            abstractC11456ia.mo3013a(f23036l);
            abstractC11456ia.mo3009a(this.f23073k);
            abstractC11456ia.mo3005b();
        }
        if (m3273m()) {
            abstractC11456ia.mo3013a(f23037m);
            abstractC11456ia.mo3015a(this.f23051a);
            abstractC11456ia.mo3005b();
        }
        if (m3272n()) {
            abstractC11456ia.mo3013a(f23038n);
            abstractC11456ia.mo3015a(this.f23059b);
            abstractC11456ia.mo3005b();
        }
        if (this.f23074l != null && m3271o()) {
            abstractC11456ia.mo3013a(f23039o);
            abstractC11456ia.mo3009a(this.f23074l);
            abstractC11456ia.mo3005b();
        }
        if (this.f23075m != null && m3270p()) {
            abstractC11456ia.mo3013a(f23040p);
            abstractC11456ia.mo3009a(this.f23075m);
            abstractC11456ia.mo3005b();
        }
        if (this.f23076n != null && m3269q()) {
            abstractC11456ia.mo3013a(f23041q);
            abstractC11456ia.mo3009a(this.f23076n);
            abstractC11456ia.mo3005b();
        }
        if (this.f23077o != null && m3268r()) {
            abstractC11456ia.mo3013a(f23042r);
            abstractC11456ia.mo3009a(this.f23077o);
            abstractC11456ia.mo3005b();
        }
        if (m3267s()) {
            abstractC11456ia.mo3013a(f23043s);
            abstractC11456ia.mo3015a(this.f23063c);
            abstractC11456ia.mo3005b();
        }
        if (this.f23053a != null && m3266t()) {
            abstractC11456ia.mo3013a(f23044t);
            abstractC11456ia.mo3015a(this.f23053a.m3519a());
            abstractC11456ia.mo3005b();
        }
        if (m3265u()) {
            abstractC11456ia.mo3013a(f23045u);
            abstractC11456ia.mo3006a(this.f23058a);
            abstractC11456ia.mo3005b();
        }
        if (m3264v()) {
            abstractC11456ia.mo3013a(f23046v);
            abstractC11456ia.mo3014a(this.f23052a);
            abstractC11456ia.mo3005b();
        }
        if (m3263w()) {
            abstractC11456ia.mo3013a(f23047w);
            abstractC11456ia.mo3014a(this.f23060b);
            abstractC11456ia.mo3005b();
        }
        if (this.f23078p != null && m3262x()) {
            abstractC11456ia.mo3013a(f23048x);
            abstractC11456ia.mo3009a(this.f23078p);
            abstractC11456ia.mo3005b();
        }
        if (this.f23079q != null && m3261y()) {
            abstractC11456ia.mo3013a(f23049y);
            abstractC11456ia.mo3009a(this.f23079q);
            abstractC11456ia.mo3005b();
        }
        if (m3260z()) {
            abstractC11456ia.mo3013a(f23050z);
            abstractC11456ia.mo3006a(this.f23062b);
            abstractC11456ia.mo3005b();
        }
        if (this.f23057a != null && m3315A()) {
            abstractC11456ia.mo3013a(f23021A);
            abstractC11456ia.mo3011a(new C11454hz((byte) 11, (byte) 11, this.f23057a.size()));
            for (Map.Entry<String, String> entry : this.f23057a.entrySet()) {
                abstractC11456ia.mo3009a(entry.getKey());
                abstractC11456ia.mo3009a(entry.getValue());
            }
            abstractC11456ia.mo3003d();
            abstractC11456ia.mo3005b();
        }
        if (m3314B()) {
            abstractC11456ia.mo3013a(f23022B);
            abstractC11456ia.mo3006a(this.f23065c);
            abstractC11456ia.mo3005b();
        }
        if (this.f23080r != null && m3313C()) {
            abstractC11456ia.mo3013a(f23023C);
            abstractC11456ia.mo3009a(this.f23080r);
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionRegistration(");
        if (m3310a()) {
            sb.append("debug:");
            String str = this.f23055a;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (m3302b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            C11419gu c11419gu = this.f23054a;
            if (c11419gu == null) {
                sb.append("null");
            } else {
                sb.append(c11419gu);
            }
            z = false;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        String str2 = this.f23061b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(C11541aj.m2702a(str2));
        }
        sb.append(", ");
        sb.append("appId:");
        String str3 = this.f23064c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        if (m3290e()) {
            sb.append(", ");
            sb.append("appVersion:");
            String str4 = this.f23066d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m3287f()) {
            sb.append(", ");
            sb.append("packageName:");
            String str5 = this.f23067e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        sb.append(", ");
        sb.append("token:");
        String str6 = this.f23068f;
        if (str6 == null) {
            sb.append("null");
        } else {
            sb.append(str6);
        }
        if (m3281h()) {
            sb.append(", ");
            sb.append("deviceId:");
            String str7 = this.f23069g;
            if (str7 == null) {
                sb.append("null");
            } else {
                sb.append(str7);
            }
        }
        if (m3278i()) {
            sb.append(", ");
            sb.append("aliasName:");
            String str8 = this.f23070h;
            if (str8 == null) {
                sb.append("null");
            } else {
                sb.append(str8);
            }
        }
        if (m3276j()) {
            sb.append(", ");
            sb.append("sdkVersion:");
            String str9 = this.f23071i;
            if (str9 == null) {
                sb.append("null");
            } else {
                sb.append(str9);
            }
        }
        if (m3275k()) {
            sb.append(", ");
            sb.append("regId:");
            String str10 = this.f23072j;
            if (str10 == null) {
                sb.append("null");
            } else {
                sb.append(str10);
            }
        }
        if (m3274l()) {
            sb.append(", ");
            sb.append("pushSdkVersionName:");
            String str11 = this.f23073k;
            if (str11 == null) {
                sb.append("null");
            } else {
                sb.append(str11);
            }
        }
        if (m3273m()) {
            sb.append(", ");
            sb.append("pushSdkVersionCode:");
            sb.append(this.f23051a);
        }
        if (m3272n()) {
            sb.append(", ");
            sb.append("appVersionCode:");
            sb.append(this.f23059b);
        }
        if (m3271o()) {
            sb.append(", ");
            sb.append("androidId:");
            String str12 = this.f23074l;
            if (str12 == null) {
                sb.append("null");
            } else {
                sb.append(str12);
            }
        }
        if (m3270p()) {
            sb.append(", ");
            sb.append("imei:");
            String str13 = this.f23075m;
            if (str13 == null) {
                sb.append("null");
            } else {
                sb.append(str13);
            }
        }
        if (m3269q()) {
            sb.append(", ");
            sb.append("serial:");
            String str14 = this.f23076n;
            if (str14 == null) {
                sb.append("null");
            } else {
                sb.append(str14);
            }
        }
        if (m3268r()) {
            sb.append(", ");
            sb.append("imeiMd5:");
            String str15 = this.f23077o;
            if (str15 == null) {
                sb.append("null");
            } else {
                sb.append(str15);
            }
        }
        if (m3267s()) {
            sb.append(", ");
            sb.append("spaceId:");
            sb.append(this.f23063c);
        }
        if (m3266t()) {
            sb.append(", ");
            sb.append("reason:");
            EnumC11418gt enumC11418gt = this.f23053a;
            if (enumC11418gt == null) {
                sb.append("null");
            } else {
                sb.append(enumC11418gt);
            }
        }
        if (m3265u()) {
            sb.append(", ");
            sb.append("validateToken:");
            sb.append(this.f23058a);
        }
        if (m3264v()) {
            sb.append(", ");
            sb.append("miid:");
            sb.append(this.f23052a);
        }
        if (m3263w()) {
            sb.append(", ");
            sb.append("createdTs:");
            sb.append(this.f23060b);
        }
        if (m3262x()) {
            sb.append(", ");
            sb.append("subImei:");
            String str16 = this.f23078p;
            if (str16 == null) {
                sb.append("null");
            } else {
                sb.append(str16);
            }
        }
        if (m3261y()) {
            sb.append(", ");
            sb.append("subImeiMd5:");
            String str17 = this.f23079q;
            if (str17 == null) {
                sb.append("null");
            } else {
                sb.append(str17);
            }
        }
        if (m3260z()) {
            sb.append(", ");
            sb.append("isHybridFrame:");
            sb.append(this.f23062b);
        }
        if (m3315A()) {
            sb.append(", ");
            sb.append("connectionAttrs:");
            Map<String, String> map = this.f23057a;
            if (map == null) {
                sb.append("null");
            } else {
                sb.append(map);
            }
        }
        if (m3314B()) {
            sb.append(", ");
            sb.append("cleanOldRegInfo:");
            sb.append(this.f23065c);
        }
        if (m3313C()) {
            sb.append(", ");
            sb.append("oldRegId:");
            String str18 = this.f23080r;
            if (str18 == null) {
                sb.append("null");
            } else {
                sb.append(str18);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    public void m3311a() {
        if (this.f23061b == null) {
            throw new C11457ib("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f23064c == null) {
            throw new C11457ib("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.f23068f != null) {
        } else {
            throw new C11457ib("Required field 'token' was not present! Struct: " + toString());
        }
    }
}
