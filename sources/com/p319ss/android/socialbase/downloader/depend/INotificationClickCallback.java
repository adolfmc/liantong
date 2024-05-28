package com.p319ss.android.socialbase.downloader.depend;

import com.p319ss.android.socialbase.downloader.model.DownloadInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.depend.INotificationClickCallback */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface INotificationClickCallback {
    boolean onClickWhenInstalled(DownloadInfo downloadInfo);

    boolean onClickWhenSuccess(DownloadInfo downloadInfo);

    boolean onClickWhenUnSuccess(DownloadInfo downloadInfo);
}
