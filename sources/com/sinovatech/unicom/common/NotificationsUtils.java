package com.sinovatech.unicom.common;

import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Binder;
import android.os.Build;
import android.support.p083v4.app.NotificationManagerCompat;
import java.lang.reflect.InvocationTargetException;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NotificationsUtils {
    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

    public static int checkOp(Context context) {
        if (Build.VERSION.SDK_INT >= 19) {
            Object systemService = context.getSystemService("appops");
            try {
                return ((Integer) systemService.getClass().getDeclaredMethod("checkOp", Integer.TYPE, Integer.TYPE, String.class).invoke(systemService, 11, Integer.valueOf(Binder.getCallingUid()), context.getPackageName())).intValue();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return -1;
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
                return -1;
            } catch (NoSuchMethodException e3) {
                e3.printStackTrace();
                return -1;
            } catch (InvocationTargetException e4) {
                e4.printStackTrace();
                return -1;
            } catch (Exception e5) {
                e5.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    @SuppressLint({"NewApi"})
    public static boolean isNotificationEnabled(Context context) {
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String packageName = context.getApplicationContext().getPackageName();
        int i = applicationInfo.uid;
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                return NotificationManagerCompat.from(context).areNotificationsEnabled();
            }
            Class<?> cls = Class.forName(AppOpsManager.class.getName());
            return ((Integer) cls.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class).invoke(appOpsManager, Integer.valueOf(((Integer) cls.getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)).intValue()), Integer.valueOf(i), packageName)).intValue() == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
