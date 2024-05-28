package com.utils.p366ak;

import android.util.Base64;
import com.gmrz.android.client.utils.Charsets;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.utils.ak.UtilByte */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class UtilByte {
    public static byte[] intToBytes(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)};
    }

    public static int byteToInt(byte[] bArr) {
        return ((bArr[3] & 255) << 24) | 0 | (bArr[0] & 255) | ((bArr[1] & 255) << 8) | ((bArr[2] & 255) << 16);
    }

    public static byte[] longToBytes(long j) {
        byte[] bArr = new byte[8];
        for (int i = 0; i < 8; i++) {
            bArr[i] = (byte) (255 & (j >> (i * 8)));
        }
        return bArr;
    }

    public static void revert(byte[] bArr) {
        short length = (short) bArr.length;
        short s = 0;
        while (true) {
            int i = (length - s) - 1;
            if (s >= i) {
                return;
            }
            byte b = bArr[s];
            bArr[s] = bArr[i];
            bArr[i] = b;
            s = (short) (s + 1);
        }
    }

    public static short revert(short s) {
        byte[] bArr = new byte[2];
        Util.setShort16(bArr, (short) 0, s);
        return Util.getShort(bArr, 0);
    }

    public static String toBase64(byte[] bArr) {
        return Base64.encodeToString(bArr, 11);
    }

    public static byte[] fromBase64(String str) {
        return Base64.decode(str, 11);
    }

    public static String byte2hex(byte[] bArr) {
        if (bArr == null) {
            return "bytes are null";
        }
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append("0123456789ABCDEF".charAt((b & 240) >> 4));
            sb.append("0123456789ABCDEF".charAt(b & 15));
        }
        return sb.toString();
    }

    public static byte[] hex2byte(String str) {
        byte[] bArr = new byte[str.length() / 2];
        char[] charArray = str.toCharArray();
        int i = 0;
        int i2 = 0;
        while (i < charArray.length) {
            bArr[i2] = (byte) Integer.parseInt(new String(charArray, i, 2), 16);
            i += 2;
            i2++;
        }
        return bArr;
    }

    public static int arraycopy(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        int i4 = i2 + i3;
        System.arraycopy(bArr, i, bArr2, i2, i3);
        return i4;
    }

    public static byte[] base642byte(String str) {
        return Base64.decode(str, 11);
    }

    public static byte[] base642byte(String str, int i) {
        return Base64.decode(str, i);
    }

    public static String byte2base64(byte[] bArr) {
        return Base64.encodeToString(bArr, 11);
    }

    public static boolean isSame(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return false;
        }
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static byte[] str2byte(String str) {
        if (str != null) {
            return str.getBytes(Charsets.utf8Charset);
        }
        return new byte[0];
    }

    public static String byte2str(byte[] bArr) {
        return bArr != null ? new String(bArr, Charsets.utf8Charset) : "";
    }
}
