package com.liulishuo.okdownload.core.listener;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.listener.assist.Listener1Assist;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class DownloadListener3 extends DownloadListener1 {
    protected abstract void canceled(@NonNull DownloadTask downloadTask);

    protected abstract void completed(@NonNull DownloadTask downloadTask);

    protected abstract void error(@NonNull DownloadTask downloadTask, @NonNull Exception exc);

    protected abstract void started(@NonNull DownloadTask downloadTask);

    protected abstract void warn(@NonNull DownloadTask downloadTask);

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
    public final void taskStart(@NonNull DownloadTask downloadTask, @NonNull Listener1Assist.Listener1Model listener1Model) {
        started(downloadTask);
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
    public void taskEnd(@NonNull DownloadTask downloadTask, @NonNull EndCause endCause, @Nullable Exception exc, @NonNull Listener1Assist.Listener1Model listener1Model) {
        switch (endCause) {
            case COMPLETED:
                completed(downloadTask);
                return;
            case CANCELED:
                canceled(downloadTask);
                return;
            case ERROR:
            case PRE_ALLOCATE_FAILED:
                error(downloadTask, exc);
                return;
            case FILE_BUSY:
            case SAME_TASK_BUSY:
                warn(downloadTask);
                return;
            default:
                Util.m13738w("DownloadListener3", "Don't support " + endCause);
                return;
        }
    }
}
