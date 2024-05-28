package com.huawei.hms.availableupdate;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.huawei.hms.availableupdate.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class NotInstalledHmsResolveMgr {

    /* renamed from: b */
    public static final NotInstalledHmsResolveMgr f11043b = new NotInstalledHmsResolveMgr();

    /* renamed from: c */
    private static final Object f11044c = new Object();

    /* renamed from: a */
    private final List<Activity> f11045a = new ArrayList(1);

    /* renamed from: a */
    public void m15190a(Activity activity) {
        synchronized (f11044c) {
            for (Activity activity2 : this.f11045a) {
                if (activity2 != null && activity2 != activity && !activity2.isFinishing()) {
                    activity2.finish();
                }
            }
            this.f11045a.add(activity);
        }
    }

    /* renamed from: b */
    public void m15189b(Activity activity) {
        synchronized (f11044c) {
            this.f11045a.remove(activity);
        }
    }
}
