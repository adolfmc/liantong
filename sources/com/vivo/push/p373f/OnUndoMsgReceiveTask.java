package com.vivo.push.p373f;

import android.content.Context;
import android.text.TextUtils;
import com.vivo.push.PushClientManager;
import com.vivo.push.PushCommand;
import com.vivo.push.p368b.OnUndoMsgReceiveCommand;
import com.vivo.push.p368b.ReporterCommand;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.util.ClientReportUtil;
import com.vivo.push.util.LogUtil;
import com.vivo.push.util.NotifyAdapterUtil;
import com.vivo.push.util.Utility;
import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.vivo.push.f.ag */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class OnUndoMsgReceiveTask extends OnReceiveTask {
    /* JADX INFO: Access modifiers changed from: package-private */
    public OnUndoMsgReceiveTask(PushCommand pushCommand) {
        super(pushCommand);
    }

    @Override // com.vivo.push.PushClientTask
    /* renamed from: a */
    public final void mo5492a(PushCommand pushCommand) {
        OnUndoMsgReceiveCommand onUndoMsgReceiveCommand = (OnUndoMsgReceiveCommand) pushCommand;
        if (PushClientManager.m5648a().m5615g() && !m5699a(Utility.m5434c(this.f21149a), onUndoMsgReceiveCommand.m5765e(), onUndoMsgReceiveCommand.m5763g())) {
            LogUtil.m5341d("OnUndoMsgTask", " vertify msg is error ");
            ReporterCommand reporterCommand = new ReporterCommand(1021L);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("messageID", String.valueOf(onUndoMsgReceiveCommand.m5764f()));
            String mo5543a = PushClientController.m5593a().m5588e().mo5543a();
            if (!TextUtils.isEmpty(mo5543a)) {
                hashMap.put("remoteAppId", mo5543a);
            }
            reporterCommand.m5761a(hashMap);
            PushClientManager.m5648a().m5638a(reporterCommand);
            return;
        }
        boolean repealNotifyById = NotifyAdapterUtil.repealNotifyById(this.f21149a, onUndoMsgReceiveCommand.m5766d());
        LogUtil.m5341d("OnUndoMsgTask", "undo message " + onUndoMsgReceiveCommand.m5766d() + ", " + repealNotifyById);
        if (repealNotifyById) {
            Context context = this.f21149a;
            LogUtil.m5348b(context, "回收client通知成功, 上报埋点 1031, messageId = " + onUndoMsgReceiveCommand.m5766d());
            ClientReportUtil.m5406a(onUndoMsgReceiveCommand.m5766d(), 1031L);
            return;
        }
        LogUtil.m5341d("OnUndoMsgTask", "undo message fail，messageId = " + onUndoMsgReceiveCommand.m5766d());
        Context context2 = this.f21149a;
        LogUtil.m5343c(context2, "回收client通知失败，messageId = " + onUndoMsgReceiveCommand.m5766d());
    }
}
