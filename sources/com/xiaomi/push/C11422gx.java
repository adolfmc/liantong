package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.gx */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11422gx implements InterfaceC11442hq<C11422gx, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public int f22914a;

    /* renamed from: a */
    private BitSet f22915a = new BitSet(2);

    /* renamed from: b */
    public int f22916b;

    /* renamed from: a */
    private static final C11461if f22912a = new C11461if("XmPushActionCheckClientInfo");

    /* renamed from: a */
    private static final C11452hx f22911a = new C11452hx("", (byte) 8, 1);

    /* renamed from: b */
    private static final C11452hx f22913b = new C11452hx("", (byte) 8, 2);

    /* renamed from: a */
    public void m3448a() {
    }

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public C11422gx m3446a(int i) {
        this.f22914a = i;
        m3443a(true);
        return this;
    }

    /* renamed from: a */
    public boolean m3447a() {
        return this.f22915a.get(0);
    }

    /* renamed from: a */
    public void m3443a(boolean z) {
        this.f22915a.set(0, z);
    }

    /* renamed from: b */
    public C11422gx m3441b(int i) {
        this.f22916b = i;
        m3440b(true);
        return this;
    }

    /* renamed from: b */
    public boolean m3442b() {
        return this.f22915a.get(1);
    }

    /* renamed from: b */
    public void m3440b(boolean z) {
        this.f22915a.set(1, z);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11422gx)) {
            return m3444a((C11422gx) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3444a(C11422gx c11422gx) {
        return c11422gx != null && this.f22914a == c11422gx.f22914a && this.f22916b == c11422gx.f22916b;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11422gx c11422gx) {
        int m3079a;
        int m3079a2;
        if (!getClass().equals(c11422gx.getClass())) {
            return getClass().getName().compareTo(c11422gx.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3447a()).compareTo(Boolean.valueOf(c11422gx.m3447a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3447a() || (m3079a2 = C11443hr.m3079a(this.f22914a, c11422gx.f22914a)) == 0) {
            int compareTo2 = Boolean.valueOf(m3442b()).compareTo(Boolean.valueOf(c11422gx.m3442b()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (!m3442b() || (m3079a = C11443hr.m3079a(this.f22916b, c11422gx.f22916b)) == 0) {
                return 0;
            }
            return m3079a;
        }
        return m3079a2;
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
                            this.f22914a = abstractC11456ia.mo3023a();
                            m3443a(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 2:
                        if (mo3021a.f23307a == 8) {
                            this.f22916b = abstractC11456ia.mo3023a();
                            m3440b(true);
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
                if (!m3447a()) {
                    throw new C11457ib("Required field 'miscConfigVersion' was not found in serialized data! Struct: " + toString());
                } else if (!m3442b()) {
                    throw new C11457ib("Required field 'pluginConfigVersion' was not found in serialized data! Struct: " + toString());
                } else {
                    m3448a();
                    return;
                }
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3448a();
        abstractC11456ia.mo3010a(f22912a);
        abstractC11456ia.mo3013a(f22911a);
        abstractC11456ia.mo3015a(this.f22914a);
        abstractC11456ia.mo3005b();
        abstractC11456ia.mo3013a(f22913b);
        abstractC11456ia.mo3015a(this.f22916b);
        abstractC11456ia.mo3005b();
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        return "XmPushActionCheckClientInfo(miscConfigVersion:" + this.f22914a + ", pluginConfigVersion:" + this.f22916b + ")";
    }
}
