package com.mob.tools.utils;

import android.text.TextUtils;
import android.util.Base64;
import com.mob.commons.C5855l;
import com.mob.commons.C5873u;
import com.mob.tools.MobLog;
import com.mob.tools.proguard.PublicMemberKeeper;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.util.zip.CRC32;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.SecretKeySpec;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class Data implements PublicMemberKeeper {
    public static byte[] SHA1(String str) throws Throwable {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return SHA1(str.getBytes("utf-8"));
    }

    public static byte[] SHA1(byte[] bArr) throws Throwable {
        MessageDigest messageDigest = MessageDigest.getInstance(C5855l.m12238a("005Qglhlgnjmjh"));
        messageDigest.update(bArr);
        return messageDigest.digest();
    }

    public static byte[] AES128Encode(String str, String str2) throws Throwable {
        if (str == null || str2 == null) {
            return null;
        }
        byte[] bytes = str.getBytes("UTF-8");
        byte[] bArr = new byte[16];
        System.arraycopy(bytes, 0, bArr, 0, Math.min(bytes.length, 16));
        return AES128Encode(bArr, str2);
    }

    public static Cipher getCipher(String str, String str2) throws Throwable {
        Cipher cipher = null;
        if (!TextUtils.isEmpty(str2)) {
            try {
                Provider provider = Security.getProvider(str2);
                if (provider != null) {
                    cipher = Cipher.getInstance(str, provider);
                }
            } catch (Throwable unused) {
            }
        }
        return cipher == null ? Cipher.getInstance(str, str2) : cipher;
    }

    public static byte[] AES128Encode(byte[] bArr, String str) throws Throwable {
        if (bArr == null || str == null) {
            return null;
        }
        return AES128Encode(bArr, str.getBytes("UTF-8"));
    }

    public static byte[] AES128Encode(byte[] bArr, byte[] bArr2) throws Throwable {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, C5855l.m12238a("003=gniigl"));
        Cipher cipher = getCipher(C5855l.m12238a("003Egniigl") + C5855l.m12238a("003n'iiil") + C5855l.m12238a("008!hkYn,imkeilglklim") + C5855l.m12238a("006fNfefefk[g>gg"), C5855l.m12238a("002!hkil"));
        cipher.init(1, secretKeySpec);
        byte[] bArr3 = new byte[cipher.getOutputSize(bArr2.length)];
        cipher.doFinal(bArr3, cipher.update(bArr2, 0, bArr2.length, bArr3, 0));
        return bArr3;
    }

    public static String AES128Decode(String str, byte[] bArr) throws Throwable {
        if (str == null || bArr == null) {
            return null;
        }
        return new String(AES128Decode(str.getBytes("UTF-8"), bArr), "UTF-8");
    }

    public static byte[] AES128Decode(byte[] bArr, byte[] bArr2) throws Throwable {
        if (bArr == null || bArr2 == null) {
            return null;
        }
        byte[] bArr3 = new byte[16];
        System.arraycopy(bArr, 0, bArr3, 0, Math.min(bArr.length, 16));
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr3, C5855l.m12238a("003^gniigl"));
        Cipher cipher = getCipher(C5855l.m12238a("003'gniigl") + C5855l.m12238a("003nViiil") + C5855l.m12238a("005Jhk6nJgjgfim") + C5855l.m12238a("006f@fefefk'g<gg"), C5855l.m12238a("002$hkil"));
        cipher.init(2, secretKeySpec);
        byte[] bArr4 = new byte[cipher.getOutputSize(bArr2.length)];
        cipher.doFinal(bArr4, cipher.update(bArr2, 0, bArr2.length, bArr4, 0));
        return bArr4;
    }

    public static String AES128PaddingDecode(byte[] bArr, byte[] bArr2) throws Throwable {
        if (bArr == null || bArr2 == null) {
            return null;
        }
        byte[] bArr3 = new byte[16];
        System.arraycopy(bArr, 0, bArr3, 0, Math.min(bArr.length, 16));
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr3, C5855l.m12238a("003?gniigl"));
        Cipher cipher = getCipher(C5855l.m12238a("003;gniigl") + C5855l.m12238a("003nIiiil") + C5855l.m12238a("008+hkBnHimkeilglklim") + C5855l.m12238a("006f4fefefkUg)gg"), C5855l.m12238a("002-hkil"));
        cipher.init(2, secretKeySpec);
        return new String(cipher.doFinal(bArr2), "UTF-8");
    }

    public static void AES128Decode(String str, InputStream inputStream, OutputStream outputStream) throws Throwable {
        if (str == null) {
            return;
        }
        AES128Decode(str.getBytes("UTF-8"), inputStream, outputStream);
    }

    public static void AES128Decode(byte[] bArr, InputStream inputStream, OutputStream outputStream) throws Throwable {
        CipherInputStream cipherInputStream;
        Throwable th;
        if (bArr == null || inputStream == null || outputStream == null) {
            return;
        }
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArr, 0, bArr2, 0, Math.min(bArr.length, 16));
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, C5855l.m12238a("003Vgniigl"));
        Cipher cipher = getCipher(C5855l.m12238a("0036gniigl") + C5855l.m12238a("003nHiiil") + C5855l.m12238a("008,hk%n4imkeilglklim") + C5855l.m12238a("006f@fefefk5gVgg"), C5855l.m12238a("002,hkil"));
        cipher.init(2, secretKeySpec);
        try {
            cipherInputStream = new CipherInputStream(inputStream, cipher);
        } catch (Throwable th2) {
            cipherInputStream = null;
            th = th2;
        }
        try {
            byte[] bArr3 = new byte[1024];
            for (int read = cipherInputStream.read(bArr3); read != -1; read = cipherInputStream.read(bArr3)) {
                outputStream.write(bArr3, 0, read);
            }
            outputStream.flush();
            C5873u.m12179a(cipherInputStream);
        } catch (Throwable th3) {
            th = th3;
            C5873u.m12179a(cipherInputStream);
            throw th;
        }
    }

    public static String byteToHex(byte[] bArr) {
        return byteToHex(bArr, 0, bArr.length);
    }

    public static String byteToHex(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        if (bArr == null) {
            return stringBuffer.toString();
        }
        while (i < i2) {
            stringBuffer.append(String.format("%02x", Byte.valueOf(bArr[i])));
            i++;
        }
        return stringBuffer.toString();
    }

    public static String bytesToHexFaster(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] cArr2 = new char[bArr.length * 2];
        int i = 0;
        for (byte b : bArr) {
            cArr2[i] = cArr[(b >>> 4) & 15];
            int i2 = i + 1;
            cArr2[i2] = cArr[b & 15];
            i = i2 + 1;
        }
        return new String(cArr2);
    }

    public static String MD5(String str) {
        byte[] rawMD5;
        if (str == null || (rawMD5 = rawMD5(str)) == null) {
            return null;
        }
        return bytesToHexFaster(rawMD5);
    }

    public static String MD5(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return MD5(bArr, 0, bArr.length);
    }

    public static String MD5(byte[] bArr, int i, int i2) {
        byte[] rawMD5;
        if (bArr == null || (rawMD5 = rawMD5(bArr, i, i2)) == null) {
            return null;
        }
        return bytesToHexFaster(rawMD5);
    }

    public static String MD5(File file) {
        FileInputStream fileInputStream;
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Throwable th) {
            th = th;
            fileInputStream = null;
        }
        try {
            byte[] rawMD5 = rawMD5(fileInputStream);
            C5873u.m12179a(fileInputStream);
            if (rawMD5 == null) {
                return null;
            }
            return bytesToHexFaster(rawMD5);
        } catch (Throwable th2) {
            th = th2;
            try {
                MobLog.getInstance().m11325w(th);
                C5873u.m12179a(fileInputStream);
                return null;
            } catch (Throwable th3) {
                C5873u.m12179a(fileInputStream);
                throw th3;
            }
        }
    }

    public static byte[] rawMD5(String str) {
        if (str == null) {
            return null;
        }
        try {
            return rawMD5(str.getBytes("utf-8"));
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return null;
        }
    }

    public static byte[] rawMD5(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return rawMD5(bArr, 0, bArr.length);
    }

    public static byte[] rawMD5(byte[] bArr, int i, int i2) {
        ByteArrayInputStream byteArrayInputStream;
        Closeable[] closeableArr;
        byte[] bArr2 = null;
        if (bArr == null) {
            return null;
        }
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr, i, i2);
            try {
                bArr2 = rawMD5(byteArrayInputStream);
                closeableArr = new Closeable[]{byteArrayInputStream};
            } catch (Throwable th) {
                th = th;
                try {
                    MobLog.getInstance().m11325w(th);
                    closeableArr = new Closeable[]{byteArrayInputStream};
                    C5873u.m12179a(closeableArr);
                    return bArr2;
                } catch (Throwable th2) {
                    C5873u.m12179a(byteArrayInputStream);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayInputStream = null;
        }
        C5873u.m12179a(closeableArr);
        return bArr2;
    }

    public static byte[] rawMD5(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            byte[] bArr = new byte[1024];
            MessageDigest messageDigest = MessageDigest.getInstance(C5855l.m12238a("003Ojehmjk"));
            int read = inputStream.read(bArr);
            while (read != -1) {
                messageDigest.update(bArr, 0, read);
                read = inputStream.read(bArr);
            }
            return messageDigest.digest();
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return null;
        }
    }

    public static String urlEncode(String str) {
        try {
            return urlEncode(str, "utf-8");
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return null;
        }
    }

    public static String urlEncode(String str, String str2) throws Throwable {
        String encode = TextUtils.isEmpty(str) ? "" : URLEncoder.encode(str, str2);
        return TextUtils.isEmpty(encode) ? encode : encode.replace("+", "%20");
    }

    public static String Base64AES(String str, String str2) {
        try {
            String encodeToString = Base64.encodeToString(AES128Encode(str2, str), 0);
            return encodeToString.contains("\n") ? encodeToString.replace("\n", "") : encodeToString;
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return null;
        }
    }

    public static String CRC32(byte[] bArr) throws Throwable {
        CRC32 crc32 = new CRC32();
        crc32.update(bArr);
        long value = crc32.getValue();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%02x", Integer.valueOf(((byte) (value >>> 56)) & 255)));
        sb.append(String.format("%02x", Integer.valueOf(((byte) (value >>> 48)) & 255)));
        sb.append(String.format("%02x", Integer.valueOf(((byte) (value >>> 40)) & 255)));
        sb.append(String.format("%02x", Integer.valueOf(((byte) (value >>> 32)) & 255)));
        sb.append(String.format("%02x", Integer.valueOf(((byte) (value >>> 24)) & 255)));
        sb.append(String.format("%02x", Integer.valueOf(((byte) (value >>> 16)) & 255)));
        sb.append(String.format("%02x", Integer.valueOf(((byte) (value >>> 8)) & 255)));
        sb.append(String.format("%02x", Integer.valueOf(((byte) value) & 255)));
        while (sb.charAt(0) == '0') {
            sb = sb.deleteCharAt(0);
        }
        return sb.toString().toLowerCase();
    }
}
