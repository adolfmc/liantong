package com.p319ss.android.socialbase.downloader.impls;

import com.p319ss.android.socialbase.downloader.constants.DownloadStatus;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.model.DownloadTask;
import com.p319ss.android.socialbase.downloader.thread.DownloadRunnable;
import com.p319ss.android.socialbase.downloader.thread.DownloadThreadPool;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/* renamed from: com.ss.android.socialbase.downloader.impls.DefaultDownloadEngine */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DefaultDownloadEngine extends AbsDownloadEngine {
    private static final String TAG = "DefaultDownloadEngine";
    private static DownloadThreadPool downloadThreadPool;

    public static void invokeFutureTasks(List<Callable<Object>> list) throws InterruptedException {
        ExecutorService chunkDownloadThreadExecutorService = DownloadComponentManager.getChunkDownloadThreadExecutorService();
        if (chunkDownloadThreadExecutorService != null) {
            chunkDownloadThreadExecutorService.invokeAll(list);
        }
    }

    public static List<Future> executeFutureTasks(List<Runnable> list) {
        ExecutorService chunkDownloadThreadExecutorService = DownloadComponentManager.getChunkDownloadThreadExecutorService();
        ArrayList arrayList = new ArrayList(list.size());
        for (Runnable runnable : list) {
            arrayList.add(chunkDownloadThreadExecutorService.submit(runnable));
        }
        return arrayList;
    }

    public DefaultDownloadEngine() {
        downloadThreadPool = new DownloadThreadPool();
    }

    public static Runnable getUnstartedTask(List<Future> list) {
        BlockingQueue<Runnable> queue;
        Runnable runnable;
        if (list == null || list.isEmpty()) {
            return null;
        }
        try {
            ExecutorService chunkDownloadThreadExecutorService = DownloadComponentManager.getChunkDownloadThreadExecutorService();
            if ((chunkDownloadThreadExecutorService instanceof ThreadPoolExecutor) && (queue = ((ThreadPoolExecutor) chunkDownloadThreadExecutorService).getQueue()) != null && !queue.isEmpty()) {
                Iterator<Future> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        runnable = null;
                        break;
                    }
                    Future next = it.next();
                    if ((next instanceof Runnable) && queue.remove(next)) {
                        runnable = (Runnable) next;
                        break;
                    }
                }
                if (runnable != null) {
                    list.remove(runnable);
                    return runnable;
                }
            }
        } catch (Throwable th) {
            Logger.m6460w(TAG, "getUnstartedTask() error: " + th.toString());
        }
        return null;
    }

    @Override // com.p319ss.android.socialbase.downloader.impls.AbsDownloadEngine
    public boolean isDownloading(int i) {
        DownloadInfo downloadInfo;
        DownloadThreadPool downloadThreadPool2 = downloadThreadPool;
        if (downloadThreadPool2 == null || !downloadThreadPool2.containsTask(i) || (downloadInfo = getDownloadInfo(i)) == null) {
            return false;
        }
        if (DownloadStatus.isDownloading(downloadInfo.getStatus())) {
            return true;
        }
        doPause(i);
        return false;
    }

    @Override // com.p319ss.android.socialbase.downloader.impls.AbsDownloadEngine
    public void removeDownloadRunnable(DownloadRunnable downloadRunnable) {
        DownloadThreadPool downloadThreadPool2 = downloadThreadPool;
        if (downloadThreadPool2 == null) {
            return;
        }
        downloadThreadPool2.removeUnAliveDownloadRunnable(downloadRunnable);
    }

    @Override // com.p319ss.android.socialbase.downloader.impls.AbsDownloadEngine
    public void doDownload(int i, DownloadTask downloadTask) {
        if (downloadTask == null) {
            return;
        }
        Logger.m6475d("DownloadTask", "start doDownload for task : " + i);
        downloadThreadPool.execute(new DownloadRunnable(downloadTask, this.mainHandler));
    }

    @Override // com.p319ss.android.socialbase.downloader.impls.AbsDownloadEngine
    public void doPause(int i) {
        DownloadThreadPool downloadThreadPool2 = downloadThreadPool;
        if (downloadThreadPool2 == null) {
            return;
        }
        downloadThreadPool2.pause(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.impls.AbsDownloadEngine
    protected DownloadRunnable doCancel(int i) {
        DownloadThreadPool downloadThreadPool2 = downloadThreadPool;
        if (downloadThreadPool2 == null) {
            return null;
        }
        return downloadThreadPool2.cancel(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.impls.AbsDownloadEngine
    public List<Integer> getAllAliveDownloadIds() {
        return downloadThreadPool.getAllAliveDownloadIds();
    }

    @Override // com.p319ss.android.socialbase.downloader.impls.AbsDownloadEngine
    public void doSetThrottleNetSpeed(int i, long j) {
        DownloadThreadPool downloadThreadPool2 = downloadThreadPool;
        if (downloadThreadPool2 == null) {
            return;
        }
        downloadThreadPool2.setThrottleNetSpeed(i, j);
    }
}
