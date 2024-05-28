package com.baidu.cloud.mediaprocess.encoder;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.util.Log;
import android.view.Surface;
import com.baidu.cloud.framework.InPort;
import com.baidu.cloud.framework.OutPort;
import com.baidu.cloud.framework.OutPortFactory;
import com.baidu.cloud.framework.frame.AudioFrameBuffer;
import com.baidu.cloud.mediaprocess.listener.OnFinishListener;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@TargetApi(16)
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AudioMediaEncoder {

    /* renamed from: a */
    public String f4613a;

    /* renamed from: b */
    public volatile MediaCodec f4614b;

    /* renamed from: c */
    public volatile MediaCodec.BufferInfo f4615c;

    /* renamed from: d */
    public volatile boolean f4616d = false;

    /* renamed from: e */
    public MediaFormat f4617e = null;

    /* renamed from: f */
    public InPort<AudioFrameBuffer> f4618f = new AudioEncodeInPortFactory(null).createInPort();

    /* renamed from: g */
    public OutPort<AudioFrameBuffer> f4619g = new OutPortFactory().createOutPort();

    /* renamed from: h */
    public volatile OnFinishListener f4620h = null;

    /* renamed from: i */
    public boolean f4621i = false;

    /* renamed from: j */
    public volatile int f4622j = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class AudioEncodeInPortFactory implements InPort.Factory<AudioFrameBuffer> {
        public /* synthetic */ AudioEncodeInPortFactory(C25361 c25361) {
        }

        @Override // com.baidu.cloud.framework.InPort.Factory
        public InPort<AudioFrameBuffer> createInPort() {
            return new InPort<AudioFrameBuffer>() { // from class: com.baidu.cloud.mediaprocess.encoder.AudioMediaEncoder.AudioEncodeInPortFactory.1
                @Override // com.baidu.cloud.framework.IPort
                public void onConfigure(Object obj) {
                }

                @Override // com.baidu.cloud.framework.IPort
                public void onFrame(AudioFrameBuffer audioFrameBuffer) {
                    if (audioFrameBuffer == null || audioFrameBuffer.size <= 0) {
                        return;
                    }
                    AudioMediaEncoder.this.push(audioFrameBuffer.buffer.array(), audioFrameBuffer.size, audioFrameBuffer.ptsUs);
                }
            };
        }
    }

    public AudioMediaEncoder(String str) {
        MediaCodecInfo mediaCodecInfo = null;
        this.f4613a = null;
        this.f4614b = null;
        this.f4615c = null;
        this.f4613a = str;
        int codecCount = MediaCodecList.getCodecCount();
        loop0: for (int i = 0; i < codecCount; i++) {
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
        }
        try {
            this.f4614b = MediaCodec.createByCodecName(mediaCodecInfo.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.f4615c = new MediaCodec.BufferInfo();
    }

    public InPort<AudioFrameBuffer> getInPort() {
        return this.f4618f;
    }

    public MediaFormat getMediaFormat() {
        return this.f4617e;
    }

    public OutPort<AudioFrameBuffer> getOutPort() {
        return this.f4619g;
    }

    /* JADX WARN: Code restructure failed: missing block: B:77:0x011b, code lost:
        r18.f4620h.onFinish(true, 0, null);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void push(byte[] r19, int r20, long r21) {
        /*
            Method dump skipped, instructions count: 371
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.cloud.mediaprocess.encoder.AudioMediaEncoder.push(byte[], int, long):void");
    }

    public void release() {
        if (this.f4614b != null) {
            this.f4614b.flush();
            this.f4614b.stop();
            this.f4614b.release();
            this.f4614b = null;
        }
        Log.d("AudioMediaEncoder", "The aac encoder was destroyed!");
    }

    public void setOnProcessOverListener(OnFinishListener onFinishListener) {
        this.f4620h = onFinishListener;
    }

    public boolean setupEncoder(int i, int i2, int i3) {
        MediaFormat createAudioFormat = MediaFormat.createAudioFormat(this.f4613a, i, i2);
        createAudioFormat.setInteger("bitrate", i3 * 1000);
        createAudioFormat.setInteger("max-input-size", 10240);
        createAudioFormat.setInteger("aac-profile", 2);
        this.f4614b.configure(createAudioFormat, (Surface) null, (MediaCrypto) null, 1);
        if (this.f4614b != null) {
            this.f4614b.start();
        }
        this.f4617e = createAudioFormat;
        return true;
    }

    public void signalEndOfInputStream(long j) {
        if (this.f4614b != null) {
            this.f4621i = true;
            push(new byte[0], 0, j);
        }
    }

    public void start() {
        this.f4616d = true;
    }

    public void stop() {
        this.f4616d = false;
    }
}
