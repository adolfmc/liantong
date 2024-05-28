package com.huawei.hms.availableupdate;

import android.app.Activity;
import com.huawei.hms.support.log.HMSLog;
import java.lang.ref.WeakReference;

/* renamed from: com.huawei.hms.availableupdate.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class UpdateAdapterMgr {

    /* renamed from: b */
    public static final UpdateAdapterMgr f11046b = new UpdateAdapterMgr();

    /* renamed from: a */
    private WeakReference<Activity> f11047a;

    /* renamed from: a */
    public boolean m15187a(Activity activity) {
        HMSLog.m14110i("UpdateAdapterMgr", "onActivityCreate");
        Activity m15188a = m15188a();
        if (m15188a != null && !m15188a.isFinishing()) {
            activity.finish();
            HMSLog.m14110i("UpdateAdapterMgr", "finish one");
            return false;
        }
        this.f11047a = new WeakReference<>(activity);
        return true;
    }

    /* renamed from: b */
    public void m15186b(Activity activity) {
        HMSLog.m14110i("UpdateAdapterMgr", "onActivityDestroy");
        Activity m15188a = m15188a();
        if (activity == null || !activity.equals(m15188a)) {
            return;
        }
        HMSLog.m14110i("UpdateAdapterMgr", "reset");
        this.f11047a = null;
    }

    /* renamed from: a */
    private Activity m15188a() {
        WeakReference<Activity> weakReference = this.f11047a;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }
}
