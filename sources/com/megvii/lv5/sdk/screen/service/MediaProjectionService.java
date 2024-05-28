package com.megvii.lv5.sdk.screen.service;

import android.app.Service;
import android.content.Intent;
import android.hardware.display.VirtualDisplay;
import android.media.MediaRecorder;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import com.megvii.lv5.AbstractC5421e2;
import com.megvii.lv5.EnumC5414d2;
import java.io.File;
import java.lang.ref.WeakReference;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class MediaProjectionService extends Service {

    /* renamed from: l */
    public static final /* synthetic */ int f13518l = 0;

    /* renamed from: a */
    public DisplayMetrics f13519a;

    /* renamed from: b */
    public boolean f13520b;

    /* renamed from: d */
    public EnumC5414d2 f13522d;

    /* renamed from: e */
    public MediaProjectionManager f13523e;

    /* renamed from: g */
    public VirtualDisplay f13525g;

    /* renamed from: h */
    public MediaRecorder f13526h;

    /* renamed from: i */
    public File f13527i;

    /* renamed from: j */
    public boolean f13528j;

    /* renamed from: k */
    public AbstractC5421e2 f13529k;

    /* renamed from: c */
    public boolean f13521c = false;

    /* renamed from: f */
    public MediaProjection f13524f = null;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.screen.service.MediaProjectionService$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class BinderC5599a extends Binder {

        /* renamed from: a */
        public WeakReference<MediaProjectionService> f13530a;

        public BinderC5599a(MediaProjectionService mediaProjectionService) {
            this.f13530a = new WeakReference<>(mediaProjectionService);
        }
    }

    /* renamed from: a */
    public void m13040a() {
        AbstractC5421e2 abstractC5421e2;
        if (!this.f13520b && (abstractC5421e2 = this.f13529k) != null) {
            abstractC5421e2.mo12888a();
        }
        MediaRecorder mediaRecorder = this.f13526h;
        if (mediaRecorder == null) {
            AbstractC5421e2 abstractC5421e22 = this.f13529k;
            if (abstractC5421e22 != null) {
                abstractC5421e22.mo12888a();
            }
        } else if (!this.f13528j) {
            AbstractC5421e2 abstractC5421e23 = this.f13529k;
            if (abstractC5421e23 != null) {
                abstractC5421e23.mo12888a();
            }
        } else {
            try {
                mediaRecorder.stop();
                this.f13526h.reset();
                this.f13526h.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.f13526h = null;
            AbstractC5421e2 abstractC5421e24 = this.f13529k;
            if (abstractC5421e24 != null) {
                abstractC5421e24.mo12887a(this.f13527i);
            }
            this.f13527i = null;
            this.f13528j = false;
            this.f13529k = null;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new BinderC5599a(this);
    }

    @Override // android.app.Service
    @RequiresApi(api = 21)
    public void onDestroy() {
        m13040a();
        VirtualDisplay virtualDisplay = this.f13525g;
        if (virtualDisplay != null) {
            virtualDisplay.release();
            this.f13525g = null;
        }
        MediaProjection mediaProjection = this.f13524f;
        if (mediaProjection != null && !this.f13521c) {
            mediaProjection.stop();
            this.f13524f = null;
            this.f13521c = false;
        }
        if (this.f13523e != null) {
            this.f13523e = null;
        }
        stopForeground(true);
        super.onDestroy();
    }
}
