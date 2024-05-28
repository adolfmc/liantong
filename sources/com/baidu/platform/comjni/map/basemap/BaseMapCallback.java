package com.baidu.platform.comjni.map.basemap;

import android.os.Bundle;
import com.baidu.mapsdkplatform.comjni.p146a.p147a.InterfaceC2960a;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BaseMapCallback {

    /* renamed from: a */
    private static final ConcurrentHashMap<Long, InterfaceC3098a> f8081a = new ConcurrentHashMap<>(2);

    /* renamed from: b */
    private static final ConcurrentHashMap<Long, InterfaceC2960a> f8082b = new ConcurrentHashMap<>(2);

    public static void release(long j) {
        f8081a.remove(Long.valueOf(j));
        f8082b.remove(Long.valueOf(j));
    }

    public static int reqLayerData(Bundle bundle, long j, int i) {
        if (f8081a.isEmpty()) {
            return 0;
        }
        for (Map.Entry<Long, InterfaceC3098a> entry : f8081a.entrySet()) {
            InterfaceC3098a value = entry.getValue();
            if (value != null && value.mo17647a(j)) {
                return value.mo17646a(bundle, j, i);
            }
        }
        for (Map.Entry<Long, InterfaceC2960a> entry2 : f8082b.entrySet()) {
            InterfaceC2960a value2 = entry2.getValue();
            if (value2 != null && value2.mo17647a(j)) {
                return value2.mo17646a(bundle, j, i);
            }
        }
        return 0;
    }

    public static boolean setMapCallback(long j, InterfaceC3098a interfaceC3098a) {
        if (interfaceC3098a == null || j == 0) {
            return false;
        }
        f8081a.put(Long.valueOf(j), interfaceC3098a);
        return true;
    }

    public static boolean setMapSDKCallback(long j, InterfaceC2960a interfaceC2960a) {
        if (interfaceC2960a == null || j == 0) {
            return false;
        }
        f8082b.put(Long.valueOf(j), interfaceC2960a);
        return true;
    }
}
