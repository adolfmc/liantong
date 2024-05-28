package com.sinovatech.unicom.separatemodule.push.oppo;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import com.heytap.msp.push.HeytapPushManager;
import com.heytap.msp.push.callback.ICallBackResultService;
import com.heytap.msp.push.callback.ISetAppNotificationCallBackService;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.push.PushManager;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class OppoPushManager {
    public static final String TAG = "OppoPushManager";
    private static OppoPushManager oppoPushManager;

    private OppoPushManager() {
    }

    public static OppoPushManager getInstance() {
        if (oppoPushManager == null) {
            synchronized (OppoPushManager.class) {
                if (oppoPushManager == oppoPushManager) {
                    oppoPushManager = new OppoPushManager();
                }
            }
        }
        return oppoPushManager;
    }

    public void initOppoSDk(Application application) {
        if (HeytapPushManager.isSupportPush(application) && DeviceHelper.isOppo()) {
            HeytapPushManager.init(application, true);
            HeytapPushManager.register(application, "k2hZsg6gv68cOg8O0WkcW8o4", "67E00f2c8b846Efe8603C10c8ce10f02", new ICallBackResultService() { // from class: com.sinovatech.unicom.separatemodule.push.oppo.OppoPushManager.1
                @Override // com.heytap.msp.push.callback.ICallBackResultService
                public void onGetNotificationStatus(int i, int i2) {
                }

                @Override // com.heytap.msp.push.callback.ICallBackResultService
                public void onGetPushStatus(int i, int i2) {
                }

                @Override // com.heytap.msp.push.callback.ICallBackResultService
                public void onSetPushTime(int i, String str) {
                }

                @Override // com.heytap.msp.push.callback.ICallBackResultService
                public void onUnRegister(int i, String str, String str2) {
                }

                @Override // com.heytap.msp.push.callback.ICallBackResultService
                public void onRegister(int i, String str, String str2, String str3) {
                    MsLogUtil.m7979d(OppoPushManager.TAG, "oppo推送onRegister状态码" + i);
                    if (i != 0 || TextUtils.isEmpty(str)) {
                        return;
                    }
                    PushManager.getInstance().setPushToken(str);
                    MsLogUtil.m7979d(OppoPushManager.TAG, "oppo推送onRegister token" + str);
                }

                @Override // com.heytap.msp.push.callback.ICallBackResultService
                public void onError(int i, String str, String str2, String str3) {
                    MsLogUtil.m7979d(OppoPushManager.TAG, "oppo推送onError" + i + "--" + str + "--" + str2 + "--" + str3);
                }
            });
        }
    }

    public String getToken() {
        if (DeviceHelper.isOppo() && HeytapPushManager.isSupportPush(App.getInstance())) {
            String registerID = HeytapPushManager.getRegisterID();
            MsLogUtil.m7979d(TAG, "oppo推送getToken" + registerID);
            PushManager.getInstance().setPushToken(registerID);
            return registerID;
        }
        return "";
    }

    public void enablePush(boolean z, Context context) {
        try {
            if (DeviceHelper.isOppo() && HeytapPushManager.isSupportPush(context)) {
                if (z) {
                    HeytapPushManager.enableAppNotificationSwitch(new ISetAppNotificationCallBackService() { // from class: com.sinovatech.unicom.separatemodule.push.oppo.OppoPushManager.2
                        @Override // com.heytap.msp.push.callback.ISetAppNotificationCallBackService
                        public void onSetAppNotificationSwitch(int i) {
                            MsLogUtil.m7979d(OppoPushManager.TAG, "打开推送" + i);
                        }
                    });
                } else {
                    HeytapPushManager.disableAppNotificationSwitch(new ISetAppNotificationCallBackService() { // from class: com.sinovatech.unicom.separatemodule.push.oppo.OppoPushManager.3
                        @Override // com.heytap.msp.push.callback.ISetAppNotificationCallBackService
                        public void onSetAppNotificationSwitch(int i) {
                            MsLogUtil.m7979d(OppoPushManager.TAG, "关闭推送" + i);
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
