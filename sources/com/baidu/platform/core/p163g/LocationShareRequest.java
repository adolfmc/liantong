package com.baidu.platform.core.p163g;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.search.share.LocationShareURLOption;
import com.baidu.platform.base.SearchRequest;
import com.baidu.platform.comapi.basestruct.Point;
import com.baidu.platform.domain.UrlProvider;
import com.baidu.platform.util.BaseParamBuilder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.g.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class LocationShareRequest extends SearchRequest {
    public LocationShareRequest(LocationShareURLOption locationShareURLOption) {
        m17503a(locationShareURLOption);
    }

    /* renamed from: a */
    private void m17503a(LocationShareURLOption locationShareURLOption) {
        this.f7508a.m17443a("qt", "cs");
        Point ll2point = CoordUtil.ll2point(locationShareURLOption.mLocation);
        BaseParamBuilder baseParamBuilder = this.f7508a;
        baseParamBuilder.m17443a("geo", ll2point.f7536x + "|" + ll2point.f7537y);
        this.f7508a.m17443a("t", locationShareURLOption.mName);
        this.f7508a.m17443a("cnt", locationShareURLOption.mSnippet);
        m18083b(false);
        m18085a(false);
    }

    @Override // com.baidu.platform.base.SearchRequest
    /* renamed from: a */
    public String mo17471a(UrlProvider urlProvider) {
        return urlProvider.mo17451q();
    }
}
