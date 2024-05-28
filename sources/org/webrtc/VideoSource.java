package org.webrtc;

import android.support.annotation.Nullable;
import org.webrtc.VideoProcessor;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class VideoSource extends MediaSource {
    private final CapturerObserver capturerObserver;
    private boolean isCapturerRunning;
    private final NativeAndroidVideoTrackSource nativeAndroidVideoTrackSource;
    @Nullable
    private VideoProcessor videoProcessor;
    private final Object videoProcessorLock;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class AspectRatio {
        public static final AspectRatio UNDEFINED = new AspectRatio(0, 0);
        public final int height;
        public final int width;

        public AspectRatio(int i, int i2) {
            this.width = i;
            this.height = i2;
        }
    }

    public VideoSource(long j) {
        super(j);
        this.videoProcessorLock = new Object();
        this.capturerObserver = new CapturerObserver() { // from class: org.webrtc.VideoSource.1
            @Override // org.webrtc.CapturerObserver
            public void onCapturerStarted(boolean z) {
                VideoSource.this.nativeAndroidVideoTrackSource.setState(z);
                synchronized (VideoSource.this.videoProcessorLock) {
                    VideoSource.this.isCapturerRunning = z;
                    if (VideoSource.this.videoProcessor != null) {
                        VideoSource.this.videoProcessor.onCapturerStarted(z);
                    }
                }
            }

            @Override // org.webrtc.CapturerObserver
            public void onCapturerStopped() {
                VideoSource.this.nativeAndroidVideoTrackSource.setState(false);
                synchronized (VideoSource.this.videoProcessorLock) {
                    VideoSource.this.isCapturerRunning = false;
                    if (VideoSource.this.videoProcessor != null) {
                        VideoSource.this.videoProcessor.onCapturerStopped();
                    }
                }
            }

            @Override // org.webrtc.CapturerObserver
            public void onFrameCaptured(VideoFrame videoFrame) {
                VideoProcessor.FrameAdaptationParameters adaptFrame = VideoSource.this.nativeAndroidVideoTrackSource.adaptFrame(videoFrame);
                synchronized (VideoSource.this.videoProcessorLock) {
                    if (VideoSource.this.videoProcessor != null) {
                        VideoSource.this.videoProcessor.onFrameCaptured(videoFrame, adaptFrame);
                        return;
                    }
                    VideoFrame applyFrameAdaptationParameters = VideoProcessor.CC.applyFrameAdaptationParameters(videoFrame, adaptFrame);
                    if (applyFrameAdaptationParameters != null) {
                        VideoSource.this.nativeAndroidVideoTrackSource.onFrameCaptured(applyFrameAdaptationParameters);
                        applyFrameAdaptationParameters.release();
                    }
                }
            }
        };
        this.nativeAndroidVideoTrackSource = new NativeAndroidVideoTrackSource(j);
    }

    public void adaptOutputFormat(int i, int i2, int i3) {
        int max = Math.max(i, i2);
        int min = Math.min(i, i2);
        adaptOutputFormat(max, min, min, max, i3);
    }

    public void adaptOutputFormat(int i, int i2, int i3, int i4, int i5) {
        adaptOutputFormat(new AspectRatio(i, i2), Integer.valueOf(i * i2), new AspectRatio(i3, i4), Integer.valueOf(i3 * i4), Integer.valueOf(i5));
    }

    public void adaptOutputFormat(AspectRatio aspectRatio, @Nullable Integer num, AspectRatio aspectRatio2, @Nullable Integer num2, @Nullable Integer num3) {
        this.nativeAndroidVideoTrackSource.adaptOutputFormat(aspectRatio, num, aspectRatio2, num2, num3);
    }

    public void setIsScreencast(boolean z) {
        this.nativeAndroidVideoTrackSource.setIsScreencast(z);
    }

    public void setVideoProcessor(@Nullable VideoProcessor videoProcessor) {
        synchronized (this.videoProcessorLock) {
            if (this.videoProcessor != null) {
                this.videoProcessor.setSink(null);
                if (this.isCapturerRunning) {
                    this.videoProcessor.onCapturerStopped();
                }
            }
            this.videoProcessor = videoProcessor;
            if (videoProcessor != null) {
                videoProcessor.setSink(new VideoSink() { // from class: org.webrtc.-$$Lambda$VideoSource$By5a8tcX7C2I01YFDRWTBDDPitI
                    @Override // org.webrtc.VideoSink
                    public final void onFrame(VideoFrame videoFrame) {
                        r0.runWithReference(new Runnable() { // from class: org.webrtc.-$$Lambda$VideoSource$OVJEwbdcZtQ5_7OAaMxeS3UMlpc
                            @Override // java.lang.Runnable
                            public final void run() {
                                VideoSource.this.nativeAndroidVideoTrackSource.onFrameCaptured(videoFrame);
                            }
                        });
                    }
                });
                if (this.isCapturerRunning) {
                    videoProcessor.onCapturerStarted(true);
                }
            }
        }
    }

    public CapturerObserver getCapturerObserver() {
        return this.capturerObserver;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getNativeVideoTrackSource() {
        return getNativeMediaSource();
    }

    @Override // org.webrtc.MediaSource
    public void dispose() {
        setVideoProcessor(null);
        super.dispose();
    }
}
