package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.service.C11615q;
import java.util.Arrays;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.ck */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11241ck {

    /* renamed from: a */
    private static final List<String> f21720a = Arrays.asList("001", "002", "003", "004", "005");

    /* renamed from: a */
    private static Boolean f21719a = null;

    /* renamed from: a */
    public static void m4524a(String str, String str2) {
    }

    /* renamed from: a */
    public static boolean m4526a(Context context) {
        if (f21719a == null) {
            try {
                if (!C11469j.m2972a(context)) {
                    f21719a = false;
                }
                String m2429a = C11615q.m2429a(context);
                if (!TextUtils.isEmpty(m2429a) && m2429a.length() >= 3) {
                    f21719a = Boolean.valueOf(f21720a.contains(m2429a.substring(m2429a.length() - 3)));
                } else {
                    f21719a = false;
                }
                m4525a("Sampling statistical connection quality: " + f21719a);
            } catch (Throwable th) {
                f21719a = false;
                AbstractC11049b.m5269c("Push-ConnectionQualityStatsHelper", "Determine sampling switch error: " + th);
            }
        }
        return f21719a.booleanValue();
    }

    /* renamed from: a */
    static void m4525a(String str) {
        m4524a("Push-ConnectionQualityStatsHelper", str);
    }
}
