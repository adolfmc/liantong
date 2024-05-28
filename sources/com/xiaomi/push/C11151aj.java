package com.xiaomi.push;

import android.content.Context;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.aj */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11151aj implements InterfaceC11150ai, InvocationHandler {

    /* renamed from: a */
    private static final String[][] f21486a = {new String[]{"com.bun.supplier.IIdentifierListener", "com.bun.supplier.IdSupplier"}, new String[]{"com.bun.miitmdid.core.IIdentifierListener", "com.bun.miitmdid.supplier.IdSupplier"}};

    /* renamed from: a */
    private Context f21489a;

    /* renamed from: a */
    private Class f21491a = null;

    /* renamed from: b */
    private Class f21494b = null;

    /* renamed from: a */
    private Method f21493a = null;

    /* renamed from: b */
    private Method f21495b = null;

    /* renamed from: c */
    private Method f21496c = null;

    /* renamed from: d */
    private Method f21497d = null;

    /* renamed from: e */
    private Method f21498e = null;

    /* renamed from: f */
    private Method f21499f = null;

    /* renamed from: g */
    private Method f21500g = null;

    /* renamed from: a */
    private final Object f21492a = new Object();

    /* renamed from: a */
    private volatile int f21487a = 0;

    /* renamed from: a */
    private volatile long f21488a = 0;

    /* renamed from: a */
    private volatile C11153a f21490a = null;

    public C11151aj(Context context) {
        this.f21489a = context.getApplicationContext();
        m4897a(context);
        m4891b(context);
    }

    @Override // com.xiaomi.push.InterfaceC11150ai
    /* renamed from: a */
    public boolean mo4862a() {
        m4893a("isSupported");
        return this.f21490a != null && Boolean.TRUE.equals(this.f21490a.f21502a);
    }

    @Override // com.xiaomi.push.InterfaceC11150ai
    /* renamed from: a */
    public String mo4863a() {
        m4893a("getOAID");
        if (this.f21490a == null) {
            return null;
        }
        return this.f21490a.f21504b;
    }

    /* renamed from: a */
    private void m4897a(Context context) {
        Class<?> m4896a = m4896a(context, "com.bun.miitmdid.core.MdidSdk");
        Class<?> cls = null;
        Class<?> cls2 = null;
        int i = 0;
        while (true) {
            String[][] strArr = f21486a;
            if (i >= strArr.length) {
                break;
            }
            String[] strArr2 = strArr[i];
            Class<?> m4896a2 = m4896a(context, strArr2[0]);
            Class<?> m4896a3 = m4896a(context, strArr2[1]);
            if (m4896a2 != null && m4896a3 != null) {
                m4890b("found class in index " + i);
                cls2 = m4896a3;
                cls = m4896a2;
                break;
            }
            i++;
            cls2 = m4896a3;
            cls = m4896a2;
        }
        this.f21491a = m4896a;
        this.f21493a = m4895a(m4896a, "InitSdk", Context.class, cls);
        this.f21494b = cls;
        this.f21496c = m4895a(cls2, "getOAID", new Class[0]);
        this.f21499f = m4895a(cls2, "isSupported", new Class[0]);
        this.f21500g = m4895a(cls2, "shutDown", new Class[0]);
    }

    /* renamed from: b */
    private void m4891b(Context context) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = -elapsedRealtime;
        Class cls = this.f21494b;
        if (cls != null) {
            try {
                ClassLoader classLoader = cls.getClassLoader();
                if (classLoader == null) {
                    classLoader = context.getClassLoader();
                }
                m4892a(this.f21493a, this.f21491a.newInstance(), context, Proxy.newProxyInstance(classLoader, new Class[]{this.f21494b}, this));
            } catch (Throwable th) {
                m4890b("call init sdk error:" + th);
            }
            this.f21488a = elapsedRealtime;
        }
        elapsedRealtime = j;
        this.f21488a = elapsedRealtime;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        this.f21488a = SystemClock.elapsedRealtime();
        if (objArr != null) {
            C11153a c11153a = new C11153a();
            int length = objArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Object obj2 = objArr[i];
                if (obj2 != null && !m4894a(obj2)) {
                    c11153a.f21504b = (String) m4892a(this.f21496c, obj2, new Object[0]);
                    c11153a.f21502a = (Boolean) m4892a(this.f21499f, obj2, new Object[0]);
                    m4892a(this.f21500g, obj2, new Object[0]);
                    if (c11153a.m4889a()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("has get succ, check duplicate:");
                        sb.append(this.f21490a != null);
                        m4890b(sb.toString());
                        synchronized (C11151aj.class) {
                            if (this.f21490a == null) {
                                this.f21490a = c11153a;
                            }
                        }
                    }
                }
                i++;
            }
        }
        m4898a();
        return null;
    }

    /* renamed from: a */
    private void m4893a(String str) {
        if (this.f21490a != null) {
            return;
        }
        long j = this.f21488a;
        long elapsedRealtime = SystemClock.elapsedRealtime() - Math.abs(j);
        int i = this.f21487a;
        if (elapsedRealtime > 3000 && i < 3) {
            synchronized (this.f21492a) {
                if (this.f21488a == j && this.f21487a == i) {
                    m4890b("retry, current count is " + i);
                    this.f21487a = this.f21487a + 1;
                    m4891b(this.f21489a);
                    j = this.f21488a;
                    elapsedRealtime = SystemClock.elapsedRealtime() - Math.abs(j);
                }
            }
        }
        if (this.f21490a != null || j < 0 || elapsedRealtime > 3000 || Looper.myLooper() == Looper.getMainLooper()) {
            return;
        }
        synchronized (this.f21492a) {
            if (this.f21490a == null) {
                try {
                    m4890b(str + " wait...");
                    this.f21492a.wait(3000L);
                } catch (Exception unused) {
                }
            }
        }
    }

    /* renamed from: a */
    private void m4898a() {
        synchronized (this.f21492a) {
            try {
                this.f21492a.notifyAll();
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.aj$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11153a {

        /* renamed from: a */
        Boolean f21502a;

        /* renamed from: a */
        String f21503a;

        /* renamed from: b */
        String f21504b;

        /* renamed from: c */
        String f21505c;

        /* renamed from: d */
        String f21506d;

        private C11153a() {
            this.f21502a = null;
            this.f21503a = null;
            this.f21504b = null;
            this.f21505c = null;
            this.f21506d = null;
        }

        /* renamed from: a */
        boolean m4889a() {
            if (!TextUtils.isEmpty(this.f21503a) || !TextUtils.isEmpty(this.f21504b) || !TextUtils.isEmpty(this.f21505c) || !TextUtils.isEmpty(this.f21506d)) {
                this.f21502a = true;
            }
            return this.f21502a != null;
        }
    }

    /* renamed from: a */
    private static boolean m4894a(Object obj) {
        return (obj instanceof Boolean) || (obj instanceof Character) || (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Float) || (obj instanceof Double);
    }

    /* renamed from: a */
    private static Class<?> m4896a(Context context, String str) {
        try {
            return C11479r.m2929a(context, str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static Method m4895a(Class<?> cls, String str, Class<?>... clsArr) {
        if (cls != null) {
            try {
                return cls.getMethod(str, clsArr);
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    /* renamed from: a */
    private static <T> T m4892a(Method method, Object obj, Object... objArr) {
        if (method != null) {
            try {
                T t = (T) method.invoke(obj, objArr);
                if (t != null) {
                    return t;
                }
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    /* renamed from: b */
    private static void m4890b(String str) {
        AbstractC11049b.m5282a("mdid:" + str);
    }
}
