package com.p319ss.android.socialbase.downloader.thread;

import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.exception.RetryCheckStatus;
import com.p319ss.android.socialbase.downloader.exception.RetryThrowable;
import com.p319ss.android.socialbase.downloader.model.DownloadChunk;
import com.p319ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IDownloadRunnableCallback {
    boolean canRetry(BaseException baseException);

    void checkSpaceOverflow(long j) throws BaseException;

    DownloadChunk getUnCompletedSubChunk(int i);

    void handleFirstConnection(String str, IDownloadHeadHttpConnection iDownloadHeadHttpConnection, long j) throws BaseException, RetryThrowable;

    void onAllChunkRetryWithReset(BaseException baseException, boolean z);

    void onChunkDowngradeRetry(BaseException baseException);

    void onCompleted(DownloadChunkRunnable downloadChunkRunnable);

    void onError(BaseException baseException);

    boolean onProgress(long j) throws BaseException;

    RetryCheckStatus onRetry(BaseException baseException, long j);

    RetryCheckStatus onSingleChunkRetry(DownloadChunk downloadChunk, BaseException baseException, long j);

    void setHttpResponseStatus(IDownloadHeadHttpConnection iDownloadHeadHttpConnection);
}
