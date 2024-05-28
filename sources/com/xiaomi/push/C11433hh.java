package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.hh */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11433hh implements InterfaceC11442hq<C11433hh, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public long f23131a;

    /* renamed from: a */
    public C11419gu f23132a;

    /* renamed from: a */
    public String f23133a;

    /* renamed from: a */
    private BitSet f23134a = new BitSet(1);

    /* renamed from: b */
    public String f23135b;

    /* renamed from: c */
    public String f23136c;

    /* renamed from: d */
    public String f23137d;

    /* renamed from: e */
    public String f23138e;

    /* renamed from: a */
    private static final C11461if f23124a = new C11461if("XmPushActionSendFeedbackResult");

    /* renamed from: a */
    private static final C11452hx f23123a = new C11452hx("", (byte) 11, 1);

    /* renamed from: b */
    private static final C11452hx f23125b = new C11452hx("", (byte) 12, 2);

    /* renamed from: c */
    private static final C11452hx f23126c = new C11452hx("", (byte) 11, 3);

    /* renamed from: d */
    private static final C11452hx f23127d = new C11452hx("", (byte) 11, 4);

    /* renamed from: e */
    private static final C11452hx f23128e = new C11452hx("", (byte) 10, 6);

    /* renamed from: f */
    private static final C11452hx f23129f = new C11452hx("", (byte) 11, 7);

    /* renamed from: g */
    private static final C11452hx f23130g = new C11452hx("", (byte) 11, 8);

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public boolean m3224a() {
        return this.f23133a != null;
    }

    /* renamed from: b */
    public boolean m3220b() {
        return this.f23132a != null;
    }

    /* renamed from: c */
    public boolean m3219c() {
        return this.f23135b != null;
    }

    /* renamed from: d */
    public boolean m3218d() {
        return this.f23136c != null;
    }

    /* renamed from: e */
    public boolean m3217e() {
        return this.f23134a.get(0);
    }

    /* renamed from: a */
    public void m3221a(boolean z) {
        this.f23134a.set(0, z);
    }

    /* renamed from: f */
    public boolean m3216f() {
        return this.f23137d != null;
    }

    /* renamed from: g */
    public boolean m3215g() {
        return this.f23138e != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11433hh)) {
            return m3222a((C11433hh) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3222a(C11433hh c11433hh) {
        if (c11433hh == null) {
            return false;
        }
        boolean m3224a = m3224a();
        boolean m3224a2 = c11433hh.m3224a();
        if ((m3224a || m3224a2) && !(m3224a && m3224a2 && this.f23133a.equals(c11433hh.f23133a))) {
            return false;
        }
        boolean m3220b = m3220b();
        boolean m3220b2 = c11433hh.m3220b();
        if ((m3220b || m3220b2) && !(m3220b && m3220b2 && this.f23132a.m3514a(c11433hh.f23132a))) {
            return false;
        }
        boolean m3219c = m3219c();
        boolean m3219c2 = c11433hh.m3219c();
        if ((m3219c || m3219c2) && !(m3219c && m3219c2 && this.f23135b.equals(c11433hh.f23135b))) {
            return false;
        }
        boolean m3218d = m3218d();
        boolean m3218d2 = c11433hh.m3218d();
        if (((m3218d || m3218d2) && !(m3218d && m3218d2 && this.f23136c.equals(c11433hh.f23136c))) || this.f23131a != c11433hh.f23131a) {
            return false;
        }
        boolean m3216f = m3216f();
        boolean m3216f2 = c11433hh.m3216f();
        if ((m3216f || m3216f2) && !(m3216f && m3216f2 && this.f23137d.equals(c11433hh.f23137d))) {
            return false;
        }
        boolean m3215g = m3215g();
        boolean m3215g2 = c11433hh.m3215g();
        if (m3215g || m3215g2) {
            return m3215g && m3215g2 && this.f23138e.equals(c11433hh.f23138e);
        }
        return true;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11433hh c11433hh) {
        int m3076a;
        int m3076a2;
        int m3078a;
        int m3076a3;
        int m3076a4;
        int m3077a;
        int m3076a5;
        if (!getClass().equals(c11433hh.getClass())) {
            return getClass().getName().compareTo(c11433hh.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3224a()).compareTo(Boolean.valueOf(c11433hh.m3224a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3224a() || (m3076a5 = C11443hr.m3076a(this.f23133a, c11433hh.f23133a)) == 0) {
            int compareTo2 = Boolean.valueOf(m3220b()).compareTo(Boolean.valueOf(c11433hh.m3220b()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (!m3220b() || (m3077a = C11443hr.m3077a(this.f23132a, c11433hh.f23132a)) == 0) {
                int compareTo3 = Boolean.valueOf(m3219c()).compareTo(Boolean.valueOf(c11433hh.m3219c()));
                if (compareTo3 != 0) {
                    return compareTo3;
                }
                if (!m3219c() || (m3076a4 = C11443hr.m3076a(this.f23135b, c11433hh.f23135b)) == 0) {
                    int compareTo4 = Boolean.valueOf(m3218d()).compareTo(Boolean.valueOf(c11433hh.m3218d()));
                    if (compareTo4 != 0) {
                        return compareTo4;
                    }
                    if (!m3218d() || (m3076a3 = C11443hr.m3076a(this.f23136c, c11433hh.f23136c)) == 0) {
                        int compareTo5 = Boolean.valueOf(m3217e()).compareTo(Boolean.valueOf(c11433hh.m3217e()));
                        if (compareTo5 != 0) {
                            return compareTo5;
                        }
                        if (!m3217e() || (m3078a = C11443hr.m3078a(this.f23131a, c11433hh.f23131a)) == 0) {
                            int compareTo6 = Boolean.valueOf(m3216f()).compareTo(Boolean.valueOf(c11433hh.m3216f()));
                            if (compareTo6 != 0) {
                                return compareTo6;
                            }
                            if (!m3216f() || (m3076a2 = C11443hr.m3076a(this.f23137d, c11433hh.f23137d)) == 0) {
                                int compareTo7 = Boolean.valueOf(m3215g()).compareTo(Boolean.valueOf(c11433hh.m3215g()));
                                if (compareTo7 != 0) {
                                    return compareTo7;
                                }
                                if (!m3215g() || (m3076a = C11443hr.m3076a(this.f23138e, c11433hh.f23138e)) == 0) {
                                    return 0;
                                }
                                return m3076a;
                            }
                            return m3076a2;
                        }
                        return m3078a;
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
                            this.f23133a = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 2:
                        if (mo3021a.f23307a == 12) {
                            this.f23132a = new C11419gu();
                            this.f23132a.mo3083a(abstractC11456ia);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 3:
                        if (mo3021a.f23307a == 11) {
                            this.f23135b = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 4:
                        if (mo3021a.f23307a == 11) {
                            this.f23136c = abstractC11456ia.mo2990a();
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
                            this.f23131a = abstractC11456ia.mo3022a();
                            m3221a(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 7:
                        if (mo3021a.f23307a == 11) {
                            this.f23137d = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 8:
                        if (mo3021a.f23307a == 11) {
                            this.f23138e = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                }
                abstractC11456ia.mo3000g();
            } else {
                abstractC11456ia.mo3001f();
                if (!m3217e()) {
                    throw new C11457ib("Required field 'errorCode' was not found in serialized data! Struct: " + toString());
                }
                m3225a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3225a();
        abstractC11456ia.mo3010a(f23124a);
        if (this.f23133a != null && m3224a()) {
            abstractC11456ia.mo3013a(f23123a);
            abstractC11456ia.mo3009a(this.f23133a);
            abstractC11456ia.mo3005b();
        }
        if (this.f23132a != null && m3220b()) {
            abstractC11456ia.mo3013a(f23125b);
            this.f23132a.mo3082b(abstractC11456ia);
            abstractC11456ia.mo3005b();
        }
        if (this.f23135b != null) {
            abstractC11456ia.mo3013a(f23126c);
            abstractC11456ia.mo3009a(this.f23135b);
            abstractC11456ia.mo3005b();
        }
        if (this.f23136c != null) {
            abstractC11456ia.mo3013a(f23127d);
            abstractC11456ia.mo3009a(this.f23136c);
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3013a(f23128e);
        abstractC11456ia.mo3014a(this.f23131a);
        abstractC11456ia.mo3005b();
        if (this.f23137d != null && m3216f()) {
            abstractC11456ia.mo3013a(f23129f);
            abstractC11456ia.mo3009a(this.f23137d);
            abstractC11456ia.mo3005b();
        }
        if (this.f23138e != null && m3215g()) {
            abstractC11456ia.mo3013a(f23130g);
            abstractC11456ia.mo3009a(this.f23138e);
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionSendFeedbackResult(");
        if (m3224a()) {
            sb.append("debug:");
            String str = this.f23133a;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (m3220b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            C11419gu c11419gu = this.f23132a;
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
        String str2 = this.f23135b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("appId:");
        String str3 = this.f23136c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("errorCode:");
        sb.append(this.f23131a);
        if (m3216f()) {
            sb.append(", ");
            sb.append("reason:");
            String str4 = this.f23137d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m3215g()) {
            sb.append(", ");
            sb.append("category:");
            String str5 = this.f23138e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    public void m3225a() {
        if (this.f23135b == null) {
            throw new C11457ib("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f23136c != null) {
        } else {
            throw new C11457ib("Required field 'appId' was not present! Struct: " + toString());
        }
    }
}
