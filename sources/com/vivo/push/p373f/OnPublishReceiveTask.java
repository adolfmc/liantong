package com.vivo.push.p373f;

import com.vivo.push.PushClientThread;
import com.vivo.push.PushCommand;
import com.vivo.push.p368b.OnPublishReceiveCommand;

/* renamed from: com.vivo.push.f.y */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class OnPublishReceiveTask extends OnReceiveTask {
    /* JADX INFO: Access modifiers changed from: package-private */
    public OnPublishReceiveTask(PushCommand pushCommand) {
        super(pushCommand);
    }

    @Override // com.vivo.push.PushClientTask
    /* renamed from: a */
    public final void mo5492a(PushCommand pushCommand) {
        PushClientThread.m5481b(new RunnableC10959z(this, (OnPublishReceiveCommand) pushCommand));
    }
}
