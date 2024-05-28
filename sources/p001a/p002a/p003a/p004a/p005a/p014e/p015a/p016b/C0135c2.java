package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.util.Enumeration;

/* renamed from: a.a.a.a.a.e.a.b.c2 */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C0135c2 extends AbstractC0263s {

    /* renamed from: w3 */
    public int f158w3;

    public C0135c2() {
        this.f158w3 = -1;
    }

    /* renamed from: q */
    private int m24186q() {
        if (this.f158w3 < 0) {
            int i = 0;
            Enumeration mo23747m = mo23747m();
            while (mo23747m.hasMoreElements()) {
                i += ((InterfaceC0136d) mo23747m.nextElement()).mo23015d().mo23005l().mo22977i();
            }
            this.f158w3 = i;
        }
        return this.f158w3;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public void mo22982a(C0252q c0252q) {
        C0252q mo23765d = c0252q.mo23765d();
        int m24186q = m24186q();
        c0252q.mo23763a(48);
        c0252q.m23767b(m24186q);
        Enumeration mo23747m = mo23747m();
        while (mo23747m.hasMoreElements()) {
            mo23765d.mo23772a((InterfaceC0136d) mo23747m.nextElement());
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: i */
    public int mo22977i() {
        int m24186q = m24186q();
        return C0177m2.m24099a(m24186q) + 1 + m24186q;
    }

    public C0135c2(InterfaceC0136d interfaceC0136d) {
        super(interfaceC0136d);
        this.f158w3 = -1;
    }

    public C0135c2(C0140e c0140e) {
        super(c0140e);
        this.f158w3 = -1;
    }

    public C0135c2(InterfaceC0136d[] interfaceC0136dArr) {
        super(interfaceC0136dArr);
        this.f158w3 = -1;
    }
}
