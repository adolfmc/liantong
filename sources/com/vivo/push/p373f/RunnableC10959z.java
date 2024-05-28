package com.vivo.push.p373f;

import android.content.Context;
import com.vivo.push.p368b.OnPublishReceiveCommand;
import com.vivo.push.sdk.PushMessageCallback;

/* compiled from: OnPublishReceiveTask.java */
/* renamed from: com.vivo.push.f.z */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class RunnableC10959z implements Runnable {

    /* renamed from: a */
    final /* synthetic */ OnPublishReceiveCommand f21012a;

    /* renamed from: b */
    final /* synthetic */ OnPublishReceiveTask f21013b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10959z(OnPublishReceiveTask onPublishReceiveTask, OnPublishReceiveCommand onPublishReceiveCommand) {
        this.f21013b = onPublishReceiveTask;
        this.f21012a = onPublishReceiveCommand;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context;
        PushMessageCallback pushMessageCallback = this.f21013b.f20972b;
        context = this.f21013b.f21149a;
        pushMessageCallback.onPublish(context, this.f21012a.m5769i(), this.f21012a.m5770h());
    }
}
