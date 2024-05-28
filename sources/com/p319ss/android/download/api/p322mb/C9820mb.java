package com.p319ss.android.download.api.p322mb;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;
import com.bytedance.applog.tracker.Tracker;
import com.p319ss.android.download.api.config.InterfaceC9800je;
import com.p319ss.android.download.api.download.DownloadModel;
import com.p319ss.android.download.api.model.DownloadAlertDialogInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.download.api.mb.mb */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C9820mb implements InterfaceC9800je {
    @Override // com.p319ss.android.download.api.config.InterfaceC9800je
    /* renamed from: mb */
    public void mo7905mb(int i, @Nullable Context context, DownloadModel downloadModel, String str, Drawable drawable, int i2) {
        Toast.makeText(context, str, 0).show();
    }

    @Override // com.p319ss.android.download.api.config.InterfaceC9800je
    /* renamed from: ox */
    public Dialog mo7903ox(@NonNull DownloadAlertDialogInfo downloadAlertDialogInfo) {
        return m7904mb(downloadAlertDialogInfo);
    }

    /* renamed from: mb */
    private static Dialog m7904mb(final DownloadAlertDialogInfo downloadAlertDialogInfo) {
        if (downloadAlertDialogInfo == null) {
            return null;
        }
        AlertDialog show = new AlertDialog.Builder(downloadAlertDialogInfo.f18776mb).setTitle(downloadAlertDialogInfo.f18777ox).setMessage(downloadAlertDialogInfo.f18771b).setPositiveButton(downloadAlertDialogInfo.f18773hj, new DialogInterface.OnClickListener() { // from class: com.ss.android.download.api.mb.mb.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Tracker.onClick(dialogInterface, i);
                if (DownloadAlertDialogInfo.this.f18779ww != null) {
                    DownloadAlertDialogInfo.this.f18779ww.mo7140mb(dialogInterface);
                }
            }
        }).setNegativeButton(downloadAlertDialogInfo.f18772h, new DialogInterface.OnClickListener() { // from class: com.ss.android.download.api.mb.mb.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Tracker.onClick(dialogInterface, i);
                if (DownloadAlertDialogInfo.this.f18779ww != null) {
                    DownloadAlertDialogInfo.this.f18779ww.mo7139ox(dialogInterface);
                }
            }
        }).show();
        show.setCanceledOnTouchOutside(downloadAlertDialogInfo.f18778u);
        show.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.ss.android.download.api.mb.mb.3
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                if (DownloadAlertDialogInfo.this.f18779ww != null) {
                    DownloadAlertDialogInfo.this.f18779ww.mo7141b(dialogInterface);
                }
            }
        });
        if (downloadAlertDialogInfo.f18774ko != null) {
            show.setIcon(downloadAlertDialogInfo.f18774ko);
        }
        return show;
    }
}
