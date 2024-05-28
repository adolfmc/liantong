package com.webrtc;

import com.webrtc.VideoEncoder;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class WrappedNativeVideoEncoder implements VideoEncoder {
    @Override // com.webrtc.VideoEncoder
    public abstract long createNativeVideoEncoder();

    @Override // com.webrtc.VideoEncoder
    @CalledByNative
    public /* synthetic */ VideoEncoder.ResolutionBitrateLimits[] getResolutionBitrateLimits() {
        return VideoEncoder.CC.$default$getResolutionBitrateLimits(this);
    }

    @Override // com.webrtc.VideoEncoder
    public abstract boolean isHardwareEncoder();

    @Override // com.webrtc.VideoEncoder
    public final VideoCodecStatus initEncode(VideoEncoder.Settings settings, VideoEncoder.Callback callback) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // com.webrtc.VideoEncoder
    public final VideoCodecStatus release() {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // com.webrtc.VideoEncoder
    public final VideoCodecStatus encode(VideoFrame videoFrame, VideoEncoder.EncodeInfo encodeInfo) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // com.webrtc.VideoEncoder
    public final VideoCodecStatus setRateAllocation(VideoEncoder.BitrateAllocation bitrateAllocation, int i) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // com.webrtc.VideoEncoder
    public final VideoEncoder.ScalingSettings getScalingSettings() {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // com.webrtc.VideoEncoder
    public final String getImplementationName() {
        throw new UnsupportedOperationException("Not implemented.");
    }
}
