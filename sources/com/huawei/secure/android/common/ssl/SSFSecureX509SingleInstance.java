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
public class SSFSecureX509SingleInstance {

    /* renamed from: a */
    private static final String f12003a = "SSFSecureX509SingleInstance";

    /* renamed from: b */
    private static volatile SecureX509TrustManager f12004b;

    private SSFSecureX509SingleInstance() {
    }

    @SuppressLint({"NewApi"})
    public static SecureX509TrustManager getInstance(Context context) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        if (context != null) {
            ContextUtil.setContext(context);
            if (f12004b == null) {
                synchronized (SSFSecureX509SingleInstance.class) {
                    if (f12004b == null) {
                        InputStream filesBksIS = BksUtil.getFilesBksIS(context);
                        if (filesBksIS == null) {
                            C5118e.m13853c(f12003a, "get assets bks");
                            filesBksIS = context.getAssets().open("hmsrootcas.bks");
                        } else {
                            C5118e.m13853c(f12003a, "get files bks");
                        }
                        f12004b = new SecureX509TrustManager(filesBksIS, "", true);
                    }
                }
            }
            return f12004b;
        }
        throw new NullPointerException("context is null");
    }

    @Deprecated
    public static void updateBks(InputStream inputStream) {
        C5118e.m13853c(f12003a, "update bks");
        long currentTimeMillis = System.currentTimeMillis();
        if (inputStream != null && f12004b != null) {
            f12004b = new SecureX509TrustManager(inputStream, "", true);
            String str = f12003a;
            C5118e.m13856a(str, "updateBks: new SecureX509TrustManager cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
            SSFCompatiableSystemCA.m13930a(f12004b);
            SASFCompatiableSystemCA.m13933a(f12004b);
        }
        String str2 = f12003a;
        C5118e.m13856a(str2, "update bks cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }

    public static void updateBks(InputStream inputStream, SecureRandom secureRandom) {
        C5118e.m13853c(f12003a, "update bks");
        long currentTimeMillis = System.currentTimeMillis();
        if (inputStream != null && f12004b != null) {
            f12004b = new SecureX509TrustManager(inputStream, "", true);
            String str = f12003a;
            C5118e.m13856a(str, "updateBks: new SecureX509TrustManager cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
            SSFCompatiableSystemCA.m13929a(f12004b, secureRandom);
            SASFCompatiableSystemCA.m13932a(f12004b, secureRandom);
        }
        String str2 = f12003a;
        C5118e.m13856a(str2, "update bks cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }
}
