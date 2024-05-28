package com.baidu.platform.comapi.map;

import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.basestruct.Point;
import com.baidu.platform.comjni.map.basemap.AppBaseMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.l */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C3070l implements Projection {

    /* renamed from: a */
    private MapController f7980a;

    public C3070l(MapController mapController) {
        this.f7980a = null;
        this.f7980a = mapController;
    }

    @Override // com.baidu.platform.comapi.map.Projection
    public GeoPoint fromPixels(int i, int i2) {
        AppBaseMap baseMap = this.f7980a.getBaseMap();
        if (baseMap == null) {
            return null;
        }
        String ScrPtToGeoPoint = baseMap.ScrPtToGeoPoint(i, i2);
        GeoPoint geoPoint = new GeoPoint(0, 0);
        if (ScrPtToGeoPoint != null) {
            try {
                JSONObject jSONObject = new JSONObject(ScrPtToGeoPoint);
                geoPoint.setLongitude(jSONObject.getDouble("geox"));
                geoPoint.setLatitude(jSONObject.getDouble("geoy"));
                return geoPoint;
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    @Override // com.baidu.platform.comapi.map.Projection
    public float metersToEquatorPixels(float f) {
        return (float) (f / this.f7980a.getZoomUnitsInMeter());
    }

    @Override // com.baidu.platform.comapi.map.Projection
    public Point toPixels(GeoPoint geoPoint, int i, Point point) {
        String geoPt3ToScrPoint;
        if (point == null) {
            point = new Point(0, 0);
        }
        AppBaseMap baseMap = this.f7980a.getBaseMap();
        if (baseMap != null && (geoPt3ToScrPoint = baseMap.geoPt3ToScrPoint((int) geoPoint.getLongitude(), (int) geoPoint.getLatitude(), i)) != null) {
            try {
                JSONObject jSONObject = new JSONObject(geoPt3ToScrPoint);
                point.setIntX(jSONObject.getInt("scrx"));
                point.setIntY(jSONObject.getInt("scry"));
            } catch (JSONException unused) {
            }
        }
        return point;
    }

    @Override // com.baidu.platform.comapi.map.Projection
    public Point toPixels(GeoPoint geoPoint, Point point) {
        String GeoPtToScrPoint;
        if (point == null) {
            point = new Point(0, 0);
        }
        AppBaseMap baseMap = this.f7980a.getBaseMap();
        if (baseMap != null && (GeoPtToScrPoint = baseMap.GeoPtToScrPoint((int) geoPoint.getLongitude(), (int) geoPoint.getLatitude())) != null) {
            try {
                JSONObject jSONObject = new JSONObject(GeoPtToScrPoint);
                point.setIntX(jSONObject.getInt("scrx"));
                point.setIntY(jSONObject.getInt("scry"));
            } catch (JSONException unused) {
            }
        }
        return point;
    }

    @Override // com.baidu.platform.comapi.map.Projection
    public Point world2Screen(float f, float f2, float f3) {
        Point point = new Point(0, 0);
        AppBaseMap baseMap = this.f7980a.getBaseMap();
        if (baseMap == null) {
            return point;
        }
        String worldPointToScreenPoint = baseMap.worldPointToScreenPoint(f, f2, f3);
        if (worldPointToScreenPoint != null) {
            try {
                JSONObject jSONObject = new JSONObject(worldPointToScreenPoint);
                point.setDoubleX(jSONObject.optDouble("scrx"));
                point.setDoubleY(jSONObject.optDouble("scry"));
                return point;
            } catch (JSONException unused) {
                return null;
            }
        }
        return null;
    }
}
