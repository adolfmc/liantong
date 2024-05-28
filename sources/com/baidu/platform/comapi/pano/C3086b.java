package com.baidu.platform.comapi.pano;

import com.baidu.mapapi.http.HttpClient;
import com.baidu.platform.comapi.pano.PanoHttpUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: PanoHttpUtil.java */
/* renamed from: com.baidu.platform.comapi.pano.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C3086b extends HttpClient.ProtoResultCallback {

    /* renamed from: a */
    final /* synthetic */ PanoHttpUtil.InterfaceC3085a f8028a;

    /* renamed from: b */
    final /* synthetic */ PanoHttpUtil f8029b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C3086b(PanoHttpUtil panoHttpUtil, PanoHttpUtil.InterfaceC3085a interfaceC3085a) {
        this.f8029b = panoHttpUtil;
        this.f8028a = interfaceC3085a;
    }

    @Override // com.baidu.mapapi.http.HttpClient.ProtoResultCallback
    public void onSuccess(String str) {
        PanoResult m17715a;
        PanoHttpUtil.InterfaceC3085a interfaceC3085a = this.f8028a;
        m17715a = this.f8029b.m17715a(str);
        interfaceC3085a.mo17712a((PanoHttpUtil.InterfaceC3085a) m17715a);
    }

    @Override // com.baidu.mapapi.http.HttpClient.ProtoResultCallback
    public void onFailed(HttpClient.HttpStateError httpStateError) {
        this.f8028a.mo17713a(httpStateError);
    }
}
