package com.baidu.mapapi.map;

import android.graphics.Point;
import com.baidu.mapapi.map.C2781v;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.platform.comapi.basestruct.GeoPoint;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WeightedLatLng extends C2781v.AbstractC2782a {
    public static final double DEFAULT_INTENSITY = 1.0d;

    /* renamed from: a */
    private Point f6516a;
    public final double intensity;
    public final LatLng mLatLng;

    public WeightedLatLng(LatLng latLng) {
        this(latLng, 1.0d);
    }

    public WeightedLatLng(LatLng latLng, double d) {
        if (latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: latLng can not be null");
        }
        this.mLatLng = latLng;
        GeoPoint ll2mc = CoordUtil.ll2mc(latLng);
        this.f6516a = new Point((int) ll2mc.getLongitudeE6(), (int) ll2mc.getLatitudeE6());
        if (d > 0.0d) {
            this.intensity = d;
        } else {
            this.intensity = 1.0d;
        }
    }

    public double getIntensity() {
        return this.intensity;
    }

    @Override // com.baidu.mapapi.map.C2781v.AbstractC2782a
    public Point getPoint() {
        return this.f6516a;
    }
}
