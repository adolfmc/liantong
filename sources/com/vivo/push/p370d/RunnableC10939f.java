package com.vivo.push.p370d;

import com.vivo.push.restructure.request.IPushRequestCallback;
import com.vivo.push.util.LogUtil;

/* compiled from: SyncProfileInfoImpl.java */
/* renamed from: com.vivo.push.d.f */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class RunnableC10939f implements Runnable {

    /* renamed from: a */
    final /* synthetic */ String f20947a;

    /* renamed from: b */
    final /* synthetic */ IPushRequestCallback f20948b;

    /* renamed from: c */
    final /* synthetic */ SyncProfileInfoImpl f20949c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10939f(SyncProfileInfoImpl syncProfileInfoImpl, String str, IPushRequestCallback iPushRequestCallback) {
        this.f20949c = syncProfileInfoImpl;
        this.f20947a = str;
        this.f20948b = iPushRequestCallback;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LogUtil.m5347b("delete profileId");
        SyncProfileInfoImpl.m5715a(this.f20949c, this.f20947a, this.f20948b, 2);
    }
}
