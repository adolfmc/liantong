package com.sinovatech.unicom.basic.server;

import android.graphics.drawable.Drawable;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ManagerBitmapCache {
    private static ManagerBitmapCache ManagerBitmapCache;
    private Map<String, Drawable> map = new HashMap();

    public static synchronized ManagerBitmapCache getInstance() {
        ManagerBitmapCache managerBitmapCache;
        synchronized (ManagerBitmapCache.class) {
            if (ManagerBitmapCache == null) {
                ManagerBitmapCache = new ManagerBitmapCache();
            }
            managerBitmapCache = ManagerBitmapCache;
        }
        return managerBitmapCache;
    }

    private ManagerBitmapCache() {
    }

    public Map<String, Drawable> getMap() {
        return this.map;
    }
}
