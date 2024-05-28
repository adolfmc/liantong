package com.gmrz.appsdk.util;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ServerRespObserver {
    private static final String KEY_SERVER_RESP = "data";
    private static final String SP_NAME = "server_resp";
    private static final String TAG = "ServerRespObserver";

    private static String getFromSp(Context context) {
        return context.getSharedPreferences("server_resp", 0).getString("data", "");
    }

    public static boolean isRespChange(Context context, String str) {
        if (str.equals(getFromSp(context))) {
            return false;
        }
        Logger.wtf("ServerRespObserver", "Check device ability server response is changed, stashed data is updated.");
        stashIntoSp(context, str);
        return true;
    }

    private static void stashIntoSp(Context context, String str) {
        context.getSharedPreferences("server_resp", 0).edit().putString("data", str).apply();
    }
}
