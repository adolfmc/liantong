package com.bytedance.sdk.openadsdk;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class TTLocation implements LocationProvider {

    /* renamed from: mb */
    private double f9488mb;

    /* renamed from: ox */
    private double f9489ox;

    public TTLocation(double d, double d2) {
        this.f9488mb = 0.0d;
        this.f9489ox = 0.0d;
        this.f9488mb = d;
        this.f9489ox = d2;
    }

    @Override // com.bytedance.sdk.openadsdk.LocationProvider
    public double getLatitude() {
        return this.f9488mb;
    }

    public void setLatitude(double d) {
        this.f9488mb = d;
    }

    @Override // com.bytedance.sdk.openadsdk.LocationProvider
    public double getLongitude() {
        return this.f9489ox;
    }

    public void setLongitude(double d) {
        this.f9489ox = d;
    }
}
