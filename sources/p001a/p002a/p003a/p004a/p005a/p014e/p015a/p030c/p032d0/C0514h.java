package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0631q;

/* renamed from: a.a.a.a.a.e.a.c.d0.h */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0514h implements InterfaceC0631q {

    /* renamed from: a */
    public InterfaceC0631q f1693a;

    /* renamed from: b */
    public int f1694b;

    public C0514h(InterfaceC0631q interfaceC0631q, int i) {
        if (interfaceC0631q != null) {
            if (i <= interfaceC0631q.mo22743c()) {
                this.f1693a = interfaceC0631q;
                this.f1694b = i;
                return;
            }
            throw new IllegalArgumentException("baseDigest output not large enough to support length");
        }
        throw new IllegalArgumentException("baseDigest must not be null");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: a */
    public String mo22748a() {
        return this.f1693a.mo22748a() + "(" + (this.f1694b * 8) + ")";
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: b */
    public void mo22744b() {
        this.f1693a.mo22744b();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: c */
    public int mo22743c() {
        return this.f1694b;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0631q
    /* renamed from: d */
    public int mo22696d() {
        return this.f1693a.mo22696d();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: a */
    public void mo22747a(byte b) {
        this.f1693a.mo22747a(b);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: a */
    public void mo22745a(byte[] bArr, int i, int i2) {
        this.f1693a.mo22745a(bArr, i, i2);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: a */
    public int mo22746a(byte[] bArr, int i) {
        byte[] bArr2 = new byte[this.f1693a.mo22743c()];
        this.f1693a.mo22746a(bArr2, 0);
        System.arraycopy(bArr2, 0, bArr, i, this.f1694b);
        return this.f1694b;
    }
}
