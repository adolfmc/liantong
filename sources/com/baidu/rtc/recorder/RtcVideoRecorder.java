package com.baidu.rtc.recorder;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import com.baidu.rtc.recorder.BRTCMediaRecorderParams;
import com.webrtc.EglBase;
import com.webrtc.GlRectDrawer;
import com.webrtc.VideoFrame;
import com.webrtc.VideoFrameDrawer;
import com.webrtc.VideoSink;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\567196_dexfile_execute.dex */
public class RtcVideoRecorder implements IRtcMediaRecorder, VideoSink {
    private MediaCodec.BufferInfo mBufferInfo;
    private MediaDataCallback mCallback;
    private GlRectDrawer mDrawer;
    private EglBase mEglBase;
    private BRTCMediaRecorderParams.BRTCMediaEncodeParams mEncodeParams;
    private MediaCodec mEncoder;
    private VideoFrameDrawer mFrameDrawer;
    private volatile boolean mInit;
    private Surface mInputSurface;
    private BRTCMediaRecorderCallback mRecorderCallback;
    private final EglBase.Context mSharedContext;
    private boolean mStart;
    private final Handler mWorkThreadHandler;
    private long mPtsStart = 0;
    private final HandlerThread mWorkThread = new HandlerThread("RtcVideoRecord");

    @Override // com.baidu.rtc.recorder.IRtcMediaRecorder
    public boolean isReleased() {
        return false;
    }

    public RtcVideoRecorder(EglBase.Context context) {
        this.mSharedContext = context;
        this.mWorkThread.start();
        this.mWorkThreadHandler = new Handler(this.mWorkThread.getLooper());
    }

    private void initEncoder() {
        this.mWorkThreadHandler.post(new Runnable() { // from class: com.baidu.rtc.recorder.-$$Lambda$RtcVideoRecorder$r9TZaCzbtWi8YV6ibUwgVnvlWnc
            @Override // java.lang.Runnable
            public final void run() {
                RtcVideoRecorder.lambda$initEncoder$0(RtcVideoRecorder.this);
            }
        });
    }

    public static /* synthetic */ void lambda$initEncoder$0(RtcVideoRecorder rtcVideoRecorder) {
        if (rtcVideoRecorder.mInit) {
            BRTCMediaRecorderCallback bRTCMediaRecorderCallback = rtcVideoRecorder.mRecorderCallback;
            if (bRTCMediaRecorderCallback != null) {
                bRTCMediaRecorderCallback.onRecordStateChanged(-1, -101);
            }
            rtcVideoRecorder.log("initEncoder by already initialized");
            return;
        }
        String str = rtcVideoRecorder.mEncodeParams.videoCodec;
        rtcVideoRecorder.mBufferInfo = new MediaCodec.BufferInfo();
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat(str, rtcVideoRecorder.mEncodeParams.videoWidth, rtcVideoRecorder.mEncodeParams.videoHeight);
        createVideoFormat.setInteger("color-format", 2130708361);
        createVideoFormat.setInteger("bitrate", rtcVideoRecorder.mEncodeParams.videoBitrate);
        createVideoFormat.setInteger("frame-rate", rtcVideoRecorder.mEncodeParams.videoFps);
        createVideoFormat.setInteger("i-frame-interval", rtcVideoRecorder.mEncodeParams.videoIFrameInterval);
        try {
            rtcVideoRecorder.mEncoder = MediaCodec.createEncoderByType(str);
            rtcVideoRecorder.mEncoder.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
            rtcVideoRecorder.mInputSurface = rtcVideoRecorder.mEncoder.createInputSurface();
            rtcVideoRecorder.mEncoder.start();
            rtcVideoRecorder.mEglBase = EglBase.CC.create(rtcVideoRecorder.mSharedContext, EglBase.CONFIG_RECORDABLE);
            rtcVideoRecorder.mEglBase.createSurface(rtcVideoRecorder.mInputSurface);
            rtcVideoRecorder.mEglBase.makeCurrent();
            rtcVideoRecorder.mDrawer = new GlRectDrawer();
            rtcVideoRecorder.mFrameDrawer = new VideoFrameDrawer();
            rtcVideoRecorder.mInit = true;
            rtcVideoRecorder.log("initEncoder");
        } catch (IOException e) {
            e.printStackTrace();
            BRTCMediaRecorderCallback bRTCMediaRecorderCallback2 = rtcVideoRecorder.mRecorderCallback;
            if (bRTCMediaRecorderCallback2 != null) {
                bRTCMediaRecorderCallback2.onRecordStateChanged(-1, -101);
            }
            rtcVideoRecorder.log("initEncoder fail");
        }
    }

    public void setMediaDataCallback(MediaDataCallback mediaDataCallback) {
        this.mCallback = mediaDataCallback;
    }

    public void setMuxerState(final boolean z) {
        this.mWorkThreadHandler.postAtFrontOfQueue(new Runnable() { // from class: com.baidu.rtc.recorder.-$$Lambda$RtcVideoRecorder$fznG6WQ_84u7e2bsO53LKNgql-w
            @Override // java.lang.Runnable
            public final void run() {
                RtcVideoRecorder.lambda$setMuxerState$1(RtcVideoRecorder.this, z);
            }
        });
    }

    public static /* synthetic */ void lambda$setMuxerState$1(RtcVideoRecorder rtcVideoRecorder, boolean z) {
        rtcVideoRecorder.mStart = z;
        rtcVideoRecorder.log("setMuxerState start ");
    }

