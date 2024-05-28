package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.util.Enumeration;

/* renamed from: a.a.a.a.a.e.a.b.n0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0179n0 extends AbstractC0338u {
    public C0179n0() {
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0338u, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public void mo22982a(C0252q c0252q) {
        c0252q.mo23763a(49);
        c0252q.mo23763a(128);
        Enumeration m23578m = m23578m();
        while (m23578m.hasMoreElements()) {
            c0252q.mo23772a((InterfaceC0136d) m23578m.nextElement());
        }
        c0252q.mo23763a(0);
        c0252q.mo23763a(0);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: i */
    public int mo22977i() {
        Enumeration m23578m = m23578m();
        int i = 0;
        while (m23578m.hasMoreElements()) {
            i += ((InterfaceC0136d) m23578m.nextElement()).mo23015d().mo22977i();
        }
        return i + 2 + 2;
    }

    public C0179n0(InterfaceC0136d interfaceC0136d) {
        super(interfaceC0136d);
    }

    public C0179n0(C0140e c0140e) {
        super(c0140e, false);
    }

    public C0179n0(InterfaceC0136d[] interfaceC0136dArr) {
        super(interfaceC0136dArr, false);
    }
}
