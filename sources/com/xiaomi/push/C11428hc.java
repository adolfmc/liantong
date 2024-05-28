package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xiaomi.push.hc */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11428hc implements InterfaceC11442hq<C11428hc, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public List<C11415gq> f22985a;

    /* renamed from: a */
    private static final C11461if f22984a = new C11461if("XmPushActionCustomConfig");

    /* renamed from: a */
    private static final C11452hx f22983a = new C11452hx("", (byte) 15, 1);

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public List<C11415gq> m3362a() {
        return this.f22985a;
    }

    /* renamed from: a */
    public boolean m3360a() {
        return this.f22985a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11428hc)) {
            return m3358a((C11428hc) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3358a(C11428hc c11428hc) {
        if (c11428hc == null) {
            return false;
        }
        boolean m3360a = m3360a();
        boolean m3360a2 = c11428hc.m3360a();
        if (m3360a || m3360a2) {
            return m3360a && m3360a2 && this.f22985a.equals(c11428hc.f22985a);
        }
        return true;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11428hc c11428hc) {
        int m3070a;
        if (!getClass().equals(c11428hc.getClass())) {
            return getClass().getName().compareTo(c11428hc.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3360a()).compareTo(Boolean.valueOf(c11428hc.m3360a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3360a() || (m3070a = C11443hr.m3070a(this.f22985a, c11428hc.f22985a)) == 0) {
            return 0;
        }
        return m3070a;
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: a */
    public void mo3083a(AbstractC11456ia abstractC11456ia) {
        abstractC11456ia.mo3020a();
        while (true) {
            C11452hx mo3021a = abstractC11456ia.mo3021a();
            if (mo3021a.f23307a != 0) {
                if (mo3021a.f23309a == 1) {
                    if (mo3021a.f23307a == 15) {
                        C11453hy mo2993a = abstractC11456ia.mo2993a();
                        this.f22985a = new ArrayList(mo2993a.f23311a);
                        for (int i = 0; i < mo2993a.f23311a; i++) {
                            C11415gq c11415gq = new C11415gq();
                            c11415gq.mo3083a(abstractC11456ia);
                            this.f22985a.add(c11415gq);
                        }
                        abstractC11456ia.mo2998i();
                    } else {
                        C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                    }
                } else {
                    C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                }
                abstractC11456ia.mo3000g();
            } else {
                abstractC11456ia.mo3001f();
                m3361a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3361a();
        abstractC11456ia.mo3010a(f22984a);
        if (this.f22985a != null) {
            abstractC11456ia.mo3013a(f22983a);
            abstractC11456ia.mo3012a(new C11453hy((byte) 12, this.f22985a.size()));
            for (C11415gq c11415gq : this.f22985a) {
                c11415gq.mo3082b(abstractC11456ia);
            }
            abstractC11456ia.mo3002e();
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionCustomConfig(");
        sb.append("customConfigs:");
        List<C11415gq> list = this.f22985a;
        if (list == null) {
            sb.append("null");
        } else {
            sb.append(list);
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    public void m3361a() {
        if (this.f22985a != null) {
            return;
        }
        throw new C11457ib("Required field 'customConfigs' was not present! Struct: " + toString());
    }
}
