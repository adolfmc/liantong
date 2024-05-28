package com.vivo.push.p370d;

import android.content.Context;
import com.vivo.push.p370d.p371a.SyncProfileInfoCommand;
import com.vivo.push.p370d.p371a.SyncProfileInfoInputDS;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.restructure.request.CommandRequest;
import com.vivo.push.restructure.request.IPushRequestCallback;
import com.vivo.push.restructure.request.RequestManager;
import com.vivo.push.util.LogUtil;
import java.util.ArrayList;

/* compiled from: SyncProfileInfoImpl.java */
/* renamed from: com.vivo.push.d.h */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class RunnableC10941h implements Runnable {

    /* renamed from: a */
    final /* synthetic */ IPushRequestCallback f20952a;

    /* renamed from: b */
    final /* synthetic */ SyncProfileInfoImpl f20953b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10941h(SyncProfileInfoImpl syncProfileInfoImpl, IPushRequestCallback iPushRequestCallback) {
        this.f20953b = syncProfileInfoImpl;
        this.f20952a = iPushRequestCallback;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LogUtil.m5347b("query all profileIds");
        if (PushClientController.m5593a().m5586g().m5756b() != 0) {
            LogUtil.m5347b("core not support sync profileInfo");
            IPushRequestCallback iPushRequestCallback = this.f20952a;
            if (iPushRequestCallback != null) {
                iPushRequestCallback.onError(8102);
                return;
            }
        }
        Context m5591b = PushClientController.m5593a().m5591b();
        RequestManager.m5499a().m5496a(new CommandRequest(new SyncProfileInfoCommand(new SyncProfileInfoInputDS(m5591b.getPackageName(), new ArrayList(), 4)), new C10942i(this), (byte) 0));
    }
}
