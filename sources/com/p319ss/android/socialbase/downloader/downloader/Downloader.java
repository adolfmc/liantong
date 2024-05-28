package com.p319ss.android.socialbase.downloader.downloader;

import android.content.Context;
import com.p319ss.android.socialbase.downloader.depend.IDownloadCacheSyncStatusListener;
import com.p319ss.android.socialbase.downloader.depend.IDownloadFileUriProvider;
import com.p319ss.android.socialbase.downloader.depend.IDownloadListener;
import com.p319ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener;
import com.p319ss.android.socialbase.downloader.depend.IDownloaderProcessConnectedListener;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import java.io.File;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.downloader.Downloader */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class Downloader extends BaseDownloader {
    private static volatile Downloader instance;

    static {
        DownloadComponentManager.setIndependentServiceCreator(new MultiProcCreater());
        instance = null;
    }

    private Downloader() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Downloader(DownloaderBuilder downloaderBuilder) {
        DownloadComponentManager.initComponent(downloaderBuilder);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0030, code lost:
        r1 = (99 * 99) - ((18 * 18) * 34);
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0000, code lost:
        continue;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String Downloader1672829046035dc(java.lang.String r4) {
        /*
        L0:
            r0 = 74
            r1 = 55
        L4:
            r2 = 0
            switch(r0) {
                case 72: goto L0;
                case 73: goto L9;
                case 74: goto Lc;
                default: goto L8;
            }
        L8:
            goto L54
        L9:
            switch(r1) {
                case 94: goto L4f;
                case 95: goto L10;
                case 96: goto L39;
                default: goto Lc;
            }
        Lc:
            switch(r1) {
                case 55: goto L4f;
                case 56: goto L4f;
                case 57: goto L10;
                default: goto Lf;
            }
        Lf:
            goto L4f
        L10:
            r0 = 18
            r1 = 1
            switch(r1) {
                case 60: goto L17;
                case 61: goto L23;
                case 62: goto L30;
                default: goto L16;
            }
        L16:
            goto L39
        L17:
            int r3 = 0 - r1
            int r3 = r3 * r2
            r2 = 0
            int r2 = r2 * 2
            int r2 = r2 - r1
            int r3 = r3 * r2
            int r3 = r3 % 6
            if (r3 == 0) goto L0
        L23:
            int r2 = 18 - r1
            int r2 = r2 * r0
            r3 = 18
            int r3 = r3 * 2
            int r3 = r3 - r1
            int r2 = r2 * r3
            int r2 = r2 % 6
            if (r2 == 0) goto L4f
        L30:
            r1 = 99
            int r1 = r1 * r1
            int r0 = r0 * r0
            int r0 = r0 * 34
            int r1 = r1 - r0
            r0 = -1
            goto L0
        L39:
            char[] r4 = r4.toCharArray()
        L3d:
            int r0 = r4.length
            if (r2 >= r0) goto L49
            char r0 = r4[r2]
            r0 = r0 ^ r2
            char r0 = (char) r0
            r4[r2] = r0
            int r2 = r2 + 1
            goto L3d
        L49:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r4)
            return r0
        L4f:
            r0 = 73
            r1 = 96
            goto L4
        L54:
            r0 = 72
            goto L4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.downloader.downloader.Downloader.Downloader1672829046035dc(java.lang.String):java.lang.String");
    }

    public static Downloader getInstance(Context context) {
        if (instance == null) {
            synchronized (Downloader.class) {
                try {
                    if (instance == null) {
                        DownloadComponentManager.setAppContext(context);
                        instance = new Downloader();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return instance;
    }

    public static synchronized void init(DownloaderBuilder downloaderBuilder) {
        synchronized (Downloader.class) {
            initOrCover(downloaderBuilder, false);
        }
    }

    public static synchronized void initOrCover(DownloaderBuilder downloaderBuilder, boolean z) {
        synchronized (Downloader.class) {
            if (downloaderBuilder == null) {
                return;
            }
            if (instance == null) {
                instance = downloaderBuilder.build();
            } else if (!DownloadComponentManager.isInit()) {
                DownloadComponentManager.initComponent(downloaderBuilder);
            } else if (z) {
                DownloadComponentManager.coverComponent(downloaderBuilder);
            }
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void addMainThreadListener(int i, IDownloadListener iDownloadListener) {
        super.addMainThreadListener(i, iDownloadListener);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void addNotificationListener(int i, IDownloadListener iDownloadListener) {
        super.addNotificationListener(i, iDownloadListener);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void addSubThreadListener(int i, IDownloadListener iDownloadListener) {
        super.addSubThreadListener(i, iDownloadListener);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ boolean canResume(int i) {
        return super.canResume(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void cancel(int i) {
        super.cancel(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void cancel(int i, boolean z) {
        super.cancel(i, z);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void clearDownloadData(int i) {
        super.clearDownloadData(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void clearDownloadData(int i, boolean z) {
        super.clearDownloadData(i, z);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void destoryDownloader() {
        super.destoryDownloader();
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void forceDownloadIngoreRecommendSize(int i) {
        super.forceDownloadIngoreRecommendSize(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ List getAllDownloadInfo() {
        return super.getAllDownloadInfo();
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ long getCurBytes(int i) {
        return super.getCurBytes(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ IDownloadFileUriProvider getDownloadFileUriProvider(int i) {
        return super.getDownloadFileUriProvider(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ int getDownloadId(String str, String str2) {
        return super.getDownloadId(str, str2);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ DownloadInfo getDownloadInfo(int i) {
        return super.getDownloadInfo(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ DownloadInfo getDownloadInfo(String str, String str2) {
        return super.getDownloadInfo(str, str2);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ List getDownloadInfoList(String str) {
        return super.getDownloadInfoList(str);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ IDownloadNotificationEventListener getDownloadNotificationEventListener(int i) {
        return super.getDownloadNotificationEventListener(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ List getDownloadingDownloadInfosWithMimeType(String str) {
        return super.getDownloadingDownloadInfosWithMimeType(str);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ List getFailedDownloadInfosWithMimeType(String str) {
        return super.getFailedDownloadInfosWithMimeType(str);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ File getGlobalSaveDir() {
        return super.getGlobalSaveDir();
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ File getGlobalSaveTempDir() {
        return super.getGlobalSaveTempDir();
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ IReserveWifiStatusListener getReserveWifiStatusListener() {
        return super.getReserveWifiStatusListener();
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ int getStatus(int i) {
        return super.getStatus(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ List getSuccessedDownloadInfosWithMimeType(String str) {
        return super.getSuccessedDownloadInfosWithMimeType(str);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ List getUnCompletedDownloadInfosWithMimeType(String str) {
        return super.getUnCompletedDownloadInfosWithMimeType(str);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ boolean isDownloadCacheSyncSuccess() {
        return super.isDownloadCacheSyncSuccess();
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ boolean isDownloadServiceForeground(int i) {
        return super.isDownloadServiceForeground(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ boolean isDownloadSuccessAndFileNotExist(DownloadInfo downloadInfo) {
        return super.isDownloadSuccessAndFileNotExist(downloadInfo);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ boolean isDownloading(int i) {
        return super.isDownloading(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ boolean isHttpServiceInit() {
        return super.isHttpServiceInit();
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void pause(int i) {
        super.pause(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void pauseAll() {
        super.pauseAll();
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void registerDownloadCacheSyncListener(IDownloadCacheSyncStatusListener iDownloadCacheSyncStatusListener) {
        super.registerDownloadCacheSyncListener(iDownloadCacheSyncStatusListener);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void registerDownloaderProcessConnectedListener(IDownloaderProcessConnectedListener iDownloaderProcessConnectedListener) {
        super.registerDownloaderProcessConnectedListener(iDownloaderProcessConnectedListener);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void removeMainThreadListener(int i, IDownloadListener iDownloadListener) {
        super.removeMainThreadListener(i, iDownloadListener);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void removeNotificationListener(int i, IDownloadListener iDownloadListener) {
        super.removeNotificationListener(i, iDownloadListener);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void removeSubThreadListener(int i, IDownloadListener iDownloadListener) {
        super.removeSubThreadListener(i, iDownloadListener);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    @Deprecated
    public /* bridge */ /* synthetic */ void removeTaskMainListener(int i) {
        super.removeTaskMainListener(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    @Deprecated
    public /* bridge */ /* synthetic */ void removeTaskNotificationListener(int i) {
        super.removeTaskNotificationListener(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    @Deprecated
    public /* bridge */ /* synthetic */ void removeTaskSubListener(int i) {
        super.removeTaskSubListener(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void restart(int i) {
        super.restart(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void restartAllFailedDownloadTasks(List list) {
        super.restartAllFailedDownloadTasks(list);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void restartAllPauseReserveOnWifiDownloadTasks(List list) {
        super.restartAllPauseReserveOnWifiDownloadTasks(list);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void resume(int i) {
        super.resume(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void setDefaultSavePath(String str) {
        super.setDefaultSavePath(str);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void setDefaultSaveTempPath(String str) {
        super.setDefaultSaveTempPath(str);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void setDownloadInMultiProcess() {
        super.setDownloadInMultiProcess();
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void setDownloadNotificationEventListener(int i, IDownloadNotificationEventListener iDownloadNotificationEventListener) {
        super.setDownloadNotificationEventListener(i, iDownloadNotificationEventListener);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void setLogLevel(int i) {
        super.setLogLevel(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    @Deprecated
    public /* bridge */ /* synthetic */ void setMainThreadListener(int i, IDownloadListener iDownloadListener) {
        super.setMainThreadListener(i, iDownloadListener);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    @Deprecated
    public /* bridge */ /* synthetic */ void setMainThreadListener(int i, IDownloadListener iDownloadListener, boolean z) {
        super.setMainThreadListener(i, iDownloadListener, z);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    @Deprecated
    public /* bridge */ /* synthetic */ void setNotificationListener(int i, IDownloadListener iDownloadListener) {
        super.setNotificationListener(i, iDownloadListener);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void setReserveWifiStatusListener(IReserveWifiStatusListener iReserveWifiStatusListener) {
        super.setReserveWifiStatusListener(iReserveWifiStatusListener);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    @Deprecated
    public /* bridge */ /* synthetic */ void setSubThreadListener(int i, IDownloadListener iDownloadListener) {
        super.setSubThreadListener(i, iDownloadListener);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void setThrottleNetSpeed(int i, long j) {
        super.setThrottleNetSpeed(i, j);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void unRegisterDownloadCacheSyncListener(IDownloadCacheSyncStatusListener iDownloadCacheSyncStatusListener) {
        super.unRegisterDownloadCacheSyncListener(iDownloadCacheSyncStatusListener);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.BaseDownloader
    public /* bridge */ /* synthetic */ void unRegisterDownloaderProcessConnectedListener(IDownloaderProcessConnectedListener iDownloaderProcessConnectedListener) {
        super.unRegisterDownloaderProcessConnectedListener(iDownloaderProcessConnectedListener);
    }
}
