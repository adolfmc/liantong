package com.p319ss.android.socialbase.downloader.downloader;

import android.app.Notification;
import android.content.Intent;
import android.os.IBinder;
import com.p319ss.android.socialbase.downloader.downloader.DownloadService;
import com.p319ss.android.socialbase.downloader.model.DownloadTask;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.downloader.IDownloadServiceHandler */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IDownloadServiceHandler<T extends DownloadService> {
    boolean isServiceAlive();

    boolean isServiceForeground();

    IBinder onBind(Intent intent);

    void onDestroy();

    void onStartCommand(Intent intent, int i, int i2);

    void onStartCommandOnMainThread();

    void pendDownloadTask(DownloadTask downloadTask);

    void setDownloadService(WeakReference<T> weakReference);

    void setLogLevel(int i);

    void setServiceConnectionListener(IDownloadServiceConnectionListener iDownloadServiceConnectionListener);

    void startForeground(int i, Notification notification);

    void startService();

    void stopForeground(boolean z);

    void tryDownload(DownloadTask downloadTask);

    void tryDownloadWithEngine(DownloadTask downloadTask);
}
