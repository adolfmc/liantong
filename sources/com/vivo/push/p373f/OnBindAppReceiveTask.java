package com.vivo.push.p373f;

import com.vivo.push.PushClientManager;
import com.vivo.push.PushClientThread;
import com.vivo.push.PushCommand;
import com.vivo.push.p368b.OnAppReceiveCommand;
import com.vivo.push.util.LogUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.vivo.push.f.h */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class OnBindAppReceiveTask extends OnReceiveTask {
    /* JADX INFO: Access modifiers changed from: package-private */
    public OnBindAppReceiveTask(PushCommand pushCommand) {
        super(pushCommand);
    }

    @Override // com.vivo.push.PushClientTask
    /* renamed from: a */
    public final void mo5492a(PushCommand pushCommand) {
        OnAppReceiveCommand onAppReceiveCommand = (OnAppReceiveCommand) pushCommand;
        String m5799e = onAppReceiveCommand.m5799e();
        LogUtil.m5341d("OnBindTask", "doTask,订阅APP结果 = " + onAppReceiveCommand.m5769i() + " clientToken= " + m5799e);
        PushClientManager.m5648a().m5635a(onAppReceiveCommand.m5770h(), onAppReceiveCommand.m5769i(), m5799e);
        PushClientThread.m5481b(new RunnableC10951i(this, m5799e, onAppReceiveCommand));
    }
}
