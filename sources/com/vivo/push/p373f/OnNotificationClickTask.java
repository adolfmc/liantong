package com.vivo.push.p373f;

import com.vivo.push.PushCommand;
import com.vivo.push.util.PushPackageUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.vivo.push.f.x */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class OnNotificationClickTask extends OnReceiveTask {
    /* JADX INFO: Access modifiers changed from: package-private */
    public OnNotificationClickTask(PushCommand pushCommand) {
        super(pushCommand);
    }

    @Override // com.vivo.push.PushClientTask
    /* renamed from: a */
    public final void mo5492a(PushCommand pushCommand) {
        if (PushPackageUtils.m5464c(this.f21149a, this.f21149a.getPackageName())) {
            NotifyOpenClientClickTask notifyOpenClientClickTask = new NotifyOpenClientClickTask(pushCommand);
            notifyOpenClientClickTask.m5700a(this.f20972b);
            notifyOpenClientClickTask.mo5492a(pushCommand);
            return;
        }
        NotifyInnerClientClickTask notifyInnerClientClickTask = new NotifyInnerClientClickTask(pushCommand);
        notifyInnerClientClickTask.m5700a(this.f20972b);
        notifyInnerClientClickTask.mo5492a(pushCommand);
    }
}
