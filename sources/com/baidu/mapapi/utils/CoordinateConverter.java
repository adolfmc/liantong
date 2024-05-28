package com.baidu.mapapi.utils;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CoordinateConverter {

    /* renamed from: a */
    private LatLng f6980a;

    /* renamed from: b */
    private CoordType f6981b;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum CoordType {
        GPS,
        COMMON,
        BD09LL,
        BD09MC
    }

    public CoordinateConverter coord(LatLng latLng) {
        this.f6980a = latLng;
        return this;
    }

    public CoordinateConverter from(CoordType coordType) {
        this.f6981b = coordType;
        return this;
    }

    public LatLng convert() {
        if (this.f6980a == null) {
            return null;
        }
        if (this.f6981b == null) {
            this.f6981b = CoordType.GPS;
        }
        switch (this.f6981b) {
            case COMMON:
                return m18621b(this.f6980a);
            case GPS:
                return m18623a(this.f6980a);
            case BD09LL:
                return m18619d(this.f6980a);
            case BD09MC:
                return m18620c(this.f6980a);
            default:
                return null;
        }
    }

    /* renamed from: a */
    private static LatLng m18623a(LatLng latLng) {
        return m18622a(latLng, "wgs84");
    }

    /* renamed from: b */
    private static LatLng m18621b(LatLng latLng) {
        return m18622a(latLng, "gcj02");
    }

    /* renamed from: c */
    private static LatLng m18620c(LatLng latLng) {
        return m18622a(latLng, "bd09mc");
    }

    /* renamed from: a */
    private static LatLng m18622a(LatLng latLng, String str) {
        if (latLng == null) {
            return null;
        }
        return CoordUtil.Coordinate_encryptEx((float) latLng.longitude, (float) latLng.latitude, str);
    }

    /* renamed from: d */
    private static LatLng m18619d(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        return CoordTrans.baiduToGcj(latLng);
    }
}
