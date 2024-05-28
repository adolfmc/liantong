package com.vivo.push.util;

import android.text.TextUtils;
import com.vivo.push.PushClientManager;
import com.vivo.push.p368b.ReporterCommand;
import com.vivo.push.restructure.PushClientController;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.vivo.push.util.f */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class ClientReportUtil {
    /* renamed from: a */
    public static boolean m5406a(long j, long j2) {
        LogUtil.m5341d("ClientReportUtil", "report message: " + j + ", reportType: " + j2);
        ReporterCommand reporterCommand = new ReporterCommand(j2);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("messageID", String.valueOf(j));
        String mo5543a = PushClientController.m5593a().m5588e().mo5543a();
        if (!TextUtils.isEmpty(mo5543a)) {
            hashMap.put("remoteAppId", mo5543a);
        }
        reporterCommand.m5761a(hashMap);
        PushClientManager.m5648a().m5638a(reporterCommand);
        return true;
    }

    /* renamed from: a */
    public static boolean m5405a(long j, HashMap<String, String> hashMap) {
        ReporterCommand reporterCommand = new ReporterCommand(j);
        reporterCommand.m5761a(hashMap);
        reporterCommand.m5760d();
        PushClientManager.m5648a().m5638a(reporterCommand);
        return true;
    }
}
