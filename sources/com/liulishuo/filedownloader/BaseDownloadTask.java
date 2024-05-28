package com.liulishuo.filedownloader;

import android.support.annotation.Nullable;
import com.liulishuo.filedownloader.ITaskHunter;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface BaseDownloadTask {
    public static final int DEFAULT_CALLBACK_PROGRESS_MIN_INTERVAL_MILLIS = 10;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface FinishListener {
        void over(BaseDownloadTask baseDownloadTask);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface IRunningTask {
        void free();

        int getAttachKey();

        ITaskHunter.IMessageHandler getMessageHandler();

        BaseDownloadTask getOrigin();

        @Nullable
        Object getPauseLock();

        /* renamed from: is */
        boolean mo13743is(int i);

        /* renamed from: is */
        boolean mo13742is(FileDownloadListener fileDownloadListener);

        boolean isContainFinishListener();

        boolean isMarkedAdded2List();

        boolean isOver();

        void markAdded2List();

        void setAttachKeyByQueue(int i);

        void setAttachKeyDefault();

        void startTaskByQueue();

        void startTaskByRescue();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InQueueTask {
        int enqueue();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface LifeCycleCallback {
        void onBegin();

        void onIng();

        void onOver();
    }

    BaseDownloadTask addFinishListener(FinishListener finishListener);

    BaseDownloadTask addHeader(String str);

    BaseDownloadTask addHeader(String str, String str2);

    InQueueTask asInQueueTask();

    boolean cancel();

    int getAutoRetryTimes();

    int getCallbackProgressMinInterval();

    int getCallbackProgressTimes();

    int getDownloadId();

    Throwable getErrorCause();

    String getEtag();

    Throwable getEx();

    String getFilename();

    int getId();

    long getLargeFileSoFarBytes();

    long getLargeFileTotalBytes();

    FileDownloadListener getListener();

    String getPath();

    int getRetryingTimes();

    int getSmallFileSoFarBytes();

    int getSmallFileTotalBytes();

    int getSoFarBytes();

    int getSpeed();

    byte getStatus();

    Object getTag();

    Object getTag(int i);

    String getTargetFilePath();

    int getTotalBytes();

    String getUrl();

    boolean isAttached();

    boolean isContinue();

    boolean isForceReDownload();

    boolean isLargeFile();

    boolean isPathAsDirectory();

    boolean isResuming();

    boolean isReusedOldFile();

    boolean isRunning();

    boolean isSyncCallback();

    boolean isUsing();

    boolean isWifiRequired();

    boolean pause();

    @Deprecated
    int ready();

    BaseDownloadTask removeAllHeaders(String str);

    boolean removeFinishListener(FinishListener finishListener);

    boolean reuse();

    BaseDownloadTask setAutoRetryTimes(int i);

    BaseDownloadTask setCallbackProgressIgnored();

    BaseDownloadTask setCallbackProgressMinInterval(int i);

    BaseDownloadTask setCallbackProgressTimes(int i);

    BaseDownloadTask setFinishListener(FinishListener finishListener);

    BaseDownloadTask setForceReDownload(boolean z);

    BaseDownloadTask setListener(FileDownloadListener fileDownloadListener);

    BaseDownloadTask setMinIntervalUpdateSpeed(int i);

    BaseDownloadTask setPath(String str);

    BaseDownloadTask setPath(String str, boolean z);

    BaseDownloadTask setSyncCallback(boolean z);

    BaseDownloadTask setTag(int i, Object obj);

    BaseDownloadTask setTag(Object obj);

    BaseDownloadTask setWifiRequired(boolean z);

    int start();
}
