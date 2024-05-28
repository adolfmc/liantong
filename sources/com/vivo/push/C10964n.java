package com.vivo.push;

import com.vivo.push.PushClientManager;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.util.LogUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PushClientManager.java */
/* renamed from: com.vivo.push.n */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C10964n implements IPushActionListener {

    /* renamed from: a */
    final /* synthetic */ PushClientManager.C10963a f21062a;

    /* renamed from: b */
    final /* synthetic */ String f21063b;

    /* renamed from: c */
    final /* synthetic */ String f21064c;

    /* renamed from: d */
    final /* synthetic */ PushClientManager f21065d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C10964n(PushClientManager pushClientManager, PushClientManager.C10963a c10963a, String str, String str2) {
        this.f21065d = pushClientManager;
        this.f21062a = c10963a;
        this.f21063b = str;
        this.f21064c = str2;
    }

    @Override // com.vivo.push.IPushActionListener
    public final void onStateChanged(int i) {
        if (i == 0) {
            Object[] m5605b = this.f21062a.m5605b();
            if (m5605b == null || m5605b.length == 0) {
                LogUtil.m5354a("PushClientManager", "bind app result is null");
                return;
            } else {
                PushClientController.m5593a().m5585h().mo5313a((String) this.f21062a.m5605b()[0], this.f21063b, this.f21064c);
                return;
            }
        }
        PushClientController.m5593a().m5585h().mo5314a("");
    }
}
