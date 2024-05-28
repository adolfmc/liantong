package com.sdk.p301q;

import android.util.Base64;
import com.sdk.p300p.C7029c;
import com.sdk.p306v.C7042b;
import com.sdk.p306v.C7043c;
import com.sdk.p306v.C7046f;
import com.sdk.p306v.EnumC7041a;
import java.security.SecureRandom;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;

/* renamed from: com.sdk.q.c */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C7036c implements InterfaceC7034a {
    @Override // com.sdk.p301q.InterfaceC7034a
    /* renamed from: a */
    public String mo8133a(String str, String str2) {
        byte[] bytes = str2.getBytes();
        byte[] decode = Base64.decode(str, 0);
        C7043c c7043c = C7042b.f18234a;
        c7043c.getClass();
        if (decode != null) {
            if (bytes != null) {
                if (bytes.length != 0) {
                    if (decode.length == 65) {
                        if (decode[0] == 4) {
                            try {
                                ECPublicKeyParameters m8118a = c7043c.m8118a(decode);
                                SM2Engine sM2Engine = new SM2Engine();
                                sM2Engine.init(true, new ParametersWithRandom(m8118a, new SecureRandom()));
                                try {
                                    byte[] processBlock = sM2Engine.processBlock(bytes, 0, bytes.length);
                                    if (processBlock != null && processBlock.length >= 97) {
                                        byte[] bArr = new byte[processBlock.length];
                                        System.arraycopy(processBlock, 0, bArr, 0, 65);
                                        System.arraycopy(processBlock, processBlock.length - 32, bArr, 65, 32);
                                        System.arraycopy(processBlock, 65, bArr, 97, processBlock.length - 97);
                                        return Base64.encodeToString(bArr, 0).replaceAll("\n", "");
                                    }
                                    throw new C7046f(EnumC7041a.E10406);
                                } catch (InvalidCipherTextException e) {
                                    throw new C7046f(EnumC7041a.E10200, e);
                                }
                            } catch (Exception unused) {
                                throw new C7046f(EnumC7041a.E10416);
                            }
                        }
                        throw new C7046f(EnumC7041a.E10403);
                    }
                    throw new C7046f(EnumC7041a.E10417);
                }
                throw new C7046f(EnumC7041a.E10415);
            }
            throw new C7046f(EnumC7041a.E10400);
        }
        throw new C7046f(EnumC7041a.E10400);
    }

    @Override // com.sdk.p301q.InterfaceC7034a
    /* renamed from: b */
    public String mo8131b(String str, String str2) {
        return new String(C7042b.m8120a(C7029c.m8139a(str2), str.substring(0, 16).getBytes(), str.substring(16).getBytes(), 2));
    }

    @Override // com.sdk.p301q.InterfaceC7034a
    /* renamed from: a */
    public String mo8134a(String str) {
        StringBuilder sb;
        String str2 = "";
        for (byte b : C7042b.m8121a(str.getBytes())) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                sb = new StringBuilder();
                sb.append(str2);
                str2 = "0";
            } else {
                sb = new StringBuilder();
            }
            sb.append(str2);
            sb.append(hexString);
            str2 = sb.toString();
        }
        return str2.toUpperCase().toUpperCase();
    }

    @Override // com.sdk.p301q.InterfaceC7034a
    /* renamed from: a */
    public String mo8132a(String str, String str2, String str3) {
        return Base64.encodeToString(C7042b.m8119b(str3.getBytes(), str.getBytes(), str2.getBytes(), 2), 0).replaceAll("\n", "");
    }
}
