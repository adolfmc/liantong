package com.baidu.mapapi.map;

import android.view.View;
import com.baidu.platform.comapi.map.MapSurfaceView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.mapapi.map.t */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class View$OnClickListenerC2779t implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ MapView f6570a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC2779t(MapView mapView) {
        this.f6570a = mapView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        MapSurfaceView mapSurfaceView;
        MapSurfaceView mapSurfaceView2;
        MapSurfaceView mapSurfaceView3;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        mapSurfaceView = this.f6570a.f6179e;
        float zoomLevel = mapSurfaceView.getZoomLevel();
        float f = 1.0f + zoomLevel;
        double d = zoomLevel;
        if (((int) Math.ceil(d)) != ((int) zoomLevel)) {
            f = (float) Math.ceil(d);
        }
        mapSurfaceView2 = this.f6570a.f6179e;
        float min = Math.min(f, mapSurfaceView2.getController().mMaxZoomLevel);
        BaiduMap.mapStatusReason |= 16;
        mapSurfaceView3 = this.f6570a.f6179e;
        mapSurfaceView3.setZoomLevel(min);
        NBSActionInstrumentation.onClickEventExit();
    }
}
