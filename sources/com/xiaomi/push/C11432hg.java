package com.xiaomi.push;

import com.xiaomi.push.service.C11541aj;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/* renamed from: com.xiaomi.push.hg */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11432hg implements InterfaceC11442hq<C11432hg, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public int f23102a;

    /* renamed from: a */
    public long f23103a;

    /* renamed from: a */
    public C11419gu f23104a;

    /* renamed from: a */
    public String f23105a;

    /* renamed from: a */
    public List<String> f23107a;

    /* renamed from: b */
    public int f23109b;

    /* renamed from: b */
    public long f23110b;

    /* renamed from: b */
    public String f23111b;

    /* renamed from: c */
    public long f23112c;

    /* renamed from: c */
    public String f23113c;

    /* renamed from: d */
    public String f23114d;

    /* renamed from: e */
    public String f23115e;

    /* renamed from: f */
    public String f23116f;

    /* renamed from: g */
    public String f23117g;

    /* renamed from: h */
    public String f23118h;

    /* renamed from: i */
    public String f23119i;

    /* renamed from: j */
    public String f23120j;

    /* renamed from: k */
    public String f23121k;

    /* renamed from: l */
    public String f23122l;

    /* renamed from: a */
    private static final C11461if f23082a = new C11461if("XmPushActionRegistrationResult");

    /* renamed from: a */
    private static final C11452hx f23081a = new C11452hx("", (byte) 11, 1);

    /* renamed from: b */
    private static final C11452hx f23083b = new C11452hx("", (byte) 12, 2);

    /* renamed from: c */
    private static final C11452hx f23084c = new C11452hx("", (byte) 11, 3);

    /* renamed from: d */
    private static final C11452hx f23085d = new C11452hx("", (byte) 11, 4);

    /* renamed from: e */
    private static final C11452hx f23086e = new C11452hx("", (byte) 10, 6);

    /* renamed from: f */
    private static final C11452hx f23087f = new C11452hx("", (byte) 11, 7);

    /* renamed from: g */
    private static final C11452hx f23088g = new C11452hx("", (byte) 11, 8);

    /* renamed from: h */
    private static final C11452hx f23089h = new C11452hx("", (byte) 11, 9);

    /* renamed from: i */
    private static final C11452hx f23090i = new C11452hx("", (byte) 11, 10);

    /* renamed from: j */
    private static final C11452hx f23091j = new C11452hx("", (byte) 10, 11);

    /* renamed from: k */
    private static final C11452hx f23092k = new C11452hx("", (byte) 11, 12);

    /* renamed from: l */
    private static final C11452hx f23093l = new C11452hx("", (byte) 11, 13);

    /* renamed from: m */
    private static final C11452hx f23094m = new C11452hx("", (byte) 10, 14);

    /* renamed from: n */
    private static final C11452hx f23095n = new C11452hx("", (byte) 11, 15);

    /* renamed from: o */
    private static final C11452hx f23096o = new C11452hx("", (byte) 8, 16);

    /* renamed from: p */
    private static final C11452hx f23097p = new C11452hx("", (byte) 11, 17);

    /* renamed from: q */
    private static final C11452hx f23098q = new C11452hx("", (byte) 8, 18);

    /* renamed from: r */
    private static final C11452hx f23099r = new C11452hx("", (byte) 11, 19);

    /* renamed from: s */
    private static final C11452hx f23100s = new C11452hx("", (byte) 2, 20);

    /* renamed from: t */
    private static final C11452hx f23101t = new C11452hx("", (byte) 15, 21);

    /* renamed from: a */
    private BitSet f23106a = new BitSet(6);

    /* renamed from: a */
    public boolean f23108a = false;

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public boolean m3255a() {
        return this.f23105a != null;
    }

    /* renamed from: b */
    public boolean m3250b() {
        return this.f23104a != null;
    }

    /* renamed from: a */
    public String m3258a() {
        return this.f23111b;
    }

    /* renamed from: c */
    public boolean m3247c() {
        return this.f23111b != null;
    }

    /* renamed from: d */
    public boolean m3245d() {
        return this.f23113c != null;
    }

    /* renamed from: a */
    public long m3259a() {
        return this.f23103a;
    }

    /* renamed from: e */
    public boolean m3243e() {
        return this.f23106a.get(0);
    }

    /* renamed from: a */
    public void m3252a(boolean z) {
        this.f23106a.set(0, z);
    }

    /* renamed from: f */
    public boolean m3241f() {
        return this.f23114d != null;
    }

    /* renamed from: g */
    public boolean m3239g() {
        return this.f23115e != null;
    }

    /* renamed from: b */
    public String m3251b() {
        return this.f23116f;
    }

    /* renamed from: h */
    public boolean m3238h() {
        return this.f23116f != null;
    }

    /* renamed from: c */
    public String m3248c() {
        return this.f23117g;
    }

    /* renamed from: i */
    public boolean m3237i() {
        return this.f23117g != null;
    }

    /* renamed from: j */
    public boolean m3236j() {
        return this.f23106a.get(1);
    }

    /* renamed from: b */
    public void m3249b(boolean z) {
        this.f23106a.set(1, z);
    }

    /* renamed from: k */
    public boolean m3235k() {
        return this.f23118h != null;
    }

    /* renamed from: l */
    public boolean m3234l() {
        return this.f23119i != null;
    }

    /* renamed from: m */
    public boolean m3233m() {
        return this.f23106a.get(2);
    }

    /* renamed from: c */
    public void m3246c(boolean z) {
        this.f23106a.set(2, z);
    }

    /* renamed from: n */
    public boolean m3232n() {
        return this.f23120j != null;
    }

    /* renamed from: o */
    public boolean m3231o() {
        return this.f23106a.get(3);
    }

    /* renamed from: d */
    public void m3244d(boolean z) {
        this.f23106a.set(3, z);
    }

    /* renamed from: p */
    public boolean m3230p() {
        return this.f23121k != null;
    }

    /* renamed from: q */
    public boolean m3229q() {
        return this.f23106a.get(4);
    }

    /* renamed from: e */
    public void m3242e(boolean z) {
        this.f23106a.set(4, z);
    }

    /* renamed from: r */
    public boolean m3228r() {
        return this.f23122l != null;
    }

    /* renamed from: s */
    public boolean m3227s() {
        return this.f23106a.get(5);
    }

    /* renamed from: f */
    public void m3240f(boolean z) {
        this.f23106a.set(5, z);
    }

    /* renamed from: a */
    public List<String> m3257a() {
        return this.f23107a;
    }

    /* renamed from: t */
    public boolean m3226t() {
        return this.f23107a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11432hg)) {
            return m3253a((C11432hg) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3253a(C11432hg c11432hg) {
        if (c11432hg == null) {
            return false;
        }
        boolean m3255a = m3255a();
        boolean m3255a2 = c11432hg.m3255a();
        if ((m3255a || m3255a2) && !(m3255a && m3255a2 && this.f23105a.equals(c11432hg.f23105a))) {
            return false;
        }
        boolean m3250b = m3250b();
        boolean m3250b2 = c11432hg.m3250b();
        if ((m3250b || m3250b2) && !(m3250b && m3250b2 && this.f23104a.m3514a(c11432hg.f23104a))) {
            return false;
        }
        boolean m3247c = m3247c();
        boolean m3247c2 = c11432hg.m3247c();
        if ((m3247c || m3247c2) && !(m3247c && m3247c2 && this.f23111b.equals(c11432hg.f23111b))) {
            return false;
        }
        boolean m3245d = m3245d();
        boolean m3245d2 = c11432hg.m3245d();
        if (((m3245d || m3245d2) && !(m3245d && m3245d2 && this.f23113c.equals(c11432hg.f23113c))) || this.f23103a != c11432hg.f23103a) {
            return false;
        }
        boolean m3241f = m3241f();
        boolean m3241f2 = c11432hg.m3241f();
        if ((m3241f || m3241f2) && !(m3241f && m3241f2 && this.f23114d.equals(c11432hg.f23114d))) {
            return false;
        }
        boolean m3239g = m3239g();
        boolean m3239g2 = c11432hg.m3239g();
        if ((m3239g || m3239g2) && !(m3239g && m3239g2 && this.f23115e.equals(c11432hg.f23115e))) {
            return false;
        }
        boolean m3238h = m3238h();
        boolean m3238h2 = c11432hg.m3238h();
        if ((m3238h || m3238h2) && !(m3238h && m3238h2 && this.f23116f.equals(c11432hg.f23116f))) {
            return false;
        }
        boolean m3237i = m3237i();
        boolean m3237i2 = c11432hg.m3237i();
        if ((m3237i || m3237i2) && !(m3237i && m3237i2 && this.f23117g.equals(c11432hg.f23117g))) {
            return false;
        }
        boolean m3236j = m3236j();
        boolean m3236j2 = c11432hg.m3236j();
        if ((m3236j || m3236j2) && !(m3236j && m3236j2 && this.f23110b == c11432hg.f23110b)) {
            return false;
        }
        boolean m3235k = m3235k();
        boolean m3235k2 = c11432hg.m3235k();
        if ((m3235k || m3235k2) && !(m3235k && m3235k2 && this.f23118h.equals(c11432hg.f23118h))) {
            return false;
        }
        boolean m3234l = m3234l();
        boolean m3234l2 = c11432hg.m3234l();
        if ((m3234l || m3234l2) && !(m3234l && m3234l2 && this.f23119i.equals(c11432hg.f23119i))) {
            return false;
        }
        boolean m3233m = m3233m();
        boolean m3233m2 = c11432hg.m3233m();
        if ((m3233m || m3233m2) && !(m3233m && m3233m2 && this.f23112c == c11432hg.f23112c)) {
            return false;
        }
        boolean m3232n = m3232n();
        boolean m3232n2 = c11432hg.m3232n();
        if ((m3232n || m3232n2) && !(m3232n && m3232n2 && this.f23120j.equals(c11432hg.f23120j))) {
            return false;
        }
        boolean m3231o = m3231o();
        boolean m3231o2 = c11432hg.m3231o();
        if ((m3231o || m3231o2) && !(m3231o && m3231o2 && this.f23102a == c11432hg.f23102a)) {
            return false;
        }
        boolean m3230p = m3230p();
        boolean m3230p2 = c11432hg.m3230p();
        if ((m3230p || m3230p2) && !(m3230p && m3230p2 && this.f23121k.equals(c11432hg.f23121k))) {
            return false;
        }
        boolean m3229q = m3229q();
        boolean m3229q2 = c11432hg.m3229q();
        if ((m3229q || m3229q2) && !(m3229q && m3229q2 && this.f23109b == c11432hg.f23109b)) {
            return false;
        }
        boolean m3228r = m3228r();
        boolean m3228r2 = c11432hg.m3228r();
        if ((m3228r || m3228r2) && !(m3228r && m3228r2 && this.f23122l.equals(c11432hg.f23122l))) {
            return false;
        }
        boolean m3227s = m3227s();
        boolean m3227s2 = c11432hg.m3227s();
        if ((m3227s || m3227s2) && !(m3227s && m3227s2 && this.f23108a == c11432hg.f23108a)) {
            return false;
        }
        boolean m3226t = m3226t();
        boolean m3226t2 = c11432hg.m3226t();
        if (m3226t || m3226t2) {
            return m3226t && m3226t2 && this.f23107a.equals(c11432hg.f23107a);
        }
        return true;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11432hg c11432hg) {
        int m3070a;
        int m3066a;
        int m3076a;
        int m3079a;
        int m3076a2;
        int m3079a2;
        int m3076a3;
        int m3078a;
        int m3076a4;
        int m3076a5;
        int m3078a2;
        int m3076a6;
        int m3076a7;
        int m3076a8;
        int m3076a9;
        int m3078a3;
        int m3076a10;
        int m3076a11;
        int m3077a;
        int m3076a12;
        if (!getClass().equals(c11432hg.getClass())) {
            return getClass().getName().compareTo(c11432hg.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3255a()).compareTo(Boolean.valueOf(c11432hg.m3255a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3255a() || (m3076a12 = C11443hr.m3076a(this.f23105a, c11432hg.f23105a)) == 0) {
            int compareTo2 = Boolean.valueOf(m3250b()).compareTo(Boolean.valueOf(c11432hg.m3250b()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (!m3250b() || (m3077a = C11443hr.m3077a(this.f23104a, c11432hg.f23104a)) == 0) {
                int compareTo3 = Boolean.valueOf(m3247c()).compareTo(Boolean.valueOf(c11432hg.m3247c()));
                if (compareTo3 != 0) {
                    return compareTo3;
                }
                if (!m3247c() || (m3076a11 = C11443hr.m3076a(this.f23111b, c11432hg.f23111b)) == 0) {
                    int compareTo4 = Boolean.valueOf(m3245d()).compareTo(Boolean.valueOf(c11432hg.m3245d()));
                    if (compareTo4 != 0) {
                        return compareTo4;
                    }
                    if (!m3245d() || (m3076a10 = C11443hr.m3076a(this.f23113c, c11432hg.f23113c)) == 0) {
                        int compareTo5 = Boolean.valueOf(m3243e()).compareTo(Boolean.valueOf(c11432hg.m3243e()));
                        if (compareTo5 != 0) {
                            return compareTo5;
                        }
                        if (!m3243e() || (m3078a3 = C11443hr.m3078a(this.f23103a, c11432hg.f23103a)) == 0) {
                            int compareTo6 = Boolean.valueOf(m3241f()).compareTo(Boolean.valueOf(c11432hg.m3241f()));
                            if (compareTo6 != 0) {
                                return compareTo6;
                            }
                            if (!m3241f() || (m3076a9 = C11443hr.m3076a(this.f23114d, c11432hg.f23114d)) == 0) {
                                int compareTo7 = Boolean.valueOf(m3239g()).compareTo(Boolean.valueOf(c11432hg.m3239g()));
                                if (compareTo7 != 0) {
                                    return compareTo7;
                                }
                                if (!m3239g() || (m3076a8 = C11443hr.m3076a(this.f23115e, c11432hg.f23115e)) == 0) {
                                    int compareTo8 = Boolean.valueOf(m3238h()).compareTo(Boolean.valueOf(c11432hg.m3238h()));
                                    if (compareTo8 != 0) {
                                        return compareTo8;
                                    }
                                    if (!m3238h() || (m3076a7 = C11443hr.m3076a(this.f23116f, c11432hg.f23116f)) == 0) {
                                        int compareTo9 = Boolean.valueOf(m3237i()).compareTo(Boolean.valueOf(c11432hg.m3237i()));
                                        if (compareTo9 != 0) {
                                            return compareTo9;
                                        }
                                        if (!m3237i() || (m3076a6 = C11443hr.m3076a(this.f23117g, c11432hg.f23117g)) == 0) {
                                            int compareTo10 = Boolean.valueOf(m3236j()).compareTo(Boolean.valueOf(c11432hg.m3236j()));
                                            if (compareTo10 != 0) {
                                                return compareTo10;
                                            }
                                            if (!m3236j() || (m3078a2 = C11443hr.m3078a(this.f23110b, c11432hg.f23110b)) == 0) {
                                                int compareTo11 = Boolean.valueOf(m3235k()).compareTo(Boolean.valueOf(c11432hg.m3235k()));
                                                if (compareTo11 != 0) {
                                                    return compareTo11;
                                                }
                                                if (!m3235k() || (m3076a5 = C11443hr.m3076a(this.f23118h, c11432hg.f23118h)) == 0) {
                                                    int compareTo12 = Boolean.valueOf(m3234l()).compareTo(Boolean.valueOf(c11432hg.m3234l()));
                                                    if (compareTo12 != 0) {
                                                        return compareTo12;
                                                    }
                                                    if (!m3234l() || (m3076a4 = C11443hr.m3076a(this.f23119i, c11432hg.f23119i)) == 0) {
                                                        int compareTo13 = Boolean.valueOf(m3233m()).compareTo(Boolean.valueOf(c11432hg.m3233m()));
                                                        if (compareTo13 != 0) {
                                                            return compareTo13;
                                                        }
                                                        if (!m3233m() || (m3078a = C11443hr.m3078a(this.f23112c, c11432hg.f23112c)) == 0) {
                                                            int compareTo14 = Boolean.valueOf(m3232n()).compareTo(Boolean.valueOf(c11432hg.m3232n()));
                                                            if (compareTo14 != 0) {
                                                                return compareTo14;
                                                            }
                                                            if (!m3232n() || (m3076a3 = C11443hr.m3076a(this.f23120j, c11432hg.f23120j)) == 0) {
                                                                int compareTo15 = Boolean.valueOf(m3231o()).compareTo(Boolean.valueOf(c11432hg.m3231o()));
                                                                if (compareTo15 != 0) {
                                                                    return compareTo15;
                                                                }
                                                                if (!m3231o() || (m3079a2 = C11443hr.m3079a(this.f23102a, c11432hg.f23102a)) == 0) {
                                                                    int compareTo16 = Boolean.valueOf(m3230p()).compareTo(Boolean.valueOf(c11432hg.m3230p()));
                                                                    if (compareTo16 != 0) {
                                                                        return compareTo16;
                                                                    }
                                                                    if (!m3230p() || (m3076a2 = C11443hr.m3076a(this.f23121k, c11432hg.f23121k)) == 0) {
                                                                        int compareTo17 = Boolean.valueOf(m3229q()).compareTo(Boolean.valueOf(c11432hg.m3229q()));
                                                                        if (compareTo17 != 0) {
                                                                            return compareTo17;
                                                                        }
                                                                        if (!m3229q() || (m3079a = C11443hr.m3079a(this.f23109b, c11432hg.f23109b)) == 0) {
                                                                            int compareTo18 = Boolean.valueOf(m3228r()).compareTo(Boolean.valueOf(c11432hg.m3228r()));
                                                                            if (compareTo18 != 0) {
                                                                                return compareTo18;
                                                                            }
                                                                            if (!m3228r() || (m3076a = C11443hr.m3076a(this.f23122l, c11432hg.f23122l)) == 0) {
                                                                                int compareTo19 = Boolean.valueOf(m3227s()).compareTo(Boolean.valueOf(c11432hg.m3227s()));
                                                                                if (compareTo19 != 0) {
                                                                                    return compareTo19;
                                                                                }
                                                                                if (!m3227s() || (m3066a = C11443hr.m3066a(this.f23108a, c11432hg.f23108a)) == 0) {
                                                                                    int compareTo20 = Boolean.valueOf(m3226t()).compareTo(Boolean.valueOf(c11432hg.m3226t()));
                                                                                    if (compareTo20 != 0) {
                                                                                        return compareTo20;
                                                                                    }
                                                                                    if (!m3226t() || (m3070a = C11443hr.m3070a(this.f23107a, c11432hg.f23107a)) == 0) {
                                                                                        return 0;
                                                                                    }
                                                                                    return m3070a;
                                                                                }
                                                                                return m3066a;
                                                                            }
                                                                            return m3076a;
                                                                        }
                                                                        return m3079a;
                                                                    }
                                                                    return m3076a2;
                                                                }
                                                                return m3079a2;
                                                            }
                                                            return m3076a3;
                                                        }
                                                        return m3078a;
                                                    }
                                                    return m3076a4;
                                                }
                                                return m3076a5;
                                            }
                                            return m3078a2;
                                        }
                                        return m3076a6;
                                    }
                                    return m3076a7;
                                }
                                return m3076a8;
                            }
                            return m3076a9;
                        }
                        return m3078a3;
                    }
                    return m3076a10;
                }
                return m3076a11;
            }
            return m3077a;
        }
        return m3076a12;
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: a */
    public void mo3083a(AbstractC11456ia abstractC11456ia) {
        abstractC11456ia.mo3020a();
        while (true) {
            C11452hx mo3021a = abstractC11456ia.mo3021a();
            if (mo3021a.f23307a != 0) {
                switch (mo3021a.f23309a) {
                    case 1:
                        if (mo3021a.f23307a == 11) {
                            this.f23105a = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 2:
                        if (mo3021a.f23307a == 12) {
                            this.f23104a = new C11419gu();
                            this.f23104a.mo3083a(abstractC11456ia);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 3:
                        if (mo3021a.f23307a == 11) {
                            this.f23111b = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 4:
                        if (mo3021a.f23307a == 11) {
                            this.f23113c = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 5:
                    default:
                        C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                        break;
                    case 6:
                        if (mo3021a.f23307a == 10) {
                            this.f23103a = abstractC11456ia.mo3022a();
                            m3252a(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 7:
                        if (mo3021a.f23307a == 11) {
                            this.f23114d = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 8:
                        if (mo3021a.f23307a == 11) {
                            this.f23115e = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 9:
                        if (mo3021a.f23307a == 11) {
                            this.f23116f = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 10:
                        if (mo3021a.f23307a == 11) {
                            this.f23117g = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 11:
                        if (mo3021a.f23307a == 10) {
                            this.f23110b = abstractC11456ia.mo3022a();
                            m3249b(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 12:
                        if (mo3021a.f23307a == 11) {
                            this.f23118h = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 13:
                        if (mo3021a.f23307a == 11) {
                            this.f23119i = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 14:
                        if (mo3021a.f23307a == 10) {
                            this.f23112c = abstractC11456ia.mo3022a();
                            m3246c(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 15:
                        if (mo3021a.f23307a == 11) {
                            this.f23120j = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 16:
                        if (mo3021a.f23307a == 8) {
                            this.f23102a = abstractC11456ia.mo3023a();
                            m3244d(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 17:
                        if (mo3021a.f23307a == 11) {
                            this.f23121k = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 18:
                        if (mo3021a.f23307a == 8) {
                            this.f23109b = abstractC11456ia.mo3023a();
                            m3242e(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 19:
                        if (mo3021a.f23307a == 11) {
                            this.f23122l = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 20:
                        if (mo3021a.f23307a == 2) {
                            this.f23108a = abstractC11456ia.mo3017a();
                            m3240f(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 21:
                        if (mo3021a.f23307a == 15) {
                            C11453hy mo2993a = abstractC11456ia.mo2993a();
                            this.f23107a = new ArrayList(mo2993a.f23311a);
                            for (int i = 0; i < mo2993a.f23311a; i++) {
                                this.f23107a.add(abstractC11456ia.mo2990a());
                            }
                            abstractC11456ia.mo2998i();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                }
                abstractC11456ia.mo3000g();
            } else {
                abstractC11456ia.mo3001f();
                if (!m3243e()) {
                    throw new C11457ib("Required field 'errorCode' was not found in serialized data! Struct: " + toString());
                }
                m3256a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3256a();
        abstractC11456ia.mo3010a(f23082a);
        if (this.f23105a != null && m3255a()) {
            abstractC11456ia.mo3013a(f23081a);
            abstractC11456ia.mo3009a(this.f23105a);
            abstractC11456ia.mo3005b();
        }
        if (this.f23104a != null && m3250b()) {
            abstractC11456ia.mo3013a(f23083b);
            this.f23104a.mo3082b(abstractC11456ia);
            abstractC11456ia.mo3005b();
        }
        if (this.f23111b != null) {
            abstractC11456ia.mo3013a(f23084c);
            abstractC11456ia.mo3009a(this.f23111b);
            abstractC11456ia.mo3005b();
        }
        if (this.f23113c != null) {
            abstractC11456ia.mo3013a(f23085d);
            abstractC11456ia.mo3009a(this.f23113c);
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3013a(f23086e);
        abstractC11456ia.mo3014a(this.f23103a);
        abstractC11456ia.mo3005b();
        if (this.f23114d != null && m3241f()) {
            abstractC11456ia.mo3013a(f23087f);
            abstractC11456ia.mo3009a(this.f23114d);
            abstractC11456ia.mo3005b();
        }
        if (this.f23115e != null && m3239g()) {
            abstractC11456ia.mo3013a(f23088g);
            abstractC11456ia.mo3009a(this.f23115e);
            abstractC11456ia.mo3005b();
        }
        if (this.f23116f != null && m3238h()) {
            abstractC11456ia.mo3013a(f23089h);
            abstractC11456ia.mo3009a(this.f23116f);
            abstractC11456ia.mo3005b();
        }
        if (this.f23117g != null && m3237i()) {
            abstractC11456ia.mo3013a(f23090i);
            abstractC11456ia.mo3009a(this.f23117g);
            abstractC11456ia.mo3005b();
        }
        if (m3236j()) {
            abstractC11456ia.mo3013a(f23091j);
            abstractC11456ia.mo3014a(this.f23110b);
            abstractC11456ia.mo3005b();
        }
        if (this.f23118h != null && m3235k()) {
            abstractC11456ia.mo3013a(f23092k);
            abstractC11456ia.mo3009a(this.f23118h);
            abstractC11456ia.mo3005b();
        }
        if (this.f23119i != null && m3234l()) {
            abstractC11456ia.mo3013a(f23093l);
            abstractC11456ia.mo3009a(this.f23119i);
            abstractC11456ia.mo3005b();
        }
        if (m3233m()) {
            abstractC11456ia.mo3013a(f23094m);
            abstractC11456ia.mo3014a(this.f23112c);
            abstractC11456ia.mo3005b();
        }
        if (this.f23120j != null && m3232n()) {
            abstractC11456ia.mo3013a(f23095n);
            abstractC11456ia.mo3009a(this.f23120j);
            abstractC11456ia.mo3005b();
        }
        if (m3231o()) {
            abstractC11456ia.mo3013a(f23096o);
            abstractC11456ia.mo3015a(this.f23102a);
            abstractC11456ia.mo3005b();
        }
        if (this.f23121k != null && m3230p()) {
            abstractC11456ia.mo3013a(f23097p);
            abstractC11456ia.mo3009a(this.f23121k);
            abstractC11456ia.mo3005b();
        }
        if (m3229q()) {
            abstractC11456ia.mo3013a(f23098q);
            abstractC11456ia.mo3015a(this.f23109b);
            abstractC11456ia.mo3005b();
        }
        if (this.f23122l != null && m3228r()) {
            abstractC11456ia.mo3013a(f23099r);
            abstractC11456ia.mo3009a(this.f23122l);
            abstractC11456ia.mo3005b();
        }
        if (m3227s()) {
            abstractC11456ia.mo3013a(f23100s);
            abstractC11456ia.mo3006a(this.f23108a);
            abstractC11456ia.mo3005b();
        }
        if (this.f23107a != null && m3226t()) {
            abstractC11456ia.mo3013a(f23101t);
            abstractC11456ia.mo3012a(new C11453hy((byte) 11, this.f23107a.size()));
            for (String str : this.f23107a) {
                abstractC11456ia.mo3009a(str);
            }
            abstractC11456ia.mo3002e();
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionRegistrationResult(");
        if (m3255a()) {
            sb.append("debug:");
            String str = this.f23105a;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (m3250b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            C11419gu c11419gu = this.f23104a;
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
        String str2 = this.f23111b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(C11541aj.m2702a(str2));
        }
        sb.append(", ");
        sb.append("appId:");
        String str3 = this.f23113c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("errorCode:");
        sb.append(this.f23103a);
        if (m3241f()) {
            sb.append(", ");
            sb.append("reason:");
            String str4 = this.f23114d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m3239g()) {
            sb.append(", ");
            sb.append("regId:");
            String str5 = this.f23115e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m3237i()) {
            sb.append(", ");
            sb.append("packageName:");
            String str6 = this.f23117g;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
        }
        if (m3236j()) {
            sb.append(", ");
            sb.append("registeredAt:");
            sb.append(this.f23110b);
        }
        if (m3235k()) {
            sb.append(", ");
            sb.append("aliasName:");
            String str7 = this.f23118h;
            if (str7 == null) {
                sb.append("null");
            } else {
                sb.append(str7);
            }
        }
        if (m3234l()) {
            sb.append(", ");
            sb.append("clientId:");
            String str8 = this.f23119i;
            if (str8 == null) {
                sb.append("null");
            } else {
                sb.append(str8);
            }
        }
        if (m3233m()) {
            sb.append(", ");
            sb.append("costTime:");
            sb.append(this.f23112c);
        }
        if (m3232n()) {
            sb.append(", ");
            sb.append("appVersion:");
            String str9 = this.f23120j;
            if (str9 == null) {
                sb.append("null");
            } else {
                sb.append(str9);
            }
        }
        if (m3231o()) {
            sb.append(", ");
            sb.append("pushSdkVersionCode:");
            sb.append(this.f23102a);
        }
        if (m3230p()) {
            sb.append(", ");
            sb.append("hybridPushEndpoint:");
            String str10 = this.f23121k;
            if (str10 == null) {
                sb.append("null");
            } else {
                sb.append(str10);
            }
        }
        if (m3229q()) {
            sb.append(", ");
            sb.append("appVersionCode:");
            sb.append(this.f23109b);
        }
        if (m3228r()) {
            sb.append(", ");
            sb.append("region:");
            String str11 = this.f23122l;
            if (str11 == null) {
                sb.append("null");
            } else {
                sb.append(str11);
            }
        }
        if (m3227s()) {
            sb.append(", ");
            sb.append("isHybridFrame:");
            sb.append(this.f23108a);
        }
        if (m3226t()) {
            sb.append(", ");
            sb.append("autoMarkPkgs:");
            List<String> list = this.f23107a;
            if (list == null) {
                sb.append("null");
            } else {
                sb.append(list);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    public void m3256a() {
        if (this.f23111b == null) {
            throw new C11457ib("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f23113c != null) {
        } else {
            throw new C11457ib("Required field 'appId' was not present! Struct: " + toString());
        }
    }
}
