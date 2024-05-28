package com.vivo.push;

import com.vivo.push.restructure.PushClientController;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PushClientManager.java */
/* renamed from: com.vivo.push.p */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C10966p implements IPushActionListener {

    /* renamed from: a */
    final /* synthetic */ PushClientManager f21069a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C10966p(PushClientManager pushClientManager) {
        this.f21069a = pushClientManager;
    }

    @Override // com.vivo.push.IPushActionListener
    public final void onStateChanged(int i) {
        if (i == 0) {
            PushClientController.m5593a().m5585h().mo5311b("");
        } else {
            PushClientController.m5593a().m5585h().mo5309c("");
        }
    }
}
