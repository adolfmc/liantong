package org.greenrobot.essentials;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class StringUtils {
    private static final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String[] split(String str, char c) {
        int i = 0;
        int i2 = 0;
        while (true) {
            int indexOf = str.indexOf(c, i);
            if (indexOf == -1) {
                break;
            }
            i2++;
            i = indexOf + 1;
        }
        String[] strArr = new String[i2 + 1];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            int indexOf2 = str.indexOf(c, i3);
            strArr[i4] = str.substring(i3, indexOf2);
            i3 = indexOf2 + 1;
        }
        strArr[i2] = str.substring(i3, str.length());
        return strArr;
    }

    public static String encodeUrl(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String encodeUrlIso(String str) {
        try {
            return URLEncoder.encode(str, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String decodeUrl(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String decodeUrlIso(String str) {
        try {
            return URLDecoder.decode(str, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String md5(String str) {
        return digest(str, "MD5", "UTF-8");
    }

    public static String sha1(String str) {
        return digest(str, "SHA-1", "UTF-8");
    }

    public static String digest(String str, String str2, String str3) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str2);
            messageDigest.update(str.getBytes(str3));
            return hex(messageDigest.digest());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String hex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            char[] cArr2 = HEX_CHARS;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }

    public static byte[] parseHex(String str) {
        int length = str.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException("Illegal string length: " + length);
        }
        int i = length / 2;
        byte[] bArr = new byte[i];
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            int i4 = i3 + 1;
            bArr[i2] = (byte) ((parseHexDigit(str.charAt(i3)) << 4) | parseHexDigit(str.charAt(i4)));
            i2++;
            i3 = i4 + 1;
        }
        return bArr;
    }

    public static int parseHexDigit(char c) {
        if ('0' > c || c > '9') {
            if ('A' > c || c > 'F') {
                if ('a' > c || c > 'f') {
                    throw new IllegalArgumentException("Illegal hex digit: " + c);
                }
                return (c - 'a') + 10;
            }
            return (c - 'A') + 10;
        }
        return c - '0';
    }

    public static String resolveEntity(String str) {
        if (str.length() <= 1 || str.charAt(0) != '#') {
            return str.equals("apos") ? "'" : str.equals("quot") ? "\"" : str.equals("gt") ? ">" : str.equals("lt") ? "<" : str.equals("amp") ? "&" : str;
        } else if (str.charAt(1) == 'x') {
            return String.valueOf((char) Integer.parseInt(str.substring(2), 16));
        } else {
            return String.valueOf((char) Integer.parseInt(str.substring(1)));
        }
    }

    public static String ellipsize(String str, int i) {
        return ellipsize(str, i, "...");
    }

    public static String ellipsize(String str, int i, String str2) {
        if (str == null || str.length() <= i) {
            return str;
        }
        return str.substring(0, i - str2.length()) + str2;
    }

    public static String[] splitLines(String str, boolean z) {
        if (z) {
            return str.split("[\n\r]+");
        }
        return str.split("\\r?\\n");
    }

    public static List<String> findLinesContaining(String str, String str2) {
        String[] splitLines = splitLines(str, true);
        ArrayList arrayList = new ArrayList();
        for (String str3 : splitLines) {
            if (str3.contains(str2)) {
                arrayList.add(str3);
            }
        }
        return arrayList;
    }

    public static String join(Iterable<?> iterable, String str) {
        if (iterable != null) {
            StringBuilder sb = new StringBuilder();
            Iterator<?> it = iterable.iterator();
            char charAt = str.length() == 1 ? str.charAt(0) : (char) 0;
            while (it.hasNext()) {
                sb.append(it.next());
                if (it.hasNext()) {
                    if (charAt != 0) {
                        sb.append(charAt);
                    } else {
                        sb.append(str);
                    }
                }
            }
            return sb.toString();
        }
        return "";
    }

    public static String join(int[] iArr, String str) {
        if (iArr != null) {
            StringBuilder sb = new StringBuilder(Math.max(16, (str.length() + 1) * iArr.length));
            char charAt = str.length() == 1 ? str.charAt(0) : (char) 0;
            for (int i = 0; i < iArr.length; i++) {
                if (i != 0) {
                    if (charAt != 0) {
                        sb.append(charAt);
                    } else {
                        sb.append(str);
                    }
                }
                sb.append(iArr[i]);
            }
            return sb.toString();
        }
        return "";
    }

    public static String join(String[] strArr, String str) {
        if (strArr != null) {
            StringBuilder sb = new StringBuilder(Math.max(16, (str.length() + 1) * strArr.length));
            char charAt = str.length() == 1 ? str.charAt(0) : (char) 0;
            for (int i = 0; i < strArr.length; i++) {
                if (i != 0) {
                    if (charAt != 0) {
                        sb.append(charAt);
                    } else {
                        sb.append(str);
                    }
                }
                sb.append(strArr[i]);
            }
            return sb.toString();
        }
        return "";
    }
}
