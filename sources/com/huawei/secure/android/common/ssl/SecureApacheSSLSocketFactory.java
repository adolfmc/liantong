package com.huawei.secure.android.common.ssl;

import android.content.Context;
import com.huawei.secure.android.common.ssl.util.C5114a;
import com.huawei.secure.android.common.ssl.util.C5118e;
import com.huawei.secure.android.common.ssl.util.ContextUtil;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.StrictHostnameVerifier;
import org.apache.http.conn.ssl.X509HostnameVerifier;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SecureApacheSSLSocketFactory extends SSLSocketFactory {
    public static final X509HostnameVerifier BROWSER_COMPATIBLE_HOSTNAME_VERIFIER = new BrowserCompatHostnameVerifier();
    public static final X509HostnameVerifier STRICT_HOSTNAME_VERIFIER = new StrictHostnameVerifier();

    /* renamed from: i */
    private static final String f12013i = SecureApacheSSLSocketFactory.class.getSimpleName();

    /* renamed from: j */
    private static volatile SecureApacheSSLSocketFactory f12014j = null;

    /* renamed from: a */
    private SSLContext f12015a;

    /* renamed from: b */
    private SSLSocket f12016b;

    /* renamed from: c */
    private Context f12017c;

    /* renamed from: d */
    private String[] f12018d;

    /* renamed from: e */
    private X509TrustManager f12019e;

    /* renamed from: f */
    private String[] f12020f;

    /* renamed from: g */
    private String[] f12021g;

    /* renamed from: h */
    private String[] f12022h;

    private SecureApacheSSLSocketFactory(KeyStore keyStore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        super(keyStore);
        this.f12016b = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    /* renamed from: a */
    public static void m13927a(X509TrustManager x509TrustManager) {
        C5118e.m13853c(f12013i, "sasf update socket factory trust manager");
        try {
            f12014j = new SecureApacheSSLSocketFactory(null, x509TrustManager);
        } catch (IOException unused) {
            C5118e.m13854b(f12013i, "IOException");
        } catch (KeyManagementException unused2) {
            C5118e.m13854b(f12013i, "KeyManagementException");
        } catch (KeyStoreException unused3) {
            C5118e.m13854b(f12013i, "KeyStoreException");
        } catch (NoSuchAlgorithmException unused4) {
            C5118e.m13854b(f12013i, "NoSuchAlgorithmException");
        } catch (UnrecoverableKeyException unused5) {
            C5118e.m13854b(f12013i, "UnrecoverableKeyException");
        } catch (CertificateException unused6) {
            C5118e.m13854b(f12013i, "CertificateException");
        }
    }

    @Deprecated
    public static SecureApacheSSLSocketFactory getInstance(KeyStore keyStore, Context context) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException, IllegalArgumentException {
        ContextUtil.setContext(context);
        if (f12014j == null) {
            synchronized (SecureApacheSSLSocketFactory.class) {
                if (f12014j == null) {
                    f12014j = new SecureApacheSSLSocketFactory(keyStore, context, (SecureRandom) null);
                }
            }
        }
        return f12014j;
    }

    @Override // org.apache.http.conn.ssl.SSLSocketFactory, org.apache.http.conn.scheme.LayeredSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        C5118e.m13853c(f12013i, "createSocket: socket host port autoClose");
        Socket createSocket = this.f12015a.getSocketFactory().createSocket(socket, str, i, z);
        if (createSocket instanceof SSLSocket) {
            m13928a(createSocket);
            this.f12016b = (SSLSocket) createSocket;
            this.f12018d = (String[]) this.f12016b.getEnabledCipherSuites().clone();
        }
        return createSocket;
    }

    public String[] getBlackCiphers() {
        return this.f12020f;
    }

    public X509Certificate[] getChain() {
        X509TrustManager x509TrustManager = this.f12019e;
        if (x509TrustManager instanceof SecureX509TrustManager) {
            return ((SecureX509TrustManager) x509TrustManager).getChain();
        }
        return new X509Certificate[0];
    }

    public Context getContext() {
        return this.f12017c;
    }

    public String[] getProtocols() {
        return this.f12022h;
    }

    public SSLContext getSslContext() {
        return this.f12015a;
    }

    public SSLSocket getSslSocket() {
        return this.f12016b;
    }

    public String[] getSupportedCipherSuites() {
        String[] strArr = this.f12018d;
        return strArr != null ? strArr : new String[0];
    }

    public String[] getWhiteCiphers() {
        return this.f12021g;
    }

    public X509TrustManager getX509TrustManager() {
        return this.f12019e;
    }

    public void setBlackCiphers(String[] strArr) {
        this.f12020f = strArr;
    }

    public void setContext(Context context) {
        this.f12017c = context.getApplicationContext();
    }

    public void setProtocols(String[] strArr) {
        this.f12022h = strArr;
    }

    public void setSslContext(SSLContext sSLContext) {
        this.f12015a = sSLContext;
    }

    public void setSslSocket(SSLSocket sSLSocket) {
        this.f12016b = sSLSocket;
    }

    public void setWhiteCiphers(String[] strArr) {
        this.f12021g = strArr;
    }

    public void setX509TrustManager(X509TrustManager x509TrustManager) {
        this.f12019e = x509TrustManager;
    }

    private SecureApacheSSLSocketFactory(KeyStore keyStore, Context context, SecureRandom secureRandom) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException, IllegalArgumentException {
        super(keyStore);
        this.f12016b = null;
        if (context == null) {
            C5118e.m13854b(f12013i, "SecureSSLSocketFactory: context is null");
            return;
        }
        setContext(context);
        setSslContext(SSLUtil.setSSLContext());
        this.f12019e = SecureX509SingleInstance.getInstance(context);
        this.f12015a.init(null, new X509TrustManager[]{this.f12019e}, secureRandom);
    }

    @Override // org.apache.http.conn.ssl.SSLSocketFactory, org.apache.http.conn.scheme.SocketFactory
    public Socket createSocket() throws IOException {
        C5118e.m13853c(f12013i, "createSocket: ");
        Socket createSocket = this.f12015a.getSocketFactory().createSocket();
        if (createSocket instanceof SSLSocket) {
            m13928a(createSocket);
            this.f12016b = (SSLSocket) createSocket;
            this.f12018d = (String[]) this.f12016b.getEnabledCipherSuites().clone();
        }
        return createSocket;
    }

    public static SecureApacheSSLSocketFactory getInstance(KeyStore keyStore, Context context, SecureRandom secureRandom) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException, IllegalArgumentException {
        ContextUtil.setContext(context);
        if (f12014j == null) {
            synchronized (SecureApacheSSLSocketFactory.class) {
                if (f12014j == null) {
                    f12014j = new SecureApacheSSLSocketFactory(keyStore, context, secureRandom);
                }
            }
        }
        return f12014j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m13926a(X509TrustManager x509TrustManager, SecureRandom secureRandom) {
        C5118e.m13853c(f12013i, "sasf update socket factory trust manager");
        try {
            f12014j = new SecureApacheSSLSocketFactory((KeyStore) null, x509TrustManager, secureRandom);
        } catch (IOException unused) {
            C5118e.m13854b(f12013i, "IOException");
        } catch (KeyManagementException unused2) {
            C5118e.m13854b(f12013i, "KeyManagementException");
        } catch (KeyStoreException unused3) {
            C5118e.m13854b(f12013i, "KeyStoreException");
        } catch (NoSuchAlgorithmException unused4) {
            C5118e.m13854b(f12013i, "NoSuchAlgorithmException");
        } catch (UnrecoverableKeyException unused5) {
            C5118e.m13854b(f12013i, "UnrecoverableKeyException");
        } catch (CertificateException unused6) {
            C5118e.m13854b(f12013i, "CertificateException");
        }
    }

    @Deprecated
    public SecureApacheSSLSocketFactory(KeyStore keyStore, InputStream inputStream, String str) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException, IllegalArgumentException {
        super(keyStore);
        this.f12016b = null;
        this.f12015a = SSLUtil.setSSLContext();
        HiCloudX509TrustManager hiCloudX509TrustManager = new HiCloudX509TrustManager(inputStream, str);
        setX509TrustManager(hiCloudX509TrustManager);
        this.f12015a.init(null, new X509TrustManager[]{hiCloudX509TrustManager}, null);
    }

    /* renamed from: a */
    private void m13928a(Socket socket) {
        boolean z;
        boolean z2 = true;
        if (C5114a.m13885a(this.f12022h)) {
            z = false;
        } else {
            C5118e.m13853c(f12013i, "set protocols");
            SSLUtil.setEnabledProtocols((SSLSocket) socket, this.f12022h);
            z = true;
        }
        if (C5114a.m13885a(this.f12021g) && C5114a.m13885a(this.f12020f)) {
            z2 = false;
        } else {
            C5118e.m13853c(f12013i, "set white cipher or black cipher");
            SSLSocket sSLSocket = (SSLSocket) socket;
            SSLUtil.setEnabledProtocols(sSLSocket);
            if (!C5114a.m13885a(this.f12021g)) {
                SSLUtil.setWhiteListCipherSuites(sSLSocket, this.f12021g);
            } else {
                SSLUtil.setBlackListCipherSuites(sSLSocket, this.f12020f);
            }
        }
        if (!z) {
            C5118e.m13853c(f12013i, "set default protocols");
            SSLUtil.setEnabledProtocols((SSLSocket) socket);
        }
        if (z2) {
            return;
        }
        C5118e.m13853c(f12013i, "set default cipher suites");
        SSLUtil.setEnableSafeCipherSuites((SSLSocket) socket);
    }

    public SecureApacheSSLSocketFactory(KeyStore keyStore, InputStream inputStream, String str, SecureRandom secureRandom) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException, IllegalArgumentException {
        super(keyStore);
        this.f12016b = null;
        this.f12015a = SSLUtil.setSSLContext();
        HiCloudX509TrustManager hiCloudX509TrustManager = new HiCloudX509TrustManager(inputStream, str);
        setX509TrustManager(hiCloudX509TrustManager);
        this.f12015a.init(null, new X509TrustManager[]{hiCloudX509TrustManager}, secureRandom);
    }

    @Deprecated
    public SecureApacheSSLSocketFactory(KeyStore keyStore, X509TrustManager x509TrustManager) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException, IllegalArgumentException {
        super(keyStore);
        this.f12016b = null;
        this.f12015a = SSLUtil.setSSLContext();
        setX509TrustManager(x509TrustManager);
        this.f12015a.init(null, new X509TrustManager[]{x509TrustManager}, null);
    }

    public SecureApacheSSLSocketFactory(KeyStore keyStore, X509TrustManager x509TrustManager, SecureRandom secureRandom) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException, IllegalArgumentException {
        super(keyStore);
        this.f12016b = null;
        this.f12015a = SSLUtil.setSSLContext();
        setX509TrustManager(x509TrustManager);
        this.f12015a.init(null, new X509TrustManager[]{x509TrustManager}, secureRandom);
    }
}
