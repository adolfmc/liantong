package com.baidu.platform.comapi.basestruct;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GeoPoint {

    /* renamed from: a */
    private double f7534a;

    /* renamed from: b */
    private double f7535b;

    public GeoPoint(int i, int i2) {
        this.f7534a = i;
        this.f7535b = i2;
    }

    public GeoPoint(double d, double d2) {
        this.f7534a = d;
        this.f7535b = d2;
    }

    public double getLatitude() {
        return this.f7534a;
    }

    public void setLatitude(double d) {
        this.f7534a = d;
    }

    public double getLongitude() {
        return this.f7535b;
    }

    public void setLongitude(double d) {
        this.f7535b = d;
    }

    public void setLatitude(int i) {
        this.f7534a = i;
    }

    public void setLongitude(int i) {
        this.f7535b = i;
    }

    public double getLatitudeE6() {
        return this.f7534a;
    }

    public void setLatitudeE6(double d) {
        this.f7534a = d;
    }

    public double getLongitudeE6() {
        return this.f7535b;
    }

    public void setLongitudeE6(double d) {
        this.f7535b = d;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        return "GeoPoint: Latitude: " + this.f7534a + ", Longitude: " + this.f7535b;
    }

    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            GeoPoint geoPoint = (GeoPoint) obj;
            return Math.abs(this.f7534a - geoPoint.f7534a) <= 1.0E-6d && Math.abs(this.f7535b - geoPoint.f7535b) <= 1.0E-6d;
        }
        return false;
    }
}
