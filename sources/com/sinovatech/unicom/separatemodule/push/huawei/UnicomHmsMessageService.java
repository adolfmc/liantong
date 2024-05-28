package com.sinovatech.unicom.separatemodule.push.huawei;

import android.text.TextUtils;
import com.huawei.hms.push.HmsMessageService;
import com.huawei.hms.push.RemoteMessage;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.push.PushManager;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UnicomHmsMessageService extends HmsMessageService {
    private static String TAG = "UnicomHmsMessageService";

    @Override // com.huawei.hms.push.HmsMessageService
    public void onNewToken(String str) {
        super.onNewToken(str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        PushManager.getInstance().setPushToken(str);
        String str2 = TAG;
        MsLogUtil.m7979d(str2, "华为推送id" + str);
    }

    @Override // com.huawei.hms.push.HmsMessageService
    public void onMessageSent(String str) {
        super.onMessageSent(str);
    }

    @Override // com.huawei.hms.push.HmsMessageService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        try {
            PushManager.getInstance().clickMessage(remoteMessage, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
