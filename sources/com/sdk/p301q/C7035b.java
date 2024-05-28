package com.sdk.p301q;

import com.sdk.p292h.C7006a;
import com.sdk.p299o.C7025a;
import com.sdk.p299o.C7026b;
import com.sdk.p300p.C7027a;
import com.sdk.p300p.C7029c;
import com.sdk.p300p.C7031e;
import com.sdk.p302r.C7037a;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* renamed from: com.sdk.q.b */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C7035b implements InterfaceC7034a {
    @Override // com.sdk.p301q.InterfaceC7034a
    /* renamed from: a */
    public String mo8133a(String str, String str2) {
        int i = C7026b.f18182a;
        PublicKey m8143a = C7025a.m8143a(str);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, m8143a);
        return C7029c.m8138a(cipher.doFinal(str2.getBytes(Charset.defaultCharset()))).toString();
    }

    @Override // com.sdk.p301q.InterfaceC7034a
    /* renamed from: b */
    public String mo8131b(String str, String str2) {
        String substring = str.substring(0, 16);
        String substring2 = str.substring(16);
        String str3 = C7027a.f18183a;
        if (str2 != null) {
            try {
                if (str2.length() != 0 && str2.trim().length() != 0) {
                    if (substring != null) {
                        if (substring.length() == 16) {
                            if (substring2.length() == 16) {
                                byte[] m8139a = C7029c.m8139a(str2);
                                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                                cipher.init(2, new SecretKeySpec(substring.getBytes("utf-8"), C0108a.f85c), new IvParameterSpec(substring2.getBytes("utf-8")));
                                return new String(cipher.doFinal(m8139a), "utf-8");
                            }
                            throw new Exception(" iv decrypt key length error");
                        }
                        throw new Exception("decrypt key length error");
                    }
                    throw new Exception("decrypt key is null");
                }
            } catch (Exception e) {
                throw new Exception("decrypt errot", e);
            }
        }
        return null;
    }

    @Override // com.sdk.p301q.InterfaceC7034a
    /* renamed from: a */
    public String mo8134a(String str) {
        String str2 = C7031e.f18190a;
        if (C7037a.m8130a(str).booleanValue()) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(Charset.defaultCharset()));
            byte[] digest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    sb.append("0");
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (Exception e) {
            C7006a.m8164a(C7031e.f18190a, "encrypt", e.getMessage(), C7031e.f18191b);
            return null;
        }
    }

    @Override // com.sdk.p301q.InterfaceC7034a
    /* renamed from: a */
    public String mo8132a(String str, String str2, String str3) {
        String str4;
        String str5;
        String str6;
        String str7 = C7027a.f18183a;
        if (str3 != null) {
            try {
                if (str3.length() != 0 && str3.trim().length() != 0) {
                    if (str == null) {
                        str4 = C7027a.f18183a;
                        str5 = "EncryptCbcIv";
                        str6 = "encrypt key is null";
                    } else if (str.length() != 16) {
                        str4 = C7027a.f18183a;
                        str5 = "EncryptCbcIv";
                        str6 = "encrypt key length error";
                    } else if (str2.length() == 16) {
                        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                        cipher.init(1, new SecretKeySpec(str.getBytes("utf-8"), C0108a.f85c), new IvParameterSpec(str2.getBytes("utf-8")));
                        return C7029c.m8138a(cipher.doFinal(str3.getBytes("utf-8")));
                    } else {
                        str4 = C7027a.f18183a;
                        str5 = "EncryptCbcIv";
                        str6 = "ivStr length error";
                    }
                    C7006a.m8164a(str4, str5, str6, C7027a.f18184b);
                    return null;
                }
            } catch (Exception e) {
                C7006a.m8164a(C7027a.f18183a, "EncryptCbcIv", e.getMessage(), C7027a.f18184b);
                return null;
            }
        }
        str4 = C7027a.f18183a;
        str5 = "EncryptCbcIv";
        str6 = "encrypt content is null";
        C7006a.m8164a(str4, str5, str6, C7027a.f18184b);
        return null;
    }
}
