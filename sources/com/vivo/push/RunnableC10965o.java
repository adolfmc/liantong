package com.vivo.push;

import com.vivo.push.p368b.AppCommand;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PushClientManager.java */
/* renamed from: com.vivo.push.o */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class RunnableC10965o implements Runnable {

    /* renamed from: a */
    final /* synthetic */ AppCommand f21066a;

    /* renamed from: b */
    final /* synthetic */ String f21067b;

    /* renamed from: c */
    final /* synthetic */ PushClientManager f21068c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10965o(PushClientManager pushClientManager, AppCommand appCommand, String str) {
        this.f21068c = pushClientManager;
        this.f21066a = appCommand;
        this.f21067b = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f21068c.m5638a(this.f21066a);
        this.f21068c.m5620c(this.f21067b);
    }
}
