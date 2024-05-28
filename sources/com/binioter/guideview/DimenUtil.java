package com.binioter.guideview;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class DimenUtil {
    public static int sp2px(Context context, float f) {
        return (int) ((f * context.getApplicationContext().getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int px2sp(Context context, float f) {
        return (int) ((f / context.getApplicationContext().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int dp2px(Context context, float f) {
        return (int) ((f * context.getApplicationContext().getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int px2dp(Context context, float f) {
        return (int) ((f / context.getApplicationContext().getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }
}
