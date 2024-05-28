package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.map.GradientLineOptions;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapsdkplatform.comapi.map.EnumC2933i;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GradientLine extends Overlay {

    /* renamed from: a */
    List<LatLng> f6035a;

    /* renamed from: b */
    int[] f6036b;

    /* renamed from: c */
    int[] f6037c;

    /* renamed from: d */
    int f6038d = 5;

    /* renamed from: e */
    GradientLineOptions.LineDirectionCross180 f6039e = GradientLineOptions.LineDirectionCross180.NONE;

    public GradientLine() {
        this.type = EnumC2933i.gradientLine;
    }

    /* renamed from: a */
    private static void m18966a(List<LatLng> list, GradientLineOptions.LineDirectionCross180 lineDirectionCross180, Bundle bundle) {
        LatLng latLng;
        String str;
        GradientLineOptions.LineDirectionCross180 lineDirectionCross1802;
        int size = list.size();
        double[] dArr = new double[size];
        double[] dArr2 = new double[size];
        for (int i = 0; i < size; i++) {
            LatLng latLng2 = list.get(i);
            if (lineDirectionCross180 == GradientLineOptions.LineDirectionCross180.FROM_EAST_TO_WEST && latLng2.longitude < 0.0d) {
                latLng = new LatLng(latLng2.latitude, latLng2.longitude + 360.0d);
                str = "lineDirectionCross180";
                lineDirectionCross1802 = GradientLineOptions.LineDirectionCross180.FROM_EAST_TO_WEST;
            } else if (lineDirectionCross180 != GradientLineOptions.LineDirectionCross180.FROM_WEST_TO_EAST || latLng2.longitude <= 0.0d) {
                bundle.putInt("lineDirectionCross180", GradientLineOptions.LineDirectionCross180.NONE.ordinal());
                latLng = latLng2;
                GeoPoint ll2mc = CoordUtil.ll2mc(latLng);
                dArr[i] = ll2mc.getLongitudeE6();
                dArr2[i] = ll2mc.getLatitudeE6();
            } else {
                latLng = new LatLng(latLng2.latitude, latLng2.longitude - 360.0d);
                str = "lineDirectionCross180";
                lineDirectionCross1802 = GradientLineOptions.LineDirectionCross180.FROM_WEST_TO_EAST;
            }
            bundle.putInt(str, lineDirectionCross1802.ordinal());
            GeoPoint ll2mc2 = CoordUtil.ll2mc(latLng);
            dArr[i] = ll2mc2.getLongitudeE6();
            dArr2[i] = ll2mc2.getLatitudeE6();
        }
        bundle.putDoubleArray("x_array", dArr);
        bundle.putDoubleArray("y_array", dArr2);
    }

    /* renamed from: a */
    private static void m18965a(int[] iArr, Bundle bundle) {
        if (iArr == null || iArr.length <= 0) {
            return;
        }
        bundle.putIntArray("color_indexs", iArr);
    }

    /* renamed from: b */
    private static void m18964b(int[] iArr, Bundle bundle) {
        if (iArr == null || iArr.length <= 0) {
            return;
        }
        bundle.putIntArray("color_array", iArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.mapapi.map.Overlay
    /* renamed from: a */
    public Bundle mo18867a(Bundle bundle) {
        super.mo18867a(bundle);
        List<LatLng> list = this.f6035a;
        if (list == null || list.size() < 2) {
            throw new IllegalStateException("BDMapSDKException: when you add GradientLine, you must at least supply 2 points");
        }
        GeoPoint ll2mc = CoordUtil.ll2mc(this.f6035a.get(0));
        bundle.putDouble("location_x", ll2mc.getLongitudeE6());
        bundle.putDouble("location_y", ll2mc.getLatitudeE6());
        bundle.putInt("width", this.f6038d);
        int[] iArr = this.f6036b;
        if (iArr != null) {
            if (iArr.length != 0) {
                m18966a(this.f6035a, this.f6039e, bundle);
                m18965a(this.f6036b, bundle);
                int[] iArr2 = this.f6037c;
                if (iArr2 != null) {
                    if (iArr2.length != 0) {
                        m18964b(iArr2, bundle);
                        return bundle;
                    }
                    throw new IllegalStateException("BDMapSDKException: colors array size can not be Equal to zero");
                }
                throw new IllegalStateException("BDMapSDKException: colors array can not be null");
            }
            throw new IllegalStateException("BDMapSDKException: Indexs array size can not be Equal to zero");
        }
        throw new IllegalStateException("BDMapSDKException: Indexs array can not be null");
    }

    public int[] getColors() {
        return this.f6037c;
    }

    public int[] getIndexs() {
        return this.f6036b;
    }

    public GradientLineOptions.LineDirectionCross180 getLineDirectionCross180() {
        return this.f6039e;
    }

    public List<LatLng> getPoints() {
        return this.f6035a;
    }

    public float getWidth() {
        return this.f6038d;
    }

    public void lineDirectionCross180(GradientLineOptions.LineDirectionCross180 lineDirectionCross180) {
        this.f6039e = lineDirectionCross180;
    }

    public void setColorIndex(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("BDMapSDKException: indexList can not empty");
        }
        this.f6036b = iArr;
        this.listener.mo18796c(this);
    }

    public void setColorList(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("BDMapSDKException: colorList can not empty");
        }
        this.f6037c = iArr;
        this.listener.mo18796c(this);
    }

    public void setPoints(List<LatLng> list) {
        if (list == null) {
            throw new IllegalArgumentException("BDMapSDKException: points list can not be null");
        }
        if (list.size() < 2) {
            throw new IllegalArgumentException("BDMapSDKException: points count can not less than 2");
        }
        if (list.contains(null)) {
            throw new IllegalArgumentException("BDMapSDKException: points list can not contains null");
        }
        this.f6035a = list;
        this.listener.mo18796c(this);
    }

    public void setWidth(int i) {
        if (i > 0) {
            this.f6038d = i;
            this.listener.mo18796c(this);
        }
    }
}
