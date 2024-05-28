package org.bouncycastle.pqc.crypto.hqc;

import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class ReedMuller {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Codeword {
        int[] type32 = new int[4];
        int[] type8 = new int[16];
    }

    ReedMuller() {
    }

    private static int Bit0Mask(int i) {
        return (-(i & 1)) & (-1);
    }

    public static void decode(long[] jArr, long[] jArr2, int i, int i2) {
        byte[] bArr = new byte[i];
        Utils.fromLongArrayToByteArray(bArr, jArr, i * 8);
        Codeword[] codewordArr = new Codeword[jArr2.length / 2];
        int[] iArr = new int[jArr2.length * 2];
        Utils.fromLongArrayToByte32Array(iArr, jArr2);
        for (int i3 = 0; i3 < codewordArr.length; i3++) {
            codewordArr[i3] = new Codeword();
            for (int i4 = 0; i4 < 4; i4++) {
                codewordArr[i3].type32[i4] = iArr[(i3 * 4) + i4];
            }
        }
        int[] iArr2 = new int[128];
        for (int i5 = 0; i5 < i; i5++) {
            expandThenSum(iArr2, codewordArr, i5 * i2, i2);
            int[] iArr3 = new int[128];
            hadamardTransform(iArr2, iArr3);
            iArr3[0] = iArr3[0] - (i2 * 64);
            findPeaks(iArr3);
            bArr[i5] = (byte) findPeaks(iArr3);
        }
        int[] iArr4 = new int[codewordArr.length * 4];
        int i6 = 0;
        for (int i7 = 0; i7 < codewordArr.length; i7++) {
            System.arraycopy(codewordArr[i7].type32, 0, iArr4, i6, codewordArr[i7].type32.length);
            i6 += 4;
        }
        Utils.fromByte32ArrayToLongArray(jArr2, iArr4);
        Utils.fromByteArrayToLongArray(jArr, bArr);
    }

    public static void encode(long[] jArr, long[] jArr2, int i, int i2) {
        byte[] bArr = new byte[i];
        Utils.fromLongArrayToByteArray(bArr, jArr2, i * 8);
        Codeword[] codewordArr = new Codeword[i * i2];
        for (int i3 = 0; i3 < codewordArr.length; i3++) {
            codewordArr[i3] = new Codeword();
        }
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = i4 * i2;
            encodeSub(codewordArr[i5], bArr[i4]);
            for (int i6 = 1; i6 < i2; i6++) {
                codewordArr[i5 + i6] = codewordArr[i5];
            }
        }
        int[] iArr = new int[codewordArr.length * 4];
        int i7 = 0;
        for (int i8 = 0; i8 < codewordArr.length; i8++) {
            System.arraycopy(codewordArr[i8].type32, 0, iArr, i7, codewordArr[i8].type32.length);
            i7 += 4;
        }
        Utils.fromByte32ArrayToLongArray(jArr, iArr);
    }

    static void encodeSub(Codeword codeword, int i) {
        int Bit0Mask = ((((Bit0Mask(i >> 7) ^ (Bit0Mask(i >> 0) & (-1431655766))) ^ (Bit0Mask(i >> 1) & (-858993460))) ^ (Bit0Mask(i >> 2) & (-252645136))) ^ (Bit0Mask(i >> 3) & (-16711936))) ^ (Bit0Mask(i >> 4) & (-65536));
        codeword.type32[0] = Bit0Mask;
        int i2 = i >> 5;
        int Bit0Mask2 = Bit0Mask ^ Bit0Mask(i2);
        codeword.type32[1] = Bit0Mask2;
        int Bit0Mask3 = Bit0Mask(i >> 6) ^ Bit0Mask2;
        codeword.type32[3] = Bit0Mask3;
        codeword.type32[2] = Bit0Mask3 ^ Bit0Mask(i2);
    }

    private static void expandThenSum(int[] iArr, Codeword[] codewordArr, int i, int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            for (int i4 = 0; i4 < 32; i4++) {
                int i5 = i + 0;
                int i6 = codewordArr[i5].type32[i3];
                iArr[(i3 * 32) + i4] = (codewordArr[i5].type32[i3] >> i4) & 1;
            }
        }
        for (int i7 = 1; i7 < i2; i7++) {
            for (int i8 = 0; i8 < 4; i8++) {
                for (int i9 = 0; i9 < 32; i9++) {
                    int i10 = (i8 * 32) + i9;
                    iArr[i10] = iArr[i10] + ((codewordArr[i7 + i].type32[i8] >> i9) & 1);
                }
            }
        }
    }

    private static int findPeaks(int[] iArr) {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < 128; i4++) {
            int i5 = iArr[i4];
            int i6 = i5 > 0 ? -1 : 0;
            int i7 = ((~i6) & (-i5)) | (i6 & i5);
            if (i7 > i3) {
                i = i5;
            }
            if (i7 > i3) {
                i2 = i4;
            }
            if (i7 > i3) {
                i3 = i7;
            }
        }
        return i2 | ((i > 0 ? 1 : 0) * 128);
    }

    private static void hadamardTransform(int[] iArr, int[] iArr2) {
        int[] clone = Arrays.clone(iArr);
        int[] clone2 = Arrays.clone(iArr2);
        int[] iArr3 = clone;
        int i = 0;
        while (i < 7) {
            for (int i2 = 0; i2 < 64; i2++) {
                int i3 = i2 * 2;
                int i4 = i3 + 1;
                clone2[i2] = iArr3[i3] + iArr3[i4];
                clone2[i2 + 64] = iArr3[i3] - iArr3[i4];
            }
            int[] clone3 = Arrays.clone(iArr3);
            int[] clone4 = Arrays.clone(clone2);
            int[] clone5 = Arrays.clone(clone3);
            i++;
            iArr3 = clone4;
            clone2 = clone5;
        }
        System.arraycopy(clone2, 0, iArr, 0, iArr.length);
        System.arraycopy(iArr3, 0, iArr2, 0, iArr2.length);
    }
}
