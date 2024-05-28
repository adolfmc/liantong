package com.liulishuo.filedownloader;

import android.support.annotation.NonNull;
import com.liulishuo.filedownloader.CompatListenerAssist;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CompatListenerAdaptee implements CompatListenerAssist.CompatListenerAssistCallback {
    @NonNull
    private final FileDownloadListener fileDownloadListener;

    public CompatListenerAdaptee(@NonNull FileDownloadListener fileDownloadListener) {
        this.fileDownloadListener = fileDownloadListener;
    }

    @Override // com.liulishuo.filedownloader.CompatListenerAssist.CompatListenerAssistCallback
    public void pending(BaseDownloadTask baseDownloadTask, long j, long j2) {
        FileDownloadListener fileDownloadListener = this.fileDownloadListener;
        if (fileDownloadListener instanceof FileDownloadLargeFileListener) {
            ((FileDownloadLargeFileListener) fileDownloadListener).pending(baseDownloadTask, j, j2);
        } else {
            fileDownloadListener.pending(baseDownloadTask, (int) j, (int) j2);
        }
    }

    @Override // com.liulishuo.filedownloader.CompatListenerAssist.CompatListenerAssistCallback
    public void started(BaseDownloadTask baseDownloadTask) {
        this.fileDownloadListener.started(baseDownloadTask);
    }

    @Override // com.liulishuo.filedownloader.CompatListenerAssist.CompatListenerAssistCallback
    public void connected(BaseDownloadTask baseDownloadTask, String str, boolean z, long j, long j2) {
        FileDownloadListener fileDownloadListener = this.fileDownloadListener;
        if (fileDownloadListener instanceof FileDownloadLargeFileListener) {
            ((FileDownloadLargeFileListener) fileDownloadListener).connected(baseDownloadTask, str, z, j, j2);
        } else {
            fileDownloadListener.connected(baseDownloadTask, str, z, (int) j, (int) j2);
        }
    }

    @Override // com.liulishuo.filedownloader.CompatListenerAssist.CompatListenerAssistCallback
    public void progress(BaseDownloadTask baseDownloadTask, long j, long j2) {
        FileDownloadListener fileDownloadListener = this.fileDownloadListener;
        if (fileDownloadListener instanceof FileDownloadLargeFileListener) {
            ((FileDownloadLargeFileListener) fileDownloadListener).progress(baseDownloadTask, j, j2);
        } else {
            fileDownloadListener.progress(baseDownloadTask, (int) j, (int) j2);
        }
    }

    @Override // com.liulishuo.filedownloader.CompatListenerAssist.CompatListenerAssistCallback
    public void blockComplete(BaseDownloadTask baseDownloadTask) throws Throwable {
        this.fileDownloadListener.blockComplete(baseDownloadTask);
    }

    @Override // com.liulishuo.filedownloader.CompatListenerAssist.CompatListenerAssistCallback
    public void retry(BaseDownloadTask baseDownloadTask, Throwable th, int i, long j) {
        FileDownloadListener fileDownloadListener = this.fileDownloadListener;
        if (fileDownloadListener instanceof FileDownloadLargeFileListener) {
            ((FileDownloadLargeFileListener) fileDownloadListener).retry(baseDownloadTask, th, i, j);
        } else {
            fileDownloadListener.retry(baseDownloadTask, th, i, (int) j);
        }
    }

    @Override // com.liulishuo.filedownloader.CompatListenerAssist.CompatListenerAssistCallback
    public void completed(BaseDownloadTask baseDownloadTask) {
        this.fileDownloadListener.completed(baseDownloadTask);
    }

    @Override // com.liulishuo.filedownloader.CompatListenerAssist.CompatListenerAssistCallback
    public void paused(BaseDownloadTask baseDownloadTask, long j, long j2) {
        FileDownloadListener fileDownloadListener = this.fileDownloadListener;
        if (fileDownloadListener instanceof FileDownloadLargeFileListener) {
            ((FileDownloadLargeFileListener) fileDownloadListener).paused(baseDownloadTask, j, j2);
        } else {
            fileDownloadListener.paused(baseDownloadTask, (int) j, (int) j2);
        }
    }

    @Override // com.liulishuo.filedownloader.CompatListenerAssist.CompatListenerAssistCallback
    public void error(BaseDownloadTask baseDownloadTask, Throwable th) {
        this.fileDownloadListener.error(baseDownloadTask, th);
    }

    @Override // com.liulishuo.filedownloader.CompatListenerAssist.CompatListenerAssistCallback
    public void warn(BaseDownloadTask baseDownloadTask) {
        this.fileDownloadListener.warn(baseDownloadTask);
    }
}
