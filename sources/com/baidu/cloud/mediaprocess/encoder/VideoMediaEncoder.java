package com.baidu.cloud.mediaprocess.encoder;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Surface;
import com.baidu.cloud.framework.InPort;
import com.baidu.cloud.framework.OutPort;
import com.baidu.cloud.framework.OutPortFactory;
import com.baidu.cloud.framework.frame.VideoFrameBuffer;
import com.baidu.cloud.mediaprocess.listener.OnFinishListener;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@TargetApi(18)
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VideoMediaEncoder {

    /* renamed from: a */
    public static final int[] f4625a = {(int) Math.sqrt(294912.0d), (int) Math.sqrt(983040.0d), (int) Math.sqrt(1966080.0d), (int) Math.sqrt(4423680.0d), (int) Math.sqrt(7864320.0d), (int) Math.sqrt(1.7825792E7d), (int) Math.sqrt(7.1303168E7d), (int) Math.sqrt(2.85212672E8d)};

    /* renamed from: b */
    public static final int[] f4626b = {2, 8, 32, 128, 512, 2048, 32768, 2097152};

    /* renamed from: c */
    public static final int[] f4627c = {12288, 25344, 101376, 168960, 345600, 921600, 1310720, 2097152, 4915200, 8388608};

    /* renamed from: d */
    public static final int[] f4628d = {1, 4, 16, 64, 256, 512, 1024, 2048, 16384, 32768};

    /* renamed from: e */
    public EncoderThread f4629e;

    /* renamed from: f */
    public Surface f4630f;

    /* renamed from: g */
    public volatile MediaCodec f4631g;

    /* renamed from: h */
    public MediaCodecInfo f4632h;

    /* renamed from: i */
    public int f4633i;

    /* renamed from: j */
    public int f4634j;

    /* renamed from: l */
    public String f4636l;

    /* renamed from: k */
    public MediaFormat f4635k = null;

    /* renamed from: m */
    public volatile boolean f4637m = false;

    /* renamed from: n */
    public InPort<VideoFrameBuffer> f4638n = new VideoEncodeInPortFactory(null).createInPort();

    /* renamed from: o */
    public OutPort<VideoFrameBuffer> f4639o = new OutPortFactory().createOutPort();

    /* renamed from: p */
    public volatile OnFinishListener f4640p = null;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class EncoderThread extends Thread {

        /* renamed from: a */
        public VideoMediaEncoder f4641a;

        /* renamed from: b */
        public MediaFormat f4642b;

        /* renamed from: d */
        public EncoderHandler f4644d;

        /* renamed from: e */
        public final Object f4645e = new Object();

        /* renamed from: f */
        public volatile boolean f4646f = false;

        /* renamed from: g */
        public boolean f4647g = false;

        /* renamed from: c */
        public MediaCodec.BufferInfo f4643c = new MediaCodec.BufferInfo();

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        public static class EncoderHandler extends Handler {
            public static final int MSG_END_OF_STREAM = 3;
            public static final int MSG_FRAME_AVAILABLE_SOON = 1;
            public static final int MSG_SHUTDOWN = 2;

            /* renamed from: a */
            public WeakReference<EncoderThread> f4648a;

            public EncoderHandler(EncoderThread encoderThread) {
                this.f4648a = new WeakReference<>(encoderThread);
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                EncoderThread encoderThread = this.f4648a.get();
                if (encoderThread == null) {
                    Log.w("VideoEncoder", "DecoderHandler.handleMessage: weak ref is null");
                } else if (i == 1) {
                    encoderThread.drainEncoder();
                } else if (i == 2) {
                    encoderThread.m19933a();
                } else if (i == 3) {
                    encoderThread.signalEndOfInputStream();
                } else {
                    throw new RuntimeException("unknown message " + i);
                }
            }
        }

        public EncoderThread(VideoMediaEncoder videoMediaEncoder) {
            this.f4641a = videoMediaEncoder;
        }

        /* renamed from: a */
        public void m19933a() {
            Looper.myLooper().quit();
        }

        /* JADX WARN: Code restructure failed: missing block: B:52:0x014c, code lost:
            r7.f4641a.f4640p.onFinish(true, 0, null);
         */
        /* JADX WARN: Code restructure failed: missing block: B:82:?, code lost:
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void drainEncoder() {
            /*
                Method dump skipped, instructions count: 373
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.cloud.mediaprocess.encoder.VideoMediaEncoder.EncoderThread.drainEncoder():void");
        }

        public EncoderHandler getHandler() {
            synchronized (this.f4645e) {
                if (!this.f4646f) {
                    throw new RuntimeException("not ready");
                }
            }
            return this.f4644d;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                Looper.prepare();
                this.f4644d = new EncoderHandler(this);
                Log.d("VideoEncoder", "encoder thread ready");
                synchronized (this.f4645e) {
                    this.f4646f = true;
                    this.f4645e.notifyAll();
                }
                Looper.loop();
                synchronized (this.f4645e) {
                    this.f4646f = false;
                    this.f4644d = null;
                }
            } catch (Exception e) {
                Log.d("VideoEncoder", Log.getStackTraceString(e));
                VideoMediaEncoder videoMediaEncoder = this.f4641a;
                if (videoMediaEncoder != null && videoMediaEncoder.f4640p != null) {
                    this.f4641a.f4640p.onFinish(false, 0, "video encoder error");
                }
            }
            Log.d("VideoEncoder", "looper quit");
        }

        public void signalEndOfInputStream() {
            VideoMediaEncoder videoMediaEncoder = this.f4641a;
            if (videoMediaEncoder == null || videoMediaEncoder.f4631g == null) {
                return;
            }
            Log.d("VideoEncoder", "in encoder thread: endofstream");
            this.f4647g = true;
            this.f4641a.f4631g.signalEndOfInputStream();
            getHandler().sendEmptyMessage(1);
        }

        public void waitUntilReady() {
            synchronized (this.f4645e) {
                while (!this.f4646f) {
                    try {
                        this.f4645e.wait();
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class VideoEncodeInPortFactory implements InPort.Factory<VideoFrameBuffer> {
        public /* synthetic */ VideoEncodeInPortFactory(C25381 c25381) {
        }

        @Override // com.baidu.cloud.framework.InPort.Factory
        public InPort<VideoFrameBuffer> createInPort() {
            return new InPort<VideoFrameBuffer>() { // from class: com.baidu.cloud.mediaprocess.encoder.VideoMediaEncoder.VideoEncodeInPortFactory.1
                @Override // com.baidu.cloud.framework.IPort
                public void onConfigure(Object obj) {
                }

                @Override // com.baidu.cloud.framework.IPort
                public void onFrame(VideoFrameBuffer videoFrameBuffer) {
                    VideoMediaEncoder.this.frameAvailableSoon();
                }
            };
        }
    }

    public VideoMediaEncoder(String str) {
        MediaCodecInfo mediaCodecInfo = null;
        this.f4636l = null;
        int codecCount = MediaCodecList.getCodecCount();
        int i = 0;
        loop0: while (true) {
            if (i >= codecCount) {
                break;
            }
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (codecInfoAt.isEncoder()) {
                for (String str2 : codecInfoAt.getSupportedTypes()) {
                    if (str2.equalsIgnoreCase(str)) {
                        mediaCodecInfo = codecInfoAt;
                        break loop0;
                    }
                }
                continue;
            }
            i++;
        }
        this.f4632h = mediaCodecInfo;
        MediaCodecInfo mediaCodecInfo2 = this.f4632h;
        this.f4631g = mediaCodecInfo2 != null ? MediaCodec.createByCodecName(mediaCodecInfo2.getName()) : MediaCodec.createEncoderByType(str);
        this.f4636l = str;
    }

    /* renamed from: a */
    public final MediaCodecInfo.CodecProfileLevel m19939a(MediaCodecInfo mediaCodecInfo, String str, int i, int i2) {
        int i3;
        str.equals("video/avc");
        this.f4633i = 1;
        int i4 = 0;
        if (str.equals("video/avc")) {
            int i5 = i * i2;
            int i6 = 0;
            while (i6 < 10 && i5 > f4627c[i6]) {
                i6++;
            }
            if (i6 >= 10) {
                i6 = 9;
            }
            i3 = f4628d[i6];
        } else {
            int i7 = 0;
            while (i7 < 8) {
                int[] iArr = f4625a;
                if (i <= iArr[i7] && i2 <= iArr[i7]) {
                    break;
                }
                i7++;
            }
            if (i7 >= 8) {
                i7 = 7;
            }
            i3 = f4626b[i7];
        }
        this.f4634j = i3;
        MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
        MediaCodecInfo.CodecProfileLevel codecProfileLevel = null;
        while (true) {
            MediaCodecInfo.CodecProfileLevel[] codecProfileLevelArr = capabilitiesForType.profileLevels;
            if (i4 >= codecProfileLevelArr.length) {
                break;
            }
            MediaCodecInfo.CodecProfileLevel codecProfileLevel2 = codecProfileLevelArr[i4];
            Log.d("VideoEncoder", "Find a codec profile [" + codecProfileLevel2.profile + "|" + codecProfileLevel2.level + "]");
            if (codecProfileLevel2.profile == this.f4633i && (codecProfileLevel == null || Math.abs(codecProfileLevel.level - this.f4634j) > Math.abs(codecProfileLevel2.level - this.f4634j))) {
                codecProfileLevel = codecProfileLevel2;
            }
            i4++;
        }
        if (codecProfileLevel != null) {
            Log.d("VideoEncoder", "The matched codec profile [" + codecProfileLevel.profile + "|" + codecProfileLevel.level + "]");
        }
        return codecProfileLevel;
    }

    /* renamed from: a */
    public final MediaFormat m19936a(String str, int i, int i2, int i3, int i4, int i5) {
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat(this.f4636l, i, i2);
        createVideoFormat.setInteger("color-format", 2130708361);
        createVideoFormat.setInteger("max-input-size", 0);
        createVideoFormat.setInteger("bitrate", i3 * 1000);
        createVideoFormat.setInteger("frame-rate", i4);
        createVideoFormat.setInteger("i-frame-interval", i5);
        return createVideoFormat;
    }

    @TargetApi(19)
    public void changeBitrate(int i) {
        try {
            if (this.f4631g != null) {
                if (Build.VERSION.SDK_INT >= 19) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("video-bitrate", i * 1000);
                    this.f4631g.setParameters(bundle);
                } else {
                    stop();
                    release();
                    this.f4631g = MediaCodec.createByCodecName(this.f4632h.getName());
                    this.f4635k.setInteger("bitrate", i * 1000);
                    this.f4631g.configure(this.f4635k, (Surface) null, (MediaCrypto) null, 1);
                    this.f4630f = this.f4631g.createInputSurface();
                    this.f4631g.start();
                    this.f4629e = new EncoderThread(this);
                    this.f4629e.start();
                    this.f4629e.waitUntilReady();
                    start();
                }
            }
        } catch (Exception e) {
            Log.d("VideoEncoder", "DecoderThread.changeBitrate exception:" + Log.getStackTraceString(e));
        }
    }

    public void frameAvailableSoon() {
        if (this.f4637m) {
            EncoderThread.EncoderHandler handler = this.f4629e.getHandler();
            if (handler.hasMessages(1)) {
                return;
            }
            handler.sendMessage(handler.obtainMessage(1));
        }
    }

    public InPort<VideoFrameBuffer> getInPort() {
        return this.f4638n;
    }

    public Surface getInputSurface() {
        return this.f4630f;
    }

    public MediaFormat getMediaFormat() {
        return this.f4635k;
    }

    public OutPort<VideoFrameBuffer> getOutPort() {
        return this.f4639o;
    }

    public void release() {
        EncoderThread encoderThread = this.f4629e;
        if (encoderThread != null) {
            EncoderThread.EncoderHandler handler = encoderThread.getHandler();
            handler.sendMessage(handler.obtainMessage(2));
            try {
                this.f4629e.join();
            } catch (InterruptedException e) {
                Log.w("VideoEncoder", "Encoder thread join() was interrupted", e);
            }
        }
        if (this.f4631g != null) {
            this.f4631g.flush();
            this.f4631g.stop();
            this.f4631g.release();
            this.f4631g = null;
        }
        Log.d("VideoEncoder", "The avc encoder was destroyed!");
    }

    @TargetApi(19)
    public boolean requestKeyFrame() {
        Bundle bundle = new Bundle();
        bundle.putInt("request-sync", 0);
        try {
            this.f4631g.setParameters(bundle);
            return true;
        } catch (IllegalStateException unused) {
            return false;
        }
    }

    public void setOnProcessOverListener(OnFinishListener onFinishListener) {
        this.f4640p = onFinishListener;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setupEncoder(int r14, int r15, int r16, int r17, int r18) {
        /*
            r13 = this;
            r7 = r13
            r8 = 1
            r9 = 0
            android.media.MediaCodecInfo r0 = r7.f4632h     // Catch: java.lang.Throwable -> L34
            java.lang.String r1 = r7.f4636l     // Catch: java.lang.Throwable -> L34
            r10 = r14
            r11 = r15
            android.media.MediaCodecInfo$CodecProfileLevel r12 = r13.m19939a(r0, r1, r14, r15)     // Catch: java.lang.Throwable -> L36
            java.lang.String r1 = r7.f4636l     // Catch: java.lang.Throwable -> L36
            r0 = r13
            r2 = r14
            r3 = r15
            r4 = r16
            r5 = r17
            r6 = r18
            android.media.MediaFormat r0 = r0.m19936a(r1, r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L36
            if (r12 == 0) goto L2c
            java.lang.String r1 = "profile"
            int r2 = r12.profile     // Catch: java.lang.Throwable -> L36
            r0.setInteger(r1, r2)     // Catch: java.lang.Throwable -> L36
            java.lang.String r1 = "level"
            int r2 = r12.level     // Catch: java.lang.Throwable -> L36
            r0.setInteger(r1, r2)     // Catch: java.lang.Throwable -> L36
        L2c:
            android.media.MediaCodec r1 = r7.f4631g     // Catch: java.lang.Throwable -> L36
            r1.configure(r0, r9, r9, r8)     // Catch: java.lang.Throwable -> L36
            r7.f4635k = r0     // Catch: java.lang.Throwable -> L36
            goto L53
        L34:
            r10 = r14
            r11 = r15
        L36:
            java.lang.String r0 = "VideoEncoder"
            java.lang.String r1 = "Failed to setupEncoder, using default configuration!"
            android.util.Log.w(r0, r1)
            java.lang.String r1 = r7.f4636l
            r0 = r13
            r2 = r14
            r3 = r15
            r4 = r16
            r5 = r17
            r6 = r18
            android.media.MediaFormat r0 = r0.m19936a(r1, r2, r3, r4, r5, r6)
            android.media.MediaCodec r1 = r7.f4631g
            r1.configure(r0, r9, r9, r8)
            r7.f4635k = r0
        L53:
            android.media.MediaCodec r0 = r7.f4631g
            android.view.Surface r0 = r0.createInputSurface()
            r7.f4630f = r0
            android.media.MediaCodec r0 = r7.f4631g
            if (r0 == 0) goto L64
            android.media.MediaCodec r0 = r7.f4631g
            r0.start()
        L64:
            com.baidu.cloud.mediaprocess.encoder.VideoMediaEncoder$EncoderThread r0 = new com.baidu.cloud.mediaprocess.encoder.VideoMediaEncoder$EncoderThread
            r0.<init>(r13)
            r7.f4629e = r0
            com.baidu.cloud.mediaprocess.encoder.VideoMediaEncoder$EncoderThread r0 = r7.f4629e
            r0.start()
            com.baidu.cloud.mediaprocess.encoder.VideoMediaEncoder$EncoderThread r0 = r7.f4629e
            r0.waitUntilReady()
            java.lang.String r0 = "VideoEncoder"
            java.lang.String r1 = "AVCEncoder was setup."
            android.util.Log.d(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.cloud.mediaprocess.encoder.VideoMediaEncoder.setupEncoder(int, int, int, int, int):void");
    }

    public void signalEndOfInputStream() {
        if (this.f4637m) {
            EncoderThread.EncoderHandler handler = this.f4629e.getHandler();
            handler.sendMessage(handler.obtainMessage(3));
        }
    }

    public void start() {
        this.f4637m = true;
    }

    public void stop() {
        this.f4637m = false;
    }
}
