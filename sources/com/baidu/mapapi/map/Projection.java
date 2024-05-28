package com.baidu.mapapi.map;

import android.graphics.Point;
import android.graphics.PointF;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapsdkplatform.comapi.map.C2925d;
import com.baidu.mapsdkplatform.comapi.map.C2948x;
import com.baidu.platform.comapi.basestruct.GeoPoint;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class Projection {

    /* renamed from: a */
    private C2925d f6385a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Projection(C2925d c2925d) {
        this.f6385a = c2925d;
    }

    public LatLng fromScreenLocation(Point point) {
        C2925d c2925d;
        if (point == null || (c2925d = this.f6385a) == null) {
            return null;
        }
        return CoordUtil.mc2ll(c2925d.m18325b(point.x, point.y));
    }

    public Point geoPoint3toScreenLocation(LatLng latLng, int i) {
        if (latLng == null || this.f6385a == null || i < 0) {
            return null;
        }
        return this.f6385a.m18337a(CoordUtil.ll2mc(latLng), i);
    }

    public float metersToEquatorPixels(float f) {
        if (f <= 0.0f) {
            return 0.0f;
        }
        return (float) (f / this.f6385a.m18371L());
    }

    public PointF toOpenGLLocation(LatLng latLng, MapStatus mapStatus) {
        if (latLng == null || mapStatus == null) {
            return null;
        }
        GeoPoint ll2mc = CoordUtil.ll2mc(latLng);
        C2948x c2948x = mapStatus.f6144a;
        return new PointF((float) (ll2mc.getLongitudeE6() - c2948x.f7372d), (float) (ll2mc.getLatitudeE6() - c2948x.f7373e));
    }

    public PointF toOpenGLNormalization(LatLng latLng, MapStatus mapStatus) {
        if (latLng == null || mapStatus == null) {
            return null;
        }
        GeoPoint ll2mc = CoordUtil.ll2mc(latLng);
        C2948x.C2949a c2949a = mapStatus.f6144a.f7379k;
        return new PointF((float) ((((ll2mc.getLongitudeE6() - c2949a.f7388a) * 2.0d) / Math.abs(c2949a.f7389b - c2949a.f7388a)) - 1.0d), (float) ((((ll2mc.getLatitudeE6() - c2949a.f7391d) * 2.0d) / Math.abs(c2949a.f7390c - c2949a.f7391d)) - 1.0d));
    }

    public Point toScreenLocation(LatLng latLng) {
        if (latLng == null || this.f6385a == null) {
            return null;
        }
        return this.f6385a.m18338a(CoordUtil.ll2mc(latLng));
    }
}
