package com.webrtc;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class VideoDecoderFallback extends WrappedNativeVideoDecoder {
    private final VideoDecoder fallback;
    private final VideoDecoder primary;

    private static native long nativeCreateDecoder(VideoDecoder videoDecoder, VideoDecoder videoDecoder2);

    public VideoDecoderFallback(VideoDecoder videoDecoder, VideoDecoder videoDecoder2) {
        this.fallback = videoDecoder;
        this.primary = videoDecoder2;
    }

    @Override // com.webrtc.WrappedNativeVideoDecoder, com.webrtc.VideoDecoder
    public long createNativeVideoDecoder() {
        return nativeCreateDecoder(this.fallback, this.primary);
    }
}
