package com.baidu.platform.core.p160d;

import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiIndoorOption;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.d.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IPoiSearch {
    /* renamed from: a */
    void mo17584a();

    /* renamed from: a */
    void mo17583a(OnGetPoiSearchResultListener onGetPoiSearchResultListener);

    /* renamed from: a */
    boolean mo17582a(PoiBoundSearchOption poiBoundSearchOption);

    /* renamed from: a */
    boolean mo17581a(PoiCitySearchOption poiCitySearchOption);

    /* renamed from: a */
    boolean mo17580a(PoiDetailSearchOption poiDetailSearchOption);

    /* renamed from: a */
    boolean mo17579a(PoiIndoorOption poiIndoorOption);

    /* renamed from: a */
    boolean mo17578a(PoiNearbySearchOption poiNearbySearchOption);
}
