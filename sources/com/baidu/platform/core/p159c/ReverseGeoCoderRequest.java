package com.baidu.platform.core.p159c;

import android.text.TextUtils;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.base.LanguageType;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.baidu.platform.base.SearchRequest;
import com.baidu.platform.domain.UrlProvider;
import com.baidu.platform.util.BaseParamBuilder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.c.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ReverseGeoCoderRequest extends SearchRequest {
    public ReverseGeoCoderRequest(ReverseGeoCodeOption reverseGeoCodeOption) {
        m17593a(reverseGeoCodeOption);
    }

    /* renamed from: a */
    private void m17593a(ReverseGeoCodeOption reverseGeoCodeOption) {
        if (reverseGeoCodeOption.getLocation() != null) {
            LatLng latLng = new LatLng(reverseGeoCodeOption.getLocation().latitude, reverseGeoCodeOption.getLocation().longitude);
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                latLng = CoordTrans.gcjToBaidu(latLng);
            }
            BaseParamBuilder baseParamBuilder = this.f7508a;
            baseParamBuilder.m17443a("location", latLng.latitude + "," + latLng.longitude);
        }
        if (reverseGeoCodeOption.getLanguage() == LanguageType.LanguageTypeEnglish) {
            this.f7508a.m17443a("language", "en");
        }
        this.f7508a.m17443a("coordtype", "bd09ll");
        this.f7508a.m17443a("page_index", String.valueOf(reverseGeoCodeOption.getPageNum()));
        this.f7508a.m17443a("page_size", String.valueOf(reverseGeoCodeOption.getPageSize()));
        this.f7508a.m17443a("pois", "1");
        this.f7508a.m17443a("extensions_poi", "1");
        this.f7508a.m17443a("extensions_town", "true");
        if (reverseGeoCodeOption.getExtensionsRoad()) {
            this.f7508a.m17443a("extensions_road", "true");
        } else {
            this.f7508a.m17443a("extensions_road", "false");
        }
        String poiType = reverseGeoCodeOption.getPoiType();
        if (!TextUtils.isEmpty(poiType)) {
            this.f7508a.m17443a("poi_types", poiType);
        }
        this.f7508a.m17443a("output", "jsonaes");
        this.f7508a.m17443a("from", "android_map_sdk");
        this.f7508a.m17443a("latest_admin", String.valueOf(reverseGeoCodeOption.getLatestAdmin()));
        this.f7508a.m17443a("radius", String.valueOf(reverseGeoCodeOption.getRadius()));
    }

    @Override // com.baidu.platform.base.SearchRequest
    /* renamed from: a */
    public String mo17471a(UrlProvider urlProvider) {
        return urlProvider.mo17463e();
    }
}
