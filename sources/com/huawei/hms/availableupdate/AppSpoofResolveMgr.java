package com.huawei.hms.availableupdate;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.huawei.hms.availableupdate.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class AppSpoofResolveMgr {

    /* renamed from: c */
    public static final AppSpoofResolveMgr f11039c = new AppSpoofResolveMgr();

    /* renamed from: d */
    private static final Object f11040d = new Object();

    /* renamed from: a */
    private final AtomicBoolean f11041a = new AtomicBoolean(false);

    /* renamed from: b */
    private final List<Activity> f11042b = new ArrayList(1);

    /* renamed from: a */
    public void m15193a(Activity activity) {
        synchronized (f11040d) {
            for (Activity activity2 : this.f11042b) {
                if (activity2 != null && activity2 != activity && !activity2.isFinishing()) {
                    activity2.finish();
                }
            }
            this.f11042b.add(activity);
        }
    }

    /* renamed from: b */
    public void m15191b(Activity activity) {
        synchronized (f11040d) {
            this.f11042b.remove(activity);
        }
    }

    /* renamed from: a */
    public void m15192a(boolean z) {
        this.f11041a.set(z);
    }

    /* renamed from: a */
    public AtomicBoolean m15194a() {
        return this.f11041a;
    }
}
