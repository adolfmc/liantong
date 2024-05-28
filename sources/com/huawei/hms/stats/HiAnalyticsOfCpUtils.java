package com.huawei.hms.stats;

import android.content.Context;
import com.huawei.hianalytics.process.HiAnalyticsInstance;
import com.huawei.hms.utils.HMSBIInitializer;
import java.util.LinkedHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.stats.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class HiAnalyticsOfCpUtils {

    /* renamed from: a */
    private static HiAnalyticsInstance f11696a;

    /* renamed from: a */
    private static HiAnalyticsInstance m14173a(Context context) {
        HiAnalyticsInstance analyticsInstance = HMSBIInitializer.getInstance(context).getAnalyticsInstance();
        f11696a = analyticsInstance;
        return analyticsInstance;
    }

    /* renamed from: b */
    public static void m14169b(Context context, int i, String str, LinkedHashMap<String, String> linkedHashMap) {
        if (m14173a(context) != null) {
            f11696a.onStreamEvent(i, str, linkedHashMap);
        }
    }

    /* renamed from: a */
    public static void m14170a(Context context, String str, String str2) {
        if (m14173a(context) != null) {
            f11696a.onEvent(context, str, str2);
        }
    }

    /* renamed from: a */
    public static void m14171a(Context context, int i, String str, LinkedHashMap<String, String> linkedHashMap) {
        if (m14173a(context) != null) {
            f11696a.onEvent(i, str, linkedHashMap);
        }
    }

    /* renamed from: a */
    public static void m14172a(Context context, int i) {
        if (m14173a(context) != null) {
            f11696a.onReport(i);
        }
    }
}
