package com.baidu.platform.core.p162f;

import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.platform.base.SearchRequest;
import com.baidu.platform.domain.UrlProvider;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.f.p */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WalkRouteRequest extends SearchRequest {
    public WalkRouteRequest(WalkingRoutePlanOption walkingRoutePlanOption) {
        m17504a(walkingRoutePlanOption);
    }

    /* renamed from: a */
    private void m17504a(WalkingRoutePlanOption walkingRoutePlanOption) {
        this.f7508a.m17443a("qt", "walk2");
        this.f7508a.m17443a("sn", m18089a(walkingRoutePlanOption.mFrom));
        this.f7508a.m17443a("en", m18089a(walkingRoutePlanOption.mTo));
        if (walkingRoutePlanOption.mFrom != null) {
            this.f7508a.m17443a("sc", walkingRoutePlanOption.mFrom.getCity());
        }
        if (walkingRoutePlanOption.mTo != null) {
            this.f7508a.m17443a("ec", walkingRoutePlanOption.mTo.getCity());
        }
        this.f7508a.m17443a("ie", "utf-8");
        this.f7508a.m17443a("lrn", "20");
        this.f7508a.m17443a("version", "3");
        this.f7508a.m17443a("rp_format", "json");
        this.f7508a.m17443a("rp_filter", "mobile");
    }

    @Override // com.baidu.platform.base.SearchRequest
    /* renamed from: a */
    public String mo17471a(UrlProvider urlProvider) {
        return urlProvider.mo17457k();
    }
}
