package com.vivo.push.p373f;

import android.content.Context;
import com.vivo.push.model.UPSNotificationMessage;
import com.vivo.push.sdk.PushMessageCallback;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NotifyOpenClientClickTask.java */
/* renamed from: com.vivo.push.f.g */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class RunnableC10950g implements Runnable {

    /* renamed from: a */
    final /* synthetic */ UPSNotificationMessage f20989a;

    /* renamed from: b */
    final /* synthetic */ NotifyOpenClientClickTask f20990b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10950g(NotifyOpenClientClickTask notifyOpenClientClickTask, UPSNotificationMessage uPSNotificationMessage) {
        this.f20990b = notifyOpenClientClickTask;
        this.f20989a = uPSNotificationMessage;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context;
        PushMessageCallback pushMessageCallback = this.f20990b.f20972b;
        context = this.f20990b.f21149a;
        pushMessageCallback.onNotificationMessageClicked(context, this.f20989a);
    }
}
