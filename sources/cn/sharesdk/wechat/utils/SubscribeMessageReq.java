package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.framework.utils.SSDKLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.wechat.utils.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SubscribeMessageReq extends WechatReq {

    /* renamed from: a */
    public int f3267a;

    /* renamed from: b */
    public String f3268b;

    /* renamed from: c */
    public String f3269c;

    @Override // cn.sharesdk.wechat.utils.WechatReq
    /* renamed from: a */
    public int mo21237a() {
        return 18;
    }

    @Override // cn.sharesdk.wechat.utils.WechatReq
    /* renamed from: a */
    public void mo21236a(Bundle bundle) {
        super.mo21236a(bundle);
        this.f3267a = bundle.getInt("_wxapi_subscribemessage_req_scene");
        this.f3268b = bundle.getString("_wxapi_subscribemessage_req_templateid");
        this.f3269c = bundle.getString("_wxapi_subscribemessage_req_reserved");
    }

    @Override // cn.sharesdk.wechat.utils.WechatReq
    /* renamed from: b */
    public void mo21234b(Bundle bundle) {
        super.mo21234b(bundle);
        bundle.putInt("_wxapi_subscribemessage_req_scene", this.f3267a);
        bundle.putString("_wxapi_subscribemessage_req_templateid", this.f3268b);
        bundle.putString("_wxapi_subscribemessage_req_reserved", this.f3269c);
    }

    @Override // cn.sharesdk.wechat.utils.WechatReq
    /* renamed from: b */
    public boolean mo21235b() {
        String str = this.f3268b;
        if (str != null && str.length() != 0) {
            if (this.f3268b.length() > 1024) {
                SSDKLog.m21740b().m21733d("ShareSDK", "MicroMsg.SDK.SubscribeMessage.ReqcheckArgs fail, templateID is too long");
                return false;
            }
            String str2 = this.f3269c;
            if (str2 == null || str2.length() <= 1024) {
                return true;
            }
            SSDKLog.m21740b().m21733d("ShareSDK", "MicroMsg.SDK.SubscribeMessage.ReqcheckArgs fail, reserved is too long");
            return false;
        }
        SSDKLog.m21740b().m21733d("ShareSDK", "MicroMsg.SDK.SubscribeMessage.ReqcheckArgs fail, templateID is null");
        return false;
    }
}
