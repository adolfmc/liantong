package cn.sharesdk.wechat.utils;

import android.os.Bundle;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.wechat.utils.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AuthResp extends WechatResp {

    /* renamed from: a */
    public String f3257a;

    /* renamed from: b */
    public String f3258b;

    /* renamed from: c */
    public String f3259c;

    /* renamed from: d */
    public String f3260d;

    /* renamed from: e */
    public String f3261e;

    /* renamed from: f */
    public boolean f3262f = false;

    @Override // cn.sharesdk.wechat.utils.WechatResp
    /* renamed from: a */
    public int mo21305a() {
        return 1;
    }

    public AuthResp(Bundle bundle) {
        mo21304a(bundle);
    }

    @Override // cn.sharesdk.wechat.utils.WechatResp
    /* renamed from: a */
    public void mo21304a(Bundle bundle) {
        super.mo21304a(bundle);
        this.f3257a = bundle.getString("_wxapi_sendauth_resp_token");
        this.f3258b = bundle.getString("_wxapi_sendauth_resp_state");
        this.f3259c = bundle.getString("_wxapi_sendauth_resp_url");
        this.f3260d = bundle.getString("_wxapi_sendauth_resp_lang");
        this.f3261e = bundle.getString("_wxapi_sendauth_resp_country");
        this.f3262f = bundle.getBoolean("_wxapi_sendauth_resp_auth_result");
    }

    @Override // cn.sharesdk.wechat.utils.WechatResp
    /* renamed from: b */
    public void mo21303b(Bundle bundle) {
        super.mo21303b(bundle);
        bundle.putString("_wxapi_sendauth_resp_token", this.f3257a);
        bundle.putString("_wxapi_sendauth_resp_state", this.f3258b);
        bundle.putString("_wxapi_sendauth_resp_url", this.f3259c);
        bundle.putString("_wxapi_sendauth_resp_lang", this.f3260d);
        bundle.putString("_wxapi_sendauth_resp_country", this.f3261e);
        bundle.putBoolean("_wxapi_sendauth_resp_auth_result", this.f3262f);
    }
}
