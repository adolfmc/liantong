package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.utils;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class Hex {
    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    public static String bytesToHexString(byte[] bArr) {
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
        return sb.toString();
    }

    public static byte[] hexStringToBytes(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String upperCase = str.toUpperCase();
        int length = upperCase.length() / 2;
        char[] charArray = upperCase.toCharArray();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (charToByte(charArray[i2 + 1]) | (charToByte(charArray[i2]) << 4));
        }
        return bArr;
    }

    public static final String long2HexString(long j, int i) {
        int i2 = i << 1;
        char[] cArr = new char[i2];
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            cArr[i3] = HEX_DIGITS[(int) (15 & j)];
            j >>>= 4;
        }
        return new String(cArr);
    }

    public static final byte[] padding(byte[] bArr, byte b, byte b2, boolean z) {
        int length = bArr.length % 8;
        int i = 1;
        if (length != 0) {
            int i2 = 8 - length;
            byte[] bArr2 = new byte[bArr.length + i2];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            bArr2[bArr.length] = b;
            while (i < i2) {
                bArr2[bArr.length + i] = b2;
                i++;
            }
            return bArr2;
        } else if (z) {
            byte[] bArr3 = new byte[bArr.length + 8];
            System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
            bArr3[bArr.length] = b;
            while (i < 8) {
                bArr3[bArr.length + i] = b2;
                i++;
            }
            return bArr3;
        } else {
            return bArr;
        }
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
