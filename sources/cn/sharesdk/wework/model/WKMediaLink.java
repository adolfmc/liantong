package cn.sharesdk.wework.model;

import android.os.Bundle;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.wework.model.WKMediaMessage;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.wework.model.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WKMediaLink extends WKMediaMessage.AbstractC1886a {

    /* renamed from: j */
    public String f3368j;

    /* renamed from: k */
    public String f3369k;

    /* renamed from: p */
    public boolean f3370p;

    /* renamed from: q */
    public String f3371q;

    @Override // cn.sharesdk.wework.model.WKMediaMessage, cn.sharesdk.wework.model.WKBaseMessage, cn.sharesdk.wework.model.BaseMessage
    /* renamed from: a */
    public boolean mo21167a() {
        if (super.mo21167a()) {
            String str = this.f3368j;
            if (str == null || str.length() == 0 || this.f3368j.length() > 10240) {
                SSDKLog.m21740b().m21744a("checkArgs fail, webpageUrl is invalid", new Object[0]);
                return false;
            }
            return true;
        }
        return false;
    }

    @Override // cn.sharesdk.wework.model.WKMediaMessage, cn.sharesdk.wework.model.WKBaseMessage, cn.sharesdk.wework.model.BaseMessage
    /* renamed from: a */
    public void mo21166a(Bundle bundle) {
        bundle.putString("_wwwebpageobject_thumbUrl", this.f3369k);
        bundle.putString("_wwwebpageobject_webpageUrl", this.f3368j);
        bundle.putBoolean("_withshareticket", this.f3370p);
        bundle.putString("_state", this.f3371q);
        super.mo21166a(bundle);
    }

    @Override // cn.sharesdk.wework.model.WKMediaMessage, cn.sharesdk.wework.model.WKBaseMessage, cn.sharesdk.wework.model.BaseMessage
    /* renamed from: b */
    public void mo21165b(Bundle bundle) {
        this.f3369k = bundle.getString("_wwwebpageobject_thumbUrl");
        this.f3368j = bundle.getString("_wwwebpageobject_webpageUrl");
        this.f3370p = bundle.getBoolean("_withshareticket");
        this.f3371q = bundle.getString("_state");
        super.mo21165b(bundle);
    }
}
