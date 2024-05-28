package com.baidu.mapapi.search.busline;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.search.core.SearcherInternal;
import com.baidu.platform.core.busline.BusLineSearchImp;
import com.baidu.platform.core.busline.IBusLineSearch;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BusLineSearch extends SearcherInternal {

    /* renamed from: b */
    private boolean f6652b = false;

    /* renamed from: a */
    IBusLineSearch f6651a = new BusLineSearchImp();

    BusLineSearch() {
    }

    public static BusLineSearch newInstance() {
        BMapManager.init();
        return new BusLineSearch();
    }

    public boolean searchBusLine(BusLineSearchOption busLineSearchOption) {
        if (this.f6651a == null) {
            throw new IllegalStateException("BDMapSDKException: searcher is null, please call newInstance first.");
        }
        if (busLineSearchOption == null || busLineSearchOption.mCity == null || busLineSearchOption.mUid == null) {
            throw new IllegalArgumentException("BDMapSDKException: option or city or uid can not be null");
        }
        return this.f6651a.mo17612a(busLineSearchOption);
    }

    public void setOnGetBusLineSearchResultListener(OnGetBusLineSearchResultListener onGetBusLineSearchResultListener) {
        IBusLineSearch iBusLineSearch = this.f6651a;
        if (iBusLineSearch == null) {
            throw new IllegalStateException("BDMapSDKException: searcher is null, please call newInstance first.");
        }
        if (onGetBusLineSearchResultListener == null) {
            throw new IllegalArgumentException("BDMapSDKException: listener can not be null");
        }
        iBusLineSearch.mo17611a(onGetBusLineSearchResultListener);
    }

    public void destroy() {
        if (this.f6652b) {
            return;
        }
        this.f6652b = true;
        this.f6651a.mo17613a();
        BMapManager.destroy();
    }
}
