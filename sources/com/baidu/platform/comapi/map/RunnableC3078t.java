package com.baidu.platform.comapi.map;

import com.baidu.platform.comjni.map.basemap.AppBaseMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.t */
/* loaded from: E:\10201592_dexfile_execute.dex */
class RunnableC3078t implements Runnable {

    /* renamed from: a */
    final /* synthetic */ MapSurfaceView f8015a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3078t(MapSurfaceView mapSurfaceView) {
        this.f8015a = mapSurfaceView;
    }

    @Override // java.lang.Runnable
    public void run() {
        AppBaseMap baseMap;
        boolean z;
        if (this.f8015a.f7659a == null || (baseMap = this.f8015a.f7659a.getBaseMap()) == null) {
            return;
        }
        z = this.f8015a.f7670m;
        baseMap.ShowSatelliteMap(z);
    }
}
