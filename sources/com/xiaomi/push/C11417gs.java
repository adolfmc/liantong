package com.xiaomi.push;

import com.xiaomi.push.service.C11541aj;
import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.xiaomi.push.gs */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11417gs implements InterfaceC11442hq<C11417gs, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public int f22814a;

    /* renamed from: a */
    public long f22815a;

    /* renamed from: a */
    public String f22816a;

    /* renamed from: a */
    private BitSet f22817a;

    /* renamed from: a */
    public Map<String, String> f22818a;

    /* renamed from: a */
    public boolean f22819a;

    /* renamed from: b */
    public int f22820b;

    /* renamed from: b */
    public String f22821b;

    /* renamed from: b */
    public Map<String, String> f22822b;

    /* renamed from: c */
    public int f22823c;

    /* renamed from: c */
    public String f22824c;

    /* renamed from: c */
    public Map<String, String> f22825c;

    /* renamed from: d */
    public String f22826d;

    /* renamed from: e */
    public String f22827e;

    /* renamed from: a */
    private static final C11461if f22801a = new C11461if("PushMetaInfo");

    /* renamed from: a */
    private static final C11452hx f22800a = new C11452hx("", (byte) 11, 1);

    /* renamed from: b */
    private static final C11452hx f22802b = new C11452hx("", (byte) 10, 2);

    /* renamed from: c */
    private static final C11452hx f22803c = new C11452hx("", (byte) 11, 3);

    /* renamed from: d */
    private static final C11452hx f22804d = new C11452hx("", (byte) 11, 4);

    /* renamed from: e */
    private static final C11452hx f22805e = new C11452hx("", (byte) 11, 5);

    /* renamed from: f */
    private static final C11452hx f22806f = new C11452hx("", (byte) 8, 6);

    /* renamed from: g */
    private static final C11452hx f22807g = new C11452hx("", (byte) 11, 7);

    /* renamed from: h */
    private static final C11452hx f22808h = new C11452hx("", (byte) 8, 8);

    /* renamed from: i */
    private static final C11452hx f22809i = new C11452hx("", (byte) 8, 9);

    /* renamed from: j */
    private static final C11452hx f22810j = new C11452hx("", (byte) 13, 10);

    /* renamed from: k */
    private static final C11452hx f22811k = new C11452hx("", (byte) 13, 11);

    /* renamed from: l */
    private static final C11452hx f22812l = new C11452hx("", (byte) 2, 12);

    /* renamed from: m */
    private static final C11452hx f22813m = new C11452hx("", (byte) 13, 13);

    public int hashCode() {
        return 0;
    }

    public C11417gs() {
        this.f22817a = new BitSet(5);
        this.f22819a = false;
    }

    public C11417gs(C11417gs c11417gs) {
        this.f22817a = new BitSet(5);
        this.f22817a.clear();
        this.f22817a.or(c11417gs.f22817a);
        if (c11417gs.m3556a()) {
            this.f22816a = c11417gs.f22816a;
        }
        this.f22815a = c11417gs.f22815a;
        if (c11417gs.m3538c()) {
            this.f22821b = c11417gs.f22821b;
        }
        if (c11417gs.m3533d()) {
            this.f22824c = c11417gs.f22824c;
        }
        if (c11417gs.m3530e()) {
            this.f22826d = c11417gs.f22826d;
        }
        this.f22814a = c11417gs.f22814a;
        if (c11417gs.m3527g()) {
            this.f22827e = c11417gs.f22827e;
        }
        this.f22820b = c11417gs.f22820b;
        this.f22823c = c11417gs.f22823c;
        if (c11417gs.m3524j()) {
            HashMap hashMap = new HashMap();
            for (Map.Entry<String, String> entry : c11417gs.f22818a.entrySet()) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
            this.f22818a = hashMap;
        }
        if (c11417gs.m3523k()) {
            HashMap hashMap2 = new HashMap();
            for (Map.Entry<String, String> entry2 : c11417gs.f22822b.entrySet()) {
                hashMap2.put(entry2.getKey(), entry2.getValue());
            }
            this.f22822b = hashMap2;
        }
        this.f22819a = c11417gs.f22819a;
        if (c11417gs.m3520n()) {
            HashMap hashMap3 = new HashMap();
            for (Map.Entry<String, String> entry3 : c11417gs.f22825c.entrySet()) {
                hashMap3.put(entry3.getKey(), entry3.getValue());
            }
            this.f22825c = hashMap3;
        }
    }

    /* renamed from: a */
    public C11417gs m3560a() {
        return new C11417gs(this);
    }

    /* renamed from: a */
    public String m3559a() {
        return this.f22816a;
    }

    /* renamed from: a */
    public C11417gs m3552a(String str) {
        this.f22816a = str;
        return this;
    }

    /* renamed from: a */
    public boolean m3556a() {
        return this.f22816a != null;
    }

    /* renamed from: a */
    public long m3561a() {
        return this.f22815a;
    }

    /* renamed from: b */
    public boolean m3545b() {
        return this.f22817a.get(0);
    }

    /* renamed from: a */
    public void m3549a(boolean z) {
        this.f22817a.set(0, z);
    }

    /* renamed from: b */
    public String m3547b() {
        return this.f22821b;
    }

    /* renamed from: b */
    public C11417gs m3543b(String str) {
        this.f22821b = str;
        return this;
    }

    /* renamed from: c */
    public boolean m3538c() {
        return this.f22821b != null;
    }

    /* renamed from: c */
    public String m3539c() {
        return this.f22824c;
    }

    /* renamed from: c */
    public C11417gs m3536c(String str) {
        this.f22824c = str;
        return this;
    }

    /* renamed from: d */
    public boolean m3533d() {
        return this.f22824c != null;
    }

    /* renamed from: d */
    public String m3534d() {
        return this.f22826d;
    }

    /* renamed from: d */
    public C11417gs m3532d(String str) {
        this.f22826d = str;
        return this;
    }

    /* renamed from: e */
    public boolean m3530e() {
        return this.f22826d != null;
    }

    /* renamed from: a */
    public int m3562a() {
        return this.f22814a;
    }

    /* renamed from: a */
    public C11417gs m3555a(int i) {
        this.f22814a = i;
        m3541b(true);
        return this;
    }

    /* renamed from: f */
    public boolean m3528f() {
        return this.f22817a.get(1);
    }

    /* renamed from: b */
    public void m3541b(boolean z) {
        this.f22817a.set(1, z);
    }

    /* renamed from: g */
    public boolean m3527g() {
        return this.f22827e != null;
    }

    /* renamed from: b */
    public int m3548b() {
        return this.f22820b;
    }

    /* renamed from: b */
    public C11417gs m3544b(int i) {
        this.f22820b = i;
        m3535c(true);
        return this;
    }

    /* renamed from: h */
    public boolean m3526h() {
        return this.f22817a.get(2);
    }

    /* renamed from: c */
    public void m3535c(boolean z) {
        this.f22817a.set(2, z);
    }

    /* renamed from: c */
    public int m3540c() {
        return this.f22823c;
    }

    /* renamed from: c */
    public C11417gs m3537c(int i) {
        this.f22823c = i;
        m3531d(true);
        return this;
    }

    /* renamed from: i */
    public boolean m3525i() {
        return this.f22817a.get(3);
    }

    /* renamed from: d */
    public void m3531d(boolean z) {
        this.f22817a.set(3, z);
    }

    /* renamed from: a */
    public void m3551a(String str, String str2) {
        if (this.f22818a == null) {
            this.f22818a = new HashMap();
        }
        this.f22818a.put(str, str2);
    }

    /* renamed from: a */
    public Map<String, String> m3558a() {
        return this.f22818a;
    }

    /* renamed from: a */
    public C11417gs m3550a(Map<String, String> map) {
        this.f22818a = map;
        return this;
    }

    /* renamed from: j */
    public boolean m3524j() {
        return this.f22818a != null;
    }

    /* renamed from: b */
    public void m3542b(String str, String str2) {
        if (this.f22822b == null) {
            this.f22822b = new HashMap();
        }
        this.f22822b.put(str, str2);
    }

    /* renamed from: b */
    public Map<String, String> m3546b() {
        return this.f22822b;
    }

    /* renamed from: k */
    public boolean m3523k() {
        return this.f22822b != null;
    }

    /* renamed from: l */
    public boolean m3522l() {
        return this.f22819a;
    }

    /* renamed from: m */
    public boolean m3521m() {
        return this.f22817a.get(4);
    }

    /* renamed from: e */
    public void m3529e(boolean z) {
        this.f22817a.set(4, z);
    }

    /* renamed from: n */
    public boolean m3520n() {
        return this.f22825c != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11417gs)) {
            return m3553a((C11417gs) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3553a(C11417gs c11417gs) {
        if (c11417gs == null) {
            return false;
        }
        boolean m3556a = m3556a();
        boolean m3556a2 = c11417gs.m3556a();
        if (((m3556a || m3556a2) && !(m3556a && m3556a2 && this.f22816a.equals(c11417gs.f22816a))) || this.f22815a != c11417gs.f22815a) {
            return false;
        }
        boolean m3538c = m3538c();
        boolean m3538c2 = c11417gs.m3538c();
        if ((m3538c || m3538c2) && !(m3538c && m3538c2 && this.f22821b.equals(c11417gs.f22821b))) {
            return false;
        }
        boolean m3533d = m3533d();
        boolean m3533d2 = c11417gs.m3533d();
        if ((m3533d || m3533d2) && !(m3533d && m3533d2 && this.f22824c.equals(c11417gs.f22824c))) {
            return false;
        }
        boolean m3530e = m3530e();
        boolean m3530e2 = c11417gs.m3530e();
        if ((m3530e || m3530e2) && !(m3530e && m3530e2 && this.f22826d.equals(c11417gs.f22826d))) {
            return false;
        }
        boolean m3528f = m3528f();
        boolean m3528f2 = c11417gs.m3528f();
        if ((m3528f || m3528f2) && !(m3528f && m3528f2 && this.f22814a == c11417gs.f22814a)) {
            return false;
        }
        boolean m3527g = m3527g();
        boolean m3527g2 = c11417gs.m3527g();
        if ((m3527g || m3527g2) && !(m3527g && m3527g2 && this.f22827e.equals(c11417gs.f22827e))) {
            return false;
        }
        boolean m3526h = m3526h();
        boolean m3526h2 = c11417gs.m3526h();
        if ((m3526h || m3526h2) && !(m3526h && m3526h2 && this.f22820b == c11417gs.f22820b)) {
            return false;
        }
        boolean m3525i = m3525i();
        boolean m3525i2 = c11417gs.m3525i();
        if ((m3525i || m3525i2) && !(m3525i && m3525i2 && this.f22823c == c11417gs.f22823c)) {
            return false;
        }
        boolean m3524j = m3524j();
        boolean m3524j2 = c11417gs.m3524j();
        if ((m3524j || m3524j2) && !(m3524j && m3524j2 && this.f22818a.equals(c11417gs.f22818a))) {
            return false;
        }
        boolean m3523k = m3523k();
        boolean m3523k2 = c11417gs.m3523k();
        if ((m3523k || m3523k2) && !(m3523k && m3523k2 && this.f22822b.equals(c11417gs.f22822b))) {
            return false;
        }
        boolean m3521m = m3521m();
        boolean m3521m2 = c11417gs.m3521m();
        if ((m3521m || m3521m2) && !(m3521m && m3521m2 && this.f22819a == c11417gs.f22819a)) {
            return false;
        }
        boolean m3520n = m3520n();
        boolean m3520n2 = c11417gs.m3520n();
        if (m3520n || m3520n2) {
            return m3520n && m3520n2 && this.f22825c.equals(c11417gs.f22825c);
        }
        return true;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11417gs c11417gs) {
        int m3069a;
        int m3066a;
        int m3069a2;
        int m3069a3;
        int m3079a;
        int m3079a2;
        int m3076a;
        int m3079a3;
        int m3076a2;
        int m3076a3;
        int m3076a4;
        int m3078a;
        int m3076a5;
        if (!getClass().equals(c11417gs.getClass())) {
            return getClass().getName().compareTo(c11417gs.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3556a()).compareTo(Boolean.valueOf(c11417gs.m3556a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3556a() || (m3076a5 = C11443hr.m3076a(this.f22816a, c11417gs.f22816a)) == 0) {
            int compareTo2 = Boolean.valueOf(m3545b()).compareTo(Boolean.valueOf(c11417gs.m3545b()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (!m3545b() || (m3078a = C11443hr.m3078a(this.f22815a, c11417gs.f22815a)) == 0) {
                int compareTo3 = Boolean.valueOf(m3538c()).compareTo(Boolean.valueOf(c11417gs.m3538c()));
                if (compareTo3 != 0) {
                    return compareTo3;
                }
                if (!m3538c() || (m3076a4 = C11443hr.m3076a(this.f22821b, c11417gs.f22821b)) == 0) {
                    int compareTo4 = Boolean.valueOf(m3533d()).compareTo(Boolean.valueOf(c11417gs.m3533d()));
                    if (compareTo4 != 0) {
                        return compareTo4;
                    }
                    if (!m3533d() || (m3076a3 = C11443hr.m3076a(this.f22824c, c11417gs.f22824c)) == 0) {
                        int compareTo5 = Boolean.valueOf(m3530e()).compareTo(Boolean.valueOf(c11417gs.m3530e()));
                        if (compareTo5 != 0) {
                            return compareTo5;
                        }
                        if (!m3530e() || (m3076a2 = C11443hr.m3076a(this.f22826d, c11417gs.f22826d)) == 0) {
                            int compareTo6 = Boolean.valueOf(m3528f()).compareTo(Boolean.valueOf(c11417gs.m3528f()));
                            if (compareTo6 != 0) {
                                return compareTo6;
                            }
                            if (!m3528f() || (m3079a3 = C11443hr.m3079a(this.f22814a, c11417gs.f22814a)) == 0) {
                                int compareTo7 = Boolean.valueOf(m3527g()).compareTo(Boolean.valueOf(c11417gs.m3527g()));
                                if (compareTo7 != 0) {
                                    return compareTo7;
                                }
                                if (!m3527g() || (m3076a = C11443hr.m3076a(this.f22827e, c11417gs.f22827e)) == 0) {
                                    int compareTo8 = Boolean.valueOf(m3526h()).compareTo(Boolean.valueOf(c11417gs.m3526h()));
                                    if (compareTo8 != 0) {
                                        return compareTo8;
                                    }
                                    if (!m3526h() || (m3079a2 = C11443hr.m3079a(this.f22820b, c11417gs.f22820b)) == 0) {
                                        int compareTo9 = Boolean.valueOf(m3525i()).compareTo(Boolean.valueOf(c11417gs.m3525i()));
                                        if (compareTo9 != 0) {
                                            return compareTo9;
                                        }
                                        if (!m3525i() || (m3079a = C11443hr.m3079a(this.f22823c, c11417gs.f22823c)) == 0) {
                                            int compareTo10 = Boolean.valueOf(m3524j()).compareTo(Boolean.valueOf(c11417gs.m3524j()));
                                            if (compareTo10 != 0) {
                                                return compareTo10;
                                            }
                                            if (!m3524j() || (m3069a3 = C11443hr.m3069a(this.f22818a, c11417gs.f22818a)) == 0) {
                                                int compareTo11 = Boolean.valueOf(m3523k()).compareTo(Boolean.valueOf(c11417gs.m3523k()));
                                                if (compareTo11 != 0) {
                                                    return compareTo11;
                                                }
                                                if (!m3523k() || (m3069a2 = C11443hr.m3069a(this.f22822b, c11417gs.f22822b)) == 0) {
                                                    int compareTo12 = Boolean.valueOf(m3521m()).compareTo(Boolean.valueOf(c11417gs.m3521m()));
                                                    if (compareTo12 != 0) {
                                                        return compareTo12;
                                                    }
                                                    if (!m3521m() || (m3066a = C11443hr.m3066a(this.f22819a, c11417gs.f22819a)) == 0) {
                                                        int compareTo13 = Boolean.valueOf(m3520n()).compareTo(Boolean.valueOf(c11417gs.m3520n()));
                                                        if (compareTo13 != 0) {
                                                            return compareTo13;
                                                        }
                                                        if (!m3520n() || (m3069a = C11443hr.m3069a(this.f22825c, c11417gs.f22825c)) == 0) {
                                                            return 0;
                                                        }
                                                        return m3069a;
                                                    }
                                                    return m3066a;
                                                }
                                                return m3069a2;
                                            }
                                            return m3069a3;
                                        }
                                        return m3079a;
                                    }
                                    return m3079a2;
                                }
                                return m3076a;
                            }
                            return m3079a3;
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

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: a */
    public void mo3083a(AbstractC11456ia abstractC11456ia) {
        abstractC11456ia.mo3020a();
        while (true) {
            C11452hx mo3021a = abstractC11456ia.mo3021a();
            if (mo3021a.f23307a != 0) {
                int i = 0;
                switch (mo3021a.f23309a) {
                    case 1:
                        if (mo3021a.f23307a == 11) {
                            this.f22816a = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 2:
                        if (mo3021a.f23307a == 10) {
                            this.f22815a = abstractC11456ia.mo3022a();
                            m3549a(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 3:
                        if (mo3021a.f23307a == 11) {
                            this.f22821b = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 4:
                        if (mo3021a.f23307a == 11) {
                            this.f22824c = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 5:
                        if (mo3021a.f23307a == 11) {
                            this.f22826d = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 6:
                        if (mo3021a.f23307a == 8) {
                            this.f22814a = abstractC11456ia.mo3023a();
                            m3541b(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 7:
                        if (mo3021a.f23307a == 11) {
                            this.f22827e = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 8:
                        if (mo3021a.f23307a == 8) {
                            this.f22820b = abstractC11456ia.mo3023a();
                            m3535c(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 9:
                        if (mo3021a.f23307a == 8) {
                            this.f22823c = abstractC11456ia.mo3023a();
                            m3531d(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 10:
                        if (mo3021a.f23307a == 13) {
                            C11454hz mo2992a = abstractC11456ia.mo2992a();
                            this.f22818a = new HashMap(mo2992a.f23313a * 2);
                            while (i < mo2992a.f23313a) {
                                this.f22818a.put(abstractC11456ia.mo2990a(), abstractC11456ia.mo2990a());
                                i++;
                            }
                            abstractC11456ia.mo2999h();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 11:
                        if (mo3021a.f23307a == 13) {
                            C11454hz mo2992a2 = abstractC11456ia.mo2992a();
                            this.f22822b = new HashMap(mo2992a2.f23313a * 2);
                            while (i < mo2992a2.f23313a) {
                                this.f22822b.put(abstractC11456ia.mo2990a(), abstractC11456ia.mo2990a());
                                i++;
                            }
                            abstractC11456ia.mo2999h();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 12:
                        if (mo3021a.f23307a == 2) {
                            this.f22819a = abstractC11456ia.mo3017a();
                            m3529e(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 13:
                        if (mo3021a.f23307a == 13) {
                            C11454hz mo2992a3 = abstractC11456ia.mo2992a();
                            this.f22825c = new HashMap(mo2992a3.f23313a * 2);
                            while (i < mo2992a3.f23313a) {
                                this.f22825c.put(abstractC11456ia.mo2990a(), abstractC11456ia.mo2990a());
                                i++;
                            }
                            abstractC11456ia.mo2999h();
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
                if (!m3545b()) {
                    throw new C11457ib("Required field 'messageTs' was not found in serialized data! Struct: " + toString());
                }
                m3557a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3557a();
        abstractC11456ia.mo3010a(f22801a);
        if (this.f22816a != null) {
            abstractC11456ia.mo3013a(f22800a);
            abstractC11456ia.mo3009a(this.f22816a);
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3013a(f22802b);
        abstractC11456ia.mo3014a(this.f22815a);
        abstractC11456ia.mo3005b();
        if (this.f22821b != null && m3538c()) {
            abstractC11456ia.mo3013a(f22803c);
            abstractC11456ia.mo3009a(this.f22821b);
            abstractC11456ia.mo3005b();
        }
        if (this.f22824c != null && m3533d()) {
            abstractC11456ia.mo3013a(f22804d);
            abstractC11456ia.mo3009a(this.f22824c);
            abstractC11456ia.mo3005b();
        }
        if (this.f22826d != null && m3530e()) {
            abstractC11456ia.mo3013a(f22805e);
            abstractC11456ia.mo3009a(this.f22826d);
            abstractC11456ia.mo3005b();
        }
        if (m3528f()) {
            abstractC11456ia.mo3013a(f22806f);
            abstractC11456ia.mo3015a(this.f22814a);
            abstractC11456ia.mo3005b();
        }
        if (this.f22827e != null && m3527g()) {
            abstractC11456ia.mo3013a(f22807g);
            abstractC11456ia.mo3009a(this.f22827e);
            abstractC11456ia.mo3005b();
        }
        if (m3526h()) {
            abstractC11456ia.mo3013a(f22808h);
            abstractC11456ia.mo3015a(this.f22820b);
            abstractC11456ia.mo3005b();
        }
        if (m3525i()) {
            abstractC11456ia.mo3013a(f22809i);
            abstractC11456ia.mo3015a(this.f22823c);
            abstractC11456ia.mo3005b();
        }
        if (this.f22818a != null && m3524j()) {
            abstractC11456ia.mo3013a(f22810j);
            abstractC11456ia.mo3011a(new C11454hz((byte) 11, (byte) 11, this.f22818a.size()));
            for (Map.Entry<String, String> entry : this.f22818a.entrySet()) {
                abstractC11456ia.mo3009a(entry.getKey());
                abstractC11456ia.mo3009a(entry.getValue());
            }
            abstractC11456ia.mo3003d();
            abstractC11456ia.mo3005b();
        }
        if (this.f22822b != null && m3523k()) {
            abstractC11456ia.mo3013a(f22811k);
            abstractC11456ia.mo3011a(new C11454hz((byte) 11, (byte) 11, this.f22822b.size()));
            for (Map.Entry<String, String> entry2 : this.f22822b.entrySet()) {
                abstractC11456ia.mo3009a(entry2.getKey());
                abstractC11456ia.mo3009a(entry2.getValue());
            }
            abstractC11456ia.mo3003d();
            abstractC11456ia.mo3005b();
        }
        if (m3521m()) {
            abstractC11456ia.mo3013a(f22812l);
            abstractC11456ia.mo3006a(this.f22819a);
            abstractC11456ia.mo3005b();
        }
        if (this.f22825c != null && m3520n()) {
            abstractC11456ia.mo3013a(f22813m);
            abstractC11456ia.mo3011a(new C11454hz((byte) 11, (byte) 11, this.f22825c.size()));
            for (Map.Entry<String, String> entry3 : this.f22825c.entrySet()) {
                abstractC11456ia.mo3009a(entry3.getKey());
                abstractC11456ia.mo3009a(entry3.getValue());
            }
            abstractC11456ia.mo3003d();
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("PushMetaInfo(");
        sb.append("id:");
        String str = this.f22816a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(C11541aj.m2702a(str));
        }
        sb.append(", ");
        sb.append("messageTs:");
        sb.append(this.f22815a);
        if (m3538c()) {
            sb.append(", ");
            sb.append("topic:");
            String str2 = this.f22821b;
            if (str2 == null) {
                sb.append("null");
            } else {
                sb.append(str2);
            }
        }
        if (m3533d()) {
            sb.append(", ");
            sb.append("title:");
            String str3 = this.f22824c;
            if (str3 == null) {
                sb.append("null");
            } else {
                sb.append(str3);
            }
        }
        if (m3530e()) {
            sb.append(", ");
            sb.append("description:");
            String str4 = this.f22826d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m3528f()) {
            sb.append(", ");
            sb.append("notifyType:");
            sb.append(this.f22814a);
        }
        if (m3527g()) {
            sb.append(", ");
            sb.append("url:");
            String str5 = this.f22827e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m3526h()) {
            sb.append(", ");
            sb.append("passThrough:");
            sb.append(this.f22820b);
        }
        if (m3525i()) {
            sb.append(", ");
            sb.append("notifyId:");
            sb.append(this.f22823c);
        }
        if (m3524j()) {
            sb.append(", ");
            sb.append("extra:");
            Map<String, String> map = this.f22818a;
            if (map == null) {
                sb.append("null");
            } else {
                sb.append(map);
            }
        }
        if (m3523k()) {
            sb.append(", ");
            sb.append("internal:");
            Map<String, String> map2 = this.f22822b;
            if (map2 == null) {
                sb.append("null");
            } else {
                sb.append(map2);
            }
        }
        if (m3521m()) {
            sb.append(", ");
            sb.append("ignoreRegInfo:");
            sb.append(this.f22819a);
        }
        if (m3520n()) {
            sb.append(", ");
            sb.append("apsProperFields:");
            Map<String, String> map3 = this.f22825c;
            if (map3 == null) {
                sb.append("null");
            } else {
                sb.append(map3);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    public void m3557a() {
        if (this.f22816a != null) {
            return;
        }
        throw new C11457ib("Required field 'id' was not present! Struct: " + toString());
    }
}
