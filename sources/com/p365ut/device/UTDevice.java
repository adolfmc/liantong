package com.p365ut.device;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ut.device.UTDevice */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class UTDevice {
    @Deprecated
    public static String getAid(String str, String str2, Context context) {
        return "";
    }

    @Deprecated
    public static void getAidAsync(String str, String str2, Context context, AidCallback aidCallback) {
    }

    public static String getUtdid(Context context) {
        return com.p343ta.utdid2.device.UTDevice.getUtdid(context);
    }

    @Deprecated
    public static String getUtdidForUpdate(Context context) {
        return com.p343ta.utdid2.device.UTDevice.getUtdidForUpdate(context);
    }
}
