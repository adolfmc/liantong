package com.p319ss.android.socialbase.downloader.depend;

import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadCompleteHandler */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IDownloadCompleteHandler {
    void handle(DownloadInfo downloadInfo) throws BaseException;

    boolean needHandle(DownloadInfo downloadInfo);
}
