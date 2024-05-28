package com.xiaomi.push;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.xiaomi.push.he */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11430he implements InterfaceC11442hq<C11430he, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public long f23005a;

    /* renamed from: a */
    public C11419gu f23006a;

    /* renamed from: a */
    public String f23007a;

    /* renamed from: a */
    public ByteBuffer f23008a;

    /* renamed from: a */
    private BitSet f23009a;

    /* renamed from: a */
    public Map<String, String> f23010a;

    /* renamed from: a */
    public boolean f23011a;

    /* renamed from: b */
    public String f23012b;

    /* renamed from: b */
    public boolean f23013b;

    /* renamed from: c */
    public String f23014c;

    /* renamed from: d */
    public String f23015d;

    /* renamed from: e */
    public String f23016e;

    /* renamed from: f */
    public String f23017f;

    /* renamed from: g */
    public String f23018g;

    /* renamed from: h */
    public String f23019h;

    /* renamed from: i */
    public String f23020i;

    /* renamed from: a */
    private static final C11461if f22990a = new C11461if("XmPushActionNotification");

    /* renamed from: a */
    private static final C11452hx f22989a = new C11452hx("", (byte) 11, 1);

    /* renamed from: b */
    private static final C11452hx f22991b = new C11452hx("", (byte) 12, 2);

    /* renamed from: c */
    private static final C11452hx f22992c = new C11452hx("", (byte) 11, 3);

    /* renamed from: d */
    private static final C11452hx f22993d = new C11452hx("", (byte) 11, 4);

    /* renamed from: e */
    private static final C11452hx f22994e = new C11452hx("", (byte) 11, 5);

    /* renamed from: f */
    private static final C11452hx f22995f = new C11452hx("", (byte) 2, 6);

    /* renamed from: g */
    private static final C11452hx f22996g = new C11452hx("", (byte) 11, 7);

    /* renamed from: h */
    private static final C11452hx f22997h = new C11452hx("", (byte) 13, 8);

    /* renamed from: i */
    private static final C11452hx f22998i = new C11452hx("", (byte) 11, 9);

    /* renamed from: j */
    private static final C11452hx f22999j = new C11452hx("", (byte) 11, 10);

    /* renamed from: k */
    private static final C11452hx f23000k = new C11452hx("", (byte) 11, 12);

    /* renamed from: l */
    private static final C11452hx f23001l = new C11452hx("", (byte) 11, 13);

    /* renamed from: m */
    private static final C11452hx f23002m = new C11452hx("", (byte) 11, 14);

    /* renamed from: n */
    private static final C11452hx f23003n = new C11452hx("", (byte) 10, 15);

    /* renamed from: o */
    private static final C11452hx f23004o = new C11452hx("", (byte) 2, 20);

    public int hashCode() {
        return 0;
    }

    public C11430he() {
        this.f23009a = new BitSet(3);
        this.f23011a = true;
        this.f23013b = false;
    }

    public C11430he(String str, boolean z) {
        this();
        this.f23012b = str;
        this.f23011a = z;
        m3339a(true);
    }

    /* renamed from: a */
    public boolean m3348a() {
        return this.f23007a != null;
    }

    /* renamed from: a */
    public C11419gu m3352a() {
        return this.f23006a;
    }

    /* renamed from: b */
    public boolean m3336b() {
        return this.f23006a != null;
    }

    /* renamed from: a */
    public String m3351a() {
        return this.f23012b;
    }

    /* renamed from: a */
    public C11430he m3344a(String str) {
        this.f23012b = str;
        return this;
    }

    /* renamed from: c */
    public boolean m3332c() {
        return this.f23012b != null;
    }

    /* renamed from: b */
    public String m3337b() {
        return this.f23014c;
    }

    /* renamed from: b */
    public C11430he m3335b(String str) {
        this.f23014c = str;
        return this;
    }

    /* renamed from: d */
    public boolean m3328d() {
        return this.f23014c != null;
    }

    /* renamed from: c */
    public String m3333c() {
        return this.f23015d;
    }

    /* renamed from: c */
    public C11430he m3331c(String str) {
        this.f23015d = str;
        return this;
    }

    /* renamed from: e */
    public boolean m3326e() {
        return this.f23015d != null;
    }

    /* renamed from: a */
    public C11430he m3340a(boolean z) {
        this.f23011a = z;
        m3339a(true);
        return this;
    }

    /* renamed from: f */
    public boolean m3325f() {
        return this.f23009a.get(0);
    }

    /* renamed from: a */
    public void m3339a(boolean z) {
        this.f23009a.set(0, z);
    }

    /* renamed from: g */
    public boolean m3324g() {
        return this.f23016e != null;
    }

    /* renamed from: a */
    public void m3343a(String str, String str2) {
        if (this.f23010a == null) {
            this.f23010a = new HashMap();
        }
        this.f23010a.put(str, str2);
    }

    /* renamed from: a */
    public Map<String, String> m3350a() {
        return this.f23010a;
    }

    /* renamed from: a */
    public C11430he m3341a(Map<String, String> map) {
        this.f23010a = map;
        return this;
    }

    /* renamed from: h */
    public boolean m3323h() {
        return this.f23010a != null;
    }

    /* renamed from: d */
    public String m3329d() {
        return this.f23017f;
    }

    /* renamed from: d */
    public C11430he m3327d(String str) {
        this.f23017f = str;
        return this;
    }

    /* renamed from: i */
    public boolean m3322i() {
        return this.f23017f != null;
    }

    /* renamed from: j */
    public boolean m3321j() {
        return this.f23018g != null;
    }

    /* renamed from: k */
    public boolean m3320k() {
        return this.f23019h != null;
    }

    /* renamed from: l */
    public boolean m3319l() {
        return this.f23020i != null;
    }

    /* renamed from: a */
    public byte[] m3347a() {
        m3342a(C11443hr.m3075a(this.f23008a));
        return this.f23008a.array();
    }

    /* renamed from: a */
    public C11430he m3338a(byte[] bArr) {
        m3342a(ByteBuffer.wrap(bArr));
        return this;
    }

    /* renamed from: a */
    public C11430he m3342a(ByteBuffer byteBuffer) {
        this.f23008a = byteBuffer;
        return this;
    }

    /* renamed from: m */
    public boolean m3318m() {
        return this.f23008a != null;
    }

    /* renamed from: n */
    public boolean m3317n() {
        return this.f23009a.get(1);
    }

    /* renamed from: b */
    public void m3334b(boolean z) {
        this.f23009a.set(1, z);
    }

    /* renamed from: o */
    public boolean m3316o() {
        return this.f23009a.get(2);
    }

    /* renamed from: c */
    public void m3330c(boolean z) {
        this.f23009a.set(2, z);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11430he)) {
            return m3345a((C11430he) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3345a(C11430he c11430he) {
        if (c11430he == null) {
            return false;
        }
        boolean m3348a = m3348a();
        boolean m3348a2 = c11430he.m3348a();
        if ((m3348a || m3348a2) && !(m3348a && m3348a2 && this.f23007a.equals(c11430he.f23007a))) {
            return false;
        }
        boolean m3336b = m3336b();
        boolean m3336b2 = c11430he.m3336b();
        if ((m3336b || m3336b2) && !(m3336b && m3336b2 && this.f23006a.m3514a(c11430he.f23006a))) {
            return false;
        }
        boolean m3332c = m3332c();
        boolean m3332c2 = c11430he.m3332c();
        if ((m3332c || m3332c2) && !(m3332c && m3332c2 && this.f23012b.equals(c11430he.f23012b))) {
            return false;
        }
        boolean m3328d = m3328d();
        boolean m3328d2 = c11430he.m3328d();
        if ((m3328d || m3328d2) && !(m3328d && m3328d2 && this.f23014c.equals(c11430he.f23014c))) {
            return false;
        }
        boolean m3326e = m3326e();
        boolean m3326e2 = c11430he.m3326e();
        if (((m3326e || m3326e2) && !(m3326e && m3326e2 && this.f23015d.equals(c11430he.f23015d))) || this.f23011a != c11430he.f23011a) {
            return false;
        }
        boolean m3324g = m3324g();
        boolean m3324g2 = c11430he.m3324g();
        if ((m3324g || m3324g2) && !(m3324g && m3324g2 && this.f23016e.equals(c11430he.f23016e))) {
            return false;
        }
        boolean m3323h = m3323h();
        boolean m3323h2 = c11430he.m3323h();
        if ((m3323h || m3323h2) && !(m3323h && m3323h2 && this.f23010a.equals(c11430he.f23010a))) {
            return false;
        }
        boolean m3322i = m3322i();
        boolean m3322i2 = c11430he.m3322i();
        if ((m3322i || m3322i2) && !(m3322i && m3322i2 && this.f23017f.equals(c11430he.f23017f))) {
            return false;
        }
        boolean m3321j = m3321j();
        boolean m3321j2 = c11430he.m3321j();
        if ((m3321j || m3321j2) && !(m3321j && m3321j2 && this.f23018g.equals(c11430he.f23018g))) {
            return false;
        }
        boolean m3320k = m3320k();
        boolean m3320k2 = c11430he.m3320k();
        if ((m3320k || m3320k2) && !(m3320k && m3320k2 && this.f23019h.equals(c11430he.f23019h))) {
            return false;
        }
        boolean m3319l = m3319l();
        boolean m3319l2 = c11430he.m3319l();
        if ((m3319l || m3319l2) && !(m3319l && m3319l2 && this.f23020i.equals(c11430he.f23020i))) {
            return false;
        }
        boolean m3318m = m3318m();
        boolean m3318m2 = c11430he.m3318m();
        if ((m3318m || m3318m2) && !(m3318m && m3318m2 && this.f23008a.equals(c11430he.f23008a))) {
            return false;
        }
        boolean m3317n = m3317n();
        boolean m3317n2 = c11430he.m3317n();
        if ((m3317n || m3317n2) && !(m3317n && m3317n2 && this.f23005a == c11430he.f23005a)) {
            return false;
        }
        boolean m3316o = m3316o();
        boolean m3316o2 = c11430he.m3316o();
        if (m3316o || m3316o2) {
            return m3316o && m3316o2 && this.f23013b == c11430he.f23013b;
        }
        return true;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11430he c11430he) {
        int m3066a;
        int m3078a;
        int m3077a;
        int m3076a;
        int m3076a2;
        int m3076a3;
        int m3076a4;
        int m3069a;
        int m3076a5;
        int m3066a2;
        int m3076a6;
        int m3076a7;
        int m3076a8;
        int m3077a2;
        int m3076a9;
        if (!getClass().equals(c11430he.getClass())) {
            return getClass().getName().compareTo(c11430he.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3348a()).compareTo(Boolean.valueOf(c11430he.m3348a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3348a() || (m3076a9 = C11443hr.m3076a(this.f23007a, c11430he.f23007a)) == 0) {
            int compareTo2 = Boolean.valueOf(m3336b()).compareTo(Boolean.valueOf(c11430he.m3336b()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (!m3336b() || (m3077a2 = C11443hr.m3077a(this.f23006a, c11430he.f23006a)) == 0) {
                int compareTo3 = Boolean.valueOf(m3332c()).compareTo(Boolean.valueOf(c11430he.m3332c()));
                if (compareTo3 != 0) {
                    return compareTo3;
                }
                if (!m3332c() || (m3076a8 = C11443hr.m3076a(this.f23012b, c11430he.f23012b)) == 0) {
                    int compareTo4 = Boolean.valueOf(m3328d()).compareTo(Boolean.valueOf(c11430he.m3328d()));
                    if (compareTo4 != 0) {
                        return compareTo4;
                    }
                    if (!m3328d() || (m3076a7 = C11443hr.m3076a(this.f23014c, c11430he.f23014c)) == 0) {
                        int compareTo5 = Boolean.valueOf(m3326e()).compareTo(Boolean.valueOf(c11430he.m3326e()));
                        if (compareTo5 != 0) {
                            return compareTo5;
                        }
                        if (!m3326e() || (m3076a6 = C11443hr.m3076a(this.f23015d, c11430he.f23015d)) == 0) {
                            int compareTo6 = Boolean.valueOf(m3325f()).compareTo(Boolean.valueOf(c11430he.m3325f()));
                            if (compareTo6 != 0) {
                                return compareTo6;
                            }
                            if (!m3325f() || (m3066a2 = C11443hr.m3066a(this.f23011a, c11430he.f23011a)) == 0) {
                                int compareTo7 = Boolean.valueOf(m3324g()).compareTo(Boolean.valueOf(c11430he.m3324g()));
                                if (compareTo7 != 0) {
                                    return compareTo7;
                                }
                                if (!m3324g() || (m3076a5 = C11443hr.m3076a(this.f23016e, c11430he.f23016e)) == 0) {
                                    int compareTo8 = Boolean.valueOf(m3323h()).compareTo(Boolean.valueOf(c11430he.m3323h()));
                                    if (compareTo8 != 0) {
                                        return compareTo8;
                                    }
                                    if (!m3323h() || (m3069a = C11443hr.m3069a(this.f23010a, c11430he.f23010a)) == 0) {
                                        int compareTo9 = Boolean.valueOf(m3322i()).compareTo(Boolean.valueOf(c11430he.m3322i()));
                                        if (compareTo9 != 0) {
                                            return compareTo9;
                                        }
                                        if (!m3322i() || (m3076a4 = C11443hr.m3076a(this.f23017f, c11430he.f23017f)) == 0) {
                                            int compareTo10 = Boolean.valueOf(m3321j()).compareTo(Boolean.valueOf(c11430he.m3321j()));
                                            if (compareTo10 != 0) {
                                                return compareTo10;
                                            }
                                            if (!m3321j() || (m3076a3 = C11443hr.m3076a(this.f23018g, c11430he.f23018g)) == 0) {
                                                int compareTo11 = Boolean.valueOf(m3320k()).compareTo(Boolean.valueOf(c11430he.m3320k()));
                                                if (compareTo11 != 0) {
                                                    return compareTo11;
                                                }
                                                if (!m3320k() || (m3076a2 = C11443hr.m3076a(this.f23019h, c11430he.f23019h)) == 0) {
                                                    int compareTo12 = Boolean.valueOf(m3319l()).compareTo(Boolean.valueOf(c11430he.m3319l()));
                                                    if (compareTo12 != 0) {
                                                        return compareTo12;
                                                    }
                                                    if (!m3319l() || (m3076a = C11443hr.m3076a(this.f23020i, c11430he.f23020i)) == 0) {
                                                        int compareTo13 = Boolean.valueOf(m3318m()).compareTo(Boolean.valueOf(c11430he.m3318m()));
                                                        if (compareTo13 != 0) {
                                                            return compareTo13;
                                                        }
                                                        if (!m3318m() || (m3077a = C11443hr.m3077a(this.f23008a, c11430he.f23008a)) == 0) {
                                                            int compareTo14 = Boolean.valueOf(m3317n()).compareTo(Boolean.valueOf(c11430he.m3317n()));
                                                            if (compareTo14 != 0) {
                                                                return compareTo14;
                                                            }
                                                            if (!m3317n() || (m3078a = C11443hr.m3078a(this.f23005a, c11430he.f23005a)) == 0) {
                                                                int compareTo15 = Boolean.valueOf(m3316o()).compareTo(Boolean.valueOf(c11430he.m3316o()));
                                                                if (compareTo15 != 0) {
                                                                    return compareTo15;
                                                                }
                                                                if (!m3316o() || (m3066a = C11443hr.m3066a(this.f23013b, c11430he.f23013b)) == 0) {
                                                                    return 0;
                                                                }
                                                                return m3066a;
                                                            }
                                                            return m3078a;
                                                        }
                                                        return m3077a;
                                                    }
                                                    return m3076a;
                                                }
                                                return m3076a2;
                                            }
                                            return m3076a3;
                                        }
                                        return m3076a4;
                                    }
                                    return m3069a;
                                }
                                return m3076a5;
                            }
                            return m3066a2;
                        }
                        return m3076a6;
                    }
                    return m3076a7;
                }
                return m3076a8;
            }
            return m3077a2;
        }
        return m3076a9;
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: a */
    public void mo3083a(AbstractC11456ia abstractC11456ia) {
        abstractC11456ia.mo3020a();
        while (true) {
            C11452hx mo3021a = abstractC11456ia.mo3021a();
            if (mo3021a.f23307a != 0) {
                short s = mo3021a.f23309a;
                if (s != 20) {
                    switch (s) {
                        case 1:
                            if (mo3021a.f23307a == 11) {
                                this.f23007a = abstractC11456ia.mo2990a();
                                continue;
                            } else {
                                C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                                break;
                            }
                        case 2:
                            if (mo3021a.f23307a == 12) {
                                this.f23006a = new C11419gu();
                                this.f23006a.mo3083a(abstractC11456ia);
                                continue;
                            } else {
                                C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                                break;
                            }
                        case 3:
                            if (mo3021a.f23307a == 11) {
                                this.f23012b = abstractC11456ia.mo2990a();
                                continue;
                            } else {
                                C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                                break;
                            }
                        case 4:
                            if (mo3021a.f23307a == 11) {
                                this.f23014c = abstractC11456ia.mo2990a();
                                continue;
                            } else {
                                C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                                break;
                            }
                        case 5:
                            if (mo3021a.f23307a == 11) {
                                this.f23015d = abstractC11456ia.mo2990a();
                                continue;
                            } else {
                                C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                                break;
                            }
                        case 6:
                            if (mo3021a.f23307a == 2) {
                                this.f23011a = abstractC11456ia.mo3017a();
                                m3339a(true);
                                continue;
                            } else {
                                C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                                break;
                            }
                        case 7:
                            if (mo3021a.f23307a == 11) {
                                this.f23016e = abstractC11456ia.mo2990a();
                                continue;
                            } else {
                                C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                                break;
                            }
                        case 8:
                            if (mo3021a.f23307a == 13) {
                                C11454hz mo2992a = abstractC11456ia.mo2992a();
                                this.f23010a = new HashMap(mo2992a.f23313a * 2);
                                for (int i = 0; i < mo2992a.f23313a; i++) {
                                    this.f23010a.put(abstractC11456ia.mo2990a(), abstractC11456ia.mo2990a());
                                }
                                abstractC11456ia.mo2999h();
                                continue;
                            } else {
                                C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                                break;
                            }
                        case 9:
                            if (mo3021a.f23307a == 11) {
                                this.f23017f = abstractC11456ia.mo2990a();
                                continue;
                            } else {
                                C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                                break;
                            }
                        case 10:
                            if (mo3021a.f23307a == 11) {
                                this.f23018g = abstractC11456ia.mo2990a();
                                continue;
                            } else {
                                C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                                break;
                            }
                        default:
                            switch (s) {
                                case 12:
                                    if (mo3021a.f23307a == 11) {
                                        this.f23019h = abstractC11456ia.mo2990a();
                                        continue;
                                    } else {
                                        C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                                        break;
                                    }
                                case 13:
                                    if (mo3021a.f23307a == 11) {
                                        this.f23020i = abstractC11456ia.mo2990a();
                                        continue;
                                    } else {
                                        C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                                        break;
                                    }
                                case 14:
                                    if (mo3021a.f23307a == 11) {
                                        this.f23008a = abstractC11456ia.mo2989a();
                                        continue;
                                    } else {
                                        C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                                        break;
                                    }
                                case 15:
                                    if (mo3021a.f23307a == 10) {
                                        this.f23005a = abstractC11456ia.mo3022a();
                                        m3334b(true);
                                        continue;
                                    } else {
                                        C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                                        break;
                                    }
                                default:
                                    C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                                    continue;
                                    continue;
                            }
                    }
                } else if (mo3021a.f23307a == 2) {
                    this.f23013b = abstractC11456ia.mo3017a();
                    m3330c(true);
                } else {
                    C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                }
                abstractC11456ia.mo3000g();
            } else {
                abstractC11456ia.mo3001f();
                if (!m3325f()) {
                    throw new C11457ib("Required field 'requireAck' was not found in serialized data! Struct: " + toString());
                }
                m3349a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3349a();
        abstractC11456ia.mo3010a(f22990a);
        if (this.f23007a != null && m3348a()) {
            abstractC11456ia.mo3013a(f22989a);
            abstractC11456ia.mo3009a(this.f23007a);
            abstractC11456ia.mo3005b();
        }
        if (this.f23006a != null && m3336b()) {
            abstractC11456ia.mo3013a(f22991b);
            this.f23006a.mo3082b(abstractC11456ia);
            abstractC11456ia.mo3005b();
        }
        if (this.f23012b != null) {
            abstractC11456ia.mo3013a(f22992c);
            abstractC11456ia.mo3009a(this.f23012b);
            abstractC11456ia.mo3005b();
        }
        if (this.f23014c != null && m3328d()) {
            abstractC11456ia.mo3013a(f22993d);
            abstractC11456ia.mo3009a(this.f23014c);
            abstractC11456ia.mo3005b();
        }
        if (this.f23015d != null && m3326e()) {
            abstractC11456ia.mo3013a(f22994e);
            abstractC11456ia.mo3009a(this.f23015d);
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3013a(f22995f);
        abstractC11456ia.mo3006a(this.f23011a);
        abstractC11456ia.mo3005b();
        if (this.f23016e != null && m3324g()) {
            abstractC11456ia.mo3013a(f22996g);
            abstractC11456ia.mo3009a(this.f23016e);
            abstractC11456ia.mo3005b();
        }
        if (this.f23010a != null && m3323h()) {
            abstractC11456ia.mo3013a(f22997h);
            abstractC11456ia.mo3011a(new C11454hz((byte) 11, (byte) 11, this.f23010a.size()));
            for (Map.Entry<String, String> entry : this.f23010a.entrySet()) {
                abstractC11456ia.mo3009a(entry.getKey());
                abstractC11456ia.mo3009a(entry.getValue());
            }
            abstractC11456ia.mo3003d();
            abstractC11456ia.mo3005b();
        }
        if (this.f23017f != null && m3322i()) {
            abstractC11456ia.mo3013a(f22998i);
            abstractC11456ia.mo3009a(this.f23017f);
            abstractC11456ia.mo3005b();
        }
        if (this.f23018g != null && m3321j()) {
            abstractC11456ia.mo3013a(f22999j);
            abstractC11456ia.mo3009a(this.f23018g);
            abstractC11456ia.mo3005b();
        }
        if (this.f23019h != null && m3320k()) {
            abstractC11456ia.mo3013a(f23000k);
            abstractC11456ia.mo3009a(this.f23019h);
            abstractC11456ia.mo3005b();
        }
        if (this.f23020i != null && m3319l()) {
            abstractC11456ia.mo3013a(f23001l);
            abstractC11456ia.mo3009a(this.f23020i);
            abstractC11456ia.mo3005b();
        }
        if (this.f23008a != null && m3318m()) {
            abstractC11456ia.mo3013a(f23002m);
            abstractC11456ia.mo3008a(this.f23008a);
            abstractC11456ia.mo3005b();
        }
        if (m3317n()) {
            abstractC11456ia.mo3013a(f23003n);
            abstractC11456ia.mo3014a(this.f23005a);
            abstractC11456ia.mo3005b();
        }
        if (m3316o()) {
            abstractC11456ia.mo3013a(f23004o);
            abstractC11456ia.mo3006a(this.f23013b);
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionNotification(");
        if (m3348a()) {
            sb.append("debug:");
            String str = this.f23007a;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (m3336b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            C11419gu c11419gu = this.f23006a;
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
        String str2 = this.f23012b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        if (m3328d()) {
            sb.append(", ");
            sb.append("appId:");
            String str3 = this.f23014c;
            if (str3 == null) {
                sb.append("null");
            } else {
                sb.append(str3);
            }
        }
        if (m3326e()) {
            sb.append(", ");
            sb.append("type:");
            String str4 = this.f23015d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        sb.append(", ");
        sb.append("requireAck:");
        sb.append(this.f23011a);
        if (m3324g()) {
            sb.append(", ");
            sb.append("payload:");
            String str5 = this.f23016e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m3323h()) {
            sb.append(", ");
            sb.append("extra:");
            Map<String, String> map = this.f23010a;
            if (map == null) {
                sb.append("null");
            } else {
                sb.append(map);
            }
        }
        if (m3322i()) {
            sb.append(", ");
            sb.append("packageName:");
            String str6 = this.f23017f;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
        }
        if (m3321j()) {
            sb.append(", ");
            sb.append("category:");
            String str7 = this.f23018g;
            if (str7 == null) {
                sb.append("null");
            } else {
                sb.append(str7);
            }
        }
        if (m3320k()) {
            sb.append(", ");
            sb.append("regId:");
            String str8 = this.f23019h;
            if (str8 == null) {
                sb.append("null");
            } else {
                sb.append(str8);
            }
        }
        if (m3319l()) {
            sb.append(", ");
            sb.append("aliasName:");
            String str9 = this.f23020i;
            if (str9 == null) {
                sb.append("null");
            } else {
                sb.append(str9);
            }
        }
        if (m3318m()) {
            sb.append(", ");
            sb.append("binaryExtra:");
            ByteBuffer byteBuffer = this.f23008a;
            if (byteBuffer == null) {
                sb.append("null");
            } else {
                C11443hr.m3072a(byteBuffer, sb);
            }
        }
        if (m3317n()) {
            sb.append(", ");
            sb.append("createdTs:");
            sb.append(this.f23005a);
        }
        if (m3316o()) {
            sb.append(", ");
            sb.append("alreadyLogClickInXmq:");
            sb.append(this.f23013b);
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    public void m3349a() {
        if (this.f23012b != null) {
            return;
        }
        throw new C11457ib("Required field 'id' was not present! Struct: " + toString());
    }
}
