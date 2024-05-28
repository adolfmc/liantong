package com.heytap.mcssdk.p208f;

import android.app.NotificationManager;
import android.content.Context;
import com.heytap.mcssdk.utils.C4746d;
import com.heytap.mcssdk.utils.C4750f;
import com.heytap.msp.push.callback.IDataMessageCallBackService;
import com.heytap.msp.push.mode.BaseMode;
import com.heytap.msp.push.mode.DataMessage;
import com.heytap.msp.push.statis.StatisticUtils;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.heytap.mcssdk.f.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4739b implements InterfaceC4741c {

    /* renamed from: a */
    private static final int f10696a = 1;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m15518a(Context context, DataMessage dataMessage) {
        if (context == null) {
            C4746d.m15494b("context is null");
            return;
        }
        C4746d.m15494b("Receive revokeMessage  extra : " + dataMessage.getStatisticsExtra() + "notifyId :" + dataMessage.getNotifyID() + "messageId : " + dataMessage.getTaskID());
        ((NotificationManager) context.getSystemService("notification")).cancel(dataMessage.getNotifyID());
        m15516b(context, dataMessage);
    }

    /* renamed from: b */
    private void m15516b(Context context, DataMessage dataMessage) {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        arrayList.add(dataMessage);
        hashMap.put(dataMessage.getEventId(), arrayList);
        StatisticUtils.statisticEvent(context, hashMap);
    }

    @Override // com.heytap.mcssdk.p208f.InterfaceC4741c
    /* renamed from: a */
    public void mo15515a(final Context context, BaseMode baseMode, final IDataMessageCallBackService iDataMessageCallBackService) {
        if (baseMode != null && baseMode.getType() == 4103) {
            final DataMessage dataMessage = (DataMessage) baseMode;
            if (iDataMessageCallBackService != null) {
                C4750f.m15465b(new Runnable() { // from class: com.heytap.mcssdk.f.b.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (dataMessage.getMsgCommand() == 1) {
                            C4739b.this.m15518a(context, dataMessage);
                        } else {
                            iDataMessageCallBackService.processMessage(context, dataMessage);
                        }
                    }
                });
            }
        }
    }
}
