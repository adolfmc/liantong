package net.lingala.zip4j.crypto.PBKDF2;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
class BinTools {
    public static final String hex = "0123456789ABCDEF";

    BinTools() {
    }

    public static String bin2hex(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            int i = (b + 256) % 256;
            stringBuffer.append("0123456789ABCDEF".charAt((i / 16) & 15));
            stringBuffer.append("0123456789ABCDEF".charAt((i % 16) & 15));
        }
        return stringBuffer.toString();
    }

    public static byte[] hex2bin(String str) {
        if (str == null) {
            str = "";
        } else if (str.length() % 2 != 0) {
            str = "0" + str;
        }
        byte[] bArr = new byte[str.length() / 2];
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            int i3 = i + 1;
            bArr[i2] = (byte) ((hex2bin(str.charAt(i)) * 16) + hex2bin(str.charAt(i3)));
            i2++;
            i = i3 + 1;
        }
        return bArr;
    }

    public static int hex2bin(char c) {
        if (c < '0' || c > '9') {
            if (c < 'A' || c > 'F') {
                if (c < 'a' || c > 'f') {
                    throw new IllegalArgumentException("Input string may only contain hex digits, but found '" + c + "'");
                }
                return (c - 'a') + 10;
            }
            return (c - 'A') + 10;
        }
        return c - '0';
    }
}
