package com.baidu.p120ar.statistic;

import android.content.Context;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.statistic.LogSendRunnable */
/* loaded from: E:\10201592_dexfile_execute.dex */
class LogSendRunnable implements Runnable {
    private static final int ERROR_REQUEST_COUNT_FOR_FREEZE = 7;
    private WeakReference<Context> mContextRef;
    private int mContinuousErrorCount;
    private volatile boolean mIsShutdown;
    private Object mLock;
    private LogSendTask[] mTasks;

    public LogSendRunnable(Context context, Object obj, LogSendTask[] logSendTaskArr) {
        if (logSendTaskArr == null) {
            throw new NullPointerException();
        }
        this.mContextRef = new WeakReference<>(context);
        this.mContinuousErrorCount = 0;
        this.mIsShutdown = false;
        this.mLock = obj;
        this.mTasks = logSendTaskArr;
    }

    public void shutdown() {
        this.mIsShutdown = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0086 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x000b A[SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            Method dump skipped, instructions count: 241
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.p120ar.statistic.LogSendRunnable.run():void");
    }
}
