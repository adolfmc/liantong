package com.baidu.p120ar.arplay.component;

import android.content.Context;
import android.os.Vibrator;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.component.VibrateManager */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VibrateManager {
    private static VibrateManager mInstance;
    private Vibrator mVibrator;
    long[] pattern = {800, 60, 400, 60};

    public static synchronized VibrateManager getInstance(Context context) {
        VibrateManager vibrateManager;
        synchronized (VibrateManager.class) {
            if (mInstance == null) {
                mInstance = new VibrateManager(context);
            }
            vibrateManager = mInstance;
        }
        return vibrateManager;
    }

    private VibrateManager(Context context) {
        this.mVibrator = (Vibrator) context.getSystemService("vibrator");
    }

    public void playVibrate(long[] jArr) {
        this.mVibrator.vibrate(jArr, -1);
    }

    public void playVibrate(long j) {
        this.mVibrator.vibrate(j);
    }

    public void stop() {
        this.mVibrator.cancel();
    }
}
