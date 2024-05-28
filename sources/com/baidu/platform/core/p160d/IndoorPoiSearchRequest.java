package com.baidu.platform.core.p160d;

import com.baidu.mapapi.search.poi.PoiIndoorOption;
import com.baidu.platform.base.SearchRequest;
import com.baidu.platform.domain.UrlProvider;
import com.baidu.platform.util.BaseParamBuilder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.d.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class IndoorPoiSearchRequest extends SearchRequest {
    public IndoorPoiSearchRequest(PoiIndoorOption poiIndoorOption) {
        m17591a(poiIndoorOption);
    }

    /* renamed from: a */
    private void m17591a(PoiIndoorOption poiIndoorOption) {
        this.f7508a.m17443a("qt", "indoor_s");
        this.f7508a.m17443a("x", "0");
        this.f7508a.m17443a("y", "0");
        this.f7508a.m17443a("from", "android_map_sdk");
        String str = poiIndoorOption.bid;
        if (str != null && !str.equals("")) {
            this.f7508a.m17443a("bid", str);
        }
        String str2 = poiIndoorOption.f6780wd;
        if (str2 != null && !str2.equals("")) {
            this.f7508a.m17443a("wd", str2);
        }
        String str3 = poiIndoorOption.floor;
        if (str3 != null && !str3.equals("")) {
            this.f7508a.m17443a("floor", str3);
        }
        BaseParamBuilder baseParamBuilder = this.f7508a;
        baseParamBuilder.m17443a("current", poiIndoorOption.currentPage + "");
        BaseParamBuilder baseParamBuilder2 = this.f7508a;
        baseParamBuilder2.m17443a("pageSize", poiIndoorOption.pageSize + "");
    }

    @Override // com.baidu.platform.base.SearchRequest
    /* renamed from: a */
    public String mo17471a(UrlProvider urlProvider) {
        return urlProvider.mo17465c();
    }
}
