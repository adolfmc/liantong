package com.ijiami;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Base64 {
    private static final char[] legalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

    private static int decode(char c) {
        int i;
        if (c < 'A' || c > 'Z') {
            if (c >= 'a' && c <= 'z') {
                i = c - 'a';
            } else if (c < '0' || c > '9') {
                if (c != '+') {
                    if (c != '/') {
                        if (c == '=') {
                            return 0;
                        }
                        throw new RuntimeException("unexpected code: " + c);
                    }
                    return 63;
                }
                return 62;
            } else {
                i = (c - '0') + 26;
            }
            return i + 26;
        }
        return c - 'A';
    }

    private static void decode(String str, OutputStream outputStream) {
        int length = str.length();
        int i = 0;
        while (true) {
            if (i < length && str.charAt(i) <= ' ') {
                i++;
            } else if (i == length) {
                return;
            } else {
                int i2 = i + 2;
                int i3 = i + 3;
                int decode = (decode(str.charAt(i)) << 18) + (decode(str.charAt(i + 1)) << 12) + (decode(str.charAt(i2)) << 6) + decode(str.charAt(i3));
                outputStream.write((decode >> 16) & 255);
                if (str.charAt(i2) == '=') {
                    return;
                }
                outputStream.write((decode >> 8) & 255);
                if (str.charAt(i3) == '=') {
                    return;
                }
                outputStream.write(decode & 255);
                i += 4;
            }
        }
    }

    public static byte[] decode(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            decode(str, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return byteArray;
        } catch (IOException unused) {
            throw new RuntimeException();
        }
    }

    public static String encode(byte[] bArr) {
        String str;
        int length = bArr.length;
        StringBuffer stringBuffer = new StringBuffer((bArr.length * 3) / 2);
        int i = length - 3;
        int i2 = 0;
        while (i2 <= i) {
            int i3 = ((bArr[i2] & 255) << 16) | ((bArr[i2 + 1] & 255) << 8) | (bArr[i2 + 2] & 255);
            char[] cArr = legalChars;
            stringBuffer.append(cArr[(i3 >> 18) & 63]);
            stringBuffer.append(cArr[(i3 >> 12) & 63]);
            stringBuffer.append(cArr[(i3 >> 6) & 63]);
            stringBuffer.append(cArr[i3 & 63]);
            i2 += 3;
        }
        int i4 = 0 + length;
        if (i2 != i4 - 2) {
            if (i2 == i4 - 1) {
                int i5 = (bArr[i2] & 255) << 16;
                char[] cArr2 = legalChars;
                stringBuffer.append(cArr2[(i5 >> 18) & 63]);
                stringBuffer.append(cArr2[(i5 >> 12) & 63]);
                str = "==";
            }
            return stringBuffer.toString();
        }
        int i6 = ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2] & 255) << 16);
        char[] cArr3 = legalChars;
        stringBuffer.append(cArr3[(i6 >> 18) & 63]);
        stringBuffer.append(cArr3[(i6 >> 12) & 63]);
        stringBuffer.append(cArr3[(i6 >> 6) & 63]);
        str = "=";
        stringBuffer.append(str);
        return stringBuffer.toString();
    }
}
