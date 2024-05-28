package com.huawei.hms.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class ResourceLoaderUtil {

    /* renamed from: a */
    private static Context f11854a;

    /* renamed from: b */
    private static String f11855b;

    public static int getAnimId(String str) {
        return f11854a.getResources().getIdentifier(str, "anim", f11855b);
    }

    public static int getColorId(String str) {
        return f11854a.getResources().getIdentifier(str, "color", f11855b);
    }

    public static int getDimenId(String str) {
        return f11854a.getResources().getIdentifier(str, "dimen", f11855b);
    }

    public static Drawable getDrawable(String str) {
        return f11854a.getResources().getDrawable(getDrawableId(str));
    }

    public static int getDrawableId(String str) {
        return f11854a.getResources().getIdentifier(str, "drawable", f11855b);
    }

    public static int getIdId(String str) {
        return f11854a.getResources().getIdentifier(str, "id", f11855b);
    }

    public static int getLayoutId(String str) {
        return f11854a.getResources().getIdentifier(str, "layout", f11855b);
    }

    public static String getString(String str) {
        return f11854a.getResources().getString(getStringId(str));
    }

    public static int getStringId(String str) {
        return f11854a.getResources().getIdentifier(str, "string", f11855b);
    }

    public static int getStyleId(String str) {
        return f11854a.getResources().getIdentifier(str, "style", f11855b);
    }

    public static Context getmContext() {
        return f11854a;
    }

    public static void setmContext(Context context) {
        f11854a = context;
        f11855b = context.getPackageName();
    }

    public static String getString(String str, Object... objArr) {
        return f11854a.getResources().getString(getStringId(str), objArr);
    }
}
