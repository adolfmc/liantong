package com.p319ss.android.socialbase.downloader.depend;

import com.p319ss.android.socialbase.downloader.model.DownloadInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener */
/* loaded from: E:\567196_dexfile_execute.dex */
public interface IDownloadNotificationEventListener {
    String getNotifyProcessName();

    boolean interceptAfterNotificationSuccess(boolean z);

    void onNotificationEvent(int i, DownloadInfo downloadInfo, String str, String str2);
}
