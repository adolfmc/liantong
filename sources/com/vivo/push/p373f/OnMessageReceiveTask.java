package com.vivo.push.p373f;

import com.vivo.push.PushClientManager;
import com.vivo.push.PushCommand;
import com.vivo.push.cache.ClientConfigManagerImpl;
import com.vivo.push.model.UnvarnishedMessage;
import com.vivo.push.p368b.MsgArriveCommand;
import com.vivo.push.p368b.OnMessageReceiveCommand;
import com.vivo.push.util.LogUtil;
import com.vivo.push.util.Utility;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.vivo.push.f.t */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class OnMessageReceiveTask extends OnReceiveTask {
    /* JADX INFO: Access modifiers changed from: package-private */
    public OnMessageReceiveTask(PushCommand pushCommand) {
        super(pushCommand);
    }

    @Override // com.vivo.push.PushClientTask
    /* renamed from: a */
    public final void mo5492a(PushCommand pushCommand) {
        OnMessageReceiveCommand onMessageReceiveCommand = (OnMessageReceiveCommand) pushCommand;
        PushClientManager.m5648a().m5638a(new MsgArriveCommand(String.valueOf(onMessageReceiveCommand.m5764f())));
        if (!ClientConfigManagerImpl.getInstance(this.f21149a).isEnablePush()) {
            LogUtil.m5341d("OnMessageTask", "command  " + pushCommand + " is ignore by disable push ");
            super.m5702a(1020);
        } else if (PushClientManager.m5648a().m5615g() && !m5699a(Utility.m5434c(this.f21149a), onMessageReceiveCommand.m5787d(), onMessageReceiveCommand.m5763g())) {
            super.m5702a(1021);
        } else {
            UnvarnishedMessage m5786e = onMessageReceiveCommand.m5786e();
            if (m5786e != null) {
                int targetType = m5786e.getTargetType();
                long msgId = m5786e.getMsgId();
                LogUtil.m5341d("OnMessageTask", "tragetType is " + targetType + " ; messageId is " + msgId);
                this.f20972b.onTransmissionMessage(this.f21149a, m5786e);
                super.m5702a(0);
                return;
            }
            super.m5702a(2807);
            LogUtil.m5354a("OnMessageTask", " message is null");
        }
    }
}
