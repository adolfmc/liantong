package com.vivo.push.ups;

import com.vivo.push.IPushActionListener;

/* compiled from: VUpsManager.java */
/* renamed from: com.vivo.push.ups.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class C10985b implements IPushActionListener {

    /* renamed from: a */
    final /* synthetic */ UPSRegisterCallback f21167a;

    /* renamed from: b */
    final /* synthetic */ VUpsManager f21168b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C10985b(VUpsManager vUpsManager, UPSRegisterCallback uPSRegisterCallback) {
        this.f21168b = vUpsManager;
        this.f21167a = uPSRegisterCallback;
    }

    @Override // com.vivo.push.IPushActionListener
    public final void onStateChanged(int i) {
        this.f21167a.onResult(new TokenResult(i, ""));
    }
}
