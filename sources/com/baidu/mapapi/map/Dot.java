package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapsdkplatform.comapi.map.EnumC2933i;
import com.baidu.platform.comapi.basestruct.GeoPoint;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class Dot extends Overlay {

    /* renamed from: a */
    LatLng f6017a;

    /* renamed from: b */
    int f6018b;

    /* renamed from: c */
    int f6019c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Dot() {
        this.type = EnumC2933i.dot;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.mapapi.map.Overlay
    /* renamed from: a */
    public Bundle mo18867a(Bundle bundle) {
        super.mo18867a(bundle);
        GeoPoint ll2mc = CoordUtil.ll2mc(this.f6017a);
        bundle.putDouble("location_x", ll2mc.getLongitudeE6());
        bundle.putDouble("location_y", ll2mc.getLatitudeE6());
        bundle.putInt("radius", this.f6019c);
        Overlay.m18893a(this.f6018b, bundle);
        return bundle;
    }

    public LatLng getCenter() {
        return this.f6017a;
    }

    public int getColor() {
        return this.f6018b;
    }

    public int getRadius() {
        return this.f6019c;
    }

    public void setCenter(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: dot center can not be null");
        }
        this.f6017a = latLng;
        this.listener.mo18796c(this);
    }

    public void setColor(int i) {
        this.f6018b = i;
        this.listener.mo18796c(this);
    }

    public void setRadius(int i) {
        if (i > 0) {
            this.f6019c = i;
            this.listener.mo18796c(this);
        }
    }
}
