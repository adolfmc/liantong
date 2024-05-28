package com.baidu.mapapi.http;

import com.baidu.mapapi.http.AsyncHttpClient;
import com.baidu.mapapi.http.HttpClient;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: AsyncHttpClient.java */
/* renamed from: com.baidu.mapapi.http.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2743a extends AsyncHttpClient.AbstractRunnableC2741a {

    /* renamed from: a */
    final /* synthetic */ HttpClient.ProtoResultCallback f5883a;

    /* renamed from: b */
    final /* synthetic */ String f5884b;

    /* renamed from: c */
    final /* synthetic */ AsyncHttpClient f5885c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2743a(AsyncHttpClient asyncHttpClient, HttpClient.ProtoResultCallback protoResultCallback, String str) {
        super(null);
        this.f5885c = asyncHttpClient;
        this.f5883a = protoResultCallback;
        this.f5884b = str;
    }

    @Override // com.baidu.mapapi.http.AsyncHttpClient.AbstractRunnableC2741a
    /* renamed from: a */
    public void mo19028a() {
        int i;
        int i2;
        HttpClient httpClient = new HttpClient("GET", this.f5883a);
        i = this.f5885c.f5873a;
        httpClient.setMaxTimeOut(i);
        i2 = this.f5885c.f5874b;
        httpClient.setReadTimeOut(i2);
        httpClient.request(this.f5884b);
    }
}
