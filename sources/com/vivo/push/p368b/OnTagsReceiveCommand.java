package com.vivo.push.p368b;

import com.vivo.push.BundleWapper;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.vivo.push.b.t */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class OnTagsReceiveCommand extends OnReceiveCommand {

    /* renamed from: a */
    private ArrayList<String> f20913a;

    /* renamed from: b */
    private ArrayList<String> f20914b;

    @Override // com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    public final String toString() {
        return "OnSetTagsCommand";
    }

    public OnTagsReceiveCommand(int i) {
        super(i);
        this.f20913a = null;
        this.f20914b = null;
    }

    /* renamed from: d */
    public final ArrayList<String> m5768d() {
        return this.f20913a;
    }

    /* renamed from: e */
    public final List<String> m5767e() {
        return this.f20914b;
    }

    @Override // com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    /* renamed from: c */
    public final void mo5322c(BundleWapper bundleWapper) {
        super.mo5322c(bundleWapper);
        bundleWapper.m5729a("content", this.f20913a);
        bundleWapper.m5729a("error_msg", this.f20914b);
    }

    @Override // com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    /* renamed from: d */
    public final void mo5321d(BundleWapper bundleWapper) {
        super.mo5321d(bundleWapper);
        this.f20913a = bundleWapper.m5722c("content");
        this.f20914b = bundleWapper.m5722c("error_msg");
    }
}
