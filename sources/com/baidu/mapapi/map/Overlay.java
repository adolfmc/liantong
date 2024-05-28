package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapsdkplatform.comapi.map.EnumC2933i;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class Overlay {

    /* renamed from: F */
    String f6297F = System.currentTimeMillis() + "_" + hashCode();

    /* renamed from: G */
    int f6298G;

    /* renamed from: H */
    boolean f6299H;

    /* renamed from: I */
    Bundle f6300I;
    protected InterfaceC2746a listener;
    public EnumC2933i type;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapapi.map.Overlay$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    interface InterfaceC2746a {
        /* renamed from: a */
        LatLngBounds mo18798a(Overlay overlay);

        /* renamed from: b */
        void mo18797b(Overlay overlay);

        /* renamed from: c */
        void mo18796c(Overlay overlay);

        /* renamed from: d */
        boolean mo18795d(Overlay overlay);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m18893a(int i, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putFloat("red", ((i >> 16) & 255) / 255.0f);
        bundle2.putFloat("green", ((i >> 8) & 255) / 255.0f);
        bundle2.putFloat("blue", (i & 255) / 255.0f);
        bundle2.putFloat("alpha", (i >>> 24) / 255.0f);
        bundle.putBundle("color", bundle2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m18892a(List<LatLng> list, Bundle bundle) {
        int size = list.size();
        double[] dArr = new double[size];
        double[] dArr2 = new double[size];
        for (int i = 0; i < size; i++) {
            GeoPoint ll2mc = CoordUtil.ll2mc(list.get(i));
            dArr[i] = ll2mc.getLongitudeE6();
            dArr2[i] = ll2mc.getLatitudeE6();
        }
        bundle.putDoubleArray("x_array", dArr);
        bundle.putDoubleArray("y_array", dArr2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static void m18891b(int i, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putFloat("red", ((i >> 16) & 255) / 255.0f);
        bundle2.putFloat("green", ((i >> 8) & 255) / 255.0f);
        bundle2.putFloat("blue", (i & 255) / 255.0f);
        bundle2.putFloat("alpha", (i >>> 24) / 255.0f);
        bundle.putBundle("m_topFaceColor", bundle2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static boolean m18890b(List<HoleOptions> list, Bundle bundle) {
        boolean z;
        boolean z2;
        if (list == null || list.size() == 0) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (HoleOptions holeOptions : list) {
            if (holeOptions instanceof CircleHoleOptions) {
                arrayList.add((CircleHoleOptions) holeOptions);
            } else if (holeOptions instanceof PolygonHoleOptions) {
                arrayList2.add((PolygonHoleOptions) holeOptions);
            }
        }
        if (arrayList.size() != 0) {
            z = m18888c(arrayList, bundle);
            bundle.putInt("has_circle_hole", z ? 1 : 0);
        } else {
            bundle.putInt("has_circle_hole", 0);
            z = false;
        }
        if (arrayList2.size() != 0) {
            z2 = m18887d(arrayList2, bundle);
            bundle.putInt("has_polygon_hole", z2 ? 1 : 0);
        } else {
            bundle.putInt("has_polygon_hole", 0);
            z2 = false;
        }
        return z || z2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static void m18889c(int i, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putFloat("red", ((i >> 16) & 255) / 255.0f);
        bundle2.putFloat("green", ((i >> 8) & 255) / 255.0f);
        bundle2.putFloat("blue", (i & 255) / 255.0f);
        bundle2.putFloat("alpha", (i >>> 24) / 255.0f);
        bundle.putBundle("m_sideFaceColor", bundle2);
    }

    /* renamed from: c */
    private static boolean m18888c(List<CircleHoleOptions> list, Bundle bundle) {
        int size = list.size();
        double[] dArr = new double[size];
        double[] dArr2 = new double[size];
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            LatLng holeCenter = list.get(i).getHoleCenter();
            int holeRadius = list.get(i).getHoleRadius();
            if (holeCenter == null || holeRadius <= 0) {
                return false;
            }
            GeoPoint ll2mc = CoordUtil.ll2mc(holeCenter);
            dArr[i] = ll2mc.getLongitudeE6();
            dArr2[i] = ll2mc.getLatitudeE6();
            iArr[i] = holeRadius;
        }
        bundle.putDoubleArray("circle_hole_x_array", dArr);
        bundle.putDoubleArray("circle_hole_y_array", dArr2);
        bundle.putIntArray("circle_hole_radius_array", iArr);
        return true;
    }

    /* renamed from: d */
    private static boolean m18887d(List<PolygonHoleOptions> list, Bundle bundle) {
        int size = list.size();
        int[] iArr = new int[size];
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            List<LatLng> holePoints = list.get(i).getHolePoints();
            if (holePoints == null) {
                return false;
            }
            arrayList.addAll(holePoints);
            iArr[i] = holePoints.size();
        }
        int size2 = arrayList.size();
        if (size2 == 0) {
            return false;
        }
        bundle.putIntArray("polygon_hole_count_array", iArr);
        double[] dArr = new double[size2];
        double[] dArr2 = new double[size2];
        for (int i2 = 0; i2 < size2; i2++) {
            GeoPoint ll2mc = CoordUtil.ll2mc((LatLng) arrayList.get(i2));
            dArr[i2] = ll2mc.getLongitudeE6();
            dArr2[i2] = ll2mc.getLatitudeE6();
        }
        bundle.putDoubleArray("polygon_hole_x_array", dArr);
        bundle.putDoubleArray("polygon_hole_y_array", dArr2);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public Bundle mo18868a() {
        Bundle bundle = new Bundle();
        bundle.putString("id", this.f6297F);
        bundle.putInt("type", this.type.ordinal());
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public Bundle mo18867a(Bundle bundle) {
        bundle.putString("id", this.f6297F);
        bundle.putInt("type", this.type.ordinal());
        bundle.putInt("visibility", this.f6299H ? 1 : 0);
        bundle.putInt("z_index", this.f6298G);
        return bundle;
    }

    public Bundle getExtraInfo() {
        return this.f6300I;
    }

    public LatLngBounds getOverlayLatLngBounds() {
        return this.listener.mo18798a(this);
    }

    public int getZIndex() {
        return this.f6298G;
    }

    public boolean isRemoved() {
        return this.listener.mo18795d(this);
    }

    public boolean isVisible() {
        return this.f6299H;
    }

    public void remove() {
        this.listener.mo18797b(this);
    }

    public void setExtraInfo(Bundle bundle) {
        this.f6300I = bundle;
    }

    public void setVisible(boolean z) {
        this.f6299H = z;
        this.listener.mo18796c(this);
    }

    public void setZIndex(int i) {
        this.f6298G = i;
        this.listener.mo18796c(this);
    }
}
