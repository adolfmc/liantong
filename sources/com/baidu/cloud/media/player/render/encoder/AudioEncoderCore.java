package com.baidu.cloud.media.player.render.encoder;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.util.Log;
import android.view.Surface;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.cloud.media.player.render.encoder.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AudioEncoderCore {

    /* renamed from: a */
    private MediaMuxerWrapper f4456a;

    /* renamed from: b */
    private MediaCodec f4457b;

    /* renamed from: d */
    private int f4459d;

    /* renamed from: e */
    private boolean f4460e;

    /* renamed from: f */
    private long f4461f = 0;

    /* renamed from: g */
    private boolean f4462g = false;

    /* renamed from: c */
    private MediaCodec.BufferInfo f4458c = new MediaCodec.BufferInfo();

    public AudioEncoderCore(MediaMuxerWrapper mediaMuxerWrapper) {
        MediaFormat createAudioFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", 44100, 2);
        createAudioFormat.setInteger("aac-profile", 2);
        createAudioFormat.setInteger("channel-mask", 16);
        createAudioFormat.setInteger("bitrate", 128000);
        createAudioFormat.setInteger("max-input-size", 163840);
        try {
            this.f4457b = MediaCodec.createEncoderByType("audio/mp4a-latm");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.f4457b.configure(createAudioFormat, (Surface) null, (MediaCrypto) null, 1);
        this.f4457b.start();
        this.f4459d = -1;
        this.f4460e = false;
        this.f4456a = mediaMuxerWrapper;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m19991a(ByteBuffer byteBuffer, int i, int i2, long j) throws Exception {
        int dequeueInputBuffer;
        Log.d("AudioEncoder", "start encode ------ ");
        ByteBuffer[] inputBuffers = this.f4457b.getInputBuffers();
        while (true) {
            dequeueInputBuffer = this.f4457b.dequeueInputBuffer(10000);
            if (dequeueInputBuffer >= 0) {
                break;
            } else if (dequeueInputBuffer == -1) {
                Log.e("AudioEncoder", "wait for MediaCodec audio encoder");
            }
        }
        ByteBuffer byteBuffer2 = inputBuffers[dequeueInputBuffer];
        byteBuffer2.clear();
        if (byteBuffer != null) {
            byteBuffer2.put(byteBuffer);
        }
        if (i2 <= 0) {
            this.f4457b.queueInputBuffer(dequeueInputBuffer, i, i2, j, 4);
        } else {
            this.f4457b.queueInputBuffer(dequeueInputBuffer, i, i2, j, 0);
        }
    }

    /* renamed from: a */
    public synchronized void m19992a() {
        this.f4462g = true;
    }

    /* renamed from: b */
    public void m19990b() throws Exception {
        ByteBuffer[] outputBuffers = this.f4457b.getOutputBuffers();
        while (true) {
            int dequeueOutputBuffer = this.f4457b.dequeueOutputBuffer(this.f4458c, 10000);
            if (dequeueOutputBuffer == -1) {
                return;
            }
            if (dequeueOutputBuffer == -3) {
                outputBuffers = this.f4457b.getOutputBuffers();
            } else if (dequeueOutputBuffer == -2) {
                if (this.f4460e) {
                    throw new RuntimeException("format changed twice");
                }
                this.f4459d = this.f4456a.m19986a(this.f4457b.getOutputFormat());
                if (!this.f4456a.m19988a()) {
                    synchronized (this.f4456a) {
                        while (!this.f4456a.m19984c()) {
                            try {
                                this.f4456a.wait(100L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                this.f4460e = true;
            } else if (dequeueOutputBuffer < 0) {
                Log.e("AudioEncoder", "unexpected result from encoder.dequeueOutputBuffer: " + dequeueOutputBuffer);
            } else {
                ByteBuffer byteBuffer = outputBuffers[dequeueOutputBuffer];
                if (byteBuffer == null) {
                    throw new RuntimeException("encoderOutputBuffer " + dequeueOutputBuffer + " was null");
                }
                if ((this.f4458c.flags & 2) != 0) {
                    this.f4458c.size = 0;
                }
                if (this.f4458c.size != 0) {
                    if (!this.f4460e) {
                        throw new RuntimeException("muxer hasn't started");
                    }
                    long j = this.f4461f;
                    if (j != 0 && j > this.f4458c.presentationTimeUs) {
                        this.f4458c.presentationTimeUs = this.f4461f + 20000;
                    }
                    byteBuffer.position(this.f4458c.offset);
                    byteBuffer.limit(this.f4458c.offset + this.f4458c.size);
                    this.f4456a.m19987a(this.f4459d, byteBuffer, this.f4458c);
                    this.f4461f = this.f4458c.presentationTimeUs;
                }
                this.f4457b.releaseOutputBuffer(dequeueOutputBuffer, false);
                if ((this.f4458c.flags & 4) != 0) {
                    return;
                }
            }
        }
    }

    /* renamed from: c */
    public void m19989c() {
        try {
            if (this.f4457b != null) {
                this.f4457b.stop();
                this.f4457b.release();
                this.f4457b = null;
            }
            if (this.f4456a != null) {
                this.f4456a.m19985b();
                this.f4456a = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
