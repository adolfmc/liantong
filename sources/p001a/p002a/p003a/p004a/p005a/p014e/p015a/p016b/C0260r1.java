package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.util.Enumeration;

/* renamed from: a.a.a.a.a.e.a.b.r1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0260r1 extends AbstractC0338u {

    /* renamed from: x3 */
    public int f537x3;

    public C0260r1() {
        this.f537x3 = -1;
    }

    /* renamed from: r */
    private int m23754r() {
        if (this.f537x3 < 0) {
            int i = 0;
            Enumeration m23578m = m23578m();
            while (m23578m.hasMoreElements()) {
                i += ((InterfaceC0136d) m23578m.nextElement()).mo23015d().mo23006k().mo22977i();
            }
            this.f537x3 = i;
        }
        return this.f537x3;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0338u, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public void mo22982a(C0252q c0252q) {
        C0252q mo23766c = c0252q.mo23766c();
        int m23754r = m23754r();
        c0252q.mo23763a(49);
        c0252q.m23767b(m23754r);
        Enumeration m23578m = m23578m();
        while (m23578m.hasMoreElements()) {
            mo23766c.mo23772a((InterfaceC0136d) m23578m.nextElement());
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: i */
    public int mo22977i() {
        int m23754r = m23754r();
        return C0177m2.m24099a(m23754r) + 1 + m23754r;
    }

    public C0260r1(InterfaceC0136d interfaceC0136d) {
        super(interfaceC0136d);
        this.f537x3 = -1;
    }

    public C0260r1(C0140e c0140e) {
        super(c0140e, true);
        this.f537x3 = -1;
    }

    public C0260r1(InterfaceC0136d[] interfaceC0136dArr) {
        super(interfaceC0136dArr, true);
        this.f537x3 = -1;
    }

    public C0260r1(C0140e c0140e, boolean z) {
        super(c0140e, z);
        this.f537x3 = -1;
    }
}
