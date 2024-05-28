package com.vivo.push.sdk;

import android.content.Context;
import com.vivo.push.PushClientManager;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.restructure.p375a.ReceivedMessage;
import com.vivo.push.util.LogUtil;

/* compiled from: CommandWorker.java */
/* renamed from: com.vivo.push.sdk.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class RunnableC10981b implements Runnable {

    /* renamed from: a */
    final /* synthetic */ ReceivedMessage f21159a;

    /* renamed from: b */
    final /* synthetic */ CommandWorker f21160b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10981b(CommandWorker commandWorker, ReceivedMessage receivedMessage) {
        this.f21160b = commandWorker;
        this.f21159a = receivedMessage;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context;
        LogUtil.m5341d("CommandWorker", " handleMessage type: ".concat(String.valueOf(this.f21159a.mo5554j())));
        PushClientManager m5648a = PushClientManager.m5648a();
        context = this.f21160b.f20863a;
        m5648a.m5646a(context);
        PushClientController.m5593a().m5589d().m5575a(this.f21159a);
    }
}
