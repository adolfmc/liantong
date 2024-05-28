package com.vivo.push.p373f;

import com.vivo.push.PushClientManager;
import com.vivo.push.PushClientThread;
import com.vivo.push.PushCommand;
import com.vivo.push.p368b.OnAppReceiveCommand;
import com.vivo.push.util.LogUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.vivo.push.f.ae */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class OnUnBindAppReceiveTask extends OnReceiveTask {
    /* JADX INFO: Access modifiers changed from: package-private */
    public OnUnBindAppReceiveTask(PushCommand pushCommand) {
        super(pushCommand);
    }

    @Override // com.vivo.push.PushClientTask
    /* renamed from: a */
    public final void mo5492a(PushCommand pushCommand) {
        OnAppReceiveCommand onAppReceiveCommand = (OnAppReceiveCommand) pushCommand;
        LogUtil.m5342c("OnUnBindTask", "doTask,解订阅APP结果 = " + onAppReceiveCommand.m5769i() + " clientToken= " + onAppReceiveCommand.m5799e());
        PushClientManager.m5648a().m5635a(onAppReceiveCommand.m5770h(), onAppReceiveCommand.m5769i(), new Object[0]);
        PushClientThread.m5481b(new RunnableC10947af(this, onAppReceiveCommand));
    }
}
