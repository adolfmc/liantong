package com.vivo.push;

import android.content.Intent;
import com.vivo.push.p368b.OnAppReceiveCommand;
import com.vivo.push.p368b.OnChangePushStatusReceiveCommand;
import com.vivo.push.p368b.OnClearCacheReceiveCommand;
import com.vivo.push.p368b.OnDispatcherReceiveCommand;
import com.vivo.push.p368b.OnListTagReceiveCommand;
import com.vivo.push.p368b.OnLogReceiveCommand;
import com.vivo.push.p368b.OnMessageReceiveCommand;
import com.vivo.push.p368b.OnNotificationClickReceiveCommand;
import com.vivo.push.p368b.OnNotifyArrivedReceiveCommand;
import com.vivo.push.p368b.OnPublishReceiveCommand;
import com.vivo.push.p368b.OnTagsReceiveCommand;
import com.vivo.push.p368b.OnUndoMsgReceiveCommand;
import com.vivo.push.p373f.OnReceiveTask;
import com.vivo.push.p373f.PushClientTaskFactory;
import com.vivo.push.util.LogUtil;

/* renamed from: com.vivo.push.l */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class PushClientFactory implements IPushClientFactory {

    /* renamed from: a */
    private PushClientTaskFactory f21031a = new PushClientTaskFactory();

    @Override // com.vivo.push.IPushClientFactory
    public final PushCommand createReceiverCommand(Intent intent) {
        PushCommand onUndoMsgReceiveCommand;
        int intExtra = intent.getIntExtra("command", -1);
        if (intExtra < 0) {
            intExtra = intent.getIntExtra("method", -1);
        }
        if (intExtra == 20) {
            onUndoMsgReceiveCommand = new OnUndoMsgReceiveCommand();
        } else if (intExtra != 2016) {
            switch (intExtra) {
                case 1:
                case 2:
                    onUndoMsgReceiveCommand = new OnTagsReceiveCommand(intExtra);
                    break;
                case 3:
                    onUndoMsgReceiveCommand = new OnMessageReceiveCommand();
                    break;
                case 4:
                    onUndoMsgReceiveCommand = new OnNotifyArrivedReceiveCommand();
                    break;
                case 5:
                    onUndoMsgReceiveCommand = new OnNotificationClickReceiveCommand();
                    break;
                case 6:
                    onUndoMsgReceiveCommand = new OnPublishReceiveCommand();
                    break;
                case 7:
                    onUndoMsgReceiveCommand = new OnLogReceiveCommand();
                    break;
                case 8:
                    onUndoMsgReceiveCommand = new OnListTagReceiveCommand();
                    break;
                case 9:
                    onUndoMsgReceiveCommand = new OnClearCacheReceiveCommand();
                    break;
                case 10:
                case 11:
                    onUndoMsgReceiveCommand = new OnAppReceiveCommand(intExtra);
                    break;
                case 12:
                    onUndoMsgReceiveCommand = new OnChangePushStatusReceiveCommand();
                    break;
                default:
                    onUndoMsgReceiveCommand = null;
                    break;
            }
        } else {
            onUndoMsgReceiveCommand = new OnDispatcherReceiveCommand();
        }
        if (onUndoMsgReceiveCommand != null) {
            BundleWapper m5735a = BundleWapper.m5735a(intent);
            if (m5735a == null) {
                LogUtil.m5346b("PushCommand", "bundleWapper is null");
            } else {
                onUndoMsgReceiveCommand.m5324b(m5735a);
            }
        }
        return onUndoMsgReceiveCommand;
    }

    @Override // com.vivo.push.IPushClientFactory
    public final PushClientTask createTask(PushCommand pushCommand) {
        return PushClientTaskFactory.m5693a(pushCommand);
    }

    @Override // com.vivo.push.IPushClientFactory
    public final OnReceiveTask createReceiveTask(PushCommand pushCommand) {
        return PushClientTaskFactory.m5692b(pushCommand);
    }
}
