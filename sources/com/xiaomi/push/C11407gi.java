package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xiaomi.push.gi */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11407gi implements InterfaceC11442hq<C11407gi, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public List<C11408gj> f22500a;

    /* renamed from: a */
    private static final C11461if f22499a = new C11461if("ClientUploadData");

    /* renamed from: a */
    private static final C11452hx f22498a = new C11452hx("", (byte) 15, 1);

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public int m3679a() {
        List<C11408gj> list = this.f22500a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* renamed from: a */
    public void m3674a(C11408gj c11408gj) {
        if (this.f22500a == null) {
            this.f22500a = new ArrayList();
        }
        this.f22500a.add(c11408gj);
    }

    /* renamed from: a */
    public boolean m3677a() {
        return this.f22500a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11407gi)) {
            return m3675a((C11407gi) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3675a(C11407gi c11407gi) {
        if (c11407gi == null) {
            return false;
        }
        boolean m3677a = m3677a();
        boolean m3677a2 = c11407gi.m3677a();
        if (m3677a || m3677a2) {
            return m3677a && m3677a2 && this.f22500a.equals(c11407gi.f22500a);
        }
        return true;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11407gi c11407gi) {
        int m3070a;
        if (!getClass().equals(c11407gi.getClass())) {
            return getClass().getName().compareTo(c11407gi.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3677a()).compareTo(Boolean.valueOf(c11407gi.m3677a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3677a() || (m3070a = C11443hr.m3070a(this.f22500a, c11407gi.f22500a)) == 0) {
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
                        this.f22500a = new ArrayList(mo2993a.f23311a);
                        for (int i = 0; i < mo2993a.f23311a; i++) {
                            C11408gj c11408gj = new C11408gj();
                            c11408gj.mo3083a(abstractC11456ia);
                            this.f22500a.add(c11408gj);
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
                m3678a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3678a();
        abstractC11456ia.mo3010a(f22499a);
        if (this.f22500a != null) {
            abstractC11456ia.mo3013a(f22498a);
            abstractC11456ia.mo3012a(new C11453hy((byte) 12, this.f22500a.size()));
            for (C11408gj c11408gj : this.f22500a) {
                c11408gj.mo3082b(abstractC11456ia);
            }
            abstractC11456ia.mo3002e();
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ClientUploadData(");
        sb.append("uploadDataItems:");
        List<C11408gj> list = this.f22500a;
        if (list == null) {
            sb.append("null");
        } else {
            sb.append(list);
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    public void m3678a() {
        if (this.f22500a != null) {
            return;
        }
        throw new C11457ib("Required field 'uploadDataItems' was not present! Struct: " + toString());
    }
}
