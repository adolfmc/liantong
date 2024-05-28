package com.vivo.push.p373f;

import com.vivo.push.PushCommand;
import com.vivo.push.p368b.OnDispatcherReceiveCommand;
import com.vivo.push.util.SharePreferenceManager;

/* renamed from: com.vivo.push.f.o */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class OnDispatcherReceiveTask extends OnReceiveTask {
    /* JADX INFO: Access modifiers changed from: package-private */
    public OnDispatcherReceiveTask(PushCommand pushCommand) {
        super(pushCommand);
    }

    @Override // com.vivo.push.PushClientTask
    /* renamed from: a */
    public final void mo5492a(PushCommand pushCommand) {
        OnDispatcherReceiveCommand onDispatcherReceiveCommand = (OnDispatcherReceiveCommand) pushCommand;
        int m5796d = onDispatcherReceiveCommand.m5796d();
        int m5795e = onDispatcherReceiveCommand.m5795e();
        SharePreferenceManager.m5455b().m5419a("key_dispatch_environment", m5796d);
        SharePreferenceManager.m5455b().m5419a("key_dispatch_area", m5795e);
    }
}
