package com.baidu.mapapi.navi;

import com.baidu.mapapi.model.LatLng;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WayPointInfo {

    /* renamed from: a */
    private String f6633a;

    /* renamed from: b */
    private LatLng f6634b;

    public WayPointInfo setLatLng(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalStateException("BDMapSDKException: The latitude and longitude of the waypoint cannot be null");
        }
        this.f6634b = latLng;
        return this;
    }

    public WayPointInfo setWayPointName(String str) {
        this.f6633a = str;
        return this;
    }

    public LatLng getLatLng() {
        return this.f6634b;
    }

    public String getWayPointName() {
        return this.f6633a;
    }
}
