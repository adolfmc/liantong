package com.baidu.mapapi.map;

import com.baidu.mapapi.model.LatLng;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class MultiPointItem {

    /* renamed from: a */
    private LatLng f6281a;

    /* renamed from: b */
    private String f6282b;

    public MultiPointItem(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: MultiPointItem point can not be null");
        }
        this.f6281a = latLng;
    }

    public LatLng getPoint() {
        return this.f6281a;
    }

    public String getTitle() {
        return this.f6282b;
    }

    public void setTitle(String str) {
        this.f6282b = str;
    }
}
