package com.baidu.cloud.media.player.apm;

import android.content.Context;
import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler INSTANCE = new CrashHandler();
    public static final String TAG = "SDKCrashHandler";
    private Thread.UncaughtExceptionHandler oldExceptionHandler = null;
    private Context appContext = null;

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        if (this.oldExceptionHandler == null) {
            this.oldExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
        if (this.appContext == null) {
            this.appContext = context.getApplicationContext();
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        try {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            printWriter.close();
            String obj = stringWriter.toString();
            if (obj.contains("com.baidu.cloud.media")) {
                APMEventHandle.getInstance(this.appContext).onPlayerCrash(obj);
            }
        } catch (Exception e) {
            Log.d("SDKCrashHandler", "" + e.getMessage());
        }
        if (this.oldExceptionHandler.equals(this)) {
            return;
        }
        this.oldExceptionHandler.uncaughtException(thread, th);
    }
}
