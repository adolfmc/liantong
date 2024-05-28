package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapsdkplatform.comapi.map.EnumC2933i;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class Arc extends Overlay {

    /* renamed from: f */
    private static final String f5886f = "Arc";

    /* renamed from: a */
    int f5887a;

    /* renamed from: b */
    int f5888b;

    /* renamed from: c */
    LatLng f5889c;

    /* renamed from: d */
    LatLng f5890d;

    /* renamed from: e */
    LatLng f5891e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Arc() {
        this.type = EnumC2933i.arc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.mapapi.map.Overlay
    /* renamed from: a */
    public Bundle mo18867a(Bundle bundle) {
        super.mo18867a(bundle);
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
        arrayList.add(this.f5889c);
        arrayList.add(this.f5890d);
        arrayList.add(this.f5891e);
        GeoPoint ll2mc = CoordUtil.ll2mc((LatLng) arrayList.get(0));
        bundle.putDouble("location_x", ll2mc.getLongitudeE6());
        bundle.putDouble("location_y", ll2mc.getLatitudeE6());
        bundle.putInt("width", this.f5888b);
        Overlay.m18892a(arrayList, bundle);
        Overlay.m18893a(this.f5887a, bundle);
        return bundle;
    }

    public int getColor() {
        return this.f5887a;
    }

    public LatLng getEndPoint() {
        return this.f5891e;
    }

    public LatLng getMiddlePoint() {
        return this.f5890d;
    }

    public LatLng getStartPoint() {
        return this.f5889c;
    }

    public int getWidth() {
        return this.f5888b;
    }

    public void setColor(int i) {
        this.f5887a = i;
        this.listener.mo18796c(this);
    }

    public void setPoints(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        if (latLng == null || latLng2 == null || latLng3 == null) {
            throw new IllegalArgumentException("BDMapSDKException:start and middle and end points can not be null");
        }
        if (latLng == latLng2 || latLng == latLng3 || latLng2 == latLng3) {
            throw new IllegalArgumentException("BDMapSDKException: start and middle and end points can not be same");
        }
        this.f5889c = latLng;
        this.f5890d = latLng2;
        this.f5891e = latLng3;
        this.listener.mo18796c(this);
    }

    public void setWidth(int i) {
        if (i > 0) {
            this.f5888b = i;
            this.listener.mo18796c(this);
        }
    }
}
