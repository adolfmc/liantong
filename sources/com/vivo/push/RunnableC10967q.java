package com.vivo.push;

import com.vivo.push.p368b.AppCommand;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PushClientManager.java */
/* renamed from: com.vivo.push.q */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class RunnableC10967q implements Runnable {

    /* renamed from: a */
    final /* synthetic */ AppCommand f21070a;

    /* renamed from: b */
    final /* synthetic */ String f21071b;

    /* renamed from: c */
    final /* synthetic */ PushClientManager f21072c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10967q(PushClientManager pushClientManager, AppCommand appCommand, String str) {
        this.f21072c = pushClientManager;
        this.f21070a = appCommand;
        this.f21071b = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f21072c.m5638a(this.f21070a);
        this.f21072c.m5620c(this.f21071b);
    }
}
