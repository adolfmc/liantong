package com.huawei.hms.push.utils.p223ha;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.push.utils.ha.PushAnalyticsCenter */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class PushAnalyticsCenter {

    /* renamed from: a */
    private PushBaseAnalytics f11687a;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.huawei.hms.push.utils.ha.PushAnalyticsCenter$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class C5055a {

        /* renamed from: a */
        private static PushAnalyticsCenter f11688a = new PushAnalyticsCenter();
    }

    public static PushAnalyticsCenter getInstance() {
        return C5055a.f11688a;
    }

    public PushBaseAnalytics getPushAnalytics() {
        return this.f11687a;
    }

    public void register(PushBaseAnalytics pushBaseAnalytics) {
        this.f11687a = pushBaseAnalytics;
    }
}
