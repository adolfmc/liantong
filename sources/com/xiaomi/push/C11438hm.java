package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.hm */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11438hm implements InterfaceC11442hq<C11438hm, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public long f23238a;

    /* renamed from: a */
    public C11419gu f23239a;

    /* renamed from: a */
    public String f23240a;

    /* renamed from: a */
    private BitSet f23241a = new BitSet(3);

    /* renamed from: b */
    public long f23242b;

    /* renamed from: b */
    public String f23243b;

    /* renamed from: c */
    public long f23244c;

    /* renamed from: c */
    public String f23245c;

    /* renamed from: d */
    public String f23246d;

    /* renamed from: e */
    public String f23247e;

    /* renamed from: a */
    private static final C11461if f23229a = new C11461if("XmPushActionUnRegistrationResult");

    /* renamed from: a */
    private static final C11452hx f23228a = new C11452hx("", (byte) 11, 1);

    /* renamed from: b */
    private static final C11452hx f23230b = new C11452hx("", (byte) 12, 2);

    /* renamed from: c */
    private static final C11452hx f23231c = new C11452hx("", (byte) 11, 3);

    /* renamed from: d */
    private static final C11452hx f23232d = new C11452hx("", (byte) 11, 4);

    /* renamed from: e */
    private static final C11452hx f23233e = new C11452hx("", (byte) 10, 6);

    /* renamed from: f */
    private static final C11452hx f23234f = new C11452hx("", (byte) 11, 7);

    /* renamed from: g */
    private static final C11452hx f23235g = new C11452hx("", (byte) 11, 8);

    /* renamed from: h */
    private static final C11452hx f23236h = new C11452hx("", (byte) 10, 9);

    /* renamed from: i */
    private static final C11452hx f23237i = new C11452hx("", (byte) 10, 10);

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public boolean m3135a() {
        return this.f23240a != null;
    }

    /* renamed from: b */
    public boolean m3131b() {
        return this.f23239a != null;
    }

    /* renamed from: c */
    public boolean m3129c() {
        return this.f23243b != null;
    }

    /* renamed from: d */
    public boolean m3127d() {
        return this.f23245c != null;
    }

    /* renamed from: e */
    public boolean m3126e() {
        return this.f23241a.get(0);
    }

    /* renamed from: a */
    public void m3132a(boolean z) {
        this.f23241a.set(0, z);
    }

    /* renamed from: f */
    public boolean m3125f() {
        return this.f23246d != null;
    }

    /* renamed from: a */
    public String m3137a() {
        return this.f23247e;
    }

    /* renamed from: g */
    public boolean m3124g() {
        return this.f23247e != null;
    }

    /* renamed from: h */
    public boolean m3123h() {
        return this.f23241a.get(1);
    }

    /* renamed from: b */
    public void m3130b(boolean z) {
        this.f23241a.set(1, z);
    }

    /* renamed from: i */
    public boolean m3122i() {
        return this.f23241a.get(2);
    }

    /* renamed from: c */
    public void m3128c(boolean z) {
        this.f23241a.set(2, z);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11438hm)) {
            return m3133a((C11438hm) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3133a(C11438hm c11438hm) {
        if (c11438hm == null) {
            return false;
        }
        boolean m3135a = m3135a();
        boolean m3135a2 = c11438hm.m3135a();
        if ((m3135a || m3135a2) && !(m3135a && m3135a2 && this.f23240a.equals(c11438hm.f23240a))) {
            return false;
        }
        boolean m3131b = m3131b();
        boolean m3131b2 = c11438hm.m3131b();
        if ((m3131b || m3131b2) && !(m3131b && m3131b2 && this.f23239a.m3514a(c11438hm.f23239a))) {
            return false;
        }
        boolean m3129c = m3129c();
        boolean m3129c2 = c11438hm.m3129c();
        if ((m3129c || m3129c2) && !(m3129c && m3129c2 && this.f23243b.equals(c11438hm.f23243b))) {
            return false;
        }
        boolean m3127d = m3127d();
        boolean m3127d2 = c11438hm.m3127d();
        if (((m3127d || m3127d2) && !(m3127d && m3127d2 && this.f23245c.equals(c11438hm.f23245c))) || this.f23238a != c11438hm.f23238a) {
            return false;
        }
        boolean m3125f = m3125f();
        boolean m3125f2 = c11438hm.m3125f();
        if ((m3125f || m3125f2) && !(m3125f && m3125f2 && this.f23246d.equals(c11438hm.f23246d))) {
            return false;
        }
        boolean m3124g = m3124g();
        boolean m3124g2 = c11438hm.m3124g();
        if ((m3124g || m3124g2) && !(m3124g && m3124g2 && this.f23247e.equals(c11438hm.f23247e))) {
            return false;
        }
        boolean m3123h = m3123h();
        boolean m3123h2 = c11438hm.m3123h();
        if ((m3123h || m3123h2) && !(m3123h && m3123h2 && this.f23242b == c11438hm.f23242b)) {
            return false;
        }
        boolean m3122i = m3122i();
        boolean m3122i2 = c11438hm.m3122i();
        if (m3122i || m3122i2) {
            return m3122i && m3122i2 && this.f23244c == c11438hm.f23244c;
        }
        return true;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11438hm c11438hm) {
        int m3078a;
        int m3078a2;
        int m3076a;
        int m3076a2;
        int m3078a3;
        int m3076a3;
        int m3076a4;
        int m3077a;
        int m3076a5;
        if (!getClass().equals(c11438hm.getClass())) {
            return getClass().getName().compareTo(c11438hm.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3135a()).compareTo(Boolean.valueOf(c11438hm.m3135a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3135a() || (m3076a5 = C11443hr.m3076a(this.f23240a, c11438hm.f23240a)) == 0) {
            int compareTo2 = Boolean.valueOf(m3131b()).compareTo(Boolean.valueOf(c11438hm.m3131b()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (!m3131b() || (m3077a = C11443hr.m3077a(this.f23239a, c11438hm.f23239a)) == 0) {
                int compareTo3 = Boolean.valueOf(m3129c()).compareTo(Boolean.valueOf(c11438hm.m3129c()));
                if (compareTo3 != 0) {
                    return compareTo3;
                }
                if (!m3129c() || (m3076a4 = C11443hr.m3076a(this.f23243b, c11438hm.f23243b)) == 0) {
                    int compareTo4 = Boolean.valueOf(m3127d()).compareTo(Boolean.valueOf(c11438hm.m3127d()));
                    if (compareTo4 != 0) {
                        return compareTo4;
                    }
                    if (!m3127d() || (m3076a3 = C11443hr.m3076a(this.f23245c, c11438hm.f23245c)) == 0) {
                        int compareTo5 = Boolean.valueOf(m3126e()).compareTo(Boolean.valueOf(c11438hm.m3126e()));
                        if (compareTo5 != 0) {
                            return compareTo5;
                        }
                        if (!m3126e() || (m3078a3 = C11443hr.m3078a(this.f23238a, c11438hm.f23238a)) == 0) {
                            int compareTo6 = Boolean.valueOf(m3125f()).compareTo(Boolean.valueOf(c11438hm.m3125f()));
                            if (compareTo6 != 0) {
                                return compareTo6;
                            }
                            if (!m3125f() || (m3076a2 = C11443hr.m3076a(this.f23246d, c11438hm.f23246d)) == 0) {
                                int compareTo7 = Boolean.valueOf(m3124g()).compareTo(Boolean.valueOf(c11438hm.m3124g()));
                                if (compareTo7 != 0) {
                                    return compareTo7;
                                }
                                if (!m3124g() || (m3076a = C11443hr.m3076a(this.f23247e, c11438hm.f23247e)) == 0) {
                                    int compareTo8 = Boolean.valueOf(m3123h()).compareTo(Boolean.valueOf(c11438hm.m3123h()));
                                    if (compareTo8 != 0) {
                                        return compareTo8;
                                    }
                                    if (!m3123h() || (m3078a2 = C11443hr.m3078a(this.f23242b, c11438hm.f23242b)) == 0) {
                                        int compareTo9 = Boolean.valueOf(m3122i()).compareTo(Boolean.valueOf(c11438hm.m3122i()));
                                        if (compareTo9 != 0) {
                                            return compareTo9;
                                        }
                                        if (!m3122i() || (m3078a = C11443hr.m3078a(this.f23244c, c11438hm.f23244c)) == 0) {
                                            return 0;
                                        }
                                        return m3078a;
                                    }
                                    return m3078a2;
                                }
                                return m3076a;
                            }
                            return m3076a2;
                        }
                        return m3078a3;
                    }
                    return m3076a3;
                }
                return m3076a4;
            }
            return m3077a;
        }
        return m3076a5;
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
                            this.f23240a = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 2:
                        if (mo3021a.f23307a == 12) {
                            this.f23239a = new C11419gu();
                            this.f23239a.mo3083a(abstractC11456ia);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 3:
                        if (mo3021a.f23307a == 11) {
                            this.f23243b = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 4:
                        if (mo3021a.f23307a == 11) {
                            this.f23245c = abstractC11456ia.mo2990a();
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
                            this.f23238a = abstractC11456ia.mo3022a();
                            m3132a(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 7:
                        if (mo3021a.f23307a == 11) {
                            this.f23246d = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 8:
                        if (mo3021a.f23307a == 11) {
                            this.f23247e = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 9:
                        if (mo3021a.f23307a == 10) {
                            this.f23242b = abstractC11456ia.mo3022a();
                            m3130b(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 10:
                        if (mo3021a.f23307a == 10) {
                            this.f23244c = abstractC11456ia.mo3022a();
                            m3128c(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                }
                abstractC11456ia.mo3000g();
            } else {
                abstractC11456ia.mo3001f();
                if (!m3126e()) {
                    throw new C11457ib("Required field 'errorCode' was not found in serialized data! Struct: " + toString());
                }
                m3136a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3136a();
        abstractC11456ia.mo3010a(f23229a);
        if (this.f23240a != null && m3135a()) {
            abstractC11456ia.mo3013a(f23228a);
            abstractC11456ia.mo3009a(this.f23240a);
            abstractC11456ia.mo3005b();
        }
        if (this.f23239a != null && m3131b()) {
            abstractC11456ia.mo3013a(f23230b);
            this.f23239a.mo3082b(abstractC11456ia);
            abstractC11456ia.mo3005b();
        }
        if (this.f23243b != null) {
            abstractC11456ia.mo3013a(f23231c);
            abstractC11456ia.mo3009a(this.f23243b);
            abstractC11456ia.mo3005b();
        }
        if (this.f23245c != null) {
            abstractC11456ia.mo3013a(f23232d);
            abstractC11456ia.mo3009a(this.f23245c);
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3013a(f23233e);
        abstractC11456ia.mo3014a(this.f23238a);
        abstractC11456ia.mo3005b();
        if (this.f23246d != null && m3125f()) {
            abstractC11456ia.mo3013a(f23234f);
            abstractC11456ia.mo3009a(this.f23246d);
            abstractC11456ia.mo3005b();
        }
        if (this.f23247e != null && m3124g()) {
            abstractC11456ia.mo3013a(f23235g);
            abstractC11456ia.mo3009a(this.f23247e);
            abstractC11456ia.mo3005b();
        }
        if (m3123h()) {
            abstractC11456ia.mo3013a(f23236h);
            abstractC11456ia.mo3014a(this.f23242b);
            abstractC11456ia.mo3005b();
        }
        if (m3122i()) {
            abstractC11456ia.mo3013a(f23237i);
            abstractC11456ia.mo3014a(this.f23244c);
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionUnRegistrationResult(");
        if (m3135a()) {
            sb.append("debug:");
            String str = this.f23240a;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (m3131b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            C11419gu c11419gu = this.f23239a;
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
        String str2 = this.f23243b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("appId:");
        String str3 = this.f23245c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("errorCode:");
        sb.append(this.f23238a);
        if (m3125f()) {
            sb.append(", ");
            sb.append("reason:");
            String str4 = this.f23246d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m3124g()) {
            sb.append(", ");
            sb.append("packageName:");
            String str5 = this.f23247e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m3123h()) {
            sb.append(", ");
            sb.append("unRegisteredAt:");
            sb.append(this.f23242b);
        }
        if (m3122i()) {
            sb.append(", ");
            sb.append("costTime:");
            sb.append(this.f23244c);
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    public void m3136a() {
        if (this.f23243b == null) {
            throw new C11457ib("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f23245c != null) {
        } else {
            throw new C11457ib("Required field 'appId' was not present! Struct: " + toString());
        }
    }
}
