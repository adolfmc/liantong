package com.heytap.mcssdk.p207e;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.heytap.mcssdk.p209g.C4742a;
import com.heytap.mcssdk.utils.C4744b;
import com.heytap.mcssdk.utils.C4746d;
import com.heytap.msp.push.mode.BaseMode;
import com.heytap.msp.push.mode.DataMessage;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.heytap.mcssdk.e.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4734b extends AbstractC4735c {
    @Override // com.heytap.mcssdk.p207e.InterfaceC4736d
    /* renamed from: a */
    public BaseMode mo15521a(Context context, int i, Intent intent) {
        if (4103 == i || 4098 == i || 4108 == i) {
            BaseMode mo15522a = mo15522a(intent, i);
            C4742a.m15513a(context, "push_transmit", (DataMessage) mo15522a);
            return mo15522a;
        }
        return null;
    }

    @Override // com.heytap.mcssdk.p207e.AbstractC4735c
    /* renamed from: a */
    public BaseMode mo15522a(Intent intent, int i) {
        try {
            DataMessage dataMessage = new DataMessage();
            dataMessage.setMessageID(C4744b.m15504d(intent.getStringExtra("messageID")));
            dataMessage.setTaskID(C4744b.m15504d(intent.getStringExtra("taskID")));
            dataMessage.setGlobalId(C4744b.m15504d(intent.getStringExtra("globalID")));
            dataMessage.setAppPackage(C4744b.m15504d(intent.getStringExtra("appPackage")));
            dataMessage.setTitle(C4744b.m15504d(intent.getStringExtra("title")));
            dataMessage.setContent(C4744b.m15504d(intent.getStringExtra("content")));
            dataMessage.setDescription(C4744b.m15504d(intent.getStringExtra("description")));
            String m15504d = C4744b.m15504d(intent.getStringExtra("notifyID"));
            int i2 = 0;
            dataMessage.setNotifyID(TextUtils.isEmpty(m15504d) ? 0 : Integer.parseInt(m15504d));
            dataMessage.setMiniProgramPkg(C4744b.m15504d(intent.getStringExtra("miniProgramPkg")));
            dataMessage.setMessageType(i);
            dataMessage.setEventId(C4744b.m15504d(intent.getStringExtra("eventId")));
            dataMessage.setStatisticsExtra(C4744b.m15504d(intent.getStringExtra("statistics_extra")));
            String m15504d2 = C4744b.m15504d(intent.getStringExtra("data_extra"));
            dataMessage.setDataExtra(m15504d2);
            String m15524a = m15524a(m15504d2);
            if (!TextUtils.isEmpty(m15524a)) {
                i2 = Integer.parseInt(m15524a);
            }
            dataMessage.setMsgCommand(i2);
            dataMessage.setBalanceTime(C4744b.m15504d(intent.getStringExtra("balanceTime")));
            dataMessage.setStartDate(C4744b.m15504d(intent.getStringExtra("startDate")));
            dataMessage.setEndDate(C4744b.m15504d(intent.getStringExtra("endDate")));
            dataMessage.setTimeRanges(C4744b.m15504d(intent.getStringExtra("timeRanges")));
            dataMessage.setRule(C4744b.m15504d(intent.getStringExtra("rule")));
            dataMessage.setForcedDelivery(C4744b.m15504d(intent.getStringExtra("forcedDelivery")));
            dataMessage.setDistinctContent(C4744b.m15504d(intent.getStringExtra("distinctBycontent")));
            dataMessage.setAppId(C4744b.m15504d(intent.getStringExtra("appID")));
            return dataMessage;
        } catch (Exception e) {
            C4746d.m15494b("OnHandleIntent--" + e.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    public String m15524a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return new JSONObject(str).optString("msg_command");
        } catch (JSONException e) {
            C4746d.m15494b(e.getMessage());
            return "";
        }
    }
}
