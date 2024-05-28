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
/* renamed from: com.baidu.mapapi.map.ad */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class View$OnClickListenerC2752ad implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ TextureMapView f6523a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC2752ad(TextureMapView textureMapView) {
        this.f6523a = textureMapView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        MapTextureView mapTextureView;
        MapTextureView mapTextureView2;
        MapTextureView mapTextureView3;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        mapTextureView = this.f6523a.f6439b;
        float zoomLevel = mapTextureView.getZoomLevel();
        float f = 1.0f + zoomLevel;
        double d = zoomLevel;
        if (((int) Math.ceil(d)) != ((int) zoomLevel)) {
            f = (float) Math.ceil(d);
        }
        mapTextureView2 = this.f6523a.f6439b;
        float min = Math.min(f, mapTextureView2.getController().mMaxZoomLevel);
        BaiduMap.mapStatusReason |= 16;
        mapTextureView3 = this.f6523a.f6439b;
        mapTextureView3.setZoomLevel(min);
        NBSActionInstrumentation.onClickEventExit();
    }
}
