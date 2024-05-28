package com.baidu.platform.core.p165i;

import com.baidu.mapapi.search.weather.OnGetWeatherResultListener;
import com.baidu.mapapi.search.weather.WeatherSearchOption;
import com.baidu.platform.base.BaseSearch;
import com.baidu.platform.base.SearchType;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.i.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WeatherSearchImp extends BaseSearch implements IWeatherSearch {

    /* renamed from: b */
    private OnGetWeatherResultListener f8159b;

    @Override // com.baidu.platform.core.p165i.IWeatherSearch
    /* renamed from: a */
    public boolean mo17468a(WeatherSearchOption weatherSearchOption) {
        WeatherParser weatherParser = new WeatherParser();
        weatherParser.m18091a(SearchType.WEATHER_SEARCH);
        return m18098a(new WeatherRequest(weatherSearchOption), this.f8159b, weatherParser);
    }

    @Override // com.baidu.platform.core.p165i.IWeatherSearch
    /* renamed from: a */
    public void mo17469a(OnGetWeatherResultListener onGetWeatherResultListener) {
        this.f7494a.lock();
        this.f8159b = onGetWeatherResultListener;
        this.f7494a.unlock();
    }

    @Override // com.baidu.platform.core.p165i.IWeatherSearch
    /* renamed from: a */
    public void mo17470a() {
        this.f7494a.lock();
        this.f8159b = null;
        this.f7494a.unlock();
    }
}