    @Override // com.webrtc.VideoSink
    public void onFrame(final VideoFrame videoFrame) {
        if (this.mInit) {
            videoFrame.retain();
            this.mWorkThreadHandler.post(new Runnable() { // from class: com.baidu.rtc.recorder.RtcVideoRecorder.1
                @Override // java.lang.Runnable
                public void run() {
                    if (!RtcVideoRecorder.this.mInit) {
                        videoFrame.release();
                        return;
                    }
                    RtcVideoRecorder.this.mFrameDrawer.drawFrame(videoFrame, RtcVideoRecorder.this.mDrawer, null, 0, 0, RtcVideoRecorder.this.mEncodeParams.videoWidth, RtcVideoRecorder.this.mEncodeParams.videoHeight);
                    videoFrame.release();
                    RtcVideoRecorder.this.drainVideo();
                    RtcVideoRecorder.this.mEglBase.swapBuffers();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void drainVideo() {
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
                        mediaDataCallback.onStop(true);
                        return;
                    }
                    return;
                } else {
                    long j = this.mBufferInfo.presentationTimeUs;
                    if (this.mPtsStart == 0 && this.mBufferInfo.presentationTimeUs != 0) {
                        this.mPtsStart = this.mBufferInfo.presentationTimeUs;
                    }
                    long j2 = j - this.mPtsStart;
                    ByteBuffer outputBuffer = this.mEncoder.getOutputBuffer(dequeueOutputBuffer);
                    outputBuffer.position(this.mBufferInfo.offset);
                    outputBuffer.limit(this.mBufferInfo.offset + this.mBufferInfo.size);
                    logFrequently("dequeueOutputBuffer > 0 bufferInfo offset : " + this.mBufferInfo.offset + " size : " + this.mBufferInfo.size + " pts : " + this.mBufferInfo.presentationTimeUs + " flag : " + this.mBufferInfo.flags + " new pts : " + j2);
                    MediaCodec.BufferInfo bufferInfo = this.mBufferInfo;
                    bufferInfo.presentationTimeUs = j2;
                    MediaDataCallback mediaDataCallback2 = this.mCallback;
                    if (mediaDataCallback2 != null) {
                        mediaDataCallback2.writeSampleData(true, outputBuffer, bufferInfo);
                    }
                    this.mEncoder.releaseOutputBuffer(dequeueOutputBuffer, false);
                }
            } else if (dequeueOutputBuffer == -2) {
                MediaFormat outputFormat = this.mEncoder.getOutputFormat();
                log("dequeueOutputBuffer INFO_OUTPUT_FORMAT_CHANGED outputFormat " + outputFormat);
                MediaDataCallback mediaDataCallback3 = this.mCallback;
                if (mediaDataCallback3 != null) {
                    mediaDataCallback3.addTrack(true, outputFormat);
                }
                if (!this.mStart) {
                    log("dequeueOutputBuffer !mStart ");
                    return;
                }
            } else {
                logFrequently("dequeueOutputBuffer outputBufferId : " + dequeueOutputBuffer);
            }
        }
    }

    public void releaseOnceOnWorkThread() {
        this.mWorkThreadHandler.post(new Runnable() { // from class: com.baidu.rtc.recorder.RtcVideoRecorder.2
            @Override // java.lang.Runnable
            public void run() {
                RtcVideoRecorder.this.releaseOnce();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseOnce() {
        log(" releaseOnce ");
        this.mInit = false;
        this.mStart = false;
        this.mEglBase.release();
        this.mFrameDrawer.release();
        this.mDrawer.release();
        this.mEncoder.stop();
        this.mEncoder.release();
        this.mEncoder = null;
        this.mPtsStart = 0L;
    }

    @Override // com.baidu.rtc.recorder.IRtcMediaRecorder
    public int startRecording(BRTCMediaRecorderParams bRTCMediaRecorderParams) {
        if (this.mInit) {
            return -103;
        }
        this.mEncodeParams = bRTCMediaRecorderParams.encodeParams;
        initEncoder();
        return 0;
    }

    @Override // com.baidu.rtc.recorder.IRtcMediaRecorder
    public int stopRecording() {
        this.mWorkThreadHandler.post(new Runnable() { // from class: com.baidu.rtc.recorder.RtcVideoRecorder.3
            @Override // java.lang.Runnable
            public void run() {
                if (RtcVideoRecorder.this.mEncoder == null) {
                    return;
                }
                RtcVideoRecorder.this.log(" stopRecording signalEndOfInputStream");
                RtcVideoRecorder.this.mEncoder.signalEndOfInputStream();
                while (RtcVideoRecorder.this.mInit) {
                    RtcVideoRecorder.this.drainVideo();
                }
            }
        });
        return 0;
    }

    @Override // com.baidu.rtc.recorder.IRtcMediaRecorder
    public void release() {
        this.mWorkThread.quitSafely();
    }

    @Override // com.baidu.rtc.recorder.IRtcMediaRecorder
    public void setMediaRecorderCallback(BRTCMediaRecorderCallback bRTCMediaRecorderCallback) {
        this.mRecorderCallback = bRTCMediaRecorderCallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log(String str) {
        BRTCMediaRecorder.log("VideoRecorder --- " + str);
    }

    private void logFrequently(String str) {
        BRTCMediaRecorder.logFrequently("VideoRecorder --- " + str);
    }
}
