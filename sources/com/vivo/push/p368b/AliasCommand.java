package com.vivo.push.p368b;

import com.vivo.push.BundleWapper;
import java.util.ArrayList;

/* renamed from: com.vivo.push.b.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class AliasCommand extends BaseAppCommand {

    /* renamed from: a */
    private ArrayList<String> f20869a;

    public AliasCommand(boolean z, String str, ArrayList<String> arrayList) {
        super(z ? 2002 : 2003, str);
        this.f20869a = arrayList;
    }

    @Override // com.vivo.push.p368b.BaseAppCommand, com.vivo.push.PushCommand
    /* renamed from: c */
    public final void mo5322c(BundleWapper bundleWapper) {
        super.mo5322c(bundleWapper);
        bundleWapper.m5729a("tags", this.f20869a);
    }

    @Override // com.vivo.push.p368b.BaseAppCommand, com.vivo.push.PushCommand
    /* renamed from: d */
    public final void mo5321d(BundleWapper bundleWapper) {
        super.mo5321d(bundleWapper);
        this.f20869a = bundleWapper.m5722c("tags");
    }

    @Override // com.vivo.push.p368b.BaseAppCommand, com.vivo.push.PushCommand
    public final String toString() {
        return "AliasCommand:" + m5326b();
    }
}
