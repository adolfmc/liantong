package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.hl */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11437hl implements InterfaceC11442hq<C11437hl, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public long f23215a;

    /* renamed from: a */
    public C11419gu f23216a;

    /* renamed from: a */
    public String f23217a;

    /* renamed from: a */
    private BitSet f23218a = new BitSet(2);

    /* renamed from: a */
    public boolean f23219a = true;

    /* renamed from: b */
    public String f23220b;

    /* renamed from: c */
    public String f23221c;

    /* renamed from: d */
    public String f23222d;

    /* renamed from: e */
    public String f23223e;

    /* renamed from: f */
    public String f23224f;

    /* renamed from: g */
    public String f23225g;

    /* renamed from: h */
    public String f23226h;

    /* renamed from: i */
    public String f23227i;

    /* renamed from: a */
    private static final C11461if f23203a = new C11461if("XmPushActionUnRegistration");

    /* renamed from: a */
    private static final C11452hx f23202a = new C11452hx("", (byte) 11, 1);

    /* renamed from: b */
    private static final C11452hx f23204b = new C11452hx("", (byte) 12, 2);

    /* renamed from: c */
    private static final C11452hx f23205c = new C11452hx("", (byte) 11, 3);

    /* renamed from: d */
    private static final C11452hx f23206d = new C11452hx("", (byte) 11, 4);

    /* renamed from: e */
    private static final C11452hx f23207e = new C11452hx("", (byte) 11, 5);

    /* renamed from: f */
    private static final C11452hx f23208f = new C11452hx("", (byte) 11, 6);

    /* renamed from: g */
    private static final C11452hx f23209g = new C11452hx("", (byte) 11, 7);

    /* renamed from: h */
    private static final C11452hx f23210h = new C11452hx("", (byte) 11, 8);

    /* renamed from: i */
    private static final C11452hx f23211i = new C11452hx("", (byte) 11, 9);

    /* renamed from: j */
    private static final C11452hx f23212j = new C11452hx("", (byte) 11, 10);

    /* renamed from: k */
    private static final C11452hx f23213k = new C11452hx("", (byte) 2, 11);

    /* renamed from: l */
    private static final C11452hx f23214l = new C11452hx("", (byte) 10, 12);

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public boolean m3158a() {
        return this.f23217a != null;
    }

    /* renamed from: b */
    public boolean m3153b() {
        return this.f23216a != null;
    }

    /* renamed from: a */
    public C11437hl m3155a(String str) {
        this.f23220b = str;
        return this;
    }

    /* renamed from: c */
    public boolean m3150c() {
        return this.f23220b != null;
    }

    /* renamed from: b */
    public C11437hl m3152b(String str) {
        this.f23221c = str;
        return this;
    }

    /* renamed from: d */
    public boolean m3148d() {
        return this.f23221c != null;
    }

    /* renamed from: c */
    public C11437hl m3149c(String str) {
        this.f23222d = str;
        return this;
    }

    /* renamed from: e */
    public boolean m3146e() {
        return this.f23222d != null;
    }

    /* renamed from: f */
    public boolean m3144f() {
        return this.f23223e != null;
    }

    /* renamed from: d */
    public C11437hl m3147d(String str) {
        this.f23224f = str;
        return this;
    }

    /* renamed from: g */
    public boolean m3143g() {
        return this.f23224f != null;
    }

    /* renamed from: e */
    public C11437hl m3145e(String str) {
        this.f23225g = str;
        return this;
    }

    /* renamed from: h */
    public boolean m3142h() {
        return this.f23225g != null;
    }

    /* renamed from: i */
    public boolean m3141i() {
        return this.f23226h != null;
    }

    /* renamed from: j */
    public boolean m3140j() {
        return this.f23227i != null;
    }

    /* renamed from: k */
    public boolean m3139k() {
        return this.f23218a.get(0);
    }

    /* renamed from: a */
    public void m3154a(boolean z) {
        this.f23218a.set(0, z);
    }

    /* renamed from: l */
    public boolean m3138l() {
        return this.f23218a.get(1);
    }

    /* renamed from: b */
    public void m3151b(boolean z) {
        this.f23218a.set(1, z);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11437hl)) {
            return m3156a((C11437hl) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3156a(C11437hl c11437hl) {
        if (c11437hl == null) {
            return false;
        }
        boolean m3158a = m3158a();
        boolean m3158a2 = c11437hl.m3158a();
        if ((m3158a || m3158a2) && !(m3158a && m3158a2 && this.f23217a.equals(c11437hl.f23217a))) {
            return false;
        }
        boolean m3153b = m3153b();
        boolean m3153b2 = c11437hl.m3153b();
        if ((m3153b || m3153b2) && !(m3153b && m3153b2 && this.f23216a.m3514a(c11437hl.f23216a))) {
            return false;
        }
        boolean m3150c = m3150c();
        boolean m3150c2 = c11437hl.m3150c();
        if ((m3150c || m3150c2) && !(m3150c && m3150c2 && this.f23220b.equals(c11437hl.f23220b))) {
            return false;
        }
        boolean m3148d = m3148d();
        boolean m3148d2 = c11437hl.m3148d();
        if ((m3148d || m3148d2) && !(m3148d && m3148d2 && this.f23221c.equals(c11437hl.f23221c))) {
            return false;
        }
        boolean m3146e = m3146e();
        boolean m3146e2 = c11437hl.m3146e();
        if ((m3146e || m3146e2) && !(m3146e && m3146e2 && this.f23222d.equals(c11437hl.f23222d))) {
            return false;
        }
        boolean m3144f = m3144f();
        boolean m3144f2 = c11437hl.m3144f();
        if ((m3144f || m3144f2) && !(m3144f && m3144f2 && this.f23223e.equals(c11437hl.f23223e))) {
            return false;
        }
        boolean m3143g = m3143g();
        boolean m3143g2 = c11437hl.m3143g();
        if ((m3143g || m3143g2) && !(m3143g && m3143g2 && this.f23224f.equals(c11437hl.f23224f))) {
            return false;
        }
        boolean m3142h = m3142h();
        boolean m3142h2 = c11437hl.m3142h();
        if ((m3142h || m3142h2) && !(m3142h && m3142h2 && this.f23225g.equals(c11437hl.f23225g))) {
            return false;
        }
        boolean m3141i = m3141i();
        boolean m3141i2 = c11437hl.m3141i();
        if ((m3141i || m3141i2) && !(m3141i && m3141i2 && this.f23226h.equals(c11437hl.f23226h))) {
            return false;
        }
        boolean m3140j = m3140j();
        boolean m3140j2 = c11437hl.m3140j();
        if ((m3140j || m3140j2) && !(m3140j && m3140j2 && this.f23227i.equals(c11437hl.f23227i))) {
            return false;
        }
        boolean m3139k = m3139k();
        boolean m3139k2 = c11437hl.m3139k();
        if ((m3139k || m3139k2) && !(m3139k && m3139k2 && this.f23219a == c11437hl.f23219a)) {
            return false;
        }
        boolean m3138l = m3138l();
        boolean m3138l2 = c11437hl.m3138l();
        if (m3138l || m3138l2) {
            return m3138l && m3138l2 && this.f23215a == c11437hl.f23215a;
        }
        return true;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11437hl c11437hl) {
        int m3078a;
        int m3066a;
        int m3076a;
        int m3076a2;
        int m3076a3;
        int m3076a4;
        int m3076a5;
        int m3076a6;
        int m3076a7;
        int m3076a8;
        int m3077a;
        int m3076a9;
        if (!getClass().equals(c11437hl.getClass())) {
            return getClass().getName().compareTo(c11437hl.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3158a()).compareTo(Boolean.valueOf(c11437hl.m3158a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3158a() || (m3076a9 = C11443hr.m3076a(this.f23217a, c11437hl.f23217a)) == 0) {
            int compareTo2 = Boolean.valueOf(m3153b()).compareTo(Boolean.valueOf(c11437hl.m3153b()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (!m3153b() || (m3077a = C11443hr.m3077a(this.f23216a, c11437hl.f23216a)) == 0) {
                int compareTo3 = Boolean.valueOf(m3150c()).compareTo(Boolean.valueOf(c11437hl.m3150c()));
                if (compareTo3 != 0) {
                    return compareTo3;
                }
                if (!m3150c() || (m3076a8 = C11443hr.m3076a(this.f23220b, c11437hl.f23220b)) == 0) {
                    int compareTo4 = Boolean.valueOf(m3148d()).compareTo(Boolean.valueOf(c11437hl.m3148d()));
                    if (compareTo4 != 0) {
                        return compareTo4;
                    }
                    if (!m3148d() || (m3076a7 = C11443hr.m3076a(this.f23221c, c11437hl.f23221c)) == 0) {
                        int compareTo5 = Boolean.valueOf(m3146e()).compareTo(Boolean.valueOf(c11437hl.m3146e()));
                        if (compareTo5 != 0) {
                            return compareTo5;
                        }
                        if (!m3146e() || (m3076a6 = C11443hr.m3076a(this.f23222d, c11437hl.f23222d)) == 0) {
                            int compareTo6 = Boolean.valueOf(m3144f()).compareTo(Boolean.valueOf(c11437hl.m3144f()));
                            if (compareTo6 != 0) {
                                return compareTo6;
                            }
                            if (!m3144f() || (m3076a5 = C11443hr.m3076a(this.f23223e, c11437hl.f23223e)) == 0) {
                                int compareTo7 = Boolean.valueOf(m3143g()).compareTo(Boolean.valueOf(c11437hl.m3143g()));
                                if (compareTo7 != 0) {
                                    return compareTo7;
                                }
                                if (!m3143g() || (m3076a4 = C11443hr.m3076a(this.f23224f, c11437hl.f23224f)) == 0) {
                                    int compareTo8 = Boolean.valueOf(m3142h()).compareTo(Boolean.valueOf(c11437hl.m3142h()));
                                    if (compareTo8 != 0) {
                                        return compareTo8;
                                    }
                                    if (!m3142h() || (m3076a3 = C11443hr.m3076a(this.f23225g, c11437hl.f23225g)) == 0) {
                                        int compareTo9 = Boolean.valueOf(m3141i()).compareTo(Boolean.valueOf(c11437hl.m3141i()));
                                        if (compareTo9 != 0) {
                                            return compareTo9;
                                        }
                                        if (!m3141i() || (m3076a2 = C11443hr.m3076a(this.f23226h, c11437hl.f23226h)) == 0) {
                                            int compareTo10 = Boolean.valueOf(m3140j()).compareTo(Boolean.valueOf(c11437hl.m3140j()));
                                            if (compareTo10 != 0) {
                                                return compareTo10;
                                            }
                                            if (!m3140j() || (m3076a = C11443hr.m3076a(this.f23227i, c11437hl.f23227i)) == 0) {
                                                int compareTo11 = Boolean.valueOf(m3139k()).compareTo(Boolean.valueOf(c11437hl.m3139k()));
                                                if (compareTo11 != 0) {
                                                    return compareTo11;
                                                }
                                                if (!m3139k() || (m3066a = C11443hr.m3066a(this.f23219a, c11437hl.f23219a)) == 0) {
                                                    int compareTo12 = Boolean.valueOf(m3138l()).compareTo(Boolean.valueOf(c11437hl.m3138l()));
                                                    if (compareTo12 != 0) {
                                                        return compareTo12;
                                                    }
                                                    if (!m3138l() || (m3078a = C11443hr.m3078a(this.f23215a, c11437hl.f23215a)) == 0) {
                                                        return 0;
                                                    }
                                                    return m3078a;
                                                }
                                                return m3066a;
                                            }
                                            return m3076a;
                                        }
                                        return m3076a2;
                                    }
                                    return m3076a3;
                                }
                                return m3076a4;
                            }
                            return m3076a5;
                        }
                        return m3076a6;
                    }
                    return m3076a7;
                }
                return m3076a8;
            }
            return m3077a;
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
                switch (mo3021a.f23309a) {
                    case 1:
                        if (mo3021a.f23307a == 11) {
                            this.f23217a = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 2:
                        if (mo3021a.f23307a == 12) {
                            this.f23216a = new C11419gu();
                            this.f23216a.mo3083a(abstractC11456ia);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 3:
                        if (mo3021a.f23307a == 11) {
                            this.f23220b = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 4:
                        if (mo3021a.f23307a == 11) {
                            this.f23221c = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 5:
                        if (mo3021a.f23307a == 11) {
                            this.f23222d = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 6:
                        if (mo3021a.f23307a == 11) {
                            this.f23223e = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 7:
                        if (mo3021a.f23307a == 11) {
                            this.f23224f = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 8:
                        if (mo3021a.f23307a == 11) {
                            this.f23225g = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 9:
                        if (mo3021a.f23307a == 11) {
                            this.f23226h = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 10:
                        if (mo3021a.f23307a == 11) {
                            this.f23227i = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 11:
                        if (mo3021a.f23307a == 2) {
                            this.f23219a = abstractC11456ia.mo3017a();
                            m3154a(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 12:
                        if (mo3021a.f23307a == 10) {
                            this.f23215a = abstractC11456ia.mo3022a();
                            m3151b(true);
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
                m3159a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3159a();
        abstractC11456ia.mo3010a(f23203a);
        if (this.f23217a != null && m3158a()) {
            abstractC11456ia.mo3013a(f23202a);
            abstractC11456ia.mo3009a(this.f23217a);
            abstractC11456ia.mo3005b();
        }
        if (this.f23216a != null && m3153b()) {
            abstractC11456ia.mo3013a(f23204b);
            this.f23216a.mo3082b(abstractC11456ia);
            abstractC11456ia.mo3005b();
        }
        if (this.f23220b != null) {
            abstractC11456ia.mo3013a(f23205c);
            abstractC11456ia.mo3009a(this.f23220b);
            abstractC11456ia.mo3005b();
        }
        if (this.f23221c != null) {
            abstractC11456ia.mo3013a(f23206d);
            abstractC11456ia.mo3009a(this.f23221c);
            abstractC11456ia.mo3005b();
        }
        if (this.f23222d != null && m3146e()) {
            abstractC11456ia.mo3013a(f23207e);
            abstractC11456ia.mo3009a(this.f23222d);
            abstractC11456ia.mo3005b();
        }
        if (this.f23223e != null && m3144f()) {
            abstractC11456ia.mo3013a(f23208f);
            abstractC11456ia.mo3009a(this.f23223e);
            abstractC11456ia.mo3005b();
        }
        if (this.f23224f != null && m3143g()) {
            abstractC11456ia.mo3013a(f23209g);
            abstractC11456ia.mo3009a(this.f23224f);
            abstractC11456ia.mo3005b();
        }
        if (this.f23225g != null && m3142h()) {
            abstractC11456ia.mo3013a(f23210h);
            abstractC11456ia.mo3009a(this.f23225g);
            abstractC11456ia.mo3005b();
        }
        if (this.f23226h != null && m3141i()) {
            abstractC11456ia.mo3013a(f23211i);
            abstractC11456ia.mo3009a(this.f23226h);
            abstractC11456ia.mo3005b();
        }
        if (this.f23227i != null && m3140j()) {
            abstractC11456ia.mo3013a(f23212j);
            abstractC11456ia.mo3009a(this.f23227i);
            abstractC11456ia.mo3005b();
        }
        if (m3139k()) {
            abstractC11456ia.mo3013a(f23213k);
            abstractC11456ia.mo3006a(this.f23219a);
            abstractC11456ia.mo3005b();
        }
        if (m3138l()) {
            abstractC11456ia.mo3013a(f23214l);
            abstractC11456ia.mo3014a(this.f23215a);
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionUnRegistration(");
        if (m3158a()) {
            sb.append("debug:");
            String str = this.f23217a;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (m3153b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            C11419gu c11419gu = this.f23216a;
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
        String str2 = this.f23220b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("appId:");
        String str3 = this.f23221c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        if (m3146e()) {
            sb.append(", ");
            sb.append("regId:");
            String str4 = this.f23222d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m3144f()) {
            sb.append(", ");
            sb.append("appVersion:");
            String str5 = this.f23223e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m3143g()) {
            sb.append(", ");
            sb.append("packageName:");
            String str6 = this.f23224f;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
        }
        if (m3142h()) {
            sb.append(", ");
            sb.append("token:");
            String str7 = this.f23225g;
            if (str7 == null) {
                sb.append("null");
            } else {
                sb.append(str7);
            }
        }
        if (m3141i()) {
            sb.append(", ");
            sb.append("deviceId:");
            String str8 = this.f23226h;
            if (str8 == null) {
                sb.append("null");
            } else {
                sb.append(str8);
            }
        }
        if (m3140j()) {
            sb.append(", ");
            sb.append("aliasName:");
            String str9 = this.f23227i;
            if (str9 == null) {
                sb.append("null");
            } else {
                sb.append(str9);
            }
        }
        if (m3139k()) {
            sb.append(", ");
            sb.append("needAck:");
            sb.append(this.f23219a);
        }
        if (m3138l()) {
            sb.append(", ");
            sb.append("createdTs:");
            sb.append(this.f23215a);
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    public void m3159a() {
        if (this.f23220b == null) {
            throw new C11457ib("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f23221c != null) {
        } else {
            throw new C11457ib("Required field 'appId' was not present! Struct: " + toString());
        }
    }
}
