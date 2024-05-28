package p000;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: u */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class Hex {
    static {
        "0123456789ABCDEF".toCharArray();
    }

    /* renamed from: a */
    public static String m77a(byte[] bArr) {
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

    /* renamed from: a */
    public static byte[] m78a(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String upperCase = str.toUpperCase();
        int length = upperCase.length() / 2;
        char[] charArray = upperCase.toCharArray();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (m79a(charArray[i2 + 1]) | (m79a(charArray[i2]) << 4));
        }
        return bArr;
    }

    /* renamed from: a */
    public static byte m79a(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
