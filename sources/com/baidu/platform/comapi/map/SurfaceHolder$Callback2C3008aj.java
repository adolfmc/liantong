package com.baidu.platform.comapi.map;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.baidu.platform.comapi.map.C3006ai;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.aj */
/* loaded from: E:\10201592_dexfile_execute.dex */
class SurfaceHolder$Callback2C3008aj extends SurfaceView implements SurfaceHolder.Callback2 {

    /* renamed from: k */
    protected InterfaceSurfaceHolder$Callback2C3005ah f7740k;

    public SurfaceHolder$Callback2C3008aj(Context context) {
        super(context);
        mo17975a(context, C3006ai.EnumC3007a.OPENGL_ES, true);
    }

    public SurfaceHolder$Callback2C3008aj(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo17975a(context, C3006ai.EnumC3007a.OPENGL_ES, true);
    }

    public SurfaceHolder$Callback2C3008aj(Context context, C3006ai.EnumC3007a enumC3007a) {
        super(context);
        mo17975a(context, enumC3007a, true);
    }

    public SurfaceHolder$Callback2C3008aj(Context context, C3006ai.EnumC3007a enumC3007a, boolean z) {
        super(context);
        mo17975a(context, enumC3007a, z);
    }

    public SurfaceHolder$Callback2C3008aj(Context context, boolean z) {
        super(context);
        mo17975a(context, C3006ai.EnumC3007a.OPENGL_ES, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public InterfaceSurfaceHolder$Callback2C3005ah mo17974a(C3006ai.EnumC3007a enumC3007a, boolean z, Context context) {
        return C3006ai.m17976a(this, enumC3007a, z, context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo17975a(Context context, C3006ai.EnumC3007a enumC3007a, boolean z) {
        if (this.f7740k != null) {
            return;
        }
        this.f7740k = mo17974a(enumC3007a, z, context);
        getHolder().addCallback(this);
    }

    public Bitmap captureImageFromSurface(int i, int i2, int i3, int i4, Object obj, Bitmap.Config config) {
        return this.f7740k.mo17862a(i, i2, i3, i4, obj, config);
    }

    public int getDebugFlags() {
        return this.f7740k.mo17842f();
    }

    public InterfaceSurfaceHolder$Callback2C3005ah getRenderControl() {
        return this.f7740k;
    }

    public int getRenderMode() {
        return this.f7740k.mo17840g();
    }

    public C3006ai.EnumC3007a getViewType() {
        InterfaceSurfaceHolder$Callback2C3005ah interfaceSurfaceHolder$Callback2C3005ah = this.f7740k;
        return interfaceSurfaceHolder$Callback2C3005ah != null ? interfaceSurfaceHolder$Callback2C3005ah.mo17854b() : C3006ai.EnumC3007a.AUTO;
    }

    @Override // android.view.SurfaceView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f7740k.mo17834k();
    }

    @Override // android.view.SurfaceView, android.view.View
    public void onDetachedFromWindow() {
        this.f7740k.mo17833l();
        super.onDetachedFromWindow();
    }

    public void onPause() {
        this.f7740k.mo17836i();
    }

    public void onResume() {
        this.f7740k.mo17835j();
    }

    public void queueEvent(Runnable runnable) {
        this.f7740k.mo17856a(runnable);
    }

    public void requestRender() {
        this.f7740k.mo17838h();
    }

    public void setDebugFlags(int i) {
        this.f7740k.mo17853b(i);
    }

    public void setRenderMode(int i) {
        this.f7740k.mo17846d(i);
    }

    public void setRenderer(InterfaceC3015aq interfaceC3015aq) {
        this.f7740k.mo17860a(interfaceC3015aq);
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.f7740k.surfaceChanged(surfaceHolder, i, i2, i3);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.f7740k.surfaceCreated(surfaceHolder);
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.f7740k.surfaceDestroyed(surfaceHolder);
    }

    @Deprecated
    public void surfaceRedrawNeeded(SurfaceHolder surfaceHolder) {
    }

    @TargetApi(26)
    public void surfaceRedrawNeededAsync(SurfaceHolder surfaceHolder, Runnable runnable) {
        this.f7740k.surfaceRedrawNeededAsync(surfaceHolder, runnable);
    }
}
