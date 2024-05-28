package com.huawei.hms.framework.network.grs;

import android.content.Context;
import com.huawei.hms.framework.common.Logger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.framework.network.grs.d */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4922d {

    /* renamed from: a */
    private static final Map<String, C4920c> f11222a = new ConcurrentHashMap(16);

    /* renamed from: b */
    private static final Object f11223b = new Object();

    /* renamed from: a */
    public static C4920c m15005a(GrsBaseInfo grsBaseInfo, Context context) {
        synchronized (f11223b) {
            int uniqueCode = grsBaseInfo.uniqueCode();
            Map<String, C4920c> map = f11222a;
            C4920c c4920c = map.get(context.getPackageName() + uniqueCode);
            if (c4920c == null) {
                Logger.m15049i("GrsClientManager", "grsClientImpl == null, and new GrsClientImpl");
                C4920c c4920c2 = new C4920c(context, grsBaseInfo);
                Map<String, C4920c> map2 = f11222a;
                map2.put(context.getPackageName() + uniqueCode, c4920c2);
                return c4920c2;
            } else if (c4920c.m15020a((Object) new C4920c(grsBaseInfo))) {
                return c4920c;
            } else {
                Logger.m15049i("GrsClientManager", "The app_name, ser_country, reg_country and issue_country is equal, but other not.");
                C4920c c4920c3 = new C4920c(context, grsBaseInfo);
                Map<String, C4920c> map3 = f11222a;
                map3.put(context.getPackageName() + uniqueCode, c4920c3);
                return c4920c3;
            }
        }
    }
}
