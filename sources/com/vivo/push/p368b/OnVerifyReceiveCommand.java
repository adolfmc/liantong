package com.vivo.push.p368b;

import com.vivo.push.BundleWapper;

/* renamed from: com.vivo.push.b.v */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class OnVerifyReceiveCommand extends OnReceiveCommand {

    /* renamed from: a */
    private String f20917a;

    /* renamed from: b */
    private long f20918b;

    public OnVerifyReceiveCommand(int i) {
        super(i);
    }

    /* renamed from: f */
    public final long m5764f() {
        return this.f20918b;
    }

    /* renamed from: g */
    public final String m5763g() {
        return this.f20917a;
    }

    @Override // com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    /* renamed from: c */
    public void mo5322c(BundleWapper bundleWapper) {
        super.mo5322c(bundleWapper);
        bundleWapper.m5730a("OnVerifyCallBackCommand.EXTRA_SECURITY_CONTENT", this.f20917a);
        bundleWapper.m5732a("notify_id", this.f20918b);
    }

    @Override // com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    /* renamed from: d */
    public void mo5321d(BundleWapper bundleWapper) {
        super.mo5321d(bundleWapper);
        this.f20917a = bundleWapper.m5734a("OnVerifyCallBackCommand.EXTRA_SECURITY_CONTENT");
        this.f20918b = bundleWapper.m5723b("notify_id", -1L);
    }
}
