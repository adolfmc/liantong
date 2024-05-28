package com.vivo.push.p373f;

import android.content.Context;
import android.text.TextUtils;
import com.vivo.push.PushClientManager;
import com.vivo.push.PushClientThread;
import com.vivo.push.PushCommand;
import com.vivo.push.cache.ClientConfigManagerImpl;
import com.vivo.push.model.InsideNotificationItem;
import com.vivo.push.p368b.MsgArriveCommand;
import com.vivo.push.p368b.OnNotifyArrivedReceiveCommand;
import com.vivo.push.p368b.ReporterCommand;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.util.ClientReportUtil;
import com.vivo.push.util.LogUtil;
import com.vivo.push.util.PushPackageUtils;
import com.vivo.push.util.Utility;
import java.util.HashMap;

/* renamed from: com.vivo.push.f.u */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class OnNotificationArrivedReceiveTask extends OnReceiveTask {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: OnNotificationArrivedReceiveTask.java */
    /* renamed from: com.vivo.push.f.u$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC10956a {
        /* renamed from: a */
        void mo5668a();

        /* renamed from: b */
        void mo5667b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OnNotificationArrivedReceiveTask(PushCommand pushCommand) {
        super(pushCommand);
    }

    @Override // com.vivo.push.PushClientTask
    /* renamed from: a */
    public final void mo5492a(PushCommand pushCommand) {
        if (pushCommand == null) {
            LogUtil.m5354a("OnNotificationArrivedTask", "command is null");
            return;
        }
        boolean isEnablePush = ClientConfigManagerImpl.getInstance(this.f21149a).isEnablePush();
        OnNotifyArrivedReceiveCommand onNotifyArrivedReceiveCommand = (OnNotifyArrivedReceiveCommand) pushCommand;
        if (!PushPackageUtils.m5463d(this.f21149a, this.f21149a.getPackageName())) {
            ReporterCommand reporterCommand = new ReporterCommand(2101L);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("messageID", String.valueOf(onNotifyArrivedReceiveCommand.m5764f()));
            String mo5543a = PushClientController.m5593a().m5588e().mo5543a();
            if (!TextUtils.isEmpty(mo5543a)) {
                hashMap.put("remoteAppId", mo5543a);
            }
            reporterCommand.m5761a(hashMap);
            PushClientManager.m5648a().m5638a(reporterCommand);
            return;
        }
        PushClientManager.m5648a().m5638a(new MsgArriveCommand(String.valueOf(onNotifyArrivedReceiveCommand.m5764f())));
        LogUtil.m5341d("OnNotificationArrivedTask", "PushMessageReceiver " + this.f21149a.getPackageName() + " isEnablePush :" + isEnablePush);
        if (!isEnablePush) {
            ReporterCommand reporterCommand2 = new ReporterCommand(1020L);
            HashMap<String, String> hashMap2 = new HashMap<>();
            hashMap2.put("messageID", String.valueOf(onNotifyArrivedReceiveCommand.m5764f()));
            String mo5543a2 = PushClientController.m5593a().m5588e().mo5543a();
            if (!TextUtils.isEmpty(mo5543a2)) {
                hashMap2.put("remoteAppId", mo5543a2);
            }
            reporterCommand2.m5761a(hashMap2);
            PushClientManager.m5648a().m5638a(reporterCommand2);
        } else if (PushClientManager.m5648a().m5615g() && !m5699a(Utility.m5434c(this.f21149a), onNotifyArrivedReceiveCommand.m5771e(), onNotifyArrivedReceiveCommand.m5763g())) {
            ReporterCommand reporterCommand3 = new ReporterCommand(1021L);
            HashMap<String, String> hashMap3 = new HashMap<>();
            hashMap3.put("messageID", String.valueOf(onNotifyArrivedReceiveCommand.m5764f()));
            String mo5543a3 = PushClientController.m5593a().m5588e().mo5543a();
            if (!TextUtils.isEmpty(mo5543a3)) {
                hashMap3.put("remoteAppId", mo5543a3);
            }
            reporterCommand3.m5761a(hashMap3);
            PushClientManager.m5648a().m5638a(reporterCommand3);
        } else {
            InsideNotificationItem m5772d = onNotifyArrivedReceiveCommand.m5772d();
            if (m5772d != null) {
                int targetType = m5772d.getTargetType();
                String tragetContent = m5772d.getTragetContent();
                LogUtil.m5341d("OnNotificationArrivedTask", "tragetType is " + targetType + " ; target is " + tragetContent);
                PushClientThread.m5480c(new RunnableC10957v(this, m5772d, onNotifyArrivedReceiveCommand));
                return;
            }
            LogUtil.m5354a("OnNotificationArrivedTask", "notify is null");
            Context context = this.f21149a;
            LogUtil.m5343c(context, "通知内容为空，" + onNotifyArrivedReceiveCommand.m5764f());
            ClientReportUtil.m5406a(onNotifyArrivedReceiveCommand.m5764f(), 1027L);
        }
    }
}
