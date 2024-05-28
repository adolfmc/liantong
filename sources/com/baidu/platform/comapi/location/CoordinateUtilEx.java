package com.baidu.platform.comapi.location;

import com.baidu.platform.comapi.basestruct.ComplexPt;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.basestruct.Point;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CoordinateUtilEx {
    public static Point getGeoPointFromString(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        return CoordinateUtil.geoStringToPoint(str);
    }

    public static String getStringFromGeoPoint(Point point) {
        return CoordinateUtil.pointToGeoString(point);
    }

    @Deprecated
    public static ComplexPt getGeoComplexPtBoundFromString(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        return CoordinateUtil.geoStringToComplexPtBound(str);
    }

    @Deprecated
    public static ComplexPt getGeoComplexPointFromString(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        return CoordinateUtil.geoStringToComplexPt(str);
    }

    public static double getDistanceByMc(Point point, Point point2) {
        return CoordinateUtil.getDistanceByMc(point.getDoubleX(), point.getDoubleY(), point2.getDoubleX(), point2.getDoubleY());
    }

    public static double getDistanceByMc(GeoPoint geoPoint, GeoPoint geoPoint2) {
        return CoordinateUtil.getDistanceByMc(geoPoint.getLongitude(), geoPoint.getLatitude(), geoPoint2.getLongitude(), geoPoint2.getLatitude());
    }

    public static Point Coordinate_encryptEx(float f, float f2, String str) {
        if (str == null) {
            return null;
        }
        if (str.equals("")) {
            str = "bd09ll";
        }
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != -1395470197) {
            if (hashCode != -1395470175) {
                if (hashCode != 98175376) {
                    if (hashCode == 113079775 && str.equals("wgs84")) {
                        c = 0;
                    }
                } else if (str.equals("gcj02")) {
                    c = 1;
                }
            } else if (str.equals("bd09mc")) {
                c = 3;
            }
        } else if (str.equals("bd09ll")) {
            c = 2;
        }
        switch (c) {
            case 0:
                return CoordinateUtil.wgs84Tobd09mc(f, f2);
            case 1:
                return CoordinateUtil.gcj02Tobd09mc(f, f2);
            case 2:
                return CoordinateUtil.bd09llTobd09mc(f, f2);
            case 3:
                return new Point(f, f2);
            default:
                return null;
        }
    }

    public static ArrayList<Point> Coordinate_encryptExArray(ArrayList<Point> arrayList, String str) {
        Point wgs84Tobd09mc;
        if (str == null) {
            return null;
        }
        if (str.equals("")) {
            str = "bd09ll";
        }
        if (str.equals("bd09ll") || str.equals("bd09mc") || str.equals("gcj02") || str.equals("wgs84")) {
            float[] fArr = new float[arrayList.size()];
            float[] fArr2 = new float[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++) {
                fArr[i] = arrayList.get(i).getIntX() / 100000.0f;
                fArr2[i] = arrayList.get(i).getIntY() / 100000.0f;
            }
            ArrayList<Point> arrayList2 = new ArrayList<>();
            for (int i2 = 0; i2 < fArr.length; i2++) {
                char c = 65535;
                int hashCode = str.hashCode();
                if (hashCode != -1395470197) {
                    if (hashCode != -1395470175) {
                        if (hashCode != 98175376) {
                            if (hashCode == 113079775 && str.equals("wgs84")) {
                                c = 0;
                            }
                        } else if (str.equals("gcj02")) {
                            c = 1;
                        }
                    } else if (str.equals("bd09mc")) {
                        c = 3;
                    }
                } else if (str.equals("bd09ll")) {
                    c = 2;
                }
                switch (c) {
                    case 0:
                        wgs84Tobd09mc = CoordinateUtil.wgs84Tobd09mc(fArr[i2], fArr2[i2]);
                        break;
                    case 1:
                        wgs84Tobd09mc = CoordinateUtil.gcj02Tobd09mc(fArr[i2], fArr2[i2]);
                        break;
                    case 2:
                        wgs84Tobd09mc = CoordinateUtil.bd09llTobd09mc(fArr[i2], fArr2[i2]);
                        break;
                    case 3:
                        wgs84Tobd09mc = new Point(fArr[i2], fArr2[i2]);
                        break;
                    default:
                        wgs84Tobd09mc = null;
                        break;
                }
                if (wgs84Tobd09mc != null) {
                    arrayList2.add(wgs84Tobd09mc);
                }
            }
            return arrayList2;
        }
        return null;
    }
}
