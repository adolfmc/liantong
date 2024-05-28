package com.baidu.rtc;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.webrtc.Logging;
import java.util.Objects;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CaptureScreenService extends Service {
    private IBinder mBinder = new CaptureScreenServiceBinder();
    private MediaProjection mMediaProjection;
    private MediaProjectionManager mProjectionManager;
    private int mResultCode;
    private Intent mResultData;

    private void log(String str) {
        Logging.m5305d(ScreenCapturerAndroid.class.getName(), str);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        log("CaptureScreenService -- onCreate");
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        log("CaptureScreenService -- onBind");
        if (intent != null) {
            this.mResultCode = intent.getIntExtra("code", -1);
            this.mResultData = (Intent) intent.getParcelableExtra("data");
            createNotificationChannel();
            this.mProjectionManager = (MediaProjectionManager) getSystemService("media_projection");
            this.mMediaProjection = this.mProjectionManager.getMediaProjection(this.mResultCode, (Intent) Objects.requireNonNull(this.mResultData));
        }
        return this.mBinder;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class CaptureScreenServiceBinder extends Binder {
        public CaptureScreenServiceBinder() {
        }

        public CaptureScreenService getService() {
            return CaptureScreenService.this;
        }
    }

    public MediaProjection getMediaProjection() {
        return this.mMediaProjection;
    }

    private void createNotificationChannel() {
        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        if (Build.VERSION.SDK_INT >= 26) {
            builder.setChannelId("brtc_notification_id");
            ((NotificationManager) getSystemService("notification")).createNotificationChannel(new NotificationChannel("brtc_notification_id", "notification_name", 2));
        }
        Notification build = builder.build();
        build.defaults = 1;
        startForeground(110, build);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        log("CaptureScreenService -- onDestroy");
        stopForeground(true);
    }
}
