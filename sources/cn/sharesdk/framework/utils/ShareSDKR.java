package cn.sharesdk.framework.utils;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ShareSDKR {
    public static int getResId(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return 0;
        }
        String packageName = context.getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            return 0;
        }
        int identifier = context.getResources().getIdentifier(str2, str, packageName);
        if (identifier <= 0) {
            identifier = context.getResources().getIdentifier(str2.toLowerCase(), str, packageName);
        }
        if (identifier <= 0) {
            Resources resources = context.getResources();
            identifier = resources.getIdentifier("ssdk_" + str2, str, packageName);
            if (identifier <= 0) {
                Resources resources2 = context.getResources();
                identifier = resources2.getIdentifier("ssdk_" + str2.toLowerCase(), str, packageName);
            }
        }
        if (identifier <= 0) {
            Resources resources3 = context.getResources();
            identifier = resources3.getIdentifier("ssdk_oks_" + str2, str, packageName);
            if (identifier <= 0) {
                Resources resources4 = context.getResources();
                identifier = resources4.getIdentifier("ssdk_oks_" + str2.toLowerCase(), str, packageName);
            }
        }
        if (identifier <= 0) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21743a("failed to parse " + str + " resource \"" + str2 + "\"");
        }
        return identifier;
    }

    public static int getBitmapRes(Context context, String str) {
        return getResId(context, "drawable", str);
    }

    public static int getStringRes(Context context, String str) {
        return getResId(context, "string", str);
    }

    public static int getStringArrayRes(Context context, String str) {
        return getResId(context, "array", str);
    }

    public static int getLayoutRes(Context context, String str) {
        return getResId(context, "layout", str);
    }

    public static int getStyleRes(Context context, String str) {
        return getResId(context, "style", str);
    }

    public static int getIdRes(Context context, String str) {
        return getResId(context, "id", str);
    }

    public static int getColorRes(Context context, String str) {
        return getResId(context, "color", str);
    }

    public static int getRawRes(Context context, String str) {
        return getResId(context, "raw", str);
    }

    public static int getPluralsRes(Context context, String str) {
        return getResId(context, "plurals", str);
    }

    public static int getAnimRes(Context context, String str) {
        return getResId(context, "anim", str);
    }
}
