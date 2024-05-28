package com.vivo.push.p373f;

import android.content.Context;
import com.vivo.push.p368b.OnAppReceiveCommand;
import com.vivo.push.sdk.PushMessageCallback;

/* compiled from: OnUnBindAppReceiveTask.java */
/* renamed from: com.vivo.push.f.af */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class RunnableC10947af implements Runnable {

    /* renamed from: a */
    final /* synthetic */ OnAppReceiveCommand f20984a;

    /* renamed from: b */
    final /* synthetic */ OnUnBindAppReceiveTask f20985b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10947af(OnUnBindAppReceiveTask onUnBindAppReceiveTask, OnAppReceiveCommand onAppReceiveCommand) {
        this.f20985b = onUnBindAppReceiveTask;
        this.f20984a = onAppReceiveCommand;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context;
        PushMessageCallback pushMessageCallback = this.f20985b.f20972b;
        context = this.f20985b.f21149a;
        pushMessageCallback.onUnBind(context, this.f20984a.m5769i(), this.f20984a.m5800d());
    }
}
