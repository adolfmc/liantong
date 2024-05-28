package com.baidu.p166vi;

import android.location.GpsStatus;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.vi.h */
/* loaded from: E:\10762272_dexfile_execute.dex */
final class HandlerC3334h extends Handler {
    @Override // android.os.Handler
    public void handleMessage(Message message) {
        int i;
        int i2;
        LocationManager locationManager;
        GpsStatus.Listener listener;
        LocationManager locationManager2;
        LocationManager locationManager3;
        GpsStatus.Listener listener2;
        LocationManager locationManager4;
        LocationListener locationListener;
        VGps vGps = (VGps) message.obj;
        if (vGps == null) {
            return;
        }
        switch (message.what) {
            case 1:
                i = vGps.f8203f;
                i2 = VGps.f8197e;
                if (i < i2) {
                    vGps.updateGps(0.0d, 0.0d, 0.0f, 0.0f, 0.0f, 0);
                    return;
                }
                return;
            case 2:
                if (VIContext.getContext() != null) {
                    vGps.f8201c = (LocationManager) VIContext.getContext().getSystemService("location");
                    locationManager = vGps.f8201c;
                    listener = vGps.f8199a;
                    locationManager.addGpsStatusListener(listener);
                    return;
                }
                return;
            case 3:
                locationManager2 = vGps.f8201c;
                if (locationManager2 != null) {
                    locationManager3 = vGps.f8201c;
                    listener2 = vGps.f8199a;
                    locationManager3.removeGpsStatusListener(listener2);
                    locationManager4 = vGps.f8201c;
                    locationListener = vGps.f8200b;
                    locationManager4.removeUpdates(locationListener);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
