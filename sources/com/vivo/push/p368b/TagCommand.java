package com.vivo.push.p368b;

import com.vivo.push.BundleWapper;
import java.io.Serializable;
import java.util.ArrayList;

/* renamed from: com.vivo.push.b.z */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class TagCommand extends BaseAppCommand {

    /* renamed from: a */
    private ArrayList<String> f20923a;

    @Override // com.vivo.push.p368b.BaseAppCommand, com.vivo.push.PushCommand
    public final String toString() {
        return "TagCommand";
    }

    public TagCommand(boolean z, String str, ArrayList<String> arrayList) {
        super(z ? 2004 : 2005, str);
        this.f20923a = arrayList;
    }

    @Override // com.vivo.push.p368b.BaseAppCommand, com.vivo.push.PushCommand
    /* renamed from: c */
    public final void mo5322c(BundleWapper bundleWapper) {
        super.mo5322c(bundleWapper);
        bundleWapper.m5731a("tags", (Serializable) this.f20923a);
    }

    @Override // com.vivo.push.p368b.BaseAppCommand, com.vivo.push.PushCommand
    /* renamed from: d */
    public final void mo5321d(BundleWapper bundleWapper) {
        super.mo5321d(bundleWapper);
        this.f20923a = bundleWapper.m5722c("tags");
    }
}
