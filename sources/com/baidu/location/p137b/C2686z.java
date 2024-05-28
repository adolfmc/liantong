package com.baidu.location.p137b;

import android.annotation.TargetApi;
import android.location.GnssNavigationMessage;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.baidu.location.p140e.C2735k;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.b.z */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2686z {

    /* renamed from: a */
    private static Object f5483a = new Object();

    /* renamed from: b */
    private static C2686z f5484b = null;

    /* renamed from: c */
    private HandlerThread f5485c;

    /* renamed from: d */
    private Handler f5486d;

    /* renamed from: e */
    private boolean f5487e = false;

    C2686z() {
    }

    /* renamed from: a */
    public static C2686z m19278a() {
        C2686z c2686z;
        synchronized (f5483a) {
            if (f5484b == null) {
                f5484b = new C2686z();
            }
            c2686z = f5484b;
        }
        return c2686z;
    }

    @TargetApi(24)
    /* renamed from: a */
    public void m19277a(GnssNavigationMessage gnssNavigationMessage, long j) {
        if (!this.f5487e || gnssNavigationMessage == null) {
            return;
        }
        try {
            if (this.f5486d != null) {
                Message obtainMessage = this.f5486d.obtainMessage(11);
                Bundle bundle = new Bundle();
                bundle.putParcelable("gnss_navigation_message", gnssNavigationMessage);
                bundle.putLong("gps_time", j);
                obtainMessage.setData(bundle);
                obtainMessage.sendToTarget();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m19276a(Location location, int i) {
        if (!this.f5487e || location == null) {
            return;
        }
        try {
            if (this.f5486d != null) {
                Message obtainMessage = this.f5486d.obtainMessage(1);
                Bundle bundle = new Bundle();
                bundle.putParcelable("loc", new Location(location));
                bundle.putInt("satnum", i);
                obtainMessage.setData(bundle);
                obtainMessage.sendToTarget();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public void m19274b() {
        if (this.f5487e) {
            try {
                if (this.f5486d != null) {
                    this.f5486d.obtainMessage(3).sendToTarget();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: c */
    public void m19273c() {
        if (this.f5487e) {
            try {
                if (this.f5486d != null) {
                    this.f5486d.obtainMessage(2).sendToTarget();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: d */
    public void m19272d() {
        if (this.f5487e) {
            try {
                if (this.f5486d != null) {
                    this.f5486d.obtainMessage(7).sendToTarget();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: e */
    public void m19271e() {
        if (this.f5487e) {
            return;
        }
        this.f5487e = true;
        if (this.f5485c == null) {
            this.f5485c = new HandlerThread("LocUploadThreadManager");
            this.f5485c.start();
            HandlerThread handlerThread = this.f5485c;
            if (handlerThread != null) {
                this.f5486d = new HandlerC2627aa(this, handlerThread.getLooper());
            }
        }
        try {
            if (this.f5486d != null) {
                this.f5486d.obtainMessage(5).sendToTarget();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (this.f5486d != null) {
                this.f5486d.sendEmptyMessageDelayed(4, C2735k.f5758Q);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* renamed from: f */
    public void m19270f() {
        if (this.f5487e) {
            C2644h.m19473a().m19464b();
            try {
                if (this.f5486d != null) {
                    this.f5486d.removeCallbacksAndMessages(null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.f5486d = null;
            try {
                if (this.f5485c != null) {
                    this.f5485c.quit();
                    this.f5485c.interrupt();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.f5485c = null;
            this.f5487e = false;
        }
    }
}
