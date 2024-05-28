package com.baidu.mapapi.map;

import com.baidu.mapapi.map.InfoWindow;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2761c implements InfoWindow.InterfaceC2745a {

    /* renamed from: a */
    final /* synthetic */ BaiduMap f6541a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2761c(BaiduMap baiduMap) {
        this.f6541a = baiduMap;
    }

    @Override // com.baidu.mapapi.map.InfoWindow.InterfaceC2745a
    /* renamed from: a */
    public void mo18794a(InfoWindow infoWindow) {
        this.f6541a.hideInfoWindow(infoWindow);
    }

    @Override // com.baidu.mapapi.map.InfoWindow.InterfaceC2745a
    /* renamed from: b */
    public void mo18793b(InfoWindow infoWindow) {
        this.f6541a.m19013a(infoWindow);
    }
}
