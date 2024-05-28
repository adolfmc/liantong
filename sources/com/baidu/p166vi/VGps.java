package com.baidu.p166vi;

import android.annotation.SuppressLint;
import android.location.GpsStatus;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.vi.VGps */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class VGps {

    /* renamed from: e */
    private static int f8197e = 3;
    @SuppressLint({"HandlerLeak"})

    /* renamed from: h */
    private static Handler f8198h = new HandlerC3334h();

    /* renamed from: a */
    private GpsStatus.Listener f8199a = new C3332f(this);

    /* renamed from: b */
    private LocationListener f8200b = new C3333g(this);

    /* renamed from: c */
    private LocationManager f8201c = null;

    /* renamed from: d */
    private GpsStatus f8202d = null;

    /* renamed from: f */
    private int f8203f = 0;

    /* renamed from: g */
    private int f8204g = 0;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public synchronized void m17395b() {
        if (!f8198h.hasMessages(1)) {
            f8198h.sendMessageDelayed(f8198h.obtainMessage(1, this), 3000L);
        }
    }

    public int getGpsSatellitesNum() {
        return this.f8203f;
    }

    public boolean init() {
        f8198h.removeMessages(2);
        Handler handler = f8198h;
        handler.sendMessage(handler.obtainMessage(2, this));
        return true;
    }

    public boolean unInit() {
        f8198h.removeMessages(1);
        f8198h.removeMessages(3);
        Handler handler = f8198h;
        handler.sendMessage(handler.obtainMessage(3, this));
        return true;
    }

    public native void updateGps(double d, double d2, float f, float f2, float f3, int i);
}
