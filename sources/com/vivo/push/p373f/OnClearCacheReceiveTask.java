package com.vivo.push.p373f;

import com.vivo.push.PushCommand;
import com.vivo.push.util.LogUtil;
import com.vivo.push.util.SystemCache;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.vivo.push.f.k */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class OnClearCacheReceiveTask extends OnReceiveTask {
    /* JADX INFO: Access modifiers changed from: package-private */
    public OnClearCacheReceiveTask(PushCommand pushCommand) {
        super(pushCommand);
    }

    @Override // com.vivo.push.PushClientTask
    /* renamed from: a */
    public final void mo5492a(PushCommand pushCommand) {
        LogUtil.m5341d("OnClearCacheTask", "delete push info " + this.f21149a.getPackageName());
        SystemCache.m5449b(this.f21149a).m5450a();
    }
}
