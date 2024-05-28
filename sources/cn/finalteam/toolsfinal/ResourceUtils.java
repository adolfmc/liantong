package cn.finalteam.toolsfinal;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ResourceUtils {
    public static int getLayoutId(Context context, String str) {
        return context.getResources().getIdentifier(str, "layout", context.getPackageName());
    }

    public static int getStringId(Context context, String str) {
        return context.getResources().getIdentifier(str, "string", context.getPackageName());
    }

    public static int getDrawableId(Context context, String str) {
        return context.getResources().getIdentifier(str, "drawable", context.getPackageName());
    }

    public static int getMipmapId(Context context, String str) {
        return context.getResources().getIdentifier(str, "mipmap", context.getPackageName());
    }

    public static int getStyleId(Context context, String str) {
        return context.getResources().getIdentifier(str, "style", context.getPackageName());
    }

    public static Object getStyleableId(Context context, String str) {
        return Integer.valueOf(context.getResources().getIdentifier(str, "styleable", context.getPackageName()));
    }

    public static int getAnimId(Context context, String str) {
        return context.getResources().getIdentifier(str, "anim", context.getPackageName());
    }

    public static int getId(Context context, String str) {
        return context.getResources().getIdentifier(str, "id", context.getPackageName());
    }

    public static int getColorId(Context context, String str) {
        return context.getResources().getIdentifier(str, "color", context.getPackageName());
    }
}
