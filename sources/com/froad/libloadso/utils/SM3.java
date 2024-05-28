package com.froad.libloadso.utils;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class SM3 {

    /* renamed from: iv */
    public static final byte[] f10133iv = {115, Byte.MIN_VALUE, 22, 111, 73, 20, -78, -71, 23, 36, 66, -41, -38, -118, 6, 0, -87, 111, 48, -68, 22, 49, 56, -86, -29, -115, -18, 77, -80, -5, 14, 78};

    /* renamed from: Tj */
    public static int[] f10132Tj = new int[64];

    static {
        int i;
        int i2 = 0;
        while (true) {
            if (i2 >= 16) {
                break;
            }
            f10132Tj[i2] = 2043430169;
            i2++;
        }
        for (i = 16; i < 64; i++) {
            f10132Tj[i] = 2055708042;
        }
    }

    /* renamed from: CF */
    public static byte[] m15915CF(byte[] bArr, byte[] bArr2) {
        return convert(m15914CF(convert(bArr), convert(bArr2)));
    }

    /* renamed from: CF */
    public static int[] m15914CF(int[] iArr, int[] iArr2) {
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
        int i9 = i;
        int i10 = 0;
        while (i10 < 64) {
            int bitCycleLeft = bitCycleLeft(bitCycleLeft(i9, 12) + i5 + bitCycleLeft(f10132Tj[i10], i10), 7);
            int FFj = FFj(i9, i2, i3, i10) + i4;
            int GGj = GGj(i5, i6, i7, i10) + i8 + bitCycleLeft + iArr3[i10];
            int bitCycleLeft2 = bitCycleLeft(i2, 9);
            int bitCycleLeft3 = bitCycleLeft(i6, 19);
            i10++;
            i6 = i5;
            i5 = m15913P0(GGj);
            i8 = i7;
            i7 = bitCycleLeft3;
            int i11 = i3;
            i3 = bitCycleLeft2;
            i2 = i9;
            i9 = FFj + (bitCycleLeft(i9, 12) ^ bitCycleLeft) + iArr4[i10];
            i4 = i11;
        }
        return new int[]{iArr[0] ^ i9, iArr[1] ^ i2, iArr[2] ^ i3, iArr[3] ^ i4, iArr[4] ^ i5, iArr[5] ^ i6, iArr[6] ^ i7, iArr[7] ^ i8};
    }

    private static int FF1j(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private static int FF2j(int i, int i2, int i3) {
        return (i & i3) | (i & i2) | (i2 & i3);
    }

    private static int FFj(int i, int i2, int i3, int i4) {
        return (i4 < 0 || i4 > 15) ? FF2j(i, i2, i3) : FF1j(i, i2, i3);
    }

    private static int GG1j(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private static int GG2j(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    private static int GGj(int i, int i2, int i3, int i4) {
        return (i4 < 0 || i4 > 15) ? GG2j(i, i2, i3) : GG1j(i, i2, i3);
    }

    /* renamed from: P0 */
    private static int m15913P0(int i) {
        rotateLeft(i, 9);
        int bitCycleLeft = bitCycleLeft(i, 9);
        rotateLeft(i, 17);
        return (i ^ bitCycleLeft) ^ bitCycleLeft(i, 17);
    }

    /* renamed from: P1 */
    private static int m15912P1(int i) {
        return bitCycleLeft(i, 23) ^ (bitCycleLeft(i, 15) ^ i);
    }

    private static byte[] back(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr2[i] = bArr[(length - i) - 1];
        }
        return bArr2;
    }

    private static int bigEndianByteToInt(byte[] bArr) {
        return FCharUtils.byteToInt(back(bArr));
    }

    private static byte[] bigEndianIntToByte(int i) {
        return back(FCharUtils.intToBytes(i));
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
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 + 1;
            bArr2[i2] = (byte) (((byte) ((bArr[i2] & 255) << i)) | ((byte) ((bArr[i3 % length] & 255) >> (8 - i))));
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

    private static byte[] convert(int[] iArr) {
        byte[] bArr = new byte[iArr.length * 4];
        for (int i = 0; i < iArr.length; i++) {
            System.arraycopy(bigEndianIntToByte(iArr[i]), 0, bArr, i * 4, 4);
        }
        return bArr;
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

    private static int[][] expand(int[] iArr) {
        int[] iArr2 = new int[68];
        int[] iArr3 = new int[64];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = iArr[i];
        }
        for (int i2 = 16; i2 < 68; i2++) {
            iArr2[i2] = (m15912P1((iArr2[i2 - 16] ^ iArr2[i2 - 9]) ^ bitCycleLeft(iArr2[i2 - 3], 15)) ^ bitCycleLeft(iArr2[i2 - 13], 7)) ^ iArr2[i2 - 6];
        }
        for (int i3 = 0; i3 < 64; i3++) {
            iArr3[i3] = iArr2[i3] ^ iArr2[i3 + 4];
        }
        return new int[][]{iArr2, iArr3};
    }

    public static byte[] padding(byte[] bArr, int i) {
        int length = 448 - (((bArr.length * 8) + 1) % 512);
        if (length < 0) {
            length = 960 - (((bArr.length * 8) + 1) % 512);
        }
        int i2 = (length + 1) / 8;
        byte[] bArr2 = new byte[i2];
        bArr2[0] = Byte.MIN_VALUE;
        long length2 = (bArr.length * 8) + (i * 512);
        byte[] bArr3 = new byte[bArr.length + i2 + 8];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        int length3 = bArr.length + 0;
        System.arraycopy(bArr2, 0, bArr3, length3, i2);
        int i3 = length3 + i2;
        byte[] back = back(FCharUtils.longToBytes(length2));
        System.arraycopy(back, 0, bArr3, i3, back.length);
        return bArr3;
    }

    public static int rotateLeft(int i, int i2) {
        return (i >> (32 - i2)) | (i << i2);
    }

    public static String sm3Hash(String str) {
        byte[] bArr = new byte[32];
        byte[] hexString2ByteArray = FCharUtils.hexString2ByteArray(str);
        SM3Digest sM3Digest = new SM3Digest();
        sM3Digest.update(hexString2ByteArray, 0, hexString2ByteArray.length);
        sM3Digest.doFinal(bArr, 0);
        return FCharUtils.bytesToHexStr(bArr);
    }

    public static byte[] sm3Hash(byte[] bArr) {
        byte[] bArr2 = new byte[32];
        SM3Digest sM3Digest = new SM3Digest();
        sM3Digest.update(bArr, 0, bArr.length);
        sM3Digest.doFinal(bArr2, 0);
        return bArr2;
    }
}
