package com.p281qq.p282e.comm.managers.plugin;

import android.text.TextUtils;
import android.util.Base64;
import com.p281qq.p282e.comm.util.GDTLogger;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* renamed from: com.qq.e.comm.managers.plugin.c */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
class C6880c {

    /* renamed from: c */
    private static final byte[] f17946c = {82, 83, 65, 47, 69, 67, 66, 47, 80, 75, 67, 83, 49, 80, 97, 100, 100, 105, 110, 103};

    /* renamed from: a */
    private PublicKey f17947a;

    /* renamed from: b */
    private final boolean f17948b;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.qq.e.comm.managers.plugin.c$b */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class C6882b {

        /* renamed from: a */
        public static final C6880c f17949a = new C6880c();
    }

    private C6880c() {
        boolean z;
        try {
            this.f17947a = m8258a("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDKta2b5Vw5YkWHCAj4rJCwS227\r/35FZ29e4I6pS2B8zSq2RgBpXUuMg7oZF1Qt3x0iyg8PeyblyNeCRB6gIMehFThe\r1Y7m1FaQyaZp+CJYOTLM4/THKp9UndrEgJ/5a83vP1375YCV2lMvWARrNlBep4RN\rnESUJhQz58Gr/F39TwIDAQAB");
            z = true;
        } catch (Throwable unused) {
            z = false;
        }
        this.f17948b = z;
    }

    /* renamed from: a */
    private PublicKey m8258a(String str) throws Exception {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (NullPointerException unused) {
            throw new Exception("公钥数据为空");
        } catch (NoSuchAlgorithmException unused2) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException unused3) {
            throw new Exception("公钥非法");
        }
    }

    /* renamed from: a */
    public boolean m8256a(String str, String str2) {
        String str3;
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        if (this.f17948b) {
            if (this.f17947a != null) {
                byte[] decode = Base64.decode(str, 0);
                try {
                    Cipher cipher = Cipher.getInstance(new String(f17946c, "UTF-8"));
                    cipher.init(2, this.f17947a);
                    str3 = new String(cipher.doFinal(decode), "UTF-8").trim();
                } catch (Throwable unused) {
                    GDTLogger.m8235d("ErrorWhileVerifySigNature");
                }
                boolean equals = str2.equals(str3);
                GDTLogger.m8235d("Verify Result" + equals + "src=" + str2 + " & target=" + str3);
                return equals;
            }
            str3 = null;
            boolean equals2 = str2.equals(str3);
            GDTLogger.m8235d("Verify Result" + equals2 + "src=" + str2 + " & target=" + str3);
            return equals2;
        }
        return true;
    }

    /* renamed from: a */
    public boolean m8257a(String str, File file) {
        String str2;
        MessageDigest messageDigest;
        FileInputStream fileInputStream;
        if (file == null || !file.exists()) {
            return false;
        }
        if (file.exists()) {
            FileInputStream fileInputStream2 = null;
            try {
                messageDigest = MessageDigest.getInstance("MD5");
                fileInputStream = new FileInputStream(file);
            } catch (Exception unused) {
            } catch (Throwable th) {
                th = th;
            }
            try {
                byte[] bArr = new byte[16384];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    messageDigest.update(bArr, 0, read);
                }
                str2 = C6883d.m8255a(messageDigest.digest());
                try {
                    fileInputStream.close();
                } catch (Exception unused2) {
                }
            } catch (Exception unused3) {
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (Exception unused4) {
                    }
                }
                str2 = "";
                return m8256a(str, str2);
            } catch (Throwable th2) {
                th = th2;
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (Exception unused5) {
                    }
                }
                throw th;
            }
            return m8256a(str, str2);
        }
        str2 = "";
        return m8256a(str, str2);
    }
}
