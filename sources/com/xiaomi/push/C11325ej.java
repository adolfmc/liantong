package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.ej */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11325ej implements InterfaceC11442hq<C11325ej, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public byte f22133a;

    /* renamed from: a */
    public int f22134a;

    /* renamed from: a */
    public String f22135a;

    /* renamed from: a */
    private BitSet f22136a = new BitSet(6);

    /* renamed from: b */
    public int f22137b;

    /* renamed from: b */
    public String f22138b;

    /* renamed from: c */
    public int f22139c;

    /* renamed from: c */
    public String f22140c;

    /* renamed from: d */
    public int f22141d;

    /* renamed from: d */
    public String f22142d;

    /* renamed from: e */
    public int f22143e;

    /* renamed from: a */
    private static final C11461if f22123a = new C11461if("StatsEvent");

    /* renamed from: a */
    private static final C11452hx f22122a = new C11452hx("", (byte) 3, 1);

    /* renamed from: b */
    private static final C11452hx f22124b = new C11452hx("", (byte) 8, 2);

    /* renamed from: c */
    private static final C11452hx f22125c = new C11452hx("", (byte) 8, 3);

    /* renamed from: d */
    private static final C11452hx f22126d = new C11452hx("", (byte) 11, 4);

    /* renamed from: e */
    private static final C11452hx f22127e = new C11452hx("", (byte) 11, 5);

    /* renamed from: f */
    private static final C11452hx f22128f = new C11452hx("", (byte) 8, 6);

    /* renamed from: g */
    private static final C11452hx f22129g = new C11452hx("", (byte) 11, 7);

    /* renamed from: h */
    private static final C11452hx f22130h = new C11452hx("", (byte) 11, 8);

    /* renamed from: i */
    private static final C11452hx f22131i = new C11452hx("", (byte) 8, 9);

    /* renamed from: j */
    private static final C11452hx f22132j = new C11452hx("", (byte) 8, 10);

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public C11325ej m4039a(byte b) {
        this.f22133a = b;
        m4034a(true);
        return this;
    }

    /* renamed from: a */
    public boolean m4040a() {
        return this.f22136a.get(0);
    }

    /* renamed from: a */
    public void m4034a(boolean z) {
        this.f22136a.set(0, z);
    }

    /* renamed from: a */
    public C11325ej m4038a(int i) {
        this.f22134a = i;
        m4030b(true);
        return this;
    }

    /* renamed from: b */
    public boolean m4033b() {
        return this.f22136a.get(1);
    }

    /* renamed from: b */
    public void m4030b(boolean z) {
        this.f22136a.set(1, z);
    }

    /* renamed from: b */
    public C11325ej m4032b(int i) {
        this.f22137b = i;
        m4026c(true);
        return this;
    }

    /* renamed from: c */
    public boolean m4029c() {
        return this.f22136a.get(2);
    }

    /* renamed from: c */
    public void m4026c(boolean z) {
        this.f22136a.set(2, z);
    }

    /* renamed from: a */
    public C11325ej m4035a(String str) {
        this.f22135a = str;
        return this;
    }

    /* renamed from: d */
    public boolean m4025d() {
        return this.f22135a != null;
    }

    /* renamed from: b */
    public C11325ej m4031b(String str) {
        this.f22138b = str;
        return this;
    }

    /* renamed from: e */
    public boolean m4021e() {
        return this.f22138b != null;
    }

    /* renamed from: c */
    public C11325ej m4028c(int i) {
        this.f22139c = i;
        m4022d(true);
        return this;
    }

    /* renamed from: f */
    public boolean m4019f() {
        return this.f22136a.get(3);
    }

    /* renamed from: d */
    public void m4022d(boolean z) {
        this.f22136a.set(3, z);
    }

    /* renamed from: c */
    public C11325ej m4027c(String str) {
        this.f22140c = str;
        return this;
    }

    /* renamed from: g */
    public boolean m4017g() {
        return this.f22140c != null;
    }

    /* renamed from: d */
    public C11325ej m4023d(String str) {
        this.f22142d = str;
        return this;
    }

    /* renamed from: h */
    public boolean m4016h() {
        return this.f22142d != null;
    }

    /* renamed from: d */
    public C11325ej m4024d(int i) {
        this.f22141d = i;
        m4020e(true);
        return this;
    }

    /* renamed from: i */
    public boolean m4015i() {
        return this.f22136a.get(4);
    }

    /* renamed from: e */
    public void m4020e(boolean z) {
        this.f22136a.set(4, z);
    }

    /* renamed from: j */
    public boolean m4014j() {
        return this.f22136a.get(5);
    }

    /* renamed from: f */
    public void m4018f(boolean z) {
        this.f22136a.set(5, z);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11325ej)) {
            return m4036a((C11325ej) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m4036a(C11325ej c11325ej) {
        if (c11325ej != null && this.f22133a == c11325ej.f22133a && this.f22134a == c11325ej.f22134a && this.f22137b == c11325ej.f22137b) {
            boolean m4025d = m4025d();
            boolean m4025d2 = c11325ej.m4025d();
            if ((m4025d || m4025d2) && !(m4025d && m4025d2 && this.f22135a.equals(c11325ej.f22135a))) {
                return false;
            }
            boolean m4021e = m4021e();
            boolean m4021e2 = c11325ej.m4021e();
            if ((m4021e || m4021e2) && !(m4021e && m4021e2 && this.f22138b.equals(c11325ej.f22138b))) {
                return false;
            }
            boolean m4019f = m4019f();
            boolean m4019f2 = c11325ej.m4019f();
            if ((m4019f || m4019f2) && !(m4019f && m4019f2 && this.f22139c == c11325ej.f22139c)) {
                return false;
            }
            boolean m4017g = m4017g();
            boolean m4017g2 = c11325ej.m4017g();
            if ((m4017g || m4017g2) && !(m4017g && m4017g2 && this.f22140c.equals(c11325ej.f22140c))) {
                return false;
            }
            boolean m4016h = m4016h();
            boolean m4016h2 = c11325ej.m4016h();
            if ((m4016h || m4016h2) && !(m4016h && m4016h2 && this.f22142d.equals(c11325ej.f22142d))) {
                return false;
            }
            boolean m4015i = m4015i();
            boolean m4015i2 = c11325ej.m4015i();
            if ((m4015i || m4015i2) && !(m4015i && m4015i2 && this.f22141d == c11325ej.f22141d)) {
                return false;
            }
            boolean m4014j = m4014j();
            boolean m4014j2 = c11325ej.m4014j();
            if (m4014j || m4014j2) {
                return m4014j && m4014j2 && this.f22143e == c11325ej.f22143e;
            }
            return true;
        }
        return false;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11325ej c11325ej) {
        int m3079a;
        int m3079a2;
        int m3076a;
        int m3076a2;
        int m3079a3;
        int m3076a3;
        int m3076a4;
        int m3079a4;
        int m3079a5;
        int m3080a;
        if (!getClass().equals(c11325ej.getClass())) {
            return getClass().getName().compareTo(c11325ej.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m4040a()).compareTo(Boolean.valueOf(c11325ej.m4040a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m4040a() || (m3080a = C11443hr.m3080a(this.f22133a, c11325ej.f22133a)) == 0) {
            int compareTo2 = Boolean.valueOf(m4033b()).compareTo(Boolean.valueOf(c11325ej.m4033b()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (!m4033b() || (m3079a5 = C11443hr.m3079a(this.f22134a, c11325ej.f22134a)) == 0) {
                int compareTo3 = Boolean.valueOf(m4029c()).compareTo(Boolean.valueOf(c11325ej.m4029c()));
                if (compareTo3 != 0) {
                    return compareTo3;
                }
                if (!m4029c() || (m3079a4 = C11443hr.m3079a(this.f22137b, c11325ej.f22137b)) == 0) {
                    int compareTo4 = Boolean.valueOf(m4025d()).compareTo(Boolean.valueOf(c11325ej.m4025d()));
                    if (compareTo4 != 0) {
                        return compareTo4;
                    }
                    if (!m4025d() || (m3076a4 = C11443hr.m3076a(this.f22135a, c11325ej.f22135a)) == 0) {
                        int compareTo5 = Boolean.valueOf(m4021e()).compareTo(Boolean.valueOf(c11325ej.m4021e()));
                        if (compareTo5 != 0) {
                            return compareTo5;
                        }
                        if (!m4021e() || (m3076a3 = C11443hr.m3076a(this.f22138b, c11325ej.f22138b)) == 0) {
                            int compareTo6 = Boolean.valueOf(m4019f()).compareTo(Boolean.valueOf(c11325ej.m4019f()));
                            if (compareTo6 != 0) {
                                return compareTo6;
                            }
                            if (!m4019f() || (m3079a3 = C11443hr.m3079a(this.f22139c, c11325ej.f22139c)) == 0) {
                                int compareTo7 = Boolean.valueOf(m4017g()).compareTo(Boolean.valueOf(c11325ej.m4017g()));
                                if (compareTo7 != 0) {
                                    return compareTo7;
                                }
                                if (!m4017g() || (m3076a2 = C11443hr.m3076a(this.f22140c, c11325ej.f22140c)) == 0) {
                                    int compareTo8 = Boolean.valueOf(m4016h()).compareTo(Boolean.valueOf(c11325ej.m4016h()));
                                    if (compareTo8 != 0) {
                                        return compareTo8;
                                    }
                                    if (!m4016h() || (m3076a = C11443hr.m3076a(this.f22142d, c11325ej.f22142d)) == 0) {
                                        int compareTo9 = Boolean.valueOf(m4015i()).compareTo(Boolean.valueOf(c11325ej.m4015i()));
                                        if (compareTo9 != 0) {
                                            return compareTo9;
                                        }
                                        if (!m4015i() || (m3079a2 = C11443hr.m3079a(this.f22141d, c11325ej.f22141d)) == 0) {
                                            int compareTo10 = Boolean.valueOf(m4014j()).compareTo(Boolean.valueOf(c11325ej.m4014j()));
                                            if (compareTo10 != 0) {
                                                return compareTo10;
                                            }
                                            if (!m4014j() || (m3079a = C11443hr.m3079a(this.f22143e, c11325ej.f22143e)) == 0) {
                                                return 0;
                                            }
                                            return m3079a;
                                        }
                                        return m3079a2;
                                    }
                                    return m3076a;
                                }
                                return m3076a2;
                            }
                            return m3079a3;
                        }
                        return m3076a3;
                    }
                    return m3076a4;
                }
                return m3079a4;
            }
            return m3079a5;
        }
        return m3080a;
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
                        if (mo3021a.f23307a == 3) {
                            this.f22133a = abstractC11456ia.mo3025a();
                            m4034a(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 2:
                        if (mo3021a.f23307a == 8) {
                            this.f22134a = abstractC11456ia.mo3023a();
                            m4030b(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 3:
                        if (mo3021a.f23307a == 8) {
                            this.f22137b = abstractC11456ia.mo3023a();
                            m4026c(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 4:
                        if (mo3021a.f23307a == 11) {
                            this.f22135a = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 5:
                        if (mo3021a.f23307a == 11) {
                            this.f22138b = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 6:
                        if (mo3021a.f23307a == 8) {
                            this.f22139c = abstractC11456ia.mo3023a();
                            m4022d(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 7:
                        if (mo3021a.f23307a == 11) {
                            this.f22140c = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 8:
                        if (mo3021a.f23307a == 11) {
                            this.f22142d = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 9:
                        if (mo3021a.f23307a == 8) {
                            this.f22141d = abstractC11456ia.mo3023a();
                            m4020e(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 10:
                        if (mo3021a.f23307a == 8) {
                            this.f22143e = abstractC11456ia.mo3023a();
                            m4018f(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    default:
                        C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                        break;
                }
                abstractC11456ia.mo3000g();
            } else {
                abstractC11456ia.mo3001f();
                if (!m4040a()) {
                    throw new C11457ib("Required field 'chid' was not found in serialized data! Struct: " + toString());
                } else if (!m4033b()) {
                    throw new C11457ib("Required field 'type' was not found in serialized data! Struct: " + toString());
                } else if (!m4029c()) {
                    throw new C11457ib("Required field 'value' was not found in serialized data! Struct: " + toString());
                } else {
                    m4041a();
                    return;
                }
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m4041a();
        abstractC11456ia.mo3010a(f22123a);
        abstractC11456ia.mo3013a(f22122a);
        abstractC11456ia.mo3016a(this.f22133a);
        abstractC11456ia.mo3005b();
        abstractC11456ia.mo3013a(f22124b);
        abstractC11456ia.mo3015a(this.f22134a);
        abstractC11456ia.mo3005b();
        abstractC11456ia.mo3013a(f22125c);
        abstractC11456ia.mo3015a(this.f22137b);
        abstractC11456ia.mo3005b();
        if (this.f22135a != null) {
            abstractC11456ia.mo3013a(f22126d);
            abstractC11456ia.mo3009a(this.f22135a);
            abstractC11456ia.mo3005b();
        }
        if (this.f22138b != null && m4021e()) {
            abstractC11456ia.mo3013a(f22127e);
            abstractC11456ia.mo3009a(this.f22138b);
            abstractC11456ia.mo3005b();
        }
        if (m4019f()) {
            abstractC11456ia.mo3013a(f22128f);
            abstractC11456ia.mo3015a(this.f22139c);
            abstractC11456ia.mo3005b();
        }
        if (this.f22140c != null && m4017g()) {
            abstractC11456ia.mo3013a(f22129g);
            abstractC11456ia.mo3009a(this.f22140c);
            abstractC11456ia.mo3005b();
        }
        if (this.f22142d != null && m4016h()) {
            abstractC11456ia.mo3013a(f22130h);
            abstractC11456ia.mo3009a(this.f22142d);
            abstractC11456ia.mo3005b();
        }
        if (m4015i()) {
            abstractC11456ia.mo3013a(f22131i);
            abstractC11456ia.mo3015a(this.f22141d);
            abstractC11456ia.mo3005b();
        }
        if (m4014j()) {
            abstractC11456ia.mo3013a(f22132j);
            abstractC11456ia.mo3015a(this.f22143e);
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("StatsEvent(");
        sb.append("chid:");
        sb.append((int) this.f22133a);
        sb.append(", ");
        sb.append("type:");
        sb.append(this.f22134a);
        sb.append(", ");
        sb.append("value:");
        sb.append(this.f22137b);
        sb.append(", ");
        sb.append("connpt:");
        String str = this.f22135a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        if (m4021e()) {
            sb.append(", ");
            sb.append("host:");
            String str2 = this.f22138b;
            if (str2 == null) {
                sb.append("null");
            } else {
                sb.append(str2);
            }
        }
        if (m4019f()) {
            sb.append(", ");
            sb.append("subvalue:");
            sb.append(this.f22139c);
        }
        if (m4017g()) {
            sb.append(", ");
            sb.append("annotation:");
            String str3 = this.f22140c;
            if (str3 == null) {
                sb.append("null");
            } else {
                sb.append(str3);
            }
        }
        if (m4016h()) {
            sb.append(", ");
            sb.append("user:");
            String str4 = this.f22142d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m4015i()) {
            sb.append(", ");
            sb.append("time:");
            sb.append(this.f22141d);
        }
        if (m4014j()) {
            sb.append(", ");
            sb.append("clientIp:");
            sb.append(this.f22143e);
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    public void m4041a() {
        if (this.f22135a != null) {
            return;
        }
        throw new C11457ib("Required field 'connpt' was not present! Struct: " + toString());
    }
}
