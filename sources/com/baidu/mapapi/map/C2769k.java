package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.platform.comapi.map.InterfaceC3039c;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.k */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C2769k implements InterfaceC3039c {

    /* renamed from: a */
    final /* synthetic */ BaiduMap f6550a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2769k(BaiduMap baiduMap) {
        this.f6550a = baiduMap;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3039c
    /* renamed from: a */
    public void mo17867a(Bitmap bitmap) {
        BaiduMap.SnapshotReadyCallback snapshotReadyCallback;
        snapshotReadyCallback = this.f6550a.f5931D;
        snapshotReadyCallback.onSnapshotReady(bitmap);
    }
}
