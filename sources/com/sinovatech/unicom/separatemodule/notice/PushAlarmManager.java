package com.sinovatech.unicom.separatemodule.notice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import com.sinovatech.unicom.p318ui.App;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class PushAlarmManager {
    public static void startAlarmManagerPush(long j) {
        PendingIntent broadcast = PendingIntent.getBroadcast(App.getInstance().getApplicationContext(), 0, new Intent(App.getInstance().getApplicationContext(), NoticeWakefulReceiver.class), 0);
        AlarmManager alarmManager = (AlarmManager) App.getInstance().getSystemService("alarm");
        if (Build.VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(0, System.currentTimeMillis() + j, broadcast);
        } else if (Build.VERSION.SDK_INT >= 21) {
            alarmManager.setExact(0, System.currentTimeMillis() + j, broadcast);
        } else {
            alarmManager.set(0, System.currentTimeMillis() + j, broadcast);
        }
    }

    public static void endAlarmManagerPush() {
        ((AlarmManager) App.getInstance().getSystemService("alarm")).cancel(PendingIntent.getBroadcast(App.getInstance().getApplicationContext(), 0, new Intent(App.getInstance().getApplicationContext(), NoticeWakefulReceiver.class), 0));
    }
}
