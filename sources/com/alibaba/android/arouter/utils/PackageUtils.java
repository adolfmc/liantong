package com.alibaba.android.arouter.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import com.alibaba.android.arouter.launcher.ARouter;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PackageUtils {
    private static int NEW_VERSION_CODE;
    private static String NEW_VERSION_NAME;

    public static boolean isNewVersion(Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        if (packageInfo != null) {
            String str = packageInfo.versionName;
            int i = packageInfo.versionCode;
            SharedPreferences sharedPreferences = context.getSharedPreferences("SP_AROUTER_CACHE", 0);
            if (str.equals(sharedPreferences.getString("LAST_VERSION_NAME", null)) && i == sharedPreferences.getInt("LAST_VERSION_CODE", -1)) {
                return false;
            }
            NEW_VERSION_NAME = str;
            NEW_VERSION_CODE = i;
            return true;
        }
        return true;
    }

    public static void updateVersion(Context context) {
        if (android.text.TextUtils.isEmpty(NEW_VERSION_NAME) || NEW_VERSION_CODE == 0) {
            return;
        }
        context.getSharedPreferences("SP_AROUTER_CACHE", 0).edit().putString("LAST_VERSION_NAME", NEW_VERSION_NAME).putInt("LAST_VERSION_CODE", NEW_VERSION_CODE).apply();
    }

    private static PackageInfo getPackageInfo(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 16384);
        } catch (Exception unused) {
            ARouter.logger.error("ARouter::", "Get package info error.");
            return null;
        }
    }
}
