package com.heytap.mcssdk.p209g;

import android.content.Context;
import com.heytap.mcssdk.utils.StatUtil;
import com.heytap.msp.push.mode.DataMessage;
import com.heytap.msp.push.mode.MessageStat;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.heytap.mcssdk.g.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4742a {
    /* renamed from: a */
    public static boolean m15514a(Context context, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MessageStat(context.getPackageName(), str));
        return StatUtil.statisticMessage(context, arrayList);
    }

    /* renamed from: a */
    public static boolean m15513a(Context context, String str, DataMessage dataMessage) {
        ArrayList arrayList = new ArrayList();
        String packageName = context.getPackageName();
        arrayList.add(dataMessage == null ? new MessageStat(packageName, str) : new MessageStat(dataMessage.getMessageType(), packageName, dataMessage.getGlobalId(), dataMessage.getTaskID(), str, null, dataMessage.getStatisticsExtra(), dataMessage.getDataExtra()));
        return StatUtil.statisticMessage(context, arrayList);
    }

    /* renamed from: a */
    public static boolean m15512a(Context context, List<String> list) {
        ArrayList arrayList = new ArrayList();
        String packageName = context.getPackageName();
        if (list != null && list.size() != 0) {
            for (String str : list) {
                arrayList.add(new MessageStat(packageName, str));
            }
        }
        return StatUtil.statisticMessage(context, arrayList);
    }
}
