package com.vivo.push.p368b;

import android.text.TextUtils;
import com.vivo.push.BundleWapper;
import com.vivo.push.model.InsideNotificationItem;
import com.vivo.push.util.MessageConvertUtil;

/* renamed from: com.vivo.push.b.q */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class OnNotifyArrivedReceiveCommand extends OnVerifyReceiveCommand {

    /* renamed from: a */
    protected InsideNotificationItem f20909a;

    /* renamed from: b */
    private String f20910b;

    @Override // com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    public final String toString() {
        return "OnNotifyArrivedCommand";
    }

    public OnNotifyArrivedReceiveCommand() {
        super(4);
    }

    /* renamed from: d */
    public final InsideNotificationItem m5772d() {
        return this.f20909a;
    }

    @Override // com.vivo.push.p368b.OnVerifyReceiveCommand, com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    /* renamed from: c */
    public final void mo5322c(BundleWapper bundleWapper) {
        super.mo5322c(bundleWapper);
        this.f20910b = MessageConvertUtil.m5337b(this.f20909a);
        bundleWapper.m5730a("notification_v1", this.f20910b);
    }

    @Override // com.vivo.push.p368b.OnVerifyReceiveCommand, com.vivo.push.p368b.OnReceiveCommand, com.vivo.push.PushCommand
    /* renamed from: d */
    public final void mo5321d(BundleWapper bundleWapper) {
        super.mo5321d(bundleWapper);
        this.f20910b = bundleWapper.m5734a("notification_v1");
        if (TextUtils.isEmpty(this.f20910b)) {
            return;
        }
        this.f20909a = MessageConvertUtil.m5338a(this.f20910b);
        InsideNotificationItem insideNotificationItem = this.f20909a;
        if (insideNotificationItem != null) {
            insideNotificationItem.setMsgId(m5764f());
        }
    }

    /* renamed from: e */
    public final String m5771e() {
        if (TextUtils.isEmpty(this.f20910b)) {
            InsideNotificationItem insideNotificationItem = this.f20909a;
            if (insideNotificationItem == null) {
                return null;
            }
            return MessageConvertUtil.m5337b(insideNotificationItem);
        }
        return this.f20910b;
    }
}
