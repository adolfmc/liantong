package com.webrtc;

import android.media.MediaCodecInfo;
import android.support.annotation.Nullable;
import com.webrtc.EglBase;
import com.webrtc.Predicate;
import com.webrtc.VideoDecoder;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class PlatformSoftwareVideoDecoderFactory extends MediaCodecVideoDecoderFactory {
    private static final Predicate<MediaCodecInfo> defaultAllowedPredicate = new Predicate<MediaCodecInfo>() { // from class: com.webrtc.PlatformSoftwareVideoDecoderFactory.1
        @Override // com.webrtc.Predicate
        public /* synthetic */ Predicate<T> and(Predicate<? super T> predicate) {
            return Predicate.CC.$default$and(this, predicate);
        }

        @Override // com.webrtc.Predicate
        public /* synthetic */ Predicate<T> negate() {
            return Predicate.CC.$default$negate(this);
        }

        @Override // com.webrtc.Predicate
        /* renamed from: or */
        public /* synthetic */ Predicate<T> mo5298or(Predicate<? super T> predicate) {
            return Predicate.CC.$default$or(this, predicate);
        }

        @Override // com.webrtc.Predicate
        public boolean test(MediaCodecInfo mediaCodecInfo) {
            return MediaCodecUtils.isSoftwareOnly(mediaCodecInfo);
        }
    };

    @Override // com.webrtc.MediaCodecVideoDecoderFactory, com.webrtc.VideoDecoderFactory
    @Nullable
    public /* bridge */ /* synthetic */ VideoDecoder createDecoder(VideoCodecInfo videoCodecInfo) {
        return super.createDecoder(videoCodecInfo);
    }

    @Override // com.webrtc.MediaCodecVideoDecoderFactory, com.webrtc.VideoDecoderFactory
    public /* bridge */ /* synthetic */ VideoCodecInfo[] getSupportedCodecs() {
        return super.getSupportedCodecs();
    }

    @Override // com.webrtc.MediaCodecVideoDecoderFactory
    public /* bridge */ /* synthetic */ void setVideoDecoderCallback(VideoDecoder.VideoDecoderCallback videoDecoderCallback) {
        super.setVideoDecoderCallback(videoDecoderCallback);
    }

    public PlatformSoftwareVideoDecoderFactory(@Nullable EglBase.Context context) {
        super(context, defaultAllowedPredicate);
    }
}
