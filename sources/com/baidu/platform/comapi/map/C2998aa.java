package com.baidu.platform.comapi.map;

import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.basestruct.Point;
import com.baidu.platform.comapi.location.CoordinateUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.aa */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2998aa {
    /* renamed from: a */
    public static GeoPoint m17988a(GeoPoint geoPoint) {
        Point bd09llTobd09mc = CoordinateUtil.bd09llTobd09mc(geoPoint.getLongitude(), geoPoint.getLatitude());
        if (bd09llTobd09mc != null) {
            return new GeoPoint(bd09llTobd09mc.getDoubleY(), bd09llTobd09mc.getDoubleX());
        }
        return null;
    }
}
