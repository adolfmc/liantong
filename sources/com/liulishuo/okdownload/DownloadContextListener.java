package com.liulishuo.okdownload;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.liulishuo.okdownload.core.cause.EndCause;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface DownloadContextListener {
    void queueEnd(@NonNull DownloadContext downloadContext);

    void taskEnd(@NonNull DownloadContext downloadContext, @NonNull DownloadTask downloadTask, @NonNull EndCause endCause, @Nullable Exception exc, int i);
}
