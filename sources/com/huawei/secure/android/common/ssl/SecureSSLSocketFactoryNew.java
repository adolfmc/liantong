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

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SecureSSLSocketFactoryNew extends SSLSocketFactory {

    /* renamed from: i */
    private static final String f12033i = "SSLFNew";

    /* renamed from: j */
    private static volatile SecureSSLSocketFactoryNew f12034j;

    /* renamed from: a */
    protected SSLContext f12035a;

    /* renamed from: b */
    protected SSLSocket f12036b;

    /* renamed from: c */
    protected Context f12037c;

    /* renamed from: d */
    protected String[] f12038d;

    /* renamed from: e */
    protected X509TrustManager f12039e;

    /* renamed from: f */
    protected String[] f12040f;

    /* renamed from: g */
    protected String[] f12041g;

    /* renamed from: h */
    protected String[] f12042h;

    @Deprecated
    public SecureSSLSocketFactoryNew(InputStream inputStream, String str) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, KeyManagementException, IllegalArgumentException {
        this.f12035a = null;
        this.f12036b = null;
        this.f12035a = SSLUtil.setSSLContext();
        HiCloudX509TrustManager hiCloudX509TrustManager = new HiCloudX509TrustManager(inputStream, str);
        setX509TrustManager(hiCloudX509TrustManager);
        this.f12035a.init(null, new X509TrustManager[]{hiCloudX509TrustManager}, null);
    }

    /* renamed from: a */
    private void m13922a(Socket socket) {
        boolean z;
        boolean z2 = true;
        if (C5114a.m13885a(this.f12042h)) {
            z = false;
        } else {
            C5118e.m13853c(f12033i, "set protocols");
            SSLUtil.setEnabledProtocols((SSLSocket) socket, this.f12042h);
            z = true;
        }
        if (C5114a.m13885a(this.f12041g) && C5114a.m13885a(this.f12040f)) {
            z2 = false;
        } else {
            C5118e.m13853c(f12033i, "set cipher");
            SSLSocket sSLSocket = (SSLSocket) socket;
            SSLUtil.setEnabledProtocols(sSLSocket);
            if (!C5114a.m13885a(this.f12041g)) {
                SSLUtil.setWhiteListCipherSuites(sSLSocket, this.f12041g);
            } else {
                SSLUtil.setBlackListCipherSuites(sSLSocket, this.f12040f);
            }
        }
        if (!z) {
            C5118e.m13853c(f12033i, "set default protocols");
            SSLUtil.setEnabledProtocols((SSLSocket) socket);
        }
        if (z2) {
            return;
        }
        C5118e.m13853c(f12033i, "set default cipher");
        SSLUtil.setEnableSafeCipherSuites((SSLSocket) socket);
    }

    @Deprecated
    public static SecureSSLSocketFactoryNew getInstance(Context context) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, IllegalAccessException, KeyManagementException, IllegalArgumentException {
        long currentTimeMillis = System.currentTimeMillis();
        ContextUtil.setContext(context);
        if (f12034j == null) {
            synchronized (SecureSSLSocketFactoryNew.class) {
                if (f12034j == null) {
                    f12034j = new SecureSSLSocketFactoryNew(context, (SecureRandom) null);
                }
            }
        }
        if (f12034j.f12037c == null && context != null) {
            f12034j.setContext(context);
        }
        C5118e.m13856a(f12033i, "getInstance: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
        return f12034j;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) throws IOException {
        C5118e.m13853c(f12033i, "createSocket: host , port");
        Socket createSocket = this.f12035a.getSocketFactory().createSocket(str, i);
        if (createSocket instanceof SSLSocket) {
            m13922a(createSocket);
            this.f12036b = (SSLSocket) createSocket;
            this.f12038d = (String[]) this.f12036b.getEnabledCipherSuites().clone();
        }
        return createSocket;
    }

    public String[] getBlackCiphers() {
        return this.f12040f;
    }

    public X509Certificate[] getChain() {
        X509TrustManager x509TrustManager = this.f12039e;
        if (x509TrustManager instanceof SecureX509TrustManager) {
            return ((SecureX509TrustManager) x509TrustManager).getChain();
        }
        return new X509Certificate[0];
    }

    public Context getContext() {
        return this.f12037c;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return new String[0];
    }

    public String[] getProtocols() {
        return this.f12042h;
    }

    public SSLContext getSslContext() {
        return this.f12035a;
    }

    public SSLSocket getSslSocket() {
        return this.f12036b;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        String[] strArr = this.f12038d;
        return strArr != null ? strArr : new String[0];
    }

    public String[] getWhiteCiphers() {
        return this.f12041g;
    }

    public X509TrustManager getX509TrustManager() {
        return this.f12039e;
    }

    public void setBlackCiphers(String[] strArr) {
        this.f12040f = strArr;
    }

    public void setContext(Context context) {
        this.f12037c = context.getApplicationContext();
    }

    public void setProtocols(String[] strArr) {
        this.f12042h = strArr;
    }

    public void setSslContext(SSLContext sSLContext) {
        this.f12035a = sSLContext;
    }

    public void setWhiteCiphers(String[] strArr) {
        this.f12041g = strArr;
    }

    public void setX509TrustManager(X509TrustManager x509TrustManager) {
        this.f12039e = x509TrustManager;
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
        C5118e.m13853c(f12033i, "createSocket");
        Socket createSocket = this.f12035a.getSocketFactory().createSocket(socket, str, i, z);
        if (createSocket instanceof SSLSocket) {
            m13922a(createSocket);
            this.f12036b = (SSLSocket) createSocket;
            this.f12038d = (String[]) this.f12036b.getEnabledCipherSuites().clone();
        }
        return createSocket;
    }

    public static SecureSSLSocketFactoryNew getInstance(Context context, SecureRandom secureRandom) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, IllegalAccessException, KeyManagementException, IllegalArgumentException {
        long currentTimeMillis = System.currentTimeMillis();
        ContextUtil.setContext(context);
        if (f12034j == null) {
            synchronized (SecureSSLSocketFactoryNew.class) {
                if (f12034j == null) {
                    f12034j = new SecureSSLSocketFactoryNew(context, secureRandom);
                }
            }
        }
        if (f12034j.f12037c == null && context != null) {
            f12034j.setContext(context);
        }
        C5118e.m13856a(f12033i, "getInstance: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
        return f12034j;
    }

    public SecureSSLSocketFactoryNew(InputStream inputStream, String str, SecureRandom secureRandom) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, KeyManagementException, IllegalArgumentException {
        this.f12035a = null;
        this.f12036b = null;
        this.f12035a = SSLUtil.setSSLContext();
        HiCloudX509TrustManager hiCloudX509TrustManager = new HiCloudX509TrustManager(inputStream, str);
        setX509TrustManager(hiCloudX509TrustManager);
        this.f12035a.init(null, new X509TrustManager[]{hiCloudX509TrustManager}, secureRandom);
    }

    private SecureSSLSocketFactoryNew(Context context, SecureRandom secureRandom) throws NoSuchAlgorithmException, CertificateException, KeyStoreException, IOException, KeyManagementException {
        this.f12035a = null;
        this.f12036b = null;
        if (context == null) {
            C5118e.m13854b(f12033i, "SecureSSLSocketFactory: context is null");
            return;
        }
        setContext(context);
        setSslContext(SSLUtil.setSSLContext());
        this.f12039e = SecureX509SingleInstance.getInstance(context);
        this.f12035a.init(null, new X509TrustManager[]{this.f12039e}, secureRandom);
    }

    @Deprecated
    public SecureSSLSocketFactoryNew(X509TrustManager x509TrustManager) throws NoSuchAlgorithmException, KeyManagementException, IllegalArgumentException {
        this.f12035a = null;
        this.f12036b = null;
        this.f12035a = SSLUtil.setSSLContext();
        setX509TrustManager(x509TrustManager);
        this.f12035a.init(null, new X509TrustManager[]{x509TrustManager}, null);
    }

    public SecureSSLSocketFactoryNew(X509TrustManager x509TrustManager, SecureRandom secureRandom) throws NoSuchAlgorithmException, KeyManagementException, IllegalArgumentException {
        this.f12035a = null;
        this.f12036b = null;
        this.f12035a = SSLUtil.setSSLContext();
        setX509TrustManager(x509TrustManager);
        this.f12035a.init(null, new X509TrustManager[]{x509TrustManager}, secureRandom);
    }
}
