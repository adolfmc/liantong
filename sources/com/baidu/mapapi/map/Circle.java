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
public final class Circle extends Overlay {

    /* renamed from: a */
    LatLng f5994a;

    /* renamed from: b */
    int f5995b;

    /* renamed from: c */
    int f5996c;

    /* renamed from: d */
    Stroke f5997d;

    /* renamed from: e */
    boolean f5998e;

    /* renamed from: f */
    int f5999f = 0;

    /* renamed from: g */
    List<HoleOptions> f6000g;

    /* renamed from: h */
    HoleOptions f6001h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Circle() {
        this.type = EnumC2933i.circle;
    }

    /* renamed from: b */
    private void m18975b(Bundle bundle) {
        BitmapDescriptor fromAsset = BitmapDescriptorFactory.fromAsset(this.f5999f == 1 ? "CircleDashTexture.png" : "lineDashTexture.png");
        if (fromAsset != null) {
            bundle.putBundle("image_info", fromAsset.m18976b());
        }
    }

    /* renamed from: c */
    private void m18974c(List<HoleOptions> list, Bundle bundle) {
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
        GeoPoint ll2mc = CoordUtil.ll2mc(this.f5994a);
        bundle.putDouble("location_x", ll2mc.getLongitudeE6());
        bundle.putDouble("location_y", ll2mc.getLatitudeE6());
        if (this.f5998e) {
            bundle.putDouble("dotted_stroke_location_x", ll2mc.getLongitudeE6());
            bundle.putDouble("dotted_stroke_location_y", ll2mc.getLatitudeE6());
            bundle.putInt("has_dotted_stroke", 1);
            m18975b(bundle);
        } else {
            bundle.putInt("has_dotted_stroke", 0);
        }
        bundle.putInt("radius", CoordUtil.getMCDistanceByOneLatLngAndRadius(this.f5994a, this.f5996c));
        Overlay.m18893a(this.f5995b, bundle);
        if (this.f5997d == null) {
            bundle.putInt("has_stroke", 0);
        } else {
            bundle.putInt("has_stroke", 1);
            bundle.putBundle("stroke", this.f5997d.m18875a(new Bundle()));
        }
        List<HoleOptions> list = this.f6000g;
        if (list != null && list.size() != 0) {
            arrayList = this.f6000g;
        } else if (this.f6001h == null) {
            bundle.putInt("has_holes", 0);
            return bundle;
        } else {
            arrayList = new ArrayList<>();
            arrayList.add(this.f6001h);
        }
        m18974c(arrayList, bundle);
        return bundle;
    }

    public LatLng getCenter() {
        return this.f5994a;
    }

    public int getDottedStrokeType() {
        return this.f5999f;
    }

    public int getFillColor() {
        return this.f5995b;
    }

    public HoleOptions getHoleOption() {
        return this.f6001h;
    }

    public List<HoleOptions> getHoleOptions() {
        return this.f6000g;
    }

    public int getRadius() {
        return this.f5996c;
    }

    public Stroke getStroke() {
        return this.f5997d;
    }

    public boolean isDottedStroke() {
        return this.f5998e;
    }

    public void setCenter(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: circle center can not be null");
        }
        this.f5994a = latLng;
        this.listener.mo18796c(this);
    }

    public void setDottedStroke(boolean z) {
        this.f5998e = z;
        this.listener.mo18796c(this);
    }

    public void setDottedStrokeType(CircleDottedStrokeType circleDottedStrokeType) {
        this.f5999f = circleDottedStrokeType.ordinal();
        this.listener.mo18796c(this);
    }

    public void setFillColor(int i) {
        this.f5995b = i;
        this.listener.mo18796c(this);
    }

    public void setHoleOption(HoleOptions holeOptions) {
        this.f6001h = holeOptions;
        this.f6000g = null;
        this.listener.mo18796c(this);
    }

    public void setHoleOptions(List<HoleOptions> list) {
        this.f6000g = list;
        this.f6001h = null;
        this.listener.mo18796c(this);
    }

    public void setRadius(int i) {
        this.f5996c = i;
        this.listener.mo18796c(this);
    }

    public void setStroke(Stroke stroke) {
        this.f5997d = stroke;
        this.listener.mo18796c(this);
    }
}
