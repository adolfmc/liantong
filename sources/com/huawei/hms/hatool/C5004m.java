package com.huawei.hms.hatool;

import android.content.Context;

/* renamed from: com.huawei.hms.hatool.m */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C5004m {

    /* renamed from: b */
    private static C5004m f11458b;

    /* renamed from: c */
    private static final Object f11459c = new Object();

    /* renamed from: a */
    private Context f11460a;

    private C5004m() {
    }

    /* renamed from: a */
    public static C5004m m14611a() {
        if (f11458b == null) {
            m14608b();
        }
        return f11458b;
    }

    /* renamed from: b */
    private static synchronized void m14608b() {
        synchronized (C5004m.class) {
            if (f11458b == null) {
                f11458b = new C5004m();
            }
        }
    }

    /* renamed from: a */
    public void m14610a(Context context) {
        synchronized (f11459c) {
            if (this.f11460a != null) {
                C5029v.m14451f("hmsSdk", "DataManager already initialized.");
                return;
            }
            this.f11460a = context;
            C5023s.m14511c().m14512b().m14726a(this.f11460a);
            C5023s.m14511c().m14512b().m14707j(context.getPackageName());
            C4993j.m14673a().m14672a(context);
        }
    }

    /* renamed from: a */
    public void m14609a(String str) {
        C5029v.m14455c("hmsSdk", "HiAnalyticsDataManager.setAppid(String appid) is execute.");
        Context context = this.f11460a;
        if (context == null) {
            C5029v.m14452e("hmsSdk", "sdk is not init");
            return;
        }
        C5023s.m14511c().m14512b().m14709i(C4980e1.m14743a("appID", str, "[a-zA-Z0-9_][a-zA-Z0-9. _-]{0,255}", context.getPackageName()));
    }
}
