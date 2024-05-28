package com.baidu.platform.comapi.map;

import android.view.View;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.i */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class View$OnLayoutChangeListenerC3067i implements View.OnLayoutChangeListener {

    /* renamed from: a */
    final /* synthetic */ TextureView$SurfaceTextureListenerC3057h f7969a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnLayoutChangeListenerC3067i(TextureView$SurfaceTextureListenerC3057h textureView$SurfaceTextureListenerC3057h) {
        this.f7969a = textureView$SurfaceTextureListenerC3057h;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        TextureView$SurfaceTextureListenerC3057h textureView$SurfaceTextureListenerC3057h = this.f7969a;
        textureView$SurfaceTextureListenerC3057h.onSurfaceTextureSizeChanged(textureView$SurfaceTextureListenerC3057h.getSurfaceTexture(), i3 - i, i4 - i2);
    }
}
