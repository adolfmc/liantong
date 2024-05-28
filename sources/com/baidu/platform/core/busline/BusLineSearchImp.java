package com.baidu.platform.core.busline;

import com.baidu.mapapi.search.busline.BusLineSearchOption;
import com.baidu.mapapi.search.busline.OnGetBusLineSearchResultListener;
import com.baidu.platform.base.BaseSearch;
import com.baidu.platform.base.SearchType;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.busline.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BusLineSearchImp extends BaseSearch implements IBusLineSearch {

    /* renamed from: b */
    OnGetBusLineSearchResultListener f8134b = null;

    @Override // com.baidu.platform.core.busline.IBusLineSearch
    /* renamed from: a */
    public boolean mo17612a(BusLineSearchOption busLineSearchOption) {
        BusLineParser busLineParser = new BusLineParser();
        busLineParser.m18091a(SearchType.BUS_LINE_DETAIL);
        return m18098a(new BusLineRequest(busLineSearchOption), this.f8134b, busLineParser);
    }

    @Override // com.baidu.platform.core.busline.IBusLineSearch
    /* renamed from: a */
    public void mo17611a(OnGetBusLineSearchResultListener onGetBusLineSearchResultListener) {
        this.f7494a.lock();
        this.f8134b = onGetBusLineSearchResultListener;
        this.f7494a.unlock();
    }

    @Override // com.baidu.platform.core.busline.IBusLineSearch
    /* renamed from: a */
    public void mo17613a() {
        this.f7494a.lock();
        this.f8134b = null;
        this.f7494a.unlock();
    }
}
