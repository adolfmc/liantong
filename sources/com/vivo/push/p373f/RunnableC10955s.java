package com.vivo.push.p373f;

import android.content.Context;
import com.vivo.push.p368b.OnLogReceiveCommand;
import com.vivo.push.sdk.PushMessageCallback;

/* compiled from: OnLogReceiveTask.java */
/* renamed from: com.vivo.push.f.s */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class RunnableC10955s implements Runnable {

    /* renamed from: a */
    final /* synthetic */ OnLogReceiveCommand f21006a;

    /* renamed from: b */
    final /* synthetic */ OnLogReceiveTask f21007b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10955s(OnLogReceiveTask onLogReceiveTask, OnLogReceiveCommand onLogReceiveCommand) {
        this.f21007b = onLogReceiveTask;
        this.f21006a = onLogReceiveCommand;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context;
        PushMessageCallback pushMessageCallback = this.f21007b.f20972b;
        context = this.f21007b.f21149a;
        pushMessageCallback.onLog(context, this.f21006a.m5791d(), this.f21006a.m5790e(), this.f21006a.m5789f());
    }
}
