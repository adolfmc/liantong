package org.bouncycastle.pqc.crypto.picnic;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class Utils {
    Utils() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int ceil_log2(int i) {
        if (i == 0) {
            return 0;
        }
        return 32 - nlz(i - 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static byte getBit(byte[] bArr, int i) {
        return (byte) ((bArr[i >>> 3] >>> ((i & 7) ^ 7)) & 1);
    }

    protected static int getBit(int[] iArr, int i) {
        return (iArr[i >>> 5] >> ((i & 31) ^ 7)) & 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int getBitFromWordArray(int[] iArr, int i) {
        return getBit(iArr, i);
    }

    private static int nlz(int i) {
        if (i == 0) {
            return 32;
        }
        int i2 = 1;
        if ((i >>> 16) == 0) {
            i2 = 17;
            i <<= 16;
        }
        if ((i >>> 24) == 0) {
            i2 += 8;
            i <<= 8;
        }
        if ((i >>> 28) == 0) {
            i2 += 4;
            i <<= 4;
        }
        if ((i >>> 30) == 0) {
            i2 += 2;
            i <<= 2;
        }
        return i2 - (i >>> 31);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int numBytes(int i) {
        if (i == 0) {
            return 0;
        }
        return ((i - 1) / 8) + 1;
    }

    protected static int parity(byte[] bArr, int i) {
        byte b = bArr[0];
        for (int i2 = 1; i2 < i; i2++) {
            b = (byte) (b ^ bArr[i2]);
        }
        int i3 = (b >>> 1) ^ b;
        int i4 = i3 ^ (i3 >>> 2);
        int i5 = i4 ^ (i4 >>> 4);
        int i6 = i5 ^ (i5 >>> 8);
        return (i6 ^ (i6 >>> 16)) & 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int parity16(int i) {
        int i2 = i ^ (i >>> 1);
        int i3 = i2 ^ (i2 >>> 2);
        int i4 = i3 ^ (i3 >>> 4);
        return (i4 ^ (i4 >>> 8)) & 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int parity32(int i) {
        int i2 = i ^ (i >>> 1);
        int i3 = i2 ^ (i2 >>> 2);
        int i4 = i3 ^ (i3 >>> 4);
        int i5 = i4 ^ (i4 >>> 8);
        return (i5 ^ (i5 >>> 16)) & 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void setBit(byte[] bArr, int i, byte b) {
        int i2 = i >>> 3;
        int i3 = (i & 7) ^ 7;
        bArr[i2] = (byte) ((b << i3) | (bArr[i2] & (~(1 << i3))));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void setBit(int[] iArr, int i, int i2) {
        int i3 = i >>> 5;
        int i4 = (i & 31) ^ 7;
        iArr[i3] = (i2 << i4) | (iArr[i3] & (~(1 << i4)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void setBitInWordArray(int[] iArr, int i, int i2) {
        setBit(iArr, i, i2);
    }
}
