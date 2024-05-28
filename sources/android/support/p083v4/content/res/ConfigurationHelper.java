package android.support.p083v4.content.res;

import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: android.support.v4.content.res.ConfigurationHelper */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class ConfigurationHelper {
    private ConfigurationHelper() {
    }

    public static int getDensityDpi(@NonNull Resources resources) {
        if (Build.VERSION.SDK_INT >= 17) {
            return resources.getConfiguration().densityDpi;
        }
        return resources.getDisplayMetrics().densityDpi;
    }
}
