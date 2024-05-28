package org.webrtc;

import android.support.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface VideoDecoderFactory {
    @Nullable
    @Deprecated
    VideoDecoder createDecoder(String str);

    @CalledByNative
    @Nullable
    VideoDecoder createDecoder(VideoCodecInfo videoCodecInfo);

    @CalledByNative
    VideoCodecInfo[] getSupportedCodecs();

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.webrtc.VideoDecoderFactory$-CC  reason: invalid class name */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public final /* synthetic */ class CC {
        @Nullable
        @Deprecated
        public static VideoDecoder $default$createDecoder(VideoDecoderFactory videoDecoderFactory, String str) {
            throw new UnsupportedOperationException("Deprecated and not implemented.");
        }

        @CalledByNative
        public static VideoCodecInfo[] $default$getSupportedCodecs(VideoDecoderFactory videoDecoderFactory) {
            return new VideoCodecInfo[0];
        }
    }
}
