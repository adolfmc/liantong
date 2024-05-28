package com.huawei.secure.android.common.ssl.util;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/* renamed from: com.huawei.secure.android.common.ssl.util.h */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5121h {

    /* renamed from: b */
    private static final String f12119b = "X509CertificateUtil";

    /* renamed from: c */
    public static final String f12120c = "hmsrootcas.bks";

    /* renamed from: d */
    public static final String f12121d = "";

    /* renamed from: e */
    public static final String f12122e = "bks";

    /* renamed from: f */
    public static final String f12123f = "052root";

    /* renamed from: g */
    private static final String f12124g = "hmsincas.bks";

    /* renamed from: h */
    private static final String f12125h = "huawei cbg application integration ca";

    /* renamed from: a */
    private Context f12126a;

    public C5121h(Context context) {
        this.f12126a = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* renamed from: a */
    public X509Certificate m13838a(String str, String str2) {
        InputStream inputStream;
        X509Certificate x509Certificate;
        KeyStore keyStore;
        try {
            try {
                keyStore = KeyStore.getInstance(f12122e);
                inputStream = this.f12126a.getAssets().open(str);
            } catch (IOException e) {
                e = e;
                inputStream = null;
                C5118e.m13854b(f12119b, "loadBksCA: exception : " + e.getMessage());
                x509Certificate = null;
                str = inputStream;
                AbstractC5117d.m13866a((InputStream) str);
                return x509Certificate;
            } catch (KeyStoreException e2) {
                e = e2;
                inputStream = null;
                C5118e.m13854b(f12119b, "loadBksCA: exception : " + e.getMessage());
                x509Certificate = null;
                str = inputStream;
                AbstractC5117d.m13866a((InputStream) str);
                return x509Certificate;
            } catch (NoSuchAlgorithmException e3) {
                e = e3;
                inputStream = null;
                C5118e.m13854b(f12119b, "loadBksCA: exception : " + e.getMessage());
                x509Certificate = null;
                str = inputStream;
                AbstractC5117d.m13866a((InputStream) str);
                return x509Certificate;
            } catch (CertificateException e4) {
                e = e4;
                inputStream = null;
                C5118e.m13854b(f12119b, "loadBksCA: exception : " + e.getMessage());
                x509Certificate = null;
                str = inputStream;
                AbstractC5117d.m13866a((InputStream) str);
                return x509Certificate;
            } catch (Throwable th) {
                th = th;
                str = 0;
                AbstractC5117d.m13866a((InputStream) str);
                throw th;
            }
            try {
                inputStream.reset();
                keyStore.load(inputStream, "".toCharArray());
                x509Certificate = (X509Certificate) keyStore.getCertificate(str2);
                str = inputStream;
            } catch (IOException e5) {
                e = e5;
                C5118e.m13854b(f12119b, "loadBksCA: exception : " + e.getMessage());
                x509Certificate = null;
                str = inputStream;
                AbstractC5117d.m13866a((InputStream) str);
                return x509Certificate;
            } catch (KeyStoreException e6) {
                e = e6;
                C5118e.m13854b(f12119b, "loadBksCA: exception : " + e.getMessage());
                x509Certificate = null;
                str = inputStream;
                AbstractC5117d.m13866a((InputStream) str);
                return x509Certificate;
            } catch (NoSuchAlgorithmException e7) {
                e = e7;
                C5118e.m13854b(f12119b, "loadBksCA: exception : " + e.getMessage());
                x509Certificate = null;
                str = inputStream;
                AbstractC5117d.m13866a((InputStream) str);
                return x509Certificate;
            } catch (CertificateException e8) {
                e = e8;
                C5118e.m13854b(f12119b, "loadBksCA: exception : " + e.getMessage());
                x509Certificate = null;
                str = inputStream;
                AbstractC5117d.m13866a((InputStream) str);
                return x509Certificate;
            }
            AbstractC5117d.m13866a((InputStream) str);
            return x509Certificate;
        } catch (Throwable th2) {
            th = th2;
            AbstractC5117d.m13866a((InputStream) str);
            throw th;
        }
    }

    /* renamed from: b */
    public X509Certificate m13837b() {
        return m13838a("hmsrootcas.bks", f12123f);
    }

    /* renamed from: a */
    public X509Certificate m13839a() {
        return m13838a(f12124g, f12125h);
    }
}
