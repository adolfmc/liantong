package org.p415a.p427d.p431d;

import org.p415a.p427d.C12704h;
import org.p415a.p427d.C12730o;
import org.p415a.p427d.InterfaceC12680d;
import org.p415a.p427d.InterfaceC12696f;
import org.p415a.p427d.p435h.C12719o;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.d.h */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12688h implements InterfaceC12680d {

    /* renamed from: a */
    private int[] f25837a = null;

    /* renamed from: a */
    private int m1407a(int i, int i2) {
        int i3;
        if (i == 0) {
            i3 = 65537 - i2;
        } else if (i2 == 0) {
            i3 = 65537 - i;
        } else {
            int i4 = i * i2;
            int i5 = i4 & 65535;
            int i6 = i4 >>> 16;
            i3 = (i5 - i6) + (i5 < i6 ? 1 : 0);
        }
        return i3 & 65535;
    }

    /* renamed from: a */
    private int m1403a(byte[] bArr, int i) {
        return ((bArr[i] << 8) & 65280) + (bArr[i + 1] & 255);
    }

    /* renamed from: a */
    private void m1406a(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >>> 8);
        bArr[i2 + 1] = (byte) i;
    }

    /* renamed from: a */
    private void m1401a(int[] iArr, byte[] bArr, int i, byte[] bArr2, int i2) {
        int m1403a = m1403a(bArr, i);
        int m1403a2 = m1403a(bArr, i + 2);
        int m1403a3 = m1403a(bArr, i + 4);
        int m1403a4 = m1403a(bArr, i + 6);
        int i3 = 0;
        int i4 = m1403a4;
        int i5 = 0;
        while (i3 < 8) {
            int i6 = i5 + 1;
            int m1407a = m1407a(m1403a, iArr[i5]);
            int i7 = i6 + 1;
            int i8 = (m1403a2 + iArr[i6]) & 65535;
            int i9 = i7 + 1;
            int i10 = (m1403a3 + iArr[i7]) & 65535;
            int i11 = i9 + 1;
            int m1407a2 = m1407a(i4, iArr[i9]);
            int i12 = i11 + 1;
            int m1407a3 = m1407a(i10 ^ m1407a, iArr[i11]);
            int m1407a4 = m1407a(((i8 ^ m1407a2) + m1407a3) & 65535, iArr[i12]);
            int i13 = (m1407a3 + m1407a4) & 65535;
            i4 = m1407a2 ^ i13;
            m1403a3 = i13 ^ i8;
            i3++;
            m1403a2 = i10 ^ m1407a4;
            m1403a = m1407a ^ m1407a4;
            i5 = i12 + 1;
        }
        int i14 = i5 + 1;
        m1406a(m1407a(m1403a, iArr[i5]), bArr2, i2);
        int i15 = i14 + 1;
        m1406a(m1403a3 + iArr[i14], bArr2, i2 + 2);
        m1406a(m1403a2 + iArr[i15], bArr2, i2 + 4);
        m1406a(m1407a(i4, iArr[i15 + 1]), bArr2, i2 + 6);
    }

    /* renamed from: a */
    private int[] m1405a(boolean z, byte[] bArr) {
        return z ? m1404a(bArr) : m1402a(m1404a(bArr));
    }

    /* renamed from: a */
    private int[] m1404a(byte[] bArr) {
        int i;
        int[] iArr = new int[52];
        int i2 = 0;
        if (bArr.length < 16) {
            byte[] bArr2 = new byte[16];
            System.arraycopy(bArr, 0, bArr2, bArr2.length - bArr.length, bArr.length);
            bArr = bArr2;
        }
        while (true) {
            if (i2 >= 8) {
                break;
            }
            iArr[i2] = m1403a(bArr, i2 * 2);
            i2++;
        }
        for (i = 8; i < 52; i++) {
            int i3 = i & 7;
            if (i3 < 6) {
                iArr[i] = (((iArr[i - 7] & 127) << 9) | (iArr[i - 6] >> 7)) & 65535;
            } else if (i3 == 6) {
                iArr[i] = (((iArr[i - 7] & 127) << 9) | (iArr[i - 14] >> 7)) & 65535;
            } else {
                iArr[i] = (((iArr[i - 15] & 127) << 9) | (iArr[i - 14] >> 7)) & 65535;
            }
        }
        return iArr;
    }

    /* renamed from: a */
    private int[] m1402a(int[] iArr) {
        int[] iArr2 = new int[52];
        int m1400b = m1400b(iArr[0]);
        int i = 1;
        int m1408a = m1408a(iArr[1]);
        int m1408a2 = m1408a(iArr[2]);
        iArr2[51] = m1400b(iArr[3]);
        iArr2[50] = m1408a2;
        iArr2[49] = m1408a;
        int i2 = 48;
        iArr2[48] = m1400b;
        int i3 = 4;
        while (i < 8) {
            int i4 = i3 + 1;
            int i5 = iArr[i3];
            int i6 = i4 + 1;
            int i7 = i2 - 1;
            iArr2[i7] = iArr[i4];
            int i8 = i7 - 1;
            iArr2[i8] = i5;
            int i9 = i6 + 1;
            int m1400b2 = m1400b(iArr[i6]);
            int i10 = i9 + 1;
            int m1408a3 = m1408a(iArr[i9]);
            int i11 = i10 + 1;
            int m1408a4 = m1408a(iArr[i10]);
            int i12 = i8 - 1;
            iArr2[i12] = m1400b(iArr[i11]);
            int i13 = i12 - 1;
            iArr2[i13] = m1408a3;
            int i14 = i13 - 1;
            iArr2[i14] = m1408a4;
            i2 = i14 - 1;
            iArr2[i2] = m1400b2;
            i++;
            i3 = i11 + 1;
        }
        int i15 = i3 + 1;
        int i16 = iArr[i3];
        int i17 = i15 + 1;
        int i18 = i2 - 1;
        iArr2[i18] = iArr[i15];
        int i19 = i18 - 1;
        iArr2[i19] = i16;
        int i20 = i17 + 1;
        int m1400b3 = m1400b(iArr[i17]);
        int i21 = i20 + 1;
        int m1408a5 = m1408a(iArr[i20]);
        int i22 = i21 + 1;
        int m1408a6 = m1408a(iArr[i21]);
        int i23 = i19 - 1;
        iArr2[i23] = m1400b(iArr[i22]);
        int i24 = i23 - 1;
        iArr2[i24] = m1408a6;
        int i25 = i24 - 1;
        iArr2[i25] = m1408a5;
        iArr2[i25 - 1] = m1400b3;
        return iArr2;
    }

    /* renamed from: b */
    private int m1400b(int i) {
        if (i < 2) {
            return i;
        }
        int i2 = 65537 / i;
        int i3 = 65537 % i;
        int i4 = 1;
        while (i3 != 1) {
            int i5 = i / i3;
            i %= i3;
            i4 = (i4 + (i5 * i2)) & 65535;
            if (i == 1) {
                return i4;
            }
            int i6 = i3 / i;
            i3 %= i;
            i2 = (i2 + (i6 * i4)) & 65535;
        }
        return (1 - i2) & 65535;
    }

    /* renamed from: a */
    int m1408a(int i) {
        return (0 - i) & 65535;
    }

    @Override // org.p415a.p427d.InterfaceC12680d
    /* renamed from: a */
    public int mo1350a(byte[] bArr, int i, byte[] bArr2, int i2) {
        int[] iArr = this.f25837a;
        if (iArr != null) {
            if (i + 8 <= bArr.length) {
                if (i2 + 8 <= bArr2.length) {
                    m1401a(iArr, bArr, i, bArr2, i2);
                    return 8;
                }
                throw new C12730o("output buffer too short");
            }
            throw new C12704h("input buffer too short");
        }
        throw new IllegalStateException("IDEA engine not initialised");
    }

    @Override // org.p415a.p427d.InterfaceC12680d
    /* renamed from: a */
    public String mo1353a() {
        return "IDEA";
    }

    @Override // org.p415a.p427d.InterfaceC12680d
    /* renamed from: a */
    public void mo1351a(boolean z, InterfaceC12696f interfaceC12696f) {
        if (interfaceC12696f instanceof C12719o) {
            this.f25837a = m1405a(z, ((C12719o) interfaceC12696f).m1328a());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to IDEA init - " + interfaceC12696f.getClass().getName());
    }

    @Override // org.p415a.p427d.InterfaceC12680d
    /* renamed from: b */
    public int mo1349b() {
        return 8;
    }

    @Override // org.p415a.p427d.InterfaceC12680d
    /* renamed from: c */
    public void mo1347c() {
    }
}
