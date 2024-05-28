package com.vivo.push.p368b;

import com.vivo.push.BundleWapper;

/* renamed from: com.vivo.push.b.l */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class OnDispatcherReceiveCommand extends OnReceiveCommand {

    /* renamed from: a */
    private int f20892a;

    /* renamed from: b */
    private int f20893b;

    public OnDispatcherReceiveCommand() {
        super(2016);
        this.f20892a = -1;
        this.f20893b = -1;
    }

    @Override // com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    /* renamed from: c */
    public final void mo5322c(BundleWapper bundleWapper) {
        super.mo5322c(bundleWapper);
        bundleWapper.m5733a("key_dispatch_environment", this.f20892a);
        bundleWapper.m5733a("key_dispatch_area", this.f20893b);
    }

    @Override // com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    /* renamed from: d */
    public final void mo5321d(BundleWapper bundleWapper) {
        super.mo5321d(bundleWapper);
        this.f20892a = bundleWapper.m5724b("key_dispatch_environment", 1);
        this.f20893b = bundleWapper.m5724b("key_dispatch_area", 1);
    }

    /* renamed from: d */
    public final int m5796d() {
        return this.f20892a;
    }

    /* renamed from: e */
    public final int m5795e() {
        return this.f20893b;
    }
}
