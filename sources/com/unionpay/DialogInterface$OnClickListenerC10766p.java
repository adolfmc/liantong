package com.unionpay;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.bytedance.applog.tracker.Tracker;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.unionpay.p */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class DialogInterface$OnClickListenerC10766p implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ UPPayWapActivity f20695a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterface$OnClickListenerC10766p(UPPayWapActivity uPPayWapActivity) {
        this.f20695a = uPPayWapActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        AlertDialog alertDialog;
        Tracker.onClick(dialogInterface, i);
        alertDialog = this.f20695a.f20629d;
        alertDialog.dismiss();
    }
}
