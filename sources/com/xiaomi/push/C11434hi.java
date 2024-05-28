package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.xiaomi.push.hi */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11434hi implements InterfaceC11442hq<C11434hi, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public C11416gr f23152a;

    /* renamed from: a */
    public C11419gu f23153a;

    /* renamed from: a */
    public String f23154a;

    /* renamed from: a */
    public Map<String, String> f23156a;

    /* renamed from: b */
    public String f23158b;

    /* renamed from: c */
    public String f23159c;

    /* renamed from: d */
    public String f23160d;

    /* renamed from: e */
    public String f23161e;

    /* renamed from: f */
    public String f23162f;

    /* renamed from: g */
    public String f23163g;

    /* renamed from: h */
    public String f23164h;

    /* renamed from: a */
    private static final C11461if f23140a = new C11461if("XmPushActionSendMessage");

    /* renamed from: a */
    private static final C11452hx f23139a = new C11452hx("", (byte) 11, 1);

    /* renamed from: b */
    private static final C11452hx f23141b = new C11452hx("", (byte) 12, 2);

    /* renamed from: c */
    private static final C11452hx f23142c = new C11452hx("", (byte) 11, 3);

    /* renamed from: d */
    private static final C11452hx f23143d = new C11452hx("", (byte) 11, 4);

    /* renamed from: e */
    private static final C11452hx f23144e = new C11452hx("", (byte) 11, 5);

    /* renamed from: f */
    private static final C11452hx f23145f = new C11452hx("", (byte) 11, 6);

    /* renamed from: g */
    private static final C11452hx f23146g = new C11452hx("", (byte) 11, 7);

    /* renamed from: h */
    private static final C11452hx f23147h = new C11452hx("", (byte) 12, 8);

    /* renamed from: i */
    private static final C11452hx f23148i = new C11452hx("", (byte) 2, 9);

    /* renamed from: j */
    private static final C11452hx f23149j = new C11452hx("", (byte) 13, 10);

    /* renamed from: k */
    private static final C11452hx f23150k = new C11452hx("", (byte) 11, 11);

    /* renamed from: l */
    private static final C11452hx f23151l = new C11452hx("", (byte) 11, 12);

    /* renamed from: a */
    private BitSet f23155a = new BitSet(1);

    /* renamed from: a */
    public boolean f23157a = true;

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public boolean m3211a() {
        return this.f23154a != null;
    }

    /* renamed from: b */
    public boolean m3206b() {
        return this.f23153a != null;
    }

    /* renamed from: a */
    public String m3213a() {
        return this.f23158b;
    }

    /* renamed from: c */
    public boolean m3204c() {
        return this.f23158b != null;
    }

    /* renamed from: b */
    public String m3207b() {
        return this.f23159c;
    }

    /* renamed from: d */
    public boolean m3202d() {
        return this.f23159c != null;
    }

    /* renamed from: e */
    public boolean m3200e() {
        return this.f23160d != null;
    }

    /* renamed from: c */
    public String m3205c() {
        return this.f23161e;
    }

    /* renamed from: f */
    public boolean m3198f() {
        return this.f23161e != null;
    }

    /* renamed from: d */
    public String m3203d() {
        return this.f23162f;
    }

    /* renamed from: g */
    public boolean m3197g() {
        return this.f23162f != null;
    }

    /* renamed from: a */
    public C11416gr m3214a() {
        return this.f23152a;
    }

    /* renamed from: h */
    public boolean m3196h() {
        return this.f23152a != null;
    }

    /* renamed from: i */
    public boolean m3195i() {
        return this.f23155a.get(0);
    }

    /* renamed from: a */
    public void m3208a(boolean z) {
        this.f23155a.set(0, z);
    }

    /* renamed from: j */
    public boolean m3194j() {
        return this.f23156a != null;
    }

    /* renamed from: e */
    public String m3201e() {
        return this.f23163g;
    }

    /* renamed from: k */
    public boolean m3193k() {
        return this.f23163g != null;
    }

    /* renamed from: f */
    public String m3199f() {
        return this.f23164h;
    }

    /* renamed from: l */
    public boolean m3192l() {
        return this.f23164h != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11434hi)) {
            return m3209a((C11434hi) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3209a(C11434hi c11434hi) {
        if (c11434hi == null) {
            return false;
        }
        boolean m3211a = m3211a();
        boolean m3211a2 = c11434hi.m3211a();
        if ((m3211a || m3211a2) && !(m3211a && m3211a2 && this.f23154a.equals(c11434hi.f23154a))) {
            return false;
        }
        boolean m3206b = m3206b();
        boolean m3206b2 = c11434hi.m3206b();
        if ((m3206b || m3206b2) && !(m3206b && m3206b2 && this.f23153a.m3514a(c11434hi.f23153a))) {
            return false;
        }
        boolean m3204c = m3204c();
        boolean m3204c2 = c11434hi.m3204c();
        if ((m3204c || m3204c2) && !(m3204c && m3204c2 && this.f23158b.equals(c11434hi.f23158b))) {
            return false;
        }
        boolean m3202d = m3202d();
        boolean m3202d2 = c11434hi.m3202d();
        if ((m3202d || m3202d2) && !(m3202d && m3202d2 && this.f23159c.equals(c11434hi.f23159c))) {
            return false;
        }
        boolean m3200e = m3200e();
        boolean m3200e2 = c11434hi.m3200e();
        if ((m3200e || m3200e2) && !(m3200e && m3200e2 && this.f23160d.equals(c11434hi.f23160d))) {
            return false;
        }
        boolean m3198f = m3198f();
        boolean m3198f2 = c11434hi.m3198f();
        if ((m3198f || m3198f2) && !(m3198f && m3198f2 && this.f23161e.equals(c11434hi.f23161e))) {
            return false;
        }
        boolean m3197g = m3197g();
        boolean m3197g2 = c11434hi.m3197g();
        if ((m3197g || m3197g2) && !(m3197g && m3197g2 && this.f23162f.equals(c11434hi.f23162f))) {
            return false;
        }
        boolean m3196h = m3196h();
        boolean m3196h2 = c11434hi.m3196h();
        if ((m3196h || m3196h2) && !(m3196h && m3196h2 && this.f23152a.m3586a(c11434hi.f23152a))) {
            return false;
        }
        boolean m3195i = m3195i();
        boolean m3195i2 = c11434hi.m3195i();
        if ((m3195i || m3195i2) && !(m3195i && m3195i2 && this.f23157a == c11434hi.f23157a)) {
            return false;
        }
        boolean m3194j = m3194j();
        boolean m3194j2 = c11434hi.m3194j();
        if ((m3194j || m3194j2) && !(m3194j && m3194j2 && this.f23156a.equals(c11434hi.f23156a))) {
            return false;
        }
        boolean m3193k = m3193k();
        boolean m3193k2 = c11434hi.m3193k();
        if ((m3193k || m3193k2) && !(m3193k && m3193k2 && this.f23163g.equals(c11434hi.f23163g))) {
            return false;
        }
        boolean m3192l = m3192l();
        boolean m3192l2 = c11434hi.m3192l();
        if (m3192l || m3192l2) {
            return m3192l && m3192l2 && this.f23164h.equals(c11434hi.f23164h);
        }
        return true;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11434hi c11434hi) {
        int m3076a;
        int m3076a2;
        int m3069a;
        int m3066a;
        int m3077a;
        int m3076a3;
        int m3076a4;
        int m3076a5;
        int m3076a6;
        int m3076a7;
        int m3077a2;
        int m3076a8;
        if (!getClass().equals(c11434hi.getClass())) {
            return getClass().getName().compareTo(c11434hi.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3211a()).compareTo(Boolean.valueOf(c11434hi.m3211a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3211a() || (m3076a8 = C11443hr.m3076a(this.f23154a, c11434hi.f23154a)) == 0) {
            int compareTo2 = Boolean.valueOf(m3206b()).compareTo(Boolean.valueOf(c11434hi.m3206b()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (!m3206b() || (m3077a2 = C11443hr.m3077a(this.f23153a, c11434hi.f23153a)) == 0) {
                int compareTo3 = Boolean.valueOf(m3204c()).compareTo(Boolean.valueOf(c11434hi.m3204c()));
                if (compareTo3 != 0) {
                    return compareTo3;
                }
                if (!m3204c() || (m3076a7 = C11443hr.m3076a(this.f23158b, c11434hi.f23158b)) == 0) {
                    int compareTo4 = Boolean.valueOf(m3202d()).compareTo(Boolean.valueOf(c11434hi.m3202d()));
                    if (compareTo4 != 0) {
                        return compareTo4;
                    }
                    if (!m3202d() || (m3076a6 = C11443hr.m3076a(this.f23159c, c11434hi.f23159c)) == 0) {
                        int compareTo5 = Boolean.valueOf(m3200e()).compareTo(Boolean.valueOf(c11434hi.m3200e()));
                        if (compareTo5 != 0) {
                            return compareTo5;
                        }
                        if (!m3200e() || (m3076a5 = C11443hr.m3076a(this.f23160d, c11434hi.f23160d)) == 0) {
                            int compareTo6 = Boolean.valueOf(m3198f()).compareTo(Boolean.valueOf(c11434hi.m3198f()));
                            if (compareTo6 != 0) {
                                return compareTo6;
                            }
                            if (!m3198f() || (m3076a4 = C11443hr.m3076a(this.f23161e, c11434hi.f23161e)) == 0) {
                                int compareTo7 = Boolean.valueOf(m3197g()).compareTo(Boolean.valueOf(c11434hi.m3197g()));
                                if (compareTo7 != 0) {
                                    return compareTo7;
                                }
                                if (!m3197g() || (m3076a3 = C11443hr.m3076a(this.f23162f, c11434hi.f23162f)) == 0) {
                                    int compareTo8 = Boolean.valueOf(m3196h()).compareTo(Boolean.valueOf(c11434hi.m3196h()));
                                    if (compareTo8 != 0) {
                                        return compareTo8;
                                    }
                                    if (!m3196h() || (m3077a = C11443hr.m3077a(this.f23152a, c11434hi.f23152a)) == 0) {
                                        int compareTo9 = Boolean.valueOf(m3195i()).compareTo(Boolean.valueOf(c11434hi.m3195i()));
                                        if (compareTo9 != 0) {
                                            return compareTo9;
                                        }
                                        if (!m3195i() || (m3066a = C11443hr.m3066a(this.f23157a, c11434hi.f23157a)) == 0) {
                                            int compareTo10 = Boolean.valueOf(m3194j()).compareTo(Boolean.valueOf(c11434hi.m3194j()));
                                            if (compareTo10 != 0) {
                                                return compareTo10;
                                            }
                                            if (!m3194j() || (m3069a = C11443hr.m3069a(this.f23156a, c11434hi.f23156a)) == 0) {
                                                int compareTo11 = Boolean.valueOf(m3193k()).compareTo(Boolean.valueOf(c11434hi.m3193k()));
                                                if (compareTo11 != 0) {
                                                    return compareTo11;
                                                }
                                                if (!m3193k() || (m3076a2 = C11443hr.m3076a(this.f23163g, c11434hi.f23163g)) == 0) {
                                                    int compareTo12 = Boolean.valueOf(m3192l()).compareTo(Boolean.valueOf(c11434hi.m3192l()));
                                                    if (compareTo12 != 0) {
                                                        return compareTo12;
                                                    }
                                                    if (!m3192l() || (m3076a = C11443hr.m3076a(this.f23164h, c11434hi.f23164h)) == 0) {
                                                        return 0;
                                                    }
                                                    return m3076a;
                                                }
                                                return m3076a2;
                                            }
                                            return m3069a;
                                        }
                                        return m3066a;
                                    }
                                    return m3077a;
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
            return m3077a2;
        }
        return m3076a8;
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
                            this.f23154a = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 2:
                        if (mo3021a.f23307a == 12) {
                            this.f23153a = new C11419gu();
                            this.f23153a.mo3083a(abstractC11456ia);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 3:
                        if (mo3021a.f23307a == 11) {
                            this.f23158b = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 4:
                        if (mo3021a.f23307a == 11) {
                            this.f23159c = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 5:
                        if (mo3021a.f23307a == 11) {
                            this.f23160d = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 6:
                        if (mo3021a.f23307a == 11) {
                            this.f23161e = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 7:
                        if (mo3021a.f23307a == 11) {
                            this.f23162f = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 8:
                        if (mo3021a.f23307a == 12) {
                            this.f23152a = new C11416gr();
                            this.f23152a.mo3083a(abstractC11456ia);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 9:
                        if (mo3021a.f23307a == 2) {
                            this.f23157a = abstractC11456ia.mo3017a();
                            m3208a(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 10:
                        if (mo3021a.f23307a == 13) {
                            C11454hz mo2992a = abstractC11456ia.mo2992a();
                            this.f23156a = new HashMap(mo2992a.f23313a * 2);
                            for (int i = 0; i < mo2992a.f23313a; i++) {
                                this.f23156a.put(abstractC11456ia.mo2990a(), abstractC11456ia.mo2990a());
                            }
                            abstractC11456ia.mo2999h();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 11:
                        if (mo3021a.f23307a == 11) {
                            this.f23163g = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 12:
                        if (mo3021a.f23307a == 11) {
                            this.f23164h = abstractC11456ia.mo2990a();
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
                m3212a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3212a();
        abstractC11456ia.mo3010a(f23140a);
        if (this.f23154a != null && m3211a()) {
            abstractC11456ia.mo3013a(f23139a);
            abstractC11456ia.mo3009a(this.f23154a);
            abstractC11456ia.mo3005b();
        }
        if (this.f23153a != null && m3206b()) {
            abstractC11456ia.mo3013a(f23141b);
            this.f23153a.mo3082b(abstractC11456ia);
            abstractC11456ia.mo3005b();
        }
        if (this.f23158b != null) {
            abstractC11456ia.mo3013a(f23142c);
            abstractC11456ia.mo3009a(this.f23158b);
            abstractC11456ia.mo3005b();
        }
        if (this.f23159c != null) {
            abstractC11456ia.mo3013a(f23143d);
            abstractC11456ia.mo3009a(this.f23159c);
            abstractC11456ia.mo3005b();
        }
        if (this.f23160d != null && m3200e()) {
            abstractC11456ia.mo3013a(f23144e);
            abstractC11456ia.mo3009a(this.f23160d);
            abstractC11456ia.mo3005b();
        }
        if (this.f23161e != null && m3198f()) {
            abstractC11456ia.mo3013a(f23145f);
            abstractC11456ia.mo3009a(this.f23161e);
            abstractC11456ia.mo3005b();
        }
        if (this.f23162f != null && m3197g()) {
            abstractC11456ia.mo3013a(f23146g);
            abstractC11456ia.mo3009a(this.f23162f);
            abstractC11456ia.mo3005b();
        }
        if (this.f23152a != null && m3196h()) {
            abstractC11456ia.mo3013a(f23147h);
            this.f23152a.mo3082b(abstractC11456ia);
            abstractC11456ia.mo3005b();
        }
        if (m3195i()) {
            abstractC11456ia.mo3013a(f23148i);
            abstractC11456ia.mo3006a(this.f23157a);
            abstractC11456ia.mo3005b();
        }
        if (this.f23156a != null && m3194j()) {
            abstractC11456ia.mo3013a(f23149j);
            abstractC11456ia.mo3011a(new C11454hz((byte) 11, (byte) 11, this.f23156a.size()));
            for (Map.Entry<String, String> entry : this.f23156a.entrySet()) {
                abstractC11456ia.mo3009a(entry.getKey());
                abstractC11456ia.mo3009a(entry.getValue());
            }
            abstractC11456ia.mo3003d();
            abstractC11456ia.mo3005b();
        }
        if (this.f23163g != null && m3193k()) {
            abstractC11456ia.mo3013a(f23150k);
            abstractC11456ia.mo3009a(this.f23163g);
            abstractC11456ia.mo3005b();
        }
        if (this.f23164h != null && m3192l()) {
            abstractC11456ia.mo3013a(f23151l);
            abstractC11456ia.mo3009a(this.f23164h);
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionSendMessage(");
        if (m3211a()) {
            sb.append("debug:");
            String str = this.f23154a;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (m3206b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            C11419gu c11419gu = this.f23153a;
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
        String str2 = this.f23158b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("appId:");
        String str3 = this.f23159c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        if (m3200e()) {
            sb.append(", ");
            sb.append("packageName:");
            String str4 = this.f23160d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m3198f()) {
            sb.append(", ");
            sb.append("topic:");
            String str5 = this.f23161e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m3197g()) {
            sb.append(", ");
            sb.append("aliasName:");
            String str6 = this.f23162f;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
        }
        if (m3196h()) {
            sb.append(", ");
            sb.append("message:");
            C11416gr c11416gr = this.f23152a;
            if (c11416gr == null) {
                sb.append("null");
            } else {
                sb.append(c11416gr);
            }
        }
        if (m3195i()) {
            sb.append(", ");
            sb.append("needAck:");
            sb.append(this.f23157a);
        }
        if (m3194j()) {
            sb.append(", ");
            sb.append("params:");
            Map<String, String> map = this.f23156a;
            if (map == null) {
                sb.append("null");
            } else {
                sb.append(map);
            }
        }
        if (m3193k()) {
            sb.append(", ");
            sb.append("category:");
            String str7 = this.f23163g;
            if (str7 == null) {
                sb.append("null");
            } else {
                sb.append(str7);
            }
        }
        if (m3192l()) {
            sb.append(", ");
            sb.append("userAccount:");
            String str8 = this.f23164h;
            if (str8 == null) {
                sb.append("null");
            } else {
                sb.append(str8);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    public void m3212a() {
        if (this.f23158b == null) {
            throw new C11457ib("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f23159c != null) {
        } else {
            throw new C11457ib("Required field 'appId' was not present! Struct: " + toString());
        }
    }
}
