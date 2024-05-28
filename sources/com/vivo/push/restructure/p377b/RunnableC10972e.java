package com.vivo.push.restructure.p377b;

import android.content.Context;
import com.vivo.push.PushConfig;
import com.vivo.push.util.Utility;

/* compiled from: PushRelyImpl.java */
/* renamed from: com.vivo.push.restructure.b.e */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class RunnableC10972e implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f21117a;

    /* renamed from: b */
    final /* synthetic */ PushConfig f21118b;

    /* renamed from: c */
    final /* synthetic */ PushRelyImpl f21119c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10972e(PushRelyImpl pushRelyImpl, Context context, PushConfig pushConfig) {
        this.f21119c = pushRelyImpl;
        this.f21117a = context;
        this.f21118b = pushConfig;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context = this.f21117a;
        Utility.m5442a(context, context.getPackageName(), this.f21118b.isAgreePrivacyStatement());
    }
}
