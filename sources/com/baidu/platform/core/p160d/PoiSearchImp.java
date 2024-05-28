package com.baidu.platform.core.p160d;

import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiIndoorOption;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.platform.base.BaseSearch;
import com.baidu.platform.base.SearchType;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.d.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PoiSearchImp extends BaseSearch implements IPoiSearch {

    /* renamed from: b */
    private OnGetPoiSearchResultListener f8140b = null;

    @Override // com.baidu.platform.core.p160d.IPoiSearch
    /* renamed from: a */
    public boolean mo17578a(PoiNearbySearchOption poiNearbySearchOption) {
        PoiSearchParser poiSearchParser = new PoiSearchParser(poiNearbySearchOption.mPageNum, poiNearbySearchOption.mPageCapacity);
        poiSearchParser.m18091a(SearchType.POI_NEAR_BY_SEARCH);
        return m18098a(new PoiSearchRequest(poiNearbySearchOption), this.f8140b, poiSearchParser);
    }

    @Override // com.baidu.platform.core.p160d.IPoiSearch
    /* renamed from: a */
    public boolean mo17581a(PoiCitySearchOption poiCitySearchOption) {
        PoiSearchParser poiSearchParser = new PoiSearchParser(poiCitySearchOption.mPageNum, poiCitySearchOption.mPageCapacity);
        poiSearchParser.m18091a(SearchType.POI_IN_CITY_SEARCH);
        return m18098a(new PoiSearchRequest(poiCitySearchOption), this.f8140b, poiSearchParser);
    }

    @Override // com.baidu.platform.core.p160d.IPoiSearch
    /* renamed from: a */
    public boolean mo17582a(PoiBoundSearchOption poiBoundSearchOption) {
        PoiSearchParser poiSearchParser = new PoiSearchParser(poiBoundSearchOption.mPageNum, poiBoundSearchOption.mPageCapacity);
        poiSearchParser.m18091a(SearchType.POI_IN_BOUND_SEARCH);
        return m18098a(new PoiSearchRequest(poiBoundSearchOption), this.f8140b, poiSearchParser);
    }

    @Override // com.baidu.platform.core.p160d.IPoiSearch
    /* renamed from: a */
    public boolean mo17580a(PoiDetailSearchOption poiDetailSearchOption) {
        PoiDetailSearchParser poiDetailSearchParser = new PoiDetailSearchParser();
        if (poiDetailSearchOption != null) {
            poiDetailSearchParser.m17586a(poiDetailSearchOption.isSearchByUids());
        }
        poiDetailSearchParser.m18091a(SearchType.POI_DETAIL_SEARCH);
        return m18098a(new PoiDetailSearchRequest(poiDetailSearchOption), this.f8140b, poiDetailSearchParser);
    }

    @Override // com.baidu.platform.core.p160d.IPoiSearch
    /* renamed from: a */
    public boolean mo17579a(PoiIndoorOption poiIndoorOption) {
        IndoorPoiSearchParser indoorPoiSearchParser = new IndoorPoiSearchParser();
        indoorPoiSearchParser.m18091a(SearchType.INDOOR_POI_SEARCH);
        return m18098a(new IndoorPoiSearchRequest(poiIndoorOption), this.f8140b, indoorPoiSearchParser);
    }

    @Override // com.baidu.platform.core.p160d.IPoiSearch
    /* renamed from: a */
    public void mo17583a(OnGetPoiSearchResultListener onGetPoiSearchResultListener) {
        this.f7494a.lock();
        this.f8140b = onGetPoiSearchResultListener;
        this.f7494a.unlock();
    }

    @Override // com.baidu.platform.core.p160d.IPoiSearch
    /* renamed from: a */
    public void mo17584a() {
        this.f7494a.lock();
        this.f8140b = null;
        this.f7494a.unlock();
    }
}
