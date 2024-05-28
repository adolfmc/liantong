package com.vivo.push.p368b;

import com.vivo.push.BundleWapper;
import com.vivo.push.PushCommand;

/* renamed from: com.vivo.push.b.s */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class OnReceiveCommand extends PushCommand {

    /* renamed from: a */
    private String f20911a;

    /* renamed from: b */
    private int f20912b;

    @Override // com.vivo.push.PushCommand
    public String toString() {
        return "OnReceiveCommand";
    }

    public OnReceiveCommand(int i) {
        super(i);
        this.f20911a = null;
        this.f20912b = 0;
    }

    /* renamed from: h */
    public final String m5770h() {
        return this.f20911a;
    }

    /* renamed from: i */
    public final int m5769i() {
        return this.f20912b;
    }

    @Override // com.vivo.push.PushCommand
    /* renamed from: c */
    public void mo5322c(BundleWapper bundleWapper) {
        bundleWapper.m5730a("req_id", this.f20911a);
        bundleWapper.m5733a("status_msg_code", this.f20912b);
    }

    @Override // com.vivo.push.PushCommand
    /* renamed from: d */
    public void mo5321d(BundleWapper bundleWapper) {
        this.f20911a = bundleWapper.m5734a("req_id");
        this.f20912b = bundleWapper.m5724b("status_msg_code", this.f20912b);
    }
}
