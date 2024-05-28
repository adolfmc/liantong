package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapsdkplatform.comapi.map.EnumC2933i;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class Polygon extends Overlay {

    /* renamed from: a */
    Stroke f6301a;

    /* renamed from: b */
    int f6302b;

    /* renamed from: c */
    List<LatLng> f6303c;

    /* renamed from: d */
    List<HoleOptions> f6304d;

    /* renamed from: e */
    HoleOptions f6305e;

    /* renamed from: f */
    boolean f6306f;

    /* renamed from: g */
    String f6307g;

    /* renamed from: h */
    EncodePointType f6308h;

    /* renamed from: i */
    int f6309i = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Polygon() {
        this.type = EnumC2933i.polygon;
    }

    /* renamed from: b */
    private void m18886b(Bundle bundle) {
        BitmapDescriptor fromAsset = BitmapDescriptorFactory.fromAsset(this.f6309i == 1 ? "CircleDashTexture.png" : "lineDashTexture.png");
        if (fromAsset != null) {
            bundle.putBundle("image_info", fromAsset.m18976b());
        }
    }

    /* renamed from: c */
    private void m18885c(List<HoleOptions> list, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        boolean m18890b = Overlay.m18890b(list, bundle2);
        bundle.putInt("has_holes", m18890b ? 1 : 0);
        if (m18890b) {
            bundle.putBundle("holes", bundle2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.mapapi.map.Overlay
    /* renamed from: a */
    public Bundle mo18867a(Bundle bundle) {
        List<HoleOptions> arrayList;
        super.mo18867a(bundle);
        if (this.f6306f) {
            bundle.putInt("has_dotted_stroke", 1);
            m18886b(bundle);
        } else {
            bundle.putInt("has_dotted_stroke", 0);
        }
        String str = this.f6307g;
        if (str == null || str.length() <= 0 || this.f6308h == null) {
            List<LatLng> list = this.f6303c;
            if (list != null) {
                GeoPoint ll2mc = CoordUtil.ll2mc(list.get(0));
                bundle.putDouble("location_x", ll2mc.getLongitudeE6());
                bundle.putDouble("location_y", ll2mc.getLatitudeE6());
                Overlay.m18892a(this.f6303c, bundle);
                if (this.f6306f) {
                    bundle.putDouble("dotted_stroke_location_x", ll2mc.getLongitudeE6());
                    bundle.putDouble("dotted_stroke_location_y", ll2mc.getLatitudeE6());
                }
            }
        } else {
            bundle.putString("encodedPoints", this.f6307g);
            bundle.putInt("encodePointType", this.f6308h.ordinal());
        }
        Overlay.m18893a(this.f6302b, bundle);
        if (this.f6301a == null) {
            bundle.putInt("has_stroke", 0);
        } else {
            bundle.putInt("has_stroke", 1);
            bundle.putBundle("stroke", this.f6301a.m18875a(new Bundle()));
        }
        List<HoleOptions> list2 = this.f6304d;
        if (list2 != null && list2.size() != 0) {
            arrayList = this.f6304d;
        } else if (this.f6305e == null) {
            bundle.putInt("has_holes", 0);
            return bundle;
        } else {
            arrayList = new ArrayList<>();
            arrayList.add(this.f6305e);
        }
        m18885c(arrayList, bundle);
        return bundle;
    }

    public String getEncodedPoint() {
        return this.f6307g;
    }

    public int getFillColor() {
        return this.f6302b;
    }

    public HoleOptions getHoleOption() {
        return this.f6305e;
    }

    public List<HoleOptions> getHoleOptions() {
        return this.f6304d;
    }

    public EncodePointType getPointType() {
        return this.f6308h;
    }

    public List<LatLng> getPoints() {
        return this.f6303c;
    }

    public Stroke getStroke() {
        return this.f6301a;
    }

    public void setEncodeInfo(String str, EncodePointType encodePointType) {
        this.f6307g = str;
        this.f6308h = encodePointType;
        this.listener.mo18796c(this);
    }

    public void setFillColor(int i) {
        this.f6302b = i;
        this.listener.mo18796c(this);
    }

    public void setHoleOption(HoleOptions holeOptions) {
        this.f6305e = holeOptions;
        this.f6304d = null;
        this.listener.mo18796c(this);
    }

    public void setHoleOptions(List<HoleOptions> list) {
        this.f6304d = list;
        this.f6305e = null;
        this.listener.mo18796c(this);
    }

    public void setPoints(List<LatLng> list) {
        if (list == null) {
            throw new IllegalArgumentException("BDMapSDKException: points list can not be null");
        }
        if (list.size() <= 2) {
            throw new IllegalArgumentException("BDMapSDKException: points count can not less than three");
        }
        if (list.contains(null)) {
            throw new IllegalArgumentException("BDMapSDKException: points list can not contains null");
        }
        int i = 0;
        while (i < list.size()) {
            int i2 = i + 1;
            for (int i3 = i2; i3 < list.size(); i3++) {
                if (list.get(i) == list.get(i3)) {
                    throw new IllegalArgumentException("BDMapSDKException: points list can not has same points");
                }
            }
            i = i2;
        }
        this.f6303c = list;
        this.listener.mo18796c(this);
    }

    public void setStroke(Stroke stroke) {
        this.f6301a = stroke;
        this.listener.mo18796c(this);
    }
}
