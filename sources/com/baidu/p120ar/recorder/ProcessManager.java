package com.baidu.p120ar.recorder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.recorder.ProcessManager */
/* loaded from: E:\10201592_dexfile_execute.dex */
class ProcessManager {
    private static final int DEFAULT_TOTAL_PROCESS = 100;
    private boolean mStart;
    private long mStartTimeMs;
    private int mTotalProcess;
    private long mTotalTimeMs;

    public ProcessManager(long j) {
        this.mTotalProcess = 100;
        this.mTotalTimeMs = 0L;
        this.mStartTimeMs = 0L;
        this.mStart = false;
        this.mTotalTimeMs = j;
    }

    public ProcessManager(long j, int i) {
        this.mTotalProcess = 100;
        this.mTotalTimeMs = 0L;
        this.mStartTimeMs = 0L;
        this.mStart = false;
        this.mTotalTimeMs = j;
        this.mTotalProcess = i;
    }

    public boolean isStart() {
        return this.mStart;
    }

    public void start(long j) {
        this.mStartTimeMs = j;
        this.mStart = true;
    }

    public int getTotalProcess() {
        return this.mTotalProcess;
    }

    public int getCurrentProcess(long j) {
        long j2 = this.mTotalTimeMs;
        if (j2 != 0) {
            long j3 = this.mStartTimeMs;
            if (j3 == 0) {
                return 0;
            }
            return (int) (((j - j3) * this.mTotalProcess) / j2);
        }
        return 0;
    }
}
