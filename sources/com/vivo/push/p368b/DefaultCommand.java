package com.vivo.push.p368b;

import com.vivo.push.BundleWapper;
import com.vivo.push.PushCommand;

/* renamed from: com.vivo.push.b.f */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class DefaultCommand extends PushCommand {

    /* renamed from: a */
    private int f20883a;

    @Override // com.vivo.push.PushCommand
    public final String toString() {
        return "DefaultCommand";
    }

    public DefaultCommand() {
        super(0);
    }

    /* renamed from: d */
    public final void m5801d() {
        this.f20883a = 3;
    }

    @Override // com.vivo.push.PushCommand
    /* renamed from: c */
    public final void mo5322c(BundleWapper bundleWapper) {
        if (bundleWapper != null) {
            bundleWapper.m5733a("APP_CLIENT_SWITCH_FLAG", this.f20883a);
        }
    }

    @Override // com.vivo.push.PushCommand
    /* renamed from: d */
    public final void mo5321d(BundleWapper bundleWapper) {
        if (bundleWapper != null) {
            this.f20883a = bundleWapper.m5724b("APP_CLIENT_SWITCH_FLAG", 0);
        }
    }
}
