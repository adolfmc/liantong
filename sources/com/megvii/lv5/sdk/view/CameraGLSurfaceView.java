package com.megvii.lv5.sdk.view;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import com.megvii.lv5.C5492l;
import com.megvii.lv5.C5553s1;
import com.megvii.lv5.RunnableC5547r1;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CameraGLSurfaceView extends GLSurfaceView {

    /* renamed from: a */
    public Context f13531a;

    /* renamed from: b */
    public C5492l f13532b;

    /* renamed from: c */
    public C5553s1 f13533c;

    /* renamed from: d */
    public C5553s1.InterfaceC5554a f13534d;

    /* renamed from: e */
    public C5553s1.InterfaceC5555b f13535e;

    /* renamed from: f */
    public InterfaceC5601b f13536f;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.view.CameraGLSurfaceView$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class C5600a implements InterfaceC5601b {
        public C5600a() {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.view.CameraGLSurfaceView$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC5601b {
    }

    public CameraGLSurfaceView(Context context) {
        super(context);
        this.f13536f = new C5600a();
        this.f13531a = context.getApplicationContext();
    }

    public CameraGLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f13536f = new C5600a();
        this.f13531a = context.getApplicationContext();
    }

    /* renamed from: a */
    public final void m13039a() {
        C5553s1 c5553s1 = this.f13533c;
        if (c5553s1 != null) {
            queueEvent(new RunnableC5547r1(c5553s1));
            C5553s1 c5553s12 = this.f13533c;
            c5553s12.f13259j = 0;
            c5553s12.f13260k = 0;
            SurfaceTexture surfaceTexture = c5553s12.f13261l;
            if (surfaceTexture != null) {
                surfaceTexture.release();
                c5553s12.f13261l = null;
            }
        }
    }

    /* renamed from: b */
    public void m13037b() {
        C5553s1 c5553s1 = this.f13533c;
        if (c5553s1 != null) {
            c5553s1.f13251b.f12846a.mo13271a(c5553s1);
            C5492l c5492l = c5553s1.f13251b;
            c5492l.f12846a.mo13273a(c5553s1.f13261l);
        }
    }

    public C5553s1 getCameraRender() {
        return this.f13533c;
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m13039a();
    }

    @Override // android.opengl.GLSurfaceView
    public void onPause() {
        super.onPause();
        m13039a();
    }

    /* renamed from: a */
    public void m13038a(C5492l c5492l, C5553s1.InterfaceC5554a interfaceC5554a, C5553s1.InterfaceC5555b interfaceC5555b) {
        this.f13532b = c5492l;
        this.f13534d = interfaceC5554a;
        this.f13535e = interfaceC5555b;
        C5553s1 c5553s1 = new C5553s1(this.f13531a, c5492l, interfaceC5554a, interfaceC5555b);
        this.f13533c = c5553s1;
        c5553s1.f13252c = this.f13536f;
        setPreserveEGLContextOnPause(true);
        setEGLContextClientVersion(2);
        setRenderer(this.f13533c);
        setRenderMode(1);
    }
}
