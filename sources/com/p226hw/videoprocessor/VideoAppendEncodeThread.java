package com.p226hw.videoprocessor;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.view.Surface;
import com.p226hw.videoprocessor.util.C5140CL;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.hw.videoprocessor.VideoAppendEncodeThread */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class VideoAppendEncodeThread extends Thread implements IVideoEncodeThread {
    private long mBaseMuxerFrameTimeUs;
    private int mBitrate;
    private AtomicBoolean mDecodeDone;
    private volatile CountDownLatch mEglContextLatch;
    private MediaCodec mEncoder;
    private Exception mException;
    private MediaExtractor mExtractor;
    private int mIFrameInterval;
    private boolean mIsFirst;
    private boolean mIsLast;
    private long mLastFrametimeUs;
    private MediaMuxer mMuxer;
    private int mMuxerVideoTrackIndex;
    private int mResultHeight;
    private int mResultWidth;
    private volatile Surface mSurface;
    private int mVideoIndex;

    public VideoAppendEncodeThread(MediaExtractor mediaExtractor, MediaMuxer mediaMuxer, int i, int i2, int i3, int i4, int i5, AtomicBoolean atomicBoolean, long j, boolean z, boolean z2, int i6) {
        super("VideoProcessEncodeThread");
        this.mMuxer = mediaMuxer;
        this.mDecodeDone = atomicBoolean;
        this.mExtractor = mediaExtractor;
        this.mBitrate = i;
        this.mResultHeight = i3;
        this.mResultWidth = i2;
        this.mIFrameInterval = i4;
        this.mVideoIndex = i5;
        this.mEglContextLatch = new CountDownLatch(1);
        this.mBaseMuxerFrameTimeUs = j;
        this.mLastFrametimeUs = j;
        this.mIsFirst = z;
        this.mIsLast = z2;
        this.mMuxerVideoTrackIndex = i6;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        MediaCodec mediaCodec;
        super.run();
        try {
            try {
                doEncode();
                mediaCodec = this.mEncoder;
                if (mediaCodec == null) {
                    return;
                }
            } catch (Exception e) {
                C5140CL.m13757e(e);
                this.mException = e;
                mediaCodec = this.mEncoder;
                if (mediaCodec == null) {
                    return;
                }
            }
            mediaCodec.stop();
            this.mEncoder.release();
        } catch (Throwable th) {
            MediaCodec mediaCodec2 = this.mEncoder;
            if (mediaCodec2 != null) {
                mediaCodec2.stop();
                this.mEncoder.release();
            }
            throw th;
        }
    }

    private void doEncode() throws IOException {
        MediaFormat trackFormat = this.mExtractor.getTrackFormat(this.mVideoIndex);
        int integer = trackFormat.containsKey("frame-rate") ? trackFormat.getInteger("frame-rate") : VideoProcessor.DEFAULT_FRAME_RATE;
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", this.mResultWidth, this.mResultHeight);
        createVideoFormat.setInteger("color-format", 2130708361);
        createVideoFormat.setInteger("bitrate", this.mBitrate);
        createVideoFormat.setInteger("frame-rate", integer);
        createVideoFormat.setInteger("i-frame-interval", this.mIFrameInterval);
        this.mEncoder = MediaCodec.createEncoderByType("video/avc");
        this.mEncoder.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
        this.mSurface = this.mEncoder.createInputSurface();
        this.mEncoder.start();
        this.mEglContextLatch.countDown();
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        boolean z = false;
        int i = 0;
        while (true) {
            if (this.mDecodeDone.get() && !z) {
                this.mEncoder.signalEndOfInputStream();
                z = true;
            }
            int dequeueOutputBuffer = this.mEncoder.dequeueOutputBuffer(bufferInfo, 2500L);
            C5140CL.m13755i("encode outputBufferIndex = " + dequeueOutputBuffer, new Object[0]);
            if (z && dequeueOutputBuffer == -1) {
                i++;
                if (i > 10) {
                    C5140CL.m13758e("INFO_TRY_AGAIN_LATER 10 times,force End!", new Object[0]);
                    return;
                }
            } else {
                i = 0;
            }
            if (dequeueOutputBuffer != -1) {
                if (dequeueOutputBuffer == -2) {
                    C5140CL.m13755i("encode newFormat = " + this.mEncoder.getOutputFormat(), new Object[0]);
                } else if (dequeueOutputBuffer < 0) {
                    C5140CL.m13758e("unexpected result from decoder.dequeueOutputBuffer: " + dequeueOutputBuffer, new Object[0]);
                } else {
                    ByteBuffer outputBuffer = this.mEncoder.getOutputBuffer(dequeueOutputBuffer);
                    bufferInfo.presentationTimeUs += this.mBaseMuxerFrameTimeUs;
                    if (this.mIsFirst || bufferInfo.flags != 2) {
                        if (!this.mIsLast && bufferInfo.flags == 4) {
                            C5140CL.m13755i("encoderDone", new Object[0]);
                            this.mEncoder.releaseOutputBuffer(dequeueOutputBuffer, false);
                            return;
                        }
                        if (bufferInfo.flags == 4 && bufferInfo.presentationTimeUs < 0) {
                            bufferInfo.presentationTimeUs = 0L;
                        }
                        C5140CL.m13755i("writeSampleData,size:" + bufferInfo.size + " time:" + (bufferInfo.presentationTimeUs / 1000) + " flag:" + bufferInfo.flags, new Object[0]);
                        this.mMuxer.writeSampleData(this.mMuxerVideoTrackIndex, outputBuffer, bufferInfo);
                        if (this.mLastFrametimeUs < bufferInfo.presentationTimeUs) {
                            this.mLastFrametimeUs = bufferInfo.presentationTimeUs;
                        }
                        this.mEncoder.releaseOutputBuffer(dequeueOutputBuffer, false);
                        if (bufferInfo.flags == 4) {
                            C5140CL.m13755i("encoderDone", new Object[0]);
                            return;
                        }
                    }
                }
            }
        }
    }

    @Override // com.p226hw.videoprocessor.IVideoEncodeThread
    public Surface getSurface() {
        return this.mSurface;
    }

    @Override // com.p226hw.videoprocessor.IVideoEncodeThread
    public CountDownLatch getEglContextLatch() {
        return this.mEglContextLatch;
    }

    public Exception getException() {
        return this.mException;
    }

    public long getLastFrametimeUs() {
        return this.mLastFrametimeUs;
    }
}
