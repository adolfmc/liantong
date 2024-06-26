package com.baidu.mapapi.search.poi;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.base.LanguageType;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PoiNearbySearchOption {
    public String mKeyword = null;
    public LatLng mLocation = null;
    public int mRadius = -1;
    public int mPageNum = 0;
    public int mPageCapacity = 10;
    public PoiSortType sortType = PoiSortType.comprehensive;
    public String mTag = "";
    public int mScope = 1;
    public boolean mRadiusLimit = false;
    public PoiFilter mPoiFilter = null;
    public boolean mIsExtendAdcode = true;
    public LanguageType mLanguageType = LanguageType.LanguageTypeChinese;

    public PoiNearbySearchOption keyword(String str) {
        this.mKeyword = str;
        return this;
    }

    public PoiNearbySearchOption location(LatLng latLng) {
        this.mLocation = latLng;
        return this;
    }

    public PoiNearbySearchOption sortType(PoiSortType poiSortType) {
        if (poiSortType != null) {
            this.sortType = poiSortType;
        }
        return this;
    }

    public PoiNearbySearchOption radius(int i) {
        this.mRadius = i;
        return this;
    }

    public PoiNearbySearchOption radiusLimit(boolean z) {
        this.mRadiusLimit = z;
        return this;
    }

    public PoiNearbySearchOption pageNum(int i) {
        this.mPageNum = i;
        return this;
    }

    public PoiNearbySearchOption pageCapacity(int i) {
        this.mPageCapacity = i;
        return this;
    }

    public PoiNearbySearchOption tag(String str) {
        this.mTag = str;
        return this;
    }

    public PoiNearbySearchOption scope(int i) {
        this.mScope = i;
        return this;
    }

    public PoiNearbySearchOption poiFilter(PoiFilter poiFilter) {
        this.mPoiFilter = poiFilter;
        return this;
    }

    public PoiNearbySearchOption language(LanguageType languageType) {
        this.mLanguageType = languageType;
        return this;
    }

    public PoiNearbySearchOption extendAdcode(boolean z) {
        this.mIsExtendAdcode = z;
        return this;
    }

    public boolean isExtendAdcode() {
        return this.mIsExtendAdcode;
    }
}
