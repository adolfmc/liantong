package com.baidu.platform.core.p164h;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.baidu.platform.base.SearchRequest;
import com.baidu.platform.domain.UrlProvider;
import com.baidu.platform.util.BaseParamBuilder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.h.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SuggestionSearchRequest extends SearchRequest {
    public SuggestionSearchRequest(SuggestionSearchOption suggestionSearchOption) {
        m17487a(suggestionSearchOption);
    }

    /* renamed from: a */
    private void m17487a(SuggestionSearchOption suggestionSearchOption) {
        this.f7508a.m17443a("query", suggestionSearchOption.mKeyword);
        this.f7508a.m17443a("region", suggestionSearchOption.mCity);
        if (suggestionSearchOption.mLocation != null) {
            LatLng latLng = new LatLng(suggestionSearchOption.mLocation.latitude, suggestionSearchOption.mLocation.longitude);
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                latLng = CoordTrans.gcjToBaidu(latLng);
            }
            BaseParamBuilder baseParamBuilder = this.f7508a;
            baseParamBuilder.m17443a("location", latLng.latitude + "," + latLng.longitude);
        }
        if (suggestionSearchOption.mCityLimit.booleanValue()) {
            this.f7508a.m17443a("city_limit", "true");
        } else {
            this.f7508a.m17443a("city_limit", "false");
        }
        this.f7508a.m17443a("from", "android_map_sdk");
        this.f7508a.m17443a("output", "json");
        this.f7508a.m17443a("extensions_adcode", suggestionSearchOption.isExtendAdcode() ? "true" : "false");
    }

    @Override // com.baidu.platform.base.SearchRequest
    /* renamed from: a */
    public String mo17471a(UrlProvider urlProvider) {
        return urlProvider.mo17464d();
    }
}
