package com.baidu.mapapi.model;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.baidu.mapsdkplatform.comapi.util.CoordUtilInternal;
import com.baidu.mapsdkplatform.comjni.tools.AppTools;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.basestruct.Point;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CoordUtil {
    public static LatLng mc2ll(GeoPoint geoPoint) {
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            return CoordTrans.baiduToGcj(CoordUtilInternal.m18167a(geoPoint));
        }
        return CoordUtilInternal.m18167a(geoPoint);
    }

    public static GeoPoint ll2mc(LatLng latLng) {
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            return CoordUtilInternal.m18170a(CoordTrans.gcjToBaidu(latLng));
        }
        return CoordUtilInternal.m18170a(latLng);
    }

    public static Point ll2point(LatLng latLng) {
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            return CoordUtilInternal.m18165b(CoordTrans.gcjToBaidu(latLng));
        }
        return CoordUtilInternal.m18165b(latLng);
    }

    public static int getMCDistanceByOneLatLngAndRadius(LatLng latLng, int i) {
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            return CoordUtilInternal.m18169a(CoordTrans.gcjToBaidu(latLng), i);
        }
        return CoordUtilInternal.m18169a(latLng, i);
    }

    public static LatLng decodeLocation(String str) {
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            return CoordTrans.baiduToGcj(CoordUtilInternal.m18166a(str));
        }
        return CoordUtilInternal.m18166a(str);
    }

    public static LatLng decodeNodeLocation(String str) {
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            return CoordTrans.baiduToGcj(CoordUtilInternal.m18164b(str));
        }
        return CoordUtilInternal.m18164b(str);
    }

    public static List<LatLng> decodeLocationList(String str) {
        return CoordUtilInternal.m18163c(str);
    }

    public static List<List<LatLng>> decodeLocationList2D(String str) {
        return CoordUtilInternal.m18162d(str);
    }

    public static LatLng Coordinate_encryptEx(float f, float f2, String str) {
        return CoordUtilInternal.m18171a(f, f2, str);
    }

    public static double getDistance(Point point, Point point2) {
        return AppTools.m18116a(point, point2);
    }
}
