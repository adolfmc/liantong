package com.huawei.agconnect.config.impl;

import android.text.TextUtils;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.SecretKey;

/* renamed from: com.huawei.agconnect.config.impl.e */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4775e implements InterfaceC4777g {

    /* renamed from: a */
    private final InterfaceC4774d f10777a;

    /* renamed from: b */
    private SecretKey f10778b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C4775e(InterfaceC4774d interfaceC4774d) {
        this.f10777a = interfaceC4774d;
        m15416a();
    }

    /* renamed from: a */
    private SecretKey m15416a() {
        try {
            String mo15400a = this.f10777a.mo15400a("/code/code1", null);
            String mo15400a2 = this.f10777a.mo15400a("/code/code2", null);
            String mo15400a3 = this.f10777a.mo15400a("/code/code3", null);
            String mo15400a4 = this.f10777a.mo15400a("/code/code4", null);
            if (mo15400a != null && mo15400a2 != null && mo15400a3 != null && mo15400a4 != null) {
                this.f10778b = C4779i.m15404a(Hex.decodeHexString(mo15400a), Hex.decodeHexString(mo15400a2), Hex.decodeHexString(mo15400a3), Hex.decodeHexString(mo15400a4), 10000);
            }
        } catch (IllegalArgumentException | NoSuchAlgorithmException | InvalidKeySpecException unused) {
            Log.e("ExclamationMark", "Exception when reading the 'K&I' for 'Config'.");
            this.f10778b = null;
        }
        return this.f10778b;
    }

    /* renamed from: a */
    public static boolean m15415a(String str) {
        return !TextUtils.isEmpty(str) && Pattern.matches("^\\[!([A-Fa-f0-9]*)]", str);
    }

    /* renamed from: b */
    static String m15414b(String str) {
        try {
            Matcher matcher = Pattern.compile("^\\[!([A-Fa-f0-9]*)]").matcher(str);
            return matcher.find() ? matcher.group(1) : "";
        } catch (IllegalStateException | IndexOutOfBoundsException unused) {
            Log.e("ExclamationMark", "getRawString exception");
            return "";
        }
    }

    @Override // com.huawei.agconnect.config.impl.InterfaceC4777g
    /* renamed from: a */
    public String mo15413a(String str, String str2) {
        String str3;
        String str4;
        if (this.f10778b == null) {
            str3 = "ExclamationMark";
            str4 = "mKey is null, return default value";
        } else if (!m15415a(str)) {
            return str2;
        } else {
            try {
                return new String(C4779i.m15408a(this.f10778b, Hex.decodeHexString(m15414b(str))), "UTF-8");
            } catch (UnsupportedEncodingException | IllegalArgumentException | GeneralSecurityException unused) {
                str3 = "ExclamationMark";
                str4 = "UnsupportedEncodingException||GeneralSecurityException||IllegalArgumentException";
            }
        }
        Log.e(str3, str4);
        return str2;
    }
}
