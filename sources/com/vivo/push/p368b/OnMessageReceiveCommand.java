package com.vivo.push.p368b;

import android.text.TextUtils;
import com.vivo.push.BundleWapper;
import com.vivo.push.model.UnvarnishedMessage;

/* renamed from: com.vivo.push.b.o */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class OnMessageReceiveCommand extends OnVerifyReceiveCommand {

    /* renamed from: a */
    protected UnvarnishedMessage f20898a;

    @Override // com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    public final String toString() {
        return "OnMessageCommand";
    }

    public OnMessageReceiveCommand() {
        super(3);
    }

    @Override // com.vivo.push.p368b.OnVerifyReceiveCommand, com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    /* renamed from: c */
    public final void mo5322c(BundleWapper bundleWapper) {
        super.mo5322c(bundleWapper);
        bundleWapper.m5730a("msg_v1", this.f20898a.unpackToJson());
    }

    @Override // com.vivo.push.p368b.OnVerifyReceiveCommand, com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    /* renamed from: d */
    public final void mo5321d(BundleWapper bundleWapper) {
        super.mo5321d(bundleWapper);
        String m5734a = bundleWapper.m5734a("msg_v1");
        if (TextUtils.isEmpty(m5734a)) {
            return;
        }
        this.f20898a = new UnvarnishedMessage(m5734a);
        this.f20898a.setMsgId(m5764f());
    }

    /* renamed from: d */
    public final String m5787d() {
        UnvarnishedMessage unvarnishedMessage = this.f20898a;
        if (unvarnishedMessage == null) {
            return null;
        }
        return unvarnishedMessage.unpackToJson();
    }

    /* renamed from: e */
    public final UnvarnishedMessage m5786e() {
        return this.f20898a;
    }
}
