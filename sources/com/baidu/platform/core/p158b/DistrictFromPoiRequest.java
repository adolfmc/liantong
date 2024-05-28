package com.baidu.platform.core.p158b;

import com.baidu.mapapi.search.district.DistrictSearchOption;
import com.baidu.platform.base.SearchRequest;
import com.baidu.platform.domain.UrlProvider;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.b.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DistrictFromPoiRequest extends SearchRequest {
    /* JADX INFO: Access modifiers changed from: package-private */
    public DistrictFromPoiRequest(DistrictSearchOption districtSearchOption) {
        m17623a(districtSearchOption);
    }

    /* renamed from: a */
    private void m17623a(DistrictSearchOption districtSearchOption) {
        if (districtSearchOption == null) {
            return;
        }
        this.f7508a.m17443a("qt", "con");
        this.f7508a.m17443a("rp_format", "json");
        this.f7508a.m17443a("rp_filter", "mobile");
        this.f7508a.m17443a("area_res", "true");
        this.f7508a.m17443a("addr_identify", "1");
        this.f7508a.m17443a("ie", "utf-8");
        this.f7508a.m17443a("pn", "0");
        this.f7508a.m17443a("rn", "10");
        this.f7508a.m17443a("c", districtSearchOption.mCityName);
        if (districtSearchOption.mDistrictName == null || districtSearchOption.mDistrictName.equals("")) {
            this.f7508a.m17443a("wd", districtSearchOption.mCityName);
        } else {
            this.f7508a.m17443a("wd", districtSearchOption.mDistrictName);
        }
    }

    @Override // com.baidu.platform.base.SearchRequest
    /* renamed from: a */
    public String mo17471a(UrlProvider urlProvider) {
        return urlProvider.mo17454n();
    }
}
