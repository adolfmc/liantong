package com.vivo.push.ups;

import com.vivo.push.IPushActionListener;
import com.vivo.push.restructure.PushClientController;

/* compiled from: VUpsManager.java */
/* renamed from: com.vivo.push.ups.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class C10984a implements IPushActionListener {

    /* renamed from: a */
    final /* synthetic */ UPSRegisterCallback f21165a;

    /* renamed from: b */
    final /* synthetic */ VUpsManager f21166b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C10984a(VUpsManager vUpsManager, UPSRegisterCallback uPSRegisterCallback) {
        this.f21166b = vUpsManager;
        this.f21165a = uPSRegisterCallback;
    }

    @Override // com.vivo.push.IPushActionListener
    public final void onStateChanged(int i) {
        this.f21165a.onResult(new TokenResult(i, PushClientController.m5593a().m5585h().mo5312b()));
    }
}
