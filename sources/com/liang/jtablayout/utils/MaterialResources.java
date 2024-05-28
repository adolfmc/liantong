package com.liang.jtablayout.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleableRes;
import android.support.p086v7.content.res.AppCompatResources;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: E:\10762272_dexfile_execute.dex */
public class MaterialResources {
    private MaterialResources() {
    }

    @Nullable
    public static ColorStateList getColorStateList(Context context, TypedArray typedArray, @StyleableRes int i) {
        int resourceId;
        ColorStateList colorStateList;
        return (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0 || (colorStateList = AppCompatResources.getColorStateList(context, resourceId)) == null) ? typedArray.getColorStateList(i) : colorStateList;
    }

    @Nullable
    public static Drawable getDrawable(Context context, TypedArray typedArray, @StyleableRes int i) {
        int resourceId;
        Drawable drawable;
        return (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0 || (drawable = AppCompatResources.getDrawable(context, resourceId)) == null) ? typedArray.getDrawable(i) : drawable;
    }

    @StyleableRes
    static int getIndexWithValue(TypedArray typedArray, @StyleableRes int i, @StyleableRes int i2) {
        return typedArray.hasValue(i) ? i : i2;
    }
}
