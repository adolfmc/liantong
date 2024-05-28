package com.baidu.mapapi.utils;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.basestruct.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SpatialRelationUtil {
    public static boolean isPolygonContainsPoint(List<LatLng> list, LatLng latLng) {
        if (list == null || list.size() == 0 || latLng == null) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (latLng.longitude == list.get(i).longitude && latLng.latitude == list.get(i).latitude) {
                return true;
            }
        }
        int size = list.size();
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            LatLng latLng2 = list.get(i2);
            i2++;
            LatLng latLng3 = list.get(i2 % size);
            if (latLng2.latitude != latLng3.latitude && latLng.latitude >= Math.min(latLng2.latitude, latLng3.latitude) && latLng.latitude < Math.max(latLng2.latitude, latLng3.latitude)) {
                double d = (((latLng.latitude - latLng2.latitude) * (latLng3.longitude - latLng2.longitude)) / (latLng3.latitude - latLng2.latitude)) + latLng2.longitude;
                if (d == latLng.longitude) {
                    return true;
                }
                if (d < latLng.longitude) {
                    i3++;
                }
            }
        }
        return i3 % 2 == 1;
    }

    public static boolean isCircleContainsPoint(LatLng latLng, int i, LatLng latLng2) {
        int i2;
        if (latLng == null || i == 0 || latLng2 == null || DistanceUtil.getDistance(latLng, latLng2) > i) {
            return false;
        }
        return i2 == 0 ? true : true;
    }

    public static LatLng getNearestPointFromLine(List<LatLng> list, LatLng latLng) {
        LatLng latLng2;
        LatLng latLng3 = null;
        if (list == null || list.size() == 0 || latLng == null) {
            return null;
        }
        int i = 0;
        while (i < list.size() - 1) {
            int i2 = i + 1;
            LatLng m18617a = m18617a(list.get(i), list.get(i2), latLng);
            if ((m18617a.latitude - list.get(i).latitude) * (m18617a.latitude - list.get(i2).latitude) > 0.0d || (m18617a.longitude - list.get(i).longitude) * (m18617a.longitude - list.get(i2).longitude) > 0.0d) {
                latLng2 = DistanceUtil.getDistance(latLng, list.get(i)) < DistanceUtil.getDistance(latLng, list.get(i2)) ? list.get(i) : list.get(i2);
            } else {
                latLng2 = m18617a;
            }
            if (latLng3 == null) {
                latLng3 = latLng2;
            } else if (DistanceUtil.getDistance(latLng, latLng2) < DistanceUtil.getDistance(latLng, latLng3)) {
                latLng3 = latLng2;
            }
            i = i2;
        }
        return latLng3;
    }

    public static LatLng getNearestDistancePointFromLine(List<LatLng> list, LatLng latLng) {
        if (list == null || list.size() == 0 || latLng == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<LatLng> it = list.iterator();
        while (it != null && it.hasNext()) {
            LatLng next = it.next();
            arrayList.add(new Point(next.longitude, next.latitude));
            if (next.equals(latLng)) {
                return next;
            }
        }
        Point m18616a = m18616a(arrayList, new Point(latLng.longitude, latLng.latitude));
        if (m18616a != null) {
            return new LatLng(m18616a.doubleY, m18616a.doubleX);
        }
        return null;
    }

    /* renamed from: a */
    private static Point m18616a(List<Point> list, Point point) {
        if (list == null || point == null || list.size() == 0 || list.size() < 2) {
            return null;
        }
        int size = list.size();
        Point point2 = list.get(0);
        Point point3 = null;
        int i = 1;
        while (true) {
            int i2 = size - 1;
            if (i > i2) {
                return point3;
            }
            Point point4 = list.get(i);
            if ((i == i2 && point4.equals(point)) || point2.equals(point)) {
                return point;
            }
            Point m18618a = m18618a(point.doubleX, point.doubleY, point2.doubleX, point2.doubleY, point4.doubleX, point4.doubleY);
            i++;
            point2 = point4;
            point3 = m18618a;
        }
    }

    /* renamed from: a */
    private static Point m18618a(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = d5 - d3;
        double d8 = d6 - d4;
        double d9 = ((d - d3) * d7) + ((d2 - d4) * d8);
        if (d9 <= 0.0d) {
            return new Point(d3, d4);
        }
        double d10 = (d7 * d7) + (d8 * d8);
        if (d9 >= d10) {
            return new Point(d5, d6);
        }
        double d11 = d9 / d10;
        return new Point(d3 + (d7 * d11), d4 + (d8 * d11));
    }

    /* renamed from: a */
    private static LatLng m18617a(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        GeoPoint ll2mc = CoordUtil.ll2mc(latLng);
        GeoPoint ll2mc2 = CoordUtil.ll2mc(latLng2);
        GeoPoint ll2mc3 = CoordUtil.ll2mc(latLng3);
        double sqrt = Math.sqrt(((ll2mc2.getLongitudeE6() - ll2mc.getLongitudeE6()) * (ll2mc2.getLongitudeE6() - ll2mc.getLongitudeE6())) + ((ll2mc2.getLatitudeE6() - ll2mc.getLatitudeE6()) * (ll2mc2.getLatitudeE6() - ll2mc.getLatitudeE6())));
        double longitudeE6 = (((ll2mc2.getLongitudeE6() - ll2mc.getLongitudeE6()) * (ll2mc3.getLongitudeE6() - ll2mc.getLongitudeE6())) + ((ll2mc2.getLatitudeE6() - ll2mc.getLatitudeE6()) * (ll2mc3.getLatitudeE6() - ll2mc.getLatitudeE6()))) / (sqrt * sqrt);
        return CoordUtil.mc2ll(new GeoPoint(ll2mc.getLatitudeE6() + ((ll2mc2.getLatitudeE6() - ll2mc.getLatitudeE6()) * longitudeE6), ll2mc.getLongitudeE6() + ((ll2mc2.getLongitudeE6() - ll2mc.getLongitudeE6()) * longitudeE6)));
    }
}
