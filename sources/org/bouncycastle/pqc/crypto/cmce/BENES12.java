package org.bouncycastle.pqc.crypto.cmce;

import java.lang.reflect.Array;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class BENES12 extends BENES {
    public BENES12(int i, int i2, int i3) {
        super(i, i2, i3);
    }

    private void apply_benes(byte[] bArr, byte[] bArr2, int i) {
        int i2;
        int i3;
        int i4;
        long[] jArr = new long[64];
        long[] jArr2 = new long[64];
        for (int i5 = 0; i5 < 64; i5++) {
            jArr[i5] = Utils.load8(bArr, i5 * 8);
        }
        if (i == 0) {
            i3 = (this.SYS_T * 2) + 40;
            i2 = 256;
        } else {
            i2 = -256;
            i3 = (this.SYS_T * 2) + 40 + (((this.GFBITS * 2) - 2) * 256);
        }
        transpose_64x64(jArr, jArr);
        int i6 = i3;
        int i7 = 0;
        while (true) {
            if (i7 > 5) {
                break;
            }
            for (int i8 = 0; i8 < 64; i8++) {
                jArr2[i8] = Utils.load4(bArr2, (i8 * 4) + i6);
            }
            transpose_64x64(jArr2, jArr2);
            layerBenes(jArr, jArr2, i7);
            i6 += i2;
            i7++;
        }
        transpose_64x64(jArr, jArr);
        for (int i9 = 0; i9 <= 5; i9++) {
            for (int i10 = 0; i10 < 32; i10++) {
                jArr2[i10] = Utils.load8(bArr2, (i10 * 8) + i6);
            }
            layerBenes(jArr, jArr2, i9);
            i6 += i2;
        }
        for (int i11 = 4; i11 >= 0; i11--) {
            for (int i12 = 0; i12 < 32; i12++) {
                jArr2[i12] = Utils.load8(bArr2, (i12 * 8) + i6);
            }
            layerBenes(jArr, jArr2, i11);
            i6 += i2;
        }
        transpose_64x64(jArr, jArr);
        for (i4 = 5; i4 >= 0; i4--) {
            for (int i13 = 0; i13 < 64; i13++) {
                jArr2[i13] = Utils.load4(bArr2, (i13 * 4) + i6);
            }
            transpose_64x64(jArr2, jArr2);
            layerBenes(jArr, jArr2, i4);
            i6 += i2;
        }
        transpose_64x64(jArr, jArr);
        for (int i14 = 0; i14 < 64; i14++) {
            Utils.store8(bArr, i14 * 8, jArr[i14]);
        }
    }

    static void layerBenes(long[] jArr, long[] jArr2, int i) {
        int i2 = 1 << i;
        int i3 = 0;
        int i4 = 0;
        while (i3 < 64) {
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
