package com.baidu.platform.core.p157a;

import com.baidu.mapapi.search.building.BuildingSearchOption;
import com.baidu.mapapi.search.building.OnGetBuildingSearchResultListener;
import com.baidu.platform.base.BaseSearch;
import com.baidu.platform.base.SearchType;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.a.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BuildingSearchImp extends BaseSearch {

    /* renamed from: b */
    private OnGetBuildingSearchResultListener f8130b;

    /* renamed from: a */
    public boolean m17626a(BuildingSearchOption buildingSearchOption) {
        BuildingParser buildingParser = new BuildingParser();
        buildingParser.m18091a(SearchType.BUILDING_SEARCH);
        return m18098a(new BuildingSearchRequest(buildingSearchOption), this.f8130b, buildingParser);
    }

    /* renamed from: a */
    public void m17625a(OnGetBuildingSearchResultListener onGetBuildingSearchResultListener) {
        this.f7494a.lock();
        this.f8130b = onGetBuildingSearchResultListener;
        this.f7494a.unlock();
    }

    /* renamed from: a */
    public void m17627a() {
        this.f7494a.lock();
        this.f8130b = null;
        this.f7494a.unlock();
    }
}
