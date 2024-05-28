package com.baidu.platform.core.p162f;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.MassTransitRoutePlanOption;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.baidu.platform.base.SearchRequest;
import com.baidu.platform.domain.UrlProvider;
import com.baidu.platform.util.BaseParamBuilder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.f.i */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MassTransitRouteRequest extends SearchRequest {
    public MassTransitRouteRequest(MassTransitRoutePlanOption massTransitRoutePlanOption) {
        m17532a(massTransitRoutePlanOption);
    }

    /* renamed from: a */
    private void m17532a(MassTransitRoutePlanOption massTransitRoutePlanOption) {
        LatLng location = massTransitRoutePlanOption.mFrom.getLocation();
        if (location != null) {
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                location = CoordTrans.gcjToBaidu(location);
            }
            BaseParamBuilder baseParamBuilder = this.f7508a;
            baseParamBuilder.m17443a("origin", location.latitude + "," + location.longitude);
        } else {
            this.f7508a.m17443a("origin", massTransitRoutePlanOption.mFrom.getName());
        }
        if (massTransitRoutePlanOption.mFrom.getCity() != null) {
            this.f7508a.m17443a("origin_region", massTransitRoutePlanOption.mFrom.getCity());
        }
        LatLng location2 = massTransitRoutePlanOption.mTo.getLocation();
        if (location2 != null) {
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                location2 = CoordTrans.gcjToBaidu(location2);
            }
            BaseParamBuilder baseParamBuilder2 = this.f7508a;
            baseParamBuilder2.m17443a("destination", location2.latitude + "," + location2.longitude);
        } else {
            this.f7508a.m17443a("destination", massTransitRoutePlanOption.mTo.getName());
        }
        if (massTransitRoutePlanOption.mTo.getCity() != null) {
            this.f7508a.m17443a("destination_region", massTransitRoutePlanOption.mTo.getCity());
        }
        BaseParamBuilder baseParamBuilder3 = this.f7508a;
        baseParamBuilder3.m17443a("tactics_incity", massTransitRoutePlanOption.mTacticsIncity.getInt() + "");
        BaseParamBuilder baseParamBuilder4 = this.f7508a;
        baseParamBuilder4.m17443a("tactics_intercity", massTransitRoutePlanOption.mTacticsIntercity.getInt() + "");
        BaseParamBuilder baseParamBuilder5 = this.f7508a;
        baseParamBuilder5.m17443a("trans_type_intercity", massTransitRoutePlanOption.mTransTypeIntercity.getInt() + "");
        BaseParamBuilder baseParamBuilder6 = this.f7508a;
        baseParamBuilder6.m17443a("page_index", massTransitRoutePlanOption.mPageIndex + "");
        BaseParamBuilder baseParamBuilder7 = this.f7508a;
        baseParamBuilder7.m17443a("page_size", massTransitRoutePlanOption.mPageSize + "");
        this.f7508a.m17443a("coord_type", massTransitRoutePlanOption.mCoordType);
        this.f7508a.m17443a("output", "json");
        this.f7508a.m17443a("from", "android_map_sdk");
    }

    @Override // com.baidu.platform.base.SearchRequest
    /* renamed from: a */
    public String mo17471a(UrlProvider urlProvider) {
        return urlProvider.mo17461g();
    }
}
