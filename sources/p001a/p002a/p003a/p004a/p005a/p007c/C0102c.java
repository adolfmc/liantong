package p001a.p002a.p003a.p004a.p005a.p007c;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.c.c */
/* loaded from: E:\567196_dexfile_execute.dex */
public class C0102c {

    /* renamed from: a */
    public static final char[] f52a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: b */
    public static final byte[] f53b;

    static {
        byte[] bArr = new byte[128];
        f53b = bArr;
        Arrays.fill(bArr, (byte) -1);
        for (int i = 48; i <= 57; i++) {
            f53b[i] = (byte) (i - 48);
        }
        for (char c = 'a'; c <= 'f'; c = (char) (c + 1)) {
            f53b[c] = (byte) ((c - 'a') + 10);
        }
        for (char c2 = 'A'; c2 <= 'F'; c2 = (char) (c2 + 1)) {
            f53b[c2] = (byte) ((c2 - 'A') + 10);
        }
    }

    /* renamed from: a */
    public static int m24397a(byte b, byte b2, byte b3, byte b4) {
        return ((b & 255) << 24) | ((b2 & 255) << 16) | ((b3 & 255) << 8) | (b4 & 255);
    }

    /* renamed from: a */
    public static String m24392a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            char[] cArr2 = f52a;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }

    /* renamed from: a */
    public static short m24398a(byte b, byte b2) {
        return (short) (((short) ((b & 255) << 8)) | ((short) (b2 & 255)));
    }

    /* renamed from: a */
    public static String m24391a(byte[] bArr, int i) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return m24390a(bArr, i, bArr.length);
    }

    /* renamed from: a */
    public static String m24390a(byte[] bArr, int i, int i2) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        if (i > i2) {
            throw new IllegalArgumentException(i + " > " + i2);
        } else if (i >= 0) {
            if (i <= bArr.length) {
                if (i2 <= bArr.length) {
                    int i3 = i2 - i;
                    char[] cArr = new char[i3 * 2];
                    for (int i4 = 0; i4 < i3; i4++) {
                        int i5 = bArr[i4 + i] & 255;
                        int i6 = i4 * 2;
                        char[] cArr2 = f52a;
                        cArr[i6] = cArr2[i5 >>> 4];
                        cArr[i6 + 1] = cArr2[i5 & 15];
                    }
                    return new String(cArr);
                }
                throw new ArrayIndexOutOfBoundsException(i + " > " + bArr.length);
            }
            throw new ArrayIndexOutOfBoundsException(i + " > " + bArr.length);
        } else {
            throw new ArrayIndexOutOfBoundsException(i + " < 0");
        }
    }

    /* renamed from: a */
    public static String m24395a(int i, int i2) {
        switch (i2) {
            case 1:
                return String.format("%02x", Integer.valueOf(i & 255));
            case 2:
                return String.format("%04x", Integer.valueOf(i & 65535));
            case 3:
                return String.format("%06x", Integer.valueOf(i & 16777215));
            case 4:
                return String.format("%08x", Integer.valueOf(i & (-1)));
            default:
                return null;
        }
    }

    /* renamed from: a */
    public static byte[] m24394a(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if ((charAt >= '0' && charAt <= '9') || ((charAt >= 'a' && charAt <= 'f') || (charAt >= 'A' && charAt <= 'F'))) {
                sb.append(charAt);
            }
        }
        String sb2 = sb.toString();
        int length2 = sb2.length() / 2;
        byte[] bArr = new byte[length2];
        for (int i2 = 0; i2 < length2; i2++) {
            int i3 = i2 * 2;
            bArr[i2] = (byte) ((f53b[sb2.charAt(i3)] << 4) | f53b[sb2.charAt(i3 + 1)]);
        }
        return bArr;
    }

    /* renamed from: a */
    public static byte[] m24393a(short s) {
        return new byte[]{(byte) ((s >> 8) & 255), (byte) (s & 255)};
    }

    /* renamed from: a */
    public static byte[] m24396a(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    /* renamed from: a */
    public static byte[] m24389a(Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
        for (int i = 0; i < objArr.length; i++) {
            if (objArr[i] != null) {
                try {
                    if (objArr[i] instanceof String) {
                        byte[] m24394a = m24394a((String) objArr[i]);
                        if (m24394a != null) {
                            byteArrayOutputStream.write(m24394a);
                        }
                    } else if (objArr[i] instanceof byte[]) {
                        byteArrayOutputStream.write((byte[]) objArr[i]);
                    } else if (objArr[i] instanceof Byte) {
                        byteArrayOutputStream.write(((Byte) objArr[i]).byteValue() & 255);
                    } else if (objArr[i] instanceof Short) {
                        byteArrayOutputStream.write(((Short) objArr[i]).shortValue() & 255);
                    } else if (objArr[i] instanceof Integer) {
                        byteArrayOutputStream.write(((Integer) objArr[i]).intValue() & 255);
                    } else if (objArr[i] instanceof Long) {
                        byteArrayOutputStream.write((int) (((Long) objArr[i]).longValue() & 255));
                    } else {
                        PrintStream printStream = System.out;
                        printStream.println("不被支持的参数[" + i + "]");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
}
