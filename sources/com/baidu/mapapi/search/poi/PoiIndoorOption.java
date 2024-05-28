package com.baidu.mapapi.search.poi;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PoiIndoorOption {
    public String bid;
    public String floor;

    /* renamed from: wd */
    public String f6780wd;
    public int currentPage = 0;
    public int pageSize = 10;

    public PoiIndoorOption poiIndoorBid(String str) {
        this.bid = str;
        return this;
    }

    public PoiIndoorOption poiIndoorWd(String str) {
        this.f6780wd = str;
        return this;
    }

    public PoiIndoorOption poiCurrentPage(int i) {
        this.currentPage = i;
        return this;
    }

    public PoiIndoorOption poiPageSize(int i) {
        this.pageSize = i;
        return this;
    }

    public PoiIndoorOption poiFloor(String str) {
        this.floor = str;
        return this;
    }
}
