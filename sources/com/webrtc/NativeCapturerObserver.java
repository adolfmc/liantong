package com.webrtc;

import com.webrtc.VideoFrame;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class NativeCapturerObserver implements CapturerObserver {
    private static VideoSink myHookSink;
    private final long nativeSource;
    private final SurfaceTextureHelper surfaceTextureHelper;

    private static native void nativeCapturerStarted(long j, boolean z);

    private static native void nativeCapturerStopped(long j);

    private static native void nativeOnFrameCaptured(long j, int i, int i2, int i3, long j2, VideoFrame.Buffer buffer);

    @CalledByNative
    public NativeCapturerObserver(long j) {
        this.nativeSource = j;
        this.surfaceTextureHelper = null;
    }

    public NativeCapturerObserver(long j, SurfaceTextureHelper surfaceTextureHelper) {
        this.nativeSource = j;
        this.surfaceTextureHelper = surfaceTextureHelper;
    }

    @Override // com.webrtc.CapturerObserver
    public void onCapturerStarted(boolean z) {
        nativeCapturerStarted(this.nativeSource, z);
    }

    @Override // com.webrtc.CapturerObserver
    public void onCapturerStopped() {
        nativeCapturerStopped(this.nativeSource);
    }

    public static void setMyHookSink(VideoSink videoSink) {
        myHookSink = videoSink;
    }

    @Override // com.webrtc.CapturerObserver
    public void onFrameCaptured(VideoFrame videoFrame) {
        VideoSink videoSink = myHookSink;
        if (videoSink != null) {
            videoSink.onFrame(videoFrame);
        }
        nativeOnFrameCaptured(this.nativeSource, videoFrame.getBuffer().getWidth(), videoFrame.getBuffer().getHeight(), videoFrame.getRotation(), videoFrame.getTimestampNs(), videoFrame.getBuffer());
    }

    public void dispose() {
        SurfaceTextureHelper surfaceTextureHelper = this.surfaceTextureHelper;
        if (surfaceTextureHelper != null) {
            surfaceTextureHelper.dispose();
        }
    }
}
