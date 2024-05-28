package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.platform.comapi.map.InterfaceC3039c;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2766h implements InterfaceC3039c {

    /* renamed from: a */
    final /* synthetic */ BaiduMap f6547a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2766h(BaiduMap baiduMap) {
        this.f6547a = baiduMap;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3039c
    /* renamed from: a */
    public void mo17867a(Bitmap bitmap) {
        BaiduMap.SnapshotReadyCallback snapshotReadyCallback;
        snapshotReadyCallback = this.f6547a.f5931D;
        snapshotReadyCallback.onSnapshotReady(bitmap);
    }
}
