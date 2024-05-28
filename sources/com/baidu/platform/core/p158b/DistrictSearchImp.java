package com.baidu.platform.core.p158b;

import com.baidu.mapapi.search.district.DistrictSearchOption;
import com.baidu.mapapi.search.district.OnGetDistricSearchResultListener;
import com.baidu.platform.base.BaseSearch;
import com.baidu.platform.base.SearchType;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.b.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DistrictSearchImp extends BaseSearch implements IDistrictSearch {

    /* renamed from: b */
    private OnGetDistricSearchResultListener f8133b = null;

    @Override // com.baidu.platform.core.p158b.IDistrictSearch
    /* renamed from: a */
    public boolean mo17617a(DistrictSearchOption districtSearchOption) {
        DistrictParser districtParser = new DistrictParser();
        districtParser.m18091a(SearchType.DISTRICT_SEARCH);
        return m18098a(new DistrictFromPoiRequest(districtSearchOption), this.f8133b, districtParser);
    }

    @Override // com.baidu.platform.core.p158b.IDistrictSearch
    /* renamed from: a */
    public void mo17616a(OnGetDistricSearchResultListener onGetDistricSearchResultListener) {
        this.f7494a.lock();
        this.f8133b = onGetDistricSearchResultListener;
        this.f7494a.unlock();
    }

    @Override // com.baidu.platform.core.p158b.IDistrictSearch
    /* renamed from: a */
    public void mo17618a() {
        this.f7494a.lock();
        this.f8133b = null;
        this.f7494a.unlock();
    }
}
