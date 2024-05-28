package com.baidu.platform.comapi.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.util.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C3094f {

    /* renamed from: a */
    private static final int f8067a = Runtime.getRuntime().availableProcessors();

    /* renamed from: b */
    private static final int f8068b = Math.min((f8067a * 2) + 1, 8);

    /* renamed from: a */
    public static ExecutorService m17683a(String str) {
        int i = f8068b;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(i, i, 60L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadFactoryC3091c(str));
        try {
            threadPoolExecutor.setKeepAliveTime(60L, TimeUnit.MILLISECONDS);
            threadPoolExecutor.allowCoreThreadTimeOut(true);
        } catch (Exception unused) {
        }
        return threadPoolExecutor;
    }
}
