package com.vivo.push.p373f;

import com.vivo.push.PushClientManager;
import com.vivo.push.PushClientTask;
import com.vivo.push.PushCommand;
import com.vivo.push.cache.ClientConfigManagerImpl;
import com.vivo.push.model.PushPackageInfo;
import com.vivo.push.p367a.CommandBridge;
import com.vivo.push.p368b.BaseAppCommand;
import com.vivo.push.p368b.ConnectConfigUpdateCommand;
import com.vivo.push.p368b.PushModeCommand;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.util.BaseSharePreference;
import com.vivo.push.util.LogUtil;
import com.vivo.push.util.OperateUtil;
import com.vivo.push.util.PushPackageUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.vivo.push.f.ai */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class SendCommandTask extends PushClientTask {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SendCommandTask(PushCommand pushCommand) {
        super(pushCommand);
    }

    @Override // com.vivo.push.PushClientTask
    /* renamed from: a */
    public final void mo5492a(PushCommand pushCommand) {
        if (this.f21149a == null) {
            LogUtil.m5341d("SendCommandTask", "SendCommandTask " + pushCommand + " ; mContext is Null");
        } else if (pushCommand == null) {
            LogUtil.m5341d("SendCommandTask", "SendCommandTask pushCommand is Null");
        } else {
            PushPackageInfo m5470a = PushPackageUtils.m5470a(this.f21149a, PushClientController.m5593a().m5587f());
            int m5326b = pushCommand.m5326b();
            if (m5326b != 0) {
                if (m5326b == 2009) {
                    LogUtil.m5350a(ClientConfigManagerImpl.getInstance(this.f21149a).isDebug());
                    if (LogUtil.m5349b()) {
                        PushClientManager.m5648a();
                        PushClientController.m5593a().m5588e().mo5532e();
                        BaseSharePreference baseSharePreference = new BaseSharePreference();
                        baseSharePreference.m5422a(this.f21149a, "com.vivo.push_preferences.hybridapptoken_v1");
                        baseSharePreference.m5423a();
                        BaseSharePreference baseSharePreference2 = new BaseSharePreference();
                        baseSharePreference2.m5422a(this.f21149a, "com.vivo.push_preferences.appconfig_v1");
                        baseSharePreference2.m5423a();
                        if (!PushClientManager.m5648a().m5616f()) {
                            ClientConfigManagerImpl.getInstance(this.f21149a).clearPush();
                        }
                    }
                } else if (m5326b == 2011) {
                    LogUtil.m5350a(ClientConfigManagerImpl.getInstance(this.f21149a).isDebug(((PushModeCommand) pushCommand).m5762d()));
                } else {
                    switch (m5326b) {
                        case 2002:
                        case 2003:
                        case 2004:
                        case 2005:
                            if (m5470a == null || m5470a.m5595c()) {
                                PushClientManager.m5648a().m5636a(((BaseAppCommand) pushCommand).m5803f(), 1005);
                                break;
                            } else {
                                BaseAppCommand baseAppCommand = (BaseAppCommand) pushCommand;
                                int m5334a = OperateUtil.m5334a(baseAppCommand);
                                if (m5334a != 0) {
                                    PushClientManager.m5648a().m5636a(baseAppCommand.m5803f(), m5334a);
                                    return;
                                }
                            }
                            break;
                    }
                }
            }
            if (m5470a == null) {
                LogUtil.m5341d("SendCommandTask", "SendCommandTask " + pushCommand + " ; pushPkgInfo is Null");
                return;
            }
            String m5602a = m5470a.m5602a();
            if (m5470a.m5595c()) {
                try {
                    PushClientManager.m5648a().m5636a(((BaseAppCommand) pushCommand).m5803f(), 1004);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pushCommand = new ConnectConfigUpdateCommand();
                LogUtil.m5341d("SendCommandTask", "SendCommandTask " + pushCommand + " ; pkgName is InBlackList ");
            }
            CommandBridge.m5815a(this.f21149a, m5602a, pushCommand);
        }
    }
}
