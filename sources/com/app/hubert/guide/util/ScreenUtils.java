package com.app.hubert.guide.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.ViewConfiguration;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ScreenUtils {
    private ScreenUtils() {
        throw new AssertionError();
    }

    public static int dp2px(Context context, int i) {
        return (int) (i * context.getResources().getDisplayMetrics().density);
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getStatusBarHeight(Context context) {
        int dp2px = dp2px(context, 20);
        LogUtil.m20451i("common statusBar height:" + dp2px);
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            dp2px = context.getResources().getDimensionPixelSize(identifier);
            LogUtil.m20451i("real statusBar height:" + dp2px);
        }
        LogUtil.m20451i("finally statusBar height:" + dp2px);
        return dp2px;
    }

    public static boolean isNavigationBarShow(Activity activity) {
        if (Build.VERSION.SDK_INT < 17) {
            return (ViewConfiguration.get(activity).hasPermanentMenuKey() || KeyCharacterMap.deviceHasKey(4)) ? false : true;
        }
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        Point point2 = new Point();
        defaultDisplay.getSize(point);
        defaultDisplay.getRealSize(point2);
        return point2.y != point.y;
    }

    public static int getNavigationBarHeight(Activity activity) {
        if (isNavigationBarShow(activity)) {
            Resources resources = activity.getResources();
            int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            int dimensionPixelSize = identifier > 0 ? resources.getDimensionPixelSize(identifier) : 0;
            LogUtil.m20451i("NavigationBar的高度:" + dimensionPixelSize);
            return dimensionPixelSize;
        }
        return 0;
    }
}
