package com.vivo.push;

import com.vivo.push.restructure.PushClientController;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BasePushClient.java */
/* renamed from: com.vivo.push.c */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class RunnableC10935c implements Runnable {

    /* renamed from: a */
    final /* synthetic */ IPushActionListener f20924a;

    /* renamed from: b */
    final /* synthetic */ BasePushClient f20925b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10935c(BasePushClient basePushClient, IPushActionListener iPushActionListener) {
        this.f20925b = basePushClient;
        this.f20924a = iPushActionListener;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int mo5317a = PushClientController.m5593a().m5585h().mo5317a();
        IPushActionListener iPushActionListener = this.f20924a;
        if (iPushActionListener != null) {
            iPushActionListener.onStateChanged(mo5317a);
        }
    }
}
