package com.unicompayment.sdk;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class UnicomFPSDK {
    public static String getDeviceIdOnline(Context context, String str, String str2) {
        Object processCmd = ApiNative.processCmd(32786, context, new String[]{str, str2});
        return processCmd == null ? "" : processCmd.toString();
    }
}
