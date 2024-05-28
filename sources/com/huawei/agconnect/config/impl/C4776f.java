package com.huawei.agconnect.config.impl;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKey;

/* renamed from: com.huawei.agconnect.config.impl.f */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
class C4776f implements InterfaceC4777g {

    /* renamed from: a */
    private SecretKey f10779a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C4776f(String str, String str2, String str3, String str4) throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalArgumentException {
        if (str == null || str2 == null || str3 == null || str4 == null) {
            return;
        }
        this.f10779a = C4779i.m15404a(Hex.decodeHexString(str), Hex.decodeHexString(str2), Hex.decodeHexString(str3), Hex.decodeHexString(str4), 5000);
    }

    @Override // com.huawei.agconnect.config.impl.InterfaceC4777g
    /* renamed from: a */
    public String mo15413a(String str, String str2) {
        SecretKey secretKey = this.f10779a;
        if (secretKey == null) {
            return str;
        }
        try {
            return new String(C4779i.m15408a(secretKey, Hex.decodeHexString(str)), "UTF-8");
        } catch (UnsupportedEncodingException | IllegalArgumentException | GeneralSecurityException unused) {
            return str2;
        }
    }
}
