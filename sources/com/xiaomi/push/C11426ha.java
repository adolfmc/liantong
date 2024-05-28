package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/* renamed from: com.xiaomi.push.ha */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11426ha implements InterfaceC11442hq<C11426ha, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public long f22954a;

    /* renamed from: a */
    public C11419gu f22955a;

    /* renamed from: a */
    public String f22956a;

    /* renamed from: a */
    public List<String> f22958a;

    /* renamed from: b */
    public String f22960b;

    /* renamed from: c */
    public String f22961c;

    /* renamed from: d */
    public String f22962d;

    /* renamed from: e */
    public String f22963e;

    /* renamed from: f */
    public String f22964f;

    /* renamed from: a */
    private static final C11461if f22944a = new C11461if("XmPushActionCommandResult");

    /* renamed from: a */
    private static final C11452hx f22943a = new C11452hx("", (byte) 12, 2);

    /* renamed from: b */
    private static final C11452hx f22945b = new C11452hx("", (byte) 11, 3);

    /* renamed from: c */
    private static final C11452hx f22946c = new C11452hx("", (byte) 11, 4);

    /* renamed from: d */
    private static final C11452hx f22947d = new C11452hx("", (byte) 11, 5);

    /* renamed from: e */
    private static final C11452hx f22948e = new C11452hx("", (byte) 10, 7);

    /* renamed from: f */
    private static final C11452hx f22949f = new C11452hx("", (byte) 11, 8);

    /* renamed from: g */
    private static final C11452hx f22950g = new C11452hx("", (byte) 11, 9);

    /* renamed from: h */
    private static final C11452hx f22951h = new C11452hx("", (byte) 15, 10);

    /* renamed from: i */
    private static final C11452hx f22952i = new C11452hx("", (byte) 11, 12);

    /* renamed from: j */
    private static final C11452hx f22953j = new C11452hx("", (byte) 2, 13);

    /* renamed from: a */
    private BitSet f22957a = new BitSet(2);

    /* renamed from: a */
    public boolean f22959a = true;

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public boolean m3405a() {
        return this.f22955a != null;
    }

    /* renamed from: a */
    public String m3408a() {
        return this.f22956a;
    }

    /* renamed from: b */
    public boolean m3400b() {
        return this.f22956a != null;
    }

    /* renamed from: c */
    public boolean m3397c() {
        return this.f22960b != null;
    }

    /* renamed from: b */
    public String m3401b() {
        return this.f22961c;
    }

    /* renamed from: d */
    public boolean m3396d() {
        return this.f22961c != null;
    }

    /* renamed from: e */
    public boolean m3395e() {
        return this.f22957a.get(0);
    }

    /* renamed from: a */
    public void m3402a(boolean z) {
        this.f22957a.set(0, z);
    }

    /* renamed from: f */
    public boolean m3394f() {
        return this.f22962d != null;
    }

    /* renamed from: g */
    public boolean m3393g() {
        return this.f22963e != null;
    }

    /* renamed from: a */
    public List<String> m3407a() {
        return this.f22958a;
    }

    /* renamed from: h */
    public boolean m3392h() {
        return this.f22958a != null;
    }

    /* renamed from: c */
    public String m3398c() {
        return this.f22964f;
    }

    /* renamed from: i */
    public boolean m3391i() {
        return this.f22964f != null;
    }

    /* renamed from: j */
    public boolean m3390j() {
        return this.f22957a.get(1);
    }

    /* renamed from: b */
    public void m3399b(boolean z) {
        this.f22957a.set(1, z);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11426ha)) {
            return m3403a((C11426ha) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3403a(C11426ha c11426ha) {
        if (c11426ha == null) {
            return false;
        }
        boolean m3405a = m3405a();
        boolean m3405a2 = c11426ha.m3405a();
        if ((m3405a || m3405a2) && !(m3405a && m3405a2 && this.f22955a.m3514a(c11426ha.f22955a))) {
            return false;
        }
        boolean m3400b = m3400b();
        boolean m3400b2 = c11426ha.m3400b();
        if ((m3400b || m3400b2) && !(m3400b && m3400b2 && this.f22956a.equals(c11426ha.f22956a))) {
            return false;
        }
        boolean m3397c = m3397c();
        boolean m3397c2 = c11426ha.m3397c();
        if ((m3397c || m3397c2) && !(m3397c && m3397c2 && this.f22960b.equals(c11426ha.f22960b))) {
            return false;
        }
        boolean m3396d = m3396d();
        boolean m3396d2 = c11426ha.m3396d();
        if (((m3396d || m3396d2) && !(m3396d && m3396d2 && this.f22961c.equals(c11426ha.f22961c))) || this.f22954a != c11426ha.f22954a) {
            return false;
        }
        boolean m3394f = m3394f();
        boolean m3394f2 = c11426ha.m3394f();
        if ((m3394f || m3394f2) && !(m3394f && m3394f2 && this.f22962d.equals(c11426ha.f22962d))) {
            return false;
        }
        boolean m3393g = m3393g();
        boolean m3393g2 = c11426ha.m3393g();
        if ((m3393g || m3393g2) && !(m3393g && m3393g2 && this.f22963e.equals(c11426ha.f22963e))) {
            return false;
        }
        boolean m3392h = m3392h();
        boolean m3392h2 = c11426ha.m3392h();
        if ((m3392h || m3392h2) && !(m3392h && m3392h2 && this.f22958a.equals(c11426ha.f22958a))) {
            return false;
        }
        boolean m3391i = m3391i();
        boolean m3391i2 = c11426ha.m3391i();
        if ((m3391i || m3391i2) && !(m3391i && m3391i2 && this.f22964f.equals(c11426ha.f22964f))) {
            return false;
        }
        boolean m3390j = m3390j();
        boolean m3390j2 = c11426ha.m3390j();
        if (m3390j || m3390j2) {
            return m3390j && m3390j2 && this.f22959a == c11426ha.f22959a;
        }
        return true;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11426ha c11426ha) {
        int m3066a;
        int m3076a;
        int m3070a;
        int m3076a2;
        int m3076a3;
        int m3078a;
        int m3076a4;
        int m3076a5;
        int m3076a6;
        int m3077a;
        if (!getClass().equals(c11426ha.getClass())) {
            return getClass().getName().compareTo(c11426ha.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3405a()).compareTo(Boolean.valueOf(c11426ha.m3405a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3405a() || (m3077a = C11443hr.m3077a(this.f22955a, c11426ha.f22955a)) == 0) {
            int compareTo2 = Boolean.valueOf(m3400b()).compareTo(Boolean.valueOf(c11426ha.m3400b()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (!m3400b() || (m3076a6 = C11443hr.m3076a(this.f22956a, c11426ha.f22956a)) == 0) {
                int compareTo3 = Boolean.valueOf(m3397c()).compareTo(Boolean.valueOf(c11426ha.m3397c()));
                if (compareTo3 != 0) {
                    return compareTo3;
                }
                if (!m3397c() || (m3076a5 = C11443hr.m3076a(this.f22960b, c11426ha.f22960b)) == 0) {
                    int compareTo4 = Boolean.valueOf(m3396d()).compareTo(Boolean.valueOf(c11426ha.m3396d()));
                    if (compareTo4 != 0) {
                        return compareTo4;
                    }
                    if (!m3396d() || (m3076a4 = C11443hr.m3076a(this.f22961c, c11426ha.f22961c)) == 0) {
                        int compareTo5 = Boolean.valueOf(m3395e()).compareTo(Boolean.valueOf(c11426ha.m3395e()));
                        if (compareTo5 != 0) {
                            return compareTo5;
                        }
                        if (!m3395e() || (m3078a = C11443hr.m3078a(this.f22954a, c11426ha.f22954a)) == 0) {
                            int compareTo6 = Boolean.valueOf(m3394f()).compareTo(Boolean.valueOf(c11426ha.m3394f()));
                            if (compareTo6 != 0) {
                                return compareTo6;
                            }
                            if (!m3394f() || (m3076a3 = C11443hr.m3076a(this.f22962d, c11426ha.f22962d)) == 0) {
                                int compareTo7 = Boolean.valueOf(m3393g()).compareTo(Boolean.valueOf(c11426ha.m3393g()));
                                if (compareTo7 != 0) {
                                    return compareTo7;
                                }
                                if (!m3393g() || (m3076a2 = C11443hr.m3076a(this.f22963e, c11426ha.f22963e)) == 0) {
                                    int compareTo8 = Boolean.valueOf(m3392h()).compareTo(Boolean.valueOf(c11426ha.m3392h()));
                                    if (compareTo8 != 0) {
                                        return compareTo8;
                                    }
                                    if (!m3392h() || (m3070a = C11443hr.m3070a(this.f22958a, c11426ha.f22958a)) == 0) {
                                        int compareTo9 = Boolean.valueOf(m3391i()).compareTo(Boolean.valueOf(c11426ha.m3391i()));
                                        if (compareTo9 != 0) {
                                            return compareTo9;
                                        }
                                        if (!m3391i() || (m3076a = C11443hr.m3076a(this.f22964f, c11426ha.f22964f)) == 0) {
                                            int compareTo10 = Boolean.valueOf(m3390j()).compareTo(Boolean.valueOf(c11426ha.m3390j()));
                                            if (compareTo10 != 0) {
                                                return compareTo10;
                                            }
                                            if (!m3390j() || (m3066a = C11443hr.m3066a(this.f22959a, c11426ha.f22959a)) == 0) {
                                                return 0;
                                            }
                                            return m3066a;
                                        }
                                        return m3076a;
                                    }
                                    return m3070a;
                                }
                                return m3076a2;
                            }
                            return m3076a3;
                        }
                        return m3078a;
                    }
                    return m3076a4;
                }
                return m3076a5;
            }
            return m3076a6;
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
                            this.f22955a = new C11419gu();
                            this.f22955a.mo3083a(abstractC11456ia);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 3:
                        if (mo3021a.f23307a == 11) {
                            this.f22956a = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 4:
                        if (mo3021a.f23307a == 11) {
                            this.f22960b = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 5:
                        if (mo3021a.f23307a == 11) {
                            this.f22961c = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 6:
                    case 11:
                    default:
                        C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                        break;
                    case 7:
                        if (mo3021a.f23307a == 10) {
                            this.f22954a = abstractC11456ia.mo3022a();
                            m3402a(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 8:
                        if (mo3021a.f23307a == 11) {
                            this.f22962d = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 9:
                        if (mo3021a.f23307a == 11) {
                            this.f22963e = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 10:
                        if (mo3021a.f23307a == 15) {
                            C11453hy mo2993a = abstractC11456ia.mo2993a();
                            this.f22958a = new ArrayList(mo2993a.f23311a);
                            for (int i = 0; i < mo2993a.f23311a; i++) {
                                this.f22958a.add(abstractC11456ia.mo2990a());
                            }
                            abstractC11456ia.mo2998i();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 12:
                        if (mo3021a.f23307a == 11) {
                            this.f22964f = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 13:
                        if (mo3021a.f23307a == 2) {
                            this.f22959a = abstractC11456ia.mo3017a();
                            m3399b(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                }
                abstractC11456ia.mo3000g();
            } else {
                abstractC11456ia.mo3001f();
                if (!m3395e()) {
                    throw new C11457ib("Required field 'errorCode' was not found in serialized data! Struct: " + toString());
                }
                m3406a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3406a();
        abstractC11456ia.mo3010a(f22944a);
        if (this.f22955a != null && m3405a()) {
            abstractC11456ia.mo3013a(f22943a);
            this.f22955a.mo3082b(abstractC11456ia);
            abstractC11456ia.mo3005b();
        }
        if (this.f22956a != null) {
            abstractC11456ia.mo3013a(f22945b);
            abstractC11456ia.mo3009a(this.f22956a);
            abstractC11456ia.mo3005b();
        }
        if (this.f22960b != null) {
            abstractC11456ia.mo3013a(f22946c);
            abstractC11456ia.mo3009a(this.f22960b);
            abstractC11456ia.mo3005b();
        }
        if (this.f22961c != null) {
            abstractC11456ia.mo3013a(f22947d);
            abstractC11456ia.mo3009a(this.f22961c);
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3013a(f22948e);
        abstractC11456ia.mo3014a(this.f22954a);
        abstractC11456ia.mo3005b();
        if (this.f22962d != null && m3394f()) {
            abstractC11456ia.mo3013a(f22949f);
            abstractC11456ia.mo3009a(this.f22962d);
            abstractC11456ia.mo3005b();
        }
        if (this.f22963e != null && m3393g()) {
            abstractC11456ia.mo3013a(f22950g);
            abstractC11456ia.mo3009a(this.f22963e);
            abstractC11456ia.mo3005b();
        }
        if (this.f22958a != null && m3392h()) {
            abstractC11456ia.mo3013a(f22951h);
            abstractC11456ia.mo3012a(new C11453hy((byte) 11, this.f22958a.size()));
            for (String str : this.f22958a) {
                abstractC11456ia.mo3009a(str);
            }
            abstractC11456ia.mo3002e();
            abstractC11456ia.mo3005b();
        }
        if (this.f22964f != null && m3391i()) {
            abstractC11456ia.mo3013a(f22952i);
            abstractC11456ia.mo3009a(this.f22964f);
            abstractC11456ia.mo3005b();
        }
        if (m3390j()) {
            abstractC11456ia.mo3013a(f22953j);
            abstractC11456ia.mo3006a(this.f22959a);
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionCommandResult(");
        if (m3405a()) {
            sb.append("target:");
            C11419gu c11419gu = this.f22955a;
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
        String str = this.f22956a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("appId:");
        String str2 = this.f22960b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("cmdName:");
        String str3 = this.f22961c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("errorCode:");
        sb.append(this.f22954a);
        if (m3394f()) {
            sb.append(", ");
            sb.append("reason:");
            String str4 = this.f22962d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m3393g()) {
            sb.append(", ");
            sb.append("packageName:");
            String str5 = this.f22963e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m3392h()) {
            sb.append(", ");
            sb.append("cmdArgs:");
            List<String> list = this.f22958a;
            if (list == null) {
                sb.append("null");
            } else {
                sb.append(list);
            }
        }
        if (m3391i()) {
            sb.append(", ");
            sb.append("category:");
            String str6 = this.f22964f;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
        }
        if (m3390j()) {
            sb.append(", ");
            sb.append("response2Client:");
            sb.append(this.f22959a);
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    public void m3406a() {
        if (this.f22956a == null) {
            throw new C11457ib("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f22960b == null) {
            throw new C11457ib("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.f22961c != null) {
        } else {
            throw new C11457ib("Required field 'cmdName' was not present! Struct: " + toString());
        }
    }
}
