package com.p319ss.android.socialbase.downloader.downloader;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;

/* renamed from: com.ss.android.socialbase.downloader.downloader.DownloadService */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DownloadService extends Service {
    private static final String TAG = "DownloadService";
    protected IDownloadServiceHandler downloadServiceHandler;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        DownloadComponentManager.setAppContext(this);
        this.downloadServiceHandler = DownloadComponentManager.getDownloadServiceHandler();
        this.downloadServiceHandler.setDownloadService(new WeakReference(this));
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onBind downloadServiceHandler != null:");
        sb.append(this.downloadServiceHandler != null);
        Logger.m6475d(str, sb.toString());
        IDownloadServiceHandler iDownloadServiceHandler = this.downloadServiceHandler;
        if (iDownloadServiceHandler != null) {
            return iDownloadServiceHandler.onBind(intent);
        }
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (Logger.debug()) {
            Logger.m6475d(TAG, "DownloadService onStartCommand");
        }
        this.downloadServiceHandler.onStartCommandOnMainThread();
        ExecutorService cPUThreadExecutor = DownloadComponentManager.getCPUThreadExecutor();
        if (cPUThreadExecutor != null) {
            cPUThreadExecutor.execute(new RunnableC102051(intent, i, i2));
        }
        return DownloadComponentManager.notAutoRebootService() ? 2 : 3;
    }

    /* renamed from: com.ss.android.socialbase.downloader.downloader.DownloadService$1 */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    class RunnableC102051 implements Runnable {
        final /* synthetic */ int val$flags;
        final /* synthetic */ Intent val$intent;
        final /* synthetic */ int val$startId;

        /* JADX INFO: Access modifiers changed from: package-private */
        public RunnableC102051(Intent intent, int i, int i2) {
            this.val$intent = intent;
            this.val$flags = i;
            this.val$startId = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (DownloadService.this.downloadServiceHandler != null) {
                DownloadService.this.downloadServiceHandler.onStartCommand(this.val$intent, this.val$flags, this.val$startId);
            }
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        if (Logger.debug()) {
            Logger.m6475d(TAG, "Service onDestroy");
        }
        IDownloadServiceHandler iDownloadServiceHandler = this.downloadServiceHandler;
        if (iDownloadServiceHandler != null) {
            iDownloadServiceHandler.onDestroy();
            this.downloadServiceHandler = null;
        }
        super.onDestroy();
    }
}
