package com.baidu.platform.core.p163g;

import com.baidu.mapapi.search.share.LocationShareURLOption;
import com.baidu.mapapi.search.share.OnGetShareUrlResultListener;
import com.baidu.mapapi.search.share.PoiDetailShareURLOption;
import com.baidu.mapapi.search.share.RouteShareURLOption;
import com.baidu.platform.base.BaseSearch;
import com.baidu.platform.base.SearchType;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.g.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ShareUrlSearchImp extends BaseSearch implements IShareUrlSearch {

    /* renamed from: b */
    OnGetShareUrlResultListener f8152b = null;

    @Override // com.baidu.platform.core.p163g.IShareUrlSearch
    /* renamed from: a */
    public boolean mo17496a(PoiDetailShareURLOption poiDetailShareURLOption) {
        ShareUrlParser shareUrlParser = new ShareUrlParser();
        shareUrlParser.m18091a(SearchType.POI_DETAIL_SHARE);
        return m18098a(new PoiDetailShareRequest(poiDetailShareURLOption), this.f8152b, shareUrlParser);
    }

    @Override // com.baidu.platform.core.p163g.IShareUrlSearch
    /* renamed from: a */
    public boolean mo17498a(LocationShareURLOption locationShareURLOption) {
        ShareUrlParser shareUrlParser = new ShareUrlParser();
        shareUrlParser.m18091a(SearchType.LOCATION_SEARCH_SHARE);
        return m18098a(new LocationShareRequest(locationShareURLOption), this.f8152b, shareUrlParser);
    }

    @Override // com.baidu.platform.core.p163g.IShareUrlSearch
    /* renamed from: a */
    public boolean mo17495a(RouteShareURLOption routeShareURLOption) {
        RouteShareParser routeShareParser = new RouteShareParser();
        routeShareParser.m18091a(SearchType.ROUTE_PLAN_SHARE);
        return m18098a(new RouteShareRequest(routeShareURLOption), this.f8152b, routeShareParser);
    }

    @Override // com.baidu.platform.core.p163g.IShareUrlSearch
    /* renamed from: a */
    public void mo17497a(OnGetShareUrlResultListener onGetShareUrlResultListener) {
        this.f7494a.lock();
        this.f8152b = onGetShareUrlResultListener;
        this.f7494a.unlock();
    }

    @Override // com.baidu.platform.core.p163g.IShareUrlSearch
    /* renamed from: a */
    public void mo17499a() {
        this.f7494a.lock();
        this.f8152b = null;
        this.f7494a.unlock();
    }
}
