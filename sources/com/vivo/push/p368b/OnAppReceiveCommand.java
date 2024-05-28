package com.vivo.push.p368b;

import com.vivo.push.BundleWapper;

/* renamed from: com.vivo.push.b.i */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class OnAppReceiveCommand extends OnReceiveCommand {

    /* renamed from: a */
    private String f20886a;

    /* renamed from: b */
    private String f20887b;

    /* renamed from: c */
    private String f20888c;

    /* renamed from: d */
    private String f20889d;

    @Override // com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    public final String toString() {
        return "OnBindCommand";
    }

    public OnAppReceiveCommand(int i) {
        super(i);
    }

    /* renamed from: d */
    public final String m5800d() {
        return this.f20886a;
    }

    /* renamed from: e */
    public final String m5799e() {
        return this.f20888c;
    }

    @Override // com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    /* renamed from: c */
    public final void mo5322c(BundleWapper bundleWapper) {
        super.mo5322c(bundleWapper);
        bundleWapper.m5730a("app_id", this.f20886a);
        bundleWapper.m5730a("client_id", this.f20887b);
        bundleWapper.m5730a("client_token", this.f20888c);
        bundleWapper.m5730a("client_token_validity_period", this.f20889d);
    }

    @Override // com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    /* renamed from: d */
    public final void mo5321d(BundleWapper bundleWapper) {
        super.mo5321d(bundleWapper);
        this.f20886a = bundleWapper.m5734a("app_id");
        this.f20887b = bundleWapper.m5734a("client_id");
        this.f20888c = bundleWapper.m5734a("client_token");
        this.f20889d = bundleWapper.m5734a("client_token_validity_period");
    }
}
