package com.baidu.platform.core.p162f;

import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.platform.base.SearchRequest;
import com.baidu.platform.domain.UrlProvider;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.f.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DriveRouteRequest extends SearchRequest {
    /* JADX INFO: Access modifiers changed from: package-private */
    public DriveRouteRequest(DrivingRoutePlanOption drivingRoutePlanOption) {
        m17545a(drivingRoutePlanOption);
    }

    /* renamed from: a */
    private void m17545a(DrivingRoutePlanOption drivingRoutePlanOption) {
        PlanNode planNode;
        this.f7508a.m17443a("qt", "cars");
        this.f7508a.m17443a("sy", drivingRoutePlanOption.mPolicy.getInt() + "");
        this.f7508a.m17443a("ie", "utf-8");
        this.f7508a.m17443a("lrn", "20");
        this.f7508a.m17443a("version", "6");
        this.f7508a.m17443a("extinfo", "32");
        this.f7508a.m17443a("mrs", "1");
        this.f7508a.m17443a("rp_format", "json");
        this.f7508a.m17443a("rp_filter", "mobile");
        this.f7508a.m17443a("route_traffic", drivingRoutePlanOption.mtrafficPolicy.getInt() + "");
        this.f7508a.m17443a("sn", m18089a(drivingRoutePlanOption.mFrom));
        this.f7508a.m17443a("en", m18089a(drivingRoutePlanOption.mTo));
        if (drivingRoutePlanOption.mCityName != null) {
            this.f7508a.m17443a("c", drivingRoutePlanOption.mCityName);
        }
        if (drivingRoutePlanOption.mFrom != null) {
            this.f7508a.m17443a("sc", drivingRoutePlanOption.mFrom.getCity());
        }
        if (drivingRoutePlanOption.mTo != null) {
            this.f7508a.m17443a("ec", drivingRoutePlanOption.mTo.getCity());
        }
        List<PlanNode> list = drivingRoutePlanOption.mWayPoints;
        String str = new String();
        String str2 = new String();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != null) {
                    str = str + m18089a(planNode);
                    str2 = str2 + planNode.getCity();
                    if (i != list.size() - 1) {
                        str = str + "|";
                        str2 = str2 + "|";
                    }
                }
            }
            this.f7508a.m17443a("wp", str);
            this.f7508a.m17443a("wpc", str2);
        }
    }

    @Override // com.baidu.platform.base.SearchRequest
    /* renamed from: a */
    public String mo17471a(UrlProvider urlProvider) {
        return urlProvider.mo17459i();
    }
}
