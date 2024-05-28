package cn.sharesdk.wework.model;

import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import cn.sharesdk.framework.utils.AppUtils;
import cn.sharesdk.framework.utils.SSDKLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.wework.model.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WKBaseMessage extends BaseMessage {

    /* renamed from: l */
    public String f3353l;

    /* renamed from: m */
    public String f3354m;

    /* renamed from: n */
    public String f3355n;

    /* renamed from: o */
    protected boolean f3356o;

    @Override // cn.sharesdk.wework.model.BaseMessage
    /* renamed from: a */
    public void mo21171a(Uri uri) {
    }

    @Override // cn.sharesdk.wework.model.BaseMessage
    /* renamed from: a */
    public boolean mo21167a() {
        return true;
    }

    @Override // cn.sharesdk.wework.model.BaseMessage
    /* renamed from: a */
    public void mo21166a(Bundle bundle) {
        String valueOf = String.valueOf(SystemClock.uptimeMillis());
        this.f3345a = valueOf;
        bundle.putString("_wwapi_basereq_transaction", valueOf);
        bundle.putString("_wwapi_basereq_openid", this.f3353l);
        bundle.putString("_wwapi_basereq_agentid", this.f3354m);
        bundle.putString("_schema", this.f3355n);
        try {
            bundle.putString("_wwapi_basereq_appbundle", this.f3351h.getPackageName());
            bundle.putString("_wwapi_basereq_appname", this.f3351h.getString(AppUtils.m21716b().labelRes));
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
        }
        bundle.putInt("_wwobject_sdkVer", 4);
        bundle.putString("_wwobject_sdkVername", "2.0.12.6");
        bundle.putBoolean("needUpdateSession", this.f3356o);
        if (f3344f == null || !TextUtils.isEmpty(this.f3352i)) {
            return;
        }
        this.f3352i = f3344f.sessionKey(this.f3353l);
    }

    @Override // cn.sharesdk.wework.model.BaseMessage
    /* renamed from: b */
    public void mo21165b(Bundle bundle) {
        this.f3345a = bundle.getString("_wwapi_basereq_transaction");
        this.f3353l = bundle.getString("_wwapi_basereq_openid");
        this.f3354m = bundle.getString("_wwapi_basereq_agentid");
        this.f3355n = bundle.getString("_schema");
        this.f3346b = bundle.getString("_wwapi_basereq_appname");
        this.f3347c = bundle.getString("_wwapi_basereq_appbundle");
        this.f3348d = bundle.getInt("_wwobject_sdkVer", 0);
        this.f3349e = bundle.getString("_wwobject_sdkVername");
        this.f3356o = bundle.getBoolean("needUpdateSession");
        if (f3344f == null || !TextUtils.isEmpty(this.f3352i)) {
            return;
        }
        this.f3352i = f3344f.sessionKey(this.f3353l);
    }
}
