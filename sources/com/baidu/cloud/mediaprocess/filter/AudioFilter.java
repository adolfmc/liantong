package com.baidu.cloud.mediaprocess.filter;

import android.media.AudioTrack;
import android.util.Log;
import com.baidu.cloud.framework.InPort;
import com.baidu.cloud.framework.OutPort;
import com.baidu.cloud.framework.OutPortFactory;
import com.baidu.cloud.framework.Sink;
import com.baidu.cloud.framework.frame.AudioFrameBuffer;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AudioFilter implements Sink<AudioFrameBuffer> {
    public AudioTrack audioTrack;

    /* renamed from: a */
    public volatile boolean f4651a = true;

    /* renamed from: b */
    public volatile boolean f4652b = true;

    /* renamed from: c */
    public volatile boolean f4653c = false;

    /* renamed from: d */
    public Thread f4654d = null;

    /* renamed from: e */
    public volatile BlockingQueue<AudioFrameBuffer> f4655e = null;

    /* renamed from: f */
    public volatile List<RingByteBuffer> f4656f = null;

    /* renamed from: g */
    public volatile int f4657g = 0;

    /* renamed from: h */
    public volatile float f4658h = 1.0f;

    /* renamed from: i */
    public volatile float f4659i = 1.0f;

    /* renamed from: j */
    public OutPort<AudioFrameBuffer> f4660j = new OutPortFactory().createOutPort();

    /* renamed from: k */
    public InPort<AudioFrameBuffer> f4661k = new AudioFilterMasterInPortFactory(null).createInPort();

    /* renamed from: l */
    public ArrayList<InPort<AudioFrameBuffer>> f4662l = null;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class AudioFilterMasterInPortFactory implements InPort.Factory<AudioFrameBuffer> {
        public /* synthetic */ AudioFilterMasterInPortFactory(RunnableC25401 runnableC25401) {
        }

        @Override // com.baidu.cloud.framework.InPort.Factory
        public InPort<AudioFrameBuffer> createInPort() {
            return new InPort<AudioFrameBuffer>() { // from class: com.baidu.cloud.mediaprocess.filter.AudioFilter.AudioFilterMasterInPortFactory.1
                @Override // com.baidu.cloud.framework.IPort
                public void onConfigure(Object obj) {
                }

                @Override // com.baidu.cloud.framework.IPort
                public void onFrame(AudioFrameBuffer audioFrameBuffer) {
                    if (AudioFilter.this.f4653c) {
                        try {
                            if (AudioFilter.this.f4655e != null) {
                                AudioFilter.this.f4655e.put(audioFrameBuffer);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class AudioFilterSubInPortFactory implements InPort.Factory<AudioFrameBuffer> {

        /* renamed from: a */
        public int f4666a;

        public /* synthetic */ AudioFilterSubInPortFactory(RunnableC25401 runnableC25401) {
        }

        @Override // com.baidu.cloud.framework.InPort.Factory
        public InPort<AudioFrameBuffer> createInPort() {
            return new InPort<AudioFrameBuffer>() { // from class: com.baidu.cloud.mediaprocess.filter.AudioFilter.AudioFilterSubInPortFactory.1
                @Override // com.baidu.cloud.framework.IPort
                public void onConfigure(Object obj) {
                    AudioFilterSubInPortFactory.this.f4666a = ((Integer) obj).intValue();
                }

                @Override // com.baidu.cloud.framework.IPort
                public void onFrame(AudioFrameBuffer audioFrameBuffer) {
                    if (audioFrameBuffer == null || audioFrameBuffer.size <= 0 || AudioFilter.this.f4657g == 0 || !AudioFilter.this.f4653c) {
                        return;
                    }
                    byte[] bArr = new byte[audioFrameBuffer.size];
                    audioFrameBuffer.buffer.get(bArr);
                    ((RingByteBuffer) AudioFilter.this.f4656f.get(AudioFilterSubInPortFactory.this.f4666a)).put(bArr, audioFrameBuffer.size);
                }
            };
        }
    }

    /* renamed from: a */
    public final void m19932a() {
        byte[] bArr;
        int i;
        while (this.f4653c) {
            try {
                try {
                    AudioFrameBuffer take = this.f4655e.take();
                    byte[] bArr2 = null;
                    if (take.buffer == null) {
                        if (this.f4660j.isPortLinked()) {
                            this.f4660j.onFrame((OutPort<AudioFrameBuffer>) new AudioFrameBuffer(null, take.ptsUs, take.size));
                            return;
                        }
                        return;
                    }
                    int i2 = take.size;
                    ArrayList arrayList = new ArrayList();
                    int i3 = Integer.MAX_VALUE;
                    for (int i4 = 0; i4 < this.f4657g; i4++) {
                        if (this.f4656f.get(i4).size() > 0) {
                            i3 = Math.min(this.f4656f.get(i4).size(), i3);
                            arrayList.add(Integer.valueOf(i4));
                        }
                    }
                    int size = arrayList.size();
                    ByteBuffer byteBuffer = take.buffer;
                    byte[] bArr3 = new byte[take.size];
                    byteBuffer.get(bArr3);
                    int min = Math.min(i2, i3);
                    if (this.f4657g <= 0 || size <= 0) {
                        if (this.f4658h >= 0.0f && this.f4658h < 1.0f) {
                            for (int i5 = 0; i5 < min - 1; i5 += 2) {
                                short s = (short) (((short) ((bArr3[i5] & 255) | ((bArr3[i] & 255) << 8))) * this.f4658h);
                                bArr3[i5] = (byte) (s & 255);
                                bArr3[i5 + 1] = (byte) ((s >> 8) & 255);
                            }
                        }
                        bArr = null;
                    } else {
                        bArr = new byte[min];
                        if (min > 0 && size > 0) {
                            byte[][] bArr4 = (byte[][]) Array.newInstance(byte.class, size, min);
                            for (int i6 = 0; i6 < size; i6++) {
                                this.f4656f.get(((Integer) arrayList.get(i6)).intValue()).get(bArr4[i6], min);
                            }
                            for (int i7 = 0; i7 < min - 1; i7 += 2) {
                                for (int i8 = 0; i8 < size; i8++) {
                                    int i9 = i7 + 1;
                                    short s2 = (short) (((short) ((bArr4[i8][i7] & 255) | ((bArr4[i8][i9] & 255) << 8))) * this.f4659i);
                                    bArr[i7] = (byte) (bArr[i7] + ((byte) (s2 & 255)));
                                    bArr[i9] = (byte) (bArr[i9] + ((byte) ((s2 >> 8) & 255)));
                                }
                            }
                        }
                        for (int i10 = 0; i10 < min - 1; i10 += 2) {
                            int i11 = i10 + 1;
                            short s3 = (short) ((bArr[i10] & 255) | ((bArr[i11] & 255) << 8));
                            int i12 = (int) (((short) ((bArr3[i10] & 255) | ((bArr3[i11] & 255) << 8))) * this.f4658h);
                            short s4 = (short) ((s3 >= 0 || i12 >= 0) ? (i12 + s3) - ((i12 * s3) / 32768) : i12 + s3 + ((i12 * s3) / 32767));
                            bArr3[i10] = (byte) (s4 & 255);
                            bArr3[i11] = (byte) ((s4 >> 8) & 255);
                        }
                    }
                    if (this.f4651a) {
                        bArr2 = bArr3;
                    } else if (this.f4657g > 0) {
                        bArr2 = bArr;
                    }
                    if (bArr2 != null && bArr2.length > 0 && this.f4652b) {
                        this.audioTrack.write(bArr2, 0, min);
                    }
                    if (this.f4660j.isPortLinked()) {
                        int length = bArr3.length;
                        ByteBuffer allocate = ByteBuffer.allocate(length);
                        allocate.put(bArr3, 0, length);
                        allocate.flip();
                        this.f4660j.onFrame((OutPort<AudioFrameBuffer>) new AudioFrameBuffer(allocate, take.ptsUs, length));
                    }
                } catch (InterruptedException unused) {
                    Log.d("AudioFilter", "break from mixingLoop, because queue.take is interrupt");
                    return;
                }
            } catch (Exception e) {
                Log.d("AudioFilter", Log.getStackTraceString(e));
                e.printStackTrace();
                return;
            }
        }
    }

    public int addSubTrack() {
        this.f4656f.add(new RingByteBuffer(6291456));
        this.f4662l.add(new AudioFilterSubInPortFactory(null).createInPort());
        int i = this.f4657g;
        this.f4657g = i + 1;
        return i;
    }

    public void clearMasterTrackQueue() {
        if (this.f4655e != null) {
            this.f4655e.clear();
        }
        Log.d("AudioFilter", "clear master track over");
    }

    public void clearSubTrack() {
        for (int i = 0; i < this.f4657g; i++) {
            this.f4656f.get(i).reset();
        }
        if (this.f4656f != null) {
            this.f4656f.clear();
        }
        ArrayList<InPort<AudioFrameBuffer>> arrayList = this.f4662l;
        if (arrayList != null) {
            arrayList.clear();
        }
        this.f4657g = 0;
    }

    @Override // com.baidu.cloud.framework.Sink
    public InPort<AudioFrameBuffer> getInPort() {
        return this.f4661k;
    }

    public InPort<AudioFrameBuffer> getMasterInPort() {
        return this.f4661k;
    }

    @Override // com.baidu.cloud.framework.Sink
    public OutPort<AudioFrameBuffer> getOutPort() {
        return this.f4660j;
    }

    public InPort<AudioFrameBuffer> getSubInPort(int i) {
        return this.f4662l.get(i);
    }

    public float getSubTrackGain() {
        return this.f4659i;
    }

    public void release() {
        this.f4653c = false;
        try {
            if (this.f4654d != null) {
                this.f4654d.interrupt();
                this.f4654d.join(1000L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AudioTrack audioTrack = this.audioTrack;
        if (audioTrack != null) {
            audioTrack.release();
            this.audioTrack = null;
        }
        resetBuffer();
        this.f4655e = null;
        this.f4656f = null;
        this.f4662l = null;
    }

    public void resetBuffer() {
        clearMasterTrackQueue();
        clearSubTrack();
    }

    public void setMasterTrackGain(float f) {
        this.f4658h = Math.max(0.0f, Math.min(1.0f, f));
    }

    public void setNeedRenderMasterTrack(boolean z) {
        this.f4651a = z;
    }

    public void setNeedRendering(boolean z) {
        this.f4652b = z;
    }

    public void setSubTrackGain(float f) {
        this.f4659i = Math.max(0.0f, Math.min(1.0f, f));
    }

    public void setup(boolean z) {
        setup(z, 3, -1);
    }

    public void setup(boolean z, int i, int i2) {
        this.f4655e = new ArrayBlockingQueue(256);
        this.f4656f = new ArrayList(3);
        this.f4662l = new ArrayList<>(3);
        int minBufferSize = AudioTrack.getMinBufferSize(44100, 12, 2);
        Log.i("AudioFilter", "AudioTrack getMinBufferSize=" + minBufferSize + ";audioSessionId=" + i2);
        if (i2 >= 0) {
            this.audioTrack = new AudioTrack(i, 44100, 12, 2, minBufferSize, 1, i2);
        } else {
            this.audioTrack = new AudioTrack(i, 44100, 12, 2, minBufferSize, 1);
        }
        this.audioTrack.play();
        this.f4651a = z;
        this.f4653c = true;
        this.f4654d = new Thread(new Runnable() { // from class: com.baidu.cloud.mediaprocess.filter.AudioFilter.1
            @Override // java.lang.Runnable
            public void run() {
                AudioFilter.this.m19932a();
            }
        });
        this.f4654d.start();
    }
}
