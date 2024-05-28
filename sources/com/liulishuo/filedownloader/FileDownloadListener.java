package com.liulishuo.filedownloader;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class FileDownloadListener {
    /* JADX INFO: Access modifiers changed from: protected */
    public void blockComplete(BaseDownloadTask baseDownloadTask) throws Throwable {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void completed(BaseDownloadTask baseDownloadTask);

    /* JADX INFO: Access modifiers changed from: protected */
    public void connected(BaseDownloadTask baseDownloadTask, String str, boolean z, int i, int i2) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void error(BaseDownloadTask baseDownloadTask, Throwable th);

    protected boolean isInvalid() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void paused(BaseDownloadTask baseDownloadTask, int i, int i2);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void pending(BaseDownloadTask baseDownloadTask, int i, int i2);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void progress(BaseDownloadTask baseDownloadTask, int i, int i2);

    /* JADX INFO: Access modifiers changed from: protected */
    public void retry(BaseDownloadTask baseDownloadTask, Throwable th, int i, int i2) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void started(BaseDownloadTask baseDownloadTask) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void warn(BaseDownloadTask baseDownloadTask);

    public FileDownloadListener() {
    }

    public FileDownloadListener(int i) {
    }
}
