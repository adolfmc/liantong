package com.megvii.livenesslib.util;

import android.app.Activity;
import android.os.Build;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FoldUtils {
    public static boolean isFold(Activity activity) {
        try {
            if (("HUAWEI".equalsIgnoreCase(Build.MANUFACTURER) || "Xiaomi".equalsIgnoreCase(Build.MANUFACTURER) || "HONOR".equalsIgnoreCase(Build.MANUFACTURER) || "samsung".equalsIgnoreCase(Build.MANUFACTURER) || "OPPO".equalsIgnoreCase(Build.MANUFACTURER)) && (activity.getResources().getConfiguration().screenLayout & 15) == 3 && Build.VERSION.SDK_INT >= 24) {
                return !activity.isInMultiWindowMode();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
