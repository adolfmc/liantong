package com.p319ss.android.download.api.config;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.p319ss.android.download.api.download.DownloadController;
import com.p319ss.android.download.api.download.DownloadModel;
import com.p319ss.android.download.api.download.DownloadStatusChangeListener;
import com.p319ss.android.download.api.model.DownloadShortInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.download.api.config.mb */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C9806mb implements DownloadStatusChangeListener {
    @Override // com.p319ss.android.download.api.download.DownloadStatusChangeListener
    public void onDownloadActive(DownloadShortInfo downloadShortInfo, int i) {
    }

    @Override // com.p319ss.android.download.api.download.DownloadStatusChangeListener
    public void onDownloadFailed(DownloadShortInfo downloadShortInfo) {
    }

    @Override // com.p319ss.android.download.api.download.DownloadStatusChangeListener
    public void onDownloadFinished(DownloadShortInfo downloadShortInfo) {
    }

    @Override // com.p319ss.android.download.api.download.DownloadStatusChangeListener
    public void onDownloadPaused(DownloadShortInfo downloadShortInfo, int i) {
    }

    @Override // com.p319ss.android.download.api.download.DownloadStatusChangeListener
    public void onDownloadStart(@NonNull DownloadModel downloadModel, @Nullable DownloadController downloadController) {
    }

    @Override // com.p319ss.android.download.api.download.DownloadStatusChangeListener
    public void onIdle() {
    }

    @Override // com.p319ss.android.download.api.download.DownloadStatusChangeListener
    public void onInstalled(DownloadShortInfo downloadShortInfo) {
    }
}
