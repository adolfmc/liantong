package com.baidu.mapapi.search.route;

import com.baidu.mapapi.model.LatLng;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class IndoorPlanNode {

    /* renamed from: a */
    private LatLng f6832a;

    /* renamed from: b */
    private String f6833b;

    public IndoorPlanNode(LatLng latLng, String str) {
        this.f6832a = null;
        this.f6833b = null;
        this.f6832a = latLng;
        this.f6833b = str;
    }

    public LatLng getLocation() {
        return this.f6832a;
    }

    public String getFloor() {
        return this.f6833b;
    }
}
