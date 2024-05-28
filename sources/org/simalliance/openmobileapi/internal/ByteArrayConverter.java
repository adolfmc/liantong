package org.simalliance.openmobileapi.internal;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class ByteArrayConverter {
    public static String byteArrayToHexString(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append(String.format("%02x", Integer.valueOf(bArr[i + i3] & 255)));
        }
        return sb.toString();
    }

    public static int byteArrayToInt(byte[] bArr) {
        switch (bArr.length) {
            case 0:
                return 0;
            case 1:
                return bArr[0] & 255;
            case 2:
                return (bArr[1] & 255) | ((bArr[0] & 255) << 8);
            case 3:
                return (bArr[2] & 255) | ((bArr[0] & 255) << 16) | ((bArr[1] & 255) << 8);
            default:
                return (bArr[3] & 255) | ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8);
        }
    }

    public static String byteArrayToPathString(byte[] bArr) {
        if (bArr.length % 2 == 0) {
            byte[] bArr2 = new byte[2];
            String str = "";
            for (int i = 0; i < bArr.length; i += 2) {
                System.arraycopy(bArr, i, bArr2, 0, 2);
                String byteArrayToHexString = byteArrayToHexString(bArr2);
                if (!byteArrayToHexString.equalsIgnoreCase("3F00")) {
                    str = str.concat(byteArrayToHexString);
                    if (i != bArr.length - 2) {
                        str = str.concat(":");
                    }
                }
            }
            return str;
        }
        throw new IllegalArgumentException("Invald path");
    }

    public static byte[] hexStringToByteArray(String str, int i, int i2) {
        if (i2 % 2 == 0) {
            String upperCase = str.toUpperCase();
            byte[] bArr = new byte[upperCase.length() / 2];
            for (int i3 = 0; i3 < i2; i3 += 2) {
                char charAt = upperCase.charAt(i3 + i);
                char charAt2 = upperCase.charAt(i3 + 1 + i);
                if (isHexChar(charAt) && isHexChar(charAt2)) {
                    bArr[i3 / 2] = (byte) ((Character.digit(charAt, 16) << 4) + Character.digit(charAt2, 16));
                } else {
                    throw new IllegalArgumentException("Invalid char found");
                }
            }
            return bArr;
        }
        throw new IllegalArgumentException("length must be multiple of 2");
    }

    public static byte[] intToByteArray(int i) {
        return new byte[]{(byte) (i >>> 24), (byte) (i >>> 16), (byte) (i >>> 8), (byte) i};
    }

    public static boolean isHexChar(char c) {
        if (Character.isLowerCase(c)) {
            c = Character.toUpperCase(c);
        }
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F');
    }

    public static String byteArrayToHexString(byte[] bArr) {
        return bArr == null ? "" : byteArrayToHexString(bArr, 0, bArr.length);
    }

    public static byte[] hexStringToByteArray(String str) {
        return hexStringToByteArray(str, 0, str.length());
    }
}
