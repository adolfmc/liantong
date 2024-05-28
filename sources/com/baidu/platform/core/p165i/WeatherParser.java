package com.baidu.platform.core.p165i;

import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.weather.OnGetWeatherResultListener;
import com.baidu.mapapi.search.weather.WeatherLifeIndexes;
import com.baidu.mapapi.search.weather.WeatherResult;
import com.baidu.mapapi.search.weather.WeatherSearchAlerts;
import com.baidu.mapapi.search.weather.WeatherSearchForecastForHours;
import com.baidu.mapapi.search.weather.WeatherSearchLocation;
import com.baidu.mapapi.search.weather.WeatherSearchRealTime;
import com.baidu.platform.base.SearchParser;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.i.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WeatherParser extends SearchParser {
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0062, code lost:
        if (r6.equals("REQUEST_ERROR") == false) goto L32;
     */
    @Override // com.baidu.platform.base.SearchParser
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.baidu.mapapi.search.core.SearchResult mo17483a(java.lang.String r6) {
        /*
            r5 = this;
            com.baidu.mapapi.search.weather.WeatherResult r0 = new com.baidu.mapapi.search.weather.WeatherResult
            r0.<init>()
            if (r6 != 0) goto Lc
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.RESULT_NOT_FOUND
            r0.error = r6
            return r0
        Lc:
            r1 = 0
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: org.json.JSONException -> L14
            r2.<init>(r6)     // Catch: org.json.JSONException -> L14
            r1 = r2
            goto L18
        L14:
            r2 = move-exception
            r2.printStackTrace()
        L18:
            if (r1 == 0) goto L8c
            int r2 = r1.length()
            if (r2 != 0) goto L22
            goto L8c
        L22:
            java.lang.String r2 = "SDK_InnerError"
            boolean r2 = r1.has(r2)
            r3 = 1
            if (r2 == 0) goto L82
            java.lang.String r2 = "SDK_InnerError"
            org.json.JSONObject r2 = r1.optJSONObject(r2)
            java.lang.String r4 = "PermissionCheckError"
            boolean r4 = r2.has(r4)
            if (r4 == 0) goto L3e
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.PERMISSION_UNFINISHED
            r0.error = r6
            return r0
        L3e:
            java.lang.String r4 = "httpStateError"
            boolean r4 = r2.has(r4)
            if (r4 == 0) goto L82
            java.lang.String r6 = "httpStateError"
            java.lang.String r6 = r2.optString(r6)
            r1 = -1
            int r2 = r6.hashCode()
            r4 = -879828873(0xffffffffcb8ee077, float:-1.872715E7)
            if (r2 == r4) goto L65
            r4 = 1470557208(0x57a6ec18, float:3.6706589E14)
            if (r2 == r4) goto L5c
            goto L6f
        L5c:
            java.lang.String r2 = "REQUEST_ERROR"
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L6f
            goto L70
        L65:
            java.lang.String r2 = "NETWORK_ERROR"
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L6f
            r3 = 0
            goto L70
        L6f:
            r3 = r1
        L70:
            switch(r3) {
                case 0: goto L7d;
                case 1: goto L78;
                default: goto L73;
            }
        L73:
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR
            r0.error = r6
            goto L81
        L78:
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.REQUEST_ERROR
            r0.error = r6
            goto L81
        L7d:
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.NETWORK_ERROR
            r0.error = r6
        L81:
            return r0
        L82:
            boolean r6 = r5.m18090a(r6, r0, r3)
            if (r6 != 0) goto L8b
            r5.m17482a(r1, r0)
        L8b:
            return r0
        L8c:
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.RESULT_NOT_FOUND
            r0.error = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.core.p165i.WeatherParser.mo17483a(java.lang.String):com.baidu.mapapi.search.core.SearchResult");
    }

    /* renamed from: a */
    private boolean m17482a(JSONObject jSONObject, WeatherResult weatherResult) {
        weatherResult.status = jSONObject.optInt("status");
        JSONObject optJSONObject = jSONObject.optJSONObject("result");
        if (optJSONObject == null) {
            return false;
        }
        m17481b(optJSONObject, weatherResult);
        m17480c(optJSONObject, weatherResult);
        m17478e(optJSONObject, weatherResult);
        m17476g(optJSONObject, weatherResult);
        m17479d(optJSONObject, weatherResult);
        m17477f(optJSONObject, weatherResult);
        return true;
    }

    /* renamed from: b */
    private boolean m17481b(JSONObject jSONObject, WeatherResult weatherResult) {
        JSONObject optJSONObject;
        if (jSONObject == null || weatherResult == null || (optJSONObject = jSONObject.optJSONObject("location")) == null) {
            return false;
        }
        if (weatherResult.getLocation() == null) {
            WeatherSearchLocation weatherSearchLocation = new WeatherSearchLocation();
            m17485a(weatherSearchLocation, optJSONObject);
            weatherResult.setLocation(weatherSearchLocation);
            return true;
        }
        m17485a(weatherResult.getLocation(), optJSONObject);
        return true;
    }

    /* renamed from: a */
    private void m17485a(WeatherSearchLocation weatherSearchLocation, JSONObject jSONObject) {
        weatherSearchLocation.setCountry(jSONObject.optString("country"));
        weatherSearchLocation.setProvince(jSONObject.optString("province"));
        weatherSearchLocation.setCity(jSONObject.optString("city"));
        weatherSearchLocation.setDistrictName(jSONObject.optString("name"));
        weatherSearchLocation.setDistrictID(jSONObject.optString("id"));
    }

    /* renamed from: c */
    private boolean m17480c(JSONObject jSONObject, WeatherResult weatherResult) {
        JSONObject optJSONObject;
        if (jSONObject == null || weatherResult == null || (optJSONObject = jSONObject.optJSONObject("now")) == null) {
            return false;
        }
        if (weatherResult.getRealTimeWeather() == null) {
            WeatherSearchRealTime weatherSearchRealTime = new WeatherSearchRealTime();
            m17484a(weatherSearchRealTime, optJSONObject);
            weatherResult.setRealTimeWeather(weatherSearchRealTime);
            return true;
        }
        m17484a(weatherResult.getRealTimeWeather(), optJSONObject);
        return true;
    }

    /* renamed from: a */
    private void m17484a(WeatherSearchRealTime weatherSearchRealTime, JSONObject jSONObject) {
        weatherSearchRealTime.setPhenomenon(jSONObject.optString("text"));
        weatherSearchRealTime.setTemperature(jSONObject.optInt("temp"));
        weatherSearchRealTime.setSensoryTemp(jSONObject.optInt("feels_like"));
        weatherSearchRealTime.setRelativeHumidity(jSONObject.optInt("rh"));
        weatherSearchRealTime.setWindPower(jSONObject.optString("wind_class"));
        weatherSearchRealTime.setWindDirection(jSONObject.optString("wind_dir"));
        weatherSearchRealTime.setUpdateTime(jSONObject.optString("uptime"));
        weatherSearchRealTime.setCO((float) jSONObject.optDouble("co"));
        weatherSearchRealTime.setNO2(jSONObject.optInt("no2"));
        weatherSearchRealTime.setPM10(jSONObject.optInt("pm10"));
        weatherSearchRealTime.setPM2_5(jSONObject.optInt("pm25"));
        weatherSearchRealTime.setClouds(jSONObject.optInt("clouds"));
        weatherSearchRealTime.setAirQualityIndex(jSONObject.optInt("aqi"));
        weatherSearchRealTime.setSO2(jSONObject.optInt("so2"));
        weatherSearchRealTime.setVisibility(jSONObject.optInt("vis"));
        weatherSearchRealTime.setO3(jSONObject.optInt("o3"));
        weatherSearchRealTime.setHourlyPrecipitation(jSONObject.optInt("prec_1h"));
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b5 A[SYNTHETIC] */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean m17479d(org.json.JSONObject r6, com.baidu.mapapi.search.weather.WeatherResult r7) {
        /*
            r5 = this;
            r0 = 0
            if (r6 == 0) goto Lbb
            if (r7 != 0) goto L7
            goto Lbb
        L7:
            java.lang.String r1 = "forecasts"
            org.json.JSONArray r6 = r6.optJSONArray(r1)
            if (r6 != 0) goto L10
            return r0
        L10:
            java.util.List r1 = r7.getForecasts()
            if (r1 != 0) goto L1e
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r7.setForecasts(r1)
        L1e:
            r1 = 0
        L1f:
            int r2 = r6.length()
            if (r0 >= r2) goto Lb9
            java.lang.Object r2 = r6.get(r0)     // Catch: org.json.JSONException -> L34
            org.json.JSONObject r2 = (org.json.JSONObject) r2     // Catch: org.json.JSONException -> L34
            com.baidu.mapapi.search.weather.WeatherSearchForecasts r1 = new com.baidu.mapapi.search.weather.WeatherSearchForecasts     // Catch: org.json.JSONException -> L32
            r1.<init>()     // Catch: org.json.JSONException -> L32
            r1 = r2
            goto L3c
        L32:
            r1 = move-exception
            goto L38
        L34:
            r2 = move-exception
            r4 = r2
            r2 = r1
            r1 = r4
        L38:
            r1.printStackTrace()
            r1 = r2
        L3c:
            if (r1 != 0) goto L3f
            goto Lb5
        L3f:
            com.baidu.mapapi.search.weather.WeatherSearchForecasts r2 = new com.baidu.mapapi.search.weather.WeatherSearchForecasts
            r2.<init>()
            java.lang.String r3 = "date"
            java.lang.String r3 = r1.optString(r3)
            r2.setDate(r3)
            java.lang.String r3 = "high"
            int r3 = r1.optInt(r3)
            r2.setHighestTemp(r3)
            java.lang.String r3 = "low"
            int r3 = r1.optInt(r3)
            r2.setLowestTemp(r3)
            java.lang.String r3 = "text_day"
            java.lang.String r3 = r1.optString(r3)
            r2.setPhenomenonDay(r3)
            java.lang.String r3 = "text_night"
            java.lang.String r3 = r1.optString(r3)
            r2.setPhenomenonNight(r3)
            java.lang.String r3 = "week"
            java.lang.String r3 = r1.optString(r3)
            r2.setWeek(r3)
            java.lang.String r3 = "wd_day"
            java.lang.String r3 = r1.optString(r3)
            r2.setWindDirectionDay(r3)
            java.lang.String r3 = "wc_day"
            java.lang.String r3 = r1.optString(r3)
            r2.setWindPowerDay(r3)
            java.lang.String r3 = "wd_night"
            java.lang.String r3 = r1.optString(r3)
            r2.setWindDirectionNight(r3)
            java.lang.String r3 = "wc_night"
            java.lang.String r3 = r1.optString(r3)
            r2.setWindPowerNight(r3)
            java.lang.String r3 = "aqi"
            int r3 = r1.optInt(r3)
            r2.setAirQualityIndex(r3)
            java.util.List r3 = r7.getForecasts()
            r3.add(r2)
        Lb5:
            int r0 = r0 + 1
            goto L1f
        Lb9:
            r6 = 1
            return r6
        Lbb:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.core.p165i.WeatherParser.m17479d(org.json.JSONObject, com.baidu.mapapi.search.weather.WeatherResult):boolean");
    }

    /* renamed from: e */
    private boolean m17478e(JSONObject jSONObject, WeatherResult weatherResult) {
        JSONArray optJSONArray;
        if (jSONObject == null || weatherResult == null || (optJSONArray = jSONObject.optJSONArray("alerts")) == null) {
            return false;
        }
        if (weatherResult.getWeatherAlerts() == null) {
            weatherResult.setWeatherAlerts(new ArrayList());
        }
        JSONObject jSONObject2 = null;
        for (int i = 0; i < optJSONArray.length(); i++) {
            try {
                jSONObject2 = (JSONObject) optJSONArray.get(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (jSONObject2 != null) {
                WeatherSearchAlerts weatherSearchAlerts = new WeatherSearchAlerts();
                weatherSearchAlerts.setDesc(jSONObject2.optString("desc"));
                weatherSearchAlerts.setLevel(jSONObject2.optString("level"));
                weatherSearchAlerts.setTitle(jSONObject2.optString("title"));
                weatherSearchAlerts.setType(jSONObject2.optString("type"));
                weatherResult.getWeatherAlerts().add(weatherSearchAlerts);
            }
        }
        return true;
    }

    /* renamed from: f */
    private boolean m17477f(JSONObject jSONObject, WeatherResult weatherResult) {
        JSONArray optJSONArray;
        if (jSONObject == null || weatherResult == null || (optJSONArray = jSONObject.optJSONArray("indexes")) == null) {
            return false;
        }
        if (weatherResult.getLifeIndexes() == null) {
            weatherResult.setLifeIndexes(new ArrayList());
        }
        JSONObject jSONObject2 = null;
        for (int i = 0; i < optJSONArray.length(); i++) {
            try {
                jSONObject2 = (JSONObject) optJSONArray.get(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (jSONObject2 != null) {
                WeatherLifeIndexes weatherLifeIndexes = new WeatherLifeIndexes();
                weatherLifeIndexes.setBrief(jSONObject2.optString("brief"));
                weatherLifeIndexes.setDetail(jSONObject2.optString("detail"));
                weatherLifeIndexes.setName(jSONObject2.optString("name"));
                weatherResult.getLifeIndexes().add(weatherLifeIndexes);
            }
        }
        return true;
    }

    /* renamed from: g */
    private boolean m17476g(JSONObject jSONObject, WeatherResult weatherResult) {
        JSONArray optJSONArray;
        if (jSONObject == null || weatherResult == null || (optJSONArray = jSONObject.optJSONArray("forecast_hours")) == null) {
            return false;
        }
        if (weatherResult.getForecastHours() == null) {
            weatherResult.setForecastHours(new ArrayList());
        }
        JSONObject jSONObject2 = null;
        for (int i = 0; i < optJSONArray.length(); i++) {
            try {
                jSONObject2 = (JSONObject) optJSONArray.get(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (jSONObject2 != null) {
                WeatherSearchForecastForHours weatherSearchForecastForHours = new WeatherSearchForecastForHours();
                weatherSearchForecastForHours.setClouds(jSONObject2.optInt("clouds"));
                weatherSearchForecastForHours.setDataTime(jSONObject2.optString("data_time"));
                weatherSearchForecastForHours.setHourlyPrecipitation(jSONObject2.optInt("prec_1h"));
                weatherSearchForecastForHours.setRelativeHumidity(jSONObject2.optInt("rh"));
                weatherSearchForecastForHours.setTemperature(jSONObject2.optInt("temp_fc"));
                weatherSearchForecastForHours.setPhenomenon(jSONObject2.optString("text"));
                weatherSearchForecastForHours.setWindDirection(jSONObject2.optString("wind_dir"));
                weatherSearchForecastForHours.setWindPower(jSONObject2.optString("wind_class"));
                weatherResult.getForecastHours().add(weatherSearchForecastForHours);
            }
        }
        return true;
    }

    @Override // com.baidu.platform.base.SearchParser
    /* renamed from: a */
    public void mo17486a(SearchResult searchResult, Object obj) {
        if (obj == null || !(obj instanceof OnGetWeatherResultListener)) {
            return;
        }
        ((OnGetWeatherResultListener) obj).onGetWeatherResultListener((WeatherResult) searchResult);
    }
}
