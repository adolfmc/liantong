package com.baidu.mapapi;

import android.content.Context;
import com.baidu.mapsdkplatform.comapi.BMapManagerInternal;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BMapManager {
    public static void init() {
        try {
            BMapManagerInternal.m18539a().m18535b();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public static void destroy() {
        BMapManagerInternal.m18539a().m18533d();
    }

    public static Context getContext() {
        try {
            return BMapManagerInternal.m18539a().m18532e();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return null;
        }
    }
}
