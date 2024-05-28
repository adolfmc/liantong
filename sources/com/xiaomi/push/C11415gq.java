package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.gq */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11415gq implements InterfaceC11442hq<C11415gq, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public int f22754a;

    /* renamed from: a */
    public long f22755a;

    /* renamed from: a */
    public String f22756a;

    /* renamed from: a */
    private BitSet f22757a = new BitSet(6);

    /* renamed from: a */
    public boolean f22758a;

    /* renamed from: b */
    public int f22759b;

    /* renamed from: b */
    public boolean f22760b;

    /* renamed from: c */
    public int f22761c;

    /* renamed from: a */
    private static final C11461if f22747a = new C11461if("OnlineConfigItem");

    /* renamed from: a */
    private static final C11452hx f22746a = new C11452hx("", (byte) 8, 1);

    /* renamed from: b */
    private static final C11452hx f22748b = new C11452hx("", (byte) 8, 2);

    /* renamed from: c */
    private static final C11452hx f22749c = new C11452hx("", (byte) 2, 3);

    /* renamed from: d */
    private static final C11452hx f22750d = new C11452hx("", (byte) 8, 4);

    /* renamed from: e */
    private static final C11452hx f22751e = new C11452hx("", (byte) 10, 5);

    /* renamed from: f */
    private static final C11452hx f22752f = new C11452hx("", (byte) 11, 6);

    /* renamed from: g */
    private static final C11452hx f22753g = new C11452hx("", (byte) 2, 7);

    /* renamed from: a */
    public void m3610a() {
    }

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public int m3613a() {
        return this.f22754a;
    }

    /* renamed from: a */
    public boolean m3609a() {
        return this.f22757a.get(0);
    }

    /* renamed from: a */
    public void m3606a(boolean z) {
        this.f22757a.set(0, z);
    }

    /* renamed from: b */
    public int m3605b() {
        return this.f22759b;
    }

    /* renamed from: b */
    public boolean m3604b() {
        return this.f22757a.get(1);
    }

    /* renamed from: b */
    public void m3603b(boolean z) {
        this.f22757a.set(1, z);
    }

    /* renamed from: c */
    public boolean m3601c() {
        return this.f22757a.get(2);
    }

    /* renamed from: c */
    public void m3600c(boolean z) {
        this.f22757a.set(2, z);
    }

    /* renamed from: c */
    public int m3602c() {
        return this.f22761c;
    }

    /* renamed from: d */
    public boolean m3599d() {
        return this.f22757a.get(3);
    }

    /* renamed from: d */
    public void m3598d(boolean z) {
        this.f22757a.set(3, z);
    }

    /* renamed from: a */
    public long m3612a() {
        return this.f22755a;
    }

    /* renamed from: e */
    public boolean m3597e() {
        return this.f22757a.get(4);
    }

    /* renamed from: e */
    public void m3596e(boolean z) {
        this.f22757a.set(4, z);
    }

    /* renamed from: a */
    public String m3611a() {
        return this.f22756a;
    }

    /* renamed from: f */
    public boolean m3595f() {
        return this.f22756a != null;
    }

    /* renamed from: g */
    public boolean m3593g() {
        return this.f22760b;
    }

    /* renamed from: h */
    public boolean m3592h() {
        return this.f22757a.get(5);
    }

    /* renamed from: f */
    public void m3594f(boolean z) {
        this.f22757a.set(5, z);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11415gq)) {
            return m3607a((C11415gq) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3607a(C11415gq c11415gq) {
        if (c11415gq == null) {
            return false;
        }
        boolean m3609a = m3609a();
        boolean m3609a2 = c11415gq.m3609a();
        if ((m3609a || m3609a2) && !(m3609a && m3609a2 && this.f22754a == c11415gq.f22754a)) {
            return false;
        }
        boolean m3604b = m3604b();
        boolean m3604b2 = c11415gq.m3604b();
        if ((m3604b || m3604b2) && !(m3604b && m3604b2 && this.f22759b == c11415gq.f22759b)) {
            return false;
        }
        boolean m3601c = m3601c();
        boolean m3601c2 = c11415gq.m3601c();
        if ((m3601c || m3601c2) && !(m3601c && m3601c2 && this.f22758a == c11415gq.f22758a)) {
            return false;
        }
        boolean m3599d = m3599d();
        boolean m3599d2 = c11415gq.m3599d();
        if ((m3599d || m3599d2) && !(m3599d && m3599d2 && this.f22761c == c11415gq.f22761c)) {
            return false;
        }
        boolean m3597e = m3597e();
        boolean m3597e2 = c11415gq.m3597e();
        if ((m3597e || m3597e2) && !(m3597e && m3597e2 && this.f22755a == c11415gq.f22755a)) {
            return false;
        }
        boolean m3595f = m3595f();
        boolean m3595f2 = c11415gq.m3595f();
        if ((m3595f || m3595f2) && !(m3595f && m3595f2 && this.f22756a.equals(c11415gq.f22756a))) {
            return false;
        }
        boolean m3592h = m3592h();
        boolean m3592h2 = c11415gq.m3592h();
        if (m3592h || m3592h2) {
            return m3592h && m3592h2 && this.f22760b == c11415gq.f22760b;
        }
        return true;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11415gq c11415gq) {
        int m3066a;
        int m3076a;
        int m3078a;
        int m3079a;
        int m3066a2;
        int m3079a2;
        int m3079a3;
        if (!getClass().equals(c11415gq.getClass())) {
            return getClass().getName().compareTo(c11415gq.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3609a()).compareTo(Boolean.valueOf(c11415gq.m3609a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3609a() || (m3079a3 = C11443hr.m3079a(this.f22754a, c11415gq.f22754a)) == 0) {
            int compareTo2 = Boolean.valueOf(m3604b()).compareTo(Boolean.valueOf(c11415gq.m3604b()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (!m3604b() || (m3079a2 = C11443hr.m3079a(this.f22759b, c11415gq.f22759b)) == 0) {
                int compareTo3 = Boolean.valueOf(m3601c()).compareTo(Boolean.valueOf(c11415gq.m3601c()));
                if (compareTo3 != 0) {
                    return compareTo3;
                }
                if (!m3601c() || (m3066a2 = C11443hr.m3066a(this.f22758a, c11415gq.f22758a)) == 0) {
                    int compareTo4 = Boolean.valueOf(m3599d()).compareTo(Boolean.valueOf(c11415gq.m3599d()));
                    if (compareTo4 != 0) {
                        return compareTo4;
                    }
                    if (!m3599d() || (m3079a = C11443hr.m3079a(this.f22761c, c11415gq.f22761c)) == 0) {
                        int compareTo5 = Boolean.valueOf(m3597e()).compareTo(Boolean.valueOf(c11415gq.m3597e()));
                        if (compareTo5 != 0) {
                            return compareTo5;
                        }
                        if (!m3597e() || (m3078a = C11443hr.m3078a(this.f22755a, c11415gq.f22755a)) == 0) {
                            int compareTo6 = Boolean.valueOf(m3595f()).compareTo(Boolean.valueOf(c11415gq.m3595f()));
                            if (compareTo6 != 0) {
                                return compareTo6;
                            }
                            if (!m3595f() || (m3076a = C11443hr.m3076a(this.f22756a, c11415gq.f22756a)) == 0) {
                                int compareTo7 = Boolean.valueOf(m3592h()).compareTo(Boolean.valueOf(c11415gq.m3592h()));
                                if (compareTo7 != 0) {
                                    return compareTo7;
                                }
                                if (!m3592h() || (m3066a = C11443hr.m3066a(this.f22760b, c11415gq.f22760b)) == 0) {
                                    return 0;
                                }
                                return m3066a;
                            }
                            return m3076a;
                        }
                        return m3078a;
                    }
                    return m3079a;
                }
                return m3066a2;
            }
            return m3079a2;
        }
        return m3079a3;
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
                        if (mo3021a.f23307a == 8) {
                            this.f22754a = abstractC11456ia.mo3023a();
                            m3606a(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 2:
                        if (mo3021a.f23307a == 8) {
                            this.f22759b = abstractC11456ia.mo3023a();
                            m3603b(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 3:
                        if (mo3021a.f23307a == 2) {
                            this.f22758a = abstractC11456ia.mo3017a();
                            m3600c(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 4:
                        if (mo3021a.f23307a == 8) {
                            this.f22761c = abstractC11456ia.mo3023a();
                            m3598d(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 5:
                        if (mo3021a.f23307a == 10) {
                            this.f22755a = abstractC11456ia.mo3022a();
                            m3596e(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 6:
                        if (mo3021a.f23307a == 11) {
                            this.f22756a = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 7:
                        if (mo3021a.f23307a == 2) {
                            this.f22760b = abstractC11456ia.mo3017a();
                            m3594f(true);
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
                m3610a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3610a();
        abstractC11456ia.mo3010a(f22747a);
        if (m3609a()) {
            abstractC11456ia.mo3013a(f22746a);
            abstractC11456ia.mo3015a(this.f22754a);
            abstractC11456ia.mo3005b();
        }
        if (m3604b()) {
            abstractC11456ia.mo3013a(f22748b);
            abstractC11456ia.mo3015a(this.f22759b);
            abstractC11456ia.mo3005b();
        }
        if (m3601c()) {
            abstractC11456ia.mo3013a(f22749c);
            abstractC11456ia.mo3006a(this.f22758a);
            abstractC11456ia.mo3005b();
        }
        if (m3599d()) {
            abstractC11456ia.mo3013a(f22750d);
            abstractC11456ia.mo3015a(this.f22761c);
            abstractC11456ia.mo3005b();
        }
        if (m3597e()) {
            abstractC11456ia.mo3013a(f22751e);
            abstractC11456ia.mo3014a(this.f22755a);
            abstractC11456ia.mo3005b();
        }
        if (this.f22756a != null && m3595f()) {
            abstractC11456ia.mo3013a(f22752f);
            abstractC11456ia.mo3009a(this.f22756a);
            abstractC11456ia.mo3005b();
        }
        if (m3592h()) {
            abstractC11456ia.mo3013a(f22753g);
            abstractC11456ia.mo3006a(this.f22760b);
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("OnlineConfigItem(");
        if (m3609a()) {
            sb.append("key:");
            sb.append(this.f22754a);
            z = false;
        } else {
            z = true;
        }
        if (m3604b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("type:");
            sb.append(this.f22759b);
            z = false;
        }
        if (m3601c()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("clear:");
            sb.append(this.f22758a);
            z = false;
        }
        if (m3599d()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("intValue:");
            sb.append(this.f22761c);
            z = false;
        }
        if (m3597e()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("longValue:");
            sb.append(this.f22755a);
            z = false;
        }
        if (m3595f()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("stringValue:");
            String str = this.f22756a;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            z = false;
        }
        if (m3592h()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("boolValue:");
            sb.append(this.f22760b);
        }
        sb.append(")");
        return sb.toString();
    }
}
