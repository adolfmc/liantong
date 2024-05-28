package com.sinovatech.unicom.separatemodule.push.xiaomi;

import android.content.Context;
import com.sinovatech.unicom.separatemodule.push.PushManager;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class XiaoMiPushMessageReceiver extends PushMessageReceiver {
    @Override // com.xiaomi.mipush.sdk.PushMessageReceiver
    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage) {
        super.onNotificationMessageClicked(context, miPushMessage);
        try {
            PushManager.getInstance().clickMessage(miPushMessage, context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
