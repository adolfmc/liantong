package com.huawei.secure.android.common.encrypt.hash;

import android.text.TextUtils;
import com.huawei.secure.android.common.encrypt.utils.C5105a;
import com.huawei.secure.android.common.encrypt.utils.C5106b;
import com.huawei.secure.android.common.encrypt.utils.HexUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class FileSHA256 {

    /* renamed from: a */
    private static final int f11905a = 8192;

    /* renamed from: b */
    private static final String f11906b = "SHA-256";

    /* renamed from: c */
    private static final String f11907c = "FileSHA256";

    /* renamed from: d */
    private static final String f11908d = "";

    /* renamed from: e */
    private static final String[] f11909e = {"SHA-256", "SHA-384", "SHA-512"};

    /* renamed from: a */
    private static boolean m13992a(File file) {
        return file != null && file.exists() && file.length() > 0;
    }

    public static String fileSHA256Encrypt(File file) {
        return fileSHAEncrypt(file, "SHA-256");
    }

    public static String fileSHAEncrypt(File file, String str) {
        FileInputStream fileInputStream;
        MessageDigest messageDigest;
        if (!TextUtils.isEmpty(str) && m13991a(str)) {
            if (!m13992a(file)) {
                C5106b.m13942b(f11907c, "file is not valid");
                return "";
            }
            try {
                try {
                    messageDigest = MessageDigest.getInstance(str);
                    fileInputStream = new FileInputStream(file);
                } catch (IOException e) {
                    e = e;
                    fileInputStream = null;
                } catch (NoSuchAlgorithmException e2) {
                    e = e2;
                    fileInputStream = null;
                } catch (Throwable th) {
                    th = th;
                    C5105a.m13954a((InputStream) null);
                    throw th;
                }
                try {
                    byte[] bArr = new byte[8192];
                    boolean z = false;
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        messageDigest.update(bArr, 0, read);
                        z = true;
                    }
                    r0 = z ? HexUtil.byteArray2HexStr(messageDigest.digest()) : null;
                    C5105a.m13954a((InputStream) fileInputStream);
                } catch (IOException e3) {
                    e = e3;
                    C5106b.m13942b(f11907c, "IOException" + e.getMessage());
                    C5105a.m13954a((InputStream) fileInputStream);
                    return r0;
                } catch (NoSuchAlgorithmException e4) {
                    e = e4;
                    C5106b.m13942b(f11907c, "NoSuchAlgorithmException" + e.getMessage());
                    C5105a.m13954a((InputStream) fileInputStream);
                    return r0;
                }
                return r0;
            } catch (Throwable th2) {
                th = th2;
                C5105a.m13954a((InputStream) null);
                throw th;
            }
        }
        C5106b.m13942b(f11907c, "algorithm is empty or not safe");
        return "";
    }

    public static String inputStreamSHA256Encrypt(InputStream inputStream) {
        return inputStream == null ? "" : inputStreamSHAEncrypt(inputStream, "SHA-256");
    }

    public static String inputStreamSHAEncrypt(InputStream inputStream, String str) {
        if (inputStream == null) {
            return "";
        }
        byte[] bArr = new byte[8192];
        try {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(str);
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read < 0) {
                        return HexUtil.byteArray2HexStr(messageDigest.digest());
                    }
                    if (read > 0) {
                        messageDigest.update(bArr, 0, read);
                    }
                }
            } catch (IOException | NoSuchAlgorithmException unused) {
                C5106b.m13942b(f11907c, "inputstraem exception");
                C5105a.m13954a(inputStream);
                return "";
            }
        } finally {
            C5105a.m13954a(inputStream);
        }
    }

    public static boolean validateFileSHA(File file, String str, String str2) {
        if (!TextUtils.isEmpty(str) && m13991a(str2)) {
            return str.equals(fileSHAEncrypt(file, str2));
        }
        C5106b.m13942b(f11907c, "hash value is null || algorithm is illegal");
        return false;
    }

    public static boolean validateFileSHA256(File file, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.equalsIgnoreCase(fileSHA256Encrypt(file));
    }

    public static boolean validateInputStreamSHA(InputStream inputStream, String str, String str2) {
        if (!TextUtils.isEmpty(str) && m13991a(str2)) {
            return str.equals(inputStreamSHAEncrypt(inputStream, str2));
        }
        C5106b.m13942b(f11907c, "hash value is null || algorithm is illegal");
        return false;
    }

    public static boolean validateInputStreamSHA256(InputStream inputStream, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.equals(inputStreamSHA256Encrypt(inputStream));
    }

    /* renamed from: a */
    private static boolean m13991a(String str) {
        for (String str2 : f11909e) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }
}
