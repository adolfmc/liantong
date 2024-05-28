package com.dueeeke.videoplayer.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.support.p086v7.app.ActionBar;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.view.ContextThemeWrapper;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class WindowUtil {
    public static double getStatusBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return identifier > 0 ? context.getResources().getDimensionPixelSize(identifier) : 0;
    }

    public static int getNavigationBarHeight(Context context) {
        if (hasNavigationBar(context)) {
            Resources resources = context.getResources();
            return resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
        }
        return 0;
    }

    public static boolean hasNavigationBar(Context context) {
        if (Build.VERSION.SDK_INT < 17) {
            return (ViewConfiguration.get(context).hasPermanentMenuKey() || KeyCharacterMap.deviceHasKey(4)) ? false : true;
        }
        Display defaultDisplay = getWindowManager(context).getDefaultDisplay();
        Point point = new Point();
        Point point2 = new Point();
        defaultDisplay.getSize(point);
        defaultDisplay.getRealSize(point2);
        return (point2.x == point.x && point2.y == point.y) ? false : true;
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context, boolean z) {
        if (z) {
            return context.getResources().getDisplayMetrics().heightPixels + getNavigationBarHeight(context);
        }
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    @SuppressLint({"RestrictedApi"})
    public static void hideSystemBar(Context context) {
        ActionBar supportActionBar;
        AppCompatActivity appCompActivity = getAppCompActivity(context);
        if (appCompActivity != null && (supportActionBar = appCompActivity.getSupportActionBar()) != null) {
            supportActionBar.setShowHideAnimationEnabled(false);
            supportActionBar.hide();
        }
        scanForActivity(context).getWindow().addFlags(1024);
        hideNavigationBar(context);
    }

    @SuppressLint({"RestrictedApi"})
    public static void showSystemBar(Context context) {
        ActionBar supportActionBar;
        scanForActivity(context).getWindow().clearFlags(1024);
        showNavigationBar(context);
        AppCompatActivity appCompActivity = getAppCompActivity(context);
        if (appCompActivity == null || (supportActionBar = appCompActivity.getSupportActionBar()) == null) {
            return;
        }
        supportActionBar.setShowHideAnimationEnabled(false);
        supportActionBar.show();
    }

    public static Activity scanForActivity(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return scanForActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    private static void hideNavigationBar(Context context) {
        scanForActivity(context).getWindow().getDecorView().setSystemUiVisibility(4102);
    }

    private static void showNavigationBar(Context context) {
        View decorView = scanForActivity(context).getWindow().getDecorView();
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & (-4103));
    }

    public static AppCompatActivity getAppCompActivity(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof AppCompatActivity) {
            return (AppCompatActivity) context;
        }
        if (context instanceof ContextThemeWrapper) {
            return getAppCompActivity(((ContextThemeWrapper) context).getBaseContext());
        }
        return null;
    }

    public static int dp2px(Context context, float f) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public static int sp2px(Context context, float f) {
        return (int) TypedValue.applyDimension(2, f, context.getResources().getDisplayMetrics());
    }

    public static WindowManager getWindowManager(Context context) {
        return (WindowManager) context.getSystemService("window");
    }

    public static boolean isEdge(Context context, MotionEvent motionEvent) {
        int dp2px = dp2px(context, 50.0f);
        float f = dp2px;
        return motionEvent.getRawX() < f || motionEvent.getRawX() > ((float) (getScreenWidth(context) - dp2px)) || motionEvent.getRawY() < f || motionEvent.getRawY() > ((float) (getScreenHeight(context, true) - dp2px));
    }
}
