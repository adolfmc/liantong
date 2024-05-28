package com.webrtc;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class VideoEncoderFallback extends WrappedNativeVideoEncoder {
    private final VideoEncoder fallback;
    private final VideoEncoder primary;

    private static native long nativeCreateEncoder(VideoEncoder videoEncoder, VideoEncoder videoEncoder2);

    public VideoEncoderFallback(VideoEncoder videoEncoder, VideoEncoder videoEncoder2) {
        this.fallback = videoEncoder;
        this.primary = videoEncoder2;
    }

    @Override // com.webrtc.WrappedNativeVideoEncoder, com.webrtc.VideoEncoder
    public long createNativeVideoEncoder() {
        return nativeCreateEncoder(this.fallback, this.primary);
    }

    @Override // com.webrtc.WrappedNativeVideoEncoder, com.webrtc.VideoEncoder
    public boolean isHardwareEncoder() {
        return this.primary.isHardwareEncoder();
    }
}
