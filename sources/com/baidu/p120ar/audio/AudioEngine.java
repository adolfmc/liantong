package com.baidu.p120ar.audio;

import android.media.AudioRecord;
import com.baidu.p120ar.utils.ARLog;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.audio.AudioEngine */
/* loaded from: E:\10201592_dexfile_execute.dex */
class AudioEngine {
    private static final String TAG = "AudioEngine";
    private static volatile boolean sRecording;
    private AudioCallback mAudioCallback;
    private AudioParams mAudioParams;
    private AudioRecord mAudioRecord;
    private VolumeListener mVolumeListener;
    private byte[] mInputBuffer = null;
    private ArrayList<ByteBuffer> mByteBufferList = null;
    private int mCurrentFrameNum = 0;
    private boolean mStart = false;

    public void setAudioCallback(AudioCallback audioCallback) {
        this.mAudioCallback = audioCallback;
    }

    public void setVolumeListener(VolumeListener volumeListener) {
        this.mVolumeListener = volumeListener;
    }

    public void setupAudioEngine(AudioParams audioParams) {
        int minBufferSize = AudioRecord.getMinBufferSize(audioParams.getSampleRate(), audioParams.getChannelConfig(), audioParams.getAudioFormat());
        if (audioParams.getFrameSize() < minBufferSize) {
            audioParams.setAudioBufferSize(((minBufferSize / 1024) + 1) * 1024 * 2);
        }
        this.mAudioRecord = new AudioRecord(audioParams.getAudioSource(), audioParams.getSampleRate(), audioParams.getChannelConfig(), audioParams.getAudioFormat(), audioParams.getAudioBufferSize());
        this.mAudioParams = audioParams;
        this.mStart = false;
        AudioCallback audioCallback = this.mAudioCallback;
        if (audioCallback != null) {
            audioCallback.onAudioSetup(true);
        }
    }

    public void startAudioEngine() {
        startAudioRecord();
        readBackAudioFrame();
    }

    public void stopAudioEngine() {
        sRecording = false;
    }

    public void releaseAudioEngine() {
        if (sRecording) {
            return;
        }
        this.mAudioRecord.release();
        this.mAudioRecord = null;
        AudioCallback audioCallback = this.mAudioCallback;
        if (audioCallback != null) {
            audioCallback.onAudioRelease();
        }
        this.mAudioCallback = null;
        this.mVolumeListener = null;
    }

