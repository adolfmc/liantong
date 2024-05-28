package com.blankj.utilcode.util;

import android.os.Vibrator;
import android.support.annotation.RequiresPermission;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class VibrateUtils {
    private static Vibrator vibrator;

    private VibrateUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    @RequiresPermission("android.permission.VIBRATE")
    public static void vibrate(long j) {
        Vibrator vibrator2 = getVibrator();
        if (vibrator2 == null) {
            return;
        }
        vibrator2.vibrate(j);
    }

    @RequiresPermission("android.permission.VIBRATE")
    public static void vibrate(long[] jArr, int i) {
        Vibrator vibrator2 = getVibrator();
        if (vibrator2 == null) {
            return;
        }
        vibrator2.vibrate(jArr, i);
    }

    @RequiresPermission("android.permission.VIBRATE")
    public static void cancel() {
        Vibrator vibrator2 = getVibrator();
        if (vibrator2 == null) {
            return;
        }
        vibrator2.cancel();
    }

    private static Vibrator getVibrator() {
        if (vibrator == null) {
            vibrator = (Vibrator) Utils.getApp().getSystemService("vibrator");
        }
        return vibrator;
    }
}
