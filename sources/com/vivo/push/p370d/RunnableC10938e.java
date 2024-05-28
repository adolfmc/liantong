package com.vivo.push.p370d;

import com.vivo.push.restructure.request.IPushRequestCallback;
import com.vivo.push.util.LogUtil;

/* compiled from: SyncProfileInfoImpl.java */
/* renamed from: com.vivo.push.d.e */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class RunnableC10938e implements Runnable {

    /* renamed from: a */
    final /* synthetic */ String f20944a;

    /* renamed from: b */
    final /* synthetic */ IPushRequestCallback f20945b;

    /* renamed from: c */
    final /* synthetic */ SyncProfileInfoImpl f20946c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10938e(SyncProfileInfoImpl syncProfileInfoImpl, String str, IPushRequestCallback iPushRequestCallback) {
        this.f20946c = syncProfileInfoImpl;
        this.f20944a = str;
        this.f20945b = iPushRequestCallback;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LogUtil.m5347b("add profileId");
        SyncProfileInfoImpl.m5715a(this.f20946c, this.f20944a, this.f20945b, 1);
    }
}
