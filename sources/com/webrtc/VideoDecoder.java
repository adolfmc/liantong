package com.webrtc;

import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface VideoDecoder {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.webrtc.VideoDecoder$-CC  reason: invalid class name */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public final /* synthetic */ class CC {
        @CalledByNative
        public static long $default$createNativeVideoDecoder(VideoDecoder videoDecoder) {
            return 0L;
        }

        @CalledByNative
        public static void $default$foundSei(VideoDecoder videoDecoder, ByteBuffer byteBuffer) {
        }

        public static boolean $default$getPrefersLateDecoding(VideoDecoder videoDecoder) {
            return true;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface Callback {
        void onDecodedFrame(VideoFrame videoFrame, Integer num, Integer num2);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface VideoDecoderCallback {
        void onSEIRecv(ByteBuffer byteBuffer);
    }

    @CalledByNative
    long createNativeVideoDecoder();

    @CalledByNative
    VideoCodecStatus decode(EncodedImage encodedImage, DecodeInfo decodeInfo);

    @CalledByNative
    void foundSei(ByteBuffer byteBuffer);

    @CalledByNative
    String getImplementationName();

    boolean getPrefersLateDecoding();

    @CalledByNative
    VideoCodecStatus initDecode(Settings settings, Callback callback);

    @CalledByNative
    VideoCodecStatus release();

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Settings {
        public final int height;
        public final int numberOfCores;
        public final int width;

        @CalledByNative("Settings")
        public Settings(int i, int i2, int i3) {
            this.numberOfCores = i;
            this.width = i2;
            this.height = i3;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class DecodeInfo {
        public final boolean isMissingFrames;
        public final long renderTimeMs;

        public DecodeInfo(boolean z, long j) {
            this.isMissingFrames = z;
            this.renderTimeMs = j;
        }
    }
}
