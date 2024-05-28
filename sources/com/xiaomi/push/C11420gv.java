package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.xiaomi.push.gv */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11420gv implements InterfaceC11442hq<C11420gv, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public int f22868a;

    /* renamed from: a */
    public long f22869a;

    /* renamed from: a */
    public C11419gu f22870a;

    /* renamed from: a */
    public C11434hi f22871a;

    /* renamed from: a */
    public String f22872a;

    /* renamed from: a */
    public Map<String, String> f22874a;

    /* renamed from: a */
    public short f22875a;

    /* renamed from: b */
    public String f22877b;

    /* renamed from: b */
    public short f22878b;

    /* renamed from: c */
    public String f22879c;

    /* renamed from: d */
    public String f22880d;

    /* renamed from: e */
    public String f22881e;

    /* renamed from: f */
    public String f22882f;

    /* renamed from: g */
    public String f22883g;

    /* renamed from: h */
    public String f22884h;

    /* renamed from: i */
    public String f22885i;

    /* renamed from: j */
    public String f22886j;

    /* renamed from: k */
    public String f22887k;

    /* renamed from: l */
    public String f22888l;

    /* renamed from: a */
    private static final C11461if f22848a = new C11461if("XmPushActionAckMessage");

    /* renamed from: a */
    private static final C11452hx f22847a = new C11452hx("", (byte) 11, 1);

    /* renamed from: b */
    private static final C11452hx f22849b = new C11452hx("", (byte) 12, 2);

    /* renamed from: c */
    private static final C11452hx f22850c = new C11452hx("", (byte) 11, 3);

    /* renamed from: d */
    private static final C11452hx f22851d = new C11452hx("", (byte) 11, 4);

    /* renamed from: e */
    private static final C11452hx f22852e = new C11452hx("", (byte) 10, 5);

    /* renamed from: f */
    private static final C11452hx f22853f = new C11452hx("", (byte) 11, 6);

    /* renamed from: g */
    private static final C11452hx f22854g = new C11452hx("", (byte) 11, 7);

    /* renamed from: h */
    private static final C11452hx f22855h = new C11452hx("", (byte) 12, 8);

    /* renamed from: i */
    private static final C11452hx f22856i = new C11452hx("", (byte) 11, 9);

    /* renamed from: j */
    private static final C11452hx f22857j = new C11452hx("", (byte) 11, 10);

    /* renamed from: k */
    private static final C11452hx f22858k = new C11452hx("", (byte) 2, 11);

    /* renamed from: l */
    private static final C11452hx f22859l = new C11452hx("", (byte) 11, 12);

    /* renamed from: m */
    private static final C11452hx f22860m = new C11452hx("", (byte) 11, 13);

    /* renamed from: n */
    private static final C11452hx f22861n = new C11452hx("", (byte) 11, 14);

    /* renamed from: o */
    private static final C11452hx f22862o = new C11452hx("", (byte) 6, 15);

    /* renamed from: p */
    private static final C11452hx f22863p = new C11452hx("", (byte) 6, 16);

    /* renamed from: q */
    private static final C11452hx f22864q = new C11452hx("", (byte) 11, 20);

    /* renamed from: r */
    private static final C11452hx f22865r = new C11452hx("", (byte) 11, 21);

    /* renamed from: s */
    private static final C11452hx f22866s = new C11452hx("", (byte) 8, 22);

    /* renamed from: t */
    private static final C11452hx f22867t = new C11452hx("", (byte) 13, 23);

    /* renamed from: a */
    private BitSet f22873a = new BitSet(5);

    /* renamed from: a */
    public boolean f22876a = false;

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public boolean m3505a() {
        return this.f22872a != null;
    }

    /* renamed from: b */
    public boolean m3498b() {
        return this.f22870a != null;
    }

    /* renamed from: a */
    public C11420gv m3501a(String str) {
        this.f22877b = str;
        return this;
    }

    /* renamed from: c */
    public boolean m3495c() {
        return this.f22877b != null;
    }

    /* renamed from: b */
    public C11420gv m3497b(String str) {
        this.f22879c = str;
        return this;
    }

    /* renamed from: d */
    public boolean m3492d() {
        return this.f22879c != null;
    }

    /* renamed from: a */
    public C11420gv m3504a(long j) {
        this.f22869a = j;
        m3499a(true);
        return this;
    }

    /* renamed from: e */
    public boolean m3489e() {
        return this.f22873a.get(0);
    }

    /* renamed from: a */
    public void m3499a(boolean z) {
        this.f22873a.set(0, z);
    }

    /* renamed from: c */
    public C11420gv m3494c(String str) {
        this.f22880d = str;
        return this;
    }

    /* renamed from: f */
    public boolean m3487f() {
        return this.f22880d != null;
    }

    /* renamed from: d */
    public C11420gv m3491d(String str) {
        this.f22881e = str;
        return this;
    }

    /* renamed from: g */
    public boolean m3486g() {
        return this.f22881e != null;
    }

    /* renamed from: h */
    public boolean m3485h() {
        return this.f22871a != null;
    }

    /* renamed from: i */
    public boolean m3484i() {
        return this.f22882f != null;
    }

    /* renamed from: j */
    public boolean m3483j() {
        return this.f22883g != null;
    }

    /* renamed from: k */
    public boolean m3482k() {
        return this.f22873a.get(1);
    }

    /* renamed from: b */
    public void m3496b(boolean z) {
        this.f22873a.set(1, z);
    }

    /* renamed from: l */
    public boolean m3481l() {
        return this.f22884h != null;
    }

    /* renamed from: m */
    public boolean m3480m() {
        return this.f22885i != null;
    }

    /* renamed from: n */
    public boolean m3479n() {
        return this.f22886j != null;
    }

    /* renamed from: a */
    public C11420gv m3500a(short s) {
        this.f22875a = s;
        m3493c(true);
        return this;
    }

    /* renamed from: o */
    public boolean m3478o() {
        return this.f22873a.get(2);
    }

    /* renamed from: c */
    public void m3493c(boolean z) {
        this.f22873a.set(2, z);
    }

    /* renamed from: p */
    public boolean m3477p() {
        return this.f22873a.get(3);
    }

    /* renamed from: d */
    public void m3490d(boolean z) {
        this.f22873a.set(3, z);
    }

    /* renamed from: q */
    public boolean m3476q() {
        return this.f22887k != null;
    }

    /* renamed from: r */
    public boolean m3475r() {
        return this.f22888l != null;
    }

    /* renamed from: s */
    public boolean m3474s() {
        return this.f22873a.get(4);
    }

    /* renamed from: e */
    public void m3488e(boolean z) {
        this.f22873a.set(4, z);
    }

    /* renamed from: t */
    public boolean m3473t() {
        return this.f22874a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11420gv)) {
            return m3502a((C11420gv) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3502a(C11420gv c11420gv) {
        if (c11420gv == null) {
            return false;
        }
        boolean m3505a = m3505a();
        boolean m3505a2 = c11420gv.m3505a();
        if ((m3505a || m3505a2) && !(m3505a && m3505a2 && this.f22872a.equals(c11420gv.f22872a))) {
            return false;
        }
        boolean m3498b = m3498b();
        boolean m3498b2 = c11420gv.m3498b();
        if ((m3498b || m3498b2) && !(m3498b && m3498b2 && this.f22870a.m3514a(c11420gv.f22870a))) {
            return false;
        }
        boolean m3495c = m3495c();
        boolean m3495c2 = c11420gv.m3495c();
        if ((m3495c || m3495c2) && !(m3495c && m3495c2 && this.f22877b.equals(c11420gv.f22877b))) {
            return false;
        }
        boolean m3492d = m3492d();
        boolean m3492d2 = c11420gv.m3492d();
        if (((m3492d || m3492d2) && !(m3492d && m3492d2 && this.f22879c.equals(c11420gv.f22879c))) || this.f22869a != c11420gv.f22869a) {
            return false;
        }
        boolean m3487f = m3487f();
        boolean m3487f2 = c11420gv.m3487f();
        if ((m3487f || m3487f2) && !(m3487f && m3487f2 && this.f22880d.equals(c11420gv.f22880d))) {
            return false;
        }
        boolean m3486g = m3486g();
        boolean m3486g2 = c11420gv.m3486g();
        if ((m3486g || m3486g2) && !(m3486g && m3486g2 && this.f22881e.equals(c11420gv.f22881e))) {
            return false;
        }
        boolean m3485h = m3485h();
        boolean m3485h2 = c11420gv.m3485h();
        if ((m3485h || m3485h2) && !(m3485h && m3485h2 && this.f22871a.m3209a(c11420gv.f22871a))) {
            return false;
        }
        boolean m3484i = m3484i();
        boolean m3484i2 = c11420gv.m3484i();
        if ((m3484i || m3484i2) && !(m3484i && m3484i2 && this.f22882f.equals(c11420gv.f22882f))) {
            return false;
        }
        boolean m3483j = m3483j();
        boolean m3483j2 = c11420gv.m3483j();
        if ((m3483j || m3483j2) && !(m3483j && m3483j2 && this.f22883g.equals(c11420gv.f22883g))) {
            return false;
        }
        boolean m3482k = m3482k();
        boolean m3482k2 = c11420gv.m3482k();
        if ((m3482k || m3482k2) && !(m3482k && m3482k2 && this.f22876a == c11420gv.f22876a)) {
            return false;
        }
        boolean m3481l = m3481l();
        boolean m3481l2 = c11420gv.m3481l();
        if ((m3481l || m3481l2) && !(m3481l && m3481l2 && this.f22884h.equals(c11420gv.f22884h))) {
            return false;
        }
        boolean m3480m = m3480m();
        boolean m3480m2 = c11420gv.m3480m();
        if ((m3480m || m3480m2) && !(m3480m && m3480m2 && this.f22885i.equals(c11420gv.f22885i))) {
            return false;
        }
        boolean m3479n = m3479n();
        boolean m3479n2 = c11420gv.m3479n();
        if ((m3479n || m3479n2) && !(m3479n && m3479n2 && this.f22886j.equals(c11420gv.f22886j))) {
            return false;
        }
        boolean m3478o = m3478o();
        boolean m3478o2 = c11420gv.m3478o();
        if ((m3478o || m3478o2) && !(m3478o && m3478o2 && this.f22875a == c11420gv.f22875a)) {
            return false;
        }
        boolean m3477p = m3477p();
        boolean m3477p2 = c11420gv.m3477p();
        if ((m3477p || m3477p2) && !(m3477p && m3477p2 && this.f22878b == c11420gv.f22878b)) {
            return false;
        }
        boolean m3476q = m3476q();
        boolean m3476q2 = c11420gv.m3476q();
        if ((m3476q || m3476q2) && !(m3476q && m3476q2 && this.f22887k.equals(c11420gv.f22887k))) {
            return false;
        }
        boolean m3475r = m3475r();
        boolean m3475r2 = c11420gv.m3475r();
        if ((m3475r || m3475r2) && !(m3475r && m3475r2 && this.f22888l.equals(c11420gv.f22888l))) {
            return false;
        }
        boolean m3474s = m3474s();
        boolean m3474s2 = c11420gv.m3474s();
        if ((m3474s || m3474s2) && !(m3474s && m3474s2 && this.f22868a == c11420gv.f22868a)) {
            return false;
        }
        boolean m3473t = m3473t();
        boolean m3473t2 = c11420gv.m3473t();
        if (m3473t || m3473t2) {
            return m3473t && m3473t2 && this.f22874a.equals(c11420gv.f22874a);
        }
        return true;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11420gv c11420gv) {
        int m3069a;
        int m3079a;
        int m3076a;
        int m3076a2;
        int m3067a;
        int m3067a2;
        int m3076a3;
        int m3076a4;
        int m3076a5;
        int m3066a;
        int m3076a6;
        int m3076a7;
        int m3077a;
        int m3076a8;
        int m3076a9;
        int m3078a;
        int m3076a10;
        int m3076a11;
        int m3077a2;
        int m3076a12;
        if (!getClass().equals(c11420gv.getClass())) {
            return getClass().getName().compareTo(c11420gv.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3505a()).compareTo(Boolean.valueOf(c11420gv.m3505a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3505a() || (m3076a12 = C11443hr.m3076a(this.f22872a, c11420gv.f22872a)) == 0) {
            int compareTo2 = Boolean.valueOf(m3498b()).compareTo(Boolean.valueOf(c11420gv.m3498b()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (!m3498b() || (m3077a2 = C11443hr.m3077a(this.f22870a, c11420gv.f22870a)) == 0) {
                int compareTo3 = Boolean.valueOf(m3495c()).compareTo(Boolean.valueOf(c11420gv.m3495c()));
                if (compareTo3 != 0) {
                    return compareTo3;
                }
                if (!m3495c() || (m3076a11 = C11443hr.m3076a(this.f22877b, c11420gv.f22877b)) == 0) {
                    int compareTo4 = Boolean.valueOf(m3492d()).compareTo(Boolean.valueOf(c11420gv.m3492d()));
                    if (compareTo4 != 0) {
                        return compareTo4;
                    }
                    if (!m3492d() || (m3076a10 = C11443hr.m3076a(this.f22879c, c11420gv.f22879c)) == 0) {
                        int compareTo5 = Boolean.valueOf(m3489e()).compareTo(Boolean.valueOf(c11420gv.m3489e()));
                        if (compareTo5 != 0) {
                            return compareTo5;
                        }
                        if (!m3489e() || (m3078a = C11443hr.m3078a(this.f22869a, c11420gv.f22869a)) == 0) {
                            int compareTo6 = Boolean.valueOf(m3487f()).compareTo(Boolean.valueOf(c11420gv.m3487f()));
                            if (compareTo6 != 0) {
                                return compareTo6;
                            }
                            if (!m3487f() || (m3076a9 = C11443hr.m3076a(this.f22880d, c11420gv.f22880d)) == 0) {
                                int compareTo7 = Boolean.valueOf(m3486g()).compareTo(Boolean.valueOf(c11420gv.m3486g()));
                                if (compareTo7 != 0) {
                                    return compareTo7;
                                }
                                if (!m3486g() || (m3076a8 = C11443hr.m3076a(this.f22881e, c11420gv.f22881e)) == 0) {
                                    int compareTo8 = Boolean.valueOf(m3485h()).compareTo(Boolean.valueOf(c11420gv.m3485h()));
                                    if (compareTo8 != 0) {
                                        return compareTo8;
                                    }
                                    if (!m3485h() || (m3077a = C11443hr.m3077a(this.f22871a, c11420gv.f22871a)) == 0) {
                                        int compareTo9 = Boolean.valueOf(m3484i()).compareTo(Boolean.valueOf(c11420gv.m3484i()));
                                        if (compareTo9 != 0) {
                                            return compareTo9;
                                        }
                                        if (!m3484i() || (m3076a7 = C11443hr.m3076a(this.f22882f, c11420gv.f22882f)) == 0) {
                                            int compareTo10 = Boolean.valueOf(m3483j()).compareTo(Boolean.valueOf(c11420gv.m3483j()));
                                            if (compareTo10 != 0) {
                                                return compareTo10;
                                            }
                                            if (!m3483j() || (m3076a6 = C11443hr.m3076a(this.f22883g, c11420gv.f22883g)) == 0) {
                                                int compareTo11 = Boolean.valueOf(m3482k()).compareTo(Boolean.valueOf(c11420gv.m3482k()));
                                                if (compareTo11 != 0) {
                                                    return compareTo11;
                                                }
                                                if (!m3482k() || (m3066a = C11443hr.m3066a(this.f22876a, c11420gv.f22876a)) == 0) {
                                                    int compareTo12 = Boolean.valueOf(m3481l()).compareTo(Boolean.valueOf(c11420gv.m3481l()));
                                                    if (compareTo12 != 0) {
                                                        return compareTo12;
                                                    }
                                                    if (!m3481l() || (m3076a5 = C11443hr.m3076a(this.f22884h, c11420gv.f22884h)) == 0) {
                                                        int compareTo13 = Boolean.valueOf(m3480m()).compareTo(Boolean.valueOf(c11420gv.m3480m()));
                                                        if (compareTo13 != 0) {
                                                            return compareTo13;
                                                        }
                                                        if (!m3480m() || (m3076a4 = C11443hr.m3076a(this.f22885i, c11420gv.f22885i)) == 0) {
                                                            int compareTo14 = Boolean.valueOf(m3479n()).compareTo(Boolean.valueOf(c11420gv.m3479n()));
                                                            if (compareTo14 != 0) {
                                                                return compareTo14;
                                                            }
                                                            if (!m3479n() || (m3076a3 = C11443hr.m3076a(this.f22886j, c11420gv.f22886j)) == 0) {
                                                                int compareTo15 = Boolean.valueOf(m3478o()).compareTo(Boolean.valueOf(c11420gv.m3478o()));
                                                                if (compareTo15 != 0) {
                                                                    return compareTo15;
                                                                }
                                                                if (!m3478o() || (m3067a2 = C11443hr.m3067a(this.f22875a, c11420gv.f22875a)) == 0) {
                                                                    int compareTo16 = Boolean.valueOf(m3477p()).compareTo(Boolean.valueOf(c11420gv.m3477p()));
                                                                    if (compareTo16 != 0) {
                                                                        return compareTo16;
                                                                    }
                                                                    if (!m3477p() || (m3067a = C11443hr.m3067a(this.f22878b, c11420gv.f22878b)) == 0) {
                                                                        int compareTo17 = Boolean.valueOf(m3476q()).compareTo(Boolean.valueOf(c11420gv.m3476q()));
                                                                        if (compareTo17 != 0) {
                                                                            return compareTo17;
                                                                        }
                                                                        if (!m3476q() || (m3076a2 = C11443hr.m3076a(this.f22887k, c11420gv.f22887k)) == 0) {
                                                                            int compareTo18 = Boolean.valueOf(m3475r()).compareTo(Boolean.valueOf(c11420gv.m3475r()));
                                                                            if (compareTo18 != 0) {
                                                                                return compareTo18;
                                                                            }
                                                                            if (!m3475r() || (m3076a = C11443hr.m3076a(this.f22888l, c11420gv.f22888l)) == 0) {
                                                                                int compareTo19 = Boolean.valueOf(m3474s()).compareTo(Boolean.valueOf(c11420gv.m3474s()));
                                                                                if (compareTo19 != 0) {
                                                                                    return compareTo19;
                                                                                }
                                                                                if (!m3474s() || (m3079a = C11443hr.m3079a(this.f22868a, c11420gv.f22868a)) == 0) {
                                                                                    int compareTo20 = Boolean.valueOf(m3473t()).compareTo(Boolean.valueOf(c11420gv.m3473t()));
                                                                                    if (compareTo20 != 0) {
                                                                                        return compareTo20;
                                                                                    }
                                                                                    if (!m3473t() || (m3069a = C11443hr.m3069a(this.f22874a, c11420gv.f22874a)) == 0) {
                                                                                        return 0;
                                                                                    }
                                                                                    return m3069a;
                                                                                }
                                                                                return m3079a;
                                                                            }
                                                                            return m3076a;
                                                                        }
                                                                        return m3076a2;
                                                                    }
                                                                    return m3067a;
                                                                }
                                                                return m3067a2;
                                                            }
                                                            return m3076a3;
                                                        }
                                                        return m3076a4;
                                                    }
                                                    return m3076a5;
                                                }
                                                return m3066a;
                                            }
                                            return m3076a6;
                                        }
                                        return m3076a7;
                                    }
                                    return m3077a;
                                }
                                return m3076a8;
                            }
                            return m3076a9;
                        }
                        return m3078a;
                    }
                    return m3076a10;
                }
                return m3076a11;
            }
            return m3077a2;
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
                            this.f22872a = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 2:
                        if (mo3021a.f23307a == 12) {
                            this.f22870a = new C11419gu();
                            this.f22870a.mo3083a(abstractC11456ia);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 3:
                        if (mo3021a.f23307a == 11) {
                            this.f22877b = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 4:
                        if (mo3021a.f23307a == 11) {
                            this.f22879c = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 5:
                        if (mo3021a.f23307a == 10) {
                            this.f22869a = abstractC11456ia.mo3022a();
                            m3499a(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 6:
                        if (mo3021a.f23307a == 11) {
                            this.f22880d = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 7:
                        if (mo3021a.f23307a == 11) {
                            this.f22881e = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 8:
                        if (mo3021a.f23307a == 12) {
                            this.f22871a = new C11434hi();
                            this.f22871a.mo3083a(abstractC11456ia);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 9:
                        if (mo3021a.f23307a == 11) {
                            this.f22882f = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 10:
                        if (mo3021a.f23307a == 11) {
                            this.f22883g = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 11:
                        if (mo3021a.f23307a == 2) {
                            this.f22876a = abstractC11456ia.mo3017a();
                            m3496b(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 12:
                        if (mo3021a.f23307a == 11) {
                            this.f22884h = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 13:
                        if (mo3021a.f23307a == 11) {
                            this.f22885i = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 14:
                        if (mo3021a.f23307a == 11) {
                            this.f22886j = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 15:
                        if (mo3021a.f23307a == 6) {
                            this.f22875a = abstractC11456ia.mo3019a();
                            m3493c(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 16:
                        if (mo3021a.f23307a == 6) {
                            this.f22878b = abstractC11456ia.mo3019a();
                            m3490d(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 17:
                    case 18:
                    case 19:
                    default:
                        C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                        break;
                    case 20:
                        if (mo3021a.f23307a == 11) {
                            this.f22887k = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 21:
                        if (mo3021a.f23307a == 11) {
                            this.f22888l = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 22:
                        if (mo3021a.f23307a == 8) {
                            this.f22868a = abstractC11456ia.mo3023a();
                            m3488e(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 23:
                        if (mo3021a.f23307a == 13) {
                            C11454hz mo2992a = abstractC11456ia.mo2992a();
                            this.f22874a = new HashMap(mo2992a.f23313a * 2);
                            for (int i = 0; i < mo2992a.f23313a; i++) {
                                this.f22874a.put(abstractC11456ia.mo2990a(), abstractC11456ia.mo2990a());
                            }
                            abstractC11456ia.mo2999h();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                }
                abstractC11456ia.mo3000g();
            } else {
                abstractC11456ia.mo3001f();
                if (!m3489e()) {
                    throw new C11457ib("Required field 'messageTs' was not found in serialized data! Struct: " + toString());
                }
                m3506a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3506a();
        abstractC11456ia.mo3010a(f22848a);
        if (this.f22872a != null && m3505a()) {
            abstractC11456ia.mo3013a(f22847a);
            abstractC11456ia.mo3009a(this.f22872a);
            abstractC11456ia.mo3005b();
        }
        if (this.f22870a != null && m3498b()) {
            abstractC11456ia.mo3013a(f22849b);
            this.f22870a.mo3082b(abstractC11456ia);
            abstractC11456ia.mo3005b();
        }
        if (this.f22877b != null) {
            abstractC11456ia.mo3013a(f22850c);
            abstractC11456ia.mo3009a(this.f22877b);
            abstractC11456ia.mo3005b();
        }
        if (this.f22879c != null) {
            abstractC11456ia.mo3013a(f22851d);
            abstractC11456ia.mo3009a(this.f22879c);
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3013a(f22852e);
        abstractC11456ia.mo3014a(this.f22869a);
        abstractC11456ia.mo3005b();
        if (this.f22880d != null && m3487f()) {
            abstractC11456ia.mo3013a(f22853f);
            abstractC11456ia.mo3009a(this.f22880d);
            abstractC11456ia.mo3005b();
        }
        if (this.f22881e != null && m3486g()) {
            abstractC11456ia.mo3013a(f22854g);
            abstractC11456ia.mo3009a(this.f22881e);
            abstractC11456ia.mo3005b();
        }
        if (this.f22871a != null && m3485h()) {
            abstractC11456ia.mo3013a(f22855h);
            this.f22871a.mo3082b(abstractC11456ia);
            abstractC11456ia.mo3005b();
        }
        if (this.f22882f != null && m3484i()) {
            abstractC11456ia.mo3013a(f22856i);
            abstractC11456ia.mo3009a(this.f22882f);
            abstractC11456ia.mo3005b();
        }
        if (this.f22883g != null && m3483j()) {
            abstractC11456ia.mo3013a(f22857j);
            abstractC11456ia.mo3009a(this.f22883g);
            abstractC11456ia.mo3005b();
        }
        if (m3482k()) {
            abstractC11456ia.mo3013a(f22858k);
            abstractC11456ia.mo3006a(this.f22876a);
            abstractC11456ia.mo3005b();
        }
        if (this.f22884h != null && m3481l()) {
            abstractC11456ia.mo3013a(f22859l);
            abstractC11456ia.mo3009a(this.f22884h);
            abstractC11456ia.mo3005b();
        }
        if (this.f22885i != null && m3480m()) {
            abstractC11456ia.mo3013a(f22860m);
            abstractC11456ia.mo3009a(this.f22885i);
            abstractC11456ia.mo3005b();
        }
        if (this.f22886j != null && m3479n()) {
            abstractC11456ia.mo3013a(f22861n);
            abstractC11456ia.mo3009a(this.f22886j);
            abstractC11456ia.mo3005b();
        }
        if (m3478o()) {
            abstractC11456ia.mo3013a(f22862o);
            abstractC11456ia.mo3007a(this.f22875a);
            abstractC11456ia.mo3005b();
        }
        if (m3477p()) {
            abstractC11456ia.mo3013a(f22863p);
            abstractC11456ia.mo3007a(this.f22878b);
            abstractC11456ia.mo3005b();
        }
        if (this.f22887k != null && m3476q()) {
            abstractC11456ia.mo3013a(f22864q);
            abstractC11456ia.mo3009a(this.f22887k);
            abstractC11456ia.mo3005b();
        }
        if (this.f22888l != null && m3475r()) {
            abstractC11456ia.mo3013a(f22865r);
            abstractC11456ia.mo3009a(this.f22888l);
            abstractC11456ia.mo3005b();
        }
        if (m3474s()) {
            abstractC11456ia.mo3013a(f22866s);
            abstractC11456ia.mo3015a(this.f22868a);
            abstractC11456ia.mo3005b();
        }
        if (this.f22874a != null && m3473t()) {
            abstractC11456ia.mo3013a(f22867t);
            abstractC11456ia.mo3011a(new C11454hz((byte) 11, (byte) 11, this.f22874a.size()));
            for (Map.Entry<String, String> entry : this.f22874a.entrySet()) {
                abstractC11456ia.mo3009a(entry.getKey());
                abstractC11456ia.mo3009a(entry.getValue());
            }
            abstractC11456ia.mo3003d();
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionAckMessage(");
        if (m3505a()) {
            sb.append("debug:");
            String str = this.f22872a;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (m3498b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            C11419gu c11419gu = this.f22870a;
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
        String str2 = this.f22877b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("appId:");
        String str3 = this.f22879c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("messageTs:");
        sb.append(this.f22869a);
        if (m3487f()) {
            sb.append(", ");
            sb.append("topic:");
            String str4 = this.f22880d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m3486g()) {
            sb.append(", ");
            sb.append("aliasName:");
            String str5 = this.f22881e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m3485h()) {
            sb.append(", ");
            sb.append("request:");
            C11434hi c11434hi = this.f22871a;
            if (c11434hi == null) {
                sb.append("null");
            } else {
                sb.append(c11434hi);
            }
        }
        if (m3484i()) {
            sb.append(", ");
            sb.append("packageName:");
            String str6 = this.f22882f;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
        }
        if (m3483j()) {
            sb.append(", ");
            sb.append("category:");
            String str7 = this.f22883g;
            if (str7 == null) {
                sb.append("null");
            } else {
                sb.append(str7);
            }
        }
        if (m3482k()) {
            sb.append(", ");
            sb.append("isOnline:");
            sb.append(this.f22876a);
        }
        if (m3481l()) {
            sb.append(", ");
            sb.append("regId:");
            String str8 = this.f22884h;
            if (str8 == null) {
                sb.append("null");
            } else {
                sb.append(str8);
            }
        }
        if (m3480m()) {
            sb.append(", ");
            sb.append("callbackUrl:");
            String str9 = this.f22885i;
            if (str9 == null) {
                sb.append("null");
            } else {
                sb.append(str9);
            }
        }
        if (m3479n()) {
            sb.append(", ");
            sb.append("userAccount:");
            String str10 = this.f22886j;
            if (str10 == null) {
                sb.append("null");
            } else {
                sb.append(str10);
            }
        }
        if (m3478o()) {
            sb.append(", ");
            sb.append("deviceStatus:");
            sb.append((int) this.f22875a);
        }
        if (m3477p()) {
            sb.append(", ");
            sb.append("geoMsgStatus:");
            sb.append((int) this.f22878b);
        }
        if (m3476q()) {
            sb.append(", ");
            sb.append("imeiMd5:");
            String str11 = this.f22887k;
            if (str11 == null) {
                sb.append("null");
            } else {
                sb.append(str11);
            }
        }
        if (m3475r()) {
            sb.append(", ");
            sb.append("deviceId:");
            String str12 = this.f22888l;
            if (str12 == null) {
                sb.append("null");
            } else {
                sb.append(str12);
            }
        }
        if (m3474s()) {
            sb.append(", ");
            sb.append("passThrough:");
            sb.append(this.f22868a);
        }
        if (m3473t()) {
            sb.append(", ");
            sb.append("extra:");
            Map<String, String> map = this.f22874a;
            if (map == null) {
                sb.append("null");
            } else {
                sb.append(map);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    public void m3506a() {
        if (this.f22877b == null) {
            throw new C11457ib("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f22879c != null) {
        } else {
            throw new C11457ib("Required field 'appId' was not present! Struct: " + toString());
        }
    }
}
