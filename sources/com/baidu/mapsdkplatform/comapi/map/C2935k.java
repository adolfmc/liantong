package com.baidu.mapsdkplatform.comapi.map;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.platform.comapi.basestruct.Point;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.map.k */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2935k {
    /* renamed from: a */
    private static double m18233a(double d) {
        return (d * 3.141592653589793d) / 180.0d;
    }

    /* renamed from: a */
    public static double m18232a(LatLng latLng, LatLng latLng2) {
        if (latLng != null && latLng2 != null) {
            Point ll2point = CoordUtil.ll2point(latLng);
            Point ll2point2 = CoordUtil.ll2point(latLng2);
            if (ll2point != null && ll2point2 != null) {
                return CoordUtil.getDistance(ll2point, ll2point2);
            }
        }
        return -1.0d;
    }

    /* renamed from: a */
    private static LatLng m18231a(LatLng latLng, LatLng latLng2, double d, double d2) {
        double d3 = latLng.latitude;
        double d4 = latLng2.latitude;
        double d5 = latLng.longitude;
        double d6 = latLng2.longitude;
        double sin = Math.sin((1.0d - d) * d2) / Math.sin(d2);
        double sin2 = Math.sin(d * d2) / Math.sin(d2);
        double m18233a = m18233a(d3);
        double m18233a2 = m18233a(d4);
        double m18233a3 = m18233a(d5);
        double m18233a4 = m18233a(d6);
        double cos = (Math.cos(m18233a) * sin * Math.cos(m18233a3)) + (Math.cos(m18233a2) * sin2 * Math.cos(m18233a4));
        double cos2 = (Math.cos(m18233a) * sin * Math.sin(m18233a3)) + (Math.cos(m18233a2) * sin2 * Math.sin(m18233a4));
        return new LatLng(m18230b(Math.atan2((sin * Math.sin(m18233a)) + (sin2 * Math.sin(m18233a2)), Math.sqrt(Math.pow(cos, 2.0d) + Math.pow(cos2, 2.0d)))), m18230b(Math.atan2(cos2, cos)));
    }

    /* renamed from: b */
    private static double m18230b(double d) {
        return (d / 3.141592653589793d) * 180.0d;
    }

    /* renamed from: b */
    public static List<LatLng> m18229b(LatLng latLng, LatLng latLng2) {
        double m18232a = m18232a(latLng, latLng2);
        ArrayList arrayList = new ArrayList();
        if (150000.0d > m18232a || m18232a < 250000.0d) {
            arrayList.add(latLng);
            arrayList.add(latLng2);
            return arrayList;
        }
        double round = Math.round(m18232a / 150000.0d);
        double m18228c = m18228c(latLng, latLng2);
        arrayList.add(latLng);
        for (double d = 0.0d; d < round; d += 1.0d) {
            arrayList.add(m18231a(latLng, latLng2, d / round, m18228c));
        }
        arrayList.add(latLng2);
        return arrayList;
    }

    /* renamed from: c */
    private static double m18228c(LatLng latLng, LatLng latLng2) {
        double m18233a = m18233a(latLng.latitude);
        double m18233a2 = m18233a(latLng2.latitude);
        double m18233a3 = m18233a(latLng.longitude);
        return Math.acos((Math.sin(m18233a) * Math.sin(m18233a2)) + (Math.cos(m18233a) * Math.cos(m18233a2) * Math.cos(Math.abs(m18233a(latLng2.longitude) - m18233a3))));
    }
}
