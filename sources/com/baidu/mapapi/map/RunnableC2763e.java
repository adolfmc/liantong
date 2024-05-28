package com.baidu.mapapi.map;

import com.baidu.mapapi.map.MapViewLayoutParams;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
class RunnableC2763e implements Runnable {

    /* renamed from: a */
    final /* synthetic */ InfoWindow f6543a;

    /* renamed from: b */
    final /* synthetic */ C2762d f6544b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2763e(C2762d c2762d, InfoWindow infoWindow) {
        this.f6544b = c2762d;
        this.f6543a = infoWindow;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f6543a.f6116c.setLayoutParams(new MapViewLayoutParams.Builder().layoutMode(MapViewLayoutParams.ELayoutMode.mapMode).position(this.f6543a.f6117d).yOffset(this.f6543a.f6120g).build());
    }
}
