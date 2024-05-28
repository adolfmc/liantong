package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0567f;

/* renamed from: a.a.a.a.a.e.a.b.v1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0360v1 extends AbstractC0494y {

    /* renamed from: z3 */
    public static final byte[] f930z3 = new byte[0];

    public C0360v1(boolean z, int i, InterfaceC0136d interfaceC0136d) {
        super(z, i, interfaceC0136d);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public void mo22982a(C0252q c0252q) {
        boolean z = this.f1606w3;
        int i = C0567f.f1819h;
        if (!z) {
            AbstractC0258r mo23006k = this.f1608y3.mo23015d().mo23006k();
            if (this.f1607x3) {
                c0252q.m23775a(C0567f.f1819h, this.f1605v3);
                c0252q.m23767b(mo23006k.mo22977i());
                c0252q.mo23772a((InterfaceC0136d) mo23006k);
                return;
            }
            if (!mo23006k.mo22976j()) {
                i = 128;
            }
            c0252q.m23775a(i, this.f1605v3);
            c0252q.m23771a(mo23006k);
            return;
        }
        c0252q.m23774a(C0567f.f1819h, this.f1605v3, f930z3);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: i */
    public int mo22977i() {
        if (!this.f1606w3) {
            int mo22977i = this.f1608y3.mo23015d().mo23006k().mo22977i();
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

    public C0360v1(int i, InterfaceC0136d interfaceC0136d) {
        super(true, i, interfaceC0136d);
    }
}
