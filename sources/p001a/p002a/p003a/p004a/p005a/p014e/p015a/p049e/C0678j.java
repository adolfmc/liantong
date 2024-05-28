package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.e.j */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C0678j {
    /* renamed from: a */
    public static void m22444a(char[] cArr, OutputStream outputStream) {
        int i = 0;
        while (i < cArr.length) {
            char c = cArr[i];
            if (c < 128) {
                outputStream.write(c);
            } else if (c < 2048) {
                outputStream.write((c >> 6) | 192);
                outputStream.write((c & '?') | 128);
            } else if (c >= 55296 && c <= 57343) {
                i++;
                if (i < cArr.length) {
                    char c2 = cArr[i];
                    if (c <= 56319) {
                        int i2 = (((c & 1023) << 10) | (c2 & 1023)) + 65536;
                        outputStream.write((i2 >> 18) | 240);
                        outputStream.write(((i2 >> 12) & 63) | 128);
                        outputStream.write(((i2 >> 6) & 63) | 128);
                        outputStream.write((i2 & 63) | 128);
                    } else {
                        throw new IllegalStateException("invalid UTF-16 codepoint");
                    }
                } else {
                    throw new IllegalStateException("invalid UTF-16 codepoint");
                }
            } else {
                outputStream.write((c >> '\f') | 224);
                outputStream.write(((c >> 6) & 63) | 128);
                outputStream.write((c & '?') | 128);
            }
            i++;
        }
    }

    /* renamed from: b */
    public static byte[] m22441b(char[] cArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            m22444a(cArr, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            throw new IllegalStateException("cannot encode string to byte array!");
        }
    }

    /* renamed from: c */
    public static String m22439c(byte[] bArr) {
        char c;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i2 < bArr.length) {
            i3++;
            if ((bArr[i2] & 240) == 240) {
                i3++;
                i2 += 4;
            } else if ((bArr[i2] & 224) == 224) {
                i2 += 3;
            } else {
                i2 = (bArr[i2] & 192) == 192 ? i2 + 2 : i2 + 1;
            }
        }
        char[] cArr = new char[i3];
        int i4 = 0;
        while (i < bArr.length) {
            if ((bArr[i] & 240) == 240) {
                int i5 = (((((bArr[i] & 3) << 18) | ((bArr[i + 1] & 63) << 12)) | ((bArr[i + 2] & 63) << 6)) | (bArr[i + 3] & 63)) - 65536;
                c = (char) ((i5 & 1023) | 56320);
                cArr[i4] = (char) ((i5 >> 10) | 55296);
                i += 4;
                i4++;
            } else if ((bArr[i] & 224) == 224) {
                c = (char) (((bArr[i] & 15) << 12) | ((bArr[i + 1] & 63) << 6) | (bArr[i + 2] & 63));
                i += 3;
            } else if ((bArr[i] & 208) == 208) {
                c = (char) (((bArr[i] & 31) << 6) | (bArr[i + 1] & 63));
                i += 2;
            } else if ((bArr[i] & 192) == 192) {
                c = (char) (((bArr[i] & 31) << 6) | (bArr[i + 1] & 63));
                i += 2;
            } else {
                c = (char) (bArr[i] & 255);
                i++;
            }
            cArr[i4] = c;
            i4++;
        }
        return new String(cArr);
    }

    /* renamed from: d */
    public static String m22438d(String str) {
        char[] charArray = str.toCharArray();
        boolean z = false;
        for (int i = 0; i != charArray.length; i++) {
            char c = charArray[i];
            if ('a' <= c && 'z' >= c) {
                charArray[i] = (char) ((c - 'a') + 65);
                z = true;
            }
        }
        return z ? new String(charArray) : str;
    }

    /* renamed from: b */
    public static String m22443b(String str) {
        char[] charArray = str.toCharArray();
        boolean z = false;
        for (int i = 0; i != charArray.length; i++) {
            char c = charArray[i];
            if ('A' <= c && 'Z' >= c) {
                charArray[i] = (char) ((c - 'A') + 97);
                z = true;
            }
        }
        return z ? new String(charArray) : str;
    }

    /* renamed from: b */
    public static String m22442b(byte[] bArr) {
        return new String(m22446a(bArr));
    }

    /* renamed from: a */
    public static byte[] m22445a(char[] cArr) {
        int length = cArr.length;
        byte[] bArr = new byte[length];
        for (int i = 0; i != length; i++) {
            bArr[i] = (byte) cArr[i];
        }
        return bArr;
    }

    /* renamed from: a */
    public static byte[] m22448a(String str) {
        int length = str.length();
        byte[] bArr = new byte[length];
        for (int i = 0; i != length; i++) {
            bArr[i] = (byte) str.charAt(i);
        }
        return bArr;
    }

    /* renamed from: c */
    public static byte[] m22440c(String str) {
        return m22441b(str.toCharArray());
    }

    /* renamed from: a */
    public static char[] m22446a(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length];
        for (int i = 0; i != length; i++) {
            cArr[i] = (char) (bArr[i] & 255);
        }
        return cArr;
    }

    /* renamed from: a */
    public static String[] m22447a(String str, char c) {
        Vector vector = new Vector();
        boolean z = true;
        while (z) {
            int indexOf = str.indexOf(c);
            if (indexOf > 0) {
                vector.addElement(str.substring(0, indexOf));
                str = str.substring(indexOf + 1);
            } else {
                vector.addElement(str);
                z = false;
            }
        }
        int size = vector.size();
        String[] strArr = new String[size];
        for (int i = 0; i != size; i++) {
            strArr[i] = (String) vector.elementAt(i);
        }
        return strArr;
    }
}
