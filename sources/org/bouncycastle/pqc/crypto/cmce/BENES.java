package org.bouncycastle.pqc.crypto.cmce;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
abstract class BENES {
    protected final int GFBITS;
    protected final int SYS_N;
    protected final int SYS_T;

    public BENES(int i, int i2, int i3) {
        this.SYS_N = i;
        this.SYS_T = i2;
        this.GFBITS = i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void transpose_64x64(long[] jArr, long[] jArr2) {
        long[][] jArr3 = {new long[]{6148914691236517205L, -6148914691236517206L}, new long[]{3689348814741910323L, -3689348814741910324L}, new long[]{1085102592571150095L, -1085102592571150096L}, new long[]{71777214294589695L, -71777214294589696L}, new long[]{281470681808895L, -281470681808896L}, new long[]{4294967295L, -4294967296L}};
        for (int i = 0; i < 64; i++) {
            jArr[i] = jArr2[i];
        }
        for (int i2 = 5; i2 >= 0; i2--) {
            int i3 = 1 << i2;
            for (int i4 = 0; i4 < 64; i4 += i3 * 2) {
                for (int i5 = i4; i5 < i4 + i3; i5++) {
                    int i6 = i5 + i3;
                    long j = (jArr[i5] & jArr3[i2][0]) | ((jArr[i6] & jArr3[i2][0]) << i3);
                    long j2 = ((jArr[i5] & jArr3[i2][1]) >>> i3) | (jArr[i6] & jArr3[i2][1]);
                    jArr[i5 + 0] = j;
                    jArr[i6] = j2;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void transpose_64x64(long[] jArr, long[] jArr2, int i) {
        long[][] jArr3 = {new long[]{6148914691236517205L, -6148914691236517206L}, new long[]{3689348814741910323L, -3689348814741910324L}, new long[]{1085102592571150095L, -1085102592571150096L}, new long[]{71777214294589695L, -71777214294589696L}, new long[]{281470681808895L, -281470681808896L}, new long[]{4294967295L, -4294967296L}};
        for (int i2 = 0; i2 < 64; i2++) {
            int i3 = i2 + i;
            jArr[i3] = jArr2[i3];
        }
        for (int i4 = 5; i4 >= 0; i4--) {
            int i5 = 1 << i4;
            for (int i6 = 0; i6 < 64; i6 += i5 * 2) {
                for (int i7 = i6; i7 < i6 + i5; i7++) {
                    int i8 = i7 + i;
                    int i9 = i7 + i5 + i;
                    jArr[i7 + 0 + i] = (jArr[i8] & jArr3[i4][0]) | ((jArr[i9] & jArr3[i4][0]) << i5);
                    jArr[i9] = ((jArr[i8] & jArr3[i4][1]) >>> i5) | (jArr[i9] & jArr3[i4][1]);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void support_gen(short[] sArr, byte[] bArr);
}
