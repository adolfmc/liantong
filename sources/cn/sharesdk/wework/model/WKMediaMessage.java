package cn.sharesdk.wework.model;

import android.os.Bundle;
import cn.sharesdk.framework.utils.SSDKLog;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.wework.model.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WKMediaMessage extends WKBaseMessage {

    /* renamed from: r */
    public String f3372r;

    /* renamed from: s */
    public String f3373s;

    /* renamed from: t */
    public byte[] f3374t;

    @Override // cn.sharesdk.wework.model.WKBaseMessage, cn.sharesdk.wework.model.BaseMessage
    /* renamed from: a */
    public void mo21166a(Bundle bundle) {
        super.mo21166a(bundle);
        bundle.putString("_wwobject_title", this.f3372r);
        bundle.putString("_wwobject_description", this.f3373s);
        bundle.putByteArray("_wwobject_thumbdata", this.f3374t);
    }

    @Override // cn.sharesdk.wework.model.WKBaseMessage, cn.sharesdk.wework.model.BaseMessage
    /* renamed from: b */
    public void mo21165b(Bundle bundle) {
        super.mo21165b(bundle);
        this.f3372r = bundle.getString("_wwobject_title");
        this.f3373s = bundle.getString("_wwobject_description");
        this.f3374t = bundle.getByteArray("_wwobject_thumbdata");
    }

    @Override // cn.sharesdk.wework.model.WKBaseMessage, cn.sharesdk.wework.model.BaseMessage
    /* renamed from: a */
    public boolean mo21167a() {
        byte[] bArr = this.f3374t;
        if (bArr != null && bArr.length > 32768) {
            SSDKLog.m21740b().m21744a("WWAPI.WWMediaMessage: checkArgs fail, thumbData is invalid", new Object[0]);
            return false;
        }
        String str = this.f3372r;
        if (str != null && str.length() > 512) {
            SSDKLog.m21740b().m21744a("WWAPI.WWMediaMessage: checkArgs fail, title is invalid", new Object[0]);
            return false;
        }
        String str2 = this.f3373s;
        if (str2 == null || str2.length() <= 1024) {
            return true;
        }
        SSDKLog.m21740b().m21744a("WWAPI.WWMediaMessage: checkArgs fail, description is invalid", new Object[0]);
        return false;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: WKMediaMessage.java */
    /* renamed from: cn.sharesdk.wework.model.g$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static abstract class AbstractC1886a extends WKMediaMessage {
        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: c */
        public int m21168c(String str) {
            if (str == null || str.length() == 0) {
                return 0;
            }
            File file = new File(str);
            if (file.exists()) {
                return (int) file.length();
            }
            return 0;
        }
    }
}
