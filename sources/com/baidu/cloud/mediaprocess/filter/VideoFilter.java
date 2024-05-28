package com.baidu.cloud.mediaprocess.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.media.MediaCodec;
import android.opengl.EGL14;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Surface;
import com.baidu.cloud.framework.InPort;
import com.baidu.cloud.framework.OutPort;
import com.baidu.cloud.framework.OutPortFactory;
import com.baidu.cloud.framework.frame.VideoFrameBuffer;
import com.baidu.cloud.mediaprocess.basefilters.ImageExtTexFilter;
import com.baidu.cloud.mediaprocess.basefilters.ImageFilter;
import com.baidu.cloud.mediaprocess.basefilters.ImageFilterGroup;
import com.baidu.cloud.mediaprocess.basefilters.VideoRGBA2NV21Filter;
import com.baidu.cloud.mediaprocess.filter.EGLPixelReader;
import com.baidu.cloud.mediaprocess.gles.EglCore;
import com.baidu.cloud.mediaprocess.gles.GlUtil;
import com.baidu.cloud.mediaprocess.gles.WindowSurface;
import com.baidu.cloud.mediaprocess.utils.BitmapUtils;
import com.baidu.cloud.mediaprocess.utils.FileUtils;
import java.io.IOException;
import java.lang.Thread;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VideoFilter {
    public static final String TAG = "VideoFilter";

    /* renamed from: a */
    public FilterThread f4742a;

    /* renamed from: h */
    public volatile Bitmap f4749h;

    /* renamed from: i */
    public boolean f4750i;

    /* renamed from: b */
    public Bitmap f4743b = null;

    /* renamed from: c */
    public Object f4744c = new Object[0];

    /* renamed from: d */
    public volatile boolean f4745d = false;

    /* renamed from: e */
    public EGLPixelReader f4746e = null;

    /* renamed from: f */
    public volatile boolean f4747f = true;

    /* renamed from: g */
    public volatile boolean f4748g = false;

    /* renamed from: j */
    public int f4751j = 18;

    /* renamed from: k */
    public int f4752k = 55;

    /* renamed from: l */
    public InPort<VideoFrameBuffer> f4753l = new VideoFilterInPortFactory(null).createInPort();

    /* renamed from: m */
    public OutPort<VideoFrameBuffer> f4754m = new OutPortFactory().createOutPort();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class VideoFilterInPortFactory implements InPort.Factory<VideoFrameBuffer> {
        public /* synthetic */ VideoFilterInPortFactory(C25471 c25471) {
        }

        @Override // com.baidu.cloud.framework.InPort.Factory
        public InPort<VideoFrameBuffer> createInPort() {
            return new InPort<VideoFrameBuffer>() { // from class: com.baidu.cloud.mediaprocess.filter.VideoFilter.VideoFilterInPortFactory.1
                @Override // com.baidu.cloud.framework.IPort
                public void onConfigure(Object obj) {
                    if (obj == null || !(obj instanceof Camera)) {
                        return;
                    }
                    try {
                        ((Camera) obj).setPreviewTexture(VideoFilter.this.getFilterInputSurfaceTexture());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override // com.baidu.cloud.framework.IPort
                public void onFrame(VideoFrameBuffer videoFrameBuffer) {
                }
            };
        }
    }

    public SurfaceTexture getFilterInputSurfaceTexture() {
        FilterThread filterThread = this.f4742a;
        if (filterThread != null) {
            return filterThread.getInputTexture();
        }
        return null;
    }

    public InPort<VideoFrameBuffer> getInPort() {
        return this.f4753l;
    }

    public OutPort<VideoFrameBuffer> getOutPort() {
        return this.f4754m;
    }

    public Bitmap getScreenShot() {
        Bitmap bitmap;
        FilterThread filterThread = this.f4742a;
        if (filterThread == null || filterThread.getState() != Thread.State.RUNNABLE) {
            return null;
        }
        synchronized (this.f4744c) {
            this.f4745d = true;
            try {
                this.f4744c.wait(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bitmap = this.f4743b;
        }
        return bitmap;
    }

    public boolean isPicStreaming() {
        return this.f4748g;
    }

    public void pause() {
        FilterThread filterThread = this.f4742a;
        if (filterThread != null) {
            filterThread.pauseProcess();
        }
    }

    public void refreshVideoFrame() {
        FilterThread filterThread = this.f4742a;
        if (filterThread != null) {
            filterThread.onFrameAvailable(null);
        }
    }

    public void release() {
        FilterThread filterThread = this.f4742a;
        if (filterThread != null) {
            filterThread.shutdown();
            this.f4742a = null;
        }
    }

    public void resume() {
        FilterThread filterThread = this.f4742a;
        if (filterThread != null) {
            filterThread.resumeProcess();
        }
    }

    public void setCurrentPresentationTimeInUs(long j) {
        FilterThread filterThread = this.f4742a;
        if (filterThread != null) {
            filterThread.setCurrentPresentationTimeInUs(j);
        }
    }

    public void setEncodeSize(int i, int i2) {
        FilterThread filterThread = this.f4742a;
        if (filterThread != null) {
            filterThread.setEncodeSize(i, i2, 0);
        }
    }

    public void setEncodeSize(int i, int i2, int i3) {
        FilterThread filterThread = this.f4742a;
        if (filterThread != null) {
            filterThread.setEncodeSize(i, i2, i3);
        }
    }

    public void setEncodeSurface(Surface surface) {
        FilterThread filterThread = this.f4742a;
        if (filterThread != null) {
            filterThread.setEncodeSurface(surface);
        }
    }

    public void setEncodingEnabled(boolean z) {
        this.f4747f = z;
    }

    public void setEpochTimeInNs(long j) {
        FilterThread filterThread = this.f4742a;
        if (filterThread != null) {
            filterThread.setPresentationStartTimeNs(j);
        }
    }

    public void setFpsControlEnabled(boolean z, int i) {
        this.f4750i = z;
        this.f4751j = i;
        int i2 = this.f4751j;
        if (i2 > 0) {
            this.f4752k = 1000 / i2;
        }
    }

    public void setImageFilters(List<ImageFilter> list) {
        FilterThread filterThread;
        if (list == null || list.size() == 0 || (filterThread = this.f4742a) == null) {
            return;
        }
        filterThread.setImageFilters(list);
    }

    public void setInputSize(int i, int i2) {
        FilterThread filterThread = this.f4742a;
        if (filterThread != null) {
            filterThread.setInputSize(i, i2);
        }
    }

    public void setOutputHorizonFlip(boolean z) {
        FilterThread filterThread = this.f4742a;
        if (filterThread != null) {
            filterThread.setOutputHorizonFlip(z);
        }
    }

    public void setPauseImgPath(Context context, String str) {
        if (!FileUtils.isExists(str) && BitmapUtils.getImageIdByName(str, context) <= 0) {
            Log.d("VideoFilter", "Pause picture path not exist:" + str);
            return;
        }
        this.f4749h = BitmapUtils.decodeBitmap(context, str);
        FilterThread filterThread = this.f4742a;
        if (filterThread != null) {
            filterThread.setPauseImgPath(this.f4749h);
        }
    }

    public void setPreviewHorizonFlip(boolean z) {
        FilterThread filterThread = this.f4742a;
        if (filterThread != null) {
            filterThread.setPreviewHorizonFlip(z);
        }
    }

    public void setPreviewSurface(Surface surface) {
        FilterThread filterThread = this.f4742a;
        if (filterThread != null) {
            filterThread.setPreviewSurface(surface);
        }
    }

    public void setPreviewSurfaceSize(int i, int i2) {
        FilterThread filterThread = this.f4742a;
        if (filterThread != null) {
            filterThread.setPreviewSize(i, i2, 0);
        }
    }

    public void setPreviewSurfaceSize(int i, int i2, int i3) {
        FilterThread filterThread = this.f4742a;
        if (filterThread != null) {
            filterThread.setPreviewSize(i, i2, i3);
        }
    }

    public void setRawPixelReader(EGLPixelReader eGLPixelReader) {
        this.f4746e = eGLPixelReader;
    }

    public void setup() {
        this.f4742a = new FilterThread(null);
        this.f4742a.setName("VideoFilter Tex Render");
        this.f4742a.start();
        this.f4742a.waitUntilReady();
    }

    public void startPictureStreaming() {
        FilterThread filterThread = this.f4742a;
        if (filterThread != null) {
            filterThread.startPictureStreaming();
        }
    }

    public void stopPictureStreaming() {
        FilterThread filterThread = this.f4742a;
        if (filterThread != null) {
            filterThread.stopPictureStreaming();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class FilterThread extends Thread implements SurfaceTexture.OnFrameAvailableListener, EGLPixelReader.OnPixelReadCallback {

        /* renamed from: H */
        public EGLPixelReader f4762H;

        /* renamed from: J */
        public int f4764J;

        /* renamed from: K */
        public int f4765K;

        /* renamed from: L */
        public boolean f4766L;

        /* renamed from: a */
        public volatile FilterHandler f4770a;

        /* renamed from: d */
        public EglCore f4773d;

        /* renamed from: s */
        public volatile SurfaceTexture f4788s;

        /* renamed from: t */
        public volatile Surface f4789t;

        /* renamed from: u */
        public volatile Surface f4790u;

        /* renamed from: v */
        public WindowSurface f4791v;

        /* renamed from: w */
        public WindowSurface f4792w;

        /* renamed from: x */
        public EGLSurface f4793x;

        /* renamed from: z */
        public volatile ImageFilterGroup f4795z;

        /* renamed from: b */
        public Object f4771b = new Object();

        /* renamed from: c */
        public volatile boolean f4772c = false;

        /* renamed from: e */
        public volatile long f4774e = -1;

        /* renamed from: f */
        public volatile long f4775f = 0;

        /* renamed from: g */
        public volatile int f4776g = 0;

        /* renamed from: h */
        public volatile int f4777h = 0;

        /* renamed from: i */
        public volatile int f4778i = 0;

        /* renamed from: j */
        public volatile int f4779j = 0;

        /* renamed from: k */
        public volatile int f4780k = 0;

        /* renamed from: l */
        public volatile int f4781l = 0;

        /* renamed from: m */
        public volatile int f4782m = 0;

        /* renamed from: n */
        public volatile int f4783n = 0;

        /* renamed from: o */
        public int f4784o = 0;

        /* renamed from: p */
        public int f4785p = 0;

        /* renamed from: q */
        public final float[] f4786q = new float[16];

        /* renamed from: r */
        public final float[] f4787r = new float[16];

        /* renamed from: y */
        public ImageExtTexFilter f4794y = null;

        /* renamed from: A */
        public ImageFilter f4755A = null;

        /* renamed from: B */
        public VideoRGBA2NV21Filter f4756B = null;

        /* renamed from: C */
        public int f4757C = -1;

        /* renamed from: D */
        public int f4758D = -1;

        /* renamed from: E */
        public int f4759E = -1;

        /* renamed from: F */
        public int f4760F = -1;

        /* renamed from: G */
        public int f4761G = -1;

        /* renamed from: I */
        public volatile boolean f4763I = false;

        /* renamed from: M */
        public boolean f4767M = false;

        /* renamed from: N */
        public boolean f4768N = false;

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        public class FilterHandler extends Handler {

            /* renamed from: a */
            public WeakReference<FilterThread> f4796a;

            public FilterHandler(FilterThread filterThread) {
                this.f4796a = new WeakReference<>(filterThread);
            }

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                FilterThread filterThread = this.f4796a.get();
                if (filterThread == null) {
                    Log.w("VideoFilter", "FilterHandler.handleMessage: weak ref is null");
                    return;
                }
                switch (i) {
                    case 1:
                        filterThread.m19872b(message.arg1, message.arg2);
                        return;
                    case 2:
                        filterThread.m19885a(message.arg1, message.arg2);
                        return;
                    case 3:
                        filterThread.m19861f();
                        return;
                    case 4:
                        break;
                    case 5:
                        FilterThread.m19867c(filterThread);
                        if (!VideoFilter.this.f4750i || FilterThread.this.f4766L) {
                            return;
                        }
                        FilterThread.this.f4770a.sendMessage(FilterThread.this.f4770a.obtainMessage(8));
                        FilterThread.this.f4766L = true;
                        return;
                    case 6:
                        FilterThread.this.setPauseImgInternal((Bitmap) message.obj);
                        return;
                    case 7:
                        if (VideoFilter.this.f4748g) {
                            int i2 = message.arg1;
                            FilterThread filterThread2 = FilterThread.this;
                            filterThread2.f4779j = filterThread2.f4784o;
                            FilterThread filterThread3 = FilterThread.this;
                            filterThread3.f4780k = filterThread3.f4785p;
                            filterThread.m19886a(i2);
                            Message obtainMessage = FilterThread.this.f4770a.obtainMessage(7);
                            obtainMessage.arg1 = FilterThread.this.f4761G;
                            FilterThread.this.f4770a.sendMessageDelayed(obtainMessage, 200L);
                            return;
                        }
                        return;
                    case 8:
                        if (VideoFilter.this.f4750i) {
                            sendMessageDelayed(obtainMessage(8), VideoFilter.this.f4752k);
                            FilterThread filterThread4 = FilterThread.this;
                            filterThread4.f4779j = filterThread4.f4765K;
                            FilterThread filterThread5 = FilterThread.this;
                            filterThread5.f4780k = filterThread5.f4764J;
                            break;
                        } else {
                            return;
                        }
                    default:
                        switch (i) {
                            case 101:
                                filterThread.m19874a((List) message.obj);
                                return;
                            case 102:
                                filterThread.m19883a((Surface) message.obj);
                                return;
                            case 103:
                                filterThread.setPreviewSurfaceInternal((Surface) message.obj);
                                return;
                            default:
                                throw new RuntimeException("unknown message " + i);
                        }
                }
                filterThread.m19863e();
            }
        }

        public /* synthetic */ FilterThread(C25471 c25471) {
        }

        /* renamed from: c */
        public static /* synthetic */ void m19867c(FilterThread filterThread) {
            filterThread.f4788s.updateTexImage();
        }

        /* renamed from: a */
        public final void m19887a() {
            Log.d("VideoFilter", "Creating gl context...");
            this.f4793x = this.f4773d.createOffscreenSurface(1, 1);
            this.f4773d.makeCurrent(this.f4793x);
            this.f4760F = GlUtil.m19853a(36197, 0);
            this.f4788s = new SurfaceTexture(this.f4760F);
            this.f4788s.setOnFrameAvailableListener(this);
            this.f4758D = GlUtil.createFrameBufferObject();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new ImageFilter());
            this.f4795z = new ImageFilterGroup(arrayList);
            m19881a(this.f4795z, 0, 0);
            this.f4794y = new ImageExtTexFilter();
            this.f4755A = new ImageFilter();
            this.f4756B = new VideoRGBA2NV21Filter();
            m19881a(this.f4794y, 0, 0);
            m19881a(this.f4755A, 0, 0);
            m19881a(this.f4756B, 0, 0);
            if (VideoFilter.this.f4749h != null) {
                setPauseImgInternal(VideoFilter.this.f4749h);
            }
        }

        /* renamed from: a */
        public final void m19886a(int i) {
            int i2;
            int i3;
            int i4;
            int i5;
            int i6;
            boolean z;
            boolean z2;
            long currentTimeMillis = System.currentTimeMillis();
            long nanoTime = System.nanoTime();
            if (VideoFilter.this.f4747f) {
                long j = this.f4774e > -1 ? nanoTime - this.f4774e : this.f4775f * 1000;
                this.f4755A.onOutputSizeChanged(this.f4776g, this.f4777h);
                if (VideoFilter.this.f4748g) {
                    i2 = this.f4779j;
                    i3 = this.f4776g;
                    i4 = this.f4780k;
                    i5 = this.f4777h;
                    i6 = 0;
                    z = false;
                    z2 = true;
                } else {
                    i2 = this.f4779j;
                    i3 = this.f4776g;
                    i4 = this.f4780k;
                    i5 = this.f4777h;
                    i6 = this.f4778i;
                    z = this.f4767M;
                    z2 = false;
                }
                m19884a(i2, i3, i4, i5, i6, z, z2);
                this.f4755A.setMVPMatrix(this.f4787r);
                WindowSurface windowSurface = this.f4792w;
                if (windowSurface == null) {
                    MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
                    int currentTimeMillis2 = (int) (System.currentTimeMillis() - currentTimeMillis);
                    long j2 = j / 1000;
                    bufferInfo.set(currentTimeMillis2, 0, j2, 0);
                    if (VideoFilter.this.f4754m.isPortLinked()) {
                        VideoFrameBuffer videoFrameBuffer = new VideoFrameBuffer(null, j2, 0);
                        videoFrameBuffer.offset = currentTimeMillis2;
                        VideoFilter.this.f4754m.onFrame((OutPort) videoFrameBuffer);
                        return;
                    }
                    return;
                }
                windowSurface.makeCurrent();
                this.f4755A.draw(i, 0);
                this.f4792w.setPresentationTime(j);
                this.f4792w.swapBuffers();
                MediaCodec.BufferInfo bufferInfo2 = new MediaCodec.BufferInfo();
                int currentTimeMillis3 = (int) (System.currentTimeMillis() - currentTimeMillis);
                long j3 = j / 1000;
                bufferInfo2.set(currentTimeMillis3, 0, j3, 0);
                if (VideoFilter.this.f4754m.isPortLinked()) {
                    VideoFrameBuffer videoFrameBuffer2 = new VideoFrameBuffer(null, j3, 0);
                    videoFrameBuffer2.offset = currentTimeMillis3;
                    VideoFilter.this.f4754m.onFrame((OutPort) videoFrameBuffer2);
                }
            }
        }

        /* renamed from: a */
        public final void m19884a(int i, int i2, int i3, int i4, int i5, boolean z, boolean z2) {
            int i6;
            int i7;
            Matrix.setIdentityM(this.f4787r, 0);
            Matrix.scaleM(this.f4787r, 0, z ? -1.0f : 1.0f, z2 ? -1.0f : 1.0f, 1.0f);
            Matrix.rotateM(this.f4787r, 0, i5, 0.0f, 0.0f, 1.0f);
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
                Matrix.scaleM(this.f4787r, 0, f / f2, 1.0f, 1.0f);
            } else if (f < f2) {
                Matrix.scaleM(this.f4787r, 0, 1.0f, f2 / f, 1.0f);
            }
        }

        /* renamed from: a */
        public final void m19883a(Surface surface) {
            Log.d("VideoFilter", "setEncodeSurfaceInternal: ");
            if (this.f4789t == surface) {
                return;
            }
            this.f4789t = surface;
            WindowSurface windowSurface = this.f4792w;
            if (windowSurface != null) {
                windowSurface.release();
                this.f4792w = null;
            }
            if (this.f4789t != null) {
                this.f4792w = new WindowSurface(this.f4773d, this.f4789t, true);
                this.f4792w.makeCurrent();
            }
        }

        /* renamed from: a */
        public final void m19882a(ImageFilter imageFilter) {
            Log.d("VideoFilter", "clearImageFilter: ");
            if (imageFilter == null || !imageFilter.isInitialized()) {
                return;
            }
            imageFilter.release();
        }

        /* renamed from: a */
        public final void m19881a(ImageFilter imageFilter, int i, int i2) {
            Log.d("VideoFilter", "initImageFilter: " + i + "x" + i2);
            if (imageFilter == null) {
                return;
            }
            imageFilter.init();
            if (i <= 0 || i2 <= 0) {
                return;
            }
            imageFilter.onOutputSizeChanged(i, i2);
        }

        /* renamed from: a */
        public final void m19874a(List<ImageFilter> list) {
            Log.d("VideoFilter", "setImageFiltersInternal: ");
            m19882a(this.f4795z);
            this.f4795z.resetFilters(list);
            m19881a(this.f4795z, this.f4779j, this.f4780k);
        }

        /* renamed from: b */
        public final void m19873b() {
            int i = this.f4757C;
            if (i != -1) {
                GlUtil.destroyTextureObject(i);
                this.f4757C = -1;
            }
        }

        /* renamed from: b */
        public final void m19872b(int i, int i2) {
            Log.i("VideoFilter", "FilterThread previewSizeChanged " + i + "x" + i2);
            EGLPixelReader eGLPixelReader = this.f4762H;
            if (eGLPixelReader != null) {
                eGLPixelReader.m19924a();
            }
            this.f4762H = new EGLPixelReader(i, i2, EGLPixelReader.PixelFormat.PIXEL_FORMAT_RGBA);
            this.f4762H.m19922a(EGL14.eglGetCurrentContext());
            this.f4762H.setOnPixelReadCallback(this);
        }

        /* renamed from: c */
        public final void m19868c() {
            int i = this.f4759E;
            if (i != -1) {
                GlUtil.destroyTextureObject(i);
                this.f4759E = -1;
            }
        }

        /* renamed from: d */
        public final void m19865d() {
            Log.d("VideoFilter", "Destroying gl context...");
            m19882a(this.f4755A);
            m19882a(this.f4756B);
            m19882a(this.f4794y);
            this.f4755A = null;
            this.f4756B = null;
            this.f4794y = null;
            m19882a(this.f4795z);
            this.f4795z = null;
            int i = this.f4757C;
            if (i != -1) {
                GlUtil.destroyTextureObject(i);
                this.f4757C = -1;
            }
            int i2 = this.f4759E;
            if (i2 != -1) {
                GlUtil.destroyTextureObject(i2);
                this.f4759E = -1;
            }
            m19883a((Surface) null);
            setPreviewSurfaceInternal(null);
            GlUtil.destroyTextureObject(this.f4758D);
            if (this.f4788s != null) {
                this.f4788s.setOnFrameAvailableListener(null);
                this.f4788s.release();
            }
            this.f4788s = null;
            GlUtil.destroyTextureObject(this.f4760F);
            if (VideoFilter.this.f4746e != null) {
                VideoFilter.this.f4746e.m19924a();
            }
            EGLSurface eGLSurface = this.f4793x;
            if (eGLSurface != null) {
                this.f4773d.releaseSurface(eGLSurface);
            }
            this.f4793x = null;
            EglCore eglCore = this.f4773d;
            if (eglCore != null) {
                eglCore.makeNothingCurrent();
            }
        }

        /* renamed from: e */
        public final void m19863e() {
            try {
                if (!VideoFilter.this.f4750i) {
                    this.f4788s.updateTexImage();
                }
                if (this.f4763I && !VideoFilter.this.f4748g && this.f4779j != 0 && this.f4780k != 0) {
                    this.f4788s.getTransformMatrix(this.f4786q);
                    this.f4794y.setTextureMatrix(this.f4786q);
                    GlUtil.bindTextureToFBO(this.f4757C, 3553, this.f4758D);
                    this.f4794y.draw(this.f4760F, this.f4758D);
                    if (VideoFilter.this.f4746e != null) {
                        VideoFilter.this.f4746e.m19923a(this.f4757C, this.f4779j, this.f4780k);
                    }
                    GlUtil.bindTextureToFBO(this.f4759E, 3553, this.f4758D);
                    this.f4795z.draw(this.f4757C, this.f4758D);
                    if (VideoFilter.this.f4745d && this.f4762H != null) {
                        this.f4762H.m19923a(this.f4759E, this.f4779j, this.f4780k);
                        VideoFilter.this.f4745d = false;
                    }
                    if (this.f4791v != null) {
                        this.f4791v.makeCurrent();
                        this.f4755A.onOutputSizeChanged(this.f4781l, this.f4782m);
                        m19884a(this.f4779j, this.f4781l, this.f4780k, this.f4782m, this.f4783n, this.f4768N, false);
                        this.f4755A.setMVPMatrix(this.f4787r);
                        this.f4755A.draw(this.f4759E, 0);
                        this.f4791v.swapBuffers();
                    }
                    m19886a(this.f4759E);
                    return;
                }
                Log.d("VideoFilter", "Skipping Frame Processing!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* renamed from: f */
        public final void m19861f() {
            Looper.myLooper().quit();
            this.f4770a.removeCallbacksAndMessages(null);
            Log.d("VideoFilter", "shutdownInternal: ");
        }

        public SurfaceTexture getInputTexture() {
            return this.f4788s;
        }

        @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
        public void onFrameAvailable(SurfaceTexture surfaceTexture) {
            FilterHandler filterHandler;
            Message obtainMessage;
            if (this.f4770a != null) {
                if (VideoFilter.this.f4750i || this.f4770a.hasMessages(4)) {
                    filterHandler = this.f4770a;
                    obtainMessage = this.f4770a.obtainMessage(5);
                } else {
                    filterHandler = this.f4770a;
                    obtainMessage = this.f4770a.obtainMessage(4);
                }
                filterHandler.sendMessage(obtainMessage);
            }
        }

        @Override // com.baidu.cloud.mediaprocess.filter.EGLPixelReader.OnPixelReadCallback
        public void onPixelRead(byte[] bArr, int i, int i2) {
            VideoFilter.this.f4743b.copyPixelsFromBuffer(ByteBuffer.wrap(bArr));
            synchronized (VideoFilter.this.f4744c) {
                VideoFilter.this.f4744c.notifyAll();
            }
        }

        public void pauseProcess() {
            this.f4763I = false;
        }

        public void resumeProcess() {
            this.f4763I = true;
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x0040, code lost:
            if (r5.f4770a != null) goto L17;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x006b, code lost:
            if (r5.f4770a == null) goto L14;
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x006d, code lost:
            r5.f4770a.removeCallbacksAndMessages(null);
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x0072, code lost:
            r5.f4770a = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x0074, code lost:
            return;
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r5 = this;
                r0 = 0
                r1 = 0
                android.os.Looper.prepare()     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48
                com.baidu.cloud.mediaprocess.filter.VideoFilter$FilterThread$FilterHandler r2 = new com.baidu.cloud.mediaprocess.filter.VideoFilter$FilterThread$FilterHandler     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48
                r2.<init>(r5)     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48
                r5.f4770a = r2     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48
                com.baidu.cloud.mediaprocess.gles.EglCore r2 = new com.baidu.cloud.mediaprocess.gles.EglCore     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48
                r2.<init>(r1, r0)     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48
                r5.f4773d = r2     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48
                r5.m19887a()     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48
                r2 = 1
                r5.f4772c = r2     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48
                r5.f4763I = r2     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48
                java.lang.Object r2 = r5.f4771b     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48
                monitor-enter(r2)     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48
                java.lang.Object r3 = r5.f4771b     // Catch: java.lang.Throwable -> L43
                r3.notifyAll()     // Catch: java.lang.Throwable -> L43
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L43
                android.os.Looper.loop()     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48
                java.lang.String r2 = "VideoFilter"
                java.lang.String r3 = "looper quit"
                android.util.Log.d(r2, r3)
                r5.f4772c = r0
                r5.f4763I = r0
                r5.m19865d()
                com.baidu.cloud.mediaprocess.gles.EglCore r0 = r5.f4773d
                if (r0 == 0) goto L3c
                r0.release()
            L3c:
                r5.f4773d = r1
                com.baidu.cloud.mediaprocess.filter.VideoFilter$FilterThread$FilterHandler r0 = r5.f4770a
                if (r0 == 0) goto L72
                goto L6d
            L43:
                r3 = move-exception
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L43
                throw r3     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48
            L46:
                r2 = move-exception
                goto L75
            L48:
                r2 = move-exception
                java.lang.String r3 = "VideoFilter"
                java.lang.String r2 = android.util.Log.getStackTraceString(r2)     // Catch: java.lang.Throwable -> L46
                android.util.Log.d(r3, r2)     // Catch: java.lang.Throwable -> L46
                java.lang.String r2 = "VideoFilter"
                java.lang.String r3 = "looper quit"
                android.util.Log.d(r2, r3)
                r5.f4772c = r0
                r5.f4763I = r0
                r5.m19865d()
                com.baidu.cloud.mediaprocess.gles.EglCore r0 = r5.f4773d
                if (r0 == 0) goto L67
                r0.release()
            L67:
                r5.f4773d = r1
                com.baidu.cloud.mediaprocess.filter.VideoFilter$FilterThread$FilterHandler r0 = r5.f4770a
                if (r0 == 0) goto L72
            L6d:
                com.baidu.cloud.mediaprocess.filter.VideoFilter$FilterThread$FilterHandler r0 = r5.f4770a
                r0.removeCallbacksAndMessages(r1)
            L72:
                r5.f4770a = r1
                return
            L75:
                java.lang.String r3 = "VideoFilter"
                java.lang.String r4 = "looper quit"
                android.util.Log.d(r3, r4)
                r5.f4772c = r0
                r5.f4763I = r0
                r5.m19865d()
                com.baidu.cloud.mediaprocess.gles.EglCore r0 = r5.f4773d
                if (r0 == 0) goto L8a
                r0.release()
            L8a:
                r5.f4773d = r1
                com.baidu.cloud.mediaprocess.filter.VideoFilter$FilterThread$FilterHandler r0 = r5.f4770a
                if (r0 == 0) goto L95
                com.baidu.cloud.mediaprocess.filter.VideoFilter$FilterThread$FilterHandler r0 = r5.f4770a
                r0.removeCallbacksAndMessages(r1)
            L95:
                r5.f4770a = r1
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.cloud.mediaprocess.filter.VideoFilter.FilterThread.run():void");
        }

        public void setCurrentPresentationTimeInUs(long j) {
            this.f4775f = j;
        }

        public void setEncodeSize(int i, int i2, int i3) {
            this.f4776g = i;
            this.f4777h = i2;
            this.f4778i = i3;
        }

        public void setEncodeSurface(Surface surface) {
            if (this.f4770a != null) {
                this.f4770a.sendMessage(this.f4770a.obtainMessage(102, 0, 0, surface));
            }
        }

        public void setImageFilters(List<ImageFilter> list) {
            if (this.f4770a != null) {
                this.f4770a.sendMessage(this.f4770a.obtainMessage(101, 0, 0, list));
            }
        }

        public void setInputSize(int i, int i2) {
            if (i == this.f4779j && i2 == this.f4780k) {
                return;
            }
            this.f4765K = i;
            this.f4764J = i2;
            this.f4779j = i;
            this.f4780k = i2;
            if (this.f4770a != null) {
                this.f4770a.sendMessage(this.f4770a.obtainMessage(2, i, i2));
            }
        }

        public void setOutputHorizonFlip(boolean z) {
            this.f4767M = z;
        }

        public void setPauseImgInternal(Bitmap bitmap) {
            if (bitmap == null) {
                Log.d("VideoFilter", "Pause bitmap is null");
                return;
            }
            int i = this.f4761G;
            int i2 = 0;
            if (i > 0) {
                GLES20.glDeleteTextures(1, new int[]{i}, 0);
                this.f4761G = 0;
            }
            if (!bitmap.isRecycled()) {
                this.f4784o = bitmap.getWidth();
                this.f4785p = bitmap.getHeight();
                int m19853a = GlUtil.m19853a(3553, 0);
                GLUtils.texImage2D(3553, 0, bitmap, 0);
                bitmap.recycle();
                i2 = m19853a;
            }
            this.f4761G = i2;
        }

        public void setPauseImgPath(Bitmap bitmap) {
            if (this.f4770a != null) {
                this.f4770a.sendMessage(this.f4770a.obtainMessage(6, 0, 0, bitmap));
            }
        }

        public void setPresentationStartTimeNs(long j) {
            this.f4774e = j;
        }

        public void setPreviewHorizonFlip(boolean z) {
            this.f4768N = z;
        }

        public void setPreviewSize(int i, int i2, int i3) {
            if (i == this.f4781l && i2 == this.f4782m) {
                return;
            }
            this.f4781l = i;
            this.f4782m = i2;
            this.f4783n = i3;
            if (VideoFilter.this.f4743b != null) {
                VideoFilter.this.f4743b.recycle();
            }
            VideoFilter.this.f4743b = Bitmap.createBitmap(this.f4781l, this.f4782m, Bitmap.Config.ARGB_8888);
            if (this.f4770a != null) {
                this.f4770a.sendMessage(this.f4770a.obtainMessage(1, i, i2));
            }
        }

        public void setPreviewSurface(Surface surface) {
            if (this.f4770a != null) {
                this.f4770a.sendMessage(this.f4770a.obtainMessage(103, 0, 0, surface));
            }
        }

        public void setPreviewSurfaceInternal(Surface surface) {
            Log.d("VideoFilter", "setPreviewSurfaceInternal: ");
            if (this.f4790u == surface) {
                return;
            }
            this.f4790u = surface;
            WindowSurface windowSurface = this.f4791v;
            if (windowSurface != null) {
                windowSurface.release();
                this.f4791v = null;
            }
            if (this.f4790u != null) {
                this.f4791v = new WindowSurface(this.f4773d, this.f4790u, true);
                this.f4791v.makeCurrent();
            }
        }

        public void shutdown() {
            EGLPixelReader eGLPixelReader;
            Log.i("VideoFilter", "shutdown");
            if (this.f4770a != null && !this.f4770a.hasMessages(3)) {
                this.f4770a.sendMessage(this.f4770a.obtainMessage(3));
            }
            try {
                join(1000L);
                if (VideoFilter.this.f4743b != null) {
                    VideoFilter.this.f4743b.recycle();
                    VideoFilter.this.f4743b = null;
                }
                eGLPixelReader = this.f4762H;
                if (eGLPixelReader == null) {
                    return;
                }
            } catch (InterruptedException unused) {
                if (VideoFilter.this.f4743b != null) {
                    VideoFilter.this.f4743b.recycle();
                    VideoFilter.this.f4743b = null;
                }
                eGLPixelReader = this.f4762H;
                if (eGLPixelReader == null) {
                    return;
                }
            } catch (Throwable th) {
                if (VideoFilter.this.f4743b != null) {
                    VideoFilter.this.f4743b.recycle();
                    VideoFilter.this.f4743b = null;
                }
                EGLPixelReader eGLPixelReader2 = this.f4762H;
                if (eGLPixelReader2 != null) {
                    eGLPixelReader2.setOnPixelReadCallback(null);
                    this.f4762H.m19924a();
                    this.f4762H = null;
                }
                throw th;
            }
            eGLPixelReader.setOnPixelReadCallback(null);
            this.f4762H.m19924a();
            this.f4762H = null;
        }

        public void startPictureStreaming() {
            if (this.f4761G < 0) {
                return;
            }
            VideoFilter.this.f4748g = true;
            Log.d("VideoFilter", "startPictureStreaming mIsPicStreaming " + VideoFilter.this.f4748g);
            this.f4779j = this.f4784o;
            this.f4780k = this.f4785p;
            Message obtainMessage = this.f4770a.obtainMessage(7);
            obtainMessage.arg1 = this.f4761G;
            this.f4770a.sendMessage(obtainMessage);
        }

        public void stopPictureStreaming() {
            VideoFilter.this.f4748g = false;
            Log.d("VideoFilter", "stopPictureStreaming mIsPicStreaming " + VideoFilter.this.f4748g);
            this.f4770a.removeMessages(7);
        }

        public void waitUntilReady() {
            synchronized (this.f4771b) {
                while (!this.f4772c) {
                    try {
                        this.f4771b.wait();
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }

        /* renamed from: a */
        public final void m19885a(int i, int i2) {
            Log.i("VideoFilter", "FilterThread inputSizeChanged " + i + "x" + i2);
            m19873b();
            this.f4757C = GlUtil.createTextureObject(3553, i, i2);
            m19868c();
            this.f4759E = GlUtil.createTextureObject(3553, 1, i, i2);
            this.f4794y.onOutputSizeChanged(i, i2);
            this.f4795z.onOutputSizeChanged(i, i2);
            if (VideoFilter.this.f4746e != null) {
                VideoFilter.this.f4746e.m19922a(EGL14.eglGetCurrentContext());
            }
        }
    }
}
