package com.baidu.p120ar.utils;

import android.os.Handler;
import android.os.Looper;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.utils.UiThreadUtils */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class UiThreadUtils {
    private static Handler sMainHandler = new Handler(Looper.getMainLooper());

    public static boolean isOnUiThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public static void runOnUiThread(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (isOnUiThread()) {
            runnable.run();
        } else {
            sMainHandler.post(runnable);
        }
    }

    public static void removeCallbacksAndMessages() {
        sMainHandler.removeCallbacksAndMessages(null);
    }

    public static void removeCallbacks(Runnable runnable) {
        sMainHandler.removeCallbacks(runnable);
    }

    public static void postDelayed(Runnable runnable, long j) {
        sMainHandler.postDelayed(runnable, j);
    }
}
