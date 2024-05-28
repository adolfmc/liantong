package com.baidu.platform.comapi.map;

import com.baidu.platform.comjni.map.basemap.AppBaseMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.v */
/* loaded from: E:\10201592_dexfile_execute.dex */
class RunnableC3080v implements Runnable {

    /* renamed from: a */
    final /* synthetic */ MapSurfaceView f8017a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3080v(MapSurfaceView mapSurfaceView) {
        this.f8017a = mapSurfaceView;
    }

    @Override // java.lang.Runnable
    public void run() {
        AppBaseMap baseMap;
        boolean z;
        if (this.f8017a.f7659a == null || (baseMap = this.f8017a.f7659a.getBaseMap()) == null) {
            return;
        }
        z = this.f8017a.f7669l;
        baseMap.ShowTrafficMap(z);
    }
}
