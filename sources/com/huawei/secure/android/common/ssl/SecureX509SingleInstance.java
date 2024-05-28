package com.huawei.secure.android.common.ssl;

import android.annotation.SuppressLint;
import android.content.Context;
import com.huawei.secure.android.common.ssl.util.BksUtil;
import com.huawei.secure.android.common.ssl.util.C5118e;
import com.huawei.secure.android.common.ssl.util.ContextUtil;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class SecureX509SingleInstance {

    /* renamed from: a */
    private static final String f12043a = "SecureX509SingleInstance";

    /* renamed from: b */
    private static volatile SecureX509TrustManager f12044b;

    private SecureX509SingleInstance() {
    }

    @SuppressLint({"NewApi"})
    public static SecureX509TrustManager getInstance(Context context) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        long currentTimeMillis = System.currentTimeMillis();
        if (context != null) {
            ContextUtil.setContext(context);
            if (f12044b == null) {
                synchronized (SecureX509SingleInstance.class) {
                    if (f12044b == null) {
                        InputStream inputStream = null;
                        try {
                            inputStream = BksUtil.getFilesBksIS(context);
                        } catch (RuntimeException unused) {
                            C5118e.m13854b(f12043a, "get files bks error");
                        }
                        if (inputStream == null) {
                            C5118e.m13853c(f12043a, "get assets bks");
                            inputStream = context.getAssets().open("hmsrootcas.bks");
                        } else {
                            C5118e.m13853c(f12043a, "get files bks");
                        }
                        f12044b = new SecureX509TrustManager(inputStream, "");
                    }
                }
            }
            String str = f12043a;
            C5118e.m13856a(str, "SecureX509TrustManager getInstance: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
            return f12044b;
        }
        throw new NullPointerException("context is null");
    }

    @Deprecated
    public static void updateBks(InputStream inputStream) {
        C5118e.m13853c(f12043a, "update bks");
        long currentTimeMillis = System.currentTimeMillis();
        if (inputStream != null && f12044b != null) {
            f12044b = new SecureX509TrustManager(inputStream, "");
            SecureSSLSocketFactory.m13924a(f12044b);
            SecureApacheSSLSocketFactory.m13927a(f12044b);
        }
        String str = f12043a;
        C5118e.m13853c(str, "SecureX509TrustManager update bks cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }

    public static void updateBks(InputStream inputStream, SecureRandom secureRandom) {
        C5118e.m13853c(f12043a, "update bks");
        long currentTimeMillis = System.currentTimeMillis();
        if (inputStream != null && f12044b != null) {
            f12044b = new SecureX509TrustManager(inputStream, "");
            SecureSSLSocketFactory.m13923a(f12044b, secureRandom);
            SecureApacheSSLSocketFactory.m13926a(f12044b, secureRandom);
        }
        String str = f12043a;
        C5118e.m13853c(str, "SecureX509TrustManager update bks cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }
}
