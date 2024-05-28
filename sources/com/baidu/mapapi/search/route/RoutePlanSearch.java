package com.baidu.mapapi.search.route;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.search.core.SearcherInternal;
import com.baidu.platform.core.p162f.IRoutePlanSearch;
import com.baidu.platform.core.p162f.RoutePlanSearchImp;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RoutePlanSearch extends SearcherInternal {

    /* renamed from: b */
    private boolean f6879b = false;

    /* renamed from: a */
    private IRoutePlanSearch f6878a = new RoutePlanSearchImp();

    RoutePlanSearch() {
    }

    public static RoutePlanSearch newInstance() {
        BMapManager.init();
        return new RoutePlanSearch();
    }

    public void setOnGetRoutePlanResultListener(OnGetRoutePlanResultListener onGetRoutePlanResultListener) {
        IRoutePlanSearch iRoutePlanSearch = this.f6878a;
        if (iRoutePlanSearch == null) {
            throw new IllegalStateException("BDMapSDKException: RoutePlanSearch is null, please call newInstance() first.");
        }
        if (onGetRoutePlanResultListener == null) {
            throw new IllegalArgumentException("BDMapSDKException: listener can not be null");
        }
        iRoutePlanSearch.mo17526a(onGetRoutePlanResultListener);
    }

    public boolean transitSearch(TransitRoutePlanOption transitRoutePlanOption) {
        if (this.f6878a == null) {
            throw new IllegalStateException("BDMapSDKException: RoutePlanSearch is null, please call newInstance() first.");
        }
        if (transitRoutePlanOption == null || transitRoutePlanOption.mCityName == null || transitRoutePlanOption.mTo == null || transitRoutePlanOption.mFrom == null) {
            throw new IllegalArgumentException("BDMapSDKException: route plan option,origin or destination or city can not be null");
        }
        return this.f6878a.mo17525a(transitRoutePlanOption);
    }

    public boolean masstransitSearch(MassTransitRoutePlanOption massTransitRoutePlanOption) {
        if (this.f6878a == null) {
            throw new IllegalStateException("BDMapSDKException: RoutePlanSearch is null, please call newInstance() first.");
        }
        if (massTransitRoutePlanOption == null || massTransitRoutePlanOption.mTo == null || massTransitRoutePlanOption.mFrom == null) {
            throw new IllegalArgumentException("BDMapSDKException: route plan option,origin or destination can not be null");
        }
        if (massTransitRoutePlanOption.mFrom.getLocation() == null && (massTransitRoutePlanOption.mFrom.getName() == null || massTransitRoutePlanOption.mFrom.getCity() == null)) {
            throw new IllegalArgumentException("BDMapSDKException: route plan option,origin is illegal");
        }
        if (massTransitRoutePlanOption.mTo.getLocation() == null && (massTransitRoutePlanOption.mTo.getName() == null || massTransitRoutePlanOption.mTo.getCity() == null)) {
            throw new IllegalArgumentException("BDMapSDKException: route plan option,destination is illegal");
        }
        return this.f6878a.mo17527a(massTransitRoutePlanOption);
    }

    public boolean walkingSearch(WalkingRoutePlanOption walkingRoutePlanOption) {
        if (this.f6878a == null) {
            throw new IllegalStateException("BDMapSDKException: RoutePlanSearch is null, please call newInstance() first.");
        }
        if (walkingRoutePlanOption == null || walkingRoutePlanOption.mTo == null || walkingRoutePlanOption.mFrom == null) {
            throw new IllegalArgumentException("BDMapSDKException: option , origin or destination can not be null");
        }
        return this.f6878a.mo17524a(walkingRoutePlanOption);
    }

    public boolean walkingIndoorSearch(IndoorRoutePlanOption indoorRoutePlanOption) {
        if (this.f6878a == null) {
            throw new IllegalStateException("BDMapSDKException: RoutePlanSearch is null, please call newInstance() first.");
        }
        if (indoorRoutePlanOption == null || indoorRoutePlanOption.mTo == null || indoorRoutePlanOption.mFrom == null) {
            throw new IllegalArgumentException("BDMapSDKException: option , origin or destination can not be null");
        }
        return this.f6878a.mo17528a(indoorRoutePlanOption);
    }

    public boolean drivingSearch(DrivingRoutePlanOption drivingRoutePlanOption) {
        if (this.f6878a == null) {
            throw new IllegalStateException("BDMapSDKException: RoutePlanSearch is null, please call newInstance() first.");
        }
        if (drivingRoutePlanOption == null || drivingRoutePlanOption.mTo == null || drivingRoutePlanOption.mFrom == null) {
            throw new IllegalArgumentException("BDMapSDKException: route plan option , origin or destination can not be null");
        }
        return this.f6878a.mo17529a(drivingRoutePlanOption);
    }

    public boolean bikingSearch(BikingRoutePlanOption bikingRoutePlanOption) {
        if (this.f6878a == null) {
            throw new IllegalStateException("BDMapSDKException: RoutePlanSearch is null, please call newInstance() first.");
        }
        if (bikingRoutePlanOption == null || bikingRoutePlanOption.mTo == null || bikingRoutePlanOption.mFrom == null) {
            throw new IllegalArgumentException("BDMapSDKException: route plan option , origin or destination can not be null");
        }
        if (bikingRoutePlanOption.mFrom.getLocation() == null && (bikingRoutePlanOption.mFrom.getName() == null || bikingRoutePlanOption.mFrom.getName() == "")) {
            throw new IllegalArgumentException("BDMapSDKException: route plan option , origin is illegal");
        }
        if (bikingRoutePlanOption.mTo.getLocation() == null && (bikingRoutePlanOption.mTo.getName() == null || bikingRoutePlanOption.mTo.getName() == "")) {
            throw new IllegalArgumentException("BDMapSDKException: route plan option , destination is illegal");
        }
        return this.f6878a.mo17530a(bikingRoutePlanOption);
    }

    public void destroy() {
        if (this.f6879b) {
            return;
        }
        this.f6879b = true;
        this.f6878a.mo17531a();
        BMapManager.destroy();
    }
}
