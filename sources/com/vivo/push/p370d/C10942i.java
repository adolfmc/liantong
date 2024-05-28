package com.vivo.push.p370d;

import com.vivo.push.p370d.p371a.SyncProfileInfoInputDS;
import com.vivo.push.restructure.request.ISendCallback;
import com.vivo.push.util.LogUtil;

/* compiled from: SyncProfileInfoImpl.java */
/* renamed from: com.vivo.push.d.i */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class C10942i implements ISendCallback<SyncProfileInfoInputDS> {

    /* renamed from: a */
    final /* synthetic */ RunnableC10941h f20954a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C10942i(RunnableC10941h runnableC10941h) {
        this.f20954a = runnableC10941h;
    }

    @Override // com.vivo.push.restructure.request.ISendCallback
    /* renamed from: a */
    public final /* synthetic */ void mo5500a(SyncProfileInfoInputDS syncProfileInfoInputDS) {
        SyncProfileInfoInputDS syncProfileInfoInputDS2 = syncProfileInfoInputDS;
        if (this.f20954a.f20952a != null) {
            LogUtil.m5347b("query success");
            this.f20954a.f20952a.onSuccess(syncProfileInfoInputDS2.m5719a());
        }
    }

    @Override // com.vivo.push.restructure.request.ISendCallback
    /* renamed from: a */
    public final void mo5501a(int i) {
        if (this.f20954a.f20952a != null) {
            LogUtil.m5347b("query err : ".concat(String.valueOf(i)));
            this.f20954a.f20952a.onError(i);
        }
    }
}
