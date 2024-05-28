package com.liulishuo.filedownloader;

import android.support.annotation.Nullable;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.ITaskHunter;
import com.liulishuo.filedownloader.progress.ProgressAssist;
import com.liulishuo.filedownloader.retry.RetryAssist;
import com.liulishuo.filedownloader.status.StatusAssist;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.OkDownload;
import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DownloadTaskAdapter implements BaseDownloadTask, BaseDownloadTask.IRunningTask {
    private static final int DEFAULT_CALLBACK_PROGRESS_COUNT = 100;
    static final int DEFAULT_CALLBACK_PROGRESS_MIN_INTERVAL_MILLIS = 10;
    public static final int KEY_TASK_ADAPTER = Integer.MIN_VALUE;
    private static final String TAG = "DownloadTaskAdapter";
    volatile int attachKey;
    private int autoRetryTimes;
    private CompatListenerAdapter compatListener;
    private DownloadTask downloadTask;
    volatile boolean isAddedToList;
    FileDownloadListener listener;
    ProgressAssist progressAssist;
    RetryAssist retryAssist;
    private List<BaseDownloadTask.FinishListener> finishListeners = new ArrayList();
    private int callbackProgressCount = 100;
    StatusAssist statusAssist = new StatusAssist();
    Builder builder = new Builder();

    @Override // com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask
    public void free() {
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask
    public ITaskHunter.IMessageHandler getMessageHandler() {
        return null;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask
    public BaseDownloadTask getOrigin() {
        return this;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask
    @Nullable
    public Object getPauseLock() {
        return null;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public int getSpeed() {
        return 0;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public BaseDownloadTask setMinIntervalUpdateSpeed(int i) {
        return this;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask
    public void startTaskByQueue() {
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask
    public void startTaskByRescue() {
    }

    public DownloadTaskAdapter(String str) {
        this.builder.url = str;
    }

    public ProgressAssist getProgressAssist() {
        return this.progressAssist;
    }

    public RetryAssist getRetryAssist() {
        return this.retryAssist;
    }

    public CompatListenerAdapter getCompatListener() {
        return this.compatListener;
    }

    public DownloadTask getDownloadTask() {
        return this.downloadTask;
    }

    public List<BaseDownloadTask.FinishListener> getFinishListeners() {
        return this.finishListeners;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public BaseDownloadTask setPath(String str) {
        this.builder.path = str;
        return this;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public BaseDownloadTask setPath(String str, boolean z) {
        Builder builder = this.builder;
        builder.path = str;
        builder.pathAsDirectory = z;
        return this;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public BaseDownloadTask setListener(FileDownloadListener fileDownloadListener) {
        this.listener = fileDownloadListener;
        return this;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public BaseDownloadTask setCallbackProgressTimes(int i) {
        this.callbackProgressCount = i;
        return this;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public BaseDownloadTask setCallbackProgressMinInterval(int i) {
        this.builder.minIntervalMillisCallbackProgress = i;
        return this;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public BaseDownloadTask setCallbackProgressIgnored() {
        setCallbackProgressTimes(-1);
        return this;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public BaseDownloadTask setTag(Object obj) {
        this.builder.tag = obj;
        return this;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public BaseDownloadTask setTag(int i, Object obj) {
        if (i == Integer.MIN_VALUE) {
            throw new IllegalArgumentException(i + " is used internally, please use another key");
        }
        this.builder.keyOfTag = Integer.valueOf(i);
        this.builder.tagWithKey = obj;
        return this;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public BaseDownloadTask setForceReDownload(boolean z) {
        this.builder.forceReDownload = z;
        return this;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public BaseDownloadTask setFinishListener(BaseDownloadTask.FinishListener finishListener) {
        addFinishListener(finishListener);
        return this;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public BaseDownloadTask addFinishListener(BaseDownloadTask.FinishListener finishListener) {
        if (finishListener == null || this.finishListeners.contains(finishListener)) {
            return this;
        }
        this.finishListeners.add(finishListener);
        return this;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public boolean removeFinishListener(BaseDownloadTask.FinishListener finishListener) {
        return this.finishListeners.remove(finishListener);
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public BaseDownloadTask setAutoRetryTimes(int i) {
        this.autoRetryTimes = i;
        return this;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public BaseDownloadTask addHeader(String str, String str2) {
        this.builder.headerMap.put(str, str2);
        return this;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public BaseDownloadTask addHeader(String str) {
        String[] split = str.split(":");
        addHeader(split[0].trim(), split[1].trim());
        return this;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public BaseDownloadTask removeAllHeaders(String str) {
        this.builder.headerMap.remove(str);
        return this;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public BaseDownloadTask setSyncCallback(boolean z) {
        this.builder.autoCallbackToUIThread = !z;
        return this;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public BaseDownloadTask setWifiRequired(boolean z) {
        this.builder.isWifiRequired = z;
        return this;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    @Deprecated
    public int ready() {
        return asInQueueTask().enqueue();
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public BaseDownloadTask.InQueueTask asInQueueTask() {
        return new InQueueTaskImpl(this);
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public boolean reuse() {
        if (isRunning()) {
            Util.m13738w(TAG, "This task[" + getId() + "] is running, if you want start the same task, please create a new one by FileDownloader#create");
            return false;
        }
        this.attachKey = 0;
        this.isAddedToList = false;
        return true;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public boolean isUsing() {
        return this.statusAssist.isUsing();
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public boolean isRunning() {
        return OkDownload.with().downloadDispatcher().isRunning(this.downloadTask);
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public boolean isAttached() {
        return this.attachKey != 0;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public int start() {
        assembleDownloadTask();
        FileDownloadList.getImpl().addIndependentTask(this);
        this.downloadTask.enqueue(this.compatListener);
        return this.downloadTask.getId();
    }

    public void assembleDownloadTask() {
        this.downloadTask = this.builder.build();
        int i = this.autoRetryTimes;
        if (i > 0) {
            this.retryAssist = new RetryAssist(i);
        }
        this.progressAssist = new ProgressAssist(this.callbackProgressCount);
        this.compatListener = CompatListenerAdapter.create(this.listener);
        this.statusAssist.setDownloadTask(this.downloadTask);
        this.downloadTask.addTag(Integer.MIN_VALUE, this);
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public boolean pause() {
        return cancel();
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public boolean cancel() {
        return OkDownload.with().downloadDispatcher().cancel(this.downloadTask);
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public int getId() {
        DownloadTask downloadTask = this.downloadTask;
        if (downloadTask != null) {
            return downloadTask.getId();
        }
        return -1;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public int getDownloadId() {
        return getId();
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public String getUrl() {
        return this.downloadTask.getUrl();
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public int getCallbackProgressTimes() {
        return this.callbackProgressCount;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public int getCallbackProgressMinInterval() {
        return this.downloadTask.getMinIntervalMillisCallbackProcess();
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public String getPath() {
        return this.builder.path;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public boolean isPathAsDirectory() {
        return this.builder.pathAsDirectory;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public String getFilename() {
        return this.downloadTask.getFilename();
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public String getTargetFilePath() {
        File file = this.downloadTask.getFile();
        if (file != null) {
            return file.getPath();
        }
        return null;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public FileDownloadListener getListener() {
        return this.listener;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public int getSoFarBytes() {
        return (int) getSoFarBytesInLong();
    }

    public long getSoFarBytesInLong() {
        BreakpointInfo info = this.downloadTask.getInfo();
        if (info != null) {
            return info.getTotalOffset();
        }
        return 0L;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public int getSmallFileSoFarBytes() {
        return (int) getLargeFileSoFarBytes();
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public long getLargeFileSoFarBytes() {
        ProgressAssist progressAssist = this.progressAssist;
        if (progressAssist == null) {
            return 0L;
        }
        return progressAssist.getSofarBytes();
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public int getTotalBytes() {
        return (int) getTotalBytesInLong();
    }

    public long getTotalBytesInLong() {
        BreakpointInfo info = this.downloadTask.getInfo();
        if (info != null) {
            return info.getTotalLength();
        }
        return 0L;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public int getSmallFileTotalBytes() {
        return (int) getLargeFileTotalBytes();
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public long getLargeFileTotalBytes() {
        BreakpointInfo info = this.downloadTask.getInfo();
        if (info != null) {
            return info.getTotalLength();
        }
        return 0L;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public byte getStatus() {
        return this.statusAssist.getStatus();
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public boolean isForceReDownload() {
        return this.builder.forceReDownload;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public Throwable getEx() {
        return this.compatListener.getListenerAssist().getException();
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public Throwable getErrorCause() {
        return getEx();
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public boolean isReusedOldFile() {
        return this.compatListener.getListenerAssist().isReuseOldFile();
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public Object getTag() {
        return this.downloadTask.getTag();
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public Object getTag(int i) {
        return this.downloadTask.getTag(i);
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public boolean isContinue() {
        return isResuming();
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public boolean isResuming() {
        return this.compatListener.getListenerAssist().isResumable();
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public String getEtag() {
        return this.compatListener.getListenerAssist().getEtag();
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public int getAutoRetryTimes() {
        return this.autoRetryTimes;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public int getRetryingTimes() {
        RetryAssist retryAssist = this.retryAssist;
        if (retryAssist != null) {
            return retryAssist.getRetriedTimes() + 1;
        }
        return 0;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public boolean isSyncCallback() {
        return !this.downloadTask.isAutoCallbackToUIThread();
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public boolean isLargeFile() {
        return this.listener instanceof FileDownloadLargeFileListener;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask
    public boolean isWifiRequired() {
        return this.downloadTask.isWifiRequired();
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask
    /* renamed from: is */
    public boolean mo13743is(int i) {
        return getId() == i;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask
    /* renamed from: is */
    public boolean mo13742is(FileDownloadListener fileDownloadListener) {
        return this.listener == fileDownloadListener;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask
    public boolean isOver() {
        return this.statusAssist.isOver();
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask
    public int getAttachKey() {
        return this.attachKey;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask
    public void setAttachKeyByQueue(int i) {
        this.attachKey = i;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask
    public void setAttachKeyDefault() {
        int hashCode;
        if (getListener() != null) {
            hashCode = getListener().hashCode();
        } else {
            hashCode = hashCode();
        }
        this.attachKey = hashCode;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask
    public boolean isMarkedAdded2List() {
        return this.isAddedToList;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask
    public void markAdded2List() {
        this.isAddedToList = true;
    }

    @Override // com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask
    public boolean isContainFinishListener() {
        return !this.finishListeners.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static final class Builder {
        private boolean forceReDownload;
        private boolean isWifiRequired;
        private Integer keyOfTag;
        String path;
        boolean pathAsDirectory;
        private Object tag;
        private Object tagWithKey;
        private String url;
        private int minIntervalMillisCallbackProgress = 10;
        Map<String, String> headerMap = new HashMap();
        private boolean autoCallbackToUIThread = true;

        Builder() {
        }

        DownloadTask build() {
            DownloadTask.Builder builder;
            if (this.path == null) {
                this.path = FileDownloadUtils.getDefaultSaveFilePath(this.url);
            }
            if (this.pathAsDirectory) {
                builder = new DownloadTask.Builder(this.url, this.path, null);
            } else {
                builder = new DownloadTask.Builder(this.url, new File(this.path));
            }
            builder.setMinIntervalMillisCallbackProcess(this.minIntervalMillisCallbackProgress);
            builder.setPassIfAlreadyCompleted(!this.forceReDownload);
            builder.setWifiRequired(this.isWifiRequired);
            for (Map.Entry<String, String> entry : this.headerMap.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
            builder.setAutoCallbackToUIThread(this.autoCallbackToUIThread);
            DownloadTask build = builder.build();
            Object obj = this.tag;
            if (obj != null) {
                build.setTag(obj);
            }
            Integer num = this.keyOfTag;
            if (num != null) {
                build.addTag(num.intValue(), this.tagWithKey);
            }
            return build;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static final class InQueueTaskImpl implements BaseDownloadTask.InQueueTask {
        final DownloadTaskAdapter downloadTaskAdapter;

        InQueueTaskImpl(DownloadTaskAdapter downloadTaskAdapter) {
            this.downloadTaskAdapter = downloadTaskAdapter;
        }

        @Override // com.liulishuo.filedownloader.BaseDownloadTask.InQueueTask
        public int enqueue() {
            FileDownloadList.getImpl().addQueueTask(this.downloadTaskAdapter);
            return this.downloadTaskAdapter.getId();
        }
    }
}
