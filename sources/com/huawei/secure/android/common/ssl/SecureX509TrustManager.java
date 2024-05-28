package com.huawei.secure.android.common.ssl;

import android.content.Context;
import com.huawei.secure.android.common.ssl.util.AbstractC5117d;
import com.huawei.secure.android.common.ssl.util.BksUtil;
import com.huawei.secure.android.common.ssl.util.C5118e;
import com.huawei.secure.android.common.ssl.util.ContextUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SecureX509TrustManager implements X509TrustManager {

    /* renamed from: c */
    private static final String f12045c = "SX509TM";

    /* renamed from: d */
    public static final String f12046d = "hmsrootcas.bks";

    /* renamed from: e */
    private static final String f12047e = "";

    /* renamed from: f */
    private static final String f12048f = "X509";

    /* renamed from: g */
    private static final String f12049g = "bks";

    /* renamed from: h */
    private static final String f12050h = "AndroidCAStore";

    /* renamed from: a */
    protected List<X509TrustManager> f12051a;

    /* renamed from: b */
    private X509Certificate[] f12052b;

    public SecureX509TrustManager(Context context) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, IllegalArgumentException {
        this(context, false);
    }

    /* renamed from: a */
    private void m13921a() {
        C5118e.m13853c(f12045c, "loadSystemCA");
        long currentTimeMillis = System.currentTimeMillis();
        try {
            KeyStore keyStore = KeyStore.getInstance(f12050h);
            keyStore.load(null, null);
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(f12048f);
            trustManagerFactory.init(keyStore);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            for (int i = 0; i < trustManagers.length; i++) {
                if (trustManagers[i] instanceof X509TrustManager) {
                    this.f12051a.add((X509TrustManager) trustManagers[i]);
                }
            }
        } catch (IOException | NegativeArraySizeException | OutOfMemoryError | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            C5118e.m13854b(f12045c, "loadSystemCA: exception : " + e.getMessage());
        }
        C5118e.m13856a(f12045c, "loadSystemCA: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        C5118e.m13853c(f12045c, "checkClientTrusted: ");
        for (X509TrustManager x509TrustManager : this.f12051a) {
            try {
                x509TrustManager.checkServerTrusted(x509CertificateArr, str);
                return;
            } catch (CertificateException e) {
                C5118e.m13854b(f12045c, "checkServerTrusted CertificateException" + e.getMessage());
            }
        }
        throw new CertificateException("checkServerTrusted CertificateException");
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        setChain(x509CertificateArr);
        C5118e.m13853c(f12045c, "checkServerTrusted begin,size=" + x509CertificateArr.length + ",authType=" + str);
        long currentTimeMillis = System.currentTimeMillis();
        int length = x509CertificateArr.length;
        for (int i = 0; i < length; i++) {
            X509Certificate x509Certificate = x509CertificateArr[i];
            C5118e.m13856a(f12045c, "server ca chain: getSubjectDN is :" + x509Certificate.getSubjectDN());
            C5118e.m13856a(f12045c, "IssuerDN :" + x509Certificate.getIssuerDN());
            C5118e.m13856a(f12045c, "SerialNumber : " + x509Certificate.getSerialNumber());
        }
        int size = this.f12051a.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                C5118e.m13853c(f12045c, "check server i=" + i2);
                X509TrustManager x509TrustManager = this.f12051a.get(i2);
                X509Certificate[] acceptedIssuers = x509TrustManager.getAcceptedIssuers();
                if (acceptedIssuers != null) {
                    C5118e.m13853c(f12045c, "client root ca size=" + acceptedIssuers.length);
                    for (int i3 = 0; i3 < acceptedIssuers.length; i3++) {
                        C5118e.m13856a(f12045c, "client root ca getIssuerDN :" + acceptedIssuers[i3].getIssuerDN());
                    }
                }
                x509TrustManager.checkServerTrusted(x509CertificateArr, str);
                C5118e.m13853c(f12045c, "checkServerTrusted end, " + x509CertificateArr[x509CertificateArr.length - 1].getIssuerDN());
                return;
            } catch (CertificateException e) {
                C5118e.m13854b(f12045c, "checkServerTrusted error :" + e.getMessage() + " , time : " + i2);
                if (i2 == size - 1) {
                    if (x509CertificateArr != null && x509CertificateArr.length > 0) {
                        C5118e.m13854b(f12045c, "root ca issuer : " + x509CertificateArr[x509CertificateArr.length - 1].getIssuerDN());
                    }
                    throw e;
                }
            }
        }
        C5118e.m13856a(f12045c, "checkServerTrusted: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        try {
            ArrayList arrayList = new ArrayList();
            for (X509TrustManager x509TrustManager : this.f12051a) {
                arrayList.addAll(Arrays.asList(x509TrustManager.getAcceptedIssuers()));
            }
            return (X509Certificate[]) arrayList.toArray(new X509Certificate[arrayList.size()]);
        } catch (Exception e) {
            C5118e.m13854b(f12045c, "getAcceptedIssuers exception : " + e.getMessage());
            return new X509Certificate[0];
        }
    }

    public X509Certificate[] getChain() {
        return this.f12052b;
    }

    public List<X509TrustManager> getX509TrustManagers() {
        return this.f12051a;
    }

    public void setChain(X509Certificate[] x509CertificateArr) {
        this.f12052b = x509CertificateArr;
    }

    public void setX509TrustManagers(List<X509TrustManager> list) {
        this.f12051a = list;
    }

    public SecureX509TrustManager(Context context, boolean z) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, IllegalArgumentException {
        this.f12051a = new ArrayList();
        if (context != null) {
            ContextUtil.setContext(context);
            if (z) {
                m13921a();
            }
            m13920a(context);
            if (this.f12051a.isEmpty()) {
                throw new CertificateException("X509TrustManager is empty");
            }
            return;
        }
        throw new IllegalArgumentException("context is null");
    }

    /* renamed from: a */
    private void m13920a(Context context) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        boolean z;
        C5118e.m13853c(f12045c, "loadBksCA");
        long currentTimeMillis = System.currentTimeMillis();
        InputStream filesBksIS = BksUtil.getFilesBksIS(context);
        if (filesBksIS != null) {
            try {
                C5118e.m13853c(f12045c, "get bks not from assets");
                m13919a(filesBksIS);
            } catch (IOException | OutOfMemoryError | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
                C5118e.m13854b(f12045c, "loadBksCA: exception : " + e.getMessage());
                z = false;
            }
        }
        z = true;
        if (!z || filesBksIS == null) {
            C5118e.m13853c(f12045c, " get bks from assets ");
            m13919a(context.getAssets().open("hmsrootcas.bks"));
        }
        C5118e.m13856a(f12045c, "loadBksCA: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }

    /* renamed from: a */
    private void m13919a(InputStream inputStream) throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(f12048f);
            KeyStore keyStore = KeyStore.getInstance("bks");
            keyStore.load(inputStream, "".toCharArray());
            trustManagerFactory.init(keyStore);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            for (int i = 0; i < trustManagers.length; i++) {
                if (trustManagers[i] instanceof X509TrustManager) {
                    this.f12051a.add((X509TrustManager) trustManagers[i]);
                }
            }
        } finally {
            AbstractC5117d.m13866a(inputStream);
        }
    }

    public SecureX509TrustManager(InputStream inputStream, String str) throws IllegalArgumentException {
        this.f12051a = new ArrayList();
        m13918a(inputStream, str);
    }

    /* renamed from: a */
    private void m13918a(InputStream inputStream, String str) {
        if (inputStream != null && str != null) {
            long currentTimeMillis = System.currentTimeMillis();
            try {
                try {
                    TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(f12048f);
                    KeyStore keyStore = KeyStore.getInstance("bks");
                    keyStore.load(inputStream, str.toCharArray());
                    trustManagerFactory.init(keyStore);
                    TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
                    for (int i = 0; i < trustManagers.length; i++) {
                        if (trustManagers[i] instanceof X509TrustManager) {
                            this.f12051a.add((X509TrustManager) trustManagers[i]);
                        }
                    }
                    AbstractC5117d.m13866a(inputStream);
                } catch (IOException | NegativeArraySizeException | OutOfMemoryError | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
                    C5118e.m13854b(f12045c, "loadInputStream: exception : " + e.getMessage());
                }
                C5118e.m13856a(f12045c, "loadInputStream: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
                return;
            } finally {
                AbstractC5117d.m13866a(inputStream);
            }
        }
        throw new IllegalArgumentException("inputstream or trustPwd is null");
    }

    public SecureX509TrustManager(String str) throws IllegalArgumentException, FileNotFoundException {
        this(str, false);
    }

    public SecureX509TrustManager(String str, boolean z) throws IllegalArgumentException, FileNotFoundException {
        FileInputStream fileInputStream;
        this.f12051a = new ArrayList();
        try {
            fileInputStream = new FileInputStream(str);
        } catch (Throwable th) {
            th = th;
            fileInputStream = null;
        }
        try {
            m13918a(fileInputStream, "");
            AbstractC5117d.m13866a((InputStream) fileInputStream);
            if (z) {
                m13921a();
            }
        } catch (Throwable th2) {
            th = th2;
            AbstractC5117d.m13866a((InputStream) fileInputStream);
            throw th;
        }
    }

    public SecureX509TrustManager(InputStream inputStream, String str, boolean z) throws IllegalArgumentException {
        this.f12051a = new ArrayList();
        if (z) {
            m13921a();
        }
        m13918a(inputStream, str);
    }
}
