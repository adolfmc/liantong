package com.sinovatech.unicom.separatemodule.push.oppo;

import android.content.Context;
import com.heytap.msp.push.mode.DataMessage;
import com.heytap.msp.push.service.DataMessageCallbackService;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class OppoQPushService extends DataMessageCallbackService {
    @Override // com.heytap.msp.push.service.DataMessageCallbackService, com.heytap.msp.push.callback.IDataMessageCallBackService
    public void processMessage(Context context, DataMessage dataMessage) {
        super.processMessage(context, dataMessage);
        MsLogUtil.m7979d("oppotui", "--OppoPushService---");
    }
}
