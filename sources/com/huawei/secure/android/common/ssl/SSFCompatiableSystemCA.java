package com.huawei.secure.android.common.ssl;

import android.content.Context;
import com.huawei.secure.android.common.ssl.util.C5114a;
import com.huawei.secure.android.common.ssl.util.C5118e;
import com.huawei.secure.android.common.ssl.util.ContextUtil;
import java.io.IOException;
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
public class SSFCompatiableSystemCA extends SSLSocketFactory {

    /* renamed from: i */
    private static final String f11993i = "SSFCompatiableSystemCA";

    /* renamed from: j */
    private static volatile SSFCompatiableSystemCA f11994j;

    /* renamed from: a */
    private SSLContext f11995a;

    /* renamed from: b */
    private SSLSocket f11996b;

    /* renamed from: c */
    private Context f11997c;

    /* renamed from: d */
    private String[] f11998d;

    /* renamed from: e */
    private X509TrustManager f11999e;

    /* renamed from: f */
    private String[] f12000f;

    /* renamed from: g */
    private String[] f12001g;

    /* renamed from: h */
    private String[] f12002h;

    private SSFCompatiableSystemCA(Context context, SecureRandom secureRandom) throws NoSuchAlgorithmException, CertificateException, KeyStoreException, IOException, KeyManagementException {
        this.f11995a = null;
        this.f11996b = null;
        if (context == null) {
            C5118e.m13854b(f11993i, "SecureSSLSocketFactory: context is null");
            return;
        }
        setContext(context);
        setSslContext(SSLUtil.setSSLContext());
        this.f11999e = SSFSecureX509SingleInstance.getInstance(context);
        this.f11995a.init(null, new X509TrustManager[]{this.f11999e}, secureRandom);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    /* renamed from: a */
    public static void m13930a(X509TrustManager x509TrustManager) {
        C5118e.m13853c(f11993i, "ssfc update socket factory trust manager");
        long currentTimeMillis = System.currentTimeMillis();
        try {
            f11994j = new SSFCompatiableSystemCA(x509TrustManager);
        } catch (KeyManagementException unused) {
            C5118e.m13854b(f11993i, "KeyManagementException");
        } catch (NoSuchAlgorithmException unused2) {
            C5118e.m13854b(f11993i, "NoSuchAlgorithmException");
        }
        String str = f11993i;
        C5118e.m13856a(str, "SSF system ca update: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }

    @Deprecated
    public static SSFCompatiableSystemCA getInstance(Context context) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, KeyManagementException, IllegalArgumentException {
        ContextUtil.setContext(context);
        if (f11994j == null) {
            synchronized (SSFCompatiableSystemCA.class) {
                if (f11994j == null) {
                    f11994j = new SSFCompatiableSystemCA(context, (SecureRandom) null);
                }
            }
        }
        if (f11994j.f11997c == null && context != null) {
            f11994j.setContext(context);
        }
        return f11994j;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) throws IOException {
        C5118e.m13853c(f11993i, "createSocket: host , port");
        Socket createSocket = this.f11995a.getSocketFactory().createSocket(str, i);
        if (createSocket instanceof SSLSocket) {
            m13931a(createSocket);
            this.f11996b = (SSLSocket) createSocket;
            this.f11998d = (String[]) this.f11996b.getEnabledCipherSuites().clone();
        }
        return createSocket;
    }

    public String[] getBlackCiphers() {
        return this.f12000f;
    }

    public X509Certificate[] getChain() {
        X509TrustManager x509TrustManager = this.f11999e;
        if (x509TrustManager instanceof SecureX509TrustManager) {
            return ((SecureX509TrustManager) x509TrustManager).getChain();
        }
        return new X509Certificate[0];
    }

    public Context getContext() {
        return this.f11997c;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return new String[0];
    }

    public String[] getProtocols() {
        return this.f12002h;
    }

    public SSLContext getSslContext() {
        return this.f11995a;
    }

    public SSLSocket getSslSocket() {
        return this.f11996b;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        String[] strArr = this.f11998d;
        return strArr != null ? strArr : new String[0];
    }

    public String[] getWhiteCiphers() {
        return this.f12001g;
    }

    public X509TrustManager getX509TrustManager() {
        return this.f11999e;
    }

    public void setBlackCiphers(String[] strArr) {
        this.f12000f = strArr;
    }

    public void setContext(Context context) {
        this.f11997c = context.getApplicationContext();
    }

    public void setProtocols(String[] strArr) {
        this.f12002h = strArr;
    }

    public void setSslContext(SSLContext sSLContext) {
        this.f11995a = sSLContext;
    }

    public void setWhiteCiphers(String[] strArr) {
        this.f12001g = strArr;
    }

    public void setX509TrustManager(X509TrustManager x509TrustManager) {
        this.f11999e = x509TrustManager;
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
        C5118e.m13853c(f11993i, "createSocket: s , host , port , autoClose");
        Socket createSocket = this.f11995a.getSocketFactory().createSocket(socket, str, i, z);
        if (createSocket instanceof SSLSocket) {
            m13931a(createSocket);
            this.f11996b = (SSLSocket) createSocket;
            this.f11998d = (String[]) this.f11996b.getEnabledCipherSuites().clone();
        }
        return createSocket;
    }

    public static SSFCompatiableSystemCA getInstance(Context context, SecureRandom secureRandom) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, KeyManagementException, IllegalArgumentException {
        ContextUtil.setContext(context);
        if (f11994j == null) {
            synchronized (SSFCompatiableSystemCA.class) {
                if (f11994j == null) {
                    f11994j = new SSFCompatiableSystemCA(context, secureRandom);
                }
            }
        }
        if (f11994j.f11997c == null && context != null) {
            f11994j.setContext(context);
        }
        return f11994j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m13929a(X509TrustManager x509TrustManager, SecureRandom secureRandom) {
        C5118e.m13853c(f11993i, "ssfc update socket factory trust manager");
        long currentTimeMillis = System.currentTimeMillis();
        try {
            f11994j = new SSFCompatiableSystemCA(x509TrustManager, secureRandom);
        } catch (KeyManagementException unused) {
            C5118e.m13854b(f11993i, "KeyManagementException");
        } catch (NoSuchAlgorithmException unused2) {
            C5118e.m13854b(f11993i, "NoSuchAlgorithmException");
        }
        String str = f11993i;
        C5118e.m13856a(str, "SSF system ca update: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }

    /* renamed from: a */
    private void m13931a(Socket socket) {
        boolean z;
        boolean z2 = true;
        if (C5114a.m13885a(this.f12002h)) {
            z = false;
        } else {
            C5118e.m13853c(f11993i, "set protocols");
            SSLUtil.setEnabledProtocols((SSLSocket) socket, this.f12002h);
            z = true;
        }
        if (C5114a.m13885a(this.f12001g) && C5114a.m13885a(this.f12000f)) {
            z2 = false;
        } else {
            C5118e.m13853c(f11993i, "set white cipher or black cipher");
            SSLSocket sSLSocket = (SSLSocket) socket;
            SSLUtil.setEnabledProtocols(sSLSocket);
            if (!C5114a.m13885a(this.f12001g)) {
                SSLUtil.setWhiteListCipherSuites(sSLSocket, this.f12001g);
            } else {
                SSLUtil.setBlackListCipherSuites(sSLSocket, this.f12000f);
            }
        }
        if (!z) {
            C5118e.m13853c(f11993i, "set default protocols");
            SSLUtil.setEnabledProtocols((SSLSocket) socket);
        }
        if (z2) {
            return;
        }
        C5118e.m13853c(f11993i, "set default cipher suites");
        SSLUtil.setEnableSafeCipherSuites((SSLSocket) socket);
    }

    @Deprecated
    public SSFCompatiableSystemCA(X509TrustManager x509TrustManager) throws NoSuchAlgorithmException, KeyManagementException, IllegalArgumentException {
        this.f11995a = null;
        this.f11996b = null;
        this.f11995a = SSLUtil.setSSLContext();
        setX509TrustManager(x509TrustManager);
        this.f11995a.init(null, new X509TrustManager[]{x509TrustManager}, null);
    }

    public SSFCompatiableSystemCA(X509TrustManager x509TrustManager, SecureRandom secureRandom) throws NoSuchAlgorithmException, KeyManagementException, IllegalArgumentException {
        this.f11995a = null;
        this.f11996b = null;
        this.f11995a = SSLUtil.setSSLContext();
        setX509TrustManager(x509TrustManager);
        this.f11995a.init(null, new X509TrustManager[]{x509TrustManager}, secureRandom);
    }
}
