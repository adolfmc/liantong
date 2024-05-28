package com.baidu.platform.core.p159c;

import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.platform.base.SearchRequest;
import com.baidu.platform.domain.UrlProvider;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.c.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GeoCoderRequest extends SearchRequest {
    public GeoCoderRequest(GeoCodeOption geoCodeOption) {
        m17607a(geoCodeOption);
    }

    /* renamed from: a */
    private void m17607a(GeoCodeOption geoCodeOption) {
        this.f7508a.m17443a("city", geoCodeOption.mCity);
        this.f7508a.m17443a("address", geoCodeOption.mAddress);
        this.f7508a.m17443a("output", "json");
        this.f7508a.m17443a("ret_coordtype", "bd09ll");
        this.f7508a.m17443a("from", "android_map_sdk");
    }

    @Override // com.baidu.platform.base.SearchRequest
    /* renamed from: a */
    public String mo17471a(UrlProvider urlProvider) {
        return urlProvider.mo17462f();
    }
}
