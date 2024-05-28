package com.heytap.mcssdk.p206d;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import com.heytap.mcssdk.p204b.C4715a;
import com.heytap.msp.push.notification.PushNotification;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.heytap.mcssdk.d.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4730a {
    /* renamed from: a */
    public static Notification m15546a(Context context, String str, PushNotification.Builder builder) {
        Notification.Builder builder2 = new Notification.Builder(context);
        if (Build.VERSION.SDK_INT >= 26) {
            builder2.setChannelId(C4715a.f10581a);
        }
        if (Build.VERSION.SDK_INT >= 20) {
            builder2.setGroup(str);
            builder2.setGroupSummary(true);
        }
        if (m15550a(builder2, builder)) {
            return builder2.build();
        }
        return null;
    }

    /* renamed from: a */
    public static NotificationManager m15547a(Context context) {
        if (context != null) {
            try {
                return (NotificationManager) context.getSystemService("notification");
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    /* renamed from: a */
    private static boolean m15550a(Notification.Builder builder, PushNotification.Builder builder2) {
        int iconRes = builder2.getIconRes();
        int iconLevel = builder2.getIconLevel();
        Icon icon = builder2.getIcon();
        if (icon != null && Build.VERSION.SDK_INT >= 23) {
            builder.setSmallIcon(icon);
            return true;
        } else if (iconRes != 0 && iconLevel != 0) {
            builder.setSmallIcon(iconRes, iconLevel);
            return true;
        } else if (iconRes != 0) {
            builder.setSmallIcon(iconRes);
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m15548a(NotificationManager notificationManager, String str, int i) {
        StatusBarNotification[] m15549a = m15549a(notificationManager, str);
        if (m15549a != null && m15549a.length != 0) {
            for (StatusBarNotification statusBarNotification : m15549a) {
                if (statusBarNotification.getId() == i) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: a */
    public static StatusBarNotification[] m15549a(NotificationManager notificationManager, String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            return notificationManager.getActiveNotifications();
        }
        return null;
    }
}
