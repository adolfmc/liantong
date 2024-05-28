package com.alipay.sdk.sys;

import android.content.Context;
import com.alipay.sdk.app.statistic.C2000a;
import com.alipay.sdk.data.C2009c;
import com.alipay.sdk.util.C2040c;
import com.p343ta.utdid2.device.UTDevice;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.sys.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2033b {

    /* renamed from: a */
    private static C2033b f3828a;

    /* renamed from: b */
    private Context f3829b;

    private C2033b() {
    }

    /* renamed from: a */
    public static C2033b m20772a() {
        if (f3828a == null) {
            f3828a = new C2033b();
        }
        return f3828a;
    }

    /* renamed from: b */
    public Context m20770b() {
        return this.f3829b;
    }

    /* renamed from: a */
    public void m20771a(Context context, C2009c c2009c) {
        this.f3829b = context.getApplicationContext();
    }

    /* renamed from: c */
    public C2009c m20769c() {
        return C2009c.m20855b();
    }

    /* renamed from: d */
    public static boolean m20768d() {
        for (String str : new String[]{"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"}) {
            if (new File(str).exists()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: e */
    public String m20767e() {
        try {
            return UTDevice.getUtdid(this.f3829b);
        } catch (Throwable th) {
            C2040c.m20715a(th);
            C2000a.m20898a("third", "GetUtdidEx", th);
            return "";
        }
    }
}
