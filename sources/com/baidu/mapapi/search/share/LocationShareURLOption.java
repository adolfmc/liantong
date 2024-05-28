package com.baidu.mapapi.search.share;

import com.baidu.mapapi.model.LatLng;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class LocationShareURLOption {
    public LatLng mLocation = null;
    public String mName = null;
    public String mSnippet = null;

    public LocationShareURLOption location(LatLng latLng) {
        this.mLocation = latLng;
        return this;
    }

    public LocationShareURLOption name(String str) {
        this.mName = str;
        return this;
    }

    public LocationShareURLOption snippet(String str) {
        this.mSnippet = str;
        return this;
    }
}
