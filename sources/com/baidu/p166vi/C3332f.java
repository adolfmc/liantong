package com.baidu.p166vi;

import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.LocationManager;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.vi.f */
/* loaded from: E:\10762272_dexfile_execute.dex */
class C3332f implements GpsStatus.Listener {

    /* renamed from: a */
    final /* synthetic */ VGps f8208a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C3332f(VGps vGps) {
        this.f8208a = vGps;
    }

    @Override // android.location.GpsStatus.Listener
    public void onGpsStatusChanged(int i) {
        LocationManager locationManager;
        GpsStatus gpsStatus;
        int i2;
        int i3;
        int i4;
        GpsStatus gpsStatus2;
        LocationManager locationManager2;
        GpsStatus gpsStatus3;
        LocationManager locationManager3;
        if (i == 2) {
            this.f8208a.updateGps(0.0d, 0.0d, 0.0f, 0.0f, 0.0f, 0);
        } else if (i == 4) {
            locationManager = this.f8208a.f8201c;
            if (locationManager != null) {
                gpsStatus2 = this.f8208a.f8202d;
                if (gpsStatus2 == null) {
                    VGps vGps = this.f8208a;
                    locationManager3 = vGps.f8201c;
                    vGps.f8202d = locationManager3.getGpsStatus(null);
                } else {
                    locationManager2 = this.f8208a.f8201c;
                    gpsStatus3 = this.f8208a.f8202d;
                    locationManager2.getGpsStatus(gpsStatus3);
                }
            }
            gpsStatus = this.f8208a.f8202d;
            int i5 = 0;
            for (GpsSatellite gpsSatellite : gpsStatus.getSatellites()) {
                if (gpsSatellite.usedInFix()) {
                    i5++;
                }
            }
            i2 = VGps.f8197e;
            if (i5 < i2) {
                i3 = this.f8208a.f8203f;
                i4 = VGps.f8197e;
                if (i3 >= i4) {
                    this.f8208a.m17395b();
                }
            }
            this.f8208a.f8203f = i5;
        }
    }
}
