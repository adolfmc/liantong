package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.xiaomi.push.gw */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11421gw implements InterfaceC11442hq<C11421gw, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public C11419gu f22901a;

    /* renamed from: a */
    public String f22902a;

    /* renamed from: a */
    public Map<String, String> f22904a;

    /* renamed from: b */
    public String f22905b;

    /* renamed from: c */
    public String f22906c;

    /* renamed from: d */
    public String f22907d;

    /* renamed from: e */
    public String f22908e;

    /* renamed from: f */
    public String f22909f;

    /* renamed from: g */
    public String f22910g;

    /* renamed from: a */
    private static final C11461if f22890a = new C11461if("XmPushActionAckNotification");

    /* renamed from: a */
    private static final C11452hx f22889a = new C11452hx("", (byte) 11, 1);

    /* renamed from: b */
    private static final C11452hx f22891b = new C11452hx("", (byte) 12, 2);

    /* renamed from: c */
    private static final C11452hx f22892c = new C11452hx("", (byte) 11, 3);

    /* renamed from: d */
    private static final C11452hx f22893d = new C11452hx("", (byte) 11, 4);

    /* renamed from: e */
    private static final C11452hx f22894e = new C11452hx("", (byte) 11, 5);

    /* renamed from: f */
    private static final C11452hx f22895f = new C11452hx("", (byte) 10, 7);

    /* renamed from: g */
    private static final C11452hx f22896g = new C11452hx("", (byte) 11, 8);

    /* renamed from: h */
    private static final C11452hx f22897h = new C11452hx("", (byte) 13, 9);

    /* renamed from: i */
    private static final C11452hx f22898i = new C11452hx("", (byte) 11, 10);

    /* renamed from: j */
    private static final C11452hx f22899j = new C11452hx("", (byte) 11, 11);

    /* renamed from: a */
    private BitSet f22903a = new BitSet(1);

    /* renamed from: a */
    public long f22900a = 0;

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public boolean m3469a() {
        return this.f22902a != null;
    }

    /* renamed from: a */
    public C11421gw m3467a(C11419gu c11419gu) {
        this.f22901a = c11419gu;
        return this;
    }

    /* renamed from: b */
    public boolean m3461b() {
        return this.f22901a != null;
    }

    /* renamed from: a */
    public String m3472a() {
        return this.f22905b;
    }

    /* renamed from: a */
    public C11421gw m3464a(String str) {
        this.f22905b = str;
        return this;
    }

    /* renamed from: c */
    public boolean m3459c() {
        return this.f22905b != null;
    }

    /* renamed from: b */
    public C11421gw m3460b(String str) {
        this.f22906c = str;
        return this;
    }

    /* renamed from: d */
    public boolean m3457d() {
        return this.f22906c != null;
    }

    /* renamed from: b */
    public String m3462b() {
        return this.f22907d;
    }

    /* renamed from: c */
    public C11421gw m3458c(String str) {
        this.f22907d = str;
        return this;
    }

    /* renamed from: e */
    public boolean m3455e() {
        return this.f22907d != null;
    }

    /* renamed from: a */
    public C11421gw m3468a(long j) {
        this.f22900a = j;
        m3463a(true);
        return this;
    }

    /* renamed from: f */
    public boolean m3453f() {
        return this.f22903a.get(0);
    }

    /* renamed from: a */
    public void m3463a(boolean z) {
        this.f22903a.set(0, z);
    }

    /* renamed from: d */
    public C11421gw m3456d(String str) {
        this.f22908e = str;
        return this;
    }

    /* renamed from: g */
    public boolean m3452g() {
        return this.f22908e != null;
    }

    /* renamed from: a */
    public Map<String, String> m3471a() {
        return this.f22904a;
    }

    /* renamed from: h */
    public boolean m3451h() {
        return this.f22904a != null;
    }

    /* renamed from: e */
    public C11421gw m3454e(String str) {
        this.f22909f = str;
        return this;
    }

    /* renamed from: i */
    public boolean m3450i() {
        return this.f22909f != null;
    }

    /* renamed from: j */
    public boolean m3449j() {
        return this.f22910g != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11421gw)) {
            return m3465a((C11421gw) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3465a(C11421gw c11421gw) {
        if (c11421gw == null) {
            return false;
        }
        boolean m3469a = m3469a();
        boolean m3469a2 = c11421gw.m3469a();
        if ((m3469a || m3469a2) && !(m3469a && m3469a2 && this.f22902a.equals(c11421gw.f22902a))) {
            return false;
        }
        boolean m3461b = m3461b();
        boolean m3461b2 = c11421gw.m3461b();
        if ((m3461b || m3461b2) && !(m3461b && m3461b2 && this.f22901a.m3514a(c11421gw.f22901a))) {
            return false;
        }
        boolean m3459c = m3459c();
        boolean m3459c2 = c11421gw.m3459c();
        if ((m3459c || m3459c2) && !(m3459c && m3459c2 && this.f22905b.equals(c11421gw.f22905b))) {
            return false;
        }
        boolean m3457d = m3457d();
        boolean m3457d2 = c11421gw.m3457d();
        if ((m3457d || m3457d2) && !(m3457d && m3457d2 && this.f22906c.equals(c11421gw.f22906c))) {
            return false;
        }
        boolean m3455e = m3455e();
        boolean m3455e2 = c11421gw.m3455e();
        if ((m3455e || m3455e2) && !(m3455e && m3455e2 && this.f22907d.equals(c11421gw.f22907d))) {
            return false;
        }
        boolean m3453f = m3453f();
        boolean m3453f2 = c11421gw.m3453f();
        if ((m3453f || m3453f2) && !(m3453f && m3453f2 && this.f22900a == c11421gw.f22900a)) {
            return false;
        }
        boolean m3452g = m3452g();
        boolean m3452g2 = c11421gw.m3452g();
        if ((m3452g || m3452g2) && !(m3452g && m3452g2 && this.f22908e.equals(c11421gw.f22908e))) {
            return false;
        }
        boolean m3451h = m3451h();
        boolean m3451h2 = c11421gw.m3451h();
        if ((m3451h || m3451h2) && !(m3451h && m3451h2 && this.f22904a.equals(c11421gw.f22904a))) {
            return false;
        }
        boolean m3450i = m3450i();
        boolean m3450i2 = c11421gw.m3450i();
        if ((m3450i || m3450i2) && !(m3450i && m3450i2 && this.f22909f.equals(c11421gw.f22909f))) {
            return false;
        }
        boolean m3449j = m3449j();
        boolean m3449j2 = c11421gw.m3449j();
        if (m3449j || m3449j2) {
            return m3449j && m3449j2 && this.f22910g.equals(c11421gw.f22910g);
        }
        return true;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11421gw c11421gw) {
        int m3076a;
        int m3076a2;
        int m3069a;
        int m3076a3;
        int m3078a;
        int m3076a4;
        int m3076a5;
        int m3076a6;
        int m3077a;
        int m3076a7;
        if (!getClass().equals(c11421gw.getClass())) {
            return getClass().getName().compareTo(c11421gw.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3469a()).compareTo(Boolean.valueOf(c11421gw.m3469a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3469a() || (m3076a7 = C11443hr.m3076a(this.f22902a, c11421gw.f22902a)) == 0) {
            int compareTo2 = Boolean.valueOf(m3461b()).compareTo(Boolean.valueOf(c11421gw.m3461b()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (!m3461b() || (m3077a = C11443hr.m3077a(this.f22901a, c11421gw.f22901a)) == 0) {
                int compareTo3 = Boolean.valueOf(m3459c()).compareTo(Boolean.valueOf(c11421gw.m3459c()));
                if (compareTo3 != 0) {
                    return compareTo3;
                }
                if (!m3459c() || (m3076a6 = C11443hr.m3076a(this.f22905b, c11421gw.f22905b)) == 0) {
                    int compareTo4 = Boolean.valueOf(m3457d()).compareTo(Boolean.valueOf(c11421gw.m3457d()));
                    if (compareTo4 != 0) {
                        return compareTo4;
                    }
                    if (!m3457d() || (m3076a5 = C11443hr.m3076a(this.f22906c, c11421gw.f22906c)) == 0) {
                        int compareTo5 = Boolean.valueOf(m3455e()).compareTo(Boolean.valueOf(c11421gw.m3455e()));
                        if (compareTo5 != 0) {
                            return compareTo5;
                        }
                        if (!m3455e() || (m3076a4 = C11443hr.m3076a(this.f22907d, c11421gw.f22907d)) == 0) {
                            int compareTo6 = Boolean.valueOf(m3453f()).compareTo(Boolean.valueOf(c11421gw.m3453f()));
                            if (compareTo6 != 0) {
                                return compareTo6;
                            }
                            if (!m3453f() || (m3078a = C11443hr.m3078a(this.f22900a, c11421gw.f22900a)) == 0) {
                                int compareTo7 = Boolean.valueOf(m3452g()).compareTo(Boolean.valueOf(c11421gw.m3452g()));
                                if (compareTo7 != 0) {
                                    return compareTo7;
                                }
                                if (!m3452g() || (m3076a3 = C11443hr.m3076a(this.f22908e, c11421gw.f22908e)) == 0) {
                                    int compareTo8 = Boolean.valueOf(m3451h()).compareTo(Boolean.valueOf(c11421gw.m3451h()));
                                    if (compareTo8 != 0) {
                                        return compareTo8;
                                    }
                                    if (!m3451h() || (m3069a = C11443hr.m3069a(this.f22904a, c11421gw.f22904a)) == 0) {
                                        int compareTo9 = Boolean.valueOf(m3450i()).compareTo(Boolean.valueOf(c11421gw.m3450i()));
                                        if (compareTo9 != 0) {
                                            return compareTo9;
                                        }
                                        if (!m3450i() || (m3076a2 = C11443hr.m3076a(this.f22909f, c11421gw.f22909f)) == 0) {
                                            int compareTo10 = Boolean.valueOf(m3449j()).compareTo(Boolean.valueOf(c11421gw.m3449j()));
                                            if (compareTo10 != 0) {
                                                return compareTo10;
                                            }
                                            if (!m3449j() || (m3076a = C11443hr.m3076a(this.f22910g, c11421gw.f22910g)) == 0) {
                                                return 0;
                                            }
                                            return m3076a;
                                        }
                                        return m3076a2;
                                    }
                                    return m3069a;
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
                            this.f22902a = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 2:
                        if (mo3021a.f23307a == 12) {
                            this.f22901a = new C11419gu();
                            this.f22901a.mo3083a(abstractC11456ia);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 3:
                        if (mo3021a.f23307a == 11) {
                            this.f22905b = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 4:
                        if (mo3021a.f23307a == 11) {
                            this.f22906c = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 5:
                        if (mo3021a.f23307a == 11) {
                            this.f22907d = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 6:
                    default:
                        C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                        break;
                    case 7:
                        if (mo3021a.f23307a == 10) {
                            this.f22900a = abstractC11456ia.mo3022a();
                            m3463a(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 8:
                        if (mo3021a.f23307a == 11) {
                            this.f22908e = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 9:
                        if (mo3021a.f23307a == 13) {
                            C11454hz mo2992a = abstractC11456ia.mo2992a();
                            this.f22904a = new HashMap(mo2992a.f23313a * 2);
                            for (int i = 0; i < mo2992a.f23313a; i++) {
                                this.f22904a.put(abstractC11456ia.mo2990a(), abstractC11456ia.mo2990a());
                            }
                            abstractC11456ia.mo2999h();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 10:
                        if (mo3021a.f23307a == 11) {
                            this.f22909f = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 11:
                        if (mo3021a.f23307a == 11) {
                            this.f22910g = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                }
                abstractC11456ia.mo3000g();
            } else {
                abstractC11456ia.mo3001f();
                m3470a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3470a();
        abstractC11456ia.mo3010a(f22890a);
        if (this.f22902a != null && m3469a()) {
            abstractC11456ia.mo3013a(f22889a);
            abstractC11456ia.mo3009a(this.f22902a);
            abstractC11456ia.mo3005b();
        }
        if (this.f22901a != null && m3461b()) {
            abstractC11456ia.mo3013a(f22891b);
            this.f22901a.mo3082b(abstractC11456ia);
            abstractC11456ia.mo3005b();
        }
        if (this.f22905b != null) {
            abstractC11456ia.mo3013a(f22892c);
            abstractC11456ia.mo3009a(this.f22905b);
            abstractC11456ia.mo3005b();
        }
        if (this.f22906c != null && m3457d()) {
            abstractC11456ia.mo3013a(f22893d);
            abstractC11456ia.mo3009a(this.f22906c);
            abstractC11456ia.mo3005b();
        }
        if (this.f22907d != null && m3455e()) {
            abstractC11456ia.mo3013a(f22894e);
            abstractC11456ia.mo3009a(this.f22907d);
            abstractC11456ia.mo3005b();
        }
        if (m3453f()) {
            abstractC11456ia.mo3013a(f22895f);
            abstractC11456ia.mo3014a(this.f22900a);
            abstractC11456ia.mo3005b();
        }
        if (this.f22908e != null && m3452g()) {
            abstractC11456ia.mo3013a(f22896g);
            abstractC11456ia.mo3009a(this.f22908e);
            abstractC11456ia.mo3005b();
        }
        if (this.f22904a != null && m3451h()) {
            abstractC11456ia.mo3013a(f22897h);
            abstractC11456ia.mo3011a(new C11454hz((byte) 11, (byte) 11, this.f22904a.size()));
            for (Map.Entry<String, String> entry : this.f22904a.entrySet()) {
                abstractC11456ia.mo3009a(entry.getKey());
                abstractC11456ia.mo3009a(entry.getValue());
            }
            abstractC11456ia.mo3003d();
            abstractC11456ia.mo3005b();
        }
        if (this.f22909f != null && m3450i()) {
            abstractC11456ia.mo3013a(f22898i);
            abstractC11456ia.mo3009a(this.f22909f);
            abstractC11456ia.mo3005b();
        }
        if (this.f22910g != null && m3449j()) {
            abstractC11456ia.mo3013a(f22899j);
            abstractC11456ia.mo3009a(this.f22910g);
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionAckNotification(");
        if (m3469a()) {
            sb.append("debug:");
            String str = this.f22902a;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (m3461b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            C11419gu c11419gu = this.f22901a;
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
        String str2 = this.f22905b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        if (m3457d()) {
            sb.append(", ");
            sb.append("appId:");
            String str3 = this.f22906c;
            if (str3 == null) {
                sb.append("null");
            } else {
                sb.append(str3);
            }
        }
        if (m3455e()) {
            sb.append(", ");
            sb.append("type:");
            String str4 = this.f22907d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m3453f()) {
            sb.append(", ");
            sb.append("errorCode:");
            sb.append(this.f22900a);
        }
        if (m3452g()) {
            sb.append(", ");
            sb.append("reason:");
            String str5 = this.f22908e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m3451h()) {
            sb.append(", ");
            sb.append("extra:");
            Map<String, String> map = this.f22904a;
            if (map == null) {
                sb.append("null");
            } else {
                sb.append(map);
            }
        }
        if (m3450i()) {
            sb.append(", ");
            sb.append("packageName:");
            String str6 = this.f22909f;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
        }
        if (m3449j()) {
            sb.append(", ");
            sb.append("category:");
            String str7 = this.f22910g;
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
    public void m3470a() {
        if (this.f22905b != null) {
            return;
        }
        throw new C11457ib("Required field 'id' was not present! Struct: " + toString());
    }
}
