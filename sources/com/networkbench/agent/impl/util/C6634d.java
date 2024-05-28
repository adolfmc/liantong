package com.networkbench.agent.impl.util;

import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.util.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6634d {

    /* renamed from: a */
    private static final String f17087a = "AES/CBC/PKCS5Padding";

    /* renamed from: b */
    private static final String f17088b = "AES";

    /* renamed from: c */
    private static final InterfaceC6393e f17089c = C6394f.m10150a();

    /* renamed from: d */
    private String f17090d;

    /* renamed from: e */
    private PublicKey f17091e;

    /* renamed from: f */
    private byte[] f17092f;

    public C6634d(String str) throws Exception {
        this.f17090d = str;
        m9100c();
    }

    /* renamed from: a */
    public String m9105a(String str) throws C6632b {
        if (str == null) {
            throw new IllegalArgumentException("encrypt aes content is null");
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(m9106a(), C0108a.f85c);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(m9106a());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, secretKeySpec, ivParameterSpec);
            return C6636f.m9094a(cipher.doFinal(str.getBytes("utf-8")));
        } catch (Throwable th) {
            f17089c.mo10121a("encryptContentAES error", th);
            throw new C6632b(th);
        }
    }

    /* renamed from: a */
    public static String m9103a(boolean z, int i) {
        String str;
        int floor;
        String str2 = z ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int length = str2.length();
        boolean z2 = true;
        do {
            str = "";
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                char charAt = str2.charAt((int) Math.floor(Math.random() * length));
                if ('0' <= charAt && charAt <= '9') {
                    i2++;
                }
                str = str + str2.charAt(floor);
            }
            if (i2 >= 2) {
                z2 = false;
                continue;
            }
        } while (z2);
        return str;
    }

    /* renamed from: a */
    public byte[] m9106a() throws Exception {
        byte[] bArr = this.f17092f;
        if (bArr != null) {
            return bArr;
        }
        this.f17092f = m9103a(false, 16).getBytes();
        return this.f17092f;
    }

    /* renamed from: b */
    public String m9101b(String str) throws C6632b {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(m9106a(), C0108a.f85c);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, secretKeySpec, new IvParameterSpec(m9106a()));
            return new String(cipher.doFinal(C6636f.m9095a(str)));
        } catch (Throwable th) {
            f17089c.mo10121a("decryptContentAES error", th);
            throw new C6632b(th);
        }
    }

    /* renamed from: a */
    public String m9104a(String str, String str2) throws C6632b {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("utf-8"), C0108a.f85c);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, secretKeySpec, new IvParameterSpec(m9106a()));
            return new String(cipher.doFinal(C6636f.m9095a(str)));
        } catch (Throwable th) {
            f17089c.mo10121a("decryptContentAES error", th);
            throw new C6632b(th);
        }
    }

    /* renamed from: c */
    public String m9099c(String str) throws Exception {
        if (str == null) {
            throw new IllegalArgumentException("encrypt aes content is null");
        }
        InterfaceC6393e interfaceC6393e = f17089c;
        interfaceC6393e.mo10122a("generateAESKey aes content:" + str);
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, m9100c());
            return C6636f.m9094a(cipher.doFinal(str.getBytes("utf-8")));
        } catch (Throwable th) {
            f17089c.mo10121a("encryptContentRsa error:", th);
            throw new C6632b(th);
        }
    }

    /* renamed from: c */
    private PublicKey m9100c() throws Exception {
        if (this.f17091e == null) {
            this.f17091e = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(C6636f.m9095a(this.f17090d)));
        }
        return this.f17091e;
    }

    /* renamed from: b */
    public PrivateKey m9102b() throws Exception {
        return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(C6636f.m9095a("MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAKfwiWBXMBMQw129MvY1P0jkgW2yLnptfW1gpSo399EJQUlRlRcTdlIl/0cWv4uVDtsn4eBhMJg8cG5NhxzzQ+bicwIOZbS21+63pHOdKh2I7Ea8X3IYGGb6TUu/uCBmyf0Q9GgGRzsBlxUmh0YI3JUNvzlp4etRdVvn5MluQvX9AgMBAAECgYBgN6O9mrZkdfo4vqlv/lnfvWuaGbgfVJPEEtcONTq6fP72rtMogXIM5tA3PoVHx4LXdSYolAZvclFIIQsEPyvrMpy6oygPTkioQXmlGVuCkgCJeK6gFId185c+IWG8OLUPuDwBC0ESh4xNUfaPaG/Tfy+AXuPLhKCzoldhe0KsgQJBANbPUovwN3PuxJkknC6RtnQoAx4xj7am9D8wD/F8607wLov7CFJgijKYrFYGfvXXRm5a4CYpcfMDxzcAXfX1T9kCQQDIJG1SY0FGWOgpVzBmN/9bNzfa/p39JAi0bKodl8+1F003Fn3J3iR2wBZpOcenYmLWdeMwVEnbz3nHwLHsEiTFAkEAsEEgzC3ocjnCGp5NgHCTMq/6DvY/o3FOC+LV0iHbucOdjcIGzADmpCldjqB54CIpajcqoGV1WB8LgPfOIRiN0QJBAKWyktes2PPW0462YpmeLslj89hZCI/1b0eA125cq1fwDLtY4ZdpVKMD8Qn5DjSkHdQUEZ2HiwZyxQ39mNArTUkCQQChxak0RvpiAm7qvHihralVOUQLv1/OLacaIRH52ZL2PEnqvzqMFGgYIOJaMpk7P/Ftk61sntIph6CR5sCGilDb")));
    }
}
