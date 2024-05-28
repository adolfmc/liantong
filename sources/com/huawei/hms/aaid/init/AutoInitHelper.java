package com.huawei.hms.aaid.init;

import android.content.Context;
import android.content.pm.PackageManager;
import com.huawei.hms.opendevice.PushClientSp;
import com.huawei.hms.support.log.HMSLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AutoInitHelper {
    public static void doAutoInit(Context context) {
        try {
            if (isAutoInitEnabled(context)) {
                HMSLog.m14110i("AutoInit", "Push init start");
                new Thread(new AutoInitRunnable(context)).start();
            }
        } catch (Exception e) {
            HMSLog.m14111e("AutoInit", "Push init failed", e);
        }
    }

    public static boolean isAutoInitEnabled(Context context) {
        PushClientSp m14368a = PushClientSp.m14368a(context);
        if (m14368a.containsKey("push_kit_auto_init_enabled")) {
            return m14368a.getBoolean("push_kit_auto_init_enabled");
        }
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getBoolean("push_kit_auto_init_enabled");
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static void setAutoInitEnabled(Context context, boolean z) {
        PushClientSp m14368a = PushClientSp.m14368a(context);
        boolean z2 = m14368a.getBoolean("push_kit_auto_init_enabled");
        m14368a.saveBoolean("push_kit_auto_init_enabled", z);
        if (!z || z2) {
            return;
        }
        doAutoInit(context);
    }
}
