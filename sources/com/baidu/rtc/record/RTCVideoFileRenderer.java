package com.baidu.rtc.record;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import com.baidu.rtc.RTCAudioSamples;
import com.webrtc.EglBase;
import com.webrtc.GlRectDrawer;
import com.webrtc.Logging;
import com.webrtc.VideoFrame;
import com.webrtc.VideoFrameDrawer;
import com.webrtc.VideoSink;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\567196_dexfile_execute.dex */
public class RTCVideoFileRenderer implements RTCAudioSamples.RTCRemoteSamplesReadyCallback, VideoSink {
    private static final int PRE_PREIOD_AUDIO_BUFFER_SIZE = 3840;
    private static final String TAG = "RTCVideoFileRenderer";
    private MediaCodec.BufferInfo audioBufferInfo;
    private MediaCodec audioEncoder;
    private ByteBuffer[] audioInputBuffers;
    private ByteBuffer[] audioOutputBuffers;
    private final HandlerThread audioThread;
    private final Handler audioThreadHandler;
    private int audioTrackIndex;
    private GlRectDrawer drawer;
    private EglBase eglBase;
    private MediaEncodeParams encodeParams;
    private VideoFrameDrawer frameDrawer;
    private byte[] mByteBuffer;
    private final RecorderCallback mCallback;
    private MediaMuxer mediaMuxer;
    private final String outputFileName;
    private final HandlerThread renderThread;
    private final Handler renderThreadHandler;
    private EglBase.Context sharedContext;
    private Surface surface;
    private MediaCodec.BufferInfo videoBufferInfo;
    private MediaCodec videoEncoder;
    private ByteBuffer[] videoOutputBuffers;
    private int trackIndex = -1;
    private boolean isRunning = true;
    private boolean encoderStarted = false;
    private volatile boolean muxerStarted = false;
    private long videoFrameStart = 0;
    private long presTime = 0;
    private volatile int audioEncodeBufferSize = 0;

    public RTCVideoFileRenderer(String str, MediaEncodeParams mediaEncodeParams, EglBase.Context context, boolean z, RecorderCallback recorderCallback) throws IOException {
        this.mByteBuffer = null;
        this.encodeParams = mediaEncodeParams;
        if (this.encodeParams == null) {
            this.encodeParams = new MediaEncodeParams();
        }
        this.outputFileName = str;
        this.renderThread = new HandlerThread("RTCVideoFileRendererRenderThread");
        this.renderThread.start();
        this.renderThreadHandler = new Handler(this.renderThread.getLooper());
        if (z) {
            this.audioThread = new HandlerThread("RTCVideoFileRendererAudioThread");
            this.audioThread.start();
            this.audioThreadHandler = new Handler(this.audioThread.getLooper());
            this.mByteBuffer = new byte[3840];
        } else {
            this.audioThread = null;
            this.audioThreadHandler = null;
        }
        this.videoBufferInfo = new MediaCodec.BufferInfo();
        this.sharedContext = context;
        this.mediaMuxer = new MediaMuxer(this.outputFileName, this.encodeParams.getOutputFormat());
        this.audioTrackIndex = z ? -1 : 0;
        this.mCallback = recorderCallback;
    }

