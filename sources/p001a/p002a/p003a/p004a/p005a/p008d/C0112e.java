package p001a.p002a.p003a.p004a.p005a.p008d;

import com.unionpay.tsmservice.request.GetTransElementsRequestParams;
import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.d.e */
/* loaded from: E:\567196_dexfile_execute.dex */
public class C0112e {

    /* renamed from: a */
    public static final char[] f97a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: b */
    public static final char[] f98b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: a */
    public static byte[] m24298a(long j) {
        byte[] bArr = new byte[8];
        for (int i = 0; i < 8; i++) {
            bArr[i] = (byte) ((j >> (i * 8)) & 255);
        }
        return bArr;
    }

    /* renamed from: b */
    public static byte[] m24288b(int i) {
        return new byte[]{(byte) ((i >> 0) & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)};
    }

    /* renamed from: c */
    public static int m24280c(byte[] bArr) {
        return ((bArr[3] & 255) << 24) | 0 | ((bArr[0] & 255) << 0) | ((bArr[1] & 255) << 8) | ((bArr[2] & 255) << 16);
    }

    /* renamed from: d */
    public static int m24278d(String str) {
        int length;
        String upperCase = str.toUpperCase();
        int i = 0;
        for (int length2 = upperCase.length(); length2 > 0; length2--) {
            char charAt = upperCase.charAt(length2 - 1);
            i = (int) (i + (Math.pow(16.0d, length - length2) * ((charAt < '0' || charAt > '9') ? charAt - '7' : charAt - '0')));
        }
        return i;
    }

    /* renamed from: e */
    public static char[] m24275e(byte[] bArr) {
        return m24291a(bArr, true);
    }

