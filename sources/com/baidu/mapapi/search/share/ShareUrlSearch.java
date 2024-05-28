package com.baidu.mapapi.search.share;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.search.core.SearcherInternal;
import com.baidu.mapapi.search.share.RouteShareURLOption;
import com.baidu.platform.core.p163g.IShareUrlSearch;
import com.baidu.platform.core.p163g.ShareUrlSearchImp;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ShareUrlSearch extends SearcherInternal {

    /* renamed from: b */
    private boolean f6913b = false;

    /* renamed from: a */
    IShareUrlSearch f6912a = new ShareUrlSearchImp();

    ShareUrlSearch() {
    }

    public static ShareUrlSearch newInstance() {
        BMapManager.init();
        return new ShareUrlSearch();
    }

    public boolean requestPoiDetailShareUrl(PoiDetailShareURLOption poiDetailShareURLOption) {
        if (this.f6912a == null) {
            throw new IllegalStateException("BDMapSDKException: searcher has been destroyed");
        }
        if (poiDetailShareURLOption == null || poiDetailShareURLOption.mUid == null) {
            throw new IllegalArgumentException("BDMapSDKException: option or uid can not be null");
        }
        return this.f6912a.mo17496a(poiDetailShareURLOption);
    }

    public boolean requestLocationShareUrl(LocationShareURLOption locationShareURLOption) {
        if (this.f6912a == null) {
            throw new IllegalStateException("BDMapSDKException: searcher has been destroyed");
        }
        if (locationShareURLOption == null || locationShareURLOption.mLocation == null || locationShareURLOption.mName == null || locationShareURLOption.mSnippet == null) {
            throw new IllegalArgumentException("BDMapSDKException: option or name or snippet  can not be null");
        }
        return this.f6912a.mo17498a(locationShareURLOption);
    }

    public boolean requestRouteShareUrl(RouteShareURLOption routeShareURLOption) {
        if (this.f6912a != null) {
            if (routeShareURLOption == null) {
                throw new IllegalArgumentException("BDMapSDKException: option is null");
            }
            if (routeShareURLOption.getmMode().ordinal() < 0) {
                return false;
            }
            if (routeShareURLOption.mFrom == null || routeShareURLOption.mTo == null) {
                throw new IllegalArgumentException("BDMapSDKException: start or end point can not be null");
            }
            if (routeShareURLOption.mMode == RouteShareURLOption.RouteShareMode.BUS_ROUTE_SHARE_MODE) {
                if ((routeShareURLOption.mFrom.getLocation() == null || routeShareURLOption.mTo.getLocation() == null) && routeShareURLOption.mCityCode < 0) {
                    throw new IllegalArgumentException("BDMapSDKException: city code can not be null if don't set start or end point");
                }
            } else if (routeShareURLOption.mFrom.getLocation() == null && !m18644a(routeShareURLOption.mFrom.getCity())) {
                throw new IllegalArgumentException("BDMapSDKException: start cityCode must be set if not set start location");
            } else {
                if (routeShareURLOption.mTo.getLocation() == null && !m18644a(routeShareURLOption.mTo.getCity())) {
                    throw new IllegalArgumentException("BDMapSDKException: end cityCode must be set if not set end location");
                }
            }
            return this.f6912a.mo17495a(routeShareURLOption);
        }
        throw new IllegalStateException("BDMapSDKException: searcher has been destroyed");
    }

    /* renamed from: a */
    private boolean m18644a(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public void setOnGetShareUrlResultListener(OnGetShareUrlResultListener onGetShareUrlResultListener) {
        IShareUrlSearch iShareUrlSearch = this.f6912a;
        if (iShareUrlSearch == null) {
            throw new IllegalStateException("BDMapSDKException: searcher has been destroyed");
        }
        if (onGetShareUrlResultListener == null) {
            throw new IllegalArgumentException("BDMapSDKException: listener can not be null");
        }
        iShareUrlSearch.mo17497a(onGetShareUrlResultListener);
    }

    public void destroy() {
        if (this.f6913b) {
            return;
        }
        this.f6913b = true;
        this.f6912a.mo17499a();
        BMapManager.destroy();
    }
}
