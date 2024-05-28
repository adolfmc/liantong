package com.baidu.platform.core.p160d;

import android.util.Log;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.platform.base.SearchRequest;
import com.baidu.platform.domain.UrlProvider;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.d.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PoiDetailSearchRequest extends SearchRequest {
    /* JADX INFO: Access modifiers changed from: package-private */
    public PoiDetailSearchRequest(PoiDetailSearchOption poiDetailSearchOption) {
        m17585a(poiDetailSearchOption);
    }

    /* renamed from: a */
    private void m17585a(PoiDetailSearchOption poiDetailSearchOption) {
        if (poiDetailSearchOption == null) {
            Log.e(PoiDetailSearchRequest.class.getSimpleName(), "Option is null");
            return;
        }
        if (!poiDetailSearchOption.isSearchByUids()) {
            poiDetailSearchOption.poiUids(poiDetailSearchOption.getUid());
        }
        this.f7508a.m17443a("uids", poiDetailSearchOption.getUids());
        this.f7508a.m17443a("extensions_adcode", poiDetailSearchOption.isExtendAdcode() ? "true" : "false");
        this.f7508a.m17443a("output", "json");
        this.f7508a.m17443a("scope", "2");
    }

    @Override // com.baidu.platform.base.SearchRequest
    /* renamed from: a */
    public String mo17471a(UrlProvider urlProvider) {
        return urlProvider.mo17466b();
    }
}
