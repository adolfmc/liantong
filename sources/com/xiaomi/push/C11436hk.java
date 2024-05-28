package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.hk */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11436hk implements InterfaceC11442hq<C11436hk, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public long f23192a;

    /* renamed from: a */
    public C11419gu f23193a;

    /* renamed from: a */
    public String f23194a;

    /* renamed from: a */
    private BitSet f23195a = new BitSet(1);

    /* renamed from: b */
    public String f23196b;

    /* renamed from: c */
    public String f23197c;

    /* renamed from: d */
    public String f23198d;

    /* renamed from: e */
    public String f23199e;

    /* renamed from: f */
    public String f23200f;

    /* renamed from: g */
    public String f23201g;

    /* renamed from: a */
    private static final C11461if f23183a = new C11461if("XmPushActionSubscriptionResult");

    /* renamed from: a */
    private static final C11452hx f23182a = new C11452hx("", (byte) 11, 1);

    /* renamed from: b */
    private static final C11452hx f23184b = new C11452hx("", (byte) 12, 2);

    /* renamed from: c */
    private static final C11452hx f23185c = new C11452hx("", (byte) 11, 3);

    /* renamed from: d */
    private static final C11452hx f23186d = new C11452hx("", (byte) 11, 4);

    /* renamed from: e */
    private static final C11452hx f23187e = new C11452hx("", (byte) 10, 6);

    /* renamed from: f */
    private static final C11452hx f23188f = new C11452hx("", (byte) 11, 7);

    /* renamed from: g */
    private static final C11452hx f23189g = new C11452hx("", (byte) 11, 8);

    /* renamed from: h */
    private static final C11452hx f23190h = new C11452hx("", (byte) 11, 9);

    /* renamed from: i */
    private static final C11452hx f23191i = new C11452hx("", (byte) 11, 10);

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public boolean m3173a() {
        return this.f23194a != null;
    }

    /* renamed from: b */
    public boolean m3168b() {
        return this.f23193a != null;
    }

    /* renamed from: a */
    public String m3175a() {
        return this.f23196b;
    }

    /* renamed from: c */
    public boolean m3166c() {
        return this.f23196b != null;
    }

    /* renamed from: d */
    public boolean m3165d() {
        return this.f23197c != null;
    }

    /* renamed from: e */
    public boolean m3164e() {
        return this.f23195a.get(0);
    }

    /* renamed from: a */
    public void m3170a(boolean z) {
        this.f23195a.set(0, z);
    }

    /* renamed from: f */
    public boolean m3163f() {
        return this.f23198d != null;
    }

    /* renamed from: b */
    public String m3169b() {
        return this.f23199e;
    }

    /* renamed from: g */
    public boolean m3162g() {
        return this.f23199e != null;
    }

    /* renamed from: h */
    public boolean m3161h() {
        return this.f23200f != null;
    }

    /* renamed from: c */
    public String m3167c() {
        return this.f23201g;
    }

    /* renamed from: i */
    public boolean m3160i() {
        return this.f23201g != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11436hk)) {
            return m3171a((C11436hk) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3171a(C11436hk c11436hk) {
        if (c11436hk == null) {
            return false;
        }
        boolean m3173a = m3173a();
        boolean m3173a2 = c11436hk.m3173a();
        if ((m3173a || m3173a2) && !(m3173a && m3173a2 && this.f23194a.equals(c11436hk.f23194a))) {
            return false;
        }
        boolean m3168b = m3168b();
        boolean m3168b2 = c11436hk.m3168b();
        if ((m3168b || m3168b2) && !(m3168b && m3168b2 && this.f23193a.m3514a(c11436hk.f23193a))) {
            return false;
        }
        boolean m3166c = m3166c();
        boolean m3166c2 = c11436hk.m3166c();
        if ((m3166c || m3166c2) && !(m3166c && m3166c2 && this.f23196b.equals(c11436hk.f23196b))) {
            return false;
        }
        boolean m3165d = m3165d();
        boolean m3165d2 = c11436hk.m3165d();
        if ((m3165d || m3165d2) && !(m3165d && m3165d2 && this.f23197c.equals(c11436hk.f23197c))) {
            return false;
        }
        boolean m3164e = m3164e();
        boolean m3164e2 = c11436hk.m3164e();
        if ((m3164e || m3164e2) && !(m3164e && m3164e2 && this.f23192a == c11436hk.f23192a)) {
            return false;
        }
        boolean m3163f = m3163f();
        boolean m3163f2 = c11436hk.m3163f();
        if ((m3163f || m3163f2) && !(m3163f && m3163f2 && this.f23198d.equals(c11436hk.f23198d))) {
            return false;
        }
        boolean m3162g = m3162g();
        boolean m3162g2 = c11436hk.m3162g();
        if ((m3162g || m3162g2) && !(m3162g && m3162g2 && this.f23199e.equals(c11436hk.f23199e))) {
            return false;
        }
        boolean m3161h = m3161h();
        boolean m3161h2 = c11436hk.m3161h();
        if ((m3161h || m3161h2) && !(m3161h && m3161h2 && this.f23200f.equals(c11436hk.f23200f))) {
            return false;
        }
        boolean m3160i = m3160i();
        boolean m3160i2 = c11436hk.m3160i();
        if (m3160i || m3160i2) {
            return m3160i && m3160i2 && this.f23201g.equals(c11436hk.f23201g);
        }
        return true;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11436hk c11436hk) {
        int m3076a;
        int m3076a2;
        int m3076a3;
        int m3076a4;
        int m3078a;
        int m3076a5;
        int m3076a6;
        int m3077a;
        int m3076a7;
        if (!getClass().equals(c11436hk.getClass())) {
            return getClass().getName().compareTo(c11436hk.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3173a()).compareTo(Boolean.valueOf(c11436hk.m3173a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3173a() || (m3076a7 = C11443hr.m3076a(this.f23194a, c11436hk.f23194a)) == 0) {
            int compareTo2 = Boolean.valueOf(m3168b()).compareTo(Boolean.valueOf(c11436hk.m3168b()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (!m3168b() || (m3077a = C11443hr.m3077a(this.f23193a, c11436hk.f23193a)) == 0) {
                int compareTo3 = Boolean.valueOf(m3166c()).compareTo(Boolean.valueOf(c11436hk.m3166c()));
                if (compareTo3 != 0) {
                    return compareTo3;
                }
                if (!m3166c() || (m3076a6 = C11443hr.m3076a(this.f23196b, c11436hk.f23196b)) == 0) {
                    int compareTo4 = Boolean.valueOf(m3165d()).compareTo(Boolean.valueOf(c11436hk.m3165d()));
                    if (compareTo4 != 0) {
                        return compareTo4;
                    }
                    if (!m3165d() || (m3076a5 = C11443hr.m3076a(this.f23197c, c11436hk.f23197c)) == 0) {
                        int compareTo5 = Boolean.valueOf(m3164e()).compareTo(Boolean.valueOf(c11436hk.m3164e()));
                        if (compareTo5 != 0) {
                            return compareTo5;
                        }
                        if (!m3164e() || (m3078a = C11443hr.m3078a(this.f23192a, c11436hk.f23192a)) == 0) {
                            int compareTo6 = Boolean.valueOf(m3163f()).compareTo(Boolean.valueOf(c11436hk.m3163f()));
                            if (compareTo6 != 0) {
                                return compareTo6;
                            }
                            if (!m3163f() || (m3076a4 = C11443hr.m3076a(this.f23198d, c11436hk.f23198d)) == 0) {
                                int compareTo7 = Boolean.valueOf(m3162g()).compareTo(Boolean.valueOf(c11436hk.m3162g()));
                                if (compareTo7 != 0) {
                                    return compareTo7;
                                }
                                if (!m3162g() || (m3076a3 = C11443hr.m3076a(this.f23199e, c11436hk.f23199e)) == 0) {
                                    int compareTo8 = Boolean.valueOf(m3161h()).compareTo(Boolean.valueOf(c11436hk.m3161h()));
                                    if (compareTo8 != 0) {
                                        return compareTo8;
                                    }
                                    if (!m3161h() || (m3076a2 = C11443hr.m3076a(this.f23200f, c11436hk.f23200f)) == 0) {
                                        int compareTo9 = Boolean.valueOf(m3160i()).compareTo(Boolean.valueOf(c11436hk.m3160i()));
                                        if (compareTo9 != 0) {
                                            return compareTo9;
                                        }
                                        if (!m3160i() || (m3076a = C11443hr.m3076a(this.f23201g, c11436hk.f23201g)) == 0) {
                                            return 0;
                                        }
                                        return m3076a;
                                    }
                                    return m3076a2;
                                }
                                return m3076a3;
                            }
                            return m3076a4;
                        }
                        return m3078a;
                    }
                    return m3076a5;
                }
                return m3076a6;
            }
            return m3077a;
        }
        return m3076a7;
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
                            this.f23194a = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 2:
                        if (mo3021a.f23307a == 12) {
                            this.f23193a = new C11419gu();
                            this.f23193a.mo3083a(abstractC11456ia);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 3:
                        if (mo3021a.f23307a == 11) {
                            this.f23196b = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 4:
                        if (mo3021a.f23307a == 11) {
                            this.f23197c = abstractC11456ia.mo2990a();
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
                            this.f23192a = abstractC11456ia.mo3022a();
                            m3170a(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 7:
                        if (mo3021a.f23307a == 11) {
                            this.f23198d = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 8:
                        if (mo3021a.f23307a == 11) {
                            this.f23199e = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 9:
                        if (mo3021a.f23307a == 11) {
                            this.f23200f = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 10:
                        if (mo3021a.f23307a == 11) {
                            this.f23201g = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                }
                abstractC11456ia.mo3000g();
            } else {
                abstractC11456ia.mo3001f();
                m3174a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3174a();
        abstractC11456ia.mo3010a(f23183a);
        if (this.f23194a != null && m3173a()) {
            abstractC11456ia.mo3013a(f23182a);
            abstractC11456ia.mo3009a(this.f23194a);
            abstractC11456ia.mo3005b();
        }
        if (this.f23193a != null && m3168b()) {
            abstractC11456ia.mo3013a(f23184b);
            this.f23193a.mo3082b(abstractC11456ia);
            abstractC11456ia.mo3005b();
        }
        if (this.f23196b != null) {
            abstractC11456ia.mo3013a(f23185c);
            abstractC11456ia.mo3009a(this.f23196b);
            abstractC11456ia.mo3005b();
        }
        if (this.f23197c != null && m3165d()) {
            abstractC11456ia.mo3013a(f23186d);
            abstractC11456ia.mo3009a(this.f23197c);
            abstractC11456ia.mo3005b();
        }
        if (m3164e()) {
            abstractC11456ia.mo3013a(f23187e);
            abstractC11456ia.mo3014a(this.f23192a);
            abstractC11456ia.mo3005b();
        }
        if (this.f23198d != null && m3163f()) {
            abstractC11456ia.mo3013a(f23188f);
            abstractC11456ia.mo3009a(this.f23198d);
            abstractC11456ia.mo3005b();
        }
        if (this.f23199e != null && m3162g()) {
            abstractC11456ia.mo3013a(f23189g);
            abstractC11456ia.mo3009a(this.f23199e);
            abstractC11456ia.mo3005b();
        }
        if (this.f23200f != null && m3161h()) {
            abstractC11456ia.mo3013a(f23190h);
            abstractC11456ia.mo3009a(this.f23200f);
            abstractC11456ia.mo3005b();
        }
        if (this.f23201g != null && m3160i()) {
            abstractC11456ia.mo3013a(f23191i);
            abstractC11456ia.mo3009a(this.f23201g);
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionSubscriptionResult(");
        if (m3173a()) {
            sb.append("debug:");
            String str = this.f23194a;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (m3168b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            C11419gu c11419gu = this.f23193a;
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
        String str2 = this.f23196b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        if (m3165d()) {
            sb.append(", ");
            sb.append("appId:");
            String str3 = this.f23197c;
            if (str3 == null) {
                sb.append("null");
            } else {
                sb.append(str3);
            }
        }
        if (m3164e()) {
            sb.append(", ");
            sb.append("errorCode:");
            sb.append(this.f23192a);
        }
        if (m3163f()) {
            sb.append(", ");
            sb.append("reason:");
            String str4 = this.f23198d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m3162g()) {
            sb.append(", ");
            sb.append("topic:");
            String str5 = this.f23199e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m3161h()) {
            sb.append(", ");
            sb.append("packageName:");
            String str6 = this.f23200f;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
        }
        if (m3160i()) {
            sb.append(", ");
            sb.append("category:");
            String str7 = this.f23201g;
            if (str7 == null) {
                sb.append("null");
            } else {
                sb.append(str7);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    public void m3174a() {
        if (this.f23196b != null) {
            return;
        }
        throw new C11457ib("Required field 'id' was not present! Struct: " + toString());
    }
}
