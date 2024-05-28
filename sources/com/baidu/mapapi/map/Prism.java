package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.BuildingInfo;
import com.baidu.mapsdkplatform.comapi.map.EnumC2933i;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class Prism extends Overlay {

    /* renamed from: a */
    float f6366a;

    /* renamed from: b */
    List<LatLng> f6367b;

    /* renamed from: c */
    int f6368c = -16777216;

    /* renamed from: d */
    int f6369d = -16711936;

    /* renamed from: e */
    BuildingInfo f6370e;

    /* renamed from: f */
    boolean f6371f;

    /* renamed from: g */
    BitmapDescriptor f6372g;

    /* renamed from: h */
    int f6373h;

    /* renamed from: i */
    boolean f6374i;

    public Prism() {
        this.type = EnumC2933i.prism;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.mapapi.map.Overlay
    /* renamed from: a */
    public Bundle mo18867a(Bundle bundle) {
        super.mo18867a(bundle);
        Overlay.m18891b(this.f6368c, bundle);
        Overlay.m18889c(this.f6369d, bundle);
        BitmapDescriptor bitmapDescriptor = this.f6372g;
        if (bitmapDescriptor != null) {
            bundle.putBundle("image_info", bitmapDescriptor.m18976b());
        }
        BuildingInfo buildingInfo = this.f6370e;
        if (buildingInfo != null) {
            bundle.putDouble("m_height", buildingInfo.getHeight());
            bundle.putString("encodedPoints", this.f6370e.getGeom());
            bundle.putInt("encodePointType", EncodePointType.BUILDINGINFO.ordinal());
            bundle.putInt("m_showLevel", this.f6373h);
            bundle.putInt("m_isAnimation", this.f6374i ? 1 : 0);
        } else {
            List<LatLng> list = this.f6367b;
            if (list != null) {
                GeoPoint ll2mc = CoordUtil.ll2mc(list.get(0));
                bundle.putDouble("location_x", ll2mc.getLongitudeE6());
                bundle.putDouble("location_y", ll2mc.getLatitudeE6());
                Overlay.m18892a(this.f6367b, bundle);
                bundle.putDouble("m_height", this.f6366a);
            }
        }
        bundle.putInt("m_isBuilding", this.f6370e != null ? 1 : 0);
        return bundle;
    }

    public BuildingInfo getBuildingInfo() {
        return this.f6370e;
    }

    public BitmapDescriptor getCustomSideImage() {
        return this.f6372g;
    }

    public float getHeight() {
        return this.f6366a;
    }

    public List<LatLng> getPoints() {
        return this.f6367b;
    }

    public int getShowLevel() {
        return this.f6373h;
    }

    public int getSideFaceColor() {
        return this.f6369d;
    }

    public int getTopFaceColor() {
        return this.f6368c;
    }

    public boolean isAnimation() {
        return this.f6374i;
    }

    public void setAnimation(boolean z) {
        this.f6374i = z;
    }

    public void setBuildingInfo(BuildingInfo buildingInfo) {
        this.f6370e = buildingInfo;
        this.listener.mo18796c(this);
    }

    public void setCustomSideImage(BitmapDescriptor bitmapDescriptor) {
        this.f6372g = bitmapDescriptor;
        this.listener.mo18796c(this);
    }

    public void setHeight(float f) {
        this.f6366a = f;
        this.listener.mo18796c(this);
    }

    public void setPoints(List<LatLng> list) {
        if (list == null) {
            throw new IllegalArgumentException("BDMapSDKException: points list can not be null");
        }
        if (list.size() <= 3) {
            throw new IllegalArgumentException("BDMapSDKException: points count can not less than four");
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
        this.f6367b = list;
        this.listener.mo18796c(this);
    }

    public void setShowLevel(int i) {
        this.f6373h = i;
    }

    public void setSideFaceColor(int i) {
        this.f6369d = i;
        this.listener.mo18796c(this);
    }

    public void setTopFaceColor(int i) {
        this.f6368c = i;
        this.listener.mo18796c(this);
    }
}
