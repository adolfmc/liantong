package com.huawei.secure.android.common;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\10762272_dexfile_execute.dex */
public class HiCloudX509TrustManager extends com.huawei.secure.android.common.ssl.SecureX509TrustManager {
    @Deprecated
    public HiCloudX509TrustManager(InputStream inputStream, String str) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        super(inputStream, str);
    }
}
