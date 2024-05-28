package com.vivo.push.p370d;

import com.vivo.push.p370d.p371a.SyncProfileInfoInputDS;
import com.vivo.push.restructure.request.IPushRequestCallback;
import com.vivo.push.restructure.request.ISendCallback;
import com.vivo.push.util.LogUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SyncProfileInfoImpl.java */
/* renamed from: com.vivo.push.d.j */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C10943j implements ISendCallback<SyncProfileInfoInputDS> {

    /* renamed from: a */
    final /* synthetic */ IPushRequestCallback f20955a;

    /* renamed from: b */
    final /* synthetic */ int f20956b;

    /* renamed from: c */
    final /* synthetic */ SyncProfileInfoImpl f20957c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C10943j(SyncProfileInfoImpl syncProfileInfoImpl, IPushRequestCallback iPushRequestCallback, int i) {
        this.f20957c = syncProfileInfoImpl;
        this.f20955a = iPushRequestCallback;
        this.f20956b = i;
    }

    @Override // com.vivo.push.restructure.request.ISendCallback
    /* renamed from: a */
    public final void mo5501a(int i) {
        if (this.f20955a != null) {
            LogUtil.m5347b(this.f20956b + " sync err : " + i);
            this.f20955a.onError(i);
        }
    }

    @Override // com.vivo.push.restructure.request.ISendCallback
    /* renamed from: a */
    public final /* synthetic */ void mo5500a(SyncProfileInfoInputDS syncProfileInfoInputDS) {
        if (this.f20955a != null) {
            LogUtil.m5347b(this.f20956b + " sync success");
            this.f20955a.onSuccess(0);
        }
    }
}
