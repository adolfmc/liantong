package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.platform.comapi.map.InterfaceC3039c;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.j */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C2768j implements InterfaceC3039c {

    /* renamed from: a */
    final /* synthetic */ BaiduMap f6549a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2768j(BaiduMap baiduMap) {
        this.f6549a = baiduMap;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3039c
    /* renamed from: a */
    public void mo17867a(Bitmap bitmap) {
        BaiduMap.SnapshotReadyCallback snapshotReadyCallback;
        snapshotReadyCallback = this.f6549a.f5931D;
        snapshotReadyCallback.onSnapshotReady(bitmap);
    }
}