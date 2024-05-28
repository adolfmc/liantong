package com.p319ss.android.socialbase.downloader.downloader;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.p319ss.android.socialbase.downloader.constants.AsyncHandleStatus;
import com.p319ss.android.socialbase.downloader.constants.ByteInvalidRetryStatus;
import com.p319ss.android.socialbase.downloader.constants.DownloadStatus;
import com.p319ss.android.socialbase.downloader.constants.ListenerType;
import com.p319ss.android.socialbase.downloader.constants.RetryDelayStatus;
import com.p319ss.android.socialbase.downloader.depend.IDownloadCompleteHandler;
import com.p319ss.android.socialbase.downloader.depend.IDownloadDepend;
import com.p319ss.android.socialbase.downloader.depend.IDownloadListener;
import com.p319ss.android.socialbase.downloader.depend.IDownloadMonitorDepend;
import com.p319ss.android.socialbase.downloader.depend.ITempFileSaveCompleteCallback;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.exception.DownloadPauseReserveWifiException;
import com.p319ss.android.socialbase.downloader.exception.DownloadRetryNeedlessException;
import com.p319ss.android.socialbase.downloader.impls.AbsDownloadEngine;
import com.p319ss.android.socialbase.downloader.impls.RetryScheduler;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.model.DownloadChunk;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.model.DownloadTask;
import com.p319ss.android.socialbase.downloader.monitor.DownloadMonitorHelper;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.utils.DownloadListenerUtils;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: com.ss.android.socialbase.downloader.downloader.DownloadStatusHandler */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DownloadStatusHandler {
    private static final String TAG = "DownloadStatusHandler";
    private final IDownloadCache downloadCache;
    private IDownloadDepend downloadDepend;
    private DownloadInfo downloadInfo;
    private IDownloadMonitorDepend downloadMonitorDepend;
    private DownloadTask downloadTask;
    private final boolean fixStartWithFileExistUpdateError;
    private final Handler mainThreadHandler;
    private SparseArray<IDownloadListener> mainThreadListeners;
    private long minByteIntervalForSyncProgress;
    private int minTimeIntervalForSyncProgress;
    private SparseArray<IDownloadListener> notificationListeners;
    private SparseArray<IDownloadListener> subThreadListeners;
    private boolean forceRefreshProcess = false;
    private volatile long lastSyncProgressTime = 0;
    private final AtomicLong msgPostDataBuffer = new AtomicLong();
    private boolean hasSyncProgressBefore = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ DownloadInfo access$000(DownloadStatusHandler downloadStatusHandler) {
        return downloadStatusHandler.downloadInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ IDownloadCache access$100(DownloadStatusHandler downloadStatusHandler) {
        return downloadStatusHandler.downloadCache;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void access$200(DownloadStatusHandler downloadStatusHandler, int i, BaseException baseException) {
        downloadStatusHandler.onStatusChanged(i, baseException);
    }

    public DownloadStatusHandler(DownloadTask downloadTask, Handler handler) {
        this.downloadTask = downloadTask;
        checkInit();
        this.mainThreadHandler = handler;
        this.downloadCache = DownloadComponentManager.getDownloadCache();
        DownloadInfo downloadInfo = downloadTask.getDownloadInfo();
        if (downloadInfo != null) {
            this.fixStartWithFileExistUpdateError = DownloadSetting.obtain(downloadInfo.getId()).optBugFix("fix_start_with_file_exist_update_error");
        } else {
            this.fixStartWithFileExistUpdateError = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkInit() {
        DownloadTask downloadTask = this.downloadTask;
        if (downloadTask != null) {
            this.downloadInfo = downloadTask.getDownloadInfo();
            this.mainThreadListeners = this.downloadTask.getDownloadListeners(ListenerType.MAIN);
            this.notificationListeners = this.downloadTask.getDownloadListeners(ListenerType.NOTIFICATION);
            this.subThreadListeners = this.downloadTask.getDownloadListeners(ListenerType.SUB);
            this.downloadDepend = this.downloadTask.getDepend();
            this.downloadMonitorDepend = this.downloadTask.getMonitorDepend();
        }
    }

    public void onPrepare() {
        if (this.downloadInfo.canSkipStatusHandler()) {
            return;
        }
        this.downloadInfo.setStatus(1);
        handlePrepare();
    }

    private void handlePrepare() {
        ExecutorService cPUThreadExecutor = DownloadComponentManager.getCPUThreadExecutor();
        if (cPUThreadExecutor != null) {
            cPUThreadExecutor.execute(new RunnableC102061());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ss.android.socialbase.downloader.downloader.DownloadStatusHandler$1 */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class RunnableC102061 implements Runnable {
        RunnableC102061() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DownloadStatusHandler.this.downloadCache.OnDownloadTaskPrepare(DownloadStatusHandler.this.downloadInfo.getId());
            DownloadStatusHandler.this.onStatusChanged(1, null);
        }
    }

    public void onStart() {
        if (this.downloadInfo.canSkipStatusHandler()) {
            this.downloadInfo.changeSkipStatus();
            return;
        }
        this.downloadCache.onDownloadTaskStart(this.downloadInfo.getId());
        if (this.downloadInfo.isFirstDownload()) {
            onStatusChanged(6, null);
        }
        onStatusChanged(2, null);
    }

    public void onFirstConnectionSuccessed(long j, String str, String str2) {
        this.downloadInfo.setTotalBytes(j);
        this.downloadInfo.seteTag(str);
        if (!TextUtils.isEmpty(str2) && TextUtils.isEmpty(this.downloadInfo.getName())) {
            this.downloadInfo.setName(str2);
        }
        try {
            this.downloadCache.OnDownloadTaskConnected(this.downloadInfo.getId(), j, str, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        onStatusChanged(3, null);
        this.minByteIntervalForSyncProgress = this.downloadInfo.getMinByteIntervalForPostToMainThread(j);
        this.minTimeIntervalForSyncProgress = this.downloadInfo.getMinProgressTimeMsInterval();
        this.forceRefreshProcess = true;
        RetryScheduler.getInstance().scheduleRetryWhenHasTaskConnected();
    }

    public boolean onProgress(long j) {
        this.msgPostDataBuffer.addAndGet(j);
        this.downloadInfo.increaseCurBytes(j);
        long uptimeMillis = SystemClock.uptimeMillis();
        return handleProgress(uptimeMillis, needSyncProgress(uptimeMillis));
    }

    public void onCancel() {
        onStatusChanged(-4, null);
    }

    public void onPause() {
        this.downloadInfo.setStatus(-2);
        try {
            this.downloadCache.OnDownloadTaskPause(this.downloadInfo.getId(), this.downloadInfo.getCurBytes());
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        onStatusChanged(-2, null);
    }

    public void onIntercept() {
        this.downloadInfo.setStatus(-7);
        try {
            this.downloadCache.OnDownloadTaskIntercept(this.downloadInfo.getId());
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        onStatusChanged(-7, null);
    }

    public void onRetry(BaseException baseException, boolean z) {
        this.downloadInfo.setFirstDownload(false);
        this.msgPostDataBuffer.set(0L);
        handleRetry(baseException, z);
    }

    public void onSingleChunkRetry(DownloadChunk downloadChunk, BaseException baseException, boolean z) {
        this.downloadInfo.setFirstDownload(false);
        this.msgPostDataBuffer.set(0L);
        this.downloadCache.OnDownloadTaskRetry(this.downloadInfo.getId());
        onStatusChanged(z ? 10 : 9, baseException, true);
    }

    public void onError(BaseException baseException) {
        this.downloadInfo.setFirstDownload(false);
        handleError(baseException);
    }

    private void handleError(BaseException baseException) {
        String str = TAG;
        Log.d(str, "handleError::" + baseException + " \r\n" + Log.getStackTraceString(new Throwable()));
        if (baseException != null && baseException.getCause() != null && (baseException.getCause() instanceof SQLiteFullException)) {
            try {
                this.downloadCache.removeDownloadTaskData(this.downloadInfo.getId());
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        } else {
            try {
                try {
                    this.downloadCache.OnDownloadTaskError(this.downloadInfo.getId(), this.downloadInfo.getCurBytes());
                } catch (SQLiteException e2) {
                    e2.printStackTrace();
                }
            } catch (SQLiteException unused) {
                this.downloadCache.removeDownloadTaskData(this.downloadInfo.getId());
            }
        }
        BaseException reviseFailedException = reviseFailedException(baseException);
        this.downloadInfo.setFailedException(reviseFailedException);
        onStatusChanged(reviseFailedException instanceof DownloadPauseReserveWifiException ? -2 : -1, reviseFailedException);
        if (DownloadSetting.obtain(this.downloadInfo.getId()).optInt("retry_schedule", 0) > 0) {
            RetryScheduler.getInstance().tryStartScheduleRetry(this.downloadInfo);
        }
    }

    private BaseException reviseFailedException(BaseException baseException) {
        Context appContext;
        if (DownloadSetting.obtain(this.downloadInfo.getId()).optInt("download_failed_check_net", 1) != 1 || !DownloadUtils.isNetworkError(baseException) || (appContext = DownloadComponentManager.getAppContext()) == null || DownloadUtils.isNetworkConnected(appContext)) {
            return baseException;
        }
        return new BaseException(this.downloadInfo.isOnlyWifi() ? 1013 : 1049, baseException.getErrorMessage());
    }

    public void onCompleted() {
        this.downloadInfo.setFirstDownload(false);
        if (!this.downloadInfo.isIgnoreDataVerify() && this.downloadInfo.getCurBytes() != this.downloadInfo.getTotalBytes()) {
            Logger.m6475d(TAG, this.downloadInfo.getErrorBytesLog());
            onError(new DownloadRetryNeedlessException(1027, "current bytes is not equals to total bytes, bytes changed with process : " + this.downloadInfo.getByteInvalidRetryStatus()));
        } else if (this.downloadInfo.getCurBytes() <= 0) {
            Logger.m6475d(TAG, this.downloadInfo.getErrorBytesLog());
            onError(new DownloadRetryNeedlessException(1026, "curBytes is 0, bytes changed with process : " + this.downloadInfo.getByteInvalidRetryStatus()));
        } else if (!this.downloadInfo.isIgnoreDataVerify() && this.downloadInfo.getTotalBytes() <= 0) {
            Logger.m6475d(TAG, this.downloadInfo.getErrorBytesLog());
            onError(new DownloadRetryNeedlessException(1044, "TotalBytes is 0, bytes changed with process : " + this.downloadInfo.getByteInvalidRetryStatus()));
        } else {
            String str = TAG;
            Logger.m6475d(str, "" + this.downloadInfo.getName() + " onCompleted start save file as target name");
            IDownloadMonitorDepend iDownloadMonitorDepend = this.downloadMonitorDepend;
            DownloadTask downloadTask = this.downloadTask;
            if (downloadTask != null) {
                iDownloadMonitorDepend = downloadTask.getMonitorDepend();
            }
            DownloadUtils.saveFileAsTargetName(this.downloadInfo, iDownloadMonitorDepend, new ITempFileSaveCompleteCallback() { // from class: com.ss.android.socialbase.downloader.downloader.DownloadStatusHandler.2
                @Override // com.p319ss.android.socialbase.downloader.depend.ITempFileSaveCompleteCallback
                public void onSuccess() {
                    DownloadStatusHandler.this.onSaveTempFileSuccess();
                }

                @Override // com.p319ss.android.socialbase.downloader.depend.ITempFileSaveCompleteCallback
                public void onFailed(BaseException baseException) {
                    String str2 = DownloadStatusHandler.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("saveFileAsTargetName onFailed : ");
                    sb.append(baseException != null ? baseException.getErrorMessage() : "");
                    Logger.m6475d(str2, sb.toString());
                    DownloadStatusHandler.this.onError(baseException);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSaveTempFileSuccess() {
        try {
            Logger.m6475d(TAG, "saveFileAsTargetName onSuccess");
            try {
                downloadCompleteProcess();
                this.downloadInfo.setFirstSuccess(false);
                this.downloadInfo.setSuccessByCache(false);
                onStatusChanged(-3, null);
                this.downloadCache.OnDownloadTaskCompleted(this.downloadInfo.getId(), this.downloadInfo.getTotalBytes());
                this.downloadCache.removeAllDownloadChunk(this.downloadInfo.getId());
                this.downloadCache.removeSegments(this.downloadInfo.getId());
            } catch (BaseException e) {
                onError(e);
            }
        } catch (Throwable th) {
            onError(new BaseException(1008, DownloadUtils.getErrorMsgWithTagPrefix(th, "onCompleted")));
        }
    }

    public void onCompleteForFileExist() throws BaseException {
        if (this.fixStartWithFileExistUpdateError) {
            downloadCompleteProcess();
            Logger.m6475d(TAG, "onCompleteForFileExist");
            this.downloadInfo.setSuccessByCache(true);
            onStatusChanged(-3, null);
            this.downloadCache.OnDownloadTaskCompleted(this.downloadInfo.getId(), this.downloadInfo.getTotalBytes());
            this.downloadCache.removeAllDownloadChunk(this.downloadInfo.getId());
            this.downloadCache.updateDownloadInfo(this.downloadInfo);
            this.downloadCache.removeSegments(this.downloadInfo.getId());
            return;
        }
        downloadCompleteProcess();
        Logger.m6475d(TAG, "onCompleteForFileExist");
        this.downloadInfo.setSuccessByCache(true);
        onStatusChanged(-3, null);
        this.downloadCache.OnDownloadTaskCompleted(this.downloadInfo.getId(), this.downloadInfo.getTotalBytes());
        this.downloadCache.removeAllDownloadChunk(this.downloadInfo.getId());
        this.downloadCache.removeSegments(this.downloadInfo.getId());
    }

    public void onCompleteForFileExist(String str) throws BaseException {
        String str2 = TAG;
        Logger.m6475d(str2, "onCompleteForFileExist existTargetFileName is " + str + " but curName is " + this.downloadInfo.getName());
        if (this.fixStartWithFileExistUpdateError) {
            DownloadUtils.copyFileFromExistFileWithSameName(this.downloadInfo, str);
            downloadCompleteProcess();
            this.downloadInfo.setSuccessByCache(true);
            onStatusChanged(-3, null);
            this.downloadCache.updateDownloadInfo(this.downloadInfo);
            return;
        }
        this.downloadCache.updateDownloadInfo(this.downloadInfo);
        DownloadUtils.copyFileFromExistFileWithSameName(this.downloadInfo, str);
        this.downloadInfo.setSuccessByCache(true);
        downloadCompleteProcess();
        onStatusChanged(-3, null);
    }

    private void downloadCompleteProcess() throws BaseException {
        List<IDownloadCompleteHandler> downloadCompleteHandlers = this.downloadTask.getDownloadCompleteHandlers();
        if (downloadCompleteHandlers.isEmpty()) {
            return;
        }
        DownloadInfo downloadInfo = this.downloadInfo;
        onStatusChanged(11, null);
        this.downloadCache.updateDownloadInfo(downloadInfo);
        for (IDownloadCompleteHandler iDownloadCompleteHandler : downloadCompleteHandlers) {
            try {
                if (iDownloadCompleteHandler.needHandle(downloadInfo)) {
                    iDownloadCompleteHandler.handle(downloadInfo);
                    this.downloadCache.updateDownloadInfo(downloadInfo);
                }
            } catch (BaseException e) {
                throw e;
            } catch (Throwable th) {
                throw new BaseException(1071, th);
            }
        }
    }

    private boolean handleProgress(long j, boolean z) {
        boolean z2 = false;
        if (this.downloadInfo.getCurBytes() == this.downloadInfo.getTotalBytes()) {
            try {
                this.downloadCache.OnDownloadTaskProgress(this.downloadInfo.getId(), this.downloadInfo.getCurBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
        if (this.forceRefreshProcess) {
            this.forceRefreshProcess = false;
            this.downloadInfo.setStatus(4);
        }
        if (this.downloadInfo.isNeedPostProgress() && z) {
            z2 = true;
        }
        onStatusChanged(4, null, z2);
        return z;
    }

    private boolean needSyncProgress(long j) {
        boolean z = true;
        if (!this.hasSyncProgressBefore) {
            this.hasSyncProgressBefore = true;
            return true;
        }
        long j2 = j - this.lastSyncProgressTime;
        if (this.msgPostDataBuffer.get() < this.minByteIntervalForSyncProgress && j2 < this.minTimeIntervalForSyncProgress) {
            z = false;
        }
        if (z) {
            this.lastSyncProgressTime = j;
            this.msgPostDataBuffer.set(0L);
        }
        return z;
    }

    public long getMinByteIntervalForSyncCache() {
        return this.minByteIntervalForSyncProgress / this.downloadInfo.getChunkCount();
    }

    public int getMinTimeIntervalForSyncCache() {
        return this.minTimeIntervalForSyncProgress / this.downloadInfo.getChunkCount();
    }

    private void handleRetry(BaseException baseException, boolean z) {
        this.downloadCache.OnDownloadTaskRetry(this.downloadInfo.getId());
        onStatusChanged(z ? 7 : 5, baseException);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStatusChanged(int i, BaseException baseException) {
        onStatusChanged(i, baseException, true);
    }

    public void handleWaitingAsyncHandler() {
        this.downloadInfo.setStatus(8);
        this.downloadInfo.setAsyncHandleStatus(AsyncHandleStatus.ASYNC_HANDLE_WAITING);
        AbsDownloadEngine downloadEngine = DownloadComponentManager.getDownloadEngine();
        if (downloadEngine != null) {
            downloadEngine.refreshDownloadTaskMap(this.downloadInfo.getId(), this.downloadTask.getHashCodeForSameTask(), 8);
        }
    }

    private void onStatusChanged(int i, BaseException baseException, boolean z) {
        SparseArray<IDownloadListener> sparseArray;
        SparseArray<IDownloadListener> sparseArray2;
        int status = this.downloadInfo.getStatus();
        if (status == -3 && i == 4) {
            return;
        }
        checkInit();
        if (i != 4 && DownloadStatus.isRealTimeUploadStatus(i)) {
            this.downloadInfo.updateRealDownloadTime(false);
            if (DownloadStatus.isTimeUploadStatus(i)) {
                this.downloadInfo.updateDownloadTime();
            }
        }
        if (!this.downloadInfo.isAddListenerToSameTask()) {
            DownloadMonitorHelper.monitorSend(this.downloadTask, baseException, i);
        }
        if (i == 6) {
            this.downloadInfo.setStatus(2);
        } else if (i == -6) {
            this.downloadInfo.setStatus(-3);
        } else {
            this.downloadInfo.setStatus(i);
        }
        if (status == -3 || status == -1) {
            if (this.downloadInfo.getRetryDelayStatus() == RetryDelayStatus.DELAY_RETRY_DOWNLOADING) {
                this.downloadInfo.setRetryDelayStatus(RetryDelayStatus.DELAY_RETRY_DOWNLOADED);
            }
            if (this.downloadInfo.getAsyncHandleStatus() == AsyncHandleStatus.ASYNC_HANDLE_DOWNLOADING) {
                this.downloadInfo.setAsyncHandleStatus(AsyncHandleStatus.ASYNC_HANDLE_DOWNLOADED);
            }
            if (this.downloadInfo.getByteInvalidRetryStatus() == ByteInvalidRetryStatus.BYTE_INVALID_RETRY_STATUS_DOWNLOADING) {
                this.downloadInfo.setByteInvalidRetryStatus(ByteInvalidRetryStatus.BYTE_INVALID_RETRY_STATUS_DOWNLOADED);
            }
        }
        DownloadListenerUtils.notifyListener(i, this.subThreadListeners, true, this.downloadInfo, baseException);
        if (i == -4) {
            return;
        }
        if (z && this.mainThreadHandler != null && (((sparseArray = this.mainThreadListeners) != null && sparseArray.size() > 0) || ((sparseArray2 = this.notificationListeners) != null && sparseArray2.size() > 0 && (this.downloadInfo.canShowNotification() || this.downloadInfo.isAutoInstallWithoutNotification())))) {
            this.mainThreadHandler.obtainMessage(i, this.downloadInfo.getId(), this.downloadTask.getHashCodeForSameTask(), baseException).sendToTarget();
            return;
        }
        AbsDownloadEngine downloadEngine = DownloadComponentManager.getDownloadEngine();
        if (downloadEngine != null) {
            downloadEngine.refreshDownloadTaskMap(this.downloadInfo.getId(), this.downloadTask.getHashCodeForSameTask(), i);
        }
    }
}
