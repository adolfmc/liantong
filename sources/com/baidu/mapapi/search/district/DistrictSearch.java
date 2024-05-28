package com.baidu.mapapi.search.district;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.search.core.SearcherInternal;
import com.baidu.platform.core.p158b.DistrictSearchImp;
import com.baidu.platform.core.p158b.IDistrictSearch;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DistrictSearch extends SearcherInternal {

    /* renamed from: a */
    private IDistrictSearch f6733a;

    /* renamed from: b */
    private boolean f6734b = false;

    DistrictSearch() {
        this.f6733a = null;
        this.f6733a = new DistrictSearchImp();
    }

    public static DistrictSearch newInstance() {
        BMapManager.init();
        return new DistrictSearch();
    }

    public boolean searchDistrict(DistrictSearchOption districtSearchOption) {
        if (this.f6733a == null) {
            throw new IllegalStateException("BDMapSDKException: searcher is null, please call newInstance first.");
        }
        if (districtSearchOption == null || districtSearchOption.mCityName == null || districtSearchOption.mCityName.equals("")) {
            throw new IllegalArgumentException("BDMapSDKException: option or city name can not be null or empty.");
        }
        return this.f6733a.mo17617a(districtSearchOption);
    }

    public void setOnDistrictSearchListener(OnGetDistricSearchResultListener onGetDistricSearchResultListener) {
        IDistrictSearch iDistrictSearch = this.f6733a;
        if (iDistrictSearch == null) {
            throw new IllegalStateException("BDMapSDKException: searcher is null, please call newInstance first.");
        }
        if (onGetDistricSearchResultListener == null) {
            throw new IllegalArgumentException("BDMapSDKException: listener can not be null");
        }
        iDistrictSearch.mo17616a(onGetDistricSearchResultListener);
    }

    public void destroy() {
        if (this.f6734b) {
            return;
        }
        this.f6734b = true;
        this.f6733a.mo17618a();
        BMapManager.destroy();
    }
}
