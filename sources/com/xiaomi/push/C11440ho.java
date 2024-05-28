package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.ho */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11440ho implements InterfaceC11442hq<C11440ho, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public long f23275a;

    /* renamed from: a */
    public C11419gu f23276a;

    /* renamed from: a */
    public String f23277a;

    /* renamed from: a */
    private BitSet f23278a = new BitSet(1);

    /* renamed from: b */
    public String f23279b;

    /* renamed from: c */
    public String f23280c;

    /* renamed from: d */
    public String f23281d;

    /* renamed from: e */
    public String f23282e;

    /* renamed from: f */
    public String f23283f;

    /* renamed from: g */
    public String f23284g;

    /* renamed from: a */
    private static final C11461if f23266a = new C11461if("XmPushActionUnSubscriptionResult");

    /* renamed from: a */
    private static final C11452hx f23265a = new C11452hx("", (byte) 11, 1);

    /* renamed from: b */
    private static final C11452hx f23267b = new C11452hx("", (byte) 12, 2);

    /* renamed from: c */
    private static final C11452hx f23268c = new C11452hx("", (byte) 11, 3);

    /* renamed from: d */
    private static final C11452hx f23269d = new C11452hx("", (byte) 11, 4);

    /* renamed from: e */
    private static final C11452hx f23270e = new C11452hx("", (byte) 10, 6);

    /* renamed from: f */
    private static final C11452hx f23271f = new C11452hx("", (byte) 11, 7);

    /* renamed from: g */
    private static final C11452hx f23272g = new C11452hx("", (byte) 11, 8);

    /* renamed from: h */
    private static final C11452hx f23273h = new C11452hx("", (byte) 11, 9);

    /* renamed from: i */
    private static final C11452hx f23274i = new C11452hx("", (byte) 11, 10);

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public boolean m3103a() {
        return this.f23277a != null;
    }

    /* renamed from: b */
    public boolean m3098b() {
        return this.f23276a != null;
    }

    /* renamed from: a */
    public String m3105a() {
        return this.f23279b;
    }

    /* renamed from: c */
    public boolean m3096c() {
        return this.f23279b != null;
    }

    /* renamed from: d */
    public boolean m3095d() {
        return this.f23280c != null;
    }

    /* renamed from: e */
    public boolean m3094e() {
        return this.f23278a.get(0);
    }

    /* renamed from: a */
    public void m3100a(boolean z) {
        this.f23278a.set(0, z);
    }

    /* renamed from: f */
    public boolean m3093f() {
        return this.f23281d != null;
    }

    /* renamed from: b */
    public String m3099b() {
        return this.f23282e;
    }

    /* renamed from: g */
    public boolean m3092g() {
        return this.f23282e != null;
    }

    /* renamed from: h */
    public boolean m3091h() {
        return this.f23283f != null;
    }

    /* renamed from: c */
    public String m3097c() {
        return this.f23284g;
    }

    /* renamed from: i */
    public boolean m3090i() {
        return this.f23284g != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11440ho)) {
            return m3101a((C11440ho) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3101a(C11440ho c11440ho) {
        if (c11440ho == null) {
            return false;
        }
        boolean m3103a = m3103a();
        boolean m3103a2 = c11440ho.m3103a();
        if ((m3103a || m3103a2) && !(m3103a && m3103a2 && this.f23277a.equals(c11440ho.f23277a))) {
            return false;
        }
        boolean m3098b = m3098b();
        boolean m3098b2 = c11440ho.m3098b();
        if ((m3098b || m3098b2) && !(m3098b && m3098b2 && this.f23276a.m3514a(c11440ho.f23276a))) {
            return false;
        }
        boolean m3096c = m3096c();
        boolean m3096c2 = c11440ho.m3096c();
        if ((m3096c || m3096c2) && !(m3096c && m3096c2 && this.f23279b.equals(c11440ho.f23279b))) {
            return false;
        }
        boolean m3095d = m3095d();
        boolean m3095d2 = c11440ho.m3095d();
        if ((m3095d || m3095d2) && !(m3095d && m3095d2 && this.f23280c.equals(c11440ho.f23280c))) {
            return false;
        }
        boolean m3094e = m3094e();
        boolean m3094e2 = c11440ho.m3094e();
        if ((m3094e || m3094e2) && !(m3094e && m3094e2 && this.f23275a == c11440ho.f23275a)) {
            return false;
        }
        boolean m3093f = m3093f();
        boolean m3093f2 = c11440ho.m3093f();
        if ((m3093f || m3093f2) && !(m3093f && m3093f2 && this.f23281d.equals(c11440ho.f23281d))) {
            return false;
        }
        boolean m3092g = m3092g();
        boolean m3092g2 = c11440ho.m3092g();
        if ((m3092g || m3092g2) && !(m3092g && m3092g2 && this.f23282e.equals(c11440ho.f23282e))) {
            return false;
        }
        boolean m3091h = m3091h();
        boolean m3091h2 = c11440ho.m3091h();
        if ((m3091h || m3091h2) && !(m3091h && m3091h2 && this.f23283f.equals(c11440ho.f23283f))) {
            return false;
        }
        boolean m3090i = m3090i();
        boolean m3090i2 = c11440ho.m3090i();
        if (m3090i || m3090i2) {
            return m3090i && m3090i2 && this.f23284g.equals(c11440ho.f23284g);
        }
        return true;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11440ho c11440ho) {
        int m3076a;
        int m3076a2;
        int m3076a3;
        int m3076a4;
        int m3078a;
        int m3076a5;
        int m3076a6;
        int m3077a;
        int m3076a7;
        if (!getClass().equals(c11440ho.getClass())) {
            return getClass().getName().compareTo(c11440ho.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3103a()).compareTo(Boolean.valueOf(c11440ho.m3103a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3103a() || (m3076a7 = C11443hr.m3076a(this.f23277a, c11440ho.f23277a)) == 0) {
            int compareTo2 = Boolean.valueOf(m3098b()).compareTo(Boolean.valueOf(c11440ho.m3098b()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (!m3098b() || (m3077a = C11443hr.m3077a(this.f23276a, c11440ho.f23276a)) == 0) {
                int compareTo3 = Boolean.valueOf(m3096c()).compareTo(Boolean.valueOf(c11440ho.m3096c()));
                if (compareTo3 != 0) {
                    return compareTo3;
                }
                if (!m3096c() || (m3076a6 = C11443hr.m3076a(this.f23279b, c11440ho.f23279b)) == 0) {
                    int compareTo4 = Boolean.valueOf(m3095d()).compareTo(Boolean.valueOf(c11440ho.m3095d()));
                    if (compareTo4 != 0) {
                        return compareTo4;
                    }
                    if (!m3095d() || (m3076a5 = C11443hr.m3076a(this.f23280c, c11440ho.f23280c)) == 0) {
                        int compareTo5 = Boolean.valueOf(m3094e()).compareTo(Boolean.valueOf(c11440ho.m3094e()));
                        if (compareTo5 != 0) {
                            return compareTo5;
                        }
                        if (!m3094e() || (m3078a = C11443hr.m3078a(this.f23275a, c11440ho.f23275a)) == 0) {
                            int compareTo6 = Boolean.valueOf(m3093f()).compareTo(Boolean.valueOf(c11440ho.m3093f()));
                            if (compareTo6 != 0) {
                                return compareTo6;
                            }
                            if (!m3093f() || (m3076a4 = C11443hr.m3076a(this.f23281d, c11440ho.f23281d)) == 0) {
                                int compareTo7 = Boolean.valueOf(m3092g()).compareTo(Boolean.valueOf(c11440ho.m3092g()));
                                if (compareTo7 != 0) {
                                    return compareTo7;
                                }
                                if (!m3092g() || (m3076a3 = C11443hr.m3076a(this.f23282e, c11440ho.f23282e)) == 0) {
                                    int compareTo8 = Boolean.valueOf(m3091h()).compareTo(Boolean.valueOf(c11440ho.m3091h()));
                                    if (compareTo8 != 0) {
                                        return compareTo8;
                                    }
                                    if (!m3091h() || (m3076a2 = C11443hr.m3076a(this.f23283f, c11440ho.f23283f)) == 0) {
                                        int compareTo9 = Boolean.valueOf(m3090i()).compareTo(Boolean.valueOf(c11440ho.m3090i()));
                                        if (compareTo9 != 0) {
                                            return compareTo9;
                                        }
                                        if (!m3090i() || (m3076a = C11443hr.m3076a(this.f23284g, c11440ho.f23284g)) == 0) {
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
                            this.f23277a = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 2:
                        if (mo3021a.f23307a == 12) {
                            this.f23276a = new C11419gu();
                            this.f23276a.mo3083a(abstractC11456ia);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 3:
                        if (mo3021a.f23307a == 11) {
                            this.f23279b = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 4:
                        if (mo3021a.f23307a == 11) {
                            this.f23280c = abstractC11456ia.mo2990a();
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
                            this.f23275a = abstractC11456ia.mo3022a();
                            m3100a(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 7:
                        if (mo3021a.f23307a == 11) {
                            this.f23281d = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 8:
                        if (mo3021a.f23307a == 11) {
                            this.f23282e = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 9:
                        if (mo3021a.f23307a == 11) {
                            this.f23283f = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 10:
                        if (mo3021a.f23307a == 11) {
                            this.f23284g = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                }
                abstractC11456ia.mo3000g();
            } else {
                abstractC11456ia.mo3001f();
                m3104a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3104a();
        abstractC11456ia.mo3010a(f23266a);
        if (this.f23277a != null && m3103a()) {
            abstractC11456ia.mo3013a(f23265a);
            abstractC11456ia.mo3009a(this.f23277a);
            abstractC11456ia.mo3005b();
        }
        if (this.f23276a != null && m3098b()) {
            abstractC11456ia.mo3013a(f23267b);
            this.f23276a.mo3082b(abstractC11456ia);
            abstractC11456ia.mo3005b();
        }
        if (this.f23279b != null) {
            abstractC11456ia.mo3013a(f23268c);
            abstractC11456ia.mo3009a(this.f23279b);
            abstractC11456ia.mo3005b();
        }
        if (this.f23280c != null && m3095d()) {
            abstractC11456ia.mo3013a(f23269d);
            abstractC11456ia.mo3009a(this.f23280c);
            abstractC11456ia.mo3005b();
        }
        if (m3094e()) {
            abstractC11456ia.mo3013a(f23270e);
            abstractC11456ia.mo3014a(this.f23275a);
            abstractC11456ia.mo3005b();
        }
        if (this.f23281d != null && m3093f()) {
            abstractC11456ia.mo3013a(f23271f);
            abstractC11456ia.mo3009a(this.f23281d);
            abstractC11456ia.mo3005b();
        }
        if (this.f23282e != null && m3092g()) {
            abstractC11456ia.mo3013a(f23272g);
            abstractC11456ia.mo3009a(this.f23282e);
            abstractC11456ia.mo3005b();
        }
        if (this.f23283f != null && m3091h()) {
            abstractC11456ia.mo3013a(f23273h);
            abstractC11456ia.mo3009a(this.f23283f);
            abstractC11456ia.mo3005b();
        }
        if (this.f23284g != null && m3090i()) {
            abstractC11456ia.mo3013a(f23274i);
            abstractC11456ia.mo3009a(this.f23284g);
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionUnSubscriptionResult(");
        if (m3103a()) {
            sb.append("debug:");
            String str = this.f23277a;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (m3098b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            C11419gu c11419gu = this.f23276a;
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
        String str2 = this.f23279b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        if (m3095d()) {
            sb.append(", ");
            sb.append("appId:");
            String str3 = this.f23280c;
            if (str3 == null) {
                sb.append("null");
            } else {
                sb.append(str3);
            }
        }
        if (m3094e()) {
            sb.append(", ");
            sb.append("errorCode:");
            sb.append(this.f23275a);
        }
        if (m3093f()) {
            sb.append(", ");
            sb.append("reason:");
            String str4 = this.f23281d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m3092g()) {
            sb.append(", ");
            sb.append("topic:");
            String str5 = this.f23282e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m3091h()) {
            sb.append(", ");
            sb.append("packageName:");
            String str6 = this.f23283f;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
        }
        if (m3090i()) {
            sb.append(", ");
            sb.append("category:");
            String str7 = this.f23284g;
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
    public void m3104a() {
        if (this.f23279b != null) {
            return;
        }
        throw new C11457ib("Required field 'id' was not present! Struct: " + toString());
    }
}
