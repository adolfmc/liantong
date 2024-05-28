package com.baidu.platform.core.p160d;

import android.text.TextUtils;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.base.LanguageType;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.baidu.platform.base.SearchRequest;
import com.baidu.platform.domain.UrlProvider;
import com.baidu.platform.util.BaseParamBuilder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.d.i */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PoiSearchRequest extends SearchRequest {
    /* JADX INFO: Access modifiers changed from: package-private */
    public PoiSearchRequest(PoiNearbySearchOption poiNearbySearchOption) {
        m17570a(poiNearbySearchOption);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PoiSearchRequest(PoiCitySearchOption poiCitySearchOption) {
        m17571a(poiCitySearchOption);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PoiSearchRequest(PoiBoundSearchOption poiBoundSearchOption) {
        m17572a(poiBoundSearchOption);
    }

    /* renamed from: a */
    private void m17570a(PoiNearbySearchOption poiNearbySearchOption) {
        if (poiNearbySearchOption == null) {
            return;
        }
        this.f7508a.m17443a("query", poiNearbySearchOption.mKeyword);
        if (poiNearbySearchOption.mLanguageType == LanguageType.LanguageTypeEnglish) {
            this.f7508a.m17443a("language", "en");
        }
        if (poiNearbySearchOption.mLocation != null) {
            LatLng latLng = new LatLng(poiNearbySearchOption.mLocation.latitude, poiNearbySearchOption.mLocation.longitude);
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                latLng = CoordTrans.gcjToBaidu(latLng);
            }
            if (latLng != null) {
                BaseParamBuilder baseParamBuilder = this.f7508a;
                baseParamBuilder.m17443a("location", latLng.latitude + "," + latLng.longitude);
            }
        }
        BaseParamBuilder baseParamBuilder2 = this.f7508a;
        baseParamBuilder2.m17443a("radius", poiNearbySearchOption.mRadius + "");
        this.f7508a.m17443a("output", "json");
        this.f7508a.m17443a("extensions_adcode", poiNearbySearchOption.isExtendAdcode() ? "true" : "false");
        BaseParamBuilder baseParamBuilder3 = this.f7508a;
        baseParamBuilder3.m17443a("page_num", poiNearbySearchOption.mPageNum + "");
        BaseParamBuilder baseParamBuilder4 = this.f7508a;
        baseParamBuilder4.m17443a("page_size", poiNearbySearchOption.mPageCapacity + "");
        BaseParamBuilder baseParamBuilder5 = this.f7508a;
        baseParamBuilder5.m17443a("scope", poiNearbySearchOption.mScope + "");
        this.f7508a.m17443a("tag", poiNearbySearchOption.mTag);
        if (poiNearbySearchOption.mRadiusLimit) {
            this.f7508a.m17443a("radius_limit", "true");
        } else {
            this.f7508a.m17443a("radius_limit", "false");
        }
        if (poiNearbySearchOption.mScope != 2 || poiNearbySearchOption.mPoiFilter == null || TextUtils.isEmpty(poiNearbySearchOption.mPoiFilter.toString())) {
            return;
        }
        this.f7508a.m17443a("filter", poiNearbySearchOption.mPoiFilter.toString());
    }

    /* renamed from: a */
    private void m17571a(PoiCitySearchOption poiCitySearchOption) {
        if (poiCitySearchOption == null) {
            return;
        }
        this.f7508a.m17443a("query", poiCitySearchOption.mKeyword);
        if (poiCitySearchOption.mLanguageType == LanguageType.LanguageTypeEnglish) {
            this.f7508a.m17443a("language", "en");
        }
        this.f7508a.m17443a("region", poiCitySearchOption.mCity);
        this.f7508a.m17443a("output", "json");
        this.f7508a.m17443a("extensions_adcode", poiCitySearchOption.isExtendAdcode() ? "true" : "false");
        BaseParamBuilder baseParamBuilder = this.f7508a;
        baseParamBuilder.m17443a("page_num", poiCitySearchOption.mPageNum + "");
        BaseParamBuilder baseParamBuilder2 = this.f7508a;
        baseParamBuilder2.m17443a("page_size", poiCitySearchOption.mPageCapacity + "");
        BaseParamBuilder baseParamBuilder3 = this.f7508a;
        baseParamBuilder3.m17443a("scope", poiCitySearchOption.mScope + "");
        this.f7508a.m17443a("tag", poiCitySearchOption.mTag);
        if (poiCitySearchOption.mIsCityLimit) {
            this.f7508a.m17443a("city_limit", "true");
        } else {
            this.f7508a.m17443a("city_limit", "false");
        }
        if (poiCitySearchOption.mScope != 2 || poiCitySearchOption.mPoiFilter == null || TextUtils.isEmpty(poiCitySearchOption.mPoiFilter.toString())) {
            return;
        }
        this.f7508a.m17443a("filter", poiCitySearchOption.mPoiFilter.toString());
    }

    /* renamed from: a */
    private void m17572a(PoiBoundSearchOption poiBoundSearchOption) {
        if (poiBoundSearchOption == null) {
            return;
        }
        this.f7508a.m17443a("query", poiBoundSearchOption.mKeyword);
        if (poiBoundSearchOption.mLanguageType == LanguageType.LanguageTypeEnglish) {
            this.f7508a.m17443a("language", "en");
        }
        this.f7508a.m17443a("tag", poiBoundSearchOption.mTag);
        if (poiBoundSearchOption.mBound != null && poiBoundSearchOption.mBound.southwest != null && poiBoundSearchOption.mBound.northeast != null) {
            LatLng latLng = new LatLng(poiBoundSearchOption.mBound.southwest.latitude, poiBoundSearchOption.mBound.southwest.longitude);
            LatLng latLng2 = new LatLng(poiBoundSearchOption.mBound.northeast.latitude, poiBoundSearchOption.mBound.northeast.longitude);
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                latLng = CoordTrans.gcjToBaidu(latLng);
                latLng2 = CoordTrans.gcjToBaidu(latLng2);
            }
            if (latLng != null && latLng2 != null) {
                BaseParamBuilder baseParamBuilder = this.f7508a;
                baseParamBuilder.m17443a("bounds", latLng.latitude + "," + latLng.longitude + "," + latLng2.latitude + "," + latLng2.longitude);
            }
        }
        this.f7508a.m17443a("output", "json");
        this.f7508a.m17443a("extensions_adcode", poiBoundSearchOption.isExtendAdcode() ? "true" : "false");
        BaseParamBuilder baseParamBuilder2 = this.f7508a;
        baseParamBuilder2.m17443a("scope", poiBoundSearchOption.mScope + "");
        BaseParamBuilder baseParamBuilder3 = this.f7508a;
        baseParamBuilder3.m17443a("page_num", poiBoundSearchOption.mPageNum + "");
        BaseParamBuilder baseParamBuilder4 = this.f7508a;
        baseParamBuilder4.m17443a("page_size", poiBoundSearchOption.mPageCapacity + "");
        if (poiBoundSearchOption.mScope != 2 || poiBoundSearchOption.mPoiFilter == null || TextUtils.isEmpty(poiBoundSearchOption.mPoiFilter.toString())) {
            return;
        }
        this.f7508a.m17443a("filter", poiBoundSearchOption.mPoiFilter.toString());
    }

    @Override // com.baidu.platform.base.SearchRequest
    /* renamed from: a */
    public String mo17471a(UrlProvider urlProvider) {
        return urlProvider.mo17467a();
    }
}
