package com.baidu.location.p140e;

import java.net.URL;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.e.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class AbstractC2729f {

    /* renamed from: g */
    public String f5724g = null;

    /* renamed from: h */
    public int f5725h = 1;

    /* renamed from: i */
    public String f5726i = null;

    /* renamed from: j */
    public Map<String, Object> f5727j = null;

    /* renamed from: k */
    public String f5728k = null;

    /* renamed from: l */
    public byte[] f5729l = null;

    /* renamed from: m */
    public byte[] f5730m = null;

    /* renamed from: n */
    public String f5731n = null;

    /* renamed from: f */
    public static int f5722f = C2720a.f5674f;

    /* renamed from: a */
    private static String f5720a = "10.0.0.172";

    /* renamed from: b */
    private static int f5721b = 80;

    /* renamed from: o */
    protected static int f5723o = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.e.f$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class C2730a implements HostnameVerifier {

        /* renamed from: a */
        private URL f5732a;

        public C2730a(URL url) {
            this.f5732a = url;
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return this.f5732a.getHost().equals(str);
        }
    }

    /* renamed from: a */
    public abstract void mo19077a();

    /* renamed from: a */
    public void m19076a(ExecutorService executorService, String str) {
        try {
            executorService.execute(new RunnableC2733i(this, str));
        } catch (Throwable unused) {
            mo19074a(false);
        }
    }

    /* renamed from: a */
    public void m19075a(ExecutorService executorService, boolean z, String str) {
        try {
            executorService.execute(new RunnableC2731g(this, str, z));
        } catch (Throwable unused) {
            mo19074a(false);
        }
    }

    /* renamed from: a */
    public abstract void mo19074a(boolean z);

    /* renamed from: b */
    public void m19073b(String str) {
        try {
            new C2732h(this, str).start();
        } catch (Throwable unused) {
            mo19074a(false);
        }
    }
}
