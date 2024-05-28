package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xiaomi.push.hj */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11435hj implements InterfaceC11442hq<C11435hj, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public C11419gu f23174a;

    /* renamed from: a */
    public String f23175a;

    /* renamed from: a */
    public List<String> f23176a;

    /* renamed from: b */
    public String f23177b;

    /* renamed from: c */
    public String f23178c;

    /* renamed from: d */
    public String f23179d;

    /* renamed from: e */
    public String f23180e;

    /* renamed from: f */
    public String f23181f;

    /* renamed from: a */
    private static final C11461if f23166a = new C11461if("XmPushActionSubscription");

    /* renamed from: a */
    private static final C11452hx f23165a = new C11452hx("", (byte) 11, 1);

    /* renamed from: b */
    private static final C11452hx f23167b = new C11452hx("", (byte) 12, 2);

    /* renamed from: c */
    private static final C11452hx f23168c = new C11452hx("", (byte) 11, 3);

    /* renamed from: d */
    private static final C11452hx f23169d = new C11452hx("", (byte) 11, 4);

    /* renamed from: e */
    private static final C11452hx f23170e = new C11452hx("", (byte) 11, 5);

    /* renamed from: f */
    private static final C11452hx f23171f = new C11452hx("", (byte) 11, 6);

    /* renamed from: g */
    private static final C11452hx f23172g = new C11452hx("", (byte) 11, 7);

    /* renamed from: h */
    private static final C11452hx f23173h = new C11452hx("", (byte) 15, 8);

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public boolean m3190a() {
        return this.f23175a != null;
    }

    /* renamed from: b */
    public boolean m3186b() {
        return this.f23174a != null;
    }

    /* renamed from: a */
    public C11435hj m3187a(String str) {
        this.f23177b = str;
        return this;
    }

    /* renamed from: c */
    public boolean m3184c() {
        return this.f23177b != null;
    }

    /* renamed from: b */
    public C11435hj m3185b(String str) {
        this.f23178c = str;
        return this;
    }

    /* renamed from: d */
    public boolean m3182d() {
        return this.f23178c != null;
    }

    /* renamed from: c */
    public C11435hj m3183c(String str) {
        this.f23179d = str;
        return this;
    }

    /* renamed from: e */
    public boolean m3180e() {
        return this.f23179d != null;
    }

    /* renamed from: d */
    public C11435hj m3181d(String str) {
        this.f23180e = str;
        return this;
    }

    /* renamed from: f */
    public boolean m3178f() {
        return this.f23180e != null;
    }

    /* renamed from: e */
    public C11435hj m3179e(String str) {
        this.f23181f = str;
        return this;
    }

    /* renamed from: g */
    public boolean m3177g() {
        return this.f23181f != null;
    }

    /* renamed from: h */
    public boolean m3176h() {
        return this.f23176a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11435hj)) {
            return m3188a((C11435hj) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3188a(C11435hj c11435hj) {
        if (c11435hj == null) {
            return false;
        }
        boolean m3190a = m3190a();
        boolean m3190a2 = c11435hj.m3190a();
        if ((m3190a || m3190a2) && !(m3190a && m3190a2 && this.f23175a.equals(c11435hj.f23175a))) {
            return false;
        }
        boolean m3186b = m3186b();
        boolean m3186b2 = c11435hj.m3186b();
        if ((m3186b || m3186b2) && !(m3186b && m3186b2 && this.f23174a.m3514a(c11435hj.f23174a))) {
            return false;
        }
        boolean m3184c = m3184c();
        boolean m3184c2 = c11435hj.m3184c();
        if ((m3184c || m3184c2) && !(m3184c && m3184c2 && this.f23177b.equals(c11435hj.f23177b))) {
            return false;
        }
        boolean m3182d = m3182d();
        boolean m3182d2 = c11435hj.m3182d();
        if ((m3182d || m3182d2) && !(m3182d && m3182d2 && this.f23178c.equals(c11435hj.f23178c))) {
            return false;
        }
        boolean m3180e = m3180e();
        boolean m3180e2 = c11435hj.m3180e();
        if ((m3180e || m3180e2) && !(m3180e && m3180e2 && this.f23179d.equals(c11435hj.f23179d))) {
            return false;
        }
        boolean m3178f = m3178f();
        boolean m3178f2 = c11435hj.m3178f();
        if ((m3178f || m3178f2) && !(m3178f && m3178f2 && this.f23180e.equals(c11435hj.f23180e))) {
            return false;
        }
        boolean m3177g = m3177g();
        boolean m3177g2 = c11435hj.m3177g();
        if ((m3177g || m3177g2) && !(m3177g && m3177g2 && this.f23181f.equals(c11435hj.f23181f))) {
            return false;
        }
        boolean m3176h = m3176h();
        boolean m3176h2 = c11435hj.m3176h();
        if (m3176h || m3176h2) {
            return m3176h && m3176h2 && this.f23176a.equals(c11435hj.f23176a);
        }
        return true;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11435hj c11435hj) {
        int m3070a;
        int m3076a;
        int m3076a2;
        int m3076a3;
        int m3076a4;
        int m3076a5;
        int m3077a;
        int m3076a6;
        if (!getClass().equals(c11435hj.getClass())) {
            return getClass().getName().compareTo(c11435hj.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3190a()).compareTo(Boolean.valueOf(c11435hj.m3190a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3190a() || (m3076a6 = C11443hr.m3076a(this.f23175a, c11435hj.f23175a)) == 0) {
            int compareTo2 = Boolean.valueOf(m3186b()).compareTo(Boolean.valueOf(c11435hj.m3186b()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (!m3186b() || (m3077a = C11443hr.m3077a(this.f23174a, c11435hj.f23174a)) == 0) {
                int compareTo3 = Boolean.valueOf(m3184c()).compareTo(Boolean.valueOf(c11435hj.m3184c()));
                if (compareTo3 != 0) {
                    return compareTo3;
                }
                if (!m3184c() || (m3076a5 = C11443hr.m3076a(this.f23177b, c11435hj.f23177b)) == 0) {
                    int compareTo4 = Boolean.valueOf(m3182d()).compareTo(Boolean.valueOf(c11435hj.m3182d()));
                    if (compareTo4 != 0) {
                        return compareTo4;
                    }
                    if (!m3182d() || (m3076a4 = C11443hr.m3076a(this.f23178c, c11435hj.f23178c)) == 0) {
                        int compareTo5 = Boolean.valueOf(m3180e()).compareTo(Boolean.valueOf(c11435hj.m3180e()));
                        if (compareTo5 != 0) {
                            return compareTo5;
                        }
                        if (!m3180e() || (m3076a3 = C11443hr.m3076a(this.f23179d, c11435hj.f23179d)) == 0) {
                            int compareTo6 = Boolean.valueOf(m3178f()).compareTo(Boolean.valueOf(c11435hj.m3178f()));
                            if (compareTo6 != 0) {
                                return compareTo6;
                            }
                            if (!m3178f() || (m3076a2 = C11443hr.m3076a(this.f23180e, c11435hj.f23180e)) == 0) {
                                int compareTo7 = Boolean.valueOf(m3177g()).compareTo(Boolean.valueOf(c11435hj.m3177g()));
                                if (compareTo7 != 0) {
                                    return compareTo7;
                                }
                                if (!m3177g() || (m3076a = C11443hr.m3076a(this.f23181f, c11435hj.f23181f)) == 0) {
                                    int compareTo8 = Boolean.valueOf(m3176h()).compareTo(Boolean.valueOf(c11435hj.m3176h()));
                                    if (compareTo8 != 0) {
                                        return compareTo8;
                                    }
                                    if (!m3176h() || (m3070a = C11443hr.m3070a(this.f23176a, c11435hj.f23176a)) == 0) {
                                        return 0;
                                    }
                                    return m3070a;
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
            return m3077a;
        }
        return m3076a6;
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
                            this.f23175a = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 2:
                        if (mo3021a.f23307a == 12) {
                            this.f23174a = new C11419gu();
                            this.f23174a.mo3083a(abstractC11456ia);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 3:
                        if (mo3021a.f23307a == 11) {
                            this.f23177b = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 4:
                        if (mo3021a.f23307a == 11) {
                            this.f23178c = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 5:
                        if (mo3021a.f23307a == 11) {
                            this.f23179d = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 6:
                        if (mo3021a.f23307a == 11) {
                            this.f23180e = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 7:
                        if (mo3021a.f23307a == 11) {
                            this.f23181f = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 8:
                        if (mo3021a.f23307a == 15) {
                            C11453hy mo2993a = abstractC11456ia.mo2993a();
                            this.f23176a = new ArrayList(mo2993a.f23311a);
                            for (int i = 0; i < mo2993a.f23311a; i++) {
                                this.f23176a.add(abstractC11456ia.mo2990a());
                            }
                            abstractC11456ia.mo2998i();
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
                m3191a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3191a();
        abstractC11456ia.mo3010a(f23166a);
        if (this.f23175a != null && m3190a()) {
            abstractC11456ia.mo3013a(f23165a);
            abstractC11456ia.mo3009a(this.f23175a);
            abstractC11456ia.mo3005b();
        }
        if (this.f23174a != null && m3186b()) {
            abstractC11456ia.mo3013a(f23167b);
            this.f23174a.mo3082b(abstractC11456ia);
            abstractC11456ia.mo3005b();
        }
        if (this.f23177b != null) {
            abstractC11456ia.mo3013a(f23168c);
            abstractC11456ia.mo3009a(this.f23177b);
            abstractC11456ia.mo3005b();
        }
        if (this.f23178c != null) {
            abstractC11456ia.mo3013a(f23169d);
            abstractC11456ia.mo3009a(this.f23178c);
            abstractC11456ia.mo3005b();
        }
        if (this.f23179d != null) {
            abstractC11456ia.mo3013a(f23170e);
            abstractC11456ia.mo3009a(this.f23179d);
            abstractC11456ia.mo3005b();
        }
        if (this.f23180e != null && m3178f()) {
            abstractC11456ia.mo3013a(f23171f);
            abstractC11456ia.mo3009a(this.f23180e);
            abstractC11456ia.mo3005b();
        }
        if (this.f23181f != null && m3177g()) {
            abstractC11456ia.mo3013a(f23172g);
            abstractC11456ia.mo3009a(this.f23181f);
            abstractC11456ia.mo3005b();
        }
        if (this.f23176a != null && m3176h()) {
            abstractC11456ia.mo3013a(f23173h);
            abstractC11456ia.mo3012a(new C11453hy((byte) 11, this.f23176a.size()));
            for (String str : this.f23176a) {
                abstractC11456ia.mo3009a(str);
            }
            abstractC11456ia.mo3002e();
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionSubscription(");
        if (m3190a()) {
            sb.append("debug:");
            String str = this.f23175a;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (m3186b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            C11419gu c11419gu = this.f23174a;
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
        String str2 = this.f23177b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("appId:");
        String str3 = this.f23178c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("topic:");
        String str4 = this.f23179d;
        if (str4 == null) {
            sb.append("null");
        } else {
            sb.append(str4);
        }
        if (m3178f()) {
            sb.append(", ");
            sb.append("packageName:");
            String str5 = this.f23180e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m3177g()) {
            sb.append(", ");
            sb.append("category:");
            String str6 = this.f23181f;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
        }
        if (m3176h()) {
            sb.append(", ");
            sb.append("aliases:");
            List<String> list = this.f23176a;
            if (list == null) {
                sb.append("null");
            } else {
                sb.append(list);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    public void m3191a() {
        if (this.f23177b == null) {
            throw new C11457ib("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f23178c == null) {
            throw new C11457ib("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.f23179d != null) {
        } else {
            throw new C11457ib("Required field 'topic' was not present! Struct: " + toString());
        }
    }
}
