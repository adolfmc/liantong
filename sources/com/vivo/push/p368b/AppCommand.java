package com.vivo.push.p368b;

import com.vivo.push.BundleWapper;

/* renamed from: com.vivo.push.b.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class AppCommand extends BaseAppCommand {

    /* renamed from: a */
    private String f20870a;

    /* renamed from: b */
    private String f20871b;

    /* renamed from: c */
    private int f20872c;

    /* renamed from: d */
    private boolean f20873d;

    public AppCommand(boolean z, String str) {
        super(z ? 2006 : 2007, str);
        this.f20872c = 1;
        this.f20873d = false;
    }

    /* renamed from: a */
    public final void m5811a(int i) {
        this.f20872c = i;
    }

    @Override // com.vivo.push.p368b.BaseAppCommand, com.vivo.push.PushCommand
    /* renamed from: c */
    public final void mo5322c(BundleWapper bundleWapper) {
        super.mo5322c(bundleWapper);
        bundleWapper.m5730a("sdk_clients", this.f20870a);
        bundleWapper.m5732a("sdk_version", 341L);
        bundleWapper.m5730a("PUSH_REGID", this.f20871b);
        if (m5326b() == 2007) {
            bundleWapper.m5733a("PUSH_UNBIND_SOURCE_CODE", this.f20872c);
        }
    }

    @Override // com.vivo.push.p368b.BaseAppCommand, com.vivo.push.PushCommand
    /* renamed from: d */
    public final void mo5321d(BundleWapper bundleWapper) {
        super.mo5321d(bundleWapper);
        this.f20870a = bundleWapper.m5734a("sdk_clients");
        this.f20871b = bundleWapper.m5734a("PUSH_REGID");
        if (m5326b() == 2007) {
            this.f20872c = bundleWapper.m5724b("PUSH_UNBIND_SOURCE_CODE", 1);
        }
    }

    @Override // com.vivo.push.p368b.BaseAppCommand, com.vivo.push.PushCommand
    public final String toString() {
        return "AppCommand:" + m5326b();
    }
}
