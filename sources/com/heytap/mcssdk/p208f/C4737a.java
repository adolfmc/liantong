package com.heytap.mcssdk.p208f;

import android.content.Context;
import com.heytap.mcssdk.PushService;
import com.heytap.mcssdk.p205c.C4720b;
import com.heytap.mcssdk.utils.C4746d;
import com.heytap.mcssdk.utils.C4750f;
import com.heytap.mcssdk.utils.Utils;
import com.heytap.msp.push.callback.ICallBackResultService;
import com.heytap.msp.push.callback.IDataMessageCallBackService;
import com.heytap.msp.push.callback.IGetAppNotificationCallBackService;
import com.heytap.msp.push.callback.ISetAppNotificationCallBackService;
import com.heytap.msp.push.mode.BaseMode;

/* renamed from: com.heytap.mcssdk.f.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4737a implements InterfaceC4741c {
    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m15520a(C4720b c4720b, PushService pushService) {
        String str;
        if (c4720b == null) {
            str = "message is null , please check param of parseCommandMessage(2)";
        } else if (pushService == null) {
            str = "pushService is null , please check param of parseCommandMessage(2)";
        } else if (pushService.getPushCallback() != null) {
            int m15558e = c4720b.m15558e();
            if (m15558e == 12287) {
                ICallBackResultService pushCallback = pushService.getPushCallback();
                if (pushCallback != null) {
                    pushCallback.onError(c4720b.m15554g(), c4720b.m15556f(), c4720b.m15551i(), c4720b.m15552h());
                    return;
                }
                return;
            } else if (m15558e == 12298) {
                pushService.getPushCallback().onSetPushTime(c4720b.m15554g(), c4720b.m15556f());
                return;
            } else if (m15558e == 12306) {
                pushService.getPushCallback().onGetPushStatus(c4720b.m15554g(), Utils.parseInt(c4720b.m15556f()));
                return;
            } else if (m15558e == 12309) {
                pushService.getPushCallback().onGetNotificationStatus(c4720b.m15554g(), Utils.parseInt(c4720b.m15556f()));
                return;
            } else {
                switch (m15558e) {
                    case 12289:
                        if (c4720b.m15554g() == 0) {
                            pushService.setRegisterID(c4720b.m15556f());
                        }
                        pushService.getPushCallback().onRegister(c4720b.m15554g(), c4720b.m15556f(), c4720b.m15551i(), c4720b.m15552h());
                        return;
                    case 12290:
                        pushService.getPushCallback().onUnRegister(c4720b.m15554g(), c4720b.m15551i(), c4720b.m15552h());
                        return;
                    default:
                        switch (m15558e) {
                            case 12316:
                            case 12317:
                                ISetAppNotificationCallBackService pushSetAppNotificationCallBack = pushService.getPushSetAppNotificationCallBack();
                                if (pushSetAppNotificationCallBack != null) {
                                    pushSetAppNotificationCallBack.onSetAppNotificationSwitch(c4720b.m15554g());
                                    return;
                                }
                                return;
                            case 12318:
                                int i = 0;
                                try {
                                    i = Integer.parseInt(c4720b.m15556f());
                                } catch (Exception unused) {
                                }
                                IGetAppNotificationCallBackService pushGetAppNotificationCallBack = pushService.getPushGetAppNotificationCallBack();
                                if (pushGetAppNotificationCallBack != null) {
                                    pushGetAppNotificationCallBack.onGetAppNotificationSwitch(c4720b.m15554g(), i);
                                    return;
                                }
                                return;
                            default:
                                return;
                        }
                }
            }
        } else {
            str = "pushService.getPushCallback() is null , please check param of parseCommandMessage(2)";
        }
        C4746d.m15482e(str);
    }

    @Override // com.heytap.mcssdk.p208f.InterfaceC4741c
    /* renamed from: a */
    public void mo15515a(Context context, BaseMode baseMode, IDataMessageCallBackService iDataMessageCallBackService) {
        if (baseMode != null && baseMode.getType() == 4105) {
            final C4720b c4720b = (C4720b) baseMode;
            C4746d.m15494b("mcssdk-CallBackResultProcessor:" + c4720b.toString());
            C4750f.m15465b(new Runnable() { // from class: com.heytap.mcssdk.f.a.1
                @Override // java.lang.Runnable
                public void run() {
                    C4737a.this.m15520a(c4720b, PushService.getInstance());
                }
            });
        }
    }
}
