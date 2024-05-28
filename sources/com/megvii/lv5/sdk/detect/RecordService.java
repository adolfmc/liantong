package com.megvii.lv5.sdk.detect;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.os.Binder;
import android.os.Build;
import android.os.HandlerThread;
import android.os.IBinder;
import com.megvii.lv5.sdk.detect.action.ActionLivenessActivity;
import com.megvii.lv5.sdk.detect.actionflash.ActionFlashLivenessActivity;
import com.megvii.lv5.sdk.detect.color.FlashLivenessActivity;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RecordService extends Service {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.detect.RecordService$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class BinderC5567a extends Binder {
        public BinderC5567a(RecordService recordService) {
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Class cls;
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            MediaProjectionManager mediaProjectionManager = (MediaProjectionManager) getSystemService("media_projection");
            int intExtra = intent.getIntExtra("code", -1);
            Intent intent2 = (Intent) intent.getParcelableExtra("data");
            int intExtra2 = intent.getIntExtra("activity", 0);
            if (intExtra2 == 0) {
                cls = ActionLivenessActivity.class;
            } else {
                cls = intExtra2 == 1 ? FlashLivenessActivity.class : ActionFlashLivenessActivity.class;
            }
            Notification.Builder builder = new Notification.Builder(getApplicationContext());
            builder.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, cls), 0)).setContentText("").setWhen(System.currentTimeMillis());
            if (i >= 26) {
                builder.setChannelId("notification_id");
            }
            if (i >= 26) {
                ((NotificationManager) getSystemService("notification")).createNotificationChannel(new NotificationChannel("notification_id", "notification_name", 2));
            }
            Notification build = builder.build();
            build.defaults = 1;
            startForeground(110, build);
            mediaProjectionManager.getMediaProjection(intExtra, intent2);
        }
        return new BinderC5567a(this);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        new HandlerThread("service_thread", 10).start();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }
}
