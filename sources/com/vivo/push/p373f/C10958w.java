package com.vivo.push.p373f;

import android.text.TextUtils;
import com.vivo.push.PushClientManager;
import com.vivo.push.p373f.OnNotificationArrivedReceiveTask;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.util.ClientReportUtil;
import com.vivo.push.util.LogUtil;
import java.util.HashMap;

/* compiled from: OnNotificationArrivedReceiveTask.java */
/* renamed from: com.vivo.push.f.w */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class C10958w implements OnNotificationArrivedReceiveTask.InterfaceC10956a {

    /* renamed from: a */
    final /* synthetic */ RunnableC10957v f21011a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C10958w(RunnableC10957v runnableC10957v) {
        this.f21011a = runnableC10957v;
    }

    @Override // com.vivo.push.p373f.OnNotificationArrivedReceiveTask.InterfaceC10956a
    /* renamed from: a */
    public final void mo5668a() {
        long m5611k = PushClientManager.m5648a().m5611k();
        if (m5611k < 1400 && m5611k != 1340) {
            LogUtil.m5346b("OnNotificationArrivedTask", "引擎版本太低，不支持正向展示功能，pushEngineSDKVersion：".concat(String.valueOf(m5611k)));
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("srt", "1");
        hashMap.put("message_id", String.valueOf(this.f21011a.f21009b.m5764f()));
        String mo5543a = PushClientController.m5593a().m5588e().mo5543a();
        if (!TextUtils.isEmpty(mo5543a)) {
            hashMap.put("app_id", mo5543a);
        }
        hashMap.put("type", "1");
        hashMap.put("dtp", "1");
        ClientReportUtil.m5405a(6L, hashMap);
    }

    @Override // com.vivo.push.p373f.OnNotificationArrivedReceiveTask.InterfaceC10956a
    /* renamed from: b */
    public final void mo5667b() {
        HashMap hashMap = new HashMap();
        hashMap.put("messageID", String.valueOf(this.f21011a.f21009b.m5764f()));
        String mo5543a = PushClientController.m5593a().m5588e().mo5543a();
        if (!TextUtils.isEmpty(mo5543a)) {
            hashMap.put("remoteAppId", mo5543a);
        }
        ClientReportUtil.m5405a(2122L, hashMap);
    }
}
