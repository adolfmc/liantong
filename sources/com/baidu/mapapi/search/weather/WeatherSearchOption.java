package com.baidu.mapapi.search.weather;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.base.LanguageType;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WeatherSearchOption {

    /* renamed from: b */
    private String f6959b;

    /* renamed from: c */
    private LatLng f6960c;

    /* renamed from: a */
    private WeatherServerType f6958a = WeatherServerType.WEATHER_SERVER_TYPE_DEFAULT;

    /* renamed from: d */
    private WeatherDataType f6961d = WeatherDataType.WEATHER_DATA_TYPE_REAL_TIME;

    /* renamed from: e */
    private LanguageType f6962e = LanguageType.LanguageTypeChinese;

    public WeatherSearchOption serverType(WeatherServerType weatherServerType) {
        this.f6958a = weatherServerType;
        return this;
    }

    public WeatherSearchOption districtID(String str) {
        this.f6959b = str;
        return this;
    }

    public WeatherSearchOption location(LatLng latLng) {
        this.f6960c = latLng;
        return this;
    }

    public WeatherSearchOption weatherDataType(WeatherDataType weatherDataType) {
        this.f6961d = weatherDataType;
        return this;
    }

    public WeatherSearchOption languageType(LanguageType languageType) {
        this.f6962e = languageType;
        return this;
    }

    public WeatherServerType getServerType() {
        return this.f6958a;
    }

    public String getDistrictID() {
        return this.f6959b;
    }

    public LatLng getLocation() {
        return this.f6960c;
    }

    public WeatherDataType getDataType() {
        return this.f6961d;
    }

    public LanguageType getLanguageType() {
        return this.f6962e;
    }
}
