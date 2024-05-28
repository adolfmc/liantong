package com.sinovatech.unicom.common;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import com.bytedance.pangle.activity.GenerateProxyActivity;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.unicompay.UnicomPayConfig;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CustomDensityHandler {
    private static final String TAG = "CustomDensityHandler";
    public static float sNonCompatDensity;
    public static float sNonCompatScaleDensity;
    public static int sNonCompattargetDensityDpi;

    public static void setCustomDensity(@NonNull Activity activity, @NonNull Application application) {
        try {
            if ((activity instanceof GenerateProxyActivity) || activity.getClass().getName().startsWith(UnicomPayConfig.paySdkPackage)) {
                return;
            }
            DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
            int i = displayMetrics.widthPixels;
            int i2 = activity.getResources().getConfiguration().orientation;
            if (i2 == 2) {
                i = displayMetrics.heightPixels;
            } else if (i2 == 1) {
                i = displayMetrics.widthPixels;
            }
            if (sNonCompatDensity == 0.0f) {
                sNonCompatDensity = displayMetrics.density;
                sNonCompatScaleDensity = displayMetrics.scaledDensity;
                sNonCompattargetDensityDpi = displayMetrics.densityDpi;
                application.registerComponentCallbacks(new ComponentCallbacks() { // from class: com.sinovatech.unicom.common.CustomDensityHandler.1
                    @Override // android.content.ComponentCallbacks
                    public void onLowMemory() {
                    }

                    @Override // android.content.ComponentCallbacks
                    public void onConfigurationChanged(Configuration configuration) {
                        if (configuration == null || configuration.fontScale <= 0.0f) {
                            return;
                        }
                        CustomDensityHandler.sNonCompatScaleDensity = App.getInstance().getResources().getDisplayMetrics().scaledDensity;
                    }
                });
            }
            float f = i / 360;
            float f2 = (sNonCompatScaleDensity / sNonCompatDensity) * f;
            int i3 = (int) (160.0f * f);
            displayMetrics.density = f;
            displayMetrics.scaledDensity = f2;
            displayMetrics.densityDpi = i3;
            DisplayMetrics displayMetrics2 = activity.getResources().getDisplayMetrics();
            displayMetrics2.density = f;
            displayMetrics2.scaledDensity = f2;
            displayMetrics2.densityDpi = i3;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
