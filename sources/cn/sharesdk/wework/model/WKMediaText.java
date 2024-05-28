package cn.sharesdk.wework.model;

import android.os.Bundle;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.wework.model.WKMediaMessage;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.wework.model.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WKMediaText extends WKMediaMessage.AbstractC1886a {

    /* renamed from: j */
    public String f3375j;

    public WKMediaText() {
    }

    public WKMediaText(String str) {
        this.f3375j = str;
    }

    @Override // cn.sharesdk.wework.model.WKMediaMessage, cn.sharesdk.wework.model.WKBaseMessage, cn.sharesdk.wework.model.BaseMessage
    /* renamed from: a */
    public boolean mo21167a() {
        if (super.mo21167a()) {
            String str = this.f3375j;
            if (str == null || str.length() == 0 || this.f3375j.length() > 10240) {
                SSDKLog.m21740b().m21744a("checkArgs fail, text is invalid", new Object[0]);
                return false;
            }
            return true;
        }
        return false;
    }

    @Override // cn.sharesdk.wework.model.WKMediaMessage, cn.sharesdk.wework.model.WKBaseMessage, cn.sharesdk.wework.model.BaseMessage
    /* renamed from: a */
    public void mo21166a(Bundle bundle) {
        bundle.putString("_wwtextobject_text", this.f3375j);
        super.mo21166a(bundle);
    }

    @Override // cn.sharesdk.wework.model.WKMediaMessage, cn.sharesdk.wework.model.WKBaseMessage, cn.sharesdk.wework.model.BaseMessage
    /* renamed from: b */
    public void mo21165b(Bundle bundle) {
        this.f3375j = bundle.getString("_wwtextobject_text");
        super.mo21165b(bundle);
    }
}
