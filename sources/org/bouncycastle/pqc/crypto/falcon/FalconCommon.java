package org.bouncycastle.pqc.crypto.falcon;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class FalconCommon {
    static final int[] l2bound = {0, 101498, 208714, 428865, 892039, 1852696, 3842630, 7959734, 16468416, 34034726, 70265242};

    void hash_to_point_ct(SHAKE256 shake256, short[] sArr, int i, int i2, short[] sArr2, int i3) {
        short s;
        int i4;
        boolean z;
        int i5;
        boolean z2;
        short s2;
        short[] sArr3 = new short[63];
        int i6 = 1 << i2;
        int i7 = i6 << 1;
        short s3 = new short[]{0, 65, 67, 71, 77, 86, 100, 122, 154, 205, 287}[i2];
        int i8 = i6 + s3;
        int i9 = 0;
        for (int i10 = 0; i10 < i8; i10++) {
            byte[] bArr = new byte[2];
            shake256.inner_shake256_extract(bArr, 0, bArr.length);
            int i11 = (bArr[1] & 255) | ((bArr[0] & 255) << 8);
            int i12 = i11 - ((((i11 - 24578) >>> 31) - 1) & 24578);
            int i13 = i12 - ((((i12 - 24578) >>> 31) - 1) & 24578);
            int i14 = (((i11 - 61445) >>> 31) - 1) | (i13 - ((((i13 - 12289) >>> 31) - 1) & 12289));
            if (i10 < i6) {
                sArr[i + i10] = (short) i14;
            } else if (i10 < i7) {
                sArr2[(i3 + i10) - i6] = (short) i14;
            } else {
                sArr3[i10 - i7] = (short) i14;
            }
        }
        int i15 = 1;
        while (i15 <= s3) {
            int i16 = i9;
            int i17 = i16;
            while (i16 < i8) {
                if (i16 < i6) {
                    int i18 = i + i16;
                    s = sArr[i18];
                    i4 = i18;
                    z = true;
                } else if (i16 < i7) {
                    int i19 = (i3 + i16) - i6;
                    s = sArr2[i19];
                    i4 = i19;
                    z = true;
                } else {
                    int i20 = i16 - i7;
                    s = sArr3[i20];
                    i4 = i20;
                    z = true;
                }
                int i21 = i16 - i17;
                int i22 = (s >>> 15) - 1;
                i17 -= i22;
                if (i16 >= i15) {
                    int i23 = i16 - i15;
                    if (i23 < i6) {
                        int i24 = (i + i16) - i15;
                        s2 = sArr[i24];
                        i5 = i24;
                        z2 = true;
                    } else if (i23 < i7) {
                        int i25 = (i3 + i23) - i6;
                        s2 = sArr2[i25];
                        i5 = i25;
                        z2 = true;
                    } else {
                        int i26 = i23 - i7;
                        short s4 = sArr3[i26];
                        i5 = i26;
                        z2 = true;
                        s2 = s4;
                    }
                    int i27 = i22 & (-(((i21 & i15) + 511) >> 9));
                    if (z) {
                        sArr[i4] = (short) (((s ^ s2) & i27) ^ s);
                    } else if (z) {
                        sArr2[i4] = (short) (((s ^ s2) & i27) ^ s);
                    } else {
                        sArr3[i4] = (short) (((s ^ s2) & i27) ^ s);
                    }
                    if (z2) {
                        sArr[i5] = (short) (((s ^ s2) & i27) ^ s2);
                    } else if (z2) {
                        sArr2[i5] = (short) (((s ^ s2) & i27) ^ s2);
                    } else {
                        sArr3[i5] = (short) (((s ^ s2) & i27) ^ s2);
                    }
                }
                i16++;
            }
            i15 <<= 1;
            i9 = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void hash_to_point_vartime(SHAKE256 shake256, short[] sArr, int i, int i2) {
        int i3 = 1 << i2;
        while (i3 > 0) {
            byte[] bArr = new byte[2];
            shake256.inner_shake256_extract(bArr, 0, 2);
            int i4 = ((bArr[0] & 255) << 8) | (bArr[1] & 255);
            if (i4 < 61445) {
                while (i4 >= 12289) {
                    i4 -= 12289;
                }
                sArr[i] = (short) i4;
                i3--;
                i++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int is_short(short[] sArr, int i, short[] sArr2, int i2, int i3) {
        int i4 = 1 << i3;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < i4; i7++) {
            short s = sArr[i + i7];
            int i8 = i5 + (s * s);
            int i9 = i6 | i8;
            short s2 = sArr2[i2 + i7];
            i5 = i8 + (s2 * s2);
            i6 = i9 | i5;
        }
        return (((long) ((-(i6 >>> 31)) | i5)) & 4294967295L) <= ((long) l2bound[i3]) ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int is_short_half(int i, short[] sArr, int i2, int i3) {
        int i4 = 1 << i3;
        int i5 = -(i >>> 31);
        int i6 = i;
        for (int i7 = 0; i7 < i4; i7++) {
            short s = sArr[i2 + i7];
            i6 += s * s;
            i5 |= i6;
        }
        return (((long) ((-(i5 >>> 31)) | i6)) & 4294967295L) <= ((long) l2bound[i3]) ? 1 : 0;
    }
}
