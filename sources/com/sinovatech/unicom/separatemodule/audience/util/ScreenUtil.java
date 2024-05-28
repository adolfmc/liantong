package com.sinovatech.unicom.separatemodule.audience.util;

import android.app.Activity;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.sinovatech.unicom.separatemodule.audience.custom.ContextConstant;
import com.sinovatech.unicom.separatemodule.audience.custom.ResourceReader;
import com.sinovatech.unicom.separatemodule.audience.custom.SafeHandler;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ScreenUtil {
    private static final int NOTCH_IN_SCREEN_VIVO = 32;
    private static final int ROUNDED_IN_SCREEN_VIVO = 8;
    private static int realScreenHeight;
    public static boolean sDeviceDataInit;
    private static float sDisplayMetricsDensity;
    static int sDisplayMetricsHeightPixels;
    static int sDisplayMetricsWidthPixels;
    private static float sDisplayRate;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnDisplayRectListener {
        void onRate(float f);
    }

    public static void initDeviceData() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) ContextConstant.getContext().getSystemService("window");
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int orientation = windowManager.getDefaultDisplay().getOrientation();
        if (orientation == 1 || orientation == 3) {
            sDisplayMetricsWidthPixels = displayMetrics.heightPixels;
            sDisplayMetricsHeightPixels = displayMetrics.widthPixels;
        } else {
            sDisplayMetricsWidthPixels = displayMetrics.widthPixels;
            sDisplayMetricsHeightPixels = displayMetrics.heightPixels;
        }
        sDisplayMetricsDensity = displayMetrics.density;
        sDeviceDataInit = true;
    }

    public static int getEquipmentWidth() {
        if (!sDeviceDataInit) {
            initDeviceData();
        }
        return sDisplayMetricsWidthPixels;
    }

    public static int getEquipmentHeight() {
        if (!sDeviceDataInit) {
            initDeviceData();
        }
        return sDisplayMetricsHeightPixels;
    }

    public static float getEquipmentDensity() {
        if (!sDeviceDataInit) {
            initDeviceData();
        }
        return sDisplayMetricsDensity;
    }

    public static int getStatusBarHeight() {
        int identifier;
        if (ResourceReader.AFTER_KITKAT && (identifier = ContextConstant.getContext().getResources().getIdentifier("status_bar_height", "dimen", "android")) > 0) {
            return ContextConstant.getContext().getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static DisplayMetrics getScreenSize(Activity activity) {
        DisplayMetrics displayMetrics;
        try {
            displayMetrics = new DisplayMetrics();
            try {
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
                return displayMetrics;
            }
        } catch (Exception e2) {
            e = e2;
            displayMetrics = null;
        }
        return displayMetrics;
    }

    public static int getRealScreenOrientation() {
        int[] screenDimensions = getScreenDimensions();
        int i = ContextConstant.getContext().getResources().getConfiguration().orientation;
        if (i == 2 || screenDimensions[0] <= screenDimensions[1]) {
            return i;
        }
        return 2;
    }

    public static int[] getScreenDimensions() {
        Display defaultDisplay = ((WindowManager) ContextConstant.getContext().getSystemService("window")).getDefaultDisplay();
        return new int[]{defaultDisplay.getWidth(), defaultDisplay.getHeight()};
    }

    public static int getNavigationBarHeight() {
        int identifier = ContextConstant.getContext().getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier > 0) {
            return ContextConstant.getContext().getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static boolean isNavigationBarShown(Activity activity) {
        int visibility;
        View findViewById = activity.findViewById(16908336);
        return (findViewById == null || (visibility = findViewById.getVisibility()) == 8 || visibility == 4) ? false : true;
    }

    public static int getScreenHeight() {
        return getEquipmentHeight();
    }

    public static int getRealScreenHeight() {
        if (realScreenHeight == 0) {
            int i = 0;
            try {
                Display defaultDisplay = ((WindowManager) ContextConstant.getContext().getSystemService("window")).getDefaultDisplay();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                Class.forName("android.view.Display").getMethod("getRealMetrics", DisplayMetrics.class).invoke(defaultDisplay, displayMetrics);
                i = displayMetrics.heightPixels;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (i == 0) {
                i = getScreenHeight();
            }
            realScreenHeight = i;
        }
        return realScreenHeight;
    }

    public static int getScreenWidth() {
        return getEquipmentWidth();
    }

    public static int dip2px(float f) {
        if (!sDeviceDataInit) {
            initDeviceData();
        }
        return (int) ((f * sDisplayMetricsDensity) + 0.5f);
    }

    public static boolean containsNotch() {
        return containsNotchInVivo();
    }

    private static boolean containsNotchInVivo() {
        try {
            Class<?> loadClass = ContextConstant.getContext().getClassLoader().loadClass("android.util.FtFeature");
            return ((Boolean) loadClass.getMethod("isFeatureSupport", Integer.TYPE).invoke(loadClass, 32)).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void finish(final Activity activity) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        if (!PhoneRomUtil.isSmartisanRom()) {
            activity.finish();
        } else {
            SafeHandler.getInst().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.util.ScreenUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    activity.finish();
                }
            }, 100L);
        }
    }

    public static void getDisplayRate(Activity activity, final OnDisplayRectListener onDisplayRectListener) {
        int realScreenHeight2;
        float f;
        if (onDisplayRectListener == null) {
            return;
        }
        if (activity == null) {
            onDisplayRectListener.onRate(getRate(null));
            return;
        }
        final WeakReference weakReference = new WeakReference(activity);
        int screenWidth = getScreenWidth();
        int screenHeight = getScreenHeight();
        if (screenHeight != getRealScreenHeight()) {
            double d = (screenWidth * 1.0f) / screenHeight;
            if ((d <= 0.5d && f / realScreenHeight2 > 0.5d) || (d > 0.5d && f / realScreenHeight2 <= 0.5d)) {
                Rect viewRect = getViewRect(activity);
                if (viewRect == null || viewRect.width() == 0 || viewRect.height() == 0) {
                    float f2 = sDisplayRate;
                    if (f2 != 0.0f) {
                        onDisplayRectListener.onRate(f2);
                    }
                    SafeHandler.getInst().post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.util.ScreenUtil.2
                        @Override // java.lang.Runnable
                        public void run() {
                            ScreenUtil.getDisplayRate((Activity) weakReference.get(), onDisplayRectListener);
                        }
                    });
                    return;
                }
                sDisplayRate = getRate(viewRect);
                if (onDisplayRectListener != null) {
                    onDisplayRectListener.onRate(sDisplayRate);
                    return;
                }
                return;
            }
        }
        if (onDisplayRectListener != null) {
            onDisplayRectListener.onRate(getRate(null));
        }
    }

    private static Rect getViewRect(Activity activity) {
        View childAt;
        if (activity == null || (childAt = ((ViewGroup) activity.getWindow().getDecorView()).getChildAt(0)) == null) {
            return null;
        }
        Rect rect = new Rect();
        childAt.getGlobalVisibleRect(rect);
        return rect;
    }

    private static float getRate(Rect rect) {
        int screenWidth = getScreenWidth();
        int screenHeight = getScreenHeight();
        int realScreenHeight2 = getRealScreenHeight();
        if (rect != null && rect.width() == screenWidth && rect.height() <= realScreenHeight2 && rect.height() >= screenHeight) {
            screenHeight = rect.height();
        }
        return (screenWidth * 1.0f) / screenHeight;
    }

    public static void clearDisplayRate() {
        sDisplayRate = 0.0f;
    }
}
