package com.tydic.softphone.tydic_tracker.app;

import android.content.Context;
import android.util.Log;
import com.tydic.softphone.tydic_tracker.tydicDB.TyBreakdownsEntity;
import com.tydic.softphone.tydic_tracker.tydicDB.TyDB;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class MyCrashHandler implements Thread.UncaughtExceptionHandler {
    private static String[] PERMISSIONS_STORAGE = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    public Context context;

    public void StartANRWatchDog() {
    }

    public MyCrashHandler(Context context) {
        this.context = context;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        int i;
        Log.e("tyLog", "Thread = " + thread.getName() + "\nThrowable = " + th.getMessage());
        String stackTraceInfo = getStackTraceInfo(th);
        String str = "";
        String stackTraceInfo2 = getStackTraceInfo(th);
        String cls = th.getClass().toString();
        long currTime = TYUtil.getCurrTime();
        long currTime2 = TYUtil.getCurrTime();
        if (Thread.currentThread().getStackTrace().length > 0) {
            str = th.getStackTrace()[1].getClassName();
            i = th.getStackTrace()[1].getLineNumber();
            th.getStackTrace()[1].getMethodName();
        } else {
            i = 0;
        }
        Log.e("tyLog", stackTraceInfo);
        TyDB tyDB = new TyDB();
        tyDB.OpenUserDb(this.context);
        TyBreakdownsEntity tyBreakdownsEntity = new TyBreakdownsEntity();
        tyBreakdownsEntity.setSes_id(TYApplication.ses_id);
        tyBreakdownsEntity.class_name = str;
        tyBreakdownsEntity.setLineNum(String.valueOf(i));
        tyBreakdownsEntity.setCrash_type(1);
        tyBreakdownsEntity.setTrack_details(stackTraceInfo2);
        tyBreakdownsEntity.setTarget_name("");
        tyBreakdownsEntity.setEvent_tag("");
        tyBreakdownsEntity.setCrash_name(cls);
        tyBreakdownsEntity.setIs_thread_break(1);
        tyBreakdownsEntity.setTimestamp(currTime);
        tyBreakdownsEntity.setCollect_time(currTime2);
        tyBreakdownsEntity.setEvent_id("");
        tyDB.insertBreakdownData(tyBreakdownsEntity);
    }

    private String getStackTraceInfo(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = null;
        try {
            PrintWriter printWriter2 = new PrintWriter(stringWriter);
            try {
                th.printStackTrace(printWriter2);
                printWriter2.close();
                return stringWriter.toString();
            } catch (Exception unused) {
                printWriter = printWriter2;
                if (printWriter != null) {
                    printWriter.close();
                }
                return "";
            } catch (Throwable th2) {
                th = th2;
                printWriter = printWriter2;
                if (printWriter != null) {
                    printWriter.close();
                }
                throw th;
            }
        } catch (Exception unused2) {
        } catch (Throwable th3) {
            th = th3;
        }
    }
}
