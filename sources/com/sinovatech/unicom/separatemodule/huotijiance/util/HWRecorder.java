package com.sinovatech.unicom.separatemodule.huotijiance.util;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.util.Log;
import android.view.Surface;
import java.io.File;
import java.nio.ByteBuffer;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HWRecorder {
    private static final int DEFAULT_BITRATE_AUDIO = 128000;
    private static final int DEFAULT_FRAME_RATE = 30;
    private static final int DEFAULT_IFRAME_INTERVAL = 5;
    private static final long DEFAULT_TIMEOUT = 10000;
    private static final String TAG = "HWRecorder";
    private MediaCodec.BufferInfo mABufferInfo;
    private long mALastPts;
    private long mAStartTime;
    private int mATrackIndex;
    private MediaCodec mAudioEncoder;
    private boolean mIsInitialized = false;
    private MediaMuxer mMuxer;
    private volatile boolean mMuxerStarted;
    private MediaCodec.BufferInfo mVBufferInfo;
    private long mVLastPts;
    private long mVStartTime;
    private int mVTrackIndex;
    private MediaCodec mVideoEncoder;

    public void init(int i, int i2, int i3, int i4, int i5, int i6, String str, int i7) throws Exception {
        if (getCodecInfo("video/avc") == null || getCodecInfo("audio/mp4a-latm") == null) {
            throw new Exception("cannot find suitable codec");
        }
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", i, i2);
        createVideoFormat.setInteger("bitrate", i4);
        if (i7 == 0) {
            createVideoFormat.setInteger("frame-rate", 30);
        } else {
            createVideoFormat.setInteger("frame-rate", i7);
        }
        createVideoFormat.setInteger("i-frame-interval", 5);
        createVideoFormat.setInteger("color-format", i3);
        this.mVideoEncoder = MediaCodec.createEncoderByType("video/avc");
        this.mVideoEncoder.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
        this.mVideoEncoder.start();
        MediaFormat createAudioFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", i5, i6);
        createAudioFormat.setInteger("aac-profile", 2);
        createAudioFormat.setInteger("bitrate", 128000);
        this.mAudioEncoder = MediaCodec.createEncoderByType("audio/mp4a-latm");
        this.mAudioEncoder.configure(createAudioFormat, (Surface) null, (MediaCrypto) null, 1);
        this.mAudioEncoder.start();
        File file = new File(str);
        if (file.exists() && !file.delete()) {
            Log.w(TAG, "delete file failed");
        }
        this.mMuxer = new MediaMuxer(str, 0);
        this.mMuxerStarted = false;
        this.mVTrackIndex = -1;
        this.mATrackIndex = -1;
        this.mVStartTime = -1L;
        this.mAStartTime = -1L;
        this.mVLastPts = -1L;
        this.mALastPts = -1L;
        this.mVBufferInfo = new MediaCodec.BufferInfo();
        this.mABufferInfo = new MediaCodec.BufferInfo();
        this.mIsInitialized = true;
        Log.i(TAG, "Recorder initialized");
    }

    private static MediaCodecInfo getCodecInfo(String str) {
        int codecCount = MediaCodecList.getCodecCount();
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (codecInfoAt.isEncoder()) {
                for (String str2 : codecInfoAt.getSupportedTypes()) {
                    if (str2.equalsIgnoreCase(str)) {
                        return codecInfoAt;
                    }
                }
                continue;
            }
        }
        return null;
    }

    public void recordImage(byte[] bArr) throws Exception {
        long nanoTime;
        if (this.mVStartTime == -1) {
            this.mVStartTime = System.nanoTime();
            nanoTime = 0;
        } else {
            nanoTime = (System.nanoTime() - this.mVStartTime) / 1000;
        }
        long j = this.mVLastPts;
        long j2 = nanoTime <= j ? nanoTime + (j - nanoTime) + 1000 : nanoTime;
        this.mVLastPts = j2;
        doRecord(this.mVideoEncoder, this.mVBufferInfo, bArr, j2);
    }

    public void recordSample(byte[] bArr) throws Exception {
        long nanoTime;
        if (this.mAStartTime == -1) {
            this.mAStartTime = System.nanoTime();
            nanoTime = 0;
        } else {
            nanoTime = (System.nanoTime() - this.mAStartTime) / 1000;
        }
        long j = this.mALastPts;
        long j2 = nanoTime <= j ? nanoTime + (j - nanoTime) + 1000 : nanoTime;
        this.mALastPts = j2;
        doRecord(this.mAudioEncoder, this.mABufferInfo, bArr, j2);
    }

    private void doRecord(MediaCodec mediaCodec, MediaCodec.BufferInfo bufferInfo, byte[] bArr, long j) throws Exception {
        if (!this.mIsInitialized) {
            Log.e(TAG, "Recorder must be initialized!");
            return;
        }
        int dequeueInputBuffer = mediaCodec.dequeueInputBuffer(10000L);
        ByteBuffer byteBuffer = mediaCodec.getInputBuffers()[dequeueInputBuffer];
        if (dequeueInputBuffer >= 0) {
            byteBuffer.clear();
            byteBuffer.put(bArr);
            mediaCodec.queueInputBuffer(dequeueInputBuffer, 0, bArr.length, j, 0);
        }
        drainEncoder(mediaCodec, bufferInfo);
    }

    private void drainEncoder(MediaCodec mediaCodec, MediaCodec.BufferInfo bufferInfo) throws Exception {
        int i = mediaCodec == this.mVideoEncoder ? this.mVTrackIndex : this.mATrackIndex;
        ByteBuffer[] outputBuffers = mediaCodec.getOutputBuffers();
        while (true) {
            int dequeueOutputBuffer = mediaCodec.dequeueOutputBuffer(bufferInfo, 10000L);
            if (dequeueOutputBuffer == -3) {
                outputBuffers = mediaCodec.getOutputBuffers();
            } else if (dequeueOutputBuffer == -2) {
                i = addTrackIndex(mediaCodec);
            } else if (dequeueOutputBuffer == -1) {
                return;
            } else {
                if (dequeueOutputBuffer < 0) {
                    Log.w(TAG, "drainEncoder unexpected result: " + dequeueOutputBuffer);
                } else if ((bufferInfo.flags & 2) == 0) {
                    if (bufferInfo.size != 0) {
                        ByteBuffer byteBuffer = outputBuffers[dequeueOutputBuffer];
                        if (byteBuffer == null) {
                            throw new RuntimeException("drainEncoder get outputBuffer " + dequeueOutputBuffer + " was null");
                        }
                        synchronized (this) {
                            if (!this.mMuxerStarted) {
                                wait();
                            }
                        }
                        byteBuffer.position(bufferInfo.offset);
                        byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
                        this.mMuxer.writeSampleData(i, byteBuffer, bufferInfo);
                    }
                    mediaCodec.releaseOutputBuffer(dequeueOutputBuffer, false);
                } else {
                    continue;
                }
            }
        }
    }

    private int addTrackIndex(MediaCodec mediaCodec) {
        int i;
        synchronized (this) {
            MediaFormat outputFormat = mediaCodec.getOutputFormat();
            if (HWCodec.getMediaType(outputFormat) == 1) {
                this.mVTrackIndex = this.mMuxer.addTrack(outputFormat);
                i = this.mVTrackIndex;
            } else {
                this.mATrackIndex = this.mMuxer.addTrack(outputFormat);
                i = this.mATrackIndex;
            }
            if (this.mVTrackIndex != -1 && this.mATrackIndex != -1) {
                this.mMuxer.start();
                this.mMuxerStarted = true;
                notifyAll();
                Log.i(TAG, "MediaMuxer has added all track, notifyAll");
            }
        }
        return i;
    }

    public void stop() {
        try {
            release();
        } catch (Exception e) {
            Log.e(TAG, "stop exception occur: " + e.getLocalizedMessage());
        }
        if (this.mIsInitialized) {
            Log.i(TAG, "Recorder released");
        }
        this.mIsInitialized = false;
    }

    private void release() throws Exception {
        MediaCodec mediaCodec = this.mVideoEncoder;
        if (mediaCodec != null) {
            mediaCodec.stop();
            this.mVideoEncoder.release();
            this.mVideoEncoder = null;
        }
        MediaCodec mediaCodec2 = this.mAudioEncoder;
        if (mediaCodec2 != null) {
            mediaCodec2.stop();
            this.mAudioEncoder.release();
            this.mAudioEncoder = null;
        }
        MediaMuxer mediaMuxer = this.mMuxer;
        if (mediaMuxer != null) {
            mediaMuxer.stop();
            this.mMuxer.release();
            this.mMuxer = null;
        }
    }
}
