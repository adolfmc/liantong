package com.baidu.mapapi.map;

import android.view.View;
import com.baidu.mapsdkplatform.comapi.map.C2948x;
import com.baidu.platform.comapi.map.MapSurfaceView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.mapapi.map.ai */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class View$OnClickListenerC2757ai implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ WearMapView f6535a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC2757ai(WearMapView wearMapView) {
        this.f6535a = wearMapView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        MapSurfaceView mapSurfaceView;
        MapSurfaceView mapSurfaceView2;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        mapSurfaceView = this.f6535a.f6495f;
        C2948x m18378E = mapSurfaceView.getBaseMap().m18378E();
        m18378E.f7369a -= 1.0f;
        mapSurfaceView2 = this.f6535a.f6495f;
        mapSurfaceView2.getBaseMap().m18339a(m18378E, 300);
        NBSActionInstrumentation.onClickEventExit();
    }
}
