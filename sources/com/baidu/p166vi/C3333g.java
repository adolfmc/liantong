package com.baidu.p166vi;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.vi.g */
/* loaded from: E:\10762272_dexfile_execute.dex */
class C3333g implements LocationListener {

    /* renamed from: a */
    final /* synthetic */ VGps f8209a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C3333g(VGps vGps) {
        this.f8209a = vGps;
    }

    @Override // android.location.LocationListener
    public void onLocationChanged(Location location) {
        int i;
        int i2;
        int i3;
        if (location != null) {
            float accuracy = location.hasAccuracy() ? location.getAccuracy() : 0.0f;
            i = this.f8209a.f8203f;
            i2 = VGps.f8197e;
            if (i < i2) {
                this.f8209a.m17395b();
                return;
            }
            float bearing = location.getBearing();
            i3 = this.f8209a.f8203f;
            this.f8209a.updateGps(location.getLongitude(), location.getLatitude(), (float) (location.getSpeed() * 3.6d), bearing, accuracy, i3);
        }
    }

    @Override // android.location.LocationListener
    public void onProviderDisabled(String str) {
        this.f8209a.updateGps(0.0d, 0.0d, 0.0f, 0.0f, 0.0f, 0);
    }

    @Override // android.location.LocationListener
    public void onProviderEnabled(String str) {
    }

    @Override // android.location.LocationListener
    public void onStatusChanged(String str, int i, Bundle bundle) {
        switch (i) {
            case 0:
            case 1:
                this.f8209a.updateGps(0.0d, 0.0d, 0.0f, 0.0f, 0.0f, 0);
                return;
            default:
                return;
        }
    }
}
