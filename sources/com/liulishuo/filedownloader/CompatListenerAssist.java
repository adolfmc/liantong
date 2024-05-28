package com.liulishuo.filedownloader;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.exception.FileDownloadNetworkPolicyException;
import com.liulishuo.filedownloader.exception.FileDownloadOutOfSpaceException;
import com.liulishuo.filedownloader.exception.FileDownloadSecurityException;
import com.liulishuo.filedownloader.retry.RetryAssist;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.exception.DownloadSecurityException;
import com.liulishuo.okdownload.core.exception.NetworkPolicyException;
import com.liulishuo.okdownload.core.exception.PreAllocateException;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CompatListenerAssist {
    private static final Executor EXECUTOR = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkDownload Block Complete", false));
    private static final String TAG = "CompatListenerAssist";
    @NonNull
    private final CompatListenerAssistCallback callback;
    private String etag;
    @Nullable
    private Exception exception;
    private boolean resumable;
    private boolean reuseOldFile;
    @NonNull
    final AtomicBoolean taskConnected;
    @NonNull
    private final Handler uiHandler;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface CompatListenerAssistCallback {
        void blockComplete(BaseDownloadTask baseDownloadTask) throws Throwable;

        void completed(BaseDownloadTask baseDownloadTask);

        void connected(BaseDownloadTask baseDownloadTask, String str, boolean z, long j, long j2);

        void error(BaseDownloadTask baseDownloadTask, Throwable th);

        void paused(BaseDownloadTask baseDownloadTask, long j, long j2);

        void pending(BaseDownloadTask baseDownloadTask, long j, long j2);

        void progress(BaseDownloadTask baseDownloadTask, long j, long j2);

        void retry(BaseDownloadTask baseDownloadTask, Throwable th, int i, long j);

        void started(BaseDownloadTask baseDownloadTask);

        void warn(BaseDownloadTask baseDownloadTask);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CompatListenerAssist(@NonNull CompatListenerAssistCallback compatListenerAssistCallback) {
        this(compatListenerAssistCallback, new Handler(Looper.getMainLooper()));
    }

    CompatListenerAssist(@NonNull CompatListenerAssistCallback compatListenerAssistCallback, @NonNull Handler handler) {
        this.callback = compatListenerAssistCallback;
        this.taskConnected = new AtomicBoolean(false);
        this.uiHandler = handler;
    }

    public void taskStart(@NonNull DownloadTask downloadTask) {
        DownloadTaskAdapter findDownloadTaskAdapter = FileDownloadUtils.findDownloadTaskAdapter(downloadTask);
        if (findDownloadTaskAdapter == null) {
            return;
        }
        this.callback.pending(findDownloadTaskAdapter, findDownloadTaskAdapter.getSoFarBytesInLong(), findDownloadTaskAdapter.getTotalBytesInLong());
        this.callback.started(findDownloadTaskAdapter);
    }

    public void setResumable(boolean z) {
        this.resumable = z;
    }

    public boolean isResumable() {
        return this.resumable;
    }

    public void setEtag(String str) {
        this.etag = str;
    }

    public String getEtag() {
        return this.etag;
    }

    public void connectStart(DownloadTask downloadTask) {
        DownloadTaskAdapter findDownloadTaskAdapter;
        if (!this.taskConnected.compareAndSet(false, true) || (findDownloadTaskAdapter = FileDownloadUtils.findDownloadTaskAdapter(downloadTask)) == null) {
            return;
        }
        long soFarBytesInLong = findDownloadTaskAdapter.getSoFarBytesInLong();
        long totalBytesInLong = findDownloadTaskAdapter.getTotalBytesInLong();
        findDownloadTaskAdapter.getProgressAssist().initSofarBytes(soFarBytesInLong);
        findDownloadTaskAdapter.getProgressAssist().calculateCallbackMinIntervalBytes(totalBytesInLong);
        this.callback.connected(findDownloadTaskAdapter, this.etag, this.resumable, soFarBytesInLong, totalBytesInLong);
    }

    public void fetchProgress(@NonNull DownloadTask downloadTask, long j) {
        DownloadTaskAdapter findDownloadTaskAdapter = FileDownloadUtils.findDownloadTaskAdapter(downloadTask);
        if (findDownloadTaskAdapter == null) {
            return;
        }
        findDownloadTaskAdapter.getProgressAssist().onProgress(findDownloadTaskAdapter, j, this.callback);
    }

    public void taskEnd(@NonNull DownloadTask downloadTask, @NonNull EndCause endCause, @Nullable Exception exc) {
        DownloadTaskAdapter findDownloadTaskAdapter = FileDownloadUtils.findDownloadTaskAdapter(downloadTask);
        if (findDownloadTaskAdapter == null) {
            return;
        }
        findDownloadTaskAdapter.getProgressAssist().clearProgress();
        this.exception = exc;
        switch (endCause) {
            case PRE_ALLOCATE_FAILED:
            case ERROR:
                handleError(findDownloadTaskAdapter, exc);
                break;
            case CANCELED:
                handleCanceled(findDownloadTaskAdapter);
                break;
            case FILE_BUSY:
            case SAME_TASK_BUSY:
                handleWarn(findDownloadTaskAdapter, endCause, exc);
                break;
            case COMPLETED:
                handleComplete(findDownloadTaskAdapter);
                break;
        }
        onTaskFinish(findDownloadTaskAdapter);
    }

    void handleWarn(@NonNull DownloadTaskAdapter downloadTaskAdapter, EndCause endCause, Exception exc) {
        Util.m13738w(TAG, "handle warn, cause: " + endCause + "real cause: " + exc);
        this.callback.warn(downloadTaskAdapter);
    }

    void handleCanceled(@NonNull DownloadTaskAdapter downloadTaskAdapter) {
        this.callback.paused(downloadTaskAdapter, downloadTaskAdapter.getProgressAssist().getSofarBytes(), downloadTaskAdapter.getTotalBytesInLong());
    }

    void handleError(@NonNull DownloadTaskAdapter downloadTaskAdapter, @Nullable Exception exc) {
        Throwable th;
        RetryAssist retryAssist = downloadTaskAdapter.getRetryAssist();
        if (retryAssist != null && retryAssist.canRetry()) {
            Log.d(TAG, "handle retry " + Thread.currentThread().getName());
            this.callback.retry(downloadTaskAdapter, exc, retryAssist.getRetriedTimes() + 1, downloadTaskAdapter.getProgressAssist().getSofarBytes());
            retryAssist.doRetry(downloadTaskAdapter.getDownloadTask());
            return;
        }
        Log.d(TAG, "handle error");
        if (exc instanceof NetworkPolicyException) {
            th = new FileDownloadNetworkPolicyException();
        } else if (exc instanceof PreAllocateException) {
            PreAllocateException preAllocateException = (PreAllocateException) exc;
            th = new FileDownloadOutOfSpaceException(preAllocateException.getFreeSpace(), preAllocateException.getRequireSpace(), downloadTaskAdapter.getProgressAssist().getSofarBytes(), preAllocateException);
        } else if (exc instanceof DownloadSecurityException) {
            th = new FileDownloadSecurityException(exc.getMessage());
        } else {
            th = new Throwable(exc);
        }
        this.callback.error(downloadTaskAdapter, th);
    }

    void onTaskFinish(@NonNull DownloadTaskAdapter downloadTaskAdapter) {
        Util.m13741d(TAG, "on task finish, have finish listener: " + downloadTaskAdapter.isContainFinishListener());
        for (BaseDownloadTask.FinishListener finishListener : downloadTaskAdapter.getFinishListeners()) {
            finishListener.over(downloadTaskAdapter);
        }
        FileDownloadList.getImpl().remove(downloadTaskAdapter);
    }

    void handleComplete(@NonNull final DownloadTaskAdapter downloadTaskAdapter) {
        this.reuseOldFile = !this.taskConnected.get();
        if (downloadTaskAdapter.getDownloadTask().isAutoCallbackToUIThread()) {
            EXECUTOR.execute(new Runnable() { // from class: com.liulishuo.filedownloader.CompatListenerAssist.1
                @Override // java.lang.Runnable
                public void run() {
                    CompatListenerAssist.this.handleBlockComplete(downloadTaskAdapter);
                }
            });
            return;
        }
        try {
            this.callback.blockComplete(downloadTaskAdapter);
            this.callback.completed(downloadTaskAdapter);
        } catch (Throwable th) {
            handleError(downloadTaskAdapter, new Exception(th));
        }
    }

    void handleBlockComplete(@NonNull final DownloadTaskAdapter downloadTaskAdapter) {
        try {
            this.callback.blockComplete(downloadTaskAdapter);
            this.uiHandler.post(new Runnable() { // from class: com.liulishuo.filedownloader.CompatListenerAssist.2
                @Override // java.lang.Runnable
                public void run() {
                    CompatListenerAssist.this.callback.completed(downloadTaskAdapter);
                }
            });
        } catch (Throwable th) {
            this.uiHandler.post(new Runnable() { // from class: com.liulishuo.filedownloader.CompatListenerAssist.3
                @Override // java.lang.Runnable
                public void run() {
                    CompatListenerAssist.this.handleError(downloadTaskAdapter, new Exception(th));
                }
            });
        }
    }

    @Nullable
    public Exception getException() {
        return this.exception;
    }

    public boolean isReuseOldFile() {
        return this.reuseOldFile;
    }
}
