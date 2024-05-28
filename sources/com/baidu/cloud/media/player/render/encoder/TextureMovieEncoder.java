package com.baidu.cloud.media.player.render.encoder;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.media.MediaExtractor;
import android.media.MediaMetadataRetriever;
import android.opengl.EGL14;
import android.opengl.EGLContext;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.cloud.media.player.render.p134a.EglCore;
import com.baidu.cloud.media.player.render.p134a.WindowSurface;
import com.baidu.cloud.media.player.render.p135b.Logger;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@TargetApi(18)
/* loaded from: E:\10201592_dexfile_execute.dex */
public class TextureMovieEncoder {

    /* renamed from: L */
    private static final int[] f4402L = {1, 0, 5, 7, 6};

    /* renamed from: B */
    private ByteBuffer f4404B;

    /* renamed from: C */
    private OnDrawEncoderFrameCallback f4405C;

    /* renamed from: a */
    private WindowSurface f4418a;

    /* renamed from: b */
    private EglCore f4419b;

    /* renamed from: c */
    private int f4420c;

    /* renamed from: d */
    private int f4421d;

    /* renamed from: e */
    private VideoEncoderCore f4422e;

    /* renamed from: f */
    private AudioEncoderCore f4423f;

    /* renamed from: g */
    private MediaMuxerWrapper f4424g;

    /* renamed from: h */
    private volatile HandlerC2516d f4425h;

    /* renamed from: i */
    private volatile HandlerC2513a f4426i;

    /* renamed from: k */
    private boolean f4428k;

    /* renamed from: l */
    private boolean f4429l;

    /* renamed from: m */
    private int f4430m;

    /* renamed from: n */
    private OnEncoderStatusUpdateListener f4431n;

    /* renamed from: s */
    private int f4436s;

    /* renamed from: t */
    private int f4437t;

    /* renamed from: u */
    private int f4438u;

    /* renamed from: j */
    private Object f4427j = new Object();

    /* renamed from: o */
    private volatile long f4432o = 0;

    /* renamed from: p */
    private volatile long f4433p = 0;

    /* renamed from: q */
    private volatile long f4434q = 0;

    /* renamed from: r */
    private volatile long f4435r = 0;

    /* renamed from: v */
    private volatile boolean f4439v = false;

    /* renamed from: w */
    private volatile boolean f4440w = false;

    /* renamed from: x */
    private float f4441x = 1.0f;

    /* renamed from: y */
    private float f4442y = 0.0f;

    /* renamed from: z */
    private long f4443z = -1;

    /* renamed from: A */
    private long f4403A = 0;

    /* renamed from: D */
    private C2515c f4406D = null;

    /* renamed from: E */
    private final Object f4407E = new Object();

    /* renamed from: F */
    private boolean f4408F = false;

    /* renamed from: G */
    private final Object f4409G = new Object();

    /* renamed from: H */
    private boolean f4410H = false;

    /* renamed from: I */
    private String f4411I = null;

    /* renamed from: J */
    private boolean f4412J = false;

    /* renamed from: K */
    private boolean f4413K = false;

    /* renamed from: M */
    private final boolean f4414M = false;

    /* renamed from: N */
    private long f4415N = 0;

    /* renamed from: O */
    private long f4416O = 0;

