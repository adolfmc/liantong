package com.baidu.platform.core.p163g;

import com.baidu.mapapi.http.HttpClient;
import com.baidu.mapapi.search.share.PoiDetailShareURLOption;
import com.baidu.platform.base.SearchRequest;
import com.baidu.platform.domain.UrlProvider;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.g.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PoiDetailShareRequest extends SearchRequest {
    public PoiDetailShareRequest(PoiDetailShareURLOption poiDetailShareURLOption) {
        m17502a(poiDetailShareURLOption);
    }

    /* renamed from: a */
    private void m17502a(PoiDetailShareURLOption poiDetailShareURLOption) {
        this.f7508a.m17443a("url", ("http://wapmap.baidu.com/s?tn=Detail&pid=" + poiDetailShareURLOption.mUid + "&smsf=3") + HttpClient.getPhoneInfo());
        m18083b(false);
        m18085a(false);
    }

    @Override // com.baidu.platform.base.SearchRequest
    /* renamed from: a */
    public String mo17471a(UrlProvider urlProvider) {
        return urlProvider.mo17452p();
    }
}
