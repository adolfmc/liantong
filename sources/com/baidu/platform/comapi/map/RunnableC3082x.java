package com.baidu.platform.comapi.map;

import com.baidu.platform.comjni.map.basemap.AppBaseMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.x */
/* loaded from: E:\10201592_dexfile_execute.dex */
class RunnableC3082x implements Runnable {

    /* renamed from: a */
    final /* synthetic */ boolean f8019a;

    /* renamed from: b */
    final /* synthetic */ MapSurfaceView f8020b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3082x(MapSurfaceView mapSurfaceView, boolean z) {
        this.f8020b = mapSurfaceView;
        this.f8019a = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        AppBaseMap baseMap;
        if (this.f8020b.f7659a == null || (baseMap = this.f8020b.f7659a.getBaseMap()) == null) {
            return;
        }
        baseMap.ShowBaseIndoorMap(this.f8019a);
    }
}
