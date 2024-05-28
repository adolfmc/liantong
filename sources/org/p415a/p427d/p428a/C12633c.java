package org.p415a.p427d.p428a;

import org.p415a.p427d.InterfaceC12726k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p038j0.C0548c;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.a.c */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12633c implements InterfaceC12726k {

    /* renamed from: g */
    private static final byte[] f25653g = {41, 46, 67, -55, -94, -40, 124, 1, 61, C0548c.f1784h, 84, -95, -20, -16, 6, 19, 98, -89, 5, -13, -64, -57, 115, -116, -104, -109, 43, -39, -68, 76, -126, -54, 30, -101, 87, 60, -3, -44, -32, 22, 103, 66, 111, 24, -118, 23, -27, 18, -66, 78, -60, -42, -38, -98, -34, 73, -96, -5, -11, -114, -69, 47, -18, 122, -87, 104, 121, -111, 21, -78, 7, 63, -108, -62, 16, -119, 11, 34, 95, 33, Byte.MIN_VALUE, Byte.MAX_VALUE, 93, -102, 90, -112, 50, 39, 53, 62, -52, -25, -65, -9, -105, 3, -1, 25, 48, -77, 72, -91, -75, -47, -41, 94, -110, 42, -84, 86, -86, -58, 79, -72, 56, -46, -106, -92, 125, -74, 118, -4, 107, -30, -100, 116, 4, -15, 69, -99, 112, 89, 100, 113, -121, 32, -122, 91, -49, 101, -26, 45, -88, 2, 27, 96, 37, -83, -82, -80, -71, -10, 28, 70, 97, 105, 52, 64, 126, 15, 85, 71, -93, 35, -35, 81, -81, 58, -61, C0548c.f1785i, -7, -50, -70, -59, -22, 38, 44, 83, 13, 110, -123, 40, -124, 9, -45, -33, -51, -12, 65, -127, 77, 82, 106, -36, 55, -56, 108, -63, -85, -6, 36, -31, 123, 8, 12, -67, -79, 74, 120, -120, -107, -117, -29, 99, -24, 109, -23, -53, -43, -2, 59, 0, 29, 57, -14, -17, -73, 14, 102, 88, -48, -28, -90, 119, 114, -8, -21, 117, 75, 10, 49, 68, 80, -76, -113, -19, 31, 26, -37, -103, -115, 51, -97, 17, -125, 20};

    /* renamed from: b */
    private int f25655b;

    /* renamed from: d */
    private int f25657d;

    /* renamed from: f */
    private int f25659f;

    /* renamed from: a */
    private byte[] f25654a = new byte[48];

    /* renamed from: c */
    private byte[] f25656c = new byte[16];

    /* renamed from: e */
    private byte[] f25658e = new byte[16];

    public C12633c() {
        m1514b();
    }

    @Override // org.p415a.p427d.InterfaceC12724i
    /* renamed from: a */
    public int mo1314a() {
        return 16;
    }

    @Override // org.p415a.p427d.InterfaceC12724i
    /* renamed from: a */
    public int mo1312a(byte[] bArr, int i) {
        int length = this.f25656c.length;
        int i2 = this.f25657d;
        byte b = (byte) (length - i2);
        while (true) {
            byte[] bArr2 = this.f25656c;
            if (i2 >= bArr2.length) {
                m1515a(bArr2);
                m1513b(this.f25656c);
                m1513b(this.f25658e);
                System.arraycopy(this.f25654a, this.f25655b, bArr, i, 16);
                m1514b();
                return 16;
            }
            bArr2[i2] = b;
            i2++;
        }
    }

    @Override // org.p415a.p427d.InterfaceC12724i
    /* renamed from: a */
    public void mo1313a(byte b) {
        byte[] bArr = this.f25656c;
        int i = this.f25657d;
        this.f25657d = i + 1;
        bArr[i] = b;
        if (this.f25657d == 16) {
            m1515a(bArr);
            m1513b(this.f25656c);
            this.f25657d = 0;
        }
    }

    /* renamed from: a */
    protected void m1515a(byte[] bArr) {
        byte b = this.f25658e[15];
        for (int i = 0; i < 16; i++) {
            byte[] bArr2 = this.f25658e;
            bArr2[i] = (byte) (f25653g[(b ^ bArr[i]) & 255] ^ bArr2[i]);
            b = bArr2[i];
        }
    }

    @Override // org.p415a.p427d.InterfaceC12724i
    /* renamed from: a */
    public void mo1311a(byte[] bArr, int i, int i2) {
        while (this.f25657d != 0 && i2 > 0) {
            mo1313a(bArr[i]);
            i++;
            i2--;
        }
        while (i2 > 16) {
            System.arraycopy(bArr, i, this.f25656c, 0, 16);
            m1515a(this.f25656c);
            m1513b(this.f25656c);
            i2 -= 16;
            i += 16;
        }
        while (i2 > 0) {
            mo1313a(bArr[i]);
            i++;
            i2--;
        }
    }

    /* renamed from: b */
    public void m1514b() {
        this.f25655b = 0;
        int i = 0;
        while (true) {
            byte[] bArr = this.f25654a;
            if (i == bArr.length) {
                break;
            }
            bArr[i] = 0;
            i++;
        }
        this.f25657d = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr2 = this.f25656c;
            if (i2 == bArr2.length) {
                break;
            }
            bArr2[i2] = 0;
            i2++;
        }
        this.f25659f = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr3 = this.f25658e;
            if (i3 == bArr3.length) {
                return;
            }
            bArr3[i3] = 0;
            i3++;
        }
    }

    /* renamed from: b */
    protected void m1513b(byte[] bArr) {
        for (int i = 0; i < 16; i++) {
            byte[] bArr2 = this.f25654a;
            bArr2[i + 16] = bArr[i];
            bArr2[i + 32] = (byte) (bArr[i] ^ bArr2[i]);
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 18; i3++) {
            int i4 = i2;
            for (int i5 = 0; i5 < 48; i5++) {
                byte[] bArr3 = this.f25654a;
                byte b = (byte) (f25653g[i4] ^ bArr3[i5]);
                bArr3[i5] = b;
                i4 = b & 255;
            }
            i2 = (i4 + i3) % 256;
        }
    }
}
