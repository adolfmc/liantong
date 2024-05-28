package com.vivo.push.p370d;

import com.vivo.push.restructure.request.IPushRequestCallback;
import com.vivo.push.util.LogUtil;

/* compiled from: SyncProfileInfoImpl.java */
/* renamed from: com.vivo.push.d.g */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class RunnableC10940g implements Runnable {

    /* renamed from: a */
    final /* synthetic */ IPushRequestCallback f20950a;

    /* renamed from: b */
    final /* synthetic */ SyncProfileInfoImpl f20951b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10940g(SyncProfileInfoImpl syncProfileInfoImpl, IPushRequestCallback iPushRequestCallback) {
        this.f20951b = syncProfileInfoImpl;
        this.f20950a = iPushRequestCallback;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LogUtil.m5347b("delete all profileIds");
        SyncProfileInfoImpl.m5715a(this.f20951b, "", this.f20950a, 3);
    }
}
