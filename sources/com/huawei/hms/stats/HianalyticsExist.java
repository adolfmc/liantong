package com.huawei.hms.stats;

import com.huawei.hms.support.log.HMSLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.stats.c */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class HianalyticsExist {

    /* renamed from: a */
    private static final Object f11697a = new Object();

    /* renamed from: b */
    private static boolean f11698b;

    /* renamed from: c */
    private static boolean f11699c;

    /* renamed from: a */
    public static boolean m14168a() {
        boolean z;
        synchronized (f11697a) {
            if (!f11698b) {
                boolean z2 = false;
                try {
                    Class.forName("com.huawei.hianalytics.process.HiAnalyticsInstance");
                    z = true;
                } catch (ClassNotFoundException unused) {
                    HMSLog.m14110i("HianalyticsExist", "In isHianalyticsExist, Failed to find class HiAnalyticsConfig.");
                    z = false;
                }
                try {
                    Class.forName("com.huawei.hms.hatool.HmsHiAnalyticsUtils");
                    z2 = true;
                } catch (ClassNotFoundException unused2) {
                    HMSLog.m14110i("HianalyticsExist", "In isHianalyticsExist, Failed to find class HmsHiAnalyticsUtils.");
                }
                if (z && !z2) {
                    f11699c = true;
                }
                f11698b = true;
                HMSLog.m14110i("HianalyticsExist", "hianalytics exist: " + f11699c);
            }
        }
        return f11699c;
    }
}
