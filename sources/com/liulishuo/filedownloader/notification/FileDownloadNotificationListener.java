package com.liulishuo.filedownloader.notification;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadList;
import com.liulishuo.filedownloader.FileDownloadListener;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class FileDownloadNotificationListener extends FileDownloadListener {
    private final FileDownloadNotificationHelper helper;

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    public void blockComplete(BaseDownloadTask baseDownloadTask) {
    }

    protected abstract BaseNotificationItem create(BaseDownloadTask baseDownloadTask);

    protected boolean disableNotification(BaseDownloadTask baseDownloadTask) {
        return false;
    }

    protected boolean interceptCancel(BaseDownloadTask baseDownloadTask, BaseNotificationItem baseNotificationItem) {
        return false;
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    public void warn(BaseDownloadTask baseDownloadTask) {
    }

    public FileDownloadNotificationListener(FileDownloadNotificationHelper fileDownloadNotificationHelper) {
        if (fileDownloadNotificationHelper == null) {
            throw new IllegalArgumentException("helper must not be null!");
        }
        this.helper = fileDownloadNotificationHelper;
    }

    public FileDownloadNotificationHelper getHelper() {
        return this.helper;
    }

    public void addNotificationItem(int i) {
        BaseDownloadTask.IRunningTask iRunningTask;
        if (i == 0 || (iRunningTask = FileDownloadList.getImpl().get(i)) == null) {
            return;
        }
        addNotificationItem(iRunningTask.getOrigin());
    }

    public void addNotificationItem(BaseDownloadTask baseDownloadTask) {
        BaseNotificationItem create;
        if (disableNotification(baseDownloadTask) || (create = create(baseDownloadTask)) == null) {
            return;
        }
        this.helper.add(create);
    }

    public void destroyNotification(BaseDownloadTask baseDownloadTask) {
        if (disableNotification(baseDownloadTask)) {
            return;
        }
        this.helper.showIndeterminate(baseDownloadTask.getId(), baseDownloadTask.getStatus());
        BaseNotificationItem remove = this.helper.remove(baseDownloadTask.getId());
        if (interceptCancel(baseDownloadTask, remove) || remove == null) {
            return;
        }
        remove.cancel();
    }

    public void showIndeterminate(BaseDownloadTask baseDownloadTask) {
        if (disableNotification(baseDownloadTask)) {
            return;
        }
        this.helper.showIndeterminate(baseDownloadTask.getId(), baseDownloadTask.getStatus());
    }

    public void showProgress(BaseDownloadTask baseDownloadTask, int i, int i2) {
        if (disableNotification(baseDownloadTask)) {
            return;
        }
        this.helper.showProgress(baseDownloadTask.getId(), baseDownloadTask.getSmallFileSoFarBytes(), baseDownloadTask.getSmallFileTotalBytes());
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    public void pending(BaseDownloadTask baseDownloadTask, int i, int i2) {
        addNotificationItem(baseDownloadTask);
        showIndeterminate(baseDownloadTask);
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    public void started(BaseDownloadTask baseDownloadTask) {
        super.started(baseDownloadTask);
        showIndeterminate(baseDownloadTask);
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    public void progress(BaseDownloadTask baseDownloadTask, int i, int i2) {
        showProgress(baseDownloadTask, i, i2);
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    public void retry(BaseDownloadTask baseDownloadTask, Throwable th, int i, int i2) {
        super.retry(baseDownloadTask, th, i, i2);
        showIndeterminate(baseDownloadTask);
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    public void completed(BaseDownloadTask baseDownloadTask) {
        destroyNotification(baseDownloadTask);
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    public void paused(BaseDownloadTask baseDownloadTask, int i, int i2) {
        destroyNotification(baseDownloadTask);
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    public void error(BaseDownloadTask baseDownloadTask, Throwable th) {
        destroyNotification(baseDownloadTask);
    }
}
