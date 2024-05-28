package com.vivo.push;

import com.vivo.push.listener.IPushQueryActionListener;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.util.ConcurrentUtils;

/* renamed from: com.vivo.push.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class BasePushClient {
    public void getRegId(IPushQueryActionListener iPushQueryActionListener) {
        ConcurrentUtils.m5404a().execute(new RunnableC10934b(this, iPushQueryActionListener));
    }

    public void deleteRegid(IPushActionListener iPushActionListener, String str, String str2) {
        PushClientController.m5593a().m5585h().mo5316a(iPushActionListener, str, str2);
    }

    public void querySubscribeState(IPushActionListener iPushActionListener) {
        ConcurrentUtils.m5404a().execute(new RunnableC10935c(this, iPushActionListener));
    }
}
