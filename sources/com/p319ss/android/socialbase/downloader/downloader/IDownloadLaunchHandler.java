package com.p319ss.android.socialbase.downloader.downloader;

import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.downloader.IDownloadLaunchHandler */
/* loaded from: E:\567196_dexfile_execute.dex */
public interface IDownloadLaunchHandler {
    List<String> getResumeMimeTypes();

    void onLaunchResume(List<DownloadInfo> list, int i);
}
