package com.sdk.p308x;

import com.sdk.base.api.CallBackTimeOut;
import com.sdk.base.framework.utils.log.LogUtils;
import com.sdk.base.module.manager.SDKManager;

/* JADX INFO: Add missing generic type declarations: [T] */
/* renamed from: com.sdk.x.b */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C7054b<T> implements CallBackTimeOut<T> {

    /* renamed from: a */
    public final /* synthetic */ C7048a f18258a;

    public C7054b(C7048a c7048a) {
        this.f18258a = c7048a;
    }

    @Override // com.sdk.base.api.CallBackTimeOut
    public void timeout(int i, int i2, String str) {
        C7048a<T>.RunnableC7053d runnableC7053d = this.f18258a.f18245d;
        runnableC7053d.f18254a.removeCallbacks(runnableC7053d);
        this.f18258a.m8112a(1, 102001, "选择流量通道失败");
        LogUtils.d_yl(C7048a.f18237h, "选择流量通道 超时 ", 0);
        SDKManager.releaseConnect(this.f18258a.f18243b);
    }
}
