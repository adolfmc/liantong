package com.networkbench.agent.impl.socket;

import android.os.Build;
import com.networkbench.agent.impl.socket.p278a.C6601a;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.KeyManagementException;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContextSpi;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.socket.l */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6615l extends SSLContextSpi {

    /* renamed from: a */
    private static Method[] f17022a = new Method[7];

    /* renamed from: b */
    private static boolean f17023b = false;

    /* renamed from: d */
    private static final int f17024d = 0;

    /* renamed from: e */
    private static final int f17025e = 1;

    /* renamed from: f */
    private static final int f17026f = 2;

    /* renamed from: g */
    private static final int f17027g = 3;

    /* renamed from: h */
    private static final int f17028h = 4;

    /* renamed from: i */
    private static final int f17029i = 5;

    /* renamed from: j */
    private static final int f17030j = 6;

    /* renamed from: k */
    private static final int f17031k = 7;

    /* renamed from: c */
    private SSLContextSpi f17032c;

    static {
        Method[] methodArr;
        f17023b = false;
        try {
            f17022a[0] = SSLContextSpi.class.getDeclaredMethod("engineCreateSSLEngine", new Class[0]);
            f17022a[1] = SSLContextSpi.class.getDeclaredMethod("engineCreateSSLEngine", String.class, Integer.TYPE);
            f17022a[2] = SSLContextSpi.class.getDeclaredMethod("engineGetClientSessionContext", new Class[0]);
            f17022a[3] = SSLContextSpi.class.getDeclaredMethod("engineGetServerSessionContext", new Class[0]);
            f17022a[4] = SSLContextSpi.class.getDeclaredMethod("engineGetServerSocketFactory", new Class[0]);
            f17022a[5] = SSLContextSpi.class.getDeclaredMethod("engineGetSocketFactory", new Class[0]);
            f17022a[6] = SSLContextSpi.class.getDeclaredMethod("engineInit", KeyManager[].class, TrustManager[].class, SecureRandom.class);
            for (Method method : f17022a) {
                if (method != null) {
                    method.setAccessible(true);
                }
            }
            C6615l c6615l = new C6615l();
            c6615l.engineCreateSSLEngine();
            c6615l.engineCreateSSLEngine(null, 0);
            c6615l.engineGetClientSessionContext();
            c6615l.engineGetServerSessionContext();
            c6615l.engineGetServerSocketFactory();
            c6615l.engineGetSocketFactory();
            c6615l.engineInit(null, null, null);
            f17023b = true;
        } catch (Throwable unused) {
            f17023b = false;
        }
    }

    private C6615l(SSLContextSpi sSLContextSpi) {
        this.f17032c = sSLContextSpi;
    }

    /* renamed from: a */
    public static C6615l m9236a(SSLContextSpi sSLContextSpi) {
        if (f17023b) {
            return new C6615l(sSLContextSpi);
        }
        return null;
    }

    private C6615l() {
    }

    /* renamed from: a */
    public static boolean m9238a() {
        return f17023b;
    }

    /* renamed from: a */
    private Object m9237a(int i, Object... objArr) throws Exception {
        SSLContextSpi sSLContextSpi = this.f17032c;
        if (sSLContextSpi == null) {
            return null;
        }
        try {
            return f17022a[i].invoke(sSLContextSpi, objArr);
        } catch (ClassCastException e) {
            throw new C6601a(e);
        } catch (IllegalAccessException e2) {
            throw new C6601a(e2);
        } catch (IllegalArgumentException e3) {
            throw new C6601a(e3);
        } catch (InvocationTargetException e4) {
            Throwable targetException = e4.getTargetException();
            if (targetException == null) {
                throw new C6601a(e4);
            }
            if (targetException instanceof Exception) {
                throw ((Exception) targetException);
            }
            if (targetException instanceof Error) {
                throw ((Error) targetException);
            }
            throw new C6601a(e4);
        }
    }

    /* renamed from: b */
    private Object m9234b(int i, Object... objArr) {
        try {
            return m9237a(i, objArr);
        } catch (RuntimeException e) {
            throw e;
        } catch (Throwable th) {
            throw new C6601a(th);
        }
    }

    /* renamed from: a */
    private Object m9235a(Object... objArr) throws KeyManagementException {
        try {
            return m9237a(6, objArr);
        } catch (RuntimeException e) {
            throw e;
        } catch (KeyManagementException e2) {
            throw e2;
        } catch (Throwable th) {
            throw new C6601a(th);
        }
    }

    @Override // javax.net.ssl.SSLContextSpi
    protected final SSLEngine engineCreateSSLEngine() {
        return (SSLEngine) m9234b(0, new Object[0]);
    }

    @Override // javax.net.ssl.SSLContextSpi
    protected final SSLEngine engineCreateSSLEngine(String str, int i) {
        return (SSLEngine) m9234b(1, str, Integer.valueOf(i));
    }

    @Override // javax.net.ssl.SSLContextSpi
    protected final SSLSessionContext engineGetClientSessionContext() {
        return (SSLSessionContext) m9234b(2, new Object[0]);
    }

    @Override // javax.net.ssl.SSLContextSpi
    protected final SSLSessionContext engineGetServerSessionContext() {
        return (SSLSessionContext) m9234b(3, new Object[0]);
    }

    @Override // javax.net.ssl.SSLContextSpi
    protected final SSLServerSocketFactory engineGetServerSocketFactory() {
        return (SSLServerSocketFactory) m9234b(4, new Object[0]);
    }

    @Override // javax.net.ssl.SSLContextSpi
    protected final SSLSocketFactory engineGetSocketFactory() {
        SSLSocketFactory sSLSocketFactory = (SSLSocketFactory) m9234b(5, new Object[0]);
        if (sSLSocketFactory == null) {
            return sSLSocketFactory;
        }
        try {
            if (Build.VERSION.SDK_INT >= 19) {
                return new CustomSSLSocketFactory(sSLSocketFactory);
            }
            return Build.VERSION.SDK_INT >= 14 ? new CustomSSLSocketFactoryOld(sSLSocketFactory) : sSLSocketFactory;
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable unused) {
            return sSLSocketFactory;
        }
    }

    @Override // javax.net.ssl.SSLContextSpi
    protected final void engineInit(KeyManager[] keyManagerArr, TrustManager[] trustManagerArr, SecureRandom secureRandom) throws KeyManagementException {
        m9235a(keyManagerArr, trustManagerArr, secureRandom);
    }

    public final boolean equals(Object obj) {
        return this.f17032c.equals(obj);
    }

    public final int hashCode() {
        return this.f17032c.hashCode();
    }

    public final String toString() {
        return this.f17032c.toString();
    }
}
