package com.p319ss.android.socialbase.downloader.downloader;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.SparseArray;
import com.p319ss.android.socialbase.downloader.impls.AbsDownloadEngine;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.model.DownloadTask;
import com.p319ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.ss.android.socialbase.downloader.downloader.AbsDownloadServiceHandler */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class AbsDownloadServiceHandler implements IDownloadServiceHandler {
    private static final String TAG = "AbsDownloadServiceHandler";
    private WeakReference<Service> downloadService;
    protected volatile boolean isServiceForeground;
    protected final SparseArray<List<DownloadTask>> pendingTasks = new SparseArray<>();
    protected volatile boolean isServiceAlive = false;
    protected volatile boolean isInvokeStartService = false;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable debounceStartServiceRunnable = new Runnable() { // from class: com.ss.android.socialbase.downloader.downloader.AbsDownloadServiceHandler.1
        @Override // java.lang.Runnable
        public void run() {
            if (Logger.debug()) {
                Logger.m6475d(AbsDownloadServiceHandler.TAG, "tryDownload: 2 try");
            }
            if (AbsDownloadServiceHandler.this.isServiceAlive) {
                return;
            }
            if (Logger.debug()) {
                Logger.m6475d(AbsDownloadServiceHandler.TAG, "tryDownload: 2 error");
            }
            AbsDownloadServiceHandler.this.startService(DownloadComponentManager.getAppContext(), null);
        }
    };

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadServiceHandler
    public void onStartCommand(Intent intent, int i, int i2) {
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadServiceHandler
    public void onStartCommandOnMainThread() {
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadServiceHandler
    public void setServiceConnectionListener(IDownloadServiceConnectionListener iDownloadServiceConnectionListener) {
    }

    protected void startService(Context context, ServiceConnection serviceConnection) {
    }

    protected void stopService(Context context, ServiceConnection serviceConnection) {
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadServiceHandler
    public void tryDownloadWithEngine(DownloadTask downloadTask) {
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadServiceHandler
    public void setDownloadService(WeakReference weakReference) {
        this.downloadService = weakReference;
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadServiceHandler
    public boolean isServiceAlive() {
        return this.isServiceAlive;
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadServiceHandler
    public boolean isServiceForeground() {
        String str = TAG;
        Logger.m6469i(str, "isServiceForeground = " + this.isServiceForeground);
        return this.isServiceForeground;
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadServiceHandler
    public IBinder onBind(Intent intent) {
        Logger.m6475d(TAG, "onBind Abs");
        return new Binder();
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadServiceHandler
    public void startForeground(int i, Notification notification) {
        WeakReference<Service> weakReference = this.downloadService;
        if (weakReference != null && weakReference.get() != null) {
            String str = TAG;
            Logger.m6469i(str, "startForeground  id = " + i + ", service = " + this.downloadService.get() + ",  isServiceAlive = " + this.isServiceAlive);
            try {
                this.downloadService.get().startForeground(i, notification);
                this.isServiceForeground = true;
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        Logger.m6460w(TAG, "startForeground: downloadService is null, do nothing!");
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadServiceHandler
    public void stopForeground(boolean z) {
        WeakReference<Service> weakReference = this.downloadService;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        String str = TAG;
        Logger.m6469i(str, "stopForeground  service = " + this.downloadService.get() + ",  isServiceAlive = " + this.isServiceAlive);
        try {
            this.isServiceForeground = false;
            this.downloadService.get().stopForeground(z);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadServiceHandler
    public void onDestroy() {
        this.isServiceAlive = false;
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadServiceHandler
    public void pendDownloadTask(DownloadTask downloadTask) {
        if (downloadTask == null) {
            return;
        }
        int downloadId = downloadTask.getDownloadId();
        synchronized (this.pendingTasks) {
            String str = TAG;
            Logger.m6475d(str, "pendDownloadTask pendingTasks.size:" + this.pendingTasks.size() + " downloadId:" + downloadId);
            List<DownloadTask> list = this.pendingTasks.get(downloadId);
            if (list == null) {
                list = new ArrayList<>();
                this.pendingTasks.put(downloadId, list);
            }
            String str2 = TAG;
            Logger.m6475d(str2, "before pendDownloadTask taskArray.size:" + list.size());
            list.add(downloadTask);
            String str3 = TAG;
            Logger.m6475d(str3, "after pendDownloadTask pendingTasks.size:" + this.pendingTasks.size());
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadServiceHandler
    public void tryDownload(DownloadTask downloadTask) {
        if (downloadTask == null) {
            return;
        }
        if (!this.isServiceAlive) {
            if (Logger.debug()) {
                Logger.m6475d(TAG, "tryDownload but service is not alive");
            }
            if (DownloadExpSwitchCode.isSwitchEnable(262144)) {
                pendDownloadTask(downloadTask);
                if (!this.isInvokeStartService) {
                    if (Logger.debug()) {
                        Logger.m6475d(TAG, "tryDownload: 1");
                    }
                    startService(DownloadComponentManager.getAppContext(), null);
                    this.isInvokeStartService = true;
                    return;
                }
                this.handler.removeCallbacks(this.debounceStartServiceRunnable);
                this.handler.postDelayed(this.debounceStartServiceRunnable, 10L);
                return;
            }
            pendDownloadTask(downloadTask);
            startService(DownloadComponentManager.getAppContext(), null);
            return;
        }
        Logger.m6475d(TAG, "tryDownload when isServiceAlive");
        resumePendingTask();
        AbsDownloadEngine downloadEngine = DownloadComponentManager.getDownloadEngine();
        if (downloadEngine != null) {
            String str = TAG;
            Logger.m6475d(str, "tryDownload current task: " + downloadTask.getDownloadId());
            downloadEngine.tryDownload(downloadTask);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void resumePendingTask() {
        SparseArray<List<DownloadTask>> clone;
        synchronized (this.pendingTasks) {
            String str = TAG;
            Logger.m6475d(str, "resumePendingTask pendingTasks.size:" + this.pendingTasks.size());
            clone = this.pendingTasks.clone();
            this.pendingTasks.clear();
        }
        AbsDownloadEngine downloadEngine = DownloadComponentManager.getDownloadEngine();
        if (downloadEngine != null) {
            for (int i = 0; i < clone.size(); i++) {
                List<DownloadTask> list = clone.get(clone.keyAt(i));
                if (list != null) {
                    for (DownloadTask downloadTask : list) {
                        String str2 = TAG;
                        Logger.m6475d(str2, "resumePendingTask key:" + downloadTask.getDownloadId());
                        downloadEngine.tryDownload(downloadTask);
                    }
                }
            }
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadServiceHandler
    public void setLogLevel(int i) {
        Logger.setLogLevel(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadServiceHandler
    public void startService() {
        if (this.isServiceAlive) {
            return;
        }
        if (Logger.debug()) {
            Logger.m6475d(TAG, "startService");
        }
        startService(DownloadComponentManager.getAppContext(), null);
    }
}
