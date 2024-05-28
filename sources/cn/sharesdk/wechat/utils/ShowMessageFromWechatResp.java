package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.wechat.utils.WXMediaMessage;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.wechat.utils.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ShowMessageFromWechatResp extends WechatReq {

    /* renamed from: a */
    public WXMediaMessage f3264a;

    /* renamed from: b */
    public String f3265b;

    /* renamed from: c */
    public String f3266c;

    @Override // cn.sharesdk.wechat.utils.WechatReq
    /* renamed from: a */
    public int mo21237a() {
        return 4;
    }

    public ShowMessageFromWechatResp(Bundle bundle) {
        mo21236a(bundle);
    }

    @Override // cn.sharesdk.wechat.utils.WechatReq
    /* renamed from: a */
    public void mo21236a(Bundle bundle) {
        super.mo21236a(bundle);
        this.f3265b = bundle.getString("_wxapi_showmessage_req_lang");
        this.f3266c = bundle.getString("_wxapi_showmessage_req_country");
        this.f3264a = WXMediaMessage.C1869a.m21322a(bundle);
    }

    @Override // cn.sharesdk.wechat.utils.WechatReq
    /* renamed from: b */
    public void mo21234b(Bundle bundle) {
        Bundle m21321a = WXMediaMessage.C1869a.m21321a(this.f3264a);
        super.mo21234b(m21321a);
        bundle.putString("_wxapi_showmessage_req_lang", this.f3265b);
        bundle.putString("_wxapi_showmessage_req_country", this.f3266c);
        bundle.putAll(m21321a);
    }

    @Override // cn.sharesdk.wechat.utils.WechatReq
    /* renamed from: b */
    public boolean mo21235b() {
        WXMediaMessage wXMediaMessage = this.f3264a;
        if (wXMediaMessage == null) {
            SSDKLog.m21740b().m21744a("checkArgs fail, message is null", new Object[0]);
            return false;
        }
        return wXMediaMessage.m21323a();
    }
}
