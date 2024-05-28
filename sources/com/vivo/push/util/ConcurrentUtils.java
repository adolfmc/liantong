package com.vivo.push.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.vivo.push.util.g */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class ConcurrentUtils {

    /* renamed from: a */
    private static final int f21209a = Runtime.getRuntime().availableProcessors();

    /* renamed from: b */
    private static final int f21210b = Math.max(2, Math.min(f21209a - 1, 4));

    /* renamed from: c */
    private static final int f21211c = (f21209a * 2) + 1;

    /* renamed from: d */
    private static ExecutorService f21212d = new ThreadPoolExecutor(f21210b, f21211c, 30, TimeUnit.SECONDS, new LinkedBlockingQueue(128), new ThreadFactoryC10989h("COMMON_THREAD"), new ThreadPoolExecutor.DiscardPolicy());

    /* renamed from: a */
    public static ExecutorService m5404a() {
        return f21212d;
    }
}