    private void initVideoEncoder() {
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat(this.encodeParams.getVideoCodec(), this.encodeParams.getVideoWidth(), this.encodeParams.getVideoHeight());
        createVideoFormat.setInteger("color-format", 2130708361);
        createVideoFormat.setInteger("bitrate", this.encodeParams.getVideoBitrate());
        createVideoFormat.setInteger("frame-rate", this.encodeParams.getVideoFrameRate());
        createVideoFormat.setInteger("i-frame-interval", this.encodeParams.getVideoIFrameInterval());
        try {
            this.videoEncoder = MediaCodec.createEncoderByType(this.encodeParams.getVideoCodec());
            this.videoEncoder.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
            this.renderThreadHandler.post(new Runnable() { // from class: com.baidu.rtc.record.-$$Lambda$RTCVideoFileRenderer$4X3W21YKlCQ0fnPh79e7wedKXWM
                @Override // java.lang.Runnable
                public final void run() {
                    RTCVideoFileRenderer.lambda$initVideoEncoder$0(RTCVideoFileRenderer.this);
                }
            });
        } catch (Exception e) {
            Logging.m5304e("RTCVideoFileRenderer", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$initVideoEncoder$0(RTCVideoFileRenderer rTCVideoFileRenderer) {
        rTCVideoFileRenderer.eglBase = EglBase.CC.create(rTCVideoFileRenderer.sharedContext, EglBase.CONFIG_RECORDABLE);
        rTCVideoFileRenderer.surface = rTCVideoFileRenderer.videoEncoder.createInputSurface();
        rTCVideoFileRenderer.eglBase.createSurface(rTCVideoFileRenderer.surface);
        rTCVideoFileRenderer.eglBase.makeCurrent();
        rTCVideoFileRenderer.drawer = new GlRectDrawer();
    }

    @Override // com.webrtc.VideoSink
    public void onFrame(final VideoFrame videoFrame) {
        videoFrame.retain();
        if (this.videoEncoder == null) {
            initVideoEncoder();
        }
        this.renderThreadHandler.post(new Runnable() { // from class: com.baidu.rtc.record.-$$Lambda$RTCVideoFileRenderer$01riTKQJ16i7FYZJtWRlK5zkQQE
            @Override // java.lang.Runnable
            public final void run() {
                RTCVideoFileRenderer.this.renderFrameOnRenderThread(videoFrame);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void renderFrameOnRenderThread(VideoFrame videoFrame) {
        if (this.frameDrawer == null) {
            this.frameDrawer = new VideoFrameDrawer();
        }
        this.frameDrawer.drawFrame(videoFrame, this.drawer, null, 0, 0, this.encodeParams.getVideoWidth(), this.encodeParams.getVideoHeight());
        videoFrame.release();
        drainVideo();
        this.eglBase.swapBuffers();
    }

    private void drainVideo() {
        if (!this.encoderStarted) {
            this.videoEncoder.start();
            this.videoOutputBuffers = this.videoEncoder.getOutputBuffers();
            this.encoderStarted = true;
            return;
        }
        while (true) {
            int dequeueOutputBuffer = this.videoEncoder.dequeueOutputBuffer(this.videoBufferInfo, 10000L);
            if (dequeueOutputBuffer == -1) {
                return;
            }
            if (dequeueOutputBuffer == -3) {
                this.videoOutputBuffers = this.videoEncoder.getOutputBuffers();
                Logging.m5304e("RTCVideoFileRenderer", "video encoder output buffers changed");
            } else if (dequeueOutputBuffer == -2) {
                MediaFormat outputFormat = this.videoEncoder.getOutputFormat();
                Logging.m5304e("RTCVideoFileRenderer", "video encoder output format changed: " + outputFormat);
                this.trackIndex = this.mediaMuxer.addTrack(outputFormat);
                if (this.audioTrackIndex != -1 && !this.muxerStarted) {
                    this.mediaMuxer.start();
                    this.muxerStarted = true;
                }
                if (!this.muxerStarted) {
                    return;
                }
            } else if (dequeueOutputBuffer < 0) {
                Logging.m5304e("RTCVideoFileRenderer", "unexpected on video encoder dequeueOutputBuffer: " + dequeueOutputBuffer);
            } else {
                try {
                    ByteBuffer byteBuffer = this.videoOutputBuffers[dequeueOutputBuffer];
                    if (byteBuffer == null) {
                        Logging.m5304e("RTCVideoFileRenderer", "encoderOutputBuffer " + dequeueOutputBuffer + " was null");
                        return;
                    }
                    byteBuffer.position(this.videoBufferInfo.offset);
                    byteBuffer.limit(this.videoBufferInfo.offset + this.videoBufferInfo.size);
                    if (this.videoFrameStart == 0 && this.videoBufferInfo.presentationTimeUs != 0) {
                        this.videoFrameStart = this.videoBufferInfo.presentationTimeUs;
                    }
                    this.videoBufferInfo.presentationTimeUs -= this.videoFrameStart;
                    if (this.muxerStarted && this.mediaMuxer != null) {
                        this.mediaMuxer.writeSampleData(this.trackIndex, byteBuffer, this.videoBufferInfo);
                    }
                    this.isRunning = this.isRunning && (this.videoBufferInfo.flags & 4) == 0;
                    this.videoEncoder.releaseOutputBuffer(dequeueOutputBuffer, false);
                    if ((this.videoBufferInfo.flags & 4) != 0) {
                        return;
                    }
                } catch (Exception e) {
                    Logging.m5304e("RTCVideoFileRenderer", e.getMessage());
                    return;
                }
            }
        }
    }

    private void initStartAudioEncoder(RTCAudioSamples rTCAudioSamples) {
        try {
            this.encodeParams.setAudioSampleRate(rTCAudioSamples.getSampleRate());
            this.audioEncoder = MediaCodec.createEncoderByType(this.encodeParams.getAudioCodec());
            MediaFormat mediaFormat = new MediaFormat();
            mediaFormat.setString("mime", this.encodeParams.getAudioCodec());
            mediaFormat.setInteger("channel-count", this.encodeParams.getAudioChannel());
            mediaFormat.setInteger("sample-rate", this.encodeParams.getAudioSampleRate());
            mediaFormat.setInteger("bitrate", this.encodeParams.getAudioBitrate());
            mediaFormat.setInteger("aac-profile", 2);
            mediaFormat.setInteger("max-input-size", 3840);
            this.audioEncoder.configure(mediaFormat, (Surface) null, (MediaCrypto) null, 1);
            this.audioEncoder.start();
            this.audioInputBuffers = this.audioEncoder.getInputBuffers();
            this.audioOutputBuffers = this.audioEncoder.getOutputBuffers();
        } catch (IOException e) {
            Logging.m5304e("RTCVideoFileRenderer", e.getMessage());
        }
    }

    @Override // com.baidu.rtc.RTCAudioSamples.RTCRemoteSamplesReadyCallback
    public void onRtcAudioRemoteSamplesReady(RTCAudioSamples rTCAudioSamples) {
        if (this.audioEncodeBufferSize + rTCAudioSamples.getData().length > 3840) {
            RTCAudioSamples rTCAudioSamples2 = new RTCAudioSamples(rTCAudioSamples.getAudioFormat(), rTCAudioSamples.getChannelCount(), rTCAudioSamples.getSampleRate(), Arrays.copyOfRange(this.mByteBuffer, 0, this.audioEncodeBufferSize));
            this.audioEncodeBufferSize = 0;
            audioSamplesRecord(rTCAudioSamples2);
        }
        System.arraycopy(rTCAudioSamples.getData(), 0, this.mByteBuffer, this.audioEncodeBufferSize, rTCAudioSamples.getData().length);
        this.audioEncodeBufferSize += rTCAudioSamples.getData().length;
    }

    private void audioSamplesRecord(final RTCAudioSamples rTCAudioSamples) {
        if (rTCAudioSamples == null) {
            return;
        }
        this.audioThreadHandler.post(new Runnable() { // from class: com.baidu.rtc.record.-$$Lambda$RTCVideoFileRenderer$IR4KFudZn8pFkt9qCB0HXfxm5xE
            @Override // java.lang.Runnable
            public final void run() {
                RTCVideoFileRenderer.lambda$audioSamplesRecord$2(RTCVideoFileRenderer.this, rTCAudioSamples);
            }
        });
    }

    public static /* synthetic */ void lambda$audioSamplesRecord$2(RTCVideoFileRenderer rTCVideoFileRenderer, RTCAudioSamples rTCAudioSamples) {
        if (rTCVideoFileRenderer.audioEncoder == null) {
            rTCVideoFileRenderer.initStartAudioEncoder(rTCAudioSamples);
        }
        int dequeueInputBuffer = rTCVideoFileRenderer.audioEncoder.dequeueInputBuffer(0L);
        if (dequeueInputBuffer >= 0) {
            ByteBuffer byteBuffer = rTCVideoFileRenderer.audioInputBuffers[dequeueInputBuffer];
            byteBuffer.clear();
            byte[] data = rTCAudioSamples.getData();
            byteBuffer.put(data);
            rTCVideoFileRenderer.audioEncoder.queueInputBuffer(dequeueInputBuffer, 0, data.length, rTCVideoFileRenderer.presTime, 0);
            rTCVideoFileRenderer.presTime += ((data.length / 2) * 1000000) / rTCVideoFileRenderer.encodeParams.getAudioSampleRate();
        }
        rTCVideoFileRenderer.drainAudio();
    }

    private void drainAudio() {
        if (this.audioBufferInfo == null) {
            this.audioBufferInfo = new MediaCodec.BufferInfo();
        }
        while (true) {
            int dequeueOutputBuffer = this.audioEncoder.dequeueOutputBuffer(this.audioBufferInfo, 10000L);
            if (dequeueOutputBuffer == -1) {
                return;
            }
            if (dequeueOutputBuffer == -3) {
                this.audioOutputBuffers = this.audioEncoder.getOutputBuffers();
                Logging.m5301w("RTCVideoFileRenderer", "encoder output buffers changed");
            } else {
                boolean z = true;
                if (dequeueOutputBuffer == -2) {
                    MediaFormat outputFormat = this.audioEncoder.getOutputFormat();
                    Logging.m5301w("RTCVideoFileRenderer", "audio encoder output format changed: " + outputFormat);
                    this.audioTrackIndex = this.mediaMuxer.addTrack(outputFormat);
                    if (this.trackIndex != -1 && !this.muxerStarted) {
                        this.mediaMuxer.start();
                        this.muxerStarted = true;
                    }
                    if (!this.muxerStarted) {
                        return;
                    }
                } else if (dequeueOutputBuffer < 0) {
                    Logging.m5304e("RTCVideoFileRenderer", "unexpected on audio encoder dequeueOutputBuffer: " + dequeueOutputBuffer);
                } else {
                    try {
                        ByteBuffer byteBuffer = this.audioOutputBuffers[dequeueOutputBuffer];
                        if (byteBuffer == null) {
                            Logging.m5304e("RTCVideoFileRenderer", "encoderOutputBuffer " + dequeueOutputBuffer + " was null");
                            return;
                        }
                        byteBuffer.position(this.audioBufferInfo.offset);
                        byteBuffer.limit(this.audioBufferInfo.offset + this.audioBufferInfo.size);
                        if (this.muxerStarted && this.mediaMuxer != null) {
                            this.mediaMuxer.writeSampleData(this.audioTrackIndex, byteBuffer, this.audioBufferInfo);
                        }
                        if (!this.isRunning || (this.audioBufferInfo.flags & 4) != 0) {
                            z = false;
                        }
                        this.isRunning = z;
                        this.audioEncoder.releaseOutputBuffer(dequeueOutputBuffer, false);
                        if ((this.audioBufferInfo.flags & 4) != 0) {
                            return;
                        }
                    } catch (Exception e) {
                        Logging.m5304e("RTCVideoFileRenderer", e.getMessage());
                        return;
                    }
                }
            }
        }
    }

    public void release() {
        this.isRunning = false;
        Handler handler = this.audioThreadHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.baidu.rtc.record.-$$Lambda$RTCVideoFileRenderer$3Rniwa9Q_TpRL66PXoAH_jTeAC0
                @Override // java.lang.Runnable
                public final void run() {
                    RTCVideoFileRenderer.lambda$release$3(RTCVideoFileRenderer.this);
                }
            });
        }
        Handler handler2 = this.renderThreadHandler;
        if (handler2 != null) {
            handler2.post(new Runnable() { // from class: com.baidu.rtc.record.-$$Lambda$RTCVideoFileRenderer$L4ugvnTVIS7nvLQRQyzDiLIBHRY
                @Override // java.lang.Runnable
                public final void run() {
                    RTCVideoFileRenderer.lambda$release$4(RTCVideoFileRenderer.this);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$release$3(RTCVideoFileRenderer rTCVideoFileRenderer) {
        Logging.m5301w("RTCVideoFileRenderer", "stop audio encoder ...");
        MediaCodec mediaCodec = rTCVideoFileRenderer.audioEncoder;
        if (mediaCodec != null) {
            mediaCodec.flush();
            rTCVideoFileRenderer.audioEncoder.stop();
            rTCVideoFileRenderer.audioEncoder.release();
            rTCVideoFileRenderer.audioEncoder = null;
        }
        try {
            if (rTCVideoFileRenderer.mediaMuxer != null && rTCVideoFileRenderer.muxerStarted) {
                rTCVideoFileRenderer.mediaMuxer.stop();
                rTCVideoFileRenderer.mediaMuxer.release();
                if (rTCVideoFileRenderer.mCallback != null) {
                    rTCVideoFileRenderer.mCallback.onRecordCompleted(true, rTCVideoFileRenderer.outputFileName);
                }
            } else if (rTCVideoFileRenderer.mCallback != null) {
                rTCVideoFileRenderer.mCallback.onRecordCompleted(false, "Record is not started!");
            }
        } catch (IllegalStateException e) {
            Logging.m5304e("RTCVideoFileRenderer", "Stop media muxer exception : " + e.getLocalizedMessage());
            RecorderCallback recorderCallback = rTCVideoFileRenderer.mCallback;
            if (recorderCallback != null) {
                recorderCallback.onRecordCompleted(false, e.getLocalizedMessage());
            }
        }
        rTCVideoFileRenderer.audioThread.quit();
    }

    public static /* synthetic */ void lambda$release$4(RTCVideoFileRenderer rTCVideoFileRenderer) {
        Logging.m5301w("RTCVideoFileRenderer", "stop video encoder ...");
        MediaCodec mediaCodec = rTCVideoFileRenderer.videoEncoder;
        if (mediaCodec != null) {
            mediaCodec.flush();
            rTCVideoFileRenderer.videoEncoder.stop();
            rTCVideoFileRenderer.videoEncoder.release();
            rTCVideoFileRenderer.videoEncoder = null;
        }
        rTCVideoFileRenderer.renderThread.quit();
        EglBase eglBase = rTCVideoFileRenderer.eglBase;
        if (eglBase != null) {
            eglBase.release();
        }
    }
}
