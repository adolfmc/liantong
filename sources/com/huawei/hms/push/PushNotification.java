package com.huawei.hms.push;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.android.HwBuildEx;
import com.huawei.hms.support.api.push.TransActivity;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.ResourceLoaderUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.push.l */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class PushNotification {

    /* renamed from: a */
    private static int f11632a;

    /* renamed from: a */
    public static synchronized void m14257a(Context context, PushSelfShowMessage pushSelfShowMessage) {
        int hashCode;
        int i;
        int i2;
        int hashCode2;
        synchronized (PushNotification.class) {
            if (context != null) {
                if (!m14253a(pushSelfShowMessage)) {
                    HMSLog.m14115d("PushSelfShowLog", "showNotification, the msg id = " + pushSelfShowMessage.m14216p());
                    if (f11632a == 0) {
                        f11632a = (context.getPackageName() + System.currentTimeMillis()).hashCode();
                    }
                    if (TextUtils.isEmpty(pushSelfShowMessage.m14220l())) {
                        String m14215q = pushSelfShowMessage.m14215q();
                        if (!TextUtils.isEmpty(m14215q)) {
                            int hashCode3 = m14215q.hashCode();
                            pushSelfShowMessage.m14243a(hashCode3);
                            HMSLog.m14115d("PushSelfShowLog", "notification msgTag = " + hashCode3);
                        }
                        if (pushSelfShowMessage.m14213s() != -1) {
                            hashCode = pushSelfShowMessage.m14213s();
                            i = (pushSelfShowMessage.m14222k() + System.currentTimeMillis()).hashCode();
                            i2 = i + 1;
                            hashCode2 = (pushSelfShowMessage.m14213s() + pushSelfShowMessage.m14222k() + context.getPackageName()).hashCode();
                        } else {
                            hashCode = f11632a + 1;
                            i = hashCode + 1;
                            i2 = i + 1;
                            hashCode2 = i2 + 1;
                            f11632a = hashCode2;
                        }
                    } else {
                        hashCode = (pushSelfShowMessage.m14220l() + pushSelfShowMessage.m14222k()).hashCode();
                        i = f11632a + 1;
                        i2 = i + 1;
                        f11632a = i2;
                        hashCode2 = (pushSelfShowMessage.m14220l() + pushSelfShowMessage.m14222k() + context.getPackageName()).hashCode();
                    }
                    HMSLog.m14115d("PushSelfShowLog", "notifyId:" + hashCode + ",openNotifyId:" + i + ",delNotifyId:" + i2 + ",alarmNotifyId:" + hashCode2);
                    int[] iArr = new int[4];
                    iArr[0] = hashCode;
                    iArr[1] = i;
                    iArr[2] = i2;
                    if (pushSelfShowMessage.m14232f() <= 0) {
                        hashCode2 = 0;
                    }
                    iArr[3] = hashCode2;
                    Notification m14255a = C5049d.m14274f() ? m14255a(context, pushSelfShowMessage, iArr) : null;
                    NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                    if (notificationManager != null && m14255a != null) {
                        if (Build.VERSION.SDK_INT >= 26) {
                            notificationManager.createNotificationChannel(new NotificationChannel("HwPushChannelID", context.getString(ResourceLoaderUtil.getStringId("hms_push_channel")), 3));
                        }
                        notificationManager.notify(hashCode, m14255a);
                        m14246d(context, pushSelfShowMessage, iArr);
                        PushAnalyticsUtils.m14262a(context, pushSelfShowMessage.m14216p(), pushSelfShowMessage.m14240b(), "100");
                    }
                }
            }
        }
    }

    /* renamed from: b */
    private static PendingIntent m14250b(Context context, PushSelfShowMessage pushSelfShowMessage, int[] iArr) {
        return PendingIntent.getBroadcast(context, iArr[2], m14254a(context, pushSelfShowMessage, iArr, "2", 268435456), C5049d.m14280b());
    }

    /* renamed from: c */
    private static PendingIntent m14248c(Context context, PushSelfShowMessage pushSelfShowMessage, int[] iArr) {
        Intent m14254a = m14254a(context, pushSelfShowMessage, iArr, "1", 268435456);
        if (m14261a()) {
            m14254a.setClass(context, TransActivity.class);
            m14254a.setFlags(268468224);
            return PendingIntent.getActivity(context, iArr[1], m14254a, C5049d.m14280b());
        }
        return PendingIntent.getBroadcast(context, iArr[1], m14254a, C5049d.m14280b());
    }

    /* renamed from: d */
    private static void m14246d(Context context, PushSelfShowMessage pushSelfShowMessage, int[] iArr) {
        HMSLog.m14110i("PushSelfShowLog", "setAutoClear time is: " + pushSelfShowMessage.m14232f());
        if (pushSelfShowMessage.m14232f() <= 0) {
            return;
        }
        m14258a(context, m14254a(context, pushSelfShowMessage, iArr, "-1", 32), pushSelfShowMessage.m14232f(), iArr[3]);
    }

    @SuppressLint({"NewApi"})
    /* renamed from: b */
    private static void m14251b(Context context, Notification.Builder builder, PushSelfShowMessage pushSelfShowMessage) {
        if ("com.huawei.android.pushagent".equals(context.getPackageName())) {
            Bundle bundle = new Bundle();
            String m14222k = pushSelfShowMessage.m14222k();
            if (TextUtils.isEmpty(m14222k)) {
                return;
            }
            bundle.putString("hw_origin_sender_package_name", m14222k);
            builder.setExtras(bundle);
        }
    }

    /* renamed from: d */
    private static void m14245d(PushSelfShowMessage pushSelfShowMessage, Notification.Builder builder) {
        String m14211u = pushSelfShowMessage.m14211u();
        String m14224j = pushSelfShowMessage.m14224j();
        if (TextUtils.isEmpty(m14224j)) {
            builder.setContentText(m14211u);
            return;
        }
        builder.setContentText(m14224j);
        if (TextUtils.isEmpty(m14211u)) {
            return;
        }
        builder.setContentTitle(m14211u);
    }

    /* renamed from: c */
    private static void m14247c(PushSelfShowMessage pushSelfShowMessage, Notification.Builder builder) {
        builder.setTicker(pushSelfShowMessage.m14208x());
    }

    /* renamed from: b */
    private static void m14249b(PushSelfShowMessage pushSelfShowMessage, Notification.Builder builder) {
        String m14212t = pushSelfShowMessage.m14212t();
        if (TextUtils.isEmpty(m14212t)) {
            return;
        }
        builder.setSubText(m14212t);
    }

    /* renamed from: a */
    private static boolean m14261a() {
        return Build.VERSION.SDK_INT >= 30;
    }

    /* renamed from: a */
    private static Intent m14254a(Context context, PushSelfShowMessage pushSelfShowMessage, int[] iArr, String str, int i) {
        Intent intent = new Intent("com.huawei.intent.action.PUSH_DELAY_NOTIFY");
        intent.putExtra("selfshow_info", pushSelfShowMessage.m14217o()).putExtra("selfshow_token", pushSelfShowMessage.m14207y()).putExtra("selfshow_event_id", str).putExtra("selfshow_notify_id", iArr[0]).putExtra("selfshow_auto_clear_id", iArr[3]).setPackage(context.getPackageName()).setFlags(i);
        return intent;
    }

    /* renamed from: a */
    private static Notification m14255a(Context context, PushSelfShowMessage pushSelfShowMessage, int[] iArr) {
        Notification.Builder builder = new Notification.Builder(context);
        if (NotificationUtils.m14265a(pushSelfShowMessage) == NotifyStyle.STYLE_BIGTEXT) {
            NotificationUtils.m14266a(builder, pushSelfShowMessage.m14230g(), pushSelfShowMessage);
        }
        NotificationIconUtil.m14271a(context, builder, pushSelfShowMessage);
        m14249b(pushSelfShowMessage, builder);
        m14245d(pushSelfShowMessage, builder);
        m14256a(context, pushSelfShowMessage, builder);
        m14260a(builder);
        m14252a(pushSelfShowMessage, builder);
        m14247c(pushSelfShowMessage, builder);
        builder.setContentIntent(m14248c(context, pushSelfShowMessage, iArr));
        builder.setDeleteIntent(m14250b(context, pushSelfShowMessage, iArr));
        if (Build.VERSION.SDK_INT >= 26) {
            builder.setChannelId("HwPushChannelID");
        }
        m14251b(context, builder, pushSelfShowMessage);
        m14259a(context, builder, pushSelfShowMessage);
        return builder.build();
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    private static void m14259a(Context context, Notification.Builder builder, PushSelfShowMessage pushSelfShowMessage) {
        if (HwBuildEx.VERSION.EMUI_SDK_INT < 11 || !C5049d.m14287a(context)) {
            return;
        }
        Bundle bundle = new Bundle();
        String m14222k = pushSelfShowMessage.m14222k();
        HMSLog.m14110i("PushSelfShowLog", "the package name of notification is:" + m14222k);
        if (!TextUtils.isEmpty(m14222k)) {
            String m14283a = C5049d.m14283a(context, m14222k);
            HMSLog.m14110i("PushSelfShowLog", "the app name is:" + m14283a);
            if (m14283a != null) {
                bundle.putCharSequence("android.extraAppName", m14283a);
            }
        }
        builder.setExtras(bundle);
    }

    /* renamed from: a */
    private static void m14258a(Context context, Intent intent, long j, int i) {
        try {
            HMSLog.m14115d("PushSelfShowLog", "enter setDelayAlarm(interval:" + j + "ms.");
            AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
            if (alarmManager != null) {
                alarmManager.set(0, System.currentTimeMillis() + j, PendingIntent.getBroadcast(context, i, intent, C5049d.m14280b()));
            }
        } catch (Exception e) {
            HMSLog.m14109w("PushSelfShowLog", "set DelayAlarm error." + e.toString());
        }
    }

    /* renamed from: a */
    private static void m14256a(Context context, PushSelfShowMessage pushSelfShowMessage, Notification.Builder builder) {
        Bitmap m14270a = NotificationIconUtil.m14270a(context, pushSelfShowMessage);
        if (m14270a != null) {
            builder.setLargeIcon(m14270a);
        }
    }

    /* renamed from: a */
    private static void m14260a(Notification.Builder builder) {
        builder.setShowWhen(true);
        builder.setWhen(System.currentTimeMillis());
    }

    /* renamed from: a */
    private static void m14252a(PushSelfShowMessage pushSelfShowMessage, Notification.Builder builder) {
        builder.setAutoCancel(pushSelfShowMessage.m14234e() == 1);
        builder.setOngoing(false);
    }

    /* renamed from: a */
    private static boolean m14253a(PushSelfShowMessage pushSelfShowMessage) {
        return pushSelfShowMessage == null || (TextUtils.isEmpty(pushSelfShowMessage.m14211u()) && TextUtils.isEmpty(pushSelfShowMessage.m14224j()));
    }
}
