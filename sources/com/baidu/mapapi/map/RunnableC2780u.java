package com.baidu.mapapi.map;

import android.view.View;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.u */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC2780u implements Runnable {

    /* renamed from: a */
    final /* synthetic */ View f6588a;

    /* renamed from: b */
    final /* synthetic */ MapView f6589b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2780u(MapView mapView, View view) {
        this.f6589b = mapView;
        this.f6588a = view;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f6589b.removeView(this.f6588a);
    }
}
