package com.chinaunicon.jtwifilib.core.crashreport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Process;
import android.util.Log;
import java.lang.Thread;

@SuppressLint({"SimpleDateFormat"})
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    public static String TAG = "com.chinaunicon.jtwifilib.core.crashreport.CrashHandler";
    private static CrashHandler instance = new CrashHandler();
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return instance;
    }

    public void init(Context context) {
        this.mContext = context;
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        CrashFileManager.getInstance().collectDeviceInfo(this.mContext);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
        if (!handleException(th) && (uncaughtExceptionHandler = this.mDefaultHandler) != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
            return;
        }
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            Log.e(TAG, "error : ", e);
        }
        Process.killProcess(Process.myPid());
        System.exit(1);
    }

    private boolean handleException(Throwable th) {
        if (th == null) {
            return false;
        }
        CrashFileManager.getInstance().saveCrashInfo(th, this.mContext);
        return true;
    }
}
