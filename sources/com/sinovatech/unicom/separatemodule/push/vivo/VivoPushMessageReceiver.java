package com.sinovatech.unicom.separatemodule.push.vivo;

import android.content.Context;
import android.text.TextUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.push.PushManager;
import com.vivo.push.model.UnvarnishedMessage;
import com.vivo.push.sdk.OpenClientPushMessageReceiver;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class VivoPushMessageReceiver extends OpenClientPushMessageReceiver {
    @Override // com.vivo.push.sdk.OpenClientPushMessageReceiver, com.vivo.push.sdk.PushMessageCallback
    public void onReceiveRegId(Context context, String str) {
        super.onReceiveRegId(context, str);
        MsLogUtil.m7979d(VivoPushManager.TAG, "receiverReGid\n" + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        PushManager.getInstance().setPushToken(str);
    }

    @Override // com.vivo.push.sdk.OpenClientPushMessageReceiver, com.vivo.push.sdk.BasePushMessageReceiver, com.vivo.push.sdk.PushMessageCallback
    public void onTransmissionMessage(Context context, UnvarnishedMessage unvarnishedMessage) {
        super.onTransmissionMessage(context, unvarnishedMessage);
    }
}
