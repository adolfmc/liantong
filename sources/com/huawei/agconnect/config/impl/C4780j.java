package com.huawei.agconnect.config.impl;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: com.huawei.agconnect.config.impl.j */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
class C4780j implements InterfaceC4774d {

    /* renamed from: a */
    private final Context f10781a;

    /* renamed from: b */
    private final String f10782b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C4780j(Context context, String str) {
        this.f10781a = context;
        this.f10782b = str;
    }

    /* renamed from: a */
    private static String m15403a(String str) {
        try {
            return "agc_" + Hex.encodeHexString(m15402a(str.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException unused) {
            return "";
        } catch (NoSuchAlgorithmException unused2) {
            return "";
        }
    }

    /* renamed from: a */
    private static byte[] m15402a(byte[] bArr) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("SHA-256").digest(bArr);
    }

    @Override // com.huawei.agconnect.config.impl.InterfaceC4774d
    /* renamed from: a */
    public String mo15400a(String str, String str2) {
        int identifier;
        String m15403a = m15403a(str);
        if (TextUtils.isEmpty(m15403a) || (identifier = this.f10781a.getResources().getIdentifier(m15403a, "string", this.f10782b)) == 0) {
            return str2;
        }
        try {
            return this.f10781a.getResources().getString(identifier);
        } catch (Resources.NotFoundException unused) {
            return str2;
        }
    }
}
