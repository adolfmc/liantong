package com.vivo.push;

import com.vivo.push.PushClientManager;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PushClientManager.java */
/* renamed from: com.vivo.push.r */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class RunnableC10968r implements Runnable {

    /* renamed from: a */
    final /* synthetic */ String f21073a;

    /* renamed from: b */
    final /* synthetic */ PushClientManager f21074b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10968r(PushClientManager pushClientManager, String str) {
        this.f21074b = pushClientManager;
        this.f21073a = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PushClientManager.C10963a m5626b;
        m5626b = this.f21074b.m5626b(this.f21073a);
        if (m5626b != null) {
            m5626b.m5608a(1003, new Object[0]);
        }
    }
}
