package com.p319ss.android.socialbase.downloader.thread;

import android.util.SparseArray;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.model.DownloadTask;
import com.p319ss.android.socialbase.downloader.monitor.DownloadMonitorHelper;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.ss.android.socialbase.downloader.thread.DownloadThreadPool */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DownloadThreadPool {
    private static ExecutorService executorService = new ThreadPoolExecutor(2, 2, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new DefaultThreadFactory("Download_OP_Thread"));
    private int clearTimes = 0;
    private volatile SparseArray<DownloadRunnable> downloadRunnablePool = new SparseArray<>();

    public static void executeOP(Runnable runnable) {
        executorService.execute(runnable);
    }

    public void execute(DownloadRunnable downloadRunnable) {
        downloadRunnable.prepareDownload();
        synchronized (DownloadThreadPool.class) {
            if (this.clearTimes >= 500) {
                clearRunnableNotAlive();
                this.clearTimes = 0;
            } else {
                this.clearTimes++;
            }
            this.downloadRunnablePool.put(downloadRunnable.getDownloadId(), downloadRunnable);
        }
        DownloadTask downloadTask = downloadRunnable.getDownloadTask();
        try {
            ExecutorService mixDefaultThreadExecutor = DownloadComponentManager.getMixDefaultThreadExecutor();
            if (downloadTask != null && downloadTask.getDownloadInfo() != null) {
                if ("mime_type_plg".equals(downloadTask.getDownloadInfo().getMimeType()) && DownloadSetting.obtainGlobal().optInt("divide_plugin", 1) == 1) {
                    downloadTask.getDownloadInfo().safePutToDBJsonData("executor_group", 3);
                }
                switch (downloadTask.getDownloadInfo().getExecutorGroup()) {
                    case 3:
                        mixDefaultThreadExecutor = DownloadComponentManager.getMixFrequentThreadExecutor();
                        break;
                    case 4:
                        mixDefaultThreadExecutor = DownloadComponentManager.getMixApkThreadExecutor();
                        break;
                }
            }
            if (mixDefaultThreadExecutor != null) {
                if (DownloadSetting.obtain(downloadRunnable.getDownloadId()).optBugFix("pause_with_interrupt", false)) {
                    downloadRunnable.setFuture(mixDefaultThreadExecutor.submit(downloadRunnable));
                    return;
                } else {
                    mixDefaultThreadExecutor.execute(downloadRunnable);
                    return;
                }
            }
            DownloadMonitorHelper.monitorSendWithTaskMonitor(downloadTask.getMonitorDepend(), downloadTask.getDownloadInfo(), new BaseException(1003, "execute failed cpu thread executor service is null"), downloadTask.getDownloadInfo() != null ? downloadTask.getDownloadInfo().getStatus() : 0);
        } catch (Exception e) {
            if (downloadTask != null) {
                DownloadMonitorHelper.monitorSendWithTaskMonitor(downloadTask.getMonitorDepend(), downloadTask.getDownloadInfo(), new BaseException(1003, DownloadUtils.getErrorMsgWithTagPrefix(e, "DownloadThreadPoolExecute")), downloadTask.getDownloadInfo() != null ? downloadTask.getDownloadInfo().getStatus() : 0);
            }
            e.printStackTrace();
        } catch (OutOfMemoryError e2) {
            if (downloadTask != null) {
                DownloadMonitorHelper.monitorSendWithTaskMonitor(downloadTask.getMonitorDepend(), downloadTask.getDownloadInfo(), new BaseException(1003, "execute OOM"), downloadTask.getDownloadInfo() != null ? downloadTask.getDownloadInfo().getStatus() : 0);
            }
            e2.printStackTrace();
        }
    }

    private void clearRunnableNotAlive() {
        try {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < this.downloadRunnablePool.size(); i++) {
                int keyAt = this.downloadRunnablePool.keyAt(i);
                if (!this.downloadRunnablePool.get(keyAt).isAlive()) {
                    arrayList.add(Integer.valueOf(keyAt));
                }
            }
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                Integer num = (Integer) arrayList.get(i2);
                if (num != null) {
                    this.downloadRunnablePool.remove(num.intValue());
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void removeUnAliveDownloadRunnable(DownloadRunnable downloadRunnable) {
        if (downloadRunnable == null) {
            return;
        }
        synchronized (DownloadThreadPool.class) {
            try {
                if (DownloadExpSwitchCode.isSwitchEnable(524288)) {
                    int indexOfValue = this.downloadRunnablePool.indexOfValue(downloadRunnable);
                    if (indexOfValue >= 0) {
                        this.downloadRunnablePool.removeAt(indexOfValue);
                    }
                } else {
                    this.downloadRunnablePool.remove(downloadRunnable.getDownloadId());
                }
            }
        }
    }

    public boolean containsTask(int i) {
        synchronized (DownloadThreadPool.class) {
            boolean z = false;
            if (this.downloadRunnablePool != null && this.downloadRunnablePool.size() > 0) {
                DownloadRunnable downloadRunnable = this.downloadRunnablePool.get(i);
                if (downloadRunnable != null && downloadRunnable.isAlive()) {
                    z = true;
                }
                return z;
            }
            return false;
        }
    }

    public DownloadRunnable cancel(int i) {
        synchronized (DownloadThreadPool.class) {
            clearRunnableNotAlive();
            DownloadRunnable downloadRunnable = this.downloadRunnablePool.get(i);
            if (downloadRunnable != null) {
                downloadRunnable.cancel();
                removeFromThreadPool(downloadRunnable);
                this.downloadRunnablePool.remove(i);
                return downloadRunnable;
            }
            return null;
        }
    }

    public void pause(int i) {
        synchronized (DownloadThreadPool.class) {
            clearRunnableNotAlive();
            DownloadRunnable downloadRunnable = this.downloadRunnablePool.get(i);
            if (downloadRunnable != null) {
                downloadRunnable.pause();
                removeFromThreadPool(downloadRunnable);
                this.downloadRunnablePool.remove(i);
            }
        }
    }

    private void removeFromThreadPool(DownloadRunnable downloadRunnable) {
        Future future;
        if (downloadRunnable == null) {
            return;
        }
        try {
            ExecutorService mixDefaultThreadExecutor = DownloadComponentManager.getMixDefaultThreadExecutor();
            DownloadTask downloadTask = downloadRunnable.getDownloadTask();
            if (downloadTask != null && downloadTask.getDownloadInfo() != null) {
                switch (downloadTask.getDownloadInfo().getExecutorGroup()) {
                    case 3:
                        mixDefaultThreadExecutor = DownloadComponentManager.getMixFrequentThreadExecutor();
                        break;
                    case 4:
                        mixDefaultThreadExecutor = DownloadComponentManager.getMixApkThreadExecutor();
                        break;
                }
            }
            if (mixDefaultThreadExecutor == null || !(mixDefaultThreadExecutor instanceof ThreadPoolExecutor)) {
                return;
            }
            ((ThreadPoolExecutor) mixDefaultThreadExecutor).remove(downloadRunnable);
            if (!DownloadSetting.obtain(downloadRunnable.getDownloadId()).optBugFix("pause_with_interrupt", false) || (future = downloadRunnable.getFuture()) == null) {
                return;
            }
            future.cancel(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Integer> getAllAliveDownloadIds() {
        ArrayList arrayList;
        synchronized (DownloadThreadPool.class) {
            clearRunnableNotAlive();
            arrayList = new ArrayList();
            for (int i = 0; i < this.downloadRunnablePool.size(); i++) {
                DownloadRunnable downloadRunnable = this.downloadRunnablePool.get(this.downloadRunnablePool.keyAt(i));
                if (downloadRunnable != null) {
                    arrayList.add(Integer.valueOf(downloadRunnable.getDownloadId()));
                }
            }
        }
        return arrayList;
    }

    public void setThrottleNetSpeed(int i, long j) {
        DownloadRunnable downloadRunnable = this.downloadRunnablePool.get(i);
        if (downloadRunnable != null) {
            downloadRunnable.setThrottleNetSpeed(j);
        }
    }
}
