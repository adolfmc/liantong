package com.networkbench.agent.impl.util;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.util.s */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6651s implements LocationListener {

    /* renamed from: b */
    private static final InterfaceC6393e f17245b = C6394f.m10150a();

    /* renamed from: a */
    protected LocationManager f17246a;

    @Override // android.location.LocationListener
    public void onProviderEnabled(String str) {
    }

    @Override // android.location.LocationListener
    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public C6651s(LocationManager locationManager) {
        this.f17246a = null;
        this.f17246a = locationManager;
    }

    @Override // android.location.LocationListener
    public void onLocationChanged(Location location) {
        C6638h.m8963w().m9055a(location);
        m8763a();
    }

    @Override // android.location.LocationListener
    public void onProviderDisabled(String str) {
        if (str == "passive") {
            m8763a();
        }
    }

    /* renamed from: a */
    private void m8763a() {
        f17245b.mo10122a("will be remove location update listener and cancel timer!");
        try {
            this.f17246a.removeUpdates(this);
        } catch (Exception e) {
            f17245b.mo10121a("stop locationUpdate occur an erro ", e);
        }
    }
}
