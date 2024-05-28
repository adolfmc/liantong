package com.vivo.push.p368b;

import com.vivo.push.BundleWapper;

/* renamed from: com.vivo.push.b.u */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class OnUndoMsgReceiveCommand extends OnVerifyReceiveCommand {

    /* renamed from: a */
    private long f20915a;

    /* renamed from: b */
    private int f20916b;

    @Override // com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    public final String toString() {
        return "OnUndoMsgCommand";
    }

    public OnUndoMsgReceiveCommand() {
        super(20);
        this.f20915a = -1L;
    }

    /* renamed from: d */
    public final long m5766d() {
        return this.f20915a;
    }

    @Override // com.vivo.push.p368b.OnVerifyReceiveCommand, com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    /* renamed from: c */
    public final void mo5322c(BundleWapper bundleWapper) {
        super.mo5322c(bundleWapper);
        bundleWapper.m5732a("undo_msg_v1", this.f20915a);
        bundleWapper.m5733a("undo_msg_type_v1", this.f20916b);
    }

    @Override // com.vivo.push.p368b.OnVerifyReceiveCommand, com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    /* renamed from: d */
    public final void mo5321d(BundleWapper bundleWapper) {
        super.mo5321d(bundleWapper);
        this.f20915a = bundleWapper.m5723b("undo_msg_v1", this.f20915a);
        this.f20916b = bundleWapper.m5724b("undo_msg_type_v1", 0);
    }

    /* renamed from: e */
    public final String m5765e() {
        long j = this.f20915a;
        if (j != -1) {
            return String.valueOf(j);
        }
        return null;
    }
}
