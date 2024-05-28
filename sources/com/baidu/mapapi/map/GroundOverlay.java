package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapsdkplatform.comapi.map.EnumC2933i;
import com.baidu.platform.comapi.basestruct.GeoPoint;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class GroundOverlay extends Overlay {

    /* renamed from: j */
    private static final String f6047j = "GroundOverlay";

    /* renamed from: a */
    int f6048a;

    /* renamed from: b */
    BitmapDescriptor f6049b;

    /* renamed from: c */
    LatLng f6050c;

    /* renamed from: d */
    double f6051d;

    /* renamed from: e */
    double f6052e;

    /* renamed from: f */
    float f6053f;

    /* renamed from: g */
    float f6054g;

    /* renamed from: h */
    LatLngBounds f6055h;

    /* renamed from: i */
    float f6056i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GroundOverlay() {
        this.type = EnumC2933i.ground;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.mapapi.map.Overlay
    /* renamed from: a */
    public Bundle mo18867a(Bundle bundle) {
        super.mo18867a(bundle);
        bundle.putBundle("image_info", this.f6049b.m18976b());
        if (this.f6048a == 1) {
            GeoPoint ll2mc = CoordUtil.ll2mc(this.f6055h.southwest);
            double longitudeE6 = ll2mc.getLongitudeE6();
            double latitudeE6 = ll2mc.getLatitudeE6();
            GeoPoint ll2mc2 = CoordUtil.ll2mc(this.f6055h.northeast);
            double longitudeE62 = ll2mc2.getLongitudeE6();
            double latitudeE62 = ll2mc2.getLatitudeE6();
            this.f6051d = longitudeE62 - longitudeE6;
            this.f6052e = latitudeE62 - latitudeE6;
            this.f6050c = CoordUtil.mc2ll(new GeoPoint(latitudeE6 + (this.f6052e / 2.0d), longitudeE6 + (this.f6051d / 2.0d)));
            this.f6053f = 0.5f;
            this.f6054g = 0.5f;
        }
        double d = this.f6051d;
        if (d <= 0.0d || this.f6052e <= 0.0d) {
            throw new IllegalStateException("BDMapSDKException: when you add ground overlay, the width and height must greater than 0");
        }
        bundle.putDouble("x_distance", d);
        if (this.f6052e == 2.147483647E9d) {
            this.f6052e = (int) ((this.f6051d * this.f6049b.f5990a.getHeight()) / this.f6049b.f5990a.getWidth());
        }
        bundle.putDouble("y_distance", this.f6052e);
        GeoPoint ll2mc3 = CoordUtil.ll2mc(this.f6050c);
        bundle.putDouble("location_x", ll2mc3.getLongitudeE6());
        bundle.putDouble("location_y", ll2mc3.getLatitudeE6());
        bundle.putFloat("anchor_x", this.f6053f);
        bundle.putFloat("anchor_y", this.f6054g);
        bundle.putFloat("transparency", this.f6056i);
        return bundle;
    }

    public float getAnchorX() {
        return this.f6053f;
    }

    public float getAnchorY() {
        return this.f6054g;
    }

    public LatLngBounds getBounds() {
        return this.f6055h;
    }

    public double getHeight() {
        return this.f6052e;
    }

    public BitmapDescriptor getImage() {
        return this.f6049b;
    }

    public LatLng getPosition() {
        return this.f6050c;
    }

    public float getTransparency() {
        return this.f6056i;
    }

    public double getWidth() {
        return this.f6051d;
    }

    public void setAnchor(float f, float f2) {
        if (f < 0.0f || f > 1.0f || f2 < 0.0f || f2 > 1.0f) {
            return;
        }
        this.f6053f = f;
        this.f6054g = f2;
        this.listener.mo18796c(this);
    }

    public void setDimensions(int i) {
        this.f6051d = i;
        this.f6052e = 2.147483647E9d;
        this.listener.mo18796c(this);
    }

    public void setDimensions(int i, int i2) {
        this.f6051d = i;
        this.f6052e = i2;
        this.listener.mo18796c(this);
    }

    public void setImage(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor == null) {
            throw new IllegalArgumentException("BDMapSDKException: image can not be null");
        }
        this.f6049b = bitmapDescriptor;
        this.listener.mo18796c(this);
    }

    public void setPosition(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: position can not be null");
        }
        this.f6048a = 2;
        this.f6050c = latLng;
        this.listener.mo18796c(this);
    }

    public void setPositionFromBounds(LatLngBounds latLngBounds) {
        if (latLngBounds == null) {
            throw new IllegalArgumentException("BDMapSDKException: bounds can not be null");
        }
        this.f6048a = 1;
        this.f6055h = latLngBounds;
        this.listener.mo18796c(this);
    }

    public void setTransparency(float f) {
        if (f > 1.0f || f < 0.0f) {
            return;
        }
        this.f6056i = f;
        this.listener.mo18796c(this);
    }
}
