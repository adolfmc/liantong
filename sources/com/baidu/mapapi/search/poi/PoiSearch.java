package com.baidu.mapapi.search.poi;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.search.core.SearcherInternal;
import com.baidu.platform.core.p160d.IPoiSearch;
import com.baidu.platform.core.p160d.PoiSearchImp;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PoiSearch extends SearcherInternal {

    /* renamed from: b */
    private boolean f6791b = false;

    /* renamed from: a */
    private IPoiSearch f6790a = new PoiSearchImp();

    PoiSearch() {
    }

    public static PoiSearch newInstance() {
        BMapManager.init();
        return new PoiSearch();
    }

    public void setOnGetPoiSearchResultListener(OnGetPoiSearchResultListener onGetPoiSearchResultListener) {
        IPoiSearch iPoiSearch = this.f6790a;
        if (iPoiSearch == null) {
            throw new IllegalStateException("BDMapSDKException: searcher is null, please call newInstance first.");
        }
        if (onGetPoiSearchResultListener == null) {
            throw new IllegalArgumentException("BDMapSDKException: listener can not be null");
        }
        iPoiSearch.mo17583a(onGetPoiSearchResultListener);
    }

    public boolean searchInCity(PoiCitySearchOption poiCitySearchOption) {
        if (this.f6790a == null) {
            throw new IllegalStateException("BDMapSDKException: searcher is null, please call newInstance first.");
        }
        if (poiCitySearchOption == null || poiCitySearchOption.mCity == null || poiCitySearchOption.mKeyword == null) {
            throw new IllegalArgumentException("BDMapSDKException: option or city or keyworld can not be null");
        }
        return this.f6790a.mo17581a(poiCitySearchOption);
    }

    public boolean searchNearby(PoiNearbySearchOption poiNearbySearchOption) {
        if (this.f6790a == null) {
            throw new IllegalStateException("BDMapSDKException: searcher is null, please call newInstance first.");
        }
        if (poiNearbySearchOption == null || poiNearbySearchOption.mLocation == null || poiNearbySearchOption.mKeyword == null) {
            throw new IllegalArgumentException("BDMapSDKException: option or location or keyworld can not be null");
        }
        if (poiNearbySearchOption.mRadius <= 0) {
            return false;
        }
        return this.f6790a.mo17578a(poiNearbySearchOption);
    }

    public boolean searchInBound(PoiBoundSearchOption poiBoundSearchOption) {
        if (this.f6790a == null) {
            throw new IllegalStateException("BDMapSDKException: searcher is null, please call newInstance first.");
        }
        if (poiBoundSearchOption == null || poiBoundSearchOption.mBound == null || poiBoundSearchOption.mKeyword == null) {
            throw new IllegalArgumentException("BDMapSDKException: option or bound or keyworld can not be null");
        }
        return this.f6790a.mo17582a(poiBoundSearchOption);
    }

    public boolean searchPoiDetail(PoiDetailSearchOption poiDetailSearchOption) {
        if (this.f6790a == null) {
            throw new IllegalStateException("BDMapSDKException: searcher is null, please call newInstance first.");
        }
        if (poiDetailSearchOption == null || poiDetailSearchOption.getUid() == null) {
            throw new IllegalArgumentException("BDMapSDKException: option or uid can not be null");
        }
        return this.f6790a.mo17580a(poiDetailSearchOption);
    }

    public boolean searchPoiIndoor(PoiIndoorOption poiIndoorOption) {
        if (this.f6790a == null) {
            throw new IllegalStateException("BDMapSDKException: searcher is null, please call newInstance first.");
        }
        if (poiIndoorOption == null || poiIndoorOption.bid == null || poiIndoorOption.f6780wd == null) {
            throw new IllegalArgumentException("BDMapSDKException: option or indoor bid or keyword can not be null");
        }
        return this.f6790a.mo17579a(poiIndoorOption);
    }

    public void destroy() {
        if (this.f6791b) {
            return;
        }
        this.f6791b = true;
        this.f6790a.mo17584a();
        BMapManager.destroy();
    }
}
