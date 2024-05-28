package com.p319ss.android.socialbase.downloader.downloader;

import android.util.SparseArray;
import com.p319ss.android.socialbase.downloader.model.DownloadChunk;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.p342db.ISqlCacheLoadCompleteCallbackAidl;
import com.p319ss.android.socialbase.downloader.p342db.SqlCacheLoadCompleteCallback;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.downloader.ISqlDownloadCache */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface ISqlDownloadCache extends IDownloadCache {
    void init(SparseArray<DownloadInfo> sparseArray, SparseArray<List<DownloadChunk>> sparseArray2, SqlCacheLoadCompleteCallback sqlCacheLoadCompleteCallback);

    void setInitCallback(ISqlCacheLoadCompleteCallbackAidl iSqlCacheLoadCompleteCallbackAidl);
}
