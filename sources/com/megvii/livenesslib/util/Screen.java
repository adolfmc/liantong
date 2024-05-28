package com.megvii.livenesslib.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Screen {
    public static float LEFTMENU_UI_PERCENT = 0.15f;
    private static final int PADDING_B = 40;
    private static final int PADDING_L = 30;
    private static final int PADDING_R = 30;
    private static final int PADDING_T = 50;
    public static float charHeight;
    public static float density;
    public static float densityDpi;
    public static float drawHeight;
    public static float drawPaddingBottom;
    public static float drawPaddingLeft;
    public static float drawPaddingRight;
    public static float drawPaddingTop;
    public static int drawRows;
    public static float drawWidth;
    public static float lineHeight;
    public static float line_space;
    public static int mHeight;
    public static int mNotificationBarHeight;
    public static int mScreenHeight;
    public static int mScreenWidth;
    public static int mWidth;

    public static void initialize(Context context) {
        try {
            int screenWidth = getScreenWidth((Activity) context);
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            mScreenWidth = displayMetrics.widthPixels;
            mScreenHeight = displayMetrics.heightPixels;
            mWidth = displayMetrics.widthPixels;
            mHeight = displayMetrics.heightPixels;
            if (drawWidth == 0.0f || drawHeight == 0.0f || mWidth == 0 || mHeight == 0 || density == 0.0f || screenWidth != mWidth) {
                density = displayMetrics.density;
                mNotificationBarHeight = (int) (density * 35.0f);
                densityDpi = displayMetrics.densityDpi;
                drawPaddingLeft = density * 30.0f;
                drawPaddingRight = density * 30.0f;
                drawPaddingTop = density * 50.0f;
                drawPaddingBottom = density * 40.0f;
                drawWidth = (mWidth - drawPaddingLeft) - drawPaddingRight;
                drawHeight = (mHeight - drawPaddingTop) - drawPaddingBottom;
            }
        } catch (Exception unused) {
        }
    }

    public static String clipImageUrl(String str, String str2) {
        if (str != null) {
            if (str2 == null) {
                return str;
            }
            if (str.endsWith(".jpg") || str.endsWith(".png") || str.endsWith(".gif") || str.endsWith(".bmp")) {
                String substring = str.substring(str.length() - 4, str.length());
                int lastIndexOf = str.lastIndexOf(".");
                int lastIndexOf2 = str.lastIndexOf("/");
                if (lastIndexOf2 != -1) {
                    int i = lastIndexOf2 + 1;
                    String substring2 = str.substring(i, lastIndexOf);
                    if (substring2.endsWith("_m") || substring2.endsWith("_b") || substring2.endsWith("_s")) {
                        String substring3 = substring2.substring(0, substring2.length() - 2);
                        return str.substring(0, i) + substring3 + str2 + substring;
                    }
                    return str.substring(0, i) + substring2 + str2 + substring;
                }
                return null;
            }
        }
        return null;
    }

    public static int getScreenWidth(Activity activity) {
        return activity.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }
}
