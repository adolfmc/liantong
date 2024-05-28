package com.liulishuo.filedownloader.status;

import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.StatusUtil;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class StatusAssist {
    private DownloadTask downloadTask;
    private byte status = 0;

    public synchronized void setDownloadTask(DownloadTask downloadTask) {
        this.downloadTask = downloadTask;
    }

    public synchronized DownloadTask getDownloadTask() {
        return this.downloadTask;
    }

    public synchronized byte getStatus() {
        if (this.downloadTask == null) {
            return this.status;
        }
        this.status = convert(StatusUtil.getStatus(this.downloadTask));
        return this.status;
    }

    synchronized byte convert(StatusUtil.Status status) {
        switch (status) {
            case COMPLETED:
                return (byte) -3;
            case IDLE:
                return (byte) -2;
            case UNKNOWN:
                return (byte) 0;
            case PENDING:
                return (byte) 1;
            case RUNNING:
                return (byte) 3;
            default:
                return (byte) 0;
        }
    }

    public synchronized boolean isUsing() {
        return getStatus() != 0;
    }

    public synchronized boolean isOver() {
        return FileDownloadStatus.isOver(getStatus());
    }
}
