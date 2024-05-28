package com.alipay.android.phone.mrpc.core;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.android.phone.mrpc.core.l */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1944l implements InterfaceC1928ab {

    /* renamed from: b */
    private static C1944l f3419b;

    /* renamed from: i */
    private static final ThreadFactory f3420i = new ThreadFactoryC1946n();

    /* renamed from: a */
    Context f3421a;

    /* renamed from: e */
    private long f3424e;

    /* renamed from: f */
    private long f3425f;

    /* renamed from: g */
    private long f3426g;

    /* renamed from: h */
    private int f3427h;

    /* renamed from: d */
    private C1931b f3423d = C1931b.m21129a("android");

    /* renamed from: c */
    private ThreadPoolExecutor f3422c = new ThreadPoolExecutor(10, 11, 3, TimeUnit.SECONDS, new ArrayBlockingQueue(20), f3420i, new ThreadPoolExecutor.CallerRunsPolicy());

    private C1944l(Context context) {
        this.f3421a = context;
        try {
            this.f3422c.allowCoreThreadTimeOut(true);
        } catch (Exception unused) {
        }
        CookieSyncManager.createInstance(this.f3421a);
        CookieManager.getInstance().setAcceptCookie(true);
    }

    /* renamed from: a */
    public static final C1944l m21106a(Context context) {
        C1944l c1944l = f3419b;
        return c1944l != null ? c1944l : m21103b(context);
    }

    /* renamed from: b */
    private static final synchronized C1944l m21103b(Context context) {
        synchronized (C1944l.class) {
            if (f3419b != null) {
                return f3419b;
            }
            C1944l c1944l = new C1944l(context);
            f3419b = c1944l;
            return c1944l;
        }
    }

    /* renamed from: a */
    public final C1931b m21108a() {
        return this.f3423d;
    }

    @Override // com.alipay.android.phone.mrpc.core.InterfaceC1928ab
    /* renamed from: a */
    public final Future<C1953u> mo21105a(AbstractC1952t abstractC1952t) {
        if (C1951s.m21070a(this.f3421a)) {
            String str = "HttpManager" + hashCode() + ": Active Task = %d, Completed Task = %d, All Task = %d,Avarage Speed = %d KB/S, Connetct Time = %d ms, All data size = %d bytes, All enqueueConnect time = %d ms, All socket time = %d ms, All request times = %d times";
            Object[] objArr = new Object[9];
            objArr[0] = Integer.valueOf(this.f3422c.getActiveCount());
            objArr[1] = Long.valueOf(this.f3422c.getCompletedTaskCount());
            objArr[2] = Long.valueOf(this.f3422c.getTaskCount());
            long j = this.f3426g;
            objArr[3] = Long.valueOf(j == 0 ? 0L : ((this.f3424e * 1000) / j) >> 10);
            int i = this.f3427h;
            objArr[4] = Long.valueOf(i != 0 ? this.f3425f / i : 0L);
            objArr[5] = Long.valueOf(this.f3424e);
            objArr[6] = Long.valueOf(this.f3425f);
            objArr[7] = Long.valueOf(this.f3426g);
            objArr[8] = Integer.valueOf(this.f3427h);
            String.format(str, objArr);
        }
        CallableC1949q callableC1949q = new CallableC1949q(this, (C1947o) abstractC1952t);
        C1945m c1945m = new C1945m(this, callableC1949q, callableC1949q);
        this.f3422c.execute(c1945m);
        return c1945m;
    }

    /* renamed from: a */
    public final void m21107a(long j) {
        this.f3424e += j;
    }

    /* renamed from: b */
    public final void m21104b(long j) {
        this.f3425f += j;
        this.f3427h++;
    }

    /* renamed from: c */
    public final void m21102c(long j) {
        this.f3426g += j;
    }
}
