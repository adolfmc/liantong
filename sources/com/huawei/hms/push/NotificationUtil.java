package com.huawei.hms.push;

import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import com.huawei.hms.aaid.utils.PushPreferences;
import java.lang.reflect.InvocationTargetException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.push.g */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class NotificationUtil {
    /* renamed from: a */
    public static boolean m14268a(Context context) {
        if (new PushPreferences(context, "push_notify_flag").getBoolean("notify_msg_enable")) {
            return false;
        }
        if (C5049d.m14275e()) {
            return ((NotificationManager) context.getSystemService("notification")).areNotificationsEnabled();
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 24) {
            return m14267b(context);
        }
        if (i >= 19) {
            return m14267b(context);
        }
        return true;
    }

    @SuppressLint({"NewApi", "InlinedApi"})
    /* renamed from: b */
    private static boolean m14267b(Context context) {
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String packageName = context.getApplicationContext().getPackageName();
        int i = applicationInfo.uid;
        try {
            Class<?> cls = Class.forName(AppOpsManager.class.getName());
            Class<?> cls2 = Integer.TYPE;
            return ((Integer) cls.getMethod("checkOpNoThrow", cls2, cls2, String.class).invoke(appOpsManager, Integer.valueOf(((Integer) cls.getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)).intValue()), Integer.valueOf(i), packageName)).intValue() == 0;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | RuntimeException | InvocationTargetException unused) {
            return true;
        }
    }
}
