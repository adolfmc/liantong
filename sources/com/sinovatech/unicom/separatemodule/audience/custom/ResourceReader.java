package com.sinovatech.unicom.separatemodule.audience.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.view.View;
import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ResourceReader {
    public static boolean AFTER_ICE_CREAM = afterIceCream();
    public static boolean AFTER_JELLY_BEAN = afterJellybean();
    public static boolean AFTER_JELLY_BEAN_MR2 = afterJellybeanMR2();
    public static boolean AFTER_JELLY_BEAN_MR1 = afterJellybeanMR1();
    public static final boolean AFTER_KITKAT = afterKitkat();
    public static final boolean AFTER_LOLLIPOP = afterLollipop();
    public static final boolean AFTER_M = afterM();
    public static final boolean AFTER_O = afterO();

    public static String getString(int i) {
        return ContextConstant.getContext().getResources().getString(i);
    }

    public static String getString(int i, Object... objArr) {
        return ContextConstant.getContext().getResources().getString(i, objArr);
    }

    public static int getDimensionPixelOffset(int i) {
        return ContextConstant.getContext().getResources().getDimensionPixelOffset(i);
    }

    public static int getDimensionPixelSize(int i) {
        return ContextConstant.getContext().getResources().getDimensionPixelSize(i);
    }

    public static float getDimension(int i) {
        return ContextConstant.getContext().getResources().getDimension(i);
    }

    public static Drawable getDrawable(int i) {
        return ContextConstant.getContext().getResources().getDrawable(i);
    }

    public static Drawable getDrawable(Context context, @DrawableRes int i) {
        if (context == null) {
            context = ContextConstant.getContext();
        }
        if (AFTER_LOLLIPOP) {
            return context.getDrawable(i);
        }
        return context.getResources().getDrawable(i);
    }

    public static int getColor(int i) {
        return ContextConstant.getContext().getResources().getColor(i);
    }

    @TargetApi(23)
    public static int getColor(@ColorRes int i, Context context) {
        if (context == null) {
            context = ContextConstant.getContext();
        }
        if (AFTER_M) {
            return context.getResources().getColor(i, context.getTheme());
        }
        return context.getResources().getColor(i);
    }

    public static ColorStateList getColorStateList(int i) {
        return ContextConstant.getContext().getResources().getColorStateList(i);
    }

    public static InputStream getAssets(String str) throws IOException {
        return ContextConstant.getContext().getResources().getAssets().open(str);
    }

    public static String[] getStringArray(int i) {
        return ContextConstant.getContext().getResources().getStringArray(i);
    }

    public static int[] getIntArray(int i) {
        return ContextConstant.getContext().getResources().getIntArray(i);
    }

    public static TypedArray getTypedArray(int i) {
        return ContextConstant.getContext().getResources().obtainTypedArray(i);
    }

    public static int getInteger(int i) {
        return ContextConstant.getContext().getResources().getInteger(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean afterIceCream() {
        return Build.VERSION.SDK_INT >= 14;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean afterJellybean() {
        return Build.VERSION.SDK_INT >= 16;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean afterJellybeanMR1() {
        return Build.VERSION.SDK_INT >= 17;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean afterJellybeanMR2() {
        return Build.VERSION.SDK_INT >= 18;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean afterKitkat() {
        return Build.VERSION.SDK_INT >= 19;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean afterLollipop() {
        return Build.VERSION.SDK_INT >= 21;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean afterM() {
        return Build.VERSION.SDK_INT >= 23;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean afterO() {
        return Build.VERSION.SDK_INT >= 26;
    }

    public static void setBackgroundDrawable(View view, Drawable drawable) {
        if (view == null) {
            return;
        }
        if (AFTER_JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }
}
