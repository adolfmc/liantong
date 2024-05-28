package com.vivo.push.restructure.p378c;

import android.text.TextUtils;
import com.vivo.push.PushClientManager;
import com.vivo.push.p368b.ReporterCommand;
import com.vivo.push.restructure.p377b.IPushRely;
import com.vivo.push.util.LogUtil;
import java.util.HashMap;

/* renamed from: com.vivo.push.restructure.c.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class ReportImpl implements IReport {

    /* renamed from: a */
    private IPushRely f21120a;

    public ReportImpl(IPushRely iPushRely) {
        this.f21120a = iPushRely;
    }

    @Override // com.vivo.push.restructure.p378c.IReport
    /* renamed from: a */
    public final void mo5523a(int i, String str) {
        LogUtil.m5341d("ReportImpl", "reportIntercepted() , msgID = " + str + ", code = " + i);
        if (i <= 0 || TextUtils.isEmpty(str)) {
            return;
        }
        ReporterCommand reporterCommand = new ReporterCommand(i);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("messageID", str);
        IPushRely iPushRely = this.f21120a;
        if (iPushRely != null) {
            String mo5543a = iPushRely.mo5543a();
            if (!TextUtils.isEmpty(mo5543a)) {
                hashMap.put("remoteAppId", mo5543a);
            }
        }
        reporterCommand.m5761a(hashMap);
        PushClientManager.m5648a().m5638a(reporterCommand);
    }
}
