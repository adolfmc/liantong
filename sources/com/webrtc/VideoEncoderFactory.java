package com.webrtc;

import android.support.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface VideoEncoderFactory {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface VideoEncoderSelector {
        @CalledByNative("VideoEncoderSelector")
        @Nullable
        VideoCodecInfo onAvailableBitrate(int i);

        @CalledByNative("VideoEncoderSelector")
        void onCurrentEncoder(VideoCodecInfo videoCodecInfo);

        @CalledByNative("VideoEncoderSelector")
        @Nullable
        VideoCodecInfo onEncoderBroken();
    }

    @CalledByNative
    @Nullable
    VideoEncoder createEncoder(VideoCodecInfo videoCodecInfo);

    @CalledByNative
    VideoEncoderSelector getEncoderSelector();

    @CalledByNative
    VideoCodecInfo[] getImplementations();

    @CalledByNative
    VideoCodecInfo[] getSupportedCodecs();

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.webrtc.VideoEncoderFactory$-CC  reason: invalid class name */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public final /* synthetic */ class CC {
        @CalledByNative
        public static VideoEncoderSelector $default$getEncoderSelector(VideoEncoderFactory videoEncoderFactory) {
            return null;
        }
    }
}
