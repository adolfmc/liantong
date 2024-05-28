package com.vivo.push.p368b;

import com.vivo.push.BundleWapper;

/* renamed from: com.vivo.push.b.n */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class OnLogReceiveCommand extends OnReceiveCommand {

    /* renamed from: a */
    private String f20895a;

    /* renamed from: b */
    private int f20896b;

    /* renamed from: c */
    private boolean f20897c;

    @Override // com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    public final String toString() {
        return "OnLogCommand";
    }

    public OnLogReceiveCommand() {
        super(7);
        this.f20896b = 0;
        this.f20897c = false;
    }

    /* renamed from: d */
    public final String m5791d() {
        return this.f20895a;
    }

    /* renamed from: b */
    public final void m5792b(String str) {
        this.f20895a = str;
    }

    /* renamed from: e */
    public final int m5790e() {
        return this.f20896b;
    }

    /* renamed from: a */
    public final void m5793a(int i) {
        this.f20896b = i;
    }

    /* renamed from: f */
    public final boolean m5789f() {
        return this.f20897c;
    }

    /* renamed from: g */
    public final void m5788g() {
        this.f20897c = false;
    }

    @Override // com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    /* renamed from: c */
    public final void mo5322c(BundleWapper bundleWapper) {
        super.mo5322c(bundleWapper);
        bundleWapper.m5730a("content", this.f20895a);
        bundleWapper.m5733a("log_level", this.f20896b);
        bundleWapper.m5728a("is_server_log", this.f20897c);
    }

    @Override // com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    /* renamed from: d */
    public final void mo5321d(BundleWapper bundleWapper) {
        super.mo5321d(bundleWapper);
        this.f20895a = bundleWapper.m5734a("content");
        this.f20896b = bundleWapper.m5724b("log_level", 0);
        this.f20897c = bundleWapper.m5720e("is_server_log");
    }
}
