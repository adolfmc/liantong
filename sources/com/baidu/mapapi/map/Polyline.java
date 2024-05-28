package com.baidu.mapapi.map;

import android.os.Bundle;
import android.util.Log;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapsdkplatform.comapi.map.C2935k;
import com.baidu.mapsdkplatform.comapi.map.EnumC2933i;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class Polyline extends Overlay {

    /* renamed from: a */
    int f6323a;

    /* renamed from: b */
    List<LatLng> f6324b;

    /* renamed from: c */
    int[] f6325c;

    /* renamed from: d */
    int[] f6326d;

    /* renamed from: j */
    BitmapDescriptor f6332j;

    /* renamed from: k */
    List<BitmapDescriptor> f6333k;

    /* renamed from: e */
    int f6327e = 5;

    /* renamed from: f */
    boolean f6328f = false;

    /* renamed from: g */
    boolean f6329g = false;

    /* renamed from: h */
    boolean f6330h = true;

    /* renamed from: i */
    boolean f6331i = true;

    /* renamed from: l */
    int f6334l = 0;

    /* renamed from: m */
    boolean f6335m = true;

    /* renamed from: n */
    boolean f6336n = false;

    /* renamed from: o */
    boolean f6337o = false;

    /* renamed from: p */
    PolylineOptions.LineCapType f6338p = PolylineOptions.LineCapType.LineCapButt;

    /* renamed from: q */
    PolylineOptions.LineJoinType f6339q = PolylineOptions.LineJoinType.LineJoinRound;

    /* renamed from: r */
    PolylineOptions.LineDirectionCross180 f6340r = PolylineOptions.LineDirectionCross180.NONE;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Polyline() {
        this.type = EnumC2933i.polyline;
    }

    /* renamed from: a */
    private Bundle m18883a(boolean z, String str) {
        if (z) {
            String str2 = this.f6334l == 1 ? "CircleDashTexture.png" : "lineDashTexture.png";
            if (str != null) {
                str2 = str;
            }
            BitmapDescriptor fromAsset = BitmapDescriptorFactory.fromAsset(str2);
            if (fromAsset != null) {
                return fromAsset.m18976b();
            }
        }
        return this.f6332j.m18976b();
    }

    /* renamed from: a */
    private static void m18884a(List<LatLng> list, PolylineOptions.LineDirectionCross180 lineDirectionCross180, Bundle bundle) {
        int size = list.size();
        double[] dArr = new double[size];
        double[] dArr2 = new double[size];
        for (int i = 0; i < size; i++) {
            LatLng latLng = list.get(i);
            GeoPoint ll2mc = CoordUtil.ll2mc((lineDirectionCross180 != PolylineOptions.LineDirectionCross180.FROM_EAST_TO_WEST || latLng.longitude >= 0.0d) ? (lineDirectionCross180 != PolylineOptions.LineDirectionCross180.FROM_WEST_TO_EAST || latLng.longitude <= 0.0d) ? latLng : new LatLng(latLng.latitude, latLng.longitude - 360.0d) : new LatLng(latLng.latitude, latLng.longitude + 360.0d));
            dArr[i] = ll2mc.getLongitudeE6();
            dArr2[i] = ll2mc.getLatitudeE6();
        }
        bundle.putDoubleArray("x_array", dArr);
        bundle.putDoubleArray("y_array", dArr2);
    }

    /* renamed from: a */
    private static void m18882a(int[] iArr, Bundle bundle) {
        if (iArr == null || iArr.length <= 0) {
            return;
        }
        bundle.putIntArray("traffic_array", iArr);
    }

    /* renamed from: b */
    private Bundle m18881b(Bundle bundle) {
        int[] iArr = this.f6326d;
        if (iArr != null) {
            if (iArr.length != 0) {
                m18877d(iArr, bundle);
                m18884a(this.f6324b, this.f6340r, bundle);
                int[] iArr2 = new int[this.f6326d.length];
                for (int i = 0; i < iArr2.length; i++) {
                    iArr2[i] = i;
                }
                int size = this.f6324b.size();
                int[] iArr3 = this.f6326d;
                if (size == iArr3.length) {
                    iArr2[iArr3.length - 1] = iArr3.length - 2;
                }
                m18878c(iArr2, bundle);
                return bundle;
            }
            throw new IllegalStateException("BDMapSDKException: colors array size can not be Equal to zero");
        }
        throw new IllegalStateException("BDMapSDKException: colors array can not be null");
    }

    /* renamed from: b */
    private Bundle m18880b(boolean z, String str) {
        if (z) {
            Bundle bundle = new Bundle();
            bundle.putInt("total", 1);
            String str2 = this.f6334l == 1 ? "CircleDashTexture.png" : "lineDashTexture.png";
            if (str == null) {
                str = str2;
            }
            BitmapDescriptor fromAsset = BitmapDescriptorFactory.fromAsset(str);
            if (fromAsset != null) {
                bundle.putBundle("texture_0", fromAsset.m18976b());
            }
            return bundle;
        }
        Bundle bundle2 = new Bundle();
        int i = 0;
        for (int i2 = 0; i2 < this.f6333k.size(); i2++) {
            if (this.f6333k.get(i2) != null) {
                bundle2.putBundle("texture_" + String.valueOf(i), this.f6333k.get(i2).m18976b());
                i++;
            }
        }
        bundle2.putInt("total", i);
        return bundle2;
    }

    /* renamed from: b */
    private static void m18879b(int[] iArr, Bundle bundle) {
        if (iArr == null || iArr.length <= 0) {
            return;
        }
        bundle.putIntArray("color_array", iArr);
        bundle.putInt("total", 1);
    }

    /* renamed from: c */
    private static void m18878c(int[] iArr, Bundle bundle) {
        if (iArr == null || iArr.length <= 0) {
            return;
        }
        bundle.putIntArray("color_indexs", iArr);
    }

    /* renamed from: d */
    private static void m18877d(int[] iArr, Bundle bundle) {
        if (iArr == null || iArr.length <= 0) {
            return;
        }
        bundle.putIntArray("color_array", iArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.mapapi.map.Overlay
    /* renamed from: a */
    public Bundle mo18867a(Bundle bundle) {
        String str;
        Bundle m18883a;
        super.mo18867a(bundle);
        List<LatLng> list = this.f6324b;
        if (list == null || list.size() < 2) {
            throw new IllegalStateException("BDMapSDKException: when you add Polyline, you must at least supply 2 points");
        }
        GeoPoint ll2mc = CoordUtil.ll2mc(this.f6324b.get(0));
        bundle.putDouble("location_x", ll2mc.getLongitudeE6());
        bundle.putDouble("location_y", ll2mc.getLatitudeE6());
        bundle.putInt("width", this.f6327e);
        if (this.f6337o) {
            return m18881b(bundle);
        }
        int i = 1;
        if (this.f6336n && this.f6324b.size() == 2) {
            this.f6324b = C2935k.m18229b(this.f6324b.get(0), this.f6324b.get(1));
        }
        m18884a(this.f6324b, this.f6340r, bundle);
        Overlay.m18893a(this.f6323a, bundle);
        m18882a(this.f6325c, bundle);
        m18879b(this.f6326d, bundle);
        int[] iArr = this.f6325c;
        if (iArr != null && iArr.length > 0 && iArr.length > this.f6324b.size() - 1) {
            Log.e("baidumapsdk", "the size of textureIndexs is larger than the size of points");
        }
        bundle.putInt("dotline", this.f6328f ? 1 : 0);
        bundle.putInt("focus", this.f6329g ? 1 : 0);
        bundle.putInt("isClickable", this.f6331i ? 1 : 0);
        if (this.f6336n) {
            this.f6335m = false;
            this.f6337o = false;
        }
        bundle.putInt("isThined", this.f6335m ? 1 : 0);
        bundle.putInt("isGradient", this.f6337o ? 1 : 0);
        bundle.putInt("lineJoinType", this.f6339q.ordinal());
        bundle.putInt("lineCapType", this.f6338p.ordinal());
        bundle.putInt("lineDirectionCross180", this.f6340r.ordinal());
        try {
            if (this.f6332j != null) {
                bundle.putInt("custom", 1);
                bundle.putBundle("image_info", m18883a(false, (String) null));
            } else {
                if (this.f6328f) {
                    bundle.putBundle("image_info", m18883a(true, (String) null));
                    bundle.putInt("dotted_line_type", this.f6334l);
                } else {
                    bundle.putBundle("image_info", m18883a(true, "line_texture.png"));
                }
                bundle.putInt("custom", 0);
            }
            if (this.f6333k != null) {
                bundle.putInt("customlist", 1);
                bundle.putBundle("image_info_list", m18880b(false, (String) null));
            } else {
                String str2 = this.f6328f ? null : "line_texture.png";
                if (this.f6326d != null && this.f6326d.length > 0) {
                    str = "image_info_list";
                    m18883a = m18880b(true, str2);
                } else if (this.f6332j != null) {
                    bundle.putBundle("image_info", this.f6332j.m18976b());
                    bundle.putInt("dotline", 0);
                    bundle.putInt("customlist", 0);
                } else {
                    str = "image_info";
                    m18883a = m18883a(true, str2);
                }
                bundle.putBundle(str, m18883a);
                bundle.putInt("customlist", 0);
            }
            if (!this.f6330h) {
                i = 0;
            }
            bundle.putInt("keep", i);
        } catch (Exception unused) {
            Log.e("baidumapsdk", "load texture resource failed!");
            bundle.putInt("dotline", 0);
        }
        return bundle;
    }

    public int getColor() {
        return this.f6323a;
    }

    public int[] getColorList() {
        return this.f6326d;
    }

    public int getDottedLineType() {
        return this.f6334l;
    }

    public PolylineOptions.LineCapType getLineCapType() {
        return this.f6338p;
    }

    public PolylineOptions.LineDirectionCross180 getLineDirectionCross180() {
        return this.f6340r;
    }

    public PolylineOptions.LineJoinType getLineJoinType() {
        return this.f6339q;
    }

    public List<LatLng> getPoints() {
        return this.f6324b;
    }

    public BitmapDescriptor getTexture() {
        return this.f6332j;
    }

    public int getWidth() {
        return this.f6327e;
    }

    public boolean isClickable() {
        return this.f6331i;
    }

    public boolean isDottedLine() {
        return this.f6328f;
    }

    public boolean isFocus() {
        return this.f6329g;
    }

    public boolean isGeodesic() {
        return this.f6336n;
    }

    public boolean isGradient() {
        return this.f6337o;
    }

    public boolean isIsKeepScale() {
        return this.f6330h;
    }

    public boolean isThined() {
        return this.f6335m;
    }

    public void setClickable(boolean z) {
        this.f6331i = z;
        this.listener.mo18796c(this);
    }

    public void setColor(int i) {
        this.f6323a = i;
        this.listener.mo18796c(this);
    }

    public void setColorList(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("BDMapSDKException: colorList can not empty");
        }
        this.f6326d = iArr;
    }

    public void setDottedLine(boolean z) {
        this.f6328f = z;
        this.listener.mo18796c(this);
    }

    public void setDottedLineType(PolylineDottedLineType polylineDottedLineType) {
        this.f6334l = polylineDottedLineType.ordinal();
        this.listener.mo18796c(this);
    }

    public void setFocus(boolean z) {
        this.f6329g = z;
        this.listener.mo18796c(this);
    }

    public void setGeodesic(boolean z) {
        this.f6336n = z;
        this.listener.mo18796c(this);
    }

    public void setGradient(boolean z) {
        this.f6337o = z;
        this.listener.mo18796c(this);
    }

    public void setIndexs(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("BDMapSDKException: indexList can not empty");
        }
        this.f6325c = iArr;
    }

    public void setIsKeepScale(boolean z) {
        this.f6330h = z;
    }

    public void setLineCapType(PolylineOptions.LineCapType lineCapType) {
        this.f6338p = lineCapType;
        this.listener.mo18796c(this);
    }

    public void setLineDirectionCross180(PolylineOptions.LineDirectionCross180 lineDirectionCross180) {
        this.f6340r = lineDirectionCross180;
    }

    public void setLineJoinType(PolylineOptions.LineJoinType lineJoinType) {
        this.f6339q = lineJoinType;
        this.listener.mo18796c(this);
    }

    public void setPoints(List<LatLng> list) {
        if (list == null) {
            throw new IllegalArgumentException("BDMapSDKException: points list can not be null");
        }
        if (list.size() < 2) {
            throw new IllegalArgumentException("BDMapSDKException: points count can not less than 2 or more than 10000");
        }
        if (list.contains(null)) {
            throw new IllegalArgumentException("BDMapSDKException: points list can not contains null");
        }
        this.f6324b = list;
        this.listener.mo18796c(this);
    }

    public void setTexture(BitmapDescriptor bitmapDescriptor) {
        this.f6332j = bitmapDescriptor;
        this.listener.mo18796c(this);
    }

    public void setTextureList(List<BitmapDescriptor> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("BDMapSDKException: textureList can not empty");
        }
        this.f6333k = list;
    }

    public void setThined(boolean z) {
        this.f6335m = z;
        this.listener.mo18796c(this);
    }

    public void setWidth(int i) {
        if (i > 0) {
            this.f6327e = i;
            this.listener.mo18796c(this);
        }
    }
}
