package com.baidu.mapapi.search.building;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.search.core.SearcherInternal;
import com.baidu.platform.core.p157a.BuildingSearchImp;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BuildingSearch extends SearcherInternal {

    /* renamed from: b */
    private boolean f6639b = false;

    /* renamed from: a */
    private final BuildingSearchImp f6638a = new BuildingSearchImp();

    public static BuildingSearch newInstance() {
        BMapManager.init();
        return new BuildingSearch();
    }

    public boolean requestBuilding(BuildingSearchOption buildingSearchOption) {
        if (this.f6638a == null) {
            throw new IllegalStateException("BDMapSDKException: BuildingSearch is null, please call newInstance() first.");
        }
        if (buildingSearchOption == null || buildingSearchOption.getLatLng() == null) {
            throw new IllegalStateException("BDMapSDKException: option or location can not be null");
        }
        return this.f6638a.m17626a(buildingSearchOption);
    }

    public void setOnGetBuildingSearchResultListener(OnGetBuildingSearchResultListener onGetBuildingSearchResultListener) {
        BuildingSearchImp buildingSearchImp = this.f6638a;
        if (buildingSearchImp == null) {
            throw new IllegalStateException("BDMapSDKException: BuildingSearch is null, please call newInstance first.");
        }
        if (onGetBuildingSearchResultListener == null) {
            throw new IllegalArgumentException("BDMapSDKException: listener can not be null");
        }
        buildingSearchImp.m17625a(onGetBuildingSearchResultListener);
    }

    public void destroy() {
        if (this.f6639b) {
            return;
        }
        this.f6639b = true;
        this.f6638a.m17627a();
        BMapManager.destroy();
    }
}
