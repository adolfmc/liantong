package com.baidu.cloud.media.player.render.encoder;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Surface;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@TargetApi(16)
/* renamed from: com.baidu.cloud.media.player.render.encoder.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VideoEncoderCore {

    /* renamed from: a */
    private Surface f4473a;

    /* renamed from: b */
    private MediaMuxerWrapper f4474b;

    /* renamed from: c */
    private MediaCodec f4475c;

    /* renamed from: e */
    private int f4477e;

    /* renamed from: f */
    private boolean f4478f;

    /* renamed from: g */
    private Bundle f4479g = new Bundle();

    /* renamed from: h */
    private long f4480h = 0;

    /* renamed from: i */
    private boolean f4481i = false;

    /* renamed from: d */
    private MediaCodec.BufferInfo f4476d = new MediaCodec.BufferInfo();

    @TargetApi(18)
    public VideoEncoderCore(int i, int i2, int i3, int i4, MediaMuxerWrapper mediaMuxerWrapper) throws IOException {
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", i, i2);
        createVideoFormat.setInteger("color-format", 2130708361);
        createVideoFormat.setInteger("bitrate", i3);
        createVideoFormat.setInteger("frame-rate", i4 <= 0 ? 30 : i4);
        createVideoFormat.setInteger("i-frame-interval", 4);
        Log.d("VideoEncoder", "format: " + createVideoFormat);
        this.f4475c = MediaCodec.createEncoderByType("video/avc");
        this.f4475c.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
        this.f4473a = this.f4475c.createInputSurface();
        this.f4475c.start();
        this.f4479g.putInt("request-sync", 0);
        if (Build.VERSION.SDK_INT >= 19) {
            this.f4475c.setParameters(this.f4479g);
        }
        this.f4477e = -1;
        this.f4478f = false;
        this.f4474b = mediaMuxerWrapper;
    }

    /* renamed from: a */
    public Surface m19983a() {
        return this.f4473a;
    }

    /* renamed from: b */
    public void m19981b() {
        Log.d("VideoEncoder", "releasing encoder objects");
        MediaCodec mediaCodec = this.f4475c;
        if (mediaCodec != null) {
            mediaCodec.stop();
            this.f4475c.release();
            this.f4475c = null;
        }
        MediaMuxerWrapper mediaMuxerWrapper = this.f4474b;
        if (mediaMuxerWrapper != null) {
            try {
                mediaMuxerWrapper.m19985b();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
            this.f4474b = null;
        }
    }

    /* renamed from: c */
    public synchronized void m19980c() {
        this.f4481i = true;
    }

    @RequiresApi(api = 18)
    /* renamed from: a */
    public void m19982a(boolean z) throws Exception {
        Log.d("VideoEncoder", "drainEncoder(" + z + ")");
        if (z) {
            Log.d("VideoEncoder", "sending EndOS to encoder");
            this.f4475c.signalEndOfInputStream();
        }
        ByteBuffer[] outputBuffers = this.f4475c.getOutputBuffers();
        while (true) {
            int dequeueOutputBuffer = this.f4475c.dequeueOutputBuffer(this.f4476d, 10000L);
            if (dequeueOutputBuffer == -1) {
                if (!z) {
                    return;
                }
                Log.d("VideoEncoder", "no output available, spinning to await EOS");
            } else if (dequeueOutputBuffer == -3) {
                outputBuffers = this.f4475c.getOutputBuffers();
            } else if (dequeueOutputBuffer == -2) {
                if (this.f4478f) {
                    throw new RuntimeException("format changed twice");
                }
                MediaFormat outputFormat = this.f4475c.getOutputFormat();
                Log.d("VideoEncoder", "encoder output format changed: " + outputFormat);
                this.f4477e = this.f4474b.m19986a(outputFormat);
                if (!this.f4474b.m19988a()) {
                    synchronized (this.f4474b) {
                        while (!this.f4474b.m19984c()) {
                            try {
                                this.f4474b.wait(100L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                this.f4478f = true;
            } else if (dequeueOutputBuffer < 0) {
                Log.w("VideoEncoder", "unexpected result from encoder.dequeueOutputBuffer: " + dequeueOutputBuffer);
            } else {
                ByteBuffer byteBuffer = outputBuffers[dequeueOutputBuffer];
                if (byteBuffer == null) {
                    throw new RuntimeException("encoderOutputBuffer " + dequeueOutputBuffer + " was null");
                }
                if ((this.f4476d.flags & 2) != 0) {
                    Log.d("VideoEncoder", "ignoring BUFFER_FLAG_CODEC_CONFIG");
                    this.f4476d.size = 0;
                }
                if (this.f4476d.size != 0) {
                    if (!this.f4478f) {
                        throw new RuntimeException("muxer hasn't started");
                    }
                    byteBuffer.position(this.f4476d.offset);
                    byteBuffer.limit(this.f4476d.offset + this.f4476d.size);
                    this.f4474b.m19987a(this.f4477e, byteBuffer, this.f4476d);
                    Log.d("VideoEncoder", "sent " + this.f4476d.size + " bytes to muxer, ts=" + this.f4476d.presentationTimeUs);
                }
                this.f4475c.releaseOutputBuffer(dequeueOutputBuffer, false);
                if (Build.VERSION.SDK_INT >= 19 && System.currentTimeMillis() - this.f4480h >= 200) {
                    this.f4475c.setParameters(this.f4479g);
                    this.f4480h = System.currentTimeMillis();
                }
                if ((this.f4476d.flags & 4) != 0) {
                    if (!z) {
                        Log.w("VideoEncoder", "reached end of stream unexpectedly");
                        return;
                    } else {
                        Log.d("VideoEncoder", "end of stream reached");
                        return;
                    }
                }
            }
        }
    }
}
