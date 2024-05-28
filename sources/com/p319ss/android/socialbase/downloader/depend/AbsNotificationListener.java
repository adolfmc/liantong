package com.p319ss.android.socialbase.downloader.depend;

import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.notification.AbsNotificationItem;
import com.p319ss.android.socialbase.downloader.notification.DownloadNotificationManager;

/* renamed from: com.ss.android.socialbase.downloader.depend.AbsNotificationListener */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public abstract class AbsNotificationListener extends AbsDownloadListener implements IDownloadExtListener {
    protected abstract AbsNotificationItem createNotificationItem();

    @Override // com.p319ss.android.socialbase.downloader.depend.AbsDownloadListener, com.p319ss.android.socialbase.downloader.depend.IDownloadListener
    public void onPrepare(DownloadInfo downloadInfo) {
        super.onPrepare(downloadInfo);
        addNotificationItem(downloadInfo);
        updateNotification(1, downloadInfo, null, true);
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.AbsDownloadListener, com.p319ss.android.socialbase.downloader.depend.IDownloadListener
    public void onStart(DownloadInfo downloadInfo) {
        super.onStart(downloadInfo);
        updateNotification(2, downloadInfo, null, false);
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.AbsDownloadListener, com.p319ss.android.socialbase.downloader.depend.IDownloadListener
    public void onProgress(DownloadInfo downloadInfo) {
        super.onProgress(downloadInfo);
        updateNotificationProgress(downloadInfo);
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.AbsDownloadListener, com.p319ss.android.socialbase.downloader.depend.IDownloadListener
    public void onPause(DownloadInfo downloadInfo) {
        super.onPause(downloadInfo);
        updateNotification(-2, downloadInfo, null, false);
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.AbsDownloadListener, com.p319ss.android.socialbase.downloader.depend.IDownloadListener
    public void onSuccessed(DownloadInfo downloadInfo) {
        super.onSuccessed(downloadInfo);
        updateNotification(-3, downloadInfo, null, false);
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.AbsDownloadListener, com.p319ss.android.socialbase.downloader.depend.IDownloadListener
    public void onFailed(DownloadInfo downloadInfo, BaseException baseException) {
        super.onFailed(downloadInfo, baseException);
        updateNotification(-1, downloadInfo, baseException, false);
    }

    private void addNotificationItem(DownloadInfo downloadInfo) {
        if (downloadInfo == null || !downloadInfo.canShowNotification()) {
            return;
        }
        AbsNotificationItem notificationItem = DownloadNotificationManager.getInstance().getNotificationItem(downloadInfo.getId());
        if (notificationItem == null) {
            DownloadNotificationManager.getInstance().addNotification(createNotificationItem());
            return;
        }
        notificationItem.updateNotificationItem(downloadInfo);
    }

    private void updateNotification(int i, DownloadInfo downloadInfo, BaseException baseException, boolean z) {
        if (downloadInfo == null || !downloadInfo.canShowNotification() || i == 4) {
            return;
        }
        AbsNotificationItem notificationItem = DownloadNotificationManager.getInstance().getNotificationItem(downloadInfo.getId());
        if (notificationItem == null) {
            notificationItem = createNotificationItem();
        }
        notificationItem.setTotalBytes(downloadInfo.getTotalBytes());
        if (i == -3) {
            notificationItem.setCurBytes(downloadInfo.getTotalBytes());
        } else {
            notificationItem.setCurBytes(downloadInfo.getCurBytes());
        }
        notificationItem.refreshStatus(i, baseException, z);
    }

    private void updateNotificationProgress(DownloadInfo downloadInfo) {
        if (downloadInfo != null && downloadInfo.canShowNotification() && downloadInfo.getStatus() == 4) {
            AbsNotificationItem notificationItem = DownloadNotificationManager.getInstance().getNotificationItem(downloadInfo.getId());
            if (notificationItem == null) {
                notificationItem = createNotificationItem();
            }
            notificationItem.refreshProgress(downloadInfo.getCurBytes(), downloadInfo.getTotalBytes());
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadExtListener
    public void onWaitingDownloadCompleteHandler(DownloadInfo downloadInfo) {
        if (downloadInfo == null || downloadInfo.isAutoInstallWithoutNotification()) {
            return;
        }
        updateNotification(11, downloadInfo, null, true);
    }
}
