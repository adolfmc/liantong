package com.xiaomi.push;

import com.xiaomi.channel.commonutils.logger.AbstractC11049b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.q */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11478q {
    /* renamed from: a */
    public static String m2936a(String str, String str2) {
        try {
            return (String) C11479r.m2929a(null, "android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Exception e) {
            AbstractC11049b.m5282a("SystemProperties.get: " + e);
            return str2;
        }
    }
}
