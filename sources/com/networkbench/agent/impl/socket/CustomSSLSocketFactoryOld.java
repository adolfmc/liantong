package com.networkbench.agent.impl.socket;

import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6645n;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.harmony.xnet.provider.jsse.SSLParametersImpl;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class CustomSSLSocketFactoryOld extends AbstractC6600a {
    private static SSLSocketFactory context;
    private static boolean installed;
    private static SSLParametersImpl parameters;
    private SSLSocketFactory delegate;
    private SSLParametersImpl sslParameters;

    /* renamed from: b */
    public static boolean m9278b() {
        boolean z = installed;
        if (z) {
            return z;
        }
        SSLSocketFactory defaultSSLSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
        try {
            CustomSSLSocketFactoryOld customSSLSocketFactoryOld = new CustomSSLSocketFactoryOld(defaultSSLSocketFactory);
            try {
                customSSLSocketFactoryOld.createSocket(customSSLSocketFactoryOld.createSocket(), "localhost", 6895, true);
            } catch (SocketException unused) {
            }
            HttpsURLConnection.setDefaultSSLSocketFactory(customSSLSocketFactoryOld);
            context = defaultSSLSocketFactory;
            installed = true;
            return true;
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable unused2) {
            return false;
        }
    }

    public CustomSSLSocketFactoryOld(SSLSocketFactory sSLSocketFactory) {
        this.delegate = sSLSocketFactory;
        this.sslParameters = m9280a(sSLSocketFactory);
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
        return new C6607d(socket, str, i, z, m9279a(this.sslParameters));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(String str, int i) throws IOException {
        return new C6606c(str, i, m9279a(this.sslParameters));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        return new C6606c(str, i, inetAddress, i2, m9279a(this.sslParameters));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(InetAddress inetAddress, int i) {
        return new C6606c(inetAddress, i, m9279a(this.sslParameters));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        return new C6606c(inetAddress, i, inetAddress2, i2, m9279a(this.sslParameters));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket() {
        return new C6606c(m9279a(this.sslParameters));
    }

    /* renamed from: a */
    private static SSLParametersImpl m9280a(SSLSocketFactory sSLSocketFactory) {
        SSLParametersImpl sSLParametersImpl;
        try {
            sSLParametersImpl = (SSLParametersImpl) C6645n.m8874b(C6645n.m8882a((Class) sSLSocketFactory.getClass(), SSLParametersImpl.class, false), sSLSocketFactory);
        } catch (Throwable unused) {
            sSLParametersImpl = null;
        }
        if (sSLParametersImpl == null) {
            C6638h.f17124y.mo10122a("sSLParametersImpl == null");
            sSLParametersImpl = m9277c();
        }
        parameters = sSLParametersImpl;
        return sSLParametersImpl;
    }

    /* renamed from: a */
    private static SSLParametersImpl m9279a(SSLParametersImpl sSLParametersImpl) {
        try {
            Method declaredMethod = SSLParametersImpl.class.getDeclaredMethod("clone", new Class[0]);
            declaredMethod.setAccessible(true);
            return (SSLParametersImpl) declaredMethod.invoke(sSLParametersImpl, new Object[0]);
        } catch (Throwable unused) {
            return parameters;
        }
    }

    /* renamed from: c */
    private static SSLParametersImpl m9277c() {
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
