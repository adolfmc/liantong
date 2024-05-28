package com.baidu.mapapi.search.geocode;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GeoCodeOption {
    public String mCity = null;
    public String mAddress = null;

    public GeoCodeOption city(String str) {
        this.mCity = str;
        return this;
    }

    public GeoCodeOption address(String str) {
        this.mAddress = str;
        return this;
    }

    public String getAddress() {
        return this.mAddress;
    }
}
