package com.baidu.platform.core.p162f;

import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.platform.base.SearchRequest;
import com.baidu.platform.domain.UrlProvider;
import com.baidu.platform.util.BaseParamBuilder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.f.n */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class TransitRouteRequest extends SearchRequest {
    public TransitRouteRequest(TransitRoutePlanOption transitRoutePlanOption) {
        m17511a(transitRoutePlanOption);
    }

    /* renamed from: a */
    private void m17511a(TransitRoutePlanOption transitRoutePlanOption) {
        this.f7508a.m17443a("qt", "bus");
        BaseParamBuilder baseParamBuilder = this.f7508a;
        baseParamBuilder.m17443a("sy", transitRoutePlanOption.mPolicy.getInt() + "");
        this.f7508a.m17443a("ie", "utf-8");
        this.f7508a.m17443a("lrn", "20");
        this.f7508a.m17443a("version", "3");
        this.f7508a.m17443a("rp_format", "json");
        this.f7508a.m17443a("rp_filter", "mobile");
        this.f7508a.m17443a("ic_info", "2");
        this.f7508a.m17443a("exptype", "depall");
        this.f7508a.m17443a("sn", m18089a(transitRoutePlanOption.mFrom));
        this.f7508a.m17443a("en", m18089a(transitRoutePlanOption.mTo));
        if (transitRoutePlanOption.mCityName != null) {
            this.f7508a.m17443a("c", transitRoutePlanOption.mCityName);
        }
        if (TransitRoutePlanOption.TransitPolicy.EBUS_NO_SUBWAY == transitRoutePlanOption.mPolicy) {
            this.f7508a.m17443a("f", "[0,2,4,7,5,8,9,10,11]");
        }
    }

    @Override // com.baidu.platform.base.SearchRequest
    /* renamed from: a */
    public String mo17471a(UrlProvider urlProvider) {
        return urlProvider.mo17460h();
    }
}
