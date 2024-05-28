package com.baidu.mapapi.search.poi;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface OnGetPoiSearchResultListener {
    void onGetPoiDetailResult(PoiDetailResult poiDetailResult);

    void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult);

    void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult);

    void onGetPoiResult(PoiResult poiResult);
}
