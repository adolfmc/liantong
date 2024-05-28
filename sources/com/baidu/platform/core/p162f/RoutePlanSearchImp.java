package com.baidu.platform.core.p162f;

import com.baidu.mapapi.search.route.BikingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.IndoorRoutePlanOption;
import com.baidu.mapapi.search.route.MassTransitRoutePlanOption;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.platform.base.BaseSearch;
import com.baidu.platform.base.SearchType;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.f.j */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RoutePlanSearchImp extends BaseSearch implements IRoutePlanSearch {

    /* renamed from: b */
    private OnGetRoutePlanResultListener f8147b = null;

    @Override // com.baidu.platform.core.p162f.IRoutePlanSearch
    /* renamed from: a */
    public void mo17526a(OnGetRoutePlanResultListener onGetRoutePlanResultListener) {
        this.f7494a.lock();
        this.f8147b = onGetRoutePlanResultListener;
        this.f7494a.unlock();
    }

    @Override // com.baidu.platform.core.p162f.IRoutePlanSearch
    /* renamed from: a */
    public boolean mo17525a(TransitRoutePlanOption transitRoutePlanOption) {
        TransitRouteParser transitRouteParser = new TransitRouteParser();
        transitRouteParser.m18091a(SearchType.TRANSIT_ROUTE);
        return m18098a(new TransitRouteRequest(transitRoutePlanOption), this.f8147b, transitRouteParser);
    }

    @Override // com.baidu.platform.core.p162f.IRoutePlanSearch
    /* renamed from: a */
    public boolean mo17527a(MassTransitRoutePlanOption massTransitRoutePlanOption) {
        MassTransitRouteParser massTransitRouteParser = new MassTransitRouteParser();
        massTransitRouteParser.m18091a(SearchType.MASS_TRANSIT_ROUTE);
        return m18098a(new MassTransitRouteRequest(massTransitRoutePlanOption), this.f8147b, massTransitRouteParser);
    }

    @Override // com.baidu.platform.core.p162f.IRoutePlanSearch
    /* renamed from: a */
    public boolean mo17524a(WalkingRoutePlanOption walkingRoutePlanOption) {
        WalkRouteParser walkRouteParser = new WalkRouteParser();
        walkRouteParser.m18091a(SearchType.WALK_ROUTE);
        return m18098a(new WalkRouteRequest(walkingRoutePlanOption), this.f8147b, walkRouteParser);
    }

    @Override // com.baidu.platform.core.p162f.IRoutePlanSearch
    /* renamed from: a */
    public boolean mo17528a(IndoorRoutePlanOption indoorRoutePlanOption) {
        IndoorRouteParser indoorRouteParser = new IndoorRouteParser();
        indoorRouteParser.m18091a(SearchType.INDOOR_ROUTE);
        return m18098a(new IndoorRouteRequest(indoorRoutePlanOption), this.f8147b, indoorRouteParser);
    }

    @Override // com.baidu.platform.core.p162f.IRoutePlanSearch
    /* renamed from: a */
    public boolean mo17529a(DrivingRoutePlanOption drivingRoutePlanOption) {
        DriveRouteParser driveRouteParser = new DriveRouteParser();
        driveRouteParser.m18091a(SearchType.DRIVE_ROUTE);
        return m18098a(new DriveRouteRequest(drivingRoutePlanOption), this.f8147b, driveRouteParser);
    }

    @Override // com.baidu.platform.core.p162f.IRoutePlanSearch
    /* renamed from: a */
    public boolean mo17530a(BikingRoutePlanOption bikingRoutePlanOption) {
        BikeRouteParser bikeRouteParser = new BikeRouteParser();
        bikeRouteParser.m18091a(SearchType.BIKE_ROUTE);
        return m18098a(new BikeRouteRequest(bikingRoutePlanOption), this.f8147b, bikeRouteParser);
    }

    @Override // com.baidu.platform.core.p162f.IRoutePlanSearch
    /* renamed from: a */
    public void mo17531a() {
        this.f7494a.lock();
        this.f8147b = null;
        this.f7494a.unlock();
    }
}
