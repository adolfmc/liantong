package com.gmrz.authenticationso.utils;

import android.util.Base64;
import android.util.Log;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class UtilByte {
    private static final String TAG = "UtilByte";

    public static short makeShort(byte b, byte b2) {
        return (short) ((b << 8) | (b2 & 255));
    }

    public static String byte2hex(byte[] bArr) {
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
            return str.getBytes(StandardCharsets.UTF_8);
        }
        return new byte[0];
    }

    public static String byte2str(byte[] bArr) {
        return bArr != null ? new String(bArr, StandardCharsets.UTF_8) : "";
    }

    public static int arrayCopy(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        if (bArr.length < i) {
            Log.e(TAG, "arrayCopy return error, src.length < srcOff");
            return -1;
        } else if (bArr.length < i3 + i) {
            Log.e(TAG, "arrayCopy return error, src.length < srcOff");
            return -1;
        } else if (bArr2.length >= i2 && bArr2.length >= i2 + i3) {
            for (int i4 = 0; i4 < i3; i4++) {
                bArr2[i2] = bArr[i];
                i2++;
                i++;
            }
            return i2;
        } else {
            return -1;
        }
    }

    public static short getShort(byte[] bArr, int i) {
        return (short) ((bArr[i + 0] & 255) | (bArr[i + 1] << 8));
    }

    public static short setShort16(byte[] bArr, short s, short s2) {
        bArr[s + 1] = (byte) (s2 >> 8);
        bArr[s + 0] = (byte) (s2 >> 0);
        return (short) (s + 2);
    }

    public static short setShort8(byte[] bArr, short s, short s2) {
        bArr[s + 0] = (byte) (s2 >> 0);
        return (short) (s + 1);
    }

    public static short str2Short(String str) {
        byte[] hex2byte = hex2byte(str);
        return (short) ((hex2byte[1] & 255) | (hex2byte[0] << 8));
    }

    public static String random(int i) {
        StringBuilder sb = new StringBuilder(i);
        for (int i2 = 0; i2 < i; i2++) {
            sb.append((char) ThreadLocalRandom.current().nextInt(33, 128));
        }
        return sb.toString();
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
        setShort16(bArr, (short) 0, s);
        return getShort(bArr, 0);
    }
}
