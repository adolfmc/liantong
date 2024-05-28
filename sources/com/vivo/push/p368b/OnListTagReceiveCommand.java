package com.vivo.push.p368b;

import com.vivo.push.BundleWapper;
import java.util.ArrayList;

/* renamed from: com.vivo.push.b.m */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class OnListTagReceiveCommand extends OnReceiveCommand {

    /* renamed from: a */
    private ArrayList<String> f20894a;

    @Override // com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    public final String toString() {
        return "OnListTagCommand";
    }

    public OnListTagReceiveCommand() {
        super(8);
    }

    /* renamed from: d */
    public final ArrayList<String> m5794d() {
        return this.f20894a;
    }

    @Override // com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    /* renamed from: c */
    public final void mo5322c(BundleWapper bundleWapper) {
        super.mo5322c(bundleWapper);
        bundleWapper.m5729a("tags_list", this.f20894a);
    }

    @Override // com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    /* renamed from: d */
    public final void mo5321d(BundleWapper bundleWapper) {
        super.mo5321d(bundleWapper);
        this.f20894a = bundleWapper.m5722c("tags_list");
    }
}
