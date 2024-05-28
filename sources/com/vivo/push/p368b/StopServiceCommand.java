package com.vivo.push.p368b;

import com.vivo.push.BundleWapper;
import com.vivo.push.PushCommand;

/* renamed from: com.vivo.push.b.y */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class StopServiceCommand extends PushCommand {

    /* renamed from: a */
    private String f20922a;

    @Override // com.vivo.push.PushCommand
    public final String toString() {
        return "StopServiceCommand";
    }

    public StopServiceCommand(String str) {
        super(2008);
        this.f20922a = str;
    }

    public StopServiceCommand() {
        super(2008);
    }

    @Override // com.vivo.push.PushCommand
    /* renamed from: c */
    public final void mo5322c(BundleWapper bundleWapper) {
        bundleWapper.m5730a("package_name", this.f20922a);
    }

    @Override // com.vivo.push.PushCommand
    /* renamed from: d */
    public final void mo5321d(BundleWapper bundleWapper) {
        this.f20922a = bundleWapper.m5734a("package_name");
    }
}
