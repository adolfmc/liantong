package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/* renamed from: com.xiaomi.push.go */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11413go implements InterfaceC11442hq<C11413go, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public int f22679a;

    /* renamed from: a */
    public EnumC11410gl f22680a;

    /* renamed from: a */
    private BitSet f22681a = new BitSet(1);

    /* renamed from: a */
    public List<C11415gq> f22682a;

    /* renamed from: a */
    private static final C11461if f22676a = new C11461if("NormalConfig");

    /* renamed from: a */
    private static final C11452hx f22675a = new C11452hx("", (byte) 8, 1);

    /* renamed from: b */
    private static final C11452hx f22677b = new C11452hx("", (byte) 15, 2);

    /* renamed from: c */
    private static final C11452hx f22678c = new C11452hx("", (byte) 8, 3);

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public int m3622a() {
        return this.f22679a;
    }

    /* renamed from: a */
    public boolean m3619a() {
        return this.f22681a.get(0);
    }

    /* renamed from: a */
    public void m3616a(boolean z) {
        this.f22681a.set(0, z);
    }

    /* renamed from: b */
    public boolean m3615b() {
        return this.f22682a != null;
    }

    /* renamed from: a */
    public EnumC11410gl m3621a() {
        return this.f22680a;
    }

    /* renamed from: c */
    public boolean m3614c() {
        return this.f22680a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11413go)) {
            return m3617a((C11413go) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3617a(C11413go c11413go) {
        if (c11413go != null && this.f22679a == c11413go.f22679a) {
            boolean m3615b = m3615b();
            boolean m3615b2 = c11413go.m3615b();
            if ((m3615b || m3615b2) && !(m3615b && m3615b2 && this.f22682a.equals(c11413go.f22682a))) {
                return false;
            }
            boolean m3614c = m3614c();
            boolean m3614c2 = c11413go.m3614c();
            if (m3614c || m3614c2) {
                return m3614c && m3614c2 && this.f22680a.equals(c11413go.f22680a);
            }
            return true;
        }
        return false;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11413go c11413go) {
        int m3077a;
        int m3070a;
        int m3079a;
        if (!getClass().equals(c11413go.getClass())) {
            return getClass().getName().compareTo(c11413go.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3619a()).compareTo(Boolean.valueOf(c11413go.m3619a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3619a() || (m3079a = C11443hr.m3079a(this.f22679a, c11413go.f22679a)) == 0) {
            int compareTo2 = Boolean.valueOf(m3615b()).compareTo(Boolean.valueOf(c11413go.m3615b()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (!m3615b() || (m3070a = C11443hr.m3070a(this.f22682a, c11413go.f22682a)) == 0) {
                int compareTo3 = Boolean.valueOf(m3614c()).compareTo(Boolean.valueOf(c11413go.m3614c()));
                if (compareTo3 != 0) {
                    return compareTo3;
                }
                if (!m3614c() || (m3077a = C11443hr.m3077a(this.f22680a, c11413go.f22680a)) == 0) {
                    return 0;
                }
                return m3077a;
            }
            return m3070a;
        }
        return m3079a;
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
                            this.f22679a = abstractC11456ia.mo3023a();
                            m3616a(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 2:
                        if (mo3021a.f23307a == 15) {
                            C11453hy mo2993a = abstractC11456ia.mo2993a();
                            this.f22682a = new ArrayList(mo2993a.f23311a);
                            for (int i = 0; i < mo2993a.f23311a; i++) {
                                C11415gq c11415gq = new C11415gq();
                                c11415gq.mo3083a(abstractC11456ia);
                                this.f22682a.add(c11415gq);
                            }
                            abstractC11456ia.mo2998i();
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 3:
                        if (mo3021a.f23307a == 8) {
                            this.f22680a = EnumC11410gl.m3635a(abstractC11456ia.mo3023a());
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
                if (!m3619a()) {
                    throw new C11457ib("Required field 'version' was not found in serialized data! Struct: " + toString());
                }
                m3620a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3620a();
        abstractC11456ia.mo3010a(f22676a);
        abstractC11456ia.mo3013a(f22675a);
        abstractC11456ia.mo3015a(this.f22679a);
        abstractC11456ia.mo3005b();
        if (this.f22682a != null) {
            abstractC11456ia.mo3013a(f22677b);
            abstractC11456ia.mo3012a(new C11453hy((byte) 12, this.f22682a.size()));
            for (C11415gq c11415gq : this.f22682a) {
                c11415gq.mo3082b(abstractC11456ia);
            }
            abstractC11456ia.mo3002e();
            abstractC11456ia.mo3005b();
        }
        if (this.f22680a != null && m3614c()) {
            abstractC11456ia.mo3013a(f22678c);
            abstractC11456ia.mo3015a(this.f22680a.m3636a());
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("NormalConfig(");
        sb.append("version:");
        sb.append(this.f22679a);
        sb.append(", ");
        sb.append("configItems:");
        List<C11415gq> list = this.f22682a;
        if (list == null) {
            sb.append("null");
        } else {
            sb.append(list);
        }
        if (m3614c()) {
            sb.append(", ");
            sb.append("type:");
            EnumC11410gl enumC11410gl = this.f22680a;
            if (enumC11410gl == null) {
                sb.append("null");
            } else {
                sb.append(enumC11410gl);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    public void m3620a() {
        if (this.f22682a != null) {
            return;
        }
        throw new C11457ib("Required field 'configItems' was not present! Struct: " + toString());
    }
}
