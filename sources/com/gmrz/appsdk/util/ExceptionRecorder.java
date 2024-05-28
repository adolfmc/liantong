package com.gmrz.appsdk.util;

import android.content.Context;
import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ExceptionRecorder {
    private static final String FILE_NAME_STASH_REMOTE_SERVICE_EXCEPTION = "fido_comm";
    private static final String KEY_STASH_REMOTE_SERVICE_EXCEPTION_HAPPENED = "fido_client_service_exception";
    private static final String TAG = "ExceptionRecorder";

    public static boolean isServiceExceptionHappened(Context context) {
        if (context != null) {
            return context.getSharedPreferences("fido_comm", 0).getBoolean("fido_client_service_exception", false);
        }
        Log.wtf("ExceptionRecorder", "context is null, can not edit sharedPreferences");
        return false;
    }

    public static void recordServiceException(Context context, boolean z) {
        if (context != null) {
            context.getSharedPreferences("fido_comm", 0).edit().putBoolean("fido_client_service_exception", z).apply();
        } else {
            Log.wtf("ExceptionRecorder", "context is null, can not edit sharedPreferences");
        }
    }
}
