package com.baidu.platform.comapi.util;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.util.i */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C3097i {

    /* renamed from: a */
    private static final ExecutorService f8069a = Executors.newSingleThreadExecutor(new ThreadFactoryC3091c("Single"));

    /* renamed from: b */
    private static final ExecutorService f8070b = Executors.newFixedThreadPool(4, new ThreadFactoryC3091c("FixedPool"));

    /* renamed from: c */
    private static final Handler f8071c = new Handler(Looper.getMainLooper());

    /* renamed from: d */
    private static final ExecutorService f8072d = C3094f.m17683a("DefaultPool");

    /* renamed from: a */
    public static ExecutorService m17680a() {
        return f8069a;
    }

    /* renamed from: a */
    public static void m17679a(Runnable runnable, long j) {
        f8071c.postDelayed(runnable, j);
    }

    /* renamed from: b */
    public static ExecutorService m17678b() {
        return f8072d;
    }
}
