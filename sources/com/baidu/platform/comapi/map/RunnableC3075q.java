package com.baidu.platform.comapi.map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.q */
/* loaded from: E:\10201592_dexfile_execute.dex */
class RunnableC3075q implements Runnable {

    /* renamed from: a */
    final /* synthetic */ MapSurfaceView f8009a;

    /* renamed from: b */
    final /* synthetic */ C3074p f8010b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3075q(C3074p c3074p, MapSurfaceView mapSurfaceView) {
        this.f8010b = c3074p;
        this.f8009a = mapSurfaceView;
    }

    @Override // java.lang.Runnable
    public void run() {
        MapSurfaceView mapSurfaceView = this.f8009a;
        if (mapSurfaceView != null) {
            mapSurfaceView.setBackgroundResource(0);
        }
    }
}
