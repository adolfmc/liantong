package org.p415a.p427d;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.e */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12693e {

    /* renamed from: a */
    protected byte[] f25859a;

    /* renamed from: b */
    protected int f25860b;

    /* renamed from: c */
    protected boolean f25861c;

    /* renamed from: d */
    protected InterfaceC12680d f25862d;

    /* renamed from: e */
    protected boolean f25863e;

    /* renamed from: f */
    protected boolean f25864f;

    protected C12693e() {
    }

    public C12693e(InterfaceC12680d interfaceC12680d) {
        this.f25862d = interfaceC12680d;
        this.f25859a = new byte[interfaceC12680d.mo1349b()];
        boolean z = false;
        this.f25860b = 0;
        String mo1353a = interfaceC12680d.mo1353a();
        int indexOf = mo1353a.indexOf(47) + 1;
        this.f25864f = indexOf > 0 && mo1353a.startsWith("PGP", indexOf);
        if (this.f25864f || (interfaceC12680d instanceof InterfaceC12733r)) {
            this.f25863e = true;
            return;
        }
        if (indexOf > 0 && mo1353a.startsWith("OpenPGP", indexOf)) {
            z = true;
        }
        this.f25863e = z;
    }

    /* renamed from: a */
    public int m1371a() {
        return this.f25862d.mo1349b();
    }

    /* renamed from: a */
    public int m1370a(int i) {
        int length;
        int i2;
        int i3 = i + this.f25860b;
        if (!this.f25864f) {
            length = this.f25859a.length;
        } else if (this.f25861c) {
            i2 = (i3 % this.f25859a.length) - (this.f25862d.mo1349b() + 2);
            return i3 - i2;
        } else {
            length = this.f25859a.length;
        }
        i2 = i3 % length;
        return i3 - i2;
    }

    /* renamed from: a */
    public int m1368a(byte[] bArr, int i) {
        int i2;
        try {
            if (this.f25860b + i <= bArr.length) {
                if (this.f25860b == 0) {
                    i2 = 0;
                } else if (!this.f25863e) {
                    throw new C12704h("data not block size aligned");
                } else {
                    this.f25862d.mo1350a(this.f25859a, 0, this.f25859a, 0);
                    i2 = this.f25860b;
                    this.f25860b = 0;
                    System.arraycopy(this.f25859a, 0, bArr, i, i2);
                }
                return i2;
            }
            throw new C12730o("output buffer too short for doFinal()");
        } finally {
            m1366b();
        }
    }

    /* renamed from: a */
    public int m1367a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4;
        if (i2 >= 0) {
            int m1371a = m1371a();
            int m1370a = m1370a(i2);
            if (m1370a <= 0 || m1370a + i3 <= bArr2.length) {
                byte[] bArr3 = this.f25859a;
                int length = bArr3.length;
                int i5 = this.f25860b;
                int i6 = length - i5;
                if (i2 > i6) {
                    System.arraycopy(bArr, i, bArr3, i5, i6);
                    i4 = this.f25862d.mo1350a(this.f25859a, 0, bArr2, i3) + 0;
                    this.f25860b = 0;
                    i2 -= i6;
                    i += i6;
                    while (i2 > this.f25859a.length) {
                        i4 += this.f25862d.mo1350a(bArr, i, bArr2, i3 + i4);
                        i2 -= m1371a;
                        i += m1371a;
                    }
                } else {
                    i4 = 0;
                }
                System.arraycopy(bArr, i, this.f25859a, this.f25860b, i2);
                this.f25860b += i2;
                int i7 = this.f25860b;
                byte[] bArr4 = this.f25859a;
                if (i7 == bArr4.length) {
                    int mo1350a = i4 + this.f25862d.mo1350a(bArr4, 0, bArr2, i3 + i4);
                    this.f25860b = 0;
                    return mo1350a;
                }
                return i4;
            }
            throw new C12730o("output buffer too short");
        }
        throw new IllegalArgumentException("Can't have a negative input length!");
    }

    /* renamed from: a */
    public void m1369a(boolean z, InterfaceC12696f interfaceC12696f) {
        this.f25861c = z;
        m1366b();
        this.f25862d.mo1351a(z, interfaceC12696f);
    }

    /* renamed from: b */
    public int m1365b(int i) {
        return i + this.f25860b;
    }

    /* renamed from: b */
    public void m1366b() {
        int i = 0;
        while (true) {
            byte[] bArr = this.f25859a;
            if (i >= bArr.length) {
                this.f25860b = 0;
                this.f25862d.mo1347c();
                return;
            }
            bArr[i] = 0;
            i++;
        }
    }
}
