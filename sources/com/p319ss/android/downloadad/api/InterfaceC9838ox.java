package com.p319ss.android.downloadad.api;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import com.p319ss.android.download.api.config.IDownloadButtonClickListener;
import com.p319ss.android.download.api.download.DownloadController;
import com.p319ss.android.download.api.download.DownloadEventConfig;
import com.p319ss.android.download.api.download.DownloadModel;
import com.p319ss.android.download.api.download.DownloadStatusChangeListener;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadad.api.ox */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface InterfaceC9838ox {
    /* renamed from: mb */
    Dialog mo7153mb(Context context, String str, boolean z, DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController, DownloadStatusChangeListener downloadStatusChangeListener, int i);

    /* renamed from: mb */
    Dialog mo7152mb(Context context, String str, boolean z, DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController, DownloadStatusChangeListener downloadStatusChangeListener, int i, IDownloadButtonClickListener iDownloadButtonClickListener);

    /* renamed from: mb */
    boolean mo7159mb(long j);

    /* renamed from: mb */
    boolean mo7158mb(long j, int i);

    /* renamed from: mb */
    boolean mo7156mb(Context context, long j, String str, DownloadStatusChangeListener downloadStatusChangeListener, int i);

    /* renamed from: mb */
    boolean mo7155mb(Context context, Uri uri, DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController);

    /* renamed from: mb */
    boolean mo7154mb(Context context, Uri uri, DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController, IDownloadButtonClickListener iDownloadButtonClickListener);
}
