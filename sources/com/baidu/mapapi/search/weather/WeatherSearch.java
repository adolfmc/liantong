package com.baidu.mapapi.search.weather;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.search.core.SearcherInternal;
import com.baidu.platform.core.p165i.IWeatherSearch;
import com.baidu.platform.core.p165i.WeatherSearchImp;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WeatherSearch extends SearcherInternal {

    /* renamed from: a */
    IWeatherSearch f6929a = new WeatherSearchImp();

    public static WeatherSearch newInstance() {
        BMapManager.init();
        return new WeatherSearch();
    }

    public boolean request(WeatherSearchOption weatherSearchOption) {
        IWeatherSearch iWeatherSearch = this.f6929a;
        if (iWeatherSearch != null) {
            if (weatherSearchOption == null) {
                throw new IllegalArgumentException("BDMapSDKException: option can not be null");
            }
            return iWeatherSearch.mo17468a(weatherSearchOption);
        }
        throw new IllegalStateException("BDMapSDKException: searcher is null, please call newInstance first.");
    }

    public void setWeatherSearchResultListener(OnGetWeatherResultListener onGetWeatherResultListener) {
        IWeatherSearch iWeatherSearch = this.f6929a;
        if (iWeatherSearch == null) {
            throw new IllegalStateException("BDMapSDKException: searcher is null, please call newInstance first.");
        }
        if (onGetWeatherResultListener == null) {
            throw new IllegalArgumentException("BDMapSDKException: listener can not be null");
        }
        iWeatherSearch.mo17469a(onGetWeatherResultListener);
    }

    public void destroy() {
        IWeatherSearch iWeatherSearch = this.f6929a;
        if (iWeatherSearch != null) {
            iWeatherSearch.mo17470a();
        }
        BMapManager.destroy();
    }
}
