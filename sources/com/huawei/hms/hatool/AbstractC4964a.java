package com.huawei.hms.hatool;

import android.content.Context;
import java.util.LinkedHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.hatool.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class AbstractC4964a {

    /* renamed from: a */
    private static C5039z0 f11340a;

    /* renamed from: a */
    private static synchronized C5039z0 m14824a() {
        C5039z0 c5039z0;
        synchronized (AbstractC4964a.class) {
            if (f11340a == null) {
                f11340a = C5019q.m14543c().m14544b();
            }
            c5039z0 = f11340a;
        }
        return c5039z0;
    }

    /* renamed from: a */
    public static void m14823a(int i, String str, LinkedHashMap<String, String> linkedHashMap) {
        if (m14824a() == null || !C5021q1.m14518b().m14519a()) {
            return;
        }
        if (i == 1 || i == 0) {
            f11340a.m14408a(i, str, linkedHashMap);
            return;
        }
        C5029v.m14453d("hmsSdk", "Data type no longer collects range.type: " + i);
    }

    @Deprecated
    /* renamed from: a */
    public static void m14822a(Context context, String str, String str2) {
        if (m14824a() != null) {
            f11340a.m14407a(context, str, str2);
        }
    }

    /* renamed from: b */
    public static void m14820b(int i, String str, LinkedHashMap<String, String> linkedHashMap) {
        if (m14824a() == null || !C5021q1.m14518b().m14519a()) {
            return;
        }
        if (i == 1 || i == 0) {
            f11340a.m14404b(i, str, linkedHashMap);
            return;
        }
        C5029v.m14453d("hmsSdk", "Data type no longer collects range.type: " + i);
    }

    /* renamed from: b */
    public static boolean m14821b() {
        return C5019q.m14543c().m14546a();
    }

    /* renamed from: c */
    public static void m14819c() {
        if (m14824a() == null || !C5021q1.m14518b().m14519a()) {
            return;
        }
        f11340a.m14409a(-1);
    }
}
