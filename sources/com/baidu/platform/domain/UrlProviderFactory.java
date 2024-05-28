package com.baidu.platform.domain;

import com.baidu.mapapi.http.HttpClient;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.domain.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class UrlProviderFactory {
    /* renamed from: a */
    public static UrlProvider m17445a() {
        if (HttpClient.isHttpsEnable) {
            return new HttpsUrlProvider();
        }
        return new HttpUrlProvider();
    }
}
