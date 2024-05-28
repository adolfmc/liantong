package com.huawei.secure.android.common.ssl.util;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class CertificateUtil {

    /* renamed from: a */
    private static final String f12105a = "CertificateUtil";

    private CertificateUtil() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v7, types: [java.io.InputStream] */
    public static X509Certificate getHwCbgRootCA(Context context) {
        Throwable th;
        InputStream inputStream;
        KeyStore keyStore;
        X509Certificate x509Certificate = null;
        try {
            try {
                keyStore = KeyStore.getInstance(C5121h.f12122e);
                inputStream = context.getAssets().open("hmsrootcas.bks");
            } catch (IOException e) {
                e = e;
                inputStream = null;
                C5118e.m13854b("CertificateUtil", "loadBksCA: exception : " + e.getMessage());
                context = inputStream;
                AbstractC5117d.m13866a((InputStream) context);
                return x509Certificate;
            } catch (RuntimeException e2) {
                e = e2;
                inputStream = null;
                C5118e.m13854b("CertificateUtil", "loadBksCA: exception : " + e.getMessage());
                context = inputStream;
                AbstractC5117d.m13866a((InputStream) context);
                return x509Certificate;
            } catch (KeyStoreException e3) {
                e = e3;
                inputStream = null;
                C5118e.m13854b("CertificateUtil", "loadBksCA: exception : " + e.getMessage());
                context = inputStream;
                AbstractC5117d.m13866a((InputStream) context);
                return x509Certificate;
            } catch (NoSuchAlgorithmException e4) {
                e = e4;
                inputStream = null;
                C5118e.m13854b("CertificateUtil", "loadBksCA: exception : " + e.getMessage());
                context = inputStream;
                AbstractC5117d.m13866a((InputStream) context);
                return x509Certificate;
            } catch (CertificateException e5) {
                e = e5;
                inputStream = null;
                C5118e.m13854b("CertificateUtil", "loadBksCA: exception : " + e.getMessage());
                context = inputStream;
                AbstractC5117d.m13866a((InputStream) context);
                return x509Certificate;
            } catch (Throwable th2) {
                th = th2;
                context = 0;
                AbstractC5117d.m13866a((InputStream) context);
                throw th;
            }
            try {
                inputStream.reset();
                keyStore.load(inputStream, "".toCharArray());
                x509Certificate = (X509Certificate) keyStore.getCertificate(C5121h.f12123f);
                context = inputStream;
            } catch (IOException e6) {
                e = e6;
                C5118e.m13854b("CertificateUtil", "loadBksCA: exception : " + e.getMessage());
                context = inputStream;
                AbstractC5117d.m13866a((InputStream) context);
                return x509Certificate;
            } catch (RuntimeException e7) {
                e = e7;
                C5118e.m13854b("CertificateUtil", "loadBksCA: exception : " + e.getMessage());
                context = inputStream;
                AbstractC5117d.m13866a((InputStream) context);
                return x509Certificate;
            } catch (KeyStoreException e8) {
                e = e8;
                C5118e.m13854b("CertificateUtil", "loadBksCA: exception : " + e.getMessage());
                context = inputStream;
                AbstractC5117d.m13866a((InputStream) context);
                return x509Certificate;
            } catch (NoSuchAlgorithmException e9) {
                e = e9;
                C5118e.m13854b("CertificateUtil", "loadBksCA: exception : " + e.getMessage());
                context = inputStream;
                AbstractC5117d.m13866a((InputStream) context);
                return x509Certificate;
            } catch (CertificateException e10) {
                e = e10;
                C5118e.m13854b("CertificateUtil", "loadBksCA: exception : " + e.getMessage());
                context = inputStream;
                AbstractC5117d.m13866a((InputStream) context);
                return x509Certificate;
            }
            AbstractC5117d.m13866a((InputStream) context);
            return x509Certificate;
        } catch (Throwable th3) {
            th = th3;
            AbstractC5117d.m13866a((InputStream) context);
            throw th;
        }
    }
}
