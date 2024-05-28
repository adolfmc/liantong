package org.bouncycastle.pqc.crypto.cmce;

import java.lang.reflect.Array;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class BENES13 extends BENES {
    public BENES13(int i, int i2, int i3) {
        super(i, i2, i3);
    }

    static void layer_ex(long[] jArr, long[] jArr2, int i) {
        int i2 = 1 << i;
        int i3 = 0;
        int i4 = 0;
        while (i3 < 128) {
            int i5 = i4;
            int i6 = i3;
            while (i6 < i3 + i2) {
                int i7 = i6 + 0;
                int i8 = i6 + i2;
                long j = (jArr[i7] ^ jArr[i8]) & jArr2[i5];
                jArr[i7] = jArr[i7] ^ j;
                jArr[i8] = jArr[i8] ^ j;
                i6++;
                i5++;
            }
            i3 += i2 * 2;
            i4 = i5;
        }
    }

    static void layer_in(long[] jArr, long[] jArr2, int i) {
        int i2 = 1 << i;
        int i3 = 0;
        int i4 = 0;
        while (i3 < 64) {
            int i5 = i4;
            int i6 = i3;
            while (i6 < i3 + i2) {
                int i7 = i6 + 0;
                int i8 = i6 + i2;
                int i9 = i5 + 1;
                long j = (jArr[i7] ^ jArr[i8]) & jArr2[i5];
                jArr[i7] = jArr[i7] ^ j;
                jArr[i8] = jArr[i8] ^ j;
                int i10 = i6 + 64;
                int i11 = i10 + 0;
                int i12 = i10 + i2;
                long j2 = (jArr[i11] ^ jArr[i12]) & jArr2[i9];
                jArr[i11] = jArr[i11] ^ j2;
                jArr[i12] = jArr[i12] ^ j2;
                i6++;
                i5 = i9 + 1;
            }
            i3 += i2 * 2;
            i4 = i5;
        }
    }

    void apply_benes(byte[] bArr, byte[] bArr2, int i) {
        int i2;
        int i3;
        int i4;
        long[] jArr = new long[128];
        long[] jArr2 = new long[128];
        long[] jArr3 = new long[64];
        long[] jArr4 = new long[64];
        if (i == 0) {
            i2 = (this.SYS_T * 2) + 40;
            i3 = 0;
        } else {
            i2 = (this.SYS_T * 2) + 40 + 12288;
            i3 = -1024;
        }
        for (int i5 = 0; i5 < 64; i5++) {
            int i6 = (i5 * 16) + 0;
            jArr[i5 + 0] = Utils.load8(bArr, i6 + 0);
            jArr[i5 + 64] = Utils.load8(bArr, i6 + 8);
        }
        transpose_64x64(jArr2, jArr, 0);
        transpose_64x64(jArr2, jArr, 64);
        int i7 = i2;
        int i8 = 0;
        while (true) {
            if (i8 > 6) {
                break;
            }
            int i9 = i7;
            for (int i10 = 0; i10 < 64; i10++) {
                jArr3[i10] = Utils.load8(bArr2, i9);
                i9 += 8;
            }
            i7 = i9 + i3;
            transpose_64x64(jArr4, jArr3);
            layer_ex(jArr2, jArr4, i8);
            i8++;
        }
        transpose_64x64(jArr, jArr2, 0);
        transpose_64x64(jArr, jArr2, 64);
        for (int i11 = 0; i11 <= 5; i11++) {
            int i12 = i7;
            for (int i13 = 0; i13 < 64; i13++) {
                jArr3[i13] = Utils.load8(bArr2, i12);
                i12 += 8;
            }
            i7 = i12 + i3;
            layer_in(jArr, jArr3, i11);
        }
        for (int i14 = 4; i14 >= 0; i14--) {
            int i15 = i7;
            for (int i16 = 0; i16 < 64; i16++) {
                jArr3[i16] = Utils.load8(bArr2, i15);
                i15 += 8;
            }
            i7 = i15 + i3;
            layer_in(jArr, jArr3, i14);
        }
        transpose_64x64(jArr2, jArr, 0);
        transpose_64x64(jArr2, jArr, 64);
        for (i4 = 6; i4 >= 0; i4--) {
            for (int i17 = 0; i17 < 64; i17++) {
                jArr3[i17] = Utils.load8(bArr2, i7);
                i7 += 8;
            }
            i7 += i3;
            transpose_64x64(jArr4, jArr3);
            layer_ex(jArr2, jArr4, i4);
        }
        transpose_64x64(jArr, jArr2, 0);
        transpose_64x64(jArr, jArr2, 64);
        for (int i18 = 0; i18 < 64; i18++) {
            int i19 = (i18 * 16) + 0;
            Utils.store8(bArr, i19 + 0, jArr[i18 + 0]);
            Utils.store8(bArr, i19 + 8, jArr[i18 + 64]);
        }
    }

    @Override // org.bouncycastle.pqc.crypto.cmce.BENES
    public void support_gen(short[] sArr, byte[] bArr) {
        byte[][] bArr2 = (byte[][]) Array.newInstance(byte.class, this.GFBITS, (1 << this.GFBITS) / 8);
        for (int i = 0; i < this.GFBITS; i++) {
            for (int i2 = 0; i2 < (1 << this.GFBITS) / 8; i2++) {
                bArr2[i][i2] = 0;
            }
        }
        for (int i3 = 0; i3 < (1 << this.GFBITS); i3++) {
            short bitrev = Utils.bitrev((short) i3, this.GFBITS);
            for (int i4 = 0; i4 < this.GFBITS; i4++) {
                byte[] bArr3 = bArr2[i4];
                int i5 = i3 / 8;
                bArr3[i5] = (byte) (bArr3[i5] | (((bitrev >> i4) & 1) << (i3 % 8)));
            }
        }
        for (int i6 = 0; i6 < this.GFBITS; i6++) {
            apply_benes(bArr2[i6], bArr, 0);
        }
        for (int i7 = 0; i7 < this.SYS_N; i7++) {
            sArr[i7] = 0;
            for (int i8 = this.GFBITS - 1; i8 >= 0; i8--) {
                sArr[i7] = (short) (sArr[i7] << 1);
                sArr[i7] = (short) (sArr[i7] | ((bArr2[i8][i7 / 8] >> (i7 % 8)) & 1));
            }
        }
    }
}
