package com.ijiami;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class JMEncryptBoxByUnidirectional {
    static {
        new JMEncryptBox();
    }

    private static native ResultData dNormalModePreB(byte[] bArr);

    private static native ResultData dRandomModePreB(byte[] bArr);

    public static ResultData decryptFromBase64(String str, EncryptionMode encryptionMode) {
        return decryptFromBytes(Base64.decode(str), encryptionMode);
    }

    public static ResultData decryptFromBytes(byte[] bArr, EncryptionMode encryptionMode) {
        ResultData decryptFromBytesToBytes = decryptFromBytesToBytes(bArr, encryptionMode);
        decryptFromBytesToBytes.setText(new String(decryptFromBytesToBytes.getData(), "UTF-8"));
        return decryptFromBytesToBytes;
    }

    public static ResultData decryptFromBytesToBytes(byte[] bArr, EncryptionMode encryptionMode) {
        return encryptionMode == EncryptionMode.NormalMode ? dNormalModePreB(bArr) : dRandomModePreB(bArr);
    }

    private static native byte[] eNormalModePreA(byte[] bArr);

    private static native byte[] eRandomModePreA(byte[] bArr);

    public static String encryptToBase64(String str, EncryptionMode encryptionMode) {
        return Base64.encode(encryptToBytes(str, encryptionMode));
    }

    public static byte[] encryptToBytes(String str, EncryptionMode encryptionMode) {
        return encryptToBytesFromBytes(str.getBytes("UTF-8"), encryptionMode);
    }

    public static byte[] encryptToBytesFromBytes(byte[] bArr, EncryptionMode encryptionMode) {
        return encryptionMode == EncryptionMode.NormalMode ? eNormalModePreA(bArr) : eRandomModePreA(bArr);
    }
}
