package com.networkbench.agent.impl.socket;

import android.annotation.TargetApi;
import com.android.org.conscrypt.SSLParametersImpl;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6645n;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class CustomSSLSocketFactory extends AbstractC6600a {
    private static boolean installed;
    private static SSLParametersImpl parameters;
    private static SSLSocketFactory sslSocketFactory;
    private SSLSocketFactory delegate;
    private SSLParametersImpl sslParameters;

    /* renamed from: b */
    public static boolean m9282b() throws ThreadDeath {
        boolean z = installed;
        if (z) {
            return z;
        }
        SSLSocketFactory defaultSSLSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
        try {
            CustomSSLSocketFactory customSSLSocketFactory = new CustomSSLSocketFactory(defaultSSLSocketFactory);
            try {
                customSSLSocketFactory.createSocket(customSSLSocketFactory.createSocket(), "localhost", 6895, true);
            } catch (SocketException unused) {
            }
            HttpsURLConnection.setDefaultSSLSocketFactory(customSSLSocketFactory);
            sslSocketFactory = defaultSSLSocketFactory;
            installed = true;
            return true;
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable unused2) {
            return false;
        }
    }

    public CustomSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.delegate = sSLSocketFactory;
        this.sslParameters = m9283a(sSLSocketFactory);
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final String[] getDefaultCipherSuites() {
        return this.delegate.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final String[] getSupportedCipherSuites() {
        return this.delegate.getSupportedCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        return new C6608e(socket, str, i, z, m9284a(this.sslParameters));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(String str, int i) {
        return new C6603b(str, i, m9284a(this.sslParameters));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        return new C6603b(str, i, inetAddress, i2, m9284a(this.sslParameters));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(InetAddress inetAddress, int i) {
        return new C6603b(inetAddress, i, m9284a(this.sslParameters));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        return new C6603b(inetAddress, i, inetAddress2, i2, m9284a(this.sslParameters));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket() {
        return new C6603b(m9284a(this.sslParameters));
    }

    /* renamed from: a */
    private static SSLParametersImpl m9283a(SSLSocketFactory sSLSocketFactory) {
        SSLParametersImpl sSLParametersImpl;
        try {
            sSLParametersImpl = (SSLParametersImpl) C6645n.m8874b(C6645n.m8882a((Class) sSLSocketFactory.getClass(), SSLParametersImpl.class, false), sSLSocketFactory);
        } catch (Throwable unused) {
            sSLParametersImpl = null;
        }
        if (sSLParametersImpl == null) {
            C6638h.f17124y.mo10122a("sSLParametersImpl == null");
            sSLParametersImpl = m9281c();
        }
        parameters = sSLParametersImpl;
        return sSLParametersImpl;
    }

    /* renamed from: a */
    private static SSLParametersImpl m9284a(SSLParametersImpl sSLParametersImpl) {
        try {
            Method declaredMethod = SSLParametersImpl.class.getDeclaredMethod("clone", new Class[0]);
            declaredMethod.setAccessible(true);
            return (SSLParametersImpl) declaredMethod.invoke(sSLParametersImpl, new Object[0]);
        } catch (Throwable unused) {
            return parameters;
        }
    }

    @TargetApi(9)
    /* renamed from: c */
    private static SSLParametersImpl m9281c() {
        try {
            Method declaredMethod = SSLParametersImpl.class.getDeclaredMethod("getDefault", new Class[0]);
            declaredMethod.setAccessible(true);
            return (SSLParametersImpl) declaredMethod.invoke(null, new Object[0]);
        } catch (Exception e) {
            C6638h.f17124y.mo10121a("createAvalidSSLParameters is error:", e);
            return null;
        }
    }

    @Override // com.networkbench.agent.impl.socket.AbstractC6600a
    /* renamed from: a */
    public final SSLSocketFactory mo9276a() {
        return this.delegate;
    }
}
