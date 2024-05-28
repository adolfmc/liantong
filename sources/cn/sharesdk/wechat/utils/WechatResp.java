package cn.sharesdk.wechat.utils;

import android.os.Bundle;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class WechatResp {

    /* renamed from: g */
    public int f3247g;

    /* renamed from: h */
    public String f3248h;

    /* renamed from: i */
    public String f3249i;

    /* renamed from: j */
    public String f3250j;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface ErrCode {
        public static final int ERR_AUTH_DENIED = -4;
        public static final int ERR_BAN = -6;
        public static final int ERR_COMM = -1;
        public static final int ERR_OK = 0;
        public static final int ERR_SENT_FAILED = -3;
        public static final int ERR_UNSUPPORT = -5;
        public static final int ERR_USER_CANCEL = -2;
    }

    /* renamed from: a */
    public abstract int mo21305a();

    /* renamed from: b */
    public void mo21303b(Bundle bundle) {
        bundle.putInt("_wxapi_command_type", mo21305a());
        bundle.putInt("_wxapi_baseresp_errcode", this.f3247g);
        bundle.putString("_wxapi_baseresp_errstr", this.f3248h);
        bundle.putString("_wxapi_baseresp_transaction", this.f3249i);
        bundle.putString("_wxapi_baseresp_openId", this.f3250j);
    }

    /* renamed from: a */
    public void mo21304a(Bundle bundle) {
        this.f3247g = bundle.getInt("_wxapi_baseresp_errcode");
        this.f3248h = bundle.getString("_wxapi_baseresp_errstr");
        this.f3249i = bundle.getString("_wxapi_baseresp_transaction");
        this.f3250j = bundle.getString("_wxapi_baseresp_openId");
    }
}
