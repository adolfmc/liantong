package org.webrtc;

import android.media.MediaCodecInfo;
import android.support.annotation.Nullable;
import org.webrtc.EglBase;
import org.webrtc.Predicate;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class PlatformSoftwareVideoDecoderFactory extends MediaCodecVideoDecoderFactory {
    private static final Predicate<MediaCodecInfo> defaultAllowedPredicate = new Predicate<MediaCodecInfo>() { // from class: org.webrtc.PlatformSoftwareVideoDecoderFactory.1
        @Override // org.webrtc.Predicate
        public /* synthetic */ Predicate<T> and(Predicate<? super T> predicate) {
            return Predicate.CC.$default$and(this, predicate);
        }

        @Override // org.webrtc.Predicate
        public /* synthetic */ Predicate<T> negate() {
            return Predicate.CC.$default$negate(this);
        }

        @Override // org.webrtc.Predicate
        /* renamed from: or */
        public /* synthetic */ Predicate<T> mo199or(Predicate<? super T> predicate) {
            return Predicate.CC.$default$or(this, predicate);
        }

        @Override // org.webrtc.Predicate
        public boolean test(MediaCodecInfo mediaCodecInfo) {
            return MediaCodecUtils.isSoftwareOnly(mediaCodecInfo);
        }
    };

    @Override // org.webrtc.MediaCodecVideoDecoderFactory, org.webrtc.VideoDecoderFactory
    @Nullable
    public /* bridge */ /* synthetic */ VideoDecoder createDecoder(VideoCodecInfo videoCodecInfo) {
        return super.createDecoder(videoCodecInfo);
    }

    @Override // org.webrtc.MediaCodecVideoDecoderFactory, org.webrtc.VideoDecoderFactory
    public /* bridge */ /* synthetic */ VideoCodecInfo[] getSupportedCodecs() {
        return super.getSupportedCodecs();
    }

    public PlatformSoftwareVideoDecoderFactory(@Nullable EglBase.Context context) {
        super(context, defaultAllowedPredicate);
    }
}
