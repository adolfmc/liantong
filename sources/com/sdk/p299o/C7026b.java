package com.sdk.p299o;

import com.sdk.p290f.C6998d;
import com.sdk.p292h.C7006a;
import com.sdk.p300p.C7029c;
import com.sdk.p302r.C7037a;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sdk.o.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C7026b extends C7006a {

    /* renamed from: a */
    public static final /* synthetic */ int f18182a = 0;

    static {
        boolean z = C6998d.f18135a;
    }

    /* renamed from: a */
    public static String m8142a(String str, String str2) {
        RSAPublicKey rSAPublicKey = (RSAPublicKey) C7025a.m8143a(str2);
        if (C7037a.m8130a(str).booleanValue()) {
            throw new Exception("rsaAes key is null");
        }
        byte[] m8139a = C7029c.m8139a(str);
        if (rSAPublicKey != null) {
            try {
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(2, rSAPublicKey);
                return new String(cipher.doFinal(m8139a), Charset.defaultCharset()).trim();
            } catch (InvalidKeyException unused) {
                throw new InvalidKeyException("解密公钥非法,请检查");
            } catch (NoSuchAlgorithmException unused2) {
                throw new NoSuchAlgorithmException("无此解密算法");
            } catch (BadPaddingException unused3) {
                throw new BadPaddingException("密文数据已损坏");
            } catch (IllegalBlockSizeException unused4) {
                throw new IllegalBlockSizeException("密文长度非法");
            } catch (NoSuchPaddingException unused5) {
                throw new NoSuchPaddingException("解密出错！不支持该填充机制");
            }
        }
        throw new Exception("解密公钥为空, 请设置");
    }
}
