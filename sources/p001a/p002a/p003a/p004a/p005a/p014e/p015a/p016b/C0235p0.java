package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0567f;

/* renamed from: a.a.a.a.a.e.a.b.p0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0235p0 extends AbstractC0494y {
    public C0235p0(int i, InterfaceC0136d interfaceC0136d) {
        super(true, i, interfaceC0136d);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public void mo22982a(C0252q c0252q) {
        Enumeration m23578m;
        c0252q.m23775a(C0567f.f1819h, this.f1605v3);
        c0252q.mo23763a(128);
        if (!this.f1606w3) {
            if (!this.f1607x3) {
                InterfaceC0136d interfaceC0136d = this.f1608y3;
                if (interfaceC0136d instanceof AbstractC0182o) {
                    if (interfaceC0136d instanceof C0149g0) {
                        m23578m = ((C0149g0) interfaceC0136d).mo24150o();
                    } else {
                        m23578m = new C0149g0(((AbstractC0182o) interfaceC0136d).mo24088m()).mo24150o();
                    }
                } else if (interfaceC0136d instanceof AbstractC0263s) {
                    m23578m = ((AbstractC0263s) interfaceC0136d).mo23747m();
                } else if (interfaceC0136d instanceof AbstractC0338u) {
                    m23578m = ((AbstractC0338u) interfaceC0136d).m23578m();
                } else {
                    throw new RuntimeException("not implemented: " + this.f1608y3.getClass().getName());
                }
                while (m23578m.hasMoreElements()) {
                    c0252q.mo23772a((InterfaceC0136d) m23578m.nextElement());
                }
            } else {
                c0252q.mo23772a(this.f1608y3);
            }
        }
        c0252q.mo23763a(0);
        c0252q.mo23763a(0);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: i */
    public int mo22977i() {
        if (!this.f1606w3) {
            int mo22977i = this.f1608y3.mo23015d().mo22977i();
            if (this.f1607x3) {
                return C0177m2.m24097b(this.f1605v3) + C0177m2.m24099a(mo22977i) + mo22977i;
            }
            return C0177m2.m24097b(this.f1605v3) + (mo22977i - 1);
        }
        return C0177m2.m24097b(this.f1605v3) + 1;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: j */
    public boolean mo22976j() {
        if (this.f1606w3 || this.f1607x3) {
            return true;
        }
        return this.f1608y3.mo23015d().mo23006k().mo22976j();
    }

    public C0235p0(boolean z, int i, InterfaceC0136d interfaceC0136d) {
        super(z, i, interfaceC0136d);
    }

    public C0235p0(int i) {
        super(false, i, new C0167k0());
    }
}
