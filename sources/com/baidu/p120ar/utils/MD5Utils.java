package com.baidu.p120ar.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.utils.MD5Utils */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MD5Utils {
    private static char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static MessageDigest messagedigest;

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.err.println(MD5Utils.class.getName() + "初始化失败，MessageDigest不支持MD5Util。");
            e.printStackTrace();
        }
    }

    public static synchronized String getMD5String(String str) {
        String mD5String;
        synchronized (MD5Utils.class) {
            mD5String = getMD5String(str.getBytes(Charset.forName("utf-8")));
        }
        return mD5String;
    }

    public static String getMD5String(byte[] bArr) {
        messagedigest.update(bArr);
        return bufferToHex(messagedigest.digest());
    }

    public static boolean checkPassword(String str, String str2) {
        return getMD5String(str).equals(str2);
    }

    public static String getFileMD5String(File file) {
        try {
            return getFileMD5String(new FileInputStream(file));
        } catch (IOException unused) {
            return null;
        }
    }

    public static String getFileMD5String(InputStream inputStream) {
        try {
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read > 0) {
                        messagedigest.update(bArr, 0, read);
                    } else {
                        return bufferToHex(messagedigest.digest());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                IoUtils.closeQuietly(inputStream);
                return null;
            }
        } finally {
            IoUtils.closeQuietly(inputStream);
        }
    }

    public static String getFileMD5StringOld(File file) throws IOException {
        if (file == null) {
            return null;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        MappedByteBuffer map = fileInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, file.length());
        fileInputStream.close();
        messagedigest.update(map);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte[] bArr) {
        return bufferToHex(bArr, 0, bArr.length);
    }

    private static String bufferToHex(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer(i2 * 2);
        int i3 = i2 + i;
        while (i < i3) {
            appendHexPair(bArr[i], stringBuffer);
            i++;
        }
        return stringBuffer.toString();
    }

    private static void appendHexPair(byte b, StringBuffer stringBuffer) {
        char[] cArr = hexDigits;
        char c = cArr[(b & 240) >> 4];
        char c2 = cArr[b & 15];
        stringBuffer.append(c);
        stringBuffer.append(c2);
    }

    public static String md5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[charArray.length];
            for (int i = 0; i < charArray.length; i++) {
                bArr[i] = (byte) charArray[i];
            }
            byte[] digest = messageDigest.digest(bArr);
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                int i2 = b & 255;
                if (i2 < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i2));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String fileToMD5(File file) {
        FileInputStream fileInputStream;
        String str = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Exception e) {
            e = e;
            fileInputStream = null;
        } catch (Throwable th) {
            th = th;
            fileInputStream = null;
            IoUtils.closeQuietly(fileInputStream);
            throw th;
        }
        try {
            try {
                byte[] bArr = new byte[1024];
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    } else if (read > 0) {
                        messageDigest.update(bArr, 0, read);
                    }
                }
                str = new BigInteger(1, messageDigest.digest()).toString(16);
            } catch (Throwable th2) {
                th = th2;
                IoUtils.closeQuietly(fileInputStream);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            IoUtils.closeQuietly(fileInputStream);
            return str;
        }
        IoUtils.closeQuietly(fileInputStream);
        return str;
    }
}
