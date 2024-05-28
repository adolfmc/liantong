package com.webrtc;

import android.support.annotation.Nullable;
import com.webrtc.VideoEncoder;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
class VideoEncoderWrapper {
    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeOnEncodedFrame(long j, EncodedImage encodedImage);

    VideoEncoderWrapper() {
    }

    @CalledByNative
    static boolean getScalingSettingsOn(VideoEncoder.ScalingSettings scalingSettings) {
        return scalingSettings.f21270on;
    }

    @CalledByNative
    @Nullable
    static Integer getScalingSettingsLow(VideoEncoder.ScalingSettings scalingSettings) {
        return scalingSettings.low;
    }

    @CalledByNative
    @Nullable
    static Integer getScalingSettingsHigh(VideoEncoder.ScalingSettings scalingSettings) {
        return scalingSettings.high;
    }

    @CalledByNative
    static VideoEncoder.Callback createEncoderCallback(final long j) {
        return new VideoEncoder.Callback() { // from class: com.webrtc.-$$Lambda$VideoEncoderWrapper$hpBzms_AX37ENfchIIBmNdxPFYM
            @Override // com.webrtc.VideoEncoder.Callback
            public final void onEncodedFrame(EncodedImage encodedImage, VideoEncoder.CodecSpecificInfo codecSpecificInfo) {
                VideoEncoderWrapper.nativeOnEncodedFrame(j, encodedImage);
            }
        };
    }
}
