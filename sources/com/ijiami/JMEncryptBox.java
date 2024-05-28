package com.ijiami;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class JMEncryptBox {
    static {
        try {
            System.loadLibrary("JMEncryptBox");
        } catch (Exception unused) {
        }
    }

    private static void byte2hex(byte b, StringBuffer stringBuffer) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        stringBuffer.append(cArr[(b & 240) >> 4]);
        stringBuffer.append(cArr[b & 15]);
    }

    private static native byte[] decryptByType1(byte[] bArr);

    private static native ResultData decryptByType2(byte[] bArr);

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
            return decryptByType2(bArr);
        }
        byte[] decryptByType1 = decryptByType1(bArr);
        ResultData resultData = new ResultData();
        resultData.setData(decryptByType1);
        resultData.setStatus(decryptByType1.length >= 2 ? 1 : -1);
        return resultData;
    }

    private static native byte[] encryptByType1(byte[] bArr);

    private static native byte[] encryptByType2(byte[] bArr);

    public static String encryptToBase64(String str, int i) {
        return Base64.encode(encryptToBytes(str, i));
    }

    public static byte[] encryptToBytes(String str, int i) {
        return encryptToBytesFromBytes(str.getBytes("UTF-8"), i);
    }

    public static byte[] encryptToBytesFromBytes(byte[] bArr, int i) {
        return i == 2 ? encryptByType2(bArr) : encryptByType1(bArr);
    }

    private static String getFinger(String str, byte[] bArr) {
        try {
            return toHexString(MessageDigest.getInstance(str).digest(bArr));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getVersion() {
        return "3.2.0.02";
    }

    public static byte[] readFileToBytes(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                inputStream.close();
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    private static native String setKey(String str);

    public static StatusInfo setLicenseKey(String str) {
        String str2;
        try {
            String[] split = setKey(str).split(";");
            StatusInfo statusInfo = new StatusInfo();
            if (split.length <= 1) {
                if (split.length > 0) {
                    statusInfo.setStatusCode(Integer.valueOf(split[0]).intValue());
                    str2 = "";
                }
                return statusInfo;
            }
            statusInfo.setStatusCode(Integer.valueOf(split[0]).intValue());
            str2 = split[1];
            statusInfo.setExpiredDate(str2);
            return statusInfo;
        } catch (Exception unused) {
            return new StatusInfo();
        }
    }

    private static String toHexString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            byte2hex(b, stringBuffer);
        }
        return stringBuffer.toString();
    }

    public static boolean writeFileFromBytes(byte[] bArr, String str) {
        if (bArr == null) {
            return false;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
            fileOutputStream.write(bArr);
            fileOutputStream.close();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }
}
