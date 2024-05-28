package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.wechat.utils.WXMediaMessage;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.wechat.utils.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GetMessageFromWechatResp extends WechatResp {

    /* renamed from: a */
    public WXMediaMessage f3263a;

    @Override // cn.sharesdk.wechat.utils.WechatResp
    /* renamed from: a */
    public int mo21305a() {
        return 3;
    }

    public GetMessageFromWechatResp(Bundle bundle) {
        mo21304a(bundle);
    }

    @Override // cn.sharesdk.wechat.utils.WechatResp
    /* renamed from: a */
    public void mo21304a(Bundle bundle) {
        super.mo21304a(bundle);
        this.f3263a = WXMediaMessage.C1869a.m21322a(bundle);
    }

    @Override // cn.sharesdk.wechat.utils.WechatResp
    /* renamed from: b */
    public void mo21303b(Bundle bundle) {
        super.mo21303b(bundle);
        bundle.putAll(WXMediaMessage.C1869a.m21321a(this.f3263a));
    }
}
