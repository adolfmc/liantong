package com.vivo.push.p368b;

import com.vivo.push.BundleWapper;
import com.vivo.push.PushCommand;

/* renamed from: com.vivo.push.b.w */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class PushModeCommand extends PushCommand {

    /* renamed from: a */
    private int f20919a;

    @Override // com.vivo.push.PushCommand
    /* renamed from: c */
    public final boolean mo5323c() {
        return true;
    }

    @Override // com.vivo.push.PushCommand
    public final String toString() {
        return "PushModeCommand";
    }

    public PushModeCommand() {
        super(2011);
        this.f20919a = 0;
    }

    /* renamed from: d */
    public final int m5762d() {
        return this.f20919a;
    }

    @Override // com.vivo.push.PushCommand
    /* renamed from: c */
    public final void mo5322c(BundleWapper bundleWapper) {
        bundleWapper.m5733a("com.bbk.push.ikey.MODE_TYPE", this.f20919a);
    }

    @Override // com.vivo.push.PushCommand
    /* renamed from: d */
    public final void mo5321d(BundleWapper bundleWapper) {
        this.f20919a = bundleWapper.m5724b("com.bbk.push.ikey.MODE_TYPE", 0);
    }
}