    /* renamed from: f */
    public static byte[] m24274f(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String upperCase = str.toUpperCase();
        int length = upperCase.length() / 2;
        char[] charArray = upperCase.toCharArray();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (m24302a(charArray[i2 + 1]) | (m24302a(charArray[i2]) << 4));
        }
        return bArr;
    }

    /* renamed from: g */
    public static String m24271g(byte[] bArr) {
        return m24279c(bArr, true);
    }

    /* renamed from: h */
    public static void m24270h(byte[] bArr) {
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            System.out.print("0x" + hexString.toUpperCase() + ",");
        }
        System.out.println("");
    }

    /* renamed from: e */
    public static String m24276e(String str) {
        String upperCase = str.toUpperCase();
        String str2 = "";
        int length = upperCase.length();
        for (int i = 0; i < length; i++) {
            char charAt = upperCase.charAt(i);
            switch (charAt) {
                case '0':
                    str2 = str2 + "0000";
                    break;
                case '1':
                    str2 = str2 + "0001";
                    break;
                case '2':
                    str2 = str2 + "0010";
                    break;
                case '3':
                    str2 = str2 + "0011";
                    break;
                case '4':
                    str2 = str2 + "0100";
                    break;
                case '5':
                    str2 = str2 + GetTransElementsRequestParams.TRANS_TYPE_DOWNLOAD_APPLY;
                    break;
                case '6':
                    str2 = str2 + "0110";
                    break;
                case '7':
                    str2 = str2 + "0111";
                    break;
                case '8':
                    str2 = str2 + "1000";
                    break;
                case '9':
                    str2 = str2 + "1001";
                    break;
                default:
                    switch (charAt) {
                        case 'A':
                            str2 = str2 + "1010";
                            continue;
                        case 'B':
                            str2 = str2 + "1011";
                            continue;
                        case 'C':
                            str2 = str2 + "1100";
                            continue;
                        case 'D':
                            str2 = str2 + "1101";
                            continue;
                        case 'E':
                            str2 = str2 + "1110";
                            continue;
                        case 'F':
                            str2 = str2 + "1111";
                            continue;
                    }
            }
        }
        return str2;
    }

    /* renamed from: g */
    public static byte[] m24272g(String str) {
        if (str.length() % 2 == 0) {
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[str.length() / 2];
            int length = str.length();
            int i = 0;
            int i2 = 0;
            while (i < length) {
                StringBuilder sb = new StringBuilder();
                sb.append("");
                int i3 = i + 1;
                sb.append(charArray[i]);
                sb.append(charArray[i3]);
                bArr[i2] = new Integer(Integer.parseInt(sb.toString(), 16) & 255).byteValue();
                i = i3 + 1;
                i2++;
            }
            return bArr;
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: a */
    public static byte[] m24294a(BigInteger bigInteger) {
        if (bigInteger == null) {
            return null;
        }
        if (bigInteger.toByteArray().length == 33) {
            byte[] bArr = new byte[32];
            System.arraycopy(bigInteger.toByteArray(), 1, bArr, 0, 32);
            return bArr;
        } else if (bigInteger.toByteArray().length == 32) {
            return bigInteger.toByteArray();
        } else {
            byte[] bArr2 = new byte[32];
            for (int i = 0; i < 32 - bigInteger.toByteArray().length; i++) {
                bArr2[i] = 0;
            }
            System.arraycopy(bigInteger.toByteArray(), 0, bArr2, 32 - bigInteger.toByteArray().length, bigInteger.toByteArray().length);
            return bArr2;
        }
    }

    /* renamed from: b */
    public static String m24284b(byte[] bArr, boolean z) {
        return m24283b(bArr, z ? f97a : f98b);
    }

    /* renamed from: b */
    public static String m24283b(byte[] bArr, char[] cArr) {
        return new String(m24290a(bArr, cArr));
    }

    /* renamed from: b */
    public static String m24287b(String str) {
        String str2 = "";
        int length = str.length();
        for (int i = 0; i < length; i++) {
            str2 = str2 + Integer.toHexString(str.charAt(i));
        }
        return str2;
    }

    /* renamed from: c */
    public static String m24279c(byte[] bArr, boolean z) {
        String str = "";
        for (int i = 0; i < bArr.length; i++) {
            str = str + Integer.toString((bArr[i] & 255) + 256, 16).substring(1);
        }
        return z ? str.toUpperCase() : str;
    }

    /* renamed from: b */
    public static int m24286b(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    /* renamed from: c */
    public static int m24282c(String str) {
        int length;
        int i = 0;
        for (int length2 = str.length(); length2 > 0; length2--) {
            i = (int) (i + (Math.pow(2.0d, length - length2) * (str.charAt(length2 - 1) - '0')));
        }
        return i;
    }

    /* renamed from: d */
    public static String m24277d(byte[] bArr) {
        String str = "";
        for (int i = 0; i < bArr.length; i++) {
            str = str + ((char) bArr[i]);
        }
        return str;
    }

    /* renamed from: b */
    public static String m24285b(byte[] bArr) {
        if (bArr != null) {
            String str = "";
            for (byte b : bArr) {
                String hexString = Integer.toHexString(b & 255);
                str = hexString.length() == 1 ? str + "0" + hexString : str + hexString;
            }
            return str.toUpperCase();
        }
        throw new IllegalArgumentException("Argument b ( byte array ) is null! ");
    }

    /* renamed from: f */
    public static String m24273f(byte[] bArr) {
        return m24284b(bArr, true);
    }

    /* renamed from: c */
    public static String m24281c(String str, int i) {
        String str2 = "";
        for (int i2 = 0; i2 < i - str.length(); i2++) {
            str2 = "0" + str2;
        }
        return (str2 + str).substring(0, i);
    }

    /* renamed from: a */
    public static BigInteger m24293a(byte[] bArr) {
        if (bArr[0] < 0) {
            byte[] bArr2 = new byte[bArr.length + 1];
            bArr2[0] = 0;
            System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
            return new BigInteger(bArr2);
        }
        return new BigInteger(bArr);
    }

    /* renamed from: a */
    public static byte m24302a(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /* renamed from: a */
    public static char[] m24291a(byte[] bArr, boolean z) {
        return m24290a(bArr, z ? f97a : f98b);
    }

    /* renamed from: a */
    public static char[] m24290a(byte[] bArr, char[] cArr) {
        int length = bArr.length;
        char[] cArr2 = new char[length << 1];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr2[i] = cArr[(bArr[i2] & 240) >>> 4];
            i = i3 + 1;
            cArr2[i3] = cArr[bArr[i2] & 15];
        }
        return cArr2;
    }

    /* renamed from: a */
    public static byte[] m24289a(char[] cArr) {
        int length = cArr.length;
        if ((length & 1) == 0) {
            byte[] bArr = new byte[length >> 1];
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = i + 1;
                i = i3 + 1;
                bArr[i2] = (byte) (((m24301a(cArr[i], i) << 4) | m24301a(cArr[i3], i3)) & 255);
                i2++;
            }
            return bArr;
        }
        throw new RuntimeException("Odd number of characters.");
    }

    /* renamed from: a */
    public static int m24301a(char c, int i) {
        int digit = Character.digit(c, 16);
        if (digit != -1) {
            return digit;
        }
        throw new RuntimeException("Illegal hexadecimal character " + c + " at index " + i);
    }

    /* renamed from: a */
    public static String m24296a(String str, int i) {
        String str2 = "";
        int i2 = 0;
        while (i2 < str.length() / i) {
            int i3 = i2 * i;
            i2++;
            str2 = str2 + ((char) m24278d(str.substring(i3, i2 * i)));
        }
        return str2;
    }

    /* renamed from: a */
    public static String m24297a(String str) {
        String str2 = "";
        int length = str.length() / 2;
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            str2 = str2 + String.valueOf((char) m24278d(str.substring(i2, i2 + 2)));
        }
        return str2;
    }

    /* renamed from: a */
    public static String m24299a(int i, int i2) {
        String hexString = Integer.toHexString(i);
        if (hexString.length() % 2 == 1) {
            hexString = "0" + hexString;
        }
        return m24281c(hexString.toUpperCase(), i2);
    }

    /* renamed from: a */
    public static String m24300a(int i) {
        String hexString = Integer.toHexString(i);
        if (hexString.length() % 2 == 1) {
            hexString = "0" + hexString;
        }
        return hexString.toUpperCase();
    }

    /* renamed from: a */
    public static int m24295a(String str, int i, int i2) {
        try {
            return Integer.parseInt(str, i2);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    /* renamed from: a */
    public static byte[] m24292a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr2[i3] = bArr[i3 + i];
        }
        return bArr2;
    }
}
