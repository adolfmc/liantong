package com.heytap.mcssdk.utils;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.heytap.mcssdk.utils.f */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4750f {

    /* renamed from: a */
    private static final ExecutorService f10722a = Executors.newSingleThreadExecutor();

    /* renamed from: b */
    private static Handler f10723b = new Handler(Looper.getMainLooper());

    /* renamed from: a */
    public static void m15466a(Runnable runnable) {
        f10722a.execute(runnable);
    }

    /* renamed from: b */
    public static void m15465b(Runnable runnable) {
        f10723b.post(runnable);
    }
}