    public AudioParams getAudioParams() {
        return this.mAudioParams;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void startAudioRecord() {
        /*
            r5 = this;
            android.media.AudioRecord r0 = r5.mAudioRecord
            int r0 = r0.getState()
            r1 = 1
            r2 = 0
            if (r0 != r1) goto L3e
            android.media.AudioRecord r0 = r5.mAudioRecord     // Catch: java.lang.IllegalStateException -> L32
            r0.startRecording()     // Catch: java.lang.IllegalStateException -> L32
            android.media.AudioRecord r0 = r5.mAudioRecord     // Catch: java.lang.IllegalStateException -> L32
            int r0 = r0.getRecordingState()     // Catch: java.lang.IllegalStateException -> L32
            r3 = 3
            if (r0 != r3) goto L19
            goto L3f
        L19:
            java.lang.String r1 = com.baidu.p120ar.audio.AudioEngine.TAG     // Catch: java.lang.IllegalStateException -> L32
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.IllegalStateException -> L32
            r3.<init>()     // Catch: java.lang.IllegalStateException -> L32
            java.lang.String r4 = "startAudioRecord state = "
            r3.append(r4)     // Catch: java.lang.IllegalStateException -> L32
            r3.append(r0)     // Catch: java.lang.IllegalStateException -> L32
            java.lang.String r0 = r3.toString()     // Catch: java.lang.IllegalStateException -> L32
            com.baidu.p120ar.utils.ARLog.m20419e(r1, r0)     // Catch: java.lang.IllegalStateException -> L32
            r1 = r2
            goto L3f
        L32:
            r0 = move-exception
            java.lang.String r1 = com.baidu.p120ar.audio.AudioEngine.TAG
            java.lang.String r3 = "startAudioRecord error!!!"
            com.baidu.p120ar.utils.ARLog.m20419e(r1, r3)
            r0.printStackTrace()
        L3e:
            r1 = r2
        L3f:
            com.baidu.p120ar.audio.AudioEngine.sRecording = r1
            if (r1 != 0) goto L63
            java.lang.String r0 = com.baidu.p120ar.audio.AudioEngine.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "startAudioRecord error!!! mAudioRecord.getState() = "
            r1.append(r3)
            android.media.AudioRecord r3 = r5.mAudioRecord
            int r3 = r3.getState()
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            com.baidu.p120ar.utils.ARLog.m20419e(r0, r1)
            r5.startCallback(r2)
        L63:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.p120ar.audio.AudioEngine.startAudioRecord():void");
    }

    private void readBackAudioFrame() {
        if (this.mAudioParams.getFrameSize() <= 0) {
            return;
        }
        if (this.mByteBufferList == null) {
            this.mByteBufferList = new ArrayList<>();
            for (int i = 0; i < this.mAudioParams.getFrameBufferCount(); i++) {
                this.mByteBufferList.add(ByteBuffer.allocate(this.mAudioParams.getFrameSize()));
            }
        }
        this.mCurrentFrameNum = 0;
        if (this.mInputBuffer == null) {
            this.mInputBuffer = new byte[this.mAudioParams.getFrameSize()];
        }
        int i2 = 0;
        while (sRecording) {
            long nanoTime = System.nanoTime();
            AudioRecord audioRecord = this.mAudioRecord;
            byte[] bArr = this.mInputBuffer;
            int read = audioRecord.read(bArr, 0, bArr.length);
            if (this.mStart && this.mAudioParams != null) {
                readBackOneFrame(nanoTime, read);
            } else {
                checkStartState(i2);
                i2++;
            }
        }
        this.mByteBufferList = null;
        this.mInputBuffer = null;
        try {
            this.mAudioRecord.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AudioCallback audioCallback = this.mAudioCallback;
        if (audioCallback != null) {
            audioCallback.onAudioStop(true);
        }
    }

    private void readBackOneFrame(long j, int i) {
        ByteBuffer byteBuffer = this.mByteBufferList.get(this.mCurrentFrameNum);
        if (i == -3) {
            ARLog.m20419e(TAG, "Audio read error");
        } else if (this.mAudioCallback != null && byteBuffer != null && byteBuffer.capacity() >= i) {
            if (this.mAudioParams.getAmplifyVolume() != 1.0f) {
                AudioHelper.amplifyVolume(this.mInputBuffer, this.mAudioParams.getAmplifyVolume());
            }
            try {
                byteBuffer.clear();
                byteBuffer.position(0);
                byteBuffer.put(this.mInputBuffer, 0, i);
                byteBuffer.flip();
                this.mAudioCallback.onAudioFrameAvailable(byteBuffer, i, j);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        this.mCurrentFrameNum++;
        this.mCurrentFrameNum %= this.mAudioParams.getFrameBufferCount();
        if (this.mVolumeListener != null) {
            this.mVolumeListener.onRealtimeVolume((int) AudioHelper.calculateVolumePercent(this.mInputBuffer));
        }
    }

    private void checkStartState(long j) {
        if (j < 20) {
            if (AudioHelper.calculateVolume(this.mInputBuffer) != 0.0d) {
                startCallback(true);
                this.mStart = true;
                return;
            }
            return;
        }
        startCallback(false);
        this.mStart = true;
    }

    private void startCallback(boolean z) {
        AudioCallback audioCallback = this.mAudioCallback;
        if (audioCallback != null) {
            audioCallback.onAudioStart(z);
        }
    }
}
