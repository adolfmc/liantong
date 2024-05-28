package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.util.Enumeration;

/* renamed from: a.a.a.a.a.e.a.b.d2 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0139d2 extends AbstractC0338u {

    /* renamed from: x3 */
    public int f162x3;

    public C0139d2() {
        this.f162x3 = -1;
    }

    /* renamed from: r */
    private int m24173r() {
        if (this.f162x3 < 0) {
            int i = 0;
            Enumeration m23578m = m23578m();
            while (m23578m.hasMoreElements()) {
                i += ((InterfaceC0136d) m23578m.nextElement()).mo23015d().mo23005l().mo22977i();
            }
            this.f162x3 = i;
        }
        return this.f162x3;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0338u, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public void mo22982a(C0252q c0252q) {
        C0252q mo23765d = c0252q.mo23765d();
        int m24173r = m24173r();
        c0252q.mo23763a(49);
        c0252q.m23767b(m24173r);
        Enumeration m23578m = m23578m();
        while (m23578m.hasMoreElements()) {
            mo23765d.mo23772a((InterfaceC0136d) m23578m.nextElement());
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: i */
    public int mo22977i() {
        int m24173r = m24173r();
        return C0177m2.m24099a(m24173r) + 1 + m24173r;
    }

    public C0139d2(InterfaceC0136d interfaceC0136d) {
        super(interfaceC0136d);
        this.f162x3 = -1;
    }

    public C0139d2(C0140e c0140e) {
        super(c0140e, false);
        this.f162x3 = -1;
    }

    public C0139d2(InterfaceC0136d[] interfaceC0136dArr) {
        super(interfaceC0136dArr, false);
        this.f162x3 = -1;
    }
}
