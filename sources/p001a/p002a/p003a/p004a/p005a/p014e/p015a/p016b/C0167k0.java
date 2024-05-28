package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.util.Enumeration;

/* renamed from: a.a.a.a.a.e.a.b.k0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0167k0 extends AbstractC0263s {
    public C0167k0() {
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public void mo22982a(C0252q c0252q) {
        c0252q.mo23763a(48);
        c0252q.mo23763a(128);
        Enumeration mo23747m = mo23747m();
        while (mo23747m.hasMoreElements()) {
            c0252q.mo23772a((InterfaceC0136d) mo23747m.nextElement());
        }
        c0252q.mo23763a(0);
        c0252q.mo23763a(0);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: i */
    public int mo22977i() {
        Enumeration mo23747m = mo23747m();
        int i = 0;
        while (mo23747m.hasMoreElements()) {
            i += ((InterfaceC0136d) mo23747m.nextElement()).mo23015d().mo22977i();
        }
        return i + 2 + 2;
    }

    public C0167k0(InterfaceC0136d interfaceC0136d) {
        super(interfaceC0136d);
    }

    public C0167k0(C0140e c0140e) {
        super(c0140e);
    }

    public C0167k0(InterfaceC0136d[] interfaceC0136dArr) {
        super(interfaceC0136dArr);
    }
}
