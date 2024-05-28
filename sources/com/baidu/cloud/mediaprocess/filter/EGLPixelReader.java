package com.baidu.cloud.mediaprocess.filter;

import android.opengl.EGLContext;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.baidu.cloud.mediaprocess.basefilters.ImageFilter;
import com.baidu.cloud.mediaprocess.basefilters.VideoARGBFilter;
import com.baidu.cloud.mediaprocess.gles.EglCore;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class EGLPixelReader {

    /* renamed from: a */
    public int f4669a;

    /* renamed from: b */
    public int f4670b;

    /* renamed from: c */
    public int f4671c;

    /* renamed from: d */
    public int f4672d;

    /* renamed from: e */
    public ByteBuffer f4673e;

    /* renamed from: f */
    public OnPixelReadCallback f4674f;

    /* renamed from: g */
    public WorkThread f4675g;

    /* renamed from: h */
    public EGLContext f4676h;

    /* renamed from: i */
    public boolean f4677i;

    /* renamed from: j */
    public PixelFormat f4678j;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnPixelReadCallback {
        void onPixelRead(byte[] bArr, int i, int i2);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum PixelFormat {
        PIXEL_FORMAT_ARGB,
        PIXEL_FORMAT_RGBA
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class WorkThread extends Thread {

        /* renamed from: a */
        public Handler f4680a = null;

        /* renamed from: b */
        public volatile boolean f4681b = false;

        /* renamed from: c */
        public Object[] f4682c = new Object[0];

        /* renamed from: d */
        public volatile boolean f4683d = false;

        /* renamed from: e */
        public ImageFilter f4684e = null;

        /* renamed from: f */
        public EglCore f4685f = null;

        /* renamed from: g */
        public final float[] f4686g = new float[16];

        /* renamed from: h */
        public EGLSurface f4687h = null;

        /* renamed from: i */
        public long f4688i = 0;

        /* renamed from: j */
        public long f4689j = 0;

        /* renamed from: k */
        public int f4690k = 0;

        /* renamed from: l */
        public float f4691l = 0.0f;

        /* renamed from: m */
        public float f4692m = 0.0f;

        /* renamed from: n */
        public long f4693n = 0;

        /* renamed from: o */
        public long f4694o = 0;

        public /* synthetic */ WorkThread(C25431 c25431) {
        }

        /* renamed from: a */
        public void m19921a() {
            this.f4680a.sendMessage(this.f4680a.obtainMessage(2));
        }

        /* renamed from: a */
        public boolean m19920a(int i, int i2, int i3) {
            if (this.f4681b) {
                return false;
            }
            EGLPixelReader eGLPixelReader = EGLPixelReader.this;
            if (i2 != eGLPixelReader.f4669a || i3 != eGLPixelReader.f4670b) {
                EGLPixelReader eGLPixelReader2 = EGLPixelReader.this;
                eGLPixelReader2.f4669a = i2;
                eGLPixelReader2.f4670b = i3;
                int i4 = eGLPixelReader2.f4669a;
                int i5 = eGLPixelReader2.f4670b;
                int i6 = eGLPixelReader2.f4671c;
                int i7 = eGLPixelReader2.f4672d;
                Matrix.setIdentityM(this.f4686g, 0);
                float f = i4 / i6;
                float f2 = i5 / i7;
                if (f > f2) {
                    Matrix.scaleM(this.f4686g, 0, f / f2, 1.0f, 1.0f);
                } else if (f < f2) {
                    Matrix.scaleM(this.f4686g, 0, 1.0f, f2 / f, 1.0f);
                }
                Matrix.scaleM(this.f4686g, 0, 1.0f, -1.0f, 1.0f);
                this.f4684e.setMVPMatrix(this.f4686g);
            }
            Message obtainMessage = this.f4680a.obtainMessage(1);
            obtainMessage.arg1 = i;
            this.f4680a.sendMessage(obtainMessage);
            return true;
        }

        /* renamed from: b */
        public void m19917b() {
            synchronized (this.f4682c) {
                while (!this.f4683d) {
                    try {
                        this.f4682c.wait(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Looper.prepare();
            this.f4685f = new EglCore(EGLPixelReader.this.f4676h, 0);
            EglCore eglCore = this.f4685f;
            EGLPixelReader eGLPixelReader = EGLPixelReader.this;
            this.f4687h = eglCore.createOffscreenSurface(eGLPixelReader.f4671c, eGLPixelReader.f4672d);
            this.f4685f.makeCurrent(this.f4687h);
            this.f4684e = EGLPixelReader.this.f4678j == PixelFormat.PIXEL_FORMAT_ARGB ? new VideoARGBFilter() : new ImageFilter();
            this.f4684e.init();
            ImageFilter imageFilter = this.f4684e;
            EGLPixelReader eGLPixelReader2 = EGLPixelReader.this;
            imageFilter.onOutputSizeChanged(eGLPixelReader2.f4671c, eGLPixelReader2.f4672d);
            this.f4680a = new Handler() { // from class: com.baidu.cloud.mediaprocess.filter.EGLPixelReader.WorkThread.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    int i = message.what;
                    if (i != 1) {
                        if (i != 2) {
                            return;
                        }
                        WorkThread.this.f4683d = false;
                        WorkThread.this.f4684e.release();
                        WorkThread.this.f4685f.makeNothingCurrent();
                        WorkThread.this.f4685f.releaseSurface(WorkThread.this.f4687h);
                        WorkThread.this.f4685f.release();
                        Log.d("EGLPixelReader", String.format("(averageTrackTime, averageReadTime) = (%f, %f)", Float.valueOf(WorkThread.this.f4691l), Float.valueOf(WorkThread.this.f4692m)));
                        Looper.myLooper().quit();
                        return;
                    }
                    WorkThread.this.f4681b = true;
                    int i2 = message.arg1;
                    WorkThread.this.f4688i = System.currentTimeMillis();
                    WorkThread.this.f4685f.makeCurrent(WorkThread.this.f4687h);
                    WorkThread.this.f4684e.draw(i2, 0);
                    WorkThread.this.f4685f.swapBuffers(WorkThread.this.f4687h);
                    EGLPixelReader eGLPixelReader3 = EGLPixelReader.this;
                    GLES20.glReadPixels(0, 0, eGLPixelReader3.f4671c, eGLPixelReader3.f4672d, 6408, 5121, eGLPixelReader3.f4673e);
                    WorkThread.this.f4689j = System.currentTimeMillis();
                    WorkThread workThread = WorkThread.this;
                    workThread.f4690k = (int) (workThread.f4689j - workThread.f4688i);
                    workThread.f4693n++;
                    float f = workThread.f4692m;
                    long j = workThread.f4693n;
                    workThread.f4692m = ((f * ((float) (j - 1))) + workThread.f4690k) / ((float) j);
                    if (EGLPixelReader.this.f4674f != null) {
                        workThread.f4688i = System.currentTimeMillis();
                        EGLPixelReader eGLPixelReader4 = EGLPixelReader.this;
                        OnPixelReadCallback onPixelReadCallback = eGLPixelReader4.f4674f;
                        byte[] array = eGLPixelReader4.f4673e.array();
                        EGLPixelReader eGLPixelReader5 = EGLPixelReader.this;
                        onPixelReadCallback.onPixelRead(array, eGLPixelReader5.f4671c, eGLPixelReader5.f4672d);
                        WorkThread.this.f4689j = System.currentTimeMillis();
                        WorkThread workThread2 = WorkThread.this;
                        workThread2.f4690k = (int) (workThread2.f4689j - workThread2.f4688i);
                        int i3 = workThread2.f4690k;
                        if (i3 > 0) {
                            float f2 = workThread2.f4691l;
                            long j2 = workThread2.f4694o;
                            long j3 = j2 + 1;
                            workThread2.f4694o = j3;
                            workThread2.f4691l = ((f2 * ((float) j2)) + i3) / ((float) j3);
                        }
                    }
                    WorkThread.this.f4681b = false;
                }
            };
            this.f4683d = true;
            synchronized (this.f4682c) {
                this.f4682c.notifyAll();
            }
            Looper.loop();
        }
    }

    public EGLPixelReader(int i, int i2) {
        this(i, i2, PixelFormat.PIXEL_FORMAT_ARGB);
    }

    public EGLPixelReader(int i, int i2, PixelFormat pixelFormat) {
        this.f4669a = 0;
        this.f4670b = 0;
        this.f4671c = 0;
        this.f4672d = 0;
        this.f4673e = null;
        this.f4674f = null;
        this.f4675g = null;
        this.f4676h = null;
        this.f4677i = false;
        PixelFormat pixelFormat2 = PixelFormat.PIXEL_FORMAT_ARGB;
        this.f4678j = pixelFormat2;
        if (pixelFormat != pixelFormat2 && pixelFormat != PixelFormat.PIXEL_FORMAT_RGBA) {
            throw new RuntimeException("PixelFormat could only be PIXEL_FORMAT_ARGB or PIXEL_FORMAT_RGBA");
        }
        this.f4678j = pixelFormat;
        this.f4671c = i;
        this.f4672d = i2;
        this.f4673e = ByteBuffer.allocateDirect(this.f4671c * this.f4672d * 4);
        this.f4673e.order(ByteOrder.nativeOrder());
        this.f4677i = false;
    }

    /* renamed from: a */
    public void m19924a() {
        if (this.f4677i) {
            this.f4675g.m19921a();
            try {
                this.f4675g.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.f4673e = null;
            this.f4677i = false;
        }
    }

    /* renamed from: a */
    public void m19923a(int i, int i2, int i3) {
        this.f4675g.m19920a(i, i2, i3);
    }

    /* renamed from: a */
    public void m19922a(EGLContext eGLContext) {
        if (this.f4677i) {
            return;
        }
        this.f4676h = eGLContext;
        this.f4675g = new WorkThread(null);
        this.f4675g.start();
        this.f4675g.m19917b();
        this.f4677i = true;
    }

    public void setOnPixelReadCallback(OnPixelReadCallback onPixelReadCallback) {
        this.f4674f = onPixelReadCallback;
    }
}
