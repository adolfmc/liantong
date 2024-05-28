package com.baidu.platform.core.p162f;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.search.route.IndoorRoutePlanOption;
import com.baidu.platform.base.SearchRequest;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.domain.UrlProvider;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.f.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class IndoorRouteRequest extends SearchRequest {
    /* JADX INFO: Access modifiers changed from: package-private */
    public IndoorRouteRequest(IndoorRoutePlanOption indoorRoutePlanOption) {
        m17542a(indoorRoutePlanOption);
    }

    /* renamed from: a */
    private void m17542a(IndoorRoutePlanOption indoorRoutePlanOption) {
        this.f7508a.m17443a("qt", "indoornavi");
        this.f7508a.m17443a("rp_format", "json");
        this.f7508a.m17443a("version", "1");
        GeoPoint ll2mc = CoordUtil.ll2mc(indoorRoutePlanOption.mFrom.getLocation());
        if (ll2mc != null) {
            String format = String.format("%f,%f", Double.valueOf(ll2mc.getLongitudeE6()), Double.valueOf(ll2mc.getLatitudeE6()));
            this.f7508a.m17443a("sn", (format + "|" + indoorRoutePlanOption.mFrom.getFloor()).replaceAll(" ", ""));
        }
        GeoPoint ll2mc2 = CoordUtil.ll2mc(indoorRoutePlanOption.mTo.getLocation());
        if (ll2mc2 != null) {
            String format2 = String.format("%f,%f", Double.valueOf(ll2mc2.getLongitudeE6()), Double.valueOf(ll2mc2.getLatitudeE6()));
            this.f7508a.m17443a("en", (format2 + "|" + indoorRoutePlanOption.mTo.getFloor()).replaceAll(" ", ""));
        }
    }

    @Override // com.baidu.platform.base.SearchRequest
    /* renamed from: a */
    public String mo17471a(UrlProvider urlProvider) {
        return urlProvider.mo17456l();
    }
}
