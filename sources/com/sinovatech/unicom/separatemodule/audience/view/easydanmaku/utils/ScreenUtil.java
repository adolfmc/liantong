package com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ScreenUtil {
    private static final String TAG = "ScreenUtil";
    private static int sDesignHeight = 1920;
    private static int sDesignWidth = 1080;
    private static int sScreenHeight = 1080;
    private static int sScreenWidth = 1920;

    public static void init(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        sScreenWidth = displayMetrics.widthPixels;
        sScreenHeight = displayMetrics.heightPixels;
        if ((sDesignWidth > sDesignHeight) != (sScreenWidth > sScreenHeight)) {
            int i = sDesignWidth;
            sDesignWidth = sDesignHeight;
            sDesignHeight = i;
        }
    }

    public static void setDesignWidthAndHeight(int i, int i2) {
        sDesignWidth = i;
        sDesignHeight = i2;
    }

    public static int autoSize(int i) {
        return autoWidth(i);
    }

    public static int autoSize(int i, int i2) {
        return isPortrait() ? autoSize(i2) : autoSize(i);
    }

    public static int autoWidth(int i) {
        int i2;
        int i3 = sScreenWidth;
        if (i3 == 0 || (i2 = sDesignWidth) == 0) {
            return i;
        }
        int i4 = (i3 * i) / i2;
        if (i == 0 || i4 != 0) {
            return i4;
        }
        return 1;
    }

    public static int autoHeight(int i) {
        int i2;
        int i3 = sScreenHeight;
        if (i3 == 0 || (i2 = sDesignHeight) == 0) {
            return i;
        }
        int i4 = (i3 * i) / i2;
        if (i == 0 || i4 != 0) {
            return i4;
        }
        return 1;
    }

    public static int getScreenWidth() {
        return sScreenWidth;
    }

    public static void setScreenWidth(int i) {
        sScreenWidth = i;
    }

    public static int getScreenHeight() {
        return sScreenHeight;
    }

    public static void setScreenHeight(int i) {
        sScreenHeight = i;
    }

    public static boolean isPortrait() {
        return getScreenHeight() > getScreenWidth();
    }
}
