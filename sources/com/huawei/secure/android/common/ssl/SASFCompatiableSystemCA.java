package com.huawei.secure.android.common.ssl;

import android.content.Context;
import com.huawei.secure.android.common.ssl.util.C5114a;
import com.huawei.secure.android.common.ssl.util.C5118e;
import com.huawei.secure.android.common.ssl.util.ContextUtil;
import java.io.IOException;
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
import org.apache.http.conn.ssl.SSLSocketFactory;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SASFCompatiableSystemCA extends SSLSocketFactory {

    /* renamed from: i */
    private static final String f11983i = "SASFCompatiableSystemCA";

    /* renamed from: j */
    private static volatile SASFCompatiableSystemCA f11984j;

    /* renamed from: a */
    private SSLContext f11985a;

    /* renamed from: b */
    private SSLSocket f11986b;

    /* renamed from: c */
    private Context f11987c;

    /* renamed from: d */
    private String[] f11988d;

    /* renamed from: e */
    private X509TrustManager f11989e;

    /* renamed from: f */
    private String[] f11990f;

    /* renamed from: g */
    private String[] f11991g;

    /* renamed from: h */
    private String[] f11992h;

    private SASFCompatiableSystemCA(KeyStore keyStore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        super(keyStore);
        this.f11986b = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    /* renamed from: a */
    public static void m13933a(X509TrustManager x509TrustManager) {
        C5118e.m13853c(f11983i, "sasfc update socket factory trust manager");
        long currentTimeMillis = System.currentTimeMillis();
        try {
            f11984j = new SASFCompatiableSystemCA(null, x509TrustManager);
        } catch (KeyManagementException unused) {
            C5118e.m13854b(f11983i, "KeyManagementException");
        } catch (KeyStoreException unused2) {
            C5118e.m13854b(f11983i, "KeyStoreException");
        } catch (NoSuchAlgorithmException unused3) {
            C5118e.m13854b(f11983i, "NoSuchAlgorithmException");
        } catch (UnrecoverableKeyException unused4) {
            C5118e.m13854b(f11983i, "UnrecoverableKeyException");
        }
        String str = f11983i;
        C5118e.m13856a(str, "sasf system ca update: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }

    @Deprecated
    public static SASFCompatiableSystemCA getInstance(KeyStore keyStore, Context context) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException, IllegalArgumentException {
        ContextUtil.setContext(context);
        if (f11984j == null) {
            synchronized (SecureApacheSSLSocketFactory.class) {
                if (f11984j == null) {
                    f11984j = new SASFCompatiableSystemCA(keyStore, context, (SecureRandom) null);
                }
            }
        }
        return f11984j;
    }

    @Override // org.apache.http.conn.ssl.SSLSocketFactory, org.apache.http.conn.scheme.LayeredSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        C5118e.m13853c(f11983i, "createSocket: socket host port autoClose");
        Socket createSocket = this.f11985a.getSocketFactory().createSocket(socket, str, i, z);
        if (createSocket instanceof SSLSocket) {
            m13934a(createSocket);
            this.f11986b = (SSLSocket) createSocket;
            this.f11988d = (String[]) this.f11986b.getEnabledCipherSuites().clone();
        }
        return createSocket;
    }

    public String[] getBlackCiphers() {
        return this.f11990f;
    }

    public X509Certificate[] getChain() {
        X509TrustManager x509TrustManager = this.f11989e;
        if (x509TrustManager instanceof SecureX509TrustManager) {
            return ((SecureX509TrustManager) x509TrustManager).getChain();
        }
        return new X509Certificate[0];
    }

    public Context getContext() {
        return this.f11987c;
    }

    public String[] getProtocols() {
        return this.f11992h;
    }

    public SSLContext getSslContext() {
        return this.f11985a;
    }

    public SSLSocket getSslSocket() {
        return this.f11986b;
    }

    public String[] getSupportedCipherSuites() {
        String[] strArr = this.f11988d;
        return strArr != null ? strArr : new String[0];
    }

    public String[] getWhiteCiphers() {
        return this.f11991g;
    }

    public X509TrustManager getX509TrustManager() {
        return this.f11989e;
    }

    public void setBlackCiphers(String[] strArr) {
        this.f11990f = strArr;
    }

    public void setContext(Context context) {
        this.f11987c = context.getApplicationContext();
    }

    public void setProtocols(String[] strArr) {
        this.f11992h = strArr;
    }

    public void setSslContext(SSLContext sSLContext) {
        this.f11985a = sSLContext;
    }

    public void setSslSocket(SSLSocket sSLSocket) {
        this.f11986b = sSLSocket;
    }

    public void setWhiteCiphers(String[] strArr) {
        this.f11991g = strArr;
    }

    public void setX509TrustManager(X509TrustManager x509TrustManager) {
        this.f11989e = x509TrustManager;
    }

    private SASFCompatiableSystemCA(KeyStore keyStore, Context context, SecureRandom secureRandom) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException, IllegalArgumentException {
        super(keyStore);
        this.f11986b = null;
        if (context == null) {
            C5118e.m13854b(f11983i, "SecureSSLSocketFactory: context is null");
            return;
        }
        setContext(context);
        setSslContext(SSLUtil.setSSLContext());
        this.f11989e = SSFSecureX509SingleInstance.getInstance(context);
        this.f11985a.init(null, new X509TrustManager[]{this.f11989e}, secureRandom);
    }

    @Override // org.apache.http.conn.ssl.SSLSocketFactory, org.apache.http.conn.scheme.SocketFactory
    public Socket createSocket() throws IOException {
        C5118e.m13853c(f11983i, "createSocket: ");
        Socket createSocket = this.f11985a.getSocketFactory().createSocket();
        if (createSocket instanceof SSLSocket) {
            m13934a(createSocket);
            this.f11986b = (SSLSocket) createSocket;
            this.f11988d = (String[]) this.f11986b.getEnabledCipherSuites().clone();
        }
        return createSocket;
    }

    public static SASFCompatiableSystemCA getInstance(KeyStore keyStore, Context context, SecureRandom secureRandom) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException, IllegalArgumentException {
        ContextUtil.setContext(context);
        if (f11984j == null) {
            synchronized (SecureApacheSSLSocketFactory.class) {
                if (f11984j == null) {
                    f11984j = new SASFCompatiableSystemCA(keyStore, context, secureRandom);
                }
            }
        }
        return f11984j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m13932a(X509TrustManager x509TrustManager, SecureRandom secureRandom) {
        C5118e.m13853c(f11983i, "sasfc update socket factory trust manager");
        long currentTimeMillis = System.currentTimeMillis();
        try {
            f11984j = new SASFCompatiableSystemCA((KeyStore) null, x509TrustManager, secureRandom);
        } catch (KeyManagementException unused) {
            C5118e.m13854b(f11983i, "KeyManagementException");
        } catch (KeyStoreException unused2) {
            C5118e.m13854b(f11983i, "KeyStoreException");
        } catch (NoSuchAlgorithmException unused3) {
            C5118e.m13854b(f11983i, "NoSuchAlgorithmException");
        } catch (UnrecoverableKeyException unused4) {
            C5118e.m13854b(f11983i, "UnrecoverableKeyException");
        }
        String str = f11983i;
        C5118e.m13856a(str, "sasf system ca update: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }

    @Deprecated
    public SASFCompatiableSystemCA(KeyStore keyStore, X509TrustManager x509TrustManager) throws NoSuchAlgorithmException, KeyManagementException, IllegalArgumentException, UnrecoverableKeyException, KeyStoreException {
        super(keyStore);
        this.f11986b = null;
        this.f11985a = SSLUtil.setSSLContext();
        setX509TrustManager(x509TrustManager);
        this.f11985a.init(null, new X509TrustManager[]{x509TrustManager}, null);
    }

    /* renamed from: a */
    private void m13934a(Socket socket) {
        boolean z;
        boolean z2 = true;
        if (C5114a.m13885a(this.f11992h)) {
            z = false;
        } else {
            C5118e.m13853c(f11983i, "set protocols");
            SSLUtil.setEnabledProtocols((SSLSocket) socket, this.f11992h);
            z = true;
        }
        if (C5114a.m13885a(this.f11991g) && C5114a.m13885a(this.f11990f)) {
            z2 = false;
        } else {
            C5118e.m13853c(f11983i, "set white cipher or black cipher");
            SSLSocket sSLSocket = (SSLSocket) socket;
            SSLUtil.setEnabledProtocols(sSLSocket);
            if (!C5114a.m13885a(this.f11991g)) {
                SSLUtil.setWhiteListCipherSuites(sSLSocket, this.f11991g);
            } else {
                SSLUtil.setBlackListCipherSuites(sSLSocket, this.f11990f);
            }
        }
        if (!z) {
            C5118e.m13853c(f11983i, "set default protocols");
            SSLUtil.setEnabledProtocols((SSLSocket) socket);
        }
        if (z2) {
            return;
        }
        C5118e.m13853c(f11983i, "set default cipher suites");
        SSLUtil.setEnableSafeCipherSuites((SSLSocket) socket);
    }

    public SASFCompatiableSystemCA(KeyStore keyStore, X509TrustManager x509TrustManager, SecureRandom secureRandom) throws NoSuchAlgorithmException, KeyManagementException, IllegalArgumentException, UnrecoverableKeyException, KeyStoreException {
        super(keyStore);
        this.f11986b = null;
        this.f11985a = SSLUtil.setSSLContext();
        setX509TrustManager(x509TrustManager);
        this.f11985a.init(null, new X509TrustManager[]{x509TrustManager}, secureRandom);
    }
}
