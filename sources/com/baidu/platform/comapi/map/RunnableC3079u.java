package com.baidu.platform.comapi.map;

import com.baidu.platform.comjni.map.basemap.AppBaseMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.u */
/* loaded from: E:\10201592_dexfile_execute.dex */
class RunnableC3079u implements Runnable {

    /* renamed from: a */
    final /* synthetic */ MapSurfaceView f8016a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3079u(MapSurfaceView mapSurfaceView) {
        this.f8016a = mapSurfaceView;
    }

    @Override // java.lang.Runnable
    public void run() {
        AppBaseMap baseMap;
        boolean z;
        if (this.f8016a.f7659a == null || (baseMap = this.f8016a.f7659a.getBaseMap()) == null) {
            return;
        }
        z = this.f8016a.f7669l;
        baseMap.ShowTrafficMap(z);
    }
}
