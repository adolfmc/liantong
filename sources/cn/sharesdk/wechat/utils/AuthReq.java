package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.framework.utils.SSDKLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.wechat.utils.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AuthReq extends WechatReq {

    /* renamed from: a */
    public String f3253a;

    /* renamed from: b */
    public String f3254b;

    /* renamed from: c */
    public String f3255c;

    /* renamed from: d */
    public AuthOptions f3256d;

    @Override // cn.sharesdk.wechat.utils.WechatReq
    /* renamed from: a */
    public int mo21237a() {
        return 1;
    }

    @Override // cn.sharesdk.wechat.utils.WechatReq
    /* renamed from: a */
    public void mo21236a(Bundle bundle) {
        super.mo21236a(bundle);
        this.f3253a = bundle.getString("_wxapi_sendauth_req_scope");
        this.f3254b = bundle.getString("_wxapi_sendauth_req_state");
        this.f3255c = bundle.getString("_wxapi_sendauth_req_ext_data");
        this.f3256d = new AuthOptions();
        this.f3256d.m21318b(bundle);
    }

    @Override // cn.sharesdk.wechat.utils.WechatReq
    /* renamed from: b */
    public void mo21234b(Bundle bundle) {
        super.mo21234b(bundle);
        bundle.putString("_wxapi_sendauth_req_scope", this.f3253a);
        bundle.putString("_wxapi_sendauth_req_state", this.f3254b);
        bundle.putString("_wxapi_sendauth_req_ext_data", this.f3255c);
        AuthOptions authOptions = this.f3256d;
        if (authOptions != null) {
            authOptions.m21319a(bundle);
        }
    }

    @Override // cn.sharesdk.wechat.utils.WechatReq
    /* renamed from: b */
    public boolean mo21235b() {
        String str = this.f3253a;
        if (str != null && str.length() != 0 && this.f3253a.length() <= 1024) {
            String str2 = this.f3254b;
            if (str2 == null || str2.length() <= 1024) {
                return true;
            }
            SSDKLog.m21740b().m21744a("MicroMsg.SDK.SendAuth.Req", "checkArgs fail, state is invalid");
            return false;
        }
        SSDKLog.m21740b().m21744a("MicroMsg.SDK.SendAuth.Req", "checkArgs fail, scope is invalid");
        return false;
    }
}
