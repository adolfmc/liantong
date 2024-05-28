package com.p319ss.android.socialbase.downloader.notification;

import android.app.Notification;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;

/* renamed from: com.ss.android.socialbase.downloader.notification.AbsNotificationItem */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public abstract class AbsNotificationItem {
    private int clickInstallTimes;
    private long curBytes;
    private String description;
    private long firstShowTime;

    /* renamed from: id */
    private int f19711id;
    protected Notification notification;
    private boolean ongoing;
    private int status = 0;
    private String title;
    private long totalBytes;

    public abstract void updateNotification(BaseException baseException, boolean z);

    public AbsNotificationItem(int i, String str) {
        this.f19711id = i;
        this.title = str;
    }

    public void updateNotificationItem(DownloadInfo downloadInfo) {
        if (downloadInfo == null) {
            return;
        }
        this.f19711id = downloadInfo.getId();
        this.title = downloadInfo.getTitle();
    }

    public int getId() {
        return this.f19711id;
    }

    public void setId(int i) {
        this.f19711id = i;
    }

    public long getCurBytes() {
        return this.curBytes;
    }

    public void setCurBytes(long j) {
        this.curBytes = j;
    }

    public long getTotalBytes() {
        return this.totalBytes;
    }

    public void setTotalBytes(long j) {
        this.totalBytes = j;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void refreshStatus(int i, BaseException baseException, boolean z) {
        refreshStatus(i, baseException, z, false);
    }

    public void refreshStatus(int i, BaseException baseException, boolean z, boolean z2) {
        if (z2 || this.status != i) {
            this.status = i;
            updateNotification(baseException, z);
        }
    }

    public long getFirstShowTime() {
        if (this.firstShowTime == 0) {
            this.firstShowTime = System.currentTimeMillis();
        }
        return this.firstShowTime;
    }

    public void refreshProgress(long j, long j2) {
        this.curBytes = j;
        this.totalBytes = j2;
        this.status = 4;
        updateNotification(null, false);
    }

    public void notify(Notification notification) {
        if (this.f19711id == 0 || notification == null) {
            return;
        }
        DownloadNotificationManager.getInstance().notifyByService(this.f19711id, this.status, notification);
    }

    public void cancel() {
        if (this.f19711id == 0) {
            return;
        }
        DownloadNotificationManager.getInstance().cancel(this.f19711id);
    }

    public synchronized void recordClickInstall() {
        this.clickInstallTimes++;
    }

    public int getClickInstallTimes() {
        return this.clickInstallTimes;
    }

    public boolean isOngoing() {
        return this.ongoing;
    }

    protected void setOngoing(boolean z) {
        this.ongoing = z;
    }

    public Notification getNotification() {
        return this.notification;
    }
}
