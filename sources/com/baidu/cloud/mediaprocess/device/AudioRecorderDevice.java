package com.baidu.cloud.mediaprocess.device;

import android.media.AudioRecord;
import android.media.audiofx.AcousticEchoCanceler;
import android.util.Log;
import com.baidu.cloud.framework.OutPort;
import com.baidu.cloud.framework.OutPortFactory;
import com.baidu.cloud.framework.frame.AudioFrameBuffer;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AudioRecorderDevice {

    /* renamed from: a */
    public byte[] f4540a;

    /* renamed from: b */
    public AudioRecord f4541b;

    /* renamed from: c */
    public volatile boolean f4542c;

    /* renamed from: d */
    public int f4543d;

    /* renamed from: e */
    public int f4544e;

    /* renamed from: f */
    public int f4545f;

    /* renamed from: g */
    public int f4546g;

    /* renamed from: h */
    public volatile boolean f4547h;

    /* renamed from: i */
    public long f4548i;

    /* renamed from: j */
    public volatile boolean f4549j;

    /* renamed from: k */
    public AcousticEchoCanceler f4550k;

    /* renamed from: l */
    public Thread f4551l;

    /* renamed from: m */
    public volatile boolean f4552m;

    /* renamed from: n */
    public OutPort<AudioFrameBuffer> f4553n;

    public AudioRecorderDevice(int i) {
        this(i, 2, 7);
    }

    public AudioRecorderDevice(int i, int i2, int i3) {
        this.f4540a = null;
        this.f4541b = null;
        this.f4542c = false;
        this.f4543d = 2;
        this.f4544e = 44100;
        this.f4545f = 2;
        this.f4546g = 7;
        this.f4547h = false;
        this.f4548i = 0L;
        this.f4549j = false;
        this.f4550k = null;
        this.f4553n = new OutPortFactory().createOutPort();
        this.f4544e = i;
        this.f4543d = i2;
        this.f4547h = false;
        this.f4546g = i3;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00b9  */
    @android.annotation.SuppressLint({"InlinedApi"})
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m19971a() {
        /*
            Method dump skipped, instructions count: 263
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.cloud.mediaprocess.device.AudioRecorderDevice.m19971a():void");
    }

    public synchronized void closeAudioRecorder() {
        this.f4552m = false;
        this.f4542c = false;
        AudioRecord audioRecord = this.f4541b;
        if (audioRecord != null) {
            audioRecord.setRecordPositionUpdateListener(null);
            this.f4541b.stop();
        }
        if (this.f4551l != null) {
            Log.i("AudioCaptureDevice", "stop audio worker thread");
            this.f4551l.interrupt();
            try {
                this.f4551l.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.f4551l = null;
        }
        AcousticEchoCanceler acousticEchoCanceler = this.f4550k;
        if (acousticEchoCanceler != null) {
            acousticEchoCanceler.release();
        }
    }

    public int getAudioFormat() {
        return this.f4545f;
    }

    public int getChannelCount() {
        return this.f4543d;
    }

    public OutPort<AudioFrameBuffer> getOutPort() {
        return this.f4553n;
    }

    public int getRecorderAudioSessionId() {
        AudioRecord audioRecord = this.f4541b;
        if (audioRecord == null) {
            return -1;
        }
        return audioRecord.getAudioSessionId();
    }

    public int getSampleRate() {
        return this.f4544e;
    }

    public synchronized boolean openAudioRecorder() {
        m19971a();
        this.f4541b.startRecording();
        this.f4542c = true;
        this.f4551l = new Thread(new Runnable() { // from class: com.baidu.cloud.mediaprocess.device.AudioRecorderDevice.1
            @Override // java.lang.Runnable
            public void run() {
                AudioRecorderDevice audioRecorderDevice = AudioRecorderDevice.this;
                while (audioRecorderDevice.f4552m && audioRecorderDevice.f4541b != null && !Thread.interrupted()) {
                    if (audioRecorderDevice.f4542c) {
                        AudioRecord audioRecord = audioRecorderDevice.f4541b;
                        byte[] bArr = audioRecorderDevice.f4540a;
                        int read = audioRecord.read(bArr, 0, bArr.length);
                        if (read <= 0 || (audioRecorderDevice.f4549j && read != audioRecorderDevice.f4540a.length)) {
                            Log.w("AudioCaptureDevice", "[AudioRecord] warning, no data to read or wrong data size.");
                        } else {
                            if (audioRecorderDevice.f4547h) {
                                Arrays.fill(audioRecorderDevice.f4540a, (byte) 0);
                            }
                            long nanoTime = (System.nanoTime() - audioRecorderDevice.f4548i) / 1000;
                            if (audioRecorderDevice.f4553n.isPortLinked()) {
                                ByteBuffer allocate = ByteBuffer.allocate(read);
                                allocate.put(audioRecorderDevice.f4540a, 0, read);
                                allocate.flip();
                                AudioFrameBuffer audioFrameBuffer = new AudioFrameBuffer();
                                audioFrameBuffer.buffer = allocate;
                                audioFrameBuffer.ptsUs = nanoTime;
                                audioFrameBuffer.size = read;
                                audioRecorderDevice.f4553n.onFrame((OutPort<AudioFrameBuffer>) audioFrameBuffer);
                            }
                        }
                    } else {
                        try {
                            Thread.sleep(25L);
                        } catch (Exception unused) {
                        }
                    }
                }
            }
        });
        Log.i("AudioCaptureDevice", "start audio worker thread.");
        this.f4552m = true;
        this.f4551l.start();
        return true;
    }

    public synchronized void restartAudioRecorder() {
        AudioRecord audioRecord = this.f4541b;
        if (audioRecord == null) {
            return;
        }
        this.f4542c = false;
        audioRecord.setRecordPositionUpdateListener(null);
        this.f4541b.stop();
        m19971a();
        this.f4541b.startRecording();
        this.f4542c = true;
    }

    public void setAudioEnabled(boolean z) {
        this.f4547h = !z;
    }

    public void setEpochTimeInNs(long j) {
        this.f4548i = j;
    }

    public void setNeedFixCaptureSize(boolean z) {
        this.f4549j = z;
    }
}
