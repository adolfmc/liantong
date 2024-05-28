package cn.sharesdk.wechat.utils;

import android.os.Bundle;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.wechat.utils.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AuthOptions {

    /* renamed from: a */
    public String f3251a;

    /* renamed from: b */
    public int f3252b = -1;

    /* renamed from: a */
    public void m21319a(Bundle bundle) {
        bundle.putString("_wxapi_sendauth_options_callback_classname", this.f3251a);
        bundle.putInt("_wxapi_sendauth_options_callback_flags", this.f3252b);
    }

    /* renamed from: b */
    public void m21318b(Bundle bundle) {
        this.f3251a = WechatTools.m21231a(bundle, "_wxapi_sendauth_options_callback_classname");
        this.f3252b = WechatTools.m21230a(bundle, "_wxapi_sendauth_options_callback_flags", -1);
    }
}
