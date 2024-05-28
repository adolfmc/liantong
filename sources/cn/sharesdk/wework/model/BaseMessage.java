package cn.sharesdk.wework.model;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.wework.utils.ReflecterHelper;
import cn.sharesdk.wework.utils.SessionProvider;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.wework.model.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class BaseMessage {

    /* renamed from: f */
    protected static SessionProvider f3344f;

    /* renamed from: a */
    public String f3345a;

    /* renamed from: b */
    public String f3346b;

    /* renamed from: c */
    public String f3347c;

    /* renamed from: d */
    public int f3348d;

    /* renamed from: e */
    public String f3349e;

    /* renamed from: g */
    protected String f3350g;

    /* renamed from: h */
    protected Context f3351h = null;

    /* renamed from: i */
    protected String f3352i = "";

    /* renamed from: a */
    public void mo21169a(Intent intent, String str) {
    }

    /* renamed from: a */
    public abstract void mo21171a(Uri uri);

    /* renamed from: a */
    public abstract void mo21166a(Bundle bundle);

    /* renamed from: a */
    public void m21174a(boolean z) {
    }

    /* renamed from: a */
    public abstract boolean mo21167a();

    /* renamed from: b */
    public abstract void mo21165b(Bundle bundle);

    /* renamed from: a */
    public void m21177a(Context context) {
        this.f3351h = context;
    }

    /* renamed from: a */
    public void m21175a(String str) {
        this.f3352i = str;
    }

    /* renamed from: a */
    public static Bundle m21176a(BaseMessage baseMessage, int i) {
        String str = null;
        if (baseMessage == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        baseMessage.mo21166a(bundle);
        if (i == 0) {
            str = "com.tencent.wework.api.model.WWAuthMessage$Req";
        } else if (i == 1) {
            str = "com.tencent.wework.api.model.WWMediaText";
        } else if (i == 2) {
            str = "com.tencent.wework.api.model.WWMediaImage";
        } else if (i == 3) {
            str = "com.tencent.wework.api.model.WWMediaFile";
        } else if (i == 4) {
            str = "com.tencent.wework.api.model.WWMediaVideo";
        } else if (i == 5) {
            str = "com.tencent.wework.api.model.WWMediaLink";
        } else if (i == 10) {
            str = "com.tencent.wework.api.model.WWMediaMiniProgram";
        }
        bundle.putString("_wwobject_identifier_", str);
        return bundle;
    }

    /* renamed from: b */
    public static BaseMessage m21173b(Uri uri) {
        try {
            if (TextUtils.isEmpty(uri.getQueryParameter("wwoid"))) {
                return null;
            }
            BaseMessage baseMessage = (BaseMessage) ReflecterHelper.m21155a("cn.sharesdk.wework.model.WKAuthMessage$Resp");
            baseMessage.mo21171a(uri);
            return baseMessage;
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            return null;
        }
    }

    /* renamed from: b */
    public void m21172b(String str) {
        this.f3350g = str;
    }
}
