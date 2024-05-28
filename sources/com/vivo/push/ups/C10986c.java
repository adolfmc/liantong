package com.vivo.push.ups;

import com.vivo.push.IPushActionListener;

/* compiled from: VUpsManager.java */
/* renamed from: com.vivo.push.ups.c */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class C10986c implements IPushActionListener {

    /* renamed from: a */
    final /* synthetic */ UPSTurnCallback f21169a;

    /* renamed from: b */
    final /* synthetic */ VUpsManager f21170b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C10986c(VUpsManager vUpsManager, UPSTurnCallback uPSTurnCallback) {
        this.f21170b = vUpsManager;
        this.f21169a = uPSTurnCallback;
    }

    @Override // com.vivo.push.IPushActionListener
    public final void onStateChanged(int i) {
        this.f21169a.onResult(new CodeResult(i));
    }
}
