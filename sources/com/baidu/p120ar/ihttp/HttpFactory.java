package com.baidu.p120ar.ihttp;

import com.baidu.p120ar.utils.ReflectionUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.ihttp.HttpFactory */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class HttpFactory {
    private static final String FACTORY_IMPL_CLASS = "com.baidu.ar.http.HttpRequestFactory";
    private static volatile int sAvailTag;
    private static volatile IHttpRequestFactory sFactory;
    private static Object sLock = new Object();

    public static boolean isAvailable() {
        if (sAvailTag == 1) {
            return true;
        }
        boolean z = false;
        if (sAvailTag == -1) {
            return false;
        }
        try {
            Class.forName("com.baidu.ar.http.HttpRequestFactory");
            z = true;
        } catch (ClassNotFoundException unused) {
        }
        synchronized (sLock) {
            sAvailTag = z ? 1 : -1;
        }
        return z;
    }

    public static IHttpRequest newRequest() {
        IHttpRequestFactory factory = getFactory();
        if (factory != null) {
            return factory.newRequest();
        }
        return null;
    }

    private static IHttpRequestFactory getFactory() {
        if (sFactory != null) {
            return sFactory;
        }
        if (isAvailable()) {
            synchronized (sLock) {
                if (sFactory == null) {
                    sFactory = (IHttpRequestFactory) ReflectionUtils.getNewInstance("com.baidu.ar.http.HttpRequestFactory");
                }
            }
        }
        return sFactory;
    }

    public static void release() {
        if (sFactory != null) {
            sFactory.release();
            sFactory = null;
        }
    }
}
