package com.huawei.hms.framework.network.grs.p220h;

import com.huawei.hms.framework.common.Logger;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.framework.network.grs.h.e */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4956e {

    /* renamed from: a */
    private static final String f11329a = "e";

    /* renamed from: a */
    public static boolean m14853a(Long l) {
        if (l == null) {
            Logger.m15047v(f11329a, "Method isTimeExpire input param expireTime is null.");
            return true;
        }
        try {
        } catch (NumberFormatException unused) {
            Logger.m15047v(f11329a, "isSpExpire spValue NumberFormatException.");
        }
        if (l.longValue() - System.currentTimeMillis() >= 0) {
            Logger.m15049i(f11329a, "isSpExpire false.");
            return false;
        }
        Logger.m15049i(f11329a, "isSpExpire true.");
        return true;
    }

    /* renamed from: a */
    public static boolean m14852a(Long l, long j) {
        if (l == null) {
            Logger.m15047v(f11329a, "Method isTimeWillExpire input param expireTime is null.");
            return true;
        }
        try {
            if (l.longValue() - (System.currentTimeMillis() + j) >= 0) {
                Logger.m15047v(f11329a, "isSpExpire false.");
                return false;
            }
        } catch (NumberFormatException unused) {
            Logger.m15047v(f11329a, "isSpExpire spValue NumberFormatException.");
        }
        return true;
    }
}
