package com.ijiami;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class JMEncryptBoxByRandom {
    static {
        new JMEncryptBox();
    }

    private static native byte[] decryptByRandomType1(byte[] bArr);

    private static native ResultData decryptByRandomType2(byte[] bArr);

    public static boolean decryptFile(String str, String str2, int i) {
        try {
            return writeFileFromBytes(decryptFromBytesToBytes(readFileToBytes(str), i).getData(), str2);
        } catch (Exception unused) {
            return false;
        }
    }

    public static ResultData decryptFromBase64(String str, int i) {
        return decryptFromBytes(Base64.decode(str), i);
    }

    public static ResultData decryptFromBytes(byte[] bArr, int i) {
        ResultData decryptFromBytesToBytes = decryptFromBytesToBytes(bArr, i);
        decryptFromBytesToBytes.setText(new String(decryptFromBytesToBytes.getData(), "UTF-8"));
        return decryptFromBytesToBytes;
    }

    public static ResultData decryptFromBytesToBytes(byte[] bArr, int i) {
        if (i == 2) {
            return decryptByRandomType2(bArr);
        }
        byte[] decryptByRandomType1 = decryptByRandomType1(bArr);
        ResultData resultData = new ResultData();
        resultData.setData(decryptByRandomType1);
        resultData.setStatus(decryptByRandomType1.length >= 2 ? 1 : -1);
        return resultData;
    }

    private static native byte[] encryptByRandomType1(byte[] bArr);

    private static native byte[] encryptByRandomType2(byte[] bArr);

    public static boolean encryptFile(String str, String str2, int i) {
        try {
            return writeFileFromBytes(encryptToBytesFromBytes(readFileToBytes(str), i), str2);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String encryptToBase64(String str, int i) {
        return Base64.encode(encryptToBytes(str, i));
    }

    public static byte[] encryptToBytes(String str, int i) {
        return encryptToBytesFromBytes(str.getBytes("UTF-8"), i);
    }

    public static byte[] encryptToBytesFromBytes(byte[] bArr, int i) {
        return i == 2 ? encryptByRandomType2(bArr) : encryptByRandomType1(bArr);
    }

    private static byte[] readFileToBytes(String str) {
        FileInputStream fileInputStream = new FileInputStream(new File(str));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read == -1) {
                fileInputStream.close();
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    private static boolean writeFileFromBytes(byte[] bArr, String str) {
        if (bArr == null) {
            return false;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
            fileOutputStream.write(bArr);
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }
}
