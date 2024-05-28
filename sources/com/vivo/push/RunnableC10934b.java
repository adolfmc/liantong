package com.vivo.push;

import com.vivo.push.listener.IPushQueryActionListener;
import com.vivo.push.restructure.PushClientController;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BasePushClient.java */
/* renamed from: com.vivo.push.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class RunnableC10934b implements Runnable {

    /* renamed from: a */
    final /* synthetic */ IPushQueryActionListener f20867a;

    /* renamed from: b */
    final /* synthetic */ BasePushClient f20868b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10934b(BasePushClient basePushClient, IPushQueryActionListener iPushQueryActionListener) {
        this.f20868b = basePushClient;
        this.f20867a = iPushQueryActionListener;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String mo5312b = PushClientController.m5593a().m5585h().mo5312b();
        IPushQueryActionListener iPushQueryActionListener = this.f20867a;
        if (iPushQueryActionListener != null) {
            iPushQueryActionListener.onSuccess(mo5312b);
        }
    }
}
