package cn.sharesdk.wechat.utils;

import android.os.Bundle;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.wechat.utils.m */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class WechatReq {

    /* renamed from: e */
    public String f3298e;

    /* renamed from: f */
    public String f3299f;

    /* renamed from: a */
    public abstract int mo21237a();

    /* renamed from: b */
    public abstract boolean mo21235b();

    /* renamed from: b */
    public void mo21234b(Bundle bundle) {
        bundle.putInt("_wxapi_command_type", mo21237a());
        bundle.putString("_wxapi_basereq_transaction", this.f3298e);
        bundle.putString("_wxapi_basereq_openid", this.f3299f);
    }

    /* renamed from: a */
    public void mo21236a(Bundle bundle) {
        this.f3298e = WechatTools.m21231a(bundle, "_wxapi_basereq_transaction");
        this.f3299f = WechatTools.m21231a(bundle, "_wxapi_basereq_openid");
    }
}
