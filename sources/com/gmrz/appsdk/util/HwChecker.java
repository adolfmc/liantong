package com.gmrz.appsdk.util;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class HwChecker {
    private static final String TAG = "HwChecker";

    public static boolean isSupportHwKeystore() {
        try {
            Class.forName("com.huawei.security.keystore.HwUniversalKeyStoreProvider");
            return true;
        } catch (ClassNotFoundException unused) {
            Logger.m15757e("HwChecker", "HwUniversalKeyStoreProvider not found");
            return false;
        }
    }
}
