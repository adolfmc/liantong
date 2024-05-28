package com.p319ss.android.socialbase.downloader.depend;

import com.p319ss.android.socialbase.downloader.constants.BoundType;
import com.p319ss.android.socialbase.downloader.model.DownloadTask;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadTaskExecuteListener */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IDownloadTaskExecuteListener {
    void onFinish(DownloadTask downloadTask, @BoundType int i);

    void onStart(DownloadTask downloadTask, @BoundType int i);
}
