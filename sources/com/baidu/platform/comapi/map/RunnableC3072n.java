package com.baidu.platform.comapi.map;

import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.MapController;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.n */
/* loaded from: E:\10201592_dexfile_execute.dex */
class RunnableC3072n implements Runnable {

    /* renamed from: a */
    final /* synthetic */ MapStatus f7982a;

    /* renamed from: b */
    final /* synthetic */ MapController.HandlerC2987b f7983b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3072n(MapController.HandlerC2987b handlerC2987b, MapStatus mapStatus) {
        this.f7983b = handlerC2987b;
        this.f7982a = mapStatus;
    }

    @Override // java.lang.Runnable
    public void run() {
        GeoPoint fromPixels = (MapController.this.getMapView() == null || MapController.this.getMapView().getProjection() == null) ? null : MapController.this.f7619i.get().getProjection().fromPixels(this.f7982a.winRound.left + (MapController.this.getScreenWidth() / 2), this.f7982a.winRound.top + (MapController.this.getScreenHeight() / 2));
        if (fromPixels != null) {
            MapController.CleanAfterDBClick(MapController.this.f7630u, (float) fromPixels.getLongitudeE6(), (float) fromPixels.getLatitudeE6());
        }
        MapController.this.f7599P = false;
    }
}