    /* renamed from: P */
    private volatile long f4417P = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnDrawEncoderFrameCallback {
        void onDrawEncoderFrame();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnEncoderStatusUpdateListener {
        void onError(int i, String str);

        void onProgress(long j);

        void onStartSuccess();

        @WorkerThread
        void onStopSuccess();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m19994f() {
    }

    public TextureMovieEncoder() {
        this.f4430m = 4;
        this.f4430m = 2;
    }

    /* renamed from: a */
    public boolean m20026a(int i) {
        return this.f4430m == i;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.cloud.media.player.render.encoder.TextureMovieEncoder$c */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2515c {

        /* renamed from: a */
        final File f4447a;

        /* renamed from: b */
        final int f4448b;

        /* renamed from: c */
        final int f4449c;

        /* renamed from: d */
        final int f4450d;

        /* renamed from: e */
        final int f4451e;

        /* renamed from: f */
        final EGLContext f4452f;

        /* renamed from: g */
        final long f4453g;

        public C2515c(File file, int i, int i2, int i3, int i4, EGLContext eGLContext, long j) {
            this.f4447a = file;
            this.f4448b = i;
            this.f4449c = i2;
            this.f4450d = i3;
            this.f4451e = i4;
            if (eGLContext != null) {
                this.f4452f = eGLContext;
            } else {
                this.f4452f = EGL14.eglGetCurrentContext();
            }
            this.f4453g = j;
        }

        public String toString() {
            return "EncoderConfig: " + this.f4448b + "x" + this.f4449c + " @" + this.f4450d + "-" + this.f4451e + " to '" + this.f4447a.toString() + "' ctxt=" + this.f4452f;
        }
    }

    /* renamed from: a */
    public void m20020a(C2515c c2515c) {
        Logger.m20031a("TextureMovieEncoder", "record_startRecording ");
        this.f4437t = c2515c.f4448b;
        this.f4438u = c2515c.f4449c;
        Logger.m20031a("TextureMovieEncoder", "Encoder: startRecording() " + c2515c);
        this.f4430m = 5;
        this.f4432o = c2515c.f4453g;
        this.f4433p = System.nanoTime();
        this.f4434q = 0L;
        this.f4435r = 0L;
        synchronized (this.f4427j) {
            if (this.f4429l) {
                Log.e("TextureMovieEncoder", "Encoder thread already running");
                return;
            }
            this.f4429l = true;
            new C2517e("TextureMovieVideoEncoder").start();
            new C2514b().start();
            while (!this.f4428k) {
                try {
                    this.f4427j.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.f4443z = -1L;
            this.f4425h.sendMessage(this.f4425h.obtainMessage(0, c2515c));
        }
    }

    /* renamed from: a */
    public void m20028a() {
        synchronized (this.f4427j) {
            while (!this.f4428k) {
                try {
                    this.f4427j.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Logger.m20031a("record", "record_stopRecording: ");
        this.f4436s = 0;
        MediaMuxerWrapper mediaMuxerWrapper = this.f4424g;
        if (mediaMuxerWrapper == null || !mediaMuxerWrapper.m19984c()) {
            this.f4413K = true;
            VideoEncoderCore videoEncoderCore = this.f4422e;
            if (videoEncoderCore != null) {
                videoEncoderCore.m19980c();
            }
            AudioEncoderCore audioEncoderCore = this.f4423f;
            if (audioEncoderCore != null) {
                audioEncoderCore.m19992a();
            }
        }
        this.f4430m = 4;
        this.f4425h.sendMessage(this.f4425h.obtainMessage(1));
        this.f4425h.sendMessage(this.f4425h.obtainMessage(5));
        this.f4426i.sendMessage(this.f4426i.obtainMessage(7));
        this.f4426i.sendMessage(this.f4426i.obtainMessage(8));
    }

    /* renamed from: a */
    public void m20024a(SurfaceTexture surfaceTexture) {
        synchronized (this.f4427j) {
            if (this.f4428k) {
                synchronized (this.f4407E) {
                    if (!this.f4408F && this.f4412J) {
                        this.f4408F = true;
                        this.f4407E.notifyAll();
                        this.f4430m = 1;
                    }
                }
                if (this.f4408F) {
                    float[] fArr = new float[16];
                    Matrix.setIdentityM(fArr, 0);
                    long timestamp = surfaceTexture.getTimestamp();
                    if (timestamp == 0) {
                        Log.w("TextureMovieEncoder", "HEY: got SurfaceTexture with timestamp of zero");
                    } else {
                        this.f4425h.sendMessage(this.f4425h.obtainMessage(2, (int) (timestamp >> 32), (int) timestamp, fArr));
                    }
                }
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.cloud.media.player.render.encoder.TextureMovieEncoder$e */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C2517e extends Thread {
        public C2517e(String str) {
            super(str);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Looper.prepare();
            synchronized (TextureMovieEncoder.this.f4427j) {
                TextureMovieEncoder.this.f4425h = new HandlerC2516d(TextureMovieEncoder.this);
                TextureMovieEncoder.this.f4428k = true;
                TextureMovieEncoder.this.f4427j.notifyAll();
            }
            Logger.m20030b("TextureMovieEncoder", "Video thread run() Prepare");
            Looper.loop();
            Log.d("TextureMovieEncoder", "Encoder thread exiting");
            synchronized (TextureMovieEncoder.this.f4427j) {
                TextureMovieEncoder.this.f4428k = TextureMovieEncoder.this.f4429l = false;
                TextureMovieEncoder.this.f4425h = null;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.cloud.media.player.render.encoder.TextureMovieEncoder$d */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class HandlerC2516d extends Handler {

        /* renamed from: a */
        private WeakReference<TextureMovieEncoder> f4454a;

        public HandlerC2516d(TextureMovieEncoder textureMovieEncoder) {
            this.f4454a = new WeakReference<>(textureMovieEncoder);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            Object obj = message.obj;
            TextureMovieEncoder textureMovieEncoder = this.f4454a.get();
            if (textureMovieEncoder == null) {
                Log.e("cccc", "VideoEncoderHandler.handleMessage: encoder is null");
                return;
            }
            switch (i) {
                case 0:
                    Logger.m20031a("EncoderVideo", "Video_msg start recording:" + obj);
                    textureMovieEncoder.m20005b((C2515c) obj);
                    return;
                case 1:
                    Logger.m20031a("EncoderVideo", "Video_ msg stop recording");
                    textureMovieEncoder.m19998d();
                    return;
                case 2:
                    Logger.m20031a("EncoderVideo", "Video_ msg frame available()");
                    if (textureMovieEncoder.f4412J) {
                        textureMovieEncoder.m20008a((float[]) obj, (message.arg1 << 32) | (message.arg2 & 4294967295L));
                        return;
                    }
                    return;
                case 3:
                    if (textureMovieEncoder.f4412J) {
                        textureMovieEncoder.m20006b(message.arg1);
                        return;
                    }
                    return;
                case 4:
                    return;
                case 5:
                    Logger.m20031a("EncoderVideo", "Video_ msg quit.");
                    Looper.myLooper().quit();
                    return;
                default:
                    throw new RuntimeException("Unhandled msg what=" + i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m20005b(C2515c c2515c) {
        Log.d("TextureMovieEncoder", "handleStartRecording " + c2515c);
        this.f4406D = c2515c;
        this.f4421d = 0;
        ErrorLogInfo errorLogInfo = new ErrorLogInfo();
        if (m20023a(c2515c.f4452f, c2515c.f4448b, c2515c.f4449c, c2515c.f4450d, c2515c.f4451e, c2515c.f4447a, errorLogInfo)) {
            Logger.m20030b("cccc", "-------------------------------- handleStartRecording " + c2515c);
            this.f4413K = false;
            OnEncoderStatusUpdateListener onEncoderStatusUpdateListener = this.f4431n;
            if (onEncoderStatusUpdateListener != null) {
                onEncoderStatusUpdateListener.onStartSuccess();
                return;
            }
            return;
        }
        String str = errorLogInfo.f4467e;
        m20025a(1111, "开始录制编码错误" + c2515c.toString() + " , 错误信息：" + str);
    }

    /* renamed from: a */
    public void m20022a(OnDrawEncoderFrameCallback onDrawEncoderFrameCallback) {
        this.f4405C = onDrawEncoderFrameCallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m20008a(float[] fArr, long j) {
        Logger.m20030b("TextureMovieEncoder", "onRecord -- handleFrameAvailable()");
        if (this.f4405C != null) {
            GLES20.glViewport(0, 0, this.f4437t, this.f4438u);
            this.f4405C.onDrawEncoderFrame();
        }
        try {
            this.f4422e.m19982a(false);
        } catch (Exception e) {
            e.printStackTrace();
            String message = e.getMessage();
            m20025a(1110, "录制编码错误transform:" + fArr + "timestampNanos:" + j + " , Exception : " + message);
        }
        if (this.f4418a != null) {
            long m20007b = m20007b();
            if (this.f4443z == -1) {
                this.f4443z = m20007b;
                this.f4403A = 0L;
            }
            long j2 = m20007b * 1000;
            this.f4418a.m20045a(j2);
            this.f4418a.m20042c();
            this.f4403A = m20007b - this.f4443z;
            Logger.m20031a("EncoderPTS", "video onRecord -- progress=  " + (this.f4403A / 1000) + "-- pts=" + j2);
            OnEncoderStatusUpdateListener onEncoderStatusUpdateListener = this.f4431n;
            if (onEncoderStatusUpdateListener != null) {
                onEncoderStatusUpdateListener.onProgress(this.f4403A / 1000);
            }
        }
    }

    /* renamed from: a */
    public static long m20011a(String str) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(str);
            return Integer.parseInt(mediaMetadataRetriever.extractMetadata(9));
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m19998d() {
        Log.d("TextureMovieEncoder", "-------------------------------- handleStopRecording --- end encode" + this.f4406D);
        this.f4413K = true;
        try {
            this.f4422e.m19982a(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        m19996e();
        Log.d("TextureMovieEncoder", "handleStopRecording before stop success");
        while (!this.f4410H && this.f4440w) {
            synchronized (this.f4409G) {
                try {
                    this.f4409G.wait();
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
        }
        long m20011a = m20011a(this.f4411I);
        Logger.m20031a("record_time", "record_duration:" + (this.f4403A / 1000) + " , file: " + m20011a);
        MediaExtractor mediaExtractor = new MediaExtractor();
        try {
            mediaExtractor.setDataSource(this.f4411I);
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        int trackCount = mediaExtractor.getTrackCount();
        for (int i = 0; i < trackCount; i++) {
            Logger.m20031a("record_time", "record_startExtracor:" + mediaExtractor.getTrackFormat(i));
        }
        OnEncoderStatusUpdateListener onEncoderStatusUpdateListener = this.f4431n;
        if (onEncoderStatusUpdateListener != null) {
            onEncoderStatusUpdateListener.onProgress(m20011a);
        }
        this.f4410H = false;
        this.f4439v = true;
        OnEncoderStatusUpdateListener onEncoderStatusUpdateListener2 = this.f4431n;
        if (onEncoderStatusUpdateListener2 != null) {
            onEncoderStatusUpdateListener2.onStopSuccess();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m20006b(int i) {
        this.f4420c = i;
    }

    /* renamed from: a */
    private boolean m20023a(EGLContext eGLContext, int i, int i2, int i3, int i4, File file, ErrorLogInfo errorLogInfo) {
        try {
            Log.e("cccc", "preparedEncoder()==============");
            this.f4411I = file.toString();
            this.f4424g = new MediaMuxerWrapper(file.toString());
            this.f4422e = new VideoEncoderCore(i, i2, i3, i4, this.f4424g);
            this.f4423f = new AudioEncoderCore(this.f4424g);
            this.f4412J = true;
            this.f4419b = new EglCore(eGLContext, 1);
            this.f4418a = new WindowSurface(this.f4419b, this.f4422e.m19983a(), true);
            this.f4418a.m20043b();
            return true;
        } catch (IOException e) {
            if (errorLogInfo != null && !TextUtils.isEmpty(e.getMessage())) {
                errorLogInfo.f4467e = e.getMessage();
            }
            e.printStackTrace();
            return false;
        } catch (IllegalStateException e2) {
            if (errorLogInfo != null && !TextUtils.isEmpty(e2.getMessage())) {
                errorLogInfo.f4467e = e2.getMessage();
            }
            e2.printStackTrace();
            this.f4412J = false;
            return false;
        } catch (RuntimeException e3) {
            if (errorLogInfo != null && !TextUtils.isEmpty(e3.getMessage())) {
                errorLogInfo.f4467e = e3.getMessage();
            }
            e3.printStackTrace();
            this.f4412J = false;
            return false;
        }
    }

    /* renamed from: e */
    private void m19996e() {
        try {
            this.f4422e.m19981b();
            if (this.f4418a != null) {
                this.f4418a.m20032d();
                this.f4418a = null;
            }
            if (this.f4419b != null) {
                this.f4419b.m20054a();
                this.f4419b = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m20009a(byte[] bArr, int i) {
        if (this.f4440w && this.f4408F && this.f4412J) {
            try {
                if (this.f4413K) {
                    return;
                }
                if (this.f4404B == null) {
                    this.f4404B = ByteBuffer.allocate(4096);
                    this.f4404B.clear();
                    this.f4404B.position(0);
                }
                this.f4404B.put(bArr, 0, i);
                if (this.f4404B.position() == 4096) {
                    ByteBuffer allocate = ByteBuffer.allocate(4096);
                    allocate.clear();
                    this.f4404B.flip();
                    allocate.put(this.f4404B);
                    this.f4404B.clear();
                    this.f4404B.position(0);
                    this.f4426i.sendMessage(this.f4425h.obtainMessage(6, allocate.capacity(), 0, allocate));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.cloud.media.player.render.encoder.TextureMovieEncoder$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class HandlerC2513a extends Handler {

        /* renamed from: b */
        private WeakReference<TextureMovieEncoder> f4445b;

        public HandlerC2513a(TextureMovieEncoder textureMovieEncoder) {
            this.f4445b = new WeakReference<>(textureMovieEncoder);
            TextureMovieEncoder.this.m19994f();
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            Object obj = message.obj;
            TextureMovieEncoder textureMovieEncoder = this.f4445b.get();
            if (textureMovieEncoder == null) {
                Log.w("TextureMovieEncoder", "AudioEncoderHandler.handleMessage: encoder is null");
                return;
            }
            switch (i) {
                case 6:
                    Logger.m20030b("EncoderAudio", "Audio_ update ..... ");
                    textureMovieEncoder.m20010a((ByteBuffer) obj, message.arg1);
                    return;
                case 7:
                    Logger.m20030b("EncoderAudio", "Audio_ stop audio encode:");
                    textureMovieEncoder.m19993g();
                    return;
                case 8:
                    Logger.m20030b("EncoderAudio", "Audio_ MSG_QUIT.");
                    Looper.myLooper().quit();
                    return;
                default:
                    throw new RuntimeException("Unhandled msg what=" + i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m20010a(ByteBuffer byteBuffer, int i) {
        try {
            byteBuffer.position(i);
            byteBuffer.flip();
            this.f4423f.m19991a(byteBuffer, 0, i, m20001c());
            this.f4423f.m19990b();
        } catch (Exception e) {
            e.printStackTrace();
            m20025a(1114, "音频编码错误");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public void m19993g() {
        try {
            this.f4423f.m19991a(null, 0, 0, m20001c());
            this.f4423f.m19990b();
        } catch (Exception e) {
            e.printStackTrace();
            m20025a(1115, "结束音频编码错误");
        }
        Logger.m20031a("record_time", "AudioStop");
        this.f4423f.m19989c();
        synchronized (this.f4409G) {
            this.f4410H = true;
            this.f4409G.notifyAll();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.cloud.media.player.render.encoder.TextureMovieEncoder$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C2514b extends Thread {
        private C2514b() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Log.e("cccc", "AudioThread run()");
            Looper.prepare();
            synchronized (TextureMovieEncoder.this.f4427j) {
                TextureMovieEncoder.this.f4440w = true;
                TextureMovieEncoder.this.f4430m = 1;
                TextureMovieEncoder.this.f4426i = new HandlerC2513a(TextureMovieEncoder.this);
                TextureMovieEncoder.this.f4427j.notifyAll();
            }
            Log.e("cccc", "AudioThread prepare");
            Looper.loop();
            Log.e("cccc", "Encoder thread exiting");
            TextureMovieEncoder.this.f4440w = false;
            synchronized (TextureMovieEncoder.this.f4427j) {
                TextureMovieEncoder.this.f4426i = null;
            }
        }
    }

    /* renamed from: b */
    protected long m20007b() {
        long nanoTime = System.nanoTime();
        if (this.f4432o != 0) {
            if (this.f4434q == 0) {
                this.f4434q = nanoTime;
            }
            long j = nanoTime - this.f4434q;
            nanoTime = (((float) j) / this.f4441x) + this.f4432o;
        }
        long j2 = nanoTime / 1000;
        long j3 = this.f4415N;
        if (j2 < j3) {
            j2 = 100 + j3;
        }
        Logger.m20030b("EncoderPTS", "video pts time=" + this.f4415N + "--result=" + j2);
        this.f4415N = j2;
        return j2;
    }

    /* renamed from: c */
    protected long m20001c() {
        long nanoTime = System.nanoTime();
        if (this.f4432o != 0) {
            if (this.f4435r == 0) {
                this.f4435r = nanoTime;
            }
            long j = nanoTime - this.f4435r;
            nanoTime = (((float) j) / this.f4441x) + this.f4432o;
        }
        long j2 = nanoTime / 1000;
        long j3 = this.f4416O;
        if (j2 < j3) {
            j2 = 100 + j3;
        }
        Logger.m20031a("EncoderPTS", "audio pts time=" + this.f4416O + "--result=" + j2);
        this.f4416O = j2;
        return j2;
    }

    /* renamed from: a */
    public void m20021a(OnEncoderStatusUpdateListener onEncoderStatusUpdateListener) {
        this.f4431n = onEncoderStatusUpdateListener;
    }

    /* renamed from: a */
    private void m20025a(int i, String str) {
        OnEncoderStatusUpdateListener onEncoderStatusUpdateListener = this.f4431n;
        if (onEncoderStatusUpdateListener != null) {
            onEncoderStatusUpdateListener.onError(i, str);
        }
    }

    /* renamed from: a */
    public void m20027a(float f) {
        this.f4442y = f;
    }
}
