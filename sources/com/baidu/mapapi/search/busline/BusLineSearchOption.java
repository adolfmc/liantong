package com.baidu.mapapi.search.busline;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BusLineSearchOption {
    public String mUid = null;
    public String mCity = null;

    public BusLineSearchOption city(String str) {
        this.mCity = str;
        return this;
    }

    public BusLineSearchOption uid(String str) {
        this.mUid = str;
        return this;
    }
}
