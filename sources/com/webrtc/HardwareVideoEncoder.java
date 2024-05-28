package com.webrtc;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.opengl.GLES20;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Surface;
import com.baidu.rtc.logreport.RtcLogCapturer;
import com.baidu.rtc.logreport.RtcLogEvent;
import com.webrtc.EglBase14;
import com.webrtc.EncodedImage;
import com.webrtc.ThreadUtils;
import com.webrtc.VideoEncoder;
import com.webrtc.VideoFrame;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
@TargetApi(19)
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class HardwareVideoEncoder implements VideoEncoder {
    private static final int DEQUEUE_OUTPUT_BUFFER_TIMEOUT_US = 100000;
    private static final String KEY_BITRATE_MODE = "bitrate-mode";
    private static final int MAX_ENCODER_Q_SIZE = 2;
    private static final int MAX_VIDEO_FRAMERATE = 30;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final String TAG = "HardwareVideoEncoder";
    private static final int VIDEO_AVC_LEVEL_3 = 256;
    private static final int VIDEO_AVC_PROFILE_HIGH = 8;
    private static final int VIDEO_ControlRateConstant = 2;
    private static final int VIDEO_ControlRateDisable = 0;
    private static final int VIDEO_ControlRateVariable = 1;
    private static int bitrateMode = 2;
    private int adjustedBitrate;
    private boolean automaticResizeOn;
    private final BitrateAdjuster bitrateAdjuster;
    private VideoEncoder.Callback callback;
    @Nullable
    private MediaCodecWrapper codec;
    private final String codecName;
    private final VideoCodecMimeType codecType;
    @Nullable
    private ByteBuffer configBuffer;
    private final long forcedKeyFrameNs;
    private int height;
    private boolean isFirstEncode;
    private final int keyFrameIntervalSec;
    private long lastKeyFrameNs;
    private final MediaCodecWrapperFactory mediaCodecWrapperFactory;
    @Nullable
    private ByteBuffer[] outputBuffers;
    @Nullable
    private Thread outputThread;
    private final Map<String, String> params;
    private volatile boolean running;
    private final EglBase14.Context sharedContext;
    @Nullable
    private volatile Exception shutdownException;
    private final Integer surfaceColorFormat;
    @Nullable
    private EglBase14 textureEglBase;
    @Nullable
    private Surface textureInputSurface;
    private boolean useSurfaceMode;
    private int width;
    private final Integer yuvColorFormat;
    private final YuvFormat yuvFormat;
    private String bitrateModePreset = PeerConnectionFactory.fieldTrialsFindFullName("BRTC-Encoder-BitrateMode");
    private final GlRectDrawer textureDrawer = new GlRectDrawer();
    private final VideoFrameDrawer videoFrameDrawer = new VideoFrameDrawer();
    private final BlockingDeque<EncodedImage.Builder> outputBuilders = new LinkedBlockingDeque();
    private final ThreadUtils.ThreadChecker encodeThreadChecker = new ThreadUtils.ThreadChecker();
    private final ThreadUtils.ThreadChecker outputThreadChecker = new ThreadUtils.ThreadChecker();
    private final BusyCount outputBuffersBusyCount = new BusyCount();

    @Override // com.webrtc.VideoEncoder
    @CalledByNative
    public /* synthetic */ long createNativeVideoEncoder() {
        return VideoEncoder.CC.$default$createNativeVideoEncoder(this);
    }

    @Override // com.webrtc.VideoEncoder
    public String getImplementationName() {
        return "HWEncoder";
    }

    @Override // com.webrtc.VideoEncoder
    @CalledByNative
    public /* synthetic */ VideoEncoder.ResolutionBitrateLimits[] getResolutionBitrateLimits() {
        return VideoEncoder.CC.$default$getResolutionBitrateLimits(this);
    }

    @Override // com.webrtc.VideoEncoder
    @CalledByNative
    public /* synthetic */ boolean isHardwareEncoder() {
        return VideoEncoder.CC.$default$isHardwareEncoder(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class BusyCount {
        private int count;
        private final Object countLock;

        private BusyCount() {
            this.countLock = new Object();
        }

        public void increment() {
            synchronized (this.countLock) {
                this.count++;
            }
        }

        public void decrement() {
            synchronized (this.countLock) {
                this.count--;
                if (this.count == 0) {
                    this.countLock.notifyAll();
                }
            }
        }

        public void waitForZero() {
            boolean z;
            synchronized (this.countLock) {
                z = false;
                while (this.count > 0) {
                    try {
                        this.countLock.wait();
                    } catch (InterruptedException e) {
                        Logging.m5303e(HardwareVideoEncoder.TAG, "Interrupted while waiting on busy count", e);
                        z = true;
                    }
                }
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public HardwareVideoEncoder(MediaCodecWrapperFactory mediaCodecWrapperFactory, String str, VideoCodecMimeType videoCodecMimeType, Integer num, Integer num2, Map<String, String> map, int i, int i2, BitrateAdjuster bitrateAdjuster, EglBase14.Context context) {
        this.isFirstEncode = false;
        this.mediaCodecWrapperFactory = mediaCodecWrapperFactory;
        this.codecName = str;
        this.codecType = videoCodecMimeType;
        this.surfaceColorFormat = num;
        this.yuvColorFormat = num2;
        this.yuvFormat = YuvFormat.valueOf(num2.intValue());
        this.params = map;
        this.keyFrameIntervalSec = i;
        this.forcedKeyFrameNs = TimeUnit.MILLISECONDS.toNanos(i2);
        this.bitrateAdjuster = bitrateAdjuster;
        this.sharedContext = context;
        this.isFirstEncode = true;
        this.encodeThreadChecker.detachThread();
    }

    @Override // com.webrtc.VideoEncoder
    public VideoCodecStatus initEncode(VideoEncoder.Settings settings, VideoEncoder.Callback callback) {
        int parseInt;
        this.encodeThreadChecker.checkIsOnValidThread();
        this.callback = callback;
        this.automaticResizeOn = settings.automaticResizeOn;
        this.width = settings.width;
        this.height = settings.height;
        this.useSurfaceMode = canUseSurface();
        if (settings.startBitrate != 0 && settings.maxFramerate != 0) {
            this.bitrateAdjuster.setTargets(settings.startBitrate * 1000, settings.maxFramerate);
        }
        this.adjustedBitrate = this.bitrateAdjuster.getAdjustedBitrateBps();
        if (!TextUtils.isEmpty(this.bitrateModePreset) && ((parseInt = Integer.parseInt(this.bitrateModePreset)) == 0 || parseInt == 1 || parseInt == 2)) {
            bitrateMode = parseInt;
        }
        Logging.m5305d(TAG, "initEncode: " + this.width + " x " + this.height + ". @ " + settings.startBitrate + "kbps. Fps: " + settings.maxFramerate + " Use surface mode: " + this.useSurfaceMode + ". BitrateMode: " + bitrateMode);
        return initEncodeInternal();
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x009b A[Catch: IllegalStateException -> 0x0165, TryCatch #1 {IllegalStateException -> 0x0165, blocks: (B:9:0x0025, B:11:0x0063, B:14:0x0071, B:26:0x0095, B:29:0x00a6, B:28:0x009b, B:32:0x00be, B:34:0x011a, B:35:0x0138, B:19:0x0080, B:22:0x008a), top: B:45:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ba A[FALL_THROUGH] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.webrtc.VideoCodecStatus initEncodeInternal() {
        /*
            Method dump skipped, instructions count: 434
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webrtc.HardwareVideoEncoder.initEncodeInternal():com.webrtc.VideoCodecStatus");
    }

    @Override // com.webrtc.VideoEncoder
    public VideoCodecStatus release() {
        VideoCodecStatus videoCodecStatus;
        this.encodeThreadChecker.checkIsOnValidThread();
        Thread thread = this.outputThread;
        if (thread == null) {
            videoCodecStatus = VideoCodecStatus.OK;
        } else {
            this.running = false;
            if (!ThreadUtils.joinUninterruptibly(thread, 5000L)) {
                Logging.m5304e(TAG, "Media encoder release timeout");
                videoCodecStatus = VideoCodecStatus.TIMEOUT;
            } else if (this.shutdownException != null) {
                Logging.m5303e(TAG, "Media encoder release exception", this.shutdownException);
                videoCodecStatus = VideoCodecStatus.ERROR;
            } else {
                videoCodecStatus = VideoCodecStatus.OK;
            }
        }
        this.textureDrawer.release();
        this.videoFrameDrawer.release();
        EglBase14 eglBase14 = this.textureEglBase;
        if (eglBase14 != null) {
            eglBase14.release();
            this.textureEglBase = null;
        }
        Surface surface = this.textureInputSurface;
        if (surface != null) {
            surface.release();
            this.textureInputSurface = null;
        }
        this.outputBuilders.clear();
        this.codec = null;
        this.outputBuffers = null;
        this.outputThread = null;
        this.encodeThreadChecker.detachThread();
        return videoCodecStatus;
    }

    @Override // com.webrtc.VideoEncoder
    public VideoCodecStatus encode(VideoFrame videoFrame, VideoEncoder.EncodeInfo encodeInfo) {
        VideoCodecStatus resetCodec;
        VideoCodecStatus encodeByteBuffer;
        this.encodeThreadChecker.checkIsOnValidThread();
        if (this.codec == null) {
            return VideoCodecStatus.UNINITIALIZED;
        }
        VideoFrame.Buffer buffer = videoFrame.getBuffer();
        boolean z = buffer instanceof VideoFrame.TextureBuffer;
        int width = videoFrame.getBuffer().getWidth();
        int height = videoFrame.getBuffer().getHeight();
        boolean z2 = canUseSurface() && z;
        if ((width == this.width && height == this.height && z2 == this.useSurfaceMode) || (resetCodec = resetCodec(width, height, z2)) == VideoCodecStatus.OK) {
            if (this.outputBuilders.size() > 2) {
                Logging.m5304e(TAG, "Dropped frame, encoder queue full");
                return VideoCodecStatus.NO_OUTPUT;
            }
            boolean z3 = false;
            for (EncodedImage.FrameType frameType : encodeInfo.frameTypes) {
                if (frameType == EncodedImage.FrameType.VideoFrameKey) {
                    z3 = true;
                }
            }
            if (z3 || shouldForceKeyFrame(videoFrame.getTimestampNs())) {
                requestKeyFrame(videoFrame.getTimestampNs());
            }
            if (this.isFirstEncode) {
                RtcLogCapturer.reportLog(RtcLogEvent.START_ENCODER, "HVEncoder-encode", Integer.valueOf(this.width), Integer.valueOf(this.height));
            }
            this.isFirstEncode = false;
            int height2 = ((buffer.getHeight() * buffer.getWidth()) * 3) / 2;
            this.outputBuilders.offer(EncodedImage.builder().setCaptureTimeNs(videoFrame.getTimestampNs()).setEncodedWidth(videoFrame.getBuffer().getWidth()).setEncodedHeight(videoFrame.getBuffer().getHeight()).setRotation(videoFrame.getRotation()));
            if (this.useSurfaceMode) {
                encodeByteBuffer = encodeTextureBuffer(videoFrame);
            } else {
                encodeByteBuffer = encodeByteBuffer(videoFrame, buffer, height2);
            }
            if (encodeByteBuffer != VideoCodecStatus.OK) {
                this.outputBuilders.pollLast();
            }
            return encodeByteBuffer;
        }
        return resetCodec;
    }

    private VideoCodecStatus encodeTextureBuffer(VideoFrame videoFrame) {
        this.encodeThreadChecker.checkIsOnValidThread();
        try {
            GLES20.glClear(16384);
            this.videoFrameDrawer.drawFrame(new VideoFrame(videoFrame.getBuffer(), 0, videoFrame.getTimestampNs()), this.textureDrawer, null);
            this.textureEglBase.swapBuffers(videoFrame.getTimestampNs());
            return VideoCodecStatus.OK;
        } catch (RuntimeException e) {
            Logging.m5303e(TAG, "encodeTexture failed", e);
            RtcLogCapturer.reportLog(RtcLogEvent.ENCODER_ERROR, "HVEncoder-encodeBuffer", -4, RtcLogCapturer.getErrorInfo(e.getMessage()));
            return VideoCodecStatus.ERROR;
        }
    }

    private VideoCodecStatus encodeByteBuffer(VideoFrame videoFrame, VideoFrame.Buffer buffer, int i) {
        this.encodeThreadChecker.checkIsOnValidThread();
        long timestampNs = (videoFrame.getTimestampNs() + 500) / 1000;
        try {
            int dequeueInputBuffer = this.codec.dequeueInputBuffer(0L);
            if (dequeueInputBuffer == -1) {
                Logging.m5305d(TAG, "Dropped frame, no input buffers available");
                return VideoCodecStatus.NO_OUTPUT;
            }
            try {
                fillInputBuffer(this.codec.getInputBuffers()[dequeueInputBuffer], buffer);
                try {
                    this.codec.queueInputBuffer(dequeueInputBuffer, 0, i, timestampNs, 0);
                    return VideoCodecStatus.OK;
                } catch (IllegalStateException e) {
                    Logging.m5303e(TAG, "queueInputBuffer failed", e);
                    RtcLogCapturer.reportLog(RtcLogEvent.ENCODER_ERROR, "HVEncoder-encodeBuffer", -3, RtcLogCapturer.getErrorInfo(e.getMessage()));
                    return VideoCodecStatus.ERROR;
                }
            } catch (IllegalStateException e2) {
                Logging.m5303e(TAG, "getInputBuffers failed", e2);
                RtcLogCapturer.reportLog(RtcLogEvent.ENCODER_ERROR, "HVEncoder-encodeBuffer", -2, RtcLogCapturer.getErrorInfo(e2.getMessage()));
                return VideoCodecStatus.ERROR;
            }
        } catch (IllegalStateException e3) {
            Logging.m5303e(TAG, "dequeueInputBuffer failed", e3);
            RtcLogCapturer.reportLog(RtcLogEvent.ENCODER_ERROR, "HVEncoder-encodeBuffer", -1, RtcLogCapturer.getErrorInfo(e3.getMessage()));
            return VideoCodecStatus.ERROR;
        }
    }

    @Override // com.webrtc.VideoEncoder
    public VideoCodecStatus setRateAllocation(VideoEncoder.BitrateAllocation bitrateAllocation, int i) {
        this.encodeThreadChecker.checkIsOnValidThread();
        if (i > 30) {
            i = 30;
        }
        this.bitrateAdjuster.setTargets(bitrateAllocation.getSum(), i);
        return VideoCodecStatus.OK;
    }

    @Override // com.webrtc.VideoEncoder
    public VideoEncoder.ScalingSettings getScalingSettings() {
        this.encodeThreadChecker.checkIsOnValidThread();
        if (this.automaticResizeOn) {
            if (this.codecType == VideoCodecMimeType.VP8) {
                return new VideoEncoder.ScalingSettings(29, 95);
            }
            if (this.codecType == VideoCodecMimeType.H264) {
                return new VideoEncoder.ScalingSettings(24, 37);
            }
        }
        return VideoEncoder.ScalingSettings.OFF;
    }

    private VideoCodecStatus resetCodec(int i, int i2, boolean z) {
        this.encodeThreadChecker.checkIsOnValidThread();
        VideoCodecStatus release = release();
        if (release != VideoCodecStatus.OK) {
            return release;
        }
        this.width = i;
        this.height = i2;
        this.useSurfaceMode = z;
        return initEncodeInternal();
    }

    private boolean shouldForceKeyFrame(long j) {
        this.encodeThreadChecker.checkIsOnValidThread();
        long j2 = this.forcedKeyFrameNs;
        return j2 > 0 && j > this.lastKeyFrameNs + j2;
    }

    private void requestKeyFrame(long j) {
        this.encodeThreadChecker.checkIsOnValidThread();
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("request-sync", 0);
            this.codec.setParameters(bundle);
            this.lastKeyFrameNs = j;
        } catch (IllegalStateException e) {
            Logging.m5303e(TAG, "requestKeyFrame failed", e);
        }
    }

    private Thread createOutputThread() {
        return new Thread() { // from class: com.webrtc.HardwareVideoEncoder.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                while (HardwareVideoEncoder.this.running) {
                    HardwareVideoEncoder.this.deliverEncodedImage();
                }
                HardwareVideoEncoder.this.releaseCodecOnOutputThread();
            }
        };
    }

    protected void deliverEncodedImage() {
        ByteBuffer slice;
        this.outputThreadChecker.checkIsOnValidThread();
        try {
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            final int dequeueOutputBuffer = this.codec.dequeueOutputBuffer(bufferInfo, 100000L);
            if (dequeueOutputBuffer < 0) {
                if (dequeueOutputBuffer == -3) {
                    this.outputBuffersBusyCount.waitForZero();
                    this.outputBuffers = this.codec.getOutputBuffers();
                    return;
                }
                return;
            }
            ByteBuffer byteBuffer = this.outputBuffers[dequeueOutputBuffer];
            byteBuffer.position(bufferInfo.offset);
            byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
            if ((bufferInfo.flags & 2) != 0) {
                Logging.m5305d(TAG, "Config frame generated. Offset: " + bufferInfo.offset + ". Size: " + bufferInfo.size);
                this.configBuffer = ByteBuffer.allocateDirect(bufferInfo.size);
                this.configBuffer.put(byteBuffer);
                return;
            }
            this.bitrateAdjuster.reportEncodedFrame(bufferInfo.size);
            if (this.adjustedBitrate != this.bitrateAdjuster.getAdjustedBitrateBps()) {
                updateBitrate();
            }
            boolean z = true;
            if ((bufferInfo.flags & 1) == 0) {
                z = false;
            }
            if (z) {
                Logging.m5305d(TAG, "Sync frame generated");
            }
            if (z && this.codecType == VideoCodecMimeType.H264) {
                Logging.m5305d(TAG, "Prepending config frame of size " + this.configBuffer.capacity() + " to output buffer with offset " + bufferInfo.offset + ", size " + bufferInfo.size);
                slice = ByteBuffer.allocateDirect(bufferInfo.size + this.configBuffer.capacity());
                this.configBuffer.rewind();
                slice.put(this.configBuffer);
                slice.put(byteBuffer);
                slice.rewind();
            } else {
                slice = byteBuffer.slice();
            }
            EncodedImage.FrameType frameType = z ? EncodedImage.FrameType.VideoFrameKey : EncodedImage.FrameType.VideoFrameDelta;
            this.outputBuffersBusyCount.increment();
            EncodedImage createEncodedImage = this.outputBuilders.poll().setBuffer(slice, new Runnable() { // from class: com.webrtc.-$$Lambda$HardwareVideoEncoder$N8Zc2vJ91DMMeBtCdYt5sPDVsHM
                @Override // java.lang.Runnable
                public final void run() {
                    HardwareVideoEncoder.lambda$deliverEncodedImage$0(HardwareVideoEncoder.this, dequeueOutputBuffer);
                }
            }).setFrameType(frameType).createEncodedImage();
            this.callback.onEncodedFrame(createEncodedImage, new VideoEncoder.CodecSpecificInfo());
            createEncodedImage.release();
        } catch (IllegalStateException e) {
            Logging.m5303e(TAG, "deliverOutput failed", e);
        }
    }

    public static /* synthetic */ void lambda$deliverEncodedImage$0(HardwareVideoEncoder hardwareVideoEncoder, int i) {
        try {
            hardwareVideoEncoder.codec.releaseOutputBuffer(i, false);
        } catch (Exception e) {
            Logging.m5303e(TAG, "releaseOutputBuffer failed", e);
        }
        hardwareVideoEncoder.outputBuffersBusyCount.decrement();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseCodecOnOutputThread() {
        this.outputThreadChecker.checkIsOnValidThread();
        Logging.m5305d(TAG, "Releasing MediaCodec on output thread");
        this.outputBuffersBusyCount.waitForZero();
        RtcLogCapturer.reportLog(RtcLogEvent.STOP_ENCODER, "HVEncoder-releaseCodecOnOutputThread");
        try {
            this.codec.stop();
        } catch (Exception e) {
            Logging.m5303e(TAG, "Media encoder stop failed", e);
            RtcLogEvent rtcLogEvent = RtcLogEvent.STOP_ENCODER_FAILED;
            RtcLogCapturer.reportLog(rtcLogEvent, "HVEncoder-releaseCodecOnOutputThread", -1, "Media encoder stop failed " + RtcLogCapturer.getErrorInfo(e.getMessage()));
        }
        try {
            this.codec.release();
        } catch (Exception e2) {
            Logging.m5303e(TAG, "Media encoder release failed", e2);
            this.shutdownException = e2;
        }
        this.configBuffer = null;
        Logging.m5305d(TAG, "Release on output thread done");
    }

    private VideoCodecStatus updateBitrate() {
        this.outputThreadChecker.checkIsOnValidThread();
        this.adjustedBitrate = this.bitrateAdjuster.getAdjustedBitrateBps();
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("video-bitrate", this.adjustedBitrate);
            this.codec.setParameters(bundle);
            return VideoCodecStatus.OK;
        } catch (IllegalStateException e) {
            Logging.m5303e(TAG, "updateBitrate failed", e);
            return VideoCodecStatus.ERROR;
        }
    }

    private boolean canUseSurface() {
        return (this.sharedContext == null || this.surfaceColorFormat == null) ? false : true;
    }

    protected void fillInputBuffer(ByteBuffer byteBuffer, VideoFrame.Buffer buffer) {
        this.yuvFormat.fillBuffer(byteBuffer, buffer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum YuvFormat {
        I420 { // from class: com.webrtc.HardwareVideoEncoder.YuvFormat.1
            @Override // com.webrtc.HardwareVideoEncoder.YuvFormat
            void fillBuffer(ByteBuffer byteBuffer, VideoFrame.Buffer buffer) {
                VideoFrame.I420Buffer i420 = buffer.toI420();
                YuvHelper.I420Copy(i420.getDataY(), i420.getStrideY(), i420.getDataU(), i420.getStrideU(), i420.getDataV(), i420.getStrideV(), byteBuffer, i420.getWidth(), i420.getHeight());
                i420.release();
            }
        },
        NV12 { // from class: com.webrtc.HardwareVideoEncoder.YuvFormat.2
            @Override // com.webrtc.HardwareVideoEncoder.YuvFormat
            void fillBuffer(ByteBuffer byteBuffer, VideoFrame.Buffer buffer) {
                VideoFrame.I420Buffer i420 = buffer.toI420();
                YuvHelper.I420ToNV12(i420.getDataY(), i420.getStrideY(), i420.getDataU(), i420.getStrideU(), i420.getDataV(), i420.getStrideV(), byteBuffer, i420.getWidth(), i420.getHeight());
                i420.release();
            }
        };

        abstract void fillBuffer(ByteBuffer byteBuffer, VideoFrame.Buffer buffer);

        static YuvFormat valueOf(int i) {
            if (i != 19) {
                if (i == 21 || i == 2141391872 || i == 2141391876) {
                    return NV12;
                }
                throw new IllegalArgumentException("Unsupported colorFormat: " + i);
            }
            return I420;
        }
    }
}
