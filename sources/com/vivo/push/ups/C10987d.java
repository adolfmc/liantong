package com.vivo.push.ups;

import com.vivo.push.IPushActionListener;

/* compiled from: VUpsManager.java */
/* renamed from: com.vivo.push.ups.d */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class C10987d implements IPushActionListener {

    /* renamed from: a */
    final /* synthetic */ UPSTurnCallback f21171a;

    /* renamed from: b */
    final /* synthetic */ VUpsManager f21172b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C10987d(VUpsManager vUpsManager, UPSTurnCallback uPSTurnCallback) {
        this.f21172b = vUpsManager;
        this.f21171a = uPSTurnCallback;
    }

    @Override // com.vivo.push.IPushActionListener
    public final void onStateChanged(int i) {
        this.f21171a.onResult(new CodeResult(i));
    }
}
