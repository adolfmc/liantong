package com.huawei.hms.framework.network.grs.p220h;

import android.os.SystemClock;
import com.huawei.hms.framework.common.Logger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.framework.network.grs.h.d */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4954d {

    /* renamed from: a */
    private static final Map<String, C4955a> f11326a = new ConcurrentHashMap(16);

    /* renamed from: com.huawei.hms.framework.network.grs.h.d$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C4955a {

        /* renamed from: a */
        private final long f11327a;

        /* renamed from: b */
        private final long f11328b;

        public C4955a(long j, long j2) {
            this.f11327a = j;
            this.f11328b = j2;
        }

        /* renamed from: a */
        public boolean m14854a() {
            return SystemClock.elapsedRealtime() - this.f11328b <= this.f11327a;
        }
    }

    /* renamed from: a */
    public static C4955a m14856a(String str) {
        Logger.m15047v("RequestUtil", "map size of get is before: " + f11326a.size());
        C4955a c4955a = f11326a.get(str);
        Logger.m15047v("RequestUtil", "map size of get is after: " + f11326a.size());
        return c4955a;
    }

    /* renamed from: a */
    public static void m14855a(String str, C4955a c4955a) {
        Logger.m15047v("RequestUtil", "map size of put is before: " + f11326a.size());
        f11326a.put(str, c4955a);
        Logger.m15047v("RequestUtil", "map size of put is after: " + f11326a.size());
    }
}
