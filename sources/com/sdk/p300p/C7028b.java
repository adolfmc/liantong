package com.sdk.p300p;

import android.content.Context;
import com.sdk.p294j.C7008a;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sdk.p.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C7028b {

    /* renamed from: a */
    public static boolean f18185a;

    /* renamed from: a */
    public static boolean m8141a(Context context) {
        long j;
        String str;
        if (f18185a) {
            return true;
        }
        Long m8158a = C7008a.m8158a(context, "access_limit_time");
        long currentTimeMillis = System.currentTimeMillis();
        if (m8158a == null) {
            j = Long.valueOf(currentTimeMillis);
            str = "access_limit_time";
        } else {
            if (currentTimeMillis - m8158a.longValue() > 600000) {
                C7008a.m8157a(context, "access_limit_time", Long.valueOf(currentTimeMillis));
            } else {
                Long m8158a2 = C7008a.m8158a(context, "access_limit_count");
                if (m8158a2 != null) {
                    return m8158a2.longValue() <= 50;
                }
            }
            j = 0L;
            str = "access_limit_count";
        }
        C7008a.m8157a(context, str, j);
        return true;
    }

    /* renamed from: b */
    public static void m8140b(Context context) {
        Long m8158a = C7008a.m8158a(context, "access_limit_count");
        C7008a.m8157a(context, "access_limit_count", Long.valueOf(m8158a == null ? 0L : m8158a.longValue() + 1));
    }
}
