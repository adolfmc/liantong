package com.vivo.push.p373f;

import com.vivo.push.PushClientTask;
import com.vivo.push.PushCommand;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.vivo.push.f.ah */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class PushClientTaskFactory {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: a */
    public static PushClientTask m5693a(PushCommand pushCommand) {
        int m5326b = pushCommand.m5326b();
        if (m5326b != 20) {
            switch (m5326b) {
                case 0:
                    break;
                case 1:
                    return new OnSetTagsReceiveTask(pushCommand);
                case 2:
                    return new OnDelTagsReceiveTask(pushCommand);
                case 3:
                    return new OnMessageReceiveTask(pushCommand);
                case 4:
                    return new OnNotificationArrivedReceiveTask(pushCommand);
                case 5:
                    return new OnNotificationClickTask(pushCommand);
                case 6:
                    return new OnPublishReceiveTask(pushCommand);
                case 7:
                    return new OnLogReceiveTask(pushCommand);
                case 8:
                    return new OnListTagReceiveTask(pushCommand);
                case 9:
                    return new OnClearCacheReceiveTask(pushCommand);
                case 10:
                    return new OnBindAppReceiveTask(pushCommand);
                case 11:
                    return new OnUnBindAppReceiveTask(pushCommand);
                case 12:
                    return new OnChangePushStatusReceiveTask(pushCommand);
                default:
                    switch (m5326b) {
                        case 100:
                            return new ChangeNetPermissionTask(pushCommand);
                        case 101:
                            return new C10948c(pushCommand);
                        default:
                            switch (m5326b) {
                                case 2000:
                                case 2001:
                                case 2002:
                                case 2003:
                                case 2004:
                                case 2005:
                                case 2008:
                                case 2009:
                                case 2010:
                                case 2011:
                                case 2012:
                                case 2013:
                                case 2014:
                                case 2015:
                                    break;
                                case 2006:
                                    return new BindAppSendCommandTask(pushCommand);
                                case 2007:
                                    return new UnbindAppSendCommandTask(pushCommand);
                                default:
                                    return null;
                            }
                    }
            }
            return new SendCommandTask(pushCommand);
        }
        return new OnUndoMsgReceiveTask(pushCommand);
    }

    /* renamed from: b */
    public static OnReceiveTask m5692b(PushCommand pushCommand) {
        int m5326b = pushCommand.m5326b();
        if (m5326b != 20) {
            if (m5326b != 2016) {
                switch (m5326b) {
                    case 1:
                        return new OnSetTagsReceiveTask(pushCommand);
                    case 2:
                        return new OnDelTagsReceiveTask(pushCommand);
                    case 3:
                        return new OnMessageReceiveTask(pushCommand);
                    case 4:
                        return new OnNotificationArrivedReceiveTask(pushCommand);
                    case 5:
                        return new OnNotificationClickTask(pushCommand);
                    case 6:
                        return new OnPublishReceiveTask(pushCommand);
                    case 7:
                        return new OnLogReceiveTask(pushCommand);
                    case 8:
                        return new OnListTagReceiveTask(pushCommand);
                    case 9:
                        return new OnClearCacheReceiveTask(pushCommand);
                    case 10:
                        return new OnBindAppReceiveTask(pushCommand);
                    case 11:
                        return new OnUnBindAppReceiveTask(pushCommand);
                    default:
                        return null;
                }
            }
            return new OnDispatcherReceiveTask(pushCommand);
        }
        return new OnUndoMsgReceiveTask(pushCommand);
    }
}
