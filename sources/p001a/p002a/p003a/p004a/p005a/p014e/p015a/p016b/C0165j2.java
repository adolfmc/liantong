package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.util.Enumeration;

/* renamed from: a.a.a.a.a.e.a.b.j2 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0165j2 extends AbstractC0263s {

    /* renamed from: w3 */
    public byte[] f207w3;

    public C0165j2(byte[] bArr) {
        this.f207w3 = bArr;
    }

    /* renamed from: q */
    private void m24112q() {
        C0161i2 c0161i2 = new C0161i2(this.f207w3);
        while (c0161i2.hasMoreElements()) {
            this.f552v3.addElement(c0161i2.nextElement());
        }
        this.f207w3 = null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s
    /* renamed from: a */
    public synchronized InterfaceC0136d mo23751a(int i) {
        if (this.f207w3 != null) {
            m24112q();
        }
        return super.mo23751a(i);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: i */
    public int mo22977i() {
        byte[] bArr = this.f207w3;
        if (bArr != null) {
            return C0177m2.m24099a(bArr.length) + 1 + this.f207w3.length;
        }
        return super.mo23005l().mo22977i();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: k */
    public AbstractC0258r mo23006k() {
        if (this.f207w3 != null) {
            m24112q();
        }
        return super.mo23006k();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: l */
    public AbstractC0258r mo23005l() {
        if (this.f207w3 != null) {
            m24112q();
        }
        return super.mo23005l();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s
    /* renamed from: m */
    public synchronized Enumeration mo23747m() {
        byte[] bArr = this.f207w3;
        if (bArr == null) {
            return super.mo23747m();
        }
        return new C0161i2(bArr);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s
    /* renamed from: o */
    public synchronized int mo23745o() {
        if (this.f207w3 != null) {
            m24112q();
        }
        return super.mo23745o();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public void mo22982a(C0252q c0252q) {
        byte[] bArr = this.f207w3;
        if (bArr != null) {
            c0252q.m23773a(48, bArr);
        } else {
            super.mo23005l().mo22982a(c0252q);
        }
    }
}
