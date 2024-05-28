package com.vivo.push.util;

import android.content.Context;
import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* renamed from: com.vivo.push.util.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class AESParseManager {

    /* renamed from: c */
    private static volatile AESParseManager f21174c;

    /* renamed from: a */
    private byte[] f21175a;

    /* renamed from: b */
    private byte[] f21176b;

    private AESParseManager(Context context) {
        SharePreferenceManager.m5455b().m5456a(ContextDelegate.getContext(context));
        SharePreferenceManager m5455b = SharePreferenceManager.m5455b();
        this.f21175a = m5455b.m5453c();
        this.f21176b = m5455b.m5452d();
    }

    /* renamed from: a */
    public static AESParseManager m5476a(Context context) {
        if (f21174c == null) {
            synchronized (AESParseManager.class) {
                if (f21174c == null) {
                    f21174c = new AESParseManager(context.getApplicationContext());
                }
            }
        }
        return f21174c;
    }

    /* renamed from: a */
    public final String m5475a(String str) throws Exception {
        String m5391a = CryptographicTool.m5391a(m5477a());
        String m5391a2 = CryptographicTool.m5391a(m5474b());
        byte[] bytes = str.getBytes("utf-8");
        SecretKeySpec secretKeySpec = new SecretKeySpec(m5391a2.getBytes("utf-8"), C0108a.f85c);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, secretKeySpec, new IvParameterSpec(m5391a.getBytes("utf-8")));
        return Base64.encodeToString(cipher.doFinal(bytes), 2);
    }

    /* renamed from: b */
    public final String m5473b(String str) throws Exception {
        return new String(CryptographicTool.m5392a(CryptographicTool.m5391a(m5477a()), CryptographicTool.m5391a(m5474b()), Base64.decode(str, 2)), "utf-8");
    }

    /* renamed from: a */
    private byte[] m5477a() {
        byte[] bArr = this.f21175a;
        return (bArr == null || bArr.length <= 0) ? SharePreferenceManager.m5455b().m5453c() : bArr;
    }

    /* renamed from: b */
    private byte[] m5474b() {
        byte[] bArr = this.f21176b;
        return (bArr == null || bArr.length <= 0) ? SharePreferenceManager.m5455b().m5452d() : bArr;
    }
}
