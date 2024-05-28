package com.p319ss.android.download.api.download.p321mb;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.p319ss.android.download.api.download.DownloadController;
import com.p319ss.android.download.api.download.DownloadEventConfig;
import com.p319ss.android.download.api.download.DownloadModel;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.download.api.download.mb.mb */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface InterfaceC9817mb {
    /* renamed from: mb */
    void mo7910mb(@NonNull DownloadModel downloadModel, @Nullable DownloadController downloadController, @Nullable DownloadEventConfig downloadEventConfig);

    /* renamed from: mb */
    void mo7909mb(@NonNull DownloadInfo downloadInfo);

    /* renamed from: mb */
    void mo7908mb(@NonNull DownloadInfo downloadInfo, BaseException baseException, String str);

    /* renamed from: mb */
    void mo7907mb(@NonNull DownloadInfo downloadInfo, String str);

    /* renamed from: ox */
    void mo7906ox(@Nullable DownloadInfo downloadInfo, String str);
}
