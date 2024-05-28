package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0631q;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.InterfaceC0674f;

/* renamed from: a.a.a.a.a.e.a.c.d0.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC0507a implements InterfaceC0631q, InterfaceC0674f {

    /* renamed from: d */
    public static final int f1624d = 64;

    /* renamed from: a */
    public byte[] f1625a;

    /* renamed from: b */
    public int f1626b;

    /* renamed from: c */
    public long f1627c;

    public AbstractC0507a() {
        this.f1625a = new byte[4];
        this.f1626b = 0;
    }

    /* renamed from: a */
    public abstract void mo22937a(long j);

    /* renamed from: a */
    public void m22964a(AbstractC0507a abstractC0507a) {
        byte[] bArr = abstractC0507a.f1625a;
        System.arraycopy(bArr, 0, this.f1625a, 0, bArr.length);
        this.f1626b = abstractC0507a.f1626b;
        this.f1627c = abstractC0507a.f1627c;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: b */
    public void mo22744b() {
        this.f1627c = 0L;
        this.f1626b = 0;
        int i = 0;
        while (true) {
            byte[] bArr = this.f1625a;
            if (i >= bArr.length) {
                return;
            }
            bArr[i] = 0;
            i++;
        }
    }

    /* renamed from: b */
    public abstract void mo22933b(byte[] bArr, int i);

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0631q
    /* renamed from: d */
    public int mo22696d() {
        return 64;
    }

    /* renamed from: f */
    public void m22963f() {
        long j = this.f1627c << 3;
        mo22747a(Byte.MIN_VALUE);
        while (this.f1626b != 0) {
            mo22747a((byte) 0);
        }
        mo22937a(j);
        mo22930g();
    }

    /* renamed from: g */
    public abstract void mo22930g();

    public AbstractC0507a(AbstractC0507a abstractC0507a) {
        this.f1625a = new byte[abstractC0507a.f1625a.length];
        m22964a(abstractC0507a);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: a */
    public void mo22747a(byte b) {
        byte[] bArr = this.f1625a;
        int i = this.f1626b;
        int i2 = i + 1;
        this.f1626b = i2;
        bArr[i] = b;
        if (i2 == bArr.length) {
            mo22933b(bArr, 0);
            this.f1626b = 0;
        }
        this.f1627c++;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: a */
    public void mo22745a(byte[] bArr, int i, int i2) {
        while (this.f1626b != 0 && i2 > 0) {
            mo22747a(bArr[i]);
            i++;
            i2--;
        }
        while (i2 > this.f1625a.length) {
            mo22933b(bArr, i);
            byte[] bArr2 = this.f1625a;
            i += bArr2.length;
            i2 -= bArr2.length;
            this.f1627c += bArr2.length;
        }
        while (i2 > 0) {
            mo22747a(bArr[i]);
            i++;
            i2--;
        }
    }
}
