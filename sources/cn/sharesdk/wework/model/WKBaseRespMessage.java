package cn.sharesdk.wework.model;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.utils.AppUtils;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.wework.utils.OpenDataUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.wework.model.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WKBaseRespMessage extends BaseMessage {

    /* renamed from: l */
    public String f3357l;

    /* renamed from: m */
    public String f3358m;

    /* renamed from: n */
    public int f3359n = 1;

    /* renamed from: o */
    public String f3360o;

    /* renamed from: p */
    public String f3361p;

    /* renamed from: q */
    public String f3362q;

    @Override // cn.sharesdk.wework.model.BaseMessage
    /* renamed from: a */
    public void mo21166a(Bundle bundle) {
        bundle.putString("_wwapi_basersp_transaction", this.f3345a);
        try {
            bundle.putString("_wwapi_basersp_appbundle", this.f3351h.getPackageName());
            bundle.putString("_wwapi_basersp_appname", this.f3351h.getString(AppUtils.m21716b().labelRes));
        } catch (Throwable unused) {
        }
        bundle.putInt("_wwobject_sdkVer", 4);
        bundle.putString("_wwobject_sdkVername", "2.0.12.6");
        bundle.putString("_err", this.f3358m);
        if (f3344f != null && TextUtils.isEmpty(this.f3352i)) {
            this.f3352i = f3344f.sessionKey(this.f3361p);
        }
        bundle.putByteArray("sessionKey", OpenDataUtils.m21161a(this.f3362q, this.f3352i));
    }

    @Override // cn.sharesdk.wework.model.BaseMessage
    /* renamed from: a */
    public boolean mo21167a() {
        return !TextUtils.isEmpty(this.f3357l);
    }

    @Override // cn.sharesdk.wework.model.BaseMessage
    /* renamed from: b */
    public void mo21165b(Bundle bundle) {
        this.f3345a = bundle.getString("_wwapi_basersp_transaction");
        this.f3346b = bundle.getString("_wwapi_basersp_appname");
        this.f3347c = bundle.getString("_wwapi_basersp_appbundle");
        this.f3348d = bundle.getInt("_wwobject_sdkVer", 0);
        this.f3349e = bundle.getString("_wwobject_sdkVername");
        this.f3358m = bundle.getString("_err", "");
        if (f3344f != null && TextUtils.isEmpty(this.f3352i)) {
            this.f3352i = f3344f.sessionKey(this.f3361p);
        }
        this.f3362q = OpenDataUtils.m21159a(bundle.getByteArray("sessionKey"), this.f3352i);
    }

    @Override // cn.sharesdk.wework.model.BaseMessage
    /* renamed from: a */
    public void mo21171a(Uri uri) {
        if (uri != null) {
            try {
                this.f3345a = uri.getQueryParameter("wwtr");
                this.f3348d = Integer.parseInt(uri.getQueryParameter("wwver"));
            } catch (Throwable th) {
                SSDKLog.m21740b().m21742a(th);
            }
            try {
                this.f3359n = Integer.parseInt(uri.getQueryParameter("errcode"));
            } catch (Throwable th2) {
                SSDKLog.m21740b().m21742a(th2);
            }
            this.f3360o = uri.getScheme();
        }
    }
}
