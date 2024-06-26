package com.blankj.utilcode.util;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class AdaptScreenUtils {
    private static List<Field> sMetricsFields;

    private AdaptScreenUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    @NonNull
    public static Resources adaptWidth(@NonNull Resources resources, int i) {
        if (resources == null) {
            throw new NullPointerException("Argument 'resources' of type Resources (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        applyDisplayMetrics(resources, (resources.getDisplayMetrics().widthPixels * 72.0f) / i);
        if (resources != null) {
            return resources;
        }
        throw new NullPointerException("Detected an attempt to return null from a method com.blankj.utilcode.util.AdaptScreenUtils.adaptWidth() marked by @android.support.annotation.NonNull");
    }

    @NonNull
    public static Resources adaptHeight(@NonNull Resources resources, int i) {
        if (resources == null) {
            throw new NullPointerException("Argument 'resources' of type Resources (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        Resources adaptHeight = adaptHeight(resources, i, false);
        if (adaptHeight != null) {
            return adaptHeight;
        }
        throw new NullPointerException("Detected an attempt to return null from a method com.blankj.utilcode.util.AdaptScreenUtils.adaptHeight() marked by @android.support.annotation.NonNull");
    }

    @NonNull
    public static Resources adaptHeight(@NonNull Resources resources, int i, boolean z) {
        if (resources == null) {
            throw new NullPointerException("Argument 'resources' of type Resources (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        applyDisplayMetrics(resources, ((resources.getDisplayMetrics().heightPixels + (z ? getNavBarHeight(resources) : 0)) * 72.0f) / i);
        if (resources != null) {
            return resources;
        }
        throw new NullPointerException("Detected an attempt to return null from a method com.blankj.utilcode.util.AdaptScreenUtils.adaptHeight() marked by @android.support.annotation.NonNull");
    }

    private static int getNavBarHeight(@NonNull Resources resources) {
        if (resources == null) {
            throw new NullPointerException("Argument 'resources' of type Resources (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier != 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    @NonNull
    public static Resources closeAdapt(@NonNull Resources resources) {
        if (resources == null) {
            throw new NullPointerException("Argument 'resources' of type Resources (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        applyDisplayMetrics(resources, Resources.getSystem().getDisplayMetrics().density * 72.0f);
        if (resources != null) {
            return resources;
        }
        throw new NullPointerException("Detected an attempt to return null from a method com.blankj.utilcode.util.AdaptScreenUtils.closeAdapt() marked by @android.support.annotation.NonNull");
    }

    public static int pt2Px(float f) {
        return (int) (((f * Utils.getApp().getResources().getDisplayMetrics().xdpi) / 72.0f) + 0.5d);
    }

    public static int px2Pt(float f) {
        return (int) (((f * 72.0f) / Utils.getApp().getResources().getDisplayMetrics().xdpi) + 0.5d);
    }

    private static void applyDisplayMetrics(@NonNull Resources resources, float f) {
        if (resources == null) {
            throw new NullPointerException("Argument 'resources' of type Resources (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        resources.getDisplayMetrics().xdpi = f;
        Utils.getApp().getResources().getDisplayMetrics().xdpi = f;
        applyOtherDisplayMetrics(resources, f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Runnable getPreLoadRunnable() {
        return new Runnable() { // from class: com.blankj.utilcode.util.AdaptScreenUtils.1
            @Override // java.lang.Runnable
            public void run() {
                AdaptScreenUtils.preLoad();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void preLoad() {
        applyDisplayMetrics(Resources.getSystem(), Resources.getSystem().getDisplayMetrics().xdpi);
    }

    private static void applyOtherDisplayMetrics(Resources resources, float f) {
        if (sMetricsFields == null) {
            sMetricsFields = new ArrayList();
            Class<?> cls = resources.getClass();
            Field[] declaredFields = cls.getDeclaredFields();
            while (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    if (field.getType().isAssignableFrom(DisplayMetrics.class)) {
                        field.setAccessible(true);
                        DisplayMetrics metricsFromField = getMetricsFromField(resources, field);
                        if (metricsFromField != null) {
                            sMetricsFields.add(field);
                            metricsFromField.xdpi = f;
                        }
                    }
                }
                cls = cls.getSuperclass();
                if (cls == null) {
                    return;
                }
                declaredFields = cls.getDeclaredFields();
            }
            return;
        }
        applyMetricsFields(resources, f);
    }

    private static void applyMetricsFields(Resources resources, float f) {
        for (Field field : sMetricsFields) {
            try {
                DisplayMetrics displayMetrics = (DisplayMetrics) field.get(resources);
                if (displayMetrics != null) {
                    displayMetrics.xdpi = f;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static DisplayMetrics getMetricsFromField(Resources resources, Field field) {
        try {
            return (DisplayMetrics) field.get(resources);
        } catch (Exception unused) {
            return null;
        }
    }
}
