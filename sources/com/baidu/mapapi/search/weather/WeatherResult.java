package com.baidu.mapapi.search.weather;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.SearchResult;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WeatherResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<WeatherResult> CREATOR = new C2848b();

    /* renamed from: a */
    private WeatherSearchRealTime f6923a;

    /* renamed from: b */
    private WeatherSearchLocation f6924b;

    /* renamed from: c */
    private List<WeatherSearchForecasts> f6925c;

    /* renamed from: d */
    private List<WeatherSearchForecastForHours> f6926d;

    /* renamed from: e */
    private List<WeatherLifeIndexes> f6927e;

    /* renamed from: f */
    private List<WeatherSearchAlerts> f6928f;

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WeatherResult() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public WeatherResult(Parcel parcel) {
        super(parcel);
        this.f6923a = (WeatherSearchRealTime) parcel.readParcelable(WeatherSearchRealTime.class.getClassLoader());
        this.f6924b = (WeatherSearchLocation) parcel.readParcelable(WeatherSearchLocation.class.getClassLoader());
        this.f6925c = parcel.createTypedArrayList(WeatherSearchForecasts.CREATOR);
        this.f6926d = parcel.createTypedArrayList(WeatherSearchForecastForHours.CREATOR);
        this.f6927e = parcel.createTypedArrayList(WeatherLifeIndexes.CREATOR);
        this.f6928f = parcel.createTypedArrayList(WeatherSearchAlerts.CREATOR);
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.f6923a, i);
        parcel.writeParcelable(this.f6924b, i);
        parcel.writeTypedList(this.f6925c);
        parcel.writeTypedList(this.f6926d);
        parcel.writeTypedList(this.f6927e);
        parcel.writeTypedList(this.f6928f);
    }

    public WeatherSearchRealTime getRealTimeWeather() {
        return this.f6923a;
    }

    public void setRealTimeWeather(WeatherSearchRealTime weatherSearchRealTime) {
        this.f6923a = weatherSearchRealTime;
    }

    public WeatherSearchLocation getLocation() {
        return this.f6924b;
    }

    public void setLocation(WeatherSearchLocation weatherSearchLocation) {
        this.f6924b = weatherSearchLocation;
    }

    public List<WeatherSearchForecasts> getForecasts() {
        return this.f6925c;
    }

    public void setForecasts(List<WeatherSearchForecasts> list) {
        this.f6925c = list;
    }

    public List<WeatherSearchForecastForHours> getForecastHours() {
        return this.f6926d;
    }

    public void setForecastHours(List<WeatherSearchForecastForHours> list) {
        this.f6926d = list;
    }

    public List<WeatherLifeIndexes> getLifeIndexes() {
        return this.f6927e;
    }

    public void setLifeIndexes(List<WeatherLifeIndexes> list) {
        this.f6927e = list;
    }

    public List<WeatherSearchAlerts> getWeatherAlerts() {
        return this.f6928f;
    }

    public void setWeatherAlerts(List<WeatherSearchAlerts> list) {
        this.f6928f = list;
    }
}
