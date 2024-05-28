package com.baidu.platform.core.p162f;

import com.baidu.mapapi.search.route.BikingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.IndoorRoutePlanOption;
import com.baidu.mapapi.search.route.MassTransitRoutePlanOption;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.f.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IRoutePlanSearch {
    /* renamed from: a */
    void mo17531a();

    /* renamed from: a */
    void mo17526a(OnGetRoutePlanResultListener onGetRoutePlanResultListener);

    /* renamed from: a */
    boolean mo17530a(BikingRoutePlanOption bikingRoutePlanOption);

    /* renamed from: a */
    boolean mo17529a(DrivingRoutePlanOption drivingRoutePlanOption);

    /* renamed from: a */
    boolean mo17528a(IndoorRoutePlanOption indoorRoutePlanOption);

    /* renamed from: a */
    boolean mo17527a(MassTransitRoutePlanOption massTransitRoutePlanOption);

    /* renamed from: a */
    boolean mo17525a(TransitRoutePlanOption transitRoutePlanOption);

    /* renamed from: a */
    boolean mo17524a(WalkingRoutePlanOption walkingRoutePlanOption);
}
