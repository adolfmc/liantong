package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0;

/* renamed from: a.a.a.a.a.e.a.c.o0.g */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0613g implements InterfaceC0612f {

    /* renamed from: a */
    public final InterfaceC0612f f1908a;

    /* renamed from: b */
    public byte[] f1909b;

    /* renamed from: c */
    public int f1910c;

    public C0613g(InterfaceC0612f interfaceC0612f, int i) {
        if (interfaceC0612f == null) {
            throw new IllegalArgumentException("generator cannot be null");
        }
        if (i >= 2) {
            this.f1908a = interfaceC0612f;
            this.f1909b = new byte[i];
            return;
        }
        throw new IllegalArgumentException("windowSize must be at least 2");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0.InterfaceC0612f
    /* renamed from: a */
    public void mo22727a(long j) {
        synchronized (this) {
            this.f1910c = 0;
            this.f1908a.mo22727a(j);
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0.InterfaceC0612f
    /* renamed from: b */
    public void mo22724b(byte[] bArr) {
        synchronized (this) {
            this.f1910c = 0;
            this.f1908a.mo22724b(bArr);
        }
    }

    /* renamed from: b */
    private void m22730b(byte[] bArr, int i, int i2) {
        synchronized (this) {
            for (int i3 = 0; i3 < i2; i3++) {
                if (this.f1910c < 1) {
                    this.f1908a.mo22725a(this.f1909b, 0, this.f1909b.length);
                    this.f1910c = this.f1909b.length;
                }
                byte[] bArr2 = this.f1909b;
                int i4 = this.f1910c - 1;
                this.f1910c = i4;
                bArr[i3 + i] = bArr2[i4];
            }
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0.InterfaceC0612f
    /* renamed from: a */
    public void mo22726a(byte[] bArr) {
        m22730b(bArr, 0, bArr.length);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0.InterfaceC0612f
    /* renamed from: a */
    public void mo22725a(byte[] bArr, int i, int i2) {
        m22730b(bArr, i, i2);
    }
}
