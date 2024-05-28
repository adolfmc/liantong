package com.baidu.p120ar.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.utils.ARSDKInfo */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARSDKInfo {
    private static final String CI_VERSION = "AUTO_GEN_CI_VERSION";
    private static final int VERSION_CODE = 5050;
    private static final String VERSION_NAME = "5.5.0";
    private static String mFuncType = "101";
    private static String mSDKType = "pro";

    public static String getCiVersion() {
        return "AUTO_GEN_CI_VERSION";
    }

    public static int getVersionCode() {
        return 5050;
    }

    public static String getVersionName() {
        return "5.5.0";
    }

    public static String getAppId(Context context) {
        if (context == null) {
            return null;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            StringBuilder sb = new StringBuilder(context.getApplicationContext().getPackageName());
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(context.getApplicationContext().getPackageName(), 0);
                if (packageInfo != null) {
                    String str = packageInfo.versionName;
                    if (!TextUtils.isEmpty(str)) {
                        sb.append("_");
                        sb.append(str);
                        ARLog.m20422d("appId = " + sb.toString());
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            return sb.toString();
        } catch (NullPointerException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void setSDKType(String str) {
        mSDKType = str;
    }

    public static String getSDKType() {
        return mSDKType;
    }

    public static void setFunctionType(String str) {
        mFuncType = str;
    }

    public static String getFunctionType() {
        return mFuncType;
    }
}
