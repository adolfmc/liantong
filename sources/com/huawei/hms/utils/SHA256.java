package com.huawei.hms.utils;

import com.huawei.hms.support.log.HMSLog;
import java.io.BufferedInputStream;
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
public abstract class SHA256 {
    public static byte[] digest(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA-256").digest(bArr);
        } catch (NoSuchAlgorithmException e) {
            HMSLog.m14112e("SHA256", "NoSuchAlgorithmException" + e.getMessage());
            return new byte[0];
        }
    }

    public static byte[] digest(File file) {
        BufferedInputStream bufferedInputStream;
        MessageDigest messageDigest;
        int i;
        BufferedInputStream bufferedInputStream2 = null;
        try {
            try {
                messageDigest = MessageDigest.getInstance("SHA-256");
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                try {
                    byte[] bArr = new byte[4096];
                    i = 0;
                    while (true) {
                        int read = bufferedInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        i += read;
                        messageDigest.update(bArr, 0, read);
                    }
                } catch (IOException | NoSuchAlgorithmException unused) {
                    bufferedInputStream2 = bufferedInputStream;
                    HMSLog.m14112e("SHA256", "An exception occurred while computing file 'SHA-256'.");
                    IOUtils.closeQuietly((InputStream) bufferedInputStream2);
                    return new byte[0];
                } catch (Throwable th) {
                    th = th;
                    IOUtils.closeQuietly((InputStream) bufferedInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = bufferedInputStream2;
            }
        } catch (IOException | NoSuchAlgorithmException unused2) {
        }
        if (i > 0) {
            byte[] digest = messageDigest.digest();
            IOUtils.closeQuietly((InputStream) bufferedInputStream);
            return digest;
        }
        IOUtils.closeQuietly((InputStream) bufferedInputStream);
        return new byte[0];
    }
}
