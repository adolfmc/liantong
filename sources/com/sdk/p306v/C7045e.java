package com.sdk.p306v;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.sdk.v.e */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C7045e {
    /* renamed from: a */
    public byte[] m8117a(byte[] bArr, byte[] bArr2, int i) {
        if (bArr == null || bArr.length != 16) {
            throw new C7046f(EnumC7041a.E10410);
        }
        if (bArr2 != null) {
            if (i == 1) {
                r0 = bArr2.length > 0;
                EnumC7041a enumC7041a = EnumC7041a.E10408;
                if (!r0) {
                    throw new C7046f(enumC7041a);
                }
            } else {
                if (bArr2.length > 0 && bArr2.length % 16 == 0) {
                    r0 = true;
                }
                EnumC7041a enumC7041a2 = EnumC7041a.E10409;
                if (!r0) {
                    throw new C7046f(enumC7041a2);
                }
            }
            try {
                Cipher cipher = Cipher.getInstance("SM4/ECB/PKCS5Padding", "BC");
                cipher.init(i, new SecretKeySpec(bArr, "SM4"));
                return cipher.doFinal(bArr2);
            } catch (Exception e) {
                if (i == 1) {
                    throw new C7046f(EnumC7041a.E10204, e);
                }
                throw new C7046f(EnumC7041a.E10205, e);
            }
        }
        throw new C7046f(EnumC7041a.E10400);
    }

    /* renamed from: a */
    public final byte[] m8116a(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        if (bArr == null || bArr.length != 16) {
            throw new C7046f(EnumC7041a.E10410);
        }
        if (bArr3 != null) {
            if (bArr2 != null) {
                if (i == 1) {
                    if (bArr3.length <= 0) {
                        throw new C7046f(EnumC7041a.E10408);
                    }
                } else if (bArr3.length <= 0 || bArr3.length % 16 != 0) {
                    throw new C7046f(EnumC7041a.E10409);
                }
                if (bArr2.length == 16) {
                    new SecretKeySpec(bArr, "SM4");
                    IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr2);
                    try {
                        Cipher cipher = Cipher.getInstance("SM4/CBC/PKCS5Padding", "BC");
                        cipher.init(i, new SecretKeySpec(bArr, "SM4"), ivParameterSpec);
                        return cipher.doFinal(bArr3);
                    } catch (Exception e) {
                        if (i == 1) {
                            throw new C7046f(EnumC7041a.E10204, e);
                        }
                        throw new C7046f(EnumC7041a.E10205, e);
                    }
                }
                throw new C7046f(EnumC7041a.E10411);
            }
            throw new C7046f(EnumC7041a.E10411);
        }
        throw new C7046f(EnumC7041a.E10400);
    }
}
