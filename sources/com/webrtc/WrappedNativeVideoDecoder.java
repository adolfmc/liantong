package com.webrtc;

import com.webrtc.VideoDecoder;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class WrappedNativeVideoDecoder implements VideoDecoder {
    @Override // com.webrtc.VideoDecoder
    public abstract long createNativeVideoDecoder();

    @Override // com.webrtc.VideoDecoder
    @CalledByNative
    public /* synthetic */ void foundSei(ByteBuffer byteBuffer) {
        VideoDecoder.CC.$default$foundSei(this, byteBuffer);
    }

    @Override // com.webrtc.VideoDecoder
    public /* synthetic */ boolean getPrefersLateDecoding() {
        return VideoDecoder.CC.$default$getPrefersLateDecoding(this);
    }

    @Override // com.webrtc.VideoDecoder
    public final VideoCodecStatus initDecode(VideoDecoder.Settings settings, VideoDecoder.Callback callback) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // com.webrtc.VideoDecoder
    public final VideoCodecStatus release() {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // com.webrtc.VideoDecoder
    public final VideoCodecStatus decode(EncodedImage encodedImage, VideoDecoder.DecodeInfo decodeInfo) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // com.webrtc.VideoDecoder
    public final String getImplementationName() {
        throw new UnsupportedOperationException("Not implemented.");
    }
}
