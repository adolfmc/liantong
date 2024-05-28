package com.baidu.p120ar.utils;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.utils.ScreenUtils */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ScreenUtils {
    public static float getScreenDensity(Context context) {
        if (context != null) {
            return context.getResources().getDisplayMetrics().density;
        }
        return 0.0f;
    }

    public static boolean isScreenOrientationLandscape(Context context) {
        return context != null && context.getResources().getConfiguration().orientation == 2;
    }
}
