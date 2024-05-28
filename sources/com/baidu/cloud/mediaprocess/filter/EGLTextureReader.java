package com.baidu.cloud.mediaprocess.filter;

import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.MediaCodec;
import android.opengl.EGLContext;
import android.opengl.EGLSurface;
import android.opengl.Matrix;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Surface;
import com.baidu.cloud.framework.OutPort;
import com.baidu.cloud.framework.OutPortFactory;
import com.baidu.cloud.framework.frame.VideoFrameBuffer;
import com.baidu.cloud.mediaprocess.basefilters.ImageFilter;
import com.baidu.cloud.mediaprocess.basefilters.VideoRGBA2NV21Filter;
import com.baidu.cloud.mediaprocess.gles.EglCore;
import com.baidu.cloud.mediaprocess.gles.WindowSurface;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class EGLTextureReader implements ITextureReader {

    /* renamed from: c */
    public int f4699c;

    /* renamed from: d */
    public int f4700d;

    /* renamed from: k */
    public boolean f4707k;

    /* renamed from: m */
    public volatile Surface f4709m;

    /* renamed from: a */
    public int f4697a = 0;

    /* renamed from: b */
    public int f4698b = 0;

    /* renamed from: e */
    public OnPixelReadCallback f4701e = null;

    /* renamed from: f */
    public WorkThread f4702f = null;

    /* renamed from: g */
    public EGLContext f4703g = null;

    /* renamed from: h */
    public volatile boolean f4704h = true;

    /* renamed from: i */
    public volatile boolean f4705i = true;

    /* renamed from: j */
    public volatile int f4706j = 0;

    /* renamed from: l */
    public volatile long f4708l = -1;

    /* renamed from: n */
    public volatile boolean f4710n = false;

    /* renamed from: o */
    public volatile boolean f4711o = false;

    /* renamed from: p */
    public volatile boolean f4712p = false;

    /* renamed from: q */
    public volatile boolean f4713q = true;

    /* renamed from: r */
    public OutPort<VideoFrameBuffer> f4714r = new OutPortFactory().createOutPort();

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
    public class WorkThread extends Thread {

        /* renamed from: a */
        public Handler f4715a = null;

        /* renamed from: b */
        public volatile boolean f4716b = false;

        /* renamed from: c */
        public Object[] f4717c = new Object[0];

        /* renamed from: d */
        public volatile boolean f4718d = false;

        /* renamed from: e */
        public ImageFilter f4719e = null;

        /* renamed from: f */
        public ImageFilter f4720f = null;

        /* renamed from: g */
        public EglCore f4721g = null;

        /* renamed from: h */
        public final float[] f4722h = new float[16];

        /* renamed from: i */
        public EGLSurface f4723i = null;

        /* renamed from: j */
        public long f4724j = 0;

        /* renamed from: k */
        public long f4725k = 0;

        /* renamed from: l */
        public int f4726l = 0;

        /* renamed from: m */
        public float f4727m = 0.0f;

        /* renamed from: n */
        public float f4728n = 0.0f;

        /* renamed from: o */
        public long f4729o = 0;

        /* renamed from: p */
        public long f4730p = 0;

        /* renamed from: q */
        public volatile long f4731q = -1;

        /* renamed from: r */
        public volatile long f4732r = -1;

        /* renamed from: s */
        public WindowSurface f4733s;

        /* renamed from: t */
        public volatile Surface f4734t;

        public /* synthetic */ WorkThread(C25451 c25451) {
        }

        /* renamed from: a */
        public static /* synthetic */ void m19906a(WorkThread workThread) {
            ImageFilter imageFilter = workThread.f4719e;
            if (imageFilter != null) {
                imageFilter.release();
            }
            ImageFilter imageFilter2 = workThread.f4720f;
            if (imageFilter2 != null) {
                imageFilter2.release();
            }
            workThread.m19907a((Surface) null);
            workThread.f4721g.makeNothingCurrent();
            EGLSurface eGLSurface = workThread.f4723i;
            if (eGLSurface != null) {
                workThread.f4721g.releaseSurface(eGLSurface);
            }
            workThread.f4721g.release();
        }

        /* renamed from: a */
        public void m19911a() {
            this.f4715a.sendMessage(this.f4715a.obtainMessage(2));
        }

        /* renamed from: a */
        public final void m19908a(int i, int i2, int i3, int i4, int i5, boolean z, boolean z2) {
            int i6;
            int i7;
            Matrix.setIdentityM(this.f4722h, 0);
            Matrix.scaleM(this.f4722h, 0, z ? -1.0f : 1.0f, z2 ? -1.0f : 1.0f, 1.0f);
            Matrix.rotateM(this.f4722h, 0, i5, 0.0f, 0.0f, 1.0f);
            if (i5 % 180 != 0) {
                int i8 = i ^ i3;
                i7 = i3 ^ i8;
                i6 = i8 ^ i7;
            } else {
                i6 = i;
                i7 = i3;
            }
            float f = i6 / i7;
            float f2 = i2 / i4;
            if (f > f2) {
                Matrix.scaleM(this.f4722h, 0, f / f2, 1.0f, 1.0f);
            } else if (f < f2) {
                Matrix.scaleM(this.f4722h, 0, 1.0f, f2 / f, 1.0f);
            }
        }

        /* renamed from: a */
        public final void m19907a(Surface surface) {
            if (this.f4734t == surface) {
                return;
            }
            this.f4734t = surface;
            WindowSurface windowSurface = this.f4733s;
            if (windowSurface != null) {
                windowSurface.release();
                this.f4733s = null;
            }
            if (this.f4734t != null) {
                this.f4733s = new WindowSurface(this.f4721g, this.f4734t, true);
                this.f4733s.makeCurrent();
            }
        }

        /* renamed from: b */
        public void m19902b() {
            synchronized (this.f4717c) {
                while (!this.f4718d) {
                    try {
                        this.f4717c.wait(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Looper.prepare();
            this.f4721g = new EglCore(EGLTextureReader.this.f4703g, 1);
            this.f4715a = new Handler() { // from class: com.baidu.cloud.mediaprocess.filter.EGLTextureReader.WorkThread.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    int i = message.what;
                    if (i == 1) {
                        WorkThread.this.f4716b = true;
                        WorkThread.this.m19910a(message.arg1);
                        WorkThread.this.f4716b = false;
                    } else if (i != 2) {
                        if (i != 3) {
                            return;
                        }
                        WorkThread.this.m19907a((Surface) message.obj);
                    } else {
                        WorkThread.this.f4718d = false;
                        WorkThread.m19906a(WorkThread.this);
                        Log.d("EGLTextureReader", String.format("(averageTrackTime, averageReadTime) = (%f, %f)", Float.valueOf(WorkThread.this.f4727m), Float.valueOf(WorkThread.this.f4728n)));
                        Looper.myLooper().quit();
                    }
                }
            };
            EglCore eglCore = this.f4721g;
            EGLTextureReader eGLTextureReader = EGLTextureReader.this;
            this.f4723i = eglCore.createOffscreenSurface(eGLTextureReader.f4699c, eGLTextureReader.f4700d);
            this.f4721g.makeCurrent(this.f4723i);
            this.f4719e = new VideoRGBA2NV21Filter();
            this.f4719e.init();
            ImageFilter imageFilter = this.f4719e;
            EGLTextureReader eGLTextureReader2 = EGLTextureReader.this;
            imageFilter.onOutputSizeChanged(eGLTextureReader2.f4699c, eGLTextureReader2.f4700d);
            this.f4720f = new ImageFilter();
            this.f4720f.init();
            ImageFilter imageFilter2 = this.f4720f;
            EGLTextureReader eGLTextureReader3 = EGLTextureReader.this;
            imageFilter2.onOutputSizeChanged(eGLTextureReader3.f4699c, eGLTextureReader3.f4700d);
            if (EGLTextureReader.this.f4708l > 0) {
                setPresentationStartTimeNs(EGLTextureReader.this.f4708l);
            }
            if (EGLTextureReader.this.f4709m != null) {
                m19907a(EGLTextureReader.this.f4709m);
            }
            this.f4718d = true;
            synchronized (this.f4717c) {
                this.f4717c.notifyAll();
            }
            Looper.loop();
        }

        public void setEncodeSurface(Surface surface) {
            Handler handler = this.f4715a;
            if (handler != null) {
                handler.sendMessage(handler.obtainMessage(3, 0, 0, surface));
            }
        }

        public void setPresentationStartTimeNs(long j) {
            this.f4731q = j;
        }

        /* renamed from: a */
        public final void m19910a(int i) {
            this.f4724j = System.currentTimeMillis();
            long currentTimeMillis = System.currentTimeMillis();
            long nanoTime = System.nanoTime();
            if (EGLTextureReader.this.f4705i && EGLTextureReader.this.f4701e != null) {
                this.f4721g.makeCurrent(this.f4723i);
                ImageFilter imageFilter = this.f4719e;
                EGLTextureReader eGLTextureReader = EGLTextureReader.this;
                imageFilter.onOutputSizeChanged(eGLTextureReader.f4699c, eGLTextureReader.f4700d);
                EGLTextureReader eGLTextureReader2 = EGLTextureReader.this;
                m19908a(eGLTextureReader2.f4697a, eGLTextureReader2.f4699c, eGLTextureReader2.f4698b, eGLTextureReader2.f4700d, eGLTextureReader2.f4706j, EGLTextureReader.this.f4710n, EGLTextureReader.this.f4711o);
                this.f4719e.setMVPMatrix(this.f4722h);
                this.f4719e.draw(i);
                this.f4721g.swapBuffers(this.f4723i);
                byte[] yUVData = ((VideoRGBA2NV21Filter) this.f4719e).getYUVData();
                this.f4725k = System.currentTimeMillis();
                this.f4726l = (int) (this.f4725k - this.f4724j);
                this.f4729o++;
                float f = this.f4728n;
                long j = this.f4729o;
                this.f4728n = ((f * ((float) (j - 1))) + this.f4726l) / ((float) j);
                this.f4724j = System.currentTimeMillis();
                EGLTextureReader eGLTextureReader3 = EGLTextureReader.this;
                OnPixelReadCallback onPixelReadCallback = eGLTextureReader3.f4701e;
                if (onPixelReadCallback != null) {
                    onPixelReadCallback.onPixelRead(yUVData, eGLTextureReader3.f4699c, eGLTextureReader3.f4700d);
                }
                this.f4725k = System.currentTimeMillis();
                this.f4726l = (int) (this.f4725k - this.f4724j);
                int i2 = this.f4726l;
                if (i2 > 0) {
                    float f2 = this.f4727m;
                    long j2 = this.f4730p;
                    long j3 = j2 + 1;
                    this.f4730p = j3;
                    this.f4727m = ((f2 * ((float) j2)) + i2) / ((float) j3);
                }
            }
            if (EGLTextureReader.this.f4704h && EGLTextureReader.this.getOutPort().isPortLinked()) {
                long j4 = this.f4731q > -1 ? nanoTime - this.f4731q : this.f4732r * 1000;
                ImageFilter imageFilter2 = this.f4720f;
                EGLTextureReader eGLTextureReader4 = EGLTextureReader.this;
                imageFilter2.onOutputSizeChanged(eGLTextureReader4.f4699c, eGLTextureReader4.f4700d);
                EGLTextureReader eGLTextureReader5 = EGLTextureReader.this;
                m19908a(eGLTextureReader5.f4697a, eGLTextureReader5.f4699c, eGLTextureReader5.f4698b, eGLTextureReader5.f4700d, eGLTextureReader5.f4706j, EGLTextureReader.this.f4712p, EGLTextureReader.this.f4713q);
                this.f4720f.setMVPMatrix(this.f4722h);
                WindowSurface windowSurface = this.f4733s;
                if (windowSurface != null) {
                    windowSurface.makeCurrent();
                    this.f4720f.draw(i, 0);
                    this.f4733s.setPresentationTime(j4);
                    this.f4733s.swapBuffers();
                    MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
                    bufferInfo.set((int) (System.currentTimeMillis() - currentTimeMillis), 0, j4 / 1000, 0);
                    EGLTextureReader.this.getOutPort().onFrame((OutPort<VideoFrameBuffer>) new VideoFrameBuffer(null, bufferInfo));
                }
            }
        }

        /* renamed from: a */
        public boolean m19909a(int i, int i2, int i3) {
            if (this.f4716b) {
                return false;
            }
            EGLTextureReader eGLTextureReader = EGLTextureReader.this;
            if (i2 != eGLTextureReader.f4697a || i3 != eGLTextureReader.f4698b) {
                EGLTextureReader eGLTextureReader2 = EGLTextureReader.this;
                eGLTextureReader2.f4697a = i2;
                eGLTextureReader2.f4698b = i3;
            }
            Message obtainMessage = this.f4715a.obtainMessage(1);
            obtainMessage.arg1 = i;
            this.f4715a.sendMessage(obtainMessage);
            return true;
        }
    }

    public EGLTextureReader(int i, int i2) {
        this.f4699c = 0;
        this.f4700d = 0;
        this.f4707k = false;
        this.f4699c = i;
        this.f4700d = i2;
        this.f4707k = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void dumpYUVData(byte[] bArr, int i, int i2) {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append("/storage/self/primary/test_");
                    sb.append(System.currentTimeMillis());
                    sb.append(".jpg");
                    FileOutputStream fileOutputStream2 = new FileOutputStream(sb.toString());
                    try {
                        YuvImage yuvImage = new YuvImage(bArr, 17, i, i2, null);
                        yuvImage.compressToJpeg(new Rect(0, 0, i, i2), 100, fileOutputStream2);
                        fileOutputStream2.close();
                        fileOutputStream = yuvImage;
                    } catch (Exception e) {
                        e = e;
                        fileOutputStream = fileOutputStream2;
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                            fileOutputStream = fileOutputStream;
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    public OutPort<VideoFrameBuffer> getOutPort() {
        return this.f4714r;
    }

    @Override // com.baidu.cloud.mediaprocess.filter.ITextureReader
    public void onTextureUpdate(int i, int i2, int i3) {
        WorkThread workThread = this.f4702f;
        if (workThread != null) {
            workThread.m19909a(i, i2, i3);
        }
    }

    @Override // com.baidu.cloud.mediaprocess.filter.ITextureReader
    public void release() {
        if (this.f4707k) {
            this.f4702f.m19911a();
            try {
                this.f4702f.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.f4707k = false;
        }
    }

    public void setEncodeOutputHorizonFlip(boolean z) {
        this.f4712p = z;
    }

    public void setEncodeOutputVerticalFlip(boolean z) {
        this.f4713q = z;
    }

    public void setEncodeSurface(Surface surface) {
        this.f4709m = surface;
        WorkThread workThread = this.f4702f;
        if (workThread != null) {
            workThread.setEncodeSurface(surface);
        }
    }

    public void setEncodingEnable(boolean z) {
        this.f4704h = z;
    }

    public void setEncodingEnabled(boolean z) {
        this.f4704h = z;
    }

    public void setEpochTimeInNs(long j) {
        this.f4708l = j;
        WorkThread workThread = this.f4702f;
        if (workThread != null) {
            workThread.setPresentationStartTimeNs(j);
        }
    }

    public void setOnPixelReadCallback(OnPixelReadCallback onPixelReadCallback) {
        this.f4701e = onPixelReadCallback;
    }

    public void setPixelOutputEnable(boolean z) {
        this.f4705i = z;
    }

    public void setPixelOutputHorizonFlip(boolean z) {
        this.f4710n = z;
    }

    public void setPixelOutputVerticalFlip(boolean z) {
        this.f4711o = z;
    }

    public void setRotation(int i) {
        this.f4706j = i;
    }

    @Override // com.baidu.cloud.mediaprocess.filter.ITextureReader
    public void setup(EGLContext eGLContext) {
        if (this.f4707k) {
            return;
        }
        this.f4703g = eGLContext;
        this.f4702f = new WorkThread(null);
        this.f4702f.start();
        this.f4702f.m19902b();
        this.f4707k = true;
    }
}
