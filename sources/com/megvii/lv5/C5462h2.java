package com.megvii.lv5;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.display.VirtualDisplay;
import android.media.MediaRecorder;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.IBinder;
import android.util.DisplayMetrics;
import com.megvii.lv5.sdk.detect.action.ActionLivenessActivity;
import com.megvii.lv5.sdk.detect.actionflash.ActionFlashLivenessActivity;
import com.megvii.lv5.sdk.detect.color.FlashLivenessActivity;
import com.megvii.lv5.sdk.screen.service.MediaProjectionService;
import java.io.File;
import java.lang.ref.WeakReference;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.h2 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5462h2 {

    /* renamed from: a */
    public MediaProjectionManager f12714a;

    /* renamed from: b */
    public DisplayMetrics f12715b;

    /* renamed from: c */
    public ServiceConnection f12716c;

    /* renamed from: d */
    public MediaProjection f12717d;

    /* renamed from: e */
    public WeakReference<MediaProjectionService> f12718e = null;

    /* renamed from: f */
    public EnumC5414d2 f12719f;

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.h2$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class ServiceConnectionC5463a implements ServiceConnection {
        public ServiceConnectionC5463a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder instanceof MediaProjectionService.BinderC5599a) {
                MediaProjectionService mediaProjectionService = ((MediaProjectionService.BinderC5599a) iBinder).f13530a.get();
                C5462h2.this.getClass();
                mediaProjectionService.getClass();
                C5462h2.this.f12718e = new WeakReference<>(mediaProjectionService);
                String str = "onServiceConnected: mediaProjection = " + C5462h2.this.f12717d;
                C5462h2 c5462h2 = C5462h2.this;
                if (c5462h2.f12717d != null) {
                    c5462h2.m13494a(10086, -1, new Intent(), true);
                }
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            WeakReference<MediaProjectionService> weakReference = C5462h2.this.f12718e;
            if (weakReference != null) {
                weakReference.clear();
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.h2$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C5464b {

        /* renamed from: a */
        public static final C5462h2 f12721a = new C5462h2();
    }

    /* renamed from: a */
    public void m13495a() {
        WeakReference<MediaProjectionService> weakReference = this.f12718e;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.f12718e.get().m13040a();
    }

    /* renamed from: a */
    public void m13494a(int i, int i2, Intent intent, boolean z) {
        int i3;
        WeakReference<MediaProjectionService> weakReference = this.f12718e;
        if (weakReference == null || weakReference.get() == null || i != 10086 || i2 != -1 || (i3 = Build.VERSION.SDK_INT) < 21) {
            return;
        }
        MediaProjectionService mediaProjectionService = this.f12718e.get();
        DisplayMetrics displayMetrics = this.f12715b;
        MediaProjection mediaProjection = this.f12717d;
        EnumC5414d2 enumC5414d2 = this.f12719f;
        mediaProjectionService.f13519a = displayMetrics;
        mediaProjectionService.f13520b = z;
        mediaProjectionService.f13522d = enumC5414d2;
        if (intent != null) {
            Class cls = enumC5414d2 == EnumC5414d2.ACTIVITY_ACTION ? ActionLivenessActivity.class : enumC5414d2 == EnumC5414d2.ACTIVITY_FLASH ? FlashLivenessActivity.class : ActionFlashLivenessActivity.class;
            Notification.Builder builder = new Notification.Builder(mediaProjectionService.getApplicationContext());
            builder.setContentIntent(PendingIntent.getActivity(mediaProjectionService, 0, new Intent(mediaProjectionService, cls), 67108864)).setContentText("").setWhen(System.currentTimeMillis());
            if (i3 >= 26) {
                builder.setChannelId("notification_id");
            }
            if (i3 >= 26) {
                ((NotificationManager) mediaProjectionService.getSystemService("notification")).createNotificationChannel(new NotificationChannel("notification_id", "notification_name", 2));
            }
            Notification build = builder.build();
            build.defaults = 1;
            mediaProjectionService.startForeground(110, build);
            MediaProjectionManager mediaProjectionManager = (MediaProjectionManager) mediaProjectionService.getApplicationContext().getSystemService("media_projection");
            mediaProjectionService.f13523e = mediaProjectionManager;
            if (mediaProjectionManager != null) {
                if (mediaProjection == null) {
                    MediaProjection mediaProjection2 = mediaProjectionManager.getMediaProjection(i2, intent);
                    mediaProjectionService.f13524f = mediaProjection2;
                    C5464b.f12721a.f12717d = mediaProjection2;
                    String str = "createVirtualDisplay: mediaProjection = " + mediaProjectionService.f13524f;
                } else {
                    mediaProjectionService.f13524f = mediaProjection;
                    mediaProjectionService.f13521c = true;
                }
                if (mediaProjectionService.f13524f != null) {
                    return;
                }
            }
        }
        mediaProjectionService.stopSelf();
    }

    /* renamed from: a */
    public void m13491a(AbstractC5421e2 abstractC5421e2) {
        int i;
        int i2;
        WeakReference<MediaProjectionService> weakReference = this.f12718e;
        if (weakReference == null || weakReference.get() == null) {
            abstractC5421e2.mo12888a();
        } else if (Build.VERSION.SDK_INT >= 21) {
            MediaProjectionService mediaProjectionService = this.f12718e.get();
            mediaProjectionService.getClass();
            String str = "service startRecording: isMediaRecorderEnable = " + mediaProjectionService.f13520b;
            String str2 = "service startRecording: isMediaRecording = " + mediaProjectionService.f13528j;
            mediaProjectionService.f13529k = abstractC5421e2;
            if (!mediaProjectionService.f13520b || mediaProjectionService.f13528j) {
                abstractC5421e2.mo12888a();
                return;
            }
            DisplayMetrics displayMetrics = mediaProjectionService.f13519a;
            int i3 = displayMetrics.widthPixels;
            int i4 = displayMetrics.densityDpi;
            int i5 = C5527o2.m13223h(mediaProjectionService.getApplicationContext()).f12955S1;
            if (i5 == 0 || i5 > (i2 = C5388b3.f12390e)) {
                i = C5388b3.f12389d;
                i5 = C5388b3.f12390e;
            } else {
                i = (int) (i5 / ((i2 * 1.0f) / C5388b3.f12389d));
            }
            int i6 = i5;
            if (i % 2 != 0) {
                i++;
            }
            int i7 = i;
            int i8 = C5453g2.f12688a;
            File file = new File(mediaProjectionService.getFilesDir(), "megviiVideo");
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, "screen_record.mp4");
            if (file2.exists()) {
                file2.delete();
            }
            if (!file.canWrite()) {
                file2 = null;
            }
            mediaProjectionService.f13527i = file2;
            boolean m13193a = C5538q0.m13193a(mediaProjectionService);
            MediaRecorder mediaRecorder = new MediaRecorder();
            mediaProjectionService.f13526h = mediaRecorder;
            mediaRecorder.setVideoSource(2);
            if (m13193a) {
                mediaProjectionService.f13526h.setAudioSource(1);
            }
            mediaProjectionService.f13526h.setOutputFormat(2);
            if (m13193a) {
                mediaProjectionService.f13526h.setAudioEncoder(1);
            }
            mediaProjectionService.f13526h.setOutputFile(mediaProjectionService.f13527i.getAbsolutePath());
            mediaProjectionService.f13526h.setVideoEncoder(2);
            mediaProjectionService.f13526h.setVideoSize(i7, i6);
            mediaProjectionService.f13526h.setVideoFrameRate(30);
            mediaProjectionService.f13526h.setVideoEncodingBitRate(i7 * 5 * i6);
            mediaProjectionService.f13526h.setOnErrorListener(new C5438f2(mediaProjectionService));
            try {
                mediaProjectionService.f13526h.prepare();
            } catch (Exception e) {
                e.printStackTrace();
            }
            VirtualDisplay virtualDisplay = mediaProjectionService.f13525g;
            if (virtualDisplay == null) {
                mediaProjectionService.f13525g = mediaProjectionService.f13524f.createVirtualDisplay("MediaRecorder", i7, i6, i4, 16, mediaProjectionService.f13526h.getSurface(), null, null);
            } else {
                virtualDisplay.setSurface(mediaProjectionService.f13526h.getSurface());
            }
            mediaProjectionService.f13526h.start();
            mediaProjectionService.f13528j = true;
        }
    }

    /* renamed from: a */
    public void m13493a(Activity activity) {
        this.f12719f = activity instanceof ActionLivenessActivity ? EnumC5414d2.ACTIVITY_ACTION : activity instanceof FlashLivenessActivity ? EnumC5414d2.ACTIVITY_FLASH : EnumC5414d2.ACTIVITY_FLEXIBLE;
        if (this.f12714a != null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 21 && this.f12717d == null) {
            MediaProjectionManager mediaProjectionManager = (MediaProjectionManager) activity.getSystemService("media_projection");
            this.f12714a = mediaProjectionManager;
            if (mediaProjectionManager != null) {
                activity.startActivityForResult(mediaProjectionManager.createScreenCaptureIntent(), 10086);
            }
        }
        this.f12715b = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(this.f12715b);
        ServiceConnectionC5463a serviceConnectionC5463a = new ServiceConnectionC5463a();
        this.f12716c = serviceConnectionC5463a;
        int i = MediaProjectionService.f13518l;
        activity.bindService(new Intent(activity, MediaProjectionService.class), serviceConnectionC5463a, 1);
    }

    /* renamed from: a */
    public void m13492a(Context context) {
        ServiceConnection serviceConnection = this.f12716c;
        if (serviceConnection != null) {
            int i = MediaProjectionService.f13518l;
            context.unbindService(serviceConnection);
            this.f12716c = null;
        }
        WeakReference<MediaProjectionService> weakReference = this.f12718e;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.f12715b = null;
        this.f12714a = null;
    }
}
