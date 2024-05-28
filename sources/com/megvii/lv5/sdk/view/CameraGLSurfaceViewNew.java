package com.megvii.lv5.sdk.view;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import com.megvii.lv5.C5492l;
import com.megvii.lv5.C5553s1;
import com.megvii.lv5.C5627v1;
import com.megvii.lv5.RunnableC5620u1;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CameraGLSurfaceViewNew extends GLSurfaceView {

    /* renamed from: a */
    public Context f13538a;

    /* renamed from: b */
    public C5492l f13539b;

    /* renamed from: c */
    public C5627v1 f13540c;

    /* renamed from: d */
    public C5553s1.InterfaceC5554a f13541d;

    /* renamed from: e */
    public C5553s1.InterfaceC5555b f13542e;

    /* renamed from: f */
    public InterfaceC5603b f13543f;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.view.CameraGLSurfaceViewNew$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class C5602a implements InterfaceC5603b {
        public C5602a() {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.view.CameraGLSurfaceViewNew$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC5603b {
    }

    public CameraGLSurfaceViewNew(Context context) {
        super(context);
        this.f13543f = new C5602a();
        this.f13538a = context.getApplicationContext();
    }

    public CameraGLSurfaceViewNew(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f13543f = new C5602a();
        this.f13538a = context.getApplicationContext();
    }

    /* renamed from: a */
    public final void m13036a() {
        C5627v1 c5627v1 = this.f13540c;
        if (c5627v1 != null) {
            queueEvent(new RunnableC5620u1(c5627v1));
            C5627v1 c5627v12 = this.f13540c;
            c5627v12.f13760e = 0;
            c5627v12.f13761f = 0;
            SurfaceTexture surfaceTexture = c5627v12.f13762g;
            if (surfaceTexture != null) {
                surfaceTexture.release();
                c5627v12.f13762g = null;
            }
        }
    }

    public C5627v1 getCameraRender() {
        return this.f13540c;
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m13036a();
    }

    @Override // android.opengl.GLSurfaceView
    public void onPause() {
        super.onPause();
        m13036a();
    }
}
