package org.p415a.p424c;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.j */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12613j extends AbstractC12615l {

    /* renamed from: c */
    private byte f25596c;

    /* renamed from: d */
    private byte f25597d;

    /* renamed from: e */
    private byte f25598e;

    public C12613j(C12592b c12592b) {
        super(c12592b);
        byte[] bArr = new byte[c12592b.read()];
        if (bArr.length != 3) {
            throw new IllegalStateException("kdf parameters size of 3 expected.");
        }
        c12592b.m1576a(bArr);
        this.f25596c = bArr[0];
        this.f25597d = bArr[1];
        this.f25598e = bArr[2];
        m1551f();
        m1550g();
    }

    /* renamed from: f */
    private void m1551f() {
        switch (this.f25597d) {
            case 8:
            case 9:
            case 10:
                return;
            default:
                throw new IllegalStateException("Hash algorithm must be SHA-256 or stronger.");
        }
    }

    /* renamed from: g */
    private void m1550g() {
        switch (this.f25598e) {
            case 7:
            case 8:
            case 9:
                return;
            default:
                throw new IllegalStateException("Symmetric key algorithm must be AES-128 or stronger.");
        }
    }

    @Override // org.p415a.p424c.AbstractC12615l, org.p415a.p424c.AbstractC12607d
    /* renamed from: a */
    public void mo1533a(C12608e c12608e) {
        super.mo1533a(c12608e);
        c12608e.write(3);
        c12608e.write(this.f25596c);
        c12608e.write(this.f25597d);
        c12608e.write(this.f25598e);
    }

    /* renamed from: b */
    public byte m1553b() {
        return this.f25597d;
    }

    /* renamed from: c */
    public byte m1552c() {
        return this.f25598e;
    }
}
