package com.huawei.hms.hatool;

import android.content.Context;
import java.util.LinkedHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class HmsHiAnalyticsUtils {
    public static void enableLog() {
        C4972c.m14782a();
    }

    public static boolean getInitFlag() {
        return AbstractC4964a.m14821b();
    }

    public static void init(Context context, boolean z, boolean z2, boolean z3, String str, String str2) {
        new C4967b(context).m14799a(z).m14797c(z2).m14798b(z3).m14801a(0, str).m14801a(1, str).m14800a(str2).m14802a();
    }

    public static void onEvent(Context context, String str, String str2) {
        AbstractC4964a.m14822a(context, str, str2);
    }

    public static void onReport() {
        AbstractC4964a.m14819c();
    }

    public static void onStreamEvent(int i, String str, LinkedHashMap<String, String> linkedHashMap) {
        AbstractC4964a.m14820b(i, str, linkedHashMap);
    }

    public static void onEvent(int i, String str, LinkedHashMap<String, String> linkedHashMap) {
        AbstractC4964a.m14823a(i, str, linkedHashMap);
    }
}
