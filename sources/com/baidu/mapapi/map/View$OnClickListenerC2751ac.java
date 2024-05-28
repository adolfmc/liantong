package com.baidu.mapapi.map;

import android.view.View;
import com.baidu.platform.comapi.map.MapTextureView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.mapapi.map.ac */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class View$OnClickListenerC2751ac implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ TextureMapView f6522a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC2751ac(TextureMapView textureMapView) {
        this.f6522a = textureMapView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        MapTextureView mapTextureView;
        MapTextureView mapTextureView2;
        MapTextureView mapTextureView3;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        mapTextureView = this.f6522a.f6439b;
        float zoomLevel = mapTextureView.getZoomLevel();
        float f = zoomLevel - 1.0f;
        double d = zoomLevel;
        if (Math.floor(d) != d) {
            f = (float) Math.floor(d);
        }
        mapTextureView2 = this.f6522a.f6439b;
        float max = Math.max(f, mapTextureView2.getController().mMinZoomLevel);
        BaiduMap.mapStatusReason |= 16;
        mapTextureView3 = this.f6522a.f6439b;
        mapTextureView3.setZoomLevel(max);
        NBSActionInstrumentation.onClickEventExit();
    }
}
