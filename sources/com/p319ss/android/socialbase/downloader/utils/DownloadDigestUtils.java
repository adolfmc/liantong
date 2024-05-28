package com.p319ss.android.socialbase.downloader.utils;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.utils.DownloadDigestUtils */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class DownloadDigestUtils {
    static final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String toHexString(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("bytes is null");
        }
        return toHexString(bArr, 0, bArr.length);
    }

    public static String toHexString(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new NullPointerException("bytes is null");
        }
        if (i < 0 || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        int i3 = i2 * 2;
        char[] cArr = new char[i3];
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = bArr[i5 + i] & 255;
            int i7 = i4 + 1;
            char[] cArr2 = HEX_CHARS;
            cArr[i4] = cArr2[i6 >> 4];
            i4 = i7 + 1;
            cArr[i7] = cArr2[i6 & 15];
        }
        return new String(cArr, 0, i3);
    }

    public static String md5Hex(File file) {
        FileInputStream fileInputStream;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            if (messageDigest == null) {
                DownloadUtils.safeClose(null);
                return null;
            }
            fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = fileInputStream.read(bArr, 0, bArr.length);
                    if (read > 0) {
                        messageDigest.update(bArr, 0, read);
                    } else {
                        String hexString = toHexString(messageDigest.digest());
                        DownloadUtils.safeClose(fileInputStream);
                        return hexString;
                    }
                }
            } catch (Exception unused) {
                DownloadUtils.safeClose(fileInputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                DownloadUtils.safeClose(fileInputStream);
                throw th;
            }
        } catch (Exception unused2) {
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
    }

    public static byte[] hexStringToBytes(String str) throws IllegalArgumentException {
        if (str == null || str.length() % 2 == 1) {
            throw new IllegalArgumentException("hexBinary needs to be even-length: " + str);
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(charArray[i], 16) << 4) + Character.digit(charArray[i + 1], 16));
        }
        return bArr;
    }

    public static String md5Hex(String str) {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    messageDigest.update(str.getBytes("UTF-8"));
                    return toHexString(messageDigest.digest());
                }
            } catch (Exception unused) {
                return null;
            }
        }
        return null;
    }

    public static String md5Hex(byte[] bArr) {
        if (bArr != null) {
            try {
                if (bArr.length != 0) {
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    messageDigest.update(bArr);
                    return toHexString(messageDigest.digest());
                }
            } catch (Exception unused) {
                return null;
            }
        }
        return null;
    }

    public static String md5Hex(byte[] bArr, int i, int i2) {
        if (bArr != null && i >= 0 && i2 > 0) {
            try {
                if (i + i2 <= bArr.length) {
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    messageDigest.update(bArr, i, i2);
                    return toHexString(messageDigest.digest());
                }
            } catch (Exception unused) {
                return null;
            }
        }
        return null;
    }
}
