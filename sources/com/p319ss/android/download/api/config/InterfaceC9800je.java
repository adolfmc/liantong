package com.p319ss.android.download.api.config;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.p319ss.android.download.api.download.DownloadModel;
import com.p319ss.android.download.api.model.DownloadAlertDialogInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.download.api.config.je */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface InterfaceC9800je {
    /* renamed from: mb */
    void mo7905mb(int i, @Nullable Context context, @Nullable DownloadModel downloadModel, String str, Drawable drawable, int i2);

    /* renamed from: ox */
    Dialog mo7903ox(@NonNull DownloadAlertDialogInfo downloadAlertDialogInfo);
}
