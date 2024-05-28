package com.gmrz.asm.p198fp.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.gmrz.asm.fp.utils.NavigationBarUtil */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class NavigationBarUtil {
    public static int getNavigationBarHeightIfRoom(Context context) {
        if (navigationGestureEnabled(context)) {
            return 0;
        }
        return getCurrentNavigationBarHeight((Activity) context);
    }

    public static boolean navigationGestureEnabled(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), getDeviceInfo(), 0) != 0;
    }

    public static String getDeviceInfo() {
        String str = Build.BRAND;
        return (TextUtils.isEmpty(str) || str.equalsIgnoreCase("HUAWEI")) ? "navigationbar_is_min" : str.equalsIgnoreCase("XIAOMI") ? "force_fsg_nav_bar" : (str.equalsIgnoreCase("VIVO") || str.equalsIgnoreCase("OPPO")) ? "navigation_gesture_on" : "navigationbar_is_min";
    }

    public static int getCurrentNavigationBarHeight(Activity activity) {
        if (isNavigationBarShown(activity)) {
            return getNavigationBarHeight(activity);
        }
        return 0;
    }

    public static boolean isNavigationBarShown(Activity activity) {
        int visibility;
        View findViewById = activity.findViewById(16908336);
        return (findViewById == null || (visibility = findViewById.getVisibility()) == 8 || visibility == 4) ? false : true;
    }

    public static int getNavigationBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }
}
