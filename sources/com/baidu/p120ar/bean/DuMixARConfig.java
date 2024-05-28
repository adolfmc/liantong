package com.baidu.p120ar.bean;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.bean.DuMixARConfig */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DuMixARConfig {
    private static String mAPIKey;
    private static String mAipAppId;
    private static String mPackageName;
    private static String mSecretKey;

    public static void setAppId(String str) {
        mAipAppId = str;
    }

    public static String getAipAppId() {
        return mAipAppId;
    }

    public static void setAPIKey(String str) {
        mAPIKey = str;
    }

    public static String getAPIKey() {
        return mAPIKey;
    }

    public static void setSecretKey(String str) {
        mSecretKey = str;
    }

    public static String getSecretKey() {
        return mSecretKey;
    }

    public static void setPackageName(String str) {
        mPackageName = str;
    }

    public static String getPackageName() {
        return mPackageName;
    }
}
