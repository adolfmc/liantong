package com.github.anrwatchdog;

import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ANRWatchDog extends Thread {
    private static final int DEFAULT_ANR_TIMEOUT = 5000;
    private ANRInterceptor _anrInterceptor;
    private ANRListener _anrListener;
    private boolean _ignoreDebugger;
    private InterruptionListener _interruptionListener;
    private boolean _logThreadsWithoutStackTrace;
    private String _namePrefix;
    private volatile boolean _reported;
    private volatile long _tick;
    private final Runnable _ticker;
    private final int _timeoutInterval;
    private final Handler _uiHandler;
    private static final ANRListener DEFAULT_ANR_LISTENER = new ANRListener() { // from class: com.github.anrwatchdog.ANRWatchDog.1
        @Override // com.github.anrwatchdog.ANRWatchDog.ANRListener
        public void onAppNotResponding(ANRError aNRError) {
            throw aNRError;
        }
    };
    private static final ANRInterceptor DEFAULT_ANR_INTERCEPTOR = new ANRInterceptor() { // from class: com.github.anrwatchdog.ANRWatchDog.2
        @Override // com.github.anrwatchdog.ANRWatchDog.ANRInterceptor
        public long intercept(long j) {
            return 0L;
        }
    };
    private static final InterruptionListener DEFAULT_INTERRUPTION_LISTENER = new InterruptionListener() { // from class: com.github.anrwatchdog.ANRWatchDog.3
        @Override // com.github.anrwatchdog.ANRWatchDog.InterruptionListener
        public void onInterrupted(InterruptedException interruptedException) {
            Log.w("ANRWatchdog", "Interrupted: " + interruptedException.getMessage());
        }
    };

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface ANRInterceptor {
        long intercept(long j);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface ANRListener {
        void onAppNotResponding(ANRError aNRError);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterruptionListener {
        void onInterrupted(InterruptedException interruptedException);
    }

    public ANRWatchDog() {
        this(5000);
    }

    public ANRWatchDog(int i) {
        this._anrListener = DEFAULT_ANR_LISTENER;
        this._anrInterceptor = DEFAULT_ANR_INTERCEPTOR;
        this._interruptionListener = DEFAULT_INTERRUPTION_LISTENER;
        this._uiHandler = new Handler(Looper.getMainLooper());
        this._namePrefix = "";
        this._logThreadsWithoutStackTrace = false;
        this._ignoreDebugger = false;
        this._tick = 0L;
        this._reported = false;
        this._ticker = new Runnable() { // from class: com.github.anrwatchdog.ANRWatchDog.4
            @Override // java.lang.Runnable
            public void run() {
                ANRWatchDog.this._tick = 0L;
                ANRWatchDog.this._reported = false;
            }
        };
        this._timeoutInterval = i;
    }

    public int getTimeoutInterval() {
        return this._timeoutInterval;
    }

    public ANRWatchDog setANRListener(ANRListener aNRListener) {
        if (aNRListener == null) {
            this._anrListener = DEFAULT_ANR_LISTENER;
        } else {
            this._anrListener = aNRListener;
        }
        return this;
    }

    public ANRWatchDog setANRInterceptor(ANRInterceptor aNRInterceptor) {
        if (aNRInterceptor == null) {
            this._anrInterceptor = DEFAULT_ANR_INTERCEPTOR;
        } else {
            this._anrInterceptor = aNRInterceptor;
        }
        return this;
    }

    public ANRWatchDog setInterruptionListener(InterruptionListener interruptionListener) {
        if (interruptionListener == null) {
            this._interruptionListener = DEFAULT_INTERRUPTION_LISTENER;
        } else {
            this._interruptionListener = interruptionListener;
        }
        return this;
    }

    public ANRWatchDog setReportThreadNamePrefix(String str) {
        if (str == null) {
            str = "";
        }
        this._namePrefix = str;
        return this;
    }

    public ANRWatchDog setReportMainThreadOnly() {
        this._namePrefix = null;
        return this;
    }

    public ANRWatchDog setReportAllThreads() {
        this._namePrefix = "";
        return this;
    }

    public ANRWatchDog setLogThreadsWithoutStackTrace(boolean z) {
        this._logThreadsWithoutStackTrace = z;
        return this;
    }

    public ANRWatchDog setIgnoreDebugger(boolean z) {
        this._ignoreDebugger = z;
        return this;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        ANRError NewMainOnly;
        setName("|ANR-WatchDog|");
        long j = this._timeoutInterval;
        while (!isInterrupted()) {
            boolean z = this._tick == 0;
            this._tick += j;
            if (z) {
                this._uiHandler.post(this._ticker);
            }
            try {
                Thread.sleep(j);
                if (this._tick != 0 && !this._reported) {
                    if (!this._ignoreDebugger && (Debug.isDebuggerConnected() || Debug.waitingForDebugger())) {
                        Log.w("ANRWatchdog", "An ANR was detected but ignored because the debugger is connected (you can prevent this with setIgnoreDebugger(true))");
                        this._reported = true;
                    } else {
                        j = this._anrInterceptor.intercept(this._tick);
                        if (j <= 0) {
                            if (this._namePrefix != null) {
                                NewMainOnly = ANRError.New(this._tick, this._namePrefix, this._logThreadsWithoutStackTrace);
                            } else {
                                NewMainOnly = ANRError.NewMainOnly(this._tick);
                            }
                            this._anrListener.onAppNotResponding(NewMainOnly);
                            j = this._timeoutInterval;
                            this._reported = true;
                        }
                    }
                }
            } catch (InterruptedException e) {
                this._interruptionListener.onInterrupted(e);
                return;
            }
        }
    }
}
