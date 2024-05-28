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
/* renamed from: com.baidu.mapapi.map.s */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class View$OnClickListenerC2778s implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ MapView f6569a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC2778s(MapView mapView) {
        this.f6569a = mapView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        MapSurfaceView mapSurfaceView;
        MapSurfaceView mapSurfaceView2;
        MapSurfaceView mapSurfaceView3;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        mapSurfaceView = this.f6569a.f6179e;
        float zoomLevel = mapSurfaceView.getZoomLevel();
        float f = zoomLevel - 1.0f;
        double d = zoomLevel;
        if (Math.floor(d) != d) {
            f = (float) Math.floor(d);
        }
        mapSurfaceView2 = this.f6569a.f6179e;
        float max = Math.max(f, mapSurfaceView2.getController().mMinZoomLevel);
        BaiduMap.mapStatusReason |= 16;
        mapSurfaceView3 = this.f6569a.f6179e;
        mapSurfaceView3.setZoomLevel(max);
        NBSActionInstrumentation.onClickEventExit();
    }
}
