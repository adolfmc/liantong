package com.unionpay;

import android.content.DialogInterface;
import com.bytedance.applog.tracker.Tracker;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.unionpay.o */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class DialogInterface$OnClickListenerC10765o implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ UPPayWapActivity f20694a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterface$OnClickListenerC10765o(UPPayWapActivity uPPayWapActivity) {
        this.f20694a = uPPayWapActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        Tracker.onClick(dialogInterface, i);
        this.f20694a.m5974a("cancel", (String) null);
    }
}
