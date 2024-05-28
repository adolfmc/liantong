package com.sinovatech.unicom.basic.p315ui;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.p083v4.app.NotificationCompat;
import com.sinovatech.unicom.basic.p315ui.activity.WelcomeClient;
import java.lang.reflect.Field;

/* renamed from: com.sinovatech.unicom.basic.ui.JiaobiaoUtils */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class JiaobiaoUtils {
    private static final String lancherActivityClassName = WelcomeClient.class.getName();
    private Activity context;

    public JiaobiaoUtils(Activity activity) {
        this.context = activity;
    }

    public void sendBadgeNumber(int i, int i2) {
        if (i < 0) {
            i = 0;
        }
        if (i > 3) {
            i = 3;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (i2 > 3) {
            i2 = 3;
        }
        int i3 = i + i2;
        String str = Build.MANUFACTURER;
        if (str.equalsIgnoreCase("samsung")) {
            sendToSamsumg(i3);
        } else if (str.equalsIgnoreCase("huawei")) {
            sendToHuawei(i3);
        }
    }

    private void sendToXiaoMi(int i) {
        NotificationManager notificationManager = (NotificationManager) this.context.getSystemService("notification");
        boolean z = true;
        Notification notification = null;
        try {
            try {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this.context);
                builder.setContentTitle("您有" + i + "未读消息");
                builder.setTicker("您有" + i + "未读消息");
                builder.setAutoCancel(true);
                builder.setSmallIcon(2131231623);
                builder.setDefaults(4);
                notification = builder.build();
                Object newInstance = Class.forName("android.app.MiuiNotification").newInstance();
                Field declaredField = newInstance.getClass().getDeclaredField("messageCount");
                declaredField.setAccessible(true);
                declaredField.set(newInstance, Integer.valueOf(i));
                Field field = notification.getClass().getField("extraNotification");
                field.setAccessible(true);
                field.set(notification, newInstance);
                if (notification != null) {
                    notificationManager.notify(101010, notification);
                }
            } catch (Exception e) {
                e.printStackTrace();
                z = false;
                Intent intent = new Intent("android.intent.action.APPLICATION_MESSAGE_UPDATE");
                intent.putExtra("android.intent.extra.update_application_component_name", this.context.getPackageName() + "/" + lancherActivityClassName);
                intent.putExtra("android.intent.extra.update_application_message_text", i);
                this.context.sendBroadcast(intent);
            }
        } catch (Throwable th) {
            if (notification != null && z) {
                notificationManager.notify(101010, notification);
            }
            throw th;
        }
    }

    private void sendToSamsumg(int i) {
        try {
            Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
            intent.putExtra("badge_count", i);
            intent.putExtra("badge_count_package_name", "com.sinovatech.unicom.ui");
            intent.putExtra("badge_count_class_name", "com.sinovatech.unicom.basic.ui.activity.WelcomeClient");
            this.context.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendToHuawei(int i) {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("package", "com.sinovatech.unicom.ui");
            bundle.putString("class", "com.sinovatech.unicom.basic.ui.activity.WelcomeClient");
            bundle.putInt("badgenumber", i);
            this.context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", (String) null, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
