package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/* renamed from: com.xiaomi.push.gz */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11424gz implements InterfaceC11442hq<C11424gz, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public long f22931a;

    /* renamed from: a */
    public C11419gu f22932a;

    /* renamed from: a */
    public String f22933a;

    /* renamed from: a */
    public List<String> f22935a;

    /* renamed from: b */
    public String f22937b;

    /* renamed from: c */
    public String f22939c;

    /* renamed from: d */
    public String f22940d;

    /* renamed from: e */
    public String f22941e;

    /* renamed from: a */
    private static final C11461if f22921a = new C11461if("XmPushActionCommand");

    /* renamed from: a */
    private static final C11452hx f22920a = new C11452hx("", (byte) 12, 2);

    /* renamed from: b */
    private static final C11452hx f22922b = new C11452hx("", (byte) 11, 3);

    /* renamed from: c */
    private static final C11452hx f22923c = new C11452hx("", (byte) 11, 4);

    /* renamed from: d */
    private static final C11452hx f22924d = new C11452hx("", (byte) 11, 5);

    /* renamed from: e */
    private static final C11452hx f22925e = new C11452hx("", (byte) 15, 6);

    /* renamed from: f */
    private static final C11452hx f22926f = new C11452hx("", (byte) 11, 7);

    /* renamed from: g */
    private static final C11452hx f22927g = new C11452hx("", (byte) 11, 9);

    /* renamed from: h */
    private static final C11452hx f22928h = new C11452hx("", (byte) 2, 10);

    /* renamed from: i */
    private static final C11452hx f22929i = new C11452hx("", (byte) 2, 11);

    /* renamed from: j */
    private static final C11452hx f22930j = new C11452hx("", (byte) 10, 12);

    /* renamed from: a */
    private BitSet f22934a = new BitSet(3);

    /* renamed from: a */
    public boolean f22936a = false;

    /* renamed from: b */
    public boolean f22938b = true;

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public boolean m3432a() {
        return this.f22932a != null;
    }

    /* renamed from: a */
    public C11424gz m3429a(String str) {
        this.f22933a = str;
        return this;
    }

    /* renamed from: b */
    public boolean m3426b() {
        return this.f22933a != null;
    }

    /* renamed from: b */
    public C11424gz m3425b(String str) {
        this.f22937b = str;
        return this;
    }

    /* renamed from: c */
    public boolean m3423c() {
        return this.f22937b != null;
    }

    /* renamed from: a */
    public String m3434a() {
        return this.f22939c;
    }

    /* renamed from: c */
    public C11424gz m3422c(String str) {
        this.f22939c = str;
        return this;
    }

    /* renamed from: d */
    public boolean m3420d() {
        return this.f22939c != null;
    }

    /* renamed from: a */
    public void m3428a(String str) {
        if (this.f22935a == null) {
            this.f22935a = new ArrayList();
        }
        this.f22935a.add(str);
    }

    /* renamed from: e */
    public boolean m3418e() {
        return this.f22935a != null;
    }

    /* renamed from: d */
    public C11424gz m3419d(String str) {
        this.f22940d = str;
        return this;
    }

    /* renamed from: f */
    public boolean m3416f() {
        return this.f22940d != null;
    }

    /* renamed from: e */
    public C11424gz m3417e(String str) {
        this.f22941e = str;
        return this;
    }

    /* renamed from: g */
    public boolean m3415g() {
        return this.f22941e != null;
    }

    /* renamed from: h */
    public boolean m3414h() {
        return this.f22934a.get(0);
    }

    /* renamed from: a */
    public void m3427a(boolean z) {
        this.f22934a.set(0, z);
    }

    /* renamed from: i */
    public boolean m3413i() {
        return this.f22934a.get(1);
    }

    /* renamed from: b */
    public void m3424b(boolean z) {
        this.f22934a.set(1, z);
    }

    /* renamed from: j */
    public boolean m3412j() {
        return this.f22934a.get(2);
    }

    /* renamed from: c */
    public void m3421c(boolean z) {
        this.f22934a.set(2, z);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11424gz)) {
            return m3430a((C11424gz) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3430a(C11424gz c11424gz) {
        if (c11424gz == null) {
            return false;
        }
        boolean m3432a = m3432a();
        boolean m3432a2 = c11424gz.m3432a();
        if ((m3432a || m3432a2) && !(m3432a && m3432a2 && this.f22932a.m3514a(c11424gz.f22932a))) {
            return false;
        }
        boolean m3426b = m3426b();
        boolean m3426b2 = c11424gz.m3426b();
        if ((m3426b || m3426b2) && !(m3426b && m3426b2 && this.f22933a.equals(c11424gz.f22933a))) {
            return false;
        }
        boolean m3423c = m3423c();
        boolean m3423c2 = c11424gz.m3423c();
        if ((m3423c || m3423c2) && !(m3423c && m3423c2 && this.f22937b.equals(c11424gz.f22937b))) {
            return false;
        }
        boolean m3420d = m3420d();
        boolean m3420d2 = c11424gz.m3420d();
        if ((m3420d || m3420d2) && !(m3420d && m3420d2 && this.f22939c.equals(c11424gz.f22939c))) {
            return false;
        }
        boolean m3418e = m3418e();
        boolean m3418e2 = c11424gz.m3418e();
        if ((m3418e || m3418e2) && !(m3418e && m3418e2 && this.f22935a.equals(c11424gz.f22935a))) {
            return false;
        }
        boolean m3416f = m3416f();
        boolean m3416f2 = c11424gz.m3416f();
        if ((m3416f || m3416f2) && !(m3416f && m3416f2 && this.f22940d.equals(c11424gz.f22940d))) {
            return false;
        }
        boolean m3415g = m3415g();
        boolean m3415g2 = c11424gz.m3415g();
        if ((m3415g || m3415g2) && !(m3415g && m3415g2 && this.f22941e.equals(c11424gz.f22941e))) {
            return false;
        }
        boolean m3414h = m3414h();
        boolean m3414h2 = c11424gz.m3414h();
        if ((m3414h || m3414h2) && !(m3414h && m3414h2 && this.f22936a == c11424gz.f22936a)) {
            return false;
        }
        boolean m3413i = m3413i();
        boolean m3413i2 = c11424gz.m3413i();
        if ((m3413i || m3413i2) && !(m3413i && m3413i2 && this.f22938b == c11424gz.f22938b)) {
            return false;
        }
        boolean m3412j = m3412j();
        boolean m3412j2 = c11424gz.m3412j();
        if (m3412j || m3412j2) {
            return m3412j && m3412j2 && this.f22931a == c11424gz.f22931a;
        }
        return true;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11424gz c11424gz) {
        int m3078a;
        int m3066a;
        int m3066a2;
        int m3076a;
        int m3076a2;
        int m3070a;
        int m3076a3;
        int m3076a4;
        int m3076a5;
        int m3077a;
        if (!getClass().equals(c11424gz.getClass())) {
            return getClass().getName().compareTo(c11424gz.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3432a()).compareTo(Boolean.valueOf(c11424gz.m3432a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3432a() || (m3077a = C11443hr.m3077a(this.f22932a, c11424gz.f22932a)) == 0) {
            int compareTo2 = Boolean.valueOf(m3426b()).compareTo(Boolean.valueOf(c11424gz.m3426b()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (!m3426b() || (m3076a5 = C11443hr.m3076a(this.f22933a, c11424gz.f22933a)) == 0) {
                int compareTo3 = Boolean.valueOf(m3423c()).compareTo(Boolean.valueOf(c11424gz.m3423c()));
                if (compareTo3 != 0) {
                    return compareTo3;
                }
                if (!m3423c() || (m3076a4 = C11443hr.m3076a(this.f22937b, c11424gz.f22937b)) == 0) {
                    int compareTo4 = Boolean.valueOf(m3420d()).compareTo(Boolean.valueOf(c11424gz.m3420d()));
                    if (compareTo4 != 0) {
                        return compareTo4;
                    }
                    if (!m3420d() || (m3076a3 = C11443hr.m3076a(this.f22939c, c11424gz.f22939c)) == 0) {
                        int compareTo5 = Boolean.valueOf(m3418e()).compareTo(Boolean.valueOf(c11424gz.m3418e()));
                        if (compareTo5 != 0) {
                            return compareTo5;
                        }
                        if (!m3418e() || (m3070a = C11443hr.m3070a(this.f22935a, c11424gz.f22935a)) == 0) {
                            int compareTo6 = Boolean.valueOf(m3416f()).compareTo(Boolean.valueOf(c11424gz.m3416f()));
                            if (compareTo6 != 0) {
                                return compareTo6;
                            }
                            if (!m3416f() || (m3076a2 = C11443hr.m3076a(this.f22940d, c11424gz.f22940d)) == 0) {
                                int compareTo7 = Boolean.valueOf(m3415g()).compareTo(Boolean.valueOf(c11424gz.m3415g()));
                                if (compareTo7 != 0) {
                                    return compareTo7;
                                }
                                if (!m3415g() || (m3076a = C11443hr.m3076a(this.f22941e, c11424gz.f22941e)) == 0) {
                                    int compareTo8 = Boolean.valueOf(m3414h()).compareTo(Boolean.valueOf(c11424gz.m3414h()));
                                    if (compareTo8 != 0) {
                                        return compareTo8;
                                    }
                                    if (!m3414h() || (m3066a2 = C11443hr.m3066a(this.f22936a, c11424gz.f22936a)) == 0) {
                                        int compareTo9 = Boolean.valueOf(m3413i()).compareTo(Boolean.valueOf(c11424gz.m3413i()));
                                        if (compareTo9 != 0) {
                                            return compareTo9;
                                        }
                                        if (!m3413i() || (m3066a = C11443hr.m3066a(this.f22938b, c11424gz.f22938b)) == 0) {
                                            int compareTo10 = Boolean.valueOf(m3412j()).compareTo(Boolean.valueOf(c11424gz.m3412j()));
                                            if (compareTo10 != 0) {
                                                return compareTo10;
                                            }
                                            if (!m3412j() || (m3078a = C11443hr.m3078a(this.f22931a, c11424gz.f22931a)) == 0) {
                                                return 0;
                                            }
                                            return m3078a;
                                        }
                                        return m3066a;
                                    }
                                    return m3066a2;
                                }
                                return m3076a;
                            }
                            return m3076a2;
                        }
                        return m3070a;
                    }
                    return m3076a3;
                }
                return m3076a4;
            }
            return m3076a5;
        }
        return m3077a;
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: a */
    public void mo3083a(AbstractC11456ia abstractC11456ia) {
        abstractC11456ia.mo3020a();
        while (true) {
            C11452hx mo3021a = abstractC11456ia.mo3021a();
            if (mo3021a.f23307a != 0) {
                switch (mo3021a.f23309a) {
                    case 2:
                        if (mo3021a.f23307a == 12) {
                            this.f22932a = new C11419gu();
                            this.f22932a.mo3083a(abstractC11456ia);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 3:
                        if (mo3021a.f23307a == 11) {
                            this.f22933a = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 4:
                        if (mo3021a.f23307a == 11) {
                            this.f22937b = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 5:
                        if (mo3021a.f23307a == 11) {
                            this.f22939c = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 6:
                        if (mo3021a.f23307a == 15) {
                            C11453hy mo2993a = abstractC11456ia.mo2993a();
                            this.f22935a = new ArrayList(mo2993a.f23311a);
                            for (int i = 0; i < mo2993a.f23311a; i++) {
                                this.f22935a.add(abstractC11456ia.mo2990a());
                            }
                            abstractC11456ia.mo2998i();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 7:
                        if (mo3021a.f23307a == 11) {
                            this.f22940d = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 8:
                    default:
                        C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                        break;
                    case 9:
                        if (mo3021a.f23307a == 11) {
                            this.f22941e = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 10:
                        if (mo3021a.f23307a == 2) {
                            this.f22936a = abstractC11456ia.mo3017a();
                            m3427a(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 11:
                        if (mo3021a.f23307a == 2) {
                            this.f22938b = abstractC11456ia.mo3017a();
                            m3424b(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 12:
                        if (mo3021a.f23307a == 10) {
                            this.f22931a = abstractC11456ia.mo3022a();
                            m3421c(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                }
                abstractC11456ia.mo3000g();
            } else {
                abstractC11456ia.mo3001f();
                m3433a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3433a();
        abstractC11456ia.mo3010a(f22921a);
        if (this.f22932a != null && m3432a()) {
            abstractC11456ia.mo3013a(f22920a);
            this.f22932a.mo3082b(abstractC11456ia);
            abstractC11456ia.mo3005b();
        }
        if (this.f22933a != null) {
            abstractC11456ia.mo3013a(f22922b);
            abstractC11456ia.mo3009a(this.f22933a);
            abstractC11456ia.mo3005b();
        }
        if (this.f22937b != null) {
            abstractC11456ia.mo3013a(f22923c);
            abstractC11456ia.mo3009a(this.f22937b);
            abstractC11456ia.mo3005b();
        }
        if (this.f22939c != null) {
            abstractC11456ia.mo3013a(f22924d);
            abstractC11456ia.mo3009a(this.f22939c);
            abstractC11456ia.mo3005b();
        }
        if (this.f22935a != null && m3418e()) {
            abstractC11456ia.mo3013a(f22925e);
            abstractC11456ia.mo3012a(new C11453hy((byte) 11, this.f22935a.size()));
            for (String str : this.f22935a) {
                abstractC11456ia.mo3009a(str);
            }
            abstractC11456ia.mo3002e();
            abstractC11456ia.mo3005b();
        }
        if (this.f22940d != null && m3416f()) {
            abstractC11456ia.mo3013a(f22926f);
            abstractC11456ia.mo3009a(this.f22940d);
            abstractC11456ia.mo3005b();
        }
        if (this.f22941e != null && m3415g()) {
            abstractC11456ia.mo3013a(f22927g);
            abstractC11456ia.mo3009a(this.f22941e);
            abstractC11456ia.mo3005b();
        }
        if (m3414h()) {
            abstractC11456ia.mo3013a(f22928h);
            abstractC11456ia.mo3006a(this.f22936a);
            abstractC11456ia.mo3005b();
        }
        if (m3413i()) {
            abstractC11456ia.mo3013a(f22929i);
            abstractC11456ia.mo3006a(this.f22938b);
            abstractC11456ia.mo3005b();
        }
        if (m3412j()) {
            abstractC11456ia.mo3013a(f22930j);
            abstractC11456ia.mo3014a(this.f22931a);
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionCommand(");
        if (m3432a()) {
            sb.append("target:");
            C11419gu c11419gu = this.f22932a;
            if (c11419gu == null) {
                sb.append("null");
            } else {
                sb.append(c11419gu);
            }
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        String str = this.f22933a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("appId:");
        String str2 = this.f22937b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("cmdName:");
        String str3 = this.f22939c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        if (m3418e()) {
            sb.append(", ");
            sb.append("cmdArgs:");
            List<String> list = this.f22935a;
            if (list == null) {
                sb.append("null");
            } else {
                sb.append(list);
            }
        }
        if (m3416f()) {
            sb.append(", ");
            sb.append("packageName:");
            String str4 = this.f22940d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m3415g()) {
            sb.append(", ");
            sb.append("category:");
            String str5 = this.f22941e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m3414h()) {
            sb.append(", ");
            sb.append("updateCache:");
            sb.append(this.f22936a);
        }
        if (m3413i()) {
            sb.append(", ");
            sb.append("response2Client:");
            sb.append(this.f22938b);
        }
        if (m3412j()) {
            sb.append(", ");
            sb.append("createdTs:");
            sb.append(this.f22931a);
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    public void m3433a() {
        if (this.f22933a == null) {
            throw new C11457ib("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f22937b == null) {
            throw new C11457ib("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.f22939c != null) {
        } else {
            throw new C11457ib("Required field 'cmdName' was not present! Struct: " + toString());
        }
    }
}
