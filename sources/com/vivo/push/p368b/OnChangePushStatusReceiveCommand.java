package com.vivo.push.p368b;

import com.vivo.push.BundleWapper;

/* renamed from: com.vivo.push.b.j */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class OnChangePushStatusReceiveCommand extends OnReceiveCommand {

    /* renamed from: a */
    private int f20890a;

    /* renamed from: b */
    private int f20891b;

    @Override // com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    public final String toString() {
        return "OnChangePushStatusCommand";
    }

    public OnChangePushStatusReceiveCommand() {
        super(12);
        this.f20890a = -1;
        this.f20891b = -1;
    }

    /* renamed from: d */
    public final int m5798d() {
        return this.f20890a;
    }

    /* renamed from: e */
    public final int m5797e() {
        return this.f20891b;
    }

    @Override // com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    /* renamed from: c */
    public final void mo5322c(BundleWapper bundleWapper) {
        super.mo5322c(bundleWapper);
        bundleWapper.m5733a("OnChangePushStatus.EXTRA_REQ_SERVICE_STATUS", this.f20890a);
        bundleWapper.m5733a("OnChangePushStatus.EXTRA_REQ_RECEIVER_STATUS", this.f20891b);
    }

    @Override // com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    /* renamed from: d */
    public final void mo5321d(BundleWapper bundleWapper) {
        super.mo5321d(bundleWapper);
        this.f20890a = bundleWapper.m5724b("OnChangePushStatus.EXTRA_REQ_SERVICE_STATUS", this.f20890a);
        this.f20891b = bundleWapper.m5724b("OnChangePushStatus.EXTRA_REQ_RECEIVER_STATUS", this.f20891b);
    }
}
