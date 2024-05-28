package com.baidu.mapapi.search.recommendstop;

import com.baidu.mapapi.model.LatLng;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RecommendStopSearchOption {
    public LatLng mLocation;

    public RecommendStopSearchOption location(LatLng latLng) {
        this.mLocation = latLng;
        return this;
    }

    public LatLng getLocation() {
        return this.mLocation;
    }
}
