package com.baidu.platform.comapi;

import android.app.Application;
import android.content.Context;
import com.baidu.p166vi.VIContext;
import com.baidu.platform.comapi.p149b.C2984c;
import com.baidu.platform.comapi.p149b.InterfaceC2983b;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2981b {

    /* renamed from: d */
    private static Context f7525d;

    /* renamed from: e */
    private static C2975a f7526e;

    /* renamed from: g */
    private static boolean f7528g;

    /* renamed from: h */
    private static boolean f7529h;

    /* renamed from: i */
    private static boolean f7530i;

    /* renamed from: j */
    private static boolean f7531j;

    /* renamed from: a */
    private static final AtomicBoolean f7522a = new AtomicBoolean(false);

    /* renamed from: b */
    private static final AtomicBoolean f7523b = new AtomicBoolean(false);

    /* renamed from: c */
    private static final AtomicBoolean f7524c = new AtomicBoolean(false);

    /* renamed from: f */
    private static final CountDownLatch f7527f = new CountDownLatch(1);

    /* renamed from: a */
    public static void m18072a(Application application, boolean z, boolean z2, boolean z3, boolean z4) {
        if (application == null) {
            throw new RuntimeException("BDMapSDKException: Application Context is null");
        }
        f7528g = z;
        f7529h = z2;
        f7530i = z3;
        f7531j = z4;
        if (f7525d == null) {
            f7525d = application;
        }
        VIContext.init(application);
    }

    /* renamed from: a */
    public static void m18071a(InterfaceC2983b interfaceC2983b) {
        while (true) {
            boolean z = f7524c.get();
            if (z) {
                return;
            }
            if (f7524c.compareAndSet(z, true)) {
                if (interfaceC2983b != null) {
                    try {
                        C2984c.f7532a.m18058a(interfaceC2983b);
                    } finally {
                        f7527f.countDown();
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static boolean m18073a() {
        return f7524c.get();
    }

    /* renamed from: b */
    public static void m18070b() {
        while (true) {
            boolean z = f7522a.get();
            if (z) {
                return;
            }
            if (f7522a.compareAndSet(z, true)) {
                f7526e = new C2975a();
                if (!f7526e.m18081a(f7525d)) {
                    throw new RuntimeException("BDMapSDKException: engine init failed");
                }
            }
        }
    }

    /* renamed from: c */
    public static void m18069c() {
        f7526e.m18080b();
        f7522a.set(false);
    }

    /* renamed from: d */
    public static Context m18068d() {
        return f7525d;
    }

    /* renamed from: e */
    public static boolean m18067e() {
        return f7529h;
    }

    /* renamed from: f */
    public static boolean m18066f() {
        return f7530i;
    }

    /* renamed from: g */
    public static boolean m18065g() {
        return f7531j;
    }
}
