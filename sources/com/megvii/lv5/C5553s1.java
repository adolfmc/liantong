package com.megvii.lv5;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import com.megvii.lv5.AbstractC5500m;
import com.megvii.lv5.C5383b;
import com.megvii.lv5.lib.jni.MegBlur;
import com.megvii.lv5.sdk.view.CameraGLSurfaceView;
import java.nio.Buffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.s1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5553s1 implements SurfaceTexture.OnFrameAvailableListener, GLSurfaceView.Renderer, AbstractC5500m.InterfaceC5503c {

    /* renamed from: t */
    public static volatile boolean f13249t;

    /* renamed from: a */
    public Context f13250a;

    /* renamed from: b */
    public C5492l f13251b;

    /* renamed from: c */
    public CameraGLSurfaceView.InterfaceC5601b f13252c;

    /* renamed from: d */
    public C5650x1 f13253d;

    /* renamed from: e */
    public C5635w1 f13254e;

    /* renamed from: l */
    public SurfaceTexture f13261l;

    /* renamed from: p */
    public int[] f13265p;

    /* renamed from: q */
    public InterfaceC5554a f13266q;

    /* renamed from: r */
    public InterfaceC5555b f13267r;

    /* renamed from: f */
    public int f13255f = 1080;

    /* renamed from: g */
    public int f13256g = 810;

    /* renamed from: h */
    public int f13257h = 1080;

    /* renamed from: i */
    public int f13258i = 810;

    /* renamed from: j */
    public int f13259j = 0;

    /* renamed from: k */
    public int f13260k = 0;

    /* renamed from: m */
    public boolean f13262m = true;

    /* renamed from: n */
    public C5666z1 f13263n = new C5666z1();

    /* renamed from: o */
    public int f13264o = -1;

    /* renamed from: s */
    public int f13268s = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.s1$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC5554a {
        void onPreviewFrame(byte[] bArr, Camera camera);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.s1$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC5555b {
        /* renamed from: a */
        void mo12937a(SurfaceTexture surfaceTexture);
    }

    public C5553s1(Context context, C5492l c5492l, InterfaceC5554a interfaceC5554a, InterfaceC5555b interfaceC5555b) {
        this.f13266q = interfaceC5554a;
        this.f13267r = interfaceC5555b;
        this.f13250a = context;
        this.f13251b = c5492l;
        f13249t = false;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        if (f13249t) {
            this.f13268s++;
        }
        if (this.f13268s < 2) {
            C5383b c5383b = C5383b.C5384a.f12381a;
            int i = this.f13264o;
            int i2 = this.f13265p[1];
            long j = c5383b.f12380a.f12359a;
            if (j != 0) {
                MegBlur.nativeProcess(j, i, i2);
            }
        }
        GLES20.glDisable(2929);
        GLES20.glDisable(2960);
        GLES20.glDisable(3089);
        GLES20.glDisable(2884);
        GLES20.glViewport(0, 0, this.f13259j, this.f13260k);
        C5650x1 c5650x1 = this.f13253d;
        int i3 = this.f13265p[1];
        boolean m13444b = this.f13251b.m13444b();
        GLES20.glUseProgram(c5650x1.f13888d);
        synchronized (c5650x1.f13885a) {
            while (!c5650x1.f13885a.isEmpty()) {
                c5650x1.f13885a.removeFirst().run();
            }
        }
        if (c5650x1.f13892h) {
            c5650x1.f13893i.position(0);
            GLES20.glVertexAttribPointer(c5650x1.f13889e, 2, 5126, false, 0, (Buffer) c5650x1.f13893i);
            GLES20.glEnableVertexAttribArray(c5650x1.f13889e);
            c5650x1.f13895k = m13444b ? C5379a2.f12360a : C5379a2.f12363d;
            c5650x1.f13894j.clear();
            c5650x1.f13894j.put(c5650x1.f13895k).position(0);
            GLES20.glVertexAttribPointer(c5650x1.f13891g, 2, 5126, false, 0, (Buffer) c5650x1.f13894j);
            GLES20.glEnableVertexAttribArray(c5650x1.f13891g);
            if (i3 != -1) {
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, i3);
                GLES20.glUniform1i(c5650x1.f13890f, 0);
            }
            GLES20.glDrawArrays(5, 0, 4);
            GLES20.glDisableVertexAttribArray(c5650x1.f13889e);
            GLES20.glDisableVertexAttribArray(c5650x1.f13891g);
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
        C5492l c5492l = this.f13251b;
        int i = c5492l.f12848c;
        int i2 = c5492l.f12849d;
        CameraGLSurfaceView.InterfaceC5601b interfaceC5601b = this.f13252c;
        if (interfaceC5601b != null) {
            CameraGLSurfaceView.this.queueEvent(new RunnableC5539q1(this, bArr, i, i2, camera));
        }
        CameraGLSurfaceView.this.requestRender();
    }

    /*  JADX ERROR: Failed to decode insn: 0x041C: FILLED_NEW_ARRAY_RANGE , method: com.megvii.lv5.s1.onSurfaceChanged(javax.microedition.khronos.opengles.GL10, int, int):void
        java.lang.NullPointerException
        	at jadx.core.dex.instructions.InsnDecoder.filledNewArray(InsnDecoder.java:549)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:479)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(javax.microedition.khronos.opengles.GL10 r28, int r29, int r30) {
        /*
            Method dump skipped, instructions count: 1105
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5553s1.onSurfaceChanged(javax.microedition.khronos.opengles.GL10, int, int):void");
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
    }
}
