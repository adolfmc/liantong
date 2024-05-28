package com.baidu.platform.core.busline;

import com.baidu.mapapi.search.busline.BusLineSearchOption;
import com.baidu.platform.base.SearchRequest;
import com.baidu.platform.domain.UrlProvider;
import com.baidu.platform.util.BaseParamBuilder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.busline.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BusLineRequest extends SearchRequest {
    public BusLineRequest(BusLineSearchOption busLineSearchOption) {
        m17614a(busLineSearchOption);
    }

    /* renamed from: a */
    private void m17614a(BusLineSearchOption busLineSearchOption) {
        this.f7508a.m17443a("qt", "bsl");
        this.f7508a.m17443a("rt_info", "1");
        this.f7508a.m17443a("ie", "utf-8");
        this.f7508a.m17443a("oue", "0");
        this.f7508a.m17443a("c", busLineSearchOption.mCity);
        this.f7508a.m17443a("uid", busLineSearchOption.mUid);
        BaseParamBuilder baseParamBuilder = this.f7508a;
        baseParamBuilder.m17443a("t", System.currentTimeMillis() + "");
    }

    @Override // com.baidu.platform.base.SearchRequest
    /* renamed from: a */
    public String mo17471a(UrlProvider urlProvider) {
        return urlProvider.mo17455m();
    }
}
