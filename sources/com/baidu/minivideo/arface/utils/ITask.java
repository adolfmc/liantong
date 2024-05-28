package com.baidu.minivideo.arface.utils;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface ITask {
    public static final int STATE_FAILED = 3;
    public static final int STATE_RUNNIN = 1;
    public static final int STATE_SUCCESS = 2;
    public static final int STATE_UNINIT = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface Callback {
        void onResult(int i, ITask iTask);
    }

    void cancel();

    boolean isRunning();

    boolean isSuccess();

    void run();

    void start(Callback callback);
}
