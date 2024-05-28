package com.baidu.mapapi.map;

import android.view.View;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.ae */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC2753ae implements Runnable {

    /* renamed from: a */
    final /* synthetic */ View f6524a;

    /* renamed from: b */
    final /* synthetic */ TextureMapView f6525b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2753ae(TextureMapView textureMapView, View view) {
        this.f6525b = textureMapView;
        this.f6524a = view;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f6525b.removeView(this.f6524a);
    }
}
