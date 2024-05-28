package com.p226hw.videoprocessor;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.view.Surface;
import com.p226hw.videoprocessor.util.C5140CL;
import com.p226hw.videoprocessor.util.VideoProgressAve;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.hw.videoprocessor.VideoEncodeThread */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class VideoEncodeThread extends Thread implements IVideoEncodeThread {
    private int mBitrate;
    private AtomicBoolean mDecodeDone;
    private volatile CountDownLatch mEglContextLatch;
    private MediaCodec mEncoder;
    private Exception mException;
    private MediaExtractor mExtractor;
    private int mFrameRate;
    private int mIFrameInterval;
    private MediaMuxer mMuxer;
    private CountDownLatch mMuxerStartLatch;
    private VideoProgressAve mProgressAve;
    private int mResultHeight;
    private int mResultWidth;
    private volatile Surface mSurface;
    private int mVideoIndex;

    public VideoEncodeThread(MediaExtractor mediaExtractor, MediaMuxer mediaMuxer, int i, int i2, int i3, int i4, int i5, int i6, AtomicBoolean atomicBoolean, CountDownLatch countDownLatch) {
        super("VideoProcessEncodeThread");
        this.mMuxer = mediaMuxer;
        this.mDecodeDone = atomicBoolean;
        this.mMuxerStartLatch = countDownLatch;
        this.mExtractor = mediaExtractor;
        this.mBitrate = i;
        this.mResultHeight = i3;
        this.mResultWidth = i2;
        this.mIFrameInterval = i4;
        this.mVideoIndex = i6;
        this.mFrameRate = i5;
        this.mEglContextLatch = new CountDownLatch(1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0018, code lost:
        if (r1 != null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x001a, code lost:
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x001b, code lost:
        r3.mException = r1;
        com.p226hw.videoprocessor.util.C5140CL.m13757e(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x003b, code lost:
        if (r1 != null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:?, code lost:
        return;
     */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            r3 = this;
            super.run()
            r3.doEncode()     // Catch: java.lang.Throwable -> L21 java.lang.Exception -> L23
            android.media.MediaCodec r0 = r3.mEncoder     // Catch: java.lang.Exception -> L15
            if (r0 == 0) goto L3e
            android.media.MediaCodec r0 = r3.mEncoder     // Catch: java.lang.Exception -> L15
            r0.stop()     // Catch: java.lang.Exception -> L15
            android.media.MediaCodec r0 = r3.mEncoder     // Catch: java.lang.Exception -> L15
            r0.release()     // Catch: java.lang.Exception -> L15
            goto L3e
        L15:
            r0 = move-exception
            java.lang.Exception r1 = r3.mException
            if (r1 != 0) goto L1b
        L1a:
            r1 = r0
        L1b:
            r3.mException = r1
            com.p226hw.videoprocessor.util.C5140CL.m13757e(r0)
            goto L3e
        L21:
            r0 = move-exception
            goto L3f
        L23:
            r0 = move-exception
            com.p226hw.videoprocessor.util.C5140CL.m13757e(r0)     // Catch: java.lang.Throwable -> L21
            r3.mException = r0     // Catch: java.lang.Throwable -> L21
            android.media.MediaCodec r0 = r3.mEncoder     // Catch: java.lang.Exception -> L38
            if (r0 == 0) goto L3e
            android.media.MediaCodec r0 = r3.mEncoder     // Catch: java.lang.Exception -> L38
            r0.stop()     // Catch: java.lang.Exception -> L38
            android.media.MediaCodec r0 = r3.mEncoder     // Catch: java.lang.Exception -> L38
            r0.release()     // Catch: java.lang.Exception -> L38
            goto L3e
        L38:
            r0 = move-exception
            java.lang.Exception r1 = r3.mException
            if (r1 != 0) goto L1b
            goto L1a
        L3e:
            return
        L3f:
            android.media.MediaCodec r1 = r3.mEncoder     // Catch: java.lang.Exception -> L4e
            if (r1 == 0) goto L59
            android.media.MediaCodec r1 = r3.mEncoder     // Catch: java.lang.Exception -> L4e
            r1.stop()     // Catch: java.lang.Exception -> L4e
            android.media.MediaCodec r1 = r3.mEncoder     // Catch: java.lang.Exception -> L4e
            r1.release()     // Catch: java.lang.Exception -> L4e
            goto L59
        L4e:
            r1 = move-exception
            java.lang.Exception r2 = r3.mException
            if (r2 != 0) goto L54
            r2 = r1
        L54:
            r3.mException = r2
            com.p226hw.videoprocessor.util.C5140CL.m13757e(r1)
        L59:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p226hw.videoprocessor.VideoEncodeThread.run():void");
    }

    private void doEncode() throws IOException {
        int i;
        MediaFormat trackFormat = this.mExtractor.getTrackFormat(this.mVideoIndex);
        int i2 = this.mFrameRate;
        if (i2 <= 0) {
            i2 = trackFormat.containsKey("frame-rate") ? trackFormat.getInteger("frame-rate") : VideoProcessor.DEFAULT_FRAME_RATE;
        }
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", this.mResultWidth, this.mResultHeight);
        createVideoFormat.setInteger("color-format", 2130708361);
        createVideoFormat.setInteger("frame-rate", i2);
        createVideoFormat.setInteger("i-frame-interval", this.mIFrameInterval);
        this.mEncoder = MediaCodec.createEncoderByType("video/avc");
        if (VideoUtil.trySetProfileAndLevel(this.mEncoder, "video/avc", createVideoFormat, 8, 512)) {
            C5140CL.m13755i("supportProfileHigh,enable ProfileHigh", new Object[0]);
        }
        int maxSupportBitrate = VideoUtil.getMaxSupportBitrate(this.mEncoder, "video/avc");
        if (maxSupportBitrate > 0 && this.mBitrate > maxSupportBitrate) {
            C5140CL.m13758e(this.mBitrate + " bitrate too large,set to:" + maxSupportBitrate, new Object[0]);
            this.mBitrate = (int) (((float) maxSupportBitrate) * 0.8f);
        }
        createVideoFormat.setInteger("bitrate", this.mBitrate);
        int i3 = 1;
        this.mEncoder.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
        this.mSurface = this.mEncoder.createInputSurface();
        this.mEncoder.start();
        this.mEglContextLatch.countDown();
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        int i4 = (int) (1000000.0f / i2);
        int i5 = -5;
        int i6 = 0;
        int i7 = 0;
        boolean z = false;
        int i8 = -5;
        long j = -1;
        while (true) {
            if (this.mDecodeDone.get() && i6 == 0) {
                this.mEncoder.signalEndOfInputStream();
                i6 = i3;
            }
            int dequeueOutputBuffer = this.mEncoder.dequeueOutputBuffer(bufferInfo, 2500L);
            C5140CL.m13755i("encode outputBufferIndex = " + dequeueOutputBuffer, new Object[0]);
            if (i6 != 0 && dequeueOutputBuffer == -1) {
                i7 += i3;
                if (i7 > 10) {
                    C5140CL.m13758e("INFO_TRY_AGAIN_LATER 10 times,force End!", new Object[0]);
                    break;
                }
            } else {
                i7 = 0;
            }
            if (dequeueOutputBuffer != -1) {
                if (dequeueOutputBuffer != -2) {
                    if (dequeueOutputBuffer < 0) {
                        C5140CL.m13758e("unexpected result from decoder.dequeueOutputBuffer: " + dequeueOutputBuffer, new Object[0]);
                        i = i6;
                    } else {
                        ByteBuffer outputBuffer = this.mEncoder.getOutputBuffer(dequeueOutputBuffer);
                        if (bufferInfo.flags == 4) {
                            i = i6;
                            if (bufferInfo.presentationTimeUs < 0) {
                                bufferInfo.presentationTimeUs = 0L;
                            }
                        } else {
                            i = i6;
                        }
                        if (!z && j != -1 && bufferInfo.presentationTimeUs < (i4 / 2) + j) {
                            C5140CL.m13758e("video 时间戳错误，lastVideoFrameTimeUs:" + j + " info.presentationTimeUs:" + bufferInfo.presentationTimeUs + " VIDEO_FRAME_TIME_US:" + i4, new Object[0]);
                            z = true;
                        }
                        if (z) {
                            bufferInfo.presentationTimeUs = i4 + j;
                            C5140CL.m13758e("video 时间戳错误，使用修正的时间戳:" + bufferInfo.presentationTimeUs, new Object[0]);
                            z = false;
                        }
                        if (bufferInfo.flags != 2) {
                            j = bufferInfo.presentationTimeUs;
                        }
                        C5140CL.m13755i("writeSampleData,size:" + bufferInfo.size + " time:" + (bufferInfo.presentationTimeUs / 1000), new Object[0]);
                        this.mMuxer.writeSampleData(i8, outputBuffer, bufferInfo);
                        notifyProgress(bufferInfo);
                        this.mEncoder.releaseOutputBuffer(dequeueOutputBuffer, false);
                        if (bufferInfo.flags == 4) {
                            C5140CL.m13755i("encoderDone", new Object[0]);
                            break;
                        }
                    }
                } else {
                    MediaFormat outputFormat = this.mEncoder.getOutputFormat();
                    if (i8 == i5) {
                        i8 = this.mMuxer.addTrack(outputFormat);
                        this.mMuxer.start();
                        this.mMuxerStartLatch.countDown();
                    }
                    C5140CL.m13755i("encode newFormat = " + outputFormat, new Object[0]);
                    i = i6;
                }
                i6 = i;
                i3 = 1;
                i5 = -5;
            }
        }
        C5140CL.m13755i("Video Encode Done!", new Object[0]);
    }

    private void notifyProgress(MediaCodec.BufferInfo bufferInfo) {
        VideoProgressAve videoProgressAve = this.mProgressAve;
        if (videoProgressAve == null) {
            return;
        }
        videoProgressAve.setEncodeTimeStamp((bufferInfo.flags & 4) > 0 ? Long.MAX_VALUE : bufferInfo.presentationTimeUs);
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

    public void setProgressAve(VideoProgressAve videoProgressAve) {
        this.mProgressAve = videoProgressAve;
    }
}
