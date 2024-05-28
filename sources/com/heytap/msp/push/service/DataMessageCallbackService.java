package com.heytap.msp.push.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.heytap.mcssdk.C4717c;
import com.heytap.mcssdk.PushService;
import com.heytap.mcssdk.utils.C4746d;
import com.heytap.msp.push.callback.IDataMessageCallBackService;
import com.heytap.msp.push.mode.DataMessage;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DataMessageCallbackService extends Service implements IDataMessageCallBackService {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        PushService.getInstance().innerInit(getApplicationContext());
        C4717c.m15574a(getApplicationContext(), intent, this);
        return 2;
    }

    @Override // com.heytap.msp.push.callback.IDataMessageCallBackService
    public void processMessage(Context context, DataMessage dataMessage) {
        C4746d.m15494b("Receive DataMessageCallbackService:messageTitle: " + dataMessage.getTitle() + " ------content:" + dataMessage.getContent() + "------describe:" + dataMessage.getDescription());
    }
}
