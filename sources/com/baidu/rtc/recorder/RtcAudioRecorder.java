package com.baidu.rtc.recorder;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import com.baidu.rtc.recorder.BRTCMediaRecorderParams;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\567196_dexfile_execute.dex */
public class RtcAudioRecorder implements IRtcMediaRecorder, RtcAudioRecordListener {
    static final int MAX_AUDIO_BUFFER_SIZE = 3840;
    private boolean mAudioOnly;
    private MediaCodec.BufferInfo mBufferInfo;
    private MediaDataCallback mCallback;
    private BRTCMediaRecorderParams.BRTCMediaEncodeParams mEncodeParams;
    private MediaCodec mEncoder;
    private volatile boolean mInit;
    private final ByteBuffer mLocalWriteBuffer;
    private BRTCMediaRecorderCallback mRecorderCallback;
    private boolean mStart;
    private final Handler mWorkThreadHandler;
    private long mPtsStart = 0;
    private long mPts = 0;
    private final HandlerThread mWorkThread = new HandlerThread("RtcAudioRecord");

    @Override // com.baidu.rtc.recorder.IRtcMediaRecorder
    public boolean isReleased() {
        return false;
    }

    public RtcAudioRecorder() {
        this.mWorkThread.start();
        this.mWorkThreadHandler = new Handler(this.mWorkThread.getLooper());
        this.mLocalWriteBuffer = ByteBuffer.allocate(3840);
    }

    public void setMediaDataCallback(MediaDataCallback mediaDataCallback) {
        this.mCallback = mediaDataCallback;
    }

    public void setMuxerState(final boolean z) {
        this.mWorkThreadHandler.postAtFrontOfQueue(new Runnable() { // from class: com.baidu.rtc.recorder.-$$Lambda$RtcAudioRecorder$LRPBElajadHUD4IROl1sfJZDZfs
            @Override // java.lang.Runnable
            public final void run() {
                RtcAudioRecorder.lambda$setMuxerState$0(RtcAudioRecorder.this, z);
            }
        });
    }

    public static /* synthetic */ void lambda$setMuxerState$0(RtcAudioRecorder rtcAudioRecorder, boolean z) {
        rtcAudioRecorder.mStart = z;
        rtcAudioRecorder.log("setMuxerState start ");
    }

    @Override // com.baidu.rtc.recorder.RtcAudioRecordListener
    public void onAudioRecord(byte[] bArr, int i) {
        log("onAudioRecord mInit " + this.mInit + " data[0] " + ((int) bArr[0]));
        if (this.mInit) {
            synchronized (this.mLocalWriteBuffer) {
                if (this.mLocalWriteBuffer.hasRemaining()) {
                    log("mLocalWriteBuffer put position : " + this.mLocalWriteBuffer.position() + " limit : " + this.mLocalWriteBuffer.limit() + " len : " + bArr.length);
                    this.mLocalWriteBuffer.put(bArr);
                } else {
                    sendToEncode(bArr);
                }
            }
        }
    }

