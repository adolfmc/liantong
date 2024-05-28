package com.huawei.hms.stats;

import android.os.Handler;
import android.os.Looper;
import com.huawei.hms.support.hianalytics.HiAnalyticsUtils;
import com.huawei.hms.support.log.HMSLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.huawei.hms.stats.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class AnalyticsCacheManager {

    /* renamed from: f */
    private static final AnalyticsCacheManager f11689f = new AnalyticsCacheManager();

    /* renamed from: a */
    private final Object f11690a = new Object();

    /* renamed from: b */
    private boolean f11691b = false;

    /* renamed from: c */
    private final List<Runnable> f11692c = new ArrayList();

    /* renamed from: d */
    private final Handler f11693d = new Handler(Looper.getMainLooper());

    /* renamed from: e */
    private final Runnable f11694e = new RunnableC5057a();

    /* compiled from: AnalyticsCacheManager.java */
    /* renamed from: com.huawei.hms.stats.a$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class RunnableC5057a implements Runnable {
        RunnableC5057a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            HMSLog.m14110i("AnalyticsCacheManager", "Timeout execCacheBi.");
            if (!HiAnalyticsUtils.getInstance().getInitFlag()) {
                AnalyticsCacheManager.this.m14177a();
            } else {
                AnalyticsCacheManager.this.m14175b();
            }
        }
    }

    private AnalyticsCacheManager() {
    }

    /* renamed from: c */
    public static AnalyticsCacheManager m14174c() {
        return f11689f;
    }

    /* renamed from: a */
    public void m14176a(Runnable runnable) {
        synchronized (this.f11690a) {
            if (runnable == null) {
                return;
            }
            if (this.f11691b) {
                return;
            }
            if (this.f11692c.size() >= 60) {
                return;
            }
            this.f11692c.add(runnable);
            this.f11693d.removeCallbacks(this.f11694e);
            this.f11693d.postDelayed(this.f11694e, 10000L);
        }
    }

    /* renamed from: b */
    public void m14175b() {
        synchronized (this.f11690a) {
            HMSLog.m14110i("AnalyticsCacheManager", "execCacheBi: cache size: " + this.f11692c.size());
            this.f11691b = true;
            Iterator<Runnable> it = this.f11692c.iterator();
            while (it.hasNext()) {
                it.next().run();
                it.remove();
            }
            this.f11691b = false;
        }
    }

    /* renamed from: a */
    public void m14177a() {
        synchronized (this.f11690a) {
            HMSLog.m14110i("AnalyticsCacheManager", "clear AnalyticsCache.");
            this.f11692c.clear();
        }
    }
}
