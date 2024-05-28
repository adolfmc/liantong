package com.vivo.push.p373f;

import com.vivo.push.PushClientTask;
import com.vivo.push.PushCommand;
import com.vivo.push.model.PushPackageInfo;
import com.vivo.push.p367a.CommandBridge;
import com.vivo.push.p368b.ChangeNetPermissionCommand;
import com.vivo.push.p368b.DefaultCommand;
import com.vivo.push.p368b.StopServiceCommand;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.util.PushPackageUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.vivo.push.f.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class ChangeNetPermissionTask extends PushClientTask {
    /* JADX INFO: Access modifiers changed from: package-private */
    public ChangeNetPermissionTask(PushCommand pushCommand) {
        super(pushCommand);
    }

    @Override // com.vivo.push.PushClientTask
    /* renamed from: a */
    public final void mo5492a(PushCommand pushCommand) {
        boolean m5684b;
        PushPackageInfo m5470a = PushPackageUtils.m5470a(this.f21149a, PushClientController.m5593a().m5587f());
        try {
            if (((ChangeNetPermissionCommand) pushCommand).m5802d()) {
                m5684b = OnChangePushStatusReceiveTask.m5685a(this.f21149a);
            } else {
                m5684b = OnChangePushStatusReceiveTask.m5684b(this.f21149a);
            }
            if (m5684b) {
                PushPackageInfo m5470a2 = PushPackageUtils.m5470a(this.f21149a, PushClientController.m5593a().m5587f());
                if (m5470a == null || m5470a2 == null || m5470a2.m5602a() == null || !m5470a2.m5602a().equals(m5470a.m5602a())) {
                    if (m5470a != null && m5470a.m5602a() != null) {
                        CommandBridge.m5815a(this.f21149a, m5470a.m5602a(), new StopServiceCommand(m5470a.m5602a()));
                    }
                    if (m5470a2 == null || m5470a2.m5602a() == null) {
                        return;
                    }
                    DefaultCommand defaultCommand = new DefaultCommand();
                    PushClientController.m5593a();
                    defaultCommand.m5801d();
                    CommandBridge.m5815a(this.f21149a, m5470a2.m5602a(), defaultCommand);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
