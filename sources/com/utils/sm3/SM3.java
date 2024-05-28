package com.utils.sm3;

import com.utils.p366ak.UtilByte;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class SM3 {

    /* renamed from: iv */
    public static final byte[] f20849iv = {115, Byte.MIN_VALUE, 22, 111, 73, 20, -78, -71, 23, 36, 66, -41, -38, -118, 6, 0, -87, 111, 48, -68, 22, 49, 56, -86, -29, -115, -18, 77, -80, -5, 14, 78};

    /* renamed from: Tj */
    public static int[] f20848Tj = new int[64];

    private static int FF1j(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private static int FF2j(int i, int i2, int i3) {
        return (i & i3) | (i & i2) | (i2 & i3);
    }

    private static int GG1j(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private static int GG2j(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    public static int rotateLeft(int i, int i2) {
        return (i >> (32 - i2)) | (i << i2);
    }

    static {
        int i;
        int i2 = 0;
        while (true) {
            if (i2 >= 16) {
                break;
            }
            f20848Tj[i2] = 2043430169;
            i2++;
        }
        for (i = 16; i < 64; i++) {
            f20848Tj[i] = 2055708042;
        }
    }

    /* renamed from: CF */
    public static byte[] m5825CF(byte[] bArr, byte[] bArr2) {
        return convert(m5824CF(convert(bArr), convert(bArr2)));
    }

    private static int[] convert(byte[] bArr) {
        int[] iArr = new int[bArr.length / 4];
        byte[] bArr2 = new byte[4];
        for (int i = 0; i < bArr.length; i += 4) {
            System.arraycopy(bArr, i, bArr2, 0, 4);
            iArr[i / 4] = bigEndianByteToInt(bArr2);
        }
        return iArr;
    }

    private static byte[] convert(int[] iArr) {
        byte[] bArr = new byte[iArr.length * 4];
        for (int i = 0; i < iArr.length; i++) {
            System.arraycopy(bigEndianIntToByte(iArr[i]), 0, bArr, i * 4, 4);
        }
        return bArr;
    }

    /* renamed from: CF */
    public static int[] m5824CF(int[] iArr, int[] iArr2) {
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr[2];
        int i4 = iArr[3];
        int i5 = iArr[4];
        int i6 = iArr[5];
        int i7 = iArr[6];
        int i8 = iArr[7];
        int[][] expand = expand(iArr2);
        int[] iArr3 = expand[0];
        int[] iArr4 = expand[1];
        int i9 = i8;
        int i10 = i7;
        int i11 = i6;
        int i12 = i5;
        int i13 = i4;
        int i14 = i3;
        int i15 = i2;
        int i16 = i;
        int i17 = 0;
        while (i17 < 64) {
            int bitCycleLeft = bitCycleLeft(bitCycleLeft(i16, 12) + i12 + bitCycleLeft(f20848Tj[i17], i17), 7);
            int FFj = FFj(i16, i15, i14, i17) + i13;
            int GGj = GGj(i12, i11, i10, i17) + i9 + bitCycleLeft + iArr3[i17];
            int bitCycleLeft2 = bitCycleLeft(i15, 9);
            int bitCycleLeft3 = bitCycleLeft(i11, 19);
            i17++;
            i11 = i12;
            i12 = m5823P0(GGj);
            i9 = i10;
            i10 = bitCycleLeft3;
            i15 = i16;
            i16 = FFj + (bitCycleLeft(i16, 12) ^ bitCycleLeft) + iArr4[i17];
            i13 = i14;
            i14 = bitCycleLeft2;
        }
        return new int[]{i16 ^ iArr[0], iArr[1] ^ i15, iArr[2] ^ i14, iArr[3] ^ i13, iArr[4] ^ i12, iArr[5] ^ i11, iArr[6] ^ i10, i9 ^ iArr[7]};
    }

    private static int[][] expand(int[] iArr) {
        int[] iArr2 = new int[68];
        int[] iArr3 = new int[64];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        for (int i = 16; i < 68; i++) {
            iArr2[i] = (m5822P1((iArr2[i - 16] ^ iArr2[i - 9]) ^ bitCycleLeft(iArr2[i - 3], 15)) ^ bitCycleLeft(iArr2[i - 13], 7)) ^ iArr2[i - 6];
        }
        for (int i2 = 0; i2 < 64; i2++) {
            iArr3[i2] = iArr2[i2] ^ iArr2[i2 + 4];
        }
        return new int[][]{iArr2, iArr3};
    }

    private static byte[] bigEndianIntToByte(int i) {
        return back(UtilByte.intToBytes(i));
    }

    private static int bigEndianByteToInt(byte[] bArr) {
        return UtilByte.byteToInt(back(bArr));
    }

    private static int FFj(int i, int i2, int i3, int i4) {
        if (i4 >= 0 && i4 <= 15) {
            return FF1j(i, i2, i3);
        }
        return FF2j(i, i2, i3);
    }

    private static int GGj(int i, int i2, int i3, int i4) {
        if (i4 >= 0 && i4 <= 15) {
            return GG1j(i, i2, i3);
        }
        return GG2j(i, i2, i3);
    }

    /* renamed from: P0 */
    private static int m5823P0(int i) {
        rotateLeft(i, 9);
        int bitCycleLeft = bitCycleLeft(i, 9);
        rotateLeft(i, 17);
        return (i ^ bitCycleLeft) ^ bitCycleLeft(i, 17);
    }

    /* renamed from: P1 */
    private static int m5822P1(int i) {
        return bitCycleLeft(i, 23) ^ (bitCycleLeft(i, 15) ^ i);
    }

    public static byte[] padding(byte[] bArr, int i) {
        int length = 448 - (((bArr.length * 8) + 1) % 512);
        if (length < 0) {
            length = 960 - (((bArr.length * 8) + 1) % 512);
        }
        int i2 = (length + 1) / 8;
        byte[] bArr2 = new byte[i2];
        bArr2[0] = Byte.MIN_VALUE;
        byte[] bArr3 = new byte[bArr.length + i2 + 8];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        int length2 = bArr.length + 0;
        System.arraycopy(bArr2, 0, bArr3, length2, bArr2.length);
        int length3 = length2 + bArr2.length;
        byte[] back = back(UtilByte.longToBytes((bArr.length * 8) + (i * 512)));
        System.arraycopy(back, 0, bArr3, length3, back.length);
        return bArr3;
    }

    private static byte[] back(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr2.length; i++) {
            bArr2[i] = bArr[(bArr2.length - i) - 1];
        }
        return bArr2;
    }

    private static int bitCycleLeft(int i, int i2) {
        int i3 = i2 % 32;
        byte[] bigEndianIntToByte = bigEndianIntToByte(i);
        int i4 = i3 / 8;
        int i5 = i3 % 8;
        if (i4 > 0) {
            bigEndianIntToByte = byteCycleLeft(bigEndianIntToByte, i4);
        }
        if (i5 > 0) {
            bigEndianIntToByte = bitSmall8CycleLeft(bigEndianIntToByte, i5);
        }
        return bigEndianByteToInt(bigEndianIntToByte);
    }

    private static byte[] bitSmall8CycleLeft(byte[] bArr, int i) {
        byte[] bArr2 = new byte[bArr.length];
        int i2 = 0;
        while (i2 < bArr2.length) {
            int i3 = i2 + 1;
            bArr2[i2] = (byte) (((byte) ((bArr[i2] & 255) << i)) | ((byte) ((bArr[i3 % bArr2.length] & 255) >> (8 - i))));
            i2 = i3;
        }
        return bArr2;
    }

    private static byte[] byteCycleLeft(byte[] bArr, int i) {
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, i, bArr2, 0, bArr.length - i);
        System.arraycopy(bArr, 0, bArr2, bArr.length - i, i);
        return bArr2;
    }
}
