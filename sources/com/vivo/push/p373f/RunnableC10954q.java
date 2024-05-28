package com.vivo.push.p373f;

import android.content.Context;
import com.vivo.push.p368b.OnListTagReceiveCommand;
import com.vivo.push.sdk.PushMessageCallback;

/* compiled from: OnListTagReceiveTask.java */
/* renamed from: com.vivo.push.f.q */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class RunnableC10954q implements Runnable {

    /* renamed from: a */
    final /* synthetic */ OnListTagReceiveCommand f21004a;

    /* renamed from: b */
    final /* synthetic */ OnListTagReceiveTask f21005b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10954q(OnListTagReceiveTask onListTagReceiveTask, OnListTagReceiveCommand onListTagReceiveCommand) {
        this.f21005b = onListTagReceiveTask;
        this.f21004a = onListTagReceiveCommand;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context;
        PushMessageCallback pushMessageCallback = this.f21005b.f20972b;
        context = this.f21005b.f21149a;
        pushMessageCallback.onListTags(context, this.f21004a.m5769i(), this.f21004a.m5794d(), this.f21004a.m5770h());
    }
}
