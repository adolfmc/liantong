package com.megvii.lv5;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import com.megvii.lv5.AbstractC5500m;
import com.megvii.lv5.C5553s1;
import com.megvii.lv5.sdk.view.CameraGLSurfaceViewNew;
import java.nio.Buffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.v1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5627v1 implements SurfaceTexture.OnFrameAvailableListener, GLSurfaceView.Renderer, AbstractC5500m.InterfaceC5503c {

    /* renamed from: n */
    public static volatile boolean f13755n;

    /* renamed from: a */
    public Context f13756a;

    /* renamed from: b */
    public C5492l f13757b;

    /* renamed from: c */
    public CameraGLSurfaceViewNew.InterfaceC5603b f13758c;

    /* renamed from: d */
    public C5659y1 f13759d;

    /* renamed from: g */
    public SurfaceTexture f13762g;

    /* renamed from: k */
    public C5553s1.InterfaceC5554a f13766k;

    /* renamed from: l */
    public C5553s1.InterfaceC5555b f13767l;

    /* renamed from: m */
    public int f13768m;

    /* renamed from: e */
    public int f13760e = 0;

    /* renamed from: f */
    public int f13761f = 0;

    /* renamed from: h */
    public boolean f13763h = true;

    /* renamed from: i */
    public C5666z1 f13764i = new C5666z1();

    /* renamed from: j */
    public int f13765j = -1;

    public C5627v1(Context context, C5492l c5492l, C5553s1.InterfaceC5554a interfaceC5554a, C5553s1.InterfaceC5555b interfaceC5555b) {
        this.f13768m = 0;
        this.f13766k = interfaceC5554a;
        this.f13767l = interfaceC5555b;
        this.f13756a = context;
        this.f13757b = c5492l;
        f13755n = false;
        this.f13768m = 0;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        GLES20.glDisable(2929);
        GLES20.glDisable(2960);
        GLES20.glDisable(3089);
        GLES20.glDisable(2884);
        GLES20.glViewport(0, 0, this.f13760e, this.f13761f);
        C5659y1 c5659y1 = this.f13759d;
        int i = this.f13765j;
        boolean m13444b = this.f13757b.m13444b();
        GLES20.glUseProgram(c5659y1.f13926d);
        synchronized (c5659y1.f13923a) {
            while (!c5659y1.f13923a.isEmpty()) {
                c5659y1.f13923a.removeFirst().run();
            }
        }
        if (c5659y1.f13930h) {
            c5659y1.f13931i.position(0);
            GLES20.glVertexAttribPointer(c5659y1.f13927e, 2, 5126, false, 0, (Buffer) c5659y1.f13931i);
            GLES20.glEnableVertexAttribArray(c5659y1.f13927e);
            c5659y1.f13933k = m13444b ? C5379a2.f12360a : C5379a2.f12363d;
            c5659y1.f13932j.clear();
            c5659y1.f13932j.put(c5659y1.f13933k).position(0);
            GLES20.glVertexAttribPointer(c5659y1.f13929g, 2, 5126, false, 0, (Buffer) c5659y1.f13932j);
            GLES20.glEnableVertexAttribArray(c5659y1.f13929g);
            if (i != -1) {
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, i);
                GLES20.glUniform1i(c5659y1.f13928f, 0);
            }
            GLES20.glDrawArrays(5, 0, 4);
            GLES20.glDisableVertexAttribArray(c5659y1.f13927e);
            GLES20.glDisableVertexAttribArray(c5659y1.f13929g);
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, 0);
        }
        GLES20.glFlush();
        GLES20.glFinish();
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
    }

    @Override // com.megvii.lv5.AbstractC5500m.InterfaceC5503c
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        C5492l c5492l = this.f13757b;
        int i = c5492l.f12848c;
        int i2 = c5492l.f12849d;
        CameraGLSurfaceViewNew.InterfaceC5603b interfaceC5603b = this.f13758c;
        if (interfaceC5603b != null) {
            CameraGLSurfaceViewNew.this.queueEvent(new RunnableC5614t1(this, bArr, i, i2, camera));
        }
        CameraGLSurfaceViewNew.this.requestRender();
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        if (this.f13760e == i && this.f13761f == i2 && !this.f13763h) {
            return;
        }
        this.f13760e = 0;
        this.f13761f = 0;
        SurfaceTexture surfaceTexture = this.f13762g;
        if (surfaceTexture != null) {
            surfaceTexture.release();
            this.f13762g = null;
        }
        if (this.f13763h) {
            this.f13763h = false;
        }
        this.f13760e = i;
        this.f13761f = i2;
        this.f13759d = new C5659y1(this.f13756a);
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glEnable(2929);
        this.f13765j = C5379a2.m13619a(480, 640);
        SurfaceTexture surfaceTexture2 = this.f13762g;
        if (surfaceTexture2 != null) {
            surfaceTexture2.release();
        }
        SurfaceTexture surfaceTexture3 = new SurfaceTexture(10);
        this.f13762g = surfaceTexture3;
        C5553s1.InterfaceC5555b interfaceC5555b = this.f13767l;
        if (interfaceC5555b != null) {
            try {
                interfaceC5555b.mo12937a(surfaceTexture3);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        C5659y1 c5659y1 = this.f13759d;
        c5659y1.f13926d = C5379a2.m13616a(c5659y1.f13924b, c5659y1.f13925c);
        String str = "The program ID for image is " + c5659y1.f13926d;
        c5659y1.f13927e = GLES20.glGetAttribLocation(c5659y1.f13926d, "position");
        c5659y1.f13928f = GLES20.glGetUniformLocation(c5659y1.f13926d, "inputImageTexture");
        c5659y1.f13929g = GLES20.glGetAttribLocation(c5659y1.f13926d, "inputTextureCoordinate");
        c5659y1.f13930h = true;
        GLES20.glViewport(0, 0, i, i2);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
    }
}
