package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.xiaomi.push.gj */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11408gj implements InterfaceC11442hq<C11408gj, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public long f22513a;

    /* renamed from: a */
    public String f22514a;

    /* renamed from: a */
    private BitSet f22515a = new BitSet(3);

    /* renamed from: a */
    public Map<String, String> f22516a;

    /* renamed from: a */
    public boolean f22517a;

    /* renamed from: b */
    public long f22518b;

    /* renamed from: b */
    public String f22519b;

    /* renamed from: c */
    public String f22520c;

    /* renamed from: d */
    public String f22521d;

    /* renamed from: e */
    public String f22522e;

    /* renamed from: f */
    public String f22523f;

    /* renamed from: g */
    public String f22524g;

    /* renamed from: a */
    private static final C11461if f22502a = new C11461if("ClientUploadDataItem");

    /* renamed from: a */
    private static final C11452hx f22501a = new C11452hx("", (byte) 11, 1);

    /* renamed from: b */
    private static final C11452hx f22503b = new C11452hx("", (byte) 11, 2);

    /* renamed from: c */
    private static final C11452hx f22504c = new C11452hx("", (byte) 11, 3);

    /* renamed from: d */
    private static final C11452hx f22505d = new C11452hx("", (byte) 10, 4);

    /* renamed from: e */
    private static final C11452hx f22506e = new C11452hx("", (byte) 10, 5);

    /* renamed from: f */
    private static final C11452hx f22507f = new C11452hx("", (byte) 2, 6);

    /* renamed from: g */
    private static final C11452hx f22508g = new C11452hx("", (byte) 11, 7);

    /* renamed from: h */
    private static final C11452hx f22509h = new C11452hx("", (byte) 11, 8);

    /* renamed from: i */
    private static final C11452hx f22510i = new C11452hx("", (byte) 11, 9);

    /* renamed from: j */
    private static final C11452hx f22511j = new C11452hx("", (byte) 13, 10);

    /* renamed from: k */
    private static final C11452hx f22512k = new C11452hx("", (byte) 11, 11);

    /* renamed from: a */
    public void m3670a() {
    }

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public String m3672a() {
        return this.f22514a;
    }

    /* renamed from: a */
    public C11408gj m3665a(String str) {
        this.f22514a = str;
        return this;
    }

    /* renamed from: a */
    public boolean m3669a() {
        return this.f22514a != null;
    }

    /* renamed from: b */
    public C11408gj m3657b(String str) {
        this.f22519b = str;
        return this;
    }

    /* renamed from: b */
    public boolean m3659b() {
        return this.f22519b != null;
    }

    /* renamed from: b */
    public String m3660b() {
        return this.f22520c;
    }

    /* renamed from: c */
    public C11408gj m3653c(String str) {
        this.f22520c = str;
        return this;
    }

    /* renamed from: c */
    public boolean m3654c() {
        return this.f22520c != null;
    }

    /* renamed from: a */
    public C11408gj m3668a(long j) {
        this.f22513a = j;
        m3661a(true);
        return this;
    }

    /* renamed from: d */
    public boolean m3650d() {
        return this.f22515a.get(0);
    }

    /* renamed from: a */
    public void m3661a(boolean z) {
        this.f22515a.set(0, z);
    }

    /* renamed from: a */
    public long m3673a() {
        return this.f22518b;
    }

    /* renamed from: b */
    public C11408gj m3658b(long j) {
        this.f22518b = j;
        m3656b(true);
        return this;
    }

    /* renamed from: e */
    public boolean m3647e() {
        return this.f22515a.get(1);
    }

    /* renamed from: b */
    public void m3656b(boolean z) {
        this.f22515a.set(1, z);
    }

    /* renamed from: a */
    public C11408gj m3662a(boolean z) {
        this.f22517a = z;
        m3652c(true);
        return this;
    }

    /* renamed from: f */
    public boolean m3645f() {
        return this.f22515a.get(2);
    }

    /* renamed from: c */
    public void m3652c(boolean z) {
        this.f22515a.set(2, z);
    }

    /* renamed from: d */
    public C11408gj m3649d(String str) {
        this.f22521d = str;
        return this;
    }

    /* renamed from: g */
    public boolean m3643g() {
        return this.f22521d != null;
    }

    /* renamed from: c */
    public String m3655c() {
        return this.f22522e;
    }

    /* renamed from: e */
    public C11408gj m3646e(String str) {
        this.f22522e = str;
        return this;
    }

    /* renamed from: h */
    public boolean m3641h() {
        return this.f22522e != null;
    }

    /* renamed from: d */
    public String m3651d() {
        return this.f22523f;
    }

    /* renamed from: f */
    public C11408gj m3644f(String str) {
        this.f22523f = str;
        return this;
    }

    /* renamed from: i */
    public boolean m3640i() {
        return this.f22523f != null;
    }

    /* renamed from: a */
    public void m3664a(String str, String str2) {
        if (this.f22516a == null) {
            this.f22516a = new HashMap();
        }
        this.f22516a.put(str, str2);
    }

    /* renamed from: a */
    public Map<String, String> m3671a() {
        return this.f22516a;
    }

    /* renamed from: a */
    public C11408gj m3663a(Map<String, String> map) {
        this.f22516a = map;
        return this;
    }

    /* renamed from: j */
    public boolean m3639j() {
        return this.f22516a != null;
    }

    /* renamed from: e */
    public String m3648e() {
        return this.f22524g;
    }

    /* renamed from: g */
    public C11408gj m3642g(String str) {
        this.f22524g = str;
        return this;
    }

    /* renamed from: k */
    public boolean m3638k() {
        return this.f22524g != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11408gj)) {
            return m3666a((C11408gj) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3666a(C11408gj c11408gj) {
        if (c11408gj == null) {
            return false;
        }
        boolean m3669a = m3669a();
        boolean m3669a2 = c11408gj.m3669a();
        if ((m3669a || m3669a2) && !(m3669a && m3669a2 && this.f22514a.equals(c11408gj.f22514a))) {
            return false;
        }
        boolean m3659b = m3659b();
        boolean m3659b2 = c11408gj.m3659b();
        if ((m3659b || m3659b2) && !(m3659b && m3659b2 && this.f22519b.equals(c11408gj.f22519b))) {
            return false;
        }
        boolean m3654c = m3654c();
        boolean m3654c2 = c11408gj.m3654c();
        if ((m3654c || m3654c2) && !(m3654c && m3654c2 && this.f22520c.equals(c11408gj.f22520c))) {
            return false;
        }
        boolean m3650d = m3650d();
        boolean m3650d2 = c11408gj.m3650d();
        if ((m3650d || m3650d2) && !(m3650d && m3650d2 && this.f22513a == c11408gj.f22513a)) {
            return false;
        }
        boolean m3647e = m3647e();
        boolean m3647e2 = c11408gj.m3647e();
        if ((m3647e || m3647e2) && !(m3647e && m3647e2 && this.f22518b == c11408gj.f22518b)) {
            return false;
        }
        boolean m3645f = m3645f();
        boolean m3645f2 = c11408gj.m3645f();
        if ((m3645f || m3645f2) && !(m3645f && m3645f2 && this.f22517a == c11408gj.f22517a)) {
            return false;
        }
        boolean m3643g = m3643g();
        boolean m3643g2 = c11408gj.m3643g();
        if ((m3643g || m3643g2) && !(m3643g && m3643g2 && this.f22521d.equals(c11408gj.f22521d))) {
            return false;
        }
        boolean m3641h = m3641h();
        boolean m3641h2 = c11408gj.m3641h();
        if ((m3641h || m3641h2) && !(m3641h && m3641h2 && this.f22522e.equals(c11408gj.f22522e))) {
            return false;
        }
        boolean m3640i = m3640i();
        boolean m3640i2 = c11408gj.m3640i();
        if ((m3640i || m3640i2) && !(m3640i && m3640i2 && this.f22523f.equals(c11408gj.f22523f))) {
            return false;
        }
        boolean m3639j = m3639j();
        boolean m3639j2 = c11408gj.m3639j();
        if ((m3639j || m3639j2) && !(m3639j && m3639j2 && this.f22516a.equals(c11408gj.f22516a))) {
            return false;
        }
        boolean m3638k = m3638k();
        boolean m3638k2 = c11408gj.m3638k();
        if (m3638k || m3638k2) {
            return m3638k && m3638k2 && this.f22524g.equals(c11408gj.f22524g);
        }
        return true;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11408gj c11408gj) {
        int m3076a;
        int m3069a;
        int m3076a2;
        int m3076a3;
        int m3076a4;
        int m3066a;
        int m3078a;
        int m3078a2;
        int m3076a5;
        int m3076a6;
        int m3076a7;
        if (!getClass().equals(c11408gj.getClass())) {
            return getClass().getName().compareTo(c11408gj.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3669a()).compareTo(Boolean.valueOf(c11408gj.m3669a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3669a() || (m3076a7 = C11443hr.m3076a(this.f22514a, c11408gj.f22514a)) == 0) {
            int compareTo2 = Boolean.valueOf(m3659b()).compareTo(Boolean.valueOf(c11408gj.m3659b()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (!m3659b() || (m3076a6 = C11443hr.m3076a(this.f22519b, c11408gj.f22519b)) == 0) {
                int compareTo3 = Boolean.valueOf(m3654c()).compareTo(Boolean.valueOf(c11408gj.m3654c()));
                if (compareTo3 != 0) {
                    return compareTo3;
                }
                if (!m3654c() || (m3076a5 = C11443hr.m3076a(this.f22520c, c11408gj.f22520c)) == 0) {
                    int compareTo4 = Boolean.valueOf(m3650d()).compareTo(Boolean.valueOf(c11408gj.m3650d()));
                    if (compareTo4 != 0) {
                        return compareTo4;
                    }
                    if (!m3650d() || (m3078a2 = C11443hr.m3078a(this.f22513a, c11408gj.f22513a)) == 0) {
                        int compareTo5 = Boolean.valueOf(m3647e()).compareTo(Boolean.valueOf(c11408gj.m3647e()));
                        if (compareTo5 != 0) {
                            return compareTo5;
                        }
                        if (!m3647e() || (m3078a = C11443hr.m3078a(this.f22518b, c11408gj.f22518b)) == 0) {
                            int compareTo6 = Boolean.valueOf(m3645f()).compareTo(Boolean.valueOf(c11408gj.m3645f()));
                            if (compareTo6 != 0) {
                                return compareTo6;
                            }
                            if (!m3645f() || (m3066a = C11443hr.m3066a(this.f22517a, c11408gj.f22517a)) == 0) {
                                int compareTo7 = Boolean.valueOf(m3643g()).compareTo(Boolean.valueOf(c11408gj.m3643g()));
                                if (compareTo7 != 0) {
                                    return compareTo7;
                                }
                                if (!m3643g() || (m3076a4 = C11443hr.m3076a(this.f22521d, c11408gj.f22521d)) == 0) {
                                    int compareTo8 = Boolean.valueOf(m3641h()).compareTo(Boolean.valueOf(c11408gj.m3641h()));
                                    if (compareTo8 != 0) {
                                        return compareTo8;
                                    }
                                    if (!m3641h() || (m3076a3 = C11443hr.m3076a(this.f22522e, c11408gj.f22522e)) == 0) {
                                        int compareTo9 = Boolean.valueOf(m3640i()).compareTo(Boolean.valueOf(c11408gj.m3640i()));
                                        if (compareTo9 != 0) {
                                            return compareTo9;
                                        }
                                        if (!m3640i() || (m3076a2 = C11443hr.m3076a(this.f22523f, c11408gj.f22523f)) == 0) {
                                            int compareTo10 = Boolean.valueOf(m3639j()).compareTo(Boolean.valueOf(c11408gj.m3639j()));
                                            if (compareTo10 != 0) {
                                                return compareTo10;
                                            }
                                            if (!m3639j() || (m3069a = C11443hr.m3069a(this.f22516a, c11408gj.f22516a)) == 0) {
                                                int compareTo11 = Boolean.valueOf(m3638k()).compareTo(Boolean.valueOf(c11408gj.m3638k()));
                                                if (compareTo11 != 0) {
                                                    return compareTo11;
                                                }
                                                if (!m3638k() || (m3076a = C11443hr.m3076a(this.f22524g, c11408gj.f22524g)) == 0) {
                                                    return 0;
                                                }
                                                return m3076a;
                                            }
                                            return m3069a;
                                        }
                                        return m3076a2;
                                    }
                                    return m3076a3;
                                }
                                return m3076a4;
                            }
                            return m3066a;
                        }
                        return m3078a;
                    }
                    return m3078a2;
                }
                return m3076a5;
            }
            return m3076a6;
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
                            this.f22514a = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 2:
                        if (mo3021a.f23307a == 11) {
                            this.f22519b = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 3:
                        if (mo3021a.f23307a == 11) {
                            this.f22520c = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 4:
                        if (mo3021a.f23307a == 10) {
                            this.f22513a = abstractC11456ia.mo3022a();
                            m3661a(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 5:
                        if (mo3021a.f23307a == 10) {
                            this.f22518b = abstractC11456ia.mo3022a();
                            m3656b(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 6:
                        if (mo3021a.f23307a == 2) {
                            this.f22517a = abstractC11456ia.mo3017a();
                            m3652c(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 7:
                        if (mo3021a.f23307a == 11) {
                            this.f22521d = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 8:
                        if (mo3021a.f23307a == 11) {
                            this.f22522e = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 9:
                        if (mo3021a.f23307a == 11) {
                            this.f22523f = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 10:
                        if (mo3021a.f23307a == 13) {
                            C11454hz mo2992a = abstractC11456ia.mo2992a();
                            this.f22516a = new HashMap(mo2992a.f23313a * 2);
                            for (int i = 0; i < mo2992a.f23313a; i++) {
                                this.f22516a.put(abstractC11456ia.mo2990a(), abstractC11456ia.mo2990a());
                            }
                            abstractC11456ia.mo2999h();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 11:
                        if (mo3021a.f23307a == 11) {
                            this.f22524g = abstractC11456ia.mo2990a();
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
                m3670a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3670a();
        abstractC11456ia.mo3010a(f22502a);
        if (this.f22514a != null && m3669a()) {
            abstractC11456ia.mo3013a(f22501a);
            abstractC11456ia.mo3009a(this.f22514a);
            abstractC11456ia.mo3005b();
        }
        if (this.f22519b != null && m3659b()) {
            abstractC11456ia.mo3013a(f22503b);
            abstractC11456ia.mo3009a(this.f22519b);
            abstractC11456ia.mo3005b();
        }
        if (this.f22520c != null && m3654c()) {
            abstractC11456ia.mo3013a(f22504c);
            abstractC11456ia.mo3009a(this.f22520c);
            abstractC11456ia.mo3005b();
        }
        if (m3650d()) {
            abstractC11456ia.mo3013a(f22505d);
            abstractC11456ia.mo3014a(this.f22513a);
            abstractC11456ia.mo3005b();
        }
        if (m3647e()) {
            abstractC11456ia.mo3013a(f22506e);
            abstractC11456ia.mo3014a(this.f22518b);
            abstractC11456ia.mo3005b();
        }
        if (m3645f()) {
            abstractC11456ia.mo3013a(f22507f);
            abstractC11456ia.mo3006a(this.f22517a);
            abstractC11456ia.mo3005b();
        }
        if (this.f22521d != null && m3643g()) {
            abstractC11456ia.mo3013a(f22508g);
            abstractC11456ia.mo3009a(this.f22521d);
            abstractC11456ia.mo3005b();
        }
        if (this.f22522e != null && m3641h()) {
            abstractC11456ia.mo3013a(f22509h);
            abstractC11456ia.mo3009a(this.f22522e);
            abstractC11456ia.mo3005b();
        }
        if (this.f22523f != null && m3640i()) {
            abstractC11456ia.mo3013a(f22510i);
            abstractC11456ia.mo3009a(this.f22523f);
            abstractC11456ia.mo3005b();
        }
        if (this.f22516a != null && m3639j()) {
            abstractC11456ia.mo3013a(f22511j);
            abstractC11456ia.mo3011a(new C11454hz((byte) 11, (byte) 11, this.f22516a.size()));
            for (Map.Entry<String, String> entry : this.f22516a.entrySet()) {
                abstractC11456ia.mo3009a(entry.getKey());
                abstractC11456ia.mo3009a(entry.getValue());
            }
            abstractC11456ia.mo3003d();
            abstractC11456ia.mo3005b();
        }
        if (this.f22524g != null && m3638k()) {
            abstractC11456ia.mo3013a(f22512k);
            abstractC11456ia.mo3009a(this.f22524g);
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("ClientUploadDataItem(");
        if (m3669a()) {
            sb.append("channel:");
            String str = this.f22514a;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (m3659b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("data:");
            String str2 = this.f22519b;
            if (str2 == null) {
                sb.append("null");
            } else {
                sb.append(str2);
            }
            z = false;
        }
        if (m3654c()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("name:");
            String str3 = this.f22520c;
            if (str3 == null) {
                sb.append("null");
            } else {
                sb.append(str3);
            }
            z = false;
        }
        if (m3650d()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("counter:");
            sb.append(this.f22513a);
            z = false;
        }
        if (m3647e()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("timestamp:");
            sb.append(this.f22518b);
            z = false;
        }
        if (m3645f()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("fromSdk:");
            sb.append(this.f22517a);
            z = false;
        }
        if (m3643g()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("category:");
            String str4 = this.f22521d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
            z = false;
        }
        if (m3641h()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("sourcePackage:");
            String str5 = this.f22522e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
            z = false;
        }
        if (m3640i()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("id:");
            String str6 = this.f22523f;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
            z = false;
        }
        if (m3639j()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("extra:");
            Map<String, String> map = this.f22516a;
            if (map == null) {
                sb.append("null");
            } else {
                sb.append(map);
            }
            z = false;
        }
        if (m3638k()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("pkgName:");
            String str7 = this.f22524g;
            if (str7 == null) {
                sb.append("null");
            } else {
                sb.append(str7);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
