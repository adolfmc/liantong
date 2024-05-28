package cn.ltzf.passguard;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class LTPassGuardEncrypt {
    static {
        System.loadLibrary("PassGuard");
    }

    public static native String Decrypt(String str, int i);

    public static native String Decrypt2(String str);

    public static native String Encrypt(String str, int i);

    public static native String MSRSAEncrypt(String str, String str2, String str3);

    public static native String RSAEncrypt(String str, String str2);

    private static String byteArrayToHexString(byte[] bArr) {
        int length = bArr.length;
        String str = new String();
        for (int i = 0; i < length; i++) {
            StringBuilder m22016a = C1730a.m22016a(str);
            m22016a.append(Integer.toHexString((bArr[i] >> 4) & 15));
            StringBuilder m22016a2 = C1730a.m22016a(m22016a.toString());
            m22016a2.append(Integer.toHexString(bArr[i] & 15));
            str = m22016a2.toString();
        }
        return str;
    }

    public static native String getCipher2(String str, String str2, String str3);

    public static native String getCipher21(String str, String str2);

    public static native String getCipher3(String str);

    public static native String getCipher4(String str, String str2);

    public static native String getCipherText(String str, String str2);

    public static String getCipherText(String str, String str2, String str3) {
        return getCipherText(RSAEncrypt(str, str3), str2);
    }

    public static native String getKey();

    public static String getMSRSAEncrypt(String str, String str2, String str3) {
        return MSRSAEncrypt(str, str2, str3);
    }

    public static native String getMd5(String str);

    public static String getSM2SM4CipherText(String str, String str2, String str3) {
        return getCipher4(str2, getCipher21(str, str3));
    }

    private static byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) (Character.digit(str.charAt(i + 1), 16) + (Character.digit(str.charAt(i), 16) << 4));
        }
        return bArr;
    }

    public static native int makeKey();

    public static native int[] passlevel(String str);
}
