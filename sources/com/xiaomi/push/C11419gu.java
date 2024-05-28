package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.gu */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11419gu implements InterfaceC11442hq<C11419gu, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public String f22841a;

    /* renamed from: d */
    public String f22846d;

    /* renamed from: a */
    private static final C11461if f22834a = new C11461if("Target");

    /* renamed from: a */
    private static final C11452hx f22833a = new C11452hx("", (byte) 10, 1);

    /* renamed from: b */
    private static final C11452hx f22835b = new C11452hx("", (byte) 11, 2);

    /* renamed from: c */
    private static final C11452hx f22836c = new C11452hx("", (byte) 11, 3);

    /* renamed from: d */
    private static final C11452hx f22837d = new C11452hx("", (byte) 11, 4);

    /* renamed from: e */
    private static final C11452hx f22838e = new C11452hx("", (byte) 2, 5);

    /* renamed from: f */
    private static final C11452hx f22839f = new C11452hx("", (byte) 11, 7);

    /* renamed from: a */
    private BitSet f22842a = new BitSet(2);

    /* renamed from: a */
    public long f22840a = 5;

    /* renamed from: b */
    public String f22844b = "xiaomi.com";

    /* renamed from: c */
    public String f22845c = "";

    /* renamed from: a */
    public boolean f22843a = false;

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public boolean m3516a() {
        return this.f22842a.get(0);
    }

    /* renamed from: a */
    public void m3513a(boolean z) {
        this.f22842a.set(0, z);
    }

    /* renamed from: b */
    public boolean m3512b() {
        return this.f22841a != null;
    }

    /* renamed from: c */
    public boolean m3510c() {
        return this.f22844b != null;
    }

    /* renamed from: d */
    public boolean m3509d() {
        return this.f22845c != null;
    }

    /* renamed from: e */
    public boolean m3508e() {
        return this.f22842a.get(1);
    }

    /* renamed from: b */
    public void m3511b(boolean z) {
        this.f22842a.set(1, z);
    }

    /* renamed from: f */
    public boolean m3507f() {
        return this.f22846d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11419gu)) {
            return m3514a((C11419gu) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3514a(C11419gu c11419gu) {
        if (c11419gu != null && this.f22840a == c11419gu.f22840a) {
            boolean m3512b = m3512b();
            boolean m3512b2 = c11419gu.m3512b();
            if ((m3512b || m3512b2) && !(m3512b && m3512b2 && this.f22841a.equals(c11419gu.f22841a))) {
                return false;
            }
            boolean m3510c = m3510c();
            boolean m3510c2 = c11419gu.m3510c();
            if ((m3510c || m3510c2) && !(m3510c && m3510c2 && this.f22844b.equals(c11419gu.f22844b))) {
                return false;
            }
            boolean m3509d = m3509d();
            boolean m3509d2 = c11419gu.m3509d();
            if ((m3509d || m3509d2) && !(m3509d && m3509d2 && this.f22845c.equals(c11419gu.f22845c))) {
                return false;
            }
            boolean m3508e = m3508e();
            boolean m3508e2 = c11419gu.m3508e();
            if ((m3508e || m3508e2) && !(m3508e && m3508e2 && this.f22843a == c11419gu.f22843a)) {
                return false;
            }
            boolean m3507f = m3507f();
            boolean m3507f2 = c11419gu.m3507f();
            if (m3507f || m3507f2) {
                return m3507f && m3507f2 && this.f22846d.equals(c11419gu.f22846d);
            }
            return true;
        }
        return false;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11419gu c11419gu) {
        int m3076a;
        int m3066a;
        int m3076a2;
        int m3076a3;
        int m3076a4;
        int m3078a;
        if (!getClass().equals(c11419gu.getClass())) {
            return getClass().getName().compareTo(c11419gu.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3516a()).compareTo(Boolean.valueOf(c11419gu.m3516a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3516a() || (m3078a = C11443hr.m3078a(this.f22840a, c11419gu.f22840a)) == 0) {
            int compareTo2 = Boolean.valueOf(m3512b()).compareTo(Boolean.valueOf(c11419gu.m3512b()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (!m3512b() || (m3076a4 = C11443hr.m3076a(this.f22841a, c11419gu.f22841a)) == 0) {
                int compareTo3 = Boolean.valueOf(m3510c()).compareTo(Boolean.valueOf(c11419gu.m3510c()));
                if (compareTo3 != 0) {
                    return compareTo3;
                }
                if (!m3510c() || (m3076a3 = C11443hr.m3076a(this.f22844b, c11419gu.f22844b)) == 0) {
                    int compareTo4 = Boolean.valueOf(m3509d()).compareTo(Boolean.valueOf(c11419gu.m3509d()));
                    if (compareTo4 != 0) {
                        return compareTo4;
                    }
                    if (!m3509d() || (m3076a2 = C11443hr.m3076a(this.f22845c, c11419gu.f22845c)) == 0) {
                        int compareTo5 = Boolean.valueOf(m3508e()).compareTo(Boolean.valueOf(c11419gu.m3508e()));
                        if (compareTo5 != 0) {
                            return compareTo5;
                        }
                        if (!m3508e() || (m3066a = C11443hr.m3066a(this.f22843a, c11419gu.f22843a)) == 0) {
                            int compareTo6 = Boolean.valueOf(m3507f()).compareTo(Boolean.valueOf(c11419gu.m3507f()));
                            if (compareTo6 != 0) {
                                return compareTo6;
                            }
                            if (!m3507f() || (m3076a = C11443hr.m3076a(this.f22846d, c11419gu.f22846d)) == 0) {
                                return 0;
                            }
                            return m3076a;
                        }
                        return m3066a;
                    }
                    return m3076a2;
                }
                return m3076a3;
            }
            return m3076a4;
        }
        return m3078a;
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: a */
    public void mo3083a(AbstractC11456ia abstractC11456ia) {
        abstractC11456ia.mo3020a();
        while (true) {
            C11452hx mo3021a = abstractC11456ia.mo3021a();
            if (mo3021a.f23307a != 0) {
                short s = mo3021a.f23309a;
                if (s != 7) {
                    switch (s) {
                        case 1:
                            if (mo3021a.f23307a == 10) {
                                this.f22840a = abstractC11456ia.mo3022a();
                                m3513a(true);
                                continue;
                            } else {
                                C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                                break;
                            }
                        case 2:
                            if (mo3021a.f23307a == 11) {
                                this.f22841a = abstractC11456ia.mo2990a();
                                continue;
                            } else {
                                C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                                break;
                            }
                        case 3:
                            if (mo3021a.f23307a == 11) {
                                this.f22844b = abstractC11456ia.mo2990a();
                                continue;
                            } else {
                                C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                                break;
                            }
                        case 4:
                            if (mo3021a.f23307a == 11) {
                                this.f22845c = abstractC11456ia.mo2990a();
                                continue;
                            } else {
                                C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                                break;
                            }
                        case 5:
                            if (mo3021a.f23307a == 2) {
                                this.f22843a = abstractC11456ia.mo3017a();
                                m3511b(true);
                                continue;
                            } else {
                                C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                                break;
                            }
                        default:
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            continue;
                    }
                } else if (mo3021a.f23307a == 11) {
                    this.f22846d = abstractC11456ia.mo2990a();
                } else {
                    C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                }
                abstractC11456ia.mo3000g();
            } else {
                abstractC11456ia.mo3001f();
                if (!m3516a()) {
                    throw new C11457ib("Required field 'channelId' was not found in serialized data! Struct: " + toString());
                }
                m3517a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3517a();
        abstractC11456ia.mo3010a(f22834a);
        abstractC11456ia.mo3013a(f22833a);
        abstractC11456ia.mo3014a(this.f22840a);
        abstractC11456ia.mo3005b();
        if (this.f22841a != null) {
            abstractC11456ia.mo3013a(f22835b);
            abstractC11456ia.mo3009a(this.f22841a);
            abstractC11456ia.mo3005b();
        }
        if (this.f22844b != null && m3510c()) {
            abstractC11456ia.mo3013a(f22836c);
            abstractC11456ia.mo3009a(this.f22844b);
            abstractC11456ia.mo3005b();
        }
        if (this.f22845c != null && m3509d()) {
            abstractC11456ia.mo3013a(f22837d);
            abstractC11456ia.mo3009a(this.f22845c);
            abstractC11456ia.mo3005b();
        }
        if (m3508e()) {
            abstractC11456ia.mo3013a(f22838e);
            abstractC11456ia.mo3006a(this.f22843a);
            abstractC11456ia.mo3005b();
        }
        if (this.f22846d != null && m3507f()) {
            abstractC11456ia.mo3013a(f22839f);
            abstractC11456ia.mo3009a(this.f22846d);
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Target(");
        sb.append("channelId:");
        sb.append(this.f22840a);
        sb.append(", ");
        sb.append("userId:");
        String str = this.f22841a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        if (m3510c()) {
            sb.append(", ");
            sb.append("server:");
            String str2 = this.f22844b;
            if (str2 == null) {
                sb.append("null");
            } else {
                sb.append(str2);
            }
        }
        if (m3509d()) {
            sb.append(", ");
            sb.append("resource:");
            String str3 = this.f22845c;
            if (str3 == null) {
                sb.append("null");
            } else {
                sb.append(str3);
            }
        }
        if (m3508e()) {
            sb.append(", ");
            sb.append("isPreview:");
            sb.append(this.f22843a);
        }
        if (m3507f()) {
            sb.append(", ");
            sb.append("token:");
            String str4 = this.f22846d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    public void m3517a() {
        if (this.f22841a != null) {
            return;
        }
        throw new C11457ib("Required field 'userId' was not present! Struct: " + toString());
    }
}
