package com.xiaomi.push;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.hb */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11427hb implements InterfaceC11442hq<C11427hb, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public EnumC11404gf f22974a;

    /* renamed from: a */
    public C11417gs f22975a;

    /* renamed from: a */
    public C11419gu f22976a;

    /* renamed from: a */
    public String f22977a;

    /* renamed from: a */
    public ByteBuffer f22978a;

    /* renamed from: b */
    public String f22981b;

    /* renamed from: a */
    private static final C11461if f22966a = new C11461if("XmPushActionContainer");

    /* renamed from: a */
    private static final C11452hx f22965a = new C11452hx("", (byte) 8, 1);

    /* renamed from: b */
    private static final C11452hx f22967b = new C11452hx("", (byte) 2, 2);

    /* renamed from: c */
    private static final C11452hx f22968c = new C11452hx("", (byte) 2, 3);

    /* renamed from: d */
    private static final C11452hx f22969d = new C11452hx("", (byte) 11, 4);

    /* renamed from: e */
    private static final C11452hx f22970e = new C11452hx("", (byte) 11, 5);

    /* renamed from: f */
    private static final C11452hx f22971f = new C11452hx("", (byte) 11, 6);

    /* renamed from: g */
    private static final C11452hx f22972g = new C11452hx("", (byte) 12, 7);

    /* renamed from: h */
    private static final C11452hx f22973h = new C11452hx("", (byte) 12, 8);

    /* renamed from: a */
    private BitSet f22979a = new BitSet(2);

    /* renamed from: a */
    public boolean f22980a = true;

    /* renamed from: b */
    public boolean f22982b = true;

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public EnumC11404gf m3389a() {
        return this.f22974a;
    }

    /* renamed from: a */
    public C11427hb m3383a(EnumC11404gf enumC11404gf) {
        this.f22974a = enumC11404gf;
        return this;
    }

    /* renamed from: a */
    public boolean m3385a() {
        return this.f22974a != null;
    }

    /* renamed from: b */
    public boolean m3373b() {
        return this.f22980a;
    }

    /* renamed from: a */
    public C11427hb m3376a(boolean z) {
        this.f22980a = z;
        m3375a(true);
        return this;
    }

    /* renamed from: c */
    public boolean m3369c() {
        return this.f22979a.get(0);
    }

    /* renamed from: a */
    public void m3375a(boolean z) {
        this.f22979a.set(0, z);
    }

    /* renamed from: b */
    public C11427hb m3371b(boolean z) {
        this.f22982b = z;
        m3370b(true);
        return this;
    }

    /* renamed from: d */
    public boolean m3368d() {
        return this.f22979a.get(1);
    }

    /* renamed from: b */
    public void m3370b(boolean z) {
        this.f22979a.set(1, z);
    }

    /* renamed from: a */
    public byte[] m3384a() {
        m3377a(C11443hr.m3075a(this.f22978a));
        return this.f22978a.array();
    }

    /* renamed from: a */
    public C11427hb m3377a(ByteBuffer byteBuffer) {
        this.f22978a = byteBuffer;
        return this;
    }

    /* renamed from: e */
    public boolean m3367e() {
        return this.f22978a != null;
    }

    /* renamed from: a */
    public String m3387a() {
        return this.f22977a;
    }

    /* renamed from: a */
    public C11427hb m3378a(String str) {
        this.f22977a = str;
        return this;
    }

    /* renamed from: f */
    public boolean m3366f() {
        return this.f22977a != null;
    }

    /* renamed from: b */
    public String m3374b() {
        return this.f22981b;
    }

    /* renamed from: b */
    public C11427hb m3372b(String str) {
        this.f22981b = str;
        return this;
    }

    /* renamed from: g */
    public boolean m3365g() {
        return this.f22981b != null;
    }

    /* renamed from: a */
    public C11427hb m3381a(C11419gu c11419gu) {
        this.f22976a = c11419gu;
        return this;
    }

    /* renamed from: h */
    public boolean m3364h() {
        return this.f22976a != null;
    }

    /* renamed from: a */
    public C11417gs m3388a() {
        return this.f22975a;
    }

    /* renamed from: a */
    public C11427hb m3382a(C11417gs c11417gs) {
        this.f22975a = c11417gs;
        return this;
    }

    /* renamed from: i */
    public boolean m3363i() {
        return this.f22975a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11427hb)) {
            return m3379a((C11427hb) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3379a(C11427hb c11427hb) {
        if (c11427hb == null) {
            return false;
        }
        boolean m3385a = m3385a();
        boolean m3385a2 = c11427hb.m3385a();
        if (((!m3385a && !m3385a2) || (m3385a && m3385a2 && this.f22974a.equals(c11427hb.f22974a))) && this.f22980a == c11427hb.f22980a && this.f22982b == c11427hb.f22982b) {
            boolean m3367e = m3367e();
            boolean m3367e2 = c11427hb.m3367e();
            if ((m3367e || m3367e2) && !(m3367e && m3367e2 && this.f22978a.equals(c11427hb.f22978a))) {
                return false;
            }
            boolean m3366f = m3366f();
            boolean m3366f2 = c11427hb.m3366f();
            if ((m3366f || m3366f2) && !(m3366f && m3366f2 && this.f22977a.equals(c11427hb.f22977a))) {
                return false;
            }
            boolean m3365g = m3365g();
            boolean m3365g2 = c11427hb.m3365g();
            if ((m3365g || m3365g2) && !(m3365g && m3365g2 && this.f22981b.equals(c11427hb.f22981b))) {
                return false;
            }
            boolean m3364h = m3364h();
            boolean m3364h2 = c11427hb.m3364h();
            if ((m3364h || m3364h2) && !(m3364h && m3364h2 && this.f22976a.m3514a(c11427hb.f22976a))) {
                return false;
            }
            boolean m3363i = m3363i();
            boolean m3363i2 = c11427hb.m3363i();
            if (m3363i || m3363i2) {
                return m3363i && m3363i2 && this.f22975a.m3553a(c11427hb.f22975a);
            }
            return true;
        }
        return false;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11427hb c11427hb) {
        int m3077a;
        int m3077a2;
        int m3076a;
        int m3076a2;
        int m3077a3;
        int m3066a;
        int m3066a2;
        int m3077a4;
        if (!getClass().equals(c11427hb.getClass())) {
            return getClass().getName().compareTo(c11427hb.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3385a()).compareTo(Boolean.valueOf(c11427hb.m3385a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3385a() || (m3077a4 = C11443hr.m3077a(this.f22974a, c11427hb.f22974a)) == 0) {
            int compareTo2 = Boolean.valueOf(m3369c()).compareTo(Boolean.valueOf(c11427hb.m3369c()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (!m3369c() || (m3066a2 = C11443hr.m3066a(this.f22980a, c11427hb.f22980a)) == 0) {
                int compareTo3 = Boolean.valueOf(m3368d()).compareTo(Boolean.valueOf(c11427hb.m3368d()));
                if (compareTo3 != 0) {
                    return compareTo3;
                }
                if (!m3368d() || (m3066a = C11443hr.m3066a(this.f22982b, c11427hb.f22982b)) == 0) {
                    int compareTo4 = Boolean.valueOf(m3367e()).compareTo(Boolean.valueOf(c11427hb.m3367e()));
                    if (compareTo4 != 0) {
                        return compareTo4;
                    }
                    if (!m3367e() || (m3077a3 = C11443hr.m3077a(this.f22978a, c11427hb.f22978a)) == 0) {
                        int compareTo5 = Boolean.valueOf(m3366f()).compareTo(Boolean.valueOf(c11427hb.m3366f()));
                        if (compareTo5 != 0) {
                            return compareTo5;
                        }
                        if (!m3366f() || (m3076a2 = C11443hr.m3076a(this.f22977a, c11427hb.f22977a)) == 0) {
                            int compareTo6 = Boolean.valueOf(m3365g()).compareTo(Boolean.valueOf(c11427hb.m3365g()));
                            if (compareTo6 != 0) {
                                return compareTo6;
                            }
                            if (!m3365g() || (m3076a = C11443hr.m3076a(this.f22981b, c11427hb.f22981b)) == 0) {
                                int compareTo7 = Boolean.valueOf(m3364h()).compareTo(Boolean.valueOf(c11427hb.m3364h()));
                                if (compareTo7 != 0) {
                                    return compareTo7;
                                }
                                if (!m3364h() || (m3077a2 = C11443hr.m3077a(this.f22976a, c11427hb.f22976a)) == 0) {
                                    int compareTo8 = Boolean.valueOf(m3363i()).compareTo(Boolean.valueOf(c11427hb.m3363i()));
                                    if (compareTo8 != 0) {
                                        return compareTo8;
                                    }
                                    if (!m3363i() || (m3077a = C11443hr.m3077a(this.f22975a, c11427hb.f22975a)) == 0) {
                                        return 0;
                                    }
                                    return m3077a;
                                }
                                return m3077a2;
                            }
                            return m3076a;
                        }
                        return m3076a2;
                    }
                    return m3077a3;
                }
                return m3066a;
            }
            return m3066a2;
        }
        return m3077a4;
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
                            this.f22974a = EnumC11404gf.m3683a(abstractC11456ia.mo3023a());
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 2:
                        if (mo3021a.f23307a == 2) {
                            this.f22980a = abstractC11456ia.mo3017a();
                            m3375a(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 3:
                        if (mo3021a.f23307a == 2) {
                            this.f22982b = abstractC11456ia.mo3017a();
                            m3370b(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 4:
                        if (mo3021a.f23307a == 11) {
                            this.f22978a = abstractC11456ia.mo2989a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 5:
                        if (mo3021a.f23307a == 11) {
                            this.f22977a = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 6:
                        if (mo3021a.f23307a == 11) {
                            this.f22981b = abstractC11456ia.mo2990a();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 7:
                        if (mo3021a.f23307a == 12) {
                            this.f22976a = new C11419gu();
                            this.f22976a.mo3083a(abstractC11456ia);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 8:
                        if (mo3021a.f23307a == 12) {
                            this.f22975a = new C11417gs();
                            this.f22975a.mo3083a(abstractC11456ia);
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
                if (!m3369c()) {
                    throw new C11457ib("Required field 'encryptAction' was not found in serialized data! Struct: " + toString());
                } else if (!m3368d()) {
                    throw new C11457ib("Required field 'isRequest' was not found in serialized data! Struct: " + toString());
                } else {
                    m3386a();
                    return;
                }
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3386a();
        abstractC11456ia.mo3010a(f22966a);
        if (this.f22974a != null) {
            abstractC11456ia.mo3013a(f22965a);
            abstractC11456ia.mo3015a(this.f22974a.m3684a());
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3013a(f22967b);
        abstractC11456ia.mo3006a(this.f22980a);
        abstractC11456ia.mo3005b();
        abstractC11456ia.mo3013a(f22968c);
        abstractC11456ia.mo3006a(this.f22982b);
        abstractC11456ia.mo3005b();
        if (this.f22978a != null) {
            abstractC11456ia.mo3013a(f22969d);
            abstractC11456ia.mo3008a(this.f22978a);
            abstractC11456ia.mo3005b();
        }
        if (this.f22977a != null && m3366f()) {
            abstractC11456ia.mo3013a(f22970e);
            abstractC11456ia.mo3009a(this.f22977a);
            abstractC11456ia.mo3005b();
        }
        if (this.f22981b != null && m3365g()) {
            abstractC11456ia.mo3013a(f22971f);
            abstractC11456ia.mo3009a(this.f22981b);
            abstractC11456ia.mo3005b();
        }
        if (this.f22976a != null) {
            abstractC11456ia.mo3013a(f22972g);
            this.f22976a.mo3082b(abstractC11456ia);
            abstractC11456ia.mo3005b();
        }
        if (this.f22975a != null && m3363i()) {
            abstractC11456ia.mo3013a(f22973h);
            this.f22975a.mo3082b(abstractC11456ia);
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionContainer(");
        sb.append("action:");
        EnumC11404gf enumC11404gf = this.f22974a;
        if (enumC11404gf == null) {
            sb.append("null");
        } else {
            sb.append(enumC11404gf);
        }
        sb.append(", ");
        sb.append("encryptAction:");
        sb.append(this.f22980a);
        sb.append(", ");
        sb.append("isRequest:");
        sb.append(this.f22982b);
        if (m3366f()) {
            sb.append(", ");
            sb.append("appid:");
            String str = this.f22977a;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
        }
        if (m3365g()) {
            sb.append(", ");
            sb.append("packageName:");
            String str2 = this.f22981b;
            if (str2 == null) {
                sb.append("null");
            } else {
                sb.append(str2);
            }
        }
        sb.append(", ");
        sb.append("target:");
        C11419gu c11419gu = this.f22976a;
        if (c11419gu == null) {
            sb.append("null");
        } else {
            sb.append(c11419gu);
        }
        if (m3363i()) {
            sb.append(", ");
            sb.append("metaInfo:");
            C11417gs c11417gs = this.f22975a;
            if (c11417gs == null) {
                sb.append("null");
            } else {
                sb.append(c11417gs);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    public void m3386a() {
        if (this.f22974a == null) {
            throw new C11457ib("Required field 'action' was not present! Struct: " + toString());
        } else if (this.f22978a == null) {
            throw new C11457ib("Required field 'pushAction' was not present! Struct: " + toString());
        } else if (this.f22976a != null) {
        } else {
            throw new C11457ib("Required field 'target' was not present! Struct: " + toString());
        }
    }
}
