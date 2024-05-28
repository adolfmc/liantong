package org.bouncycastle.pqc.crypto.newhope;

import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class ErrorCorrection {
    ErrorCorrection() {
    }

    static short LDDecode(int i, int i2, int i3, int i4) {
        return (short) (((((m252g(i) + m252g(i2)) + m252g(i3)) + m252g(i4)) - 98312) >>> 31);
    }

    static int abs(int i) {
        int i2 = i >> 31;
        return (i ^ i2) - i2;
    }

    /* renamed from: f */
    static int m253f(int[] iArr, int i, int i2, int i3) {
        int i4 = (i3 * 2730) >> 25;
        int i5 = i4 - ((12288 - (i3 - (i4 * 12289))) >> 31);
        iArr[i] = (i5 >> 1) + (i5 & 1);
        int i6 = i5 - 1;
        iArr[i2] = (i6 >> 1) + (i6 & 1);
        return abs(i3 - ((iArr[i] * 2) * 12289));
    }

    /* renamed from: g */
    static int m252g(int i) {
        int i2 = (i * 2730) >> 27;
        int i3 = i2 - ((49155 - (i - (49156 * i2))) >> 31);
        return abs((((i3 >> 1) + (i3 & 1)) * 98312) - i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void helpRec(short[] sArr, short[] sArr2, byte[] bArr, byte b) {
        short s = 8;
        byte[] bArr2 = new byte[8];
        bArr2[0] = b;
        byte[] bArr3 = new byte[32];
        ChaCha20.process(bArr, bArr2, bArr3, 0, bArr3.length);
        int[] iArr = new int[8];
        int[] iArr2 = new int[4];
        int i = 0;
        while (i < 256) {
            int i2 = i + 0;
            int i3 = ((bArr3[i >>> 3] >>> (i & 7)) & 1) * 4;
            int i4 = i + 256;
            int i5 = i + 512;
            int i6 = i + 768;
            int m253f = (24577 - (((m253f(iArr, 0, 4, (sArr2[i2] * s) + i3) + m253f(iArr, 1, 5, (sArr2[i4] * s) + i3)) + m253f(iArr, 2, 6, (sArr2[i5] * s) + i3)) + m253f(iArr, 3, 7, (sArr2[i6] * 8) + i3))) >> 31;
            int i7 = ~m253f;
            iArr2[0] = (i7 & iArr[0]) ^ (m253f & iArr[4]);
            iArr2[1] = (i7 & iArr[1]) ^ (m253f & iArr[5]);
            iArr2[2] = (i7 & iArr[2]) ^ (m253f & iArr[6]);
            iArr2[3] = (iArr[7] & m253f) ^ (i7 & iArr[3]);
            sArr[i2] = (short) ((iArr2[0] - iArr2[3]) & 3);
            sArr[i4] = (short) ((iArr2[1] - iArr2[3]) & 3);
            sArr[i5] = (short) ((iArr2[2] - iArr2[3]) & 3);
            sArr[i6] = (short) (((-m253f) + (iArr2[3] * 2)) & 3);
            i++;
            s = 8;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void rec(byte[] bArr, short[] sArr, short[] sArr2) {
        Arrays.fill(bArr, (byte) 0);
        int[] iArr = new int[4];
        for (int i = 0; i < 256; i++) {
            int i2 = i + 0;
            int i3 = i + 768;
            iArr[0] = ((sArr[i2] * 8) + 196624) - (((sArr2[i2] * 2) + sArr2[i3]) * 12289);
            int i4 = i + 256;
            iArr[1] = ((sArr[i4] * 8) + 196624) - (((sArr2[i4] * 2) + sArr2[i3]) * 12289);
            int i5 = i + 512;
            iArr[2] = ((sArr[i5] * 8) + 196624) - (((sArr2[i5] * 2) + sArr2[i3]) * 12289);
            iArr[3] = ((sArr[i3] * 8) + 196624) - (sArr2[i3] * 12289);
            int i6 = i >>> 3;
            bArr[i6] = (byte) ((LDDecode(iArr[0], iArr[1], iArr[2], iArr[3]) << (i & 7)) | bArr[i6]);
        }
    }
}
