package com.baidu.platform.comapi.map.p153b;

import com.baidu.platform.comapi.map.MapController;
import com.baidu.platform.comapi.map.p153b.p154a.C3025a;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.b.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C3034c implements C3025a.InterfaceC3026a {

    /* renamed from: a */
    final /* synthetic */ C3029b f7830a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C3034c(C3029b c3029b) {
        this.f7830a = c3029b;
    }

    @Override // com.baidu.platform.comapi.map.p153b.p154a.C3025a.InterfaceC3026a
    /* renamed from: a */
    public boolean mo17884a(C3025a c3025a) {
        MapController mapController;
        MapController mapController2;
        MapController mapController3;
        MapController mapController4;
        MapController mapController5;
        MapController mapController6;
        MapController mapController7;
        MapController mapController8;
        MapController mapController9;
        mapController = this.f7830a.f7805b;
        if (mapController.isTwoTouchClickZoomEnabled()) {
            mapController2 = this.f7830a.f7805b;
            mapController2.setActingTwoClickZoom(true);
            mapController3 = this.f7830a.f7805b;
            C3035d gestureMonitor = mapController3.getGestureMonitor();
            mapController4 = this.f7830a.f7805b;
            gestureMonitor.m17876c(mapController4.getZoomLevel() - 1.0f);
            mapController5 = this.f7830a.f7805b;
            mapController5.mapStatusChangeStart();
            mapController6 = this.f7830a.f7805b;
            mapController6.MapMsgProc(8193, 4, 0);
            mapController7 = this.f7830a.f7805b;
            if (mapController7.isNaviMode()) {
                mapController8 = this.f7830a.f7805b;
                if (mapController8.getNaviMapViewListener() != null) {
                    mapController9 = this.f7830a.f7805b;
                    mapController9.getNaviMapViewListener().onAction(521, null);
                }
            }
            return true;
        }
        return false;
    }
}
