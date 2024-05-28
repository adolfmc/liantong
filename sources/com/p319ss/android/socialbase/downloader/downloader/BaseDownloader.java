package com.p319ss.android.socialbase.downloader.downloader;

import android.content.Context;
import android.text.TextUtils;
import com.p319ss.android.socialbase.downloader.constants.ListenerType;
import com.p319ss.android.socialbase.downloader.depend.IDownloadCacheSyncStatusListener;
import com.p319ss.android.socialbase.downloader.depend.IDownloadFileUriProvider;
import com.p319ss.android.socialbase.downloader.depend.IDownloadListener;
import com.p319ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener;
import com.p319ss.android.socialbase.downloader.depend.IDownloaderProcessConnectedListener;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.model.DownloadTask;
import com.p319ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.io.File;
import java.util.List;

/* renamed from: com.ss.android.socialbase.downloader.downloader.BaseDownloader */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class BaseDownloader {
    private String globalDefaultSavePath;
    private String globalDefaultSaveTempPath;

    public static DownloadTask with(Context context) {
        Downloader.getInstance(context);
        return new DownloadTask();
    }

    public void pause(int i) {
        DownloadProcessDispatcher.getInstance().pause(i);
    }

    public int getDownloadId(String str, String str2) {
        return DownloadProcessDispatcher.getInstance().getDownloadId(str, str2);
    }

    public List<DownloadInfo> getDownloadInfoList(String str) {
        return DownloadProcessDispatcher.getInstance().getDownloadInfoList(str);
    }

    public void cancel(int i) {
        cancel(i, true);
    }

    public void cancel(int i, boolean z) {
        DownloadProcessDispatcher.getInstance().cancel(i, z);
    }

    public void resume(int i) {
        DownloadProcessDispatcher.getInstance().resume(i);
    }

    public boolean canResume(int i) {
        return DownloadProcessDispatcher.getInstance().canResume(i);
    }

    public void restart(int i) {
        DownloadProcessDispatcher.getInstance().restart(i);
    }

    public void pauseAll() {
        DownloadProcessDispatcher.getInstance().pauseAll();
    }

    public List<DownloadInfo> getFailedDownloadInfosWithMimeType(String str) {
        return DownloadProcessDispatcher.getInstance().getFailedDownloadInfosWithMimeType(str);
    }

    public void restartAllFailedDownloadTasks(List<String> list) {
        DownloadProcessDispatcher.getInstance().restartAllFailedDownloadTasks(list);
    }

    public void restartAllPauseReserveOnWifiDownloadTasks(List<String> list) {
        DownloadProcessDispatcher.getInstance().restartAllPauseReserveOnWifiDownloadTasks(list);
    }

    public long getCurBytes(int i) {
        return DownloadProcessDispatcher.getInstance().getCurBytes(i);
    }

    public int getStatus(int i) {
        return DownloadProcessDispatcher.getInstance().getStatus(i);
    }

    public boolean isDownloading(int i) {
        boolean isDownloading;
        if (DownloadExpSwitchCode.isSwitchEnable(4194304)) {
            synchronized (this) {
                isDownloading = DownloadProcessDispatcher.getInstance().isDownloading(i);
            }
            return isDownloading;
        }
        return DownloadProcessDispatcher.getInstance().isDownloading(i);
    }

    public DownloadInfo getDownloadInfo(int i) {
        return DownloadProcessDispatcher.getInstance().getDownloadInfo(i);
    }

    public DownloadInfo getDownloadInfo(String str, String str2) {
        return DownloadProcessDispatcher.getInstance().getDownloadInfo(str, str2);
    }

    public IDownloadNotificationEventListener getDownloadNotificationEventListener(int i) {
        return DownloadProcessDispatcher.getInstance().getDownloadNotificationEventListener(i);
    }

    public void setDownloadNotificationEventListener(int i, IDownloadNotificationEventListener iDownloadNotificationEventListener) {
        DownloadProcessDispatcher.getInstance().setDownloadNotificationEventListener(i, iDownloadNotificationEventListener);
    }

    public List<DownloadInfo> getSuccessedDownloadInfosWithMimeType(String str) {
        return DownloadProcessDispatcher.getInstance().getSuccessedDownloadInfosWithMimeType(str);
    }

    public void clearDownloadData(int i) {
        DownloadProcessDispatcher.getInstance().clearDownloadData(i, true);
    }

    public void clearDownloadData(int i, boolean z) {
        DownloadProcessDispatcher.getInstance().clearDownloadData(i, z);
    }

    public void forceDownloadIngoreRecommendSize(int i) {
        DownloadProcessDispatcher.getInstance().forceDownloadIngoreRecommendSize(i);
    }

    @Deprecated
    public void removeTaskMainListener(int i) {
        DownloadProcessDispatcher.getInstance().removeDownloadListener(i, null, ListenerType.MAIN, true);
    }

    public void removeMainThreadListener(int i, IDownloadListener iDownloadListener) {
        if (iDownloadListener == null) {
            return;
        }
        DownloadProcessDispatcher.getInstance().removeDownloadListener(i, iDownloadListener, ListenerType.MAIN, false);
    }

    @Deprecated
    public void setMainThreadListener(int i, IDownloadListener iDownloadListener) {
        if (iDownloadListener == null) {
            return;
        }
        DownloadProcessDispatcher.getInstance().addDownloadListener(i, iDownloadListener, ListenerType.MAIN, true);
    }

    @Deprecated
    public void setMainThreadListener(int i, IDownloadListener iDownloadListener, boolean z) {
        if (iDownloadListener == null) {
            return;
        }
        DownloadProcessDispatcher.getInstance().addDownloadListener(i, iDownloadListener, ListenerType.MAIN, true, z);
    }

    public void addMainThreadListener(int i, IDownloadListener iDownloadListener) {
        if (iDownloadListener == null) {
            return;
        }
        DownloadProcessDispatcher.getInstance().addDownloadListener(i, iDownloadListener, ListenerType.MAIN, false);
    }

    @Deprecated
    public void setSubThreadListener(int i, IDownloadListener iDownloadListener) {
        if (iDownloadListener == null) {
            return;
        }
        DownloadProcessDispatcher.getInstance().addDownloadListener(i, iDownloadListener, ListenerType.SUB, true);
    }

    public void addSubThreadListener(int i, IDownloadListener iDownloadListener) {
        if (iDownloadListener == null) {
            return;
        }
        DownloadProcessDispatcher.getInstance().addDownloadListener(i, iDownloadListener, ListenerType.SUB, false);
    }

    @Deprecated
    public void removeTaskSubListener(int i) {
        DownloadProcessDispatcher.getInstance().removeDownloadListener(i, null, ListenerType.SUB, true);
    }

    public void removeSubThreadListener(int i, IDownloadListener iDownloadListener) {
        if (iDownloadListener == null) {
            return;
        }
        DownloadProcessDispatcher.getInstance().removeDownloadListener(i, iDownloadListener, ListenerType.SUB, false);
    }

    @Deprecated
    public void removeTaskNotificationListener(int i) {
        DownloadProcessDispatcher.getInstance().removeDownloadListener(i, null, ListenerType.NOTIFICATION, true);
    }

    public void removeNotificationListener(int i, IDownloadListener iDownloadListener) {
        if (iDownloadListener == null) {
            return;
        }
        DownloadProcessDispatcher.getInstance().removeDownloadListener(i, iDownloadListener, ListenerType.NOTIFICATION, false);
    }

    @Deprecated
    public void setNotificationListener(int i, IDownloadListener iDownloadListener) {
        if (iDownloadListener == null) {
            return;
        }
        DownloadProcessDispatcher.getInstance().addDownloadListener(i, iDownloadListener, ListenerType.NOTIFICATION, true);
    }

    public void addNotificationListener(int i, IDownloadListener iDownloadListener) {
        if (iDownloadListener == null) {
            return;
        }
        DownloadProcessDispatcher.getInstance().addDownloadListener(i, iDownloadListener, ListenerType.NOTIFICATION, false);
    }

    public boolean isDownloadSuccessAndFileNotExist(DownloadInfo downloadInfo) {
        return DownloadProcessDispatcher.getInstance().isDownloadSuccessAndFileNotExist(downloadInfo);
    }

    public boolean isHttpServiceInit() {
        return DownloadProcessDispatcher.getInstance().isHttpServiceInit();
    }

    public List<DownloadInfo> getUnCompletedDownloadInfosWithMimeType(String str) {
        return DownloadProcessDispatcher.getInstance().getUnCompletedDownloadInfosWithMimeType(str);
    }

    public List<DownloadInfo> getDownloadingDownloadInfosWithMimeType(String str) {
        return DownloadProcessDispatcher.getInstance().getDownloadingDownloadInfosWithMimeType(str);
    }

    public List<DownloadInfo> getAllDownloadInfo() {
        return DownloadProcessDispatcher.getInstance().getAllDownloadInfo();
    }

    public void destoryDownloader() {
        DownloadComponentManager.unRegisterDownloadReceiver();
    }

    public void setLogLevel(int i) {
        DownloadProcessDispatcher.getInstance().setLogLevel(i);
    }

    public void registerDownloadCacheSyncListener(IDownloadCacheSyncStatusListener iDownloadCacheSyncStatusListener) {
        DownloadProcessDispatcher.getInstance().registerDownloadCacheSyncListener(iDownloadCacheSyncStatusListener);
    }

    public void unRegisterDownloadCacheSyncListener(IDownloadCacheSyncStatusListener iDownloadCacheSyncStatusListener) {
        DownloadProcessDispatcher.getInstance().unRegisterDownloadCacheSyncListener(iDownloadCacheSyncStatusListener);
    }

    public boolean isDownloadCacheSyncSuccess() {
        return DownloadProcessDispatcher.getInstance().isDownloadCacheSyncSuccess();
    }

    public void setDownloadInMultiProcess() {
        if (DownloadExpSwitchCode.isSwitchEnable(4194304)) {
            synchronized (this) {
                DownloadComponentManager.setDownloadInMultiProcess();
            }
            return;
        }
        DownloadComponentManager.setDownloadInMultiProcess();
    }

    public IDownloadFileUriProvider getDownloadFileUriProvider(int i) {
        return DownloadProcessDispatcher.getInstance().getDownloadFileUriProvider(i);
    }

    public void registerDownloaderProcessConnectedListener(IDownloaderProcessConnectedListener iDownloaderProcessConnectedListener) {
        DownloadProcessDispatcher.getInstance().registerDownloaderProcessConnectedListener(iDownloaderProcessConnectedListener);
    }

    public void unRegisterDownloaderProcessConnectedListener(IDownloaderProcessConnectedListener iDownloaderProcessConnectedListener) {
        DownloadProcessDispatcher.getInstance().unRegisterDownloaderProcessConnectedListener(iDownloaderProcessConnectedListener);
    }

    public boolean isDownloadServiceForeground(int i) {
        return DownloadProcessDispatcher.getInstance().getDownloadHandler(i).isServiceForeground();
    }

    public IReserveWifiStatusListener getReserveWifiStatusListener() {
        return DownloadComponentManager.getReserveWifiStatusListener();
    }

    public void setReserveWifiStatusListener(IReserveWifiStatusListener iReserveWifiStatusListener) {
        DownloadComponentManager.setReserveWifiStatusListener(iReserveWifiStatusListener);
    }

    public void setThrottleNetSpeed(int i, long j) {
        DownloadProcessDispatcher.getInstance().setThrottleNetSpeed(i, j);
    }

    public File getGlobalSaveDir() {
        return getGlobalSaveDir(this.globalDefaultSavePath, true);
    }

    public File getGlobalSaveTempDir() {
        return getGlobalSaveDir(this.globalDefaultSaveTempPath, false);
    }

    private File getGlobalSaveDir(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            File file = new File(str);
            try {
                if (!file.exists()) {
                    file.mkdirs();
                } else if (!file.isDirectory()) {
                    if (!z) {
                        return null;
                    }
                    file.delete();
                    file.mkdirs();
                }
                return file;
            } catch (Throwable unused) {
                return file;
            }
        } catch (Throwable unused2) {
            return null;
        }
    }

    public void setDefaultSavePath(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.globalDefaultSavePath = str;
    }

    public void setDefaultSaveTempPath(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.globalDefaultSaveTempPath = str;
    }
}
