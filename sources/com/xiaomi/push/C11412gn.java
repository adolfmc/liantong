package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.gn */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11412gn implements InterfaceC11442hq<C11412gn, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public long f22671a;

    /* renamed from: a */
    public EnumC11406gh f22672a;

    /* renamed from: a */
    public String f22673a;

    /* renamed from: a */
    private BitSet f22674a = new BitSet(1);

    /* renamed from: a */
    private static final C11461if f22668a = new C11461if("DataCollectionItem");

    /* renamed from: a */
    private static final C11452hx f22667a = new C11452hx("", (byte) 10, 1);

    /* renamed from: b */
    private static final C11452hx f22669b = new C11452hx("", (byte) 8, 2);

    /* renamed from: c */
    private static final C11452hx f22670c = new C11452hx("", (byte) 11, 3);

    public int hashCode() {
        return 0;
    }

    /* renamed from: a */
    public C11412gn m3630a(long j) {
        this.f22671a = j;
        m3625a(true);
        return this;
    }

    /* renamed from: a */
    public boolean m3631a() {
        return this.f22674a.get(0);
    }

    /* renamed from: a */
    public void m3625a(boolean z) {
        this.f22674a.set(0, z);
    }

    /* renamed from: a */
    public C11412gn m3629a(EnumC11406gh enumC11406gh) {
        this.f22672a = enumC11406gh;
        return this;
    }

    /* renamed from: b */
    public boolean m3624b() {
        return this.f22672a != null;
    }

    /* renamed from: a */
    public String m3633a() {
        return this.f22673a;
    }

    /* renamed from: a */
    public C11412gn m3626a(String str) {
        this.f22673a = str;
        return this;
    }

    /* renamed from: c */
    public boolean m3623c() {
        return this.f22673a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C11412gn)) {
            return m3627a((C11412gn) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m3627a(C11412gn c11412gn) {
        if (c11412gn != null && this.f22671a == c11412gn.f22671a) {
            boolean m3624b = m3624b();
            boolean m3624b2 = c11412gn.m3624b();
            if ((m3624b || m3624b2) && !(m3624b && m3624b2 && this.f22672a.equals(c11412gn.f22672a))) {
                return false;
            }
            boolean m3623c = m3623c();
            boolean m3623c2 = c11412gn.m3623c();
            if (m3623c || m3623c2) {
                return m3623c && m3623c2 && this.f22673a.equals(c11412gn.f22673a);
            }
            return true;
        }
        return false;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11412gn c11412gn) {
        int m3076a;
        int m3077a;
        int m3078a;
        if (!getClass().equals(c11412gn.getClass())) {
            return getClass().getName().compareTo(c11412gn.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m3631a()).compareTo(Boolean.valueOf(c11412gn.m3631a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m3631a() || (m3078a = C11443hr.m3078a(this.f22671a, c11412gn.f22671a)) == 0) {
            int compareTo2 = Boolean.valueOf(m3624b()).compareTo(Boolean.valueOf(c11412gn.m3624b()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (!m3624b() || (m3077a = C11443hr.m3077a(this.f22672a, c11412gn.f22672a)) == 0) {
                int compareTo3 = Boolean.valueOf(m3623c()).compareTo(Boolean.valueOf(c11412gn.m3623c()));
                if (compareTo3 != 0) {
                    return compareTo3;
                }
                if (!m3623c() || (m3076a = C11443hr.m3076a(this.f22673a, c11412gn.f22673a)) == 0) {
                    return 0;
                }
                return m3076a;
            }
            return m3077a;
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
                switch (mo3021a.f23309a) {
                    case 1:
                        if (mo3021a.f23307a == 10) {
                            this.f22671a = abstractC11456ia.mo3022a();
                            m3625a(true);
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 2:
                        if (mo3021a.f23307a == 8) {
                            this.f22672a = EnumC11406gh.m3680a(abstractC11456ia.mo3023a());
                            break;
                        } else {
                            C11459id.m2995a(abstractC11456ia, mo3021a.f23307a);
                            break;
                        }
                    case 3:
                        if (mo3021a.f23307a == 11) {
                            this.f22673a = abstractC11456ia.mo2990a();
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
                if (!m3631a()) {
                    throw new C11457ib("Required field 'collectedAt' was not found in serialized data! Struct: " + toString());
                }
                m3632a();
                return;
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC11442hq
    /* renamed from: b */
    public void mo3082b(AbstractC11456ia abstractC11456ia) {
        m3632a();
        abstractC11456ia.mo3010a(f22668a);
        abstractC11456ia.mo3013a(f22667a);
        abstractC11456ia.mo3014a(this.f22671a);
        abstractC11456ia.mo3005b();
        if (this.f22672a != null) {
            abstractC11456ia.mo3013a(f22669b);
            abstractC11456ia.mo3015a(this.f22672a.m3681a());
            abstractC11456ia.mo3005b();
        }
        if (this.f22673a != null) {
            abstractC11456ia.mo3013a(f22670c);
            abstractC11456ia.mo3009a(this.f22673a);
            abstractC11456ia.mo3005b();
        }
        abstractC11456ia.mo3004c();
        abstractC11456ia.mo3018a();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DataCollectionItem(");
        sb.append("collectedAt:");
        sb.append(this.f22671a);
        sb.append(", ");
        sb.append("collectionType:");
        EnumC11406gh enumC11406gh = this.f22672a;
        if (enumC11406gh == null) {
            sb.append("null");
        } else {
            sb.append(enumC11406gh);
        }
        sb.append(", ");
        sb.append("content:");
        String str = this.f22673a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    public void m3632a() {
        if (this.f22672a == null) {
            throw new C11457ib("Required field 'collectionType' was not present! Struct: " + toString());
        } else if (this.f22673a != null) {
        } else {
            throw new C11457ib("Required field 'content' was not present! Struct: " + toString());
        }
    }
}
