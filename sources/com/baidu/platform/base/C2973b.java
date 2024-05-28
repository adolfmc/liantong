package com.baidu.platform.base;

import android.text.TextUtils;
import com.baidu.mapapi.http.AsyncHttpClient;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.platform.core.p159c.ReverseGeoCoderParser;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: BaseSearch.java */
/* renamed from: com.baidu.platform.base.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2973b extends HttpClient.ProtoResultCallback {

    /* renamed from: a */
    final /* synthetic */ SearchParser f7500a;

    /* renamed from: b */
    final /* synthetic */ Object f7501b;

    /* renamed from: c */
    final /* synthetic */ BaseSearch f7502c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2973b(BaseSearch baseSearch, SearchParser searchParser, Object obj) {
        this.f7502c = baseSearch;
        this.f7500a = searchParser;
        this.f7501b = obj;
    }

    @Override // com.baidu.mapapi.http.HttpClient.ProtoResultCallback
    public void onSuccess(String str) {
        boolean m18093c;
        String str2;
        AsyncHttpClient asyncHttpClient;
        m18093c = this.f7502c.m18093c(str);
        if (!m18093c) {
            String m18097a = this.f7500a instanceof ReverseGeoCoderParser ? this.f7502c.m18097a(str) : "";
            if (!TextUtils.isEmpty(m18097a)) {
                str2 = m18097a;
                BaseSearch baseSearch = this.f7502c;
                SearchParser searchParser = this.f7500a;
                Object obj = this.f7501b;
                asyncHttpClient = baseSearch.f7495b;
                baseSearch.m18096a(str2, searchParser, obj, asyncHttpClient, this);
            }
        }
        str2 = str;
        BaseSearch baseSearch2 = this.f7502c;
        SearchParser searchParser2 = this.f7500a;
        Object obj2 = this.f7501b;
        asyncHttpClient = baseSearch2.f7495b;
        baseSearch2.m18096a(str2, searchParser2, obj2, asyncHttpClient, this);
    }

    @Override // com.baidu.mapapi.http.HttpClient.ProtoResultCallback
    public void onFailed(HttpClient.HttpStateError httpStateError) {
        this.f7502c.m18105a(httpStateError, this.f7500a, this.f7501b);
    }
}
