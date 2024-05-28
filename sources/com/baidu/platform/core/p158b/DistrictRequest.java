package com.baidu.platform.core.p158b;

import com.baidu.platform.base.SearchRequest;
import com.baidu.platform.domain.UrlProvider;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.b.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DistrictRequest extends SearchRequest {
    public DistrictRequest(String str) {
        m17619a(str);
    }

    /* renamed from: a */
    private void m17619a(String str) {
        this.f7508a.m17443a("qt", "ext");
        this.f7508a.m17443a("num", "1000");
        this.f7508a.m17443a("l", "10");
        this.f7508a.m17443a("ie", "utf-8");
        this.f7508a.m17443a("oue", "1");
        this.f7508a.m17443a("res", "api");
        this.f7508a.m17443a("fromproduct", "android_map_sdk");
        this.f7508a.m17443a("uid", str);
    }

    @Override // com.baidu.platform.base.SearchRequest
    /* renamed from: a */
    public String mo17471a(UrlProvider urlProvider) {
        return urlProvider.mo17453o();
    }
}
