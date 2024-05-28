package com.froad.libloadso.utils;

import java.io.PrintStream;
import java.util.Arrays;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p038j0.C0548c;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SM4Util {
    private static final int BLOCK = 16;
    public static final int DECRYPT = 0;
    public static final String ENCRK = "510B326E1BB9ECDEB8B2E331B04731AF";
    public static final int ENCRYPT = 1;
    public static final int ROUND = 32;
    private static final String TAG = "SM4Util";
    private static SM4Util sm4;
    private byte[] Sbox = {-42, -112, -23, -2, -52, -31, 61, -73, 22, -74, 20, -62, 40, -5, 44, 5, 43, 103, -102, 118, 42, -66, 4, -61, -86, 68, 19, 38, 73, -122, 6, -103, -100, 66, 80, -12, -111, -17, -104, 122, 51, 84, 11, 67, -19, -49, -84, 98, -28, -77, 28, -87, -55, 8, -24, -107, Byte.MIN_VALUE, -33, -108, -6, 117, -113, 63, -90, 71, 7, -89, -4, -13, 115, 23, -70, -125, 89, 60, 25, -26, -123, 79, -88, 104, 107, -127, -78, 113, 100, -38, -117, -8, -21, 15, 75, 112, 86, -99, 53, 30, 36, 14, 94, 99, 88, -47, -94, 37, 34, 124, 59, 1, 33, 120, -121, -44, 0, 70, 87, -97, -45, 39, 82, 76, C0548c.f1784h, 2, -25, -96, -60, -56, -98, -22, -65, -118, -46, 64, -57, 56, -75, -93, -9, -14, -50, -7, 97, 21, -95, -32, -82, 93, -92, -101, 52, 26, 85, -83, -109, 50, 48, -11, -116, -79, -29, 29, -10, -30, 46, -126, 102, -54, 96, -64, 41, 35, -85, 13, 83, 78, 111, -43, -37, 55, 69, -34, -3, -114, 47, 3, -1, 106, 114, 109, 108, 91, 81, -115, 27, -81, -110, -69, -35, -68, Byte.MAX_VALUE, 17, -39, C0548c.f1785i, 65, 31, 16, 90, -40, 10, -63, 49, -120, -91, -51, 123, -67, 45, 116, -48, 18, -72, -27, -76, -80, -119, 105, -105, 74, 12, -106, 119, 126, 101, -71, -15, 9, -59, 110, -58, -124, 24, -16, 125, -20, 58, -36, 77, 32, 121, -18, 95, 62, -41, -53, 57, 72};

    /* renamed from: CK */
    private int[] f10135CK = {462357, 472066609, 943670861, 1415275113, 1886879365, -1936483679, -1464879427, -993275175, -521670923, -66909679, 404694573, 876298825, 1347903077, 1819507329, -2003855715, -1532251463, -1060647211, -589042959, -117504499, 337322537, 808926789, 1280531041, 1752135293, -2071227751, -1599623499, -1128019247, -656414995, -184876535, 269950501, 741554753, 1213159005, 1684763257};

    private SM4Util() {
    }

    private int ByteSub(int i) {
        byte[] bArr = this.Sbox;
        return (bArr[i & 255] & 255) | ((bArr[(i >>> 24) & 255] & 255) << 24) | ((bArr[(i >>> 16) & 255] & 255) << 16) | ((bArr[(i >>> 8) & 255] & 255) << 8);
    }

    /* renamed from: L1 */
    private int m15911L1(int i) {
        return Rotl(i, 24) ^ (((Rotl(i, 2) ^ i) ^ Rotl(i, 10)) ^ Rotl(i, 18));
    }

    /* renamed from: L2 */
    private int m15910L2(int i) {
        return Rotl(i, 23) ^ (Rotl(i, 13) ^ i);
    }

    private int Rotl(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    private void SMS4KeyExt(byte[] bArr, int[] iArr, int i) {
        int[] iArr2 = new int[4];
        int[] iArr3 = new int[4];
        for (int i2 = 0; i2 < 4; i2++) {
            int i3 = i2 * 4;
            iArr3[0] = bArr[i3 + 0] & 255;
            iArr3[1] = bArr[i3 + 1] & 255;
            iArr3[2] = bArr[i3 + 2] & 255;
            iArr3[3] = bArr[i3 + 3] & 255;
            iArr2[i2] = (iArr3[1] << 16) | (iArr3[0] << 24) | (iArr3[2] << 8) | iArr3[3];
        }
        iArr2[0] = iArr2[0] ^ (-1548633402);
        iArr2[1] = iArr2[1] ^ 1453994832;
        iArr2[2] = iArr2[2] ^ 1736282519;
        iArr2[3] = iArr2[3] ^ (-1301273892);
        for (int i4 = 0; i4 < 32; i4 += 4) {
            int i5 = i4 + 0;
            int m15910L2 = m15910L2(ByteSub(((iArr2[1] ^ iArr2[2]) ^ iArr2[3]) ^ this.f10135CK[i5])) ^ iArr2[0];
            iArr2[0] = m15910L2;
            iArr[i5] = m15910L2;
            int i6 = i4 + 1;
            int m15910L22 = m15910L2(ByteSub(((iArr2[2] ^ iArr2[3]) ^ iArr2[0]) ^ this.f10135CK[i6])) ^ iArr2[1];
            iArr2[1] = m15910L22;
            iArr[i6] = m15910L22;
            int i7 = i4 + 2;
            int m15910L23 = m15910L2(ByteSub(((iArr2[3] ^ iArr2[0]) ^ iArr2[1]) ^ this.f10135CK[i7])) ^ iArr2[2];
            iArr2[2] = m15910L23;
            iArr[i7] = m15910L23;
            int i8 = i4 + 3;
            int m15910L24 = m15910L2(ByteSub(((iArr2[0] ^ iArr2[1]) ^ iArr2[2]) ^ this.f10135CK[i8])) ^ iArr2[3];
            iArr2[3] = m15910L24;
            iArr[i8] = m15910L24;
        }
        if (i == 0) {
            for (int i9 = 0; i9 < 16; i9++) {
                int i10 = iArr[i9];
                int i11 = 31 - i9;
                iArr[i9] = iArr[i11];
                iArr[i11] = i10;
            }
        }
    }

    public static String bytesToHexStr(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString().toUpperCase();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static byte[] dealSm4Data(byte[] r2, boolean r3) {
        /*
            if (r2 != 0) goto L4
            r2 = 0
            return r2
        L4:
            java.lang.String r2 = bytesToHexStr(r2)
            if (r3 == 0) goto L1c
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
        Lf:
            r3.append(r2)
            java.lang.String r2 = "80"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            goto L2c
        L1c:
            int r3 = r2.length()
            int r3 = r3 / 2
            int r3 = r3 % 16
            if (r3 == 0) goto L2c
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            goto Lf
        L2c:
            int r3 = r2.length()
            int r3 = r3 / 2
            int r0 = r3 / 16
            int r1 = r3 % 16
            if (r1 == 0) goto L3a
            int r0 = r0 + 1
        L3a:
            int r0 = r0 * 16
            byte[] r0 = new byte[r0]
            byte[] r2 = hexString2ByteArray(r2)
            r1 = 0
            java.lang.System.arraycopy(r2, r1, r0, r1, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.froad.libloadso.utils.SM4Util.dealSm4Data(byte[], boolean):byte[]");
    }

    private static byte[] dealSm4DataPKCS5(byte[] bArr, int i) {
        if (bArr == null) {
            return null;
        }
        String bytesToHexStr = bytesToHexStr(bArr);
        int length = bArr.length;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(bytesToHexStr);
        int i2 = 0;
        if (length % 16 != 0) {
            int i3 = 16 - (length - ((length / 16) * 16));
            while (i2 < i3) {
                stringBuffer.append(int2HexStr(i3));
                i2++;
            }
        } else if (i == 1) {
            while (i2 < 16) {
                stringBuffer.append(int2HexStr(16));
                i2++;
            }
        }
        return hexString2ByteArray(stringBuffer.toString());
    }

    private static byte[] dealSm4DecDataPKCS5(byte[] bArr) {
        int length = bArr.length;
        byte b = bArr[length - 1];
        PrintStream printStream = System.out;
        printStream.println("dealSm4DecDataPKCS5>>>endPi:" + ((int) b));
        return FCharUtils.hexString2ByteArray(FCharUtils.bytesToHexStr(bArr).substring(0, (length - b) * 2));
    }

    private static byte[] dealSm4Key(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[16];
        if (length >= 16) {
            System.arraycopy(bArr, 0, bArr2, 0, 16);
        } else if (length < 16) {
            System.arraycopy(bArr, 0, bArr2, 0, length);
        }
        return bArr2;
    }

    public static SM4Util getInstance() {
        if (sm4 == null) {
            sm4 = new SM4Util();
        }
        return sm4;
    }

    public static byte[] hexString2ByteArray(String str) {
        int length = str.length();
        if (length % 2 != 0) {
            return null;
        }
        byte[] bArr = new byte[length / 2];
        int i = 0;
        while (i < length) {
            int i2 = i + 2;
            bArr[i / 2] = (byte) Integer.parseInt(str.substring(i, i2), 16);
            i = i2;
        }
        return bArr;
    }

    public static String int2HexStr(int i) {
        String hexString = Integer.toHexString(i & 255);
        if (hexString.length() < 2) {
            hexString = "0" + hexString;
        }
        return hexString.toUpperCase();
    }

    public static void main(String[] strArr) {
        String bytesToHexStr = FCharUtils.bytesToHexStr(getInstance().sms4_ecb_pkcs5(FCharUtils.hexString2ByteArray("01002CFFFFFF002C5001000153480112090C000000000008866545D08FF818704DA1D5EF84DFEB841E3C9000C6FEFE14504C415432303031313931363336333633313330204646541400010203040506070809000102030405060708090716092140202780140001020304050607080900010203040506070809"), FCharUtils.hexString2ByteArray("31323334353637383132333435363738"), 1));
        PrintStream printStream = System.out;
        printStream.println("e2:" + bytesToHexStr);
        String bytesToHexStr2 = FCharUtils.bytesToHexStr(getInstance().sms4_ecb(FCharUtils.hexString2ByteArray("11223344556677881122334455667788"), FCharUtils.hexString2ByteArray("01002CFFFFFF002C5001000153480112090C000000000008866545D08FF818704DA1D5EF84DFEB841E3C9000C6FEFE14504C415432303031313931363336333633313330204646541400010203040506070809000102030405060708090716092140202780140001020304050607080900010203040506070809"), 1, true, 0));
        PrintStream printStream2 = System.out;
        printStream2.println("e3:" + bytesToHexStr2);
        String bytesToHexStr3 = FCharUtils.bytesToHexStr(getInstance().sms4_ecb(FCharUtils.hexString2ByteArray(bytesToHexStr2), FCharUtils.hexString2ByteArray("01002CFFFFFF002C5001000153480112090C000000000008866545D08FF818704DA1D5EF84DFEB841E3C9000C6FEFE14504C415432303031313931363336333633313330204646541400010203040506070809000102030405060708090716092140202780140001020304050607080900010203040506070809"), 0, true, 0));
        PrintStream printStream3 = System.out;
        printStream3.println("e4:" + bytesToHexStr3);
    }

    public void SMS4Crypt(byte[] bArr, byte[] bArr2, int[] iArr) {
        int[] iArr2 = new int[4];
        int[] iArr3 = new int[4];
        for (int i = 0; i < 4; i++) {
            int i2 = i * 4;
            iArr3[0] = bArr[i2 + 0] & 255;
            iArr3[1] = bArr[i2 + 1] & 255;
            iArr3[2] = bArr[i2 + 2] & 255;
            iArr3[3] = bArr[i2 + 3] & 255;
            iArr2[i] = (iArr3[1] << 16) | (iArr3[0] << 24) | (iArr3[2] << 8) | iArr3[3];
        }
        for (int i3 = 0; i3 < 32; i3 += 4) {
            iArr2[0] = m15911L1(ByteSub(((iArr2[1] ^ iArr2[2]) ^ iArr2[3]) ^ iArr[i3 + 0])) ^ iArr2[0];
            iArr2[1] = m15911L1(ByteSub(((iArr2[2] ^ iArr2[3]) ^ iArr2[0]) ^ iArr[i3 + 1])) ^ iArr2[1];
            iArr2[2] = m15911L1(ByteSub(((iArr2[3] ^ iArr2[0]) ^ iArr2[1]) ^ iArr[i3 + 2])) ^ iArr2[2];
            iArr2[3] = m15911L1(ByteSub(((iArr2[0] ^ iArr2[1]) ^ iArr2[2]) ^ iArr[i3 + 3])) ^ iArr2[3];
        }
        for (int i4 = 0; i4 < 16; i4 += 4) {
            int i5 = 3 - (i4 / 4);
            bArr2[i4] = (byte) ((iArr2[i5] >>> 24) & 255);
            bArr2[i4 + 1] = (byte) ((iArr2[i5] >>> 16) & 255);
            bArr2[i4 + 2] = (byte) ((iArr2[i5] >>> 8) & 255);
            bArr2[i4 + 3] = (byte) (iArr2[i5] & 255);
        }
    }

    public byte[] dealMac(byte[] bArr, byte[] bArr2) {
        byte[] sms4_cbc = sms4_cbc(new byte[16], bArr, bArr2, 1, false);
        byte[] bArr3 = new byte[16];
        System.arraycopy(sms4_cbc, sms4_cbc.length - 16, bArr3, 0, 16);
        return bArr3;
    }

    public byte[] sms4_cbc(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, boolean z) {
        int[] iArr = new int[32];
        SMS4KeyExt(bArr3, iArr, i);
        byte[] dealSm4Data = dealSm4Data(bArr2, z);
        int length = dealSm4Data.length;
        byte[] bArr4 = new byte[length];
        byte[] bArr5 = new byte[16];
        byte[] bArr6 = new byte[16];
        if (i == 1) {
            System.arraycopy(bArr, 0, bArr5, 0, 16);
            int i2 = 0;
            while (length >= 16) {
                int i3 = i2 + 16;
                byte[] copyOfRange = Arrays.copyOfRange(dealSm4Data, i2, i3);
                xors(bArr5, copyOfRange);
                SMS4Crypt(copyOfRange, bArr6, iArr);
                System.arraycopy(bArr6, 0, bArr5, 0, 16);
                System.arraycopy(bArr6, 0, bArr4, i2, 16);
                length -= 16;
                i2 = i3;
            }
        } else {
            System.arraycopy(bArr, 0, bArr5, 0, 16);
            int i4 = 0;
            while (length >= 16) {
                int i5 = i4 + 16;
                byte[] copyOfRange2 = Arrays.copyOfRange(dealSm4Data, i4, i5);
                SMS4Crypt(copyOfRange2, bArr6, iArr);
                xors(bArr5, bArr6);
                System.arraycopy(copyOfRange2, 0, bArr5, 0, 16);
                System.arraycopy(bArr6, 0, bArr4, i4, 16);
                length -= 16;
                i4 = i5;
            }
        }
        return bArr4;
    }

    public byte[] sms4_cbc_pkcs5(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        int[] iArr = new int[32];
        SMS4KeyExt(dealSm4Key(bArr3), iArr, i);
        byte[] dealSm4DataPKCS5 = dealSm4DataPKCS5(bArr2, i);
        System.out.println("sms4_cbc_pkcs5>>>input:" + bytesToHexStr(dealSm4DataPKCS5));
        int length = dealSm4DataPKCS5.length;
        byte[] bArr4 = new byte[length];
        byte[] bArr5 = new byte[16];
        byte[] bArr6 = new byte[16];
        System.arraycopy(bArr, 0, bArr6, 0, 16);
        int i2 = 0;
        while (length >= 16) {
            int i3 = i2 + 16;
            byte[] copyOfRange = Arrays.copyOfRange(bArr2, i2, i3);
            xors(bArr6, copyOfRange);
            SMS4Crypt(copyOfRange, bArr5, iArr);
            System.arraycopy(bArr5, 0, bArr6, 0, 16);
            System.arraycopy(bArr5, 0, bArr4, i2, 16);
            length -= 16;
            i2 = i3;
        }
        return bArr4;
    }

    public byte[] sms4_ecb(byte[] bArr, byte[] bArr2, int i, boolean z, int i2) {
        byte[] dealSm4Data;
        byte[] bArr3;
        if (bArr == null || bArr2 == null) {
            return null;
        }
        int[] iArr = new int[32];
        SMS4KeyExt(dealSm4Key(bArr2), iArr, i);
        if (i == 1) {
            String bytesToHexStr = FCharUtils.bytesToHexStr(bArr);
            if (i2 == 1) {
                bytesToHexStr = FCharUtils.hexStr2LV00_2(bytesToHexStr);
            }
            dealSm4Data = dealSm4Data(FCharUtils.hexString2ByteArray(bytesToHexStr), z);
        } else {
            dealSm4Data = dealSm4Data(bArr, false);
        }
        int length = dealSm4Data.length;
        byte[] bArr4 = new byte[length];
        byte[] bArr5 = new byte[16];
        int i3 = 0;
        while (length >= 16) {
            int i4 = i3 + 16;
            SMS4Crypt(Arrays.copyOfRange(dealSm4Data, i3, i4), bArr5, iArr);
            System.arraycopy(bArr5, 0, bArr4, i3, 16);
            length -= 16;
            i3 = i4;
        }
        if (i == 0) {
            if (i2 == 1) {
                int i5 = (bArr4[0] << 8) + bArr4[1];
                System.out.println("sms4_ecb>>>dataLen:" + i5);
                bArr3 = new byte[i5];
                System.arraycopy(bArr4, 2, bArr3, 0, i5);
            } else {
                bArr3 = bArr4;
            }
            if (z) {
                String bytesToHexStr2 = FCharUtils.bytesToHexStr(bArr3);
                return FCharUtils.hexString2ByteArray(bytesToHexStr2.substring(0, bytesToHexStr2.lastIndexOf("80")));
            }
            return bArr3;
        }
        return bArr4;
    }

    public byte[] sms4_ecb_pkcs5(byte[] bArr, byte[] bArr2, int i) {
        int[] iArr = new int[32];
        SMS4KeyExt(dealSm4Key(bArr2), iArr, i);
        byte[] dealSm4DataPKCS5 = dealSm4DataPKCS5(bArr, i);
        System.out.println("dealSm4DataPKCS5>>>input:" + bytesToHexStr(dealSm4DataPKCS5));
        int length = dealSm4DataPKCS5.length;
        byte[] bArr3 = new byte[length];
        byte[] bArr4 = new byte[16];
        int i2 = 0;
        while (length >= 16) {
            int i3 = i2 + 16;
            SMS4Crypt(Arrays.copyOfRange(dealSm4DataPKCS5, i2, i3), bArr4, iArr);
            System.arraycopy(bArr4, 0, bArr3, i2, 16);
            length -= 16;
            i2 = i3;
        }
        return i == 0 ? dealSm4DecDataPKCS5(bArr3) : bArr3;
    }

    public byte[] sms4_ecb_xor(byte[] bArr, byte[] bArr2, int i) {
        byte[] bArr3;
        int[] iArr = new int[32];
        byte[] dealSm4Key = dealSm4Key(bArr2);
        SMS4KeyExt(dealSm4Key, iArr, i);
        int length = bArr.length;
        if (length % 16 == 0) {
            bArr3 = null;
        } else {
            int i2 = (length / 16) * 16;
            byte[] bArr4 = new byte[i2];
            int i3 = length - i2;
            byte[] bArr5 = new byte[i3];
            System.arraycopy(bArr, 0, bArr4, 0, i2);
            System.arraycopy(bArr, i2, bArr5, 0, i3);
            bArr = bArr4;
            bArr3 = bArr5;
        }
        int length2 = bArr.length;
        byte[] bArr6 = new byte[length];
        byte[] bArr7 = new byte[16];
        int i4 = 0;
        while (length2 >= 16) {
            int i5 = i4 + 16;
            SMS4Crypt(Arrays.copyOfRange(bArr, i4, i5), bArr7, iArr);
            System.arraycopy(bArr7, 0, bArr6, i4, 16);
            length2 -= 16;
            i4 = i5;
        }
        if (length != i4) {
            byte[] bArr8 = new byte[8];
            byte[] bArr9 = new byte[8];
            System.arraycopy(dealSm4Key, 0, bArr8, 0, 8);
            System.arraycopy(dealSm4Key, 8, bArr9, 0, 8);
            xors(bArr8, bArr9);
            int length3 = bArr3.length;
            if (length3 > 8) {
                byte[] bArr10 = new byte[8];
                System.arraycopy(bArr3, 0, bArr10, 0, 8);
                xors(bArr10, bArr8);
                System.arraycopy(bArr10, 0, bArr3, 0, 8);
                int i6 = length3 - 8;
                byte[] bArr11 = new byte[i6];
                System.arraycopy(bArr3, 8, bArr11, 0, i6);
                xors(bArr11, bArr8);
                System.arraycopy(bArr11, 0, bArr3, 8, i6);
            } else {
                xors(bArr3, bArr8);
            }
            System.arraycopy(bArr3, 0, bArr6, i4, length3);
        }
        return bArr6;
    }

    public void xors(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
    }
}
