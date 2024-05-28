package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xiaomi.push.gy */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11423gy implements InterfaceC11442hq<C11423gy, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public List<C11412gn> f22919a;

    /* renamed from: a */
    private static final C11461if f22918a = new C11461if("XmPushActionCollectData");

    /* renamed from: a */
    private static final C11452hx f22917a = new C11452hx("", (byte) 15, 1);

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public C11423gy m3435a(List<C11412gn> list) {
        this.f22919a = list;
        return this;
    }

    /* renamed from: a */
    public boolean m3438a() {
        return this.f22919a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11423gy)) {
            return m3436a((C11423gy) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3436a(C11423gy c11423gy) {
        if (c11423gy == null) {
            return false;
        }
        boolean m3438a = m3438a();
        boolean m3438a2 = c11423gy.m3438a();
        if (m3438a || m3438a2) {
            return m3438a && m3438a2 && this.f22919a.equals(c11423gy.f22919a);
        }
        return true;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11423gy c11423gy) {
        int m3070a;
        if (!getClass().equals(c11423gy.getClass())) {
            return getClass().getName().compareTo(c11423gy.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3438a()).compareTo(Boolean.valueOf(c11423gy.m3438a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3438a() || (m3070a = C11443hr.m3070a(this.f22919a, c11423gy.f22919a)) == 0) {
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
                        this.f22919a = new ArrayList(mo2993a.f23311a);
                        for (int i = 0; i < mo2993a.f23311a; i++) {
                            C11412gn c11412gn = new C11412gn();
                            c11412gn.mo3083a(abstractC11456ia);
                            this.f22919a.add(c11412gn);
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
                m3439a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3439a();
        abstractC11456ia.mo3010a(f22918a);
        if (this.f22919a != null) {
            abstractC11456ia.mo3013a(f22917a);
            abstractC11456ia.mo3012a(new C11453hy((byte) 12, this.f22919a.size()));
            for (C11412gn c11412gn : this.f22919a) {
                c11412gn.mo3082b(abstractC11456ia);
            }
            abstractC11456ia.mo3002e();
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionCollectData(");
        sb.append("dataCollectionItems:");
        List<C11412gn> list = this.f22919a;
        if (list == null) {
            sb.append("null");
        } else {
            sb.append(list);
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    public void m3439a() {
        if (this.f22919a != null) {
            return;
        }
        throw new C11457ib("Required field 'dataCollectionItems' was not present! Struct: " + toString());
    }
}