    private void sendToEncode(byte[] bArr) {
        this.mLocalWriteBuffer.flip();
        final byte[] bArr2 = new byte[this.mLocalWriteBuffer.limit()];
        this.mLocalWriteBuffer.get(bArr2);
        log("mLocalWriteBuffer put to inner data");
        this.mLocalWriteBuffer.clear();
        log("mLocalWriteBuffer put position : " + this.mLocalWriteBuffer.position() + " limit : " + this.mLocalWriteBuffer.limit() + " len : " + bArr.length);
        this.mLocalWriteBuffer.put(bArr);
        this.mWorkThreadHandler.post(new Runnable() { // from class: com.baidu.rtc.recorder.-$$Lambda$RtcAudioRecorder$GX2hIggpAlENUJQwXD07GZw2kvg
            @Override // java.lang.Runnable
            public final void run() {
                RtcAudioRecorder.this.encode(r1, bArr2.length);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void encode(byte[] bArr, int i) {
        int dequeueInputBuffer;
        if (this.mInit && (dequeueInputBuffer = this.mEncoder.dequeueInputBuffer(10000L)) >= 0) {
            ByteBuffer inputBuffer = this.mEncoder.getInputBuffer(dequeueInputBuffer);
            inputBuffer.clear();
            inputBuffer.put(bArr);
            if (this.mPtsStart == 0) {
                this.mPtsStart = System.nanoTime();
            }
            long nanoTime = System.nanoTime() - this.mPtsStart;
            if (nanoTime > 0) {
                long j = nanoTime / 1000;
            }
            this.mPts += ((bArr.length / 2) * 1000000) / this.mEncodeParams.audioSampleRate;
            this.mEncoder.queueInputBuffer(dequeueInputBuffer, 0, i, this.mPts, 0);
            logFrequently("queueInputBuffer -- len : " + i + " mPts : " + this.mPts + " data[0] : " + ((int) bArr[0]));
            drainAudio();
        }
    }

    private void drainAudio() {
        while (true) {
            int dequeueOutputBuffer = this.mEncoder.dequeueOutputBuffer(this.mBufferInfo, 10000L);
            if (dequeueOutputBuffer == -1) {
                return;
            }
            if (dequeueOutputBuffer >= 0) {
                if (!this.mStart) {
                    log("dequeueOutputBuffer mStart = false ");
                    this.mEncoder.releaseOutputBuffer(dequeueOutputBuffer, false);
                    return;
                } else if ((this.mBufferInfo.flags & 2) != 0) {
                    log("dequeueOutputBuffer BUFFER_FLAG_CODEC_CONFIG ");
                    this.mEncoder.releaseOutputBuffer(dequeueOutputBuffer, false);
                } else if ((this.mBufferInfo.flags & 4) != 0) {
                    log("dequeueOutputBuffer BUFFER_FLAG_END_OF_STREAM ");
                    this.mEncoder.releaseOutputBuffer(dequeueOutputBuffer, false);
                    releaseOnce();
                    MediaDataCallback mediaDataCallback = this.mCallback;
                    if (mediaDataCallback != null) {
                        mediaDataCallback.onStop(false);
                        return;
                    }
                    return;
                } else {
                    ByteBuffer outputBuffer = this.mEncoder.getOutputBuffer(dequeueOutputBuffer);
                    outputBuffer.position(this.mBufferInfo.offset);
                    outputBuffer.limit(this.mBufferInfo.offset + this.mBufferInfo.size);
                    logFrequently("dequeueOutputBuffer > 0 bufferInfo offset : " + this.mBufferInfo.offset + " size : " + this.mBufferInfo.size + " pts : " + this.mBufferInfo.presentationTimeUs + " flag : " + this.mBufferInfo.flags);
                    MediaDataCallback mediaDataCallback2 = this.mCallback;
                    if (mediaDataCallback2 != null) {
                        if (this.mAudioOnly) {
                            byte[] bArr = new byte[this.mBufferInfo.size + 7];
                            addADTStoPacket(bArr, bArr.length, this.mEncodeParams.audioChannel, this.mEncodeParams.audioSampleRate);
                            outputBuffer.get(bArr, 7, this.mBufferInfo.size);
                            this.mCallback.writeSampleData(bArr, this.mBufferInfo);
                        } else {
                            mediaDataCallback2.writeSampleData(false, outputBuffer, this.mBufferInfo);
                        }
                    }
                    this.mEncoder.releaseOutputBuffer(dequeueOutputBuffer, false);
                }
            } else if (dequeueOutputBuffer == -2) {
                MediaFormat outputFormat = this.mEncoder.getOutputFormat();
                log("dequeueOutputBuffer INFO_OUTPUT_FORMAT_CHANGED -- outputFormat " + outputFormat);
                MediaDataCallback mediaDataCallback3 = this.mCallback;
                if (mediaDataCallback3 != null && !this.mAudioOnly) {
                    mediaDataCallback3.addTrack(false, outputFormat);
                    if (!this.mStart) {
                        log("dequeueOutputBuffer !mStart ");
                        return;
                    }
                }
            } else {
                logFrequently("dequeueOutputBuffer outputBufferId : " + dequeueOutputBuffer);
            }
        }
    }

    @Override // com.baidu.rtc.recorder.IRtcMediaRecorder
    public int startRecording(BRTCMediaRecorderParams bRTCMediaRecorderParams) {
        if (this.mInit) {
            return -103;
        }
        this.mEncodeParams = bRTCMediaRecorderParams.encodeParams;
        this.mAudioOnly = bRTCMediaRecorderParams.audioOnly();
        initEncoder();
        return 0;
    }

    public void releaseOnceOnWorkThread() {
        this.mWorkThreadHandler.post(new Runnable() { // from class: com.baidu.rtc.recorder.RtcAudioRecorder.1
            @Override // java.lang.Runnable
            public void run() {
                RtcAudioRecorder.this.releaseOnce();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseOnce() {
        log(" releaseOnce ");
        this.mInit = false;
        this.mStart = false;
        this.mEncoder.stop();
        this.mEncoder.release();
        this.mEncoder = null;
        this.mPtsStart = 0L;
        this.mPts = 0L;
    }

    private void initEncoder() {
        this.mWorkThreadHandler.post(new Runnable() { // from class: com.baidu.rtc.recorder.-$$Lambda$RtcAudioRecorder$PXDxLTMD4hWxwE7k5YV2QHvqjEE
            @Override // java.lang.Runnable
            public final void run() {
                RtcAudioRecorder.lambda$initEncoder$2(RtcAudioRecorder.this);
            }
        });
    }

    public static /* synthetic */ void lambda$initEncoder$2(RtcAudioRecorder rtcAudioRecorder) {
        if (rtcAudioRecorder.mInit) {
            BRTCMediaRecorderCallback bRTCMediaRecorderCallback = rtcAudioRecorder.mRecorderCallback;
            if (bRTCMediaRecorderCallback != null) {
                bRTCMediaRecorderCallback.onRecordStateChanged(-1, -101);
            }
            rtcAudioRecorder.log("initEncoder by already initialized");
            return;
        }
        MediaFormat createAudioFormat = MediaFormat.createAudioFormat(rtcAudioRecorder.mEncodeParams.audioCodec, rtcAudioRecorder.mEncodeParams.audioSampleRate, rtcAudioRecorder.mEncodeParams.audioChannel);
        createAudioFormat.setInteger("aac-profile", 2);
        createAudioFormat.setInteger("bitrate", rtcAudioRecorder.mEncodeParams.audioBitrate);
        createAudioFormat.setInteger("max-input-size", 3840);
        rtcAudioRecorder.mBufferInfo = new MediaCodec.BufferInfo();
        try {
            rtcAudioRecorder.mEncoder = MediaCodec.createEncoderByType(rtcAudioRecorder.mEncodeParams.audioCodec);
            rtcAudioRecorder.mEncoder.configure(createAudioFormat, (Surface) null, (MediaCrypto) null, 1);
            rtcAudioRecorder.mEncoder.start();
            synchronized (rtcAudioRecorder.mLocalWriteBuffer) {
                rtcAudioRecorder.mLocalWriteBuffer.clear();
            }
            rtcAudioRecorder.mInit = true;
            rtcAudioRecorder.log("initEncoder");
        } catch (Exception e) {
            e.printStackTrace();
            BRTCMediaRecorderCallback bRTCMediaRecorderCallback2 = rtcAudioRecorder.mRecorderCallback;
            if (bRTCMediaRecorderCallback2 != null) {
                bRTCMediaRecorderCallback2.onRecordStateChanged(-1, -101);
            }
            rtcAudioRecorder.log("initEncoder fail");
        }
    }

    @Override // com.baidu.rtc.recorder.IRtcMediaRecorder
    public int stopRecording() {
        this.mWorkThreadHandler.post(new Runnable() { // from class: com.baidu.rtc.recorder.-$$Lambda$RtcAudioRecorder$l1y2FhxoPtaP2mZtfw6HxWIZwds
            @Override // java.lang.Runnable
            public final void run() {
                RtcAudioRecorder.lambda$stopRecording$3(RtcAudioRecorder.this);
            }
        });
        return 0;
    }

    public static /* synthetic */ void lambda$stopRecording$3(RtcAudioRecorder rtcAudioRecorder) {
        if (rtcAudioRecorder.mEncoder == null) {
            return;
        }
        rtcAudioRecorder.log(" stopRecording signalEndOfInputStream");
        synchronized (rtcAudioRecorder.mLocalWriteBuffer) {
            rtcAudioRecorder.mLocalWriteBuffer.flip();
            byte[] bArr = new byte[rtcAudioRecorder.mLocalWriteBuffer.limit()];
            rtcAudioRecorder.mLocalWriteBuffer.get(bArr);
            rtcAudioRecorder.log("stopRecording mLocalWriteBuffer put to inner data len : " + bArr.length);
            rtcAudioRecorder.mLocalWriteBuffer.clear();
            rtcAudioRecorder.encode(bArr, bArr.length);
        }
        int dequeueInputBuffer = rtcAudioRecorder.mEncoder.dequeueInputBuffer(10000L);
        if (dequeueInputBuffer >= 0) {
            rtcAudioRecorder.mEncoder.queueInputBuffer(dequeueInputBuffer, 0, 0, 0L, 4);
            rtcAudioRecorder.log("queueInputBuffer eos -- ");
            while (rtcAudioRecorder.mInit) {
                rtcAudioRecorder.drainAudio();
            }
            return;
        }
        rtcAudioRecorder.releaseOnce();
        MediaDataCallback mediaDataCallback = rtcAudioRecorder.mCallback;
        if (mediaDataCallback != null) {
            mediaDataCallback.onStop(false);
        }
    }

    @Override // com.baidu.rtc.recorder.IRtcMediaRecorder
    public void release() {
        this.mWorkThread.quitSafely();
    }

    @Override // com.baidu.rtc.recorder.IRtcMediaRecorder
    public void setMediaRecorderCallback(BRTCMediaRecorderCallback bRTCMediaRecorderCallback) {
        this.mRecorderCallback = bRTCMediaRecorderCallback;
    }

    private void addADTStoPacket(byte[] bArr, int i, int i2, int i3) {
        int[] iArr = {96000, 88200, 64000, 48000, 44100, 32000, 24000, 22050, 16000, 12000, 11025, 8000, 7350};
        int i4 = 0;
        while (true) {
            if (i4 >= iArr.length) {
                i4 = 4;
                break;
            } else if (iArr[i4] == i3) {
                break;
            } else {
                i4++;
            }
        }
        bArr[0] = -1;
        bArr[1] = -7;
        bArr[2] = (byte) (64 + (i4 << 2) + (i2 >> 2));
        bArr[3] = (byte) (((i2 & 3) << 6) + (i >> 11));
        bArr[4] = (byte) ((i & 2047) >> 3);
        bArr[5] = (byte) (((i & 7) << 5) + 31);
        bArr[6] = -4;
    }

    private void log(String str) {
        BRTCMediaRecorder.log("AudioRecorder --- " + str);
    }

    private void logFrequently(String str) {
        BRTCMediaRecorder.logFrequently("AudioRecorder --- " + str);
    }
}
