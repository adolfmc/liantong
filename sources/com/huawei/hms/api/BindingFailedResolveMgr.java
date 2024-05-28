package com.huawei.hms.api;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
class BindingFailedResolveMgr {

    /* renamed from: b */
    static final BindingFailedResolveMgr f10986b = new BindingFailedResolveMgr();

    /* renamed from: c */
    private static final Object f10987c = new Object();

    /* renamed from: a */
    List<Activity> f10988a = new ArrayList(1);

    BindingFailedResolveMgr() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m15238a(Activity activity) {
        synchronized (f10987c) {
            for (Activity activity2 : this.f10988a) {
                if (activity2 != null && activity2 != activity && !activity2.isFinishing()) {
                    activity2.finish();
                }
            }
            this.f10988a.add(activity);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m15237b(Activity activity) {
        synchronized (f10987c) {
            this.f10988a.remove(activity);
        }
    }
}
