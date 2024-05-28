package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o;

/* renamed from: a.a.a.a.a.e.a.c.o0.c */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0609c implements InterfaceC0612f {

    /* renamed from: f */
    public static long f1902f = 10;

    /* renamed from: c */
    public InterfaceC0605o f1905c;

    /* renamed from: d */
    public byte[] f1906d;

    /* renamed from: e */
    public byte[] f1907e;

    /* renamed from: b */
    public long f1904b = 1;

    /* renamed from: a */
    public long f1903a = 1;

    public C0609c(InterfaceC0605o interfaceC0605o) {
        this.f1905c = interfaceC0605o;
        this.f1907e = new byte[interfaceC0605o.mo22743c()];
        this.f1906d = new byte[interfaceC0605o.mo22743c()];
    }

    /* renamed from: c */
    private void m22736c(byte[] bArr) {
        this.f1905c.mo22746a(bArr, 0);
    }

    /* renamed from: d */
    private void m22735d(byte[] bArr) {
        this.f1905c.mo22745a(bArr, 0, bArr.length);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0.InterfaceC0612f
    /* renamed from: a */
    public void mo22727a(long j) {
        synchronized (this) {
            m22737b(j);
            m22735d(this.f1907e);
            m22736c(this.f1907e);
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0.InterfaceC0612f
    /* renamed from: b */
    public void mo22724b(byte[] bArr) {
        synchronized (this) {
            m22735d(bArr);
            m22735d(this.f1907e);
            m22736c(this.f1907e);
        }
    }

    /* renamed from: b */
    private void m22738b() {
        long j = this.f1903a;
        this.f1903a = 1 + j;
        m22737b(j);
        m22735d(this.f1906d);
        m22735d(this.f1907e);
        m22736c(this.f1906d);
        if (this.f1903a % f1902f == 0) {
            m22739a();
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0.InterfaceC0612f
    /* renamed from: a */
    public void mo22726a(byte[] bArr) {
        mo22725a(bArr, 0, bArr.length);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0.InterfaceC0612f
    /* renamed from: a */
    public void mo22725a(byte[] bArr, int i, int i2) {
        synchronized (this) {
            m22738b();
            int i3 = i2 + i;
            int i4 = 0;
            while (i != i3) {
                if (i4 == this.f1906d.length) {
                    m22738b();
                    i4 = 0;
                }
                bArr[i] = this.f1906d[i4];
                i++;
                i4++;
            }
        }
    }

    /* renamed from: b */
    private void m22737b(long j) {
        for (int i = 0; i != 8; i++) {
            this.f1905c.mo22747a((byte) j);
            j >>>= 8;
        }
    }

    /* renamed from: a */
    private void m22739a() {
        m22735d(this.f1907e);
        long j = this.f1904b;
        this.f1904b = 1 + j;
        m22737b(j);
        m22736c(this.f1907e);
    }
}
