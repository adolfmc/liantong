package com.baidu.p122b.p125c.p126a;

import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.c.a.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2398e {

    /* renamed from: a */
    private byte[] f4213a;

    /* renamed from: b */
    private int f4214b;

    /* renamed from: c */
    private int f4215c;

    /* renamed from: f */
    private int f4218f;

    /* renamed from: g */
    private InterfaceC2402i f4219g;

    /* renamed from: h */
    private AbstractC2399f f4220h;

    /* renamed from: d */
    private int f4216d = 0;

    /* renamed from: e */
    private int f4217e = 0;

    /* renamed from: i */
    private int f4221i = 1;

    /* renamed from: j */
    private boolean f4222j = false;

    public C2398e(C2395b c2395b, int i) {
        this.f4213a = null;
        this.f4214b = 0;
        this.f4215c = 0;
        this.f4218f = 0;
        this.f4219g = null;
        this.f4220h = null;
        this.f4214b = i;
        this.f4215c = i;
        this.f4218f = i;
        this.f4213a = new byte[this.f4214b * 2];
        this.f4220h = new C2397d(c2395b);
        this.f4219g = new C2401h(this.f4214b);
    }

    /* renamed from: a */
    private int m20274a(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        if (bArr == null || i3 == 0) {
            return 0;
        }
        int i4 = this.f4221i;
        if (i4 == 2 || i4 == 3 || i3 % this.f4215c == 0 || i4 == 6) {
            if (this.f4222j) {
                this.f4220h.m20266d(bArr, i, i3, bArr2, i2);
            } else {
                this.f4220h.m20267c(bArr, i, i3, bArr2, i2);
            }
            return i3;
        } else if (this.f4219g != null) {
            throw new IllegalBlockSizeException("Input length (with padding) not multiple of " + this.f4215c + " bytes");
        } else {
            throw new IllegalBlockSizeException("Input length not multiple of " + this.f4215c + " bytes");
        }
    }

    /* renamed from: a */
    int m20278a(int i) {
        int i2 = this.f4216d + i;
        InterfaceC2402i interfaceC2402i = this.f4219g;
        if (interfaceC2402i == null || this.f4222j) {
            return i2;
        }
        int i3 = this.f4215c;
        int i4 = this.f4214b;
        if (i3 != i4) {
            int i5 = this.f4218f;
            return i2 < i5 ? i5 : (i2 + i4) - ((i2 - i5) % i4);
        }
        return i2 + interfaceC2402i.mo20264a(i2);
    }

    /* renamed from: a */
    int m20275a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int mo20264a;
        byte[] bArr3;
        int i4;
        InterfaceC2402i interfaceC2402i;
        int m20274a;
        int i5 = this.f4216d + i2;
        int i6 = this.f4215c;
        int i7 = this.f4214b;
        if (i6 != i7) {
            int i8 = this.f4218f;
            mo20264a = i5 < i8 ? i8 - i5 : i7 - ((i5 - i8) % i7);
        } else {
            InterfaceC2402i interfaceC2402i2 = this.f4219g;
            mo20264a = interfaceC2402i2 != null ? interfaceC2402i2.mo20264a(i5) : 0;
        }
        if (mo20264a > 0 && mo20264a != this.f4214b && this.f4219g != null && this.f4222j) {
            throw new IllegalBlockSizeException("Input length must be multiple of " + this.f4214b + " when decrypting with padded cipher");
        }
        int i9 = (this.f4222j || this.f4219g == null) ? i5 : i5 + mo20264a;
        if (bArr2 != null) {
            int length = bArr2.length - i3;
            if (((!this.f4222j || this.f4219g == null) && length < i9) || (this.f4222j && length < i9 - this.f4214b)) {
                throw new ShortBufferException("Output buffer too short: " + length + " bytes given, " + i9 + " bytes needed");
            }
            if (this.f4216d == 0 && (this.f4222j || this.f4219g == null)) {
                bArr3 = bArr;
                i4 = i;
            } else {
                byte[] bArr4 = new byte[i9];
                int i10 = this.f4216d;
                if (i10 != 0) {
                    System.arraycopy(this.f4213a, 0, bArr4, 0, i10);
                }
                if (i2 != 0) {
                    System.arraycopy(bArr, i, bArr4, this.f4216d, i2);
                }
                if (!this.f4222j && (interfaceC2402i = this.f4219g) != null) {
                    interfaceC2402i.mo20263a(bArr4, i5, mo20264a);
                }
                bArr3 = bArr4;
                i4 = 0;
            }
            if (this.f4222j) {
                if (length < i9) {
                    this.f4220h.mo20270b();
                }
                byte[] bArr5 = new byte[i5];
                m20274a = m20274a(bArr3, i4, bArr5, 0, i5);
                InterfaceC2402i interfaceC2402i3 = this.f4219g;
                if (interfaceC2402i3 != null && (m20274a = interfaceC2402i3.mo20262b(bArr5, 0, m20274a)) < 0) {
                    throw new BadPaddingException("Given final block not properly padded");
                }
                if (bArr2.length - i3 < m20274a) {
                    this.f4220h.mo20268c();
                    throw new ShortBufferException("Output buffer too short: " + (bArr2.length - i3) + " bytes given, " + m20274a + " bytes needed");
                }
                for (int i11 = 0; i11 < m20274a; i11++) {
                    bArr2[i3 + i11] = bArr5[i11];
                }
            } else {
                m20274a = m20274a(bArr3, i4, bArr2, i3, i9);
            }
            this.f4216d = 0;
            this.f4218f = this.f4214b;
            if (this.f4221i != 0) {
                this.f4220h.mo20273a();
            }
            return m20274a;
        }
        throw new ShortBufferException("Output buffer is null");
    }

    /* renamed from: a */
    public void m20277a(int i, byte[] bArr, byte[] bArr2, SecureRandom secureRandom) {
        this.f4222j = i == 2 || i == 4;
        if (this.f4221i == 0) {
            if (bArr2 != null) {
                throw new InvalidAlgorithmParameterException("ECB mode cannot use IV");
            }
        } else if (bArr2 == null) {
            if (this.f4222j) {
                throw new InvalidAlgorithmParameterException("Parameters missing");
            }
            if (secureRandom == null) {
                secureRandom = C2396c.f4208a;
            }
            bArr2 = new byte[this.f4214b];
            secureRandom.nextBytes(bArr2);
        }
        this.f4216d = 0;
        this.f4218f = this.f4214b;
        this.f4220h.mo20272a(this.f4222j, "", bArr, bArr2);
    }

    /* renamed from: a */
    public byte[] m20276a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = null;
        try {
            byte[] bArr3 = new byte[m20278a(i2)];
            int m20275a = m20275a(bArr, i, i2, bArr3, 0);
            if (m20275a < bArr3.length) {
                bArr2 = new byte[m20275a];
                if (m20275a != 0) {
                    System.arraycopy(bArr3, 0, bArr2, 0, m20275a);
                    return bArr2;
                }
                return bArr2;
            }
            return bArr3;
        } catch (ShortBufferException unused) {
            return bArr2;
        }
    }
}
