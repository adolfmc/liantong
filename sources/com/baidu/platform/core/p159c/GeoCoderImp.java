package com.baidu.platform.core.p159c;

import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.platform.base.BaseSearch;
import com.baidu.platform.base.SearchRequest;
import com.baidu.platform.base.SearchType;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.c.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GeoCoderImp extends BaseSearch implements IGeoCoder {

    /* renamed from: b */
    OnGetGeoCoderResultListener f8135b = null;

    @Override // com.baidu.platform.core.p159c.IGeoCoder
    /* renamed from: a */
    public boolean mo17605a(GeoCodeOption geoCodeOption) {
        GeoCoderParser geoCoderParser = new GeoCoderParser();
        SearchRequest geoCoderRequest = new GeoCoderRequest(geoCodeOption);
        geoCoderParser.m18091a(SearchType.GEO_CODER);
        if (geoCodeOption != null) {
            geoCoderParser.m17608b(geoCodeOption.getAddress());
        }
        return m18098a(geoCoderRequest, this.f8135b, geoCoderParser);
    }

    @Override // com.baidu.platform.core.p159c.IGeoCoder
    /* renamed from: a */
    public boolean mo17603a(ReverseGeoCodeOption reverseGeoCodeOption) {
        ReverseGeoCoderParser reverseGeoCoderParser = new ReverseGeoCoderParser();
        ReverseGeoCoderRequest reverseGeoCoderRequest = new ReverseGeoCoderRequest(reverseGeoCodeOption);
        reverseGeoCoderParser.m18091a(SearchType.REVERSE_GEO_CODER);
        return m18098a(reverseGeoCoderRequest, this.f8135b, reverseGeoCoderParser);
    }

    @Override // com.baidu.platform.core.p159c.IGeoCoder
    /* renamed from: a */
    public void mo17604a(OnGetGeoCoderResultListener onGetGeoCoderResultListener) {
        this.f7494a.lock();
        this.f8135b = onGetGeoCoderResultListener;
        this.f7494a.unlock();
    }

    @Override // com.baidu.platform.core.p159c.IGeoCoder
    /* renamed from: a */
    public void mo17606a() {
        this.f7494a.lock();
        this.f8135b = null;
        this.f7494a.unlock();
    }
}
