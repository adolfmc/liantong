package com.baidu.mapapi.map;

import android.graphics.Point;
import android.util.Log;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapsdkplatform.comapi.map.C2925d;
import com.baidu.platform.comapi.basestruct.GeoPoint;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class MapStatusUpdate {

    /* renamed from: o */
    private static final String f6156o = "MapStatusUpdate";

    /* renamed from: a */
    MapStatus f6157a;

    /* renamed from: b */
    LatLng f6158b;

    /* renamed from: c */
    LatLngBounds f6159c;

    /* renamed from: d */
    int f6160d;

    /* renamed from: e */
    int f6161e;

    /* renamed from: f */
    float f6162f;

    /* renamed from: g */
    int f6163g;

    /* renamed from: h */
    int f6164h;

    /* renamed from: i */
    float f6165i;

    /* renamed from: j */
    Point f6166j;

    /* renamed from: k */
    int f6167k = 0;

    /* renamed from: l */
    int f6168l = 0;

    /* renamed from: m */
    int f6169m = 0;

    /* renamed from: n */
    int f6170n = 0;

    /* renamed from: p */
    private int f6171p;

    private MapStatusUpdate() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MapStatusUpdate(int i) {
        this.f6171p = i;
    }

    /* renamed from: a */
    private float m18925a(float f) {
        return (float) (Math.pow(2.0d, 18.0f - f) / (SysOSUtil.getDensityDpi() / 310.0f));
    }

    /* renamed from: a */
    private float m18920a(LatLngBounds latLngBounds, C2925d c2925d, int i, int i2) {
        GeoPoint ll2mc = CoordUtil.ll2mc(latLngBounds.southwest);
        GeoPoint ll2mc2 = CoordUtil.ll2mc(latLngBounds.northeast);
        int latitudeE6 = (int) ll2mc.getLatitudeE6();
        return c2925d.m18355a((int) ll2mc.getLongitudeE6(), (int) ll2mc2.getLatitudeE6(), (int) ll2mc2.getLongitudeE6(), latitudeE6, i, i2);
    }

    /* renamed from: a */
    private MapStatusUpdate m18923a(MapStatus mapStatus) {
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate();
        synchronized (this) {
            mapStatusUpdate.f6157a = mapStatus;
            mapStatusUpdate.f6159c = this.f6159c;
            mapStatusUpdate.f6167k = this.f6167k;
            mapStatusUpdate.f6168l = this.f6168l;
            mapStatusUpdate.f6169m = this.f6169m;
            mapStatusUpdate.f6170n = this.f6170n;
        }
        return mapStatusUpdate;
    }

    /* renamed from: a */
    private LatLng m18921a(LatLngBounds latLngBounds, C2925d c2925d, float f) {
        double latitudeE6;
        double latitudeE62;
        if (latLngBounds == null || c2925d == null) {
            return null;
        }
        GeoPoint ll2mc = CoordUtil.ll2mc(latLngBounds.getCenter());
        int i = this.f6167k;
        double d = i * f;
        int i2 = this.f6169m;
        double d2 = i2 * f;
        double d3 = this.f6168l * f;
        double d4 = this.f6170n * f;
        double longitudeE6 = i > i2 ? ll2mc.getLongitudeE6() - ((d - d2) / 2.0d) : i < i2 ? ll2mc.getLongitudeE6() + ((d2 - d) / 2.0d) : ll2mc.getLongitudeE6();
        int i3 = this.f6168l;
        int i4 = this.f6170n;
        if (i3 < i4) {
            latitudeE62 = ll2mc.getLatitudeE6() - ((d4 - d3) / 2.0d);
        } else if (i3 <= i4) {
            latitudeE6 = ll2mc.getLatitudeE6();
            return CoordUtil.mc2ll(new GeoPoint(latitudeE6, longitudeE6));
        } else {
            latitudeE62 = ll2mc.getLatitudeE6();
            d3 -= d4;
        }
        latitudeE6 = latitudeE62 + (d3 / 2.0d);
        return CoordUtil.mc2ll(new GeoPoint(latitudeE6, longitudeE6));
    }

    /* renamed from: a */
    private boolean m18924a(int i, int i2, int i3, int i4, C2925d c2925d) {
        MapStatusUpdate m18376G = c2925d.m18376G();
        return (m18376G != null && i == m18376G.f6167k && i2 == m18376G.f6168l && i3 == m18376G.f6169m && i4 == m18376G.f6170n) ? false : true;
    }

    /* renamed from: a */
    private boolean m18922a(LatLngBounds latLngBounds, C2925d c2925d) {
        MapStatusUpdate m18376G = c2925d.m18376G();
        if (m18376G == null) {
            return true;
        }
        return (latLngBounds.southwest.latitude == m18376G.f6159c.southwest.latitude && latLngBounds.southwest.longitude == m18376G.f6159c.southwest.longitude && latLngBounds.northeast.latitude == m18376G.f6159c.northeast.latitude && latLngBounds.northeast.longitude == m18376G.f6159c.northeast.longitude) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public MapStatus m18919a(C2925d c2925d, MapStatus mapStatus) {
        if (c2925d == null || mapStatus == null) {
            return null;
        }
        switch (this.f6171p) {
            case 1:
                return this.f6157a;
            case 2:
                return new MapStatus(mapStatus.rotate, this.f6158b, mapStatus.overlook, mapStatus.zoom, mapStatus.targetScreen, null);
            case 3:
                LatLngBounds latLngBounds = this.f6159c;
                if (latLngBounds == null) {
                    return null;
                }
                GeoPoint ll2mc = CoordUtil.ll2mc(latLngBounds.southwest);
                GeoPoint ll2mc2 = CoordUtil.ll2mc(this.f6159c.northeast);
                float m18355a = c2925d.m18355a((int) ll2mc.getLongitudeE6(), (int) ll2mc2.getLatitudeE6(), (int) ll2mc2.getLongitudeE6(), (int) ll2mc.getLatitudeE6(), mapStatus.f6144a.f7378j.right - mapStatus.f6144a.f7378j.left, mapStatus.f6144a.f7378j.bottom - mapStatus.f6144a.f7378j.top);
                return new MapStatus(mapStatus.rotate, this.f6159c.getCenter(), mapStatus.overlook, m18355a, mapStatus.targetScreen, null);
            case 4:
                return new MapStatus(mapStatus.rotate, this.f6158b, mapStatus.overlook, this.f6162f, mapStatus.targetScreen, null);
            case 5:
                GeoPoint m18325b = c2925d.m18325b((c2925d.m18374I() / 2) + this.f6163g, (c2925d.m18373J() / 2) + this.f6164h);
                return new MapStatus(mapStatus.rotate, CoordUtil.mc2ll(m18325b), mapStatus.overlook, mapStatus.zoom, mapStatus.targetScreen, m18325b.getLongitudeE6(), m18325b.getLatitudeE6(), null);
            case 6:
                return new MapStatus(mapStatus.rotate, mapStatus.target, mapStatus.overlook, mapStatus.zoom + this.f6165i, mapStatus.targetScreen, mapStatus.m18931a(), mapStatus.m18929b(), null);
            case 7:
                return new MapStatus(mapStatus.rotate, CoordUtil.mc2ll(c2925d.m18325b(this.f6166j.x, this.f6166j.y)), mapStatus.overlook, mapStatus.zoom + this.f6165i, this.f6166j, null);
            case 8:
                return new MapStatus(mapStatus.rotate, mapStatus.target, mapStatus.overlook, this.f6162f, mapStatus.targetScreen, mapStatus.m18931a(), mapStatus.m18929b(), null);
            case 9:
                LatLngBounds latLngBounds2 = this.f6159c;
                if (latLngBounds2 == null) {
                    return null;
                }
                GeoPoint ll2mc3 = CoordUtil.ll2mc(latLngBounds2.southwest);
                GeoPoint ll2mc4 = CoordUtil.ll2mc(this.f6159c.northeast);
                float m18355a2 = c2925d.m18355a((int) ll2mc3.getLongitudeE6(), (int) ll2mc4.getLatitudeE6(), (int) ll2mc4.getLongitudeE6(), (int) ll2mc3.getLatitudeE6(), this.f6160d, this.f6161e);
                return new MapStatus(mapStatus.rotate, this.f6159c.getCenter(), mapStatus.overlook, m18355a2, mapStatus.targetScreen, null);
            case 10:
                if (this.f6159c == null) {
                    return null;
                }
                int m18374I = (c2925d.m18374I() - this.f6167k) - this.f6169m;
                if (m18374I < 0) {
                    m18374I = c2925d.m18374I();
                    Log.e(f6156o, "Bound paddingLeft or paddingRight too larger, please check");
                }
                int m18373J = (c2925d.m18373J() - this.f6168l) - this.f6170n;
                if (m18373J < 0) {
                    m18373J = c2925d.m18373J();
                    Log.e(f6156o, "Bound paddingTop or paddingBottom too larger, please check");
                }
                float m18920a = m18920a(this.f6159c, c2925d, m18374I, m18373J);
                LatLng m18921a = m18921a(this.f6159c, c2925d, m18925a(m18920a));
                if (m18921a == null) {
                    Log.e(f6156o, "Bound center error");
                    return null;
                }
                boolean m18922a = m18922a(this.f6159c, c2925d);
                boolean m18924a = m18924a(this.f6167k, this.f6168l, this.f6169m, this.f6170n, c2925d);
                if (m18922a || m18924a) {
                    MapStatus mapStatus2 = new MapStatus(mapStatus.rotate, m18921a, mapStatus.overlook, m18920a, null, null);
                    c2925d.m18346a(m18923a(mapStatus2));
                    return mapStatus2;
                } else if (c2925d.m18376G() != null) {
                    return c2925d.m18376G().f6157a;
                } else {
                    return null;
                }
            case 11:
                if (this.f6159c == null) {
                    return null;
                }
                int m18374I2 = (c2925d.m18374I() - this.f6167k) - this.f6169m;
                if (m18374I2 < 0) {
                    m18374I2 = c2925d.m18374I();
                    Log.e(f6156o, "Bound paddingLeft or paddingRight too larger, please check");
                }
                int m18373J2 = (c2925d.m18373J() - this.f6168l) - this.f6170n;
                if (m18373J2 < 0) {
                    m18373J2 = c2925d.m18373J();
                    Log.e(f6156o, "Bound paddingTop or paddingBottom too larger, please check");
                }
                GeoPoint ll2mc5 = CoordUtil.ll2mc(this.f6159c.southwest);
                GeoPoint ll2mc6 = CoordUtil.ll2mc(this.f6159c.northeast);
                float m18355a3 = c2925d.m18355a((int) ll2mc5.getLongitudeE6(), (int) ll2mc6.getLatitudeE6(), (int) ll2mc6.getLongitudeE6(), (int) ll2mc5.getLatitudeE6(), m18374I2, m18373J2);
                Point point = new Point(this.f6167k + (m18374I2 / 2), this.f6168l + (m18373J2 / 2));
                return new MapStatus(mapStatus.rotate, this.f6159c.getCenter(), mapStatus.overlook, m18355a3, point, null);
            default:
                return null;
        }
    }
}
