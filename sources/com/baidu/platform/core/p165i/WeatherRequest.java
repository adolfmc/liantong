package com.baidu.platform.core.p165i;

import android.text.TextUtils;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.base.LanguageType;
import com.baidu.mapapi.search.weather.WeatherDataType;
import com.baidu.mapapi.search.weather.WeatherSearchOption;
import com.baidu.mapapi.search.weather.WeatherServerType;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.baidu.platform.base.SearchRequest;
import com.baidu.platform.domain.UrlProvider;
import com.baidu.platform.util.BaseParamBuilder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.i.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WeatherRequest extends SearchRequest {

    /* renamed from: b */
    WeatherSearchOption f8155b;

    public WeatherRequest(WeatherSearchOption weatherSearchOption) {
        this.f8155b = weatherSearchOption;
        m17472a(weatherSearchOption);
    }

    /* renamed from: a */
    private void m17472a(WeatherSearchOption weatherSearchOption) {
        if (!TextUtils.isEmpty(weatherSearchOption.getDistrictID())) {
            this.f7508a.m17443a("district_id", weatherSearchOption.getDistrictID());
        }
        if (weatherSearchOption.getLocation() != null) {
            LatLng latLng = new LatLng(weatherSearchOption.getLocation().latitude, weatherSearchOption.getLocation().longitude);
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                latLng = CoordTrans.gcjToBaidu(latLng);
            }
            BaseParamBuilder baseParamBuilder = this.f7508a;
            baseParamBuilder.m17443a("location", latLng.longitude + "," + latLng.latitude);
            this.f7508a.m17443a("coordtype", m17475a(CoordType.BD09LL));
        }
        if (weatherSearchOption.getDataType() != null) {
            this.f7508a.m17443a("data_type", m17473a(weatherSearchOption.getDataType()));
        }
        if (weatherSearchOption.getLanguageType() != null) {
            this.f7508a.m17443a("language", m17474a(weatherSearchOption.getLanguageType()));
        }
    }

    @Override // com.baidu.platform.base.SearchRequest
    /* renamed from: a */
    public String mo17471a(UrlProvider urlProvider) {
        WeatherSearchOption weatherSearchOption = this.f8155b;
        if (weatherSearchOption == null) {
            return "";
        }
        if (weatherSearchOption.getServerType() == WeatherServerType.LANGUAGE_SERVER_TYPE_ABROAD) {
            return urlProvider.mo17448t();
        }
        return urlProvider.mo17449s();
    }

    /* renamed from: a */
    private String m17473a(WeatherDataType weatherDataType) {
        switch (C3118d.f8156a[weatherDataType.ordinal()]) {
            case 1:
                return "now";
            case 2:
                return "fc";
            case 3:
                return "fc_hour";
            case 4:
                return "index";
            case 5:
                return "alert";
            case 6:
                return "all";
            default:
                return "";
        }
    }

    /* renamed from: a */
    private String m17475a(CoordType coordType) {
        switch (C3118d.f8157b[coordType.ordinal()]) {
            case 1:
                return "bd09ll";
            case 2:
                return "gcj02";
            default:
                return "";
        }
    }

    /* renamed from: a */
    private String m17474a(LanguageType languageType) {
        switch (C3118d.f8158c[languageType.ordinal()]) {
            case 1:
                return "en";
            case 2:
                return "cn";
            default:
                return "";
        }
    }
}
