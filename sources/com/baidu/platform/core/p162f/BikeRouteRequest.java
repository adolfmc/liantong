package com.baidu.platform.core.p162f;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.BikingRoutePlanOption;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.baidu.platform.base.SearchRequest;
import com.baidu.platform.domain.UrlProvider;
import com.baidu.platform.util.BaseParamBuilder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.f.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BikeRouteRequest extends SearchRequest {
    public BikeRouteRequest(BikingRoutePlanOption bikingRoutePlanOption) {
        m17555a(bikingRoutePlanOption);
    }

    /* renamed from: a */
    private void m17555a(BikingRoutePlanOption bikingRoutePlanOption) {
        this.f7508a.m17443a("mode", "riding");
        LatLng location = bikingRoutePlanOption.mFrom.getLocation();
        if (location != null) {
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                location = CoordTrans.gcjToBaidu(location);
            }
            BaseParamBuilder baseParamBuilder = this.f7508a;
            baseParamBuilder.m17443a("origin", location.latitude + "," + location.longitude);
        } else {
            this.f7508a.m17443a("origin", bikingRoutePlanOption.mFrom.getName());
        }
        LatLng location2 = bikingRoutePlanOption.mTo.getLocation();
        if (location2 != null) {
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                location2 = CoordTrans.gcjToBaidu(location2);
            }
            BaseParamBuilder baseParamBuilder2 = this.f7508a;
            baseParamBuilder2.m17443a("destination", location2.latitude + "," + location2.longitude);
        } else {
            this.f7508a.m17443a("destination", bikingRoutePlanOption.mTo.getName());
        }
        this.f7508a.m17443a("origin_region", bikingRoutePlanOption.mFrom.getCity());
        this.f7508a.m17443a("destination_region", bikingRoutePlanOption.mTo.getCity());
        if (bikingRoutePlanOption.mRidingType == 1) {
            this.f7508a.m17443a("riding_type", String.valueOf(bikingRoutePlanOption.mRidingType));
        }
        this.f7508a.m17443a("output", "json");
        this.f7508a.m17443a("from", "android_map_sdk");
    }

    @Override // com.baidu.platform.base.SearchRequest
    /* renamed from: a */
    public String mo17471a(UrlProvider urlProvider) {
        return urlProvider.mo17458j();
    }
}
