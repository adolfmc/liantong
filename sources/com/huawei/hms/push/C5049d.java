package com.huawei.hms.push;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import com.huawei.hms.support.log.HMSLog;
import java.util.Date;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: CommFun.java */
/* renamed from: com.huawei.hms.push.d */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5049d {
    /* renamed from: a */
    public static String m14283a(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 128)).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            HMSLog.m14110i("PushSelfShowLog", "get the app name of package:" + str + " failed.");
            return null;
        }
    }

    /* renamed from: b */
    public static Intent m14279b(Context context, String str) {
        try {
            return context.getPackageManager().getLaunchIntentForPackage(str);
        } catch (Exception unused) {
            HMSLog.m14109w("PushSelfShowLog", str + " not have launch activity");
            return null;
        }
    }

    /* renamed from: c */
    public static boolean m14277c(Context context, String str) {
        if (context != null && str != null && !"".equals(str)) {
            try {
                if (context.getPackageManager().getApplicationInfo(str, 8192) == null) {
                    return false;
                }
                HMSLog.m14115d("PushSelfShowLog", str + " is installed");
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    /* renamed from: d */
    private static boolean m14276d() {
        return Build.VERSION.SDK_INT >= 30;
    }

    /* renamed from: e */
    public static boolean m14275e() {
        return Build.VERSION.SDK_INT >= 33;
    }

    /* renamed from: f */
    public static boolean m14274f() {
        return Build.VERSION.SDK_INT >= 11;
    }

    /* renamed from: a */
    public static Boolean m14282a(Context context, String str, Intent intent) {
        try {
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
            if (queryIntentActivities != null && queryIntentActivities.size() > 0) {
                int size = queryIntentActivities.size();
                for (int i = 0; i < size; i++) {
                    if (queryIntentActivities.get(i).activityInfo != null && str.equals(queryIntentActivities.get(i).activityInfo.applicationInfo.packageName)) {
                        return Boolean.TRUE;
                    }
                }
            }
        } catch (Exception e) {
            HMSLog.m14111e("PushSelfShowLog", e.toString(), e);
        }
        return Boolean.FALSE;
    }

    /* renamed from: b */
    public static int m14280b() {
        return m14278c() ? 67108864 : 134217728;
    }

    /* renamed from: c */
    private static boolean m14278c() {
        return Build.VERSION.SDK_INT >= 23;
    }

    /* renamed from: a */
    public static long m14281a(String str) {
        if (str == null) {
            str = "";
        }
        try {
            Date date = new Date();
            int hours = (date.getHours() * 2) + (date.getMinutes() / 30);
            String concat = str.concat(str);
            HMSLog.m14110i("PushSelfShowLog", "startIndex is " + hours + ",ap is:" + concat + ",length is:" + concat.length());
            int length = concat.length();
            for (int i = hours; i < length; i++) {
                if (concat.charAt(i) != '0') {
                    long minutes = 60000 * (((i - hours) * 30) - (date.getMinutes() % 30));
                    HMSLog.m14115d("PushSelfShowLog", "startIndex is:" + hours + " i is:" + i + " delay:" + minutes);
                    if (minutes >= 0) {
                        return minutes;
                    }
                    return 0L;
                }
            }
        } catch (Exception e) {
            HMSLog.m14111e("PushSelfShowLog", "error ", e);
        }
        return 0L;
    }

    /* renamed from: a */
    public static void m14284a(Context context, Intent intent, long j) {
        try {
            HMSLog.m14115d("PushSelfShowLog", "enter setAPDelayAlarm(interval:" + j + "ms.");
            AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
            if (alarmManager != null) {
                alarmManager.set(0, System.currentTimeMillis() + j, PendingIntent.getBroadcast(context, (int) (System.currentTimeMillis() / 1000), intent, m14280b()));
            }
        } catch (Exception e) {
            HMSLog.m14109w("PushSelfShowLog", "set DelayAlarm error" + e.toString());
        }
    }

    /* renamed from: a */
    public static boolean m14287a(Context context) {
        return "com.huawei.hwid".equals(context.getPackageName());
    }

    /* renamed from: a */
    public static void m14286a(Context context, int i) {
        if (context == null) {
            HMSLog.m14112e("PushSelfShowLog", "context is null");
            return;
        }
        try {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.cancel(i);
            }
        } catch (Exception e) {
            HMSLog.m14112e("PushSelfShowLog", "removeNotifiCationById err:" + e.toString());
        }
    }

    /* renamed from: a */
    public static void m14285a(Context context, Intent intent) {
        try {
            int intExtra = intent.getIntExtra("selfshow_auto_clear_id", 0);
            HMSLog.m14115d("PushSelfShowLog", "setDelayAlarm(cancel) alarmNotityId " + intExtra);
            if (intExtra == 0) {
                return;
            }
            Intent intent2 = new Intent("com.huawei.intent.action.PUSH_DELAY_NOTIFY");
            intent2.setPackage(context.getPackageName()).setFlags(32);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
            PendingIntent broadcast = PendingIntent.getBroadcast(context, intExtra, intent2, m14288a());
            if (broadcast != null && alarmManager != null) {
                HMSLog.m14115d("PushSelfShowLog", "alarm cancel");
                alarmManager.cancel(broadcast);
            } else {
                HMSLog.m14115d("PushSelfShowLog", "alarm not exist");
            }
        } catch (Exception e) {
            HMSLog.m14112e("PushSelfShowLog", "cancelAlarm err:" + e.toString());
        }
    }

    /* renamed from: a */
    private static int m14288a() {
        return m14276d() ? 603979776 : 536870912;
    }
}
