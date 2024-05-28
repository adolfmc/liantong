package com.baidu.mapsdkplatform.comapi.util;

import android.os.Bundle;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapsdkplatform.comjni.tools.AppTools;
import com.baidu.mapsdkplatform.comjni.tools.JNITools;
import com.baidu.platform.comapi.basestruct.ComplexPt;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.basestruct.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.util.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CoordUtilInternal {

    /* renamed from: a */
    static double[] f7419a = {1.289059486E7d, 8362377.87d, 5591021.0d, 3481989.83d, 1678043.12d, 0.0d};

    /* renamed from: b */
    static double[] f7420b = {7.5E7d, 6.0E7d, 4.5E7d, 3.0E7d, 1.5E7d, 0.0d};

    /* renamed from: c */
    static double[][] f7421c = {new double[]{1.410526172116255E-8d, 8.98305509648872E-6d, -1.9939833816331d, 200.9824383106796d, -187.2403703815547d, 91.6087516669843d, -23.38765649603339d, 2.57121317296198d, -0.03801003308653d, 1.73379812E7d}, new double[]{-7.435856389565537E-9d, 8.983055097726239E-6d, -0.78625201886289d, 96.32687599759846d, -1.85204757529826d, -59.36935905485877d, 47.40033549296737d, -16.50741931063887d, 2.28786674699375d, 1.026014486E7d}, new double[]{-3.030883460898826E-8d, 8.98305509983578E-6d, 0.30071316287616d, 59.74293618442277d, 7.357984074871d, -25.38371002664745d, 13.45380521110908d, -3.29883767235584d, 0.32710905363475d, 6856817.37d}, new double[]{-1.981981304930552E-8d, 8.983055099779535E-6d, 0.03278182852591d, 40.31678527705744d, 0.65659298677277d, -4.44255534477492d, 0.85341911805263d, 0.12923347998204d, -0.04625736007561d, 4482777.06d}, new double[]{3.09191371068437E-9d, 8.983055096812155E-6d, 6.995724062E-5d, 23.10934304144901d, -2.3663490511E-4d, -0.6321817810242d, -0.00663494467273d, 0.03430082397953d, -0.00466043876332d, 2555164.4d}, new double[]{2.890871144776878E-9d, 8.983055095805407E-6d, -3.068298E-8d, 7.47137025468032d, -3.53937994E-6d, -0.02145144861037d, -1.234426596E-5d, 1.0322952773E-4d, -3.23890364E-6d, 826088.5d}};

    /* renamed from: d */
    static double[][] f7422d = {new double[]{-0.0015702102444d, 111320.7020616939d, 1.704480524535203E15d, -1.033898737604234E16d, 2.611266785660388E16d, -3.51496691766537E16d, 2.659570071840392E16d, -1.072501245418824E16d, 1.800819912950474E15d, 82.5d}, new double[]{8.277824516172526E-4d, 111320.7020463578d, 6.477955746671607E8d, -4.082003173641316E9d, 1.077490566351142E10d, -1.517187553151559E10d, 1.205306533862167E10d, -5.124939663577472E9d, 9.133119359512032E8d, 67.5d}, new double[]{0.00337398766765d, 111320.7020202162d, 4481351.045890365d, -2.339375119931662E7d, 7.968221547186455E7d, -1.159649932797253E8d, 9.723671115602145E7d, -4.366194633752821E7d, 8477230.501135234d, 52.5d}, new double[]{0.00220636496208d, 111320.7020209128d, 51751.86112841131d, 3796837.749470245d, 992013.7397791013d, -1221952.21711287d, 1340652.697009075d, -620943.6990984312d, 144416.9293806241d, 37.5d}, new double[]{-3.441963504368392E-4d, 111320.7020576856d, 278.2353980772752d, 2485758.690035394d, 6070.750963243378d, 54821.18345352118d, 9540.606633304236d, -2710.55326746645d, 1405.483844121726d, 22.5d}, new double[]{-3.218135878613132E-4d, 111320.7020701615d, 0.00369383431289d, 823725.6402795718d, 0.46104986909093d, 2351.343141331292d, 1.58060784298199d, 8.77738589078284d, 0.37238884252424d, 7.45d}};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: CoordUtilInternal.java */
    /* renamed from: com.baidu.mapsdkplatform.comapi.util.b$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2956a {

        /* renamed from: a */
        double f7423a;

        /* renamed from: b */
        double f7424b;

        C2956a() {
        }
    }

    /* renamed from: a */
    static C2956a m18168a(C2956a c2956a, double[] dArr) {
        C2956a c2956a2 = new C2956a();
        c2956a2.f7423a = dArr[0] + (dArr[1] * Math.abs(c2956a.f7423a));
        double abs = Math.abs(c2956a.f7424b) / dArr[9];
        c2956a2.f7424b = dArr[2] + (dArr[3] * abs) + (dArr[4] * abs * abs) + (dArr[5] * abs * abs * abs) + (dArr[6] * abs * abs * abs * abs) + (dArr[7] * abs * abs * abs * abs * abs) + (dArr[8] * abs * abs * abs * abs * abs * abs);
        c2956a2.f7423a *= c2956a.f7423a < 0.0d ? -1 : 1;
        c2956a2.f7424b *= c2956a.f7424b < 0.0d ? -1 : 1;
        return c2956a2;
    }

    /* renamed from: a */
    public static LatLng m18167a(GeoPoint geoPoint) {
        if (geoPoint == null) {
            return null;
        }
        C2956a c2956a = new C2956a();
        c2956a.f7423a = geoPoint.getLongitudeE6();
        c2956a.f7424b = geoPoint.getLatitudeE6();
        C2956a c2956a2 = new C2956a();
        c2956a2.f7423a = c2956a.f7423a;
        if (c2956a2.f7423a > 2.0037508342E7d) {
            c2956a2.f7423a = (c2956a2.f7423a - 2.0037508342E7d) - 2.0037508342E7d;
        } else if (c2956a2.f7423a < -2.0037508342E7d) {
            c2956a2.f7423a = 2.0037508342E7d - ((-2.0037508342E7d) - c2956a2.f7423a);
        }
        c2956a2.f7424b = c2956a.f7424b;
        if (c2956a2.f7424b < 1.0E-6d && c2956a2.f7424b >= 0.0d) {
            c2956a2.f7424b = 1.0E-6d;
        } else if (c2956a2.f7424b < 0.0d && c2956a2.f7424b > -1.0E-6d) {
            c2956a2.f7424b = -1.0E-6d;
        } else if (c2956a2.f7424b > 2.0037508342E7d) {
            c2956a2.f7424b = 2.0037508342E7d;
        } else if (c2956a2.f7424b < -2.0037508342E7d) {
            c2956a2.f7424b = -2.0037508342E7d;
        }
        double[] dArr = new double[10];
        int i = 0;
        while (true) {
            if (i >= 6) {
                break;
            } else if (Math.abs(c2956a2.f7424b) > f7419a[i]) {
                dArr = f7421c[i];
                break;
            } else {
                i++;
            }
        }
        C2956a m18168a = m18168a(c2956a2, dArr);
        return new LatLng(m18168a.f7424b, m18168a.f7423a);
    }

    /* renamed from: a */
    public static GeoPoint m18170a(LatLng latLng) {
        C2956a c2956a = new C2956a();
        double[] dArr = new double[10];
        c2956a.f7424b = Math.abs(latLng.latitude * 1000000.0d);
        if (c2956a.f7424b < 0.1d) {
            c2956a.f7424b = 0.1d;
        }
        int i = 0;
        while (true) {
            if (i >= f7420b.length) {
                break;
            } else if (c2956a.f7424b > f7420b[i]) {
                dArr = f7422d[i];
                break;
            } else {
                i++;
            }
        }
        c2956a.f7423a = latLng.longitude;
        c2956a.f7424b = latLng.latitude;
        C2956a m18168a = m18168a(c2956a, dArr);
        return new GeoPoint(m18168a.f7424b, m18168a.f7423a);
    }

    /* renamed from: b */
    public static Point m18165b(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        JNITools.CoordinateEncryptMc((float) latLng.longitude, (float) latLng.latitude, bundle);
        Point point = new Point(0, 0);
        point.setIntX((int) bundle.getDouble("x"));
        point.setIntY((int) bundle.getDouble("y"));
        return point;
    }

    /* renamed from: a */
    public static int m18169a(LatLng latLng, int i) {
        LatLng latLng2 = new LatLng(latLng.latitude + (i / 111000.0d), latLng.longitude);
        GeoPoint m18170a = m18170a(latLng);
        GeoPoint m18170a2 = m18170a(latLng2);
        return (int) Math.sqrt(Math.pow(m18170a.getLatitudeE6() - m18170a2.getLatitudeE6(), 2.0d) + Math.pow(m18170a.getLongitudeE6() - m18170a2.getLongitudeE6(), 2.0d));
    }

    /* renamed from: a */
    public static LatLng m18166a(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("strkey", str);
        JNITools.TransGeoStr2Pt(bundle);
        GeoPoint geoPoint = new GeoPoint(0, 0);
        geoPoint.setLongitudeE6(bundle.getInt("ptx"));
        geoPoint.setLatitudeE6(bundle.getInt("pty"));
        return m18167a(geoPoint);
    }

    /* renamed from: b */
    public static LatLng m18164b(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("strkey", str);
        JNITools.TransNodeStr2Pt(bundle);
        return m18167a(new GeoPoint(bundle.getDouble("pty"), bundle.getDouble("ptx")));
    }

    /* renamed from: c */
    public static List<LatLng> m18163c(String str) {
        ComplexPt m18115a = AppTools.m18115a(str);
        ArrayList arrayList = new ArrayList();
        if (m18115a == null || m18115a.f7541d == null) {
            return null;
        }
        ArrayList<ArrayList<Point>> arrayList2 = m18115a.f7541d;
        if (arrayList2.size() > 0) {
            ArrayList<Point> arrayList3 = arrayList2.get(0);
            for (int i = 0; i < arrayList3.size(); i++) {
                Point point = arrayList3.get(i);
                if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                    arrayList.add(CoordTrans.baiduToGcj(m18167a(new GeoPoint(point.getDoubleY() / 100.0d, point.getDoubleX() / 100.0d))));
                } else {
                    arrayList.add(m18167a(new GeoPoint(point.getDoubleY() / 100.0d, point.getDoubleX() / 100.0d)));
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static LatLng m18171a(float f, float f2, String str) {
        if (str == null) {
            return null;
        }
        if (str.equals("") || str.equals("bd09ll")) {
            return new LatLng(f, f2);
        }
        if (str.equals("bd09ll") || str.equals("bd09mc") || str.equals("gcj02") || str.equals("wgs84")) {
            Bundle bundle = new Bundle();
            JNITools.CoordinateEncryptEx(f, f2, str, bundle);
            if (bundle.isEmpty()) {
                return null;
            }
            return new LatLng(bundle.getDouble("y"), bundle.getDouble("x"));
        }
        return null;
    }

    /* renamed from: d */
    public static List<List<LatLng>> m18162d(String str) {
        ComplexPt m18115a = AppTools.m18115a(str);
        if (m18115a == null || m18115a.f7541d == null) {
            return null;
        }
        ArrayList<ArrayList<Point>> arrayList = m18115a.f7541d;
        ArrayList arrayList2 = new ArrayList();
        Iterator<ArrayList<Point>> it = arrayList.iterator();
        while (it.hasNext()) {
            ArrayList arrayList3 = new ArrayList();
            Iterator<Point> it2 = it.next().iterator();
            while (it2.hasNext()) {
                Point next = it2.next();
                if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                    arrayList3.add(CoordTrans.baiduToGcj(m18167a(new GeoPoint(next.getDoubleY() / 100.0d, next.getDoubleX() / 100.0d))));
                } else {
                    arrayList3.add(m18167a(new GeoPoint(next.getDoubleY() / 100.0d, next.getDoubleX() / 100.0d)));
                }
            }
            arrayList2.add(arrayList3);
        }
        return arrayList2;
    }
}
