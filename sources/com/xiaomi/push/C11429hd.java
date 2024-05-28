package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xiaomi.push.hd */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11429hd implements InterfaceC11442hq<C11429hd, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public List<C11413go> f22988a;

    /* renamed from: a */
    private static final C11461if f22987a = new C11461if("XmPushActionNormalConfig");

    /* renamed from: a */
    private static final C11452hx f22986a = new C11452hx("", (byte) 15, 1);

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public List<C11413go> m3357a() {
        return this.f22988a;
    }

    /* renamed from: a */
    public boolean m3355a() {
        return this.f22988a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11429hd)) {
            return m3353a((C11429hd) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3353a(C11429hd c11429hd) {
        if (c11429hd == null) {
            return false;
        }
        boolean m3355a = m3355a();
        boolean m3355a2 = c11429hd.m3355a();
        if (m3355a || m3355a2) {
            return m3355a && m3355a2 && this.f22988a.equals(c11429hd.f22988a);
        }
        return true;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11429hd c11429hd) {
        int m3070a;
        if (!getClass().equals(c11429hd.getClass())) {
            return getClass().getName().compareTo(c11429hd.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3355a()).compareTo(Boolean.valueOf(c11429hd.m3355a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3355a() || (m3070a = C11443hr.m3070a(this.f22988a, c11429hd.f22988a)) == 0) {
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
                        this.f22988a = new ArrayList(mo2993a.f23311a);
                        for (int i = 0; i < mo2993a.f23311a; i++) {
                            C11413go c11413go = new C11413go();
                            c11413go.mo3083a(abstractC11456ia);
                            this.f22988a.add(c11413go);
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
                m3356a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3356a();
        abstractC11456ia.mo3010a(f22987a);
        if (this.f22988a != null) {
            abstractC11456ia.mo3013a(f22986a);
            abstractC11456ia.mo3012a(new C11453hy((byte) 12, this.f22988a.size()));
            for (C11413go c11413go : this.f22988a) {
                c11413go.mo3082b(abstractC11456ia);
            }
            abstractC11456ia.mo3002e();
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionNormalConfig(");
        sb.append("normalConfigs:");
        List<C11413go> list = this.f22988a;
        if (list == null) {
            sb.append("null");
        } else {
            sb.append(list);
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    public void m3356a() {
        if (this.f22988a != null) {
            return;
        }
        throw new C11457ib("Required field 'normalConfigs' was not present! Struct: " + toString());
    }
}
