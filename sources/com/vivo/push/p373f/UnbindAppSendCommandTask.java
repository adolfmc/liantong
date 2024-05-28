package com.vivo.push.p373f;

import com.vivo.push.PushClientManager;
import com.vivo.push.PushClientTask;
import com.vivo.push.PushCommand;
import com.vivo.push.model.PushPackageInfo;
import com.vivo.push.p367a.CommandBridge;
import com.vivo.push.p368b.BaseAppCommand;
import com.vivo.push.p368b.ConnectConfigUpdateCommand;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.util.OperateUtil;
import com.vivo.push.util.PushPackageUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.vivo.push.f.aj */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class UnbindAppSendCommandTask extends PushClientTask {
    /* JADX INFO: Access modifiers changed from: package-private */
    public UnbindAppSendCommandTask(PushCommand pushCommand) {
        super(pushCommand);
    }

    @Override // com.vivo.push.PushClientTask
    /* renamed from: a */
    public final void mo5492a(PushCommand pushCommand) {
        BaseAppCommand baseAppCommand = (BaseAppCommand) pushCommand;
        PushPackageInfo m5470a = PushPackageUtils.m5470a(this.f21149a, PushClientController.m5593a().m5587f());
        if (m5470a == null) {
            PushClientManager.m5648a().m5635a(baseAppCommand.m5803f(), 1005, new Object[0]);
            return;
        }
        String m5602a = m5470a.m5602a();
        if (m5470a.m5595c()) {
            PushClientManager.m5648a().m5635a(baseAppCommand.m5803f(), 1004, new Object[0]);
            pushCommand = new ConnectConfigUpdateCommand();
        } else {
            int m5334a = OperateUtil.m5334a(baseAppCommand);
            if (m5334a != 0) {
                PushClientManager.m5648a().m5635a(baseAppCommand.m5803f(), m5334a, new Object[0]);
                return;
            }
        }
        CommandBridge.m5815a(this.f21149a, m5602a, pushCommand);
    }
}
