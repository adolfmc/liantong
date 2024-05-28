package com.networkbench.agent.impl.socket;

import android.os.Build;
import com.networkbench.agent.impl.util.C6631a;
import com.networkbench.agent.impl.util.C6645n;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLContextSpi;
import org.apache.harmony.security.fortress.Services;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.socket.k */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6614k extends Provider.Service {

    /* renamed from: a */
    public static final String f17018a = "SSLContext";

    /* renamed from: b */
    public static final String f17019b = "TLS";

    /* renamed from: c */
    public static final String[] f17020c = {"Default", "SSL", "TLSv1.1", "TLSv1.2", "SSLv3", "TLSv1", "TLS"};

    /* renamed from: d */
    private Provider.Service f17021d;

    private C6614k(Provider.Service service) {
        super(service.getProvider(), service.getType(), service.getAlgorithm(), service.getClassName(), null, null);
        this.f17021d = service;
    }

    /* renamed from: a */
    public static C6614k m9246a(Provider.Service service) {
        C6614k c6614k = new C6614k(service);
        try {
            C6645n.m8881a(Provider.Service.class, service, c6614k);
            return c6614k;
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: f */
    private static Provider m9239f() {
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            if (sSLContext != null) {
                return sSLContext.getProvider();
            }
            return null;
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m9248a() {
        Provider m9239f;
        C6614k m9246a;
        int i = 0;
        if (!C6615l.m9238a() || (m9239f = m9239f()) == null) {
            return false;
        }
        boolean z = false;
        while (true) {
            String[] strArr = f17020c;
            if (i >= strArr.length) {
                return z;
            }
            Provider.Service service = m9239f.getService("SSLContext", strArr[i]);
            if (service != null && !(service instanceof C6614k) && (m9246a = m9246a(service)) != null) {
                z |= m9246a.m9242c();
            }
            i++;
        }
    }

    /* renamed from: b */
    public static boolean m9244b() {
        Provider m9239f = m9239f();
        int i = 0;
        if (m9239f == null) {
            return false;
        }
        boolean z = true;
        while (true) {
            String[] strArr = f17020c;
            if (i >= strArr.length) {
                return z;
            }
            Provider.Service service = m9239f.getService("SSLContext", strArr[i]);
            if (service != null && (service instanceof C6614k)) {
                z &= ((C6614k) service).m9241d();
            }
            i++;
        }
    }

    /* renamed from: c */
    public boolean m9242c() {
        Provider provider = getProvider();
        if (provider == null) {
            return false;
        }
        try {
            if (Build.VERSION.SDK_INT <= 23) {
                synchronized (Services.class) {
                    m9245a(provider);
                }
                return true;
            }
            m9245a(provider);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: a */
    private void m9245a(Provider provider) throws Exception {
        Method declaredMethod = Provider.class.getDeclaredMethod("putService", Provider.Service.class);
        declaredMethod.setAccessible(true);
        declaredMethod.invoke(provider, this);
    }

    /* renamed from: d */
    public boolean m9241d() {
        Provider provider = getProvider();
        if (provider == null || this.f17021d == null) {
            C6631a.m9136a(provider);
            C6631a.m9136a(this.f17021d);
            return false;
        }
        try {
            if (Build.VERSION.SDK_INT <= 23) {
                synchronized (Services.class) {
                    m9243b(provider);
                }
                return true;
            }
            m9243b(provider);
            return true;
        } catch (Exception e) {
            C6631a.m9135a((Throwable) e);
            return false;
        }
    }

    /* renamed from: b */
    private void m9243b(Provider provider) throws Exception {
        provider.put(getType() + "" + getAlgorithm(), this.f17021d.getClassName());
        Method declaredMethod = Provider.class.getDeclaredMethod("removeService", Provider.Service.class);
        declaredMethod.setAccessible(true);
        declaredMethod.invoke(provider, this);
    }

    @Override // java.security.Provider.Service
    public Object newInstance(Object obj) throws NoSuchAlgorithmException {
        Object newInstance = super.newInstance(obj);
        try {
            return m9247a(newInstance);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            th.printStackTrace();
            return newInstance;
        }
    }

    /* renamed from: a */
    private Object m9247a(Object obj) {
        C6615l m9236a;
        boolean z = obj instanceof SSLContextSpi;
        C6631a.m9134a(z);
        if (!z || (m9236a = C6615l.m9236a((SSLContextSpi) obj)) == null) {
            return obj;
        }
        C6631a.m9136a(m9236a);
        return m9236a;
    }

    /* renamed from: e */
    public Provider.Service m9240e() {
        return this.f17021d;
    }
}
