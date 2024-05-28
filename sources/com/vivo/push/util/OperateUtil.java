package com.vivo.push.util;

import com.vivo.push.p368b.BaseAppCommand;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.vivo.push.util.y */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class OperateUtil {
    /* renamed from: a */
    public static int m5334a(BaseAppCommand baseAppCommand) {
        SharePreferenceManager m5455b = SharePreferenceManager.m5455b();
        int b = baseAppCommand.m5326b();
        long currentTimeMillis = System.currentTimeMillis();
        int b2 = m5455b.m5413b("com.vivo.push_preferences.operate." + b + "OPERATE_COUNT", 0);
        long b3 = currentTimeMillis - m5455b.m5412b("com.vivo.push_preferences.operate." + b + "START_TIME", 0L);
        if (b3 > 86400000 || b3 < 0) {
            m5455b.m5418a("com.vivo.push_preferences.operate." + b + "START_TIME", System.currentTimeMillis());
            m5455b.m5419a("com.vivo.push_preferences.operate." + b + "OPERATE_COUNT", 1);
        } else if (b2 >= baseAppCommand.m5806d()) {
            return 1001;
        } else {
            m5455b.m5419a("com.vivo.push_preferences.operate." + b + "OPERATE_COUNT", b2 + 1);
        }
        return 0;
    }
}
