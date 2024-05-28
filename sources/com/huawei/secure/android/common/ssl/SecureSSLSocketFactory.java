package com.huawei.secure.android.common.ssl;

import android.content.Context;
import com.huawei.secure.android.common.ssl.util.C5114a;
import com.huawei.secure.android.common.ssl.util.C5118e;
import com.huawei.secure.android.common.ssl.util.ContextUtil;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;
import org.apache.http.conn.ssl.StrictHostnameVerifier;
import org.apache.http.conn.ssl.X509HostnameVerifier;

@Deprecated
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SecureSSLSocketFactory extends SSLSocketFactory {
    @Deprecated
    public static final X509HostnameVerifier BROWSER_COMPATIBLE_HOSTNAME_VERIFIER = new BrowserCompatHostnameVerifier();
    @Deprecated
    public static final X509HostnameVerifier STRICT_HOSTNAME_VERIFIER = new StrictHostnameVerifier();

    /* renamed from: i */
    private static final String f12023i = SecureSSLSocketFactory.class.getSimpleName();

    /* renamed from: j */
    private static volatile SecureSSLSocketFactory f12024j = null;

    /* renamed from: a */
    private SSLContext f12025a;

    /* renamed from: b */
    private SSLSocket f12026b;

    /* renamed from: c */
    private Context f12027c;

    /* renamed from: d */
    private String[] f12028d;

    /* renamed from: e */
    private X509TrustManager f12029e;

    /* renamed from: f */
    private String[] f12030f;

    /* renamed from: g */
    private String[] f12031g;

    /* renamed from: h */
    private String[] f12032h;

    @Deprecated
    public SecureSSLSocketFactory(InputStream inputStream, String str) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, KeyManagementException, IllegalArgumentException {
        this.f12025a = null;
        this.f12026b = null;
        this.f12025a = SSLUtil.setSSLContext();
        HiCloudX509TrustManager hiCloudX509TrustManager = new HiCloudX509TrustManager(inputStream, str);
        setX509TrustManager(hiCloudX509TrustManager);
        this.f12025a.init(null, new X509TrustManager[]{hiCloudX509TrustManager}, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    /* renamed from: a */
    public static void m13924a(X509TrustManager x509TrustManager) {
        C5118e.m13853c(f12023i, "ssf update socket factory trust manager");
        long currentTimeMillis = System.currentTimeMillis();
        try {
            f12024j = new SecureSSLSocketFactory(x509TrustManager);
        } catch (KeyManagementException unused) {
            C5118e.m13854b(f12023i, "KeyManagementException");
        } catch (NoSuchAlgorithmException unused2) {
            C5118e.m13854b(f12023i, "NoSuchAlgorithmException");
        }
        String str = f12023i;
        C5118e.m13856a(str, "update: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }

    @Deprecated
    public static SecureSSLSocketFactory getInstance(Context context) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, IllegalAccessException, KeyManagementException, IllegalArgumentException {
        long currentTimeMillis = System.currentTimeMillis();
        ContextUtil.setContext(context);
        if (f12024j == null) {
            synchronized (SecureSSLSocketFactory.class) {
                if (f12024j == null) {
                    f12024j = new SecureSSLSocketFactory(context, (SecureRandom) null);
                }
            }
        }
        if (f12024j.f12027c == null && context != null) {
            f12024j.setContext(context);
        }
        String str = f12023i;
        C5118e.m13856a(str, "getInstance: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
        return f12024j;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) throws IOException {
        C5118e.m13853c(f12023i, "createSocket: host , port");
        Socket createSocket = this.f12025a.getSocketFactory().createSocket(str, i);
        if (createSocket instanceof SSLSocket) {
            m13925a(createSocket);
            this.f12026b = (SSLSocket) createSocket;
            this.f12028d = (String[]) this.f12026b.getEnabledCipherSuites().clone();
        }
        return createSocket;
    }

    public String[] getBlackCiphers() {
        return this.f12030f;
    }

    public X509Certificate[] getChain() {
        X509TrustManager x509TrustManager = this.f12029e;
        if (x509TrustManager instanceof SecureX509TrustManager) {
            return ((SecureX509TrustManager) x509TrustManager).getChain();
        }
        return new X509Certificate[0];
    }

    public Context getContext() {
        return this.f12027c;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return new String[0];
    }

    public String[] getProtocols() {
        return this.f12032h;
    }

    public SSLContext getSslContext() {
        return this.f12025a;
    }

    public SSLSocket getSslSocket() {
        return this.f12026b;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        String[] strArr = this.f12028d;
        return strArr != null ? strArr : new String[0];
    }

    public String[] getWhiteCiphers() {
        return this.f12031g;
    }

    public X509TrustManager getX509TrustManager() {
        return this.f12029e;
    }

    public void setBlackCiphers(String[] strArr) {
        this.f12030f = strArr;
    }

    public void setContext(Context context) {
        this.f12027c = context.getApplicationContext();
    }

    public void setProtocols(String[] strArr) {
        this.f12032h = strArr;
    }

    public void setSslContext(SSLContext sSLContext) {
        this.f12025a = sSLContext;
    }

    public void setWhiteCiphers(String[] strArr) {
        this.f12031g = strArr;
    }

    public void setX509TrustManager(X509TrustManager x509TrustManager) {
        this.f12029e = x509TrustManager;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return createSocket(inetAddress.getHostAddress(), i);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException, UnknownHostException {
        return createSocket(str, i);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return createSocket(inetAddress.getHostAddress(), i);
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        C5118e.m13853c(f12023i, "createSocket s host port autoClose");
        Socket createSocket = this.f12025a.getSocketFactory().createSocket(socket, str, i, z);
        if (createSocket instanceof SSLSocket) {
            m13925a(createSocket);
            this.f12026b = (SSLSocket) createSocket;
            this.f12028d = (String[]) this.f12026b.getEnabledCipherSuites().clone();
        }
        return createSocket;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m13923a(X509TrustManager x509TrustManager, SecureRandom secureRandom) {
        C5118e.m13853c(f12023i, "ssf update socket factory trust manager");
        long currentTimeMillis = System.currentTimeMillis();
        try {
            f12024j = new SecureSSLSocketFactory(x509TrustManager, secureRandom);
        } catch (KeyManagementException unused) {
            C5118e.m13854b(f12023i, "KeyManagementException");
        } catch (NoSuchAlgorithmException unused2) {
            C5118e.m13854b(f12023i, "NoSuchAlgorithmException");
        }
        String str = f12023i;
        C5118e.m13856a(str, "update: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }

    public static SecureSSLSocketFactory getInstance(Context context, SecureRandom secureRandom) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, IllegalAccessException, KeyManagementException, IllegalArgumentException {
        long currentTimeMillis = System.currentTimeMillis();
        ContextUtil.setContext(context);
        if (f12024j == null) {
            synchronized (SecureSSLSocketFactory.class) {
                if (f12024j == null) {
                    f12024j = new SecureSSLSocketFactory(context, secureRandom);
                }
            }
        }
        if (f12024j.f12027c == null && context != null) {
            f12024j.setContext(context);
        }
        String str = f12023i;
        C5118e.m13856a(str, "getInstance: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
        return f12024j;
    }

    public SecureSSLSocketFactory(InputStream inputStream, String str, SecureRandom secureRandom) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, KeyManagementException, IllegalArgumentException {
        this.f12025a = null;
        this.f12026b = null;
        this.f12025a = SSLUtil.setSSLContext();
        HiCloudX509TrustManager hiCloudX509TrustManager = new HiCloudX509TrustManager(inputStream, str);
        setX509TrustManager(hiCloudX509TrustManager);
        this.f12025a.init(null, new X509TrustManager[]{hiCloudX509TrustManager}, secureRandom);
    }

    /* renamed from: a */
    private void m13925a(Socket socket) {
        boolean z;
        boolean z2 = true;
        if (C5114a.m13885a(this.f12032h)) {
            z = false;
        } else {
            C5118e.m13853c(f12023i, "set protocols");
            SSLUtil.setEnabledProtocols((SSLSocket) socket, this.f12032h);
            z = true;
        }
        if (C5114a.m13885a(this.f12031g) && C5114a.m13885a(this.f12030f)) {
            z2 = false;
        } else {
            C5118e.m13853c(f12023i, "set white cipher or black cipher");
            SSLSocket sSLSocket = (SSLSocket) socket;
            SSLUtil.setEnabledProtocols(sSLSocket);
            if (!C5114a.m13885a(this.f12031g)) {
                SSLUtil.setWhiteListCipherSuites(sSLSocket, this.f12031g);
            } else {
                SSLUtil.setBlackListCipherSuites(sSLSocket, this.f12030f);
            }
        }
        if (!z) {
            C5118e.m13853c(f12023i, "set default protocols");
            SSLUtil.setEnabledProtocols((SSLSocket) socket);
        }
        if (z2) {
            return;
        }
        C5118e.m13853c(f12023i, "set default cipher suites");
        SSLUtil.setEnableSafeCipherSuites((SSLSocket) socket);
    }

    private SecureSSLSocketFactory(Context context, SecureRandom secureRandom) throws NoSuchAlgorithmException, CertificateException, KeyStoreException, IOException, KeyManagementException {
        this.f12025a = null;
        this.f12026b = null;
        if (context == null) {
            C5118e.m13854b(f12023i, "SecureSSLSocketFactory: context is null");
            return;
        }
        setContext(context);
        setSslContext(SSLUtil.setSSLContext());
        this.f12029e = SecureX509SingleInstance.getInstance(context);
        this.f12025a.init(null, new X509TrustManager[]{this.f12029e}, secureRandom);
    }

    @Deprecated
    public SecureSSLSocketFactory(X509TrustManager x509TrustManager) throws NoSuchAlgorithmException, KeyManagementException, IllegalArgumentException {
        this.f12025a = null;
        this.f12026b = null;
        this.f12025a = SSLUtil.setSSLContext();
        setX509TrustManager(x509TrustManager);
        this.f12025a.init(null, new X509TrustManager[]{x509TrustManager}, null);
    }

    public SecureSSLSocketFactory(X509TrustManager x509TrustManager, SecureRandom secureRandom) throws NoSuchAlgorithmException, KeyManagementException, IllegalArgumentException {
        this.f12025a = null;
        this.f12026b = null;
        this.f12025a = SSLUtil.setSSLContext();
        setX509TrustManager(x509TrustManager);
        this.f12025a.init(null, new X509TrustManager[]{x509TrustManager}, secureRandom);
    }
}
