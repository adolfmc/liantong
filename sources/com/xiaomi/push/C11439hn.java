package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xiaomi.push.hn */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11439hn implements InterfaceC11442hq<C11439hn, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public C11419gu f23257a;

    /* renamed from: a */
    public String f23258a;

    /* renamed from: a */
    public List<String> f23259a;

    /* renamed from: b */
    public String f23260b;

    /* renamed from: c */
    public String f23261c;

    /* renamed from: d */
    public String f23262d;

    /* renamed from: e */
    public String f23263e;

    /* renamed from: f */
    public String f23264f;

    /* renamed from: a */
    private static final C11461if f23249a = new C11461if("XmPushActionUnSubscription");

    /* renamed from: a */
    private static final C11452hx f23248a = new C11452hx("", (byte) 11, 1);

    /* renamed from: b */
    private static final C11452hx f23250b = new C11452hx("", (byte) 12, 2);

    /* renamed from: c */
    private static final C11452hx f23251c = new C11452hx("", (byte) 11, 3);

    /* renamed from: d */
    private static final C11452hx f23252d = new C11452hx("", (byte) 11, 4);

    /* renamed from: e */
    private static final C11452hx f23253e = new C11452hx("", (byte) 11, 5);

    /* renamed from: f */
    private static final C11452hx f23254f = new C11452hx("", (byte) 11, 6);

    /* renamed from: g */
    private static final C11452hx f23255g = new C11452hx("", (byte) 11, 7);

    /* renamed from: h */
    private static final C11452hx f23256h = new C11452hx("", (byte) 15, 8);

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public boolean m3120a() {
        return this.f23258a != null;
    }

    /* renamed from: b */
    public boolean m3116b() {
        return this.f23257a != null;
    }

    /* renamed from: a */
    public C11439hn m3117a(String str) {
        this.f23260b = str;
        return this;
    }

    /* renamed from: c */
    public boolean m3114c() {
        return this.f23260b != null;
    }

    /* renamed from: b */
    public C11439hn m3115b(String str) {
        this.f23261c = str;
        return this;
    }

    /* renamed from: d */
    public boolean m3112d() {
        return this.f23261c != null;
    }

    /* renamed from: c */
    public C11439hn m3113c(String str) {
        this.f23262d = str;
        return this;
    }

    /* renamed from: e */
    public boolean m3110e() {
        return this.f23262d != null;
    }

    /* renamed from: d */
    public C11439hn m3111d(String str) {
        this.f23263e = str;
        return this;
    }

    /* renamed from: f */
    public boolean m3108f() {
        return this.f23263e != null;
    }

    /* renamed from: e */
    public C11439hn m3109e(String str) {
        this.f23264f = str;
        return this;
    }

    /* renamed from: g */
    public boolean m3107g() {
        return this.f23264f != null;
    }

    /* renamed from: h */
    public boolean m3106h() {
        return this.f23259a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11439hn)) {
            return m3118a((C11439hn) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3118a(C11439hn c11439hn) {
        if (c11439hn == null) {
            return false;
        }
        boolean m3120a = m3120a();
        boolean m3120a2 = c11439hn.m3120a();
        if ((m3120a || m3120a2) && !(m3120a && m3120a2 && this.f23258a.equals(c11439hn.f23258a))) {
            return false;
        }
        boolean m3116b = m3116b();
        boolean m3116b2 = c11439hn.m3116b();
        if ((m3116b || m3116b2) && !(m3116b && m3116b2 && this.f23257a.m3514a(c11439hn.f23257a))) {
            return false;
        }
        boolean m3114c = m3114c();
        boolean m3114c2 = c11439hn.m3114c();
        if ((m3114c || m3114c2) && !(m3114c && m3114c2 && this.f23260b.equals(c11439hn.f23260b))) {
            return false;
        }
        boolean m3112d = m3112d();
        boolean m3112d2 = c11439hn.m3112d();
        if ((m3112d || m3112d2) && !(m3112d && m3112d2 && this.f23261c.equals(c11439hn.f23261c))) {
            return false;
        }
        boolean m3110e = m3110e();
        boolean m3110e2 = c11439hn.m3110e();
        if ((m3110e || m3110e2) && !(m3110e && m3110e2 && this.f23262d.equals(c11439hn.f23262d))) {
            return false;
        }
        boolean m3108f = m3108f();
        boolean m3108f2 = c11439hn.m3108f();
        if ((m3108f || m3108f2) && !(m3108f && m3108f2 && this.f23263e.equals(c11439hn.f23263e))) {
            return false;
        }
        boolean m3107g = m3107g();
        boolean m3107g2 = c11439hn.m3107g();
        if ((m3107g || m3107g2) && !(m3107g && m3107g2 && this.f23264f.equals(c11439hn.f23264f))) {
            return false;
        }
        boolean m3106h = m3106h();
        boolean m3106h2 = c11439hn.m3106h();
        if (m3106h || m3106h2) {
            return m3106h && m3106h2 && this.f23259a.equals(c11439hn.f23259a);
        }
        return true;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11439hn c11439hn) {
        int m3070a;
        int m3076a;
        int m3076a2;
        int m3076a3;
        int m3076a4;
        int m3076a5;
        int m3077a;
        int m3076a6;
        if (!getClass().equals(c11439hn.getClass())) {
            return getClass().getName().compareTo(c11439hn.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3120a()).compareTo(Boolean.valueOf(c11439hn.m3120a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3120a() || (m3076a6 = C11443hr.m3076a(this.f23258a, c11439hn.f23258a)) == 0) {
            int compareTo2 = Boolean.valueOf(m3116b()).compareTo(Boolean.valueOf(c11439hn.m3116b()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (!m3116b() || (m3077a = C11443hr.m3077a(this.f23257a, c11439hn.f23257a)) == 0) {
                int compareTo3 = Boolean.valueOf(m3114c()).compareTo(Boolean.valueOf(c11439hn.m3114c()));
                if (compareTo3 != 0) {
                    return compareTo3;
                }
                if (!m3114c() || (m3076a5 = C11443hr.m3076a(this.f23260b, c11439hn.f23260b)) == 0) {
                    int compareTo4 = Boolean.valueOf(m3112d()).compareTo(Boolean.valueOf(c11439hn.m3112d()));
                    if (compareTo4 != 0) {
                        return compareTo4;
                    }
                    if (!m3112d() || (m3076a4 = C11443hr.m3076a(this.f23261c, c11439hn.f23261c)) == 0) {
                        int compareTo5 = Boolean.valueOf(m3110e()).compareTo(Boolean.valueOf(c11439hn.m3110e()));
                        if (compareTo5 != 0) {
                            return compareTo5;
                        }
                        if (!m3110e() || (m3076a3 = C11443hr.m3076a(this.f23262d, c11439hn.f23262d)) == 0) {
                            int compareTo6 = Boolean.valueOf(m3108f()).compareTo(Boolean.valueOf(c11439hn.m3108f()));
                            if (compareTo6 != 0) {
                                return compareTo6;
                            }
                            if (!m3108f() || (m3076a2 = C11443hr.m3076a(this.f23263e, c11439hn.f23263e)) == 0) {
                                int compareTo7 = Boolean.valueOf(m3107g()).compareTo(Boolean.valueOf(c11439hn.m3107g()));
                                if (compareTo7 != 0) {
                                    return compareTo7;
                                }
                                if (!m3107g() || (m3076a = C11443hr.m3076a(this.f23264f, c11439hn.f23264f)) == 0) {
                                    int compareTo8 = Boolean.valueOf(m3106h()).compareTo(Boolean.valueOf(c11439hn.m3106h()));
                                    if (compareTo8 != 0) {
                                        return compareTo8;
                                    }
                                    if (!m3106h() || (m3070a = C11443hr.m3070a(this.f23259a, c11439hn.f23259a)) == 0) {
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
                            this.f23258a = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 2:
                        if (mo3021a.f23307a == 12) {
                            this.f23257a = new C11419gu();
                            this.f23257a.mo3083a(abstractC11456ia);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 3:
                        if (mo3021a.f23307a == 11) {
                            this.f23260b = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 4:
                        if (mo3021a.f23307a == 11) {
                            this.f23261c = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 5:
                        if (mo3021a.f23307a == 11) {
                            this.f23262d = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 6:
                        if (mo3021a.f23307a == 11) {
                            this.f23263e = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 7:
                        if (mo3021a.f23307a == 11) {
                            this.f23264f = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 8:
                        if (mo3021a.f23307a == 15) {
                            C11453hy mo2993a = abstractC11456ia.mo2993a();
                            this.f23259a = new ArrayList(mo2993a.f23311a);
                            for (int i = 0; i < mo2993a.f23311a; i++) {
                                this.f23259a.add(abstractC11456ia.mo2990a());
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
                m3121a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3121a();
        abstractC11456ia.mo3010a(f23249a);
        if (this.f23258a != null && m3120a()) {
            abstractC11456ia.mo3013a(f23248a);
            abstractC11456ia.mo3009a(this.f23258a);
            abstractC11456ia.mo3005b();
        }
        if (this.f23257a != null && m3116b()) {
            abstractC11456ia.mo3013a(f23250b);
            this.f23257a.mo3082b(abstractC11456ia);
            abstractC11456ia.mo3005b();
        }
        if (this.f23260b != null) {
            abstractC11456ia.mo3013a(f23251c);
            abstractC11456ia.mo3009a(this.f23260b);
            abstractC11456ia.mo3005b();
        }
        if (this.f23261c != null) {
            abstractC11456ia.mo3013a(f23252d);
            abstractC11456ia.mo3009a(this.f23261c);
            abstractC11456ia.mo3005b();
        }
        if (this.f23262d != null) {
            abstractC11456ia.mo3013a(f23253e);
            abstractC11456ia.mo3009a(this.f23262d);
            abstractC11456ia.mo3005b();
        }
        if (this.f23263e != null && m3108f()) {
            abstractC11456ia.mo3013a(f23254f);
            abstractC11456ia.mo3009a(this.f23263e);
            abstractC11456ia.mo3005b();
        }
        if (this.f23264f != null && m3107g()) {
            abstractC11456ia.mo3013a(f23255g);
            abstractC11456ia.mo3009a(this.f23264f);
            abstractC11456ia.mo3005b();
        }
        if (this.f23259a != null && m3106h()) {
            abstractC11456ia.mo3013a(f23256h);
            abstractC11456ia.mo3012a(new C11453hy((byte) 11, this.f23259a.size()));
            for (String str : this.f23259a) {
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
        StringBuilder sb = new StringBuilder("XmPushActionUnSubscription(");
        if (m3120a()) {
            sb.append("debug:");
            String str = this.f23258a;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (m3116b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            C11419gu c11419gu = this.f23257a;
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
        String str2 = this.f23260b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("appId:");
        String str3 = this.f23261c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("topic:");
        String str4 = this.f23262d;
        if (str4 == null) {
            sb.append("null");
        } else {
            sb.append(str4);
        }
        if (m3108f()) {
            sb.append(", ");
            sb.append("packageName:");
            String str5 = this.f23263e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m3107g()) {
            sb.append(", ");
            sb.append("category:");
            String str6 = this.f23264f;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
        }
        if (m3106h()) {
            sb.append(", ");
            sb.append("aliases:");
            List<String> list = this.f23259a;
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
    public void m3121a() {
        if (this.f23260b == null) {
            throw new C11457ib("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f23261c == null) {
            throw new C11457ib("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.f23262d != null) {
        } else {
            throw new C11457ib("Required field 'topic' was not present! Struct: " + toString());
        }
    }
}
