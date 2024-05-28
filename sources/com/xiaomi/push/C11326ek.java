package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xiaomi.push.ek */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11326ek implements InterfaceC11442hq<C11326ek, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public String f22148a;

    /* renamed from: a */
    public List<C11325ej> f22149a;

    /* renamed from: b */
    public String f22150b;

    /* renamed from: a */
    private static final C11461if f22145a = new C11461if("StatsEvents");

    /* renamed from: a */
    private static final C11452hx f22144a = new C11452hx("", (byte) 11, 1);

    /* renamed from: b */
    private static final C11452hx f22146b = new C11452hx("", (byte) 11, 2);

    /* renamed from: c */
    private static final C11452hx f22147c = new C11452hx("", (byte) 15, 3);

    public int hashCode() {
        return 0;
    }

    public C11326ek() {
    }

    public C11326ek(String str, List<C11325ej> list) {
        this();
        this.f22148a = str;
        this.f22149a = list;
    }

    /* renamed from: a */
    public boolean m4012a() {
        return this.f22148a != null;
    }

    /* renamed from: a */
    public C11326ek m4009a(String str) {
        this.f22150b = str;
        return this;
    }

    /* renamed from: b */
    public boolean m4008b() {
        return this.f22150b != null;
    }

    /* renamed from: c */
    public boolean m4007c() {
        return this.f22149a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11326ek)) {
            return m4010a((C11326ek) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m4010a(C11326ek c11326ek) {
        if (c11326ek == null) {
            return false;
        }
        boolean m4012a = m4012a();
        boolean m4012a2 = c11326ek.m4012a();
        if ((m4012a || m4012a2) && !(m4012a && m4012a2 && this.f22148a.equals(c11326ek.f22148a))) {
            return false;
        }
        boolean m4008b = m4008b();
        boolean m4008b2 = c11326ek.m4008b();
        if ((m4008b || m4008b2) && !(m4008b && m4008b2 && this.f22150b.equals(c11326ek.f22150b))) {
            return false;
        }
        boolean m4007c = m4007c();
        boolean m4007c2 = c11326ek.m4007c();
        if (m4007c || m4007c2) {
            return m4007c && m4007c2 && this.f22149a.equals(c11326ek.f22149a);
        }
        return true;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11326ek c11326ek) {
        int m3070a;
        int m3076a;
        int m3076a2;
        if (!getClass().equals(c11326ek.getClass())) {
            return getClass().getName().compareTo(c11326ek.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m4012a()).compareTo(Boolean.valueOf(c11326ek.m4012a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m4012a() || (m3076a2 = C11443hr.m3076a(this.f22148a, c11326ek.f22148a)) == 0) {
            int compareTo2 = Boolean.valueOf(m4008b()).compareTo(Boolean.valueOf(c11326ek.m4008b()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (!m4008b() || (m3076a = C11443hr.m3076a(this.f22150b, c11326ek.f22150b)) == 0) {
                int compareTo3 = Boolean.valueOf(m4007c()).compareTo(Boolean.valueOf(c11326ek.m4007c()));
                if (compareTo3 != 0) {
                    return compareTo3;
                }
                if (!m4007c() || (m3070a = C11443hr.m3070a(this.f22149a, c11326ek.f22149a)) == 0) {
                    return 0;
                }
                return m3070a;
            }
            return m3076a;
        }
        return m3076a2;
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
                            this.f22148a = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 2:
                        if (mo3021a.f23307a == 11) {
                            this.f22150b = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 3:
                        if (mo3021a.f23307a == 15) {
                            C11453hy mo2993a = abstractC11456ia.mo2993a();
                            this.f22149a = new ArrayList(mo2993a.f23311a);
                            for (int i = 0; i < mo2993a.f23311a; i++) {
                                C11325ej c11325ej = new C11325ej();
                                c11325ej.mo3083a(abstractC11456ia);
                                this.f22149a.add(c11325ej);
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
                m4013a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m4013a();
        abstractC11456ia.mo3010a(f22145a);
        if (this.f22148a != null) {
            abstractC11456ia.mo3013a(f22144a);
            abstractC11456ia.mo3009a(this.f22148a);
            abstractC11456ia.mo3005b();
        }
        if (this.f22150b != null && m4008b()) {
            abstractC11456ia.mo3013a(f22146b);
            abstractC11456ia.mo3009a(this.f22150b);
            abstractC11456ia.mo3005b();
        }
        if (this.f22149a != null) {
            abstractC11456ia.mo3013a(f22147c);
            abstractC11456ia.mo3012a(new C11453hy((byte) 12, this.f22149a.size()));
            for (C11325ej c11325ej : this.f22149a) {
                c11325ej.mo3082b(abstractC11456ia);
            }
            abstractC11456ia.mo3002e();
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("StatsEvents(");
        sb.append("uuid:");
        String str = this.f22148a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        if (m4008b()) {
            sb.append(", ");
            sb.append("operator:");
            String str2 = this.f22150b;
            if (str2 == null) {
                sb.append("null");
            } else {
                sb.append(str2);
            }
        }
        sb.append(", ");
        sb.append("events:");
        List<C11325ej> list = this.f22149a;
        if (list == null) {
            sb.append("null");
        } else {
            sb.append(list);
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    public void m4013a() {
        if (this.f22148a == null) {
            throw new C11457ib("Required field 'uuid' was not present! Struct: " + toString());
        } else if (this.f22149a != null) {
        } else {
            throw new C11457ib("Required field 'events' was not present! Struct: " + toString());
        }
    }
}
