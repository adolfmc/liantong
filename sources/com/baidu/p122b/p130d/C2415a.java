package com.baidu.p122b.p130d;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p038j0.C0548c;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.d.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2415a {

    /* renamed from: a */
    private static final byte[] f4261a = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, C0548c.f1784h, 55, 56, 57, 43, 47};

    /* renamed from: a */
    public static byte[] m20212a(byte[] bArr) {
        return m20211a(bArr, bArr.length);
    }

    /* renamed from: a */
    public static byte[] m20211a(byte[] bArr, int i) {
        byte b;
        int i2;
        int i3;
        int i4 = (i / 4) * 3;
        if (i4 == 0) {
            return new byte[0];
        }
        byte[] bArr2 = new byte[i4];
        int i5 = i;
        int i6 = 0;
        while (true) {
            byte b2 = bArr[i5 - 1];
            b = 10;
            if (b2 != 10 && b2 != 13 && b2 != 32 && b2 != 9) {
                if (b2 != 61) {
                    break;
                }
                i6++;
            }
            i5--;
        }
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        while (i7 < i5) {
            byte b3 = bArr[i7];
            if (b3 != b && b3 != 13 && b3 != 32 && b3 != 9) {
                if (b3 >= 65 && b3 <= 90) {
                    i2 = b3 - 65;
                } else if (b3 >= 97 && b3 <= 122) {
                    i2 = b3 - 71;
                } else if (b3 >= 48 && b3 <= 57) {
                    i2 = b3 + 4;
                } else if (b3 == 43) {
                    i2 = 62;
                } else if (b3 != 47) {
                    return null;
                } else {
                    i2 = 63;
                }
                int i11 = (i8 << 6) | ((byte) i2);
                if (i10 % 4 == 3) {
                    int i12 = i9 + 1;
                    bArr2[i9] = (byte) ((16711680 & i11) >> 16);
                    int i13 = i12 + 1;
                    bArr2[i12] = (byte) ((65280 & i11) >> 8);
                    i3 = i13 + 1;
                    bArr2[i13] = (byte) (i11 & 255);
                } else {
                    i3 = i9;
                }
                i10++;
                i9 = i3;
                i8 = i11;
            }
            i7++;
            b = 10;
        }
        if (i6 > 0) {
            int i14 = i8 << (i6 * 6);
            int i15 = i9 + 1;
            bArr2[i9] = (byte) ((i14 & 16711680) >> 16);
            if (i6 == 1) {
                i9 = i15 + 1;
                bArr2[i15] = (byte) ((i14 & 65280) >> 8);
            } else {
                i9 = i15;
            }
        }
        byte[] bArr3 = new byte[i9];
        System.arraycopy(bArr2, 0, bArr3, 0, i9);
        return bArr3;
    }
}
